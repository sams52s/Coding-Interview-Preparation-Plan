# Java Basics

---

## 🧮 Variables

### 🔹 What is a Variable?
A variable is a named memory location used to store data.

### 🔸 Declaration & Initialization

```java
int age;          // Declaration
age = 25;         // Initialization
int height = 180; // Declaration + Initialization
```

### 🔸 Variable Types

- **Local Variables**: Defined inside methods/blocks; scope is limited to those blocks.
- **Instance Variables**: Defined in class but outside methods; belong to objects.
- **Static Variables**: Declared with `static`; shared among all instances.

### 🔸 Variable Scope

| Type      | Scope                        |
|-----------|------------------------------|
| Local     | Inside method/block          |
| Instance  | Within class instance        |
| Static    | Class-wide (shared globally) |

### 🔸 Default Values

| Type         | Default Value |
|--------------|---------------|
| `int`, `long`| `0`           |
| `float`      | `0.0f`        |
| `double`     | `0.0d`        |
| `boolean`    | `false`       |
| `char`       | `'\u0000'`    |
| Object refs  | `null`        |

### 🔸 Constants with `final`

```java
final double PI = 3.14159;
```

---

## Data Types (Primitive and Non###Primitive)
Java has two main categories of data types:

### ✅ 1. Primitive Data Types

These are the most basic data types built into the language. There are 8 primitives in Java:

| Type     | Size       | Example         | Description                                |
|----------|------------|-----------------|--------------------------------------------|
| `byte`   | 1 byte     | `byte b = 10;`  | Useful for saving memory in large arrays.  |
| `short`  | 2 bytes    | `short s = 200;`| Stores small integer values.               |
| `int`    | 4 bytes    | `int x = 5000;` | Default for integer values.                |
| `long`   | 8 bytes    | `long l = 900000000L;` | For large integers.               |
| `float`  | 4 bytes    | `float f = 5.75f;` | Single-precision decimal numbers.      |
| `double` | 8 bytes    | `double d = 19.99;` | Default for decimal values.          |
| `char`   | 2 bytes    | `char c = 'A';` | Stores a single Unicode character.         |
| `boolean`| 1 bit      | `boolean flag = true;` | Only true or false.                |

