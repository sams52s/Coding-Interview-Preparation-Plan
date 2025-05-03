# 14. Synchronization and Fail-Fast vs Fail-Safe Iterators

## ⚖️ Overview
In multi-threaded Java applications, data consistency and thread safety are critical. The Java Collections Framework provides both synchronized and concurrent solutions. It's also important to understand how different iterators behave when collections are modified during iteration.

---

## 🐐 Synchronized Collections

Java provides mechanisms to synchronize collection objects, ensuring only one thread can access the collection at a time.

### Methods to Synchronize Collections

1. **Legacy Synchronized Classes**
   - `Vector`, `Hashtable`, and `Stack` are synchronized by default.

2. **Using `Collections.synchronizedXXX()`**
   - Wraps a collection to make it synchronized.
   
   ```java
   List<String> syncList = Collections.synchronizedList(new ArrayList<>());
   Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
   ```

   > Always synchronize on the collection when iterating:
   ```java
   synchronized(syncList) {
       for (String s : syncList) {
           // Safe iteration
       }
   }
   ```

3. **Thread-safe Alternatives (from java.util.concurrent)**
   - `ConcurrentHashMap`, `CopyOnWriteArrayList`, `ConcurrentSkipListSet`

---

## ❌ Fail-Fast Iterators

Fail-fast iterators throw a `ConcurrentModificationException` if the collection is structurally modified after the creation of the iterator.

### Affected Collections:
- `ArrayList`, `HashMap`, `HashSet`, etc.

### Example:
```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");

for (String s : list) {
    list.add("C"); // Throws ConcurrentModificationException
}
```

