# Task 4 & 5 
## REST API Integration & REST API Controller

### What you'll learn
- How backend services consume external REST APIs as part of a larger system architecture.
- How to use Spring’s RestTemplate to send POST requests and deserialize JSON responses into Java objects.
- How API boundaries act as contracts between teams, enabling independent development and safer system changes.
- How to incorporate external incentive logic into an existing transaction-processing workflow.
### What you'll do
- Run the provided Incentive API service locally and connect Midas Core to its /incentive endpoint.
- Implement a method that posts validated Transaction objects to the Incentive API and receives an Incentive response.
- Update your transaction-processing logic to store the incentive amount and correctly adjust user balances—adding incentives to recipients but not subtracting them from senders.
- Run TaskFourTests, use your debugger to determine wilbur’s final balance, and submit the rounded-down result.

### Here is the background information on your task
The Incentive API is quite simple - it exposes a single endpoint that returns incentive information when passed data about a transaction. Your team doesn't know or care about the logic that determines incentives - it has been decoupled from the part of the system you are working on, and can therefore be treated like a black box. By designing the system as two separate components, a change to one can be made without affecting the other. Furthermore, since the two units of behavior are being worked on by separate teams, articulating the system as two separate components (and therefore two separate codebases) allows each team to maintain ownership over the behavior they care about. One team can make changes to their component without affecting the other, provided the API does not change. Therefore, the REST API acts as a contract between the two teams/components, provided the contract isn't broken, one component can be modified with the guarantee that it will not affect the other.

A REST API was chosen to serve incentive information, since the interactions between the two components strongly resemble a client-server relationship. The communication between the two can be modeled with a request/response pattern, so a REST API is the obvious choice. They are a ubiquitous pattern, simple to implement, and easy to use. Fortunately for you, the incentives API has already been built - all you have to do is integrate it into Midas Core. Good luck!

### Here is your task
Your task is to integrate the incentives API with Midas Core. An executable JAR containing a copy of the incentives API controller, which runs on local port 8080, has been included in the services folder of the project repo. The API has a single endpoint “/incentive” which accepts JSON POST requests and can be reached at "http://localhost:8080/incentive" (when the aforementioned jar is running). This endpoint expects a JSON serialized Transaction object which exactly mirrors the Transaction class provided in the project repo (hint: you should let Spring take care of serialization and simply pass a Transaction object to the method you call on your RestTemplate). The endpoint will respond with a JSON serialized Incentive object which has a single field: “amount.”

After a transaction is validated, it should be posted to the incentive API. The incentive API will respond with an amount >= 0, which should be recorded alongside the transaction amount in a new incentive field. When modifying user balances, the incentive should be added to the recipient’s balance, but should not be deducted from the sender’s balance. Once you have finished integrating the incentive API, run “TaskFourTests” and use your debugger to figure out the balance of the “wilbur” user after all transactions have been processed. Once you know, submit the number below, rounded down to the nearest integer. 

### What you'll learn
- How to expose REST API endpoints within a Spring Boot application and return JSON responses.
- How to design simple, user-facing APIs that surface data from a backend system.
- How to apply architectural reasoning when adding new functionality to an existing service.
- How REST controllers integrate alongside other application components such as Kafka listeners and database layers.
### What you'll do
- Create a Spring REST controller inside Midas Core that exposes a GET /balance endpoint.
- Accept a userId as a request parameter, look up the corresponding user, and return a JSON-serialized Balance object (defaulting to 0 if the user does not exist).
- Configure your Spring application to run on port 33400 and ensure the new endpoint works alongside the rest of Midas Core.
- Run TaskFiveTests with the Incentive API running, and submit the output snippet containing the BEGIN and END markers.

### Here is the background information on your task
Users need a way to query their current balance - not allowing them to do so would surely result in questionable financial decisions and misguided confidence in one's own economic standing. Science, not hilarity, however, is the goal of the Midas project, so you have been tasked with making account balances visible to their respective users. From an architectural perspective, there are two broad ways to go about adding this behavior. You could simply tack it on to Midas Core, but that component currently has a single broad task - handle incoming transactions. Adding a REST controller for viewing user balances on top of such a component would muddy its purpose. The other option is to add a new component - another small Spring application - to surface user balances. While this would lead to a slightly cleaner architecture, it would also mean an entirely new component to deploy and support. This is a classic example of an architectural question without an obvious answer.

In this case, the decision has been made to add the functionality to Midas Core. Since Spring makes it quite easy to add a REST controller to your application and the feature request is relatively minor, the decreased development time and deployment burden outweigh the benefits gained from a slightly cleaner architecture. If the added behavior evolves into something more complex in the future, it will require minimal effort to move the functionality to a different component. The decision to extract the behavior to a new component will be made later when more information about the future of the system is available. For example, if more endpoints need to be added that surface information from the database, that would be a good indicator that it’s time to break Midas Core into two separate components. Good software architecture is about elegance and balance - factors like development time and deployment burden should be just as much a part of your design calculus as clean organization.

### Here is your task
Your final task is to expose a REST API for querying user balances. The API controller must expose a “/balance” endpoint that responds exclusively to GET requests, accepts a userId as a request parameter, and returns an instance of the provided Balance class serialized to JSON. Your spring application should expose this API on port 33400. If a user does not exist, the endpoint should return a balance of 0. You should integrate this REST Controller directly into Midas Core - it should run alongside the Kafka listener you've already implemented. When you’re finished, run “TaskFiveTests” and submit the output (including the begin and end tags) below. Do not modify the Balance class’ toString() implementation, or verification will fail. Be sure to have the Incentive API running when you execute the test. 

