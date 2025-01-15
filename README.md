### E-Commerce Website Automation Testing project for Avactis website
![Last Commit](https://img.shields.io/github/last-commit/anaghakarurkar/Avactis) ![GitHub repo size](https://img.shields.io/github/repo-size/anaghakarurkar/Avactis)

## Overview

This project is developed to automate testing for an e-commerce website. It ensures the functionality and reliability of the website are thoroughly tested. The project leverages industry-standard tools and frameworks to deliver a robust and efficient testing suite.

## Technologies Used

- **Selenium**: Used for browser automation to simulate user interactions with the website.

- **Java**: The programming language used to implement the test scripts and frameworks.

- **TestNG Framework**: Utilized for test execution and management, including annotations, grouping, and assertions.

- **Log4j**: For logging application activities and debugging information.

- **Allure Reports**: For generating detailed and user-friendly test reports.

- **Jenkins**: Used for Continuous Integration (CI) to automate test execution and integrate with the development lifecycle.

## Features

- Comprehensive test coverage for core functionalities of the e-commerce website.

- Detailed test execution reports with logs and visual representation of results.

- Integration with Jenkins for automated test execution.

- Modular and maintainable test design using TestNG.

## Prerequisites

- Java Development Kit (JDK) installed and configured.

- Maven or Gradle for project build and dependency management.

- Jenkins setup for CI/CD pipeline.

- Supported browsers (e.g., Chrome, Firefox) and corresponding WebDriver executables.

## Setup Instructions

<ol>
  <li><p>Clone the repository:</p> <p><strong>  git clone</strong></p></li>
  <li><p>Navigate to the project directory:</p><p><strong>  cd ecommerce-automation-testing</strong></p></li>
  <li> <p>Install dependencies using Maven:</p> <p><strong>  mvn clean install</strong></p></li>
  <li>Configure the testng.xml file for the test suite.</li>
  <li><p>Execute tests:</p> <p><strong>  mvn test</strong></p></li>
  <li><p>View test results in the Allure Reports:</p> <p><strong>  allure serve target/allure-results</strong></p> </li>
</ol>





