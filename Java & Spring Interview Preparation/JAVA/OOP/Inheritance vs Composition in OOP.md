# 🔄 Inheritance vs Composition in OOP

**Inheritance** and **Composition** are two core techniques used in object-oriented programming to achieve code reuse and establish relationships between classes.

While both allow one class to use functionality defined in another, they differ fundamentally in structure, flexibility, and maintainability.

---

## 🧬 1. What is Inheritance?

Inheritance is an **"is-a"** relationship where a subclass **extends** a superclass, inheriting its fields and behaviors.

### ✅ Key Characteristics:
- Promotes **code reuse**
- Enables **polymorphism**
- Supports method overriding
- Tight coupling between parent and child

### 🔍 Example:
```java
class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}
```

---

## 🧱 2. What is Composition?

Composition models a **"has-a"** relationship where one class contains a reference to another class and **delegates** behavior to it.

### ✅ Key Characteristics:
- Promotes **loose coupling**
- More **flexible and maintainable**
- Supports runtime behavior changes
- Encourages reuse via delegation

### 🔍 Example:
```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine = new Engine();
    void startCar() {
        engine.start();
    }
}
```

---

## ⚔️ 3. Inheritance vs Composition: Side-by-Side Comparison

| Aspect                | Inheritance                          | Composition                             |
|-----------------------|--------------------------------------|------------------------------------------|
| Relationship Type     | "is-a"                               | "has-a"                                  |
| Coupling              | Tight                                | Loose                                    |
| Flexibility           | Rigid                                | Flexible (changeable at runtime)         |
| Reuse Mechanism       | Class hierarchy                      | Object delegation                        |
| Encapsulation         | Leaks internal structure             | Better encapsulation                     |
| Polymorphism          | Built-in via overriding              | Manual via interface delegation          |
| Testability           | Less isolated, harder to mock        | Better for mocking in unit tests         |
| Multiple Behaviors    | Harder to achieve                    | Easier through component substitution    |

---

## 🛠️ 4. When to Use Which?

| Situation                                          | Preferred Approach    |
|---------------------------------------------------|------------------------|
| Reuse functionality across similar entities        | Inheritance            |
| Replace or vary behavior at runtime                | Composition            |
| Decoupled, testable, and maintainable architecture | Composition            |
| Polymorphic hierarchies (e.g., UI widgets)         | Inheritance            |
| Avoiding fragile base class problem                | Composition            |

---

## 🚧 5. Pitfalls of Overusing Inheritance

- Fragile base class problem
- Inheritance breaking encapsulation
- Tight coupling makes code harder to refactor
- Difficulty modeling dynamic relationships
- Lack of reuse across unrelated classes

> 🔁 Prefer composition over inheritance for **maintainability** and **flexibility**, as advised by Effective Java (Joshua Bloch).

---

## 🧩 6. Advanced Concepts & Techniques

### 🔄 Liskov Substitution Principle (LSP) and Inheritance

The Liskov Substitution Principle states that objects of a superclass should be replaceable with objects of its subclasses without affecting program correctness.

```java
// Violation of LSP
class Rectangle {
    protected int width, height;
    public void setWidth(int w) { width = w; }
    public void setHeight(int h) { height = h; }
    public int getArea() { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) {
        width = w;
        height = w; // Breaking LSP - unexpected behavior
    }
    
    @Override
    public void setHeight(int h) {
        height = h;
        width = h; // Breaking LSP - unexpected behavior
    }
}
```

### 🧪 Dependency Injection & Composition

Composition becomes more powerful when combined with dependency injection:

```java
// Composition with dependency injection
class PaymentProcessor {
    private final PaymentGateway gateway;
    
    // Constructor injection
    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }
    
    public void processPayment(double amount) {
        gateway.pay(amount);
    }
}

// Multiple implementations can be injected
class StripeGateway implements PaymentGateway { /*...*/ }
class PayPalGateway implements PaymentGateway { /*...*/ }
```

### 🔄 Multiple Interface Implementation vs Multiple Inheritance

Java supports multiple interface implementation as a safer alternative to multiple inheritance:

