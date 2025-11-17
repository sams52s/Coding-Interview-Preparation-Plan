# 🔐 Security & ⚡ Performance in System Design

---

## 1. Overview
Security and performance are foundational pillars in distributed system design. Security ensures that data and services are protected from unauthorized access and malicious actors, while performance guarantees that the system responds quickly and efficiently under various loads. These two aspects often have trade-offs; for example, adding security layers like encryption can introduce latency, while optimizing for speed may expose vulnerabilities. Designing a robust system requires balancing both to meet business and user needs.

A fundamental model in security is the **CIA Triad**, which stands for:
- **Confidentiality**: Ensuring that information is accessible only to those authorized to have access.
- **Integrity**: Maintaining and assuring the accuracy and completeness of data over its lifecycle.
- **Availability**: Ensuring that authorized users have reliable access to information and resources when needed.

In modern cloud-native systems, balancing security and performance is even more critical due to dynamic scaling, multi-tenant environments, and complex service interactions. Effective design must consider how security mechanisms impact latency and throughput, and how performance optimizations might introduce security risks.

---

## 2. Security Fundamentals

### 🔒 2.1 Core Security Concepts
| Concept         | Description                                      |
|-----------------|--------------------------------------------------|
| Authentication  | Verifying user identity (e.g., username/password, OAuth) |
| Authorization   | Ensuring users can only access allowed resources |
| Encryption      | Protecting data at rest and in transit           |
| Integrity       | Ensuring data is not tampered with               |
| Confidentiality | Preventing unauthorized data access              |

### 🔐 2.2 Common Attack Vectors & Mitigations
| Attack Type           | Description                              | Mitigation                              |
|-----------------------|------------------------------------------|----------------------------------------|
| SQL Injection         | Malicious SQL queries via input           | Prepared statements, Input validation  |
| XSS                   | Injecting malicious scripts               | Input sanitization, CSP headers        |
| CSRF                  | Unauthorized actions on behalf of user   | CSRF tokens, SameSite cookies          |
| MITM                  | Intercepting communication                | TLS/SSL, Certificate pinning           |
| DDoS                  | Overwhelming system resources             | Rate limiting, WAF, CDN                 |
| Brute Force           | Systematic password guessing              | Rate limiting, Account lockout         |
| Insecure Deserialization | Exploiting unsafe deserialization of data | Input validation, Use safe libraries    |
| Security Misconfiguration | Incorrectly configured security controls | Regular audits, Automated configuration management |

### 🛡 2.3 Security Implementation Examples

#### Authentication with JWT
```java
@RestController
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Validate credentials
        if (isValidUser(request)) {
            String token = jwtTokenUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.status(401).build();
    }
}
```

##### JWT Structure Breakdown
A JSON Web Token (JWT) consists of three parts separated by dots (`.`):
- **Header**: contains metadata such as the signing algorithm and token type.
- **Payload**: contains claims or statements about an entity (user) and additional data.
- **Signature**: verifies the token’s integrity and authenticity using a secret or public/private key.

Example token format:  
`xxxxx.yyyyy.zzzzz`

---

#### CSRF Protection using Spring Security
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeRequests()
                .anyRequest().authenticated();
    }
}
```

---

#### API Gateway Security
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: secure-service
          uri: lb://secure-service
          predicates:
            - Path=/api/**
          filters:
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 10
                redis-rate-limiter.burstCapacity: 20
            - name: CircuitBreaker
              args:
                name: serviceCircuitBreaker
```

### 🔐 2.5 Real-Life Scenario
> A microservices-based eCommerce system needs to expose a public API. To protect customer data, it uses OAuth2, HTTPS, and API Gateway with rate limiting and JWT token validation.

---

## 3. Performance Fundamentals

### ⚙️ 3.1 Performance Metrics
| Metric              | Description                      |
|---------------------|----------------------------------|
| Latency             | Time taken to process a request  |
| Throughput          | Requests processed per second    |
| Load                | Current traffic level            |
| Resource Utilization | CPU, Memory, Disk usage          |

