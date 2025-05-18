# 🔗 Linked Lists in Data Structures

A **Linked List** is a linear data structure where each element (node) contains a reference (pointer) to the next node in the sequence. Unlike arrays, linked lists do not require contiguous memory and can grow/shrink dynamically.

---

## 📘 Types of Linked Lists

### 1. Singly Linked List

Each node points to the next node. Traversal is one-way.

```
[head] -> [data|next] -> [data|next] -> null
```

```java
class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}
```

### 2. Doubly Linked List

Each node has `prev` and `next` pointers. Allows bidirectional traversal.

```
null <- [prev|data|next] <-> [prev|data|next] <-> null
```

```java
class DNode {
    int data;
    DNode prev, next;
    DNode(int data) { this.data = data; }
}
```

### 3. Circular Linked List

- Last node points back to the head.
- Can be singly or doubly circular.

---

## 🛠️ Common Operations

| Operation        | Description                        | Time Complexity |
|------------------|------------------------------------|------------------|
| Insertion (Head) | Add a node at the beginning        | O(1)             |
| Insertion (Tail) | Add a node at the end              | O(n) or O(1)*    |
| Deletion         | Remove a specific node             | O(n)             |
| Traversal        | Iterate through the list           | O(n)             |
| Search           | Find a value in the list           | O(n)             |

\* O(1) if tail pointer is maintained

---

## 📌 When to Use Linked Lists

- When frequent insertions/deletions are needed (e.g., real-time data feed, undo operations).
- When memory size is unknown ahead of time.
- In dynamic data structures (e.g., adjacency list in graphs).

### ✅ Best Case Scenarios

| Scenario | Justification |
|----------|---------------|
| Insert at head or tail | O(1) operation if tail is known |
| Iterative traversal from start | Efficient if only forward traversal is needed |

### ❌ When Not to Use

- When frequent random access is required → Use Array/ArrayList
- When memory overhead is critical (linked lists use extra memory for pointers)

### ❌ Worst Case Scenarios

| Scenario | Explanation |
|----------|-------------|
| Search/Delete by value | Linear scan required – O(n) |
| Access by index | Not supported efficiently (O(n)) |

---

## 🔎 Code Snippets

### Insert at Head (Singly):
```java
void insertAtHead(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
}
```

### Delete by Value (Singly):
```java
void delete(int key) {
    Node curr = head, prev = null;
    if (curr != null && curr.data == key) {
        head = curr.next;
        return;
    }
    while (curr != null && curr.data != key) {
        prev = curr;
        curr = curr.next;
    }
    if (curr != null) prev.next = curr.next;
}
```

### Reverse Linked List:
```java
Node prev = null, curr = head, next = null;
while (curr != null) {
    next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
head = prev;
```

---

## ⚠️ Common Pitfalls

- Losing head reference while deleting
- Infinite loop in circular lists
- NullPointerException when not checking nulls
- Forgetting to update both `prev` and `next` in doubly linked lists

---

## 💡 Real-World Use Cases

- **Undo/Redo Operations** (editors, IDEs)
- **Music playlists** (next/previous functionality)
- **HashMap chaining** (separate chaining in collision handling)
- **Dynamic memory allocation** systems

---

## 💼 Interview Questions & Answers

### ❓ What are the advantages of linked lists over arrays?
**✅** Dynamic size, easy insertion/deletion, no memory waste

### ❓ Why is linked list traversal slower than arrays?
**✅** No direct access to elements; must traverse one-by-one

### ❓ How do you detect a loop in a linked list?
**✅** Floyd’s Cycle Detection Algorithm (Tortoise and Hare)

### ❓ Difference between singly and doubly linked list?
**✅**
- Singly: One-way traversal, less memory
- Doubly: Bi-directional, easier deletion

### ❓ How do you merge two sorted linked lists?
```java
Node merge(Node a, Node b) {
    if (a == null) return b;
    if (b == null) return a;
    if (a.data < b.data) {
        a.next = merge(a.next, b);
        return a;
    } else {
        b.next = merge(a, b.next);
        return b;
    }
}
```

### ❓ When would you use a circular linked list?
**✅** In round-robin scheduling, looping buffers, or cyclic iteration

### ❓ How would you find the intersection point of two linked lists?
**✅ A:**
```java
Node findIntersection(Node headA, Node headB) {
    if (headA == null || headB == null) return null;
    
    Node a = headA;
    Node b = headB;
    
    while (a != b) {
        a = (a == null) ? headB : a.next;
        b = (b == null) ? headA : b.next;
    }
    return a;
}
```

### ❓ How to implement a deep copy of a linked list with arbitrary pointers?
**✅ A:**
```java
Node copyList(Node head) {
    // Step 1: Create interweaved list
    Node curr = head;
    while (curr != null) {
        Node copy = new Node(curr.val);
        copy.next = curr.next;
        curr.next = copy;
        curr = copy.next;
    }
    
    // Step 2: Set arbitrary pointers
    curr = head;
    while (curr != null) {
        if (curr.arbitrary != null) {
            curr.next.arbitrary = curr.arbitrary.next;
        }
        curr = curr.next.next;
    }
    
    // Step 3: Split the lists
    Node original = head;
    Node copy = head.next;
    Node copyHead = copy;
    
    while (original != null && copy != null) {
        original.next = original.next.next;
        copy.next = copy.next != null ? copy.next.next : null;
        original = original.next;
        copy = copy.next;
    }
    
    return copyHead;
}
```

