# 15. Immutable Collections in Java

## 🌐 Overview
Immutable collections are collections whose elements cannot be added, removed, or changed once created. They provide thread-safety, simplicity, and integrity by preventing accidental modifications. 

Java introduced built-in support for immutable collections in **Java 9** using factory methods like `List.of()`, `Set.of()`, and `Map.of()`.

---

## 🔗 Why Use Immutable Collections?
- Prevent accidental modification.
- Make code easier to reason about.
- Improve thread safety without synchronization.
- Useful in concurrent programming and API design.
- Reduce memory overhead in certain scenarios.
- Support functional programming paradigms.
- Guarantee consistent state across application lifecycle.

---

## 🔄 Creating Immutable Collections

### 1. Java 9+ Factory Methods
```java
List<String> immutableList = List.of("A", "B", "C");
Set<Integer> immutableSet = Set.of(1, 2, 3);
Map<String, Integer> immutableMap = Map.of("one", 1, "two", 2);

// For larger maps (>10 entries)
Map<String, Integer> largeMap = Map.ofEntries(
    Map.entry("one", 1),
    Map.entry("two", 2),
    Map.entry("three", 3)
    // Add as many as needed
);
```

### 2. `Collections.unmodifiableXXX()` (Java 1.2+)
```java
List<String> modifiable = new ArrayList<>();
modifiable.add("X");
List<String> immutable = Collections.unmodifiableList(modifiable);
```

> Note: Changes in the original collection affect the unmodifiable one.

### 3. Collectors to Immutable Collections (Java 10+)
```java
List<String> immutableList = stream.collect(Collectors.toUnmodifiableList());
Set<String> immutableSet = stream.collect(Collectors.toUnmodifiableSet());
Map<String, Integer> immutableMap = stream.collect(Collectors.toUnmodifiableMap(
    k -> k, // key mapper
    v -> v.length() // value mapper
));
```

### 4. Guava's Immutable Collections (Third Party)
```java
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableMap;

ImmutableList<String> guavaList = ImmutableList.of("A", "B", "C");
ImmutableSet<Integer> guavaSet = ImmutableSet.of(1, 2, 3);
ImmutableMap<String, Integer> guavaMap = ImmutableMap.of("one", 1, "two", 2);

// Builder pattern for complex construction
ImmutableList<String> builtList = ImmutableList.<String>builder()
                                  .add("A")
                                  .addAll(otherCollection)
                                  .add("Z")
                                  .build();
```

---

## 📆 Best Practices
- Favor `List.of()` over `Collections.unmodifiableList()` where supported.
- Avoid modifying the original collection after wrapping it.
- Use immutable collections when returning from APIs.
- Document immutability in method contracts.
- Ensure your collection elements are also immutable for true immutability.
- Consider memory usage - immutable collections may have optimized memory footprints.
- Use defensive copying when necessary to protect against mutation.
- Consider using the `final` keyword with immutable collection fields.

---

## ⚖️ Comparison Table

| Feature                    | `Collections.unmodifiableList()` | `List.of()`        | Guava ImmutableList |
|---------------------------|----------------------------------|--------------------|---------------------|
| Java Version              | Java 1.2+                        | Java 9+            | Third-Party         |
| Truly Immutable           | No                               | Yes                | Yes                 |
| Allows Nulls              | Yes                              | No                 | No                  |
| Backed by Original List   | Yes                              | No                 | No                  |
| Thread-safe               | Yes (if original is thread-safe) | Yes                | Yes                 |
| Serializable              | Depends                          | Yes                | Yes                 |
| Memory Optimization       | No                               | Yes                | Yes                 |
| Builder Pattern           | No                               | No                 | Yes                 |

---

## 🪡 Advanced Topics

### 1. Defensive Copies
```java
public class UserProfile {
    private final List<String> roles;

    public UserProfile(List<String> roles) {
        this.roles = List.copyOf(roles); // Defensive copy
    }

    public List<String> getRoles() {
        return roles; // Safe to return directly since it's immutable
    }
}
```

### 2. Deep vs. Shallow Immutability
- **Shallow**: Collection is immutable, elements may not be.
- **Deep**: Collection and its elements are immutable.

```java
// Shallow immutability - problematic
List<Date> dates = List.of(new Date(), new Date());
dates.get(0).setTime(0); // Allowed! The Date object is mutable

// Deep immutability - safer
List<Instant> instants = List.of(Instant.now(), Instant.now());
// Instant is immutable, so no elements can be changed
```

### 3. Immutability in Streams
```java
List<String> names = List.of("Alice", "Bob", "Charlie");
List<String> upper = names.stream()
                          .map(String::toUpperCase)
                          .collect(Collectors.toUnmodifiableList());
```

### 4. Performance Considerations
Immutable collections often have performance advantages:
- No need for thread synchronization
- Potential for structural sharing in persistent data structures
- Optimized for read-heavy operations

