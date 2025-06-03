## 🧠 Programming Fundamentals

### 1. What are the differences between Primitive and Non-Primitive Types in Java?

**Answer:**  
Primitive types in Java are the basic data types that store simple values directly. They include `byte`, `short`, `int`, `long`, `float`, `double`, `char`, and `boolean`. These types are stored in stack memory and have a fixed size.

Non-primitive types (also known as reference types) include classes, interfaces, arrays, and enums. They store references (memory addresses) to objects located in heap memory. Examples include `String`, `ArrayList`, and custom classes.

**Example:**  
```java
int num = 10;               // Primitive type
String text = "Hello";      // Non-primitive type (reference)
```

**Real-world scenario:**  
When you want to store a simple number like age or count, primitive types are efficient. For complex data like a user profile, you use non-primitive types.

**Follow-up question:**  
*Q: What happens when you assign one non-primitive variable to another?*  
**A:** Both variables point to the same object in memory. Changes via one reference affect the other.

---

### 2. What are the four OOP Principles and how do they apply in Java?

**Answer:**  
The four pillars of Object-Oriented Programming (OOP) are:

- **Encapsulation:** Bundling data and methods that operate on data within one unit (class), controlling access via access modifiers.  
- **Abstraction:** Hiding complex implementation details and exposing only necessary parts.  
- **Inheritance:** Creating new classes from existing ones to promote code reuse.  
- **Polymorphism:** Ability of objects to take many forms, typically via method overriding or interface implementation.

**Example:**  
```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal myDog = new Dog();  // Polymorphism
        myDog.sound();             // Outputs: Dog barks
    }
}
```

**Real-world scenario:**  
In a banking app, you can have a base class `Account` and subclasses like `SavingsAccount` and `CheckingAccount` inheriting common properties and behaviors.

**Follow-up question:**  
*Q: How does Java enforce encapsulation?*  
**A:** Using access modifiers (`private`, `protected`, `public`) and getter/setter methods to control access.

---

### 3. How and when do you use the static keyword in Java?

**Answer:**  
The `static` keyword denotes that a member (variable or method) belongs to the class rather than any instance. Static members are shared among all instances.

**Use cases:**  
- Utility or helper methods (e.g., `Math.max()`).  
- Constants (`static final`).  
- Shared counters or caches.

**Example:**  
```java
class Counter {
    static int count = 0;

    Counter() {
        count++;
    }
}

public class Test {
    public static void main(String[] args) {
        new Counter();
        new Counter();
        System.out.println(Counter.count);  // Outputs: 2
    }
}
```

**Real-world scenario:**  
In a web app, you might use static methods in a utility class for common operations like validation.

**Follow-up question:**  
*Q: Can static methods access instance variables?*  
**A:** No, because instance variables belong to objects, and static methods belong to the class.

---

### 4. What is Platform Independence in Java and how does JVM facilitate it?

**Answer:**  
Java is platform-independent because compiled Java code (bytecode) can run on any device with a Java Virtual Machine (JVM). The JVM acts as an interpreter between bytecode and the underlying hardware.

**Example:**  
You compile Java code once:
```bash
javac MyProgram.java
```
and run it anywhere:
```bash
java MyProgram
```

The JVM translates bytecode into machine-specific instructions.

**Real-world scenario:**  
Write once, run anywhere: A Java desktop app can run on Windows, MacOS, or Linux without recompilation.

**Follow-up question:**  
*Q: What role does the Java Runtime Environment (JRE) play?*  
**A:** The JRE includes the JVM and standard libraries required to run Java programs.

---

### 5. What is the difference between Constructors and Methods in Java?

**Answer:**  
- **Constructor:** Special block called when an object is created. It has the same name as the class and no return type. Used to initialize objects.  
- **Method:** Defines behaviors or actions. Has a return type and can be called multiple times.

