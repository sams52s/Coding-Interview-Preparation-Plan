# 🧬 Generics in Java

Generics enable types (classes and methods) to operate on objects of various types while providing compile-time type safety.
They allow the definition of classes, interfaces, and methods with a placeholder for the type.

---

## 🔠 Type Parameters & Wildcards

- **Type Parameters**: Represent generic types using placeholders like `T`, `E`, `K`, `V`, etc.
- **Wildcards**: Represent unknown types using `?`
  - `<?>` : Unbounded wildcard
  - `<? extends T>` : Upper bound
  - `<? super T>` : Lower bound

```java
List<?> unknownTypeList;
List<? extends Number> numbers;
List<? super Integer> integers;
```

---

## 📏 Bounded Types

Use bounds to restrict the types that can be used as generic parameters.

```java
public <T extends Number> void printDouble(T num) {
    System.out.println(num.doubleValue());
}
```

---

## 🧪 Generic Methods & Classes

**Generic Method**:
```java
public <T> void printArray(T[] array) {
    for (T item : array) {
        System.out.println(item);
    }
}
```

**Generic Class**:
```java
public class Box<T> {
    private T value;
    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
```

---

## 🧾 Type Erasure

- Generics are implemented through **type erasure**.
- The generic type is replaced with `Object` or its bound at runtime.
- This ensures backward compatibility with legacy code.

```java
Box<String> stringBox = new Box<>();
Box<Integer> intBox = new Box<>();
// At runtime, both are just Box
```

### 🧯 Type Erasure Visualization

```text
Generic Code              →      After Compilation
---------------------           --------------------------
Box<String>               →      Box (with Object reference)
<T> get()                 →      Object get()
```

- At runtime, type information is not preserved.

---

## 🧩 Generic Interfaces

```java
public interface Processor<T> {
    void process(T input);
}
```

Can be implemented with or without specifying a type:
```java
public class StringProcessor implements Processor<String> {
    public void process(String input) {
        System.out.println(input);
    }
}
```

---

## 🏗️ Generic Constructors

A constructor can be generic even if the class is not:

```java
public class Demo {
    public <T> Demo(T data) {
        System.out.println(data);
    }
}
```

---

## 📦 Generic Collections

Collections in Java (e.g., `List`, `Map`) are designed using generics:

```java
List<String> names = new ArrayList<>();
Map<Integer, String> map = new HashMap<>();
```

Generics allow compile-time type checking and prevent class cast exceptions.

---

## 🧠 Type Inference

Java can often infer the generic type from context:

```java
Box<String> box = new Box<>();  // type inferred
```

With methods:
```java
public static <T> T identity(T value) {
    return value;
}

String str = identity("hello");
```

---

## 🧮 Generic Algorithms

Generic methods help create reusable algorithms:

```java
public <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

---

## 🔠 Generic Type Parameters

| Symbol | Meaning             |
|--------|----------------------|
| `T`    | Type                 |
| `E`    | Element (used in collections) |
| `K`    | Key                  |
| `V`    | Value                |
| `N`    | Number               |

---

## 📐 Generic Type Bounds

- **Upper Bound**: `T extends Number`
- **Lower Bound**: `T super Integer` (used with wildcards)
- Multiple bounds:

```java
<T extends Number & Comparable<T>>
```

---

## 🌀 Generic Type Wildcards

- `<?>` – Unbounded wildcard
- `<? extends T>` – Upper bounded wildcard (read-only)
- `<? super T>` – Lower bounded wildcard (write-only)

---

## 🔗 Generic Type Parameters with Multiple Bounds

```java
<T extends Number & Comparable<T>>
```

- The type must be a subtype of **Number** and implement **Comparable**.
- Only one class can be extended; interfaces can be multiple.

---

## 🛠️ Real-world Use Cases

- **DAO Classes**: Create reusable Data Access Objects with generic types.
- **Utility Libraries**: Collections, sorting, and transformation utilities.
- **REST API Responses**: Use `ResponseEntity<T>` in Spring Boot to handle different payload types.

---

## ⚠️ Limitations and Pitfalls

- Cannot use primitives directly: `List<int>` ❌ → Use `List<Integer>` ✅
- Cannot create arrays of parameterized types: `new T[]` is illegal
- Type information is erased at runtime (no `instanceof T`)
- Cannot instantiate type parameters: `new T()` ❌

---

## 🚫 Raw Types vs. Type-Safe Generics

Raw types skip compile-time type checks:

```java
List list = new ArrayList(); // raw type
list.add("text");
list.add(42); // no error at compile time
```

Using generics enforces safety:

```java
List<String> list = new ArrayList<>();
list.add("text");
// list.add(42); // compile-time error
```

---

## 🆕 Java Enhancements

- **Java 7**: Diamond operator (`new ArrayList<>()`)
- **Java 8**: Type inference in lambdas
- **Java 10**: `var` with generics in local variable declarations
- **Java 16+**: Pattern matching with `instanceof`

---

## Conclusion
Generics are a powerful feature in Java that enhances code reusability and type safety.
They allow developers to create flexible and type-safe data structures and algorithms. Understanding generics is crucial for writing modern Java applications.

## 📝 References
- [Java Generics Documentation](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)
- [Java Generics - GeeksforGeeks](https://www.geeksforgeeks.org/generics-in-java/)
- [Java Generics - Baeldung](https://www.baeldung.com/java-generics)