```java
// Benchmark comparing mutable vs immutable list lookups
public void benchmarkLookup() {
    // Create collections
    ArrayList<Integer> mutableList = new ArrayList<>();
    for (int i = 0; i < 1000000; i++) mutableList.add(i);
    
    List<Integer> immutableList = List.copyOf(mutableList);
    
    // Lookup benchmarks show immutable collections can be faster
    // for read operations due to optimization and no need for
    // concurrency checks
}
```

### 5. Custom Immutable Collection Implementation
```java
public final class ImmutableIntList {
    private final int[] values;

    public ImmutableIntList(int[] initialValues) {
        this.values = Arrays.copyOf(initialValues, initialValues.length);
    }

    public int get(int index) {
        return values[index];
    }

    public int size() {
        return values.length;
    }

    public ImmutableIntList concat(ImmutableIntList other) {
        int[] result = new int[this.values.length + other.values.length];
        System.arraycopy(this.values, 0, result, 0, this.values.length);
        System.arraycopy(other.values, 0, result, this.values.length, other.values.length);
        return new ImmutableIntList(result);
    }
}
```

### 6. Handling Mutable Objects in Immutable Collections
When dealing with mutable objects in immutable collections:

```java
// Problem: Mutable objects in immutable collections
List<StringBuilder> builders = List.of(
    new StringBuilder("Hello"),
    new StringBuilder("World")
);

// Still mutable:
builders.get(0).append("!"); // Allowed! Mutates the StringBuilder

// Solution: Use unmodifiable wrappers around mutable objects
class ImmutableStringBuilder {
    private final String value;
    
    public ImmutableStringBuilder(StringBuilder sb) {
        this.value = sb.toString();
    }
    
    public String getValue() {
        return value;
    }
}

List<ImmutableStringBuilder> safeBuilders = List.of(
    new ImmutableStringBuilder(new StringBuilder("Hello")),
    new ImmutableStringBuilder(new StringBuilder("World"))
);
```

### 7. Memory Optimization in Java 9+ Immutable Collections
Java 9's immutable collections are optimized for memory:
- Small lists (0-2 elements) use specialized implementations
- Compact array storage
- No need for capacity management or load factors

---

## 🤝 Use Cases
- Caching static data.
- Configuration values.
- Constant lookup tables.
- Sharing collections across threads.
- Designing robust, fail-safe APIs.
- Value objects in domain-driven design.
- Data transfer objects (DTOs) in service layers.
- Functional programming paradigms requiring persistent data structures.
- High-performance read-only data structures in memory-constrained environments.

---

## ❓ Interview Questions and Answers

### Q1: What is the difference between `unmodifiableList()` and `List.of()`?
**A:** `unmodifiableList()` wraps a mutable list but reflects its changes; `List.of()` returns a truly immutable collection and throws exceptions on mutation. Additionally, `List.of()` doesn't accept null elements while `unmodifiableList()` permits them if the underlying list allows nulls.

### Q2: Are Java 9 `List.of()` collections thread-safe?
**A:** Yes, since they're immutable, they are inherently thread-safe. No synchronization is needed for read operations, and modify operations throw exceptions.

### Q3: Can `null` be used in `List.of()`?
**A:** No. `List.of(null)` throws `NullPointerException`, as Java 9+ immutable collections don't permit null elements.

### Q4: How do immutable collections help in multi-threaded environments?
**A:** They eliminate the need for synchronization as their state can't be changed, avoiding race conditions and ensuring consistent views across threads without locks.

### Q5: How to return immutable collections from a method?
**A:** Use `List.copyOf(input)` or `List.of(...)` for creating immutable return values. This prevents callers from modifying internal state.

### Q6: What's the performance benefit of immutable collections?
**A:** They avoid defensive copying in concurrent reads, which can lead to faster and safer code in multi-threaded applications. They also enable certain optimizations in memory usage and processing.

### Q7: How would you handle a situation where you need to modify an immutable collection?
**A:** Create a new immutable collection with the desired changes:

```java
List<String> original = List.of("A", "B", "C");
// To "modify" by adding "D":
List<String> modified = Stream.concat(
    original.stream(), 
    Stream.of("D")
).collect(Collectors.toUnmodifiableList());
```

### Q8: What's the difference between shallow and deep immutability?
**A:** Shallow immutability means the collection itself cannot be modified, but its elements might be mutable. Deep immutability ensures both the collection and all its elements are immutable.

### Q9: How do immutable collections contribute to functional programming in Java?
**A:** They align with functional programming principles by ensuring data doesn't change after creation. This enables referential transparency, making code easier to reason about and test.

### Q10: Explain the error: "java.lang.UnsupportedOperationException" when working with immutable collections.
**A:** This exception is thrown when attempting to modify an immutable collection. It occurs with methods that would change the collection's state like add(), remove(), or set().

