# 🔁 Lambda Expressions & Functional Programming in Java

Lambda expressions, introduced in **Java 8**, enable a **functional programming style** in Java. They provide a concise and powerful way to represent **anonymous functions**, improving readability and supporting operations on collections and streams.

---

## 🚀 1. What is a Lambda Expression?

A **lambda expression** is a shorthand for defining an **anonymous function** that can be passed around as a value.

### ✅ Syntax:
```java
(parameters) -> expression
```
Or:
```java
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

A **functional interface** has **only one abstract method**.

### ✅ Common Functional Interfaces (in `java.util.function`):
| Interface     | Abstract Method    | Description                            |
|---------------|---------------------|----------------------------------------|
| `Predicate<T>`| `test(T t)`         | Returns a boolean                      |
| `Function<T,R>`| `apply(T t)`       | Transforms a value                     |
| `Consumer<T>` | `accept(T t)`       | Performs an action                     |
| `Supplier<T>` | `get()`             | Supplies a value                       |
| `UnaryOperator<T>`| `apply(T t)`    | Same input and output type             |
| `BinaryOperator<T>`| `apply(T,T)`   | Two inputs of same type, one output    |

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

### ✅ First-Class Functions
- Functions can be treated as variables, passed as arguments, and returned from methods.

### ✅ Higher-Order Functions
- Accept functions as parameters or return functions

### ✅ Pure Functions
- Always produce the same output for the same input and have no side effects

### ✅ Immutability
- Avoids changing state; creates new values instead

### ✅ Declarative Style
- Focuses on *what* to do rather than *how* to do it (e.g., `stream().filter().map()`)

---

## 🧰 5. Benefits of Lambda and Functional Style
- Reduced boilerplate code
- Improved code readability
- Enables **Streams API** and parallel operations
- Encourages immutability and side-effect-free coding
- Better suited for multi-core/multi-threaded environments

---

## 🔄 6. Advanced Lambda Techniques

### ✅ Method References
Four types of method references:
```java
// Static method reference
Function<String, Integer> parser = Integer::parseInt;

// Instance method reference on specific object
Consumer<String> printer = System.out::println;

// Instance method reference on arbitrary object of specific type
Function<String, Integer> lengthFunc = String::length;

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

### ✅ Function Composition
Combining multiple functions:
```java
Function<String, String> toLowerCase = String::toLowerCase;
Function<String, String> trim = String::trim;
Function<String, String> trimAndLowercase = toLowerCase.compose(trim);

// Or using andThen
Function<String, String> trimAndLowercaseAlt = trim.andThen(toLowerCase);
```

### ✅ Currying and Partial Application
```java
// Currying example
Function<Integer, Function<Integer, Integer>> curryAdd = 
    x -> y -> x + y;
    
Function<Integer, Integer> add5 = curryAdd.apply(5);
int result = add5.apply(3); // Returns 8
```

### ✅ Custom Functional Interfaces
```java
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

TriFunction<Integer, Integer, Integer, Integer> sum3 = 
    (a, b, c) -> a + b + c;
```

## 🌊 7. Advanced Stream Operations

### ✅ Collectors in Depth
```java
// Grouping
Map<Department, List<Employee>> byDept = 
    employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

// Partitioning
Map<Boolean, List<Employee>> seniorJunior = 
    employees.stream().collect(Collectors.partitioningBy(e -> e.getYears() > 5));

// Custom collectors
String names = employees.stream()
    .map(Employee::getName)
    .collect(Collectors.joining(", ", "Names: ", "."));
```

### ✅ Parallel Streams
```java
long count = numbers.parallelStream()
    .filter(n -> n % 2 == 0)
    .count();
```

### ✅ Performance Considerations
- Not all operations benefit from parallelization
- Small data sets may perform worse in parallel
- Stateful lambdas can cause race conditions
- Fork/Join pool is shared across all parallel streams

## 📦 8. Optional as a Functional Approach

```java
// Returning Optional
public Optional<User> findUserById(String id) {
    // ...
}

// Working with Optional
userOptional
    .filter(user -> user.isActive())
    .map(User::getAddress)
    .flatMap(address -> Optional.ofNullable(address.getZipCode()))
    .orElse("Unknown");
```

