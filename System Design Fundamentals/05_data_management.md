# 🗃️ Data Management in System Design

## 1. Overview
Data management is a cornerstone of robust system architecture. It governs how data is stored, accessed, maintained, and protected, directly impacting system consistency, scalability, and fault tolerance. Effective data management ensures reliable operations, seamless growth, and resilience against failures.

---

## 2. Key Concepts of Data Management

| Concept            | Description                                |
|--------------------|--------------------------------------------|
| Data Consistency   | Ensuring correctness across systems         |
| Data Availability  | Data is accessible when needed              |
| Partition Tolerance| System continues even if parts fail         |
| Durability         | Data is preserved after operations          |
| Scalability        | Able to grow data handling capabilities     |

---

## 3. Data Storage Models

### 🔸 Relational Databases (RDBMS)
- Structured schema
- Strong ACID compliance
- Good for OLTP (Online Transaction Processing)

**Example: PostgreSQL, MySQL**
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);
```

**Advanced RDBMS Example:**
```sql
-- Database Schema for E-commerce
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2)
);

-- Indexing Strategy
CREATE INDEX idx_orders_user ON orders(user_id);
CREATE INDEX idx_orders_status ON orders(status, created_at);

-- Partitioning Example
CREATE TABLE orders_partition (
    LIKE orders INCLUDING INDEXES
) PARTITION BY RANGE (created_at);

CREATE TABLE orders_2023 PARTITION OF orders_partition
    FOR VALUES FROM ('2023-01-01') TO ('2024-01-01');
```

### 🔸 NoSQL Databases
- Schema-less or flexible schema
- BASE model, high availability

| Type          | Description       | Examples               |
|---------------|------------------|------------------------|
| Key-Value     | Fast lookups     | Redis, DynamoDB        |
| Document      | JSON-like docs   | MongoDB, Couchbase     |
| Column-Family | Wide tables      | Cassandra, HBase       |
| Graph         | Nodes/Edges      | Neo4j, ArangoDB        |

#### MongoDB Document Store
```javascript
// User Profile with Embedded Documents
db.users.insertOne({
    _id: ObjectId(),
    email: "user@example.com",
    profile: {
        name: "John Doe",
        age: 30,
        preferences: ["tech", "books"]
    },
    addresses: [{
        type: "home",
        street: "123 Main St",
        city: "Boston"
    }]
});

// Indexing Strategy
db.users.createIndex({ "email": 1 }, { unique: true });
db.users.createIndex({ "profile.name": 1, "profile.age": 1 });
```

---

## 4. Data Partitioning (Sharding)

| Type           | Description                                 |
|----------------|---------------------------------------------|
| Range-based    | Split by value range (e.g., dates)          |
| Hash-based     | Use hash of key to determine shard           |
| Directory-based| Maintain a lookup table for shard location  |

**Real-world Example:**  
A social media app uses hash-based sharding on user ID to distribute users across 10 shards.

---

## 5. Replication

| Type             | Description                | Use Case              |
|------------------|---------------------------|-----------------------|
| Master-Slave     | Read from slaves, write to master | Read-heavy systems   |
| Master-Master    | Writes on both sides      | Active-active clusters|
| Multi-Master (async) | Conflict resolution required | Distributed setups   |

**Spring Boot + MySQL Read Replica Config:**
```yaml
spring:
  datasource:
    url: jdbc:mysql://master-db:3306/mydb
    replica-url: jdbc:mysql://replica-db:3306/mydb
