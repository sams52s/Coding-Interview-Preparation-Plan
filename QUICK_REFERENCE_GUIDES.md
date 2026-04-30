# Quick Reference Guides

Navigation: [Main README](README.md) | [Project Architecture](PROJECT_ARCHITECTURE.md) | [Interview Tracks](INTERVIEW_TRACKS.md)

This document serves as a **quick lookup reference** during interview prep and practice sessions. Keep this tab open while solving problems.

---

## 1. Java Annotations Cheat Sheet

### Built-in Annotations

| Annotation | Target | Purpose | Example |
|------------|--------|---------|---------|
| `@Override` | Method | Compiler check for overriding | `@Override public void method()` |
| `@Deprecated` | Any | Mark as obsolete | `@Deprecated(since="2.0")` |
| `@SuppressWarnings` | Any | Suppress compiler warnings | `@SuppressWarnings("unchecked")` |
| `@FunctionalInterface` | Interface | Must have single abstract method | `@FunctionalInterface public interface Predicate` |
| `@SafeVarargs` | Method | Suppress varargs warnings | `@SafeVarargs public static <T> void method(T... args)` |

### Spring Core Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Component` | Bean registration | `@Component public class MyService {}` |
| `@Service` | Business logic layer marker | `@Service public class UserService {}` |
| `@Repository` | Data access layer marker | `@Repository public class UserRepository {}` |
| `@Controller` | Web controller marker | `@Controller public class UserController {}` |
| `@RestController` | REST endpoint (combines @Controller + @ResponseBody) | `@RestController public class UserAPI {}` |
| `@Autowired` | Dependency injection | `@Autowired private UserService userService;` |
| `@Qualifier` | Specify bean to inject | `@Autowired @Qualifier("primaryBean") private Bean bean;` |
| `@Bean` | Create bean programmatically | `@Bean public UserService userService() {}` |
| `@Configuration` | Marks config class | `@Configuration public class AppConfig {}` |
| `@Value` | Inject property | `@Value("${app.name}") private String appName;` |
| `@Property Source` | Load properties file | `@PropertySource("app.properties")` |
| `@EnableXxx` | Enable feature | `@EnableCaching`, `@EnableScheduling` |
| `@Transactional` | Transaction management | `@Transactional public void saveUser() {}` |
| `@Cacheable` | Cache result | `@Cacheable("users") public User getUser(Long id)` |
| `@CacheEvict` | Remove cache | `@CacheEvict("users") public void deleteUser(Long id)` |

### Spring Web Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@RequestMapping` | Map HTTP request | `@RequestMapping("/users")` |
| `@GetMapping` | HTTP GET | `@GetMapping("/users/{id}")` |
| `@PostMapping` | HTTP POST | `@PostMapping("/users")` |
| `@PutMapping` | HTTP PUT | `@PutMapping("/users/{id}")` |
| `@DeleteMapping` | HTTP DELETE | `@DeleteMapping("/users/{id}")` |
| `@PatchMapping` | HTTP PATCH | `@PatchMapping("/users/{id}")` |
| `@PathVariable` | URL parameter | `public User getUser(@PathVariable Long id)` |
| `@RequestParam` | Query parameter | `public List<User> search(@RequestParam String name)` |
| `@RequestBody` | Parse request body | `public User create(@RequestBody User user)` |
| `@ResponseBody` | Convert return to JSON | `@ResponseBody public User getUser()` |
| `@ExceptionHandler` | Handle exceptions | `@ExceptionHandler(RuntimeException.class)` |
| `@ControllerAdvice` | Global exception handler | `@ControllerAdvice public class GlobalExceptionHandler` |
| `@ResponseStatus` | HTTP response status | `@ResponseStatus(HttpStatus.CREATED)` |
| `@CrossOrigin` | CORS support | `@CrossOrigin(origins = "http://localhost:3000")` |

