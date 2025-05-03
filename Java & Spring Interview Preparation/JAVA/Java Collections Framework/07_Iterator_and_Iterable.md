# 📘 Iterator and Iterable in Java

## 📌 Introduction
Java provides `Iterator` and `Iterable` interfaces to support element traversal in collections. These interfaces are foundational for using enhanced for-loops (`for-each`) and allow safe element removal during iteration.

---

## 🔄 Iterable Interface

### ✅ Definition
```java
public interface Iterable<T> {
    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) { ... }
    default Spliterator<T> spliterator() { ... }
}
```

### 🧠 Key Points
- Root interface for all collection types
- Enables use of enhanced `for-each` loop
- Introduced default methods in Java 8: `forEach()` and `spliterator()`

### Example
```java
List<String> list = Arrays.asList("A", "B", "C");
for (String item : list) {
    System.out.println(item);
}
```

---

## 🔁 Iterator Interface

### ✅ Definition
```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove(); // optional
}
```

### 🧠 Key Points
- Used to iterate over collections
- Provides methods to check and access the next element
- Allows removal of elements during iteration

### Example
```java
List<String> list = new ArrayList<>(List.of("Java", "Python", "C++"));
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String val = it.next();
    if (val.equals("Python")) {
        it.remove();
    }
}
System.out.println(list); // Output: [Java, C++]
```

---

## 🔁 ListIterator (Extension of Iterator)

### ✅ Definition
```java
public interface ListIterator<E> extends Iterator<E> {
    boolean hasPrevious();
    E previous();
    int nextIndex();
    int previousIndex();
    void set(E e);
    void add(E e);
}
```

### 🧠 Features
- Bidirectional traversal
- Modify list during iteration
- Available only to List implementations

### Example
```java
List<String> list = new ArrayList<>(List.of("A", "B", "C"));
ListIterator<String> li = list.listIterator();
while (li.hasNext()) {
    String val = li.next();
    li.set(val + "1");
}
System.out.println(list); // Output: [A1, B1, C1]
```

---

## 🚫 Common Pitfalls
- Using collection's `remove()` instead of iterator's `remove()` during iteration causes `ConcurrentModificationException`
- Attempting to use `ListIterator` on non-list collections
- Calling `next()` before checking `hasNext()` can lead to `NoSuchElementException`
- Using multiple iterators on the same collection concurrently without synchronization

---

## 🔐 Fail-Fast vs Fail-Safe Iterators

### Fail-Fast Iterators
- Work directly with the collection's internal data structure
- Use a modification counter (`modCount`) to detect concurrent modifications
- Throw `ConcurrentModificationException` if the collection is modified outside the iterator
- More memory-efficient but not thread-safe
- Examples: `ArrayList`, `HashMap`, `HashSet`, `LinkedList`

### Fail-Safe Iterators
- Work on a clone/snapshot of the collection data
- Don't throw exceptions when underlying collection changes during iteration
- May not reflect the latest state of the collection (potentially stale data)
- More memory intensive but thread-safe
- Examples: `CopyOnWriteArrayList`, `ConcurrentHashMap`, collections from `java.util.concurrent`

### Examples

```java
// Fail-Fast Example
List<String> list = new ArrayList<>(List.of("Java", "Python", "C++"));
for (String language : list) {
    if (language.equals("Python")) {
        list.remove(language); // Will throw ConcurrentModificationException
    }
}

// Fail-Safe Example
List<String> safeList = new CopyOnWriteArrayList<>(List.of("Java", "Python", "C++"));
for (String language : safeList) {
    if (language.equals("Python")) {
        safeList.remove(language); // Works fine
    }
}
```

---

## 🔄 Advanced Iterator Features

### The forEachRemaining() Method (Java 8+)
```java
List<String> languages = List.of("Java", "Python", "Kotlin", "Go");
Iterator<String> it = languages.iterator();
it.next(); // Skip first element
it.forEachRemaining(lang -> System.out.println("Processing: " + lang));
```

### Spliterator Interface
Introduced in Java 8 for parallel iteration support:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
Spliterator<Integer> spliterator = numbers.spliterator();
Spliterator<Integer> secondHalf = spliterator.trySplit();

