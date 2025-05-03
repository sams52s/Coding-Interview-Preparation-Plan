# 17. Java Collections - Interview Questions & Answers

## ✨ Overview
This document compiles the most common and advanced interview questions on Java Collections Framework, organized by topic, with concise answers and code examples where applicable.

---

## 🤔 Basic Questions

### Q1: What is the Java Collections Framework?
**A:** It is a unified architecture that provides interfaces and classes to store and manipulate groups of data as a single unit.

### Q2: What is the difference between Collection and Collections?
**A:** `Collection` is the root interface in the framework. `Collections` is a utility class that provides static methods for sorting, searching, and manipulating collections.

### Q3: Why doesn't `Map` extend `Collection`?
**A:** Because `Map` deals with key-value pairs rather than individual elements like other collections.

---

## 🔢 Intermediate Questions

### Q4: Difference between ArrayList and LinkedList?
| Feature        | ArrayList          | LinkedList           |
|----------------|---------------------|------------------------|
| Access time    | Fast (O(1))         | Slow (O(n))            |
| Insertion/Del  | Slow (O(n))         | Fast at ends (O(1))    |
| Memory         | Less overhead       | More (due to pointers) |

### Q5: How does HashSet ensure uniqueness?
**A:** It uses `hashCode()` and `equals()` methods internally with a `HashMap` to prevent duplicates.

### Q6: Can we store null in a Collection?
**A:** Yes. Most collections allow null, except TreeMap (key) and TreeSet (elements if not comparable).

### Q7: How is fail-fast behavior achieved in iterators?
**A:** Through a modCount variable in collections which throws `ConcurrentModificationException` when modified outside the iterator.

---

## 🤝 Advanced Questions

### Q8: Difference between HashMap and ConcurrentHashMap?
| Feature               | HashMap        | ConcurrentHashMap     |
|------------------------|----------------|------------------------|
| Thread Safety          | No             | Yes                    |
| Null Key/Value         | One null key   | Null keys not allowed  |
| Performance (concurrent)| Poor           | High (segment locking) |

### Q9: What is the time complexity of HashMap operations?
**A:** Average-case: O(1), Worst-case: O(n) (when many hash collisions occur)

### Q10: What are NavigableMap and NavigableSet?
**A:** Interfaces that extend `SortedMap` and `SortedSet` with navigation methods like `lower()`, `floor()`, `ceiling()`, `higher()`.

### Q11: Explain CopyOnWriteArrayList.
**A:** A thread-safe variant of ArrayList where all mutative operations are implemented by making a fresh copy of the underlying array. Suitable for scenarios with frequent reads and infrequent writes.

---

## ⚖️ Scenario-Based Questions

### Q12: How would you implement an LRU Cache?
**A:** Use `LinkedHashMap` with access order set to `true` and override `removeEldestEntry()`.

### Q13: What is the best data structure to maintain insertion order with fast lookup?
**A:** `LinkedHashMap`

### Q14: How to sort a list of custom objects?
```java
Collections.sort(list, Comparator.comparing(Person::getAge));
```
Or implement `Comparable` in the class.

---

## 🚀 Collections in Multithreading

### Q15: What are some concurrent collection classes?
- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `ConcurrentLinkedQueue`
- `ConcurrentSkipListMap`
- `BlockingQueue` implementations
- `LinkedTransferQueue`
- `ConcurrentLinkedDeque`

### Q16: Difference between synchronizedMap and ConcurrentHashMap?
**A:** `synchronizedMap` locks entire map; `ConcurrentHashMap` allows concurrent reads and updates via segment locking (pre-Java 8) or node-level locking (Java 8+) with higher concurrency.

### Q17: How has ConcurrentHashMap evolved in Java 8+?
**A:** In Java 8+, ConcurrentHashMap replaced segment locking with node-level locking using CAS operations, added stream-friendly methods like `forEach`, `reduce`, and `search`, and supports a configurable concurrency level.

---

## 🤖 Code Output Questions

