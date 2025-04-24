## 📘 Comprehensive Software Engineering Interview Question Bank

🎯 Use this interactive checklist to track your interview prep. Tick off each question as you go!
🧠 Difficulty levels: 🔰 Beginner | ⚙️ Intermediate | 🧠 Advanced

Want to contribute new questions or improved answers? [Open an issue or PR](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Question%20Bank.md) 🙌


### 📑 Table of Contents

- [🧠 Programming Fundamentals](#🧠-programming-fundamentals)
- [☕ Core Java & Exception Handling](#☕-core-java--exception-handling)
- [📚 Collections Framework](#📚-collections-framework)
- [🧵 Multithreading](#🧵-multithreading)
- [☕ Spring Boot & Core](#☕-spring-boot--core)
- [📖 Spring Data JPA & Hibernate](#📖-spring-data-jpa--hibernate)
- [🧩 Microservices](#🧩-microservices)
- [🌐 REST API in Java Spring](#🌐-rest-api-in-java-spring)
- [🚀 Advanced & Additional Questions](#🚀-advanced--additional-questions)
- [🧩 Data Structures and Algorithms](#🧩-data-structures-and-algorithms)
- [🧱 System Design](#🧱-system-design)
- [💾 Database & SQL](#💾-database--sql)
- [🌐 Web & Frontend](#🌐-web--frontend)
- [🧪 Miscellaneous & Puzzles](#🧪-miscellaneous--puzzles)
- [📚 Resources & Recommended Reading](#📚-resources--recommended-reading)

### 🧠 Programming Fundamentals
<details><summary>Click to expand Q&A</summary>



#### Beginner 🔰

1. **What are the primitive and non-primitive data types in Java? How are they different?**

> **Answer:** Primitive types include `int`, `char`, `float`, `boolean`, etc. They store actual values directly. Non-primitive types (or reference types) include `String`, `Array`, `Class`, and custom objects. These store references (memory addresses) to the actual data. Primitive types are stored on the stack while non-primitives are stored in the heap.

2. **Why are strings immutable in Java?**

> **Answer:** Strings are immutable because once created, their values cannot be changed. This helps in caching (e.g., String pool), thread safety, and security (e.g., avoiding data manipulation in network connections or file paths).

3. **What are the four principles of Object-Oriented Programming (OOP)?**

> **Answer:**
>
> - **Encapsulation:** Binding data and methods together.
> - **Abstraction:** Hiding complex details and showing only the essential features.
> - **Inheritance:** Acquiring properties and behaviors from another class.
> - **Polymorphism:** Ability to take many forms (method overloading/overriding).

4. **What is the purpose of the static keyword in Java methods?**

> **Answer:** It allows a method to belong to the class rather than instances of it. Static methods can be called without creating an object.

5. **Explain the difference between method overloading and method overriding.**

> **Answer:**
>
> - **Overloading:** Same method name with different parameter lists within the same class.
> - **Overriding:** A subclass redefines a method from the parent class with the same signature.

6. **What is the difference between an abstract class and an interface in Java?**

> **Answer:** An abstract class can have method implementations and constructors. Interfaces (prior to Java 8) only contained abstract methods. Java 8+ allows default and static methods in interfaces.

7. **Why are immutable objects often preferred in OOP design?**

> **Answer:** They simplify concurrent programming, are inherently thread-safe, and avoid unintended side effects. They are easier to test and debug.

8. **What are the key points of OOP?**

> **Answer:**
>
> - Code reusability through inheritance
> - Improved maintainability via encapsulation
> - Flexibility and polymorphism
> - Secure architecture via abstraction

9. **What is the difference between pass-by-value and pass-by-reference in Java?**

> **Answer:** Java uses pass-by-value for everything. When passing objects, the reference value (address) is passed, but not the actual object. Changes affect the object pointed to, but not the reference itself.

10. **How does Java achieve platform independence?**

> **Answer:** Java source code is compiled into bytecode, which runs on the JVM (Java Virtual Machine). JVMs exist for different platforms, making Java "write once, run anywhere."

11. **What is encapsulation in OOP, and why is it important?**

> **Answer:** It is the process of wrapping data and code together into a single unit (e.g., class). It protects internal state and ensures only controlled access via getters/setters.

12. **Explain the difference between constructors and methods in Java.**

> **Answer:**
>
> - **Constructor:** Initializes a new object; has the same name as the class and no return type.
> - **Method:** Performs actions or computations; has a return type and can be called multiple times.

13. **Why do we use getters and setters in Java?**

> **Answer:** They allow controlled access to private fields. Getters retrieve field values; setters modify them, enabling encapsulation and validation logic.

#### Intermediate ⚙️

1. **What happens when you declare a variable or class with the final keyword?**

> **Answer:** Declaring a variable as `final` means its value cannot be changed once assigned. Declaring a class as `final` means it cannot be subclassed. Declaring a method as `final` means it cannot be overridden by subclasses.

2. **Describe the effect of using the final keyword on variables and classes in Java.**

> **Answer:** For variables, `final` makes them constants (one-time assignment). For methods, it restricts overriding. For classes, it prevents inheritance, which can improve security and performance.

3. **What is the diamond problem in OOP, and how does Java address it?**

> **Answer:** The diamond problem occurs in multiple inheritance when two parent classes have the same method, leading to ambiguity. Java avoids this by not allowing multiple class inheritance. Instead, it uses interfaces with default methods and requires explicit resolution if conflict arises.

4. **What are the key differences between stack, queue, and heap data structures?**

> **Answer:**
>
> - **Stack:** LIFO (Last In First Out), used for function calls.
> - **Queue:** FIFO (First In First Out), used in scheduling.
> - **Heap:** A tree-based structure used for efficient retrieval of max/min elements (used in priority queues and memory allocation).

5. **Describe how Java handles memory management, including stack and heap usage.**

> **Answer:** Java uses automatic garbage collection. Stack memory stores primitive variables and method call frames. Heap memory stores objects and class instances. The JVM manages memory using generational GC algorithms to optimize performance.

6. **What is polymorphism and how is it implemented in Java?**

> **Answer:** Polymorphism allows objects to take many forms. In Java, it is implemented through method overriding (runtime polymorphism) and method overloading (compile-time polymorphism). It promotes code flexibility and reusability.

7. **Compare and contrast abstract classes and interfaces. What are their respective benefits?**

> **Answer:** Abstract classes can provide partial implementation, hold state via instance variables, and define constructors. Interfaces are purely abstract (before Java 8) and support multiple inheritance. Use abstract classes when classes share common code; use interfaces to define contract behavior across unrelated classes.

8. **How does garbage collection work in Java?**

> **Answer:** The JVM automatically reclaims memory by deleting unreachable objects. It uses generational garbage collection—Young Generation, Old Generation, and Permanent Generation. Collectors like G1, CMS, and Parallel GC handle this task in different ways to optimize pause time and throughput.

9. **What is the difference between checked and unchecked exceptions in Java?**

> **Answer:** Checked exceptions are checked at compile time (e.g., IOException), and must be either caught or declared. Unchecked exceptions (e.g., NullPointerException) occur at runtime and are subclasses of `RuntimeException`. Checked exceptions force error handling; unchecked ones are for programming errors.

10. **Explain the SOLID principles in OOP design.**

> **Answer:**
>
> - **S**: Single Responsibility — one reason to change.
> - **O**: Open/Closed — open for extension, closed for modification.
> - **L**: Liskov Substitution — objects of a superclass should be replaceable with subclass objects.
> - **I**: Interface Segregation — many specific interfaces over one general-purpose.
> - **D**: Dependency Inversion — depend on abstractions, not concretions.

11. **What is reflection in Java, and why might you use it?**

> **Answer:** Reflection allows inspection and manipulation of classes, methods, and fields at runtime. It’s useful for frameworks (like Spring), debuggers, and tools that work with dynamic code but can be slower and compromise security if misused.

12. **How does Java handle multiple inheritance?**

> **Answer:** Java avoids ambiguity of multiple class inheritance by allowing it only through interfaces. A class can implement multiple interfaces but extend only one class. If two interfaces have the same default method, the implementing class must override it to resolve conflict.

#### Advanced 🔰🔰🔰 🧠

1. **Implement the Singleton design pattern in Java.**

> **Answer:** The Singleton pattern ensures only one instance of a class is created.

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

2. **Identify OOP features in a given code sample.**

> **Answer:** Look for:
>
> - Encapsulation (private fields, public getters/setters)
> - Inheritance (class extends another class)
> - Polymorphism (method overriding or overloading)
> - Abstraction (abstract classes/interfaces)

3. **How would you implement a thread-safe Singleton in Java?**

> **Answer:** Use synchronized block or double-checked locking.

```java
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

4. **What is the difference between deep copy and shallow copy in Java?**

> **Answer:**
>
> - **Shallow copy:** Copies object references (shared mutable state).
> - **Deep copy:** Recursively copies all fields and referenced objects (full duplication).

5. **Implement a producer-consumer scenario using Java concurrency APIs.**

> **Answer:**

```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
Runnable producer = () -> {
    try {
        while (true) {
            queue.put(produceItem());
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
};
Runnable consumer = () -> {
    try {
        while (true) {
            consumeItem(queue.take());
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
};
```

6. **Explain volatile keyword usage with examples in Java.**

> **Answer:** `volatile` ensures visibility of changes to variables across threads. It prevents caching and reads/writes always happen from/to main memory.

```java
public class SharedObject {
    private volatile boolean flag = false;
    public void writer() {
        flag = true;
    }
    public void reader() {
        if (flag) {
            // Do something
        }
    }
}
```








</details>

### ☕ Core Java & Exception Handling
<details><summary>Click to expand Q&A</summary>



1. **Explain Java ClassLoader and its types.**

> **Answer:** ClassLoader loads compiled Java classes into the JVM during runtime. Types include:
>
> - **Bootstrap ClassLoader**: Loads core Java classes from `rt.jar`
> - **Extension ClassLoader**: Loads classes from `ext` directory
> - **Application ClassLoader**: Loads classes from the classpath

2. **What is serialization and deserialization in Java?**

> **Answer:** Serialization converts an object into a byte stream for storage or transmission. Deserialization converts the byte stream back into an object. Implement `Serializable` interface to use it.

3. **What is Java Reflection API?**

> **Answer:** Reflection API allows inspection and manipulation of classes, fields, methods, and constructors at runtime. It’s used in frameworks like Spring and for dynamic behavior.

4. **Describe the try-catch-finally blocks with examples.**

> **Answer:**

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error: " + e);
} finally {
    System.out.println("Cleanup executed");
}
```

> `try` block executes code, `catch` handles exceptions, and `finally` runs regardless of outcome.

5. **How to create a custom exception in Java?**

> **Answer:**

```java
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
```

> Use `throw new MyException("message")` to throw it and handle using `try-catch`.








</details>

### 📚 Collections Framework
<details><summary>Click to expand Q&A</summary>



1. **What is the difference between List, Set, and Map?**

> **Answer:**
>
> - **List:** Ordered collection that allows duplicates (e.g., `ArrayList`, `LinkedList`).
> - **Set:** Unordered collection that doesn’t allow duplicates (e.g., `HashSet`, `TreeSet`).
> - **Map:** Key-value pairs; keys are unique (e.g., `HashMap`, `TreeMap`).

2. **Describe the internal implementation of HashMap.**

> **Answer:** HashMap uses an array of buckets. Each bucket is a linked list (or a tree in case of hash collisions after Java 8). Keys are hashed to find the index, and collisions are handled using chaining.

3. **Explain ConcurrentHashMap and its use cases.**

> **Answer:** It’s a thread-safe version of HashMap. It uses internal partitioning to allow concurrent access without locking the entire map. Useful in multi-threaded applications where read-write operations are common.

4. **How does TreeSet ensure sorting of elements?**

> **Answer:** TreeSet uses a Red-Black Tree (self-balancing binary search tree). Elements are sorted using natural order or a custom comparator.






</details>

### 🧵 Multithreading
<details><summary>Click to expand Q&A</summary>



1. **Explain thread lifecycle in Java.**

> **Answer:** States: `New`, `Runnable`, `Running`, `Blocked/Waiting`, and `Terminated`. Threads transition between these states based on code execution and synchronization.

2. **What is thread synchronization, and how is it implemented?**

> **Answer:** Synchronization ensures that only one thread accesses a resource at a time. Implemented using `synchronized` blocks/methods or `Lock` classes.

3. **What is the difference between Runnable and Callable?**

> **Answer:** `Runnable` does not return a result or throw checked exceptions. `Callable` returns a result and can throw exceptions. Used with `ExecutorService` for multi-threaded tasks.

4. **How can you handle deadlocks in Java?**

> **Answer:** Avoid nested locks, use timeout for acquiring locks, or detect cycles in thread dependency graphs. Tools like `jconsole` can help detect deadlocks.






</details>

### ☕ Spring Boot & Core
<details><summary>Click to expand Q&A</summary>



1. **Explain Spring IOC container.**

> **Answer:** It manages object creation and dependency injection. It uses XML or annotations to wire components automatically.

2. **What is Spring Boot Auto-Configuration?**

> **Answer:** Automatically configures Spring application based on dependencies present in the classpath. It reduces manual configuration effort.

3. **Describe the differences between @Component, @Service, and @Repository annotations.**

> **Answer:** All are stereotype annotations. `@Component` is generic, `@Service` is for business logic, `@Repository` is for data access and adds exception translation.

4. **Explain Dependency Injection in Spring.**

> **Answer:** Spring injects dependencies into a class through constructor, setter, or field injection. It promotes loose coupling and modular design.






</details>

### 📖 Spring Data JPA & Hibernate
<details><summary>Click to expand Q&A</summary>



1. **What is Hibernate, and how does it integrate with Spring Data JPA?**

> **Answer:** Hibernate is an ORM framework that maps Java objects to database tables. Spring Data JPA abstracts Hibernate, providing JPA repository interfaces and query creation from method names.

2. **Explain the concept of Lazy and Eager loading.**

> **Answer:** Lazy loading loads related data on demand. Eager loading fetches all related data immediately. Use lazy for performance; eager for convenience when all data is required.

3. **What is the difference between @Entity and @Table annotations?**

> **Answer:** `@Entity` marks a class as a JPA entity. `@Table` specifies the table name and schema details. If `@Table` is omitted, the default table name is the entity name.

4. **How do you handle transactions in Spring?**

> **Answer:** Use `@Transactional` annotation. Spring manages commit and rollback automatically. Propagation and isolation levels can be configured for fine-grained control.






</details>

### 🧩 Microservices
<details><summary>Click to expand Q&A</summary>


1. **What are microservices, and why use them?**

> **Answer:** Microservices are small, independent services that communicate via APIs. They promote modularity, scalability, and ease of deployment.

2. **Explain service discovery and registry (e.g., Eureka).**

> **Answer:** Services register themselves with a registry (e.g., Eureka Server). Clients query the registry to discover available service instances dynamically.

3. **How do microservices handle failures and recovery?**

> **Answer:** Techniques include retries, circuit breakers (e.g., Resilience4j), failover mechanisms, and fallback methods.

4. **Describe communication methods between microservices.**

> **Answer:** Synchronous (REST, gRPC) and asynchronous (Kafka, RabbitMQ). Choice depends on latency, reliability, and coupling requirements.



5. **What is distributed tracing and why is it important?**

> **Answer:** Distributed tracing tracks requests as they flow through microservices, helping debug, analyze latency, and monitor end-to-end flows. Tools: Spring Cloud Sleuth + Zipkin. Example:

```properties
spring.application.name=order-service
spring.sleuth.sampler.probability=1.0
spring.zipkin.base-url=http://localhost:9411
```

Log output includes trace and span IDs:

```
[order-service,2485d271f7e1aef2,2485d271f7e1aef2] Processing order 123
```

6. **What is an API Gateway and what problems does it solve?**

> **Answer:** A gateway acts as a reverse proxy that routes requests to microservices and manages:
>
> - Routing
> - Load balancing
> - Authentication and authorization
> - Rate limiting and monitoring
> - Request transformation
>   Example Spring Cloud Gateway config:

```java
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("product_route", r -> r
            .path("/products/**")
            .filters(f -> f.rewritePath("/products/(?<segment>.*)", "/${segment}")
                            .addRequestHeader("X-Gateway-Source", "api-gateway")
                            .circuitBreaker(c -> c.setName("productCB")
                                                  .setFallbackUri("forward:/fallback/products")))
            .uri("lb://product-service"))
        .build();
}
```






</details>

### 🌐 REST API in Java Spring
<details><summary>Click to expand Q&A</summary>



1. **What are RESTful web services, and how do you design them?**

> **Answer:** RESTful services use HTTP methods (GET, POST, PUT, DELETE) and follow REST principles like statelessness, resource-based URLs, and standard status codes. Design using controllers, DTOs, and service layers.

2. **Explain HTTP status codes used in REST APIs.**

> **Answer:**
>
> - `200 OK`: Successful request
> - `201 Created`: Resource created
> - `400 Bad Request`: Client error
> - `401 Unauthorized` / `403 Forbidden`: Auth errors
> - `404 Not Found`: Resource missing
> - `500 Internal Server Error`: Server failure

3. **How do you implement exception handling in REST APIs?**

> **Answer:** Use `@ControllerAdvice` with `@ExceptionHandler` to centralize exception handling. Return meaningful error responses using custom exception classes and error DTOs.

4. **What are different ways to secure REST APIs in Spring?**

> **Answer:**
>
> - Basic Auth
> - OAuth 2.0 / JWT tokens
> - API Key validation
> - HTTPS and CORS settings
> - Method-level security (`@PreAuthorize`, `@Secured`)






</details>

### 🚀 Advanced & Additional Questions
<details><summary>Click to expand Q&A</summary>



6. **What are best practices for writing secure Java code?**

> **Answer:**
>
> - Validate user inputs (whitelisting preferred)
> - Use prepared statements to prevent SQL injection
> - Avoid storing plain-text passwords; use bcrypt or Argon2
> - Sanitize outputs to prevent XSS
> - Avoid exposing stack traces in production
> - Use HTTPS for all data transmissions
> - Keep dependencies updated to patch known vulnerabilities

7. **How would you securely store passwords in a Java application?**

> **Answer:** Use hashing algorithms like BCrypt or Argon2 with a unique salt for each password. Spring Security provides `BCryptPasswordEncoder`:

```java
PasswordEncoder encoder = new BCryptPasswordEncoder();
String hashed = encoder.encode("myPassword");
```

8. **What is CI/CD and why is it important in DevOps?**

> **Answer:** CI/CD automates build, test, and deployment processes. CI (Continuous Integration) merges code frequently with automatic testing. CD (Continuous Delivery/Deployment) ensures rapid and reliable app delivery.

9. **How would you set up a CI/CD pipeline for a Spring Boot app?**

> **Answer:** Tools: Jenkins/GitHub Actions + Maven + Docker + Ansible/K8s. Stages include:
>
> - Checkout code
> - Build JAR (Maven)
> - Unit tests + coverage
> - Static analysis (e.g., SonarQube)
> - Package + Archive artifacts
> - Deploy to Dev/Staging/Prod with approvals

10. **How do you containerize a Java app using Docker?**

> **Answer:**

```dockerfile
FROM eclipse-temurin:17-jre-alpine
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Run: `docker build -t myapp . && docker run -p 8080:8080 myapp`

11. **How does Kubernetes help in deploying Java apps at scale?**

> **Answer:** Kubernetes automates deployment, scaling, and recovery. Components include Deployments, Pods, Services, ConfigMaps, Secrets, Ingress, and Probes (health checks).

12. **What are unit testing best practices in Java?**

> **Answer:**
>
> - Use JUnit 5 with descriptive names
> - Follow AAA pattern (Arrange, Act, Assert)
> - Keep tests independent
> - Use mocks for external dependencies
> - Aim for high coverage but focus on meaningful tests

13. **How would you test a Spring REST API?**

> **Answer:** Use `@WebMvcTest`, `@SpringBootTest`, and MockMvc for controller layer tests:

```java
@Test
public void shouldReturnUser() throws Exception {
    mockMvc.perform(get("/users/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id").value(1));
}
```

1. **Explain JVM memory model and garbage collection tuning.**

> **Answer:** JVM divides memory into stack, heap (Young/Old), metaspace. GC tuning adjusts memory sizes and GC algorithms (G1, ZGC) to reduce pauses and optimize throughput.

2. **How does Java 8 Stream API improve collection processing?**

> **Answer:** Stream API allows functional-style processing (map, filter, reduce) with lazy evaluation and parallelism, improving code readability and performance.

3. **Describe Reactive Programming with Spring WebFlux.**

> **Answer:** WebFlux supports reactive streams using `Mono` and `Flux`. It's non-blocking and suitable for handling massive I/O workloads efficiently.

4. **What is Docker, and how is it used with Java applications?**

> **Answer:** Docker packages applications with dependencies into containers. Java apps run consistently across environments using Docker images.

5. **What are functional interfaces and lambda expressions?**

> **Answer:** Functional interfaces have a single abstract method (e.g., `Runnable`, `Comparator`). Lambdas provide concise implementations:

```java
(x, y) -> x + y;
```








</details>

### 🧩 Data Structures and Algorithms
<details><summary>Click to expand Q&A</summary>



#### Beginner

1. **Given an array of integers, write a program to find the second largest number.**

> **Answer:**

```java
int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
for (int num : array) {
    if (num > first) {
        second = first;
        first = num;
    } else if (num > second && num != first) {
        second = num;
    }
}
System.out.println("Second largest: " + second);
```

2. **What is the difference between an array and a linked list?**

> **Answer:**
>
> - **Array:** Fixed size, random access, contiguous memory.
> - **Linked List:** Dynamic size, sequential access, scattered memory using nodes.

3. **Given a binary tree, write a function to return its in-order traversal.**

> **Answer:**

```java
void inOrder(TreeNode node) {
    if (node != null) {
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }
}
```

#### Intermediate

1. **Given an integer array, move all zeroes to the end.**

> **Answer:**

```java
int index = 0;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] != 0) {
        nums[index++] = nums[i];
    }
}
while (index < nums.length) {
    nums[index++] = 0;
}
```

2. **Explain how binary search works and its limitations.**

> **Answer:** Binary search repeatedly divides a sorted array in half, checking the middle element. Limitations: it requires the array to be sorted and doesn't work efficiently on linked lists due to lack of random access.

#### Advanced 🔰🔰🔰

1. **Implement Dijkstra's algorithm.**

> **Answer:** Dijkstra's algorithm finds the shortest path from a source node to all other nodes in a weighted graph with non-negative weights.

```java
class Graph {
    int V;
    List<List<Node>> adj;

    class Node implements Comparable<Node> {
        int vertex, weight;
        Node(int v, int w) {
            vertex = v; weight = w;
        }
        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
    }

    void dijkstra(int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;
            if (visited[u]) continue;
            visited[u] = true;

            for (Node neighbor : adj.get(u)) {
                if (!visited[neighbor.vertex] && dist[u] + neighbor.weight < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = dist[u] + neighbor.weight;
                    pq.add(new Node(neighbor.vertex, dist[neighbor.vertex]));
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }
}
```

2. **Design an LRU cache.**

> **Answer:** LRU (Least Recently Used) Cache stores items based on access order. Use `LinkedHashMap` or combine HashMap + Doubly Linked List.

```java
class LRUCache {
    private final int capacity;
    private final Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
```




</details>

### 🧱 System Design
<details><summary>Click to expand Q&A</summary>



1. **Design a URL shortener ensuring uniqueness.**

> **Answer:** Use base62 or base64 encoding on a unique ID (e.g., database auto-increment or UUID). Maintain a database table mapping the short URL to the original URL. Ensure uniqueness by checking for collisions. Use caching for popular links.

2. **How would you scale a messaging queue system?**

> **Answer:** Use a distributed messaging broker like Kafka or RabbitMQ. Partition topics and replicate for fault tolerance. Use consumer groups to parallelize processing. Monitor throughput and latency. Implement retries, DLQs (Dead Letter Queues), and auto-scaling consumers.

3. **How many APIs are required to solve the ticket reservation problem?**

> **Answer:** Typically, you need:
>
> - Search API (check availability)
> - Booking API (lock and reserve)
> - Payment API (process transaction)
> - Confirmation API (finalize ticket)
> - Cancellation/Refund API

4. **How would you design a rate-limiting system for an API?**

> **Answer:** Use token bucket or leaky bucket algorithms. Store token state in Redis or memory. Use middleware/interceptor to enforce limits based on API keys, IPs, or user IDs. Include headers to communicate remaining quota.

5. **Design a system to monitor electricity usage across a region in real time.**

> **Answer:**
>
> - IoT devices send data via MQTT/HTTP
> - Stream data using Apache Kafka
> - Store in a time-series DB (e.g., InfluxDB)
> - Visualize in dashboards (Grafana)
> - Include alerting and historical trend analysis

6. **Suppose you're building an e-commerce site with product listings and details. How would you structure the backend and database?**

> **Answer:**
>
> - Use microservices: Product, Inventory, Cart, Order, Payment
> - Relational DBs for transactional data; NoSQL for product catalogs
> - Search service (Elasticsearch)
> - Caching (Redis), async processing (RabbitMQ/Kafka)
> - REST or GraphQL APIs

7. **Create a design for a simplified version of an internet banking backend.**

> **Answer:**
>
> - Services: User Auth, Account, Transaction, Notification
> - Database with ACID guarantees
> - Secure API Gateway, JWT authentication
> - Event logging and transaction audit
> - Redundancy and fraud detection systems

8. **How would you design a distributed caching system?**

> **Answer:** Use Redis or Memcached in a cluster. Partition keys using consistent hashing. Use replication and backup nodes for high availability. Implement cache invalidation strategies (LRU, TTL). Consider eventual consistency for distributed nodes.


</details>

### 💾 Database & SQL
<details><summary>Click to expand Q&A</summary>



3. **Write a SQL query to find duplicate rows in a table.**

> **Answer:**

```sql
SELECT column_name, COUNT(*)
FROM table_name
GROUP BY column_name
HAVING COUNT(*) > 1;
```

4. **What is indexing in databases and how does it improve performance?**

> **Answer:** Indexes are special lookup tables that improve query speed. They allow the database to find data without scanning every row. Indexes are created on columns that are frequently searched or used in JOIN, WHERE, or ORDER BY clauses.

5. **Write a SQL query to fetch the second highest salary from an employee table.**

> **Answer:**

```sql
SELECT MAX(salary)
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```

1. **What is the difference between DELETE, TRUNCATE, and DROP in SQL?**

> **Answer:**
>
> - **DELETE:** Removes specific rows; can be rolled back; slower.
> - **TRUNCATE:** Removes all rows; faster; can't be rolled back.
> - **DROP:** Deletes the entire table structure and data.

2. **Explain inner join, outer join, left join, and right join.**

> **Answer:**
>
> - **INNER JOIN:** Matches records in both tables.
> - **LEFT JOIN:** All from left table + matched from right.
> - **RIGHT JOIN:** All from right table + matched from left.
> - **OUTER JOIN (FULL):** All records from both tables; unmatched = NULL.

1. What is the difference between DELETE, TRUNCATE, and DROP in SQL?
2. Explain inner join, outer join, left join, and right join.


</details>

### 🌐 Web & Frontend
<details><summary>Click to expand Q&A</summary>



3. **What is event-driven programming in JavaScript?**

> **Answer:** It's a programming paradigm where the flow of execution is determined by events (e.g., user interactions, messages). Functions are executed when a specific event occurs.

4. **How does EventListener work in DOM manipulation?**

> **Answer:** `addEventListener()` attaches an event handler to an element. It listens for a specific event (like click, mouseover) and triggers a callback function when the event occurs.

5. **What is the difference between synchronous and asynchronous JavaScript?**

> **Answer:**
>
> - **Synchronous:** Code runs sequentially. Each operation blocks the next until complete.
> - **Asynchronous:** Operations (e.g., setTimeout, fetch) run in the background. Other code can execute while waiting for a response.





### 🧪 Miscellaneous & Puzzles
<details><summary>Click to expand Q&A</summary>



### 🧠 Additional Core Java & OOP Questions (Extended)

1. **What is the use of the `super` keyword in Java?**
> **Answer:** `super` is used to refer to the immediate parent class. It is commonly used to:
> - Call a parent class constructor
> - Access parent class methods/fields that are overridden or hidden

2. **What exactly is `System.out.println` in Java?**
> **Answer:** `System` is a class from `java.lang`, `out` is a static PrintStream object, and `println()` is a method of PrintStream used to print a line to the console.

3. **What part of memory is cleaned during garbage collection?**
> **Answer:** The heap memory. It stores objects, and garbage collection reclaims memory from unreachable objects in the heap.

4. **What is constructor overloading in Java?**
> **Answer:** Defining multiple constructors with different parameter lists in the same class to initialize objects in various ways.

5. **What is the difference between static methods, static variables, and static classes?**
> **Answer:**
> - **Static variable:** Shared across all instances of a class
> - **Static method:** Belongs to the class, not the instance
> - **Static class:** Only inner classes can be static in Java; used for grouping static methods/constants

6. **What is the difference between a private and a protected modifier?**
> **Answer:**
> - **Private:** Accessible only within the declared class
> - **Protected:** Accessible within the same package and by subclasses

7. **What is a marker interface?**
> **Answer:** An interface with no methods or fields, used to signal the compiler or JVM with metadata (e.g., `Serializable`, `Cloneable`).

8. **What is the purpose of the Comparable interface?**
> **Answer:** Provides a way to define natural ordering for objects. Requires implementing `compareTo()`.

9. **What is the difference between a HashSet and a TreeSet?**
> **Answer:**
> - **HashSet:** Unordered, constant-time performance
> - **TreeSet:** Sorted, logarithmic time performance

10. **What is the purpose of the `java.util.concurrent` package?**
> **Answer:** It provides utilities for concurrent programming (e.g., thread pools, atomic variables, locks, concurrent collections) to improve multithreading performance and scalability.

### 🧵 Additional Multithreading Questions

1. **What is context switching in Java?**
> **Answer:** Switching the CPU from one thread to another. It involves saving the state of the current thread and loading the state of the next thread.

2. **What is the difference between user threads and daemon threads?**
> **Answer:**
> - **User threads:** Run until task completion, prevent JVM shutdown
> - **Daemon threads:** Run in background (e.g., GC), JVM terminates when only daemon threads remain

3. **What is the purpose of the sleep() method in Java?**
> **Answer:** Pauses the execution of the current thread for a specified time, allowing other threads to execute.

4. **What is the difference between wait() and sleep()?**
> **Answer:**
> - `wait()` releases the monitor lock; used in synchronization blocks for inter-thread communication
> - `sleep()` pauses execution but retains locks

5. **What is the difference between notify() and notifyAll()?**
> **Answer:**
> - `notify()` wakes a single waiting thread
> - `notifyAll()` wakes all waiting threads on the object's monitor

1. **Explain Agile methodologies.**

> **Answer:** Agile is an iterative approach to software development that focuses on collaboration, customer feedback, and small, rapid releases. Frameworks include Scrum, Kanban, XP.

2. **Describe Continuous Integration and Continuous Deployment (CI/CD).**

> **Answer:** CI automates code integration into a shared repo, running tests with every commit. CD automates deployment to production after CI, reducing release time and risks.



> **Answer:**
>
> - **Monolithic:** Single codebase, tightly coupled components, easier to deploy but harder to scale and maintain.
> - **Microservices:** Multiple independent services, loosely coupled, easier to scale and deploy, but more complex to manage and require inter-service communication.

6. **How do you ensure data consistency in a distributed system?**

> **Answer:** Use distributed transactions (e.g., two-phase commit), eventual consistency models, or consensus algorithms (e.g., Paxos, Raft). Choose based on system requirements and trade-offs between consistency and availability.

7. **What is the CAP theorem?**

> **Answer:** The CAP theorem states that in a distributed system, you can only achieve two of the following three guarantees:
>
> - **Consistency:** All nodes see the same data at the same time.
> - **Availability:** Every request receives a response, regardless of the state of any individual node.
> - **Partition Tolerance:** The system continues to operate despite network partitions.

8. **How would you calculate the time complexity of a given algorithm?**

> **Answer:** Analyze how the number of operations grows with input size. Look at loops, recursion depth, and nested operations. Express it using Big-O notation (e.g., O(1), O(n), O(log n), O(n²)).

9. **A poisoned candy problem: How many test subjects are needed to detect the poisoned candy from 1000 options within 1 hour?**

> **Answer:** Use binary encoding. You need `log2(1000) ≈ 10` test subjects. Each candy corresponds to a binary number, and each tester represents one bit. You test all candies simultaneously and decode results using which testers get poisoned.

10. **Given 100 bulbs toggled by 100 people based on divisibility rules, how many bulbs are on at the end?**

> **Answer:** Bulbs with an odd number of divisors remain ON. Only perfect squares have an odd number of divisors. So, bulbs 1, 4, 9, ..., 100 (i.e., 10 bulbs) remain ON.


</details>

### 📚 Resources & Recommended Reading

**📘 Books:**

- *Effective Java* by Joshua Bloch — Best practices and design principles
- *Java Concurrency in Practice* by Brian Goetz — Deep dive into Java multithreading
- *Spring in Action* by Craig Walls — Great for mastering Spring Framework
- *Designing Data-Intensive Applications* by Martin Kleppmann — Must-read for System Design
- *Clean Code* by Robert C. Martin — Fundamental for clean architecture and refactoring
- *Grokking the System Design Interview* — Simplifies system design for interviews
- *Cracking the Coding Interview* by Gayle Laakmann McDowell — DSA + Java-based solutions

**🌐 Online Platforms:**

- [GeeksforGeeks](https://www.geeksforgeeks.org/) — CS concepts and Java
- [Java Brains](https://www.youtube.com/user/koushks) — Spring & RESTful APIs (YouTube)
- [Baeldung](https://www.baeldung.com/) — High-quality Java + Spring articles
- [Dev.to](https://dev.to/t/spring) — Spring community articles
- [Spring Official Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/) — Core reference
- [Kubernetes Docs](https://kubernetes.io/docs/) — Production-grade deployment guide
- [Docker Docs](https://docs.docker.com/) — For containerization fundamentals
- [JWT.io](https://jwt.io/introduction) — JWT standards and debugging tool
- [OWASP](https://owasp.org/) — Security standards and Top 10 risks