### JPA/Hibernate Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Entity` | Mark as JPA entity | `@Entity public class User {}` |
| `@Table` | Specify table name | `@Table(name = "users")` |
| `@Id` | Primary key | `@Id private Long id;` |
| `@GeneratedValue` | Auto-generate ID | `@GeneratedValue(strategy = GenerationType.AUTO)` |
| `@Column` | Column configuration | `@Column(name = "user_name", nullable = false)` |
| `@Transient` | Exclude from persistence | `@Transient private String tempData;` |
| `@OneToOne` | One-to-one relationship | `@OneToOne private Address address;` |
| `@OneToMany` | One-to-many relationship | `@OneToMany(mappedBy = "user") private List<Order> orders;` |
| `@ManyToOne` | Many-to-one relationship | `@ManyToOne private User user;` |
| `@ManyToMany` | Many-to-many relationship | `@ManyToMany private List<Role> roles;` |
| `@JoinColumn` | Specify join column | `@JoinColumn(name = "user_id")` |
| `@JoinTable` | Many-to-many join table | `@JoinTable(name = "user_roles")` |
| `@Embedded` | Embed another entity | `@Embedded private Address address;` |
| `@Embeddable` | Mark as embeddable | `@Embeddable public class Address {}` |
| `@Enumerated` | Enum mapping | `@Enumerated(EnumType.STRING) private Status status;` |
| `@Temporal` | Date/time format | `@Temporal(TemporalType.TIMESTAMP)` |
| `@Lob` | Large object | `@Lob private byte[] data;` |
| `@Version` | Optimistic locking | `@Version private Long version;` |
| `@Cascade` | Cascade operations | `@OneToMany(cascade = CascadeType.ALL)` |
| `@Fetch` | Fetch strategy | `@ManyToOne(fetch = FetchType.LAZY)` |

### Testing Annotations (JUnit 5)

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Test` | Mark test method | `@Test public void testUserCreation()` |
| `@BeforeEach` | Run before each test | `@BeforeEach void setUp()` |
| `@AfterEach` | Run after each test | `@AfterEach void tearDown()` |
| `@BeforeAll` | Run before all tests (static) | `@BeforeAll static void setupAll()` |
| `@AfterAll` | Run after all tests (static) | `@AfterAll static void tearDownAll()` |
| `@Disabled` | Skip test | `@Disabled("Not ready yet")` |
| `@DisplayName` | Custom test name | `@DisplayName("User creation test")` |
| `@ParameterizedTest` | Parameterized test | `@ParameterizedTest @ValueSource(ints = {1,2,3})` |
| `@SpringBootTest` | Full Spring context | `@SpringBootTest` |
| `@WebMvcTest` | Only MVC components | `@WebMvcTest(UserController.class)` |
| `@DataJpaTest` | Only JPA layer | `@DataJpaTest` |
| `@ExtendWith` | Custom extension | `@ExtendWith(MockitoExtension.class)` |
| `@Mock` | Mock dependency (Mockito) | `@Mock private UserRepository repo;` |
| `@InjectMocks` | Inject mocks | `@InjectMocks private UserService service;` |
| `@Spy` | Spy on real object (Mockito) | `@Spy private UserService service;` |

---

## 2. SQL Query Patterns

### SELECT Variations

```sql
-- Basic SELECT
SELECT column1, column2 FROM table1;

-- DISTINCT (remove duplicates)
SELECT DISTINCT department FROM employees;

-- WHERE clause
SELECT * FROM users WHERE age > 18 AND status = 'active';

-- ORDER BY
SELECT * FROM products ORDER BY price DESC, name ASC;

-- LIMIT/OFFSET (pagination)
SELECT * FROM orders LIMIT 10 OFFSET 20;

-- CASE statement
SELECT name, 
  CASE 
    WHEN salary > 100000 THEN 'Senior'
    WHEN salary > 50000 THEN 'Mid-level'
    ELSE 'Junior'
  END AS level
FROM employees;

-- COALESCE (handle NULLs)
SELECT name, COALESCE(phone, email, 'No contact') AS contact FROM users;
```

### JOIN Types

```sql
-- INNER JOIN (only matching rows)
SELECT u.name, o.order_id 
FROM users u 
INNER JOIN orders o ON u.id = o.user_id;

