# 🔍 Collections vs Collection in Java

## 📌 Introduction
In Java, the terms `Collection` and `Collections` may sound similar, but they refer to **completely different entities**. Understanding the difference between these two is crucial when working with the Java Collections Framework.

---

## 🆚 Key Differences

| Feature | `Collection` | `Collections` |
|--------|--------------|---------------|
| Type | Interface | Utility Class |
| Package | `java.util` | `java.util` |
| Purpose | Defines the root interface for most collection types (List, Set, Queue) | Contains utility methods to operate on collections (sort, reverse, shuffle, etc.) |
| Usage | To create and manipulate collections | To perform operations on collections |
| Inheritance | Inherited by List, Set, Queue interfaces | Final class (cannot be extended) |

---

## 📁 What is `Collection`?

`Collection<E>` is a **root interface** of the Java Collections Framework (excluding Map). It defines basic operations such as:

```java
boolean add(E e);
boolean remove(Object o);
boolean contains(Object o);
Iterator<E> iterator();
```

> Subinterfaces: `List`, `Set`, `Queue`, `Deque`

### Core Methods of Collection Interface

| Method | Description |
|--------|-------------|
| `size()` | Returns number of elements |
| `isEmpty()` | Checks if collection contains no elements |
| `contains(Object o)` | Checks if collection contains specified element |
| `iterator()` | Returns an iterator over the elements |
| `toArray()` | Returns an array containing all elements |
| `add(E e)` | Adds an element to the collection |
| `remove(Object o)` | Removes a single instance of element |
| `containsAll(Collection<?> c)` | Checks if collection contains all elements of another collection |
| `addAll(Collection<? extends E> c)` | Adds all elements from another collection |
| `removeAll(Collection<?> c)` | Removes all elements contained in another collection |
| `retainAll(Collection<?> c)` | Retains only elements contained in another collection |
| `clear()` | Removes all elements |
| `equals(Object o)` | Compares with another object for equality |
| `hashCode()` | Returns hash code value |

---

## 📁 What is `Collections`?

`Collections` is a **final utility class** that provides **static methods** to operate on or return collections:

### Common Methods:
```java
Collections.sort(list);
Collections.reverse(list);
Collections.shuffle(list);
Collections.min(list);
Collections.max(list);
Collections.synchronizedList(list);
Collections.unmodifiableList(list);
```

---

## 💡 Example

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>(); // using Collection interface
        collection.add("Apple");
        collection.add("Banana");

        List<String> list = new ArrayList<>(collection);
        Collections.sort(list); // using Collections utility

        System.out.println(list);
    }
}
```

### Output:
```
[Apple, Banana]
```

---

## 🧠 Advanced Utility Methods from `Collections`

### Thread-Safe Wrappers:
```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
```

### Unmodifiable Views:
```java
List<String> unmodifiable = Collections.unmodifiableList(myList);
Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(myMap);
Set<String> unmodifiableSet = Collections.unmodifiableSet(mySet);
```

### Empty Collections:
```java
List<String> emptyList = Collections.emptyList();
Map<String, Integer> emptyMap = Collections.emptyMap();
Set<String> emptySet = Collections.emptySet();
```

### Singleton Collections:
```java
List<String> singleton = Collections.singletonList("OnlyOne");
Set<Integer> singletonSet = Collections.singleton(42);
Map<String, Integer> singletonMap = Collections.singletonMap("key", 1);
```

### Checked Collections (Runtime type safety):
```java
List rawList = new ArrayList();
List<String> checkedList = Collections.checkedList(rawList, String.class);
```

### Binary Search and Advanced Sorting:
```java
// Binary search (list must be sorted)
int index = Collections.binarySearch(sortedList, "search_element");

// Sort with Comparator
Collections.sort(list, Comparator.comparing(Person::getAge));

// Sort with natural order and null handling
Collections.sort(list, Comparator.nullsFirst(Comparator.naturalOrder()));
```

---

## 🌟 Collection Factory Methods (Java 9+)

Java 9 introduced convenient factory methods for creating small, immutable collections:

```java
// Immutable List, Set and Map
List<String> list = List.of("one", "two", "three");
Set<Integer> set = Set.of(1, 2, 3);
Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);

// For larger maps
Map<String, Integer> mapEntries = Map.ofEntries(
    Map.entry("one", 1),
    Map.entry("two", 2),
    Map.entry("three", 3)
);
```

## 🔄 Collection Streams Integration

Collections work seamlessly with Java Streams:

```java
// Filter and transform a collection using streams
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

// Group elements by a property
Map<String, List<Employee>> empsByDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

## 🧪 Collections vs Arrays

| Feature | `Collections` | `Arrays` |
|---------|---------------|----------|
| Resizable | ✅ Yes | ❌ No |
| Type Safety | ✅ Yes (with Generics) | ❌ No (for primitives) |
| Utility Class | `Collections` | `Arrays` |
| Utility Methods | sort(), reverse(), min(), max() | sort(), binarySearch(), fill(), copyOf() |
| Memory Usage | Higher (due to object overhead) | Lower (especially for primitives) |
| Performance | Variable (depends on implementation) | Generally faster for primitives |

---

## 🔐 Immutability with `Collections`
```java
List<String> immutableList = Collections.unmodifiableList(new ArrayList<>(List.of("A", "B")));
```

### Immutability Patterns

```java
// 1. Defensive copying
public List<String> getItems() {
    return new ArrayList<>(this.items); // return a copy
}

// 2. Unmodifiable view
public List<String> getItems() {
    return Collections.unmodifiableList(items);
}

// 3. Java 9+ immutable collections
public List<String> getDefaultItems() {
    return List.of("item1", "item2");
}
```