📚 **Reference:**
- [Oracle Java Primitive Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Java Primitive Data Types](https://www.javatpoint.com/java-primitive-data-types)
- [Java Primitive Data Types - GeeksforGeeks](https://www.geeksforgeeks.org/java-primitive-data-types/)

### ✅ 2. Non-Primitive Data Types
These are more complex data types that are derived from primitive data types. They include:
| Type        | Example                | Description                                |
|-------------|------------------------|--------------------------------------------|
| `String`    | `String str = "Hello";`| A sequence of characters.                  |
| `Array`     | `int[] arr = {1, 2, 3};`| A collection of elements of the same type. |
| `Class`     | `class MyClass {}`     | A blueprint for creating objects.          |
| `Interface` | `interface MyInterface {}`| A contract for classes to implement.   |
| `Enum`      | `enum Day {SUNDAY, MONDAY}`| A special Java type used to define collections of constants. |
| `Object`    | `Object obj = new Object();`| The root class of all Java classes.   |
| `Collection` | `List<String> list = new ArrayList<>();`| A framework for storing and manipulating groups of objects. |
| `Map`       | `Map<String, Integer> map = new HashMap<>();`| A collection of key-value pairs. |
| `Set`       | `Set<String> set = new HashSet<>();`| A collection that contains no duplicate elements. |
| `List`      | `List<String> list = new ArrayList<>();`| An ordered collection (also known as a sequence). |
| `Stack`     | `Stack<Integer> stack = new Stack<>();`| A last-in-first-out (LIFO) data structure. |
| `Queue`     | `Queue<String> queue = new LinkedList<>();`| A first-in-first-out (FIFO) data structure. |
| `LinkedList`| `LinkedList<String> linkedList = new LinkedList<>();`| A doubly-linked list implementation of the List and Deque interfaces. |
| `HashMap`   | `HashMap<String, String> map = new HashMap<>();`| A hash table based implementation of the Map interface. | 
| `Classes and Objects`| `class Car { String color; } Car myCar = new Car();`| A class is a blueprint for creating objects. An object is an instance of a class. |
| `StringBuilder`| `StringBuilder sb = new StringBuilder("Hello");`| A mutable sequence of characters. |

These types store references to memory locations, not actual values.

### 📚 **Reference:**
- [Java Non-Primitive Data Types](https://www.javatpoint.com/java-non-primitive-data-types)
- [Java Non-Primitive Data Types - JavaTpoint](https://www.javatpoint.com/java-non-primitive-data-types)
- [Java Objects - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/objects.html)
- [Java Non-Primitive Data Types - GeeksforGeeks](https://www.geeksforgeeks.org/java-non-primitive-data-types/)

### 📝 **Note:**

- ✅ **Primitive types** are stored in the **stack**, faster, and consume less memory.
- ✅ **Non-primitive types** are stored in the **heap**, more flexible, and can be `null`.
- ✅ Non-primitives have **methods and attributes**.
- ✅ Arrays, Strings, and user-defined types fall under non-primitive data types.


### 🔁 Comparison: Primitive vs Non-Primitive Data Types

| Feature                    | Primitive Data Types                        | Non-Primitive Data Types                        |
|----------------------------|---------------------------------------------|-------------------------------------------------|
| **Definition**             | Basic built-in data types                   | Derived or user-defined types                   |
| **Examples**               | `int`, `char`, `boolean`, `float`, etc.    | `String`, `Array`, `Class`, `List`, etc.        |
| **Memory Storage**         | Stored in **stack** memory                  | Stored in **heap** memory                       |
| **Nullability**            | Cannot be null (default values only)       | Can be null                                     |
| **Methods/Attributes**     | Do not have built-in methods                | Have built-in methods and behaviors             |
| **Mutability**             | Immutable (value-based)                    | Mutable (especially in collections or objects)  |
| **Default Values**         | Defaults like `0`, `false`, `'\u0000'`     | Defaults to `null`                              |
| **Performance**            | Faster and memory-efficient                 | Slightly slower due to object overhead          |
| **Wrapper Classes**        | Has (e.g., `Integer`, `Double`, etc.)      | Not applicable                                  |
| **Usage**                  | Simple operations, performance-critical apps| Complex data structures and applications        |

### 🧠 **Tip**:  
Primitive types are typically used when performance and memory efficiency matter, while non-primitives offer flexibility and are used in object-oriented scenarios.

### 🔷 1. Java Data Types Hierarchy Diagram
```
Java Data Types
│
├──▶ Primitive Types (8 total)
│   ├── Numeric
│   │   ├── Integer: byte, short, int, long
│   │   └── Floating: float, double
│   ├── Character: char
│   └── Boolean: boolean
│
└──▶ Non-Primitive Types
    ├── String
    ├── Array
    ├── Class / Object
    ├── Interface / Enum
    └── Collections
        ├── List
        ├── Set
        ├── Map
        └── Queue
```

---

## 📊 Operators

### 🔹 Arithmetic Operators

| Operator | Description        | Example         |
|----------|--------------------|-----------------|
| `+`      | Addition           | `a + b`         |
| `-`      | Subtraction        | `a - b`         |
| `*`      | Multiplication     | `a * b`         |
| `/`      | Division           | `a / b`         |
| `%`      | Modulus (remainder)| `a % b`         |

### 🔹 Relational Operators

```java
==, !=, >, <, >=, <=
```

Used to compare two values and return a boolean result.

### 🔹 Logical Operators

```java
&&  // Logical AND
||  // Logical OR
!   // Logical NOT
```

### 🔹 Assignment Operators

```java
=, +=, -=, *=, /=, %=
```

Shorthand for performing an operation and assignment.

### 🔹 Unary Operators

```java
++, --, +, -, !
```

Used for incrementing/decrementing or logical negation.

### 🔹 Bitwise Operators

```java
&, |, ^, ~, <<, >>, >>>
```

Work at the bit level. Useful in performance-sensitive areas.

### 🔹 Ternary Operator

```java
String result = (score >= 40) ? "Pass" : "Fail";
```

Short-hand for simple `if-else`.

### 🔹 `instanceof` Operator

Checks if an object is of a specific type:

```java
if (obj instanceof String) {
    System.out.println("It's a string");
}
```