// First half
spliterator.forEachRemaining(System.out::println); // 6-10

// Second half
secondHalf.forEachRemaining(System.out::println); // 1-5
```

### Implementing a Custom Iterator
```java
public class EvenNumberIterator implements Iterator<Integer> {
    private final int max;
    private int current = 0;
    
    public EvenNumberIterator(int max) {
        this.max = max;
    }
    
    @Override
    public boolean hasNext() {
        return current <= max;
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = current;
        current += 2;
        return result;
    }
}

// Usage
Iterator<Integer> evenNumbers = new EvenNumberIterator(10);
while (evenNumbers.hasNext()) {
    System.out.println(evenNumbers.next()); // 0, 2, 4, 6, 8, 10
}
```

### Creating a Custom Iterable
```java
public class EvenNumbers implements Iterable<Integer> {
    private final int max;
    
    public EvenNumbers(int max) {
        this.max = max;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new EvenNumberIterator(max);
    }
    
    // Inner iterator class
    private class EvenNumberIterator implements Iterator<Integer> {
        private int current = 0;
        
        private EvenNumberIterator(int max) {
            // Constructor
        }
        
        @Override
        public boolean hasNext() {
            return current <= max;
        }
        
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int result = current;
            current += 2;
            return result;
        }
    }
}

// Usage
EvenNumbers evens = new EvenNumbers(10);
for (int num : evens) {
    System.out.println(num); // 0, 2, 4, 6, 8, 10
}
```

---

## 📚 Advanced Use Cases

### 1. Lazy Iteration with Custom Iterators
```java
// Fibonacci sequence iterator - computes values on demand
public class FibonacciIterator implements Iterator<BigInteger> {
    private BigInteger current = BigInteger.ZERO;
    private BigInteger next = BigInteger.ONE;
    
    @Override
    public boolean hasNext() {
        return true; // Infinite sequence
    }
    
    @Override
    public BigInteger next() {
        BigInteger result = current;
        BigInteger temp = current.add(next);
        current = next;
        next = temp;
        return result;
    }
}
```

### 2. Composite Iterators
```java
// Iterator that combines multiple iterators
public class CompositeIterator<T> implements Iterator<T> {
    private final Queue<Iterator<T>> iterators = new LinkedList<>();
    
    public CompositeIterator(List<Iterator<T>> iteratorList) {
        iterators.addAll(iteratorList);
    }
    
    @Override
    public boolean hasNext() {
        // Remove empty iterators
        while (!iterators.isEmpty() && !iterators.peek().hasNext()) {
            iterators.poll();
        }
        return !iterators.isEmpty();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterators.peek().next();
    }
}
```

### 3. Batch Processing Iterator
```java
// Iterator that processes elements in batches
public class BatchIterator<T> implements Iterator<List<T>> {
    private final Iterator<T> source;
    private final int batchSize;
    
    public BatchIterator(Iterator<T> source, int batchSize) {
        this.source = source;
        this.batchSize = batchSize;
    }
    
    @Override
    public boolean hasNext() {
        return source.hasNext();
    }
    
    @Override
    public List<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        List<T> batch = new ArrayList<>(batchSize);
        for (int i = 0; i < batchSize && source.hasNext(); i++) {
            batch.add(source.next());
        }
        return batch;
    }
}
```

### 4. Paging Iterator
```java
// Iterator for pagination use cases
public class PagingIterator<T> implements Iterator<List<T>> {
    private final List<T> source;
    private final int pageSize;
    private int currentPage = 0;
    
    public PagingIterator(List<T> source, int pageSize) {
        this.source = source;
        this.pageSize = pageSize;
    }
    
    @Override
    public boolean hasNext() {
        return currentPage * pageSize < source.size();
    }
    
    @Override
    public List<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        int fromIndex = currentPage * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, source.size());
        currentPage++;
        return source.subList(fromIndex, toIndex);
    }
    
    public void reset() {
        currentPage = 0;
    }
}
```

### 5. Thread-Safe Iterator Wrapper
```java
// Thread-safe iterator wrapper
public class ThreadSafeIterator<T> implements Iterator<T> {
    private final Iterator<T> delegate;
    private final Object lock = new Object();
    
