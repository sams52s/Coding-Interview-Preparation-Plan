# 🧱 Object-Oriented Programming (OOP) Concepts in Java

Java is fundamentally an object-oriented programming language. It uses OOP principles to organize code into reusable, scalable, and maintainable components.

Java is fundamentally an object-oriented programming language. It uses OOP principles to organize code into reusable, scalable, and maintainable components.

---

## 🧍 Classes & Objects

A **class** defines a blueprint for creating objects, while an **object** is an instance of a class.

```java
class Car {
    String color;
    void drive() {
        System.out.println("Driving...");
    }
}

Car myCar = new Car();
myCar.color = "Red";
myCar.drive();
```

### 📊 Class vs Object Diagram

```text
Class: Car
+-------------+
| color       |
| drive()     |
+-------------+
       ↓
  Object: myCar
  color = "Red"
```

---

## 🏗️ Constructors & Overloading

Constructors initialize objects. Overloading allows multiple constructors with different parameters.

```java
class Book {
    String title;
    Book() { this.title = "Untitled"; }
    Book(String title) { this.title = title; }
}
```

---

## 🧮 Static & Instance Members

- **Static members** are shared across all instances.
- **Instance members** are unique to each object.

```java
class Counter {
    static int count = 0;
    int id;
    Counter() {
        count++;
        id = count;
    }
}
```

---

## 🧬 Inheritance, `super`, and Method Overriding

Inheritance allows a class to acquire properties and behaviors from another.

```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    @Override
    void sound() { System.out.println("Bark"); }
}
```

Use `super()` to call the parent constructor or method.

---

## 🎭 Polymorphism

### Compile-time (Method Overloading)

```java
void greet() {}
void greet(String name) {}
```

### Runtime (Method Overriding)

```java
Animal a = new Dog();
a.sound(); // Bark
```

---

## 🎯 Abstraction & Abstract Classes

Abstract classes cannot be instantiated and may contain abstract methods.

```java
abstract class Shape {
    abstract void draw();
}
class Circle extends Shape {
    void draw() { System.out.println("Drawing Circle"); }
}
```

---

## 🔌 Interfaces & Functional Interfaces

Interfaces declare methods without implementations.

```java
interface Printable {
    void print();
}

@FunctionalInterface
interface Operation {
    int apply(int a, int b);
}
```

---

## 📦 Encapsulation

Encapsulation is achieved by keeping fields private and providing access via getters/setters.

```java
class User {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

---

## 🔐 Access Modifiers

| Modifier    | Class | Package | Subclass | World |
| ----------- | ----- | ------- | -------- | ----- |
| `public`    | ✅     | ✅       | ✅        | ✅     |
| `protected` | ✅     | ✅       | ✅        | ❌     |
| (default)   | ✅     | ✅       | ❌        | ❌     |
| `private`   | ✅     | ❌       | ❌        | ❌     |

---

## 📦 Packages

Packages organize related classes.

```java
package com.example;
import java.util.*;
```

---

## 🧩 Inner & Anonymous Classes

### Inner Class

```java
class Outer {
    class Inner {
        void display() { System.out.println("Inner"); }
    }
}
```

### Anonymous Class

```java
Runnable r = new Runnable() {
    public void run() { System.out.println("Running"); }
};
```

---

## 🎌 Enum Classes

Enums are used for a fixed set of constants.

```java
enum Day { MONDAY, TUESDAY, WEDNESDAY }
```

---

## 🧪 Object Cloning

Cloning creates a copy of an object.

```java
class Person implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

---

## 🧹 Garbage Collection

Java handles memory cleanup automatically. Objects with no references are eligible for GC.

---

## 🚫 Final Keyword

| Use            | Effect               |
| -------------- | -------------------- |
| `final` var    | Cannot reassign      |
| `final` method | Cannot override      |
| `final` class  | Cannot be subclassed |

---

## 🧼 Finalize Method (Deprecated)

Called by GC before destroying object.

```java
protected void finalize() throws Throwable {
    System.out.println("Finalizing...");
}
```

