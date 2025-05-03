# 🔍 Functions vs Methods in Java

In Java, **methods** are the building blocks of behavior within a class. Unlike some other languages, Java doesn’t support standalone **functions** — all functions must be declared within a class and are thus called **methods**.

---

## 🧭 1. What is a Method in Java?
A **method** in Java is a block of code that performs a specific task. It is always associated with a **class or object**.

### ✅ Syntax:
```java
modifier returnType methodName(parameters) {
    // method body
    return value;
}
```

### 🧪 Example:
```java
public int add(int a, int b) {
    return a + b;
}
```

---

## ❓ Why No Standalone Functions in Java?
Unlike C, Python, or JavaScript, Java is **purely object-oriented**, meaning all code must be part of a class. That’s why **Java doesn’t support functions outside of classes**.

---

## 🧩 2. Types of Methods in Java

### 📌 Based on Context:
- **Instance Methods** – Require object creation
- **Static Methods** – Belong to the class, don’t require objects

### 📌 Based on Return:
- **Void Methods** – No return value
- **Return-Type Methods** – Return specific value types

### 📌 Based on Overloading:
- **Overloaded Methods** – Same name, different parameters

---

## 🔄 3. Static vs Instance Methods

| Feature              | Static Method                      | Instance Method                        |
|----------------------|-------------------------------------|----------------------------------------|
| Accessed Via         | Class name                         | Object reference                        |
| Access Instance Data | ❌ Cannot (without object)          | ✅ Can                                  |
| Common Use           | Utilities, constants               | Behavior tied to object                |

```java
class Utility {
    static int square(int x) {
        return x * x;
    }
}

Utility.square(4); // ✅
```

---

## 🔁 4. Method Overloading
```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```
- Methods with the **same name but different parameters** are considered overloaded.

---

## 🧠 5. Key Notes on Methods

- All **functions in Java are methods** (because they must reside in a class).
- `main(String[] args)` is a **static method** used as an entry point.
- Methods can be **abstract**, **final**, **static**, or **synchronized**.
- Constructors are **not** methods, although they resemble them.

---

## 🧠 Interview Questions & Answers

- ❓ **Is there a difference between a function and a method in Java?**  
  > Technically, no. All functions are methods in Java because Java doesn’t allow functions outside classes.

- ❓ **Can a method return multiple values?**  
  > Not directly. You can return an array, collection, or a custom object wrapping multiple fields.

- ❓ **What is method overloading?**  
  > Method overloading is defining multiple methods with the same name but different parameter types or counts.

- ❓ **Can we call a method without creating an object?**  
  > Yes, if it’s `static`. Static methods belong to the class, not an object.

- ❓ **Can Java have functions like C/C++?**  
  > No, Java enforces object-oriented structure — all code must be part of a class.

- ❓ **What’s the difference between constructor and method?**  
  > Constructors initialize objects and have no return type; methods define behavior and must declare a return type.
