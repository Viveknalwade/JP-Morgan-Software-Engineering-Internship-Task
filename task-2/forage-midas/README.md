# Kafka Integration 
## Task Overview

### What you'll learn
- How message queues—specifically Kafka—are used to decouple services, improve scalability, and enable asynchronous communication in large backend systems.
- How to integrate Kafka into a Spring Boot application by creating a listener that consumes messages from a configured topic.
- How to deserialize incoming Kafka messages into domain objects and verify your integration using an embedded Kafka instance and automated tests.
### What you'll do
- Implement a Kafka listener in Midas Core that reads from the topic defined in application.yml and deserializes each incoming message into the provided Transaction class.
- Configure your listener to use the project’s existing Spring Boot setup—no need to specify host/port, as the tests use an embedded Kafka instance.
- Run TaskTwoTests, use your debugger to inspect the first four received transactions, and record the amounts attached to each.
- Submit the list of the four transaction amounts once your listener is working correctly.

## Here is the background information on your task
Your team has decided to place a message queue between the frontend(s) and backend of Midas. Such a queue constitutes an additional layer of indirection between the components it interacts with. There are several benefits to such an architecture, chief among which include:

### Decoupling - 
with a message queue wedged between them, the frontend(s) and backend of Midas are entirely decoupled - modifying one is guaranteed not to affect the other.
### Asynchronous communication - 
since messages are allowed to remain in the queue for a configurable period of time, the backend does not necessarily need to keep up in lockstep with the frontend. Consider the possibility that a burst of activity momentarily overloads the backend. In such a case, users could continue to conduct transactions, which would simply be appended to the back of the queue while the backend takes its time chewing through the collection.
### Scalability - 
depending on the implementation, a message queue can have multiple producers and multiple consumers. Such an implementation makes scaling both sides of the equation trivial: multiple producers have no issue writing to the same queue, which effectively acts as a load balancer for multiple consumers.

Your team has selected Kafka as an implementation - an excellent message queue which many consider the de facto choice when it comes to large, enterprise-grade applications. It’s a robust, battle-tested technology that scales easily, integrates seamlessly with just about any stack, and is backed by both the Apache Foundation and a host of clever open source contributors. Your task is to integrate Kafka into Midas Core.

### Here is your task
Midas Core needs a way to receive all incoming transactions. To this end, you must implement a class that listens to a Kafka topic and handles incoming messages. The name of the topic in question has already been added as a configurable value in the project's application.yml file. The Kafka Listener you implement should use this configuration value to select its topic. Your Kafka Listener should deserialize all incoming messages to the provided transaction class. Your goal for this task is simply to integrate Kafka into Midas Core; no need to do anything with the transactions yet. That comes later. The provided tests use an in-memory, embedded Kafka instance that should autowire itself into your Spring Application, so there is no need to specify a host or port in your consumer configuration.

Once you are successfully receiving transactions, execute "TaskTwoTests" in the test folder of the repo and use your debugger to record the amount attached to the first four transactions received by Midas Core. Once you have them noted down, submit the list below. 

## Procedure
 1. Add Required Kafka Dependencies
 2. Update application.yml
 3. Implement the Kafka Listener (src/main/java/com/jpmc/midascore/kafka/TransactionListener.java)
 4. Update MidasCoreApplication(@EnableKafka)
 5. Clean & Run (IMPORTANT ORDER) -
    - mvn clean
    - mvn -Dtest=TaskTwoTests test
 6. After Run , notice that 
    - This test is INTENTIONALLY INFINITE
    - The test will not stop by itself
    - It keeps producing transactions every few seconds
    - JPMorgan expects you to manually observe values
    - Then stop the test yourself
 7. In TransactionListener.java , Put a breakpoint(red dot) on this line:
    - RECEIVED_TRANSACTIONS.add(transaction);
    - after breakpoint you will see the red dot on that line in the left side
 8. Run test in DEBUG mode
    - Go to: src/test/java/com/jpmc/midascore/TaskTwoTests.java
    - Click Debug
 9. When debugger stops
    - You’ll see a Variables panel
    - Look for: transaction
    - Expand it → check: amount
    - 📝 Write it down
 10. Continue execution
    - Breakpoint hits again
    - Capture next amount
    - Do this 4 times
 11. Stop the test