-- LEFT JOIN (all from left table)
SELECT u.name, COUNT(o.id) AS order_count 
FROM users u 
LEFT JOIN orders o ON u.id = o.user_id 
GROUP BY u.id, u.name;

-- RIGHT JOIN (all from right table)
SELECT o.order_id, u.name 
FROM users u 
RIGHT JOIN orders o ON u.id = o.user_id;

-- FULL OUTER JOIN (all from both)
SELECT u.name, o.order_id 
FROM users u 
FULL OUTER JOIN orders o ON u.id = o.user_id;

-- CROSS JOIN (Cartesian product)
SELECT * FROM colors CROSS JOIN sizes;

-- Self-join
SELECT e1.name AS employee, e2.name AS manager 
FROM employees e1 
JOIN employees e2 ON e1.manager_id = e2.id;
```

### Aggregation

```sql
-- COUNT, SUM, AVG, MIN, MAX
SELECT 
  COUNT(*) AS total_orders,
  SUM(amount) AS total_revenue,
  AVG(amount) AS avg_order,
  MIN(amount) AS min_order,
  MAX(amount) AS max_order
FROM orders;

-- GROUP BY with HAVING
SELECT department, AVG(salary) AS avg_salary 
FROM employees 
GROUP BY department 
HAVING AVG(salary) > 60000;

-- String aggregation (database-specific)
-- PostgreSQL: string_agg(name, ', ')
-- MySQL: GROUP_CONCAT(name SEPARATOR ', ')
SELECT department, GROUP_CONCAT(name SEPARATOR ', ') AS employees 
FROM employees 
GROUP BY department;
```

### Window Functions / Analytical

```sql
-- ROW_NUMBER (1, 2, 3...)
SELECT name, salary, 
  ROW_NUMBER() OVER (ORDER BY salary DESC) AS rank 
FROM employees;

-- RANK (gaps for ties: 1, 1, 3, 4)
SELECT name, salary, 
  RANK() OVER (ORDER BY salary DESC) AS rank 
FROM employees;

-- DENSE_RANK (no gaps: 1, 1, 2, 3)
SELECT name, salary, 
  DENSE_RANK() OVER (ORDER BY salary DESC) AS rank 
FROM employees;

-- LAG / LEAD (previous/next row)
SELECT name, salary, 
  LAG(salary) OVER (ORDER BY salary) AS prev_salary,
  LEAD(salary) OVER (ORDER BY salary) AS next_salary 
FROM employees;

-- Running total
SELECT date, amount, 
  SUM(amount) OVER (ORDER BY date) AS running_total 
FROM transactions 
ORDER BY date;

-- PARTITION BY
SELECT department, name, salary,
  AVG(salary) OVER (PARTITION BY department) AS dept_avg 
FROM employees;
```

### Subqueries & CTEs

```sql
-- Subquery in WHERE
SELECT * FROM users 
WHERE id IN (SELECT user_id FROM orders WHERE amount > 1000);

-- Subquery in FROM
SELECT * FROM (
  SELECT user_id, COUNT(*) as order_count 
  FROM orders 
  GROUP BY user_id
) subq 
WHERE order_count > 5;

-- Common Table Expression (CTE)
WITH high_value_users AS (
  SELECT user_id, SUM(amount) as total 
  FROM orders 
  GROUP BY user_id 
  HAVING SUM(amount) > 10000
)
SELECT u.name, h.total 
FROM users u 
JOIN high_value_users h ON u.id = h.user_id;

-- Recursive CTE (hierarchy/tree)
WITH RECURSIVE hierarchy AS (
  SELECT id, parent_id, name, 0 as level 
  FROM categories 
  WHERE parent_id IS NULL
  UNION ALL
  SELECT c.id, c.parent_id, c.name, h.level + 1 
  FROM categories c 
  JOIN hierarchy h ON c.parent_id = h.id
)
SELECT * FROM hierarchy;
```

### INSERT, UPDATE, DELETE

```sql
-- INSERT
INSERT INTO users (name, email, age) 
VALUES ('John', 'john@example.com', 30);

