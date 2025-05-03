# 🧱 Abstract Classes and Interfaces in Java

Java provides two primary tools to achieve **abstraction** and **polymorphism**: **abstract classes** and **interfaces**. They allow developers to define contracts and base structures for objects while enabling flexibility, extensibility, and testability.

---

## 📌 1. What is Abstraction?

**Abstraction** is the process of hiding internal implementation details and exposing only essential features to the outside world.

> Java achieves abstraction using:
> - Abstract Classes
> - Interfaces

### 🔑 Levels of Abstraction:
- **Implementation Abstraction**: Hiding how something works (methods)
- **Data Abstraction**: Hiding internal state (variables)
- **Control Abstraction**: Hiding flow control details (loops, conditionals)

---

## 🧭 2. Abstract Classes

An **abstract class** is a class that cannot be instantiated directly. It may include both abstract methods (without a body) and concrete methods (with a body).

### ✅ Key Characteristics:
- Can contain constructors, fields, concrete and abstract methods
- Can define access modifiers (public, protected, private)
- Supports single inheritance
- Ideal when classes share common structure/behavior but differ in implementation
- Can have instance initializer blocks and static blocks

### 🔍 Syntax Example:
```java
abstract class Animal {
    protected String name;
    
    // Constructor in abstract class
    public Animal(String name) {
        this.name = name;
    }
    
    abstract void makeSound();
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    void makeSound() {
        System.out.println(name + " barks");
    }
}
```

### 🛠 Advanced Abstract Class Features:
- Can declare `final` methods that subclasses cannot override
- Can have protected abstract methods forcing subclasses to implement but not exposing publicly
- Can have nested classes, interfaces, and enums

---

## 📄 3. Interfaces

An **interface** is a fully abstract reference type that specifies what a class must do but not how.

### ✅ Key Characteristics:
- All methods are implicitly public and abstract (until Java 7)
- From Java 8: allows **default** and **static** methods
- From Java 9: allows **private** methods
- Cannot have instance variables, only `public static final` constants
- Supports **multiple inheritance** of type via `implements`

### 🔍 Syntax Example:
```java
interface Movable {
    void move();
    
    // Default method (Java 8+)
    default void stop() {
        System.out.println("Default stopping mechanism");
    }
    
    // Static method (Java 8+)
    static int getDefaultSpeed() {
        return 10;
    }
    
    // Private method (Java 9+)
    private void log(String message) {
        System.out.println("Log: " + message);
    }
}

class Car implements Movable {
    public void move() {
        System.out.println("Car is moving");
    }
    
    // Override default method
    @Override
    public void stop() {
        System.out.println("Car stops with brakes");
    }
}
```

### 🛠 Advanced Interface Features:

#### Private Methods in Interfaces (Java 9+)
```java
interface Processable {
    default void process() {
        // Reuse private helper methods
        validate();
        doProcessing();
    }
    
    private void validate() {
        System.out.println("Validating...");
    }
    
    private void doProcessing() {
        System.out.println("Processing...");
    }
}
```

#### Sealed Interfaces (Java 17+)
```java
public sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();
}

final class Circle implements Shape {
    private double radius;
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Only permitted classes can implement a sealed interface
```

---

## ⚔️ 4. Abstract Class vs Interface

| Feature                | Abstract Class                   | Interface                               |
|------------------------|----------------------------------|------------------------------------------|
| Instantiation          | ❌ No                           | ❌ No                                    |
| Method Types           | Abstract + Concrete              | Abstract (Java 7), +default/static (Java 8+) |
| Constructors           | ✅ Yes                          | ❌ No                                    |
| Variables              | Instance or static              | `public static final` only               |
| Access Modifiers       | Any                             | Public only (for methods, fields)        |
| Inheritance            | Single                          | Multiple via implementation              |
| Use Case               | Base class with partial logic   | Behavioral contract or capability        |
| Multiple Inheritance   | ❌ No                           | ✅ Yes                                   |
| Instance Variables     | ✅ Yes                          | ❌ No (constants only)                   |
| State Management       | ✅ Yes                          | ❌ No                                    |
| Default Methods        | ❌ Not needed (use concrete)    | ✅ Yes (Java 8+)                         |
| Serialization          | Depends on implementation       | Typically stateless, easier to serialize |