    public ThreadSafeIterator(Iterator<T> delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public boolean hasNext() {
        synchronized (lock) {
            return delegate.hasNext();
        }
    }
    
    @Override
    public T next() {
        synchronized (lock) {
            return delegate.next();
        }
    }
    
    @Override
    public void remove() {
        synchronized (lock) {
            delegate.remove();
        }
    }
}
```

### 6. Iterator-to-Stream Bridge
```java
// Java 8+ - Convert an Iterator to a Stream
public class IteratorStreamBridge {
    public static <T> Stream<T> iteratorToStream(Iterator<T> iterator) {
        // Create a Spliterator from the Iterator
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(
            iterator, Spliterator.ORDERED);
        
        // Get a Stream from the Spliterator
        return StreamSupport.stream(spliterator, false);
    }
    
    // Example usage
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        Iterator<String> iterator = list.iterator();
        
        // Process with Stream API
        iteratorToStream(iterator)
            .filter(s -> !s.equals("B"))
            .map(String::toLowerCase)
            .forEach(System.out::println);
    }
}
```

### 7. Lazy Loading Iterator
```java
// Iterator that loads data on demand from a data source
public class LazyLoadingIterator<T> implements Iterator<T> {
    private final Supplier<List<T>> dataLoader;
    private final int batchSize;
    private Iterator<T> currentBatch;
    private boolean hasMoreData = true;
    
    public LazyLoadingIterator(Supplier<List<T>> dataLoader, int batchSize) {
        this.dataLoader = dataLoader;
        this.batchSize = batchSize;
    }
    
    private boolean ensureDataLoaded() {
        if ((currentBatch == null || !currentBatch.hasNext()) && hasMoreData) {
            List<T> data = dataLoader.get();
            if (data.isEmpty()) {
                hasMoreData = false;
                return false;
            }
            currentBatch = data.iterator();
        }
        return currentBatch != null && currentBatch.hasNext();
    }
    
    @Override
    public boolean hasNext() {
        return ensureDataLoaded();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentBatch.next();
    }
}
```

### 8. Filtered Iterator
```java
// Iterator that filters elements based on a predicate
public class FilteredIterator<T> implements Iterator<T> {
    private final Iterator<T> delegate;
    private final Predicate<T> predicate;
    private T nextElement;
    private boolean hasNext;
    
    public FilteredIterator(Iterator<T> delegate, Predicate<T> predicate) {
        this.delegate = delegate;
        this.predicate = predicate;
        findNext();
    }
    
    private void findNext() {
        while (delegate.hasNext()) {
            T element = delegate.next();
            if (predicate.test(element)) {
                nextElement = element;
                hasNext = true;
                return;
            }
        }
        hasNext = false;
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = nextElement;
        findNext();
        return result;
    }
}
```

---

## 🔄 Iterator Design Patterns

### 1. External Iterator vs Internal Iterator
```java
// External Iterator - client controls iteration
List<String> list = Arrays.asList("A", "B", "C");
Iterator<String> it = list.iterator();  // External iterator
while (it.hasNext()) {
    System.out.println(it.next());
}

// Internal Iterator - collection controls iteration (Java 8+)
list.forEach(System.out::println);  // Internal iterator
```

### 2. Iterator-based Composite Pattern
```java
interface Component {
    Iterator<Component> iterator();
}

class Leaf implements Component {
    private String name;
    
    public Leaf(String name) {
        this.name = name;
    }
    
    @Override
    public Iterator<Component> iterator() {
        return Collections.emptyIterator();
    }
    
    @Override
    public String toString() {
        return name;
    }
}

class Composite implements Component {
    private String name;
    private List<Component> children = new ArrayList<>();
    
    public Composite(String name) {
        this.name = name;
    }
    
    public void addComponent(Component component) {
        children.add(component);
    }
    
    @Override
    public Iterator<Component> iterator() {
        return children.iterator();
    }
    
    public Iterator<Component> deepIterator() {
        return new CompositeIterator();
    }
    
