## ☕ Core Java Concepts — Interview Q&A

---

### HashMap internal working & collision handling

**Interviewer:** How does a HashMap work internally?  
**Candidate:** It uses an array of buckets, where each key's hashCode determines the bucket index. If multiple keys map to the same bucket (collision), they are stored as a linked list or tree (Java 8+).

---

### equals() and hashCode()

**Interviewer:** Why are equals() and hashCode() important?  
**Candidate:** equals() defines logical equality; hashCode() ensures that equal objects return the same hash value. They're crucial for using objects as keys in hash-based collections.

---

### Wrapper classes

**Interviewer:** What are wrapper classes in Java?  
**Candidate:** They are object representations of primitive types, like Integer for int. They allow primitives to be used in collections and support features like nullability and generics.

---

### String pool

**Interviewer:** What is the Java String pool?  
**Candidate:** It's a special memory region where Java stores unique string literals to save memory and improve performance through string interning.

---

### Generics (methods, classes)

**Interviewer:** Why do we use generics?  
**Candidate:** Generics provide type safety and reduce the need for type casting, improving code readability and maintainability.

---

### ClassLoader types

**Interviewer:** What are the types of ClassLoaders?  
**Candidate:** Bootstrap, Extension, and Application ClassLoaders — they load core classes, JDK extensions, and application-level classes, respectively.

---

### Marker interfaces

**Interviewer:** What is a marker interface?  
**Candidate:** It's an interface with no methods, used to signal metadata to the JVM or frameworks, like Serializable or Cloneable.

---

### Serialization & deserialization

**Interviewer:** How does serialization work in Java?  
**Candidate:** It converts an object into a byte stream for storage or transmission; deserialization reconstructs the object. This requires the class to implement Serializable.

---

### Optional class

**Interviewer:** What is the purpose of Optional?  
**Candidate:** It represents a value that might be present or absent, helping to avoid NullPointerExceptions and improving API design.

---

### Java 8 Stream API

**Interviewer:** What does the Stream API offer?  
**Candidate:** It enables functional-style operations on collections, such as map, filter, reduce, and supports parallel execution.

---

### Parallel streams vs streams

**Interviewer:** What's the difference between parallel streams and regular streams?  
**Candidate:** Parallel streams split tasks across multiple threads to improve performance on multicore systems, but they add overhead and require thread-safety.

---

## 📚 Collections Framework — Interview Q&A

---

### List, Set, Map differences

**Interviewer:** How do List, Set, and Map differ?  
**Candidate:** List is ordered and allows duplicates; Set is unordered and unique; Map stores key-value pairs.

---

### ArrayList vs LinkedList vs Vector

**Interviewer:** When would you use each?  
**Candidate:** ArrayList offers fast random access, LinkedList excels at frequent insertions/removals, Vector is synchronized for thread-safe operations.

---

### HashSet vs TreeSet vs LinkedHashSet

**Interviewer:** How do the Set types compare?  
**Candidate:** HashSet is unordered, TreeSet is sorted, LinkedHashSet maintains insertion order.

---

### HashMap vs ConcurrentHashMap vs Hashtable

**Interviewer:** What's the difference?  
**Candidate:** HashMap is non-thread-safe, ConcurrentHashMap supports concurrent access, Hashtable is legacy and fully synchronized.

---

### PriorityQueue, Deque

**Interviewer:** What's the use of PriorityQueue?  
**Candidate:** It allows efficient retrieval of the smallest/largest element based on a comparator or natural ordering.

**Interviewer:** What about Deque?  
**Candidate:** Deque supports insertion and removal from both ends, functioning as both a stack and queue.

---

### Iterator vs ListIterator

**Interviewer:** What's the difference?  
**Candidate:** Iterator allows forward-only traversal; ListIterator allows bidirectional traversal and element modification.

---

### Double brace initialization

**Interviewer:** What is double brace initialization?  
**Candidate:** It's a concise way to initialize collections but creates anonymous inner classes, which may lead to memory leaks.

---

### Collections sorting & Comparable interface

**Interviewer:** How do you sort collections?  
**Candidate:** Using Collections.sort() or stream().sorted(), relying on Comparable for natural order or Comparator for custom order.

---

## 🛡 Exception Handling — Interview Q&A

---

### Checked vs unchecked exceptions

**Interviewer:** What's the difference?  
**Candidate:** Checked exceptions are verified at compile-time; unchecked (RuntimeException) are not, representing programming errors.

---

### try-catch-finally

**Interviewer:** How does try-catch-finally work?  
**Candidate:** try wraps code that may throw exceptions; catch handles them; finally executes code regardless of exceptions, typically cleanup.

---

### throw vs throws

**Interviewer:** How do throw and throws differ?  
**Candidate:** throw is used to explicitly throw an exception; throws declares exceptions a method might propagate.

---

### Custom exceptions

**Interviewer:** How do you create a custom exception?  
**Candidate:** Extend Exception (for checked) or RuntimeException (for unchecked), optionally adding custom fields or methods.

---

### Exception propagation

**Interviewer:** What is exception propagation?  
**Candidate:** When an exception isn't caught in a method, it propagates up the call stack until caught or causes program termination.

---

