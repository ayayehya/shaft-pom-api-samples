# API Test Cases using SHAFT Engine and POM Design Pattern

This repository contains sample test cases for various API endpoints. The tests are implemented using **SHAFT Engine** and follow the **Page Object Model (POM)** design pattern for better code organization and maintainability.

---

## 📋 Project Description

The purpose of this project is to demonstrate how to write automated test cases for REST APIs using the **SHAFT Engine**. The following sample APIs are covered:

1. **Users API**: Returns a list of 10 random users.  
   Endpoint: `https://fake-json-api.mock.beeceptor.com/users`  
2. **Companies API**: Provides a list of 10 random companies.  
   Endpoint: `https://fake-json-api.mock.beeceptor.com/companies`  
3. **To-Do Tasks API**: Fetches a list of to-do tasks.  
   Endpoint: `https://dummy-json.mock.beeceptor.com/todos`  
4. **Posts API**: Retrieves a list of blog posts.  
   Endpoint: `https://dummy-json.mock.beeceptor.com/posts`  
5. **Continents API**: Returns information about continents.  
   Endpoint: `https://dummy-json.mock.beeceptor.com/continents`  

---

## 🚀 Tools and Technologies

- **Programming Language**: Java  
- **Automation Framework**: SHAFT Engine  
- **Design Pattern**: Page Object Model (POM)  
- **Testing Framework**: TestNG  

---

## 📂 Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── pages/         # Page classes for each API endpoint
├── test/
│   ├── java/
│   │   └── tests/         # Test classes for API endpoints
└── resources/
    └── testng.xml         # TestNG configuration file
