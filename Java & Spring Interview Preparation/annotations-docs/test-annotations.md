# 🧪 Test Annotations in Java – JUnit, Mockito, Spring

This document provides an in-depth overview of annotations used in Java testing frameworks including **JUnit 4**, **JUnit 5**, **Mockito**, and **Spring Test**. These annotations help structure unit, integration, and mock-based testing effectively.

---

## 📘 1. JUnit 5 (Jupiter) Annotations

| Annotation         | Description                                        |
|--------------------|----------------------------------------------------|
| `@Test`            | Marks a test method                                |
| `@BeforeEach`      | Runs before each test                              |
| `@AfterEach`       | Runs after each test                               |
| `@BeforeAll`       | Runs once before all tests (must be static)        |
| `@AfterAll`        | Runs once after all tests (must be static)         |
| `@DisplayName`     | Custom name for test reporting                     |
| `@Disabled`        | Skips the test                                     |
| `@Nested`          | Allows nested test classes                         |
| `@Tag`             | Categorize tests (e.g., `@Tag("integration")`)     |
| `@RepeatedTest`    | Repeats a test multiple times                      |
| `@ParameterizedTest` | Supports dynamic test data                      |

### ✅ Example:
```java
@Test
@DisplayName("Should validate email format")
void validateEmail() {
    assertTrue(EmailValidator.isValid("test@example.com"));
}
```

---

## 🔄 2. Parameterized Tests

| Annotation         | Description                                       |
|--------------------|---------------------------------------------------|
| `@ParameterizedTest` | Enables running test with multiple inputs      |
| `@ValueSource`     | Provides literal values                           |
| `@CsvSource`       | Provides comma-separated values                   |
| `@MethodSource`    | Uses a static method to supply arguments          |

### ✅ Example:
```java
@ParameterizedTest
@ValueSource(strings = {"", " ", "\t"})
void shouldDetectEmptyStrings(String input) {
    assertTrue(input.trim().isEmpty());
}
```

---

## ⚙️ 3. Assertions & Assumptions

### JUnit 5 Assertions:
```java
assertEquals(expected, actual);
assertThrows(Exception.class, () -> someMethod());
assertTimeout(Duration.ofMillis(500), () -> slowMethod());
```

### Assumptions:
```java
assumeTrue(System.getProperty("env").equals("dev"));
```

---

## 🧪 4. Mockito Annotations

| Annotation      | Description                              |
|-----------------|------------------------------------------|
| `@Mock`         | Creates a mock object                    |
| `@Spy`          | Wraps a real object but tracks usage     |
| `@InjectMocks`  | Injects mock dependencies                |
| `@Captor`       | Captures arguments passed to mocks       |
| `@ExtendWith(MockitoExtension.class)` | Enables annotations in JUnit 5 |

### ✅ Example:
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void shouldSaveUser() {
        when(userRepository.save(any())).thenReturn(new User("test"));
        assertNotNull(userService.save("test"));
    }
}
```

---

## 🌱 5. Spring Test Annotations

| Annotation             | Description                                                  |
|------------------------|--------------------------------------------------------------|
| `@SpringBootTest`      | Loads full Spring context for integration testing            |
| `@WebMvcTest`          | Slices MVC layer, mocks other layers                         |
| `@DataJpaTest`         | Configures H2 + JPA for repository tests                     |
| `@MockBean`            | Adds Mockito mock to Spring context                          |
| `@Autowired`           | Inject test dependencies                                     |
| `@ContextConfiguration`| Loads application context with specific config               |
| `@DirtiesContext`      | Marks context dirty so it will be recreated for next test    |
| `@TestConfiguration`   | Declares additional beans for test use only                  |

### ✅ Example:
```java
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    PaymentGateway paymentGateway;

    @Test
    void shouldPlaceOrder() {
        when(paymentGateway.charge(any())).thenReturn(true);
        assertTrue(orderService.placeOrder("item-123"));
    }
}
```

---

## 🔄 6. Transactional Tests

| Annotation         | Description                                            |
|--------------------|--------------------------------------------------------|
| `@Transactional`   | Rolls back transaction after test                      |
| `@Rollback(false)` | Prevent rollback to verify DB changes                  |

---

## 🧠 7. Test Configuration and Extensions

| Annotation             | Description                                 |
|------------------------|---------------------------------------------|
| `@TestInstance`        | Controls test class lifecycle               |
| `@TestMethodOrder`     | Specifies test method execution order       |
| `@TestPropertySource`  | Overrides application properties             |
| `@Sql`                 | Executes SQL before or after test methods   |
| `@EnableAutoConfiguration(exclude = ...)` | Disable auto-config for testing |

---

## 📊 8. Advanced Testing Annotations

### Spring Test Slices
```java
@DataMongoTest // MongoDB tests
@JsonTest // JSON serialization tests
@JdbcTest // JDBC tests
@JooqTest // JOOQ tests
@RestClientTest // REST client tests
@WebFluxTest // WebFlux tests
```

### Conditional Test Execution
```java
@EnabledOnOs(OS.LINUX)
@EnabledOnJre(JRE.JAVA_11)
@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging")
```

### Performance Testing
```java
@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
void performanceTest() {
    // test must complete within 500ms
}

@RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
void loadTest() {
    // repeated 10 times
}
```

### Custom Test Ordering
```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTest {
    @Test
    @Order(1)
    void first() {}
    
    @Test
    @Order(2)
    void second() {}
}
```

### Resource Management
```java
@TempDir
Path tempDir;

@RegisterExtension
static WireMockExtension wiremock = WireMockExtension.newInstance()
    .options(wireMockConfig().dynamicPort())
    .build();
```

## 🔍 9. Advanced Testing Patterns

### Test Fixtures
```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FixtureTest {
    @TestTemplate
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testWithCsvFile(String input, String expected) {
        assertEquals(expected, process(input));
    }
}
```

### Custom Test Extensions
```java
public class TimingExtension implements BeforeTestExecutionCallback, 
                                      AfterTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        context.getStore(NAMESPACE).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        long startTime = context.getStore(NAMESPACE).get(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Test %s took %d ms.", 
            context.getRequiredTestMethod().getName(), duration));
    }
}
```

---

## 💼 Interview Questions & Answers

### ❓ What is the difference between `@Mock` and `@MockBean`?
- `@Mock`: Used in standalone Mockito tests  
- `@MockBean`: Registers mock inside Spring context

### ❓ When do you use `@WebMvcTest` vs `@SpringBootTest`?
- `@WebMvcTest`: Test controllers only (lightweight)  
- `@SpringBootTest`: Loads entire application context (heavier)

### ❓ How does `@Transactional` work in tests?
**✅** Automatically rolls back changes after each test method.

### ❓ What does `@DataJpaTest` do?
**✅** Configures Spring Data JPA, H2, and scans repositories only.

### ❓ How do you organize parameterized tests in JUnit 5?
**✅** Using `@ParameterizedTest` + `@ValueSource`, `@CsvSource`, or `@MethodSource`

### ❓ What is the purpose of `@ExtendWith(MockitoExtension.class)`?
**✅** Registers the Mockito extension for annotation-based mocking in JUnit 5.

### ❓ How do you handle dynamic test generation?
**✅ A:**
```java
@TestFactory
Stream<DynamicTest> dynamicTests() {
    return Stream.of("A", "B", "C")
        .map(str -> dynamicTest(
            "test" + str,
            () -> assertTrue(str.length() > 0)
        ));
}
```

### ❓ How do you implement test fixtures with Spring?
**✅ A:**
```java
@SqlGroup({
    @Sql("/create-user-before.sql"),
    @Sql(scripts = "/delete-user-after.sql", 
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@Test
void userTest() {
    // test with predefined database state
}
```

### ❓ How do you test async code?
**✅ A:**
```java
@Test
void asyncTest() {
    CompletableFuture<String> future = asyncOperation();
    
    assertTimeout(Duration.ofSeconds(5), () -> {
        String result = future.get();
        assertEquals("expected", result);
    });
}
```

### ❓ How do you implement custom test annotations?
**✅ A:**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("integration")
@DisplayName("Integration test")
public @interface IntegrationTest {
}

// Usage
@IntegrationTest
void shouldIntegrate() {
    // test code
}
```

---

## 🧩 Best Practices

- Use `@BeforeEach` for consistent test setup  
- Use `@Nested` to logically group test scenarios  
- Avoid hardcoded IDs or DB state — use H2 + rollback  
- Favor JUnit 5 with Mockito and Spring Boot starter test  
- Use `@TestConfiguration` for mocking third-party beans  

---

## ✅ Summary

This document covers testing annotations from JUnit 5, Mockito, and Spring Boot. Mastery of these enables clean, reliable unit and integration testing in enterprise Java applications.

---

## 📝 References

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)  
- [Mockito Documentation](https://site.mockito.org/)  
- [Spring Boot Test Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
