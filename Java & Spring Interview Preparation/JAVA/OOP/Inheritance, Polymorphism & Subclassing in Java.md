# 🧬 Inheritance, Polymorphism & Subclassing in Java

Java supports **inheritance** and **polymorphism**, two pillars of object-oriented programming (OOP). These enable **code reusability**, **hierarchical class modeling**, and **dynamic method resolution**, making applications easier to maintain and scale.

---

## 🔗 1. Inheritance in Java

Inheritance is a mechanism by which one class (the **child** or **subclass**) inherits the fields and methods of another class (the **parent** or **superclass**).

### ✅ Syntax:
```java
class Superclass {
    // fields and methods
}

class Subclass extends Superclass {
    // inherits and may override methods
}
```

### 🧪 Example:
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
```

> 🧠 Inheritance creates an **IS-A** relationship. `Dog IS-A Animal`.

---

## 🧭 2. Types of Inheritance in Java

| Type         | Description                                               | Supported? |
|--------------|-----------------------------------------------------------|------------|
| Single       | One subclass inherits from one superclass                | ✅ Yes     |
| Multilevel   | A subclass inherits from another subclass                | ✅ Yes     |
| Hierarchical | Multiple subclasses inherit from a common superclass     | ✅ Yes     |
| Multiple     | One class inherits from multiple classes                 | ❌ No (via classes) |
| Hybrid       | Combination of above types                               | ❌ Indirect via interfaces |

> ⚠️ Java avoids multiple inheritance of **classes** to prevent ambiguity (e.g., diamond problem). It supports multiple inheritance via **interfaces**.

---

## 📥 3. The `super` Keyword

The `super` keyword refers to the **immediate superclass**. It is used for:
- Accessing superclass methods and fields
- Invoking superclass constructor (`super()`)

```java
class Animal {
    String name = "Animal";
    void speak() {
        System.out.println("Speaking...");
    }
}

class Dog extends Animal {
    String name = "Dog";
    void display() {
        System.out.println(super.name); // Animal
        super.speak();                  // Speaking...
    }
}
```

> 📌 `super()` must be the first line in a subclass constructor.

---

## 🧬 4. Polymorphism in Java

Polymorphism allows one interface to be used for a general class of actions. It manifests in two forms:

### 🔹 Compile-Time Polymorphism (Method Overloading)
- Same method name, different parameter lists
```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```

### 🔹 Run-Time Polymorphism (Method Overriding)
- Method is redefined in the subclass
```java
Animal a = new Dog();
a.sound(); // Dog barks (runtime decision)
```

> ✅ Achieved via **dynamic method dispatch** using method overriding.

---

## 👥 5. Subclassing in Java

Subclassing involves defining a new class that **extends** an existing class.

### 📌 Rules and Access:
| Modifier     | Subclass Access     |
|--------------|---------------------|
| public       | ✅ Yes              |
| protected    | ✅ Yes              |
| default      | ✅ Same package only|
| private      | ❌ No               |

### Other Key Points:
- Use `@Override` to ensure correct method overriding.
- Use `final` to prevent further overriding.
- Use `super` to access hidden or overridden methods.

---

## 📚 Advanced Concepts

### 🔄 Object Slicing (via Polymorphism)
```java
Animal a = new Dog();
a.sound(); // Dog’s method is called
```
Only the **overridden methods** are dynamically bound — fields are accessed based on the reference type.

### 🧰 Abstract Classes vs Inheritance
- Abstract classes define **common behavior** but may include **abstract methods** (no body)
- Cannot instantiate abstract classes directly
- Used when subclasses share structure but differ in behavior

### 🌐 Interfaces and Multiple Inheritance
- Java avoids multiple class inheritance but allows a class to implement multiple interfaces
```java
interface Swimmable { void swim(); }
interface Runnable { void run(); }

class Dog implements Swimmable, Runnable {
    public void swim() {}
    public void run() {}
}
```

### 🔀 Covariant Return Types
Java allows an overriding method to return a subclass of the original method's return type:

```java
class Animal {
    Animal reproduce() { return new Animal(); }
}

class Dog extends Animal {
    @Override
    Dog reproduce() { return new Dog(); } // Covariant return type
}
```

### 🎭 Method Hiding vs Method Overriding
- **Method Overriding**: Applies to instance methods
- **Method Hiding**: Occurs when a static method in a subclass has the same signature as a static method in the superclass

```java
class Parent {
    static void display() { System.out.println("Parent"); }
    void show() { System.out.println("Parent show"); }
}

class Child extends Parent {
    static void display() { System.out.println("Child"); } // Method hiding
    @Override
    void show() { System.out.println("Child show"); }      // Method overriding
}
```

### ↔️ Composition vs Inheritance
- **Inheritance**: "IS-A" relationship (Dog IS-A Animal)
- **Composition**: "HAS-A" relationship (Car HAS-A Engine)

```java
// Inheritance approach
class Animal { void eat() { /* code */ } }
class Dog extends Animal { }

// Composition approach
class Engine { void start() { /* code */ } }
class Car {
    private Engine engine = new Engine();
    void startCar() { engine.start(); }
}
```

### 📏 Liskov Substitution Principle
A fundamental principle stating that objects of a superclass should be replaceable with objects of its subclasses without affecting program correctness.

### 🔒 Sealed Classes (Java 17+)
Restrict which classes can inherit from a class:

```java
public sealed class Shape permits Circle, Rectangle, Triangle {
    // Only Circle, Rectangle, and Triangle can extend Shape
}