---

## 📍 this & super Keywords

- `this` refers to the current object
- `super` refers to the parent class

```java
this.name = name;
super.show();
```

---

## 🔁 Object Class Methods

| Method       | Purpose                        |
| ------------ | ------------------------------ |
| `toString()` | Returns string representation  |
| `equals()`   | Checks logical equality        |
| `hashCode()` | Returns hash code              |
| `clone()`    | Clones object                  |
| `finalize()` | Cleanup before GC (deprecated) |

---

---

## 🧩 Composition vs Inheritance

| Feature     | Inheritance                    | Composition                    |
| ----------- | ------------------------------ | ------------------------------ |
| Definition  | IS-A relationship              | HAS-A relationship             |
| Flexibility | Less flexible (tight coupling) | More flexible (loose coupling) |
| Reusability | Code reuse via extension       | Code reuse via delegation      |
| Example Use | `extends` a superclass         | Using other classes as fields  |

### ✅ Best Practice

- Prefer **composition** over inheritance when possible.

### 🧪 Example

```java
class Engine {
    void start() { System.out.println("Engine started"); }
}
class Car {
    private Engine engine = new Engine();
    void drive() { engine.start(); }
}
```



---

## 🧠 SOLID Principles (OOP Design Best Practices)

The **SOLID** principles are five core design guidelines for writing maintainable and scalable software:

### 🧍 S – Single Responsibility Principle (SRP)

> A class should have only one reason to change.

### 🔓 O – Open/Closed Principle (OCP)

> Software entities should be open for extension, but closed for modification.

### 🧩 L – Liskov Substitution Principle (LSP)

> Subtypes must be substitutable for their base types.

### 🔄 I – Interface Segregation Principle (ISP)

> Clients should not be forced to depend on methods they do not use.

### 🧰 D – Dependency Inversion Principle (DIP)

> Depend on abstractions, not on concrete implementations.

### ✅ Example (DIP with Interface)

```java
interface MessageService {
    void send(String msg);
}
class EmailService implements MessageService {
    public void send(String msg) {
        System.out.println("Email: " + msg);
    }
}
class Notification {
    private MessageService service;
    Notification(MessageService service) { this.service = service; }
    void notifyUser() { service.send("Hello"); }
}
```



---

## 🧰 Common Design Patterns in Java

Design patterns are proven solutions to common software design problems. Below are essential OOP-based patterns:

---

### 🏭 Factory Pattern

Provides an interface for creating objects, but allows subclasses to alter the type of objects that will be created.

```java
interface Shape {
    void draw();
}
class Circle implements Shape {
    public void draw() { System.out.println("Circle drawn"); }
}
class Square implements Shape {
    public void draw() { System.out.println("Square drawn"); }
}
class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equals("circle")) return new Circle();
        if (type.equals("square")) return new Square();
        return null;
    }
}
```

**Usage:**
```java
Shape shape = new ShapeFactory().getShape("circle");
shape.draw();
```

---

### 🎭 Strategy Pattern

Enables selecting an algorithm’s behavior at runtime.

```java
interface PaymentStrategy {
    void pay(int amount);
}
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid with credit card: " + amount);
    }
}
class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid with PayPal: " + amount);
    }
}
class Checkout {
    private PaymentStrategy strategy;
    public Checkout(PaymentStrategy strategy) { this.strategy = strategy; }
    public void completePurchase(int amount) {
        strategy.pay(amount);
    }
}
```

**Usage:**
```java
Checkout checkout = new Checkout(new CreditCardPayment());
checkout.completePurchase(500);
```

---

### 🔒 Singleton Pattern

Ensures a class has only one instance and provides a global point of access to it.

