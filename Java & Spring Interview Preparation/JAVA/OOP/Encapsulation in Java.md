# 🔐 Encapsulation in Java

**Encapsulation** is one of the four fundamental OOP principles in Java (along with inheritance, polymorphism, and abstraction). It is the mechanism of **binding data and the methods that operate on that data** into a single unit, typically a class, and **restricting direct access** to some of the object’s components.

---

## 🧱 1. What is Encapsulation?

Encapsulation is the technique of **hiding internal state** and requiring all interaction to be performed through an object’s **methods**.

> ✅ Encapsulation ensures data integrity and security by making fields private and exposing public getter/setter methods.

---

## 🎯 2. Goals of Encapsulation

- Protect internal object state
- Improve code modularity
- Simplify maintenance and debugging
- Promote loose coupling and high cohesion
- Enable validation through setters

---

## 🔒 3. How to Achieve Encapsulation in Java

1. Declare fields **private**
2. Provide **public getters and setters** to access and update them

### ✅ Example:
```java
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
```

> 🔍 You control access and validation logic through your public interface.

---

## 📦 4. Encapsulation vs Abstraction

| Feature        | Encapsulation                                 | Abstraction                          |
|----------------|-----------------------------------------------|--------------------------------------|
| Hides          | Data (implementation details)                 | Complexity (only relevant behavior)  |
| Focus          | **How** data is accessed and protected        | **What** operations are exposed      |
| Achieved using | Access modifiers, getter/setter methods       | Abstract classes, interfaces         |
| Purpose        | Data security and integrity                   | Simplify interface for user          |

---

## 🛡️ 5. Access Modifiers Recap

| Modifier   | Class | Package | Subclass | World |
|------------|-------|---------|----------|--------|
| public     | ✅    | ✅      | ✅       | ✅     |
| protected  | ✅    | ✅      | ✅       | ❌     |
| default    | ✅    | ✅      | ❌       | ❌     |
| private    | ✅    | ❌      | ❌       | ❌     |

---

## ⚙️ 6. Benefits of Encapsulation

- Restricts unauthorized access
- Improves flexibility and maintainability
- Enhances testability
- Supports principle of least privilege
- Enforces validation logic centrally

---

## 📘 7. Advanced Practices

### 🔁 Immutable Classes:
```java
public final class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}
```
- No setters
- All fields `final`
- Class `final`

### 🔎 Reflection and Encapsulation:
Java reflection can bypass encapsulation, but it should be used responsibly in frameworks/testing.

### 🛡️ Defensive Copying for Mutable Objects:
```java
public class Student {
    private final Date enrollmentDate;
    
    public Student(Date enrollmentDate) {
        // Defensive copy in constructor
        this.enrollmentDate = new Date(enrollmentDate.getTime());
    }
    
    public Date getEnrollmentDate() {
        // Defensive copy in getter
        return new Date(enrollmentDate.getTime());
    }
}
```

### 🏗️ Builder Pattern for Encapsulation:
```java
public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String address;
    
    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.address = builder.address;
    }
    
    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private String address;
        
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        // Other builder methods
        
        public Person build() {
            // Validation logic here
            return new Person(this);
        }
    }
}
```

### 📦 Package-Level Encapsulation:
Using default (package-private) access modifiers to encapsulate implementation details within a package.

```java
// In file PackageDataManager.java
class PackageDataManager {
    // Only accessible within the package
    void processInternalData() { /* logic */ }
}

// In file PublicAPI.java
public class PublicAPI {
    private PackageDataManager manager = new PackageDataManager();
    
    public void doOperation() {
        // Using package-encapsulated functionality
        manager.processInternalData();
    }
}
```

