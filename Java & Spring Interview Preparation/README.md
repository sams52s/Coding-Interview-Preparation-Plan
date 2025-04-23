
# 🚩 Java & Spring Interview Preparation Checklist


## 🚀 [Introduction to Java](https://github.com/sams52s/Coding-Interview-Preparation-Plan/tree/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Introduction%20to%20Java)
---
## Topics to cover:
- [Java Basics](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Introduction%20to%20Java/Java%20Basics.md)
- [History, Features, Evolution (Java 8 → Java 24+)](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Introduction%20to%20Java/Java%20History%2C%20Features%2C%20and%20Evolution.md)
- [JVM, JRE, JDK – Differences & Roles](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Introduction%20to%20Java/JVM%2C%20JRE%2C%20JDK%20%E2%80%93%20Differences%20%26%20Roles.md)
- [Java IDEs (Eclipse, IntelliJ IDEA, NetBeans)](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Introduction%20to%20Java/Java%20IDEs.md)

## 🔍 OOP Concepts
- Classes & Objects
- Constructors & Overloading
- Static & Instance Members
- Inheritance, super keyword, Method Overriding
- Abstraction & Abstract Classes
- Interfaces & Functional Interfaces
- Polymorphism (Compile-time & Runtime)
- Encapsulation
- Access Modifiers (public, private, protected, default)

## 📌 Exception Handling
- Checked vs Unchecked Exceptions
- `try-catch-finally`
- Multi-catch blocks
- `throw` vs `throws`
- Custom Exceptions
- Best Practices

## 📚 Java Collections Framework
- Interfaces: List, Set, Queue, Map
- Implementations: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `LinkedHashMap`, `TreeMap`
- Iterator, ListIterator, Enumeration
- Collections Utility Class
- Comparable vs Comparator

## ✨ Generics
- Type Parameters & Wildcards
- Bounded Types
- Generic Methods & Classes

## 💾 Java I/O & NIO
- File Handling (File, FileReader, BufferedReader)
- Serialization & Deserialization
- Java NIO (Buffers, Channels, Selectors)

## 🔄 Multithreading & Concurrency
- Thread Lifecycle, Runnable, Callable
- Executor Framework (ThreadPool)
- Synchronization, Locks (ReentrantLock)
- `wait()`, `notify()`, `notifyAll()`
- Atomic Classes, `volatile`
- Concurrency Utilities (`CountDownLatch`, `CyclicBarrier`, `Semaphore`)
- CompletableFuture

## 🔗 Lambda & Streams (Java 8+)
- Lambda Expressions
- Stream API (filter, map, reduce, collect)
- Method References
- Functional Interfaces (Predicate, Function, Consumer, Supplier)
- Optional Class

## 🏷️ Java Annotations
- Built-in (`@Override`, `@FunctionalInterface`, `@SuppressWarnings`)
- Custom Annotations
- Retention, Target, Reflection Processing
- Meta-Annotations (`@Documented`, `@Inherited`, `@Retention`, `@Target`)


## 🧠 Java Memory Management
- Stack vs Heap Memory
- Garbage Collection & Types (G1, ZGC, Shenandoah)
- JVM Flags & Optimization
- Memory Leaks, Profiling Tools (VisualVM, JProfiler)
- Escape Analysis

## 📦 Java Modules (Java 9+)
- Introduction to JPMS
- `module-info.java`
- Modular Programming

## 🛠️ ClassLoader & Reflection
- ClassLoader Hierarchy (Bootstrap, Extension, Application)
- Custom ClassLoader Implementation
- Reflection API

## 🔵 Java Reflection
-  Dynamic Class & Method Invocation
-  Pros & Cons of Reflection
-  Use Cases (Frameworks, Libraries)

## 🎯 Design Patterns
- Singleton, Factory, Abstract Factory
- Builder, Prototype
- Adapter, Strategy, Observer, Template
- DAO, DTO, MVC

## ✅ Best Practices
- Clean Code Principles
- Code Optimization Techniques
- Defensive Coding
- Immutability & Exception Design

## 🍃 Spring Core
- IoC & Dependency Injection (Constructor, Setter, Field)
- Bean Scopes & Lifecycle
- XML, Annotation, Java Configurations
- ApplicationContext vs BeanFactory

