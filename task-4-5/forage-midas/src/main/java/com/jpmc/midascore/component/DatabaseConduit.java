package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRecordRepository transactionRecordRepository;
    private final IncentiveClient incentiveClient;

    public DatabaseConduit(UserRepository userRepository,TransactionRecordRepository transactionRecordRepository,IncentiveClient incentiveClient) {
        this.userRepository = userRepository;
        this.transactionRecordRepository = transactionRecordRepository;
        this.incentiveClient = incentiveClient;
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

        @Transactional
        public void process(Transaction transaction) {

       Optional<UserRecord> senderOpt =
        userRepository.findById(transaction.getSenderId());

        Optional<UserRecord> recipientOpt =
        userRepository.findById(transaction.getRecipientId());

if (senderOpt.isEmpty() || recipientOpt.isEmpty()) {
    return;
}

UserRecord sender = senderOpt.get();
UserRecord recipient = recipientOpt.get();

        float amount = transaction.getAmount();

        // validate balance
        if (sender.getBalance() < amount) {
            return;
        }

          // 🔹 Call Incentive API
    float incentiveAmount = 0f;
    try {
        incentiveAmount = incentiveClient
                .fetchIncentive(transaction)
                .getAmount();
    } catch (Exception e) {
        // If incentive API fails, treat incentive as 0
        incentiveAmount = 0f;
    }

        // update balances
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount + incentiveAmount);

        userRepository.save(sender);
        userRepository.save(recipient);

        TransactionRecord record =
                new TransactionRecord(sender, recipient, amount, incentiveAmount);

        transactionRecordRepository.save(record);
    }
}
