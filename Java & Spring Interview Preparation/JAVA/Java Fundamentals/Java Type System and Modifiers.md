# Java Type System and Modifiers

## Type Modifiers (final, static, transient, volatile)
### 1. **final**: Used to declare constants or prevent method overriding.
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
### 2. **static**: Used to declare class-level variables or methods.
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
### 3. **transient**: Used to indicate that a field should not be serialized.
- **Purpose**: Prevents serialization of sensitive data.
- **Usage**: Applied to instance variables.
- **Effects**:
> A transient variable is not included in the serialized object.
> Useful for security-sensitive data (e.g., passwords).
- **Example**:
```java
transient int sensitiveData; // not serialized
```
### 4. **volatile**: Used to indicate that a variable's value may be changed by different threads.
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
### 5. **synchronized**: Used to control access to a method or block by multiple threads.
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
### 6. **native**: Used to indicate that a method is implemented in native code (e.g., C/C++).
- **Purpose**: Allows Java to call platform-specific code.
- **Usage**: Applied to methods.
- **Effects**:
> A native method is declared in Java but implemented in another language.
> Requires the Java Native Interface (JNI) to call native code.
- **Example**:
```java
native void nativeMethod(); // native method declaration
```
## 📝 **Summary Table**
| Modifier   | Purpose                                      | Usage                      | Effects                                      | Example                                   |
|------------|----------------------------------------------|---------------------------|----------------------------------------------|-------------------------------------------|
| final      | Prevents modification                        | Variable, Method, Class   | Immutable values, non-overridable methods/classes| final int MAX_VALUE = 100;               |
| static     | Belongs to class, not instance               | Variable, Method          | Shared across instances                      | static int staticVariable;                |
| transient  | Prevents serialization                       | Variable                  | Excluded from serialized object              | transient int sensitiveData;              |
| volatile   | Ensures visibility across threads            | Variable                  | Immediate visibility of changes              | volatile int sharedVariable;              |    
| synchronized| Controls access by multiple threads         | Method, Block             | Prevents thread interference                 | synchronized void synchronizedMethod() {}  |
| native     | Indicates native code implementation         | Method                    | Calls platform-specific code                 | native void nativeMethod();                |

## ✅ **Best Practices:**
- Use `final` for constants and immutable classes.
- Use `static` for utility methods and constants.
- Use `transient` for sensitive data in serialization.
- Use `volatile` for shared variables in multi-threaded environments.
- Use `synchronized` for critical sections in multi-threaded applications.


## Type System (Static vs Dynamic)

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
