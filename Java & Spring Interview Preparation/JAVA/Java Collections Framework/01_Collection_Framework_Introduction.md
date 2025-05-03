# Introduction to Java Collection Framework

## Overview
The Java Collection Framework provides a unified architecture for representing and manipulating collections of objects. It contains interfaces, implementations, and algorithms that allow developers to store, retrieve, manipulate, and communicate data efficiently.

## Collection Framework Hierarchy
```
                                   Iterable<E>
                                       |
                                   Collection<E>
                    /               /        \              \
           List<E>              Queue<E>     Set<E>        Map<K,V> (not Collection)
          /      \              /     \      /    \         /    \
   ArrayList  LinkedList    PriorityQ  Deque HashSet TreeSet HashMap TreeMap
                              |         |      |             |
                              |      ArrayDeque LinkedHashSet LinkedHashMap
                              |
                           LinkedList
```

## Core Components of Collection Framework

### 1. Interfaces
- **Collection**: Root interface with basic methods like `add()`, `remove()`, `clear()`
- **List**: Ordered collection (sequence) with duplicates allowed
- **Set**: Collection with no duplicate elements
- **Queue**: Collection for holding elements for processing
- **Deque**: Double-ended queue
- **Map**: Key-value pair mappings (not a true Collection)

### 2. Implementations
- **ArrayList**: Dynamic array implementation
- **LinkedList**: Doubly linked list implementation
- **HashSet**: Hash table based implementation of Set
- **TreeSet**: Sorted Set implementation using a tree structure
- **HashMap**: Hash table based Map implementation
- **TreeMap**: Sorted Map implementation using a tree structure

### 3. Algorithms
Polymorphic algorithms that operate on collections:
- Sorting
- Searching
- Shuffling
- Reversing
- Finding min/max values

## Key Features

### 1. Reduced Programming Effort
The framework provides ready-to-use data structures and algorithms, eliminating the need to write custom implementations.

### 2. Increased Program Speed and Quality
The implementations offer high-performance data structures and algorithms designed by experts.

### 3. Interoperability
Common interfaces allow collections to work seamlessly with each other, enabling one implementation to be easily swapped for another.

### 4. Consistency
All collections follow similar patterns, making the API easier to learn and use.

### 5. Reusability
Generic interfaces and implementations can be applied across various applications.

## Basic Example

```java
import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        // Creating a List
        List<String> fruitList = new ArrayList<>();
        fruitList.add("Apple");
        fruitList.add("Banana");
        fruitList.add("Cherry");
        fruitList.add("Apple");  // Duplicates allowed in List
        
        System.out.println("List elements: " + fruitList);
        
        // Creating a Set
        Set<String> fruitSet = new HashSet<>(fruitList);
        System.out.println("Set elements (no duplicates): " + fruitSet);
        
        // Creating a Map
        Map<String, Integer> fruitCalories = new HashMap<>();
        fruitCalories.put("Apple", 95);
        fruitCalories.put("Banana", 105);
        fruitCalories.put("Cherry", 50);
        
        System.out.println("Map elements: " + fruitCalories);
        
        // Iterating through Map
        for(Map.Entry<String, Integer> entry : fruitCalories.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " calories");
        }
    }
}
```

## Evolution of Collection Framework

The Java Collection Framework has evolved over time:
- Introduced in Java 1.2
- Generics added in Java 5
- Enhanced with `forEach()` and lambda expressions in Java 8
- Convenience factory methods in Java 9
- Additional improvements in subsequent Java versions

## Advanced Topics in Collections

### 1. Performance Characteristics

| Collection | Add/Remove | Search | Access | Notes |
|------------|------------|--------|--------|-------|
| ArrayList | O(1) amortized / O(n) worst | O(n) | O(1) | Best for random access |
| LinkedList | O(1) | O(n) | O(n) | Best for frequent insertions/deletions |
| HashSet | O(1) | O(1) | O(1) | No order guarantee |
| TreeSet | O(log n) | O(log n) | O(log n) | Maintains sorted order |
| HashMap | O(1) | O(1) | O(1) | No order guarantee |
| TreeMap | O(log n) | O(log n) | O(log n) | Maintains sorted keys |

### 2. Concurrent Collections

Java provides thread-safe collections in the `java.util.concurrent` package:

- **ConcurrentHashMap**: Thread-safe alternative to HashMap with better concurrency
- **CopyOnWriteArrayList**: Thread-safe variant of ArrayList where mutations create a fresh copy
- **CopyOnWriteArraySet**: Set implementation backed by CopyOnWriteArrayList
- **ConcurrentSkipListMap/Set**: Concurrent replacements for TreeMap/TreeSet

