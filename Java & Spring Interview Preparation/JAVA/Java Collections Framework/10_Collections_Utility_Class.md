# 📚 Java Collections Utility Class – Advanced Guide

## 📌 Introduction
The `Collections` class in Java (`java.util.Collections`) is a final utility class that provides **static methods** for performing operations on collection types like `List`, `Set`, and `Map`. These methods include **algorithms** (sorting, searching), **wrappers** (synchronized, unmodifiable), and **utility functions** for enhancing code simplicity and performance.

---

## 🔧 Core Utility Methods

### 1. Sorting Collections
```java
Collections.sort(list); // Sorts using natural order
Collections.sort(list, comparator); // Custom sorting
```
> Sorts in-place; for immutable lists use streams or copy beforehand.

### 2. Searching in Collections
```java
Collections.binarySearch(sortedList, key);
```
> List must be pre-sorted using the same comparator.

### 3. Shuffling Collections
```java
Collections.shuffle(list); // Randomizes element order
```
> Useful for simulations, gaming logic, randomized tests.

### 4. Reversing Elements
```java
Collections.reverse(list); // In-place reverse
```

### 5. Finding Extremes
```java
Collections.max(list);
Collections.min(list);
```
> With comparator: `Collections.max(list, comparator);`

### 6. Filling with a Common Value
```java
Collections.fill(list, "value");
```
> Sets all values in list to the same element.

### 7. Copying Between Lists
```java
Collections.copy(destList, srcList);
```
> `destList` must be pre-sized. Throws `IndexOutOfBoundsException` otherwise.

### 8. Counting Frequency
```java
Collections.frequency(list, element);
```
> Returns number of occurrences.

### 9. Checking Disjoint Collections
```java
Collections.disjoint(list1, list2);
```
> Returns true if no common elements.

### 10. Creating Synchronized Collections
```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
```
> Enables thread-safe access; external synchronization needed for iteration.

### 11. Creating Unmodifiable Collections
```java
List<String> unmodifiableList = Collections.unmodifiableList(new ArrayList<>());
```
> Attempts to modify throw `UnsupportedOperationException`.

### 12. Creating Empty/Singleton Collections
```java
Collections.emptyList();
Collections.singleton("only");
Collections.singletonList("single");
```

---

## 💡 Advanced Use Cases
- `Collections.nCopies(n, element)`: Returns immutable list of n copies.
- `Collections.reverseOrder()`: Reverse comparator for descending sort.
```java
Collections.sort(list, Collections.reverseOrder());
```
- `Collections.enumeration(list)`: Returns an `Enumeration` view.
- `Collections.asLifoQueue()`: Wraps a Deque as a LIFO Queue.

---

## 🧠 Best Practices
- Prefer `List.of()`/`Set.of()` (Java 9+) for immutability.
- Avoid modifying synchronized collections while iterating unless synchronized externally.
- Always sort before `binarySearch()`.
- Use `copyOf()` for safe, defensive copying (Java 10+).

---

## 🚀 Performance Table (On `List`)
| Operation              | Time Complexity |
|------------------------|-----------------|
| sort                   | O(n log n)      |
| binarySearch           | O(log n)        |
| shuffle/reverse/fill   | O(n)            |
| max/min/frequency      | O(n)            |
| copy                   | O(n)            |

---

## 🛡️ Thread Safety & Immutability
- `synchronizedList()` is useful for basic thread-safety, but not scalable.
- Prefer `CopyOnWriteArrayList`, `ConcurrentHashMap` in multi-threaded environments.
- Use `unmodifiableXXX()` or `List.of()` to create read-only views.

---

## ❓ Interview Questions and Answers

### Q1: What is the difference between `Collection` and `Collections`?
**A:** `Collection` is the root interface of Java collections; `Collections` is a utility class containing static helper methods.

### Q2: What are thread-safe alternatives to `ArrayList`?
**A:** `Collections.synchronizedList()` or `CopyOnWriteArrayList` for concurrent usage.