```java
class Singleton {
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

**Usage:**
```java
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
System.out.println(s1 == s2); // true
```

---

### 👁️ Observer Pattern

Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically.

```java
interface Observer {
    void update(String message);
}
class Subscriber implements Observer {
    private String name;
    public Subscriber(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
class Publisher {
    private List<Observer> observers = new ArrayList<>();
    public void subscribe(Observer o) { observers.add(o); }
    public void notifyAll(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}
```

**Usage:**
```java
Publisher publisher = new Publisher();
publisher.subscribe(new Subscriber("A"));
publisher.notifyAll("New article published!");
```

---

### 🧱 Builder Pattern

Separates the construction of a complex object from its representation.

```java
class User {
    private String name, email;
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
    }
    public static class Builder {
        private String name, email;
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public User build() { return new User(this); }
    }
}
```

**Usage:**
```java
User user = new User.Builder().setName("Alice").setEmail("a@mail.com").build();
```

---

### 🔌 Adapter Pattern

Allows incompatible interfaces to work together.

```java
interface MediaPlayer {
    void play(String file);
}
class AudioPlayer implements MediaPlayer {
    public void play(String file) {
        System.out.println("Playing audio: " + file);
    }
}
class VideoPlayer {
    void playVideo(String filename) {
        System.out.println("Playing video: " + filename);
    }
}
class VideoAdapter implements MediaPlayer {
    private VideoPlayer player;
    public VideoAdapter(VideoPlayer player) { this.player = player; }
    public void play(String file) { player.playVideo(file); }
}
```

**Usage:**
```java
MediaPlayer player = new VideoAdapter(new VideoPlayer());
player.play("movie.mp4");
```

---

### 🎨 Decorator Pattern

Adds additional behavior to objects dynamically.

```java
interface Coffee {
    String getDescription();
}
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
}
class MilkDecorator implements Coffee {
    private Coffee coffee;
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
}
```

**Usage:**
```java
Coffee coffee = new MilkDecorator(new SimpleCoffee());
System.out.println(coffee.getDescription());
```

---

### 🧬 Prototype Pattern

Creates clones of objects without depending on their concrete classes.

```java
abstract class Prototype implements Cloneable {
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }
}
class Record extends Prototype {
    int id;
    Record(int id) { this.id = id; }
}
```

**Usage:**
```java
Record original = new Record(1);
Record copy = (Record) original.clone();
```

---

### 🕵️ Proxy Pattern

Provides a placeholder for another object to control access.

```java
interface Image {
    void display();
}
class RealImage implements Image {
    private String filename;
    RealImage(String filename) { this.filename = filename; loadFromDisk(); }
    void loadFromDisk() { System.out.println("Loading " + filename); }
    public void display() { System.out.println("Displaying " + filename); }
}
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    ProxyImage(String filename) { this.filename = filename; }
    public void display() {
        if (realImage == null) realImage = new RealImage(filename);
        realImage.display();
    }
}
```

**Usage:**
```java
Image image = new ProxyImage("photo.jpg");
image.display(); // Loads and displays
image.display(); // Only displays
```

---

## 📘 Summary Cheatsheet

| Concept          | Key Elements                                                                          |
| ---------------- | ------------------------------------------------------------------------------------- |
| Class/Object     | Blueprint vs. Instance                                                                |
| Encapsulation    | private fields, getters/setters                                                       |
| Inheritance      | `extends`, `super`                                                                    |
| Polymorphism     | Overriding, Overloading                                                               |
| Abstraction      | `abstract` classes, `interface`                                                       |
| Composition      | Object collaboration                                                                  |
| SOLID Principles | SRP, OCP, LSP, ISP, DIP                                                               |
| Design Patterns  | Factory, Strategy, Singleton, Observer, Builder, Adapter, Decorator, Prototype, Proxy |
| Keyword Use      | `this`, `super`, `final`                                                              |

---

## 🧪 Practice Questions
- Implement a simple class hierarchy using inheritance.
- Create a class that demonstrates method overloading and overriding.
- Design a class using the Singleton pattern.
- Implement a factory pattern for creating different types of vehicles.
- Create an interface and implement it in multiple classes.

---

## 📚 Additional Resources
- [Java Documentation](https://docs.oracle.com/javase/8/docs/api/)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/0596007124)
- [Java Design Patterns](https://www.journaldev.com/java-design-patterns)