### Throwable base class

**Interviewer:** What is the Throwable class?  
**Candidate:** It's the superclass for all exceptions and errors in Java, forming the base of Java's error-handling hierarchy.

---

## 📊 Java Version Features — Interview Q&A

---

### Java 8 Features

**Interviewer:** What are the key features introduced in Java 8?  
**Candidate:** Java 8 brought several revolutionary features:
1. **Lambda Expressions:**
   - Enabled functional programming
   - Syntax: `(parameters) -> { body }`
   - Example: `list.forEach(item -> System.out.println(item))`

2. **Stream API:**
   - Supports functional-style operations on collections
   - Enables parallel processing
   - Key operations: map, filter, reduce, collect

3. **Optional:**
   - Handles null values gracefully
   - Reduces NullPointerExceptions
   - Methods: map(), flatMap(), orElse()

4. **Default Methods:**
   - Allows adding new methods to interfaces without breaking implementations
   - Uses 'default' keyword
   - Example: Collection interface's forEach()

5. **Method References:**
   - Shorthand for lambda expressions
   - Types: Static, Instance, Constructor
   - Example: `String::length`

6. **New Date/Time API:**
   - Thread-safe
   - Immutable classes
   - Better timezone handling
   - Key classes: LocalDate, LocalTime, LocalDateTime

---

### Java 9-11 Features

**Interviewer:** What significant changes came with Java 9 through 11?  
**Candidate:** Several important features were introduced:

1. **Java 9:**
   - Module System (JPMS)
   - JShell REPL
   - Collection Factory Methods
   - Private interface methods
   - Stream API improvements

2. **Java 10:**
   - Local Variable Type Inference (var)
   - Parallel Full GC for G1
   - Application Class-Data Sharing

3. **Java 11 (LTS):**
   - HTTP Client API (standardized)
   - String methods: isBlank(), lines(), strip()
   - Lambda parameter var syntax
   - Single-file source code execution

---

### Java 12-17 Features

**Interviewer:** What are the major features from Java 12 to 17?  
**Candidate:** These versions brought significant modernization:

1. **Java 14-15:**
   - Records (preview -> final)
   - Pattern Matching for instanceof
   - Text Blocks
   - Helpful NullPointerExceptions

2. **Java 16:**
   - Records (final)
   - Pattern Matching for instanceof (final)
   - Unix-Domain Socket Channels

3. **Java 17 (LTS):**
   - Sealed Classes (final)
   - Pattern Matching for switch (preview)
   - Foreign Function & Memory API (incubator)
   - Context-Specific Deserialization Filters

---

### Java 18-21 Features

**Interviewer:** What are the latest features in Java 18-21?  
**Candidate:** Recent versions focus on modern programming patterns:

1. **Java 18-20:**
   - UTF-8 by Default
   - Simple Web Server
   - Vector API (incubator)
   - Pattern Matching for switch (preview)

2. **Java 21 (LTS):**
   - Virtual Threads
   - Structured Concurrency
   - String Templates (preview)
   - Record Patterns
   - Sequenced Collections
   - Pattern Matching for switch (final)

---

## 📦 Popular Java Libraries & Tools — Interview Q&A

**Interviewer:** Let's discuss common Java tools and libraries in detail.

### Build Tools

**Interviewer:** Compare Maven and Gradle.  
**Candidate:**
- **Maven:**
  - XML-based configuration
  - Predefined build lifecycle
  - Extensive plugin ecosystem
  - Better for standardized projects

- **Gradle:**
  - Groovy/Kotlin DSL
  - More flexible build scripts
  - Better performance
  - Better for complex, custom builds

### Testing Frameworks

**Interviewer:** What testing tools do you use and why?  
**Candidate:**
1. **JUnit 5:**
   - Standard testing framework
   - Annotations: @Test, @BeforeEach, @ParameterizedTest
   - Assertions and assumptions

2. **Mockito:**
   - Mocking framework
   - Verifies behavior
   - Stub method returns

3. **AssertJ:**
   - Fluent assertions
   - Better readability
   - Rich assertion library

4. **Testcontainers:**
   - Integration testing with containers
   - Database testing
   - Microservices testing

### Logging Frameworks

**Interviewer:** Explain logging solutions in Java.  
**Candidate:**
1. **SLF4J:**
   - Facade pattern
   - Abstract logging dependencies
   - Provides logging API

2. **Log4j2:**
   - High performance
   - Asynchronous logging
   - Various appenders
   - Configuration flexibility

### Development Tools

**Interviewer:** What development tools enhance productivity?  
**Candidate:**
1. **Lombok:**
   - Reduces boilerplate
   - @Data, @Builder annotations
   - Clean POJO creation

2. **Spring Tools:**
   - Spring Boot DevTools
   - Spring Initializr
   - Spring Boot Actuator

3. **Code Quality:**
   - SonarQube
   - CheckStyle
   - PMD
   - SpotBugs

### Documentation

**Interviewer:** How do you handle documentation?  
**Candidate:**
1. **Javadoc:**
   - Standard documentation
   - API documentation
   - IDE integration

2. **Swagger/OpenAPI:**
   - REST API documentation
   - Interactive documentation
   - Client generation
