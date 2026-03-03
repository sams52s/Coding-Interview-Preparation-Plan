# Stream Processing and Event-Driven Architectures

Modern systems deal with continuous and unbounded streams of data (e.g., website clicks, IoT sensor readings, financial transactions). Processing this data in real-time or near real-time is crucial for rapid decision-making.

## 1. Batch vs. Stream Processing

- **Batch Processing**: Processing large volumes of data arriving over a period (e.g., end-of-day reports, monthly billing). Usually runs periodically on stored data (Hadoop, Spark Batch). High latency (hours/days).
- **Stream Processing**: Processing data continuously as it arrives, record by record or in tiny micro-batches. Used for fraud detection, real-time analytics, monitoring (Apache Kafka, Flink). Low latency (milliseconds/seconds).

## 2. Event-Driven Architectures (EDA)

EDA shifts the paradigm from request-driven (synchronous API calls) to event-driven (asynchronous message passing). A system reacting to state changes.

### Key Components
- **Events**: A record of something that happened in the past (e.g., `UserCreated`, `OrderShipped`).
- **Producers**: Applications that generate and publish events.
- **Message Broker / Event Log**: The intermediaries that receive and distribute events (e.g., Kafka, RabbitMQ).
- **Consumers**: Applications that subscribe to events and react to them.

### Advantages of EDA
1. **Decoupling**: Producers and consumers are independent. You can add new consumers without modifying producers.
2. **Scalability**: Brokers can handle immense throughput and allow consumers to process events at their own pace.
3. **Resiliency**: If a consumer is down, messages are buffered in the broker and processed once the consumer recovers.

## 3. Deep Dive into Apache Kafka

Kafka is a foundational tool in modern EDA. It's not a traditional message queue (like RabbitMQ) but a **distributed commit log**.

### Core Kafka Architecture
1. **Topics**: Categorized streams of records. They are append-only logs.
2. **Partitions**: Topics are broken down into partitions. Partitions allow Kafka to scale horizontally (different partitions on different brokers). Order is *only* guaranteed within a partition, NOT across the whole topic.
3. **Producers**: Publish data to topics. They can choose which partition to write to (often based on a hash of a key, like `userId`).
4. **Consumers & Consumer Groups**: 
   - Consumers read records from partitions.
   - A **Consumer Group** is a set of consumers. Each partition is consumed by *exactly one* consumer in a group. This allows parallel processing. Adding more consumers than partitions provides no benefit (some will sit idle).
5. **Brokers**: The servers comprising the Kafka cluster.

### Why is Kafka so fast?
- **Sequential I/O**: It appends data to logs on disk sequentially, leveraging the OS page cache and avoiding random disk seeks.
- **Zero-Copy**: It bypasses the application layer when transferring data from disk to the network socket, making reads incredibly efficient.
- **Batching**: Producers can batch messages before sending, reducing network overhead.

## Interview Questions on Stream Processing & Kafka

1. **How does Kafka ensure high throughput compared to traditional message queues like RabbitMQ?**
   - *Answer*: Kafka uses sequential disk I/O, the zero-copy principle, batches messages, and allows massive horizontal scaling via partitions. RabbitMQ typically stores messages in memory and uses a different routing model which is CPU-intensive.
2. **Explain exactly what a "Consumer Group" in Kafka does.**
   - *Answer*: It allows a group of parallel consumer instances to collaboratively read from a topic. Kafka assigns each partition to one consumer within the group. It enables parallel processing of the event stream and fault tolerance (if a consumer crashes, its partitions are rebalanced to other consumers in the group).
3. **How do you guarantee message ordering in Kafka?**
   - *Answer*: Kafka only guarantees ordering within a specific *partition*. To guarantee order for a specific stream of events (e.g., all events for user ID 123), you must configure the producer to use the `userId` as the message key. All messages with the same key will be routed to the same partition.
4. **What is an Exchanged in RabbitMQ vs a Topic in Kafka?**
   - *Answer*: In RabbitMQ, producers send messages to an Exchange, which routes them to Queues based on binding rules. It's a "smart broker, dumb consumer" model. In Kafka, producers append to a Topic (a dumb log), and consumers track their own offsets (a "dumb broker, smart consumer" model).