### Q18:
```java
List<Integer> list = Arrays.asList(1, 2, 3);
list.add(4);
System.out.println(list);
```
**A:** Throws `UnsupportedOperationException` because `Arrays.asList()` returns a fixed-size list.

### Q19:
```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("b", 2);
map.put("a", 3);
System.out.println(map.size() + ", " + map.get("a"));
```
**A:** Outputs `2, 3` because the duplicate key "a" overwrites the previous value.

### Q20:
```java
Set<Integer> set = new TreeSet<>(Comparator.reverseOrder());
set.add(1);
set.add(3);
set.add(2);
set.forEach(System.out::print);
```
**A:** Outputs `321` because TreeSet maintains sorted order based on the provided comparator.

---

## 🔄 Java 8+ Collections Features

### Q21: What are the factory methods for collections in Java 9+?
**A:** Java 9 introduced convenient factory methods for creating immutable collections:
```java
List<String> list = List.of("a", "b", "c");
Set<String> set = Set.of("a", "b", "c");
Map<String, Integer> map = Map.of("a", 1, "b", 2);
Map<String, Integer> mapEntries = Map.ofEntries(
    Map.entry("a", 1),
    Map.entry("b", 2)
);
```

### Q22: How do Java Streams interact with Collections?
**A:** Streams provide functional-style operations on collections:
```java
// Finding average age of people over 20
double avgAge = people.stream()
    .filter(p -> p.getAge() > 20)
    .mapToInt(Person::getAge)
    .average()
    .orElse(0);

// Grouping by department
Map<Department, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

### Q23: What is a Spliterator and how does it relate to parallel streams?
**A:** Spliterator is an iterator interface designed for parallel traversal. Collections implement custom spliterators to optimize parallel stream operations by efficiently splitting the data for parallel processing.

---

## 🧩 Collection Design Patterns and Advanced Usage

### Q24: How would you implement a thread-safe cache with expiring elements?
**A:** Combining `ConcurrentHashMap` with a background thread or `ScheduledExecutorService`:
```java
public class ExpiringCache<K, V> {
    private final ConcurrentHashMap<K, CacheItem<V>> map = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public void put(K key, V value, long expiryTimeMillis) {
        CacheItem<V> item = new CacheItem<>(value, System.currentTimeMillis() + expiryTimeMillis);
        map.put(key, item);
    }
    
    public Optional<V> get(K key) {
        CacheItem<V> item = map.get(key);
        if (item != null && !item.isExpired()) {
            return Optional.of(item.getValue());
        }
        map.remove(key);
        return Optional.empty();
    }
    
    // Start background cleanup
    public void startCleanupTask(long periodMillis) {
        scheduler.scheduleAtFixedRate(() -> {
            map.forEach((k, v) -> {
                if (v.isExpired()) map.remove(k);
            });
        }, periodMillis, periodMillis, TimeUnit.MILLISECONDS);
    }
    
    private static class CacheItem<V> {
        private final V value;
        private final long expiryTime;
        
        // Constructor, getters, isExpired method
    }
}
```

### Q25: How would you implement a bi-directional map?
**A:** Using two HashMaps or using libraries like Apache Commons Collections' BidiMap:
```java
public class BiMap<K, V> {
    private final Map<K, V> forward = new HashMap<>();
    private final Map<V, K> backward = new HashMap<>();
    
    public void put(K key, V value) {
        if (forward.containsKey(key)) {
            backward.remove(forward.get(key));
        }
        if (backward.containsKey(value)) {
            forward.remove(backward.get(value));
        }
        forward.put(key, value);
        backward.put(value, key);
    }
    
    public V getByKey(K key) {
        return forward.get(key);
    }
    
    public K getByValue(V value) {
        return backward.get(value);
    }
    
