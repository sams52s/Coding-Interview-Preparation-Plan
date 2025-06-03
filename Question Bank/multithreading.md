## 🧵 Multithreading & Concurrency Interview Q&A

### 1. Thread Lifecycle

**Q:** Can you explain the lifecycle of a thread in Java?

**A:**  
The thread lifecycle in Java consists of several states:

- **New:** The thread is created but not yet started.
- **Runnable:** The thread is ready to run and waiting for CPU time.
- **Running:** The thread is executing.
- **Blocked/Waiting:** The thread is waiting for a resource or notification.
- **Timed Waiting:** The thread is waiting for a specified period.
- **Terminated:** The thread has completed execution or has been stopped.

**Real-world scenario:**  
Imagine a web server handling multiple client requests. Each request is handled by a separate thread that goes through these lifecycle stages as it processes the request.

**Code example:**

```java
public class ThreadLifecycleDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running...");
        });

        System.out.println("State after creation: " + thread.getState()); // NEW
        thread.start();
        System.out.println("State after start: " + thread.getState()); // RUNNABLE or RUNNING
    }
}
```

**Follow-up Q:** How can you control or influence the thread lifecycle?

**Follow-up A:**  
You can influence the lifecycle using methods like `start()`, `sleep()`, `wait()`, and `interrupt()`. For example, `start()` moves a thread from NEW to RUNNABLE, `sleep()` moves it to TIMED_WAITING, and `interrupt()` can wake a sleeping or waiting thread or stop its execution.

---

### 2. Runnable vs Callable

**Q:** What is the difference between `Runnable` and `Callable` in Java concurrency?

**A:**  
Both `Runnable` and `Callable` represent tasks executed by threads, but:

- `Runnable` does not return a result and cannot throw checked exceptions.
- `Callable` returns a result and can throw checked exceptions.

**Real-world scenario:**  
In a banking application, a `Runnable` might log transactions asynchronously, while a `Callable` might calculate interest and return the result.

**Code example:**

```java
// Runnable example
Runnable runnableTask = () -> System.out.println("Runnable task executed");
new Thread(runnableTask).start();

// Callable example
Callable<Integer> callableTask = () -> {
    Thread.sleep(1000);
    return 123;
};
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<Integer> future = executor.submit(callableTask);
try {
    System.out.println("Callable result: " + future.get());
} catch (Exception e) {
    e.printStackTrace();
}
executor.shutdown();
```

**Follow-up Q:** When would you prefer `Callable` over `Runnable`?

**Follow-up A:**  
Use `Callable` when you need to return a result or handle exceptions during task execution. `Runnable` is suitable for fire-and-forget tasks without a return value.

---

### 3. Synchronization

**Q:** What is synchronization in Java and why is it important?

**A:**  
Synchronization controls access to shared resources by multiple threads to prevent inconsistent data and race conditions. It ensures that only one thread can execute a synchronized block or method at a time for a given object.

**Real-world scenario:**  
In a ticket booking system, synchronization prevents overselling tickets by ensuring one booking thread updates the seat count at a time.

**Code example:**

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

**Follow-up Q:** What is the difference between synchronized methods and synchronized blocks?

**Follow-up A:**  
A synchronized method locks the entire method for a given object, while a synchronized block locks only a specified section of code, allowing finer control and potentially better performance.

---

### 4. wait(), notify(), notifyAll()

**Q:** How do `wait()`, `notify()`, and `notifyAll()` work in Java concurrency?

**A:**  
These methods are used for inter-thread communication on shared objects:

- `wait()`: Causes the current thread to release the lock and wait until notified.
- `notify()`: Wakes one waiting thread.
- `notifyAll()`: Wakes all waiting threads.

They must be called within synchronized context.

**Real-world scenario:**  
A producer-consumer problem where the consumer waits for the producer to produce data.

**Code example:**

```java
class SharedResource {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait();
        }
        data = value;
        available = true;
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        available = false;
        notify();
        return data;
    }
}
```

**Follow-up Q:** What are some common pitfalls when using these methods?

**Follow-up A:**  
Common pitfalls include calling them outside synchronized blocks, which throws `IllegalMonitorStateException`, and using `if` instead of `while` to check conditions, which can cause spurious wakeups leading to incorrect behavior.

---

### 5. sleep() vs wait()

**Q:** What is the difference between `sleep()` and `wait()` methods?

**A:**  
- `sleep()` pauses the current thread for a specified time without releasing any locks.
- `wait()` releases the lock and waits until notified or timed out.

`wait()` is used for inter-thread communication, while `sleep()` is for pausing execution.

**Real-world scenario:**  
`wait()` is used in producer-consumer scenarios, while `sleep()` might be used to simulate delay.

**Code example:**

```java
// sleep example
Thread.sleep(1000);

// wait example inside synchronized block
synchronized (obj) {
    obj.wait();
}
```

