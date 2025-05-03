# 📘 Java `Set` Interface - Complete Guide

## 📌 Introduction
The `Set` interface in Java, part of the `java.util` package, defines a collection that **does not allow duplicate elements**. It models the mathematical set abstraction and is a subtype of the `Collection` interface.

---

## 🔍 Key Characteristics
- **No duplicate elements allowed**
- **Null elements**: Only one `null` allowed (in implementations like `HashSet`)
- **Unordered or ordered**: Depends on implementation (e.g., `HashSet` vs. `TreeSet`)
- **Implements `Collection`**, inherits methods like `add()`, `remove()`, `contains()`

---

## 🧱 Common Implementations
| Implementation | Description | Use Case |
|----------------|-------------|----------|
| `HashSet` | Backed by a hash table | Fast lookup, insertion, deletion (no order) |
| `LinkedHashSet` | Maintains insertion order | Order-preserving set |
| `TreeSet` | Implements `SortedSet`, backed by a red-black tree | Sorted (ascending) unique elements |
| `EnumSet` | High-performance set for enum types | Working with enum constants |
| `CopyOnWriteArraySet` | Thread-safe, used in concurrent environments | Read-heavy concurrent apps |

---

## 🔄 Basic Operations
```java
Set<String> names = new HashSet<>();
names.add("Alice");
names.add("Bob");
names.add("Alice");  // Duplicate, ignored
System.out.println(names);  // Output: [Alice, Bob]
```

---

## 🔁 Iteration
```java
for (String name : names) {
    System.out.println(name);
}

// Or using iterator
Iterator<String> it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

---

## 🔀 Advanced Features

### Sorted and Navigable Sets
```java
SortedSet<Integer> sorted = new TreeSet<>();
sorted.add(10);
sorted.add(5);
sorted.add(15);
System.out.println(sorted);  // [5, 10, 15]

NavigableSet<Integer> nav = (NavigableSet<Integer>) sorted;
System.out.println(nav.floor(12));  // 10
```

### Bulk Operations
```java
Set<String> setA = new HashSet<>(List.of("A", "B", "C"));
Set<String> setB = new HashSet<>(List.of("B", "C", "D"));

setA.retainAll(setB);  // Intersection
System.out.println(setA);  // [B, C]
```

### Immutable Sets (Java 9+)
```java
Set<String> immutableSet = Set.of("A", "B", "C");
```

---

## 🧠 Interview Questions

### Q1. How does `HashSet` ensure uniqueness?
**A:** It uses the `hashCode()` and `equals()` methods to detect duplicates. Elements with the same hash code are further compared using `equals()`.

### Q2. Difference between `HashSet` and `TreeSet`?
**A:** `HashSet` offers constant-time performance (O(1)) for add, remove, and contains, but does not maintain order. `TreeSet` keeps elements sorted and provides log-time performance (O(log n)). `TreeSet` implements the `NavigableSet` interface and allows range operations like `headSet`, `tailSet`, and `subSet`.

### Q3. Why is `LinkedHashSet` used?
**A:** It maintains the insertion order, unlike `HashSet`, which offers no ordering guarantee. This makes it useful for predictable iteration order while still providing near-constant time performance. It's perfect for maintaining order-sensitive unique elements like a user's browsing history.

### Q4. Can we store null in a Set?
**A:** Yes, `HashSet` and `LinkedHashSet` allow a single `null` element. `TreeSet` does not allow `null` if natural ordering is used because `null` cannot be compared to other objects. When using a custom `Comparator` in `TreeSet`, the comparator must handle null values explicitly.

### Q5. Is `Set` thread-safe?
**A:** No, by default it is not. Use `Collections.synchronizedSet()` or concurrent implementations like `CopyOnWriteArraySet` for thread safety. `CopyOnWriteArraySet` is best for read-heavy scenarios with infrequent modifications.

### Q6. How do `equals()` and `hashCode()` affect `HashSet`?
**A:** For custom objects in a `HashSet`, proper implementation of both methods is crucial:
- If `equals()` returns true, `hashCode()` must return the same value for both objects
- Objects with equal hash codes are not necessarily equal (hash collisions)
- Improperly implemented methods can cause duplicate entries or make elements unfindable

### Q7. What is the load factor in `HashSet`?
**A:** The load factor (default 0.75) determines when the backing `HashMap` resizes. A higher load factor saves memory but increases lookup time due to hash collisions. A lower value decreases collision probability but uses more memory.

### Q8. How does `EnumSet` differ from other `Set` implementations?
**A:** `EnumSet` is a specialized Set implementation for enum types:
- Extremely memory efficient (bit vector internally)
- Much faster than `HashSet` for enum elements
- Cannot contain null or elements of different enum types
- Always maintains enum constants in their natural order

### Q9. When should I use `ConcurrentSkipListSet` over synchronized sets?
**A:** `ConcurrentSkipListSet` should be used when:
- Concurrent thread access is needed in a sorted set
- High concurrency with many threads is expected
- Lock-free thread safety is required
- Range operations need to be performed atomically

### Q10. What is the time complexity of common operations in different Set implementations?
**A:** 
- `HashSet`: O(1) for add/remove/contains
- `LinkedHashSet`: O(1) for add/remove/contains
- `TreeSet`: O(log n) for add/remove/contains
- `EnumSet`: O(1) for all operations

---

## 📊 Set vs List
| Feature | `Set` | `List` |
|---------|-------|--------|
| Duplicates | ❌ Not allowed | ✅ Allowed |
| Order | ❌ No (unless LinkedHashSet/TreeSet) | ✅ Maintains order |
| Access by Index | ❌ Not supported | ✅ Supported |
| Performance | Fast lookup | Fast index access |
| Memory Usage | Higher (hash tables/trees) | Lower for ArrayList |
| Null Elements | Implementation dependent | Generally allowed |

---

## 🔄 Advanced Set Operations

### Custom Objects in Sets
```java
class Person {
    private String name;
    private int age;
    