### Q3: What happens if the destination list in `Collections.copy()` is smaller than the source?
**A:** `IndexOutOfBoundsException` is thrown.

### Q4: How to create a fixed-size list filled with a value?
**A:** Use `Collections.nCopies(n, value)`.

### Q5: What's the purpose of `Collections.disjoint()`?
**A:** Checks whether two collections share any elements. Useful in filtering, exclusions.

### Q6: How to create an immutable list with a single value?
**A:** Use `Collections.singletonList(value)` or `List.of(value)` (Java 9+).

### Q7: Why prefer `Collections.reverseOrder()` over writing your own comparator?
**A:** It provides a built-in reverse comparator and ensures readability and consistency.

### Q8: When would you use `Collections.checkedCollection()` methods?
**A:** These methods provide runtime type safety for generic collections. They throw a `ClassCastException` if an attempt is made to add an element of the wrong type, preventing type pollution in legacy code or when collections are passed to untrusted code.
```java
List<String> safeStringList = Collections.checkedList(new ArrayList<>(), String.class);
// This will throw ClassCastException at runtime:
// ((List)safeStringList).add(Integer.valueOf(42));
```

### Q9: What are the limitations of `Collections.synchronizedMap()` compared to `ConcurrentHashMap`?
**A:** `synchronizedMap` uses intrinsic locking which blocks entire collection during operations, creating a performance bottleneck. `ConcurrentHashMap` offers:
- Better concurrency with segment/stripe locking
- Lock-free reads
- Atomic compound operations like `putIfAbsent`
- Better scalability for read-heavy workloads
- Thread-safe iteration without external synchronization

### Q10: How does `Collections.unmodifiableCollection()` differ from immutable collections (Java 9+)?
**A:** 
- `unmodifiableCollection` creates a read-only view of a mutable collection. The backing collection can still change.
- Immutable collections (`List.of()`, etc.) are truly immutable - no one can modify them.
```java
List<String> original = new ArrayList<>(List.of("one", "two"));
List<String> unmodifiable = Collections.unmodifiableList(original);
original.add("three"); // This affects unmodifiable view too!

List<String> immutable = List.of("one", "two");
// immutable.add("three"); // UnsupportedOperationException
```

### Q11: Explain how `Collections.rotate()` works with an example.
**A:** `rotate()` shifts elements by a specified distance. Positive distance shifts right, negative shifts left.
```java
List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
Collections.rotate(list, 2);
// Result: [4, 5, 1, 2, 3] - shifted right by 2
Collections.rotate(list, -1);
// Result: [5, 1, 2, 3, 4] - shifted left by 1
```

### Q12: What is `Collections.replaceAll()` used for and how is it different from `List.replaceAll()`?
**A:** 
- `Collections.replaceAll(list, oldVal, newVal)` replaces all instances of a specific value
- `List.replaceAll(UnaryOperator)` (Java 8+) applies a function to transform each element
```java
// Replace specific value
List<String> fruits = new ArrayList<>(List.of("apple", "orange", "apple"));
Collections.replaceAll(fruits, "apple", "pear"); 
// Result: ["pear", "orange", "pear"]

// Transform each element
fruits.replaceAll(s -> s.toUpperCase());
// Result: ["PEAR", "ORANGE", "PEAR"]
```

### Q13: How would you implement a thread-safe stack using Collections utility methods?
**A:** Using `Collections.synchronizedDeque()` with a `LinkedList` or `ArrayDeque`:
```java
Deque<String> stack = Collections.synchronizedDeque(new ArrayDeque<>());

// Thread-safe operations
synchronized (stack) {
    stack.push("item");
    String item = stack.pop();
    // Important: synchronize during iteration
    for (String element : stack) {
        // process element
    }
}
```

