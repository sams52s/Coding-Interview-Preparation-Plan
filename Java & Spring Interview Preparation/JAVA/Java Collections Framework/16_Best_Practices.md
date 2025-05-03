# 16. Best Practices for Using Java Collections

## 🧠 Why Best Practices Matter
Using collections efficiently in Java can greatly affect application performance, memory usage, and code readability. These best practices help you write cleaner, faster, and more reliable code.

---

## ✅ General Best Practices

### 1. **Prefer Interfaces Over Implementations**
```java
// Good
List<String> list = new ArrayList<>();

// Not recommended
ArrayList<String> list = new ArrayList<>();
```
> Use `List`, `Set`, or `Map` as the reference type to make switching implementations easier.

### 2. **Initialize with an Appropriate Capacity**
```java
Map<String, String> map = new HashMap<>(100);  // Avoid rehashing
```
> Prevents frequent resizing or rehashing which can degrade performance.

### 3. **Choose the Right Collection Type**
- Use `ArrayList` for fast random access.
- Use `LinkedList` for frequent insertion/deletion.
- Use `HashSet` for unique unordered data.
- Use `TreeMap` when keys need to be sorted.

### 4. **Use Immutable Collections When Possible**
```java
List<String> roles = List.of("ADMIN", "USER");
```
> Safer to share across threads, avoids accidental modification.

### 5. **Avoid Modifying Collections During Iteration**
```java
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String s = iterator.next();
    if (s.equals("delete")) {
        iterator.remove(); // ✅ Safe
    }
}
```
> Modifying directly using list.remove() during iteration causes `ConcurrentModificationException`.

---

## 🛡️ Performance Optimization Tips

### 1. **Use Streams Judiciously**
```java
List<String> filtered = names.stream()
    .filter(n -> n.startsWith("A"))
    .collect(Collectors.toList());
```
> Avoid complex operations in streams that increase runtime complexity.

### 2. **Use `containsKey()` over `get()` for Null-Sensitive Maps**
```java
if (map.containsKey("key")) {
    map.get("key");
}
```
> Prevents ambiguity when `null` is a valid value.

### 3. **Use `LinkedHashMap` for LRU Cache**
```java
Map<String, String> cache = new LinkedHashMap<>(16, 0.75f, true);
```
> Maintains access order and enables LRU eviction policy.

### 4. **Minimize Boxing/Unboxing in Collections**
```java
List<Integer> list = new ArrayList<>();
```
> Consider using primitive specialized collections (e.g., Eclipse Collections or fastutil) for performance.

---

## 🤝 Concurrency Tips

### 1. **Use Concurrent Collections**
- `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`

### 2. **Avoid Synchronized Wrappers in Multi-threaded Contexts**
```java
List<String> list = Collections.synchronizedList(new ArrayList<>());  // Legacy
```
> Prefer modern concurrent collections from `java.util.concurrent` package.

### 3. **Use Immutable Collections for Shared Read-Only Data**
> Reduces the need for synchronization and prevents race conditions.

---

## 🚫 Common Pitfalls to Avoid

| Pitfall | Fix |
|--------|-----|
| Using wrong collection type | Know when to use `Set`, `Map`, `List`, etc. |
| Forgetting to override `equals()` and `hashCode()` in key-based collections | Use proper implementations for correct behavior in `HashMap`, `HashSet`. |
| Iterating and modifying a collection simultaneously | Use `Iterator.remove()` or concurrent collections. |
| Not closing stream operations with terminal operations | Always collect or consume streams to avoid memory leaks. |
| Using unsuitable equals/hashCode implementations | Ensure proper contract implementation for hash-based collections |
| Unbounded growth in cache collections | Implement eviction strategies (LRU, size-based, TTL) |
| Inefficient filtering of large collections | Use predicate-based removal instead of repeated iterations |

---

## 🧩 Advanced Collection Techniques

### 1. **Custom Collection Views**
```java
// Creates a read-only view backed by the original map
Map<String, Integer> unmodifiableView = Collections.unmodifiableMap(originalMap);

// Creates a synchronized view
Set<User> synchronizedSet = Collections.synchronizedSet(userSet);

// Creates a checked collection that enforces type safety at runtime
List<String> checkedList = Collections.checkedList(stringList, String.class);
```