### 🧵 Encapsulation and Thread Safety:
```java
public class ThreadSafeCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

### 🔄 Encapsulation with Collections:
```java
public class Library {
    private final List<Book> books = new ArrayList<>();
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public List<Book> getBooks() {
        // Return unmodifiable view to prevent external changes
        return Collections.unmodifiableList(books);
    }
}
```

---

## 🧠 Interview Questions & Answers

- ❓ **What is encapsulation in Java?**
  > Wrapping fields and methods together in a class and restricting direct access to fields.

- ❓ **How is encapsulation achieved in Java?**
  > By using private fields and providing public getter/setter methods.

- ❓ **Why should fields be private?**
  > To prevent external modification and maintain control through setter validation.

- ❓ **What is the benefit of using setters over public fields?**
  > Setters can enforce validation and prevent inconsistent states.

- ❓ **What is the difference between encapsulation and abstraction?**
  > Encapsulation hides data; abstraction hides implementation complexity.

- ❓ **Can we achieve encapsulation without setters?**
  > Yes, using constructor-only initialization for immutable objects.

- ❓ **Is JavaBeans standard related to encapsulation?**
  > Yes, it requires fields to be private and accessed via getters/setters.

- ❓ **What happens if you expose fields as public?**
  > You lose control over data validation and make your class harder to maintain.

- ❓ **How does encapsulation improve code maintainability?**
  > By localizing changes to access logic in a single place.

- ❓ **Can encapsulation exist without inheritance or polymorphism?**
  > Yes, encapsulation is independent of other OOP principles.
  
- ❓ **How does encapsulation relate to the Law of Demeter (Principle of Least Knowledge)?**
  > Encapsulation supports the Law of Demeter by restricting access to object internals, reducing coupling, and encouraging objects to only interact with their immediate "friends."

- ❓ **How would you handle encapsulation with collections?**
  > Return unmodifiable views or defensive copies of collections to prevent external modification. Example: `return Collections.unmodifiableList(myList);`

- ❓ **How does encapsulation help with thread safety?**
  > By controlling access to data through synchronized methods or using thread-safe data structures, encapsulation helps prevent race conditions and ensures data consistency.

- ❓ **What's the difference between JavaBean encapsulation and POJO encapsulation?**
  > JavaBeans follow strict conventions (private fields, get/set prefixes, no-arg constructor), while POJOs can be encapsulated without following these conventions.

- ❓ **When might you intentionally break encapsulation?**
  > Rare cases include performance-critical code, framework requirements, or when serialization needs direct access to fields.

- ❓ **How do you balance encapsulation with performance concerns?**
  > Profile first to identify actual bottlenecks; consider package-private access for trusted code; use object pools or flyweights for expensive objects; optimize critical paths.

- ❓ **What are encapsulation violations, and what problems do they cause?**
  > Violations include public fields, exposing mutable objects, or reflection abuse. They can cause data corruption, unexpected state changes, and make maintenance difficult.

- ❓ **How does encapsulation work with inheritance?**
  > Subclasses cannot access private members of their superclass but can access protected members. This allows controlled access while maintaining encapsulation.

- ❓ **What is temporal coupling and how does encapsulation help prevent it?**
  > Temporal coupling occurs when methods must be called in a specific order. Encapsulation can hide this complexity by creating higher-level methods that handle the proper sequence internally.

- ❓ **How can you ensure encapsulation with serialization?**
  > Implement `readObject()` and `writeObject()` methods to control the serialization process; use `transient` for fields that shouldn't be serialized; validate deserialized objects.

- ❓ **How does encapsulation differ in functional programming vs OOP?**
  > OOP encapsulation binds data and behaviors in objects with access control, while functional programming achieves encapsulation through closures, higher-order functions, and immutability.

- ❓ **How would you refactor poorly encapsulated code?**
  > Identify public fields and convert to private with getters/setters; replace direct field access with methods; introduce validation; use immutable objects where appropriate; extract cohesive functionality.

- ❓ **How does encapsulation relate to Design by Contract?**
  > Encapsulation helps enforce contracts by validating inputs in setters, ensuring invariants, and controlling how objects can be modified, making it easier to guarantee preconditions and postconditions.