-- INSERT from SELECT
INSERT INTO archive_users 
SELECT * FROM users WHERE created_at < DATE_SUB(NOW(), INTERVAL 2 YEAR);

-- UPDATE
UPDATE users SET age = 31, updated_at = NOW() WHERE id = 1;

-- UPDATE with JOIN
UPDATE employees e 
JOIN departments d ON e.dept_id = d.id 
SET e.salary = e.salary * 1.1 
WHERE d.budget > 100000;

-- DELETE
DELETE FROM users WHERE id = 1;

-- DELETE with JOIN
DELETE u FROM users u 
WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.user_id = u.id);
```

### Index Best Practices

```sql
-- CREATE INDEX
CREATE INDEX idx_user_email ON users(email);

-- COMPOSITE INDEX (order matters!)
CREATE INDEX idx_order_user_date ON orders(user_id, created_at);

-- CHECK INDEX usage
EXPLAIN SELECT * FROM users WHERE email = 'test@example.com';

-- DROP INDEX
DROP INDEX idx_user_email ON users;
```

---

## 3. System Design Algorithms Quick Reference

### Consistent Hashing

**When to Use:** Distributed caching, load balancing, data sharding  
**Time Complexity:** O(log N) for node lookup

```
Ring: [0 ... 360]
Hash node position: hash(node_id) % 360
Hash key position: hash(key) % 360
Data goes to: NextNode clockwise on ring

Rebalancing on node failure: Only affects keys in that node's range
```

**Implementation Tip:**
```
- Use sorted map (TreeMap in Java)
- Add virtual nodes (replicas) to reduce imbalance
- Common: 3 virtual nodes per physical node
```

### Rate Limiting Algorithms

| Algorithm | Complexity | Use Case | Pros | Cons |
|-----------|-----------|----------|------|------|
| **Token Bucket** | O(1) | Allows bursts | Flexible, handles spikes | Memory overhead |
| **Leaky Bucket** | O(1) | Smooth rate | Consistent output | No burst handling |
| **Sliding Window** | O(N) | Per-minute limits | Precise | Memory intensive |
| **Fixed Window** | O(1) | Simple limits | Fast | Edge case issues |

**Token Bucket Example:**
```
Bucket capacity: 100 tokens
Refill rate: 10 tokens/second
Request costs: 1 token

Request arrives:
  if bucket.tokens >= cost:
    bucket.tokens -= cost
    ALLOW
  else:
    DENY
```

### Load Balancing Algorithms

| Algorithm | Complexity | Best For | Pros | Cons |
|-----------|-----------|----------|------|------|
| **Round Robin** | O(1) | Uniform load | Simple, fair distribution | Ignores server capacity |
| **Least Connections** | O(1) | Long-lived connections | Balances active requests | More computation |
| **Weighted** | O(1) | Heterogeneous servers | Accounts for capacity | Needs tuning |
| **IP Hash** | O(1) | Sticky sessions | Deterministic | Can create imbalance |
| **Consistent Hash** | O(log N) | Cache/CDN | Minimal rebalancing | More complex |

### Caching Patterns

**Cache-Aside (Lazy Loading):**
```
Request → Check cache → Hit: Return
                     → Miss: Load from DB → Store in cache → Return
Pro: Simple, data freshness guaranteed
Con: Cache miss penalty, stale data possible
```

**Write-Through:**
```
Write → Cache + DB simultaneously
Pro: Data consistency
Con: Slower writes, cache miss on crash
```

**Write-Behind (Write-Back):**
```
Write → Cache immediately → Async write to DB
Pro: Fast writes
Con: Data loss risk, complex
```

### Database Sharding Strategies

| Strategy | Example | Pros | Cons |
|----------|---------|------|------|
| **Range** | By user_id (1-1M, 1M-2M) | Simple, range queries easy | Unbalanced load |
| **Hash** | hash(user_id) % num_shards | Balanced distribution | Range queries hard |
| **Directory** | Lookup table user_id → shard | Flexible, rebalancing easy | Lookup overhead |
| **Geographic** | By region/country | Local data, compliance | Unbalanced growth |

### Circuit Breaker Pattern

```
CLOSED → trying to call service
  Success: stay CLOSED
  Failure: increment count
  Failure threshold reached → OPEN

