# Annotations Documentation Library

This folder is a focused reference for Java and framework annotations used in backend development, persistence, lambda/functional programming, and testing.

**Navigation:** [Main README](../../README.md) | [Learning Roadmap](../../README.md#learning-roadmap) | [Project Architecture](../../PROJECT_ARCHITECTURE.md) | [Java & Spring Hub](../README.md) | [Spring Boot](../Spring%20Boot/README.md) | Related: [Spring Boot Q&A](../../Question%20Bank/spring-boot.md)

**Practice:** [Library Project](../../projects/02_library_management_system.md) | [Spring Boot REST API Project](../../projects/03_spring_boot_rest_api.md) | [Code Refactoring Examples](../../CODE_REFACTORING_EXAMPLES.md)

## Folder files
- [java-annotations.md](java-annotations.md) — built-in annotations, custom annotations, meta-annotations, retention, targets, and annotation processing.
- [spring-annotations.md](spring-annotations.md) — Spring stereotypes, dependency injection, web MVC, transactions, configuration, security, and testing annotations.
- [lambda-annotations.md](lambda-annotations.md) — `@FunctionalInterface`, SAM rules, functional APIs, and lambda-related annotation usage.
- [jpa-annotations.md](jpa-annotations.md) — JPA/Hibernate entity mapping, relationships, inheritance, lifecycle callbacks, and converters.
- [test-annotations.md](test-annotations.md) — JUnit, Mockito, Spring Test, transactional tests, and test lifecycle annotations.
- [README.md](README.md) — this annotations guide.

## How it connects
- The [advanced Java and annotations stage](../../README.md#learning-roadmap) uses this folder for Java, Spring, JPA, lambda, and testing annotations.
- [Spring Boot](../Spring%20Boot/README.md) depends heavily on these annotations for DI, REST APIs, persistence, security, testing, and configuration.
- Related question bank files: [spring-boot.md](../../Question%20Bank/spring-boot.md), [spring-data-JPA-&-hibernate.md](../../Question%20Bank/spring-data-JPA-%26-hibernate.md), [testing-quality.md](../../Question%20Bank/testing-quality.md).

## Suggested reading order
1. [java-annotations.md](java-annotations.md)
2. [lambda-annotations.md](lambda-annotations.md)
3. [spring-annotations.md](spring-annotations.md)
4. [jpa-annotations.md](jpa-annotations.md)
5. [test-annotations.md](test-annotations.md)

## Annotation categories to master
- Core Java: `@Override`, `@Deprecated`, `@SuppressWarnings`, `@Retention`, `@Target`.
- Spring components: `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController`.
- Dependency injection: `@Autowired`, `@Qualifier`, `@Primary`, `@Value`.
- Web/API: `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestBody`.
- Data/JPA: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, relationships, and lifecycle callbacks.
- Testing: `@Test`, `@BeforeEach`, `@Mock`, `@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest`.

## Applied practice
- Build a custom validation annotation with a `ConstraintValidator`, then document the error response shape.
- Explain meta-annotation composition in Spring, for example a custom stereotype that combines component scanning and transaction behavior.
- Compare Lombok convenience with debugging, generated code visibility, constructor clarity, and team standards.
- Connect annotation behavior to proxy limitations: self-invocation, final methods/classes, transaction boundaries, and AOP ordering.
- Use test annotations intentionally: pick `@WebMvcTest`, `@DataJpaTest`, or `@SpringBootTest` based on the slice being verified.