```java
// Multiple interface implementation with composition
interface Swimmer { void swim(); }
interface Flyer { void fly(); }

class Duck implements Swimmer, Flyer {
    private final SwimmingBehavior swimming = new NaturalSwimming();
    private final FlyingBehavior flying = new WingFlying();
    
    @Override
    public void swim() { swimming.performSwim(); }
    
    @Override
    public void fly() { flying.performFly(); }
}
```

### 🏗️ Refactoring from Inheritance to Composition

```java
// Before: Inheritance-based
class TextBox extends Rectangle {
    private String text;
    
    public void setText(String text) { this.text = text; }
    public String getText() { return text; }
    // Inherits all Rectangle methods - some may not make sense
}

// After: Composition-based
class TextBox {
    private Rectangle shape = new Rectangle();
    private String text;
    
    public void setText(String text) { this.text = text; }
    public String getText() { return text; }
    public void setSize(int w, int h) { 
        shape.setWidth(w);
        shape.setHeight(h);
    }
    // Only expose methods that make sense
}
```

### 🧮 Strategy Pattern & Composition

The Strategy pattern relies heavily on composition to enable runtime behavior changes:

```java
interface SortStrategy {
    void sort(List<String> data);
}

class QuickSort implements SortStrategy {
    @Override
    public void sort(List<String> data) {
        // QuickSort implementation
    }
}

class MergeSort implements SortStrategy {
    @Override
    public void sort(List<String> data) {
        // MergeSort implementation
    }
}

class DataSorter {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void sortData(List<String> data) {
        strategy.sort(data);
    }
}
```

---

## 🌐 7. Real-World Applications

### 🎮 Game Development
- **Inheritance approach**: Entity → Character → Player/Enemy
- **Composition approach**: Entity with Components (Physics, Rendering, Input)
- Unity and Unreal Engine both shifted toward composition-based systems

### 📱 UI Frameworks
- **Inheritance**: UIComponent → Button/Panel/TextField
- **Composition**: Component with Layout, Style, and Behavior objects
- SwingBuilder and JavaFX use composition extensively

### 🔄 Testing & Mocking
```java
// Hard to test due to inheritance
class PaymentProcessor extends HttpClient {
    public boolean processPayment(Payment p) {
        // Uses inherited methods - hard to mock
        return post("/payments", p).isSuccessful();
    }
}

// Easily testable with composition
class PaymentProcessor {
    private final HttpClient client;
    
    PaymentProcessor(HttpClient client) {
        this.client = client;
    }
    
    public boolean processPayment(Payment p) {
        return client.post("/payments", p).isSuccessful();
    }
}
```

---

## 🧠 Interview Questions & Answers

### Basic Concepts

- ❓ **What is the difference between inheritance and composition?**
  > Inheritance is a structural relationship ("is-a"), while composition is a behavioral relationship ("has-a").

- ❓ **Why is composition generally preferred over inheritance?**
  > Because it avoids tight coupling, improves testability, and allows runtime behavior changes.

- ❓ **Can inheritance and composition be used together?**
  > Yes. For example, a subclass may use composition for one of its internal fields.

- ❓ **What is the fragile base class problem?**
  > A change in a superclass unintentionally breaks behavior in all its subclasses.

- ❓ **How does composition support better encapsulation?**
  > It hides implementation details better by containing rather than exposing internal class structure.

### Advanced Topics

- ❓ **Explain the Liskov Substitution Principle and its relationship to inheritance.**
  > LSP states that objects of a superclass should be replaceable with objects of its subclasses without affecting program correctness. Properly designed inheritance should respect this principle.

- ❓ **What is the diamond problem in multiple inheritance and how does Java avoid it?**
  > The diamond problem occurs when a class inherits from two classes that have a common parent, creating ambiguity. Java avoids it by not allowing multiple class inheritance, instead offering multiple interface implementation with default methods requiring explicit override resolution.

- ❓ **How would you refactor a deep inheritance hierarchy to use composition?**
  > 1) Identify behaviors in the hierarchy. 2) Create interfaces for these behaviors. 3) Extract behavior implementations to strategy classes. 4) Replace inheritance with delegation to these strategy implementations. 5) Use dependency injection to configure objects.