OPEN → not calling service
  After timeout → HALF_OPEN

HALF_OPEN → test service health
  Success → back to CLOSED
  Failure → back to OPEN
```

### Retry Strategy

```
Exponential Backoff:
Delay = base_delay * (exponential_base ^ attempt)
Example: 1s, 2s, 4s, 8s, 16s

With jitter (prevent thundering herd):
Delay = base_delay * (2 ^ attempt) + random(0, jitter)

Max retry: 3-5 attempts
Timeout per attempt: 5-10 seconds
```

---

## 4. Docker & Kubernetes Commands

### Docker

```bash
# Build image
docker build -t myapp:1.0 .

# Run container
docker run -d -p 8080:8080 --name myapp myapp:1.0
docker run -e APP_ENV=prod -v /host/path:/container/path myapp:1.0

# List/manage
docker ps              # Running containers
docker images          # Available images
docker logs myapp      # View logs
docker exec -it myapp bash  # Shell access
docker stop/start myapp
docker rm myapp        # Delete container
docker rmi myapp:1.0   # Delete image

# Push to registry
docker tag myapp:1.0 registry.com/myapp:1.0
docker push registry.com/myapp:1.0

# Docker Compose
docker-compose up -d           # Start services
docker-compose down            # Stop services
docker-compose logs -f myapp   # Follow logs
```

### Kubernetes

```bash
# Deployment
kubectl create deployment myapp --image=myapp:1.0
kubectl apply -f deployment.yaml
kubectl get deployments
kubectl describe deployment myapp
kubectl rollout status deployment/myapp

# Pods
kubectl get pods
kubectl get pods -o wide          # Show node assignment
kubectl logs pod-name
kubectl exec -it pod-name -- bash

# Service exposure
kubectl expose deployment myapp --port=80 --target-port=8080 --type=LoadBalancer
kubectl get svc
kubectl describe svc myapp

# Scaling
kubectl scale deployment myapp --replicas=5
kubectl autoscale deployment myapp --min=2 --max=10 --cpu-percent=80

# ConfigMap & Secrets
kubectl create configmap app-config --from-file=config.properties
kubectl create secret generic db-secret --from-literal=password=secret123
kubectl get configmaps / secrets

# Debugging
kubectl get events
kubectl top nodes
kubectl top pods
kubectl describe pod pod-name
```

---

## 5. Git Workflow for Interviews

### Basic Commands

```bash
# Check status
git status
git diff              # Unstaged changes
git diff --staged     # Staged changes

# Stage & commit
git add file.txt      # Add specific file
git add .             # Add all changes
git commit -m "feat: add user authentication"

# Push & pull
git push origin main
git pull origin main

# View history
git log --oneline -10
git log --graph --decorate --all
git show commit-hash
```

### Handling Mistakes (Important for Interviews!)

```bash
# Undo uncommitted local changes
git reset HEAD file.txt  # Unstage
git checkout -- file.txt # Discard changes

# Amend last commit
git commit --amend           # Update message
git commit --amend --no-edit # Add forgotten files

# Undo committed changes (creates new commit)
git revert commit-hash

# Undo to previous commit (careful! loses history)
git reset --soft HEAD~1      # Keep changes staging
git reset --mixed HEAD~1     # Keep changes unstaged (default)
git reset --hard HEAD~1      # Discard changes

# Find lost commits
git reflog
git reset --hard reflog-id
```

### Interview Setup

```bash
# Clone and setup
git clone <repo-url>
cd <repo>
git checkout -b feature/interview-solution

# During interview
git add solution.java
git commit -m "Solve user management system"
git push origin feature/interview-solution

# After interview
git checkout main
git pull origin main
```

---

## 📌 Bookmark This Section During Practice

Save this document for quick reference while coding. Most common lookups:
- **Java Annotations**: When using Spring/JPA
- **SQL Patterns**: When writing database queries
- **Algorithms**: When designing systems
- **Docker/K8s**: When discussing deployment
- **Git**: When handling code mistakes
