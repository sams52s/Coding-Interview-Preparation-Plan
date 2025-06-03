## 🌿 Spring Boot & Core — Interview Q&A

---

### Q1: What is the IoC container in Spring, and how does Dependency Injection work?

**Answer:**  
The IoC (Inversion of Control) container in Spring is a core component responsible for managing the lifecycle and configuration of application objects (beans). Dependency Injection (DI) is a design pattern where the IoC container injects dependencies into objects, rather than the objects creating their own dependencies.

**Real-world scenario:**  
Imagine a web application with a `UserService` that requires a `UserRepository`. Instead of `UserService` creating a `UserRepository` instance, the IoC container injects it, making the code more modular and testable.

**Code Example:**
```java
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**Follow-up Q:** Why is Dependency Injection beneficial in large applications?

**Detailed Answer:**  
Dependency Injection promotes loose coupling, making components easier to test, maintain, and replace. In large applications, this reduces code duplication, improves modularity, and facilitates unit testing by allowing mock implementations to be injected.

---

### Q2: What are the differences between @Component, @Service, and @Repository in Spring?

**Answer:**  
All three annotations mark a class as a Spring-managed bean, but they serve different semantic purposes:
- `@Component`: Generic stereotype for any Spring-managed component.
- `@Service`: Specialization of `@Component` for service-layer classes.
- `@Repository`: Specialization for persistence-layer classes, with additional exception translation for database operations.

**Real-world scenario:**  
Use `@Service` for business logic, `@Repository` for data access, and `@Component` for other components (e.g., utility classes).

**Code Example:**
```java
@Component
public class EmailValidator { ... }

@Service
public class OrderService { ... }

@Repository
public class OrderRepository { ... }
```

**Follow-up Q:** What happens if you use `@Component` instead of `@Repository` for a DAO class?

**Detailed Answer:**  
The class will still be registered as a bean, but you lose benefits like automatic exception translation from database exceptions to Spring’s `DataAccessException`.

---

### Q3: What is auto-configuration in Spring Boot and how does it simplify application setup?

**Answer:**  
Auto-configuration is a feature in Spring Boot that automatically configures application components based on dependencies present in the classpath. It reduces boilerplate configuration, allowing developers to get started quickly with sensible defaults.

**Real-world scenario:**  
If you include `spring-boot-starter-web` in your project, Spring Boot auto-configures Tomcat, Jackson, and other web-related beans without manual setup.

**Code Example:**
```java
// No explicit configuration needed
@SpringBootApplication
public class MyApp { ... }
```

**Follow-up Q:** How can you customize or disable auto-configuration?

**Detailed Answer:**  
You can exclude specific auto-configurations using `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})` or override beans via your own `@Configuration` classes.

---

### Q4: What is the purpose of the spring-boot-starter-parent in a Spring Boot project?

**Answer:**  
`spring-boot-starter-parent` is a special starter that provides default configurations for Maven projects, such as dependency versions, plugin settings, and properties. It simplifies dependency management and ensures compatibility.

**Real-world scenario:**  
When you create a new Spring Boot Maven project, extending from `spring-boot-starter-parent` ensures you get consistent versions of dependencies and plugins.

**Code Example:**
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.0</version>
</parent>
```

**Follow-up Q:** Can you use Spring Boot without the starter parent?

**Detailed Answer:**  
Yes, but you must manage all dependency versions and plugin configurations yourself, increasing the maintenance burden.

---

### Q5: How does @Transactional work in Spring, and why is it important?

**Answer:**  
`@Transactional` is an annotation that manages database transactions. It ensures that a series of operations either all succeed or all fail, maintaining data integrity.

**Real-world scenario:**  
In a banking application, transferring money involves debiting one account and crediting another. `@Transactional` ensures both operations succeed or are rolled back together.

**Code Example:**
```java
@Transactional
public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
    accountService.debit(fromId, amount);
    accountService.credit(toId, amount);
}
```

**Follow-up Q:** What happens if an exception occurs inside a `@Transactional` method?

**Detailed Answer:**  
By default, if a runtime exception occurs, the transaction is rolled back. You can customize rollback behavior using the annotation’s attributes.

---

### Q6: How do you set up a REST controller in Spring Boot?