**Example:**  
```java
class Person {
    String name;

    // Constructor
    Person(String name) {
        this.name = name;
    }

    // Method
    void sayHello() {
        System.out.println("Hello, " + name);
    }
}
```

**Real-world scenario:**  
Constructors set initial values; methods perform actions like updating or displaying data.

**Follow-up question:**  
*Q: Can constructors be overloaded?*  
**A:** Yes, multiple constructors with different parameters allow flexible object creation.

---

### 6. What is the Singleton Pattern and how do you implement it in Java?

**Answer:**  
The Singleton pattern ensures a class has only one instance and provides a global point of access.

**Basic implementation:**  
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

**Thread-safe implementation:**  
```java
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
```

**Real-world scenario:**  
Use Singleton for logging, configuration settings, or database connections to avoid multiple instances.

**Follow-up question:**  
*Q: Why is double-checked locking used in the thread-safe Singleton?*  
**A:** To reduce synchronization overhead by locking only during the first instance creation.

---

### 7. What are the SOLID Principles?

**Answer:**  
SOLID is an acronym for five design principles to write maintainable and scalable code:

- **S**ingle Responsibility Principle: A class should have one reason to change.  
- **O**pen/Closed Principle: Software entities should be open for extension but closed for modification.  
- **L**iskov Substitution Principle: Subtypes must be substitutable for their base types.  
- **I**nterface Segregation Principle: Clients should not be forced to depend on interfaces they do not use.  
- **D**ependency Inversion Principle: Depend on abstractions, not on concrete implementations.

**Example (SRP):**  
Instead of one class handling user data and database operations, separate these concerns into different classes.

**Real-world scenario:**  
Applying SOLID helps avoid rigid, fragile codebases and facilitates easier testing and maintenance.

**Follow-up question:**  
*Q: How does the Open/Closed Principle improve software extensibility?*  
**A:** By allowing new functionality via inheritance or composition without modifying existing code.

---

### 8. What is the Reflection API in Java?

**Answer:**  
Reflection allows inspection and modification of classes, methods, fields, and constructors at runtime.

**Example:**  
```java
Class<?> clazz = Class.forName("java.lang.String");
Method[] methods = clazz.getDeclaredMethods();
for (Method method : methods) {
    System.out.println(method.getName());
}
```

**Real-world scenario:**  
Used in frameworks (like Spring) for dependency injection, serialization, or testing.

**Follow-up question:**  
*Q: What are the risks of using reflection?*  
**A:** It can break encapsulation, reduce performance, and cause security issues.

---

### 9. How does Garbage Collection work in Java?

**Answer:**  
Java automatically manages memory via Garbage Collection (GC), which reclaims memory used by objects no longer reachable.

**Key concepts:**  
- **Heap:** Memory area for objects.  
- **Reachability:** Objects not reachable from any references are eligible for GC.

**Example:**  
```java
String s = new String("Hello");
s = null;  // The String object is now eligible for GC
```

**Real-world scenario:**  
GC prevents memory leaks by cleaning unused objects, but improper use of references can cause leaks.

**Follow-up question:**  
*Q: Can you force garbage collection?*  
**A:** You can call `System.gc()`, but it's only a suggestion to the JVM.

---

### 10. What are Functional Interfaces and Lambdas in Java?

**Answer:**  
- **Functional Interface:** An interface with a single abstract method, used as the target for lambda expressions.  
- **Lambda Expressions:** Enable concise representation of anonymous functions.

**Example:**  
```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class Test {
    public static void main(String[] args) {
        Greeting greet = (name) -> System.out.println("Hello, " + name);
        greet.sayHello("Alice");
    }
}
```

**Real-world scenario:**  
Used in Java 8+ APIs like Streams for filtering, mapping, and processing collections.

**Follow-up question:**  
*Q: Can functional interfaces have default methods?*  
**A:** Yes, but only one abstract method.

---

### 11. What is the Java Memory Model (Stack, Heap, Metaspace)?

