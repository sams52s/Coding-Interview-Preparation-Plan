# 📘 Advanced Guide to `List` Interface in Java

## 📌 Introduction
The `List<E>` interface in Java, part of the `java.util` package, extends the `Collection` interface and represents an **ordered collection** (sequence). It permits **duplicate elements**, provides **indexed access**, and supports various traversal and manipulation operations.

---

## 🔍 Key Characteristics
- Maintains insertion order
- Allows duplicates and nulls
- Supports positional (indexed) access
- Allows random and sequential access
- Provides ListIterator for bidirectional traversal
- Supports bulk operations like `sort`, `replaceAll`, and `removeIf`

---

## 🧱 Common Implementations
| Implementation             | Characteristics                             | Best Use Case                          |
|---------------------------|----------------------------------------------|-----------------------------------------|
| `ArrayList`               | Backed by a dynamic array                    | Frequent access, rare insert/delete     |
| `LinkedList`              | Doubly linked list                          | Frequent insertions/deletions           |
| `Vector`                  | Synchronized, legacy                        | Thread-safe operations (legacy support) |
| `Stack`                   | Extends `Vector`, LIFO                       | Stack-based problems                    |
| `CopyOnWriteArrayList`    | Thread-safe with copy-on-write semantics     | Read-heavy concurrent applications      |

---

## 💡 Real-World Use Cases
- To-do list applications
- Playlist management in music apps
- Navigation history in browsers
- Ordered display of items in e-commerce
- Undo/redo functionality in editors

---

## 🛠️ Core Operations
```java
List<String> list = new ArrayList<>();

list.add("Java");             // Add to end
list.add(1, "Spring");        // Insert at index

String val = list.get(0);     // Access element

list.remove("Spring");        // Remove by object
list.remove(2);               // Remove by index

boolean exists = list.contains("Java");
int pos = list.indexOf("Java");

for (String item : list) {
    System.out.println(item);
}
```

---

## 🔄 Advanced Features

### 🔁 ListIterator Example
```java
ListIterator<String> it = list.listIterator();
while (it.hasNext()) {
    System.out.println(it.next());
    it.add("NewElement");
}
```

### 🔃 Sorting with Comparator
```java
list.sort((a, b) -> a.compareToIgnoreCase(b));
```

### 🔄 Replacing All Elements
```java
list.replaceAll(str -> str.toUpperCase());
```

### 🧩 SubList View
```java
List<String> sub = list.subList(1, 3); // Elements from index 1 to 2
```

### 🧪 Stream Operations
```java
list.stream()
    .filter(s -> s.startsWith("A"))
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

---

## 🚫 Common Pitfalls
- Using `==` instead of `.equals()` for object comparison
- Concurrent modification without using `ListIterator`
- Not considering the time complexity of `remove(index)` or `add(index, element)`
- Forgetting that `ArrayList` grows dynamically (can impact performance)

---

## 🔐 Thread Safety
- `ArrayList` and `LinkedList` are **not thread-safe**
- Use:
```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
List<String> cowList = new CopyOnWriteArrayList<>();
```

---

## 🧠 Interview Questions

### Q1: Why is `List` preferred over arrays?
**A:** Because it offers dynamic resizing, rich APIs, and better integration with Java Collections.

### Q2: Difference between `ArrayList` and `LinkedList`?
**A:** `ArrayList` is better for random access; `LinkedList` is better for frequent insertion/deletion.

### Q3: Difference between `ListIterator` and `Iterator`?
**A:** `ListIterator` supports bidirectional traversal and add/set/remove; `Iterator` supports only forward traversal and remove.

### Q4: When to use `CopyOnWriteArrayList`?
**A:** When thread safety is needed in read-heavy scenarios with infrequent writes.

### Q5: How to sort a list of custom objects?
**A:** Use `list.sort(Comparator)` or `Collections.sort(list, Comparator)`.

### Q6: What happens if you modify a list during iteration?
**A:** You'll get `ConcurrentModificationException` unless using `ListIterator` or concurrent classes.

### Q7: How can you make a List read-only?
**A:** Use `Collections.unmodifiableList(list)` to create a read-only view of the list. Any attempt to modify it will throw `UnsupportedOperationException`.

### Q8: How would you efficiently find the intersection of two lists?
**A:** Convert both lists to Sets and use `retainAll()`:
```java
List<String> list1 = Arrays.asList("a", "b", "c");
List<String> list2 = Arrays.asList("b", "c", "d");
Set<String> set1 = new HashSet<>(list1);
set1.retainAll(list2);
List<String> intersection = new ArrayList<>(set1);
```

### Q9: What's the difference between fail-fast and fail-safe iterators?
**A:** Fail-fast iterators (like ArrayList's) throw `ConcurrentModificationException` if modified during iteration. Fail-safe iterators (like CopyOnWriteArrayList's) work on a copy and don't throw exceptions when the collection is modified.

### Q10: How to find elements common to two lists while preserving duplicates?
**A:** 
```java
List<String> commonElements = new ArrayList<>(list1);
commonElements.retainAll(list2);
```

---

## ⏱️ Time Complexity Comparison

### ArrayList
| Operation             | Time Complexity |
|----------------------|-----------------|
| `get(index)`         | O(1)            |
| `add(element)`       | O(1) amortized  |
| `add(index, elem)`   | O(n)            |
| `remove(index)`      | O(n)            |
| `contains(elem)`     | O(n)            |
| `size()`             | O(1)            |
| `clear()`            | O(n)            |
| `isEmpty()`          | O(1)            |

### LinkedList
| Operation             | Time Complexity    |
|----------------------|---------------------|
| `get(index)`         | O(n)                |
| `add(element)`       | O(1)                |
| `add(index, elem)`   | O(n)                |
| `addFirst/Last(e)`   | O(1)                |
| `remove(index)`      | O(n)                |
| `removeFirst/Last()` | O(1)                |
| `contains(elem)`     | O(n)                |

---

## 🔋 Advanced List Operations

### 👥 List Views and Wrappers
```java
// Create a synchronized list
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Create an unmodifiable list
List<String> readOnlyList = Collections.unmodifiableList(list);