### Q14: Explain `Collections.indexOfSubList()` and `lastIndexOfSubList()`.
**A:** These methods find the starting position of a sublist within a larger list:
```java
List<Integer> main = List.of(1, 2, 3, 4, 5, 1, 2, 3);
List<Integer> sub = List.of(1, 2, 3);

int first = Collections.indexOfSubList(main, sub); // Returns 0
int last = Collections.lastIndexOfSubList(main, sub); // Returns 5
```

### Q15: How can you make a collection thread-safe but with better concurrency than `Collections.synchronizedCollection()`?
**A:** Use concurrent collections from `java.util.concurrent` package:
- `CopyOnWriteArrayList` - Optimized for frequent reads, infrequent writes
- `ConcurrentHashMap` - High-concurrency version of HashMap
- `ConcurrentSkipListSet`/`Map` - Concurrent sorted collections
- `ConcurrentLinkedQueue`/`Deque` - Non-blocking thread-safe collections

```java
// Better than Collections.synchronizedList
List<String> list = new CopyOnWriteArrayList<>();

// Better than Collections.synchronizedMap
Map<String, Integer> map = new ConcurrentHashMap<>();

// Better than Collections.synchronizedSet with TreeSet
NavigableSet<String> set = new ConcurrentSkipListSet<>();
```

## 🔄 Advanced Collection Techniques

### Bulk Operations with `Collections`
```java
// Add all even numbers from 0-100 to a collection
List<Integer> numbers = new ArrayList<>();
Collections.addAll(numbers, IntStream.rangeClosed(0, 100)
                             .filter(n -> n % 2 == 0)
                             .boxed()
                             .toArray(Integer[]::new));
                             
// Find min/max with custom comparator
Person youngest = Collections.min(people, Comparator.comparing(Person::getAge));
```

### Custom Collection Views
```java
// Create a fixed-size list view backed by an array
String[] array = {"a", "b", "c"};
List<String> listView = Arrays.asList(array);

// Create range view of a sorted list
SortedSet<Integer> first10 = sortedSet.headSet(10);
SortedSet<Integer> from5to10 = sortedSet.subSet(5, 10);
```

### Collection-Based Algorithms
```java
// Using binary search with custom comparator
int idx = Collections.binarySearch(employees, targetEmployee, 
                                   Comparator.comparing(Employee::getSalary));

// Find and swap elements meeting certain conditions
Collections.swap(list, 
                 IntStream.range(0, list.size())
                         .filter(i -> list.get(i).equals("target1"))
                         .findFirst().orElse(-1),
                 IntStream.range(0, list.size())
                         .filter(i -> list.get(i).equals("target2"))
                         .findFirst().orElse(-1));
```

## 🔍 Performance Optimizations

| Collection Method | When to Use | Alternatives | 
|-------------------|-------------|-------------|
| `Collections.sort()` | Small-medium lists | `parallelStream().sorted()` for large lists |
| `Collections.binarySearch()` | Frequently searched sorted lists | `HashMap` for O(1) lookups |
| `Collections.synchronizedX()` | Simple thread-safety needs | `java.util.concurrent` collections for high concurrency |
| `Collections.unmodifiableX()` | API boundaries, defensive coding | Factory methods (`List.of()`) for true immutability |

---

## ✅ Summary
The `Collections` utility class is a cornerstone of the Java Collection Framework, offering a comprehensive toolkit for collection manipulation. Its static methods span multiple domains:

- **Algorithmic operations**: Sorting, searching, shuffling, and extrema finding
- **Collection wrappers**: Synchronized, unmodifiable, checked, and empty collections
- **Bulk operations**: Manipulation of multiple elements efficiently
- **Thread-safety mechanisms**: Protection in concurrent environments
- **Type-safety enforcement**: Runtime type checking for generic collections

While many features have modern alternatives (Stream API, factory methods in Java 9+), understanding the `Collections` class remains essential for working with legacy code and mastering the full capabilities of the Java Collections Framework. Advanced developers should understand both when to use these utilities and when to choose newer alternatives for better performance, safety, and code expressiveness.

> 🔗 Next: [11_Sorting_and_Searching](./11_Sorting_and_Searching.md)
