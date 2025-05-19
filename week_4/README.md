# 📅 Week 4: Advanced System Design & Architecture

> 🎯 Master large-scale distributed systems, design patterns, and complex algorithms. Focus on real-world applications and industry best practices.

---

## 📚 Core Focus Areas:

### 1. System Design Fundamentals
- [ ] **Scalability**
  - Horizontal vs Vertical Scaling
  - Capacity Planning
  - Performance Metrics & SLAs
- [ ] **High Availability**
  - Redundancy & Replication
  - Fault Tolerance Strategies
  - Disaster Recovery Planning
- [ ] **Distributed Systems**
  - CAP Theorem in Practice
  - ACID vs BASE
  - Consistency Models
  - Consensus Protocols (Paxos, Raft)
- [ ] **Security & Performance**
  - Authentication & Authorization
  - SSL/TLS
  - DDoS Protection
  - Rate Limiting Strategies
  - Performance Monitoring
  - APM Tools Integration
- [ ] **Data Management**
  - Data Partitioning
  - Consistent Hashing
  - Write-Ahead Logging
  - Change Data Capture
  - Data Migration Strategies

### 2. Core Infrastructure Components
- [ ] **Load Balancing**
  - Algorithms (Round Robin, Least Connections)
  - Health Checks
  - SSL Termination
- [ ] **Caching**
  - Distributed Cache (Redis, Memcached)
  - Cache Invalidation Strategies
  - Cache-Aside Pattern
- [ ] **Data Storage**
  - Polyglot Persistence
  - Sharding Strategies
  - Replication Patterns
  - Indexing Optimization
- [ ] **Service Communication**
  - gRPC vs REST
  - GraphQL Implementation
  - Service Discovery
  - Circuit Breaking
  - Bulk heading
  - Retry Patterns
- [ ] **Monitoring & Observability**
  - Distributed Tracing
  - Log Aggregation
  - Metrics Collection
  - Alert Management
  - SLO/SLA Monitoring

### 3. Modern Architecture Patterns
- [ ] **Microservices**
  - Domain-Driven Design
  - Service Mesh
  - API Gateway Pattern
- [ ] **Event-Driven Architecture**
  - Event Sourcing
  - CQRS
  - Message Queues
- [ ] **Serverless Architecture**
  - FaaS vs BaaS
  - Cold/Warm Starts
  - Cost Optimization
- [ ] **Cloud-Native Patterns**
  - Container Orchestration
  - Service Mesh Implementation
  - GitOps Workflow
  - Infrastructure as Code
  - Blue-Green Deployment
  - Canary Releases
- [ ] **Resilience Patterns**
  - Circuit Breaker
  - Bulkhead
  - Timeout/Retry
  - Rate Limiter
  - Dead Letter Queues
  - Fallback Mechanisms

### 4. Advanced Distributed Systems Concepts
- [ ] **Consensus Algorithms**
  - Raft Implementation
  - Byzantine Fault Tolerance
  - Vector Clocks
  - Gossip Protocols
- [ ] **Distributed Transactions**
  - 2PC/3PC Protocols
  - Saga Pattern
  - Distributed Locking
  - Transaction Isolation Levels
- [ ] **Stream Processing**
  - Apache Kafka Streams
  - Apache Flink
  - Real-time Analytics
  - Complex Event Processing
- [ ] **Data Replication**
  - Multi-Master Replication
  - Change Data Capture
  - Conflict Resolution
  - Eventual Consistency

### 5. Performance Engineering
- [ ] **Load Testing**
  - Performance Metrics
  - Stress Testing
  - Chaos Engineering
  - Performance Profiling
- [ ] **Database Optimization**
  - Query Optimization
  - Index Design
  - Partitioning Strategies
  - Read/Write Separation
- [ ] **Network Optimization**
  - Protocol Optimization
  - Connection Pooling
  - Content Delivery
  - Edge Computing

---

### 🧪 Practice Focus for this Week

- Solve 2–3 LeetCode/HackerRank/Codeforces/Codechef problems daily focused on Graphs and DP.
- Design a basic System Architecture for "URL Shortener" or "File Sharing Service".
- Build a small Spring Boot project containerized with Docker (Optional Challenge).

## 📚 Recommended Case Studies
- URL Shortener System
- Real-time Chat Application
- Distributed Job Scheduler
- Payment Processing System
- Social Media Feed
- Distributed Search Engine
- Video Streaming Platform
- E-commerce Cart System
- Notification Service
- Distributed Caching System
- Real-time Analytics Pipeline
- Distributed Time Series Database
- Real-time Fraud Detection System
- Distributed Task Scheduler
- Global Content Delivery Network
- Multi-region Database System
- Real-time Recommendation Engine
- Distributed Rate Limiter
- Log Processing Pipeline

## 🔗 Essential Reading Materials
- System Design Interview (Alex Xu)
- Designing Data-Intensive Applications (Martin Kleppmann)
- Building Microservices (Sam Newman)

---

