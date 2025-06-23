# 📘 Core Spring Framework Deep Dive

## Overview
This section explores advanced internals of the Spring Core framework, including Dependency Injection, AOP, context lifecycle, and event handling.

## Topics Covered

### 1. Spring Core (Advanced IoC & DI)
- Custom Bean Post Processors
- Bean Factory Post Processors
- Custom Scopes
- Method Injection
- Circular Dependencies Handling

### 2. Event Processing
- Application Events
- Custom Events
- Async Event Processing
- Transaction-bound Events
- Event Threading Models
- Error Handling in Async Events
- Event Order Guarantees

### 3. AOP Programming
- Custom Annotations
- Pointcuts & Advice
- Around Advice
- Method Interception

### 4. Advanced Bean Lifecycle
- Bean Creation Phases
- Lifecycle Callbacks
- @PostConstruct and @PreDestroy
- SmartLifecycle Interface
- Custom Bean Definition
- Bean Factory Post-Processing

### 5. Advanced Context Management
- Multiple Context Hierarchies
- Context Propagation
- WebApplicationContext
- Reactive Context

## References
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html)
- [Baeldung: Spring Events](https://www.baeldung.com/spring-events)
- [Spring AOP Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)

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

---