### 2. **Memory-Efficient Collections**
```java
// EnumMap is more efficient for enum keys than HashMap
Map<DayOfWeek, Schedule> schedule = new EnumMap<>(DayOfWeek.class);

// EnumSet is a specialized Set for enum values with bit vector implementation
Set<Permission> permissions = EnumSet.of(Permission.READ, Permission.WRITE);

// ArrayDeque is more efficient than LinkedList as a queue
Queue<Task> tasks = new ArrayDeque<>(initialCapacity);
```

### 3. **Specialized Search Operations**
```java
// Binary search on sorted lists - O(log n)
int index = Collections.binarySearch(sortedList, key);

// Finding min/max without sorting entire collection
String min = Collections.min(strings, comparator);
Integer max = Collections.max(numbers);

// Frequency counting and disjoint testing
int occurrences = Collections.frequency(collection, element);
boolean hasOverlap = !Collections.disjoint(collection1, collection2);
```

### 4. **Collection Composition Techniques**
```java
// Multimaps (one key to multiple values)
Map<Department, List<Employee>> departmentEmployees = new HashMap<>();
departmentEmployees.computeIfAbsent(dept, k -> new ArrayList<>()).add(employee);

// BiMap (bidirectional map with unique values)
Map<String, Integer> nameToId = new HashMap<>();
Map<Integer, String> idToName = nameToId.entrySet().stream()
    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
```

---

## 📊 Advanced Performance Optimization

### 1. **Batch Operations**
```java
// Use batch add operations when possible
collection.addAll(Arrays.asList(elem1, elem2, elem3));

// Remove multiple elements at once
collection.removeAll(elementsToRemove);

// Bulk filtering
collection.removeIf(e -> e.getStatus() == Status.INACTIVE); // Java 8+
```

### 2. **Memory Footprint Reduction**
```java
// Trimming collections after bulk loading
ArrayList<Data> list = new ArrayList<>(initialCapacity);
// ... add items ...
list.trimToSize(); // Reduces unused capacity

// Using specialized collection libraries
// Eclipse Collections, FastUtil, HPPC, Koloboke for primitive collections
// IntList intList = new IntArrayList(); // Example with a third-party library

// Avoid deep collection nesting
// Instead of Map<K, List<Map<T, V>>>, consider flattening or using composite keys
```

### 3. **Lazy Evaluation Patterns**
```java
// Use Stream's lazy evaluation
Stream<String> infiniteStream = Stream.iterate("", s -> s + "a");
String firstMatchingElement = infiniteStream
    .filter(s -> s.length() > 10)
    .findFirst()
    .orElse("");  // Only processes until first match

// Custom lazy collection wrappers
Supplier<List<ExpensiveObject>> lazyList = () -> generateExpensiveList(); 
// Only materialize when needed with lazyList.get()
```

### 4. **Collection-Specific Optimizations**
```java
// For HashMap: optimize load factor based on expected usage
Map<K, V> map = new HashMap<>(expectedSize, 0.6f);  // Lower load factor, fewer collisions

// For ArrayList: reserve sufficient capacity for known size collections
List<Item> items = new ArrayList<>(exactSize);  // Avoids incremental resizing

// For ConcurrentHashMap: optimize concurrency level
ConcurrentHashMap<K, V> concurrentMap = new ConcurrentHashMap<>(
    expectedSize, 0.75f, 16);  // 16 concurrent threads for better write throughput
```

---

## 🔄 Advanced Concurrency Patterns

### 1. **Lock-Free Collections**
```java
// Optimistic locking with CAS operations
AtomicReference<List<String>> atomicList = new AtomicReference<>(new ArrayList<>());
// Update with CAS semantics
boolean updated = false;
while (!updated) {
    List<String> oldList = atomicList.get();
    List<String> newList = new ArrayList<>(oldList);
    newList.add("new item");
    updated = atomicList.compareAndSet(oldList, newList);
}
```

### 2. **Custom Concurrent Collection Wrappers**
```java
// Custom concurrent collection with read-write lock
public class ConcurrentReadOptimizedList<E> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<E> list = new ArrayList<>();
    
    public E get(int index) {
        lock.readLock().lock();
        try {
            return list.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public void add(E element) {
        lock.writeLock().lock();
        try {
            list.add(element);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
```

### 3. **Thread Confinement**
```java
// ThreadLocal collections for thread-confined operations
ThreadLocal<List<WorkItem>> perThreadItems = ThreadLocal.withInitial(ArrayList::new);

// Later in thread execution
perThreadItems.get().add(workItem); // Each thread has its own isolated list
```