## 🔗 Additional Resources
- [System Design Primer GitHub](https://github.com/donnemartin/system-design-primer)
- [Spring Cloud Official Docs](https://spring.io/projects/spring-cloud)
- [LeetCode Graph Problems Card](https://leetcode.com/explore/learn/card/graph/)
- [LeetCode Dynamic Programming Problems Card](https://leetcode.com/explore/learn/card/dynamic-programming/)
- [GeeksforGeeks System Design](https://www.geeksforgeeks.org/system-design-tutorial/)
- [System Design Interview – An Insider's Guide](https://www.amazon.com/System-Design-Interview-insiders-guide/dp/0984782850)
- [Designing Data-Intensive Applications](https://www.amazon.com/Designing-Data-Intensive-Applications-Foundations-Scalability/dp/1492035612)
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Docker Official Documentation](https://docs.docker.com/get-started/)



### 📜 Engineering Articles
- [How Discord stores trillions of messages](https://discord.com/blog/how-discord-stores-trillions-of-messages)
- [How Uber built a real-time data pipeline](https://eng.uber.com/real-time-data-pipeline/)
- [How Netflix handles billions of events per day](https://netflixtechblog.com/how-netflix-handles-billions-of-events-per-day-4f3b2c1a5e8d)
- [How Spotify scales its backend](https://engineering.atspotify.com/2020/01/how-spotify-scales-its-backend/)
- [How Facebook handles billions of messages](https://engineering.fb.com/2018/10/01/core-data/facebook-messenger-messaging-scale/)
- [How Twitter handles billions of tweets](https://blog.twitter.com/engineering/en_us/topics/infrastructure/2020/how-twitter-handles-billions-of-tweets.html)
- [How LinkedIn handles billions of connections](https://engineering.linkedin.com/blog/2019/how-linkedin-handles-billions-of-connections)
- [Building In-Video Search at Netflix](https://netflixtechblog.com/building-in-video-search-at-netflix-1c5f3f3c5f3f)
- [How Canva scaled Media uploads from Zero to 50 Million per Day](https://www.canva.com/learn/how-canva-scaled-media-uploads-from-zero-to-50-million-per-day/)
- [How Airbnb avoids double payments in a Distributed Payments System](https://medium.com/airbnb-engineering/how-airbnb-avoids-double-payments-in-a-distributed-payments-system-5f3b2c1a5e8d)
- [Stripe’s payments APIs - The first 10 years](https://stripe.com/docs/stripe-10-years)
- [Real time messaging at Slack](https://slack.engineering/real-time-messaging-at-slack/)
- [How Dropbox handles file synchronization](https://blogs.dropbox.com/tech/2018/10/how-dropbox-handles-file-synchronization/)
- [How Pinterest handles billions of pins](https://medium.com/pinterest-engineering/how-pinterest-handles-billions-of-pins-4f3b2c1a5e8d)
- [How Cloudflare's CDN Works](https://blog.cloudflare.com/how-cloudflare-works/)
- [DynamoDB at Scale](https://www.amazon.science/publications/amazon-dynamodb-a-scalable-predictably-performant-and-fully-managed-nosql-database-service)
- [Meta's Social Graph](https://engineering.fb.com/2021/02/11/data-infrastructure/tao/)
- [Google's Large Scale Distributed Systems](https://research.google/pubs/pub41378/)

### 🔬 Advanced Distributed Systems Papers
- [Paxos: The Part-Time Parliament](https://lamport.azurewebsites.net/pubs/lamport-paxos-simple.pdf)
- [MapReduce: Simplified Data Processing on Large Clusters](https://research.google/pubs/archive/33491.pdf)
- [The Dataflow Model of Computation](https://research.google/pubs/archive/33491.pdf)
- [The Tail at Scale](https://research.google/pubs/archive/43438.pdf)
- [The Chubby lock service for loosely-coupled distributed systems](https://research.google/pubs/archive/33491.pdf)
- [The Google File System](https://research.google/pubs/archive/33491.pdf)
- [Dynamo: Amazon’s Highly Available Key-value Store](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf)
- [Spanner: Google’s Globally-Distributed Database](https://research.google/pubs/archive/43438.pdf)
- [Kafka: a Distributed Messaging System for Log Processing](https://kafka.apache.org/documentation/)
- [Spanner: Google’s Globally-Distributed Database](https://research.google/pubs/archive/43438.pdf)
- [Bigtable: A Distributed Storage System for Structured Data](https://research.google/pubs/archive/bigtable.pdf)
- [ZooKeeper: Wait-free coordination for Internet-scale systems](https://research.google/pubs/archive/33491.pdf)
- [The Log-Structured Merge-Tree (LSM-Tree)](https://research.google/pubs/archive/33491.pdf)
- [The Chubby lock service for loosely-coupled distributed systems](https://research.google/pubs/archive/33491.pdf)
- [The Twelve-Factor App](https://12factor.net/)
- [The Art of Multiprocessor Programming](https://www.amazon.com/Art-Multiprocessor-Programming-MIT-Press/dp/0262035675)
- [The Tail at Scale](https://research.google/pubs/archive/43438.pdf)
- [The Data Distribution Service (DDS) for Real-Time Systems](https://www.omg.org/spec/DDS/)
- [CAP Theorem](https://en.wikipedia.org/wiki/CAP_theorem)
- [ACID vs BASE](https://www.geeksforgeeks.org/acid-properties-in-dbms/)
- [Distributed Systems](https://en.wikipedia.org/wiki/Distributed_system)
- [Microservices](https://microservices.io/)
- [RESTful APIs](https://restfulapi.net/)
---
## 📅 Weekly Revision + Mock Test
- **Revision**: Review all topics covered this week.
- [Problem List](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/week_4/solution_of_week_4_coding_problem/problem%20List.md)

---



## 🎯 Advanced Challenges
1. Design a distributed rate limiter
2. Implement a simple distributed cache
3. Build a basic service mesh
4. Create a fault-tolerant message queue

---

## 🎯 End Goal of Week 4

By the end of this week, you should be able to:
- Architect scalable backend systems.
- Understand how microservices communicate and manage failure.
- Build professional APIs following best practices.
- Write optimized SQL queries and manage transaction integrity.
- Solve graph and DP-based problems with confidence.

---
