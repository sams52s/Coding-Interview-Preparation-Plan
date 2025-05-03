# 13. Concurrent Collections in Java

## 🚀 Overview
Concurrent collections are specialized data structures from the `java.util.concurrent` package designed to be used in multithreaded environments. They offer high performance and thread safety without needing explicit synchronization, avoiding problems like race conditions and `ConcurrentModificationException`.

---

## 🔒 Why Concurrent Collections?

- **Thread-safe**: Can be safely accessed and modified by multiple threads.
- **High-performance**: Use fine-grained locking or lock-free algorithms to reduce contention.
- **Avoids Fail-Fast**: Unlike standard collections, concurrent collections do not throw `ConcurrentModificationException`.
- **Scalability**: Ideal for modern multi-core systems.

---

## 🧰 Core Concurrent Collections

### 1. `ConcurrentHashMap<K,V>`
- Segmented internal structure for high concurrency.
- Allows concurrent read and write.
- Thread-safe and better alternative to `Hashtable`.

```java
Map<String, Integer> map = new ConcurrentHashMap<>();
map.put("A", 1);
map.put("B", 2);
System.out.println(map.get("A"));
```

### 2. `CopyOnWriteArrayList<E>`
- Thread-safe variant of `ArrayList`.
- Good for lists with more reads than writes.
- Writes create a fresh copy of the underlying array.

```java
List<String> list = new CopyOnWriteArrayList<>();
list.add("apple");
list.add("banana");
for (String item : list) {
    System.out.println(item);
}
```

### 3. `CopyOnWriteArraySet<E>`
- Thread-safe variant of `Set` backed by a `CopyOnWriteArrayList`.
- Maintains insertion order and prevents duplicates.

### 4. `ConcurrentLinkedQueue<E>`
- An unbounded, thread-safe, non-blocking FIFO queue.
- Suitable for high-performance producer-consumer scenarios.

### 5. `ConcurrentLinkedDeque<E>`
- Double-ended version of `ConcurrentLinkedQueue`.

### 6. `LinkedBlockingQueue<E>`
- Blocking queue backed by linked nodes.
- Can be bounded or unbounded.
- Used in thread pools.

### 7. `ArrayBlockingQueue<E>`
- Bounded blocking queue backed by an array.
- Maintains insertion order.

### 8. `PriorityBlockingQueue<E>`
- Unbounded blocking queue with elements ordered by priority.

### 9. `DelayQueue<E extends Delayed>`
- Elements become available for access only after a delay has expired.
- Useful for task scheduling.

### 10. `SynchronousQueue<E>`
- No internal capacity.
- Each insert operation must wait for a corresponding remove operation.

---

## 🔄 Advanced Concurrent Collection Features

### 1. Atomic Operations in ConcurrentHashMap (Java 8+)
```java
// Compute if absent (atomic operation)
map.computeIfAbsent("key", k -> expensiveComputation(k));

// Update atomically
map.compute("key", (k, v) -> v == null ? 1 : v + 1);

// Merge values
map.merge("key", 1, Integer::sum);
```

### 2. Aggregate Operations (Parallel Processing)
```java
// Parallel forEach
concurrentMap.forEach(1000, (key, value) -> process(key, value));

// Search operations
String result = concurrentMap.search(10000, (k, v) -> v > 100 ? k : null);

// Reduction operations
Integer sum = concurrentMap.reduce(10, (k, v) -> v, Integer::sum);
```

### 3. ThreadLocal for Thread Confinement
```java
ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal.withInitial(() -> 
    new SimpleDateFormat("yyyy-MM-dd"));

String format(Date date) {
    return dateFormatter.get().format(date);
}
```

### 4. StampedLock for Optimistic Reading
```java
class OptimisticReadingMap<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final StampedLock lock = new StampedLock();
    
    public V get(K key) {
        long stamp = lock.tryOptimisticRead();
        V value = map.get(key);
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                value = map.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return value;
    }
    
    public void put(K key, V value) {
        long stamp = lock.writeLock();
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
```

