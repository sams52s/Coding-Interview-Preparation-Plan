# 🧷 Java Annotations

Annotations in Java provide metadata about the code and are used by the compiler, runtime, or frameworks for validation, behavior configuration, documentation, and code generation.

---

## 📌 What are Annotations?

- Annotations are **special markers** in Java code prefixed with `@`.
- They **do not change code behavior directly** but can influence compilers or be accessed via **reflection** at runtime.
- Commonly used in **Spring, Hibernate, JPA, Jakarta EE, Swagger**, and testing frameworks like **JUnit/TestNG**.

```java
@Override
public String toString() {
    return "Example";
}
```

---

## 🛠️ Built-in Java Annotations

| Annotation              | Purpose                                                |
|-------------------------|--------------------------------------------------------|
| `@Override`             | Ensures method is correctly overriding superclass method |
| `@Deprecated`           | Marks method or class as obsolete                      |
| `@SuppressWarnings`     | Suppresses compiler warnings                           |
| `@FunctionalInterface`  | Ensures interface has only one abstract method         |
| `@SafeVarargs`          | Suppresses warnings for varargs with generics          |
| `@Retention`            | Sets annotation visibility duration                    |
| `@Target`               | Sets annotation applicable elements                    |
| `@Documented`           | Makes annotation appear in generated Javadoc           |
| `@Inherited`            | Allows subclass to inherit annotation                  |
| `@Repeatable`           | Allows multiple instances of same annotation           |

---

## 🔧 Meta-Annotations Summary

| Meta-Annotation     | Description |
|---------------------|-------------|
| `@Retention`        | Declares how long annotations are retained (SOURCE, CLASS, RUNTIME) |
| `@Target`           | Restricts where annotation can be applied (METHOD, FIELD, etc.) |
| `@Documented`       | Includes annotation in generated Javadoc |
| `@Inherited`        | Enables annotation inheritance by subclasses |
| `@Repeatable`       | Allows same annotation multiple times on one element |

---

## ✨ Creating Custom Annotations

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LogExecutionTime {
    boolean enabled() default true;
}
```

Usage:
```java
@LogExecutionTime(enabled = true)
public void process() {}
```

With multiple attributes:
```java
public @interface Role {
    String name();
    String[] permissions() default {};
}
```

---

## 📐 Retention Policies

| Retention         | Visibility                        |
|-------------------|------------------------------------|
| `SOURCE`          | Discarded during compilation       |
| `CLASS` (default) | Retained in `.class` files, invisible at runtime |
| `RUNTIME`         | Available via reflection at runtime |

```java
@Retention(RetentionPolicy.RUNTIME)
```

---

## 🎯 Targets (`ElementType`)

| ElementType         | Applies To                        |
|---------------------|-----------------------------------|
| `TYPE`              | Class, interface, enum            |
| `FIELD`             | Fields                            |
| `METHOD`            | Methods                           |
| `PARAMETER`         | Method parameters                 |
| `CONSTRUCTOR`       | Constructors                      |
| `LOCAL_VARIABLE`    | Local variables                   |
| `ANNOTATION_TYPE`   | Another annotation                |
| `PACKAGE`           | Packages                          |
| `TYPE_USE`          | Type declarations (Java 8+)       |

```java
@Target({ElementType.METHOD, ElementType.TYPE})
```

---

## 🔄 Repeatable Annotations

```java
@Repeatable(Schedules.class)
@interface Schedule {
    String value();
}

@interface Schedules {
    Schedule[] value();
}

@Schedule("daily")
@Schedule("weekly")
public class Task {}
```

---

## 🧠 Type Annotations (Java 8+)

```java
List<@NonNull String> names = new ArrayList<>();
public void process() throws @Critical Exception {}
```

Use `@Target(ElementType.TYPE_USE)`.

---

## 🧪 Annotation Processing (APT)

Used for code generation, validation, or custom compile-time behavior.

```java
@SupportedAnnotationTypes("com.example.MyAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MyProcessor extends AbstractProcessor {
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for (Element e : env.getElementsAnnotatedWith(MyAnnotation.class)) {
            // generate or validate
        }
        return true;
    }
}
```

---

## 🔍 Accessing Annotations via Reflection

```java
Method method = MyClass.class.getMethod("serve");
LogExecutionTime annotation = method.getAnnotation(LogExecutionTime.class);
if (annotation != null && annotation.enabled()) {
    // Execute logic conditionally
}
```

---

## 🎯 Advanced Annotation Concepts

### Annotation Composition
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Auth {
    String role() default "USER";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Auth(role = "ADMIN")
@interface AdminOnly {}

@AdminOnly // Effectively applies @Auth(role = "ADMIN")
public class AdminPanel {}
```

