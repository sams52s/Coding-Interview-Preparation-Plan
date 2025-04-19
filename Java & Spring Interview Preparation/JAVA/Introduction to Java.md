# Introduction to Java
---

> ## Topics to cover:
> - Java Basics
> - History, Features, Evolution (Java 8 ### 17+)
> - JVM, JRE, JDK – Differences & Roles
> - Java Program Execution Flow
> - Bytecode and .class Files
> - Java Development Kit (JDK) Installation
> - Java IDEs (Eclipse, IntelliJ IDEA, NetBeans)

## Java Basics
---

### Data Types (Primitive and Non###Primitive)
Java has two main categories of data types:

✅ 1. Primitive Data Types

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

✅ 2. Non-Primitive Data Types
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
📚 **Reference:**
- [Java Non-Primitive Data Types](https://www.javatpoint.com/java-non-primitive-data-types)
- [Java Non-Primitive Data Types - JavaTpoint](https://www.javatpoint.com/java-non-primitive-data-types)
- [Java Objects - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/objects.html)
- [Java Non-Primitive Data Types - GeeksforGeeks](https://www.geeksforgeeks.org/java-non-primitive-data-types/)
📝 **Note:**

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

🧠 **Tip**:  
Primitive types are typically used when performance and memory efficiency matter, while non-primitives offer flexibility and are used in object-oriented scenarios.

🔷 1. Java Data Types Hierarchy Diagram
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

### Type Modifiers (final, static, transient, volatile)
 1. **final**: Used to declare constants or prevent method overriding.
 - **Purpose**: Prevents modification.
 - **Usage**: Can be applied to variables, methods, and classes.
 - **Effects**:
 > A final variable cannot be reassigned.
 > A final method cannot be overridden.
 > A final class cannot be extended.
 > A final parameter cannot be modified.
 - **Example**:
```java
final int MAX_VALUE = 100; // constant
final class FinalClass {} // cannot be subclassed
final void finalMethod() {} // cannot be overridden
```
2. **static**: Used to declare class-level variables or methods.
- **Purpose**: Belongs to the class rather than instances.
- **Usage**: Can be applied to variables, methods, and blocks.
- **Effects**:
> A static variable is shared among all instances of the class.
> A static method can be called without creating an instance of the class.
> A static block is executed when the class is loaded.
- **Example**:
```java
static int staticVariable; // class variable
static void staticMethod() {} // class method
static { // static block
    // initialization code
}   
```
3. **transient**: Used to indicate that a field should not be serialized.
- **Purpose**: Prevents serialization of sensitive data.
- **Usage**: Applied to instance variables.
- **Effects**:
> A transient variable is not included in the serialized object.
> Useful for security-sensitive data (e.g., passwords).
- **Example**:
```java
transient int sensitiveData; // not serialized
```
4. **volatile**: Used to indicate that a variable's value may be changed by different threads.
- **Purpose**: Ensures visibility of changes across threads.
- **Usage**: Applied to instance variables.
- **Effects**:
> A volatile variable is always read from and written to main memory.
> Prevents caching of the variable in thread-local memory.
> Ensures that the most recent value is always visible to all threads.
- **Example**:
```java
volatile int sharedVariable; // visible across threads
```
5. **synchronized**: Used to control access to a method or block by multiple threads.
- **Purpose**: Prevents thread interference.
- **Usage**: Applied to methods or blocks.
- **Effects**:
> A synchronized method can only be accessed by one thread at a time.
> A synchronized block can be used to synchronize a specific section of code.
- **Example**:
```java
synchronized void synchronizedMethod() {} // synchronized method
synchronized (this) { // synchronized block
    // critical section
}
```
6. **native**: Used to indicate that a method is implemented in native code (e.g., C/C++).
- **Purpose**: Allows Java to call platform-specific code.
- **Usage**: Applied to methods.
- **Effects**:
> A native method is declared in Java but implemented in another language.
> Requires the Java Native Interface (JNI) to call native code.
- **Example**:
```java
native void nativeMethod(); // native method declaration
```
📝 **Summary Table**
| Modifier   | Purpose                                      | Usage                      | Effects                                      | Example                                   |
|------------|----------------------------------------------|---------------------------|----------------------------------------------|-------------------------------------------|
| final      | Prevents modification                        | Variable, Method, Class   | Immutable values, non-overridable methods/classes| final int MAX_VALUE = 100;               |
| static     | Belongs to class, not instance               | Variable, Method          | Shared across instances                      | static int staticVariable;                |
| transient  | Prevents serialization                       | Variable                  | Excluded from serialized object              | transient int sensitiveData;              |
| volatile   | Ensures visibility across threads            | Variable                  | Immediate visibility of changes              | volatile int sharedVariable;              |    
| synchronized| Controls access by multiple threads         | Method, Block             | Prevents thread interference                 | synchronized void synchronizedMethod() {}  |
| native     | Indicates native code implementation         | Method                    | Calls platform-specific code                 | native void nativeMethod();                |

✅ **Best Practices:**
- Use `final` for constants and immutable classes.
- Use `static` for utility methods and constants.
- Use `transient` for sensitive data in serialization.
- Use `volatile` for shared variables in multi-threaded environments.
- Use `synchronized` for critical sections in multi-threaded applications.


### Type System (Static vs Dynamic)

Java uses a **static type system**, where type checks happen at **compile-time**. This ensures early error detection and optimized performance. 

```java
int count = 10; // Compiler knows count is an int
```

In contrast, **dynamic type systems** (like Python, JavaScript) determine types at runtime, offering more flexibility but higher risk of runtime errors.


### Type Casting (Implicit and Explicit)

**Type casting** is converting a variable from one data type to another.

#### Implicit (Widening) Casting:
Done automatically by the compiler when no data is lost.

```java
int i = 100;
long l = i; // Implicit cast from int to long
```

#### Explicit (Narrowing) Casting:
Manually done by the programmer. Risk of data loss.

```java
double d = 9.78;
int i = (int) d; // Explicit cast
```


### Type Conversion (Automatic and Manual)

Conversion refers to changing the type either automatically or manually:

- **Automatic**: Done by the compiler for compatible types.
- **Manual**: Requires casting syntax for incompatible or risky conversions.

```java
float f = 10;       // automatic
int i = (int) f;    // manual
```


### Type Promotion

Java promotes smaller types to larger types during operations to prevent data loss.

```java
byte a = 10, b = 20;
int result = a + b; // promoted to int
```


### Type Inference (`var` keyword in Java 10)

Introduced in Java 10, `var` allows the compiler to infer types based on assigned values:

```java
var message = "Hello"; // inferred as String
```

> Note: It's still statically typed and the type is fixed after inference.



### Type Safety

Java enforces strict typing, reducing the chances of type-related runtime errors.

```java
List<String> names = new ArrayList<>();
names.add("Alice");
// names.add(42); // Compile-time error
```

Generics further strengthen type safety by ensuring only specific types are allowed.



### Type Checking (Compile-time, Runtime)

- **Compile-time checking**: Ensures type correctness before the program runs.
- **Runtime checking**: Done using `instanceof` or `getClass()`.

```java
if (obj instanceof String) {
    System.out.println("It's a string");
}
```


### Type Erasure (Generics)

Generics in Java are **compile-time only**. At runtime, type information is erased.

```java
List<String> list1 = new ArrayList<>();
List<Integer> list2 = new ArrayList<>();
// Both are treated as List<Object> at runtime due to type erasure
```



### Type Parameters (Generics)

Allow classes and methods to operate on objects of various types while providing compile-time type safety.

```java
class Box<T> {
    T value;
}
```


### Type Bounds (Upper and Lower)

Used to restrict the types that can be passed to generics:

```java
// Upper bound
public <T extends Number> void print(T t) {}

// Lower bound
List<? super Integer> list;
```

---

### Type Aliasing

Java doesn’t support type aliasing directly. Alternatives include:

- Using interfaces or abstract classes as logical aliases
- Using `typedef`-like patterns via wrapper classes



### Type Annotations

Annotations applied to types rather than declarations (Java 8+):

```java
@NonNull String name;
```

Used for frameworks, tools, and validation.


### Type Comparison (`instanceof`, `getClass()`)

Used to compare the actual type of an object:

```java
if (obj instanceof List) { ... }

if (obj.getClass() == String.class) { ... }
```



### Type Compatibility (Widening and Narrowing)

#### Widening:
Safe conversion — no data loss.

```java
int i = 100;
double d = i; // widening
```

#### Narrowing:
Unsafe conversion — possible data loss.

```java
double d = 5.99;
int i = (int) d; // narrowing
```



### Type Hierarchy (Object Class, Wrapper Classes)

- All classes in Java inherit from `Object`.
- Primitives have wrapper classes:
  - `int` → `Integer`
  - `double` → `Double`
  - `char` → `Character`

```java
Object obj = new Integer(5);
```


### Type Wrapping (Autoboxing, Unboxing)

Java automatically converts between primitives and their wrapper classes.

```java
Integer obj = 10;      // autoboxing
int value = obj;       // unboxing
```



### Type Unwrapping

Manual extraction of primitive values from wrapper objects:

```java
Integer wrapped = Integer.valueOf(100);
int unwrapped = wrapped.intValue(); // manual unwrapping
```


### Variables (Declaration, Initialization, Scope)

Variables are containers for storing data values.


```java
int age;            // Declaration
age = 25;           // Initialization
int height = 180;   // Declaration + Initialization
```

#### Scope:
- **Local**: Inside methods, accessible only within.
- **Instance**: Declared inside class but outside methods.
- **Static**: Shared across all instances, declared with `static` keyword.

```java
public class Example {
    static int staticVar; // Class variable
    int instanceVar;      // Instance variable

    void method() {
        int localVar = 10; // Local variable
        System.out.println(localVar);
    }
}
```




### Operators (Arithmetic, Relational, Logical, Bitwise)

#### Arithmetic:
```java
+, -, *, /, %      // e.g., int result = a + b;
```

#### Relational:
```java
==, !=, >, <, >=, <=
```

#### Logical:
```java
&&, ||, !
```

#### Bitwise:
```java
&, |, ^, ~, <<, >>, >>>
```



### Control Statements (if, switch, loops)

#### Conditional Statements:
    
```java
if (x > 0) { 
    // Code for positive case
} else if (x == 0) { 
    // Code for zero case
} else { 
    // Code for negative case
}
```

#### Switch:
```java
switch(day) {
    case 1: System.out.println("Mon"); break;
    case 2: System.out.println("Tue"); break; // Added case for Tuesday
    case 3: System.out.println("Wed"); break; // Added case for Wednesday
    case 4: System.out.println("Thu"); break; // Added case for Thursday
    case 5: System.out.println("Fri"); break; // Added case for Friday
    case 6: System.out.println("Sat"); break; // Added case for Saturday
    case 7: System.out.println("Sun"); break; // Added case for Sunday
    ...
}
```

#### Loops:
```java
for (int i = 0; i < 10; i++) { }
while (x < 5) { 
    // Code for when x is less than 5
}
do { 
    // Code to execute at least once
} while (x < 5);
```
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break; // Exit loop when i is 5
    }
}
```
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        continue; // Skip the rest of the loop when i is 5
    }
}
```
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        return; // Exit the method when i is 5
    }
}
```
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        throw new Exception("Error at i = 5"); // Throw an exception when i is 5
    }
}
```