### 5. CompletableFuture with Concurrent Collections
```java
public List<Product> fetchProductsAsync(List<String> ids) {
    List<CompletableFuture<Product>> futures = ids.stream()
        .map(id -> CompletableFuture.supplyAsync(() -> fetchProduct(id)))
        .collect(Collectors.toList());
        
    ConcurrentMap<String, Product> resultMap = new ConcurrentHashMap<>();
    
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
        .thenAccept(v -> futures.forEach(f -> {
            Product p = f.join();
            resultMap.put(p.getId(), p);
        }));
        
    return new ArrayList<>(resultMap.values());
}
```

### 6. Fork-Join Framework Integration
```java
class SumTask extends RecursiveTask<Long> {
    private final ConcurrentHashMap<String, Integer> map;
    private final List<String> keys;
    private static final int THRESHOLD = 100;
    
    // Constructor and implementation...
    
    @Override
    protected Long compute() {
        if (keys.size() <= THRESHOLD) {
            return keys.stream()
                .mapToLong(key -> map.getOrDefault(key, 0))
                .sum();
        }
        
        int mid = keys.size() / 2;
        SumTask left = new SumTask(map, keys.subList(0, mid));
        SumTask right = new SumTask(map, keys.subList(mid, keys.size()));
        right.fork();
        long leftResult = left.compute();
        long rightResult = right.join();
        return leftResult + rightResult;
    }
}
```

### 7. Non-Blocking Algorithms
- Lock-free data structures use atomic operations (CAS)
- Higher throughput under high contention
- Example: `AtomicReference`, `LongAdder`, `ConcurrentSkipListMap`

```java
public class LockFreeStack<E> {
    private final AtomicReference<Node<E>> head = new AtomicReference<>();
    
    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while (!head.compareAndSet(oldHead, newHead));
    }
    
    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) return null;
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }
    
    private static class Node<E> {
        final E item;
        Node<E> next;
        
        Node(E item) { this.item = item; }
    }
}
```

---

## 🔍 Memory Consistency and the Java Memory Model

- **Happens-before relationship**: Ensures visibility of memory operations across threads
- **Volatile fields**: Provide visibility guarantees without synchronization
- **Atomic operations**: Ensure thread-safety for single variables

```java
// Using atomic variables for counters
AtomicLong counter = new AtomicLong();
counter.incrementAndGet(); // Thread-safe increment

// Using LongAdder for high-concurrency counters
LongAdder adder = new LongAdder();
adder.increment();
long sum = adder.sum();
```

---

## ⚖️ Comparison Table

| Collection                  | Ordering      | Blocking | Bounded | Use Case                     | Performance Characteristics |
|----------------------------|---------------|----------|---------|------------------------------|----------------------------|
| `ConcurrentHashMap`        | Key-based     | ❌        | ❌       | Fast concurrent map          | Read-optimized, lock striping |
| `CopyOnWriteArrayList`     | Insertion     | ❌        | ❌       | Frequent reads               | Read-optimized, write-expensive |
| `ConcurrentLinkedQueue`    | FIFO          | ❌        | ❌       | Producer-consumer pattern    | Non-blocking algorithm |
| `LinkedBlockingQueue`      | FIFO          | ✅        | ✅/❌     | Thread pool queues           | Separate locks for head/tail |
| `ArrayBlockingQueue`       | FIFO          | ✅        | ✅       | Bounded task queues          | Single lock, fair option |
| `PriorityBlockingQueue`    | Priority      | ✅        | ❌       | Scheduler queues             | Heap-based priority |
| `SynchronousQueue`         | N/A           | ✅        | ✅       | Thread handoff               | Direct transfer, no storage |
| `ConcurrentSkipListMap`    | Sorted        | ❌        | ❌       | Sorted concurrent map        | O(log n) operations |
| `LinkedTransferQueue`      | FIFO          | ✅        | ❌       | Producer-consumer            | Direct transfer optimization |

---

## 📊 Performance Considerations

1. **Memory Overhead**
   - `ConcurrentHashMap`: Lower than `Hashtable` due to finer-grained locking
   - `CopyOnWriteArrayList`: High for write-heavy scenarios (creates new array copies)