### Spring Core & Dependency Injection
- IoC Container & DI Patterns (Constructor, Setter, Field)
- Bean Lifecycle & Scopes (singleton, prototype, request, session)
- BeanPostProcessor & BeanFactoryPostProcessor
- Circular Dependency Resolution
- Advanced DI Annotations (`@Lazy`, `@Primary`, `@Qualifier`, `@Lookup`)
- Profiles & Conditional Beans (`@Profile`, `@Conditional`)

## 📌 Spring Annotations (Comprehensive List)
### 🌱 Core Stereotype Annotations
- @Component
- @Service
- @Repository
- @Controller

### ⚙️ Configuration Annotations
- @Configuration
- @Bean
- @Import
- @ImportResource
- @ComponentScan
- @ConfigurationProperties
- @EnableConfigurationProperties
- @PropertySource

### 🔄 Lifecycle Callback Annotations
- @PostConstruct
- @PreDestroy

### 🔍 Conditional Annotations
- @Conditional
- @ConditionalOnClass
- @ConditionalOnMissingBean
- @ConditionalOnProperty

### 🔗 Dependency Injection Annotations
- @Autowired
- @Qualifier
- @Primary
- @Lazy
- @Value

### 📐 Bean Scope & Profile Annotations
- @Scope
- @Profile

### 🗂️ Event & Asynchronous Annotations
- @EventListener
- @EnableAsync
- @Async

### ⏰ Scheduling Annotations
- @Scheduled
- @EnableScheduling

### 🔄 Transaction Management Annotations
- @Transactional
- @EnableTransactionManagement

### 🗃️ Caching Annotations
- @Cacheable
- @CachePut
- @CacheEvict
- @Caching
- @EnableCaching

### 🌐 Web & Reactive Web Annotations
- @EnableWebMvc
- @EnableWebFlux
- @RestController
- @RequestMapping
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
- @RequestBody, @ResponseBody
- @PathVariable, @RequestParam, @RequestHeader, @CookieValue
- @ControllerAdvice
- @ExceptionHandler
- @CrossOrigin

### 📡 Spring Boot Specific Annotations
- @SpringBootApplication
- @EnableAutoConfiguration
- @ConditionalOnBean
- @ConditionalOnMissingClass
- @ConditionalOnExpression
- @ConditionalOnResource
- @ConditionalOnSingleCandidate
- @ConditionalOnWebApplication
- @ConditionalOnNotWebApplication

### 🧩 AOP Annotations
- @Aspect
- @Before
- @After
- @AfterReturning
- @AfterThrowing
- @Around
- @EnableAspectJAutoProxy


## 🚦 Spring Boot
- Starters & Auto-Configuration
- Application Properties & YAML
- Profiles & Environment
- CommandLineRunner & ApplicationRunner
- Embedded Servers (Tomcat, Jetty)

## 🌐 Spring MVC
- DispatcherServlet Flow
- Controllers & RequestMapping
- REST Controllers (`@RestController`, `@RequestBody`, `@PathVariable`)
- Exception Handling (`@ExceptionHandler`, `@ControllerAdvice`)
- Validation (`@Valid`, BindingResult)
- Form Handling & Data Binding

## 🗃️ Spring Data JPA
- Repository Interfaces (`CrudRepository`, `JpaRepository`)
- Entities & Relationships (OneToOne, OneToMany, ManyToOne, ManyToMany)
- Fetch Types (EAGER vs LAZY)
- Query Methods & `@Query`
- Transaction Management (`@Transactional`)
- Pagination & Sorting
- Spring Data REST
- Spring Data JPA Auditing
- Resolving N+1 Problem
- Criteria API & Specifications

## 🔐 Spring Security
- Basic Authentication & JWT
- OAuth2 Concepts
- Filters & Authentication Flow
- Security Annotations (`@PreAuthorize`, `@Secured`)

## 🟠 Spring Reactive Programming
-  Reactor (`Mono`, `Flux`)
-  WebFlux & WebClient
-  Backpressure & RSocket Integration

## 🔗 Spring Integration
- Message Channels & Endpoints
- Spring Integration DSL
- File Transfer & Transformation
- Spring Cloud Stream
- Spring Cloud Data Flow

