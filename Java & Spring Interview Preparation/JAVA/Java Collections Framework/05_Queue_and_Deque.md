# 📘 Java Queue and Deque Interface Guide

## 📌 Introduction
Java provides the `Queue` and `Deque` interfaces to support FIFO and double-ended operations, respectively. Both extend the `Collection` interface and are widely used in task scheduling, caching, and data structure implementations.

---

## 🔄 Queue Interface

A `Queue<E>` typically orders elements in a **First-In-First-Out (FIFO)** manner.

### Common Implementations
| Class | Description |
|-------|-------------|
| `LinkedList` | Implements both `Queue` and `Deque` interfaces |
| `PriorityQueue` | Orders elements according to their natural ordering or a Comparator |
| `ArrayDeque` | Resizable-array implementation of `Deque` |

### Core Methods
```java
queue.add(element);      // Throws exception if capacity full
queue.offer(element);    // Returns false if capacity full
queue.remove();          // Removes head, throws exception if empty
queue.poll();            // Removes head, returns null if empty
queue.element();         // Retrieves head, throws exception if empty
queue.peek();            // Retrieves head, returns null if empty
```

### Example:
```java
Queue<String> q = new LinkedList<>();
q.offer("A");
q.offer("B");
System.out.println(q.poll());  // Output: A
```

---

## 🔁 Deque Interface

A `Deque<E>` (double-ended queue) allows insertion and removal from both ends.

### Use Cases
- Implementing stacks and queues
- Browser history
- Sliding window algorithms

### Core Methods
```java
deque.addFirst(element);
deque.addLast(element);
deque.removeFirst();
deque.removeLast();
deque.getFirst();
deque.getLast();
```

### Example:
```java
Deque<String> dq = new ArrayDeque<>();
dq.addFirst("X");
dq.addLast("Y");
System.out.println(dq.removeLast());  // Output: Y
```

### Advanced Deque Operations
```java
// Using Deque as a Stack
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);           // Adds to front (same as addFirst)
stack.push(2);
System.out.println(stack.pop());  // Output: 2 (same as removeFirst)

// Using Iterator with Deque
Deque<String> deque = new ArrayDeque<>();
deque.addAll(Arrays.asList("A", "B", "C"));
Iterator<String> descendingItr = deque.descendingIterator();
while (descendingItr.hasNext()) {
    System.out.print(descendingItr.next() + " ");  // Output: C B A
}
```

---

## 🔐 Thread-Safe Alternatives

| Interface | Thread-Safe Class | Description |
|-----------|-------------------|-------------|
| Queue | `ConcurrentLinkedQueue` | Non-blocking implementation |
| Queue | `LinkedBlockingQueue` | Optionally bounded blocking implementation |
| Queue | `ArrayBlockingQueue` | Bounded blocking implementation |
| Queue | `PriorityBlockingQueue` | Blocking priority queue |
| Queue | `DelayQueue` | Blocking queue of delayed elements |
| Queue | `SynchronousQueue` | Each insert operation must wait for a remove |
| Deque | `LinkedBlockingDeque` | Optionally bounded blocking deque |
| Deque | `ConcurrentLinkedDeque` | Non-blocking deque implementation |

### Blocking Queue Methods
```java
BlockingQueue<Task> tasks = new LinkedBlockingQueue<>(100);
// Blocking methods
tasks.put(task);          // Blocks if queue full
Task task = tasks.take(); // Blocks if queue empty

// Timed methods
boolean success = tasks.offer(task, 1, TimeUnit.SECONDS);  // Wait up to 1 second
Task task = tasks.poll(1, TimeUnit.SECONDS);               // Wait up to 1 second
```

---

## 🚀 Performance Considerations

| Implementation | Insert | Remove | Peek | Memory Usage |
|----------------|--------|--------|------|-------------|
| LinkedList     | O(1)   | O(1)   | O(1) | High (pointers) |
| ArrayDeque     | O(1)*  | O(1)*  | O(1) | Medium |
| PriorityQueue  | O(log n) | O(log n) | O(1) | Medium |

\* Amortized time complexity; can be O(n) when resizing is required

### Memory Optimization
```java
// Pre-sizing for known capacity requirements
Queue<Integer> queue = new ArrayDeque<>(1000);
// Trimming excess capacity
((ArrayDeque<Integer>)queue).trimToSize();
```

---

## ❗ Pitfalls and Best Practices
- Use `offer` and `poll` instead of `add` and `remove` to avoid exceptions on failure
- `PriorityQueue` does not guarantee FIFO for equal elements
- Avoid using `null` in `Queue`/`Deque` implementations (especially `ConcurrentLinkedQueue`)
- Be careful with unbounded queues - they can lead to `OutOfMemoryError`
- `ArrayDeque` is generally preferable over `Stack` and `LinkedList` for stack/queue operations
- For producer-consumer patterns, consider using `BlockingQueue` implementations

### Common Anti-patterns
```java
// Anti-pattern: Using LinkedList for large queues with many iterations
Queue<Integer> badChoice = new LinkedList<>();  // High memory overhead

// Better choice for most queue operations
Queue<Integer> betterChoice = new ArrayDeque<>();
```

---

## 🧠 Interview Questions

### Q1. How does `Queue` differ from `Stack`?
**A:** Queue uses FIFO (First-In-First-Out) where elements are removed in the same order they were added, like a line of people. Stack uses LIFO (Last-In-First-Out) where the most recently added element is the first to be removed, like a stack of plates. In Java, `Deque` interface provides both behaviors, while the legacy `Stack` class is considered obsolete.

