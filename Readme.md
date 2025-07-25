# FirstClub Membership Program

This is the backend system for the FirstClub Membership Program, a subscription-based platform offering tiered benefits to users.

## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
  - [User Endpoints](#user-endpoints)
  - [Membership Plan Endpoints](#membership-plan-endpoints)
  - [Membership Subscription Endpoints](#membership-subscription-endpoints)
- [Services](#services)
- [Exception Handling](#exception-handling)
- [Object-Oriented Programming (OOP) Principles](#object-oriented-programming-oop-principles)
- [Database Schema](#database-schema)
- [Setup and Running the Project](#setup-and-running-the-project)

---
## Project Overview

This project is a Spring Boot application that provides the backend services for a membership program. It allows users to subscribe to different membership plans, each with its own set of benefits and tiers. The system is designed to be scalable and maintainable, following best practices for modern web application development.

---

## Technologies Used

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database**
- **Maven**
- **Lombok**
---

## Project Structure

The project follows a standard layered architecture:

-   **Controllers**: Handle incoming HTTP requests and delegate to the service layer.
-   **Services**: Contain the business logic of the application.
-   **Repositories**: Provide an abstraction over the database and handle data access.
-   **Models**: Represent the data entities of the application.
-   **DTOs**: Data Transfer Objects used to transfer data between layers.
-   **Exceptions**: Custom exception classes for handling application-specific errors.

---

## API Endpoints

### User Endpoints

**Base Path**: `/api/v1/users`

| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/` | Creates a new user. |
| `GET` | `/{userId}` | Retrieves a user by their ID. |
| `GET` | `/` | Retrieves a list of all users. |
| `PUT` | `/{userId}` | Updates an existing user's information. |
| `DELETE` | `/{userId}` | Deletes a user by their ID. |

### Membership Plan Endpoints

**Base Path**: `/api/v1/plans`

| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/` | Creates a new membership plan. |
| `GET` | `/{planId}` | Retrieves a membership plan by its ID. |
| `GET` | `/` | Retrieves a list of all membership plans. |
| `PUT` | `/{planId}` | Updates an existing membership plan. |
| `DELETE` | `/{planId}` | Deletes a membership plan by its ID. |

### Membership Subscription Endpoints

**Base Path**: `/api/v1/memberships`

| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/plans` | Retrieves a list of all membership plans. |
| `POST` | `/subscribe` | Subscribes a user to a membership plan. |
| `GET` | `/user/{userId}` | Retrieves the active membership for a user. |
| `PUT` | `/user/{userId}/change-plan` | Changes the membership plan for a user. |
| `DELETE` | `/user/{userId}/cancel` | Cancels a user's membership subscription. |

---

## Services

-   **`UserService`**: Manages user-related operations such as creating, retrieving, updating, and deleting users.
-   **`MembershipPlanService`**: Handles the logic for managing membership plans, including creation, retrieval, updates, and deletion.
-   **`MembershipService`**: Manages membership subscriptions, including subscribing users to plans, changing plans, and canceling subscriptions.

---

## Exception Handling

-   **`InvalidRequestException`**: Thrown when a request is malformed or contains invalid data. It results in an HTTP `400 Bad Request` response.
-   **`ResourceNotFoundException`**: Thrown when a requested resource (e.g., a user or membership plan) is not found. It results in an HTTP `404 Not Found` response.
-   **`DatabaseException`**: A generic exception for database-related errors, resulting in an HTTP `500 Internal Server Error` response.

---

## Object-Oriented Programming (OOP) Principles

-   **Encapsulation**: The models (`User`, `MembershipPlan`, `UserMembership`) encapsulate data and behavior.
-   **Abstraction**: The service layer uses interfaces (`UserService`, `MembershipPlanService`, `MembershipService`) to define contracts for business logic, hiding the implementation details.
-   **Inheritance**: The custom exception classes inherit from `RuntimeException`.
-   **Polymorphism**: Spring's dependency injection and the use of interfaces allow for polymorphism.

---

## Database Schema

-   **`User`**:
    -   `userId` (Primary Key)
    -   `name`
    -   `email` (Unique)
    -   `phone`
    
-   **`MembershipPlan`**:
    -   `planId` (Primary Key)
    -   `plan`
    -   `tier`
    -   `tierLevel`
    -   `price`
    -   `durationInDays`
    -   `freeDelivery`
    -   `extraDiscount`
    -   `exclusiveDeals`
    -   `prioritySupport`
    
-   **`UserMembership`**:
    -   `membershipId` (Primary Key)
    -   `user` (Foreign Key to `User`)
    -   `plan` (Foreign Key to `MembershipPlan`)
    -   `active`
    -   `startDate`
    -   `endDate`

---

## Setup and Running the Project

To run this project locally, you will need:

-   Java 17 or later
-   Apache Maven

**Steps:**

1.  **Clone the repository.**
2.  **Navigate to the project directory.**
3.  **Build the project:**
    ```bash
    ./mvnw clean install
    ```
4.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

The application will start on port `8080`. The H2 database console is available at `http://localhost:8080/h2-console`.

## Note

This project does not include a payment service. The focus is on the membership management functionality. In a real-world application, a separate payment service would be integrated to handle transactions.