**Answer:**  
- **Stack:** Stores method call frames, local variables, and references. Memory is managed automatically.  
- **Heap:** Stores objects and arrays; shared among threads.  
- **Metaspace:** Stores class metadata (replaced PermGen in Java 8+).

**Real-world scenario:**  
Understanding memory areas helps optimize performance and troubleshoot memory leaks or `OutOfMemoryError`.

**Follow-up question:**  
*Q: What happens if the heap is full?*  
**A:** JVM triggers GC; if insufficient, an `OutOfMemoryError` is thrown.

---

### 12. What is the difference between final, finally, and finalize in Java?

**Answer:**  
- **final:** Keyword to declare constants, prevent method overriding, or inheritance.  
- **finally:** Block that always executes after try/catch, used for cleanup.  
- **finalize():** Method called by GC before object destruction (deprecated in Java 9+).

**Example:**  
```java
final int MAX = 100;

try {
    // code
} finally {
    System.out.println("Always executes");
}

@Override
protected void finalize() throws Throwable {
    System.out.println("Object is being garbage collected");
}
```

**Real-world scenario:**  
Use `finally` to close resources; avoid relying on `finalize()`.

**Follow-up question:**  
*Q: Why is finalize deprecated?*  
**A:** Unpredictable timing and performance issues; use `try-with-resources` or cleaners instead.

---

### 13. What is the Diamond Problem and how does Java handle it?

**Answer:**  
The Diamond Problem occurs in multiple inheritance when two parent classes have a method with the same signature, leading to ambiguity.

**Java solution:**  
Java does not support multiple class inheritance but allows multiple interfaces. Since Java 8, interfaces can have default methods, so ambiguity is resolved by overriding the method in the implementing class.

**Example:**  
```java
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}

class C implements A, B {
    @Override
    public void show() {
        A.super.show();  // Explicitly choosing A's method
    }
}
```

**Real-world scenario:**  
Avoid ambiguity by explicitly overriding methods when implementing multiple interfaces.

**Follow-up question:**  
*Q: Can classes inherit from multiple classes in Java?*  
**A:** No, only single inheritance is allowed to avoid this problem.

---

### 14. What is Pass-by-Value vs Pass-by-Reference in Java?

**Answer:**  
Java is strictly pass-by-value. For primitives, the actual value is passed. For objects, the reference (address) is passed by value.

**Example:**  
```java
void modify(int x) {
    x = 10;
}

void modifyObject(StringBuilder sb) {
    sb.append(" World");
}

int a = 5;
modify(a);  // a remains 5

StringBuilder sb = new StringBuilder("Hello");
modifyObject(sb);  // sb now contains "Hello World"
```

**Real-world scenario:**  
Understanding this helps avoid bugs when modifying objects inside methods.

**Follow-up question:**  
*Q: Can you change the reference of an object parameter inside a method?*  
**A:** No, changing the reference only affects the local copy.

---

### 15. What are Deep Copy and Shallow Copy?

**Answer:**  
- **Shallow Copy:** Copies object references, so both objects share the same nested objects.  
- **Deep Copy:** Copies objects recursively, creating independent copies.

**Example:**  
```java
class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow copy
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep copy
    protected Person deepClone() {
        return new Person(name, new Address(address.city));
    }
}
```

**Real-world scenario:**  
Use deep copy when you want to modify copies without affecting originals.

**Follow-up question:**  
*Q: Which copy is faster?*  
**A:** Shallow copy is faster but riskier if nested objects are mutable.

---

### 16. What is the Volatile Keyword in Java and when is it used?

**Answer:**  
`volatile` ensures visibility of changes to variables across threads. It prevents caching of variables in CPU registers or caches.

**Example:**  
```java
public class Flag {
    private volatile boolean flag = false;

    public void setFlag() {
        flag = true;
    }

    public boolean getFlag() {
        return flag;
    }
}
```

**Real-world scenario:**  
Use volatile for flags or state variables accessed by multiple threads where atomicity is not required.

