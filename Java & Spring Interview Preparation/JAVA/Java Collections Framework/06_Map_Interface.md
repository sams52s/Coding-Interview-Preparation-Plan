# 📘 Java `Map` Interface - A Deep Dive

## 📌 Introduction
The `Map<K, V>` interface is part of the Java Collections Framework but **does not extend** the `Collection` interface. It represents a **mapping between keys and values**, where **each key is unique** and maps to **at most one value**.

---

## 🔑 Key Characteristics
- Stores key-value pairs
- Keys must be unique; values can be duplicated
- Allows one `null` key and multiple `null` values (in `HashMap`)
- Not part of the `Collection` hierarchy

---

## 🧱 Common Implementations
| Implementation     | Characteristics | Best Use Case |
|--------------------|------------------|---------------|
| `HashMap`          | Unordered, allows null keys/values | General-purpose map |
| `LinkedHashMap`    | Maintains insertion order | Caches, ordered logs |
| `TreeMap`          | Sorted by key (natural/comparator) | Range searches |
| `Hashtable`        | Thread-safe (legacy) | Legacy systems |
| `ConcurrentHashMap`| Thread-safe, no locking full map | Multithreaded env |

---

## 🛠️ Core Operations

### 1. Adding Key-Value Pairs
```java
map.put("name", "Alice");
map.put("age", 30);
```

### 2. Accessing Values
```java
String name = map.get("name");
```

### 3. Checking Keys/Values
```java
map.containsKey("name");
map.containsValue("Alice");
```

### 4. Removing Entries
```java
map.remove("age");
```

### 5. Iterating Over Map
```java
for (Map.Entry<String, Object> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

---

## 🔁 View Collections
- `keySet()` → returns `Set<K>`
- `values()` → returns `Collection<V>`
- `entrySet()` → returns `Set<Map.Entry<K,V>>`

---

## 🔐 Thread Safety
- `HashMap` is not thread-safe
- Use `ConcurrentHashMap` or `Collections.synchronizedMap()`
```java
Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
```

---

## 📊 HashMap vs TreeMap vs LinkedHashMap
| Feature | `HashMap` | `TreeMap` | `LinkedHashMap` |
|--------|-----------|----------|------------------|
| Ordering | None | Sorted by key | Insertion order |
| Performance | O(1) | O(log n) | O(1) |
| Nulls | 1 null key, many null values | No null keys | 1 null key allowed |
| Use Case | Fast lookup | Sorted map | Predictable iteration |

---

## ⚙️ Advanced Features

### 1. Default Methods (Java 8+)
```java
map.getOrDefault("city", "Unknown");
map.forEach((k, v) -> System.out.println(k + v));
```

### 2. `compute()` Methods Family
```java
// Update existing or insert new value
map.compute("age", (k, v) -> (v == null) ? 1 : (Integer)v + 1);

// Only compute if key exists
map.computeIfPresent("visits", (k, v) -> v + 1);

// Only compute if key doesn't exist
map.computeIfAbsent("country", k -> "USA");
```

### 3. `merge()` Operations
```java
// Add/update entry with combining function
map.merge("visits", 1, Integer::sum);

// Conditional updates with null handling
map.merge("score", newScore, (oldVal, newVal) -> 
    oldVal > newVal ? oldVal : newVal);
```

### 4. Immutable Maps
```java
// Java 9+ factory methods
Map<String, String> immutable1 = Map.of("A", "Apple", "B", "Banana");
Map<String, String> immutable2 = Map.ofEntries(
    Map.entry("A", "Apple"),
    Map.entry("B", "Banana"),
    Map.entry("C", "Cherry")
);