```

---

## 6. CAP Theorem

| Property          | Description                       |
|-------------------|-----------------------------------|
| Consistency       | All nodes see the same data       |
| Availability      | Every request gets a response     |
| Partition Tolerance| System functions despite network splits |

> 📌 In distributed systems, only 2 out of 3 are guaranteed at a time.

---

## 7. Data Consistency Models

| Model               | Description                               |
|---------------------|-------------------------------------------|
| Strong Consistency  | Reads reflect most recent writes          |
| Eventual Consistency| Data becomes consistent over time         |
| Causal Consistency  | Preserves cause-effect order              |
| Read-Your-Writes    | Guarantees visibility of recent writes    |

**Example:**  
DNS is eventually consistent; banking systems require strong consistency.

---

## 8. Data Lifecycle Management

- Creation → Usage → Archiving → Deletion
- Set policies for retention (e.g., GDPR)
- Apply versioning, backups, soft deletes

---

## 9. Caching Strategies

| Strategy        | Description                                 |
|-----------------|---------------------------------------------|
| Cache-Aside     | App loads data into cache on miss           |
| Write-Through   | Writes to cache and DB                      |
| Write-Behind    | Delay DB write from cache                   |
| Read-Through    | Cache handles fetching                      |

**Spring Boot Redis Example:**
```java
@Cacheable("products")
public Product getProduct(String id) {
    return productRepository.findById(id).orElseThrow();
}
```

#### Redis Caching Patterns
```java
@Service
public class UserCacheService {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    // Cache-Aside Pattern Implementation
    public User getUserWithCaching(String userId) {
        String cacheKey = "user:" + userId;
        User user = redisTemplate.opsForValue().get(cacheKey);
        
        if (user == null) {
            user = userRepository.findById(userId);
            redisTemplate.opsForValue().set(cacheKey, user, 1, TimeUnit.HOURS);
        }
        return user;
    }
}
```

---

## 10. Backup and Recovery

- Snapshots, Incremental Backups
- RPO (Recovery Point Objective) & RTO (Recovery Time Objective)
- Automate daily backups & offsite storage

---

## 11. Data Governance

- Schema Management
- Access Controls
- Audit Trails
- Encryption at rest and in transit

---

## 12. Tools & Best Practices

| Tool                | Purpose                  |
|---------------------|-------------------------|
| Flyway / Liquibase  | DB Schema migrations    |
| pgBackRest / mysqldump | Backup & restore     |
| Redis / Hazelcast   | Caching                 |
| Apache Kafka        | Event streaming & decoupling |
| Prometheus / ELK    | Monitoring & logs       |

---

## 13. Summary & Thought Process

- Always align DB model with business needs
- Understand read/write patterns before choosing DB
- Plan for scalability from Day 1
- Prioritize data integrity and traceability
- Consider regulations like GDPR/CCPA

---

## 14. References
- [Designing Data-Intensive Applications](https://dataintensive.net/)
- [Google Cloud Architecture Center – Data](https://cloud.google.com/architecture/data)
- [Martin Fowler: Event Sourcing](https://martinfowler.com/eaaDev/EventSourcing.html)

---

## 4. Real-World Data Management Scenarios

### Scenario 1: Social Media Platform
```plaintext
Challenge: Managing user feed data
Solution:
1. Store user posts in MongoDB for flexible schema
2. Use Redis for feed caching
3. Implement fan-out on write for followers
4. Shard data by user_id
5. Use message queue for async processing

Implementation:
- Posts Collection: MongoDB
- Feed Cache: Redis Sorted Sets
- Media Storage: Object Storage (S3)
- Analytics: Cassandra
```

### Scenario 2: E-commerce Platform
```plaintext
Challenge: Product Catalog and Order Management
Solution:
1. Products: PostgreSQL (ACID needed)
2. Product Search: Elasticsearch
3. Shopping Cart: Redis
4. Order Processing: Queue-based system
5. Analytics: Data Warehouse

Performance Optimizations:
- Materialized Views for reporting
- Partial indexes for active orders
- Cache product details in Redis
```

## 5. Advanced Data Patterns

| Pattern | Use Case | Implementation |
|---------|----------|----------------|
| CQRS | High-read systems | Separate read/write models |
| Event Sourcing | Audit trails | Store state changes as events |
| Saga Pattern | Distributed transactions | Choreography/Orchestration |

### CQRS Implementation Example
```java
@Service
public class OrderService {
    // Command Side
    @Transactional
    public void createOrder(CreateOrderCommand cmd) {
        Order order = new Order(cmd.getUserId(), cmd.getItems());
        orderRepository.save(order);
        eventPublisher.publish(new OrderCreatedEvent(order));
    }
    
    // Query Side
    public OrderProjection getOrder(String orderId) {
        return orderProjectionRepository.findById(orderId);
    }
}
```