# 📘 Java Collection Interfaces

Java Collection Framework is built around a set of **core interfaces** that define the behavior of different types of collections. These interfaces provide a unified architecture for storing, retrieving, and manipulating data.

---

## 📚 Core Collection Interfaces

### 📌 `Iterable<E>` – The Root Interface
```java
public interface Iterable<E> {
    Iterator<E> iterator();
    default void forEach(Consumer<? super E> action);
    default Spliterator<E> spliterator();
}
```
- Enables enhanced `for-each` loop
- Provides functional-style iteration support (Java 8+)

---

### 📌 `Collection<E>` – Foundation of Collections
```java
public interface Collection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    boolean add(E e);
    boolean remove(Object o);
    void clear();
    Object[] toArray();
    <T> T[] toArray(T[] a);
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c);
    boolean removeAll(Collection<?> c);
    boolean retainAll(Collection<?> c);
    default boolean removeIf(Predicate<? super E> filter);
    default Stream<E> stream();
    default Stream<E> parallelStream();
}
```
- Root interface for all collection types (except Map)
- Defines basic collection operations and batch utilities

#### 🔄 Iteration Methods
```java
// Classic iterator
Iterator<String> it = collection.iterator();
while(it.hasNext()) {
    String element = it.next();
    // Can modify collection during iteration
    if(element.isEmpty()) it.remove();
}

// Enhanced for-loop (Java 5+)
for(String element : collection) {
    // Cannot modify collection during iteration
}

// forEach() with lambda (Java 8+)
collection.forEach(element -> System.out.println(element));

// Stream API (Java 8+)
collection.stream()
    .filter(e -> e.length() > 3)
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

#### 🛡️ Unmodifiable & Immutable Collections
```java
// Unmodifiable view (pre-Java 9)
Collection<String> readOnly = Collections.unmodifiableCollection(collection);

// Immutable collections (Java 9+)
Collection<String> immutable = List.of("one", "two", "three");
Collection<String> immutable2 = Set.of("apple", "banana");
```

#### ⚡ Performance Considerations
- `contains()`, `remove()` - O(n) for most implementations
- `size()` - O(1) for most implementations
- Batch operations (`addAll`, `removeAll`) often more efficient than repeated single operations

---

### 📌 `List<E>` – Ordered Collection
- Represents an **ordered** collection (sequence)
- Allows **duplicate** and **null** elements
- Elements accessible by index (positional access)
- Preserves insertion order
- Provides more control via `ListIterator`

#### 🔧 Common Implementations:
- `ArrayList`: Resizable array
  - **Strengths**: O(1) random access, efficient iteration
  - **Weaknesses**: O(n) insertions/deletions in the middle
  - **Memory**: Contiguous memory, potential for wasted space
  - **Thread Safety**: Not thread-safe (use `Collections.synchronizedList()` or `CopyOnWriteArrayList`)
  
- `LinkedList`: Doubly linked list
  - **Strengths**: O(1) insertions/deletions at any position when iterator is positioned there
  - **Weaknesses**: O(n) random access, more memory overhead
  - **Memory**: Nodes scattered in memory, each with prev/next references
  - **Also Implements**: `Queue` and `Deque` interfaces
  
- `Vector`: Legacy synchronized list
  - **Thread-safe** but with performance overhead
  - Generally replaced by `ArrayList` + explicit synchronization
  
- `CopyOnWriteArrayList`: Thread-safe variant
  - Creates fresh copy on each mutation operation
  - Ideal for read-heavy, write-rare scenarios
  - No concurrent modification exceptions when iterating

#### 🧰 Advanced List Features
```java
// Sub-lists (views of original list)
List<String> subList = list.subList(2, 5); // Elements [2,5)

// ListIterator - bi-directional iteration and modification
ListIterator<String> iter = list.listIterator();
while(iter.hasNext()) {
    int index = iter.nextIndex();
    String element = iter.next();
    iter.set("modified-" + element); // Replace current element
    iter.add("new-element");       // Add after current position
}

// Random access optimization
if(list instanceof RandomAccess) {
    // Use index-based access for better performance
    for(int i = 0; i < list.size(); i++) {
        // Direct indexing is efficient
    }
}

