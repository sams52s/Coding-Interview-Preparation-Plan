# 📚 Java Collections Framework

The Java Collections Framework (JCF) provides a set of interfaces and classes to store, retrieve, and manipulate data efficiently. It supports various types of collections such as lists, sets, queues, and maps.

---

## 🔗 Core Interfaces

| Interface | Description |
|----------|-------------|
| `List`   | Ordered collection, allows duplicates. Ex: `ArrayList`, `LinkedList` |
| `Set`    | Unordered, no duplicates. Ex: `HashSet`, `TreeSet` |
| `Queue`  | FIFO data structure. Ex: `PriorityQueue`, `Deque` |
| `Map`    | Key-value pairs. Ex: `HashMap`, `TreeMap` |

### 🧭 Interface Hierarchy Diagram
```text
      Collection
        /   |   \
   List  Set  Queue
    |     |     |
ArrayList HashSet PriorityQueue
LinkedList TreeSet ArrayDeque

      Map
      /  \
HashMap TreeMap
```

---

## 🧱 Common Implementations

| Class              | Interface Implemented | Key Characteristics |
|--------------------|------------------------|----------------------|
| `ArrayList`        | `List`                 | Fast random access, slow insertion/deletion in middle |
| `LinkedList`       | `List`, `Deque`        | Fast insertion/deletion, slow random access |
| `HashSet`          | `Set`                  | No duplicates, unordered, backed by HashMap |
| `TreeSet`          | `NavigableSet`         | Sorted order, no duplicates |
| `HashMap`          | `Map`                  | Fast lookup, unordered |
| `LinkedHashMap`    | `Map`                  | Maintains insertion order |
| `TreeMap`          | `NavigableMap`         | Sorted keys |
| `PriorityQueue`    | `Queue`                | Elements ordered by natural/comparator order |
| `Deque`            | `Queue`                | Double-ended queue operations |

---

## 🔁 Iteration Mechanisms

- **Iterator**: Basic forward-only iterator.
- **ListIterator**: Bi-directional iterator for `List`.
- **Enumeration**: Legacy iterator (used with `Vector`, `Hashtable`).

```java
Iterator<String> itr = list.iterator();
while (itr.hasNext()) {
    System.out.println(itr.next());
}
```

### ✅ Interview Tip
- Use `ListIterator` when you need to **modify a list while iterating**.
- Use `Iterator.remove()` for safe element removal.

---

## 🧰 Collections Utility Class

The `Collections` class contains static methods for sorting, reversing, shuffling, etc.

```java
Collections.sort(list);
Collections.reverse(list);
Collections.shuffle(list);
Collections.unmodifiableList(list); // Makes list immutable
```

Also includes:
- `Collections.max()`, `Collections.min()`
- `Collections.copy()`

---

## 🆚 Comparable vs Comparator

- **Comparable**: Natural ordering using `compareTo()` method (inside class).
- **Comparator**: Custom ordering using `compare()` method (outside class).

```java
class Student implements Comparable<Student> {
    public int compareTo(Student s) {
        return this.id - s.id;
    }
}

Comparator<Student> byName = (a, b) -> a.name.compareTo(b.name);
```

### ✅ Interview Tip
- Prefer **Comparator** for flexible sorting (e.g., by name, age).
- Java 8 lambdas simplify Comparator usage.

---

## 🔒 Synchronization in Collections

- Legacy classes like `Vector`, `Hashtable` are synchronized.
- Use `Collections.synchronizedList()` for thread-safe wrappers:

```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

- For concurrent use:
  - `ConcurrentHashMap`
  - `CopyOnWriteArrayList`
  - `BlockingQueue`

---

## 🔁 Stream API and Collections

Use Stream API for declarative processing of collections:

```java
List<String> result = list.stream()
    .filter(s -> s.startsWith("A"))
    .sorted()
    .collect(Collectors.toList());
```

- `stream()`: for non-parallel
- `parallelStream()`: for parallel processing

### ✅ Interview Tip
- Use `Collectors.groupingBy()` for grouping data.
- Streams are best for **read-heavy**, **non-mutating** pipelines.

---

## ⚙️ Performance Comparison

| Feature        | `ArrayList` | `LinkedList` | `HashSet` | `TreeSet` |
|----------------|-------------|--------------|-----------|-----------|
| Access         | ✅ Fast      | 🚫 Slow       | N/A       | N/A       |
| Insert (mid)   | 🚫 Slow      | ✅ Fast       | ✅ Fast    | 🚫 Slow    |
| Search         | ✅ Fast      | 🚫 Slow       | ✅ Fast    | 🚫 Slower  |
| Memory         | Efficient   | Overhead     | Efficient | Overhead  |

### 📌 Real-world Example
- Use `ArrayList` for ordered, frequent read-heavy lists (e.g., user lists).
- Use `LinkedList` for queue-like data structures.
- Use `TreeSet`/`TreeMap` when sorting is required.

---

## ❄️ Immutable Collections

Use `Collections.unmodifiableXXX()` or `List.of()`, `Set.of()` (Java 9+):

```java
List<String> immutable = List.of("A", "B");
```

Also:
- `Map.of()` for creating small immutable maps
- Defensive copies: return immutable views from APIs

---

## 🧭 NavigableMap and NavigableSet

Provide methods for navigating keys/values:
- `lowerKey()`, `floorKey()`, `ceilingKey()`, `higherKey()`
- `subMap()`, `headMap()`, `tailMap()`

```java
NavigableMap<Integer, String> map = new TreeMap<>();
map.put(3, "C");
map.lowerKey(3); // Returns 2 if exists
```

### ✅ Use Case
- Use `NavigableMap` for time-series, price charts, and sorted navigation.

---

## 🧠 WeakHashMap and IdentityHashMap

- **WeakHashMap**:
  - Keys can be GC'd when not referenced elsewhere.
  - Useful for caches or memory-sensitive mappings.

- **IdentityHashMap**:
  - Uses `==` instead of `.equals()` for key comparison.
  - Useful when object identity matters (e.g., serialization frameworks).

---

## 🥇 PriorityQueue and Deque

- `PriorityQueue`: Orders elements using natural or custom comparator order.
- `Deque`: Double-ended queue. Supports both FIFO and LIFO operations.

```java
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("first");
deque.addLast("last");
```

### ✅ Interview Tip
- Use `PriorityQueue` for task scheduling, top-N problems.
- Use `Deque` for implementing stacks and queues efficiently.

---

## 📘 Summary Cheatsheet

| Interface | Common Classes           | Use Case                            |
|-----------|---------------------------|-------------------------------------|
| `List`    | `ArrayList`, `LinkedList` | Ordered sequence                    |
| `Set`     | `HashSet`, `TreeSet`      | No duplicates                       |
| `Queue`   | `PriorityQueue`, `Deque`  | FIFO/LIFO and priority management   |
| `Map`     | `HashMap`, `TreeMap`      | Key-value storage                   |

---

## 📚 Further Reading
- [Java Collections Framework Documentation](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/index.html)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)
- [Java Concurrency in Practice by Brian Goetz](https://www.oreilly.com/library/view/java-concurrency-in/9780134686097/)