---

## 🚀 5. Functional Interfaces (Java 8+)

A **functional interface** is an interface that contains exactly **one abstract method**. It is designed to work with **lambda expressions**.

### 🔍 Example:
```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

Greeting greet = (name) -> System.out.println("Hello, " + name);
greet.sayHello("Alice");
```

### 🧩 Advanced Functional Interface Features:

#### Functional Interface Composition
```java
// Combining functional interfaces
Function<String, Integer> parseLength = s -> s.length();
Function<Integer, Boolean> isEven = num -> num % 2 == 0;

// Compose functions
Function<String, Boolean> isEvenLength = isEven.compose(parseLength);
boolean result = isEvenLength.apply("Java"); // true because "Java" length is 4
```

#### Built-in Functional Interfaces

| Interface              | Method                 | Description                        |
|------------------------|------------------------|------------------------------------|
| `Predicate<T>`         | `boolean test(T t)`    | Tests if input meets condition     |
| `Function<T,R>`        | `R apply(T t)`         | Transforms T to R                  |
| `Consumer<T>`          | `void accept(T t)`     | Performs operation on input        |
| `Supplier<T>`          | `T get()`              | Supplies a value                   |
| `BiFunction<T,U,R>`    | `R apply(T t, U u)`    | Takes two inputs, returns result   |

---

## 🧠 6. Default Method Conflict Resolution

When a class implements multiple interfaces with conflicting default methods, Java uses specific rules to resolve them.

### Resolution Rules:
1. Classes always win over interfaces
2. Subinterfaces win over superinterfaces
3. If rules 1 & 2 don't resolve the conflict, explicitly override the method

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

