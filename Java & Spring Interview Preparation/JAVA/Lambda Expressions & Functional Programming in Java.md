# 🔁 Lambda Expressions & Functional Programming in Java

Lambda expressions, introduced in **Java 8**, enable a **functional programming style** in Java. They provide a concise and powerful way to represent **anonymous functions**, improving readability and supporting operations on collections and streams.

---

## 🚀 1. What is a Lambda Expression?

A **lambda expression** is a shorthand for defining an **anonymous function** that can be passed around as a value.

### ✅ Syntax:
```java
(parameters) -> expression
(parameters) -> { statements; }
```

### 🔍 Example:
```java
Runnable r = () -> System.out.println("Running in a lambda");
r.run();
```

---

## ⚙️ 2. Functional Interfaces

Lambda expressions are only valid in contexts where a **functional interface** is expected.

### ✅ Common Functional Interfaces (`java.util.function`):
| Interface         | Abstract Method   | Description                          |
|------------------|-------------------|--------------------------------------|
| `Predicate<T>`    | `test(T t)`       | Returns a boolean                    |
| `Function<T,R>`   | `apply(T t)`      | Transforms a value                   |
| `Consumer<T>`     | `accept(T t)`     | Performs an action                   |
| `Supplier<T>`     | `get()`           | Supplies a value                     |
| `UnaryOperator<T>`| `apply(T t)`      | Same input and output type           |
| `BinaryOperator<T>`| `apply(T, T)`    | Two inputs of same type, one output  |

---

## 🎯 3. Lambda Expression Examples

### ✅ Runnable Example:
```java
Runnable r = () -> System.out.println("Lambda Thread");
new Thread(r).start();
```

### ✅ Comparator Example:
```java
List<String> names = Arrays.asList("John", "Jane", "Alex");
Collections.sort(names, (a, b) -> b.compareTo(a));
```

### ✅ Stream Filter with Predicate:
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
nums.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
```

---

## 🧠 4. Functional Programming Concepts

- **First-Class Functions** – Functions are treated like values.
- **Higher-Order Functions** – Accept or return other functions.
- **Pure Functions** – No side effects, same output for same input.
- **Immutability** – Avoids changing existing state.
- **Declarative Style** – Focus on *what* not *how*. (e.g., `stream().filter().map()`)

---

## 🧰 5. Benefits of Lambda and Functional Style

- Reduced boilerplate code
- Improved readability
- Enables Streams and parallelism
- Encourages immutability and stateless logic
- Better suited for concurrency

---

## 🔄 6. Advanced Lambda Techniques

### ✅ Method References

```java
Function<String, Integer> parser = Integer::parseInt;             // static
Consumer<String> printer = System.out::println;                  // instance on object
Function<String, Integer> lengthFunc = String::length;           // instance on type
Supplier<List<String>> listSupplier = ArrayList::new;            // constructor
```

### ✅ Function Composition

```java
Function<String, String> toLowerCase = String::toLowerCase;
Function<String, String> trim = String::trim;
Function<String, String> composed = toLowerCase.compose(trim);
Function<String, String> chained = trim.andThen(toLowerCase);
```

### ✅ Currying and Partial Application

```java
Function<Integer, Function<Integer, Integer>> curryAdd = x -> y -> x + y;
Function<Integer, Integer> add5 = curryAdd.apply(5);
int result = add5.apply(3); // 8
```

### ✅ Custom Functional Interface

```java
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
```

```java
TriFunction<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
```

---

## 🎯 Advanced Functional Patterns

### Pattern Matching with Lambdas (Java 17+)
```java
sealed interface Shape permits Circle, Rectangle {
    double area();
}

Shape shape = // ...
double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
};
```

### Lazy Evaluation
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

### Error Handling with Either Type
```java
public sealed interface Either<L, R> {
    record Left<L, R>(L value) implements Either<L, R> {}
    record Right<L, R>(R value) implements Either<L, R> {}
    
    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }
    
    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }
}

// Usage
Either<String, Integer> result = compute()
    .map(v -> v * 2)
    .flatMap(this::validateResult);
```

## 🔄 Advanced Stream Operations

### Custom Collectors
```java
public class CustomCollector {
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
LinkedList<String> linked = stream.collect(CustomCollector.toLinkedList());
```

### Specialized Stream Operations
```java
// Sliding window collector
public static <T> Collector<T, ?, List<List<T>>> sliding(int windowSize) {
    return Collector.of(
        ArrayList::new,
        (List<List<T>> lists, T t) -> {
            if (lists.isEmpty() || lists.get(lists.size() - 1).size() == windowSize) {
                lists.add(new ArrayList<>());
            }
            lists.get(lists.size() - 1).add(t);
        },
        (lists1, lists2) -> {
            lists1.addAll(lists2);
            return lists1;
        }
    );
}
```

---

## 💼 Interview Questions & Answers

- **What is a lambda expression?**  
  → A concise way to represent an anonymous function.

- **What is a functional interface?**  
  → An interface with one abstract method.

- **What package contains core functional interfaces?**  
  → `java.util.function`

- **What is method reference?**  
  → A shorthand for calling existing methods via `Class::method`.

- **Can lambda access outside variables?**  
  → Yes, if they are effectively final.

- **Difference between lambda and anonymous class?**  
  → Lambdas are more concise and don't capture `this`.

- **What’s the role of `@FunctionalInterface`?**  
  → Ensures the interface has only one abstract method.

- **What’s the difference between map and flatMap?**  
  → `map` transforms, `flatMap` flattens nested structures.

- **What is function composition?**  
  → Combining functions via `compose()` and `andThen()`.

- **How to handle exceptions in lambdas?**  
  → Use try-catch inside lambda or wrap with helper functions.

### Advanced Lambda Questions

**Q11: How does type inference work with lambda expressions?**
**✅ A:** The compiler determines lambda parameter types from the context:
```java
// Type inference examples
Function<String, Integer> f1 = (String s) -> s.length(); // Explicit
Function<String, Integer> f2 = s -> s.length();          // Inferred
var list = List.of("a", "b");
list.forEach(s -> System.out.println(s));                // Context-based
```

**Q12: What are the limitations of lambda expressions?**
**✅ A:**
- Can't access non-final local variables
- Can't use break/continue for outer loops
- Can't throw checked exceptions without wrapping
```java
int value = 10;
// value++; // Would make lambda invalid
Runnable r = () -> System.out.println(value);
```

**Q13: How do you handle checked exceptions in lambda expressions?**
**✅ A:** Use wrapper methods or functional interfaces:
```java
@FunctionalInterface
interface ThrowingFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}

