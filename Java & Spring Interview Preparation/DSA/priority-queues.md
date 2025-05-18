# 🧮 Priority Queues in Data Structures

A **Priority Queue** is an abstract data structure where each element has a priority. Elements are served based on priority, not just insertion order. Internally, it's often implemented using a **binary heap**.

---

## 🔰 What is a Priority Queue?

- A type of queue where the **highest (or lowest) priority** element is dequeued first.
- Not necessarily FIFO.
- Supports efficient access to the **min** or **max** element.

---

## 🧱 Java Implementation

### ✅ Min-Heap (Default)
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.add(4);
pq.add(1);
pq.add(7);
System.out.println(pq.poll()); // 1 (smallest element)
```

### ✅ Max-Heap (Custom Comparator)
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

### ✅ PriorityQueue with Custom Objects
```java
class Task {
    int priority;
    String name;

    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }
}

PriorityQueue<Task> taskQueue = new PriorityQueue<>(Comparator.comparingInt(t -> t.priority));
```

---

## 🔍 Heap Structure

- Java's `PriorityQueue` is backed by a **binary min-heap**.
- Insertions take O(log n)
- Access/removal of min/max is O(log n)
- No random access like in array or list

---

## 🧠 Real-World Use Cases

- CPU/OS task scheduling (priority jobs)
- Dijkstra's shortest path algorithm
- Huffman encoding tree construction
- K largest/smallest elements problems
- A* search algorithm
- Stream data analytics (top N elements)

---

## 📈 Time & Space Complexity

| Operation     | Time Complexity |
|----------------|------------------|
| Insertion      | O(log n)         |
| Deletion (min/max) | O(log n)     |
| Peek (min/max) | O(1)             |
| Search/Update  | O(n)             |

---

## ❗ Pitfalls & Limitations

- No built-in support for `decrease-key` like in C++ STL.
- Not thread-safe (`use PriorityBlockingQueue` if needed).
- Poor performance if frequent mid-queue updates are needed.

---

## 💼 Interview Questions & Answers

### ❓ How is Java's PriorityQueue implemented internally?
**✅** Using a binary heap in an array. Maintains the heap invariant after insertion/removal.

### ❓ Can a PriorityQueue contain duplicates?
**✅** Yes. Duplicate elements are allowed.

### ❓ How to implement a max-heap in Java?
**✅** Use a reverse comparator:
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

### ❓ Can we access arbitrary elements in a priority queue?
**❌** No. You only access the head (min/max). Random access is O(n).

### ❓ What is a stable priority queue?
**✅** Maintains original insertion order for equal-priority elements (Java’s PQ is not stable).

### ❓ How do you implement a bounded priority queue?
**✅ A:**
```java
class BoundedPriorityQueue<E> {
    private final PriorityQueue<E> queue;
    private final int maxSize;
    
    public BoundedPriorityQueue(int maxSize, Comparator<E> comparator) {
        this.maxSize = maxSize;
        this.queue = new PriorityQueue<>(comparator);
    }
    
    public void offer(E element) {
        if (queue.size() < maxSize) {
            queue.offer(element);
        } else if (queue.comparator().compare(element, queue.peek()) > 0) {
            queue.poll();
            queue.offer(element);
        }
    }
}
```

### ❓ How would you implement an efficient median finder?
**✅ A:**
```java
class MedianFinder {
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // max heap
    private PriorityQueue<Integer> large = new PriorityQueue<>(); // min heap
    
    public void addNum(int num) {
        small.offer(num);
        large.offer(small.poll());
        if (small.size() < large.size()) {
            small.offer(large.poll());
        }
    }
    
    public double findMedian() {
        return small.size() > large.size() ? 
               small.peek() : 
               (small.peek() + large.peek()) / 2.0;
    }
}
```

### ❓ How do you handle priority queue with dynamic priorities?
**✅ A:**
```java
class DynamicPriorityQueue<E> {
    private class Entry {
        E element;
        int priority;
        long timestamp;
        
        Entry(E element, int priority, long timestamp) {
            this.element = element;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }
    
    private PriorityQueue<Entry> queue = new PriorityQueue<>((a, b) -> {
        if (a.priority != b.priority) return b.priority - a.priority;
        return Long.compare(a.timestamp, b.timestamp); // maintain FIFO for equal priorities
    });
    
    private long timestamp = 0;
    
    public void offer(E element, int priority) {
        queue.offer(new Entry(element, priority, timestamp++));
    }
    
    public void updatePriority(E element, int newPriority) {
        // Note: This is O(n), not efficient for large queues
        queue.removeIf(entry -> entry.element.equals(element));
        offer(element, newPriority);
    }
}
```

### ❓ How do you implement a priority queue that supports decrease-key operation?
**✅ A:**
```java
class IndexedPriorityQueue<T extends Comparable<T>> {
    private final List<T> elements;
    private final Map<T, Integer> indices;
    
    public IndexedPriorityQueue() {
        elements = new ArrayList<>();
        indices = new HashMap<>();
    }
    
    public void decreaseKey(T element, T newValue) {
        if (indices.containsKey(element)) {
            int index = indices.get(element);
            if (newValue.compareTo(element) < 0) {
                elements.set(index, newValue);
                indices.put(newValue, index);
                indices.remove(element);
                siftUp(index);
            }
        }
    }
    