    // Constructor, getters, etc.
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

Set<Person> people = new HashSet<>();
people.add(new Person("John", 30)); // Added
people.add(new Person("John", 30)); // Ignored (duplicate)
```

### Custom Sorting with TreeSet
```java
// Sort by name, then by age if names are equal
TreeSet<Person> sortedPeople = new TreeSet<>(
    Comparator.comparing(Person::getName)
              .thenComparing(Person::getAge)
);
sortedPeople.add(new Person("Alice", 30));
sortedPeople.add(new Person("Bob", 25));
```

### Set Operations (Union, Intersection, Difference)
```java
Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4));
Set<Integer> setB = new HashSet<>(Arrays.asList(3, 4, 5, 6));

// Union (A ∪ B)
Set<Integer> union = new HashSet<>(setA);
union.addAll(setB); // [1, 2, 3, 4, 5, 6]

// Intersection (A ∩ B)
Set<Integer> intersection = new HashSet<>(setA);
intersection.retainAll(setB); // [3, 4]

// Difference (A - B)
Set<Integer> difference = new HashSet<>(setA);
difference.removeAll(setB); // [1, 2]

// Symmetric Difference (A △ B)
Set<Integer> symmetricDiff = new HashSet<>(setA);
symmetricDiff.addAll(setB);
Set<Integer> tmp = new HashSet<>(setA);
tmp.retainAll(setB);
symmetricDiff.removeAll(tmp); // [1, 2, 5, 6]
```

### EnumSet Operations
```java
enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

// Creating EnumSet
EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);

// Union
EnumSet<Day> allDays = EnumSet.allOf(Day.class);  // All enum constants

// Complement
EnumSet<Day> notWeekdays = EnumSet.complementOf(weekdays); // weekend days
```

### Using NavigableSet Features (Java 6+)
```java
NavigableSet<Integer> navSet = new TreeSet<>(Arrays.asList(1, 3, 5, 7, 9));

// Returns greatest element less than or equal to given element
Integer floor = navSet.floor(4);  // 3

// Returns least element greater than or equal to given element
Integer ceiling = navSet.ceiling(4);  // 5

// Returns greatest element less than given element
Integer lower = navSet.lower(5);  // 3

// Returns least element greater than given element
Integer higher = navSet.higher(5);  // 7

// Returns and removes the first element
Integer pollFirst = navSet.pollFirst();  // 1

// Returns descending view of the set
NavigableSet<Integer> descending = navSet.descendingSet();  // [9, 7, 5, 3]

// Returns subset view
NavigableSet<Integer> subSet = navSet.subSet(3, true, 7, true);  // [3, 5, 7]
```

### Immutable Sets (Java 9+)
```java
// Factory methods for immutable sets
Set<String> immutable1 = Set.of("a", "b", "c");

// Create immutable copy with Collectors (Java 10+)
Set<String> immutable2 = mutableSet.stream().collect(Collectors.toUnmodifiableSet());

// Copy of creates an unmodifiable copy (Java 10+)
Set<String> immutable3 = Set.copyOf(mutableSet);
```

### Thread-Safe Sets
```java
// Synchronized wrapper
Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());

// For read-heavy workloads with few modifications
Set<String> concurrentSet = new CopyOnWriteArraySet<>();

// Thread-safe sorted set
NavigableSet<String> concurrentSortedSet = new ConcurrentSkipListSet<>();
```

---

## ✅ Best Practices
- Override `equals()` and `hashCode()` properly for custom objects
- Choose `LinkedHashSet` when order matters
- Use `TreeSet` when sorted data is required
- Prefer `Set.of()` for immutable sets (Java 9+)
- Consider memory usage when creating large sets
- Use `EnumSet` for enum types (much more efficient)
- Avoid using `Set` when duplicates are acceptable
- Be careful with mutable objects as set elements (changing fields after insertion can break the set)
- Profile your application to choose between HashSet's speed and TreeSet's sorting
- Use `CopyOnWriteArraySet` only for read-heavy scenarios with few modifications

---

## 💡 Performance Tips
- Use `HashSet` for most use cases due to O(1) performance
- Initialize with expected capacity to minimize rehashing
- Consider using `LinkedHashSet` if you need both O(1) performance and predictable iteration order
- For extremely large sets of integers, consider specialized collections or bit sets
- Removing elements during iteration should be done through the Iterator's `remove()` method
- Use `EnumSet` whenever you're working with enum constants
- For concurrent scenarios, evaluate whether true concurrency is needed versus synchronization

---

## 📘 Summary
The `Set` interface is ideal for scenarios where uniqueness is required. It provides various implementations that cater to different needs like speed, order, or sorting. Understanding the internal workings and performance characteristics of each implementation allows you to choose the right tool for your specific requirements.

> 🔗 Next: [Queue and Deque Interface](./05_Queue_and_Deque.md)
