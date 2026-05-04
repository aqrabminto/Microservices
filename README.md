# 🧠 Patient Management System
The Patient Management System is a comprehensive platform designed to manage patient data, billing, and analytics. It consists of multiple microservices, including the patient service, billing service, auth service, and analytics service, all of which are integrated through an API gateway. The system provides a robust and scalable solution for healthcare providers to manage patient information, track billing and payments, and gain insights into patient behavior and treatment outcomes.

## 🚀 Features
* Patient management: create, update, and retrieve patient information
* Billing management: generate bills, track payments, and manage billing accounts
* Authentication and authorization: secure access to the system using JWT tokens
* Analytics: gain insights into patient behavior and treatment outcomes
* API gateway: provides a single entry point for all services and routes requests to the appropriate microservice
* Kafka integration: enables asynchronous communication between microservices
* gRPC integration: enables high-performance communication between microservices

## 🛠️ Tech Stack
* Java 
* Spring Boot
* Spring Cloud Gateway
* Kafka
* gRPC
* JWT
* Spring Security
* H2 database (optional)
* MySQL database (optional)
* Postgre database (optional)

## 📦 Installation
To install the Patient Management System, follow these steps:
 Just Spin the Docker Container in every services

## 💻 Usage
To use the Patient Management System, follow these steps:
1. Register a new patient: `curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","email":"johndoe@example.com"}' http://localhost:8080/patients`
2. Login to the system: `curl -X POST -H "Content-Type: application/json" -d '{"username":"admin","password":"password"}' http://localhost:8080/login`
3. Generate a bill for a patient: `curl -X POST -H "Content-Type: application/json" -d '{"patientId":1,"amount":100.0}' http://localhost:8080/bills`
4. View patient analytics: `curl -X GET http://localhost:8080/analytics/patients`

## 📂 Project Structure
```markdown
patient-management-system
├── api-gateway
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── pm
│   │   │   │           └── apigateway
│   │   │   │               └── ApiGatewayApplication.java
│   │   │   └── resources
│   │   │       └── application.yml
│   └── target
│       └── api-gateway-0.0.1-SNAPSHOT.jar
├── patient-service
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── pm
│   │   │   │           └── patientservice
│   │   │   │               └── PatientServiceApplication.java
│   │   │   └── resources
│   │   │       └── application.properties
│   └── target
│       └── patient-service-0.0.1-SNAPSHOT.jar
├── billing-service
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── pm
│   │   │   │           └── billingservice
│   │   │   │               └── BillingServiceApplication.java
│   │   │   └── resources
│   │   │       └── application.properties
│   └── target
│       └── billing-service-0.0.1-SNAPSHOT.jar
├── auth-service
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── pm
│   │   │   │           └── authservice
│   │   │   │               └── AuthServiceApplication.java
│   │   │   └── resources
│   │   │       └── application.properties
│   └── target
│       └── auth-service-0.0.1-SNAPSHOT.jar
├── analytics-service
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── pm
│   │   │   │           └── analyticsservice
│   │   │   │               └── AnalyticsServiceApplication.java
│   │   │   └── resources
│   │   │       └── application.properties
│   └── target
│       └── analytics-service-0.0.1-SNAPSHOT.jar
└── pom.xml
```

## 📸 Screenshots

![Project Architecture](assets/Spring%20Project%20Structure.svg)

![GRPC Architecture](assets/GRPC%20Architecture.svg)

![Kafka Architecture](assets/kafka%20architecture.svg)
![Kafka Architecture](assets/API%20Gateway%20Architecture.svg)



## 📝 License
The Patient Management System is licensed under the Apache License 2.0.



## 💖 Thanks Message
This is written by [readme.ai](https://readme-generator-phi.vercel.app/) - Your AI-powered README generator.