// Java 10+ copy methods
Map<String, String> immutable3 = Collections.unmodifiableMap(new HashMap<>(originalMap));
```

### 5. Specialized Map Implementations

#### EnumMap
```java
enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY }
EnumMap<Day, String> schedule = new EnumMap<>(Day.class);
schedule.put(Day.MONDAY, "Work from home");
schedule.put(Day.FRIDAY, "Team lunch");
```

#### WeakHashMap
```java
// Keys are weakly referenced - garbage collected when no longer referenced elsewhere
Map<Object, String> weakMap = new WeakHashMap<>();
Object key = new Object();
weakMap.put(key, "value");
key = null; // After next GC, entry may disappear
```

#### IdentityHashMap
```java
// Uses reference equality (==) instead of equals()
Map<String, String> identityMap = new IdentityHashMap<>();
String key1 = new String("key");
String key2 = new String("key");
identityMap.put(key1, "value1");
identityMap.put(key2, "value2"); // Both keys stored despite having same content
```

### 6. Advanced Stream Operations with Maps
```java
// Collecting data into maps
Map<String, Integer> nameToLength = people.stream()
    .collect(Collectors.toMap(Person::getName, Person::getAge));

// Handling duplicate keys
Map<String, List<Person>> lastNameToPeople = people.stream()
    .collect(Collectors.groupingBy(Person::getLastName));

// Computing statistics
Map<Department, Double> deptToAvgSalary = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)));
```

### 7. Guava Library Extensions (Google)
```java
// BiMap - bidirectional map (key->value and value->key)
BiMap<String, Integer> codes = HashBiMap.create();
codes.put("apple", 1);
String fruit = codes.inverse().get(1); // Get key by value

// Multimap - multiple values per key
Multimap<String, String> directorToMovies = ArrayListMultimap.create();
directorToMovies.put("Nolan", "Inception");
directorToMovies.put("Nolan", "Interstellar");
Collection<String> nolanMovies = directorToMovies.get("Nolan");

// Table - a two-key map
Table<String, String, Double> cityTempByMonth = HashBasedTable.create();
cityTempByMonth.put("New York", "Jan", 32.0);
cityTempByMonth.put("New York", "Jul", 85.0);
cityTempByMonth.put("LA", "Jan", 65.0);
```

### 8. Performance Tuning
```java
// Pre-sizing maps for better performance
Map<String, User> userMap = new HashMap<>(10000, 0.75f);