### Arrays (Single-Dimensional, Multi-Dimensional)

#### Single-Dimensional:
```java
int[] arr = {1, 2, 3};
```

#### Multi-Dimensional Arrays:
```java
int[][] matrix = {
    {1, 2},
    {3, 4}
};
```



### Strings (`String`, `StringBuilder`, `StringBuffer`)

- **String**: Immutable sequence of characters.
- **StringBuilder**: Mutable, faster (non-synchronized).
- **StringBuffer**: Mutable, thread-safe (synchronized).



### String Manipulation (`substring`, `indexOf`, `charAt`, etc.)

```java
String text = "Java";
text.substring(1);     // "ava"
text.indexOf("a");     // 1
text.charAt(2);        // 'v'
```



### String Formatting (`String.format`, `printf`)

```java
String name = "Alice";
String msg = String.format("Hello %s!", name);
System.out.printf("Hello %s!", name);
```



### String Comparison (`equals`, `==`, `compareTo`)

```java
str1.equals(str2);     // content comparison
str1 == str2;          // reference comparison
str1.compareTo(str2);  // lexicographic order
```



### String Immutability

Strings are immutable:
```java
String s = "Java";
s.concat("Lang");  // s remains "Java"
```



### String Pool

A special memory region where string literals are stored.

```java
String a = "hello";
String b = "hello";     // a and b refer to the same object in pool
```



