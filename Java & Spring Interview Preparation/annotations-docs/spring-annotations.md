# 🌱 Spring Annotations

Spring Framework and Spring Boot rely heavily on annotations to reduce boilerplate code and enable declarative configuration. These annotations provide powerful ways to configure components, inject dependencies, manage transactions, define REST APIs, and more.

---

## 📦 1. Core Component Annotations

| Annotation       | Purpose                                               |
|------------------|--------------------------------------------------------|
| `@Component`      | Generic stereotype for any Spring-managed component   |
| `@Service`        | Marks a service layer class                           |
| `@Repository`     | DAO layer marker; enables exception translation       |
| `@Controller`     | Marks a web controller (MVC)                          |
| `@RestController` | Shortcut for `@Controller + @ResponseBody`           |
| `@Configuration`  | Indicates a class defines beans                       |
| `@Bean`           | Declares a bean in a `@Configuration` class           |

```java
@Service
public class UserService { ... }

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

---

## 💉 2. Dependency Injection Annotations

| Annotation     | Purpose                                      |
|----------------|-----------------------------------------------|
| `@Autowired`    | Injects dependencies by type                 |
| `@Qualifier`    | Resolves conflicts with multiple beans       |
| `@Inject`       | JSR-330 alternative to `@Autowired`          |
| `@Value`        | Injects values from properties or SpEL       |
| `@Primary`      | Declares a default bean when multiple candidates exist |

```java
@Autowired
@Qualifier("emailService")
private NotificationService service;

@Value("${server.port}")
private int port;
```

---

## 🧵 3. Lifecycle and Scope

| Annotation      | Purpose                                  |
|------------------|-------------------------------------------|
| `@PostConstruct` | Invoked after dependency injection        |
| `@PreDestroy`    | Called before bean is destroyed           |
| `@Scope`         | Defines bean scope (`singleton`, `prototype`, etc.) |

```java
@PostConstruct
public void init() {
    // initialization logic
}
```

---

## 🌐 4. Web and REST Annotations

| Annotation       | Purpose                                       |
|------------------|------------------------------------------------|
| `@RequestMapping` | Maps requests to controller methods           |
| `@GetMapping`     | Shortcut for `@RequestMapping(method = GET)` |
| `@PostMapping`    | Shortcut for POST                             |
| `@PutMapping`     | Shortcut for PUT                              |
| `@DeleteMapping`  | Shortcut for DELETE                           |
| `@PathVariable`   | Binds URI path variable                       |
| `@RequestParam`   | Binds query parameter                         |
| `@RequestBody`    | Deserializes request body to Java object      |
| `@ResponseBody`   | Serializes return value into HTTP response    |

```java
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}
```

---

## 🔐 5. Security and Validation

| Annotation       | Purpose                                 |
|------------------|------------------------------------------|
| `@Secured`        | Restricts access to roles                |
| `@PreAuthorize`   | Checks expression before method executes |
| `@Validated`      | Enables validation on a class or method  |
| `@Valid`          | Triggers bean validation                 |

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) { ... }
```

---

## 🛠️ 6. Spring Boot Annotations

| Annotation               | Purpose                                                              |
|--------------------------|----------------------------------------------------------------------|
| `@SpringBootApplication` | Combines `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan` |
| `@EnableAutoConfiguration` | Enables Spring Boot's auto-config                                 |
| `@ComponentScan`         | Scans specified packages for components                             |
| `@EnableConfigurationProperties` | Binds configuration properties to Java class              |