### ❓ How would you check if a linked list is palindromic?
**✅ A:**
```java
boolean isPalindrome(Node head) {
    if (head == null || head.next == null) return true;
    
    // Find middle
    Node slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Reverse second half
    Node secondHalf = reverseList(slow.next);
    
    // Compare halves
    Node firstHalf = head;
    while (secondHalf != null) {
        if (firstHalf.val != secondHalf.val) return false;
        firstHalf = firstHalf.next;
        secondHalf = secondHalf.next;
    }
    
    return true;
}
```

### ❓ How to handle memory-efficient doubly linked list?
**✅ A:**
```java
class XORLinkedList {
    static class Node {
        int data;
        Node xorPtr;  // XOR of prev and next
        
        Node(int data) {
            this.data = data;
        }
    }
    
    private Node XOR(Node a, Node b) {
        return (Node)((long)a ^ (long)b);
    }
    
    Node traverse(Node head) {
        Node curr = head;
        Node prev = null;
        Node next;
        
        while (curr != null) {
            next = XOR(prev, curr.xorPtr);
            prev = curr;
            curr = next;
        }
        return prev; // returns last node
    }
}
```

### ❓ How would you implement a thread-safe linked list?
**✅ A:**
```java
class ConcurrentLinkedList {
    private Node head;
    private final ReentrantLock lock = new ReentrantLock();
    
    public void insertAtHead(int data) {
        lock.lock();
        try {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        } finally {
            lock.unlock();
        }
    }
    
    public boolean delete(int key) {
        lock.lock();
        try {
            if (head == null) return false;
            if (head.data == key) {
                head = head.next;
                return true;
            }
            Node curr = head;
            while (curr.next != null && curr.next.data != key) {
                curr = curr.next;
            }
            if (curr.next != null) {
                curr.next = curr.next.next;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
```

---

## 🎯 Advanced Linked List Problems & Solutions

### 1. LRU Cache Implementation
```java
class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, Node> cache;
    private Node head, tail;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
```

### 2. Skip List Implementation
```java
class SkipList {
    private static final double PROBABILITY = 0.5;
    private static final int MAX_LEVEL = 16;
    
    static class Node {
        int value;
        Node[] next;
        
        Node(int value, int level) {
            this.value = value;
            next = new Node[level + 1];
        }
    }
    
    private Node head;
    private int level;
    
    public SkipList() {
        head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        level = 0;
    }
    
    private int randomLevel() {
        int lvl = 0;
        while (Math.random() < PROBABILITY && lvl < MAX_LEVEL) lvl++;
        return lvl;
    }
}
```

### 3. XOR Linked List (Memory Efficient)
```java
class XORLinkedList {
    static class Node {
        int data;
        Node npx; // XOR of next and previous node
        
        Node(int data) {
            this.data = data;
        }
    }
    
    private Node head;
    
    Node XOR(Node a, Node b) {
        return (Node)((long)a ^ (long)b);
    }
    
    void insert(int data) {
        Node newNode = new Node(data);
        newNode.npx = XOR(head, null);
        if (head != null) {
            Node next = XOR(head.npx, null);
            head.npx = XOR(newNode, next);
        }
        head = newNode;
    }
}
```

## 🔄 Advanced Operations & Algorithms

### 1. Clone a Linked List with Random Pointers
```java
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // Step 1: Create interleaved list
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        
        // Step 2: Copy random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        // Step 3: Separate lists
        Node dummy = new Node(0);
        Node copyTail = dummy;
        curr = head;
        while (curr != null) {
            Node copy = curr.next;
            curr.next = copy.next;
            copyTail.next = copy;
            copyTail = copy;
            curr = curr.next;
        }
        
        return dummy.next;
    }
}
```

### 2. Quicksort on Linked List
```java
class QuickSortList {
    Node partition(Node start, Node end) {
        int pivot = start.data;
        Node curr = start.next;
        Node pivotNode = start;
        
        while (curr != end) {
            if (curr.data < pivot) {
                pivotNode = start;
                int temp = start.data;
                start.data = curr.data;
                curr.data = temp;
                start = start.next;
            }
            curr = curr.next;
        }
        
        int temp = start.data;
        start.data = pivot;
        pivotNode.data = temp;
        
        return pivotNode;
    }
    
    void quickSort(Node start, Node end) {
        if (start != end) {
            Node pivot = partition(start, end);
            quickSort(start, pivot);
            quickSort(pivot.next, end);
        }
    }
}
```

## 🧪 Additional Interview Problems

### 1. Reverse Nodes in k-Group
```java
public Node reverseKGroup(Node head, int k) {
    Node curr = head;
    int count = 0;
    
    // Count k nodes
    while (curr != null && count < k) {
        curr = curr.next;
        count++;
    }
    
    if (count == k) {
        curr = reverseKGroup(curr, k);
        while (count-- > 0) {
            Node temp = head.next;
            head.next = curr;
            curr = head;
            head = temp;
        }
        head = curr;
    }
    return head;
}
```

### 2. Merge Sort Implementation
```java
public Node mergeSort(Node head) {
    if (head == null || head.next == null) return head;
    
    // Find middle
    Node middle = getMiddle(head);
    Node nextOfMiddle = middle.next;
    middle.next = null;
    
    // Recursive sort
    Node left = mergeSort(head);
    Node right = mergeSort(nextOfMiddle);
    
    // Merge
    return sortedMerge(left, right);
}
```

---

## ✅ Summary

Linked lists offer flexibility in memory management and efficient dynamic operations. Mastering their structure, variants, and operations is fundamental for cracking interviews and implementing real-time data flows.

---

## 📝 References

- [GeeksForGeeks – Linked List](https://www.geeksforgeeks.org/data-structures/linked-list/)
- [Visualgo – Linked List Animation](https://visualgo.net/en/list)
- [LeetCode – Linked List Problems](https://leetcode.com/tag/linked-list/)