// Must resolve the conflict explicitly
class C implements A, B {
    @Override
    public void show() {
        // Choose one or custom implementation
        A.super.show(); // Call A's implementation
        // or B.super.show();
    }
}
```

---

## 🔬 7. Real-World Use Cases

| Scenario                         | Use Abstract Class                      | Use Interface                             |
|----------------------------------|------------------------------------------|--------------------------------------------|
| Defining template with base logic | ✅ Yes                                   | ❌ No (unless default methods suffice)      |
| Cross-cutting capabilities       | ❌ Use inheritance                       | ✅ Example: `Comparable`, `Serializable`   |
| Multiple type inheritance        | ❌ Not possible                          | ✅ Via multiple interfaces                  |
| Framework base class             | ✅ Spring, JPA use abstract classes      | ✅ Use interfaces for configuration & APIs |

### Practical Examples:

#### 🔷 Spring Framework
- **Interfaces**: `Repository`, `Service`, `Component`
- **Abstract Classes**: `AbstractController`, `JdbcDaoSupport`

#### 🔷 Java Collections
- **Interfaces**: `List`, `Set`, `Map`
- **Abstract Classes**: `AbstractList`, `AbstractSet`

---

## 📊 8. Design Patterns Using Abstract Classes vs Interfaces

| Design Pattern        | Abstract Class Usage                  | Interface Usage                    |
|-----------------------|--------------------------------------|------------------------------------|
| Template Method       | ✅ Define skeleton algorithm          | ❌ Less suitable                   |
| Strategy              | ❌ Less flexible                      | ✅ Define family of algorithms     |
| Observer              | ✅ For basic Observable functionality | ✅ For Observer contract          |
| Adapter               | ✅ For partial implementations        | ✅ For client expectations         |
| Factory Method        | ✅ Creator with default behavior      | ✅ Product interface definition   |
| Command               | ❌ Less suitable                      | ✅ For command contract           |

---

## 🚨 9. Common Pitfalls and Considerations

### Performance Considerations:
- Interface method calls may require an extra indirection compared to abstract class methods
- Default methods add complexity to the JVM's method dispatch
- Multiple interface implementations can lead to harder-to-trace bugs

### Evolution Strategies:
- Adding methods to interfaces breaks all implementations
- Default methods provide a safer way to evolve interfaces
- Abstract classes can add methods with implementation more easily

### Interface Pollution:
- Creating too many single-method interfaces leads to fragmentation
- Consider using functional interfaces when appropriate

---

## 🧠 Interview Questions & Answers

- ❓ **When should you use an abstract class vs an interface?**
  > Use an abstract class when you need to share code among related classes. Use an interface when unrelated classes should adhere to a contract.

- ❓ **Can abstract classes have private or protected methods?**
  > Yes, abstract classes can define members with any access modifier.

- ❓ **What is the diamond problem and how does Java avoid it?**
  > Diamond problem arises in multiple inheritance. Java avoids it by disallowing multiple class inheritance and resolving interface conflicts via method resolution.

- ❓ **What happens if two interfaces have the same default method?**
  > The implementing class must explicitly override the method to resolve the conflict.

- ❓ **What are marker interfaces in Java?**
  > Interfaces with no methods. Example: `Serializable`, `Cloneable`.

- ❓ **What is the difference between `implements` and `extends`?**
  > `extends` is used for inheriting from a class or interface, while `implements` is used by a class to implement an interface.

- ❓ **Can interfaces have constructors or instance blocks?**
  > No, interfaces cannot have constructors or instance blocks since they cannot be instantiated.

- ❓ **What happens if a class implements multiple interfaces with same method signature?**
  > There is no conflict. The method is implemented once in the class.

- ❓ **Can you create an interface inside a class?**
  > Yes, interfaces can be nested and even declared as static or private inside classes.

- ❓ **Is it good practice to use default methods extensively in interfaces?**
  > No, default methods are best used sparingly to avoid breaking the design contract of interfaces.

- ❓ **Can an abstract class implement an interface?**
  > Yes, and it can leave the implementation of the interface methods to its subclasses.

- ❓ **What are sealed classes and interfaces? (Java 17+)**
  > Sealed types restrict which classes can inherit/implement them using the `permits` clause, enabling stronger control over class hierarchies.

- ❓ **What's the purpose of private methods in interfaces? (Java 9+)**
  > They enable code reuse within the interface without exposing the methods to implementing classes, helping to organize default method implementations.

- ❓ **Can you define variables in an interface?**
  > Only constants (`public static final`) are allowed. They are implicitly declared with these modifiers even if not specified.

- ❓ **How do you call a superinterface's default method from an implementing class?**
  > Using the syntax `InterfaceName.super.methodName()`.

- ❓ **When is an abstract class preferable over an interface with default methods?**
  > When shared state management is needed, when constructors are required, when access control (protected/private members) is needed, or when a cohesive family of classes should share implementation.

- ❓ **What's the difference between an abstract method and a default method?**
  > Abstract methods have no implementation and must be implemented by subclasses. Default methods have an implementation in the interface that subclasses can use, override, or call.

- ❓ **Can an interface extend multiple interfaces?**
  > Yes, interfaces can extend multiple interfaces, unlike classes.

- ❓ **What happens if a class inherits the same method from both a superclass and an interface?**
  > The class method from the superclass takes precedence over interface default methods.

- ❓ **How do functional interfaces differ from regular interfaces?**
  > Functional interfaces have exactly one abstract method, making them eligible for lambda expressions and method references.

- ❓ **Can an abstract class have final methods?**
  > Yes, final methods in an abstract class cannot be overridden by subclasses.

- ❓ **What's the advantage of the Template Method pattern using abstract classes?**
  > It allows defining the skeleton of an algorithm in a method, deferring some steps to subclasses while maintaining the overall algorithm structure.

- ❓ **How do you design for testability using interfaces?**
  > By programming to interfaces rather than implementations, you can easily substitute mock implementations for testing.
