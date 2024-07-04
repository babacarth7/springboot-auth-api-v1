# Simple CRUD Application with JWT Authentication

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Introduction
This is a simple CRUD (Create, Read, Update, Delete) application built with Spring Boot and MySQL, featuring JWT (JSON Web Token) authentication. It allows users to securely perform CRUD operations.

## Features
- User registration and login with JWT authentication
- Secure CRUD operations
- Database integration with MySQL

## Technologies Used
- Java
- Spring Boot
- MySQL
- JWT (JSON Web Tokens)
- Maven

## Setup

### Prerequisites
- JDK 8 or higher
- Maven
- MySQL

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/babacarth7/springboot-auth-api-v1.git
   cd springboot-auth-api-v1
   ```

2. Configure the MySQL database:
   - Create a database in MySQL.
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Build the project:
   ```sh
   mvn clean install
   ```

4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage
After running the application, you can use tools like Postman or curl to interact with the API.

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate a user and receive a JWT

### CRUD Operations
- `GET /api/users/` - Get all users
- `GET /api/users/me` - Get a single user

## Authentication
This application uses JWT for authentication. Users must register and log in to obtain a token. The token must be included in the Authorization header of subsequent requests to access protected routes.

Example:
```http
Authorization: Bearer your-jwt-token
```

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [JWT](https://jwt.io/)