    private class CompositeIterator implements Iterator<Component> {
        private Stack<Iterator<Component>> stack = new Stack<>();
        
        public CompositeIterator() {
            stack.push(children.iterator());
        }
        
        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            
            if (!stack.peek().hasNext()) {
                stack.pop();
                return hasNext();
            }
            
            return true;
        }
        
        @Override
        public Component next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            Iterator<Component> it = stack.peek();
            Component component = it.next();
            
            if (component instanceof Composite) {
                stack.push(((Composite) component).iterator());
            }
            
            return component;
        }
    }
    
    @Override
    public String toString() {
        return name;
    }
}
```

### 3. Iterator State Pattern
```java
interface IteratorState<T> {
    boolean hasNext();
    T next();
    void process(StatefulIterator<T> context);
}

class HasNextState<T> implements IteratorState<T> {
    @Override
    public boolean hasNext() {
        return true;
    }
    
    @Override
    public T next() {
        throw new IllegalStateException("Call hasNext() first");
    }
    
    @Override
    public void process(StatefulIterator<T> context) {
        if (context.hasMoreElements()) {
            context.setState(context.getNextState());
        } else {
            context.setState(context.getEndState());
        }
    }
}

class NextState<T> implements IteratorState<T> {
    @Override
    public boolean hasNext() {
        return true;
    }
    
    @Override
    public T next() {
        throw new IllegalStateException("Element already consumed");
    }
    
    @Override
    public void process(StatefulIterator<T> context) {
        context.setState(context.getHasNextState());
    }
}

class EndState<T> implements IteratorState<T> {
    @Override
    public boolean hasNext() {
        return false;
    }
    
    @Override
    public T next() {
        throw new NoSuchElementException();
    }
    
    @Override
    public void process(StatefulIterator<T> context) {
        // Do nothing, we're at the end
    }
}

class StatefulIterator<T> implements Iterator<T> {
    private final Iterable<T> source;
    private Iterator<T> iterator;
    private IteratorState<T> state;
    private T currentElement;
    
    private IteratorState<T> hasNextState = new HasNextState<>();
    private IteratorState<T> nextState = new NextState<>();
    private IteratorState<T> endState = new EndState<>();
    
    public StatefulIterator(Iterable<T> source) {
        this.source = source;
        this.iterator = source.iterator();
        this.state = hasNextState;
    }
    
    @Override
    public boolean hasNext() {
        state.process(this);
        return state.hasNext();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        currentElement = iterator.next();
        state.process(this);
        return currentElement;
    }
    
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }
    
    public void setState(IteratorState<T> state) {
        this.state = state;
    }
    
    public IteratorState<T> getHasNextState() {
        return hasNextState;
    }
    
    public IteratorState<T> getNextState() {
        return nextState;
    }
    
    public IteratorState<T> getEndState() {
        return endState;
    }
}
```

---

## 🔄 Iterator with Java Stream API

### 1. Converting Between Iterators and Streams
```java
List<String> list = Arrays.asList("Java", "Python", "C++");

// Iterator to Stream
Iterator<String> iterator = list.iterator();
Stream<String> stream = StreamSupport.stream(
    Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), 
    false);

// Stream to Iterator
Stream<String> stream2 = list.stream();
Iterator<String> iterator2 = stream2.iterator();
```

### 2. Parallel Iteration with Spliterators
```java
List<Integer> numbers = IntStream.rangeClosed(1, 1000)
    .boxed()
    .collect(Collectors.toList());

// Create a spliterator from the list
Spliterator<Integer> spliterator = numbers.spliterator();

// Split the spliterator for parallel processing
Spliterator<Integer> firstHalf = spliterator;
Spliterator<Integer> secondHalf = firstHalf.trySplit();

// Process in parallel (manually)
CompletableFuture<Integer> sum1 = CompletableFuture.supplyAsync(() -> {
    int sum = 0;
    firstHalf.forEachRemaining(i -> sum += i);
    return sum;
});

CompletableFuture<Integer> sum2 = CompletableFuture.supplyAsync(() -> {
    int sum = 0;
    secondHalf.forEachRemaining(i -> sum += i);
    return sum;
});