## 📊 Performance Characteristics

| Collection | Implementation | Add | Remove | Get | Contains | Memory Usage |
|------------|---------------|------|--------|-----|----------|-------------|
| ArrayList | Dynamic Array | O(1)* | O(n) | O(1) | O(n) | Low |
| LinkedList | Doubly-Linked List | O(1) | O(1)** | O(n) | O(n) | High |
| HashSet | Hash Table | O(1) | O(1) | N/A | O(1) | Medium |
| TreeSet | Red-Black Tree | O(log n) | O(log n) | N/A | O(log n) | Medium |
| HashMap | Hash Table | O(1) | O(1) | O(1) | O(1) | Medium |
| TreeMap | Red-Black Tree | O(log n) | O(log n) | O(log n) | O(log n) | Medium |

\* Amortized - occasional O(n) when resizing
\** O(1) when position is known, O(n) to find

## 🔄 Concurrent Collections

Java provides thread-safe collections designed for concurrent access:

```java
// ConcurrentHashMap - efficiently supports concurrent reads and a configurable number of writes
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// CopyOnWriteArrayList - thread-safe variant optimized for reads
List<String> concurrentList = new CopyOnWriteArrayList<>();

// ConcurrentSkipListMap - concurrent sorted map implementation
NavigableMap<String, Integer> skipList = new ConcurrentSkipListMap<>();

// BlockingQueue - supports operations that wait for queue to become non-empty/non-full
BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>(100);
```

---

## 🧠 Interview Questions & Answers

### Q1: What is the difference between Collection and Collections?
**A:** `Collection` is an interface that represents a group of objects, whereas `Collections` is a utility class with static methods for collection operations like sorting, searching, and synchronizing.

### Q2: Can we create an object of Collection?
**A:** No. `Collection` is an interface and cannot be instantiated. You instantiate its implementations like `ArrayList`, `HashSet`, etc.

### Q3: What is the purpose of Collections.unmodifiableList()?
**A:** It returns an immutable view of the specified list. Modifying the original list will reflect changes, but direct modification of the unmodifiable list will throw `UnsupportedOperationException`.

### Q4: How can you make a collection thread-safe?
**A:** By using wrappers like `Collections.synchronizedList()` or thread-safe collections like `CopyOnWriteArrayList`.

### Q5: How is Collections.sort() different from Arrays.sort()?
**A:** `Collections.sort()` works on `List` implementations, while `Arrays.sort()` works on arrays. Both perform in-place sorting but on different types.

### Q6: What is a fail-fast iterator?
**A:** A fail-fast iterator throws `ConcurrentModificationException` if the collection is modified after the iterator is created. Most collections in Java use fail-fast iterators.

### Q7: What is the difference between Collections.emptyList() and new ArrayList<>()?
**A:** `Collections.emptyList()` returns an immutable empty list singleton object that cannot be modified. `new ArrayList<>()` creates a new mutable empty list that can be modified.

### Q8: How do Java 9 collection factory methods differ from constructors?
**A:** Java 9 factory methods (`List.of()`, `Set.of()`, etc.) create immutable collections, while constructors create mutable ones. Factory methods are more concise and prevent null elements.

### Q9: How would you create a thread-safe list in Java?
**A:** Three ways:
1. Use `Collections.synchronizedList(new ArrayList<>())`
2. Use `CopyOnWriteArrayList` for read-heavy scenarios
3. Use external synchronization with a regular list

### Q10: What's the difference between `equals()` and `==` when comparing collections?
**A:** `equals()` compares the contents of the collections (elements), while `==` checks if both references point to the same memory location (object identity).

### Q11: How can you sort a list in reverse order?
**A:** Three ways:
1. `Collections.sort(list, Collections.reverseOrder())`
2. `Collections.sort(list); Collections.reverse(list);`
3. `list.sort(Comparator.reverseOrder())` (Java 8+)

### Q12: What is the time complexity of adding an element to ArrayList vs LinkedList?
**A:** For ArrayList, it's O(1) amortized time (occasional O(n) when resizing). For LinkedList, it's O(1) when adding to the beginning or end, but O(n) when adding to a specific position because you need to traverse to that position.

### Q13: What are the differences between HashSet and TreeSet?
**A:** HashSet uses HashMap internally and offers O(1) operations with no ordering guarantees. TreeSet uses TreeMap internally, maintains elements in sorted order, but operations are O(log n).

### Q14: How do you efficiently remove elements from a collection during iteration?
**A:** Use the `Iterator.remove()` method:
```java
Iterator<String> it = collection.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (shouldRemove(item)) {
        it.remove(); // Safe way to remove during iteration
    }
}
```

### Q15: What are weak collections in Java?
**A:** Weak collections (like `WeakHashMap`) hold weak references to objects, allowing them to be garbage collected when no strong references exist. They're useful for implementing caches.

---

## 📘 Summary
- `Collection` is a foundational interface for most Java collections.
- `Collections` is a helper class with static methods to manipulate collections.
- Use `Collection` when designing flexible APIs.
- Use `Collections` to apply ready-to-use algorithms.
- Choose the right collection implementation based on your specific requirements for performance, thread safety, and memory usage.
- Modern Java (8+) offers powerful options for collection processing through streams and new API methods.

> 🔗 Next: [Comparable vs Comparator](./09_Comparable_vs_Comparator.md)
