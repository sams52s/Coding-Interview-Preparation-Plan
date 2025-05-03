# 🧱 SOLID Principles in Java OOP

**SOLID** is an acronym representing five key design principles for writing maintainable and scalable object-oriented software. These principles help developers avoid tightly coupled code and promote better software architecture.

> ✅ Coined by Robert C. Martin (Uncle Bob), SOLID principles are widely adopted in clean code and agile development practices.

---

## 📌 1. S — Single Responsibility Principle (SRP)

**A class should have only one reason to change.**

Each class should only be responsible for one part of the functionality.

### 🔍 Example:
```java
class InvoicePrinter {
    void print(Invoice invoice) {
        // printing logic
    }
}

class InvoicePersistence {
    void save(Invoice invoice) {
        // persistence logic
    }
}
```
> 🔑 SRP avoids "God classes" that do too much.

---

## 📌 2. O — Open/Closed Principle (OCP)

**Software entities should be open for extension but closed for modification.**

You should be able to add new functionality without changing existing code.

### 🔍 Example:
```java
interface Shape {
    double area();
}

class Rectangle implements Shape {
    public double area() { return 10 * 5; }
}

class Circle implements Shape {
    public double area() { return Math.PI * 5 * 5; }
}

class AreaCalculator {
    double totalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }
}
```
> 🔑 Leverages **polymorphism** to avoid modifying existing logic.

---

## 📌 3. L — Liskov Substitution Principle (LSP)

**Subtypes must be substitutable for their base types.**

Derived classes must not break the behavior of the base class.

### 🔍 Example:
```java
class Bird {
    void fly() {}
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}
```
> ❌ Violates LSP. Refactor by separating behaviors:
```java
interface Bird { void layEgg(); }
interface FlyingBird extends Bird { void fly(); }
```

---

## 📌 4. I — Interface Segregation Principle (ISP)

**Clients should not be forced to depend on interfaces they do not use.**

Split large interfaces into smaller, role-specific ones.

### 🔍 Example:
```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {}
    public void eat() { throw new UnsupportedOperationException(); }
}
```
> ❌ Violates ISP. Better:
```java
interface Workable { void work(); }
interface Eatable { void eat(); }
```

---

## 📌 5. D — Dependency Inversion Principle (DIP)

**Depend on abstractions, not concretions.**

High-level modules should not depend on low-level modules; both should depend on abstractions.

### 🔍 Example:
```java
class LightBulb {
    void turnOn() {}
}

class Switch {
    private LightBulb bulb = new LightBulb();
    void operate() {
        bulb.turnOn();
    }
}
```
> ❌ Tightly coupled. Better with abstraction:
```java
interface Switchable { void turnOn(); void turnOff(); }

class LightBulb implements Switchable {
    public void turnOn() { System.out.println("Bulb turned on"); }
    public void turnOff() { System.out.println("Bulb turned off"); }
}

class Fan implements Switchable {
    public void turnOn() { System.out.println("Fan started"); }
    public void turnOff() { System.out.println("Fan stopped"); }
}

class Switch {
    private Switchable device;
    Switch(Switchable device) {
        this.device = device;
    }
    void operate() {
        device.turnOn();
    }
}
```

> 🔑 This enables **dependency injection** and follows DIP by depending on abstractions.

---

## 🔄 SOLID Principles in Practice

### Real-world Applications

- **SRP**: Service layer in Spring applications - each service handles specific business logic
- **OCP**: JPA inheritance strategies - extend functionality without modifying base classes
- **LSP**: Collections framework in Java - ArrayList can be used wherever List is expected
- **ISP**: Spring's specialized annotations (@RestController vs @Controller)
- **DIP**: Spring's dependency injection framework - autowiring by interface

### 💻 SOLID in Modern Java Development

#### Java 8+ Features Supporting SOLID
```java
// Functional interfaces support ISP
@FunctionalInterface
interface Validator<T> {
    boolean validate(T t);
}

// Composing behaviors with default methods (OCP)
interface Logger {
    default void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
}
```

#### Spring Framework Implementation
```java
@Service
public class OrderService {
    private final OrderRepository repository;
    private final PaymentGateway paymentGateway;
    
    // Constructor injection (DIP)
    @Autowired
    public OrderService(OrderRepository repository, 
                       PaymentGateway paymentGateway) {
        this.repository = repository;
        this.paymentGateway = paymentGateway;
    }
}
```