**Follow-up question:**  
*Q: Does volatile guarantee atomicity?*  
**A:** No, it only guarantees visibility, not atomicity.

---

## Additional Core Questions

### 17. What is the difference between Method Overloading and Overriding?

**Answer:**  
- **Overloading:** Same method name, different parameters within the same class (compile-time polymorphism).  
- **Overriding:** Subclass provides specific implementation of a superclass method (runtime polymorphism).

**Example:**  
```java
class Calculator {
    int add(int a, int b) { return a + b; }                 // Overloading
    double add(double a, double b) { return a + b; }
}

class Animal {
    void sound() { System.out.println("Animal sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Dog barks"); }       // Overriding
}
```

**Follow-up question:**  
*Q: Can private methods be overridden?*  
**A:** No, private methods are not visible to subclasses.

---

### 18. What is the difference between Interface and Abstract Class with examples?

**Answer:**  
- **Interface:** Defines a contract with abstract methods (Java 8+ can have default/static methods). Supports multiple inheritance.  
- **Abstract Class:** Can have abstract and concrete methods, fields, constructors. Supports single inheritance.

**Example:**  
```java
interface Flyable {
    void fly();
}

abstract class Bird {
    abstract void sing();
    void breathe() { System.out.println("Breathing"); }
}
```

**Real-world scenario:**  
Use interfaces for capabilities (e.g., `Flyable`), abstract classes for common base behavior.

**Follow-up question:**  
*Q: Can interfaces have constructors?*  
**A:** No, interfaces cannot have constructors.

---

### 19. When to use Composition vs Inheritance?

**Answer:**  
- **Inheritance:** Use when "is-a" relationship exists and you want to reuse code.  
- **Composition:** Use when "has-a" relationship exists, favors flexible design by delegating responsibilities.

**Example:**  
```java
class Engine {
    void start() { System.out.println("Engine started"); }
}

class Car {
    private Engine engine = new Engine();  // Composition
    void start() {
        engine.start();
        System.out.println("Car started");
    }
}
```

**Follow-up question:**  
*Q: Why is composition preferred over inheritance?*  
**A:** It reduces tight coupling and promotes better encapsulation.

---

### 20. Explain the Class Loading Process in JVM.

**Answer:**  
Class loading involves three steps:

- **Loading:** JVM loads the `.class` file into memory.  
- **Linking:** Verifies, prepares (allocates memory for static variables), and optionally resolves symbolic references.  
- **Initialization:** Executes static initializers and static blocks.

**Follow-up question:**  
*Q: What is the role of the ClassLoader?*  
**A:** Loads classes dynamically at runtime, supports custom loading mechanisms.

---

### 21. What are Different Types of Polymorphism with examples?

**Answer:**  
- **Compile-time polymorphism:** Method overloading, operator overloading (not in Java).  
- **Runtime polymorphism:** Method overriding using inheritance and interfaces.

**Example:**  
Covered in earlier questions.

---

### 22. How do you create Immutable Objects in Java?

**Answer:**  
- Declare class as `final`.  
- Make all fields `private final`.  
- No setters.  
- Initialize fields via constructor.  
- Return copies for mutable fields.

**Example:**  
```java
final class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

---

### 23. What are the Categories of Design Patterns?

**Answer:**  
- **Creational:** Object creation patterns (Singleton, Factory).  
- **Structural:** Object composition patterns (Adapter, Decorator).  
- **Behavioral:** Object interaction patterns (Observer, Strategy).

---

### 24. What are Clean Code Principles and Practices?

**Answer:**  
Write readable, maintainable, and efficient code by following naming conventions, small functions, meaningful comments, and avoiding duplication.

---

### 25. What are Common Causes and Solutions for Java Memory Leaks?

**Answer:**  
- Unreleased references (static collections).  
- Listeners not removed.  
- Inner classes holding outer references.

**Solutions:** Use weak references, remove listeners, and avoid unnecessary static references.

---

### 26. What are Java 8+ Functional Programming Concepts?

**Answer:**  
Includes lambdas, streams, functional interfaces, method references, and optional.

**Example:**  
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream()
     .filter(name -> name.startsWith("A"))
     .forEach(System.out::println);
```

