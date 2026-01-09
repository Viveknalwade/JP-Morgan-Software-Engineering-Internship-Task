# Task One: Project Setup
Set up your local development environment and get acquainted with the project codebase
## What you'll learn
How Software Engineers build and configure backend systems used to process high-volume financial transactions.
How to set up a Java development environment using Java 17, Spring Boot, Maven, and an IDE that supports enterprise projects.
How to work with engineering requirements and prepare a project scaffold for future integration tasks.
## What you'll do
Set up your local development environment by installing Java 17, forking and cloning the project repository, and opening it in your IDE.
Explore the existing project scaffold to understand how the Midas Core service is structured.
Add the required dependencies to your Spring Boot project and update configuration files.
Build and run the project, then verify your setup by running automated tests.

## Here is the background information on your task
Your team has been tasked with building the Midas system, a high-profile initiative responsible for processing financial transactions at scale. Completing this project successfully would be a major achievement for your engineering career.

In this program, you will focus on Midas Core — the service responsible for receiving, validating, and recording financial transactions. Midas Core relies on several external systems:

### Kafka to receive new transactions
- A SQL database to validate and store data
- A REST API to expose processed information
- A teammate has already created a basic project scaffold following the system architecture. While some initial classes exist, much of the implementation is left to you. The project uses Spring Boot, which provides a powerful dependency injection framework and simplifies connecting to external infrastructure.

Your first task is to prepare your local environment, review the scaffold, and add important project dependencies before development begins.

1. Fork and Clone the Repository

Fork and clone the project repo, which already contains the scaffold:

- 🔗 https://github.com/vagabond-systems/forage-midas
- git clone https://github.com/your-username/forage-midas.git

cd forage-midas

2. Open the Project in Your IDE

Open the project in your preferred IDE.
You will have the easiest experience with an IDE that supports Spring Boot or Maven projects, such as IntelliJ IDEA.
If you need an IDE, IntelliJ Community Edition is free, well-supported, and widely used in the industry.

3. Install and Configure Java 17

This project requires Java 17.

You can install it using one of the following methods:

- Download directly from Oracle:
- https://www.oracle.com/au/java/technologies/downloads/#java17
- Install via SDKMAN!:
- https://sdkman.io/
- Confirm your installation:

- java -version
Configure your IDE to use the Java 17 JDK.
4. Familiarize Yourself With the Codebase

Spend a few minutes exploring the project structure.
Take note of:

- The src directory
- Existing classes in the scaffold
- Configuration files
- Test files
- Understanding what already exists will help you complete upcoming tasks.

5. Add Required Dependencies

Add the following dependencies to your Spring Boot project, ensuring each is pinned to the specified version:

Dependency	Group	Version
- spring-boot-starter-data-jpa	| org.springframework.boot	| .2.5
- spring-boot-starter-web	| org.springframework.boot	| 3.2.5
- spring-kafka	| org.springframework.kafka	| 3.1.4
- h2	| com.h2database	| 2.2.224
- spring-boot-starter-test	| org.springframework.boot	| 3.2.5
- spring-kafka-test	| org.springframework.kafka	| 3.1.4
- kafka (Testcontainers)	| org.testcontainers	| 1.19.1
Add these entries to your pom.xml.

6. Update Application Configuration

Open application.yml and add the following property:

- general:

 kafka-topic: trader-updates

7. Build and Run the Project

Use Maven to build and start the application:

- mvn clean install
- mvn spring-boot:run
8. Run the Automated Tests

Once your setup is complete, run the Task 1 tests.

Run all tests:

- mvn test
- Run a single test:
- mvn -Dtest=TaskOneTests test

At the end of the test output, you will see a snippet marked with BEGIN and END.
Copy this snippet and submit it as your Task 1 answer.

# Output
- ---begin output ---
- 1142725631254665682354316777216387420489
- ---end output ---
