# E-Commerce Microservices Application

## Overview

This is a microservices-based e-commerce application built using Java Spring and Domain-Driven Design (DDD). The application is composed of multiple services, each responsible for a specific domain, and uses various technologies to ensure scalability, resilience, and maintainability.

## Table of Contents

- [Architecture]
- [Services]
  - [Customer Service]
  - [Order Service]
  - [Payment Service]
  - [Product Service]
  - [Notification Service]
- [Technologies Used]
- [Service Discovery and Configuration]
- [Gateway]
- [Event-Driven Architecture]
- [DB]
- [Postgresql and Mongo]

## Architecture

The application follows the microservices architecture pattern with each service encapsulating a specific business domain. The services communicate with each other using REST APIs and messaging.

![Architecture Diagram](source/ecommerce.png) 

## Services

### Customer Service

- Manages customer information.
- CRUD operations for customer data.
- Exposes REST endpoints for managing customers.

### Order Service

- Manages orders and order processing.
- Handles creation, updating, and retrieval of orders.
- Sends order notifications via Kafka.

### Payment Service

- Manages payment processing.
- Integrates with payment gateways.
- Handles payment status updates.

### Product Service

- Manages product catalog.
- CRUD operations for product data.
- Exposes REST endpoints for managing products.

### Notification Service

- Listens to Kafka topics for order notifications.
- Sends email notifications to users about their purchases.

## Technologies Used

- **Java Spring Boot**: For building the microservices.
- **Spring Cloud**: For service discovery and configuration management.
  - **Eureka**: For service discovery.
  - **Config Server**: For centralized configuration management.
- **Apache Kafka**: For event-driven communication between services.
- **Spring Data JPA**: For database interactions.
- **Mongo/PostgreSQL**: For production database.
- **Docker**: For containerizing the services.
- **Swagger**: For API documentation.

## Service Discovery and Configuration

### Eureka Server

The application uses Eureka for service discovery. Each service registers with Eureka and discovers other services through it.

### Config Server

A centralized config server is used to manage all configurations of the services. This allows for externalized configuration and dynamic updates without redeploying the services.

## Event-Driven Architecture

### Apache Kafka

- **Order Service**: Publishes order events to Kafka topics.
- **Notification Service**: Subscribes to order events from Kafka and sends email notifications to users.

## Setup and Running

### Prerequisites

- Java 17 or higher
- Maven
- Docker (for containerized deployment)
- Kafka (for event-driven communication)
