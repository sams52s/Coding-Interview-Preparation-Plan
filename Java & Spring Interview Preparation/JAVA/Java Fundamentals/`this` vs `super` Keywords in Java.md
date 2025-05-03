# 🔄 `this` vs `super` Keywords in Java

Java provides two special references — `this` and `super` — to interact with the current class and its superclass. These keywords are essential for inheritance, method overriding, and constructor chaining.

---

## 🧭 1. `this` Keyword

The `this` keyword refers to the **current object** of the class. It is commonly used to resolve ambiguity, access current fields, and chain constructors.

### ✅ Common Uses:
- Refer to current class's fields when there is a naming conflict
- Invoke current class's methods
- Invoke current class's constructors (constructor chaining)
- Pass current object as an argument
- Return current object from a method (useful in method chaining)

### 🧪 Example:
```java
class Student {
    String name;

    Student(String name) {
        this.name = name; // refers to instance variable
    }

    void display() {
        System.out.println(this.name); // refers to current object
    }
}
```

### 🔁 Constructor Chaining:
```java
class Rectangle {
    int length, width;

    Rectangle() {
        this(10, 5); // calls parameterized constructor
    }

    Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }
}
```

---

## 🧬 2. `super` Keyword

The `super` keyword refers to the **immediate parent class** of the current object. It is used to invoke superclass methods or constructors.

### ✅ Common Uses:
- Call superclass constructor
- Access superclass method when overridden
- Access superclass fields

### 🧪 Example:
```java
class Animal {
    String name = "Animal";
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    String name = "Dog";
    void sound() {
        super.sound(); // calls Animal's sound
        System.out.println("Dog barks");
    }

    void showName() {
        System.out.println(super.name); // access Animal's name
        System.out.println(this.name);  // access Dog's name
    }
}
```

### 🔁 Constructor Use:
```java
class Animal {
    Animal(String name) {
        System.out.println("Animal: " + name);
    }
}

class Dog extends Animal {
    Dog() {
        super("Buddy"); // calls superclass constructor
    }
}
```

---

## 🔍 3. Comparison Table

| Feature                 | `this`                          | `super`                              |
|------------------------|----------------------------------|---------------------------------------|
| Refers To              | Current class instance           | Immediate superclass instance         |
| Used For               | Access own fields/methods        | Access superclass fields/methods      |
| Constructor Call       | Calls another constructor in same class | Calls superclass constructor     |
| Use in Overridden Method | Calls current method explicitly | Access overridden method in parent    |
| Access Modifier Check  | None                             | Parent members must be visible (e.g., `protected`, `public`) |

---

## ❗ Key Points
- `this()` and `super()` must be the **first statement** in a constructor.
- Cannot use `this` or `super` in static context.
- Cannot call both `this()` and `super()` in the same constructor.
- `this` helps in fluent interfaces or builder patterns.
- `super` is often used to extend or augment inherited behavior.

---

## 🧠 Interview Questions & Answers

- ❓ **What is the difference between `this` and `super`?**  
  > `this` refers to the current class instance, while `super` refers to the immediate parent class.

- ❓ **Can we use `this()` and `super()` together in one constructor?**  
  > No, only one can be the first statement in a constructor.

- ❓ **When would you use `super` explicitly?**  
  > To access a parent class's field or method when it is hidden or overridden in the subclass, or to call a specific parent constructor.

- ❓ **Can `super` be used to access private members of a parent class?**  
  > No. Private members are not accessible outside the declaring class—even by subclasses.

- ❓ **Why is `this` useful in setter methods or constructor injection?**  
  > It helps resolve ambiguity between instance variables and method parameters with the same name.

- ❓ **What if a method in both parent and child has the same name? How to call each?**  
  > Use `this.method()` for the current class and `super.method()` for the parent class.

- ❓ **Can you assign `this` to another variable or pass it as a parameter?**  
  > Yes, `this` can be passed to methods or assigned to a variable of the same type.

- ❓ **What is the output if a child class field hides a parent field?**  
  > Accessing via `super.field` refers to parent’s field, and `this.field` refers to child’s field.
