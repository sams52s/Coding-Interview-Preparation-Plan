# System Design Fundamentals

System Design is a critical component of Senior Software Engineering interviews. It evaluates your ability to architecture scalable, reliable, and maintainable systems.

## 1. Key Characteristics of Distributed Systems

- **Scalability**: The capability of a system to handle a growing amount of work by adding resources to the system.
  - **Vertical Scaling (Scale-up)**: Adding more power (CPU, RAM) to an existing machine. (Limited ceiling).
  - **Horizontal Scaling (Scale-out)**: Adding more machines into your pool of resources. (Virtually limitless, but introduces complexity).
- **Latency vs. Throughput**:
  - **Latency**: The time it takes for a message to be delivered.
  - **Throughput**: The quantity of data successfully moving through the system in a given time period.
- **Availability**: The percentage of time a system remains operational (e.g., 99.99% or "four nines").
- **Reliability (Fault Tolerance)**: The probability a system will fail in a given period. A reliable system continues to work even when parts of it fail.

## 2. The CAP Theorem

In any distributed data store, you can only guarantee **two out of the three** following characteristics:
- **Consistency**: Every read receives the most recent write or an error.
- **Availability**: Every request receives a (non-error) response, without the guarantee that it contains the most recent write.
- **Partition Tolerance**: The system continues to operate despite an arbitrary number of messages being dropped or delayed by the network between nodes.

*Reality Note*: Network partitions are inevitable in distributed systems. Therefore, you must choose between Consistency (CP) or Availability (AP).
- **CP System (e.g., MongoDB, HBase, Redis Cluster)**: Return an error if nodes cannot sync.
- **AP System (e.g., Cassandra, DynamoDB)**: Return the available data, even if it's stale (eventual consistency).

## 3. Core Components

1. **Load Balancers (LB)**
   - Distributes incoming network traffic across multiple servers.
   - Algorithms: Round Robin, Least Connections, IP Hash.
   - Hardware (F5) vs Software (Nginx, HAProxy). L4 (Transport) vs L7 (Application) balancing.
2. **Caching**
   - Stores copies of frequently accessed data temporarily.
   - Eviction Policies: LRU (Least Recently Used), LFU, FIFO.
   - Tools: Redis, Memcached.
3. **Databases**
   - **Relational (SQL)**: Structured data, ACID properties. (MySQL, PostgreSQL). Good for complex queries.
   - **Non-Relational (NoSQL)**: Semi-structured/unstructured data. (MongoDB, Cassandra). Good for horizontal scalability and fast reads/writes.
4. **Message Queues / Event Streaming**
   - Decouples components for asynchronous processing.
   - Tools: RabbitMQ, Apache Kafka, Amazon SQS.
5. **Content Delivery Network (CDN)**
   - A globally distributed network of proxy servers, deployed in multiple data centers to serve static assets (images, JS, CSS) closer to users. Tools: Cloudflare, AWS CloudFront.

## 4. Typical System Design Interview Structure (45 mins)

1. **Requirements Clarification (5 mins)**
   - Functional (What the system does) vs Non-functional (Scale, Latency, Availability).
2. **Back-of-the-Envelope Estimation (5 mins)**
   - Traffic, storage sizes, and bandwidth.
3. **High-Level Design (10-15 mins)**
   - Draw boxes and arrows (Client -> LB -> Web Tier -> DB Tier).
4. **Detailed Design / Deep Dive (15-20 mins)**
   - Address bottlenecks. Discuss DB schema, API design, trade-offs (e.g., "Why Cassandra over PostgreSQL here?").
5. **Resolve Bottlenecks / Scaling (5 mins)**
   - Single points of failure, scaling the DB (sharding/replication).

## 5. Common System Design Interview Questions
1. Design URL Shortener (e.g., bit.ly)
2. Design Twitter / News Feed System
3. Design WhatsApp / Chat Application
4. Design Netflix / YouTube
5. Design Uber / Ride-Sharing App
