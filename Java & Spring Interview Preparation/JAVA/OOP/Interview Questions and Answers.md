# 🧠 Object-Oriented Programming in Java - Interview Questions & Answers

This file contains carefully curated **interview questions and answers** on OOP in Java, ranging from **beginner** to **advanced** levels. Each section builds on the previous to prepare you for coding interviews, technical discussions, and system design rounds.

---

## 📘 Table of Contents

- [Beginner Level](#beginner-level)
- [Intermediate Level](#intermediate-level)
- [Advanced Level](#advanced-level)
- [Design Patterns in OOP](#design-patterns-in-oop)
- [Scenario-Based Questions](#scenario-based-questions)
- [Code Output Questions](#code-output-questions)
- [Quick Cheatsheet](#quick-cheatsheet)

---

## ✅ Beginner Level

### Q1. What are the main principles of OOP?
**A:** Encapsulation, Inheritance, Polymorphism, and Abstraction.

### Q2. What is a class in Java?
**A:** A blueprint for creating objects. It defines fields (variables) and methods (functions).

### Q3. What is an object?
**A:** An instance of a class that contains state (variables) and behavior (methods).

### Q4. What is encapsulation?
**A:** Wrapping data and methods together into a single unit. Achieved using private variables and public getters/setters.

### Q5. Difference between method overloading and overriding?
**A:**
- **Overloading**: Same method name, different parameters (compile-time).
- **Overriding**: Subclass redefines a method of superclass (runtime).

---

## 🌀 Intermediate Level

### Q6. What is inheritance and how is it used in Java?
**A:** A mechanism to acquire properties of a superclass. Promotes code reuse via `extends` keyword.

### Q7. What is polymorphism?
**A:** Ability of an object to take many forms. Includes compile-time (overloading) and runtime (overriding).

### Q8. What is abstraction?
**A:** Hiding implementation details and showing only functionality. Achieved using abstract classes or interfaces.

### Q9. What is the difference between `this` and `super`?
**A:** `this` refers to current class instance, `super` refers to the immediate superclass.

### Q10. What is the use of `final` keyword in OOP?
**A:** Prevents modification: final variables cannot be reassigned, methods cannot be overridden, classes cannot be extended.

---

## 🔬 Advanced Level

### Q11. Interface vs Abstract Class?
| Feature | Abstract Class | Interface |
|--------|----------------|-----------|
| Multiple Inheritance | No | Yes |
| Constructors | Yes | No |
| Fields | Instance and static | static & final only (pre-Java 8) |
| Method Types | Concrete and abstract | Abstract, default, static (Java 8+) |

### Q12. What is composition and why is it preferred over inheritance?
**A:** Composition involves using references to other classes as fields. It provides more flexibility and avoids the tight coupling of inheritance.

### Q13. What are SOLID principles?
- **S**: Single Responsibility Principle
- **O**: Open/Closed Principle
- **L**: Liskov Substitution Principle
- **I**: Interface Segregation Principle
- **D**: Dependency Inversion Principle

### Q14. What is object slicing in Java?
**A:** Not applicable in Java (present in C++). In Java, all object references are polymorphic.

### Q15. What is the significance of the `Object` class?
**A:** All classes inherit from `java.lang.Object`. It defines methods like `equals()`, `hashCode()`, `toString()` etc.

---

## 🧩 Design Patterns in OOP

### Q16. Explain the Singleton Pattern.
**A:** Restricts class to a single instance. Ensures controlled access via a static `getInstance()` method.

### Q17. What is the Strategy Pattern?
**A:** Enables selecting an algorithm at runtime using interfaces.

### Q18. Difference between Factory and Abstract Factory Pattern?
**A:**
- **Factory**: Creates one product.
- **Abstract Factory**: Creates families of related products.

### Q19. What is the Builder Pattern?
**A:** Used to construct complex objects step by step. Separates construction from representation.

### Q20. What is the Observer Pattern?
**A:** Establishes a one-to-many dependency. When one object changes state, all dependents are notified.

---

## 📎 Scenario-Based Questions

### Q21. How would you model a school system using OOP principles?
**A:** A comprehensive school system design would include:
- **Class Hierarchy**: Abstract `Person` class extended by `Student`, `Teacher`, and `Staff` classes
- **Interfaces**: `Payable` (for `Teacher` and `Staff`), `Enrollable` (for `Student`), `Gradable` (for courses/assignments)
- **Association**: `Course` has-many `Student` and has-one `Teacher`
- **Composition**: `Department` composed of multiple `Teacher` objects
- **Encapsulation**: Private fields like student grades, teacher salary with appropriate getters/setters
- **Polymorphism**: Method `calculatePayment()` implemented differently for `Teacher` (based on courses) and `Staff` (fixed)

**UML Overview**:

### Q22. How do you design an online shopping cart system?
Use composition for `Cart` having a list of `Items`, `Product` classes, interfaces for `DiscountStrategy`, etc.

---

## 🧪 Code Output Questions

### Q23. What is the output?
```java
class A {
    void show() { System.out.println("A"); }
}
class B extends A {
    void show() { System.out.println("B"); }
}
public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.show();
    }
}
```
**A:** Output is `B` (runtime polymorphism).

### Q24. What happens?
```java
abstract class Animal {
    abstract void makeSound();
}
class Dog extends Animal {
    void makeSound() { System.out.println("Bark"); }
}
```
**A:** Valid. `Dog` must implement `makeSound()`.

---

## 🧾 Quick Cheatsheet

| Concept | Keyword | Notes |
|--------|---------|-------|
| Encapsulation | private + getters/setters | Protects data |
| Inheritance | extends | Promotes reuse |
| Abstraction | abstract / interface | Hides implementation |
| Polymorphism | method overloading/overriding | Flexible behavior |
| Design Principle | SOLID | Clean and scalable design |

---

> ✅ Keep practicing! Try implementing real-world systems using OOP. Combine patterns and principles for optimal design.
> 📚 For more resources, check out [Java Design Patterns](https://refactoring.guru/design-patterns/java) and [Effective Java](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/) by Joshua Bloch.