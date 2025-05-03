# 11. 🔄 Sorting and Searching in Java Collections

## 📌 Introduction
Sorting and searching are essential operations in data manipulation. Java provides efficient APIs in the `Collections` utility class, and functional programming via `Stream`, as well as the `Comparable` and `Comparator` interfaces to implement various sorting and searching strategies.

---

## 🧪 Sorting Collections

### ✅ Using `Collections.sort()`
Sorts a `List` into its natural ordering (must implement `Comparable`).
```java
List<Integer> numbers = Arrays.asList(5, 3, 2, 4, 1);
Collections.sort(numbers); // Ascending order
```

### 🔁 Sorting in Reverse Order
```java
Collections.sort(numbers, Collections.reverseOrder());
```

### 🧠 Custom Sorting with `Comparator`
```java
List<String> names = Arrays.asList("John", "Alice", "Bob");
Collections.sort(names, (a, b) -> a.length() - b.length()); // by length
```

### 👨‍🎓 Sorting Custom Objects
```java
class Student implements Comparable<Student> {
    int id;
    String name;

    public int compareTo(Student other) {
        return this.name.compareTo(other.name); // natural order by name
    }
}
Collections.sort(studentList); // uses compareTo
```

### 🛠 Sorting with `Comparator`
```java
Collections.sort(studentList, Comparator.comparingInt(s -> s.id));
Collections.sort(studentList, Comparator.comparing(Student::getName).reversed());
```

### 🚀 Java 8+ Stream-Based Sorting
```java
List<String> sorted = list.stream()
                          .sorted()
                          .collect(Collectors.toList());
```

### 🔗 Multi-Level Sorting
```java
Collections.sort(studentList, 
    Comparator.comparing(Student::getName)
              .thenComparing(Student::getId));
```

---

## 🔍 Searching Collections

### 🔍 Binary Search (Requires Sorted List)
```java
int index = Collections.binarySearch(list, key);
```

### 🔎 With Custom Comparator
```java
int index = Collections.binarySearch(list, key, comparator);
```

### ✅ Stream-based Searching
```java
Optional<String> result = list.stream()
                              .filter(item -> item.equals("target"))
                              .findFirst();
```

---

## ⚙️ Performance Complexity
| Operation              | Time Complexity |
|------------------------|-----------------|
| `sort()`               | O(n log n)      |
| `binarySearch()`       | O(log n)        |
| `stream().sorted()`    | O(n log n)      |

---

## 🧠 Best Practices
- Always sort a list before applying `binarySearch()`.
- Use `Comparator` for flexibility and multiple sorting criteria.
- Use method references for cleaner syntax: `Comparator.comparing(Class::method)`.
- Use `thenComparing()` for multi-field sorting.
- Prefer `Stream.sorted()` for functional programming styles.

---

## ❓ Interview Questions & Answers

### Q1: What's the difference between `Comparable` and `Comparator`?
**A:** `Comparable` is implemented by the class and defines its natural order via `compareTo()`. `Comparator` is used externally to impose different ordering with `compare()`.

### Q2: What happens if `binarySearch()` is used on an unsorted list?
**A:** The result is undefined. The list must be sorted first.

### Q3: How can we sort a list in descending order?
**A:** Use `Collections.reverseOrder()` or `.reversed()` on a comparator.

### Q4: Can we sort a list by multiple fields?
**A:** Yes. Chain comparators using `thenComparing()`, e.g., by name, then ID.

### Q5: How to sort nulls first or last?
```java
Collections.sort(list, Comparator.nullsFirst(Comparator.naturalOrder()));
```

### Q6: What's the advantage of `Comparator.comparing()`?
**A:** It's more concise and allows chaining for multiple fields.

### Q7: Is `Collections.sort()` stable?
**A:** Yes, it’s a stable sort since Java 7 (uses TimSort).

### Q8: When would you prefer `Stream.sorted()` over `Collections.sort()`?
**A:** When working with pipelines or wanting to return a new sorted stream without modifying the original list.

---

## 🧠 Advanced Sorting Techniques

### 🧵 Parallel Sorting (Java 8+)
```java
// For arrays
Arrays.parallelSort(array); // Uses Fork/Join framework

// For collections via parallel streams
List<Integer> sortedList = list.parallelStream()
                               .sorted()
                               .collect(Collectors.toList());
```

### 🌟 Specialized Collections with Built-in Ordering
- `TreeSet` / `TreeMap` - Red-black tree implementation, always sorted
- `PriorityQueue` - Heap-based implementation, elements retrieved by priority
- `ConcurrentSkipListSet` / `ConcurrentSkipListMap` - Thread-safe sorted collections

### 🔧 Advanced Comparator Techniques
```java
// Using composition with nulls handling
Comparator<Employee> byDepartment = Comparator
    .comparing(Employee::getDepartment, Comparator.nullsFirst(String::compareTo))
    .thenComparing(Employee::getSalary, Comparator.reverseOrder());

// Natural order with case insensitivity
Comparator<String> caseInsensitive = String.CASE_INSENSITIVE_ORDER;

// Using a map for custom ordering
Map<String, Integer> customOrder = Map.of("High", 1, "Medium", 2, "Low", 3);
Comparator<Task> byPriority = Comparator.comparing(
    task -> customOrder.getOrDefault(task.getPriority(), Integer.MAX_VALUE)
);
```

### 🎭 Sorting Immutable Collections
```java
// Creating sorted immutable lists (Java 9+)
List<String> immutableSorted = list.stream()
    .sorted()
    .collect(Collectors.collectingAndThen(
        Collectors.toList(), 
        Collections::unmodifiableList));

// Or with Java 10+
var immutableSorted = List.copyOf(sortedList);
```

