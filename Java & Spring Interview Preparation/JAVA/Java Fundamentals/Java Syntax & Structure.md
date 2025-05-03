# Java Syntax & Structure

This section covers the foundational syntax and structure of Java programs. Understanding this is essential for writing, reading, and debugging Java code effectively.

---

## 📌 Structure of a Basic Java Program (`class`, `main()`)

Every Java program consists of at least one class, and execution starts from the `main` method:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Breakdown:

- `public class HelloWorld` – Defines a class named `HelloWorld`
- `public static void main(String[] args)` – The entry point of the program
  - `public` – Accessible from anywhere
  - `static` – Belongs to the class, not an instance
  - `void` – No return value
  - `String[] args` – Command-line arguments

---

## 🔑 Access Modifiers

- `public`: Accessible from any class in any package.
- `private`: Accessible only within the class where it is declared.
- `protected`: Accessible within the same package and subclasses in other packages.
- *(default)* (no modifier): Accessible only within the same package.

---

## ⚙️ `static` Keyword

- Used to indicate that a method or variable belongs to the class, not instances.
- Common for:
  - Utility methods (e.g., `Math.max`)
  - Shared constants
  - `main` method (`public static void main`)
- Accessed without creating objects.

---

## 📛 `final` Keyword

- **final variable**: Cannot be reassigned once initialized.
- **final method**: Cannot be overridden in subclasses.
- **final class**: Cannot be extended.

```java
final int MAX_USERS = 100;
```

---

## 📦 Classpath in Java

- Tells the JVM where to find `.class` files and packages.
- By default, current directory is used.
- Use `-cp` or `-classpath`:

```bash
java -cp . MyProgram
java -cp ".:lib/library.jar" MyApp
```

---

## 🧾 Java Statements End with Semicolons

Each executable statement in Java must end with a semicolon (`;`).

```java
int a = 5;
System.out.println(a);
```

---

## 🔠 Case Sensitivity in Java

Java is case-sensitive:

- `MyClass` and `myclass` are different identifiers.
- Variable `price` is different from `Price`.

---

## 🧱 Use of Curly Braces `{}` to Define Blocks

Used for:

- Class and method bodies
- Control structures (`if`, `for`, `while`, etc.)

```java
if (score > 90) {
    System.out.println("Excellent");
}
```

Even for one-liners, curly braces are recommended.

---

## 🧾 Naming Conventions and Rules for Identifiers

### Rules:

- Must start with a letter, `_`, or `$`
- Cannot start with a digit
- Cannot be a keyword

### Conventions:

- **Class Names**: `PascalCase` – e.g., `BankAccount`
- **Method Names**: `camelCase` – e.g., `calculateTotal()`
- **Variable Names**: `camelCase` – e.g., `totalAmount`
- **Constants**: `ALL_CAPS_WITH_UNDERSCORES` – e.g., `MAX_LIMIT`
- **File Names**: Must match the public class name (e.g., `Student.java`)
- **Package Names**: Lowercase and dot-separated (`com.example.util`)

---

## ⚙️ Compilation vs Interpretation (`javac`, `java`)

- `.java` files are compiled to `.class` files using:

```bash
javac HelloWorld.java
```

- `.class` files are run using:

```bash
java HelloWorld
```

---

## 💬 Comments in Java

### Single-Line

```java
// This is a comment
```

### Multi-Line

```java
/*
  Multi-line
  comment
*/
```

### Javadoc

```java
/**
 * Documentation comment
 * @author You
 */
```

---

## 🧩 Additional Relevant Topics

### Whitespace and Formatting

- Ignored by compiler but important for readability
- Use consistent indentation (e.g., 4 spaces)
- Use blank lines to separate logical sections
- Use spaces around operators and after commas
- Use meaningful variable and method names
- Avoid long lines (max 80-120 characters)
- Use comments to explain complex logic
- Follow naming conventions for classes, methods, and variables
- Use braces `{}` for all control structures, even single statements
### Reserved Keywords

- Cannot be used as variable or method names:
  `class`, `public`, `static`, `void`, `if`, `while`, etc.


---

## 🧭 Java Program Structure Summary

```java
// Package Declaration
package com.example;

// Imports
import java.util.*;

// Class Declaration
public class SampleApp {

    // Main Method
    public static void main(String[] args) {
        // Statement
        System.out.println("Welcome!");
    }
}
```