**Answer:**  
A REST controller is set up using the `@RestController` annotation, which combines `@Controller` and `@ResponseBody` to handle HTTP requests and return JSON/XML responses.

**Real-world scenario:**  
Building an API for a mobile app, you expose endpoints to create and fetch user data.

**Code Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // fetch and return user
    }
}
```

**Follow-up Q:** How do you handle validation and errors in a REST controller?

**Detailed Answer:**  
Use `@Valid` and exception handlers (`@ExceptionHandler`, `@ControllerAdvice`) to validate inputs and provide meaningful error responses.

---

### Q7: What are some security best practices when developing Spring Boot applications?

**Answer:**  
- Use Spring Security for authentication and authorization.
- Store secrets securely (avoid hardcoding).
- Use HTTPS for all communications.
- Validate and sanitize all user inputs.
- Implement CSRF protection for state-changing operations.
- Restrict CORS as needed.

**Real-world scenario:**  
An e-commerce app uses JWT tokens for stateless authentication and restricts admin endpoints to authorized users only.

**Code Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated();
    }
}
```

**Follow-up Q:** How do you securely store user passwords in Spring applications?

**Detailed Answer:**  
Always hash passwords using a strong algorithm like BCrypt, and never store plaintext passwords. Spring Security’s `PasswordEncoder` provides built-in support.

---

## 📖 Spring Data JPA & Hibernate — Interview Q&A

---

### Q8: How does Hibernate integrate with Spring Boot as an ORM solution?

**Answer:**  
Hibernate is a popular ORM (Object-Relational Mapping) framework. Spring Boot integrates Hibernate via Spring Data JPA, configuring it automatically based on JPA properties and dependencies.

**Real-world scenario:**  
When you add `spring-boot-starter-data-jpa` to your project, Hibernate is used by default to map Java entities to database tables.

**Code Example:**
```java
@Entity
public class Product { ... }
```
```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
```

**Follow-up Q:** Can you replace Hibernate with another JPA provider in Spring Boot?

**Detailed Answer:**  
Yes, you can use providers like EclipseLink by excluding Hibernate and adding the desired dependency.

---

### Q9: What are JPA repositories and how do they simplify data access?

**Answer:**  
JPA repositories are interfaces that extend Spring Data interfaces like `JpaRepository`, providing CRUD operations and query methods without implementing them manually.

**Real-world scenario:**  
To manage users, you define `UserRepository extends JpaRepository<User, Long>`, and Spring generates implementations for methods like `findAll()` or `findByEmail()`.

**Code Example:**
```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
```

**Follow-up Q:** How can you define custom queries in a JPA repository?

**Detailed Answer:**  
You can use method naming conventions, `@Query` annotations, or native SQL queries for complex operations.

---

### Q10: What is the difference between lazy and eager loading in JPA/Hibernate?

**Answer:**  
Lazy loading defers the loading of related entities until they are accessed, while eager loading fetches them immediately with the main entity.

**Real-world scenario:**  
Fetching an `Order` entity with a list of `OrderItems`:  
- Lazy: `OrderItems` are loaded only when accessed.  
- Eager: `OrderItems` are loaded with the `Order`.

**Code Example:**
```java
@OneToMany(fetch = FetchType.LAZY)
private List<OrderItem> items;
```

**Follow-up Q:** What issues can arise from lazy loading?

**Detailed Answer:**  
Lazy loading can cause `LazyInitializationException` if related entities are accessed outside of an active session/transaction (e.g., after the session is closed).

---

### Q11: What is the difference between @Entity and @Table annotations in JPA?

**Answer:**  
`@Entity` marks a class as a JPA entity, while `@Table` specifies the database table name and properties. `@Table` is optional; if omitted, the table name defaults to the class name.

**Real-world scenario:**  
Mapping a `User` class to a table named `app_users`:

**Code Example:**
```java
@Entity
@Table(name = "app_users")
public class User { ... }
```

**Follow-up Q:** When would you need to use the `@Table` annotation?

**Detailed Answer:**  
Use `@Table` when you want to customize the table name, define unique constraints, or specify schema details.

---

### Q12: How is transaction management handled in Spring Data JPA?

**Answer:**  
Spring Data JPA manages transactions automatically for repository methods. You can also use `@Transactional` for custom transaction boundaries.

**Real-world scenario:**  
Saving multiple entities in a service method — if one fails, the entire operation is rolled back.