## 🧪 Advanced Searching Techniques

### 🔎 Custom Binary Search
```java
// Finding insertion point when key doesn't exist
int index = Collections.binarySearch(list, key);
if (index < 0) {
    int insertionPoint = -index - 1;
    // insertionPoint is where key would be inserted
}
```

### 🌐 Range Search with NavigableSet/Map
```java
NavigableSet<Integer> set = new TreeSet<>(list);
// All elements between 10-20 inclusive
SortedSet<Integer> range = set.subSet(10, true, 20, true);
```

### 🔄 Searching in Reverse-Ordered Collections
```java
NavigableSet<Integer> descendingSet = new TreeSet<>(Comparator.reverseOrder());
descendingSet.addAll(list);
// First element less than or equal to key
Integer floor = descendingSet.floor(key);
```

---

## 🚀 Internal Sorting Algorithms in Java

| Collection Type | Algorithm | Notes |
|----------------|-----------|-------|
| `Arrays.sort()` (primitives) | Dual-Pivot Quicksort | O(n log n) average, not stable |
| `Arrays.sort()` (objects) | TimSort | O(n log n), stable, adaptive |
| `Collections.sort()` | TimSort | O(n log n), stable |
| `TreeMap/TreeSet` | Red-Black Tree | O(log n) insertion, maintains sorted order |
| `PriorityQueue` | Binary Heap | O(log n) insertion, O(1) peek at min/max |
| `Arrays.parallelSort()` | Fork/Join mergesort | O(n log n), uses multiple threads |

---

## ❓ Interview Questions & Answers

### Q9: How does `TimSort` work, and why is it used in Java?
**A:** TimSort is a hybrid sorting algorithm derived from merge sort and insertion sort. It's designed for real-world data, exploiting existing order ("runs") in the data. It's stable, adaptive (performs well on partially sorted data), and has O(n log n) worst-case performance. Java uses it because it outperforms traditional sorting algorithms on practical datasets.

### Q10: What is a stable sort and why does it matter?
**A:** A stable sort preserves the relative order of equal elements. This matters when sorting by multiple criteria sequentially (e.g., sort by department, then by salary). Without stability, the secondary sort could disrupt the primary sort's ordering.

### Q11: How would you efficiently find the top K elements in a large collection?
**A:** Use a PriorityQueue with size K, maintaining the K largest elements:
```java
PriorityQueue<Integer> topK = new PriorityQueue<>(k); // min-heap
for (Integer num : largeCollection) {
    if (topK.size() < k) {
        topK.offer(num);
    } else if (num > topK.peek()) {
        topK.poll();
        topK.offer(num);
    }
}
```

### Q12: How does `Arrays.parallelSort()` differ from `Arrays.sort()`?
**A:** `parallelSort()` uses the Fork/Join framework to divide the array and sort parts concurrently on multiple threads, then merge results. It generally performs better on large arrays and multicore systems, but adds overhead for small arrays. It's also stable for object arrays.

### Q13: What's the difference between `Comparator.comparing()` and `Comparator.thenComparing()`?
**A:** `comparing()` creates a new Comparator using a key extraction function, while `thenComparing()` extends an existing Comparator with a secondary sort criterion that's applied when the primary comparison results in equality.

### Q14: How would you handle case-insensitive sorting of strings?
**A:** Three approaches:
```java
// Option 1: Using String.CASE_INSENSITIVE_ORDER
Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

// Option 2: Using String methods
Collections.sort(list, (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()));

// Option 3: Using Collator for locale-aware comparison
Collator collator = Collator.getInstance();
collator.setStrength(Collator.SECONDARY); // Ignores case
Collections.sort(list, collator);
```

### Q15: How can we optimize sorting when we need to sort the same collection multiple times with different criteria?
**A:** Create sorted views without modifying the original collection:
```java
List<Employee> employees = /* original list */;

// Create sorted views without modifying the original
List<Employee> byNameView = employees.stream()
    .sorted(Comparator.comparing(Employee::getName))
    .collect(Collectors.toList());

List<Employee> bySalaryView = employees.stream()
    .sorted(Comparator.comparingDouble(Employee::getSalary))
    .collect(Collectors.toList());
```

### Q16: How would you implement sort functionality for a large dataset that doesn't fit into memory?
**A:** Use external sorting with a merge sort approach:
1. Divide the dataset into manageable chunks
2. Sort each chunk individually and write to disk
3. Perform a K-way merge on the sorted chunks

### Q17: What is natural ordering in Java, and what interfaces are required to implement it?
**A:** Natural ordering is the default way objects of a class are ordered. A class must implement the `Comparable<T>` interface and override the `compareTo()` method to define its natural ordering. Example:
```java
public class Event implements Comparable<Event> {
    private LocalDateTime timestamp;
    
    @Override
    public int compareTo(Event other) {
        return this.timestamp.compareTo(other.timestamp);
    }
}
```

### Q18: How would you efficiently check if a sorted list contains all elements from another list?
**A:** Use binary search for each element of the second list:
```java
boolean containsAll(List<Integer> sortedList, List<Integer> elements) {
    for (Integer element : elements) {
        if (Collections.binarySearch(sortedList, element) < 0) {
            return false;
        }
    }
    return true;
}
```

## ✅ Summary
Java offers powerful and flexible tools for sorting and searching, suitable for both simple and complex object structures. Understanding the internal algorithms and performance characteristics of Java's sorting and searching mechanisms is crucial for writing efficient code. By mastering `Comparator`, `Comparable`, parallel sorting, and specialized collections, developers can implement optimal solutions for diverse data manipulation requirements.

> 🔗 Next: [Performance Comparison Table](./12_Performance_Comparison_Table.md)
