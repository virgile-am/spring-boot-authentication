
# Spring Boot Authentication & PKI Implementation

## Project Overview

This project demonstrates a secure Spring Boot web application that incorporates different authentication methods (basic and token-based) and a simple Public Key Infrastructure (PKI) setup. The project also includes HTTPS configuration to ensure secure communication.

### Features

- **Basic Authentication:** Traditional username and password-based authentication.
- **Token-Based Authentication:** JSON Web Token (JWT) used for stateless authentication.
- **Public Key Infrastructure (PKI):** Generation of a self-signed certificate for securing communications.
- **HTTPS Configuration:** Secure HTTP communication using the generated certificate.

## Lab Objectives

- Implement basic and token-based authentication.
- Understand and implement PKI concepts.
- Configure and enforce HTTPS for secure communication.

## Prerequisites

Before you begin, ensure you have the following installed:

- [Java 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Keytool](https://docs.oracle.com/en/java/javase/21/tools/keytool.html) (included with the JDK)

## Setup & Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/spring-boot-authentication.git
cd spring-boot-authentication
```

### Step 2: Generate the Keystore

Use the following command to generate a keystore with a self-signed certificate:

```bash
keytool -genkeypair -alias mycert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 365
```

Fill in the required information such as `CN`, `OU`, `O`, `L`, `ST`, and `C`.

### Step 3: Update Application Properties

Edit the `application.properties` file with your JWT secret and keystore details:

```properties
# JWT Properties
jwt.secret=mySecretKey
jwt.expiration=3600000

# HTTPS Configuration
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your_password
server.ssl.keyStoreType=PKCS12
server.ssl.keyAlias=mycert
```

### Step 4: Build and Run the Project

Use Maven to build and run the project:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `https://localhost:8080`.

## Usage

### Register a User

To register a new user, send a POST request to `/auth/register`:

```bash
curl -X POST https://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"testuser", "password":"testpassword"}'
```

### Login a User

To authenticate a user and receive a JWT, send a POST request to `/auth/login`:

```bash
curl -X POST https://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"testuser", "password":"testpassword"}'
```

### Access Secure Endpoints

Use the received JWT token to access secured endpoints:

```bash
curl -X GET https://localhost:8080/secure-endpoint \
-H "Authorization: Bearer your_jwt_token"
```

## Testing

Ensure all functionality works by following these steps:

1. Register a user.
2. Login with the registered user and obtain a JWT.
3. Access a secure endpoint using the JWT.
4. Verify HTTPS is enforced by attempting to access the application over HTTP.

 

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README provides clear instructions and information about the project, making it easy for anyone to understand and set up the application.