public static <T, R, E extends Exception> Function<T, R> wrapper(
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
     .map(wrapper(Files::readString))
     .forEach(System.out::println);
```

**Q14: What is the difference between reduce and collect operations?**
**✅ A:**
```java
// Reduce combines elements into a single value
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);

// Collect creates a new mutable result container
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
```

**Q15: How do you debug lambda expressions?**
**✅ A:**
```java
stream.peek(x -> System.out.println("Before filter: " + x))
      .filter(x -> x > 0)
      .peek(x -> System.out.println("After filter: " + x))
      .collect(Collectors.toList());

// Or using breakpoints in IDE
stream.map(x -> {
    System.out.println("Processing: " + x); // Breakpoint here
    return process(x);
})
```

---

## ✅ Summary

Lambdas and streams enable Java developers to write clean, concise, and expressive code using a **functional paradigm**. Understanding these concepts leads to better design, performance, and readability.

---

## 📝 References

- [Java Lambda Expressions – Oracle](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)  
- [Java Functional Interfaces – Baeldung](https://www.baeldung.com/java-8-functional-interfaces)  
- [Java 8 Streams Guide – Baeldung](https://www.baeldung.com/java-8-streams)  
- [GeeksforGeeks – Lambda in Java](https://www.geeksforgeeks.org/lambda-expressions-java-8/)

---

## More Advanced Stream Operations

#### Parallel Streams and Performance
```java
// Parallel stream example
long count = list.parallelStream()
                .filter(item -> isExpensiveOperation(item))
                .count();

// Custom thread pool for parallel operations
ForkJoinPool customPool = new ForkJoinPool(4);
int result = customPool.submit(() ->
    list.parallelStream()
        .map(String::length)
        .reduce(0, Integer::sum)
).get();
```

#### Advanced Collectors
```java
// Custom grouping with complex reduction
Map<Department, Employee> topPerformers = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.collectingAndThen(
            Collectors.maxBy(Comparator.comparing(Employee::getPerformanceScore)),
            Optional::get
        )
    ));

// Hierarchical grouping
Map<String, Map<String, List<Employee>>> byDepartmentAndRole = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.groupingBy(Employee::getRole)
    ));
```

### Additional Interview Questions

**Q16: How do parallel streams work internally?**
**✅ A:** Parallel streams use the Fork/Join framework internally:
- Splits the data into sub-tasks
- Uses the common ForkJoinPool by default
- Each thread processes a chunk of data
- Results are combined using the combiner function
```java
// Example showing parallel vs sequential performance
long start = System.currentTimeMillis();
list.parallelStream()
    .filter(this::complexOperation)
    .count();
System.out.println("Parallel time: " + (System.currentTimeMillis() - start));
```

**Q17: What are the best practices for using lambda expressions?**
**✅ A:**
- Keep lambdas small and focused
- Extract complex lambdas to methods
- Avoid side effects
- Use method references when possible
- Be careful with parallel streams
```java
// Bad
list.stream().forEach(item -> {
    // Complex logic here
    complexOperation1(item);
    complexOperation2(item);
    complexOperation3(item);
});

// Good
list.stream()
    .map(this::processItem)
    .forEach(System.out::println);

private String processItem(String item) {
    // Complex logic extracted to method
    return complexOperation(item);
}
```

**Q18: How do you handle state in functional programming?**
**✅ A:**
```java
// Immutable state handling
public class ImmutableCounter {
    private final int count;
    
    private ImmutableCounter(int count) {
        this.count = count;
    }
    
    public ImmutableCounter increment() {
        return new ImmutableCounter(count + 1);
    }
    
    public static ImmutableCounter of(int initial) {
        return new ImmutableCounter(initial);
    }
}

// Usage with streams
Stream.iterate(ImmutableCounter.of(0), c -> c.increment())
      .limit(10)
      .forEach(c -> System.out.println(c.getCount()));
```

**Q19: Explain the concept of currying in functional programming**
**✅ A:** Currying transforms a function with multiple arguments into a sequence of functions:
```java
// Traditional approach
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// Curried version
Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;

// Usage
Function<Integer, Integer> add5 = curriedAdd.apply(5);
int result = add5.apply(3); // Returns 8
```

**Q20: How do you implement the Observer pattern using lambdas?**
**✅ A:**
```java
public class EventBus<T> {
    private List<Consumer<T>> listeners = new ArrayList<>();
    
    public void subscribe(Consumer<T> listener) {
        listeners.add(listener);
    }
    
    public void publish(T event) {
        listeners.forEach(listener -> listener.accept(event));
    }
}

// Usage
EventBus<String> bus = new EventBus<>();
bus.subscribe(msg -> System.out.println("Listener 1: " + msg));
bus.subscribe(msg -> System.out.println("Listener 2: " + msg));
bus.publish("Hello, Observers!");
```