// Algorithms from Collections utility class
Collections.sort(list);
Collections.binarySearch(list, "target");
Collections.reverse(list);
Collections.shuffle(list);
```

#### 🔍 Use Cases:
- Maintaining task lists
- Ordered history logs
- When index-based access is needed
- When insertion order matters
- Data processing pipelines

#### 📊 Performance Comparison

| Operation           | ArrayList       | LinkedList       |
|---------------------|-----------------|------------------|
| get(index)          | O(1)            | O(n)             |
| add(E)              | O(1) amortized* | O(1)             |
| add(index, E)       | O(n)            | O(n)**           |
| remove(index)       | O(n)            | O(n)**           |
| Iterator.remove()   | O(n)            | O(1)             |
| Contains(Object)    | O(n)            | O(n)             |

*May require resize
**O(1) if position is already known (e.g., with ListIterator)

---

### 📌 `Set<E>` – Uniqueness Guaranteed
- Collection that **does not allow duplicates**
- Implements mathematical set abstraction
- Equality is based on `equals()` and `hashCode()`
- May or may not maintain order

#### 🔧 Common Implementations:
- `HashSet`: Fast lookup, unordered
  - **Performance**: O(1) for add, remove, contains
  - **Internals**: Backed by HashMap with dummy values
  - **Requirements**: Good hashCode() implementation for performance
  - **Load Factor**: Controls space-time tradeoff (default 0.75)
  
- `LinkedHashSet`: Maintains insertion order
  - **Performance**: O(1) for add, remove, contains
  - **Memory**: Slightly more overhead than HashSet
  - **Use Case**: When both uniqueness and order matter
  
- `TreeSet`: Sorted set, uses natural or custom comparator
  - **Performance**: O(log n) for add, remove, contains
  - **Implements**: NavigableSet interface
  - **Sorting**: Natural order or via provided Comparator
  - **Operations**: floor(), ceiling(), higher(), lower()

- `EnumSet`: Specialized set for enum values
  - **Performance**: Ultra-efficient bit vector implementation
  - **Memory**: Extremely compact representation
  - **Limitation**: Can only store enum constants of a single type

#### 🧪 Set Operations
```java
// Mathematical set operations
Set<String> union = new HashSet<>(set1);
union.addAll(set2);  // Union

Set<String> intersection = new HashSet<>(set1);
intersection.retainAll(set2);  // Intersection

Set<String> difference = new HashSet<>(set1);
difference.removeAll(set2);  // Difference

// Check subset
boolean isSubset = set1.containsAll(set2);

// Converting between set types
Set<String> hashSet = new HashSet<>(collection);
Set<String> treeSet = new TreeSet<>(hashSet);
Set<String> linkedSet = new LinkedHashSet<>(treeSet);

// NavigableSet operations (TreeSet)
NavigableSet<Integer> navSet = new TreeSet<>();
navSet.add(1); navSet.add(10); navSet.add(5); navSet.add(20);
Integer lower = navSet.lower(5);     // Greatest element < 5
Integer floor = navSet.floor(5);     // Greatest element <= 5
Integer ceiling = navSet.ceiling(6); // Smallest element >= 6
Integer higher = navSet.higher(5);   // Smallest element > 5

// Descending order
NavigableSet<Integer> descSet = navSet.descendingSet();
```

#### 🔍 Use Cases:
- Unique tags in systems
- Preventing duplicate database entries
- Implementing mathematical sets
- Fast membership testing
- Removing duplicates from other collections

#### 🛠️ HashSet Implementation Details
```java
// How HashSet works internally
public class SimpleHashSet<E> {
    private HashMap<E, Object> map = new HashMap<>();
    private static final Object PRESENT = new Object();
    
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }
    
    public boolean contains(E e) {
        return map.containsKey(e);
    }
    
    public boolean remove(E e) {
        return map.remove(e) == PRESENT;
    }
}
```

#### ⚠️ Hash Collisions
- Objects with same hashCode but different equals() cause collisions
- Collisions degrade performance from O(1) to O(n) in worst case
- HashSet uses linked list or balanced tree to resolve collisions

#### 🧠 Additional Interview Questions & Answers

- **Q:** What happens if you add an object to a HashSet, modify its fields, and try to retrieve it?
  > **A:** You might not be able to find it. If the modifications affect the hashCode, the object's bucket location changes, but HashSet doesn't update its position. Always use immutable objects as keys or set elements.

- **Q:** How does HashSet prevent duplicate values?
  > **A:** It uses the object's hashCode() to determine the storage bucket and equals() to check for equality with existing elements in that bucket. An element is considered a duplicate if equals() returns true.

- **Q:** When should you use LinkedHashSet instead of HashSet?
  > **A:** Use LinkedHashSet when you need to maintain insertion order while still requiring O(1) lookup and uniqueness guarantee.

- **Q:** Why is TreeSet slower than HashSet for most operations?
  > **A:** TreeSet uses a Red-Black tree with O(log n) complexity for most operations to maintain sorted order, while HashSet uses a hash table with O(1) average case.

- **Q:** Can null be added to various Set implementations?
  > **A:** HashSet and LinkedHashSet allow one null element, but TreeSet doesn't allow null (would throw NullPointerException).

- **Q:** What happens if you try to use a class without proper equals() and hashCode() in a HashSet?
  > **A:** The set will use the default Object implementations which are based on object identity (memory address), potentially leading to duplicate logical values being stored.

- **Q:** How would you efficiently find common elements between two lists?
  > **A:** Convert one list to a Set, then iterate through the second list and check for membership in the Set. This reduces complexity from O(n²) to O(n).
  
- **Q:** What's the difference between fail-fast and fail-safe iterators?
  > **A:** Fail-fast iterators (like those in ArrayList/HashSet) throw ConcurrentModificationException if the collection is modified during iteration. Fail-safe iterators (like in CopyOnWriteArrayList/ConcurrentHashMap) operate on a snapshot and don't throw exceptions but may not reflect recent modifications.

- **Q:** How can you make a Set thread-safe?
  > **A:** Use Collections.synchronizedSet(set), ConcurrentHashMap.newKeySet(), or CopyOnWriteArraySet. Each has different performance characteristics and consistency guarantees.

- **Q:** What is the load factor in HashSet and how does it affect performance?
  > **A:** Load factor determines when to resize the internal table (default 0.75). Lower values waste space but reduce collision probability; higher values save memory but may increase lookup time.

---

### 📌 `Queue<E>` – First-In, First-Out (FIFO)
- Designed for holding elements **prior to processing**
- Follows **FIFO** order
- Offers additional inspection/removal methods: `peek()`, `poll()`, `offer()`

#### 🔧 Common Implementations:
- `LinkedList`: Can be used as Queue
- `PriorityQueue`: Orders elements by priority
- `ArrayDeque`: Double-ended queue without capacity restrictions

#### 🔍 Use Cases:
- Job scheduling
- Print queues

---

### 📌 `Deque<E>` – Double-Ended Queue
- Extension of `Queue` to support **insertion and removal from both ends**
- Can act as both **stack (LIFO)** and **queue (FIFO)**

#### 🔧 Common Implementations:
- `ArrayDeque`
- `LinkedList`

#### 🔍 Use Cases:
- Undo-redo functionality
- Navigation history (forward/backward)

---

### 📌 `Map<K, V>` – Key-Value Mappings
- Not part of the `Collection` hierarchy
- Associates **unique keys** with **values**
- Allows lookup, update, and iteration over key-value pairs

#### 🔧 Common Implementations:
- `HashMap`: Fast lookup, allows one null key
- `LinkedHashMap`: Preserves insertion order
- `TreeMap`: Sorted by key (natural or comparator)
- `Hashtable`: Legacy, synchronized

#### 🔍 Use Cases:
- Dictionary-style data storage
- Caching user sessions
- Mapping identifiers to objects

---

## 📈 Interface Hierarchy
```
java.lang.Iterable
    └── java.util.Collection
         ├── List
         ├── Set
         │   └── SortedSet
         │       └── NavigableSet
         └── Queue
             └── Deque

