## 🧪 Testing & Quality [NEW]

### Unit Testing

**Q: What is unit testing and why is it important?**  
A: Unit testing involves testing individual components or functions of a software application in isolation to ensure they work as intended. It helps catch bugs early, facilitates refactoring, and improves code quality and maintainability. For example, testing a method that calculates the total price in a shopping cart ensures it handles discounts and taxes correctly before integrating with other components.

**Q: Can you explain some key features of JUnit 5?**  
A: JUnit 5 is the latest version of the popular Java testing framework. It supports modular architecture with the JUnit Platform, Jupiter API, and Vintage engine for backward compatibility. Key features include improved annotations, dynamic tests, better assertions, and extensibility. For example, `@Test`, `@BeforeEach`, and `@AfterEach` help manage test lifecycle events clearly.

**Example:**  
```java
import org.junit.jupiter.api.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        Assertions.assertEquals(5, calculator.add(2, 3));
    }
}
```

**Follow-up Q: How does JUnit 5 improve test lifecycle management compared to earlier versions?**  
A: JUnit 5 introduces more granular lifecycle annotations such as `@BeforeAll`, `@BeforeEach`, `@AfterEach`, and `@AfterAll` with support for static and instance methods. This allows better control over setup and teardown processes, supporting both per-class and per-method initialization, improving test isolation and performance.

---

**Q: What are parameterized tests and when would you use them?**  
A: Parameterized tests allow running the same test logic multiple times with different inputs, reducing code duplication and increasing coverage. They are useful when testing functions that should behave correctly over a range of inputs.

**Example:**  
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "level" })
    void testIsPalindrome(String candidate) {
        Assertions.assertTrue(StringUtils.isPalindrome(candidate));
    }
}
```

**Follow-up Q: What are some other sources you can use for parameterized tests in JUnit 5?**  
A: JUnit 5 supports various sources like `@CsvSource`, `@CsvFileSource`, `@MethodSource`, and `@EnumSource`, allowing complex input combinations and external data-driven testing.

---

**Q: What are test doubles and how do Mock, Stub, and Spy differ?**  
A: Test doubles are objects that replace real components during testing to isolate the unit under test.  
- **Mock:** Simulates behavior and verifies interactions, often created with frameworks like Mockito.  
- **Stub:** Provides predefined responses without behavior verification.  
- **Spy:** Wraps a real object, allowing partial mocking and real method calls.

**Example using Mockito:**  
```java
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void testUserCreation() {
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.save(any(User.class))).thenReturn(new User(1, "John"));
        
        UserService userService = new UserService(mockRepo);
        User user = userService.createUser("John");
        
        verify(mockRepo).save(any(User.class));
        Assertions.assertEquals("John", user.getName());
    }
}
```

**Follow-up Q: When would you prefer using a Spy over a Mock?**  
A: Use a Spy when you want to test some real behavior of an object but override or verify specific methods. It’s helpful when partial mocking is needed without rewriting the entire object's behavior.

---

**Q: How does Mockito enhance unit testing?**  
A: Mockito simplifies creating mocks and stubs, enabling behavior verification and interaction testing. It supports annotations like `@Mock` and `@InjectMocks`, reducing boilerplate and improving readability.

**Example:**  
```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    PaymentGateway paymentGateway;

    @InjectMocks
    OrderService orderService;

    @Test
    void testProcessOrder() {
        when(paymentGateway.process(anyDouble())).thenReturn(true);

        boolean result = orderService.processOrder(100.0);

        Assertions.assertTrue(result);
        verify(paymentGateway).process(100.0);
    }
}
```

**Follow-up Q: Can Mockito be used for mocking static methods?**  
A: Traditionally, Mockito did not support static method mocking, but since Mockito 3.4.0, it supports static mocking via the `mockStatic()` method, allowing legacy or utility static methods to be mocked in tests.

---

**Q: What tools can be used to measure test coverage and why is it important?**  
A: Tools like JaCoCo, Cobertura, and Emma measure how much of your code is executed during tests, identifying untested parts. High coverage increases confidence in code correctness and helps detect dead code.

**Scenario:** A team integrates JaCoCo in their CI pipeline to ensure coverage stays above 80%, preventing untested code from being merged.

**Follow-up Q: Does 100% test coverage guarantee bug-free code?**  
A: No, high coverage reduces risk but doesn’t guarantee correctness. Tests must also be meaningful, covering edge cases and behavior, not just lines of code.

---

### Integration Testing

**Q: What is integration testing and how does it differ from unit testing?**  
A: Integration testing verifies that different components or systems work together correctly. Unlike unit tests that isolate a single unit, integration tests validate interactions, such as database access, REST API calls, or messaging.

**Scenario:** Testing a service method that calls a repository and an external API to confirm that the entire workflow functions as expected.

---

**Q: How does Spring Boot Test facilitate integration testing?**  
A: Spring Boot Test provides annotations like `@SpringBootTest` to bootstrap the entire application context, allowing tests to run with full dependency injection, configuration, and embedded servers.

**Example:**  
```java
@SpringBootTest
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUser() throws Exception {
        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John"));
    }
}
```

**Follow-up Q: How can you speed up integration tests that use Spring Boot Test?**  
A: Use slicing annotations like `@WebMvcTest` to load only web layer beans, mock dependencies, or use in-memory databases to reduce startup time.

---

**Q: What are TestContainers and how do they improve integration testing?**  
A: TestContainers is a Java library that provides lightweight, throwaway instances of common databases, message brokers, or other services using Docker containers for integration tests. This ensures tests run against realistic environments without manual setup.

**Example:**  
```java
@Container
static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13");