### String Interning

Using `intern()` to manually put strings in the pool:

```java
String s1 = new String("hello");
String s2 = s1.intern(); // refers to pooled string
```



### StringBuilder vs StringBuffer

| Feature        | StringBuilder     | StringBuffer      |
|-||-|
| Thread-safe    | ❌ No             | ✅ Yes             |
| Performance    | ✅ Faster         | ⛔ Slower          |
| Mutability     | ✅ Mutable        | ✅ Mutable         |



### String Methods (`length`, `isEmpty`, `toUpperCase`, `toLowerCase`)

```java
str.length();
str.isEmpty();
str.toUpperCase();
str.toLowerCase();
```



### String Conversion (`toCharArray`, `toLowerCase`, `toUpperCase`)

```java
char[] chars = str.toCharArray();
str.toLowerCase();
str.toUpperCase();
```



### String Searching (`contains`, `startsWith`, `endsWith`)

```java
str.contains("Java");
str.startsWith("Hel");
str.endsWith("ld");
```



### String Splitting (`split`, `join`)

```java
String[] words = str.split(" ");
String joined = String.join("-", words);
```



### String Reversal

```java
String reversed = new StringBuilder(str).reverse().toString();
```



### String Replacement (`replace`, `replaceAll`)

```java
str.replace("a", "b");
str.replaceAll("\d", "*");
```



