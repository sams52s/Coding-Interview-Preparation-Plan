# 🧱 Stacks and Queues – Core DSA Structures

Stacks and Queues are linear data structures that manage data in specific access orders. These are fundamental in computer science and frequently appear in real-world systems and interviews.

---

## 📦 1. Stack (LIFO – Last In First Out)

A stack allows insertion and deletion at **one end only** (the top).

### ✅ Common Operations

| Operation | Description                | Time Complexity |
|-----------|----------------------------|------------------|
| `push()`  | Add element to top         | O(1)             |
| `pop()`   | Remove top element         | O(1)             |
| `peek()`  | View top without removing  | O(1)             |
| `isEmpty()` | Check if stack is empty  | O(1)             |

### 🔧 Java Implementation (Stack)
```java
Stack<Integer> stack = new Stack<>();
stack.push(10);
int val = stack.pop();
int top = stack.peek();
```

### ✅ Modern Java (Deque as Stack)
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(5);
int top = stack.pop();
```

### 🛠️ Use Cases
- Function call stack / recursion
- Undo/Redo operations
- Expression evaluation (Postfix, Infix)
- Backtracking (mazes, paths)
- Syntax parsing (XML/HTML tags, brackets)

---

## 📬 2. Queue (FIFO – First In First Out)

Queue allows insertion at rear and deletion from front.

### ✅ Common Operations

| Operation    | Description              | Time Complexity |
|--------------|--------------------------|------------------|
| `offer()`    | Add to rear              | O(1)             |
| `poll()`     | Remove from front        | O(1)             |
| `peek()`     | View front without removing | O(1)          |
| `isEmpty()`  | Check if queue is empty  | O(1)             |

### 🔧 Java Implementation (Queue)
```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(10);
int val = queue.poll();
```

### 🔧 Circular Queue (Array Implementation)
```java
int[] queue = new int[capacity];
int front = 0, rear = -1, size = 0;
```

### 🛠️ Use Cases
- Job scheduling
- Print queues, message queues
- BFS (Breadth-First Search)
- Rate limiting, cache eviction (LRU)

---

## ⏰ Best vs Worst Case Scenarios

| Structure | Best Case (O(1))        | Worst Case (O(n))               |
|-----------|--------------------------|----------------------------------|
| Stack     | push/pop/peek            | Searching within stack           |
| Queue     | enqueue/dequeue/peek     | Searching/removing by value      |

---

## ⚠️ Pitfalls & Tips

- Never access internal elements directly (breaks abstraction)
- Check empty state before pop/poll
- Avoid using Java's legacy `Stack` class in concurrent environments

---

## 💼 Interview Questions & Answers

### ❓ Why use `Deque` over `Stack`?
**✅** `Deque` is faster and thread-safe for single-threaded apps, unlike legacy `Stack`.

### ❓ How do you implement a stack using queues?
Use two queues; on push, enqueue to empty queue and dequeue everything from the other queue into it.

### ❓ How do you reverse a queue?
Use a stack to hold elements and reinsert into the queue.

### ❓ What is a monotonic stack?
A stack that maintains elements in increasing/decreasing order (used in histogram problems, next greater element).

---

## 🧪 Popular Coding Problems

### Stack Problems:
- Valid Parentheses
- Min Stack (support getMin in O(1))
- Evaluate Reverse Polish Notation
- Largest Rectangle in Histogram
- Daily Temperatures

### Queue Problems:
- Implement Queue using Stacks
- Rotting Oranges (BFS)
- Sliding Window Maximum (using Deque)
- First Non-Repeating Character in Stream

---

## ✅ Summary

Stacks and queues are powerful abstract data types that support controlled access. Understanding them unlocks solutions in parsing, scheduling, and traversal problems.

---

## 📝 References

- [GeeksforGeeks – Stack](https://www.geeksforgeeks.org/stack-data-structure/)
- [GeeksforGeeks – Queue](https://www.geeksforgeeks.org/queue-data-structure/)
- [Java Deque Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html)
- [Visualgo – Stack/Queue](https://visualgo.net/en/list)
