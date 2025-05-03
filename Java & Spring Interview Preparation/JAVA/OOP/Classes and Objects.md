# 🧱 Classes and Objects in Java

Java is an object-oriented programming language. The **class** is the blueprint for creating objects, and an **object** is a runtime instance of a class.

---

## 🧭 1. What is a Class?

A **class** is a user-defined data type that acts as a blueprint for objects. It encapsulates **fields (attributes)** and **methods (functions)** into a single unit.

### ✅ Syntax:
```java
class ClassName {
    // Fields (data members)
    dataType variableName;

    // Methods (member functions)
    returnType methodName(parameters) {
        // method body
    }
}
```

### 🧪 Example:
```java
class Car {
    String model;
    int year;

    void start() {
        System.out.println(model + " started.");
    }
}
```

---

## 👤 2. What is an Object?

An **object** is an instance of a class. It represents a specific entity with defined state and behavior.

### ✅ Syntax:
```java
ClassName obj = new ClassName();
```

### 🧪 Example:
```java
Car myCar = new Car();
myCar.model = "Toyota";
myCar.year = 2022;
myCar.start();
```

### 📦 Object Memory Representation:
```
Heap Memory:
---------------------------
| model  |  "Toyota"      |
| year   |   2022         |
| start() method pointer |
---------------------------
```

---

## 🛠️ 3. Components of a Class

| Component      | Description                                 |
|----------------|---------------------------------------------|
| Fields         | Variables that hold object state            |
| Methods        | Functions that define object behavior       |
| Constructors   | Special methods to initialize objects       |
| Blocks         | Initialization blocks (static or instance)  |
| Nested Classes | Classes defined inside another class        |

### 🔁 Constructor Example:
```java
class Person {
    String name;

    // Constructor
    Person(String name) {
        this.name = name;
    }
}
```

---

## 🔄 4. Creating Multiple Objects
```java
Car car1 = new Car();
Car car2 = new Car();
car1.model = "Tesla";
car2.model = "BMW";
```

Each object has its **own copy of instance variables**.

---

## 🧬 5. Advanced Concepts

### 🔹 Object Comparison:
```java
Car car1 = new Car();
Car car2 = new Car();
System.out.println(car1 == car2);       // false
System.out.println(car1.equals(car2));   // false (unless overridden)
```

### 🔹 Method Overloading in Classes:
```java
class Printer {
    void print(String msg) {
        System.out.println(msg);
    }
    void print(int number) {
        System.out.println(number);
    }
}
```

### 🔹 Static vs Instance Members:
- **Static** → shared by all objects (class-level)
- **Instance** → unique to each object (object-level)

### 🔹 Object Lifecycle and Garbage Collection:
```java
public class LifecycleDemo {
    public LifecycleDemo() {
        // Object creation/initialization
        System.out.println("Object created");
    }
    
    public void finalize() {
        // Called before garbage collection (deprecated in newer Java versions)
        System.out.println("Object being garbage collected");
    }
    
    public static void main(String[] args) {
        LifecycleDemo obj = new LifecycleDemo(); // Creation
        obj = null; // Object eligible for garbage collection
        System.gc(); // Request garbage collection
    }
}
```

### 🔹 Immutable Objects:
```java
public final class ImmutablePerson {
    private final String name;
    private final int age;
    
    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    // No setters to modify state
}
```

### 🔹 Anonymous Classes:
```java
interface Greeting {
    void greet();
}

public class AnonymousDemo {
    public static void main(String[] args) {
        Greeting greeting = new Greeting() {
            public void greet() {
                System.out.println("Hello from anonymous class!");
            }
        };
        greeting.greet();
    }
}
```

### 🔹 Object Cloning:
```java
class Employee implements Cloneable {
    String name;
    Address address; // Another class
    
    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone(); // Shallow copy
        // For deep copy:
        cloned.address = address.clone();
        return cloned;
    }
}
```

### 🔹 Object Serialization:
```java
class SerializableDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    
    // This field won't be serialized
    private transient String password;
}
```

---

## 🧠 6. Interview Questions & Answers

- ❓ **What is the difference between a class and an object?**  
  > A class is a blueprint, whereas an object is an instance of that blueprint.

- ❓ **How are objects stored in memory in Java?**  
  > Objects are stored in heap memory, and references (pointers) are stored in the stack.

- ❓ **Can a class be declared inside another class?**  
  > Yes, this is called a nested class. It can be static or non-static.

- ❓ **What is the purpose of a constructor?**  
  > Constructors initialize new objects and can be overloaded.

- ❓ **Can you instantiate a class without `new`?**  
  > Yes, through **reflection** or **deserialization**, but it's not common for typical use cases.

- ❓ **What is the default value of object references?**  
  > `null`, if not explicitly initialized.

- ❓ **What is the difference between instance and static fields in a class?**  
  > Instance fields are unique per object. Static fields are shared across all instances.

- ❓ **How does method overloading relate to classes?**  
  > Method overloading allows a class to have multiple methods with the same name but different parameter lists.

- ❓ **Is it possible to have multiple classes in a single file?**  
  > Yes, but only one can be `public` and must match the filename.

- ❓ **What is the equals() and hashCode() contract?**  
  > When overriding equals(), you should also override hashCode(). Two equal objects must return the same hashCode(), but two objects with the same hashCode() may not be equal.

- ❓ **What is the purpose of the finalize() method?**  
  > It's called by the garbage collector before reclaiming an object's memory. However, it's deprecated in modern Java as it's unpredictable and can cause performance issues.

- ❓ **Explain the difference between shallow and deep copying of objects.**  
  > Shallow copy duplicates object references, while deep copy creates new instances of referenced objects.

- ❓ **What makes an object immutable in Java?**  
  > Make the class final, make fields private and final, don't provide setters, ensure mutable fields can't be modified, and use defensive copying in constructors and getters.

- ❓ **What are anonymous classes and when would you use them?**  
  > Anonymous classes are unnamed classes defined and instantiated in a single expression, useful for one-off implementations of interfaces or abstract classes.

- ❓ **How do you make an object eligible for garbage collection?**  
  > Remove all references to it by setting them to null or reassigning them to other objects.

- ❓ **What is object serialization and why is it useful?**  
  > Serialization converts objects to byte streams for storage or transmission. It's useful for saving object state, deep copying, and distributed computing.

- ❓ **What's the purpose of the 'transient' keyword?**  
  > It excludes a field from serialization, useful for security-sensitive or non-serializable fields.

- ❓ **How does the Singleton pattern ensure only one object instance exists?**  
  > By making the constructor private and providing a static method that returns the single instance, creating it if needed.

- ❓ **What's the difference between 'this' and 'super' keywords?**  
  > 'this' refers to the current object instance, while 'super' refers to the parent class instance.

- ❓ **How can an enum be considered a special type of class in Java?**  
  > Enums can have fields, methods, and implement interfaces like regular classes, but with fixed instances.

---

## 📌 Summary Table

| Concept        | Class                               | Object                             |
|----------------|--------------------------------------|-------------------------------------|
| Definition     | Blueprint or template                | Instance created from the class     |
| Memory         | Not allocated till object is created | Allocated in heap at runtime        |
| Use            | Declare fields and methods           | Access and use data and behaviors   |
| Example        | `Car` class                          | `Car myCar = new Car();`            |
| Lifetime       | Loaded when first referenced         | Created and destroyed during runtime |
| Reusability    | Can be used to create many objects   | Represents one specific instance    |
| Access Control | Can control access with modifiers    | Access limited by class definition  |