java.util.Map (separate hierarchy)
    └── SortedMap
        └── NavigableMap
```

---

## ⚖️ Comparison Table

| Interface | Ordered | Allows Duplicates | Null Allowed       | Key Feature                |
|-----------|---------|-------------------|---------------------|----------------------------|
| List      | ✅ Yes  | ✅ Yes             | ✅ Yes              | Indexed and ordered        |
| Set       | ❌ No   | ❌ No              | ✅ Yes (limited)     | Uniqueness guarantee       |
| Queue     | ❌ No   | ✅ Yes             | ✅ Yes              | FIFO ordering              |
| Map       | ❌ No   | ✅ (values only)   | ✅ 1 key (HashMap)  | Key-value pair mapping     |

---

## ✅ Optional Operations
- Not all implementations support all interface methods
- Unsupported operations will throw `UnsupportedOperationException`
- Allows:
  - Immutable Collections
  - Read-only wrappers
  - Fixed-size data structures

---

## 🚀 Java Version Highlights
- **Java 5**: Generics, enhanced for-loop
- **Java 8**: Default/static methods in interfaces, Stream API
- **Java 9+**: Factory methods (e.g., `List.of()`), improved immutability support

---

## 🧠 Interview Questions & Answers

- **Q:** Why doesn’t `Map` extend `Collection`?
  > Because `Map` handles pairs (keys and values), whereas `Collection` is designed for single elements.

- **Q:** What is the root interface of all collections?
  > `Iterable<E>` is the top-most interface for collection traversal.

- **Q:** What’s the difference between `add()` and `offer()` in queues?
  > `add()` may throw an exception if the queue is full; `offer()` returns false instead.

- **Q:** When should you prefer a `Set` over a `List`?
  > When element uniqueness is required and ordering isn't a concern.

- **Q:** What’s the difference between `HashMap` and `TreeMap`?
  > `HashMap` is unordered and faster; `TreeMap` keeps keys sorted.

- **Q:** What are optional operations in interfaces?
  > Methods that may not be supported in all implementations (e.g., `remove()` on an unmodifiable list).

---

## 🔁 Example: Using Multiple Collection Interfaces
```java
Collection<String> collection = new ArrayList<>();
collection.add("Apple");

List<String> list = new ArrayList<>(collection);
Set<String> set = new HashSet<>(list);
Queue<String> queue = new LinkedList<>(list);
Deque<String> deque = new ArrayDeque<>(list);

Map<String, Integer> map = new HashMap<>();
for (String fruit : list) map.put(fruit, fruit.length());
```

---

## ✅ Interface Selection Tips

- Use `List` when order and index-based access are needed
- Use `Set` when preventing duplicates is required
- Use `Queue`/`Deque` for task scheduling or stack/queue behaviors
- Use `Map` for fast key-based lookups
- Code to interfaces, not implementations (e.g., `List<String> list = new ArrayList<>();`)

---

> 📌 Next: [List Interface – Implementations & Use Cases](./03_List_Interface.md)