### Q2. When would you use a `Deque` over a `Queue`?
**A:** When you need to add/remove elements from both ends. Common use cases include:
- Sliding window algorithms where you need efficient addition/removal at both ends
- Implementing both stack and queue behaviors in one data structure
- Palindrome checking where you compare elements from both ends
- Work stealing algorithms where threads can take work from either end
- Browser history navigation (back/forward functionality)

### Q3. What's the difference between `offer()` and `add()`?
**A:** Both add elements to a queue, but they handle capacity restrictions differently:
- `add()` throws an `IllegalStateException` if the queue is capacity-restricted and full
- `offer()` returns `false` if the element can't be added (e.g., queue is full)
- When working with bounded queues, `offer()` is generally preferred to avoid exceptions

### Q4. How is `PriorityQueue` internally implemented?
**A:** `PriorityQueue` uses a min-heap data structure implemented as an array. Elements are ordered either by their natural ordering or by a provided `Comparator`. Key points:
- The heap property ensures that the smallest element is always at the root
- Insertion (`offer()`) and removal (`poll()`) operations are O(log n)
- The internal array is resized dynamically as needed
- Unlike other queues, iteration order is not predictable
- Elements with equal priority are not guaranteed to be processed in FIFO order

### Q5. How does `ArrayDeque` differ from `LinkedList`?
**A:** Key differences include:
- `ArrayDeque` is faster for most operations as it has better locality of reference and less overhead
- `ArrayDeque` doesn't allow `null` elements while `LinkedList` does
- `LinkedList` implements `List` interface, allowing index-based access
- `ArrayDeque` uses a resizable array, while `LinkedList` uses doubly-linked nodes
- `LinkedList` has O(1) insertion/removal in the middle when you have a reference to the node
- `ArrayDeque` has lower memory overhead per element

### Q6. What are blocking queues and when would you use them?
**A:** Blocking queues are thread-safe queues that block or time out when attempting to add to a full queue or remove from an empty queue. They're used in producer-consumer scenarios where:
- Producers need to wait when the queue is full
- Consumers need to wait when the queue is empty
- You need thread coordination without explicit locking
- You're implementing a thread pool or task scheduling system
- You want to control the rate of message processing

### Q7. How would you implement a custom priority queue for a specific use case?
**A:** For a custom priority queue:
1. Define a custom `Comparator` that implements your priority logic
2. Pass this comparator to the `PriorityQueue` constructor
```java
PriorityQueue<Task> taskQueue = new PriorityQueue<>(
    (t1, t2) -> {
        // First compare by priority level (higher priority first)
        int result = t2.getPriority() - t1.getPriority();
        if (result == 0) {
            // If same priority, compare by creation time (older first)
            return t1.getCreationTime().compareTo(t2.getCreationTime());
        }
        return result;
    }
);
```

### Q8. What is the difference between `ConcurrentLinkedQueue` and `LinkedBlockingQueue`?
**A:** Both are thread-safe queue implementations, but:
- `ConcurrentLinkedQueue` is non-blocking (never waits)
- `LinkedBlockingQueue` provides blocking operations (`put`/`take`)
- `ConcurrentLinkedQueue` has lower overhead when contention is low
- `LinkedBlockingQueue` can be bounded, `ConcurrentLinkedQueue` cannot
- `LinkedBlockingQueue` has additional methods like `drainTo()` for batch operations

### Q9. How would you implement a rate limiter using queues?
**A:** You can implement a token bucket rate limiter using a `DelayQueue`:
```java
class RateLimiter {
    private final BlockingQueue<Token> tokens;
    private final int maxTokens;
    private final long refillInterval;
    
    static class Token implements Delayed {
        private final long availableTime;
        
        public Token(long delayMillis) {
            this.availableTime = System.currentTimeMillis() + delayMillis;
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(availableTime - System.currentTimeMillis(), 
                                TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(Delayed other) {
            return Long.compare(getDelay(TimeUnit.MILLISECONDS), 
                               other.getDelay(TimeUnit.MILLISECONDS));
        }
    }
    
    // Implementation details omitted for brevity
}
```

### Q10. How can you use `Deque` for solving the sliding window maximum problem?
**A:** You can use a `Deque` to maintain indices of useful elements:
```java
public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums.length == 0) return new int[0];
    int n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();
    
    for (int i = 0; i < n; i++) {
        // Remove indices outside current window
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }
        
        // Remove smaller elements as they're not useful
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }
        
        // Add current index
        deque.offerLast(i);
        
        // Add to result if window has reached size k
        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()];
        }
    }
    
    return result;
}
```

---

## ✅ Summary
- `Queue` supports FIFO operations while `Deque` supports both FIFO and LIFO
- `ArrayDeque` is generally the best choice for non-concurrent queues and stacks
- `PriorityQueue` is useful when elements need to be processed based on priority
- Use thread-safe variants (`BlockingQueue`, `ConcurrentLinkedQueue`) for concurrent access
- Understand the difference between blocking and non-blocking queue operations
- Choose the right queue implementation based on your specific requirements:
  - Bounded vs Unbounded
  - Priority-based vs FIFO
  - Blocking vs Non-blocking
  - Memory efficiency vs Access time

> 🔗 Next: [Map Interface](./06_Map_Interface.md)