### 4. **Concurrent Bulk Operations**
```java
// Parallel collection processing
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
// Atomic putIfAbsent
map.putIfAbsent(key, 0);

// Atomic compute operations 
map.compute(key, (k, v) -> v == null ? 1 : v + 1);

// Parallel forEach
map.forEach(4, (k, v) -> process(k, v)); // parallelism hint of 4
```

---

## 🛠️ Custom Collection Implementations

### 1. **When to Create Custom Collections**
- When standard collections don't provide required performance characteristics
- When domain-specific constraints need enforcement
- When specialized thread-safety is required
- For implementing specific algorithms or data structures

### 2. **Best Practices for Custom Collections**
```java
// Implement standard Collection interfaces
public class FixedSizeList<E> implements List<E> {
    // Implementation methods...
}

// Extend AbstractCollection for easier implementation
public class ReadOnlySet<E> extends AbstractSet<E> {
    // Only need to implement iterator() and size()
}

// Use composition over inheritance when customizing behavior
public class BoundedQueue<E> {
    private final Queue<E> delegate;
    private final int maxSize;
    
    // Implement queue operations with bounds checking
}
```

### 3. **Common Custom Collection Patterns**
```java
// Observable collections
public class ObservableList<E> implements List<E> {
    private final List<E> delegate;
    private final List<CollectionChangeListener> listeners = new ArrayList<>();
    
    // Notify listeners on modification
}

// Validating collections
public class ValidatingSet<E> implements Set<E> {
    private final Set<E> delegate;
    private final Predicate<E> validator;
    
    // Validate elements before adding them
}

// Auto-expiring caches
public class ExpiringCache<K, V> {
    private final Map<K, ExpiringEntry<V>> map = new ConcurrentHashMap<>();
    
    // Implement time-based expiration
}
```

## 📌 Code Review Checklist for Collections
- [ ] Are interfaces used instead of concrete types?
- [ ] Is the collection size initialized when predictable?
- [ ] Are you using `List.of()` or `Map.of()` for immutable structures?
- [ ] Are streams used efficiently and clearly?
- [ ] Is the right collection chosen for the task?
- [ ] Are thread-safe collections used in concurrent contexts?
- [ ] Is collection memory footprint considered for large datasets?
- [ ] Are specialized collections used where appropriate (EnumMap, EnumSet)?
- [ ] Is collection lifecycle management properly handled (e.g., clearing references)?
- [ ] Are bulk operations used instead of repeated single operations?

---

## ❓ Interview Questions & Answers

### Q1: Why is it recommended to program to interfaces?
**A:** It provides flexibility in switching between implementations and promotes loose coupling. This allows changing underlying implementations without affecting client code. For example, changing from ArrayList to LinkedList only requires changing the instantiation, not everywhere the List is used.

### Q2: How can you implement a thread-safe list?
**A:** Use `CopyOnWriteArrayList` for read-heavy workloads, `Collections.synchronizedList()` for balanced read/write operations, or custom implementations with fine-grained locking. For high concurrency, consider concurrent data structures with lock striping or lock-free algorithms.

### Q3: What's the benefit of initializing a collection with capacity?
**A:** Reduces the number of internal resizes/reallocations, improving performance. For ArrayList, it avoids array copying during growth. For HashMap, it minimizes rehashing, which is O(n) in time complexity. For example, a HashMap with default load factor that needs to store 100 elements should be initialized with capacity ~134 (100/0.75).

### Q4: Why should you avoid modifying a collection while iterating?
**A:** It leads to `ConcurrentModificationException` unless done via `Iterator.remove()` or concurrent collections. This exception is thrown by fail-fast iterators to prevent unpredictable behavior. Instead, use proper iterator methods, collect elements to modify first, or use removeIf() (Java 8+).

### Q5: When should you use `TreeSet` over `HashSet`?
**A:** Use `TreeSet` when you need the elements sorted. TreeSet maintains elements in sorted order with O(log n) operations, while HashSet has O(1) but unordered. TreeSet is also preferable when you need range operations like ceiling(), floor(), or subSet().

### Q6: How would you design a high-performance cache using Java collections?
**A:** Combine `LinkedHashMap` with access ordering (LRU implementation), set a fixed size with a custom removeEldestEntry method. For concurrent access, wrap with synchronization or use ConcurrentHashMap with a size limit enforced on put operations. For more advanced needs, consider Google Guava's Cache or Caffeine.