@BeforeAll
static void setup() {
    System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
}
```

**Follow-up Q: What are the benefits of TestContainers over embedded databases?**  
A: TestContainers provide closer-to-production environments, support multiple database versions, and help catch environment-specific bugs that embedded databases might miss.

---

**Q: How do you perform database testing effectively?**  
A: Use isolated test databases or in-memory databases, apply transactions that rollback after tests, seed test data, and verify data persistence and retrieval. Tools like Flyway or Liquibase can help maintain schema consistency.

**Example:**  
```java
@Transactional
@Test
void testSaveUser() {
    User user = new User("Alice");
    userRepository.save(user);

    User found = userRepository.findByName("Alice");
    Assertions.assertNotNull(found);
}
```

**Follow-up Q: Why is transaction rollback important in database tests?**  
A: It ensures database state remains clean between tests, avoiding side effects and making tests independent and repeatable.

---

**Q: How can REST API testing be automated?**  
A: Use tools and libraries like Spring's `MockMvc`, RestAssured, or Postman collections to automate HTTP request execution, response validation, and integration with CI pipelines.

**Example with RestAssured:**  
```java
import static io.restassured.RestAssured.*;

@Test
void testGetUserApi() {
    given().when().get("/users/1")
           .then().statusCode(200)
           .body("name", equalTo("John"));
}
```

**Follow-up Q: How do you handle authentication in REST API tests?**  
A: Include authentication headers or tokens in requests, mock authentication providers, or use test-specific credentials to simulate authorized access.

---

**Q: What is WebTestClient and when would you use it?**  
A: WebTestClient is a reactive web client for testing Spring WebFlux applications, supporting both server-side and client-side testing with reactive streams.

**Example:**  
```java
@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetUser() {
        webTestClient.get().uri("/users/1")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .jsonPath("$.name").isEqualTo("John");
    }
}
```

**Follow-up Q: How does WebTestClient differ from MockMvc?**  
A: WebTestClient supports reactive streams and non-blocking I/O, making it suitable for reactive applications, whereas MockMvc is designed for traditional servlet-based MVC applications.

---

### Performance Testing

**Q: What is JMH and why is it used for performance testing?**  
A: JMH (Java Microbenchmark Harness) is a Java framework for writing reliable and accurate microbenchmarks to measure method-level performance, accounting for JVM optimizations like JIT and warm-up.

**Example:**  
```java
@Benchmark
public int measureSum() {
    return IntStream.range(0, 100).sum();
}
```

**Follow-up Q: Why shouldn't you use regular unit tests for performance benchmarking?**  
A: Unit tests lack precise timing controls, JVM warm-up handling, and statistical analysis, leading to unreliable and inconsistent performance results.

---

**Q: What are common load testing tools and their use cases?**  
A: Tools like Apache JMeter, Gatling, and Locust simulate concurrent users to test system behavior under load, helping identify bottlenecks and scalability limits.

**Scenario:** A web service is tested with JMeter simulating 1000 concurrent users to ensure response times stay within SLA.

**Follow-up Q: How do you interpret load testing results?**  
A: Analyze throughput, latency, error rates, and resource utilization to identify performance degradation points and capacity limits.

---

**Q: How do profiling tools help in performance testing?**  
A: Profilers like VisualVM, YourKit, or Java Flight Recorder monitor CPU, memory, and thread usage, helping identify hotspots, memory leaks, and inefficient code paths during testing.

**Follow-up Q: When should you profile your application?**  
A: Profiling is best done during development and load testing phases to optimize performance and resource consumption before production deployment.

---

**Q: What strategies exist for detecting memory leaks in Java applications?**  
A: Use heap dumps, profilers, and monitoring tools to analyze object retention and garbage collection behavior. Look for growing memory usage patterns and uncollected objects.

**Follow-up Q: Can unit tests detect memory leaks?**  
A: Generally no, memory leaks are better detected via integration or load testing combined with profiling tools, as unit tests are too short and isolated to reveal leaks.

---

### Advanced Performance Testing

**Q: How would you design and implement a comprehensive performance testing strategy?**

**A:** A comprehensive strategy involves multiple layers:

1. **Load Testing:**
```java
@Test
void loadTest() {
    IntStream.range(0, 1000)
        .parallel()
        .forEach(i -> {
            RestAssured.given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(200);
        });
}
```

2. **Stress Testing with JMeter:**
```xml
<jmeterTestPlan version="1.2">
    <hashTree>
        <ThreadGroup guiclass="ThreadGroupGui">
            <stringProp name="ThreadGroup.num_threads">1000</stringProp>
            <stringProp name="ThreadGroup.ramp_time">60</stringProp>
            <HTTPSamplerProxy>
                <stringProp name="HTTPSampler.path">/api/users</stringProp>
            </HTTPSamplerProxy>
        </ThreadGroup>
    </hashTree>
