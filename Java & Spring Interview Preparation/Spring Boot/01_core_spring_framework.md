# 📘 Core Spring Framework Deep Dive

## Overview
This document provides a comprehensive, technical deep dive into the advanced internals of the Spring Core framework. It covers dependency injection, AOP, context lifecycle, event handling, resource management, advanced configuration, and best practices for building robust, maintainable, and scalable Spring applications.

---

## Table of Contents

1. [Spring Core (Advanced IoC & DI)](#spring-core-advanced-ioc--di)
2. [Event Processing](#event-processing)
3. [AOP Programming](#aop-programming)
4. [Advanced Bean Lifecycle](#advanced-bean-lifecycle)
5. [Advanced Context Management](#advanced-context-management)
6. [Resource Management & Externalization](#resource-management--externalization)
7. [Advanced Configuration & Profiles](#advanced-configuration--profiles)
8. [Testing & Mocking in Spring Core](#testing--mocking-in-spring-core)
9. [Error Handling & Diagnostics](#error-handling--diagnostics)
10. [Performance Tuning & Optimization](#performance-tuning--optimization)
11. [Best Practices & Patterns](#best-practices--patterns)
12. [References](#references)

---

## 1. Spring Core (Advanced IoC & DI)

Spring's core is built around the Inversion of Control (IoC) principle, implemented via Dependency Injection (DI). Advanced use involves custom extension points, scopes, resolution strategies, and understanding the bean lifecycle in depth.

### Custom Bean Post Processors
BeanPostProcessor allows you to intercept bean creation and apply custom logic before and after initialization. Common use cases include proxy wrapping, auditing, and annotation processing.

| Interface                | Description                                               |
|--------------------------|-----------------------------------------------------------|
| `BeanPostProcessor`      | Intercept beans before/after initialization               |
| `InstantiationAwareBeanPostProcessor` | Intercept before instantiation and property population |

**Example: Logging Bean Initialization**
```java
@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Initializing bean: " + beanName);
        return bean;
    }
}
```

### Bean Factory Post Processors
BeanFactoryPostProcessor operates on bean definitions before beans are instantiated. Useful for modifying property values or registering additional beans.

**Example: Customizing Property Values**
```java
@Component
public class CustomBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        // Modify bean definitions here
    }
}
```

### Custom Scopes
Spring supports singleton and prototype out-of-the-box. You can define custom scopes (e.g., per thread, per session).

| Scope Name   | Description                           |
|--------------|---------------------------------------|
| singleton    | One instance per Spring container     |
| prototype    | New instance every injection          |
| request      | One instance per HTTP request         |
| session      | One per HTTP session                  |
| application  | One per ServletContext                |
| websocket    | One per WebSocket                     |
| custom       | User-defined (e.g., thread)           |

**Example: Custom Thread Scope**
```java
@Component
@Scope(scopeName = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ThreadScopedBean { }
```
You must also register the custom scope:
```java
@Configuration
public class ThreadScopeConfig implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.registerScope("thread", new SimpleThreadScope());
    }
}
```

### Method Injection
Singleton beans cannot directly depend on prototype beans unless you use method injection. Spring injects a method that returns a fresh prototype every time.

**Example: Lookup Method Injection**
```java
public abstract class SingletonBean {
    @Lookup
    public abstract PrototypeBean getPrototypeBean();
}
```

### Circular Dependencies Handling
Spring can resolve circular dependencies for singleton beans injected via setter or field injection, but not with constructor injection or for prototype beans.

**Edge Case:**
- Constructor-based circular dependencies will throw `BeanCurrentlyInCreationException`.
- Prototype beans cannot be resolved circularly.

### Dependency Resolution
Ambiguity can be resolved using:
- `@Primary` to mark a default bean
- `@Qualifier` to specify the bean by name

**Example:**
```java
@Bean
@Primary
public Service myServiceImpl1() { ... }

@Bean
public Service myServiceImpl2() { ... }

@Autowired
public void setService(@Qualifier("myServiceImpl2") Service service) { ... }
```

### Lazy Initialization
`@Lazy` delays bean creation until first use, reducing startup time.
```java
@Component
@Lazy
public class HeavyBean { ... }
```

### FactoryBeans
`FactoryBean<T>` provides custom instantiation logic. The bean returned is not the FactoryBean itself, but the object it creates.

**Example:**
```java
@Component
public class MyFactoryBean implements FactoryBean<MyType> {
    @Override
    public MyType getObject() { return new MyType(); }
    @Override
    public Class<?> getObjectType() { return MyType.class; }
}
```

### Environment Abstraction
Access environment properties, profiles, and variables programmatically.
```java
@Autowired
private Environment env;

public String getProperty() {
    return env.getProperty("my.custom.property");
}
```

---

## 2. Event Processing

Spring's event model supports decoupled communication between components.

### Application Events
Events are objects extending `ApplicationEvent` or any POJO. You publish events using `ApplicationEventPublisher`. Listeners can be annotated with `@EventListener` or implement `ApplicationListener<T>`.

**Example: Publishing and Listening**
```java
public class UserCreatedEvent extends ApplicationEvent {
    private final User user;
    public UserCreatedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
    public User getUser() { return user; }
}

@Component
public class UserCreatedListener {
    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        // Handle event
    }
}
```
**Publishing:**
```java
@Autowired
private ApplicationEventPublisher publisher;

public void createUser(User user) {
    publisher.publishEvent(new UserCreatedEvent(this, user));
}
```

### Custom Events
You can define domain-specific events by extending `ApplicationEvent` or using any POJO with `@EventListener`.

### Async Event Processing
Annotate listeners with `@Async` to process events asynchronously. Enable async support with `@EnableAsync`.
```java
@Component
public class AsyncListener {
    @Async
    @EventListener
    public void handleEvent(MyEvent event) {
        // Non-blocking processing
    }
}
```
**Edge Case:** Exceptions in async listeners are not propagated to the publisher. Use `AsyncUncaughtExceptionHandler` for error handling.

### Transaction-bound Events
Spring supports `@TransactionalEventListener` to fire events after transaction commit, rollback, or before commit.
```java
@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
public void onUserCreated(UserCreatedEvent event) { ... }
```

### Event Threading Models
- Synchronous: Default, runs in publisher's thread.
- Asynchronous: Requires `@Async` and a task executor.

### Error Handling in Async Events
Configure `AsyncUncaughtExceptionHandler` in your `@Configuration` class.
```java
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> { /* handle error */ };
    }
}
```

### Event Order Guarantees
Use `@Order` or implement `Ordered` to control listener execution order.
```java
@Component
public class OrderedListener {
    @EventListener
    @Order(1)
    public void onEvent(MyEvent event) { ... }
}
```

### Summary Table: Event Listener Types
| Listener Type                      | Registration Method     | Threading      |
|------------------------------------|------------------------|---------------|
| ApplicationListener<T>             | Interface              | Synchronous   |
| `@EventListener`                   | Annotation             | Synchronous   |
| `@Async` + `@EventListener`        | Annotation             | Asynchronous  |
| `@TransactionalEventListener`      | Annotation             | Transactional |

---

## 3. AOP Programming

Aspect-Oriented Programming (AOP) allows modularization of cross-cutting concerns such as logging, security, and transactions.

### Custom Annotations
Define custom annotations to mark join points for advice.
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable { }
```

### Pointcuts & Advice
Pointcuts define where advice is applied. Advice can be `@Before`, `@After`, `@Around`, etc.

**Example: Logging Aspect**
```java
@Aspect
@Component
public class LoggingAspect {
    @Around("@annotation(Loggable)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("After: " + joinPoint.getSignature());
        return result;
    }
}
```

### Method Interception
For lower-level interception, use `MethodInterceptor` from Spring AOP.
```java
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // Pre-processing
        Object result = invocation.proceed();
        // Post-processing
        return result;
    }
}
```

### Proxying Mechanisms
Spring uses JDK dynamic proxies for interfaces and CGLIB proxies for classes.
| Proxy Type   | When Used                | Limitation                    |
|--------------|-------------------------|-------------------------------|
| JDK Dynamic  | If bean implements interface | Only public interface methods |
| CGLIB        | Otherwise (concrete class)   | Final methods not proxied     |

### AOP Limitations
- Final methods and classes cannot be proxied with CGLIB.
- Self-invocation does not trigger advice.

**Edge Case:**
If a method within a bean calls another method in the same bean, the advice will not be applied unless the call goes through the proxy.

### Aspect Ordering
Use `@Order` or `Ordered` interface to control precedence of aspects.
```java
@Aspect
@Order(1)
public class FirstAspect { ... }
```

### Summary Table: Advice Types
| Advice Type    | Annotation       | Execution Time         |
|----------------|------------------|-----------------------|
| Before         | `@Before`        | Before method         |
| After          | `@After`         | After method          |
| AfterReturning | `@AfterReturning`| On normal return      |
| AfterThrowing  | `@AfterThrowing` | On exception thrown   |
| Around         | `@Around`        | Surrounds method call |

---

## 4. Advanced Bean Lifecycle

Spring beans go through a well-defined lifecycle, with extension points for custom logic.

### Bean Creation Phases
1. **Instantiation:** Bean instance is created.
2. **Populate Properties:** Dependencies are injected.
3. **BeanNameAware/BeanFactoryAware:** Aware interfaces are called.
4. **BeanPostProcessor (before init):** Pre-initialization logic.
5. **InitializingBean / @PostConstruct:** Initialization callbacks.
6. **BeanPostProcessor (after init):** Post-initialization logic.
7. **Usage**
8. **Destruction:** `DisposableBean`/`@PreDestroy`.

**Lifecycle Table**
| Stage                | Extension Point                   |
|----------------------|-----------------------------------|
| Instantiation        | Constructor, FactoryBean          |
| Dependency Injection | `@Autowired`, setter, field       |
| Pre-initialization   | `BeanPostProcessor`               |
| Initialization       | `InitializingBean`, `@PostConstruct` |
| Post-initialization  | `BeanPostProcessor`               |
| Destruction          | `DisposableBean`, `@PreDestroy`   |

### Lifecycle Callbacks
Implement `InitializingBean` and `DisposableBean` for custom logic.
```java
@Component
public class LifecycleBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        // Initialization logic
    }
    @Override
    public void destroy() {
        // Cleanup logic
    }
}
```

### @PostConstruct and @PreDestroy
Preferred for lifecycle methods in modern Spring.
```java
@Component
public class AnnotatedLifecycleBean {
    @PostConstruct
    public void init() { /* ... */ }
    @PreDestroy
    public void cleanup() { /* ... */ }
}
```

### SmartLifecycle Interface
For advanced startup/shutdown control (e.g., for message listeners).
```java
@Component
public class MySmartLifecycle implements SmartLifecycle {
    private boolean running = false;
    @Override
    public void start() { running = true; }
    @Override
    public void stop() { running = false; }
    @Override
    public boolean isRunning() { return running; }
}
```

### Custom Bean Definition
Register beans programmatically using `BeanDefinitionRegistry`.
```java
@Configuration
public class DynamicBeanConfig implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        RootBeanDefinition def = new RootBeanDefinition(MyBean.class);
        registry.registerBeanDefinition("myBean", def);
    }
}
```

### Bean Factory Post-Processing
See Section 1 for `BeanFactoryPostProcessor`.

### Bean Destruction Callbacks
Always release resources (connections, threads) in destroy callbacks to avoid leaks.

**Edge Case:** Beans with prototype scope are not managed for destruction by the container.

---

## 5. Advanced Context Management

Spring's `ApplicationContext` manages the entire bean lifecycle and environment. Advanced usage includes context hierarchies, propagation, and programmatic control.

### Multiple Context Hierarchies
Parent-child context hierarchies enable modularization (e.g., web and business contexts).
```java
AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext(ParentConfig.class);
AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
child.setParent(parent);
child.register(ChildConfig.class);
child.refresh();
```
**Real-world:** In Spring MVC, `WebApplicationContext` (child) inherits from root context (parent).

### Context Propagation
Context does not automatically propagate across threads. Use `TaskDecorator` (Spring 5+) or context-aware executors.
```java
ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
executor.setTaskDecorator(new ContextCopyingTaskDecorator());
```
**Edge Case:** Without propagation, beans with request/session scope may not be available in async tasks.

### WebApplicationContext
Specialized context for web apps; supports request, session, and application scopes.
```java
@Autowired
private WebApplicationContext webCtx;
```

### Reactive Context
In reactive flows (WebFlux), context is propagated using `Context` from Project Reactor, not `ThreadLocal`.
```java
Mono.deferContextual(ctx -> { ... });
```

### Context Refresh & Shutdown
You can refresh or close contexts programmatically.
```java
ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
ctx.refresh();
ctx.close();
```

### ApplicationContextAware
Access the context from any bean by implementing `ApplicationContextAware`.
```java
@Component
public class ContextAwareBean implements ApplicationContextAware {
    private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }
}
```

### Summary Table: Context Types
| Context Type                | Usage Scenario             |
|-----------------------------|---------------------------|
| `ApplicationContext`        | Generic/root context      |
| `WebApplicationContext`     | Web apps (servlet-based)  |
| `ReactiveWebApplicationContext` | WebFlux/reactive      |

---

## 6. Resource Management & Externalization

Externalizing configuration and resource access is key for portable and maintainable applications.

### Resource Abstraction
Spring's `Resource` abstraction supports files, URLs, classpath resources, etc.
```java
@Autowired
private ResourceLoader resourceLoader;

public void loadResource() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:data.txt");
    try (InputStream is = resource.getInputStream()) {
        // Read from stream
    }
}
```
**Edge Case:** Resource existence is not guaranteed; always check with `resource.exists()`.

### Property Sources
Use `@PropertySource` to load `.properties` files, or YAML via Spring Boot.
```java
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig { }
```
**YAML Example:**  
`application.yml`
```yaml
my:
  property: value
```
Access via `@Value("${my.property}")` or `@ConfigurationProperties`.

### Profile-based Configuration
Define beans for specific environments using `@Profile`.
```java
@Bean
@Profile("prod")
public DataSource prodDataSource() { ... }
```
Activate profiles with `spring.profiles.active=prod`.

### Reloadable Resources
Use `ReloadableResourceBundleMessageSource` for hot-reloading messages/properties (usually only in dev).
```java
@Bean
public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
    ms.setBasename("classpath:messages");
    ms.setCacheSeconds(10);
    return ms;
}
```

### MessageSource (i18n)
Externalize text/messages for internationalization.
```java
@Autowired
private MessageSource messageSource;

public String getMessage(String code) {
    return messageSource.getMessage(code, null, Locale.ENGLISH);
}
```

### Summary Table: Resource Types
| Resource URI Prefix | Example                       | Description                  |
|---------------------|-------------------------------|------------------------------|
| classpath:          | classpath:app.properties      | Classpath resource           |
| file:               | file:/data/config.txt         | File system resource         |
| http:               | http://example.com/data.txt   | Remote resource              |

---

## 7. Advanced Configuration & Profiles

Spring provides powerful mechanisms for flexible, environment-aware configuration.

### Conditional Beans
Use `@Conditional` or `@Profile` to create beans only under certain conditions.
```java
@Bean
@ConditionalOnProperty(name = "feature.enabled", havingValue = "true")
public FeatureService featureService() { ... }
```
**Custom Condition:**
```java
public class MyCondition implements Condition {
    public boolean matches(ConditionContext ctx, AnnotatedTypeMetadata md) {
        return "true".equals(ctx.getEnvironment().getProperty("my.condition"));
    }
}
```
```java
@Bean
@Conditional(MyCondition.class)
public MyBean myBean() { ... }
```

### Profile-based Beans
```java
@Bean
@Profile("dev")
public DataSource devDataSource() { ... }
```
Activate via env variable or `spring.profiles.active`.

### Type-safe Configuration
Use `@ConfigurationProperties` for structured, type-safe configuration.
```java
@ConfigurationProperties(prefix = "app.datasource")
public class DataSourceProperties {
    private String url;
    private String username;
    // getters/setters
}
```
```java
@EnableConfigurationProperties(DataSourceProperties.class)
public class AppConfig { }
```

### Environment Abstraction
See Section 1 for `Environment` usage.

### FactoryBeans
See Section 1 for `FactoryBean<T>`.

### Summary Table: Conditional Annotations
| Annotation                | Description                          |
|---------------------------|--------------------------------------|
| `@ConditionalOnProperty`  | Based on property value              |
| `@ConditionalOnClass`     | Based on class presence              |
| `@Profile`                | Based on active profile              |
| `@Conditional`            | Custom logic                         |

---

## 8. Testing & Mocking in Spring Core

Spring provides comprehensive support for unit and integration testing with context management and bean mocking.

### Unit Testing
Use `@ContextConfiguration` to load specific config classes, or `@SpringBootTest` for full context.
```java
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class MyTest { ... }
```

### Context Caching
Spring caches contexts between tests to speed up execution. Context is reloaded if test config changes.

### Profile-specific Tests
Activate profiles in tests using `@ActiveProfiles`.
```java
@ActiveProfiles("test")
public class MyTest { ... }
```

### Mocking Beans
Replace real beans with mocks using `@MockBean` (Spring Boot) or `@Primary` in test configs.
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceTest {
    @MockBean
    private DependencyBean dependencyBean;
    // Test cases...
}
```
**Edge Case:** `@MockBean` replaces all beans of the given type in the context.

### Testing Events and AOP
Test listeners and aspects in isolation by publishing events or invoking proxied methods in your test.
```java
@Autowired
private ApplicationEventPublisher publisher;

@Test
public void testEvent() {
    publisher.publishEvent(new MyEvent(this));
}
```

### Test Configuration
Use `@TestConfiguration` for test-specific beans.
```java
@TestConfiguration
public class TestBeans {
    @Bean
    public MyMockBean myMockBean() { ... }
}
```

### Summary Table: Testing Annotations
| Annotation           | Purpose                                 |
|----------------------|-----------------------------------------|
| `@SpringBootTest`    | Loads full application context          |
| `@ContextConfiguration` | Loads custom config                  |
| `@MockBean`          | Replaces bean with mock                 |
| `@TestConfiguration` | Adds beans for tests only               |
| `@ActiveProfiles`    | Sets active profile for test            |

---

## 9. Error Handling & Diagnostics

Robust error handling and diagnostics are critical for maintainable Spring applications.

### BeanCreationException
Thrown when Spring cannot instantiate a bean. Common causes: missing dependencies, constructor errors, or misconfiguration.
```text
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'myBean'
```
**Debugging:** Check the stack trace for the underlying cause.

### Circular Dependency Errors
Spring throws an error if circular dependencies cannot be resolved (e.g., with constructor injection).
```text
Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException
```
**Resolution:** Use setter/field injection or refactor to break the cycle.

### ApplicationContextException
Raised on context startup failures, often due to configuration errors.

### Logging & Debugging
Enable debug logging for Spring:
```properties
logging.level.org.springframework=DEBUG
```
This prints bean wiring and context details.

### Custom Error Handlers
Implement `ApplicationListener<ApplicationFailedEvent>` to handle startup failures.
```java
@Component
public class StartupFailureListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        // Custom logging or alerting
    }
}
```

### Diagnostics Table
| Exception Type                    | Typical Cause                             |
|-----------------------------------|-------------------------------------------|
| `BeanCreationException`           | Instantiation/config errors               |
| `BeanCurrentlyInCreationException`| Circular dependency                       |
| `ApplicationContextException`     | Context startup failure                   |

---

## 10. Performance Tuning & Optimization

Optimizing Spring applications involves careful bean management and resource handling.

### Lazy Initialization
`@Lazy` defers bean creation, reducing startup time.
```java
@Component
@Lazy
public class ExpensiveBean { ... }
```

### Prototype Scope
Use for lightweight, short-lived beans (e.g., per-request objects).
```java
@Scope("prototype")
@Component
public class RequestScopedBean { ... }
```
**Edge Case:** Prototype beans are not managed for destruction.

### Avoid Unnecessary Proxies
Only use AOP where needed. Excessive proxies add overhead.

### Bean Definition Overriding
Disable bean overriding in production to catch config errors.
```properties
spring.main.allow-bean-definition-overriding=false
```

### Resource Cleanup
Always release resources in destroy callbacks.

### Optimization Table
| Tactic                       | Benefit                               |
|------------------------------|---------------------------------------|
| Lazy beans                   | Faster startup                        |
| Prototype scope              | Lower memory footprint                |
| No unnecessary proxies       | Lower method call overhead            |
| Resource cleanup             | Prevents leaks                        |
| Disable bean overriding      | Early config error detection          |

---

## 11. Best Practices & Patterns

Follow these best practices for robust, maintainable Spring applications:

### Loose Coupling
Always inject interfaces rather than concrete classes.
```java
@Autowired
private ServiceInterface service;
```

### Separation of Concerns
Extract cross-cutting concerns (logging, security) into aspects or event listeners.

### Fail Fast
Validate configuration and dependencies at startup. Use `@Validated` on configuration properties.

### Documentation
Use JavaDoc and inline comments for complex beans and configuration classes.

### Performance
Minimize bean creation time, avoid unnecessary proxies, and use lazy initialization where appropriate.

### Consistent Naming
Adopt a clear, consistent naming convention for beans and configuration classes.

### Additional Patterns
- Use `@ConfigurationProperties` for structured config.
- Use `@Profile` for environment-specific beans.
- Use `@EventListener` for decoupled, event-driven design.

### Summary Table: Best Practices
| Practice                  | Benefit                           |
|---------------------------|-----------------------------------|
| Interface-based DI        | Testability, flexibility          |
| AOP/events for X-cutting  | Separation of concerns            |
| Fail-fast config          | Early error detection              |
| Documentation             | Maintainability                   |
| Consistent naming         | Readability                       |

---

## References

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html)
- [Spring Framework: Bean Scopes](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes)
- [Spring Bean Lifecycle](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle)
- [Spring AOP Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- [Spring Events Guide (Baeldung)](https://www.baeldung.com/spring-events)
- [Spring Async Events](https://www.baeldung.com/spring-events-async)
- [Spring Context Propagation](https://www.baeldung.com/spring-context-propagation)
- [Spring Profiles](https://www.baeldung.com/spring-profiles)
- [Spring Resource Abstraction](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources)
- [Spring Testing Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html)
- [Spring Boot Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [Spring @ConfigurationProperties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#configuration-properties)
- [Spring FactoryBean](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension-factorybean)
- [Spring @Conditional](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-conditional)
- [Spring @EventListener](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events-annotation)
- [Spring SmartLifecycle](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle-smart)

### Deep Dives
- [BeanPostProcessor Internals](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension-bpp)
- [BeanFactoryPostProcessor Deep Dive](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension-bfpp)
- [Custom Scopes](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-custom)
- [Method Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-method-injection)
- [Circular Dependencies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-circular-dependencies)
- [AOP Proxying](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-proxying)
- [Testing with Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)

---

#### Subtopics
- **Spring Core (Advanced IoC & DI)**
  - [Custom Bean Post Processors](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension-bpp)
  - [Bean Factory Post Processors](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-extension-bfpp)
  - [Custom Scopes](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-custom)
  - [Method Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-method-injection)
  - [Circular Dependencies Handling](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-circular-dependencies)
- **Event Processing**
  - [Application Events](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events)
  - [Custom Events](https://www.baeldung.com/spring-events)
  - [Async Event Processing](https://www.baeldung.com/spring-events-async)
  - Transaction-bound Events
  - Event Threading Models
  - Error Handling in Async Events
  - Event Order Guarantees
- **AOP Programming**
  - [Custom Annotations](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-at-aspectj)
  - [Pointcuts & Advice](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-pointcuts)
  - [Around Advice](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-around-advice)
  - [Method Interception](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-api)
- **Advanced Bean Lifecycle**
  - [Bean Creation Phases](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle)
  - [Lifecycle Callbacks](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle-callbacks)
  - [@PostConstruct and @PreDestroy](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-postconstruct-predestroy-annotations)
  - [SmartLifecycle Interface](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle-smart)
  - Custom Bean Definition
  - Bean Factory Post-Processing
- **Advanced Context Management**
  - [Multiple Context Hierarchies](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#web-application-context)
  - [Context Propagation](https://www.baeldung.com/spring-context-propagation)
  - [WebApplicationContext](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#web-application-context)
  - [Reactive Context](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-reactive-context)

#### Example
*Custom Bean Post Processor*
```java
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // Custom logic before bean initialization
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // Custom logic after bean initialization
        return bean;
    }
}
```

#### External Links
- [Spring Framework Docs: Core](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html)
- [Baeldung: Spring Events](https://www.baeldung.com/spring-events)
- [Spring AOP Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- [Spring Context Propagation](https://www.baeldung.com/spring-context-propagation)
- [Spring Profiles](https://www.baeldung.com/spring-profiles)
- [Spring Resource Abstraction](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources)
- [Spring Testing Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html)
- [Spring Boot Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

---