// Using custom initial capacity and load factor
Map<String, Object> options = new HashMap<>(32, 0.6f);
```

---

## 🧠 Interview Questions

### Q1. Why is Map not part of the Collection hierarchy?
**A:** Because it uses a key-value mapping rather than a collection of individual elements, which doesn't align with the `Collection<E>` structure. Maps store entries (key-value pairs), not individual elements. This fundamental structural difference is why Map has its own interface hierarchy parallel to Collection.

### Q2. Difference between HashMap and Hashtable?
**A:** 
- **Thread safety**: HashMap is unsynchronized while Hashtable is synchronized
- **Null handling**: HashMap allows one null key and multiple null values; Hashtable doesn't allow null keys or values
- **Performance**: HashMap is generally faster due to lack of synchronization
- **Iteration**: HashMap uses fail-fast Iterator; Hashtable uses Enumeration (legacy) and Iterator
- **Heritage**: Hashtable is legacy (Java 1.0) while HashMap was introduced later (Java 1.2) with improved design

### Q3. How does HashMap maintain uniqueness of keys?
**A:** It uses the `hashCode()` and `equals()` methods:
1. When adding a key, its `hashCode()` is used to determine the bucket location
2. If the bucket has entries, `equals()` method compares the key with existing keys
3. If `equals()` returns true for any key, the new value replaces the old one
4. For proper functioning, objects used as keys should override both `hashCode()` and `equals()` consistently

### Q4. What happens if two keys have the same hash code?
**A:** This is called a hash collision and is handled through:
1. **Chaining**: In Java's HashMap, colliding entries are stored in a linked list (or balanced tree in Java 8+ if the list becomes too long)
2. First, the bucket is located using `hashCode()`
3. Then, the linked list/tree is searched using `equals()`
4. In Java 8+, if a bucket contains 8+ entries, the linked list converts to a balanced tree (red-black) for O(log n) vs O(n) lookup
5. If the bucket size reduces to 6, it reverts to a linked list

### Q5. When to use TreeMap?
**A:** Use TreeMap when:
- You need keys sorted in natural order or by a custom Comparator
- You need range-view operations like `subMap()`, `headMap()`, `tailMap()`
- You need to find the closest matches to a key with methods like `floorKey()`, `ceilingKey()`
- You need the first or last entry in the map
- You're willing to accept O(log n) performance for insertion and retrieval operations
- You need guaranteed O(log n) worst-case performance (vs HashMap's O(n) worst-case)

### Q6. How is ConcurrentHashMap different from HashMap?
**A:** ConcurrentHashMap offers:
- **Thread safety** without locking the entire map (segment/bucket-level locking)
- **No locking for reads** and only minimal locking for writes
- **No ConcurrentModificationException** during iteration
- **Atomic compound operations** like `putIfAbsent()`, `replace()`
- **Null prohibition** - doesn't allow null keys or values
- **Weakly consistent iterators** that reflect state at some point during iteration
- **Better scalability** with multiple threads than Collections.synchronizedMap()
- **Segment-based locking** (pre-Java 8) or **node-based locking** (Java 8+)

### Q7. What is the load factor in HashMap?
**A:** Load factor is a measure that determines when to resize the hash table:
- Default value is 0.75 (75% full)
- Lower values decrease collision probability but increase space requirements
- Higher values optimize space but increase collision probability and lookup times
- Resize operation creates a new array with double capacity and rehashes all entries
- Formula: if (size > capacity * loadFactor) then resize()

### Q8. Explain the internal working of HashMap in Java 8+.
**A:** In Java 8+:
1. Data structure is array of nodes (buckets)
2. Each node can be start of a linked list or a red-black tree
3. When a bucket has 8+ entries, linked list converts to tree (TREEIFY_THRESHOLD)
4. When a bucket shrinks to 6 entries, tree converts back to list (UNTREEIFY_THRESHOLD)
5. Default capacity is 16, always a power of 2
6. Hash function: (h = key.hashCode()) ^ (h >>> 16) - XORs high bits with low bits
7. Index calculation: hash & (n-1) where n is capacity
8. Resize doubles capacity and rehashes all elements

### Q9. What problems might occur if you don't override hashCode() when overriding equals()?
**A:** Breaking the hashCode contract can cause:
- Two equal objects might end up in different buckets
- HashMap/HashSet would treat equal objects as different
- Objects that should be found with get() will be "lost" in the map
- Potential duplicate keys in maps contrary to the unique key contract
- The contract states: equal objects must have equal hash codes

### Q10. When would you choose LinkedHashMap over HashMap?
**A:** Choose LinkedHashMap when:
- You need predictable iteration order (insertion-order or access-order)
- You're implementing an LRU cache (with accessOrder=true)
- You want to retrieve elements in the same order they were inserted
- You need the performance of HashMap but with ordered iteration
- You're processing entries in a specific sequence

### Q11. Explain the difference between fail-fast and fail-safe iterators.
**A:**
- **Fail-fast** (HashMap, ArrayList): Throw ConcurrentModificationException if collection is modified during iteration outside the iterator's methods
- **Fail-safe** (ConcurrentHashMap, CopyOnWriteArrayList): Work on a copy of the collection, allowing concurrent modifications without exceptions but potentially not reflecting the latest state

### Q12. How would you implement a custom key for a HashMap?
**A:** For a proper custom key:
1. Override `equals()` based on the object's logical equality requirements
2. Override `hashCode()` consistent with equals() (equal objects must have equal hash codes)
3. Make the key immutable to prevent hash code changes after insertion
4. Consider caching the hash code if computation is expensive
5. Distribute hash codes well to minimize collisions
6. Consider using Objects.hash() or similar utility methods

Example:
```java
public class EmployeeKey {
    private final String id;
    private final String department;
    