### Annotation Inheritance Patterns
```java
@Inherited
@interface ClassLevel {
    String value();
}

@ClassLevel("parent")
class Parent {}

class Child extends Parent {} // Inherits @ClassLevel

// Custom inheritance for methods
public @interface InheritedMethod {
    boolean inherit() default true;
}

class AnnotationUtils {
    public static <T extends Annotation> T findAnnotation(Method method, Class<T> annotationType) {
        T annotation = method.getAnnotation(annotationType);
        if (annotation != null) return annotation;
        
        // Check superclass methods
        Class<?> superclass = method.getDeclaringClass().getSuperclass();
        if (superclass != null) {
            try {
                Method superMethod = superclass.getDeclaredMethod(
                    method.getName(), 
                    method.getParameterTypes()
                );
                return findAnnotation(superMethod, annotationType);
            } catch (NoSuchMethodException e) {
                return null;
            }
        }
        return null;
    }
}
```

### Advanced Annotation Processing
```java
@SupportedAnnotationTypes("com.example.*")
public class AdvancedProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Generate source code
        try {
            JavaFileObject file = processingEnv.getFiler()
                .createSourceFile("GeneratedClass");
            
            try (PrintWriter out = new PrintWriter(file.openWriter())) {
                out.println("package com.example;");
                out.println("public class GeneratedClass {");
                // Generate code based on annotations
                out.println("}");
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(
                Diagnostic.Kind.ERROR, 
                "Failed to create file: " + e.getMessage()
            );
        }
        return true;
    }
}
```

---

## 🧱 Real-World Annotation Use Cases

### ✅ Spring Boot

```java
@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired private Service service;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }
}
```

### ✅ Hibernate/JPA

```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue private Long id;
    @Column(nullable = false) private String name;
}
```

### ✅ Validation (Hibernate Validator / Jakarta Bean Validation)

```java
public class Account {
    @NotNull @Email private String email;
    @Min(18) private int age;
}
```

### ✅ Swagger / OpenAPI

```java
@ApiModel(description = "User DTO")
public class UserDto {
    @ApiModelProperty(value = "The user's name", required = true)
    private String name;
}
```

---

## ⚠️ Best Practices

- Prefer runtime retention only when necessary (for frameworks).
- Keep annotations focused on metadata — not logic.
- Avoid using annotations for everything: sometimes config or logic is better.
- Always document the purpose of custom annotations.

---

## 💼 Interview Questions & Answers

- **What is an annotation in Java?**  
  → Metadata used to provide information to the compiler or tools/frameworks.

- **Difference between `@Target` and `@Retention`?**  
  → `@Target` defines where the annotation can be applied; `@Retention` defines how long it is retained.

- **What are marker annotations?**  
  → Annotations without elements, e.g., `@Override`.

- **What is an annotation processor?**  
  → A compile-time tool that reads annotations to generate files or validate code.

- **Can annotations be repeated?**  
  → Yes, using `@Repeatable` and a container annotation.

- **What are type annotations and why are they useful?**  
  → Allow annotation of types like generics, exceptions — mainly for static analysis.

- **Can annotations have default values?**  
  → Yes, using `default` keyword in the declaration.

- **What are limitations of annotations?**  
  → No logic allowed, can’t extend, only constants allowed as values.

---

## 💼 Additional Interview Questions & Answers

### ❓ How do you handle circular dependencies in annotation processing?
**✅ A:**
```java
public class CircularProcessor extends AbstractProcessor {
    private Set<Element> processed = new HashSet<>();
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MyAnnotation.class)) {
            if (!processed.contains(element)) {
                processed.add(element);
                // Process element
            }
        }
        return true;
    }
}
```

### ❓ How can you create composite annotations?
**✅ A:**
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping("/api")
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 500, message = "Server Error")
})
public @interface RestApiController {
    String value() default "";
}

@RestApiController
public class UserController {} // Gets all composed annotations
```

### ❓ How do you handle annotation validation?
**✅ A:**
```java
@Retention(RetentionPolicy.RUNTIME)
@interface ValidRange {
    int min() default 0;
    int max() default 100;
}

