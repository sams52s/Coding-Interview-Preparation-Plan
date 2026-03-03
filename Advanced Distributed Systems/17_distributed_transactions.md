# Distributed Transactions

In a microservices architecture, a single business workflow often spans multiple independent services, each with its own database ("Database per Service" pattern). Ensuring data consistency across these disparate databases requires a distributed transaction.

## 1. The Problem

In a monolith, a transaction is managed by a single Relational Database Management System (RDBMS) ensuring ACID (Atomicity, Consistency, Isolation, Durability) properties. 
In microservices, you cannot use a simple local database transaction because operations happen across network boundaries.

*Example*: E-commerce "Place Order" flow:
1. `Order Service`: Create order (`Status: PENDING`).
2. `Inventory Service`: Deduct stock.
3. `Payment Service`: Charge credit card.
If the Payment Service fails, the Inventory and Order services must roll back their changes to maintain consistency.

## 2. Two-Phase Commit (2PC)

2PC is a traditional, synchronous protocol for distributed transactions.

### How it works
Relies on a centralized **Transaction Coordinator**.
1. **Prepare Phase**: The coordinator asks all participating services if they are ready to commit. Services lock resources and reply "Yes" or "No".
2. **Commit/Rollback Phase**: 
   - If all say "Yes", the coordinator tells all to commit.
   - If even one says "No", the coordinator tells all to roll back.

### Pros
- Strong consistency.

### Cons
- **Blocking / Synchronous**: Services lock resources until the coordinator finishes, hurting throughput.
- **Single Point of Failure**: If the coordinator crashes during the prepare phase, resources remain locked indefinitely.
- Poor performance in modern microservices/cloud environments.

## 3. The Saga Pattern

The Saga pattern is the modern, preferred approach for handling distributed transactions in microservices. It replaces a single distributed transaction with a sequence of local transactions.

### How it works
If any local transaction fails, the Saga executes **compensating transactions** to undo the work completed by the preceding local transactions. Sagas ensure **Eventual Consistency** (BASE) rather than Strong Consistency (ACID).

### Approaches to Saga implementation:

#### A. Choreography-based Saga (Event-Driven)
Services publish domain events, and other services subscribe to those events and act. There is no central controller.
- *Flow*: `OrderService` saves order and emits `OrderCreatedEvent`. `InventoryService` listens, deducts stock, emits `StockDeductedEvent`. `PaymentService` listens, charges card. If `PaymentService` fails, it emits `PaymentFailedEvent`. `InventoryService` and `OrderService` listen to *that* event and run compensating logic.
- *Pros*: Simple for small workflows, no single point of failure.
- *Cons*: Hard to track the overall flow as the number of services grows. Cyclic dependencies easily form.

#### B. Orchestration-based Saga (Command-Driven)
A centralized **Saga Orchestrator** manages the workflow, telling participant services what local transactions to execute.
- *Flow*: `OrderService` creates an order and tells the `OrderOrchestrator` to begin. The orchestrator explicitly sends a "Deduct Stock Command" to `InventoryService`. If inventory replies "Success", the orchestrator sends a "Charge Card Command" to `PaymentService`. If payment fails, the orchestrator sends a "Compensate Stock Command" to `InventoryService`.
- *Pros*: Clear workflow definition. Avoids cyclic dependencies. Easier to scale complex workflows.
- *Cons*: Introduces a central point of control (though modern orchestrators like Temporal, AWS Step Functions, or Camunda are highly resilient).

## 4. Key Considerations & Patterns for Distributed Transactions

1. **Idempotency**: Services participating in Sagas MUST be idempotent (capable of receiving the same command/event multiple times without causing unintended side effects), because message queues (like Kafka/RabbitMQ) guarantee "At-Least-Once" delivery.
2. **Outbox Pattern**: When executing a local database transaction and publishing an event (e.g., to Kafka), both actions must succeed or fail together. You cannot use a 2PC across the DB and Kafka. The **Transactional Outbox** pattern saves the event to an "Outbox" table in the *same* database transaction, then a separate process (like Debezium) reads the table and publishes to Kafka.
3. **Compensating Transactions are Hard**: Sometimes you cannot "undo". E.g., if you sent a shipping email, you can't un-send it. You must design business processes to handle this (e.g., send an apology email).

## Interview Questions on Distributed Transactions

1. **Why don't we use 2PC in microservices?**
   - *Answer*: It relies on locking resources across the network, making the system slow, tightly coupled, and less available. It fundamentally reduces system availability.
2. **Explain the Saga Pattern and its types.**
   - *Answer*: Saga breaks a distributed transaction into a sequence of local transactions. If one fails, compensating transactions are triggered. Types are Choreography (event-driven, decentralized) and Orchestration (command-driven, centralized coordinator).
3. **What is the Outbox Pattern and why is it necessary?**
   - *Answer*: It ensures reliable message publishing by saving the message/event to an `outbox` table in the same database transaction as the business entity update. This solves the dual-write problem between the database and the message broker without needing a 2PC.