int totalSum = sum1.join() + sum2.join();
System.out.println("Total Sum: " + totalSum);

// More easily with parallel streams
int parallelSum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
System.out.println("Parallel Sum: " + parallelSum);
```

### 3. Custom Spliterator for Specialized Iteration
```java
public class WordSpliterator implements Spliterator<String> {
    private final String[] words;
    private int start;
    private final int end;
    
    public WordSpliterator(String[] words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }
    
    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
        if (start < end) {
            action.accept(words[start++]);
            return true;
        }
        return false;
    }
    
    @Override
    public Spliterator<String> trySplit() {
        int currentSize = end - start;
        if (currentSize <= 1) {
            return null;
        }
        
        int splitPoint = start + currentSize / 2;
        WordSpliterator spliterator = new WordSpliterator(words, start, splitPoint);
        start = splitPoint;
        return spliterator;
    }
    
    @Override
    public long estimateSize() {
        return end - start;
    }
    
    @Override
    public int characteristics() {
        return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
    }
    
    // Usage
    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy dog";
        String[] words = text.split(" ");
        
        Spliterator<String> spliterator = new WordSpliterator(words, 0, words.length);
        Stream<String> stream = StreamSupport.stream(spliterator, true);
        
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
}
```

---

## 🌐 Advanced Iterator Scenarios

### 1. Event-driven Iterator
```java
public class EventIterator<T> implements Iterator<T> {
    private final BlockingQueue<T> queue;
    private final AtomicBoolean done = new AtomicBoolean(false);
    private T next;

    public EventIterator() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void push(T item) {
        queue.add(item);
    }

    public void finish() {
        done.set(true);
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        
        try {
            next = queue.poll(100, TimeUnit.MILLISECONDS);
            return next != null || !done.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        T result = next;
        next = null;
        return result;
    }
}
```

### 2. Database Result Iterator
```java
public class ResultSetIterator<T> implements Iterator<T>, AutoCloseable {
    private final ResultSet resultSet;
    private final Connection connection;
    private final Function<ResultSet, T> mapper;
    private boolean hasNext;
    
    public ResultSetIterator(Connection connection, String query, Function<ResultSet, T> mapper) 
            throws SQLException {
        this.connection = connection;
        Statement stmt = connection.createStatement();
        this.resultSet = stmt.executeQuery(query);
        this.mapper = mapper;
        this.hasNext = resultSet.next();
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        
        try {
            T current = mapper.apply(resultSet);
            hasNext = resultSet.next();
            return current;
        } catch (SQLException e) {
            throw new RuntimeException("Error reading from ResultSet", e);
        }
    }
    
