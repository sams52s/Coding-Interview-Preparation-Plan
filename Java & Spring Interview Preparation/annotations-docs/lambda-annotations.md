# 🧩 Lambda Annotations in Java

Java introduced lambda expressions in Java 8, enabling a functional programming style. Annotations such as `@FunctionalInterface` support this feature by ensuring correct use of functional interfaces — the foundational element for lambdas.

---

## 📌 What is a Functional Interface?

- A **functional interface** contains exactly **one abstract method** (SAM – Single Abstract Method).
- It may have any number of `default` or `static` methods.
- Lambdas or method references can be used to implement them.

```java
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
```

---

## 🏷️ @FunctionalInterface Annotation

| Annotation              | Purpose                                      |
|--------------------------|----------------------------------------------|
| `@FunctionalInterface`   | Compile-time enforcement for SAM compliance  |

```java
@FunctionalInterface
public interface Printer {
    void print(String msg);
    default void log(String msg) {
        System.out.println("LOG: " + msg);
    }
}
```

If more than one abstract method is declared, a compilation error occurs.

---

## 🔁 Lambda Expression + Functional Interface

```java
Printer printer = msg -> System.out.println("Printed: " + msg);
printer.print("Hello");
```

Method Reference:
```java
Consumer<String> logger = System.out::println;
```

---

## 🔧 Custom Functional Interfaces

```java
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
```

Usage:
```java
TriFunction<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;
```

---

## 🧠 Built-in Functional Interfaces

Java provides these in `java.util.function`:

| Interface           | Method       | Description                          |
|---------------------|--------------|--------------------------------------|
| `Function<T,R>`     | `apply`      | Transforms input to output           |
| `Predicate<T>`      | `test`       | Tests a condition                    |
| `Consumer<T>`       | `accept`     | Consumes input, returns void         |
| `Supplier<T>`       | `get`        | Provides values without input        |
| `UnaryOperator<T>`  | `apply`      | Operates on and returns same type    |
| `BinaryOperator<T>` | `apply`      | Combines two inputs of same type     |

---

## ⚙️ SAM Conversion (Single Abstract Method)

Lambdas are converted into instances of functional interfaces using **SAM conversion**.

```java
Runnable task = () -> System.out.println("Running...");
```

This is compiled using `invokedynamic`, resulting in **efficient runtime objects**.

---

## 📦 Real-World Use Cases

- **Event Listeners**:
```java
button.setOnClickListener(e -> System.out.println("Clicked!"));
```

- **Stream APIs**:
```java
List<String> data = List.of("a", "b", "abc");
data.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
```

- **Async Callbacks**:
```java
void execute(Callback cb) { cb.call(); }
execute(() -> System.out.println("Done!"));
```

---

## 🧩 Lambda-Compatible Annotations

| Annotation               | Purpose                                           |
|--------------------------|--------------------------------------------------|
| `@FunctionalInterface`   | Marks an interface as SAM                        |
| `@Target(TYPE_USE)`      | Allows annotations on lambda parameter types     |
| `@NonNull`, `@Nullable`  | Used in lambdas for parameter validation         |
| `@Retention`             | Retains metadata at runtime                      |
| `@Documented`, `@Inherited` | Improves API tooling, documentation clarity |

---

## 📐 Advanced Lambda Techniques

### ✅ Currying
```java
Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;
```

### ✅ Function Composition
```java
Function<String, String> trim = String::trim;
Function<String, String> upper = String::toUpperCase;
Function<String, String> composed = trim.andThen(upper);
```

### ✅ Recursive Lambda
```java
UnaryOperator<Integer>[] factorial = new UnaryOperator[1];
factorial[0] = n -> (n <= 1) ? 1 : n * factorial[0].apply(n - 1);
```

---

## ⚠️ Common Pitfalls

- Can't throw checked exceptions unless declared
- Can't have multiple abstract methods in functional interface
- `this` in lambdas refers to enclosing class, not the lambda scope
- Lambdas can accidentally capture mutable external variables

---