```java
// Example of concurrent collections
Map<String, String> concurrentMap = new ConcurrentHashMap<>();
List<String> concurrentList = new CopyOnWriteArrayList<>();

// Safe for concurrent access by multiple threads
concurrentMap.put("key", "value");
concurrentList.add("element");
```

### 3. Legacy Collections

Several collections are considered legacy but still used in older codebases:

- **Vector**: Synchronized version of ArrayList
- **Stack**: LIFO implementation extending Vector
- **Hashtable**: Synchronized version of HashMap
- **Properties**: Specialized Hashtable for string key-value pairs

These should generally be avoided in new code in favor of their modern counterparts.

### 4. Immutable Collections (Java 9+)

Java 9 introduced factory methods for creating immutable collections:

```java
// Immutable List
List<String> immutableList = List.of("one", "two", "three");

// Immutable Map
Map<String, Integer> immutableMap = Map.of(
    "one", 1,
    "two", 2,
    "three", 3
);

// Immutable Set
Set<String> immutableSet = Set.of("one", "two", "three");
```

These collections throw UnsupportedOperationException if modification is attempted.

### 5. Working with the Collections Utility Class

The `Collections` class provides static methods for collection operations:

```java
List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 1, 2, 4));

// Sorting
Collections.sort(list);  // [1, 2, 3, 4, 5]

// Binary search (on sorted list)
int index = Collections.binarySearch(list, 3);  // Returns 2

// Reverse
Collections.reverse(list);  // [5, 4, 3, 2, 1]

// Shuffle
Collections.shuffle(list);  // Random order

// Min/Max
int min = Collections.min(list);
int max = Collections.max(list);

// Thread-safety wrappers
List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

// Unmodifiable wrappers
List<String> unmodifiableList = Collections.unmodifiableList(list);

// Empty collections
List<Object> emptyList = Collections.emptyList();
```

### 6. Comparable and Comparator

For custom sorting:

```java
// Using Comparable (natural ordering)
class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}

// Using Comparator (custom ordering)
Comparator<Person> nameComparator = new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
};

// Java 8 lambda style
Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
Comparator<Person> reverseAgeComparator = ageComparator.reversed();

// Chaining multiple comparators
Comparator<Person> multiComparator = Comparator
    .comparing(Person::getLastName)
    .thenComparing(Person::getFirstName)
    .thenComparingInt(Person::getAge);
```

### 7. Stream API Integration

Collections work seamlessly with Java 8's Stream API:

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Basic operations
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Numeric operations
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream().mapToInt(Integer::intValue).sum();

// Advanced operations
Map<Boolean, List<String>> partitionedNames = names.stream()
    .collect(Collectors.partitioningBy(name -> name.length() > 4));

Map<Integer, List<String>> groupedByLength = names.stream()
    .collect(Collectors.groupingBy(String::length));

// Parallel processing
long count = numbers.parallelStream()
    .filter(n -> n % 2 == 0)
    .count();
```

### 8. Specialized Collections

Java offers specialized collections for specific use cases:

```java
// EnumSet - highly efficient Set implementation for enum types
enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
EnumSet<Day> weekend = EnumSet.complementOf(weekdays);

// EnumMap - specialized Map implementation for enum keys
EnumMap<Day, String> dayActivities = new EnumMap<>(Day.class);
dayActivities.put(Day.MONDAY, "Work");
dayActivities.put(Day.SATURDAY, "Relax");

// BitSet - special collection for sets of bits/flags
BitSet bitSet = new BitSet(8);
bitSet.set(0);  // Set first bit
bitSet.set(3);  // Set fourth bit
boolean isSet = bitSet.get(3);  // true
```

### 9. Collection Views and Unmodifiable Wrappers

```java
// List views
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> subList = numbers.subList(1, 4); // View of elements [1, 4)

// Map views
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob", 85);
scores.put("Charlie", 90);

Set<String> keys = scores.keySet(); // View of keys
Collection<Integer> values = scores.values(); // View of values
Set<Map.Entry<String, Integer>> entries = scores.entrySet(); // View of entries

// Java 10+ unmodifiable copies
List<String> originalList = new ArrayList<>(Arrays.asList("a", "b", "c"));
List<String> copyOfList = List.copyOf(originalList);
```

### 10. Java 9+ Collection Factory Methods

```java
// Java 9+ compact collection creation
List<String> immutableList = List.of("one", "two", "three");
Set<Integer> immutableSet = Set.of(1, 2, 3, 4, 5);
Map<String, Integer> immutableMap = Map.of(
    "one", 1, 
    "two", 2, 
    "three", 3
);

