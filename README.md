# hexagonal-architecture-gradle-java
An exploration of implementing hexagonal architecture in a simple Java application using Gradle to enforce dependencies.

Using Gradle, the application is divided into 4 parts, that can only 'see' the classes that they're allowed to see.

- App creates the application and injects the right dependencies.
- Core contains the business logic without knowing anything about 'the outside world'.
- There's a driving adapter that responds to the outside world providing input.
- There's a driven adapter that can be instructed to store something in the outside world.

Everything is written with a minimal amount of frameworks.
So no Spring, Jackson, Mockito, database, etc.

As it is set up using Gradle, you can open this project in your IDE and for example won't need to worry about accidentally using a utility class from a module you're not supposed to access.

An ArchUnit test is included to show that there is another way to enforce hexagonal architecture using tests, though its added value here is minimal.
Were you to use it instead of the multimodule Gradle setup, the current ArchUnit implementation would have to be improved as currently it doesn't prevent adapters from calling each other's code, for example.