public final class Circle extends Shape { } // Must be final, sealed, or non-sealed
```

### ⚙️ Default Methods in Interfaces
Interfaces can provide implementation for methods, enabling evolution while maintaining backward compatibility:

```java
interface Vehicle {
    void accelerate();
    default void brake() {
        System.out.println("Basic braking system");
    }
}
```

### 🎯 Functional Interfaces & Lambda Expressions
Single-method interfaces can be implemented using lambda expressions:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Lambda implementation
Calculator addition = (a, b) -> a + b;
Calculator subtraction = (a, b) -> a - b;
```

---

## 🧠 Interview Questions & Answers

- ❓ **What is the primary use of inheritance?**  
  > To promote code reuse and establish a natural hierarchy.

- ❓ **How is method overriding different from method overloading?**  
  > Overriding occurs in different classes (parent-child), overloading occurs in the same class.

- ❓ **What is dynamic dispatch?**  
  > Runtime decision of which overridden method to invoke based on the object.

- ❓ **Why can't Java have multiple inheritance with classes?**  
  > To avoid the diamond problem and ambiguity in method resolution.

- ❓ **How can multiple inheritance be achieved in Java?**  
  > By implementing multiple interfaces.

- ❓ **Can constructors be inherited?**  
  > No. Constructors are not inherited but can be invoked via `super()`.

- ❓ **What happens if you don't override a method in a subclass?**  
  > The method from the superclass is used.

- ❓ **Can we override private or static methods?**  
  > No. Private methods are not visible to the subclass; static methods belong to the class, not the instance.

- ❓ **What is the use of `@Override` annotation?**  
  > It tells the compiler that a method is meant to override a superclass method, and catches errors if it doesn't.

- ❓ **What's the difference between `super` and `this`?**  
  > `this` refers to the current object, `super` refers to the parent class object.

- ❓ **How do access modifiers affect inheritance?**  
  > Only public and protected members are accessible in subclasses outside the package.

- ❓ **What is object slicing?**  
  > When a subclass object is referenced by a superclass variable, only the accessible superclass parts are used.

- ❓ **Can a class extend more than one class in Java?**  
  > No, Java supports single inheritance only.

- ❓ **What are covariant return types?**  
  > A feature that allows an overriding method to return a subtype of the type returned by the overridden method.

- ❓ **Explain method hiding in Java**  
  > Method hiding occurs when a static method in a subclass has the same signature as a static method in the superclass. Unlike overriding, which uses dynamic binding, hidden methods are resolved at compile-time based on the reference type.

- ❓ **What is the difference between IS-A and HAS-A relationships?**  
  > IS-A is an inheritance relationship (Dog IS-A Animal), while HAS-A is a composition relationship (Car HAS-A Engine).

- ❓ **What is the Liskov Substitution Principle?**  
  > Objects of a superclass should be replaceable with objects of its subclasses without altering the correctness of the program.

- ❓ **When should you use composition over inheritance?**  
  > Use composition when you need flexibility in behavior, want to avoid the "fragile base class" problem, or when the relationship is better described as "HAS-A" rather than "IS-A".

- ❓ **What are sealed classes in Java?**  
  > Introduced in Java 17, sealed classes restrict which classes can inherit from them, providing more control over the class hierarchy.

- ❓ **How do default methods in interfaces affect inheritance?**  
  > They allow interfaces to evolve by adding new methods with implementations without breaking existing code. If a class implements multiple interfaces with the same default method, the class must override the method.

- ❓ **What happens when a class implements two interfaces with the same default method?**  
  > The class must override the method to resolve the conflict, or the code won't compile.

- ❓ **Can abstract classes have constructors?**  
  > Yes, abstract classes can have constructors that are called when subclasses are instantiated.

- ❓ **What is the diamond problem in multiple inheritance?**  
  > It occurs when a class inherits from two classes that have a common ancestor. The ambiguity arises when the subclass needs to determine which implementation of a method to use.

- ❓ **How does Java handle the diamond problem with interfaces?**  
  > When a class implements multiple interfaces with the same default method, Java forces the implementing class to override the method, resolving the ambiguity.

- ❓ **What is the `instanceof` operator used for in inheritance scenarios?**  
  > It checks whether an object is an instance of a specific class or implements a particular interface, useful for safe downcasting.

- ❓ **Can you override a final method?**  
  > No, final methods cannot be overridden in subclasses.

- ❓ **What's the difference between early binding and late binding?**  
  > Early binding (static binding) happens at compile time for static, private, and final methods. Late binding (dynamic binding) happens at runtime for virtual (overridden) methods.

- ❓ **How does the bridge method mechanism work in Java?**  
  > Bridge methods are synthetic methods generated by the compiler to handle type erasure and maintain polymorphic behavior when dealing with generic methods in inheritance scenarios.

- ❓ **What are marker interfaces?**  
  > Interfaces without any methods (like Serializable, Cloneable) used to indicate that implementing classes have certain capabilities.

- ❓ **Can an abstract class extend a concrete class?**  
  > Yes, an abstract class can extend a concrete class.

- ❓ **What happens when a class implements two interfaces where one method has a different return type?**  
  > This creates a compile-time error unless one return type is a subtype of the other.