### String Trimming (`trim`)

```java
str.trim();  // Removes leading and trailing whitespace
```

### String Formatting (`String.format`, `printf`)

```java
String name = "Alice";
String msg = String.format("Hello %s!", name);
System.out.printf("Hello %s!", name);
```

## Java History, Features, and Evolution (Java 8 → Java 24+)

Java has evolved significantly since its inception, with major enhancements introduced in each release. Below is a breakdown of key features introduced from Java 8 onwards, focusing on productivity, performance, modern programming paradigms, and developer ergonomics.



### Java 8 (March 2014)
A major release focused on functional programming and modern API improvements.

- ✅ **Lambda Expressions**: Enables functional programming by allowing concise syntax for implementing functional interfaces.
  ```java
  // Example: Lambda Expression
  List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
  names.forEach(name -> System.out.println(name));
  ```

- ✅ **Stream API**: Simplifies data processing using functional-style operations.
  ```java
  // Example: Stream API
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
  List<Integer> evenNumbers = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
  ```

- ✅ **Functional Interfaces (`@FunctionalInterface`)**: Provides a clear contract for single-method interfaces.
  ```java
  @FunctionalInterface
  interface Greeting {
      void sayHello(String name);
  }
  ```

- ✅ **`Optional<T>` class**: Avoids `NullPointerException` by representing optional values.
  ```java
  // Example: Optional
  Optional<String> optional = Optional.ofNullable(null);
  System.out.println(optional.orElse("Default Value"));
  ```

- ✅ **New Date and Time API (`java.time`)**: Replaces the legacy `Date` and `Calendar` classes.
  ```java
  // Example: Date and Time API
  LocalDate today = LocalDate.now();
  System.out.println("Today's date: " + today);
  ```

- ✅ **Default and static methods in interfaces**: Allows adding methods to interfaces without breaking existing implementations.
  ```java
  interface Vehicle {
      default void start() {
          System.out.println("Vehicle is starting");
      }
  }
  ```

- ✅ **Nashorn JavaScript Engine**: Executes JavaScript code within Java applications.

- ✅ **Method References and Constructor References**: Simplifies lambda expressions.
  ```java
  // Example: Method Reference
  names.forEach(System.out::println);
  ```

📚 [Java 8 Features - Oracle](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)



### Java 9 (September 2017)
Focused on modularization and REPL support.

- ✅ **Java Platform Module System (JPMS)**: Introduced modular programming to improve application scalability and maintainability.
  ```java
  // Example: Module Declaration (module-info.java)
  module com.example.myapp {
      requires java.base;
  }
  ```

- ✅ **JShell (REPL for Java)**: Interactive tool for experimenting with Java code.
  ```shell
  jshell> int sum = 5 + 10;
  jshell> System.out.println(sum);
  ```

- ✅ **Improved Stream API**: Added methods like `takeWhile` and `dropWhile` for better control over streams.
  ```java
  // Example: takeWhile
  List<Integer> numbers = List.of(1, 2, 3, 4, 5);
  numbers.stream()
         .takeWhile(n -> n < 4)
         .forEach(System.out::println);
  ```