</jmeterTestPlan>
```

**Follow-up Q: How do you handle performance degradation in production?**

**A:** Implement:
1. Circuit breakers
2. Caching strategies
3. Resource pooling

**Example:**
```java
@Configuration
public class ResilienceConfig {
    @Bean
    public CircuitBreaker userServiceCircuitBreaker() {
        return CircuitBreaker.ofDefaults("userService")
            .slidingWindowSize(100)
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .build();
    }
}
```

---

### Memory Management Testing

**Q: How would you identify and fix memory leaks in a Spring Boot application?**

**A:** Use multiple approaches:

1. **Heap Dump Analysis:**
```java
public class MemoryLeakDetector {
    public static void analyzeHeapDump() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            HotSpotDiagnosticMXBean mxBean = ManagementFactory
                .newPlatformMXBeanProxy(server, 
                    "com.sun.management:type=HotSpotDiagnostic",
                    HotSpotDiagnosticMXBean.class);
            mxBean.dumpHeap("heap_dump.hprof", true);
        } catch (Exception e) {
            log.error("Failed to create heap dump", e);
        }
    }
}
```

2. **Resource Monitoring:**
```java
@Component
public class MemoryMonitor {
    @Scheduled(fixedRate = 60000)
    public void checkMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        if (usedMemory > threshold) {
            alertMemoryIssue();
        }
    }
}
```

**Follow-up Q: What tools would you use for continuous memory monitoring?**

**A:** Implement:
1. JVM metrics with Micrometer
2. Grafana dashboards
3. Custom memory alerts

---

### Integration Testing Best Practices

**Q: How would you test microservices interactions?**

**A:** Implement contract testing:

```java
@SpringBootTest
@AutoConfigureMessageVerifier
class OrderServiceContractTest {
    
    @Autowired
    private OrderService orderService;
    
    @Test
    public void validateOrderCreationContract() {
        // given
        stubFor(post("/payment-service/process")
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"status\":\"SUCCESS\"}")));
                
        // when
        OrderResponse response = orderService.createOrder(new Order("123", 100.0));
        
        // then
        verify(postRequestedFor(urlEqualTo("/payment-service/process")));
        assertThat(response.getStatus()).isEqualTo("CREATED");
    }
}
```

**Follow-up Q: How do you handle flaky tests in integration testing?**

**A:** Implement retry mechanisms and proper test isolation:

```java
@Test
@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
void testWithRetry() {
    // Test logic that might be flaky
    assertThat(serviceCall()).isSuccessful();
}
```

---

### Performance Monitoring in Production

**Q: How would you implement real-time performance monitoring?**

**A:** Use Spring Boot Actuator with custom metrics:

```java
@RestController
public class UserController {
    private final MeterRegistry meterRegistry;
    
    @GetMapping("/users")
    public List<User> getUsers() {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            return userService.getAllUsers();
        } finally {
            sample.stop(meterRegistry.timer("api.users.get.time"));
        }
    }
}
```

**Real-world scenario:**
```java
@Configuration
public class MonitoringConfig {
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
    
    @Bean
    public CustomHealthIndicator customHealthIndicator() {
        return new CustomHealthIndicator();
    }
}
```

---

This comprehensive Q&A format covers essential testing and quality topics, providing clear explanations, practical scenarios, code examples, and deeper insights through follow-up questions and answers.