> **Note:** Structural modifications = add/remove operations (excluding iterator's `remove()`).

---

## 🚧 Fail-Safe Iterators

Fail-safe iterators **do not throw exceptions** if the collection is modified during iteration. They work on a **clone** of the original collection.

### Common Fail-Safe Collections:
- `CopyOnWriteArrayList`
- `ConcurrentHashMap`
- `ConcurrentSkipListSet`

### Example:
```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("A");
list.add("B");

for (String s : list) {
    list.add("C"); // No exception
}
```

> **Drawback:** Copying overhead can affect performance if the list is large or frequently mutated.

---

## 🎓 Best Practices

- Use **fail-safe** collections for concurrent read-write scenarios.
- Avoid synchronizing manually unless necessary.
- For high-concurrency maps, use `ConcurrentHashMap` instead of `Hashtable`.
- Minimize write operations during iteration to avoid `ConcurrentModificationException`.
- Prefer `CopyOnWriteArrayList` for mostly-read collections with infrequent writes.

---

## 🔎 Comparison Table

| Feature                  | Fail-Fast                    | Fail-Safe                      |
|--------------------------|------------------------------|--------------------------------|
| Behavior on modification| Throws `ConcurrentModificationException` | Safe, iterates over snapshot |
| Underlying collection    | Actual                       | Cloned/copy                    |
| Performance              | Faster                       | Slower (copy overhead)         |
| Use in concurrent apps   | Risky                        | Recommended                    |
| Memory usage             | Lower                        | Higher (needs space for copy)  |
| Consistency              | Always current state         | Point-in-time snapshot         |
| Implementation examples  | ArrayList, HashMap, HashSet  | ConcurrentHashMap, CopyOnWriteArrayList |
| Java descriptor          | Non-deterministic behavior   | Weakly consistent              |
| Iterator invalidation    | Yes, on structural change    | No, keeps working on snapshot  |

---

## ❓ Interview Questions

### Q1: What is the difference between fail-fast and fail-safe iterators?
**A:** Fail-fast iterators throw a `ConcurrentModificationException` on structural modification. Fail-safe iterators operate on a copy, hence do not throw an exception.

### Q2: How does `CopyOnWriteArrayList` ensure fail-safe iteration?
**A:** It creates a **new copy** of the underlying array on every modification. Iterators access the snapshot.

### Q3: Why is `ConcurrentHashMap` preferred over `Hashtable`?
**A:** `ConcurrentHashMap` uses segment-based locking or lock striping (Java 7 and below) and `synchronized` blocks internally (Java 8+), offering better scalability and performance. It allows concurrent reads and a configurable number of concurrent writes, while `Hashtable` locks the entire structure for any operation.

### Q4: What precautions must be taken when using `Collections.synchronizedList()`?
**A:** External synchronization is required during iteration to avoid `ConcurrentModificationException`:
```java
List<String> list = Collections.synchronizedList(new ArrayList<>());
synchronized(list) {
    Iterator<String> iter = list.iterator();
    while(iter.hasNext()) {
        // Safe iteration
    }
}
```

### Q5: What is the internal mechanism that detects structural changes in fail-fast iterators?
**A:** A `modCount` field tracks changes. The iterator compares the current `modCount` to the expected `modCount`. If unequal, it throws an exception.

### Q6: What are weakly consistent iterators?
**A:** Weakly consistent iterators guarantee to traverse elements as they existed when the iterator was constructed and may (but are not guaranteed to) reflect modifications after construction. All iterators for ConcurrentHashMap, ConcurrentSkipListMap, CopyOnWriteArrayList, and other concurrent collections are weakly consistent.

### Q7: How does lock striping work in ConcurrentHashMap (pre-Java 8)?
**A:** Lock striping divides the map into segments, each with its own lock. This allows multiple threads to modify different segments concurrently, increasing throughput. For example, with 16 segments, up to 16 threads can write simultaneously if they target different segments.

### Q8: How has ConcurrentHashMap's implementation changed in Java 8+?
**A:** Java 8+ replaced the segment-based implementation with a node-based approach using synchronized blocks and CAS (Compare-And-Swap) operations. It uses a more fine-grained locking strategy at the node level rather than segment level, further improving concurrency by reducing lock contention.

### Q9: Why might CopyOnWriteArrayList perform poorly in write-heavy scenarios?
**A:** Every modification creates a complete copy of the underlying array, leading to:
1. High memory usage (two copies of the array exist during modification)
2. Copy overhead proportional to array size (O(n) for each write operation)
3. Temporary GC pressure from discarded arrays

It's optimized for scenarios with frequent reads and rare writes.

### Q10: How can you safely remove elements during iteration without ConcurrentModificationException?
**A:** Use the iterator's own `remove()` method:
```java
List<String> list = new ArrayList<>();
// ...populate list
Iterator<String> iter = list.iterator();
while(iter.hasNext()) {
    String item = iter.next();
    if (shouldRemove(item)) {
        iter.remove(); // Safe removal
    }
}
```

### Q11: What happens if you use an enhanced for-loop with ConcurrentHashMap?
**A:** The enhanced for-loop uses the map's iterator, which for ConcurrentHashMap is weakly consistent. It will not throw ConcurrentModificationException but may not reflect all modifications made after iteration begins.

### Q12: How does ConcurrentSkipListMap differ from ConcurrentHashMap?
**A:** ConcurrentSkipListMap:
- Maintains keys in sorted order (NavigableMap)
- Has O(log n) lookup performance vs O(1) for HashMap
- Provides guaranteed log(n) time cost for containsKey, get, put and remove operations
- Does not need resizing operations (better predictable latency)
- Optimized for scenarios requiring ordered traversal or range operations

### Q13: What is the relationship between Stream's parallel operations and concurrent collections?
**A:** Parallel streams can benefit from concurrent collections' thread-safe nature, but don't require them. However:
1. Using fail-fast collections with parallel streams may cause ConcurrentModificationException
2. Using concurrent collections ensures thread-safety but might impact performance due to synchronization
3. Immutable collections are optimal for parallel stream processing (no synchronization needed)

### Q14: How can you debug ConcurrentModificationException effectively?
**A:** To debug ConcurrentModificationException:
1. Use thread dumps to identify concurrent access patterns
2. Add logging before modification operations to track which thread modifies the collection
3. Consider using Java Flight Recorder to capture detailed thread activity
4. Replace the collection with a concurrent version temporarily for diagnostic purposes
5. Review synchronized blocks to ensure proper scope and object locking

### Q15: What are the atomicity guarantees of ConcurrentHashMap's methods?
**A:** Individual methods like get(), put(), remove() are atomic, but compound operations like "check then act" (e.g., check if key exists, then add if missing) are not atomic without using the provided atomic methods:
```java
// Not atomic across multiple operations
if (!map.containsKey(key)) {
    map.put(key, value);
}

// Atomic compound operation
map.putIfAbsent(key, value);
```

---

> 🔗 Next: [Immutable Collections](./15_Immutable_Collections.md)