## 💼 Interview Questions & Answers — Lambda & Functional Interfaces

### ❓ What is a functional interface?
✅ A: An interface with one abstract method; used with lambdas.

### ❓ What is the use of `@FunctionalInterface`?
✅ A: Prevents multiple abstract method declarations at compile time.

### ❓ Can a functional interface have default/static methods?
✅ A: Yes, only one abstract method is required.

### ❓ What is SAM conversion?
✅ A: Automatic conversion of a lambda to an instance of a SAM interface.

### ❓ Difference between lambda and anonymous inner class?

| Aspect       | Lambda                       | Anonymous Class               |
|--------------|-------------------------------|-------------------------------|
| `this`       | Refers to outer class         | Refers to anonymous class     |
| Verbosity    | Concise                       | More verbose                  |
| Performance  | Better (uses invokedynamic)   | Slower (creates inner class)  |
| Use case     | Functional programming        | Extending abstract behavior   |

### ❓ How are lambdas compiled?
✅ A: Into private static methods, then linked via `invokedynamic`.

### ❓ Can lambdas be serialized?
✅ A: Only if the target functional interface extends `Serializable`. Not generally recommended.

### ❓ Can lambdas throw exceptions?
✅ A: Yes — only if declared in the functional interface’s method.

### ❓ Can you annotate lambda parameters?
✅ A: Yes, with type-use annotations:

```java
Predicate<@NonNull String> predicate = s -> s.length() > 0;
```

### ❓ How do you handle exception handling in lambdas?
✅ A: Using wrapper methods or specialized functional interfaces:
```java
@FunctionalInterface
interface ThrowingFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}

public static <T, R, E extends Exception> Function<T, R> handleException(
    ThrowingFunction<T, R, E> throwingFunction) {
    return t -> {
        try {
            return throwingFunction.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}

// Usage
List<String> files = Arrays.asList("file1.txt", "file2.txt");
files.stream()
     .map(handleException(Files::readString))
     .forEach(System.out::println);
```

### ❓ How do you implement lazy evaluation with lambdas?
✅ A:
```java
public class Lazy<T> {
    private T value;
    private final Supplier<T> supplier;
    
    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    
    public T get() {
        if (value == null) {
            value = supplier.get();
        }
        return value;
    }
    
    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
}

// Usage
Lazy<ExpensiveObject> lazy = Lazy.of(() -> new ExpensiveObject());
```

### ❓ How do you implement method chaining with lambdas?
✅ A:
```java
@FunctionalInterface
interface Transform<T> {
    T apply(T t);
    
    default Transform<T> andThen(Transform<T> after) {
        return t -> after.apply(apply(t));
    }
}

// Usage
Transform<String> trim = String::trim;
Transform<String> upper = String::toUpperCase;
Transform<String> process = trim.andThen(upper);
String result = process.apply("  hello  "); // "HELLO"
```

### ❓ How do you implement custom collectors with lambdas?
✅ A:
```java
public class CustomCollectors {
    public static <T> Collector<T, ?, LinkedList<T>> toLinkedList() {
        return Collector.of(
            LinkedList::new,
            LinkedList::add,
            (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            }
        );
    }
}

// Usage
LinkedList<String> linked = stream.collect(CustomCollectors.toLinkedList());
```

### ❓ How do you implement pattern matching with lambdas?
✅ A:
```java
public class PatternMatching {
    interface Pattern<T, R> {
        Optional<R> match(T value);
    }
    
    static <T, R> Pattern<T, R> when(Predicate<T> pred, Function<T, R> map) {
        return v -> pred.test(v) ? Optional.of(map.apply(v)) : Optional.empty();
    }
    
    @SafeVarargs
    static <T, R> Function<T, R> match(Pattern<T, R>... patterns) {
        return t -> Stream.of(patterns)
            .map(p -> p.match(t))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElseThrow();
    }
}

// Usage
var matcher = match(
    when(s -> s.startsWith("a"), s -> "starts with a"),
    when(s -> s.length() > 5, s -> "long string"),
    when(s -> true, s -> "default case")
);
```