public class AnnotationValidator {
    public static void validate(Object obj) throws IllegalArgumentException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            ValidRange range = field.getAnnotation(ValidRange.class);
            if (range != null) {
                field.setAccessible(true);
                try {
                    int value = (int) field.get(obj);
                    if (value < range.min() || value > range.max()) {
                        throw new IllegalArgumentException(
                            "Field " + field.getName() + " out of range"
                        );
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```

### ❓ How do you test custom annotations?
**✅ A:**
```java
@Test
void testCustomAnnotation() {
    // Given
    @ValidRange(min = 1, max = 10)
    class TestClass {
        private int value = 15;
    }
    
    // When/Then
    TestClass test = new TestClass();
    assertThrows(IllegalArgumentException.class, () -> 
        AnnotationValidator.validate(test)
    );
}
```

### ❓ How do you handle versioning with annotations?
**✅ A:**
```java
@interface ApiVersion {
    int since() default 1;
    int until() default Integer.MAX_VALUE;
}

@ApiVersion(since = 2, until = 4)
public class VersionedApi {
    public boolean isSupported(int version) {
        ApiVersion ann = getClass().getAnnotation(ApiVersion.class);
        return version >= ann.since() && version <= ann.until();
    }
}
```

### ❓ How do you implement custom annotation retention?
**✅ A:**
```java
// Custom retention implementation
public class CustomRetentionProcessor {
    public static Set<Class<?>> findClassesWithAnnotation(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(YourAnnotation.class);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Feature {
    String name();
    boolean enabled() default true;
}

@Feature(name = "test-feature")
class FeatureImpl {}
```

### ❓ What's the difference between class-level and runtime retention?
**✅ A:**
```java
// CLASS retention - not available at runtime
@Retention(RetentionPolicy.CLASS)
@interface CompileTimeOnly {
    String value();
}

// RUNTIME retention - available via reflection
@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeAccess {
    String value();
}

@CompileTimeOnly("Won't be available")
@RuntimeAccess("Will be available")
class Example {
    public static void checkAnnotations() {
        RuntimeAccess runtime = Example.class.getAnnotation(RuntimeAccess.class);
        CompileTimeOnly compile = Example.class.getAnnotation(CompileTimeOnly.class); // Returns null
    }
}
```

### ❓ How do you handle annotation inheritance with interfaces?
**✅ A:**
```java
@Inherited
@interface ServiceMarker {
    String value();
}

@ServiceMarker("parent")
interface ParentService {}

interface ChildService extends ParentService {} // Doesn't inherit @ServiceMarker

@ServiceMarker("implementation")
class ServiceImpl implements ParentService {} // Gets its own @ServiceMarker
```

### ❓ How do you implement custom annotation validation logic?
**✅ A:**
```java
@Retention(RetentionPolicy.RUNTIME)
@interface DateRange {
    String start();
    String end();
}

public class DateRangeValidator {
    public static void validate(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            DateRange range = field.getAnnotation(DateRange.class);
            if (range != null) {
                LocalDate start = LocalDate.parse(range.start());
                LocalDate end = LocalDate.parse(range.end());
                if (start.isAfter(end)) {
                    throw new IllegalArgumentException("Invalid date range");
                }
            }
        }
    }
}
```

### ❓ How do you handle multiple annotations on the same element?
**✅ A:**
```java
@interface Validate {
    String[] rules();
}

@interface Log {
    LogLevel level() default LogLevel.INFO;
}

class AnnotationHandler {
    public static void process(Method method) {
        Validate validate = method.getAnnotation(Validate.class);
        Log log = method.getAnnotation(Log.class);
        
        if (validate != null && log != null) {
            // Handle both annotations
            Arrays.stream(validate.rules()).forEach(rule -> validateRule(rule));
            logMethod(method, log.level());
        }
    }
}

@Validate(rules = {"notNull", "length"})
@Log(level = LogLevel.DEBUG)
public void processData(String input) {}
```

### ❓ How do you implement conditional annotation processing?
**✅ A:**
```java
@Retention(RetentionPolicy.RUNTIME)
@interface ConditionalOnProperty {
    String value();
    String havingValue() default "";
}

public class ConditionalProcessor {
    public static boolean shouldProcess(Class<?> clazz) {
        ConditionalOnProperty annotation = clazz.getAnnotation(ConditionalOnProperty.class);
        if (annotation != null) {
            String propertyValue = System.getProperty(annotation.value());
            return annotation.havingValue().equals(propertyValue);
        }
        return true;
    }
}

@ConditionalOnProperty(value = "env", havingValue = "dev")
class DevConfig {}
```

### ❓ How do you implement custom annotation documentation?
**✅ A:**
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface ApiDoc {
    String value();
    String[] authors();
    String version();
}

@ApiDoc(
    value = "User management API",
    authors = {"John", "Jane"},
    version = "1.0"
)
class UserAPI {
    public static String getDocumentation() {
        ApiDoc doc = UserAPI.class.getAnnotation(ApiDoc.class);
        return String.format(
            "API: %s\nVersion: %s\nAuthors: %s",
            doc.value(),
            doc.version(),
            String.join(", ", doc.authors())
        );
    }
}
```

---

## ✅ Summary

Java annotations enable declarative programming. They're critical in modern Java development with frameworks like Spring, JPA, Jakarta EE, and more. Mastering them is essential for writing clear, scalable, and framework-friendly code.

---

## 📝 References

- [Java Annotations – Oracle Docs](https://docs.oracle.com/javase/tutorial/java/annotations/)  
- [Baeldung – Custom Annotations](https://www.baeldung.com/java-custom-annotation)  
- [GeeksforGeeks – Java Annotations](https://www.geeksforgeeks.org/annotations-in-java/)  
- [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
- [JSR-380 Bean Validation](https://beanvalidation.org/2.0/)
- [Java Reflection](https://docs.oracle.com/javase/tutorial/reflect/)