- ✅ **Private methods in interfaces**: Enables code reuse within interfaces.
  ```java
  interface Helper {
      private void log(String message) {
          System.out.println(message);
      }
  }
  ```

- ✅ **Stack-Walking API**: Provides a flexible mechanism for stack trace analysis.

- ✅ **Compact Strings**: Optimizes memory usage for `String` objects.

- ✅ **Multi-Release JARs**: Allows JAR files to include version-specific class files.



### Java 10 (March 2018)
Targeted type inference and performance improvements.

- ✅ **Local Variable Type Inference (`var`)**: Simplifies variable declarations.
  ```java
  // Example: var
  var message = "Hello, Java 10!";
  System.out.println(message);
  ```

- ✅ **Application Class-Data Sharing (AppCDS)**: Reduces startup time by sharing class metadata.

- ✅ **Root Certificates**: Improves security by including default root certificates.

- ✅ **Parallel Full GC for G1**: Enhances garbage collection performance.



### Java 11 (September 2018)
First LTS release after Java 8.

- ✅ **New String Methods**: Adds utility methods like `isBlank()`, `lines()`, and `strip()`.
  ```java
  // Example: String methods
  String text = "  Hello  ";
  System.out.println(text.strip()); // "Hello"
  ```

- ✅ **HTTP Client API (Standard)**: Simplifies HTTP requests.
  ```java
  // Example: HTTP Client
  HttpClient client = HttpClient.newHttpClient();
  HttpRequest request = HttpRequest.newBuilder()
                                   .uri(URI.create("https://example.com"))
                                   .build();
  HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
  System.out.println(response.body());
  ```

- ✅ **`var` in lambda parameters**: Improves readability in lambda expressions.

- ✅ **Flight Recorder and Mission Control (OpenJDK)**: Provides tools for performance monitoring.

- ✅ **Removal of Java EE and CORBA Modules**: Simplifies the JDK by removing outdated modules.



### Java 12 (March 2019)
Feature-focused release.

- ✅ **Switch Expressions (Preview)**: Simplifies switch statements.
  ```java
  // Example: Switch Expressions
  int day = 5;
  String result = switch (day) {
      case 1, 2, 3, 4, 5 -> "Weekday";
      case 6, 7 -> "Weekend";
      default -> "Invalid day";
  };
  ```

- ✅ **Shenandoah Garbage Collector (Experimental)**: Reduces GC pause times.

- ✅ **JVM Constants API**: Improves access to low-level JVM constants.

- ✅ **Microbenchmark Suite**: Provides tools for benchmarking Java code.



### Java 13 (September 2019)
Minor enhancements to improve developer productivity.

- ✅ **Text Blocks (Preview)**: Simplifies multi-line string literals.
  ```java
  // Example: Text Blocks
  String json = """
      {
          "name": "Alice",
          "age": 30
      }
      """;
  ```

- ✅ **Dynamic CDS Archives**: Improves startup performance.

- ✅ **Reimplement legacy Socket API**: Enhances maintainability of the networking stack.



### Java 14 (March 2020)

- ✅ Records (Preview)
- ✅ NullPointerException Enhancements (`getMessage()`)
- ✅ Pattern Matching for `instanceof` (Preview)
- ✅ Helpful NullPointerExceptions
- ✅ Packaging Tool (Incubator)



### Java 15 (September 2020)

- ✅ Sealed Classes (Preview)
- ✅ Hidden Classes (JVM support for frameworks)
- ✅ Text Blocks (Standard)
- ✅ Edwards-Curve Digital Signature Algorithm (EdDSA)



### Java 16 (March 2021)

- ✅ Records (Standard)
- ✅ Pattern Matching for `instanceof` (Standard)
- ✅ Vector API (Incubator)
- ✅ Strong encapsulation of JDK internals



### Java 17 (September 2021) – LTS

- ✅ Sealed Interfaces (Standard)
- ✅ Foreign Function & Memory API (Incubator)
- ✅ Pattern Matching for Switch (Preview)
- ✅ Removal of RMI Activation
- ✅ Deprecate Security Manager (JEP 411)



### Java 18 (March 2022)

- ✅ Simple Web Server (JEP 408)
- ✅ Code Snippets in Java SE Docs (JEP 413)
- ✅ UTF-8 by default
- ✅ Vector API (2nd Incubator)