### Q11: How would you implement a cache using immutable collections?
**A:** For a simple cache:
```java
public class ImmutableCache<K, V> {
    private final Map<K, V> cache;
    
    public ImmutableCache(Map<K, V> initialData) {
        this.cache = Map.copyOf(initialData);
    }
    
    public V get(K key) {
        return cache.get(key);
    }
    
    // To update, create a new cache instance
    public ImmutableCache<K, V> put(K key, V value) {
        Map<K, V> newMap = new HashMap<>(this.cache);
        newMap.put(key, value);
        return new ImmutableCache<>(newMap);
    }
}
```

### Q12: What are the memory implications of immutable collections in Java?
**A:** Java 9+ immutable collections often use less memory than their mutable counterparts because they:
1. Don't need extra capacity for future growth
2. Have specialized implementations for small collections
3. Don't need to store modcount fields for fail-fast iterators
4. Can use more compact internal representations

### Q13: How would you convert between different types of immutable collections?
**A:**
```java
// List to Set
List<String> immutableList = List.of("A", "B", "C");
Set<String> immutableSet = Set.copyOf(immutableList);

// Set to List
Set<String> set = Set.of("X", "Y", "Z");
List<String> list = List.copyOf(set);

// Collection to Map
List<User> users = List.of(new User("1", "Alice"), new User("2", "Bob"));
Map<String, User> userMap = users.stream()
    .collect(Collectors.toUnmodifiableMap(User::getId, u -> u));
```

### Q14: How do Java 9+ immutable collections handle iteration compared to Collections.unmodifiableList()?
**A:** Both provide fail-fast semantics for modification during iteration. However, Java 9+ collections may offer more efficient iteration since they're designed to be immutable from the ground up, while `unmodifiableList()` adds a wrapper layer that performs checks.

### Q15: How would you design an API that needs to return a collection that might be empty?
**A:** Use empty immutable collections rather than null:
```java
public List<Customer> getCustomersByRegion(String region) {
    // If no customers found
    if (noCustomersInRegion(region)) {
        return List.of(); // Empty immutable list - better than null
    }
    // Otherwise return found customers
    return customersInRegion.stream()
        .collect(Collectors.toUnmodifiableList());
}
```

### Q16: How do immutable collections behave with serialization?
**A:** Java 9+ immutable collections are serializable in a space-efficient manner. When deserialized, they maintain their immutability guarantees. However, if you serialize a `Collections.unmodifiableList()`, you need to ensure the backing collection is also serializable.

### Q17: What's the difference between Guava's ImmutableList and Java's List.of()?
**A:** 
1. Guava provides a builder pattern for more complex construction
2. Guava has been available since Java 6, while List.of() requires Java 9+
3. Guava offers specialized collections like ImmutableSortedMap and ImmutableBiMap
4. List.of() is part of the JDK, requiring no external dependencies

---

## 📚 Advanced Implementation Patterns

### 1. Copy-on-Write Collections

```java
public class CopyOnWriteList<T> {
    private List<T> immutableList = List.of();

    public synchronized void add(T element) {
        List<T> newList = new ArrayList<>(immutableList);
        newList.add(element);
        immutableList = List.copyOf(newList);
    }

    public List<T> getSnapshot() {
        return immutableList; // Already immutable, safe to return
    }
}
```

### 2. Persistent Data Structures
Functional programming often uses persistent data structures, which are immutable collections that provide efficient "modification" operations by sharing structure:

```java
// Conceptual example (not standard Java)
public class PersistentList<T> {
    private final T head;
    private final PersistentList<T> tail;
    
    // Empty list singleton
    private static final PersistentList<?> EMPTY = new PersistentList<>();
    
    @SuppressWarnings("unchecked")
    public static <T> PersistentList<T> empty() {
        return (PersistentList<T>) EMPTY;
    }
    
    // Add to front - creates new list but shares tail structure
    public PersistentList<T> cons(T value) {
        return new PersistentList<>(value, this);
    }
    
    // Private constructors
    private PersistentList() {
        this.head = null;
        this.tail = null;
    }
    
    private PersistentList(T head, PersistentList<T> tail) {
        this.head = head;
        this.tail = tail;
    }
}
```

### 3. Immutable Collection Wrappers for Legacy APIs

```java
public class LegacyAPIAdapter {
    private final LegacyService service;
    
    public LegacyAPIAdapter(LegacyService service) {
        this.service = service;
    }
    
    // Make mutable results from legacy API immutable
    public List<Customer> getCustomers() {
        // Legacy API returns mutable ArrayList
        List<Customer> mutableList = service.getCustomerList();
        return List.copyOf(mutableList);
    }
}
```

---

> 🔗 Next: [Best Practices for Collections](./16_Best_Practices.md)
