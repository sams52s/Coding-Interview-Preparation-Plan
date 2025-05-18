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

```java
public <T extends Number> void printDouble(T num) {
    System.out.println(num.doubleValue());
}
```

Multiple bounds:

```java
public <T extends Number & Comparable<T>> void process(T item) { }
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

---

## 🧩 Generic Interfaces

```java
public interface Processor<T> {
    void process(T input);
}
```

```java
public class StringProcessor implements Processor<String> {
    public void process(String input) {
        System.out.println(input);
    }
}
```

---

## 🏗️ Generic Constructors

```java
public class Demo {
    public <T> Demo(T data) {
        System.out.println(data);
    }
}
```

---

## 📦 Generic Collections

```java
List<String> names = new ArrayList<>();
Map<Integer, String> map = new HashMap<>();
```

---

## 🧠 Type Inference

```java
Box<String> box = new Box<>();  // Diamond operator
```

```java
public static <T> T identity(T value) {
    return value;
}

String str = identity("hello");
```

---

## 🧮 Generic Algorithms

```java
public <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

---

## 🌀 PECS Principle

```java
List<? extends Number> producers; // Read from
List<? super Integer> consumers;  // Write to
```

> **PECS** = *Producer Extends, Consumer Super*

---

## 🧪 Method Chaining with Generics

```java
public class Chain<T> {
    private T value;
    public Chain<T> set(T value) {
        this.value = value;
        return this;
    }
}
```

---

## 🔍 Generics and Reflection

```java
Type genericType = Box.class.getTypeParameters()[0];
System.out.println(genericType.getTypeName());
```

---

## 🚫 Limitations

- Cannot use primitives: `List<int>` ❌ → Use `List<Integer>` ✅  
- Cannot do: `new T()` or `T[]`  
- Cannot catch generic exceptions: `catch (T e)` ❌  
- Cannot create generic exception classes  

---

## 🧯 Generic Arrays Workaround

```java
@SuppressWarnings("unchecked")
T[] array = (T[]) new Object[10]; // Unsafe cast
```

---

## 📘 Generic in Frameworks

```java
public interface JpaRepository<T, ID extends Serializable> {
    Optional<T> findById(ID id);
}
```

---

## 🔠 Generic Type Parameters

| Symbol | Meaning                        |
|--------|--------------------------------|
| `T`    | Type                           |
| `E`    | Element (used in collections)  |
| `K`    | Key                            |
| `V`    | Value                          |
| `N`    | Number                         |

---

## 🚫 Raw Types vs. Type-Safe Generics

```java
List list = new ArrayList(); // raw type
list.add("text");
list.add(42);
```

```java
List<String> list = new ArrayList<>();
list.add("text");
// list.add(42); // compile-time error
```

---

## 🆕 Java Enhancements

- **Java 7**: Diamond operator  
- **Java 8**: Type inference with Lambdas  
- **Java 10**: `var` for local variables  
- **Java 16+**: Pattern matching enhancements  

---

## 💼 Interview Questions & Answers – Generics in Java

### ❓ Q1: What is the purpose of generics in Java?  
**✅ A:** Generics enable type safety and reusability. They allow classes, interfaces, and methods to operate on different data types while ensuring compile-time type checking.

---

### ❓ Q2: What is type erasure in generics?  
**✅ A:** Type erasure is the process by which generic type information is removed during compilation. This ensures backward compatibility but limits access to actual type parameters at runtime.

---

### ❓ Q3: What is the difference between `<? extends T>` and `<? super T>`?  
**✅ A:**  
- `<? extends T>`: Accepts T or any subclass – used when **reading** data (producer).  
- `<? super T>`: Accepts T or any superclass – used when **writing** data (consumer).

---

### ❓ Q4: Can we create arrays of generic types?  
**✅ A:** No, Java does not support `new T[]` due to type erasure. A workaround is using `Object[]` with unchecked casting, or using collections like `List<T>`.

---

### ❓ Q5: Why can't we use primitives with generics?  
**✅ A:** Java generics work only with reference types. Primitives like `int` or `double` must be wrapped using their wrapper classes like `Integer` or `Double`.

---

### ❓ Q6: How do generic methods differ from generic classes?  
**✅ A:** A generic method defines type parameters in the method signature and can be used independently of the class's type. A generic class defines type parameters at class level, affecting all its methods and fields.

---

### ❓ Q7: What is the PECS principle?  
**✅ A:** PECS stands for **Producer Extends, Consumer Super**. It’s a rule to decide when to use `extends` or `super` with wildcards in generics.

---

### ❓ Q8: Can you use instanceof with generics?  
**✅ A:** No. Due to type erasure, you cannot check the type parameter at runtime using `instanceof T`.

---

