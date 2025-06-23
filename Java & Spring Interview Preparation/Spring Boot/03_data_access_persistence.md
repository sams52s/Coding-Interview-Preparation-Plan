# 3. Data Access & Persistence

#### Overview
This document provides a comprehensive guide to advanced data access and persistence techniques in Spring Boot. It covers JPA, repositories, transactions, performance tuning, NoSQL, migrations, error handling, security, and best practices for building robust, scalable, and maintainable data layers.

---

## Table of Contents

1. [JPA & Spring Data Fundamentals](#jpa--spring-data-fundamentals)  
2. [Advanced JPA Concepts](#advanced-jpa-concepts)  
3. [Transaction Management](#transaction-management)  
4. [Performance Optimization](#performance-optimization)  
5. [NoSQL & Polyglot Persistence](#nosql--polyglot-persistence)  
6. [Database Migrations](#database-migrations)  
7. [Error Handling & Diagnostics](#error-handling--diagnostics)  
8. [Testing Data Access Layers](#testing-data-access-layers)  
9. [Security & Data Integrity](#security--data-integrity)  
10. [Best Practices & Patterns](#best-practices--patterns)  
11. [References](#references)  

---

## JPA & Spring Data Fundamentals

### Conceptual Overview
Java Persistence API (JPA) is the standard for ORM (Object-Relational Mapping) in Java. Spring Data JPA builds on top of JPA to simplify data access layers by providing repository abstractions, query derivation, and integration with Spring's programming model.

### Key Components

| Component             | Description                                                                                  | Common Annotations/Interfaces                 |
|-----------------------|----------------------------------------------------------------------------------------------|-----------------------------------------------|
| Entity                | Represents a table in the database.                                                         | `@Entity`, `@Table`                           |
| Repository            | Interface to perform CRUD and query operations.                                             | `JpaRepository`, `CrudRepository`             |
| Query Methods         | Derived or custom queries defined by method names or annotations.                           | `@Query`, method naming conventions           |
| DTO Projections       | Data Transfer Objects or interfaces to fetch partial entity data efficiently.               | Interfaces or classes with selected fields    |
| Relationships         | Defines associations between entities.                                                     | `@OneToMany`, `@ManyToOne`, `@ManyToMany`     |

### Entity Mapping Example

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    // getters and setters
}
```

### Repository Example

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatus(String status);

    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findActiveUsers(@Param("status") String status);
}
```

### DTO Projection Example

Using interface-based projection to fetch partial data:

```java
public interface UserSummary {
    String getName();
    int getOrderCount();
}
```

Usage in repository:

```java
@Query("SELECT u.name AS name, COUNT(o) AS orderCount FROM User u LEFT JOIN u.orders o GROUP BY u.name")
List<UserSummary> findUserSummaries();
```

### Common Repository Interfaces

| Interface          | Description                                        | Key Methods                          |
|--------------------|--------------------------------------------------|------------------------------------|
| `CrudRepository`   | Basic CRUD operations                             | `save()`, `findById()`, `delete()` |
| `JpaRepository`    | Extends `CrudRepository`, adds JPA-specific methods | `flush()`, `saveAndFlush()`, paging |
| `PagingAndSortingRepository` | Adds pagination and sorting capabilities | `findAll(Pageable pageable)`       |

### Summary Diagram

```
+-----------------+       +---------------------+       +-------------------+
|    Entity       |<----->|  Spring Data JPA    |<----->|    Database       |
|  (@Entity)      |       |  Repositories       |       |  (Relational DB)  |
+-----------------+       +---------------------+       +-------------------+
```

---

## Advanced JPA Concepts

### Custom Repository Implementations
Spring Data allows extending repository interfaces with custom implementations for complex logic beyond method naming or JPQL.

```java
public interface UserRepositoryCustom {
    List<User> findUsersWithCustomLogic();
}

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findUsersWithCustomLogic() {
        // Custom JPQL or Criteria API logic
    }
}

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {}
```

### Specification Pattern
Enables building dynamic, type-safe queries using `JpaSpecificationExecutor`.

```java
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {}

// Example Specification
public class UserSpecifications {
    public static Specification<User> hasStatus(String status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }
}
```

Usage:

```java
List<User> activeUsers = userRepository.findAll(UserSpecifications.hasStatus("ACTIVE"));
```

### Dynamic Queries with Criteria API

```java
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<User> cq = cb.createQuery(User.class);
Root<User> user = cq.from(User.class);
cq.select(user).where(cb.equal(user.get("status"), "ACTIVE"));
List<User> result = em.createQuery(cq).getResultList();
```

### Batch Processing
For bulk operations, use `@Modifying` with JPQL update/delete queries and configure batch size.

```java
@Modifying
@Query("UPDATE User u SET u.status = :status WHERE u.lastLogin < :date")
int deactivateOldUsers(@Param("status") String status, @Param("date") LocalDate date);
```

Configure batch size in `application.yml`:

```yaml
spring:
  jpa:
    properties:
      hibernate.jdbc.batch_size: 50
```

### Entity Graphs
Optimize fetching strategies to avoid N+1 select problems.

```java
@EntityGraph(attributePaths = {"orders"})
List<User> findAll();
```

### Auditing
Automatically track entity creation and modification timestamps.

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    // other fields
}
```

Enable auditing in configuration:

```java
@Configuration
@EnableJpaAuditing
public class JpaConfig {}
```

### Summary Table: Advanced JPA Annotations

| Annotation             | Purpose                                    | Usage Example                                   |
|------------------------|--------------------------------------------|------------------------------------------------|
| `@Modifying`           | Marks modifying queries (update/delete)    | On repository methods performing bulk updates  |
| `@EntityGraph`         | Defines fetch graph for optimized queries  | `@EntityGraph(attributePaths = {"orders"})`    |
| `@CreatedDate`         | Auditing - creation timestamp               | On entity fields with `AuditingEntityListener` |
| `@LastModifiedDate`    | Auditing - last modified timestamp          | Same as above                                  |

---

## Transaction Management

### Conceptual Overview
Transaction management ensures data integrity and consistency by grouping operations into atomic units of work. Spring provides declarative and programmatic transaction management with support for propagation behaviors and isolation levels.

### Declarative Transactions

Use `@Transactional` at method or class level to demarcate transaction boundaries.

```java
@Transactional
public void transferMoney(Account from, Account to, BigDecimal amount) {
    // business logic
}
```

### Transaction Propagation Types

| Propagation Type   | Description                                                                                     |
|-------------------|-------------------------------------------------------------------------------------------------|
| `REQUIRED`        | Support current transaction or create a new one if none exists (default).                       |
| `REQUIRES_NEW`    | Suspend current transaction and create a new one.                                              |
| `SUPPORTS`        | Support current transaction if exists; execute non-transactionally otherwise.                   |
| `NOT_SUPPORTED`   | Execute non-transactionally, suspending any existing transaction.                              |
| `MANDATORY`       | Support current transaction; throw exception if none exists.                                  |
| `NEVER`           | Execute non-transactionally; throw exception if a transaction exists.                          |
| `NESTED`          | Execute within a nested transaction if supported by the underlying DB.                         |

### Isolation Levels

| Isolation Level           | Description                                                                                      |
|---------------------------|------------------------------------------------------------------------------------------------|
| `DEFAULT`                 | Use default isolation level of the underlying datastore.                                        |
| `READ_UNCOMMITTED`        | Allows dirty reads, non-repeatable reads, and phantom reads.                                    |
| `READ_COMMITTED`          | Prevents dirty reads but allows non-repeatable reads and phantom reads.                         |
| `REPEATABLE_READ`         | Prevents dirty and non-repeatable reads but allows phantom reads.                               |
| `SERIALIZABLE`            | Highest isolation, prevents dirty, non-repeatable, and phantom reads by serializing access.    |

Example with isolation:

```java
@Transactional(isolation = Isolation.SERIALIZABLE)
public void processOrder() {
    // method implementation
}
```

### Programmatic Transaction Management

```java
DefaultTransactionDefinition def = new DefaultTransactionDefinition();
def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

TransactionStatus status = txManager.getTransaction(def);
try {
    // business logic
    txManager.commit(status);
} catch (Exception e) {
    txManager.rollback(status);
    throw e;
}
```

### Transaction Flow Summary

```
+------------------+
| Service Method    | @Transactional
+------------------+
        |
        v
+------------------+
| Transaction Start |
+------------------+
        |
        v
+------------------+
| Database Actions  |
+------------------+
        |
        v
+------------------+
| Commit / Rollback |
+------------------+
```

---

## Performance Optimization

### Connection Pooling
Spring Boot uses HikariCP by default, a high-performance JDBC connection pool.

**Basic Configuration:**

```yaml
spring:
  datasource:
    hikari:
      minimum-idle: 5
      maximum-pool-size: 20
      idle-timeout: 30000
      max-lifetime: 1800000
      connection-timeout: 30000
```

### Caching

Integrate Spring Cache abstraction with providers like Ehcache or Redis to reduce DB hits.

```java
@Cacheable("users")
public User getUser(Long id) {
    // fetch user from DB
}
```

Enable caching:

```java
@Configuration
@EnableCaching
public class CacheConfig {}
```

### Pagination & Sorting

Use Spring Data's `Pageable` and `Sort` interfaces to handle large datasets efficiently.

```java
Page<User> users = userRepository.findAll(PageRequest.of(0, 10, Sort.by("name").ascending()));
```

### Lazy vs Eager Loading

- **Lazy Loading:** Entities or collections are fetched on demand. Avoids unnecessary data retrieval but can cause N+1 problems.
- **Eager Loading:** Entities or collections are fetched immediately. Can lead to over-fetching.

**Tip:** Use `@EntityGraph` or JPQL fetch joins to optimize fetch plans.

### Query Optimization

- Enable SQL logging for analysis.

```yaml
spring:
  jpa:
    show-sql: true
    properties:
      hibernate.format_sql: true
```

- Use database indexes on frequently queried columns.
- Avoid SELECT *; fetch only required columns.

### Batch Size Tuning

Configure Hibernate batch size to optimize bulk inserts/updates.

```yaml
spring:
  jpa:
    properties:
      hibernate.jdbc.batch_size: 50
      hibernate.order_inserts: true
      hibernate.order_updates: true
```

**Example bulk insert:**

```java
for (int i = 0; i < users.size(); i++) {
    entityManager.persist(users.get(i));
    if (i % 50 == 0) {
        entityManager.flush();
        entityManager.clear();
    }
}
```

### Practical Tips Summary

| Tip                       | Description                                    |
|---------------------------|------------------------------------------------|
| Use connection pooling    | Avoid overhead of opening/closing connections  |
| Enable caching            | Reduce redundant DB queries                     |
| Use pagination            | Prevent loading large datasets into memory     |
| Optimize fetch strategies | Prevent N+1 selects and over-fetching          |
| Tune batch size           | Improve performance for bulk operations        |
| Analyze SQL logs          | Identify slow queries and optimize indexes     |

---

## NoSQL & Polyglot Persistence

### Overview
Polyglot persistence refers to using multiple types of databases depending on use case requirements. Spring Data provides support for several NoSQL databases such as MongoDB, Redis, and Elasticsearch.

---

### MongoDB

MongoDB is a document-oriented NoSQL database storing JSON-like documents.

**Spring Data MongoDB Setup:**

```java
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String name;

    private double price;

    // getters and setters
}
```

Repository interface:

```java
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
}
```

Configuration example (application.yml):

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/mydatabase
```

---

### Redis

Redis is an in-memory key-value store used for caching, messaging, and fast data access.

**Spring Data Redis Setup:**

```java
@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
}
```

Repository example:

```java
@Repository
public interface ProductRedisRepository extends CrudRepository<Product, String> {}
```

Use case: Caching frequently accessed data with TTL.

---

### Elasticsearch

Elasticsearch is a distributed search engine optimized for full-text search and analytics.

**Spring Data Elasticsearch Setup:**

```java
@Document(indexName = "products")
public class Product {
    @Id
    private String id;

    private String name;

    private String description;

    // getters and setters
}
```

Repository interface:

```java
public interface ProductSearchRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);
}
```

Configuration example:

```yaml
spring:
  elasticsearch:
    rest:
      uris: http://localhost:9200
```

---

### Polyglot Persistence Strategy

| Database     | Use Case                              | Strengths                       |
|--------------|-------------------------------------|--------------------------------|
| MongoDB      | Flexible schema, document storage   | Rich querying, aggregation      |
| Redis        | Caching, session storage, messaging | Extremely fast, in-memory       |
| Elasticsearch| Search and analytics                 | Full-text search, scalability   |

---

## Database Migrations

### Overview
Database migrations manage incremental changes to the database schema, ensuring consistency across environments.

### Flyway

Flyway uses versioned SQL scripts to apply migrations.

**Configuration:**

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
```

Migration script example (`V1__create_user_table.sql`):

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    status VARCHAR(50)
);
```

### Liquibase

Liquibase manages schema changes via XML, YAML, JSON, or SQL changelogs.

Example changelog (XML):

```xml
<changeSet id="1" author="dev">
    <createTable tableName="users">
        <column name="id" type="BIGINT" autoIncrement="true" primaryKey="true"/>
        <column name="name" type="VARCHAR(255)"/>
        <column name="status" type="VARCHAR(50)"/>
    </createTable>
</changeSet>
```

Configure Liquibase:

```yaml
spring:
  liquibase:
    change-log: classpath:db/changelog/db.changelog-master.xml
```

### Rollback & Baseline Strategies

- Plan rollback scripts or use Flyway/Liquibase commands for safe rollbacks.
- Use baseline commands to mark existing schema state when introducing migrations to legacy databases.

---

## Error Handling & Diagnostics

### Exception Translation
Spring translates low-level persistence exceptions into a consistent hierarchy of `DataAccessException`.

Annotate repositories with `@Repository` to enable this.

### Custom Exceptions

Define domain-specific exceptions to provide clearer error semantics.

```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

Throw exceptions in service layer:

```java
User user = userRepository.findById(id)
    .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
```

### Transaction Rollback Rules

Customize rollback behavior:

```java
@Transactional(rollbackFor = {CustomException.class})
public void someMethod() { ... }
```

### SQL Logging for Diagnostics

Enable detailed SQL logging:

```yaml
spring:
  jpa:
    show-sql: true
    properties:
      hibernate.format_sql: true
      hibernate.use_sql_comments: true
logging:
  level:
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
```

---

## Testing Data Access Layers

### Unit Testing with Embedded Databases

Use H2 or other embedded databases for fast, isolated tests.

```java
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByStatus() {
        User user = new User();
        user.setName("John");
        user.setStatus("ACTIVE");
        userRepository.save(user);

        List<User> activeUsers = userRepository.findByStatus("ACTIVE");
        assertThat(activeUsers).isNotEmpty();
    }
}
```

### Test Lifecycle Best Practices

- Use `@DataJpaTest` for repository layer testing.
- Rollback transactions after each test to maintain isolation.
- Use `@BeforeEach` and `@AfterEach` for setup and cleanup.
- Seed test data programmatically or via SQL scripts.

### TestEntityManager

`TestEntityManager` provides an alternative to `EntityManager` in tests with convenience methods.

```java
@Autowired
private TestEntityManager entityManager;

@Test
public void testPersistAndFind() {
    User user = new User();
    user.setName("Alice");
    entityManager.persistAndFlush(user);

    User found = entityManager.find(User.class, user.getId());
    assertThat(found.getName()).isEqualTo("Alice");
}
```

### Integration Testing with Testcontainers

Use Docker containers for real database integration tests.

```java
@Testcontainers
@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
        .withDatabaseName("testdb")
        .withUsername("user")
        .withPassword("password");

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    public void testRepository() {
        // test logic
    }
}
```

---

## Security & Data Integrity

### Input Validation

Use Bean Validation API to validate entity and DTO fields.

```java
public class UserDTO {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Email
    private String email;

    // getters and setters
}
```

Enable validation in controller layers.

### SQL Injection Prevention

- Use parameterized queries or Spring Data method parameters.
- Avoid concatenating user input into queries.

Example safe query:

```java
@Query("SELECT u FROM User u WHERE u.name = :name")
User findByName(@Param("name") String name);
```

### Encryption

Encrypt sensitive data at rest using database or application-level encryption.

Example: Encrypt passwords with BCrypt before persisting.

```java
String encodedPassword = passwordEncoder.encode(rawPassword);
user.setPassword(encodedPassword);
```

### Auditing & Logging

Track changes and access to sensitive data via auditing.

Use Spring Security and logging frameworks to monitor data access.

### Row-level Security

Implement database-level row security policies or application-level filters to restrict data visibility per user.

---

## Best Practices & Patterns

| Practice                  | Description                                                                                  |
|---------------------------|----------------------------------------------------------------------------------------------|
| Repository per Aggregate   | Design repositories to manage aggregate roots only, maintaining domain boundaries.           |
| DTOs for API Boundaries   | Use DTOs to decouple API models from persistence entities, improving flexibility and security.|
| Read/Write Separation     | Apply CQRS pattern or separate databases for read and write operations for scalability.      |
| Connection Management     | Always close JDBC resources explicitly in custom code to prevent leaks.                      |
| Migration Automation      | Integrate Flyway or Liquibase with CI/CD pipelines for automated schema management.          |

---

## References

- [Spring Data JPA Docs](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
- [Baeldung: Spring Data JPA](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)  
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)  
- [Spring Data Redis](https://spring.io/projects/spring-data-redis)  
- [Spring Data Elasticsearch](https://spring.io/projects/spring-data-elasticsearch)  
- [Flyway](https://flywaydb.org/documentation/)  
- [Liquibase](https://www.liquibase.org/)  
- [Testcontainers](https://www.testcontainers.org/)  
- [JPA Auditing](https://www.baeldung.com/database-auditing-jpa)  
- [Spring Cache Abstraction](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache)  
- [Hibernate Performance Tips](https://vladmihalcea.com/tutorials/hibernate/)  
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)  
- [Spring Data Commons](https://spring.io/projects/spring-data-commons)  
- [Spring Boot Data Access Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html)  

---