- ❓ **What is the difference between aggregation and composition in UML?**
  > Aggregation is a "has-a" relationship where the child can exist independently of the parent. Composition is a stronger "contains-a" relationship where the child cannot exist without the parent. In code, both are implemented similarly through object references.

- ❓ **How does the Decorator pattern use composition?**
  > The Decorator pattern wraps an object to add new behaviors at runtime through composition. It implements the same interface as the decorated object and holds a reference to it, delegating calls and adding functionality without inheritance.

### Implementation & Design

- ❓ **When would you still choose inheritance over composition?**
  > Inheritance makes sense when: 1) There's a true "is-a" relationship. 2) Subclasses are proper subtypes of the parent. 3) The parent class is designed for extension. 4) The inheritance hierarchy is shallow. 5) The API won't change frequently.

- ❓ **What role does composition play in the Strategy design pattern?**
  > In the Strategy pattern, composition allows algorithms (strategies) to be selected and changed at runtime. The context class contains a reference to a strategy interface, and concrete strategies can be swapped without changing the context's code.

- ❓ **How would you implement behavior extension using composition instead of inheritance?**
  > Use the Decorator pattern: create a wrapper class implementing the same interface as the original component, delegating to the wrapped component while adding new functionality.
  ```java
  interface Coffee { double getCost(); }
  class SimpleCoffee implements Coffee { public double getCost() { return 2.0; } }
  class MilkDecorator implements Coffee {
      private Coffee coffee;
      public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
      public double getCost() { return coffee.getCost() + 0.5; }
  }
  ```

- ❓ **What are the performance implications of favoring composition over inheritance?**
  > Composition may introduce slight overhead due to method delegation and additional object creation. However, this overhead is typically negligible compared to the maintainability benefits. In performance-critical sections, carefully designed inheritance might be justified after profiling.

- ❓ **How do you handle method name conflicts when implementing multiple interfaces in Java?**
  > When interfaces have methods with the same signature, the implementing class must provide a single implementation that satisfies both interfaces. If the default implementations differ, the class must explicitly override the method.

### Practical Application

- ❓ **How is composition used in modern frontend frameworks like React?**
  > React uses composition extensively through component composition. Components can contain other components, creating a component tree. This allows for better reusability compared to inheritance hierarchies.

- ❓ **How do Java collections use composition and inheritance together?**
  > The Java Collections Framework uses inheritance for type hierarchies (List, Set, Map interfaces) while using composition for implementation delegation. For example, `LinkedHashMap` extends `HashMap` but uses composition to maintain linked lists for ordering.

- ❓ **How would you design a system using composition to allow for behavior changes at runtime?**
  > Use the Strategy pattern with dependency injection. Define behavior interfaces, implement various strategies, and inject them into the main class. Provide setter methods to change strategies at runtime.
  ```java
  class ShippingService {
      private ShippingCalculator calculator;
      public void setCalculator(ShippingCalculator calc) { this.calculator = calc; }
      public double calculateShipping(Order order) { return calculator.calculate(order); }
  }
  ```

- ❓ **How does Spring framework use composition over inheritance?**
  > Spring heavily leverages composition through dependency injection. Beans are composed together rather than inheriting from each other. Spring AOP uses composition to add cross-cutting concerns without inheritance.

- ❓ **When working with legacy code, how would you incrementally move from inheritance to composition?**
  > 1) Start by identifying problematic inheritance relationships. 2) Extract behaviors into interfaces. 3) Create implementations that delegate to the original code. 4) Gradually replace inheritance with composition where it makes sense. 5) Use the Adapter pattern to maintain compatibility with existing code.

## 📚 8. Resources for Further Learning

- **Books**:
  - "Effective Java" by Joshua Bloch
  - "Design Patterns: Elements of Reusable Object-Oriented Software" by Gang of Four
  - "Head First Design Patterns" by Eric Freeman & Elisabeth Robson

- **Online Resources**:
  - [Refactoring Guru - Composition over Inheritance](https://refactoring.guru/design-patterns/composition-over-inheritance)
  - [Martin Fowler on Composition vs Inheritance](https://martinfowler.com/bliki/CompositionOverInheritance.html)
  - [Baeldung - Java Composition](https://www.baeldung.com/java-composition-aggregation-association)