    private void siftUp(int index) {
        // Implementation similar to regular heap siftUp
    }
}
```

### ❓ How to implement a priority queue that supports batch operations efficiently?
**✅ A:**
```java
class BatchPriorityQueue<E> {
    private final PriorityQueue<E> queue;
    private final List<E> batch;
    private final int batchSize;
    
    public BatchPriorityQueue(int batchSize, Comparator<E> comparator) {
        this.queue = new PriorityQueue<>(comparator);
        this.batch = new ArrayList<>();
        this.batchSize = batchSize;
    }
    
    public void offer(E element) {
        batch.add(element);
        if (batch.size() >= batchSize) {
            flushBatch();
        }
    }
    
    private void flushBatch() {
        queue.addAll(batch);
        batch.clear();
    }
    
    public E poll() {
        if (queue.isEmpty()) {
            flushBatch();
        }
        return queue.poll();
    }
}
```

---

## 🔬 Advanced Priority Queue Implementations

### Custom Binary Heap Implementation
```java
public class BinaryHeap<T extends Comparable<T>> {
    private List<T> heap;
    private final boolean isMinHeap;
    
    public BinaryHeap(boolean isMinHeap) {
        this.heap = new ArrayList<>();
        this.isMinHeap = isMinHeap;
    }
    
    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && compare(heap.get(index), heap.get(parent)) < 0) {
            Collections.swap(heap, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }
    
    private void siftDown(int index) {
        int minIndex;
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            minIndex = index;
            
            if (left < heap.size() && compare(heap.get(left), heap.get(minIndex)) < 0)
                minIndex = left;
            if (right < heap.size() && compare(heap.get(right), heap.get(minIndex)) < 0)
                minIndex = right;
            
            if (minIndex == index) break;
            Collections.swap(heap, index, minIndex);
            index = minIndex;
        }
    }
    
    private int compare(T a, T b) {
        return isMinHeap ? a.compareTo(b) : b.compareTo(a);
    }
}
```

### Thread-Safe Priority Queue
```java
public class ConcurrentPriorityQueue<E> {
    private final PriorityQueue<E> queue;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    
    public ConcurrentPriorityQueue(Comparator<? super E> comparator) {
        queue = new PriorityQueue<>(comparator);
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
    }
    
    public void offer(E e) {
        lock.lock();
        try {
            queue.offer(e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                if (nanos <= 0) return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
}
```

## 🎯 Detailed Problem Solutions

### Merge K Sorted Lists
```java
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>(
        (a, b) -> a.val - b.val
    );
    
    // Add first node from each list
    for (ListNode list : lists) {
        if (list != null) pq.offer(list);
    }
    
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    while (!pq.isEmpty()) {
        ListNode curr = pq.poll();
        tail.next = curr;
        tail = curr;
        
        if (curr.next != null) {
            pq.offer(curr.next);
        }
    }
    
    return dummy.next;
}
```

### Task Scheduler
```java
public int leastInterval(char[] tasks, int n) {
    // Count frequency of each task
    int[] freq = new int[26];
    for (char task : tasks) {
        freq[task - 'A']++;
    }
    
    // Create max heap of frequencies
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    for (int f : freq) {
        if (f > 0) pq.offer(f);
    }
    
    int time = 0;
    Queue<int[]> cooldown = new LinkedList<>();
    
    while (!pq.isEmpty() || !cooldown.isEmpty()) {
        time++;
        
        if (!pq.isEmpty()) {
            int count = pq.poll() - 1;
            if (count > 0) {
                cooldown.offer(new int[]{count, time + n});
            }
        }
        
        if (!cooldown.isEmpty() && cooldown.peek()[1] == time) {
            pq.offer(cooldown.poll()[0]);
        }
    }
    
    return time;
}
```

### Additional Interview Problems

### ❓ How would you implement a multi-priority queue?
**✅ A:**
```java
class MultiPriorityQueue<E> {
    private final Map<Integer, PriorityQueue<E>> queues;
    private final PriorityQueue<Integer> priorities;
    
    public MultiPriorityQueue() {
        queues = new HashMap<>();
        priorities = new PriorityQueue<>();
    }
    
    public void offer(E element, int priority) {
        queues.computeIfAbsent(priority, k -> {
            priorities.offer(k);
            return new PriorityQueue<>();
        }).offer(element);
    }
    
    public E poll() {
        while (!priorities.isEmpty()) {
            int highestPriority = priorities.peek();
            PriorityQueue<E> queue = queues.get(highestPriority);
            E element = queue.poll();
            
            if (queue.isEmpty()) {
                queues.remove(highestPriority);
                priorities.poll();
            }
            
            if (element != null) return element;
        }
        return null;
    }
}
```

---

## ✅ Summary

Priority queues are critical for efficient selection of min/max values. They are ideal in greedy algorithms, stream handling, and real-time scheduling. Mastering both built-in usage and heap logic is crucial.

---

## 📝 References

- [GeeksforGeeks – Priority Queue](https://www.geeksforgeeks.org/priority-queue-set-1-introduction/)  
- [Oracle Docs – PriorityQueue](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)  
- [Visualgo – Heap Visualization](https://visualgo.net/en/heap)