### Java 19 (September 2022)

- ✅ Virtual Threads (Preview - JEP 425)
- ✅ Structured Concurrency (Incubator)
- ✅ Pattern Matching for Switch (2nd Preview)
- ✅ Foreign Function & Memory API (2nd Preview)



### Java 20 (March 2023)

- ✅ Virtual Threads (2nd Preview - JEP 436)
- ✅ Record Patterns (Preview - JEP 432)
- ✅ Foreign Function & Memory API (3rd Preview)



### Java 21 (September 2023) – LTS

- ✅ Virtual Threads (Standard - JEP 444)
- ✅ Record Patterns (Standard - JEP 440)
- ✅ Pattern Matching for Switch (Standard - JEP 441)
- ✅ Scoped Values (Preview - JEP 445)
- ✅ Sequenced Collections (JEP 431)



### Java 22 (March 2024)

- ✅ Unnamed Variables and Patterns (Preview - JEP 443)
- ✅ Stream Gatherers (Incubator - JEP 461)
- ✅ Foreign Function & Memory API (4th Preview - JEP 447)
- ✅ Implicit Classes and Instance Main Methods (Preview - JEP 463)



### Java 23 (Expected September 2024)

- ✅ Record Patterns (Refinements)
- ✅ Sealed Interfaces (Enhanced enforcement)
- ✅ Continued improvements to Pattern Matching
- ✅ Finalization of Memory & Foreign API (expected)



### Java 24 (Expected March 2025)

- ✅ Virtual Threads and Structured Concurrency (Finalized)
- ✅ Continued enhancements to developer productivity
- ✅ Expanded Preview of Stream API enhancements and project Valhalla deliverables



✅ Java continues to evolve with a focus on **performance**, **developer productivity**, and **modern language features** like **pattern matching**, **records**, and **virtual threads**, maintaining backward compatibility and enterprise support through LTS releases.

📌 Stay updated via:
- [OpenJDK JEP Index](https://openjdk.org/jeps/0)
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)

## JVM, JRE, JDK – Differences & Roles
---

### JVM (Java Virtual Machine)
- **Role**: Executes Java bytecode
- **Components**:
  - Class Loader
  - Execution Engine
  - Garbage Collector
- **Memory Areas**:
  - Method Area
  - Heap Area
  - Stack Area
  - PC Register
  - Native Method Stack

---

### JRE (Java Runtime Environment)
- **Role**: Provides runtime environment for Java applications
- **Components**:
  - JVM
  - Core libraries
  - Supporting files

---

### JDK (Java Development Kit)
- **Role**: Development kit for building Java applications
- **Components**:
  - JRE
  - Development tools (`javac`, `jar`, `javadoc`, etc.)
- **Differences**:
  - **JDK** ⊃ **JRE** ⊃ **JVM**

---

## Java Program Execution Flow
---

1. **Compilation**:  
   `.java` source files → compiled into `.class` bytecode by `javac`.

2. **Class Loading**:  
   JVM loads bytecode via Class Loader subsystem.

3. **Bytecode Verification**:  
   Ensures bytecode is safe and conforms to JVM standards.

4. **Execution**:  
   Bytecode is executed by the JVM using the interpreter or JIT compiler.

---

## Bytecode and .class Files

- **Bytecode**:  
  Intermediate, platform-independent code executed by the JVM.

- **`.class` Files**:  
  Binary files containing compiled bytecode generated by `javac`.

---

## Java Development Kit (JDK) Installation
---