**Code Example:**
```java
@Transactional
public void saveOrderAndItems(Order order, List<OrderItem> items) {
    orderRepository.save(order);
    itemRepository.saveAll(items);
}
```

**Follow-up Q:** How can you control transaction propagation and isolation levels?

**Detailed Answer:**  
Use attributes like `propagation` and `isolation` in the `@Transactional` annotation to fine-tune transactional behavior.

---

### Q13: Advanced Transaction Management Scenarios

**Interview Question:**  
How would you handle complex transaction scenarios in a distributed system?

**Answer:**  
Complex transactions require careful consideration of propagation levels, isolation, and distributed transaction management.

**Real-world Implementation:**
```java
@Service
public class OrderProcessingService {
    @Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.REPEATABLE_READ,
        rollbackFor = {PaymentException.class},
        timeout = 30
    )
    public OrderResult processOrder(Order order) {
        try {
            // Validate inventory
            inventoryService.checkStock(order.getItems());
            
            // Process payment
            paymentService.processPayment(order.getPaymentDetails());
            
            // Update inventory
            inventoryService.reduceStock(order.getItems());
            
            // Create shipping order
            shippingService.createShipment(order);
            
            return OrderResult.success(order);
        } catch (Exception e) {
            throw new OrderProcessingException("Failed to process order", e);
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCompensation(Order order) {
        // Compensation logic for failed orders
    }
}
```

**Follow-up Q1:** How do you handle partial failures in distributed transactions?

**Answer with Example:**
```java
@Service
public class CompensatingTransactionService {
    @Autowired
    private TransactionTemplate transactionTemplate;
    
    public void handlePartialFailure(Order order) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    compensatePayment(order);
                    compensateInventory(order);
                    logFailure(order);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    notifyAdmin(order, e);
                }
            }
        });
    }
}
```

**Follow-up Q2:** How do you implement idempotency in distributed transactions?

**Implementation Example:**
```java
@Service
public class IdempotentOrderService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Transactional
    public OrderResult processOrderIdempotently(String idempotencyKey, Order order) {
        String lockKey = "order:lock:" + idempotencyKey;
        
        try {
            boolean locked = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, "PROCESSING", Duration.ofMinutes(5));
                
            if (!locked) {
                return OrderResult.alreadyProcessing();
            }
            
            // Check if already processed
            Optional<OrderResult> existingResult = orderRepository
                .findByIdempotencyKey(idempotencyKey);
            if (existingResult.isPresent()) {
                return existingResult.get();
            }
            
            // Process new order
            return processOrder(order, idempotencyKey);
        } finally {
            redisTemplate.delete(lockKey);
        }
    }
}
```

---

### Q14: Advanced Spring Security Implementations

**Interview Question:**  
How would you implement a JWT-based authentication system with refresh tokens?

**Answer with Implementation:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/admin/**"). hasRole("ADMIN")
            .anyRequest().authenticated();
        
        return http.build();
    }
    
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(
            secretKey,
            accessTokenValidityInMs,
            refreshTokenValidityInMs
        );
    }
}

@Service
public class AuthenticationService {
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    
    public JwtResponse authenticate(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        
        String accessToken = tokenProvider.createAccessToken(auth);
        String refreshToken = tokenProvider.createRefreshToken(auth);
        
        saveRefreshToken(auth.getName(), refreshToken);
        
        return new JwtResponse(accessToken, refreshToken);
    }
    
    @Transactional
    public JwtResponse refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new InvalidTokenException("Invalid refresh token");
        }
        
        String username = tokenProvider.getUsername(refreshToken);
        RefreshToken storedToken = refreshTokenRepository
            .findByUsername(username)
            .orElseThrow(() -> new TokenNotFoundException());
            
        if (!storedToken.getToken().equals(refreshToken)) {
            throw new InvalidTokenException("Refresh token mismatch");
        }
        
        // Generate new tokens
        String newAccessToken = tokenProvider.createAccessToken(username);
        String newRefreshToken = tokenProvider.createRefreshToken(username);
        
        // Update stored refresh token
        storedToken.setToken(newRefreshToken);
        refreshTokenRepository.save(storedToken);
        
        return new JwtResponse(newAccessToken, newRefreshToken);
    }
}
```