---

### 27. Advanced Clean Code Practices

**Interview Question:** How would you refactor a complex codebase to improve maintainability?

**Answer:** Apply clean code principles systematically with concrete patterns and practices.

**Implementation Example:**
```java
// Before refactoring
public class OrderProcessor {
    public void process(Order o) {
        if(o.getTotal() > 100) {
            if(o.getCustomer().getType().equals("PREMIUM")) {
                // ... 50 lines of complex logic
            }
        }
    }
}

// After refactoring
public class OrderProcessor {
    private static final double PREMIUM_THRESHOLD = 100.0;
    private final DiscountCalculator discountCalculator;
    private final ShippingService shippingService;

    public OrderResult process(Order order) {
        validateOrder(order);
        
        return OrderResult.builder()
            .withDiscount(calculateDiscount(order))
            .withShipping(calculateShipping(order))
            .build();
    }

    private void validateOrder(Order order) {
        Objects.requireNonNull(order, "Order cannot be null");
        if (order.getItems().isEmpty()) {
            throw new InvalidOrderException("Order must contain items");
        }
    }

    private Discount calculateDiscount(Order order) {
        return order.getTotal() > PREMIUM_THRESHOLD && order.getCustomer().isPremium()
            ? discountCalculator.calculatePremiumDiscount(order)
            : discountCalculator.calculateStandardDiscount(order);
    }
}
```

**Follow-up Q1:** How do you handle technical debt in a large project?

**Implementation:**
```java
public class TechnicalDebtTracker {
    @Deprecated(since = "2.0", forRemoval = true)
    public void legacyMethod() {
        // Track usage and plan removal
        MetricsCollector.trackDeprecatedUsage("legacyMethod");
        // Delegate to new implementation
        newImplementation();
    }

    // New implementation with better design
    public void newImplementation() {
        // Modern implementation
    }
}
```

### 28. Advanced Memory Management

**Interview Question:** How would you design a memory-efficient cache system?

**Answer:** Implement a cache with weak references and eviction policies.

**Implementation Example:**
```java
public class SmartCache<K, V> {
    private final Map<K, WeakReference<V>> cache;
    private final int maxSize;
    private final EvictionPolicy evictionPolicy;

    public V get(K key) {
        WeakReference<V> ref = cache.get(key);
        if (ref != null) {
            V value = ref.get();
            if (value != null) {
                evictionPolicy.recordAccess(key);
                return value;
            } else {
                cache.remove(key);
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (cache.size() >= maxSize) {
            evict();
        }
        cache.put(key, new WeakReference<>(value));
        evictionPolicy.recordInsertion(key);
    }

    private void evict() {
        K keyToEvict = evictionPolicy.selectVictim();
        if (keyToEvict != null) {
            cache.remove(keyToEvict);
        }
    }
}

interface EvictionPolicy {
    void recordAccess(Object key);
    void recordInsertion(Object key);
    Object selectVictim();
}
```

**Follow-up Q:** How would you handle concurrent access to this cache?

**Answer with Implementation:**
```java
public class ConcurrentSmartCache<K, V> {
    private final ConcurrentHashMap<K, WeakReference<V>> cache;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V get(K key) {
        lock.readLock().lock();
        try {
            WeakReference<V> ref = cache.get(key);
            return ref != null ? ref.get() : null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cleanupWeakReferences();
            cache.put(key, new WeakReference<>(value));
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void cleanupWeakReferences() {
        cache.entrySet().removeIf(entry -> 
            entry.getValue().get() == null);
    }
}
```

---

This structured Q&A format provides detailed explanations, real-world examples, Java code snippets, and follow-up questions to deepen understanding of core programming fundamentals.