2. **Scalability**
   - Modern concurrent collections scale with number of CPUs/cores
   - `ConcurrentHashMap` partitions reduce lock contention

3. **Throughput vs. Latency**
   - Non-blocking collections (ConcurrentLinkedQueue) offer better throughput
   - Blocking collections may have better predictability

4. **When to Choose What**
   ```
   High read, low write → CopyOnWriteArrayList/Set
   High throughput map → ConcurrentHashMap
   Need ordering guarantee → ConcurrentSkipListMap
   Thread coordination → Blocking queues
   ```

---

## 📌 Best Practices

- Use `CopyOnWriteArrayList` for frequent reads and rare writes.
- Prefer `ConcurrentHashMap` over `Hashtable` or synchronized `HashMap`.
- Use blocking queues (`LinkedBlockingQueue`, `ArrayBlockingQueue`) for inter-thread communication.
- Always define capacity when using bounded queues to avoid memory overflow.
- Prefer non-blocking algorithms for high contention scenarios.
- Use specialized atomic classes (`LongAdder` instead of `AtomicLong`) for high-contention counters.
- Consider using `StampedLock` for read-heavy workloads with occasional writes.
- Avoid premature optimization - measure performance before choosing specialized collections.

---

## 📋 Real-world Producer-Consumer Implementation

```java
class ProductionLine {
    private final BlockingQueue<Task> taskQueue;
    private final int numWorkers;
    private final ExecutorService workerPool;
    private final AtomicBoolean running = new AtomicBoolean(true);
    
    public ProductionLine(int capacity, int workers) {
        this.taskQueue = new ArrayBlockingQueue<>(capacity);
        this.numWorkers = workers;
        this.workerPool = Executors.newFixedThreadPool(workers);
    }
    
    public void start() {
        for (int i = 0; i < numWorkers; i++) {
            workerPool.submit(() -> {
                while (running.get() || !taskQueue.isEmpty()) {
                    try {
                        Task task = taskQueue.poll(100, TimeUnit.MILLISECONDS);
                        if (task != null) {
                            task.process();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }
    
    public boolean addTask(Task task) {
        try {
            return taskQueue.offer(task, 500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    public void shutdown() {
        running.set(false);
        workerPool.shutdown();
        try {
            if (!workerPool.awaitTermination(10, TimeUnit.SECONDS)) {
                workerPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            workerPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
```

---

## ❓ Interview Questions and Answers

### Q1: How is `ConcurrentHashMap` different from `Hashtable`?
**A:** `ConcurrentHashMap` uses segmented locking (or striped locks in Java 8+) for better performance. `Hashtable` uses a single lock for the entire map. Additionally, ConcurrentHashMap allows concurrent reads and lock-free readers, while Hashtable locks the entire table for both reads and writes. ConcurrentHashMap does not allow null keys or values, and it offers specialized methods like compute(), merge(), and putIfAbsent() for atomic updates.

### Q2: When would you use `CopyOnWriteArrayList`?
**A:** When you have many threads reading from a list and very few threads writing to it. Writing creates a new copy, which is expensive for frequent writes. It's ideal for collections that are primarily used for iteration (like event listener lists), where reads vastly outnumber modifications.

### Q3: What happens if you modify a `CopyOnWriteArrayList` during iteration?
**A:** The modification will not be visible in the ongoing iteration, which iterates over the previous snapshot. This is called "snapshot semantics" - the iterator has a consistent view of the list at the point the iterator was created, regardless of subsequent modifications.

### Q4: Which queue is suitable for implementing a real-time scheduler?
**A:** `PriorityBlockingQueue` because it dequeues based on element priority, ensuring higher priority tasks are processed first. For time-based scheduling, `DelayQueue` is often used as it releases elements only when their delay has expired.

### Q5: What is a `SynchronousQueue` and how is it used?
**A:** A queue with no capacity where each `put()` must wait for a `take()`. Often used for thread handoff in producer-consumer scenarios where direct transfer is desired. The Executors.newCachedThreadPool() uses SynchronousQueue for task handoff to threads.