## Spring AOP
-  Joinpoints, Pointcuts, Advice
-  Proxy Types (CGLIB vs JDK Dynamic
-  AspectJ Integration
-  Transaction Management with AOP
-  Logging & Monitoring with AOP
-  Performance Monitoring
-  Caching with AOP



## 🛠️ Lombok
- Annotations (`@Getter`, `@Setter`, `@Data`, `@Builder`, `@Slf4j`)
- Lombok & Spring Integration

## 💼 Hibernate Integration
- JPA vs Hibernate
- First & Second-Level Caching
- Lazy Initialization Issues

## 📡 Spring REST APIs
- Building RESTful APIs
- HATEOAS, HTTP Methods & Status Codes
- Global Exception Handling in REST
- Swagger & OpenAPI Documentation

## ⚙️ JVM Internals & Performance
- Java Compilation & Class Loading
- JIT Compiler & Garbage Collection Tuning
- Profiling Tools (VisualVM, JProfiler)
- Thread Dumps & Heap Dumps

## 🐳 DevOps & Build Tools
- Maven & Gradle
- Docker & Containerization
- CI/CD Basics
- Logging (Logback, Log4j)
- Spring Boot Deployment (AWS, Linux Server)

## 🧪 Testing
- JUnit 5 & Mockito
- Integration Testing
- TestContainers
- Spring Test (`@WebMvcTest`, `@DataJpaTest`, `@SpringBootTest`)

## 📖 Miscellaneous
- Java vs Kotlin, Spring vs Spring Boot
- Microservices Concepts
- REST vs SOAP APIs
- JSON & XML Serialization (Jackson, Gson)
- Postman API Testing

## 🔧 Additional Topics
- Dependency Injection
- `@Autowired`, Constructor Injection
- Beans & Java Lifecycle
- Classpath Management
- Java & Spring Integration
- Lombok & Spring Annotation Deep Dive

## ⚪️ Build Tools & DevOps
-  Maven (Lifecycle & Dependencies)
-  Gradle Basics
-  Docker & Containerization

## 🟡 Design Patterns
- Singleton, Factory, Abstract Factory
- Builder, Prototype, Adapter, Strategy, Observer, Decorator
- Spring Patterns (Proxy/AOP, Template method)
- MVC, DAO, DTO
- Microservices Patterns (Circuit Breaker, API Gateway)
- Event-Driven Architecture
- CQRS, Saga Pattern
- Event Sourcing
- Domain-Driven Design (DDD)
- Microservices Communication Patterns (REST, gRPC, GraphQL)
- Service Discovery Patterns
- Rate Limiting Patterns

## 📅 Interview Preparation Tips
- Daily coding practice
- Mock interviews
- Diagram visualizations
- Real-world scenarios & clear explanations

# 🔗 Java & Spring Resources & Practice Links

## 🌐 Official Documentation
- [Java Official Documentation](https://docs.oracle.com/en/java/)
- [Spring Framework Official](https://spring.io/projects/spring-framework)
- [Spring Boot Official](https://spring.io/projects/spring-boot)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Official Documentation](https://site.mockito.org/)
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/)
- [Docker Official Documentation](https://docs.docker.com/)
- [Maven Central Repository](https://search.maven.org/)
- [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [Spring Annotations Reference](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config.html)
- [RESTful API Guide](https://restfulapi.net/)
- [Postman API Testing](https://learning.postman.com/docs/getting-started/introduction/)

## 📖 Tutorials & Guides
- [Baeldung (Java & Spring Tutorials)](https://www.baeldung.com/)
- [Java Guides](https://www.javaguides.net/)
- [GeeksforGeeks Java](https://www.geeksforgeeks.org/java/)
- [Java Design Patterns (Refactoring Guru)](https://refactoring.guru/design-patterns/java)
- [FreeCodeCamp Java & Spring](https://www.freecodecamp.org/learn/java-programming/)

## 🎓 Courses
- [Udemy: Java Masterclass](https://www.udemy.com/course/java-the-complete-java-developer-course/)

## 📚 Books
- [Effective Java](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)
- [Spring in Action](https://www.amazon.com/Spring-Action-Craig-Walls/dp/1617294942)

## 💻 Practice Platforms
- [LeetCode](https://leetcode.com/)
- [HackerRank (Java)](https://www.hackerrank.com/domains/java)

## 🖥️ GitHub Examples
- [GitHub Spring Boot Examples](https://github.com/spring-projects/spring-boot/tree/main/spring-boot-samples)
