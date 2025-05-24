# Java 8 (March 2014) - The Functional Revolution 🚀

## 🎯 Feature Impact Analysis

| Feature                | Impact Level | Learning Curve | Performance Impact | Migration Complexity |
|-----------------------|--------------|----------------|-------------------|---------------------|
| Lambda Expressions    | High         | Medium         | Positive         | Medium              |
| Stream API           | High         | High           | Variable         | Medium              |
| CompletableFuture    | High         | High           | Positive         | Complex             |
| Optional<T>          | Medium       | Low            | Minimal          | Easy                |
| New Date/Time API    | High         | Medium         | Positive         | Medium              |
| Default Methods      | Medium       | Low            | None             | Easy                |
| Method References    | Medium       | Low            | Positive         | Easy                |

## ✨ Overview
Java 8 was a landmark release introducing functional programming concepts, streamlining API design, and modernizing Java’s ability to handle data, time, and parallelism. It marked a shift toward a more expressive, declarative style.

---

## 🚀 Detailed Feature Breakdown

### 🔹 Lambda Expressions

**Definition**: Anonymous functions that provide a clear and concise way to represent single-method interface implementations.

**Why it matters**:
- Reduces boilerplate code.
- Makes functional-style operations possible.
- Enables parallel stream processing.

**Examples**:
```java
// Basic example: iterate list
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println(name));

// Comparator using lambda
Comparator<String> compareByLength = (s1, s2) -> s1.length() - s2.length();

// Event handling in JavaFX
button.setOnAction(event -> System.out.println("Button clicked!"));

// Custom thread
new Thread(() -> System.out.println("Running in separate thread")).start();
```

**Best Practices**:
- Keep lambdas small and focused.
- Extract complex lambdas into separate methods.
- Use type inference when possible.
- Consider method references for simple cases.

**Common Pitfalls**:
- Overusing lambdas for complex logic.
- Capturing mutable variables.
- Ignoring exception handling.

**Real-world use case**:
Used extensively in event handlers, stream transformations, and APIs like CompletableFuture.

 <!-- TODO -->
<!-- **See also**: [Functional Programming Patterns](../FunctionalProgramming/Patterns.md)  -->


---

### 🔹 Stream API
**Definition**: A new abstraction to process sequences of elements in a declarative, functional style.

**Why it matters**:
- Simplifies data transformations.
- Supports parallel execution.
- Reduces the need for verbose loops.

**Example**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> evenNumbers = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .collect(Collectors.toList());
```

**Advanced Operations**:
```java
// Complex stream operations
List<Employee> employees = // ...
Map<Department, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

// Parallel stream with custom collector
List<Transaction> transactions = // ...
Summary summary = transactions.parallelStream()
    .collect(Collector.of(
        Summary::new,
        Summary::accumulate,
        Summary::combine
    ));
```

**Performance Considerations**:
- When to use parallel streams
- Memory consumption patterns
- Proper use of terminal operations

**Stream Operation Categories**:
1. Intermediate Operations:
   - filter, map, flatMap, distinct, sorted, peek, limit, skip
2. Terminal Operations:
   - forEach, collect, reduce, count, min/max, anyMatch/allMatch/noneMatch
3. Short-circuiting Operations:
   - findFirst, findAny, limit

**Real-world use case**:
Transforming database result sets, aggregating statistics, or processing collections.

**See also**: [Functional Programming Patterns](../FunctionalProgramming/Patterns.md) [TODO]

---

### 🔹 CompletableFuture API
**Definition**: Enhanced Future API for asynchronous programming.

**Why it matters**:
- Simplifies writing asynchronous, non-blocking code.
- Supports chaining and composition of asynchronous tasks.
- Provides robust error handling and timeout management.

**Examples**:
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenApply(String::toUpperCase);

// Combining multiple futures
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Price: ");
CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> 42.0);
CompletableFuture<String> combined = future1.thenCombine(
    future2,
    (str, price) -> str + price
);
```

**Chaining and Composition**:
- `thenCompose` for flat-mapping futures.
- `thenCombine` to combine results of two futures.

**Error Handling**:
- `exceptionally` to provide fallback on exceptions.
- `handle` to process result or exception.

**Timeout Management**:
- `orTimeout(long timeout, TimeUnit unit)` to complete exceptionally if timeout exceeded.
- `completeOnTimeout(T value, long timeout, TimeUnit unit)` to complete with a default value on timeout.