### ❓ How do you implement monads with lambdas?
✅ A:
```java
public class Optional<T> {
    private final T value;
    
    private Optional(T value) {
        this.value = value;
    }
    
    public <R> Optional<R> map(Function<T, R> mapper) {
        return value == null ? empty() : of(mapper.apply(value));
    }
    
    public <R> Optional<R> flatMap(Function<T, Optional<R>> mapper) {
        return value == null ? empty() : mapper.apply(value);
    }
    
    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }
    
    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }
}
```

### ❓ How do you handle lambda capture and memory leaks?
✅ A:
```java
public class MemoryLeakExample {
    private final List<Runnable> listeners = new ArrayList<>();
    
    // Bad - captures 'this' implicitly
    public void addListenerBad() {
        listeners.add(() -> System.out.println(this.toString()));
    }
    
    // Good - no implicit capture
    public void addListenerGood() {
        String value = this.toString();
        listeners.add(() -> System.out.println(value));
    }
}
```

### ❓ How do you implement lambda-based memoization?
✅ A:
```java
public class Memoizer<T, R> {
    private final Map<T, R> cache = new ConcurrentHashMap<>();
    
    public Function<T, R> memoize(Function<T, R> function) {
        return input -> cache.computeIfAbsent(input, function);
    }
}

// Usage
Function<Integer, BigInteger> factorial = new Memoizer<Integer, BigInteger>()
    .memoize(n -> n <= 1 ? BigInteger.ONE : 
        BigInteger.valueOf(n).multiply(factorial.apply(n - 1)));
```

### ❓ How do you handle partial application with lambdas?
✅ A:
```java
public class PartialApplication {
    interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }
    
    public static <A, B, C, R> BiFunction<B, C, R> partial(
            TriFunction<A, B, C, R> f, A a) {
        return (b, c) -> f.apply(a, b, c);
    }
}

// Usage
TriFunction<String, String, Integer, String> formatter = 
    (template, value, count) -> template.formatted(value, count);
BiFunction<String, Integer, String> errorFormatter = 
    partial(formatter, "Error: %s occurred %d times");
```

### ❓ How do you implement lambda-based event handling?
✅ A:
```java
public class EventBus<T> {
    private final Map<Class<?>, List<Consumer<T>>> subscribers = new HashMap<>();
    
    public void subscribe(Class<?> eventType, Consumer<T> handler) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>())
                  .add(handler);
    }
    
    public void publish(T event) {
        Class<?> eventType = event.getClass();
        Optional.ofNullable(subscribers.get(eventType))
                .ifPresent(handlers -> 
                    handlers.forEach(handler -> handler.accept(event)));
    }
}

// Usage
EventBus<Object> bus = new EventBus<>();
bus.subscribe(UserCreatedEvent.class, 
    event -> System.out.println("User created: " + event));
```

### ❓ How do you optimize lambda performance?
✅ A:
```java
public class LambdaPerformance {
    // Bad - creates new lambda for each call
    public void bad(List<String> list) {
        list.forEach(s -> System.out.println(s));
    }
    
    // Good - reuses static method reference
    public void good(List<String> list) {
        list.forEach(System.out::println);
    }
    
    // Better - avoid unnecessary boxing/unboxing
    private static final ToIntFunction<String> LENGTH = String::length;
    public void better(List<String> list) {
        list.stream()
            .mapToInt(LENGTH)
            .forEach(System.out::println);
    }
}
```

---

## ✅ Summary

Lambdas and functional interfaces simplify Java code and enable a functional style. Annotations like `@FunctionalInterface` ensure safety and readability, while SAM conversion and method references offer modern expressiveness. Mastery of lambda annotations is critical in modern Java development.

---

## 📝 References

- [Java Functional Interfaces – Oracle Docs](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)  
- [Baeldung – Java Functional Interfaces](https://www.baeldung.com/java-8-functional-interfaces)  
- [GeeksforGeeks – Functional Interfaces](https://www.geeksforgeeks.org/functional-interfaces-java/)