    @Override
    public void close() throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    // Usage example with try-with-resources
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        try (Connection conn = DriverManager.getConnection(url, "user", "password");
             ResultSetIterator<String> iterator = new ResultSetIterator<>(
                 conn, 
                 "SELECT name FROM users", 
                 rs -> {
                     try {
                         return rs.getString("name");
                     } catch (SQLException e) {
                         return null;
                     }
                 })) {
            
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 3. Self-paging Iterator for API Calls
```java
public class PagingApiIterator<T> implements Iterator<T> {
    private final Function<Integer, List<T>> pageLoader;
    private final int pageSize;
    private List<T> currentPage = Collections.emptyList();
    private int currentIndex = 0;
    private int currentPageNumber = 0;
    private boolean hasMorePages = true;
    
    public PagingApiIterator(Function<Integer, List<T>> pageLoader, int pageSize) {
        this.pageLoader = pageLoader;
        this.pageSize = pageSize;
    }
    
    private boolean ensureDataLoaded() {
        if (currentIndex >= currentPage.size() && hasMorePages) {
            loadNextPage();
        }
        
        return currentIndex < currentPage.size();
    }
    
    private void loadNextPage() {
        currentPage = pageLoader.apply(currentPageNumber);
        currentPageNumber++;
        currentIndex = 0;
        
        if (currentPage.size() < pageSize) {
            hasMorePages = false;
        }
    }
    
    @Override
    public boolean hasNext() {
        return ensureDataLoaded();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        return currentPage.get(currentIndex++);
    }
    
    // Example usage with a REST API
    public static void main(String[] args) {
        // Simulate API calls returning paginated data
        Function<Integer, List<String>> apiClient = pageNum -> {
            System.out.println("Loading page " + pageNum);
            // In real code, this would be a REST API call
            if (pageNum < 3) {
                return Arrays.asList("Item " + (pageNum * 10 + 1), 
                                    "Item " + (pageNum * 10 + 2), 
                                    "Item " + (pageNum * 10 + 3));
            }
            return Collections.emptyList();
        };
        
        Iterator<String> iterator = new PagingApiIterator<>(apiClient, 3);
        
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

---

## 🧠 Interview Questions

### Q1: What is the difference between `Iterator` and `ListIterator`?
**A:** `Iterator` supports forward-only traversal with operations like `hasNext()`, `next()`, and `remove()`. `ListIterator` extends this functionality with bidirectional traversal (`hasPrevious()`, `previous()`), element replacement (`set()`), addition (`add()`), and index tracking (`nextIndex()`, `previousIndex()`).

### Q2: Why should you use `iterator.remove()` instead of `collection.remove()`?
**A:** To avoid `ConcurrentModificationException`. The iterator's `remove()` method updates the iterator's state and the collection's modification count atomically, ensuring consistency between the iterator and the collection.

### Q3: What collections support `ListIterator`?
**A:** Only classes that implement the `List` interface such as `ArrayList` and `LinkedList`.

### Q4: How does a fail-fast iterator work?
**A:** It checks for structural modifications via a modification counter (`modCount`). The iterator stores this counter's value at creation. During iteration, it compares the current `modCount` with the stored value. If different, it throws a `ConcurrentModificationException`.

### Q5: When would you choose a fail-safe iterator over a fail-fast iterator?
**A:** Use fail-safe iterators when:
- Working in multi-threaded environments
- Needing to modify a collection during iteration without exceptions
- Thread safety is more important than memory efficiency
- You're okay with potentially working on a slightly stale view of the collection

### Q6: What are the trade-offs between using an Iterator vs the forEach() method?
**A:** 
- **Iterator**: More verbose but offers finer control, allows pausing iteration, and enables element removal.
- **forEach()**: More concise and functional but less flexible. Cannot break out early and doesn't allow modifying the collection during iteration.

### Q7: How would you implement an iterator that skips certain elements?
**A:** Create a custom iterator that overrides the `next()` method to skip elements based on a predicate:

```java
public class FilteringIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<T> predicate;
    private T nextElement;
    private boolean hasNext;
    
    public FilteringIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        findNext();
    }
    
    private void findNext() {
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                nextElement = element;
                hasNext = true;
                return;
            }
        }
        hasNext = false;
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        T result = nextElement;
        findNext();
        return result;
    }
}
```

### Q8: What's the difference between Enumeration and Iterator interfaces?
**A:** 
- **Enumeration** is an older interface with methods `hasMoreElements()` and `nextElement()`
- **Iterator** is newer with `hasNext()`, `next()`, and `remove()` methods
- Iterator allows removing elements; Enumeration is read-only
- Enumeration is considered legacy and has been largely replaced by Iterator

### Q9: How does the Spliterator interface support parallel processing?
**A:** Spliterator supports parallel processing through:
- The `trySplit()` method that partitions the source data for concurrent processing
- Characteristic flags that describe spliterator properties (ORDERED, DISTINCT, SORTED, etc.)
- Specialized implementations for specific collection types optimized for parallel operations
- Integration with the Stream API to enable efficient parallel stream operations

### Q10: Can you make a collection immutable but still iterable?
**A:** Yes. Collections can be made immutable using wrappers like `Collections.unmodifiableList()` while still supporting iteration. The iterator will work but its `remove()`, if called, will throw `UnsupportedOperationException`.

### Q11: How would you implement a thread-safe iterator?
**A:** To implement a thread-safe iterator, I would:
1. Use synchronization around critical sections of the iterator's methods
2. Consider using a copy-on-write collection like `CopyOnWriteArrayList` to avoid concurrent modification issues
3. Use a mutex lock to coordinate access between threads
4. Consider using atomic variables for state management

```java
public class ThreadSafeIterator<T> implements Iterator<T> {
    private final Iterator<T> delegate;
    private final ReentrantLock lock = new ReentrantLock();
    