**Follow-up Q:** Can `sleep()` be interrupted? What happens?

**Follow-up A:**  
Yes, `sleep()` can be interrupted by another thread calling `interrupt()`, which throws `InterruptedException`.

---

### 6. Deadlock Prevention

**Q:** What is a deadlock and how can it be prevented?

**A:**  
A deadlock occurs when two or more threads wait indefinitely for each other to release locks. Prevention techniques include:

- Lock ordering: Acquire locks in a consistent order.
- Lock timeout: Use `tryLock()` with timeout.
- Avoid nested locks when possible.

**Real-world scenario:**  
Two threads trying to lock two resources in opposite order causing a deadlock.

**Code example:**

```java
ReentrantLock lock1 = new ReentrantLock();
ReentrantLock lock2 = new ReentrantLock();

public void safeLocking() {
    try {
        if(lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
            try {
                if(lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    // critical section
                }
            } finally {
                lock2.unlock();
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    } finally {
        lock1.unlock();
    }
}
```

**Follow-up Q:** What tools can help detect deadlocks?

**Follow-up A:**  
Java VisualVM, jstack, and thread dump analysis can help detect deadlocks by showing blocked threads and locked resources.

---

### 7. Thread Pool Usage

**Q:** What are thread pools and why are they used?

**A:**  
Thread pools manage a fixed number of threads to execute tasks, improving performance by reusing threads and controlling resource usage.

**Real-world scenario:**  
Web servers use thread pools to handle multiple client requests efficiently.

**Code example:**

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
for (int i = 0; i < 10; i++) {
    executor.submit(() -> {
        System.out.println("Task executed by " + Thread.currentThread().getName());
    });
}
executor.shutdown();
```

**Follow-up Q:** What are the differences between fixed, cached, and scheduled thread pools?

**Follow-up A:**  
- Fixed thread pool: Fixed number of threads.
- Cached thread pool: Creates new threads as needed, reuses idle threads.
- Scheduled thread pool: Executes tasks after a delay or periodically.

---

### 8. Context Switching

**Q:** What is context switching in multithreading?

**A:**  
Context switching is the process where the CPU switches from executing one thread to another, saving and loading thread states, which incurs overhead.

**Real-world scenario:**  
In multitasking OS, frequent context switches between threads reduce CPU efficiency.

**Follow-up Q:** How can context switching overhead be minimized?

**Follow-up A:**  
By reducing the number of active threads, using thread pools, and designing tasks to minimize blocking.

---

### 9. User vs Daemon Threads

**Q:** What is the difference between user threads and daemon threads?

**A:**  
User threads prevent the JVM from exiting until they finish. Daemon threads run in the background and do not prevent JVM termination.

**Real-world scenario:**  
Garbage collection runs in daemon threads.

**Code example:**

```java
Thread daemonThread = new Thread(() -> {
    while (true) {
        System.out.println("Daemon thread running...");
    }
});
daemonThread.setDaemon(true);
daemonThread.start();
```

**Follow-up Q:** What happens if only daemon threads remain?

**Follow-up A:**  
The JVM will exit once all user threads finish, even if daemon threads are still running.

---

### 10. ExecutorService, Future, CountDownLatch, Semaphore

**Q:** Can you explain the usage of `ExecutorService`, `Future`, `CountDownLatch`, and `Semaphore`?

**A:**  
- `ExecutorService`: Manages thread execution.
- `Future`: Represents result of asynchronous computation.
- `CountDownLatch`: Allows threads to wait until a countdown reaches zero.
- `Semaphore`: Controls access to resources by multiple threads.

**Real-world scenario:**  
Using `CountDownLatch` to wait for multiple services to start before proceeding.

**Code example:**

```java
CountDownLatch latch = new CountDownLatch(3);

for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        System.out.println("Service started");
        latch.countDown();
    }).start();
}

latch.await();
System.out.println("All services started");
```

**Follow-up Q:** How does a semaphore differ from a mutex?

**Follow-up A:**  
A mutex allows only one thread to access a resource, while a semaphore can allow multiple threads up to a limit.

---

### 11. Producer-Consumer Example

**Q:** Can you illustrate a producer-consumer problem in Java?

**A:**  
The producer generates data and puts it into a shared buffer; the consumer takes data from the buffer. Proper synchronization prevents race conditions.

**Code example:**

```java
class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(value);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int val = queue.poll();
        notify();
        return val;
    }
}
```

**Follow-up Q:** How can this problem be solved using higher-level concurrency utilities?

**Follow-up A:**  
Using `BlockingQueue` from `java.util.concurrent` simplifies the implementation by handling synchronization internally.

---

### Additional Questions

---

### 12. Thread Local Usage and Implementation

**Q:** What is `ThreadLocal` and when would you use it?

**A:**  
`ThreadLocal` provides thread-local variables, ensuring each thread has its own independent copy, useful for maintaining thread-specific data.

**Code example:**

```java
ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