**Example with chaining, error handling, and timeout**:
```java
CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(this::processData)
    .exceptionally(ex -> handleError(ex))
    .orTimeout(3, TimeUnit.SECONDS)
    .thenAccept(result -> System.out.println("Final result: " + result));
```

**See also**: [Java Concurrency Documentation](../Concurrency/README.md) [TODO]

---

### 🔹 Functional Interfaces (`@FunctionalInterface`)
**Definition**: Interfaces with a single abstract method, used as the target type for lambda expressions and method references.

**Why it matters**:
- Enables custom behavior injection.
- Powers Java 8 features like Streams, CompletableFuture.

**Example**:
```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}
```

**Real-world use case**:
Custom callbacks, strategies, or filtering logic.

---

### 🔹 Optional<T>
**Definition**: A container object that may or may not contain a non-null value.

**Why it matters**:
- Reduces NullPointerExceptions.
- Encourages explicit absence handling.

**Example**:
```java
Optional<String> optional = Optional.ofNullable(null);
System.out.println(optional.orElse("Default Value"));
```

**Real-world use case**:
Wrapping method return values where null might otherwise be returned.

---

### 🔹 New Date and Time API (`java.time`)
**Definition**: A modern date-time API replacing the flawed `Date` and `Calendar` classes.

**Why it matters**:
- Immutable and thread-safe.
- Clear API design.
- Supports time zones, durations, periods.

**Example**:
```java
LocalDate today = LocalDate.now();
System.out.println("Today's date: " + today);
```

**Real-world use case**:
Financial applications, scheduling systems, time zone calculations.

**See also**: [Date-Time Best Practices](../DateTime/BestPractices.md) [TODO]

---

### 🔹 Default and Static Methods in Interfaces
**Definition**: Interfaces can now have concrete methods.

**Why it matters**:
- Allows API evolution without breaking existing implementations.
- Adds utility methods directly in interfaces.

**Example**:
```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting");
    }
}
```

**Real-world use case**:
Adding default behaviors in libraries and frameworks.

---

### 🔹 Nashorn JavaScript Engine
**Definition**: Lightweight JavaScript runtime integrated into the JVM.

**Why it matters**:
- Enables embedding JavaScript in Java applications.
- Useful for scripting, templating, or dynamic behavior.

**Example**:
```java
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from Nashorn');");
```

**Real-world use case**:
Scripting within enterprise apps or dynamic rule engines.

---

### 🔹 Method References and Constructor References
**Definition**: Shorthand for lambda expressions that invoke existing methods or constructors.

**Why it matters**:
- Makes code more concise.
- Improves readability.

**Example**:
```java
names.forEach(System.out::println);
```

**Real-world use case**:
Passing method references in streams, event handlers.

---

## 📚 JEP References
- [JEP 126](https://openjdk.org/jeps/126): Lambda Expressions and Virtual Extension Methods
- [JEP 174](https://openjdk.org/jeps/174): Nashorn JavaScript Engine
- [JEP 118](https://openjdk.org/jeps/118): Access to Parameter Names at Runtime
- [JEP 104](https://openjdk.org/jeps/104): Type Annotations

---

## 🔗 Official Resources
- [Java 8 Oracle Docs](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [OpenJDK Java 8](https://openjdk.org/projects/jdk8/)

---


## 📋 Feature Navigation Table

| Category                | Feature                              | Link                                  |
|-------------------------|-------------------------------------|---------------------------------------|
| Functional Programming  | Lambda Expressions                  | [Jump to section](#-lambda-expressions) |
| Functional Programming  | Method References                   | [Jump to section](#-method-references-and-constructor-references) |
| Functional Programming  | Functional Interfaces               | [Jump to section](#-functional-interfaces-functionalinterface) |
| Data APIs               | Stream API                          | [Jump to section](#-stream-api) |
| Data APIs               | Optional                            | [Jump to section](#-optionalt) |
| Data APIs               | New Date and Time API               | [Jump to section](#-new-date-and-time-api-javatime) |
| Concurrency             | CompletableFuture API               | [Jump to section](#-completablefuture-api) |
| Language Enhancements   | Default and Static Methods          | [Jump to section](#-default-and-static-methods-in-interfaces) |
| Language Enhancements   | Nashorn JavaScript Engine           | [Jump to section](#-nashorn-javascript-engine) |

---

## 🏁 Summary
Java 8 transformed the Java landscape by embracing functional programming, improving the data and concurrency APIs, and paving the way for modern, expressive Java applications. Understanding these features is crucial for every modern Java developer.