    public ThreadSafeIterator(Iterator<T> delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public boolean hasNext() {
        lock.lock();
        try {
            return delegate.hasNext();
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public T next() {
        lock.lock();
        try {
            return delegate.next();
        } finally {
            lock.unlock();
        }
    }
}
```

### Q12: Compare the performance characteristics of different iterator implementations (ArrayList vs LinkedList vs HashSet).
**A:** 
- **ArrayList Iterator**: O(1) for `next()` and `hasNext()` operations due to direct indexing, but O(n) for `remove()` due to element shifting
- **LinkedList Iterator**: O(1) for all operations (`next()`, `hasNext()`, `remove()`) as it only involves pointer manipulation
- **HashSet Iterator**: O(1) for `hasNext()`, but `next()` can be O(h) where h is the hash table's capacity, as it may need to traverse empty buckets

Iterator traversal performance is directly tied to the underlying data structure. ArrayList has the best cache locality making it fastest for large datasets when just iterating sequentially, while LinkedList performs better for frequent insertions/deletions during iteration.

### Q13: What is the relationship between Java's for-each loop and the Iterator pattern?
**A:** The for-each loop (enhanced for loop) is syntactic sugar for using an iterator. When you write:

```java
for (String s : stringCollection) {
    System.out.println(s);
}
```

The Java compiler translates it to:

```java
Iterator<String> it = stringCollection.iterator();
while (it.hasNext()) {
    String s = it.next();
    System.out.println(s);
}
```

The for-each loop works with any class that:
1. Implements `Iterable<T>` interface
2. Is an array

This demonstrates how the Iterator pattern is deeply integrated into Java's syntax as a first-class language feature.

### Q14: How would you implement a custom iterator that skips every other element?
**A:** 
```java
public class SkippingIterator<T> implements Iterator<T> {
    private final Iterator<T> delegate;
    
    public SkippingIterator(Iterator<T> delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        T result = delegate.next();
        // Skip the next element if it exists
        if (delegate.hasNext()) {
            delegate.next();
        }
        return result;
    }
}
```

### Q15: What are the challenges of implementing custom iterators for recursive data structures like trees?
**A:** Key challenges include:

1. **Managing traversal state**: The iterator needs to keep track of its position in the hierarchy.
2. **Depth vs. breadth traversal**: Deciding between depth-first or breadth-first traversal strategies.
3. **Memory management**: For large trees, storing traversal state can consume significant memory.
4. **Handling modifications**: Detecting structural modifications during iteration.

Solutions typically involve:
- Using an explicit stack or queue to track traversal state
- Implementing the Composite pattern alongside Iterator
- Using lazy evaluation techniques for large structures
- Creating specialized iterators for different traversal strategies (pre-order, in-order, post-order, level-order)

### Q16: How can you combine multiple iterators into a single iterator?
**A:** Use a composite pattern to combine multiple iterators:

```java
public class ChainedIterator<T> implements Iterator<T> {
    private final List<Iterator<T>> iterators;
    private int currentIndex = 0;
    
    public ChainedIterator(List<Iterator<T>> iterators) {
        this.iterators = new ArrayList<>(iterators);
    }
    
    @Override
    public boolean hasNext() {
        while (currentIndex < iterators.size()) {
            if (iterators.get(currentIndex).hasNext()) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterators.get(currentIndex).next();
    }
}
```

### Q17: How does the Iterator pattern help with dependency inversion?
**A:** The Iterator pattern supports dependency inversion by:

1. Providing an abstraction (the Iterator interface) that decouples clients from the specific collection implementation
2. Allowing client code to work with any collection that provides an Iterator
3. Enabling swapping of collection implementations without changing client code
4. Hiding internal data structure details from client code

For example, client code can process elements using a common interface:

```java
void processItems(Iterator<Item> items) {
    while (items.hasNext()) {
        process(items.next());
    }
}
```

This function works with any collection (ArrayList, LinkedList, HashSet) without knowing its implementation details, achieving dependency inversion.

### Q18: How would you implement an iterator that supports "look ahead" functionality?
**A:** 
```java
public class LookAheadIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private T nextItem;
    private boolean hasNext;
    
    public LookAheadIterator(Iterator<T> source) {
        this.source = source;
        advance();
    }
    
    private void advance() {
        hasNext = source.hasNext();
        if (hasNext) {
            nextItem = source.next();
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T result = nextItem;
        advance();
        return result;
    }
    
    // Look ahead without consuming
    public T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return nextItem;
    }
}
```

### Q19: How does Java's Stream API compare to traditional Iterator usage?
**A:**

**Iterator Approach:**
- More imperative style
- Mutable state management
- External iteration (controlled by client)
- Direct control over the iteration process
- Low-level and verbose
- Single-pass traversal

**Stream API Approach:**
- More declarative style
- Immutable operations
- Internal iteration (controlled by the stream)
- Pipeline of operations
- Higher-level abstractions
- Lazy evaluation
- Parallel processing support

**Example comparison:**

```java
// Iterator approach
List<String> filtered = new ArrayList<>();
Iterator<String> iterator = strings.iterator();
while (iterator.hasNext()) {
    String s = iterator.next();
    if (s.length() > 3) {
        filtered.add(s.toUpperCase());
    }
}

// Stream approach
List<String> filtered = strings.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

The Stream API generally leads to more concise and readable code while offering better optimization potential, but Iterators provide more fine-grained control when needed.

### Q20: How would you handle exceptions during iteration?
**A:** There are several approaches to handle exceptions during iteration:

1. **Filtering out problematic elements:**
```java
public class ExceptionSafeIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private final Consumer<Exception> exceptionHandler;
    private T nextElement;
    private boolean hasNext;
    
    public ExceptionSafeIterator(Iterator<T> source, Consumer<Exception> exceptionHandler) {
        this.source = source;
        this.exceptionHandler = exceptionHandler;
        findNext();
    }
    
    private void findNext() {
        hasNext = false;
        while (source.hasNext() && !hasNext) {
            try {
                nextElement = source.next();
                hasNext = true;
            } catch (Exception e) {
                exceptionHandler.accept(e);
            }
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        T result = nextElement;
        findNext();
        return result;
    }
}
```

2. **Using Optional to represent potentially absent values:**
```java
public class OptionalResultIterator<T> implements Iterator<Optional<T>> {
    private final Iterator<T> source;
    
    public OptionalResultIterator(Iterator<T> source) {
        this.source = source;
    }
    
    @Override
    public boolean hasNext() {
        return source.hasNext();
    }
    
    @Override
    public Optional<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        try {
            return Optional.ofNullable(source.next());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
```

3. **Wrapping in a checked exception transformer:**
```java
public class CheckedExceptionHandlingIterator<T, E extends Exception> implements Iterator<T> {
    private final Iterator<T> delegate;
    private final Function<Exception, E> exceptionTransformer;
    
    public CheckedExceptionHandlingIterator(Iterator<T> delegate, Function<Exception, E> exceptionTransformer) {
        this.delegate = delegate;
        this.exceptionTransformer = exceptionTransformer;
    }
    
    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }
    
    @Override
    public T next() {
        try {
            return delegate.next();
        } catch (RuntimeException e) {
            throw exceptionTransformer.apply(e);
        }
    }
}
```

---

## 📘 Summary

Iterator and Iterable interfaces form the foundation of Java's collection traversal mechanisms. They provide powerful abstractions that enable different traversal strategies, safe concurrent access, and consistent API across diverse data structures. Understanding the nuances of iterator implementations and their design patterns can significantly improve code quality, maintainability, and performance in Java applications.

From basic iteration to advanced patterns like lazy loading, filtering, and parallel processing, the Iterator pattern continues to be a fundamental building block in modern Java programming.

> 🔗 Next: [Collections vs Collection](./08_Collections_vs_Collection.md)