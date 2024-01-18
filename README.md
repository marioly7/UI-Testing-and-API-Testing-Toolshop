# UI TESTING AND API TESTING TOOLSHOP
## [Toolshop](https://practicesoftwaretesting.com/#/) Purchase Products Tests 

This project executes tests for different scenarios within the main flux (Purchase Products) of the Toolshop website.

## Scenarios

- Purchase Products Successful
- Purchase Products Failed Due to Invalid Payment Details
- Purchase Products Failed Due to Empty Payment Details
- Login Failed Due to Invalid Fields
- Login Failded Due to Empty Fields
- Register Failed Due to Invalid Fields
- Register Failed Due to Empty Fields

## Tech Stack

This project utilizes the following technologies:

- [JavaScript](https://developer.mozilla.org/en/docs/Web/JavaScript) - The language used by Postman for creating test scripts.
- [Postman](https://www.postman.com/) - A platform for writing and executing test scripts for API testing.
- [Java](https://www.java.com/en/) - Includes the steps for executing the features.
- [Selenium](https://www.selenium.dev/) - Helps automate tests by interacting with web elements.
- [TestNG](https://testng.org/) - A framework used to validate test assertions.
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The preferred integrated development environment (IDE).

## How to Execute the Tests

The Java + Selenium + TestNG code requires SDK version 17 to run.

The Java + Selenium + TestNG code requires the latest Chrome and Firefox drivers. They can be uploaded (if necessary) to the `resources` directory.

All the necessary dependencies are already listed in the `pom.xml` file.

### Java, Selenium and TestNG Project ☕

To run the project, execute the `test-suite.xml` file by right-clicking it and selecting "Run '...\test-suite.xml'".

### Postman Project 📨

Executing the tests on Postman requires the installation of Postman Desktop and importing the `Toolshop.postman_collection.json` file.

> Note: `postman_collection > Toolshop.postman_collection.json`

After importing, run the collection by right-clicking on the `Toolshop` collection and selecting "Run Collection".