// Fixed-size list
List<String> fixedSizeList = Collections.nCopies(10, "default");

// Empty immutable list
List<String> emptyList = Collections.emptyList();

// Singleton list
List<String> singletonList = Collections.singletonList("only item");
```

### 🔄 Bulk Operations
```java
// Remove all elements from list1 that are in list2
list1.removeAll(list2);

// Keep only elements in list1 that are in list2
list1.retainAll(list2);

// Check if list1 contains all elements in list2
boolean containsAll = list1.containsAll(list2);

// Add all elements from list2 to list1
list1.addAll(list2);
```

### 🔍 Searching and Filtering
```java
// Binary search (requires sorted list)
int index = Collections.binarySearch(sortedList, "key");

// Filter with streams
List<String> filtered = list.stream()
    .filter(s -> s.length() > 5)
    .collect(Collectors.toList());

// Find first matching element
Optional<String> first = list.stream()
    .filter(s -> s.startsWith("A"))
    .findFirst();
```

### 🔧 Custom List Implementation Example
```java
public class CircularArrayList<E> extends AbstractList<E> {
    private Object[] elements;
    private int size = 0;
    private int head = 0;
    
    public CircularArrayList(int capacity) {
        elements = new Object[capacity];
    }
    
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (E) elements[(head + index) % elements.length];
    }
    
    public boolean add(E e) {
        // Implementation details...
        return true;
    }
    
    public int size() {
        return size;
    }
}
```

## 🚀 Performance Optimization Techniques

### 🔍 Pre-sizing Collections
```java
// Avoid resizing overhead by initializing with expected capacity
List<String> list = new ArrayList<>(10000);
```

### 🔄 Batch Processing
```java
// More efficient than adding one at a time
list.addAll(Arrays.asList("item1", "item2", "item3"));
```

### 🏃‍♂️ Parallel Processing
```java
// Process large lists in parallel
list.parallelStream()
    .filter(s -> s.length() > 5)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

## 📊 List vs Array vs Set
| Feature             | Array                   | List                     | Set                      |
|---------------------|--------------------------|---------------------------|--------------------------|
| Size                | Fixed                    | Dynamic                   | Dynamic                  |
| Type Support        | Primitives + Objects     | Objects only              | Objects only             |
| Flexibility         | Low                      | High                      | Medium                   |
| Performance (Access)| Fast O(1)                | O(1) to O(n)              | O(1) to O(n)             |
| Duplicates          | Allowed                  | Allowed                   | Not allowed              |
| Order               | Maintained               | Maintained                | Implementation dependent |
| Memory Efficiency   | High                     | Lower due to resizing     | Lower (overhead)         |
| Null Values         | One per index            | Multiple allowed          | At most one              |

## 🔰 Best Practices

1. **Choose the right implementation** based on your use case:
   - `ArrayList` for random access
   - `LinkedList` for frequent insertions/deletions
   - `CopyOnWriteArrayList` for concurrent read-heavy scenarios

2. **Avoid premature optimization** - start with ArrayList unless you have a specific reason not to

3. **Use factory methods** for creating lists:
   ```java
   List<String> list = List.of("a", "b", "c"); // Java 9+
   ```

4. **Consider immutability** for defensive programming

5. **Use specialized methods** over general ones for better performance:
   ```java
   // Better than list.get(list.size() - 1)
   LinkedList<String> linkedList = new LinkedList<>();
   String last = linkedList.getLast();
   ```

6. **Avoid list resizing** by initializing with expected capacity

7. **Use bulk operations** when possible instead of multiple single operations

## ✅ Summary
The `List` interface provides an abstraction for ordered collections with rich functionality. Understanding the performance characteristics and special features of each implementation allows developers to make optimal choices for their specific use cases. The robust API, combined with stream operations and utility methods from the Collections class, makes List one of the most versatile and widely used data structures in Java applications.

> 🔗 Next: [Set Interface](./04_Set_Interface.md)
