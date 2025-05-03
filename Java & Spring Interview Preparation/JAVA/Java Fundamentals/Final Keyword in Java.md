# 🔐 Final Keyword in Java

The `final` keyword in Java is a modifier that can be applied to **variables**, **methods**, and **classes**. It enforces immutability, prevents overriding, and enhances security and predictability in code.

---

## 📘 1. Final Variables

### ✅ Behavior:
- A **final variable** can only be assigned **once**.
- Once initialized, its value **cannot be changed**.
- Can be a constant or a reference (immutable reference, not content).

### 🔍 Types:
- Final primitive variables → value is constant
- Final object references → reference is constant, object contents can change

### 🧪 Example:
```java
final int MAX_USERS = 100;
final List<String> names = new ArrayList<>();
names.add("John"); // Allowed
names = new ArrayList<>(); // ❌ Not allowed
```

---

## 🧩 2. Final Methods

### ✅ Behavior:
- A **final method** cannot be **overridden** by subclasses.
- Used to prevent modification of essential logic in base classes.

### 🧪 Example:
```java
class Base {
    public final void show() {
        System.out.println("Final Method");
    }
}

class Derived extends Base {
    // void show() {} ❌ Compilation error
}
```

---

## 🧱 3. Final Classes

### ✅ Behavior:
- A **final class** **cannot be extended** (inherited).
- Prevents inheritance to maintain a secure and closed implementation.

### 🧪 Example:
```java
final class Utility {
    public static void log(String msg) {
        System.out.println(msg);
    }
}

// class ExtendedUtility extends Utility {} ❌ Not allowed
```

Common final classes in Java include:
- `java.lang.String`
- `java.lang.Math`
- `java.lang.System`

---

## ⚠️ 4. Common Misunderstandings
- `final` on object reference **does not** make the object immutable.
- `final` is **not** the same as `const` (Java does not have `const`).
- Final local variables must be initialized **before use**.
- Final methods are still **inherited**, just not **overridden**.

---

## 📋 5. Best Practices
- Use `final` for constants (`static final`) to enhance clarity and avoid reassignment.
- Favor `final` for method parameters and local variables to increase readability.
- Declare utility/helper classes as `final` if they contain only static methods.
- Mark fields as `final` when their value is set once during construction.

---

## 🧠 6. Interview Questions & Answers

- ❓ **What does the `final` keyword do in Java?**  
  > It restricts further modification: final variables can’t be reassigned, final methods can’t be overridden, and final classes can’t be extended.

- ❓ **Can a final variable be initialized in a constructor?**  
  > Yes, instance-level final variables can be assigned once inside a constructor.

- ❓ **Is a final object completely immutable?**  
  > No, the reference is immutable, but the object’s internal state can still change.

- ❓ **Can static methods be final?**  
  > Yes. It prevents method hiding in subclasses.

- ❓ **Why use final for parameters or local variables?**  
  > Improves code safety and prevents accidental reassignment, especially in anonymous classes/lambdas.
- ❓ **What happens if you try to override a final method?**
  > Compilation error: "Cannot override the final method from Base".
- ❓ **Can you declare a constructor as final?**
  > No, constructors cannot be final as they are not inherited.
- ❓ **Can you declare an interface as final?**
    > No, interfaces cannot be final as they are meant to be implemented by other classes.
- ❓ **What is the difference between `final`, `finally`, and `finalize`?**
  > `final` is a keyword for immutability, `finally` is a block in exception handling, and `finalize` is a method called by the garbage collector before an object is removed.

- ❓ **Can you declare a class as final and abstract?**
  > No, a class cannot be both final and abstract as they serve opposite purposes.
- ❓ **Can you declare a final class as abstract?**
    > No, a final class cannot be abstract because it cannot be subclassed.
- ❓ **Can you declare a final method as static?**
  > Yes, a final method can also be static. It cannot be overridden in subclasses.