# Java Basics

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