### Prerequisites:
- Check supported OS and hardware requirements
- Download JDK from:
  - [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
  - [OpenJDK](https://jdk.java.net/)

### Installation Steps:
1. Install JDK
2. Set Environment Variables:
   - `JAVA_HOME`
   - Add `%JAVA_HOME%/bin` to `PATH`
3. Verify Installation:
   ```bash
   java -version
   javac -version
   ```
4. Configure IDE (Eclipse, IntelliJ IDEA, NetBeans)
5. Create and run a "Hello, World!" Java program
6. Debug common setup issues
7. Regularly update JDK to stay current

---

## Java IDEs (Eclipse, IntelliJ IDEA, NetBeans)
---

### Overview:
Popular Java IDEs boost productivity by offering features like:
- Syntax highlighting
- Code completion
- Debugging tools
- Build tools integration
- Refactoring support

---

### Eclipse
- **Features**: 
  - Rich plugin ecosystem
  - Advanced debugging tools
  - Code navigation and refactoring
  - Maven and Gradle support
- **Installation**: 
  - Download “Eclipse IDE for Java Developers” from [Eclipse Downloads](https://www.eclipse.org/downloads/).
- **Setup**:
  1. Install Eclipse and launch it.
  2. Create a new Java Project.
  3. Write and run a Java program.
  4. Set breakpoints and debug.
- **Unique Features**:
  - Highly customizable with plugins.
  - Supports multiple programming languages.
- **Troubleshooting**:
  - **Issue**: Workspace issues  
    **Solution**: Reset workspace or create a new one.
  - **Issue**: Plugin conflicts  
    **Solution**: Disable or update conflicting plugins.

---

### IntelliJ IDEA
- **Features**: 
  - Smart code suggestions and inspections
  - Built-in Git integration
  - Maven/Gradle support
  - Advanced refactoring tools
- **Installation**: 
  - Download Community or Ultimate Edition from [JetBrains](https://www.jetbrains.com/idea/download/).
- **Setup**:
  1. Install IntelliJ IDEA and launch it.
  2. Create a new project and select the JDK version.
  3. Configure the project structure.
  4. Write and run your first Java program.
- **Unique Features**:
  - Intelligent auto-complete and code analysis.
  - Seamless integration with version control systems.
- **Troubleshooting**:
  - **Issue**: Performance lag  
    **Solution**: Enable Power Save mode or increase heap size.
  - **Issue**: Configuration mismatch  
    **Solution**: Verify JDK path in project settings.

---

### NetBeans
- **Features**: 
  - Easy-to-use GUI designer
  - Built-in profiler
  - Maven/Gradle support
  - Code templates and debugging tools
- **Installation**: 
  - Download from [Apache NetBeans](https://netbeans.apache.org/download/).
- **Setup**:
  1. Install NetBeans and launch it.
  2. Create a new Java Application.
  3. Write and run your first Java program.
  4. Use the built-in debugger for troubleshooting.
- **Unique Features**:
  - Simplified GUI development.
  - Excellent support for beginners.
- **Troubleshooting**:
  - **Issue**: JDK path not detected  
    **Solution**: Set the correct JDK path in NetBeans settings.
  - **Issue**: Out-of-memory errors  
    **Solution**: Increase IDE heap size in configuration files.

---

### IDE Comparison

| Feature            | Eclipse           | IntelliJ IDEA       | NetBeans          |
|--------------------|-------------------|---------------------|-------------------|
| Performance        | Moderate          | High                | Moderate          |
| Usability          | Medium            | High                | Medium            |
| Plugin Support     | Extensive         | Rich & curated      | Moderate          |
| Git Integration    | Good              | Excellent           | Good              |
| Build Tool Support | Maven, Gradle     | Maven, Gradle       | Maven, Gradle     |
| Cost               | Free              | Free (Community)    | Free              |
| Community Support  | Large             | Large               | Moderate          |
| GUI Development    | Limited           | Limited             | Excellent         |

---

### Choosing the Right IDE
- **Beginners**: Start with IntelliJ IDEA Community Edition or NetBeans for simplicity.
- **Enterprise Development**: IntelliJ IDEA Ultimate or Eclipse for advanced features.
- **GUI Development**: NetBeans for its built-in GUI designer.

---

### Customization & Best Practices
- Use themes like Dracula or Solarized for better readability.
- Set up code formatting rules to maintain consistency.
- Learn shortcuts (e.g., `Ctrl+Shift+F` to format code).
- Enable autosave and autosuggestions for efficiency.
- Regularly update the IDE and plugins to avoid compatibility issues.

---

### Common Issues
- **Installation Errors**: Ensure system requirements are met and download the correct version.
- **JDK Path Not Detected**: Verify `JAVA_HOME` and IDE settings.
- **Out-of-Memory Errors**: Increase IDE heap size in configuration files.
- **UI Freezing**: Disable unnecessary plugins or extensions.

---

### Resources
- [Eclipse Documentation](https://help.eclipse.org/)
- [IntelliJ Documentation](https://www.jetbrains.com/idea/documentation/)
- [NetBeans Documentation](https://netbeans.apache.org/kb/)
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)