// For larger maps
Map<String, Integer> largeMap = Map.ofEntries(
    Map.entry("one", 1),
    Map.entry("two", 2),
    Map.entry("three", 3),
    Map.entry("four", 4)
    // Can add many more entries
);
```

## Real-World Use Cases

1. **ArrayList**: 
   - UI component data models
   - Database query results storage
   - Dynamic lists where random access is frequent
   - Implementing stacks when not thread-safe

2. **LinkedList**: 
   - Implementation of queues and deques
   - Frequent insertions/deletions in the middle of the list
   - Undo functionality in applications
   - Music playlists with frequent rearrangement

3. **HashSet**: 
   - Removing duplicate entries
   - Membership checking (e.g., permissions)
   - Implementation of dictionaries
   - Tracking unique visitors to a website

4. **TreeSet**: 
   - Maintaining sorted unique elements
   - Range queries (e.g., finding all elements between x and y)
   - Implementing leaderboards
   - Calendar event storage where ordering matters

5. **HashMap**: 
   - Caching systems
   - Frequency counters
   - Symbol tables in compilers
   - Session storage in web applications

6. **TreeMap**: 
   - Sorted dictionaries
   - Time-based event scheduling
   - Range-based access to data
   - Phone directories or contact lists

7. **PriorityQueue**: 
   - Task scheduling by priority
   - Implementing algorithms like Dijkstra's
   - Bandwidth management
   - Event-driven simulations

8. **ConcurrentHashMap**: 
   - Thread-safe caches
   - Shared lookup tables in multi-threaded environments
   - Web server sessions
   - Online game state management

9. **ArrayDeque**:
   - Implementing both stack and queue
   - Sliding window problems
   - Work-stealing algorithms
   - Browser history navigation

10. **CopyOnWriteArrayList**:
    - Event listener lists
    - Read-heavy concurrent scenarios
    - Thread-safe iteration where modifications are rare

## Key Considerations

1. **Collection Selection**:
   - Consider access patterns (random vs. sequential)
   - Insertion/deletion frequency
   - Sorting requirements
   - Need for uniqueness

2. **Thread Safety**:
   - Use concurrent collections for multi-threaded access
   - Consider synchronization overhead
   - Choose between synchronization and copy-on-write
   - Be aware of atomic operations

3. **Performance Optimization**:
   - Specify initial capacity when known
   - Use specialized collections for specific data types
   - Consider memory footprint
   - Profile before optimization

4. **Common Pitfalls**:
   - Modifying collections during iteration
   - Ignoring returned values from collection operations
   - Using incorrect collection for your use case
   - Not considering memory impact of large collections

5. **Best Practices**:
   - Favor interfaces over implementations in declarations
   - Use immutable collections when possible
   - Leverage streams for bulk operations
   - Document collection usage expectations

## Interview Questions

### Basic Questions

1. **Q: What is the Java Collection Framework?**  
   **A:** The Java Collection Framework is a unified architecture for representing and manipulating collections, consisting of interfaces, implementation classes, and algorithms. It provides data structures to store, retrieve, manipulate, and communicate collections of objects efficiently.

2. **Q: What are the primary interfaces in the Java Collection Framework?**  
   **A:** The primary interfaces are:
   - Collection: Root interface
   - List: Ordered collection allowing duplicates
   - Set: Collection that cannot contain duplicates
   - Queue: Collection designed for holding elements for processing
   - Map: Object that maps keys to values (not a true Collection)
   - Deque: Double-ended queue supporting insertion and removal at both ends

3. **Q: What's the difference between ArrayList and LinkedList?**  
   **A:** ArrayList is backed by a dynamic array, offering O(1) random access but O(n) insertions/deletions in the middle. LinkedList is implemented as a doubly-linked list, providing O(1) insertions/deletions anywhere (once position is located) but O(n) random access. ArrayList is more memory-efficient for storage, while LinkedList is better for frequent modifications.

4. **Q: Explain the difference between HashSet and TreeSet.**  
   **A:** HashSet uses a hash table for storage, offering O(1) add/remove/contains operations with no ordering guarantees. TreeSet uses a red-black tree, maintains elements in sorted order, and provides O(log n) add/remove/contains operations. Choose HashSet for maximum performance when order doesn't matter, and TreeSet when you need sorted elements.

5. **Q: What is the difference between HashMap and Hashtable?**  
   **A:** Key differences:
   - Hashtable is synchronized (thread-safe), HashMap is not
   - HashMap allows one null key and multiple null values, Hashtable doesn't allow nulls
   - HashMap is generally faster than Hashtable due to lack of synchronization
   - Hashtable is considered legacy, with ConcurrentHashMap being the modern thread-safe alternative

### Intermediate Questions

6. **Q: How does HashMap work internally? What is the significance of the load factor?**  
   **A:** HashMap uses an array of buckets, each potentially containing a linked list (or tree in Java 8+) of entries. When adding a key-value pair:
   1. The key's hashCode() is used to determine the bucket
   2. If collisions occur, entries are stored in the same bucket
   
   The load factor (default 0.75) determines when the map is resized. If the map becomes more than 75% full, it resizes to maintain performance. A higher load factor reduces space overhead but increases lookup time, while a lower factor increases space usage but might improve performance.

7. **Q: What is the difference between fail-fast and fail-safe iterators?**  
   **A:** 
   - Fail-fast iterators (like those in HashMap, ArrayList) throw ConcurrentModificationException if the collection is modified while iterating (except through the iterator's methods).
   - Fail-safe iterators (like those in CopyOnWriteArrayList, ConcurrentHashMap) don't throw exceptions during concurrent modification because they work on a copy of the collection or use other mechanisms to handle concurrent access.
   
   Example:
   ```java
   // Fail-fast example
   List<String> list = new ArrayList<>();
   list.add("A"); list.add("B");
   Iterator<String> it = list.iterator();
   while(it.hasNext()) {
       String value = it.next();
       list.remove(value); // Will throw ConcurrentModificationException
   }
   
   // Fail-safe example
   List<String> concurrentList = new CopyOnWriteArrayList<>();
   concurrentList.add("A"); concurrentList.add("B");
   Iterator<String> safeIt = concurrentList.iterator();
   while(safeIt.hasNext()) {
       String value = safeIt.next();
       concurrentList.remove(value); // Will not throw exception
   }
   ```

8. **Q: What are the differences between List, Set, and Map interfaces?**  
   **A:** 
   - **List**: Ordered collection that allows duplicates. Elements accessible by index.
   - **Set**: Collection that doesn't allow duplicates. No guaranteed order (except for sorted sets).
   - **Map**: Key-value pairs where keys are unique. Not a true Collection, but part of the framework.
   
   Functionally, Lists are suitable when element order matters or duplicates are needed, Sets when uniqueness is required, and Maps when you need to associate values with keys.

### Advanced Questions

9. **Q: How would you implement a custom collection class that maintains elements in insertion order but also provides O(1) access by value?**  
   **A:** Combine a LinkedHashMap with a custom collection wrapper:
   ```java
   public class OrderedSet<E> {
       private final Map<E, Integer> positionMap = new HashMap<>();
       private final List<E> elements = new ArrayList<>();
       
       public boolean add(E element) {
           if (positionMap.containsKey(element)) {
               return false; // Already exists
           }
           positionMap.put(element, elements.size());
           elements.add(element);
           return true;
       }
       
       public boolean remove(E element) {
           Integer position = positionMap.remove(element);
           if (position == null) {
               return false;
           }
           
           // Remove from list and update positions of shifted elements
           elements.remove(position.intValue());
           for (int i = position; i < elements.size(); i++) {
               positionMap.put(elements.get(i), i);
           }
           return true;
       }
       
       public E getByPosition(int index) {
           return elements.get(index);
       }
       
       public int getPosition(E element) {
           Integer position = positionMap.get(element);
           return position != null ? position : -1;
       }
   }
   ```

10. **Q: What are concurrent collections and when would you use them?**  
    **A:** Concurrent collections (in java.util.concurrent) are designed for multi-threaded environments. Key examples:
    - ConcurrentHashMap: A thread-safe hash-based map that doesn't lock the entire collection
    - CopyOnWriteArrayList: A thread-safe variant that creates a new copy when modified
    - ConcurrentSkipListMap/Set: Thread-safe sorted collections
    
    Use these when multiple threads need to access or modify a collection simultaneously without external synchronization. They offer better scalability than synchronized collections by using techniques like lock striping, copy-on-write semantics, and compare-and-swap operations.

11. **Q: How would you design a custom thread-safe cache with expiration functionality using Java collections?**  
    **A:** Using ConcurrentHashMap with scheduled cleanup:
    ```java
    public class ExpiringCache<K, V> {
        private final ConcurrentHashMap<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
        private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        private final long defaultExpiryMs;
        
        public ExpiringCache(long defaultExpiryMs) {
            this.defaultExpiryMs = defaultExpiryMs;
            // Schedule periodic cleanup of expired entries
            scheduler.scheduleAtFixedRate(this::cleanup, 
                defaultExpiryMs/2, defaultExpiryMs/2, TimeUnit.MILLISECONDS);
        }
        
        public void put(K key, V value) {
            put(key, value, defaultExpiryMs);
        }
        
        public void put(K key, V value, long expiryMs) {
            long expiryTime = System.currentTimeMillis() + expiryMs;
            cache.put(key, new CacheEntry<>(value, expiryTime));
        }
        
        public V get(K key) {
            CacheEntry<V> entry = cache.get(key);
            if (entry == null) {
                return null;
            }
            
            // Check if entry has expired
            if (entry.isExpired()) {
                cache.remove(key);
                return null;
            }
            
            return entry.getValue();
        }
        
        private void cleanup() {
            for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
                if (entry.getValue().isExpired()) {
                    cache.remove(entry.getKey());
                }
            }
        }
        
        private static class CacheEntry<V> {
            private final V value;
            private final long expiryTime;
            
            CacheEntry(V value, long expiryTime) {
                this.value = value;
                this.expiryTime = expiryTime;
            }
            
            V getValue() {
                return value;
            }
            
            boolean isExpired() {
                return System.currentTimeMillis() > expiryTime;
            }
        }
    }
    ```

12. **Q: How would you implement a LRU (Least Recently Used) cache using Java collections?**  
    **A:** Using LinkedHashMap with access-order:
    ```java
    public class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;
        
        public LRUCache(int capacity) {
            // true for access-order, false for insertion-order
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            // Remove the eldest entry when size exceeds the capacity
            return size() > capacity;
        }
        
        // Optional: thread-safe version
        public synchronized V getOrDefault(K key, V defaultValue) {
            return super.getOrDefault(key, defaultValue);
        }
        
        public synchronized V put(K key, V value) {
            return super.put(key, value);
        }
    }
    ```

13. **Q: How are collections and streams related in modern Java?**  
    **A:** Collections are data structures for storing objects, while Streams (introduced in Java 8) provide a functional programming approach to process those collections. Key relationships:
    
    - All collections can be converted to streams via `.stream()` or `.parallelStream()`
    - Streams can be collected back into collections via `.collect()`
    - Streams enable functional-style operations on collections (map, filter, reduce)
    - Streams can represent infinite sequences while collections are always finite
    - Streams are lazily evaluated and optimized for pipelining operations
    
    Example of their synergy:
    ```java
    // Transform a collection using Stream API and collect back to a collection
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    Set<String> uppercaseNames = names.stream()
        .map(String::toUpperCase)
        .filter(name -> name.length() > 3)
        .collect(Collectors.toCollection(TreeSet::new));
    ```

## Additional Specialized Collections

### WeakHashMap and IdentityHashMap

- **WeakHashMap**: A Map implementation where the keys are held as weak references, allowing entries to be garbage-collected when the key is no longer referenced elsewhere. Useful for implementing caches and memory-sensitive mappings.

```java
WeakHashMap<Key, Value> cache = new WeakHashMap<>();
cache.put(new Key("id"), new Value());
// When the Key object is no longer referenced elsewhere, 
// it may be removed from the map by the garbage collector
```

- **IdentityHashMap**: A Map implementation that uses reference equality (==) instead of object equality (equals()) for keys. Primarily used when object identity, rather than object equality, is required.

```java
IdentityHashMap<Object, String> identityMap = new IdentityHashMap<>();
String s1 = new String("key");
String s2 = new String("key");
identityMap.put(s1, "value1");
identityMap.put(s2, "value2");
// Both entries remain in the map despite equal content
```

## Summary Cheatsheet

| Interface | Common Classes | Use Case |
|-----------|----------------|----------|
| `List`    | `ArrayList`, `LinkedList` | Ordered sequence with potential duplicates |
| `Set`     | `HashSet`, `TreeSet`, `LinkedHashSet` | No duplicates, with various ordering options |
| `Queue`   | `PriorityQueue`, `ArrayDeque` | FIFO/priority processing |
| `Deque`   | `ArrayDeque`, `LinkedList` | Double-ended queue (FIFO/LIFO) |
| `Map`     | `HashMap`, `TreeMap`, `LinkedHashMap` | Key-value mapping |

## Further Reading

- [Java Collections Framework Documentation](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/index.html)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/) - Chapter 9: Generics
- [Java Concurrency in Practice by Brian Goetz](https://jcip.net/) - For thread-safe collections

## Next Steps

Continue to [Collection Interfaces](./02_Collection_Interfaces.md) to learn about the core interfaces that define collection behavior.