### Q7: What are the trade-offs between ArrayList and LinkedList for different operations?
**A:** ArrayList provides O(1) random access but O(n) insertion/deletion in the middle due to array shifting. LinkedList offers O(1) insertion/deletion at both ends and at an iterator position but O(n) random access and higher memory overhead per element due to node storage. Choose ArrayList for random access and LinkedList for frequent modifications.

### Q8: How would you handle a very large collection that doesn't fit in memory?
**A:** Options include:
- Using external storage with memory-mapped files
- Implementing pagination with database queries
- Using streaming operations with bounded memory usage
- Partitioning the data into manageable chunks
- Using specialized big data collections like Apache collections or Eclipse collections
- Implementing custom disk-backed collection with caching

### Q9: Explain how ConcurrentHashMap achieves its thread safety and performance.
**A:** ConcurrentHashMap uses lock striping (segmentation) to divide the map into multiple segments, each with its own lock. This allows multiple threads to modify different segments concurrently. It also uses CAS (Compare-And-Swap) operations for updates, avoiding full locking in many scenarios. This combination provides high concurrency while maintaining thread safety.

### Q10: When would you use weak references in collections and how?
**A:** Use weak references when implementing caches where entries should be automatically removed when no longer referenced elsewhere. `WeakHashMap` maintains keys using weak references, allowing entries to be garbage collected when keys are no longer strongly referenced. This prevents memory leaks in caching scenarios.

```java
Map<Document, DocumentData> cache = new WeakHashMap<>();
// When Document objects are no longer referenced elsewhere,
// their entries will automatically be removed
```

### Q11: How would you implement a priority queue with custom ordering?
**A:** Use a PriorityQueue with a custom Comparator:

```java
Queue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> {
    // First by priority (higher first)
    int result = t2.getPriority() - t1.getPriority();
    if (result == 0) {
        // Then by creation time (older first)
        return t1.getCreationTime().compareTo(t2.getCreationTime());
    }
    return result;
});
```

### Q12: How would you modify a collection to enforce immutability?
**A:** Several approaches:
1. Use Collections utility methods:
```java
List<String> immutable = Collections.unmodifiableList(originalList);
```
2. Use Java 9+ factory methods:
```java
List<String> immutable = List.of("a", "b", "c");
Map<String, Integer> map = Map.of("key1", 1, "key2", 2);
```
3. Use defensive copying:
```java
public List<String> getItems() {
    return new ArrayList<>(this.items); // Return a copy
}
```
4. Use third-party libraries like Guava:
```java
ImmutableList<String> list = ImmutableList.copyOf(originalList);
```

### Q13: How does the CopyOnWriteArrayList work and when is it appropriate?
**A:** CopyOnWriteArrayList creates a fresh copy of the underlying array for every modification operation. This makes it thread-safe without synchronization during reads but very expensive for writes. It's appropriate for collections that are frequently traversed but rarely modified, such as observer lists or event listener collections.

### Q14: What is the difference between Hashtable and ConcurrentHashMap?
**A:** Both are thread-safe, but:
- Hashtable uses synchronized methods, locking the entire table for each operation
- ConcurrentHashMap uses lock striping and CAS operations, allowing concurrent reads and writes
- Hashtable doesn't allow null keys or values, ConcurrentHashMap allows null values
- ConcurrentHashMap offers better scalability and performance in concurrent environments
- Hashtable is considered legacy, while ConcurrentHashMap is modern and preferred

### Q15: How would you implement a bidirectional map in Java?
**A:** A bidirectional map allows lookups from both keys to values and values to keys:

```java
public class BidirectionalMap<K, V> {
    private final Map<K, V> forward = new HashMap<>();
    private final Map<V, K> backward = new HashMap<>();
    
    public V put(K key, V value) {
        // Remove old mappings if present
        if (forward.containsKey(key)) {
            backward.remove(forward.get(key));
        }
        if (backward.containsKey(value)) {
            forward.remove(backward.get(value));
        }
        
        forward.put(key, value);
        backward.put(value, key);
        return value;
    }
    
    public V getByKey(K key) {
        return forward.get(key);
    }
    
    public K getByValue(V value) {
        return backward.get(value);
    }
    
    // Additional methods...
}
```

---

> 🔗 Next: [Interview Questions and Answers](./17_Interview_Questions_and_Answers.md)
