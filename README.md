# Kafka
![alt text](image.png)

## Up the app
docker compose up -d

## Access the queen in the localhost pot 8282
http://localhost:8282/

## App fluxo of the Order and Payment
![alt text](image-1.png)
![alt text](image-2.png)


## CURL to register topic
curl --location 'http://localhost:8081/api/orders' \
--header 'Content-Type: application/json' \
--data '{
  "customerId": "user_amanda_1001",
  "totalAmount": 77.00,
  "cep": "04849270"
}'


# 💳 Payment Service

A production-inspired payment microservice built with **Java 21** and **Spring Boot**, demonstrating modern backend development practices, **Hexagonal Architecture**, asynchronous processing and event-driven communication.

## 🚀 Highlights

* Java 21
* Spring Boot
* Hexagonal Architecture
* Apache Kafka
* OpenFeign
* PostgreSQL
* MongoDB
* Docker
* REST API
* Asynchronous external API calls
* Virtual Threads
* CompletableFuture
* Domain-driven design principles
* Event-driven architecture

## 🏗️ Architecture

The project follows **Hexagonal Architecture**, keeping business rules independent from frameworks, databases and external services.

```text
                         ┌─────────────────────┐
                         │     Kafka Consumer   │
                         │    Inbound Adapter   │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │      Use Case       │
                         │                     │
                         │   Create Payment    │
                         └──────────┬──────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
                    ▼               ▼               ▼
              ViaCEP API       Economy API     Persistence
              OpenFeign       OpenFeign       Port
                    │               │               │
                    ▼               ▼          ┌────┴────┐
                 Address         Currency      │         │
                                             PostgreSQL MongoDB
```

## ⚡ Asynchronous Processing

The payment process performs independent external API calls concurrently.

The service queries:

* **ViaCEP** — address information
* **AwesomeAPI** — USD/BRL exchange rate

These calls are executed concurrently using **Java 21 Virtual Threads** and `CompletableFuture`, reducing the total waiting time compared to sequential execution.

## 📨 Event-Driven Communication

Orders are received through **Apache Kafka**.

```text
Order Service
      │
      │ Kafka
      ▼
orders-topic
      │
      ▼
Payment Service
      │
      ├── ViaCEP
      │
      ├── Economy API
      │
      ▼
   Payment
      │
      ├── PostgreSQL
      └── MongoDB
```

## 🔌 External Integrations

### ViaCEP

Used to retrieve address information based on the customer's ZIP code.

### AwesomeAPI

Used to retrieve the current USD/BRL exchange rate.

Both integrations are isolated behind **outbound ports**, keeping the application core independent from HTTP clients and external APIs.

## 🧱 Project Structure

```text
src/main/java/com/github/celso_ricardo_bastos/payment_service/

├── domain/
│   ├── model/
│   └── exception/
│
├── ports/
│   ├── inbound/
│   └── outbound/
│
├── usecases/
│
└── adapters/
    ├── inbound/
    │   └── kafka/
    │
    └── outbound/
        ├── viacep/
        ├── economia/
        ├── postgres/
        ├── mongo/
        └── kafka/
```

## 🛠️ Technologies

| Technology             | Purpose                    |
| ---------------------- | -------------------------- |
| Java 21                | Application development    |
| Spring Boot            | Application framework      |
| Spring Cloud OpenFeign | External API communication |
| Apache Kafka           | Asynchronous messaging     |
| PostgreSQL             | Relational persistence     |
| MongoDB                | Document persistence       |
| Docker                 | Infrastructure             |
| Maven                  | Dependency management      |

## 🎯 Main Concepts Demonstrated

This project was designed to demonstrate practical implementation of:

* Hexagonal Architecture
* Dependency Inversion
* Ports and Adapters
* Domain isolation
* Event-driven architecture
* Asynchronous processing
* Concurrent external API calls
* Virtual Threads
* Microservice communication
* Polyglot persistence
* External API integration

## ▶️ Running the Project

### Requirements

* Java 21
* Maven
* Docker
* Docker Compose

Clone the repository:

```bash
git clone https://github.com/celsobastos/payment-service.git
```

Enter the project:

```bash
cd payment-service
```

Start the infrastructure:

```bash
docker compose up -d
```

Run the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## 👨‍💻 About

This project is part of my backend engineering portfolio, focusing on **Java, Spring Boot, microservices, distributed systems and software architecture**.

The goal is not only to build a functional payment service, but to demonstrate how modern backend systems can be structured for **maintainability, scalability and separation of concerns**.