```java
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

---

## ⚙️ 7. Transaction Management

| Annotation      | Purpose                          |
|-----------------|-----------------------------------|
| `@Transactional` | Defines transactional boundaries |

```java
@Transactional
public void transferMoney(...) { ... }
```

---

## 🧪 8. Spring Testing Annotations

| Annotation          | Purpose                              |
|---------------------|--------------------------------------|
| `@SpringBootTest`    | Loads full application context        |
| `@WebMvcTest`        | Loads only Spring MVC components     |
| `@DataJpaTest`       | Loads only JPA-related components    |
| `@MockBean`          | Replaces bean with Mockito mock      |
| `@TestConfiguration` | Defines test-specific beans          |

```java
@SpringBootTest
public class UserServiceTest {
    @Autowired private UserService userService;
}
```

---

## 💡 Best Practices

- Use `@Component`-level annotations appropriately (`@Service`, `@Repository`).
- Combine `@Configuration` with `@Bean` for manual bean creation.
- Prefer constructor injection over field injection.
- Keep controllers thin; delegate logic to service layer.

---

## 💼 Interview Questions & Answers — Spring Annotations

### ❓ What is the difference between `@Component`, `@Service`, and `@Repository`?

All are stereotype annotations that register Spring beans.  
- `@Component` is general-purpose.  
- `@Service` signals service layer logic.  
- `@Repository` wraps data access and enables exception translation.

---

### ❓ How does `@Autowired` work internally?

It uses reflection and the `AutowiredAnnotationBeanPostProcessor` to inject beans by type. If multiple candidates exist, Spring throws an error unless `@Qualifier` or `@Primary` is used.

---

### ❓ Constructor vs Field Injection?

- Constructor injection is **preferred** for testability and immutability.
- Field injection is simpler but harder to test.

---

### ❓ What is `@SpringBootApplication`?

It combines:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

To bootstrap and scan Spring Boot applications from the main class.

---

### ❓ Difference between `@RestController` and `@Controller`?

- `@Controller` returns views (e.g., JSP).
- `@RestController` returns JSON/XML directly as response body.

---

### ❓ How does `@Transactional` work?

Spring uses proxies (AOP) to wrap methods. Transactions are committed or rolled back based on exception types and method boundaries.

---

### ❓ What are Spring bean scopes?

- `singleton` (default)
- `prototype`
- `request`, `session`, `application` (for web context)

---

### ❓ What is `@Qualifier` and when is it used?

Used with `@Autowired` when multiple beans of the same type exist.

---

### ❓ What’s the difference between `@Bean` and `@Component`?

- `@Bean` is used in `@Configuration` to define beans programmatically.
- `@Component` is used on the class itself for scanning.

---

### ❓ Key Spring Testing annotations?

- `@SpringBootTest`: Full context
- `@WebMvcTest`: Controller tests
- `@DataJpaTest`: JPA layer
- `@MockBean`: Replace real beans with mocks

---

### ❓ What is `@EnableConfigurationProperties`?

It binds external configuration (like `application.yml`) to Java POJOs using `@ConfigurationProperties`.

---

### ❓ What’s the difference between `@Valid` and `@Validated`?

- `@Valid`: JSR-303 standard annotation.
- `@Validated`: Spring’s enhancement that supports validation groups.

---

### ❓ Can we create custom annotations in Spring?

Yes. Combine multiple annotations into one:
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface MyCustomService {}
```

---

### ❓ How do you handle conditional bean creation?
**✅ A:** Spring provides several conditional annotations:
```java
@Configuration
public class ConditionalConfig {
    @Bean
    @ConditionalOnProperty(name = "app.feature.enabled", havingValue = "true")
    public FeatureService featureService() {
        return new FeatureService();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "com.amazonaws.services.s3.AmazonS3")
    public StorageService s3StorageService() {
        return new S3StorageService();
    }
}
```

### ❓ How do you implement custom validation with annotations?
**✅ A:**
```java
@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password is too weak";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class PasswordStrengthValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && 
               password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*[0-9].*");
    }
}

public class UserDTO {
    @StrongPassword
    private String password;
}
```

### ❓ How do you implement custom aspect with annotations?
**✅ A:**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}

@Aspect
@Component
public class LoggingAspect {
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("{} executed in {}ms", joinPoint.getSignature(), (end - start));
        return result;
    }
}
```

### ❓ How do you implement custom error handling with annotations?
**✅ A:**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("USER_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());
        
        return ResponseEntity
            .badRequest()
            .body(new ValidationErrorResponse(errors));
    }
}
```

### ❓ How do you implement custom configuration properties?
**✅ A:**
```java
@ConfigurationProperties(prefix = "app.mail")
@Validated
public class MailProperties {
    @NotNull
    private String host;
    
    @Min(1) @Max(65535)
    private int port = 25;
    
    private Security security = new Security();
    
    @Valid
    public static class Security {
        private boolean enabled = true;
        private String username;
        private String password;
        // getters, setters
    }
    // getters, setters
}

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfig {
    @Autowired
    private MailProperties mailProperties;
    
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        // configure other properties
        return sender;
    }
}
```

### ❓ How do you implement method security with annotations?
**✅ A:**
```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }
}

@Service
public class SecuredService {
    @PreAuthorize("hasRole('ADMIN') and #user.department == authentication.principal.department")
    public void processSecuredOperation(@P("user") User user) {
        // secured operation
    }
    
    @PostAuthorize("returnObject.owner == authentication.principal.username")
    public Document getDocument(Long id) {
        return documentRepository.findById(id);
    }
}
```

---

## ✅ Summary

Spring annotations abstract away configuration and wiring, helping developers build modular, testable, and maintainable applications with ease. Mastery of these annotations is essential for Spring and Spring Boot developers.

---

## 📝 References

- [Spring Core Annotations](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html)  
- [Spring Boot Annotations Guide – Baeldung](https://www.baeldung.com/spring-annotations)  
- [Spring Security Docs](https://docs.spring.io/spring-security/)  
- [Spring Validation Docs](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