    public EmployeeKey(String id, String department) {
        this.id = id;
        this.department = department;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeKey)) return false;
        EmployeeKey that = (EmployeeKey) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(department, that.department);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, department);
    }
}
```

### Q13. How does WeakHashMap differ from HashMap?
**A:** WeakHashMap:
- Holds keys using weak references, allowing them to be garbage collected when no strong references exist
- Used for implementing caches or mappings where memory conservation is critical
- When a key is garbage collected, its entry is eventually removed
- Has slightly higher overhead due to reference management
- Will not prevent memory leaks caused by values (only keys are weakly referenced)
- Is useful for resource management where you don't want the map to prevent objects from being collected

### Q14. What are the trade-offs between HashMap, TreeMap and LinkedHashMap?
**A:**

| Aspect | HashMap | TreeMap | LinkedHashMap |
|--------|---------|---------|---------------|
| Time Complexity | O(1) avg case | O(log n) | O(1) avg case |
| Space Complexity | Higher than TreeMap | Lower than HashMap | Highest (extra linked list) |
| Ordering | None | Sorted by key | Insertion or access order |
| Null Keys | One allowed | None allowed | One allowed |
| Iteration Speed | Faster than TreeMap | Slower | Fastest |
| Memory Overhead | Medium | Low | High |
| Use Case | Fast lookup | Sorted data | Ordered access/LRU cache |

### Q15. Explain internal resizing in HashMap and its performance implications.
**A:** Resizing in HashMap:
1. Triggered when size > capacity × loadFactor
2. Creates new array with double capacity
3. Rehashes all existing entries to new locations
4. Time complexity is O(n) where n is number of entries
5. Can cause temporary performance degradation
6. May trigger multiple resizing operations in batch insertions
7. Often better to pre-size HashMap if final size is known
8. In Java 8+, linked lists may be converted to trees during rehashing

---

## 🔍 Advanced Map Concepts

### 1. Custom Sorting in TreeMap
```java
// Sort by value length
TreeMap<String, String> customSorted = new TreeMap<>((s1, s2) -> 
    s1.length() - s2.length());
    
// Sort by multiple criteria
TreeMap<Person, Data> personMap = new TreeMap<>(
    Comparator.comparing(Person::getLastName)
              .thenComparing(Person::getFirstName)
              .thenComparingInt(Person::getAge));
```

### 2. Memory-Sensitive Map Implementations
```java
// Lower memory footprint with primitive specializations (Trove, Eclipse Collections)
TIntObjectMap<String> efficientMap = new TIntObjectHashMap<>();
efficientMap.put(1, "one");

// Compact maps for small data
Map<String, Integer> smallMap = Collections.singletonMap("key", 1);
```

### 3. Thread-Safe Map Patterns
```java
// Read-heavy concurrent access
Map<String, Data> readOptimizedMap = new ConcurrentHashMap<>();

// Write-heavy with full thread isolation
ThreadLocal<Map<String, Object>> threadLocalMap = ThreadLocal.withInitial(HashMap::new);

// Copy-on-write pattern for rare updates
Map<String, User> cowMap = new CopyOnWriteArrayMap<>();

// Concurrent with Custom Concurrency Level
ConcurrentHashMap<String, Data> customConcurrencyMap = 
    new ConcurrentHashMap<>(16, 0.75f, 32); // 32 concurrent threads
```

### 4. Event Notifications on Map Changes
```java
// Using Observable pattern with map
class ObservableMap<K, V> extends HashMap<K, V> {
    private List<MapChangeListener<K, V>> listeners = new ArrayList<>();
    
    public void addListener(MapChangeListener<K, V> listener) {
        listeners.add(listener);
    }
    
    @Override
    public V put(K key, V value) {
        V oldValue = super.put(key, value);
        listeners.forEach(l -> l.onPut(key, value, oldValue));
        return oldValue;
    }
    
    // Similar overrides for remove, clear, etc.
}
```

---

## 📘 Summary
The `Map` interface is central to Java's key-value data structures. Modern Java has enhanced maps with stream operations, functional methods, and specialized implementations. When selecting a map implementation, consider:

- Ordering requirements (unsorted, insertion-order, or sorted)
- Thread safety needs (synchronized, concurrent, or unsynchronized)
- Performance characteristics (time vs. space tradeoffs)
- Special features like weak references, bi-directional mapping, or multi-value support
- Memory constraints and collection size

Understanding the underlying implementation details is crucial for maximizing performance and reliability in production systems.

> 🔗 Next: [Iterator and Iterable](./07_Iterator_and_Iterable.md)