### ⚡ 3.2 Performance Optimization Examples

#### Caching Implementation
```java
@Service
@CacheConfig(cacheNames = {"products"})
public class ProductService {
    @Cacheable(key = "#id")
    public Product getProduct(Long id) {
        // Expensive database operation
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException());
    }
}
```

#### Async Processing
```java
@Service
public class OrderService {
    @Async
    public CompletableFuture<Order> processOrder(Order order) {
        return CompletableFuture.supplyAsync(() -> {
            // Process order asynchronously
            return processOrderDetails(order);
        });
    }
}
```

### 3.3 JVM and DB Tuning Tips
- Use proper Garbage Collection algorithm (e.g., G1GC or ZGC) suited for application workload.
- Set maximum database connections based on available CPU cores and memory to avoid resource exhaustion.
- Enable query caching if supported by the database to reduce repeated query execution time.

---

### Common Caching Strategies
| Strategy     | Description                          | Tools           |
|--------------|------------------------------------|-----------------|
| Cache-Aside  | App loads data into cache on miss  | Redis, Memcached |
| Write-Through| Writes to cache and DB simultaneously | Hazelcast       |
| Write-Behind | Writes to cache, DB updated later  | Apache Ignite    |

---

#### Application-Level Rate Limiting with Bucket4j (Java)
```java
@RestController
public class RateLimitedController {

    private final Bucket bucket;

    public RateLimitedController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @GetMapping("/limited-resource")
    public ResponseEntity<String> getLimitedResource() {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("Request successful");
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }
    }
}
```

---

### 🎯 3.6 Performance Benchmarking
| Test Type      | Tool          | Metrics                      |
|----------------|---------------|------------------------------|
| Load Testing   | Apache JMeter | RPS, Response Time           |
| Stress Testing | Gatling       | Throughput, Error Rate       |
| Profiling     | JProfiler     | Memory Usage, CPU Time       |
| Monitoring    | Prometheus    | Resource Utilization         |

### 🌟 Real-World Scenarios

#### Scenario 1: E-commerce Platform
```plaintext
Challenge: High traffic during flash sales
Solution:
1. Implement rate limiting at API Gateway
2. Use Redis for session management
3. Deploy CDN for static content
4. Implement circuit breakers for critical services
5. Use message queues for order processing
```

#### Scenario 2: Financial Application
```plaintext
Challenge: Secure transaction processing
Solution:
1. Implement end-to-end encryption
2. Use mutual TLS for service communication
3. Implement audit logging
4. Deploy WAF for API protection
5. Regular security scanning and pentesting
```

---

## 4. Security vs Performance Trade-Offs

| Trade-Off                | Impact                               |
|--------------------------|------------------------------------|
| Encryption vs Speed      | SSL/TLS impacts latency             |
| Logging vs Throughput    | Excessive logging affects performance |
| Validation vs Complexity | Input validation may slow down request handling |
| API Gateway Auth vs Latency | Adding JWT/OAuth checks on every request increases response time |

---

## 5. Tools & Practices

| Tool              | Use                           |
|-------------------|-------------------------------|
| Spring Security   | AuthN/AuthZ                  |
| OWASP ZAP         | Security scanning            |
| JMeter/Locust     | Load testing                 |
| Prometheus + Grafana | Performance monitoring      |
| SonarQube         | Static code analysis         |
| Trivy             | Container vulnerability scanner |
| Keycloak          | Identity and access management |
| Chaos Monkey      | Resilience testing           |

---

## 6. Best Practices Summary
- Secure APIs with HTTPS, Auth, Rate Limiting
- Isolate sensitive services and data
- Use profiling tools to find performance hotspots
- Monitor continuously
- Tune JVM and DB settings for performance
- Plan for scalability & failover
- Perform regular threat modeling and risk assessment
- Use synthetic monitoring for proactive performance alerts

---

## 7. References
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Spring Security Docs](https://docs.spring.io/spring-security/)
- [Spring Boot Performance Guide](https://www.baeldung.com/spring-boot-performance)
- [Google SRE Book](https://sre.google/books/)