### ❓ Q9: What are raw types? Why are they discouraged?  
**✅ A:** Raw types refer to using a generic class or interface without specifying a type. They are discouraged because they bypass compile-time checks and may lead to runtime errors.

---

### ❓ Q10: Can we use static fields or methods with type parameters?  
**✅ A:** No, static fields and methods cannot refer to the type parameters of the generic class. You must declare type parameters within the method signature itself.

---

### ❓ Q11: What are bridge methods in Java Generics?
**✅ A:** Bridge methods are synthetic methods generated by the compiler to ensure type safety and polymorphism work correctly with generics after type erasure. For example:

```java
public class StringBox extends Box<String> {
    @Override
    public void set(String value) { /* implementation */ }
    // Compiler generates bridge method:
    // public void set(Object value) { set((String)value); }
}
```

### ❓ Q12: Explain recursive type bounds with an example.
**✅ A:** Recursive type bounds are used when a type parameter needs to be compared with or reference itself:

```java
public class RecursiveNode<T extends Comparable<T>> implements Comparable<RecursiveNode<T>> {
    private T value;
    
    @Override
    public int compareTo(RecursiveNode<T> other) {
        return this.value.compareTo(other.value);
    }
}
```

### ❓ Q13: How do you handle generic type inheritance?
**✅ A:** Generic type inheritance follows these rules:
```java
// If Base<T> is a superclass of Child<T>
class Child<T> extends Base<T> { }

// If you want to restrict T
class NumberChild<T extends Number> extends Base<T> { }

// If you want to fix the type parameter
class StringChild extends Base<String> { }
```

### ❓ Q14: What is type witness and when is it needed?
**✅ A:** A type witness is an explicit type argument provided to help the compiler infer types correctly:
```java
// Without type witness (compilation error)
List<String> list = Collections.emptyList();

// With type witness
List<String> list = Collections.<String>emptyList();
```

### ❓ Q15: How do you create immutable generic types?
**✅ A:**
```java
public final class ImmutableBox<T> {
    private final T value;
    
    public ImmutableBox(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
}
```

---

## 🎯 Advanced Generic Patterns

### Builder Pattern with Generics
```java
public class GenericBuilder<T> {
    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();
    
    public GenericBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }
    
    public <V> GenericBuilder<T> with(BiConsumer<T, V> consumer, V value) {
        modifiers.add(instance -> consumer.accept(instance, value));
        return this;
    }
    
    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        return value;
    }
}

// Usage
Person person = new GenericBuilder<>(Person::new)
    .with(Person::setName, "John")
    .with(Person::setAge, 25)
    .build();
```

### Type-Safe Heterogeneous Container
```java
public class TypeSafeMap {
    private Map<Class<?>, Object> container = new HashMap<>();
    
    public <T> void put(Class<T> type, T value) {
        container.put(Objects.requireNonNull(type), value);
    }
    
    public <T> T get(Class<T> type) {
        return type.cast(container.get(type));
    }
}

// Usage
TypeSafeMap map = new TypeSafeMap();
map.put(String.class, "Hello");
map.put(Integer.class, 42);
String s = map.get(String.class); // Type-safe retrieval
```

---

## 🔬 Advanced Generic Type Inference

```java
public class TypeInference {
    // Method with complex generic type inference
    public static <T> List<T> zip(List<? extends T> list1, List<? extends T> list2) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
            result.add(list1.get(i));
            result.add(list2.get(i));
        }
        return result;
    }
    
    // Usage showing type inference
    List<Number> numbers = zip(Arrays.asList(1, 2), Arrays.asList(3.14, 2.718));
}
```

## 📚 Generic Method Reference Examples

```java
public class MethodReferenceDemo<T> {
    private List<T> list = new ArrayList<>();
    
    public <R> List<R> map(Function<T, R> mapper) {
        return list.stream()
                  .map(mapper)
                  .collect(Collectors.toList());
    }
    
    // Usage with method reference
    public static void example() {
        MethodReferenceDemo<String> demo = new MethodReferenceDemo<>();
        List<Integer> lengths = demo.map(String::length);
    }
}
```

---

## 🚨 Common Compiler Errors

```java
List<Object> list = new ArrayList<String>(); // ❌ Type mismatch
```

```java
List<?> list = new ArrayList<String>();
list.add("test"); // ❌ Cannot add to a List<?> except null
```

---

## ✅ Summary

Generics increase flexibility, reduce redundancy, and ensure stronger type-checking at compile time.  
Mastering generics is essential for writing clean, modern, scalable Java code.

---

## 📝 References

- [Java Generics Documentation](https://docs.oracle.com/javase/tutorial/java/generics/index.html)  
- [Effective Java - Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)  
- [Baeldung Generics](https://www.baeldung.com/java-generics)  
- [GeeksforGeeks](https://www.geeksforgeeks.org/generics-in-java/)