threadLocal.set(5);
System.out.println(threadLocal.get());
```

**Follow-up Q:** How does `ThreadLocal` prevent data sharing issues?

**Follow-up A:**  
By isolating data per thread, it prevents interference between threads without explicit synchronization.

---

### 13. Fork/Join Framework Deep Dive

**Q:** What is the Fork/Join framework and when is it used?

**A:**  
It is designed for parallelism by recursively splitting tasks into smaller subtasks and joining results, ideal for divide-and-conquer algorithms.

**Code example:**

```java
class SumTask extends RecursiveTask<Integer> {
    private int[] arr;
    private int start, end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {
        if (end - start <= 10) {
            int sum = 0;
            for (int i = start; i < end; i++) sum += arr[i];
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask left = new SumTask(arr, start, mid);
            SumTask right = new SumTask(arr, mid, end);
            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}
```

**Follow-up Q:** How does work stealing improve performance?

**Follow-up A:**  
Idle threads steal tasks from busy threads' queues, balancing load and improving CPU utilization.

---

### 14. CompletableFuture vs Future

**Q:** How does `CompletableFuture` differ from `Future`?

**A:**  
`CompletableFuture` supports chaining, combining, and asynchronous callbacks, while `Future` only supports blocking to get the result.

**Code example:**

```java
CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenAccept(System.out::println);
```

**Follow-up Q:** Can `CompletableFuture` handle exceptions?

**Follow-up A:**  
Yes, with methods like `exceptionally()` and `handle()`.

---

### 15. Atomic Operations and Classes

**Q:** What are atomic operations and how do atomic classes help?

**A:**  
Atomic operations are indivisible actions that prevent race conditions. Atomic classes like `AtomicInteger` provide thread-safe operations without synchronization.

**Code example:**

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

**Follow-up Q:** When should you use atomic classes over synchronization?

**Follow-up A:**  
When you need simple atomic operations with better performance and less overhead than synchronization.

---

### 16. Phaser vs CyclicBarrier vs CountDownLatch

**Q:** What are the differences between `Phaser`, `CyclicBarrier`, and `CountDownLatch`?

**A:**  
- `CountDownLatch`: One-time countdown, threads wait until count reaches zero.
- `CyclicBarrier`: Allows a fixed number of threads to wait for each other repeatedly.
- `Phaser`: More flexible, supports dynamic registration and multiple phases.

**Follow-up Q:** When would you use `Phaser`?

**Follow-up A:**  
When you need to coordinate threads over multiple phases with dynamic participation.

---

### 17. ReentrantLock vs Synchronized

**Q:** How does `ReentrantLock` differ from the `synchronized` keyword?

**A:**  
`ReentrantLock` offers more features like fairness, ability to interrupt lock acquisition, and tryLock with timeout, while `synchronized` is simpler and easier to use.

**Follow-up Q:** When should you prefer `ReentrantLock`?

**Follow-up A:**  
When you need advanced lock control, such as fairness or interruptible locking.

---

### 18. Virtual Threads vs Platform Threads

**Q:** What are virtual threads and how do they differ from platform threads?

**A:**  
Virtual threads are lightweight threads managed by the JVM, allowing millions of concurrent threads. Platform threads map directly to OS threads and are heavier.

**Follow-up Q:** What are the benefits of virtual threads?

**Follow-up A:**  
Improved scalability and simpler concurrency models without blocking OS threads.

---

### 19. Memory Consistency Effects

**Q:** What are memory consistency effects in concurrency?

**A:**  
They refer to visibility guarantees between threads, ensuring changes by one thread become visible to others, controlled by synchronization and volatile variables.

**Follow-up Q:** How does the `volatile` keyword affect memory consistency?

**Follow-up A:**  
It ensures that reads and writes to a variable are directly from and to main memory, preventing caching issues.

---

### 20. Thread Pool Types and Configurations

**Q:** What are common thread pool types and how do you configure them?

**A:**  
Common types include fixed, cached, scheduled, and single-thread pools. Configuration involves setting core pool size, max pool size, keep-alive time, and queue type.

**Follow-up Q:** How do you choose the right thread pool?

**Follow-up A:**  
Based on task nature (CPU-bound vs I/O-bound), expected load, and resource constraints.

---

### 21. Lock-free Algorithms

**Q:** What are lock-free algorithms and why are they important?

**A:**  
Lock-free algorithms avoid locks, reducing contention and deadlocks, improving scalability and performance.

**Real-world scenario:**  
High-performance concurrent data structures like `ConcurrentLinkedQueue`.

**Follow-up Q:** What Java classes provide lock-free implementations?

**Follow-up A:**  
Classes in `java.util.concurrent.atomic` and concurrent collections like `ConcurrentLinkedQueue`.

---

This comprehensive Q&A session covers essential multithreading and concurrency topics, providing clear explanations, practical examples, and insightful follow-ups to prepare for technical interviews.