---

## 🧠 Interview Questions & Answers

- ❓ **What is a lambda expression in Java?**
  > A lambda is an anonymous function that can be treated as an object and passed as a parameter to methods.

- ❓ **What is a functional interface?**
  > An interface with exactly one abstract method. It can have multiple default or static methods.

- ❓ **What package contains the core functional interfaces?**
  > `java.util.function`

- ❓ **Can lambda expressions access variables outside their scope?**
  > Yes, but only **effectively final** variables can be accessed.

- ❓ **What is method reference and how is it related to lambdas?**
  > A shorter syntax to refer to existing methods using `ClassName::methodName`, used as an alternative to lambdas.

- ❓ **What are some common functional interfaces used with streams?**
  > `Predicate`, `Function`, `Consumer`, `Supplier`, `BinaryOperator`, etc.

- ❓ **What is the difference between lambda and anonymous class?**
  > Lambdas are more concise and do not carry `this` pointer from outer scope; anonymous classes are more verbose.

- ❓ **How does functional programming help in concurrency?**
  > By minimizing shared mutable state and promoting stateless computation.

- ❓ **Can you return a lambda from a method?**
  > Yes, if the method returns a functional interface type.

- ❓ **What is the role of `@FunctionalInterface` annotation?**
  > It enforces that the interface has only one abstract method and can be used as a lambda target.

- ❓ **What is the difference between map and flatMap in Stream API?**
  > `map` transforms elements one-to-one, while `flatMap` transforms each element to a stream and then flattens all streams into one.

- ❓ **Explain variable capture in lambda expressions**
  > Lambdas can access local variables from the enclosing scope if they are effectively final (not modified after initialization).

- ❓ **What are the advantages and disadvantages of parallel streams?**
  > Advantages: Potential performance improvement on large data sets with computationally intensive operations.
  > Disadvantages: Overhead for small data sets, potential race conditions with stateful operations, shared ForkJoinPool.

- ❓ **How would you debug lambda expressions?**
  > Add logging within the lambda, extract it to a named method, or use debugging tools like peeking in streams: `.peek(System.out::println)`.

- ❓ **What is the difference between intermediate and terminal operations in streams?**
  > Intermediate operations (like `map`, `filter`) return a new stream and are lazy. Terminal operations (like `collect`, `forEach`) produce a result and are eager.

- ❓ **What is a pure function and why is it important in functional programming?**
  > A pure function always produces the same output for the same input and has no side effects. It's important for predictability, testability, and parallel execution.

- ❓ **How do you handle exceptions in lambda expressions?**
  > By wrapping the lambda in a try-catch block, creating a wrapper function that handles exceptions, or using specialized libraries like Vavr.

- ❓ **Explain function composition and provide an example**
  > Function composition combines multiple functions to form a new function. Example: `Function<String, Integer> parse = String::trim.andThen(Integer::parseInt)`.

- ❓ **What is the difference between Predicate.and(), Predicate.or(), and Predicate.negate()?**
  > They allow combining predicates: `and()` for logical AND, `or()` for logical OR, and `negate()` for logical NOT.

- ❓ **How would you implement a custom functional interface and when would you need one?**
  > Create an interface with a single abstract method and annotate with `@FunctionalInterface`. You'd need one when existing interfaces don't match your method signature requirements.

- ❓ **What are method references and what types are available in Java?**
  > Method references are shorthand for lambdas that call a single method. Types: static methods, instance methods on particular objects, instance methods on any object of a type, and constructors.

- ❓ **How do Optional, Stream, and functional programming relate to each other?**
  > They're all part of Java's functional approach. Optional provides a functional way to handle nullable values, Stream offers functional-style operations on collections, and they both leverage functional interfaces and lambdas.

- ❓ **What is currying in functional programming and how can you implement it in Java?**
  > Currying transforms a function with multiple arguments into a sequence of functions each with a single argument. In Java: `Function<A, Function<B, C>>`.