### ⚠️ Common Anti-patterns

| Principle | Anti-pattern | Better Approach |
|-----------|--------------|----------------|
| SRP | God Class | Break into smaller, focused classes |
| OCP | Switch Statements | Replace with polymorphism |
| LSP | Partial Implementations | Use composition over inheritance |
| ISP | Fat Interfaces | Split into role-specific interfaces |
| DIP | New Operator Overuse | Use DI containers or factories |

---

## 🧠 Interview Questions & Answers

- ❓ **What are SOLID principles?**
  > SOLID is a mnemonic acronym for five principles that improve object-oriented design and maintainability: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, and Dependency Inversion.

- ❓ **Why is the Single Responsibility Principle important?**
  > It makes code easier to understand, test, and maintain by ensuring each class has one reason to change. This leads to smaller classes with higher cohesion, reduced coupling, and better testability.

- ❓ **How does OCP relate to design patterns?**
  > It encourages the use of abstraction and patterns like Strategy, Template Method, and Decorator. For instance, Strategy pattern allows adding new algorithms without changing existing code, while Decorator adds functionality without modifying core classes.

- ❓ **How can LSP violations be identified?**
  > When a subclass changes or breaks the behavior expected of its superclass. Signs include: throwing unexpected exceptions, changing return types, strengthening preconditions, weakening postconditions, or requiring special client code to handle subtypes differently.

- ❓ **What does Interface Segregation achieve?**
  > It reduces coupling and ensures that classes only implement methods they actually need, preventing "implementation pollution" and making the system more modular and flexible.

- ❓ **How does DIP benefit testing?**
  > It enables dependency injection and makes unit testing easier through mocks. By depending on abstractions, components can be tested in isolation with test doubles (mocks, stubs) substituted for real implementations.

- ❓ **Can SOLID be applied to functional programming?**
  > Yes, the principles are adaptable to functional and modular paradigms. In functional programming, SRP maps to pure functions, OCP relates to higher-order functions, LSP translates to type substitutability, ISP maps to focused function signatures, and DIP aligns with dependency injection through function parameters.

- ❓ **How would you refactor a violation of the Open/Closed Principle?**
  > 1. Identify the part that varies
  > 2. Extract it into an interface
  > 3. Implement the interface for each variation
  > 4. Use polymorphism to select the appropriate implementation
  > Example: Replace if/switch statements with strategy pattern implementations.

- ❓ **What's the relationship between SOLID and Clean Architecture?**
  > SOLID principles form the foundation for Clean Architecture. The Dependency Inversion Principle specifically enables the dependency rule in Clean Architecture, where high-level policy doesn't depend on low-level details.

- ❓ **How do SOLID principles improve maintainability?**
  > They lead to code that's loosely coupled, highly cohesive, and focused on specific concerns, making it easier to understand, extend, and modify. This reduces technical debt and makes large-scale refactoring less necessary.

- ❓ **How would you implement Dependency Inversion in a legacy application?**
  > 1. Identify high-level modules depending directly on low-level modules
  > 2. Extract interfaces from the low-level modules
  > 3. Update high-level modules to depend on these interfaces
  > 4. Introduce a DI container or factory if needed
  > 5. Consider using the Adapter pattern for third-party dependencies

- ❓ **What are practical challenges in applying SOLID principles?**
  > - Overengineering simple problems
  > - Learning curve for junior developers
  > - Increased number of classes and interfaces
  > - Finding the right level of abstraction
  > - Balancing SOLID with performance considerations
  > - Legacy code integration

## 📚 Further Reading

- **Clean Code** by Robert C. Martin
- **Effective Java** by Joshua Bloch
- **Head First Design Patterns** by Eric Freeman & Elisabeth Robson
- **Refactoring: Improving the Design of Existing Code** by Martin Fowler

---

## 🛠️ SOLID Principles Cheat Sheet

| Principle | Key Focus | Code Smell | Design Pattern Example |
|-----------|-----------|------------|------------------------|
| SRP | Cohesion | God class | Facade |
| OCP | Extension | Switch statements | Strategy, Template Method |
| LSP | Substitutability | Type checking | Factory Method |
| ISP | Interface cohesion | Unused methods | Adapter |
| DIP | Abstraction | Direct instantiation | Dependency Injection |