### Q6: Is `ConcurrentHashMap` fail-fast?
**A:** No. Iterators are weakly consistent and do not throw `ConcurrentModificationException`. They may reflect some but not all modifications made after the iterator was created. This design allows for higher concurrency at the cost of strict consistency during iteration.

### Q7: Can `ConcurrentHashMap` have null keys or values?
**A:** No. Unlike `HashMap`, it does not allow null keys or null values. This restriction exists because in a concurrent context, a null return value needs to unambiguously mean "no mapping exists" rather than "the mapping exists with a null value."

### Q8: Why is `CopyOnWriteArrayList` not suitable for frequent write operations?
**A:** Because every mutation (add/remove) creates a new copy of the entire array, which is inefficient for frequent updates due to memory allocation and copying overhead. This can lead to significant performance degradation and garbage collection pressure.

### Q9: What is lock striping in `ConcurrentHashMap`?
**A:** Lock striping is a technique where multiple locks control different segments of a data structure. In ConcurrentHashMap (pre-Java 8), the map is divided into segments, each with its own lock. This allows multiple threads to operate on different segments simultaneously, increasing throughput. Java 8+ uses a more granular approach with per-node locking.

### Q10: What is the difference between `LinkedBlockingQueue` and `ArrayBlockingQueue`?
**A:** `LinkedBlockingQueue` is backed by linked nodes and can be optionally bounded, while `ArrayBlockingQueue` is always bounded and backed by an array. LinkedBlockingQueue uses separate locks for head and tail, potentially offering higher throughput under contention. ArrayBlockingQueue uses a single lock, which is simpler but may have more contention.

### Q11: How does the `forEach` method in `ConcurrentHashMap` differ from regular map iterations?
**A:** `ConcurrentHashMap.forEach()` provides snapshot consistency during iteration and supports parallelism through its overloaded forms. It won't throw ConcurrentModificationException and can process entries with a given parallelism threshold in parallel, leveraging the fork-join framework.

### Q12: What is the purpose of `LongAdder` versus `AtomicLong`?
**A:** `LongAdder` is designed for high-contention scenarios where many threads update a common counter. It reduces contention by maintaining multiple variables that can be updated independently and summed on demand. This offers significantly better performance than `AtomicLong` under high contention, at the cost of slightly more memory usage.

### Q13: Why might you use a `ConcurrentSkipListMap` instead of a `TreeMap` with synchronization?
**A:** `ConcurrentSkipListMap` provides thread safety with better concurrency than a synchronized `TreeMap`. It maintains elements in sorted order while allowing concurrent access without global locking. Skip lists provide O(log n) expected time for containment, insertion, and removal operations while offering superior scalability compared to a synchronized TreeMap.

### Q14: How does a `BlockingQueue` help in implementing the producer-consumer pattern?
**A:** `BlockingQueue` provides built-in thread synchronization through methods like `put()` (blocks if the queue is full) and `take()` (blocks if the queue is empty). This eliminates the need for explicit locking, wait/notify mechanisms, and condition variables, making producer-consumer implementations simpler and less error-prone.

### Q15: What are the memory consistency effects of concurrent collections?
**A:** Concurrent collections ensure that actions in a thread prior to placing an object into a collection happen-before actions subsequent to accessing that element from the collection in another thread. This guarantees visibility of changes across threads without explicit synchronization, following the Java Memory Model's happens-before relationship.

### Q16: When would you choose `ConcurrentLinkedQueue` over `LinkedBlockingQueue`?
**A:** Choose `ConcurrentLinkedQueue` when you need a high-throughput, non-blocking data structure and can handle scenarios where operations might fail rather than block (e.g., queue is empty/full). Use `LinkedBlockingQueue` when thread coordination is required and you need operations to wait until they can succeed (e.g., in classic producer-consumer scenarios).

---

## 🔗 Next: [Synchronization and Fail-Fast Behavior](./14_Synchronization_and_FailFast.md)
