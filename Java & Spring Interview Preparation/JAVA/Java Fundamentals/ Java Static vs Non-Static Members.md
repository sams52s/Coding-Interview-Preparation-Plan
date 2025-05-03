# ⚙️ Java Static vs Non-Static Members

In Java, class members (fields, methods, blocks, and nested classes) can be either **static** or **non-static (instance)**. Understanding the distinction is essential for class design, memory efficiency, access control, and implementing design patterns.

---

## 📘 1. What is a Static Member?
A **static** member is associated with the **class** itself rather than any object. It is shared across all instances of that class.

### ✅ Features:
- Belongs to the class, not any specific instance
- Initialized only once at class loading time
- Can be accessed using the class name
- Cannot access instance members directly
- Memory efficient for shared constants and utilities

### 🧪 Example:
```java
class Counter {
    static int count = 0;

    Counter() {
        count++;
        System.out.println(count);
    }
}
```

---

## 👤 2. What is a Non-Static (Instance) Member?
A **non-static** member belongs to the individual object. Each object has its own copy.

### ✅ Features:
- Requires object instantiation
- Can access both static and instance members
- Suitable for maintaining object-specific state and behavior

### 🧪 Example:
```java
class Car {
    String color; // non-static field

    void showColor() {
        System.out.println(color);
    }
}
```

---

## 🔄 3. Comparison Table

| Feature              | Static Member                         | Non-Static Member                      |
|----------------------|----------------------------------------|-----------------------------------------|
| Belongs To           | Class                                  | Object (Instance)                       |
| Memory Allocation    | Once, at class load time               | Every time an object is created         |
| Accessed Using       | Class name or object                   | Only through object                     |
| Access Scope         | Can access only static members         | Can access both static and non-static   |
| Common Use           | Constants, Utility Methods             | Object-specific data and behavior       |
| Keyword              | `static`                               | No keyword required                     |

---

## 🧠 4. Access Syntax

### Static Access:
```java
Math.sqrt(25);          // static method
Counter.count;          // static field
```

### Non-Static Access:
```java
Car myCar = new Car();
myCar.color = "Red";
myCar.showColor();
```

---

## 🚀 5. Use Cases

### ✅ Static:
- Utility methods (`Math.max()`, `Collections.sort()`)
- Constant fields (`public static final`)
- Shared resources (like counters, caches)
- Singleton and Factory pattern methods

### ✅ Non-Static:
- Object-specific behavior (e.g., bank account, product state)
- Polymorphic design (overridable instance methods)
- Encapsulation of data and behavior within instances

---

## ❌ 6. Common Mistakes
- Accessing instance variables from static methods (causes compiler error)
- Using object references to call static methods (legal but misleading)
- Overusing static for logic that should belong to objects
- Thread safety issues from improperly synchronized static variables
- Forgetting that `static` methods can't use `this` or `super`

---

## 📋 7. Best Practices
- Use `static` for **stateless** logic shared across all instances
- Avoid mutable static fields unless absolutely necessary and thread-safe
- Group static methods in utility or helper classes (e.g., `StringUtils`)
- Prefer class name for static access (e.g., `MyClass.method()`)
- Document shared static state to avoid unexpected side effects

---

## 🧠 8. Interview Questions & Answers

- ❓ **What is the difference between static and non-static methods?**  
  > Static methods belong to the class and don't need an instance. Non-static methods require an object and can access instance state.

- ❓ **Can we override static methods in Java?**  
  > No. Static methods are bound at compile time and cannot be overridden—only hidden.

- ❓ **Can a static method access instance variables?**  
  > No. Instance variables require an object context, which static methods don't have.

- ❓ **What happens if you call a static method using an object?**  
  > It works, but it is discouraged as the method is still resolved via the class.

- ❓ **When should you prefer static methods?**  
  > When logic is stateless and not tied to instance data—e.g., conversions, calculations, validation, formatting.

- ❓ **Are static blocks allowed in Java?**  
  > Yes. Static initialization blocks are used to initialize static fields.

```java
static {
    // runs once when class is loaded
    config = loadConfig();
}
```

- ❓ **Can inner classes be static?**  
  > Yes. Static nested classes do not hold a reference to an outer class instance.