    // Other methods like remove, size, etc.
}
```

### Q26: What is a weak reference collection and when would you use it?
**A:** Weak reference collections like `WeakHashMap` allow keys to be garbage collected when no strong references to them exist. They're useful for implementing caches that shouldn't prevent objects from being garbage collected.

---

## ⚙️ Collection Performance Optimizations

### Q27: How would you tune HashMap/HashSet for better performance?
**A:**
- Set initial capacity to avoid rehashing
- Choose proper load factor
- Ensure good hashCode() implementation
- Use immutable keys for Maps

```java
// Bad
Map<String, Data> map = new HashMap<>(); // Default initial capacity (16)

// Better
Map<String, Data> map = new HashMap<>(1000, 0.75f); // Initial capacity of 1000
```

### Q28: What's the most efficient way to find the intersection of two large collections?
**A:** Convert the smaller collection to a HashSet for O(n) lookup, then iterate through the larger collection checking for membership:

```java
public <T> Set<T> findIntersection(Collection<T> first, Collection<T> second) {
    // Determine which collection is smaller
    Collection<T> smaller = first.size() <= second.size() ? first : second;
    Collection<T> larger = smaller == first ? second : first;
    
    // Create HashSet from smaller collection for O(1) lookups
    Set<T> smallerSet = new HashSet<>(smaller);
    
    // Find intersection by checking membership
    Set<T> intersection = new HashSet<>();
    for (T item : larger) {
        if (smallerSet.contains(item)) {
            intersection.add(item);
        }
    }
    return intersection;
}
```

### Q29: When should you prefer ArrayList vs ArrayDeque?
**A:** 
- Use `ArrayList` for random access operations and when you primarily add/remove elements from the end
- Use `ArrayDeque` for queue/stack operations (adding/removing from both ends) as it offers O(1) operations for add/remove at both ends

---

## ✨ Behavioral Questions

### Q30: Have you used Java Collections in a real-world application?
**A:** [Your example here: e.g., Task tracking app using HashMap for user task mapping and PriorityQueue for task scheduling.]

### Q31: How do you decide which Collection implementation to use?
**A:** Based on:
- Need for ordering? → `List`, `LinkedHashSet`
- Need for uniqueness? → `Set`
- Key-value association? → `Map`
- Thread safety? → `ConcurrentHashMap`, `CopyOnWriteArrayList`
- Sorting? → `TreeSet`, `TreeMap`
- Queue operations? → Various `Queue` implementations
- Memory efficiency? → Primitive collections from third-party libraries
- Expected collection size? → Consider initial capacity

### Q32: How would you handle a collection performance issue in production?
**A:** I would:
1. Profile the application to identify the bottleneck
2. Analyze access patterns to determine the best collection type
3. Consider specialized collections (e.g., Trove, Eclipse Collections) for primitive types
4. Review collection sizing, hashCode implementations, and equals methods
5. Consider thread-safety requirements and their overhead
6. Implement caching strategies if appropriate

---

## 🚀 Specialized Collections

### Q33: When would you use specialized collections like Trove or Eclipse Collections?
**A:** When dealing with large collections of primitive types to avoid autoboxing/unboxing overhead and reduce memory consumption.

### Q34: What are the benefits of using immutable collections?
**A:** 
- Thread safety without synchronization overhead
- Safe to share across threads/methods
- Prevented from accidental modifications
- Can be cached and reused
- Useful for defensive programming

### Q35: How do you efficiently handle large, sorted collections?
**A:** Consider using:
- `TreeMap`/`TreeSet` for moderate sizes with sorting needs
- `ConcurrentSkipListMap`/`ConcurrentSkipListSet` for concurrent sorted access
- External sorting algorithms for very large collections
- Database-backed collections for persistent, indexed storage
- B-tree implementations for extremely large in-memory sorted collections

---

## 🎉 Final Tips
- Master `equals()` and `hashCode()` contracts
- Know time complexities for each collection
- Use `Collections.unmodifiableX` for immutability
- Prefer composition over inheritance in extending collection classes
- Understand memory overhead of different collections
- Consider collection initialization capacity for performance
- Use the Stream API for functional-style bulk operations
- Be aware of specialized collection libraries for performance-critical applications

---

> 🔗 Next: Revisit your understanding of [Best Practices](./16_Best_Practices.md) for efficient use of Java Collections
