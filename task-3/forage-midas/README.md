# Task 3: H2 Integration
## Task Overview
### What you'll learn
- How to integrate a SQL database into a Spring Boot application using H2 and Spring Data JPA.
- How backend systems validate financial transactions and enforce business rules before persisting data.
- How to model relational data using JPA entities, including one-to-many and many-to-one relationships.
- How to combine data ingestion (Kafka) with database persistence in a cohesive service flow.
### What you'll do
- Configure Midas Core to use an H2 in-memory database through Spring Boot and JPA.
- Implement validation logic to determine whether a transaction is valid based on user IDs and account balances.
- Create a TransactionRecord JPA entity and persist valid transactions while discarding invalid ones.
- Update the sender and recipient balances when transactions are successfully processed.

Run TaskThreeTests, inspect the final balance of the waldorf user in your debugger, and submit the rounded-down value.
## Here is the background information on your task
The next step in the development of Midas Core is the integration of a database to record transactions. Such a step immediately raises the question of which one to incorporate. There are quite a few options when it comes to picking a storage solution, but they generally fall into two broad categories: SQL and NoSQL. As a rule of thumb, when dealing with financial data, you should use a SQL database because they almost always offer stronger guarantees when it comes to failure scenarios. While NOSQL databases are often faster and have better search features, SQL databases shine in their robustness. When money is involved, performance is a worthy sacrifice for resiliency to data loss when things go awry.

The question then becomes which of the many SQL offerings to choose from? In the interest of easing development, you will use H2, which has strong out-of-the-box Spring support and can be used as an in-memory database. While an in-memory database simplifies local development considerably, it is not suited for production. Fortunately, since you will integrate with the database using JPA, the actual database backend is abstracted away by Spring. It should require minimal effort for JPMC's tech-ops team to point your app at an existing database when it comes time to deploy to production.

## Here is your task
Your next task is to integrate Midas Core with an H2 database. Whenever a transaction is received via Kafka, Midas Core should validate and record it to the database. A transaction is considered valid if the following are true:

- The senderId is valid
- The recipientId is valid
- The sender has a balance greater than or equal to the transaction amount

If the above conditions are met, the transaction should be recorded to the database, and both the sender and recipient balances should be adjusted accordingly. If the conditions are not met, the transaction should be discarded with no modification to the database. Transaction entities should maintain a many-to-one relationship with their respective sender and recipient User entities (hint: this will necessitate creating a new TransactionRecord class with an @entity annotation rather than modifying the existing Transaction class). When you are finished, execute "TaskThreeTests" and use your debugger to record the balance of the "waldorf" user after all transactions have been processed (rounded down to the nearest integer). When you figure it out, submit the number below. 

## Procedure
1. Add Required H2 Dependencies
2. Update application.yml
3. Create TransactionRecord Entity (src/main/java/com/jpmc/midascore/entity/TransactionRecord.java)
4. Create Repository for TransactionRecord (src/main/java/com/jpmc/midascore/repository/TransactionRecordRepository.java)
5. Update DatabaseConduit.java for database logic
6. Update Kafka Listener
7. Clean & Run (IMPORTANT ORDER) -
- mvn clean
- mvn -Dtest=TaskThreeTests test
8. After Run , notice that
- This test is INTENTIONALLY INFINITE
- The test will not stop by itself
- It keeps producing transactions every few seconds
- JPMorgan expects you to manually observe values
- Then stop the test yourself
9. In DatabaseConduit.java , Put a breakpoint(red dot) on this line:
Optional<UserRecord> senderOpt =
        userRepository.findById(transaction.getSenderId());
- after breakpoint you will see the red dot on that line in the left side
10. Run test in DEBUG mode
- Go to: src/test/java/com/jpmc/midascore/TaskThreeTests.java
- Click Debug
- When debugger stops
- You’ll see a Variables panel
- Look for: sender,recipient,amount,transaction
- Expand it → check: sender and you will see name and value called "waldorf"
- 📝 Write it down
- Continue execution - Breakpoint hits again - Capture next name - Do this if you didnt get the name "waldorf"
- Stop the test

### output 
Waldorf balance : 522 (it may varies from different debugging perspective)
