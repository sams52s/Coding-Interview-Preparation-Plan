# 🧩 Data Structures & Algorithms Interview Master Question Bank

This comprehensive question bank is now structured as a series of interview-style questions and answers, each addressing a core topic in data structures and algorithms. Each Q&A includes real-world scenarios, code snippets, explanations, and follow-up questions for deeper understanding. Use this as your definitive reference manual for interviews and mastering DSA concepts.

---

## Arrays & Strings

### Q: How do you find the second largest number in an array using only a single traversal?

**A:**  
To find the second largest element in an array in a single pass, you maintain two variables: one for the largest (`first`) and one for the second largest (`second`). As you iterate, update these variables accordingly.  

**Real-world scenario:**  
Suppose you are processing competition scores and want to find the runner-up efficiently without sorting the entire list.

**Code:**
```python
def second_largest(arr):
    first = second = float('-inf')
    for num in arr:
        if num > first:
            second = first
            first = num
        elif first > num > second:
            second = num
    return second if second != float('-inf') else None
```
**Explanation:**  
The code keeps track of the highest and second-highest values. If the array has all identical elements or only one unique element, it returns `None`.

**Follow-up Q:**  
What if the array contains duplicate maximum values?  
**A:**  
The above approach will skip duplicates for the second largest. If you want the second *distinct* largest, this works; if not, you may need to adjust for your requirements.

---

### Q: How would you move all zeroes in an array to the end while preserving the order of non-zero elements?

**A:**  
Use two pointers: one iterates through the array, and the other (`last_non_zero`) tracks the position to place the next non-zero element.

**Real-world scenario:**  
Optimizing storage for sparse data, such as compressing arrays for efficient transmission or display.

**Code:**
```python
def move_zeroes(nums):
    last_non_zero = 0
    for i in range(len(nums)):
        if nums[i] != 0:
            nums[last_non_zero], nums[i] = nums[i], nums[last_non_zero]
            last_non_zero += 1
```
**Explanation:**  
Non-zero elements are moved forward, and zeroes fill the remaining positions.

**Follow-up Q:**  
Is this algorithm in-place, and what is its time complexity?  
**A:**  
Yes, it's in-place (O(1) extra space), and the time complexity is O(n).

---

### Q: How do you solve the Two Sum and Three Sum problems efficiently?

**A:**  
For Two Sum, use a hash map to store previously seen numbers. For Three Sum, sort the array and use pointers.

**Real-world scenario:**  
Finding pairs of financial transactions that sum to a suspicious amount (fraud detection).

**Code (Two Sum):**
```python
def two_sum(nums, target):
    seen = {}
    for i, num in enumerate(nums):
        if target - num in seen:
            return [seen[target - num], i]
        seen[num] = i
    return []
```
**Explanation:**  
For each number, check if the complement exists in the map. If so, return the indices.

**Follow-up Q:**  
How would you extend this to Three Sum?  
**A:**  
Sort the array, then for each element, use the Two Sum approach for the remaining part of the array.

---

### Q: How do you find the maximum value in every subarray of size k (Sliding Window Maximum)?

**A:**  
Use a deque to keep track of indices of potential maximums in the current window.

**Real-world scenario:**  
Analyzing rolling maximum stock prices or smoothing noisy sensor data.

**Code:**
```python
from collections import deque
def sliding_window_max(nums, k):
    dq, res = deque(), []
    for i, n in enumerate(nums):
        while dq and dq[0] <= i - k:
            dq.popleft()
        while dq and nums[dq[-1]] < n:
            dq.pop()
        dq.append(i)
        if i >= k - 1:
            res.append(nums[dq[0]])
    return res
```
**Explanation:**  
The deque stores indices; the front is always the index of the current window's maximum.

**Follow-up Q:**  
What is the time complexity?  
**A:**  
O(n), since each element is added and removed at most once.

---

### Q: How do you find the length of the longest substring without repeating characters?

**A:**  
Use a sliding window with a set to track unique characters.

**Real-world scenario:**  
Validating password strength or compressing data by identifying unique substrings.

**Code:**
```python
def length_of_longest_substring(s):
    chars = set()
    left = res = 0
    for right in range(len(s)):
        while s[right] in chars:
            chars.remove(s[left])
            left += 1
        chars.add(s[right])
        res = max(res, right - left + 1)
    return res
```
**Explanation:**  
The window expands as long as characters are unique, and shrinks otherwise.

**Follow-up Q:**  
How would you modify this to return the substring itself?  
**A:**  
Track the start index and update it when a longer substring is found.

---

### Q: How can you check if a string is a valid palindrome, possibly ignoring non-alphanumeric characters?

**A:**  
Normalize the string by removing non-alphanumeric characters and comparing it to its reverse.

**Real-world scenario:**  
Checking DNA sequences or validating user input in natural language processing.

**Code:**
```python
import re
def is_palindrome(s):
    s = re.sub(r'[^A-Za-z0-9]', '', s).lower()
    return s == s[::-1]
```
**Explanation:**  
Regular expressions strip unwanted characters; comparison is case-insensitive.

**Follow-up Q:**  
How would you check for palindrome with at most one character removal?  
**A:**  
Use two pointers and allow one mismatch, recursively checking the substrings.

---

### Q: How do you compress a string using run-length encoding?

**A:**  
Count consecutive repeating characters and append the count after the character.

**Real-world scenario:**  
Data compression algorithms, such as for image or text files.

**Code:**
```python
def compress(s):
    res, count = [], 1
    for i in range(1, len(s)):
        if s[i] == s[i-1]:
            count += 1
        else:
            res.append(s[i-1] + (str(count) if count > 1 else ''))
            count = 1
    res.append(s[-1] + (str(count) if count > 1 else ''))
    return ''.join(res)
```
**Explanation:**  
Iterate through the string, grouping consecutive identical characters.

**Follow-up Q:**  
What is the time complexity?  
**A:**  
O(n), where n is the length of the string.
---
## Searching & Sorting

### Q: How does binary search work and what are its variations?

**A:**  
Binary search repeatedly divides the search interval in half. If the value of the search key is less than the item in the middle, it narrows the interval to the lower half, otherwise to the upper half.

**Real-world scenario:**  
Database indexing, searching sorted logs, or any sorted dataset.

**Code:**
```python
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
```
**Explanation:**  
Efficient for sorted arrays, with O(log n) time complexity.

**Follow-up Q:**  
How would you use binary search to find the first or last occurrence of a target?  
**A:**  
Modify the search to continue if the target is found, moving left/right to find boundaries.

---
## Linked Lists

### Q: How do you reverse a singly linked list in-place?

**A:**  
Iterate through the list, reversing the direction of each node's pointer.

**Real-world scenario:**  
Undo operations in text editors, reversing user history, or implementing stack-like behavior.

**Code:**
```python
def reverse_list(head):
    prev = None
    curr = head
    while curr:
        next_node = curr.next
        curr.next = prev
        prev = curr
        curr = next_node
    return prev
```
**Explanation:**  
At each step, the current node's `next` pointer is redirected to the previous node.

**Follow-up Q:**  
What is the space complexity?  
**A:**  
O(1), as it reverses the list in-place.
## Trees & Graphs

### Q: What are the main tree traversal methods and when would you use each?

**A:**  
The main traversals are:
 - In-order: Left, Root, Right (for BSTs, yields sorted order)
 - Pre-order: Root, Left, Right (used for copying trees)
 - Post-order: Left, Right, Root (used for deleting trees)

**Real-world scenario:**  
Evaluating arithmetic expressions (expression trees), serializing trees for storage.

**Code (In-order):**
```python
def inorder(root):
    return inorder(root.left) + [root.val] + inorder(root.right) if root else []
```
**Explanation:**  
Recursively visits the left subtree, the root, then the right subtree.

**Follow-up Q:**  
How would you implement iterative tree traversals?  
**A:**  
Use a stack to simulate recursion.
## Dynamic Programming

### Q: How can you compute the nth Fibonacci number efficiently?

**A:**  
Use dynamic programming to store previously computed results and avoid redundant calculations.

**Real-world scenario:**  
Population modeling, recursive financial projections, or any scenario with overlapping subproblems.

**Code:**
```python
def fib(n):
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    return a
```
**Explanation:**  
This approach uses two variables and iterates n times, achieving O(n) time and O(1) space.

**Follow-up Q:**  
How would you use memoization for recursive Fibonacci?  
**A:**  
Store results in a dictionary or array to avoid recomputation.
## Stack & Queue

### Q: How do you check if a string has valid parentheses?

**A:**  
Use a stack to ensure every opening bracket has a corresponding closing bracket in the correct order.

**Real-world scenario:**  
Expression parsing in compilers, validating mathematical expressions, or code formatting tools.

**Code:**
```python
def is_valid(s):
    stack = []
    mapping = {')':'(', '}':'{', ']':'['}
    for char in s:
        if char in mapping.values():
            stack.append(char)
        elif char in mapping:
            if not stack or mapping[char] != stack.pop():
                return False
    return not stack
```
**Explanation:**  
Push opening brackets onto the stack, and for each closing bracket, check if it matches the top of the stack.

**Follow-up Q:**  
How would you extend this to support custom bracket types?  
**A:**  
Update the `mapping` dictionary with new bracket pairs.
## Heap & Priority Queue

### Q: How do you find the kth largest element in an array using a heap?

**A:**  
Use a min-heap of size k, maintaining the k largest elements seen so far.

**Real-world scenario:**  
Finding the k highest scores in a leaderboard or streaming analytics.

**Code:**
```python
import heapq
def kth_largest(nums, k):
    return heapq.nlargest(k, nums)[-1]
```
**Explanation:**  
`heapq.nlargest` efficiently finds the k largest elements; the last is the kth largest.

**Follow-up Q:**  
How would you solve this if the data stream is too large to store?  
**A:**  
Maintain a min-heap of size k as you process the stream.
## Hash Tables

### Q: How would you implement a basic HashMap from scratch?

**A:**  
Use an array of buckets (lists), mapping keys to bucket indices via a hash function. Use separate chaining to handle collisions.

**Real-world scenario:**  
Implementing a cache, database indexing, or symbol tables in interpreters.

**Code:**
```python
class HashMap:
    def __init__(self, size=1000):
        self.size = size
        self.table = [[] for _ in range(size)]
    def put(self, key, value):
        h = hash(key) % self.size
        for i, (k, v) in enumerate(self.table[h]):
            if k == key:
                self.table[h][i] = (key, value)
                return
        self.table[h].append((key, value))
    def get(self, key):
        h = hash(key) % self.size
        for k, v in self.table[h]:
            if k == key:
                return v
        return None
    def remove(self, key):
        h = hash(key) % self.size
        self.table[h] = [(k, v) for k, v in self.table[h] if k != key]
```
**Explanation:**  
Each bucket stores key-value pairs; collisions are resolved by storing multiple pairs in a bucket.

**Follow-up Q:**  
How would you handle hash collisions more efficiently?  
**A:**  
Use open addressing or more advanced hash functions.
## Bit Manipulation

### Q: How do you find the element that appears only once in an array where every other element appears twice?

**A:**  
Use XOR: all duplicate numbers cancel out, leaving the unique number.

**Real-world scenario:**  
Error detection in data streams or finding a corrupted entry.

**Code:**
```python
def single_number(nums):
    res = 0
    for n in nums:
        res ^= n
    return res
```
**Explanation:**  
XOR of a number with itself is 0; XOR with 0 is the number itself.

**Follow-up Q:**  
How would you find a number appearing once when every other appears three times?  
**A:**  
Use bit counting for each bit position.
## Advanced Topics

### Q: What is a Trie and how is it used?

**A:**  
A Trie (prefix tree) is used for efficient retrieval of strings, such as words in a dictionary. Each node represents a character, and paths from the root to leaves represent words.

**Real-world scenario:**  
Autocomplete in search engines, spell checking, and IP routing.

**Code:**
```python
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False
class Trie:
    def __init__(self):
        self.root = TrieNode()
    def insert(self, word):
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
        node.is_end = True
    def search(self, word):
        node = self.root
        for c in word:
            if c not in node.children:
                return False
            node = node.children[c]
        return node.is_end
```
**Explanation:**  
Insertion and search are both O(L), where L is the length of the word.

**Follow-up Q:**  
How would you implement prefix search?  
**A:**  
Traverse the Trie up to the prefix, then collect all words below that node.
## System Design Concepts

### Q: How do you analyze an algorithm's time and space complexity?

**A:**  
Time complexity describes how the runtime grows as input size increases; space complexity describes memory usage growth.

**Real-world scenario:**  
Planning for scalability in production systems.

**Follow-up Q:**  
What is the difference between Big O, Big Theta, and Big Omega notations?  
**A:**  
Big O: Upper bound; Big Omega: Lower bound; Big Theta: Tight bound (both upper and lower).
## Problem-Solving Patterns

### Q: What is the two pointers technique and when is it useful?

**A:**  
Use two pointers to traverse a data structure from different directions or at different speeds.

**Real-world scenario:**  
Checking if an array is a palindrome, merging sorted arrays, or removing duplicates.

**Code Example:**
```python
def is_palindrome(arr):
    left, right = 0, len(arr) - 1
    while left < right:
        if arr[left] != arr[right]:
            return False
        left += 1
        right -= 1
    return True
```
**Explanation:**  
Left and right pointers move toward each other, comparing elements.

**Follow-up Q:**  
How can two pointers be used in partitioning problems?  
**A:**  
One pointer can track the boundary between partitions.
---

## Searching & Sorting
### Binary Search and Its Variations
**Explanation:**  
Efficiently find an element in a sorted array (O(log n)).

**Use Case:**  
Database indexing, search engines.

**Code:**
```python
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
```

---
### Merge Sort, Quick Sort, Heap Sort
**Explanation:**  
Efficient comparison-based sorting algorithms.

**Use Case:**  
Large dataset sorting, database query optimization.

**Code (Merge Sort):**
```python
def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) // 2
        L = arr[:mid]
        R = arr[mid:]
        merge_sort(L)
        merge_sort(R)
        i = j = k = 0
        while i < len(L) and j < len(R):
            if L[i] < R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1
        while i < len(L):
            arr[k] = L[i]
            i += 1
            k += 1
        while j < len(R):
            arr[k] = R[j]
            j += 1
            k += 1
```

---
### Search in Rotated Sorted Array
**Explanation:**  
Find an element in a rotated sorted array using modified binary search.

**Use Case:**  
Circular buffer lookup, versioned data structures.

**Code:**
```python
def search_rotated(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        if arr[left] <= arr[mid]:
            if arr[left] <= target < arr[mid]:
                right = mid - 1
            else:
                left = mid + 1
        else:
            if arr[mid] < target <= arr[right]:
                left = mid + 1
            else:
                right = mid - 1
    return -1
```

---
### Kth Largest/Smallest Element
**Explanation:**  
Find the kth largest/smallest element using heap or Quickselect.

**Use Case:**  
Leaderboard rankings, percentile calculations.

**Code (Quickselect):**
```python
import random
def quickselect(arr, k):
    if len(arr) == 1:
        return arr[0]
    pivot = random.choice(arr)
    lows = [el for el in arr if el < pivot]
    highs = [el for el in arr if el > pivot]
    pivots = [el for el in arr if el == pivot]
    if k < len(lows):
        return quickselect(lows, k)
    elif k < len(lows) + len(pivots):
        return pivots[0]
    else:
        return quickselect(highs, k - len(lows) - len(pivots))
```

---
### Meeting Rooms Problem
**Explanation:**  
Given intervals, check if a person could attend all meetings (no overlaps).

**Use Case:**  
Calendar scheduling, resource allocation.

**Code:**
```python
def can_attend_meetings(intervals):
    intervals.sort(key=lambda x: x[0])
    for i in range(1, len(intervals)):
        if intervals[i][0] < intervals[i-1][1]:
            return False
    return True
```

---
### Merge Intervals
**Explanation:**  
Merge overlapping intervals.

**Use Case:**  
Calendar applications, merging time slots.

**Code:**
```python
def merge_intervals(intervals):
    intervals.sort(key=lambda x: x[0])
    merged = []
    for interval in intervals:
        if not merged or merged[-1][1] < interval[0]:
            merged.append(interval)
        else:
            merged[-1][1] = max(merged[-1][1], interval[1])
    return merged
```

## Linked Lists
### Reverse a Linked List
**Explanation:**  
Reverse a singly linked list in-place.

**Use Case:**  
Undo operations, reversing history, stack implementation.

**Code:**
```python
def reverse_list(head):
    prev = None
    curr = head
    while curr:
        next_node = curr.next
        curr.next = prev
        prev = curr
        curr = next_node
    return prev
```

---
### Detect Cycle in Linked List
**Explanation:**  
Use Floyd's Tortoise and Hare algorithm to detect cycles.

**Use Case:**  
Detecting infinite loops in scheduling, network topology analysis.

**Code:**
```python
def has_cycle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return True
    return False
```

---
### Merge Two Sorted Lists
**Explanation:**  
Merge two sorted linked lists into one sorted list.

**Use Case:**  
Merging sorted data streams, external sorting.

**Code:**
```python
def merge_lists(l1, l2):
    dummy = curr = ListNode(0)
    while l1 and l2:
        if l1.val < l2.val:
            curr.next, l1 = l1, l1.next
        else:
            curr.next, l2 = l2, l2.next
        curr = curr.next
    curr.next = l1 or l2
    return dummy.next
```

---
### LRU Cache Implementation
**Explanation:**  
Design a data structure that removes the least recently used item when capacity is reached.

**Use Case:**  
Web browser caching, memory management.

**Code:**
```python
from collections import OrderedDict
class LRUCache:
    def __init__(self, capacity):
        self.cache = OrderedDict()
        self.capacity = capacity
    def get(self, key):
        if key not in self.cache:
            return -1
        self.cache.move_to_end(key)
        return self.cache[key]
    def put(self, key, value):
        if key in self.cache:
            self.cache.move_to_end(key)
        self.cache[key] = value
        if len(self.cache) > self.capacity:
            self.cache.popitem(last=False)
```

---
### Deep Copy of Linked List with Random Pointer
**Explanation:**  
Create a deep copy of a linked list where each node has a random pointer.

**Use Case:**  
Copying complex data structures, serialization.

**Code:**
```python
def copy_random_list(head):
    if not head:
        return None
    old_to_new = {}
    curr = head
    while curr:
        old_to_new[curr] = ListNode(curr.val)
        curr = curr.next
    curr = head
    while curr:
        old_to_new[curr].next = old_to_new.get(curr.next)
        old_to_new[curr].random = old_to_new.get(curr.random)
        curr = curr.next
    return old_to_new[head]
```

---
### Remove Nth Node from End
**Explanation:**  
Remove the nth node from the end in a single pass using two pointers.

**Use Case:**  
Deleting recent history, managing undo stack.

**Code:**
```python
def remove_nth_from_end(head, n):
    dummy = ListNode(0)
    dummy.next = head
    slow = fast = dummy
    for _ in range(n+1):
        fast = fast.next
    while fast:
        slow = slow.next
        fast = fast.next
    slow.next = slow.next.next
    return dummy.next
```

## Trees & Graphs
### Binary Tree Traversals (In-order, Pre-order, Post-order)
**Explanation:**  
Traverse trees in different orders for various applications.

**Use Case:**  
Expression tree evaluation, serialization.

**Code (In-order):**
```python
def inorder(root):
    return inorder(root.left) + [root.val] + inorder(root.right) if root else []
```

---
### Level Order Traversal
**Explanation:**  
Traverse the tree level by level using a queue.

**Use Case:**  
Printing organization hierarchies, shortest path in unweighted trees.

**Code:**
```python
from collections import deque
def level_order(root):
    if not root: return []
    q, res = deque([root]), []
    while q:
        node = q.popleft()
        res.append(node.val)
        if node.left: q.append(node.left)
        if node.right: q.append(node.right)
    return res
```

---
### Lowest Common Ancestor
**Explanation:**  
Find the lowest common ancestor of two nodes in a binary tree.

**Use Case:**  
File system directory trees, organizational charts.

**Code:**
```python
def lca(root, p, q):
    if not root or root == p or root == q:
        return root
    left = lca(root.left, p, q)
    right = lca(root.right, p, q)
    if left and right:
        return root
    return left or right
```

---
### Validate Binary Search Tree
**Explanation:**  
Check if a tree is a valid BST.

**Use Case:**  
Database indexing, data validation.

**Code:**
```python
def is_valid_bst(root, low=float('-inf'), high=float('inf')):
    if not root:
        return True
    if not (low < root.val < high):
        return False
    return is_valid_bst(root.left, low, root.val) and is_valid_bst(root.right, root.val, high)
```

---
### Graph Representations (Adjacency List/Matrix)
**Explanation:**  
Represent graphs in memory for traversal and search.

**Use Case:**  
Social networks, road maps.

**Code (Adjacency List):**
```python
graph = {0: [1,2], 1: [2], 2: [0,3], 3: [3]}
```

---
### BFS and DFS Implementations
**Explanation:**  
Breadth-first and depth-first search for traversing graphs.

**Use Case:**  
Web crawling, puzzle solving.

**Code (BFS):**
```python
def bfs(graph, start):
    visited, queue = set(), [start]
    while queue:
        v = queue.pop(0)
        if v not in visited:
            visited.add(v)
            queue.extend(set(graph[v]) - visited)
    return visited
```

---
### Dijkstra's Algorithm
**Explanation:**  
Find the shortest path in a weighted graph.

**Use Case:**  
GPS navigation, network routing.

**Code:**
```python
import heapq
def dijkstra(graph, start):
    heap, dist = [(0, start)], {start: 0}
    while heap:
        d, u = heapq.heappop(heap)
        for v, w in graph[u]:
            if v not in dist or d + w < dist[v]:
                dist[v] = d + w
                heapq.heappush(heap, (dist[v], v))
    return dist
```

---
### Minimum Spanning Tree (Kruskal's Algorithm)
**Explanation:**  
Find a subset of edges that connects all vertices with the minimum total edge weight.

**Use Case:**  
Network design, clustering.

**Code:**
```python
def kruskal(n, edges):
    parent = list(range(n))
    def find(u):
        while parent[u] != u:
            parent[u] = parent[parent[u]]
            u = parent[u]
        return u
    res = cost = 0
    for w, u, v in sorted(edges):
        pu, pv = find(u), find(v)
        if pu != pv:
            parent[pu] = pv
            cost += w
            res += 1
    return cost if res == n-1 else None
```

---
### Topological Sort
**Explanation:**  
Order vertices in a directed acyclic graph such that for every directed edge u->v, u comes before v.

**Use Case:**  
Task scheduling, course prerequisite resolution.

**Code:**
```python
def topological_sort(graph):
    visited, stack = set(), []
    def dfs(u):
        visited.add(u)
        for v in graph[u]:
            if v not in visited:
                dfs(v)
        stack.append(u)
    for u in graph:
        if u not in visited:
            dfs(u)
    return stack[::-1]
```

---
### Number of Islands
**Explanation:**  
Count the number of connected components (islands) in a 2D grid.

**Use Case:**  
Image processing, cluster detection.

**Code:**
```python
def num_islands(grid):
    if not grid: return 0
    rows, cols = len(grid), len(grid[0])
    def dfs(r, c):
        if 0 <= r < rows and 0 <= c < cols and grid[r][c] == '1':
            grid[r][c] = '0'
            for dr, dc in [(-1,0),(1,0),(0,-1),(0,1)]:
                dfs(r+dr, c+dc)
    count = 0
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1':
                dfs(r, c)
                count += 1
    return count
```

---
### **Advanced Graph Algorithms**

#### Bellman-Ford Algorithm
**Explanation:**  
Find shortest paths from a single source to all vertices in a graph that may have negative weights.

**Use Case:**  
Currency arbitrage detection, routing in networks with negative edges.

**Code:**
```python
def bellman_ford(graph, V, source):
    dist = [float('inf')] * V
    dist[source] = 0
    for _ in range(V-1):
        for u, v, w in graph:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
    # Check for negative-weight cycles
    for u, v, w in graph:
        if dist[u] + w < dist[v]:
            return None  # Negative cycle detected
    return dist
```

#### Floyd-Warshall Algorithm
**Explanation:**  
Find shortest paths between all pairs of vertices.

**Use Case:**  
All-pairs shortest path in transportation networks.

**Code:**
```python
def floyd_warshall(graph):
    V = len(graph)
    dist = [row[:] for row in graph]
    for k in range(V):
        for i in range(V):
            for j in range(V):
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
    return dist
```

## Dynamic Programming
### Fibonacci Variations
**Explanation:**  
Compute the nth Fibonacci number using DP to avoid redundant calculations.

**Use Case:**  
Population modeling, recursive financial projections.

**Code:**
```python
def fib(n):
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    return a
```

---
### Longest Common Subsequence
**Explanation:**  
Find the longest subsequence present in both sequences.

**Use Case:**  
Diff tools, DNA sequence comparison.

**Code:**
```python
def lcs(X, Y):
    m, n = len(X), len(Y)
    dp = [[0]*(n+1) for _ in range(m+1)]
    for i in range(m):
        for j in range(n):
            if X[i] == Y[j]:
                dp[i+1][j+1] = dp[i][j]+1
            else:
                dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j])
    return dp[m][n]
```

---
### 0/1 Knapsack
**Explanation:**  
Maximize value in a knapsack with limited capacity, each item can be taken or not.

**Use Case:**  
Resource allocation, investment portfolios.

**Code:**
```python
def knapsack(weights, values, W):
    n = len(weights)
    dp = [[0]*(W+1) for _ in range(n+1)]
    for i in range(1, n+1):
        for w in range(W+1):
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i-1][w], dp[i-1][w-weights[i-1]] + values[i-1])
            else:
                dp[i][w] = dp[i-1][w]
    return dp[n][W]
```

---
### Climbing Stairs
**Explanation:**  
Count ways to reach the top of stairs, taking 1 or 2 steps at a time.

**Use Case:**  
Game theory, combinatorial problems.

**Code:**
```python
def climb_stairs(n):
    a, b = 1, 1
    for _ in range(n):
        a, b = b, a + b
    return a
```

---
### Maximum Subarray
**Explanation:**  
Find the contiguous subarray with the maximum sum (Kadane's algorithm).

**Use Case:**  
Financial analysis, signal processing.

**Code:**
```python
def max_subarray(nums):
    curr = res = nums[0]
    for n in nums[1:]:
        curr = max(n, curr + n)
        res = max(res, curr)
    return res
```

---
### Coin Change Variations
**Explanation:**  
Find the minimum number of coins for a given amount.

**Use Case:**  
ATM machines, currency exchange.

**Code:**
```python
def coin_change(coins, amount):
    dp = [float('inf')] * (amount+1)
    dp[0] = 0
    for coin in coins:
        for x in range(coin, amount+1):
            dp[x] = min(dp[x], dp[x-coin]+1)
    return dp[amount] if dp[amount] != float('inf') else -1
```

---
### Edit Distance
**Explanation:**  
Minimum operations to convert one string into another.

**Use Case:**  
Spell checkers, DNA mutation analysis.

**Code:**
```python
def edit_distance(word1, word2):
    m, n = len(word1), len(word2)
    dp = [[0]*(n+1) for _ in range(m+1)]
    for i in range(m+1):
        for j in range(n+1):
            if i == 0:
                dp[i][j] = j
            elif j == 0:
                dp[i][j] = i
            elif word1[i-1] == word2[j-1]:
                dp[i][j] = dp[i-1][j-1]
            else:
                dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    return dp[m][n]
```

---
### **Advanced Dynamic Programming**

#### Longest Increasing Subsequence (LIS)
**Explanation:**  
Find the length of the longest increasing subsequence in an array.

**Use Case:**  
Stock price analysis, version control.

**Code (O(n log n)):**
```python
import bisect
def length_of_LIS(nums):
    sub = []
    for x in nums:
        i = bisect.bisect_left(sub, x)
        if i == len(sub):
            sub.append(x)
        else:
            sub[i] = x
    return len(sub)
```

#### Matrix Chain Multiplication
**Explanation:**  
Determine the most efficient way to multiply a given sequence of matrices.

**Use Case:**  
Query optimization in databases, graphics transformations.

**Code:**
```python
def matrix_chain_order(p):
    n = len(p) - 1
    dp = [[0]*n for _ in range(n)]
    for l in range(2, n+1):
        for i in range(n-l+1):
            j = i+l-1
            dp[i][j] = float('inf')
            for k in range(i, j):
                q = dp[i][k] + dp[k+1][j] + p[i]*p[k+1]*p[j+1]
                if q < dp[i][j]:
                    dp[i][j] = q
    return dp[0][n-1]
```

## Stack & Queue
### Valid Parentheses
**Explanation:**  
Check if the input string has valid opening and closing brackets.

**Use Case:**  
Expression parsing, code compilation.

**Code:**
```python
def is_valid(s):
    stack = []
    mapping = {')':'(', '}':'{', ']':'['}
    for char in s:
        if char in mapping.values():
            stack.append(char)
        elif char in mapping:
            if not stack or mapping[char] != stack.pop():
                return False
    return not stack
```

---
### Implement Queue Using Stacks
**Explanation:**  
Simulate a queue using two stacks.

**Use Case:**  
Job scheduling, asynchronous task queues.

**Code:**
```python
class MyQueue:
    def __init__(self):
        self.in_stack, self.out_stack = [], []
    def push(self, x):
        self.in_stack.append(x)
    def pop(self):
        self.peek()
        return self.out_stack.pop()
    def peek(self):
        if not self.out_stack:
            while self.in_stack:
                self.out_stack.append(self.in_stack.pop())
        return self.out_stack[-1]
    def empty(self):
        return not self.in_stack and not self.out_stack
```

---
### Min Stack Design
**Explanation:**  
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

**Use Case:**  
Stock span problems, undo operations.

**Code:**
```python
class MinStack:
    def __init__(self):
        self.stack, self.min_stack = [], []
    def push(self, x):
        self.stack.append(x)
        if not self.min_stack or x <= self.min_stack[-1]:
            self.min_stack.append(x)
    def pop(self):
        if self.stack.pop() == self.min_stack[-1]:
            self.min_stack.pop()
    def top(self):
        return self.stack[-1]
    def getMin(self):
        return self.min_stack[-1]
```

---
### Next Greater Element
**Explanation:**  
For each element, find the next greater element to its right.

**Use Case:**  
Stock price prediction, temperature forecasts.

**Code:**
```python
def next_greater(nums):
    res, stack = [-1]*len(nums), []
    for i, n in enumerate(nums):
        while stack and nums[stack[-1]] < n:
            res[stack.pop()] = n
        stack.append(i)
    return res
```

---
### Largest Rectangle in Histogram
**Explanation:**  
Find the area of the largest rectangle in a histogram.

**Use Case:**  
Image processing, skyline silhouette calculation.

**Code:**
```python
def largest_rectangle(heights):
    stack, max_area = [], 0
    heights.append(0)
    for i, h in enumerate(heights):
        while stack and heights[stack[-1]] > h:
            height = heights[stack.pop()]
            width = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, height * width)
        stack.append(i)
    return max_area
```

## Heap & Priority Queue
### Kth Largest Element
**Explanation:**  
Find the kth largest element using a min-heap.

**Use Case:**  
Leaderboard, streaming analytics.

**Code:**
```python
import heapq
def kth_largest(nums, k):
    return heapq.nlargest(k, nums)[-1]
```

---
### Merge K Sorted Lists
**Explanation:**  
Merge k sorted linked lists into one sorted list using a heap.

**Use Case:**  
Log file merging, external sorting.

**Code:**
```python
import heapq
def merge_k_lists(lists):
    heap = []
    for i, node in enumerate(lists):
        if node:
            heapq.heappush(heap, (node.val, i, node))
    dummy = curr = ListNode(0)
    while heap:
        val, i, node = heapq.heappop(heap)
        curr.next = node
        curr = curr.next
        if node.next:
            heapq.heappush(heap, (node.next.val, i, node.next))
    return dummy.next
```

---
### Top K Frequent Elements
**Explanation:**  
Find the k most frequent elements using a heap.

**Use Case:**  
Word frequency analysis, recommendation engines.

**Code:**
```python
from collections import Counter
import heapq
def top_k_frequent(nums, k):
    count = Counter(nums)
    return [item for item, _ in heapq.nlargest(k, count.items(), key=lambda x: x[1])]
```

---
### Median from Data Stream
**Explanation:**  
Maintain a running median using two heaps.

**Use Case:**  
Real-time analytics, financial tickers.

**Code:**
```python
import heapq
class MedianFinder:
    def __init__(self):
        self.small, self.large = [], []
    def addNum(self, num):
        heapq.heappush(self.small, -num)
        heapq.heappush(self.large, -heapq.heappop(self.small))
        if len(self.large) > len(self.small):
            heapq.heappush(self.small, -heapq.heappop(self.large))
    def findMedian(self):
        if len(self.small) > len(self.large):
            return -self.small[0]
        return (-self.small[0] + self.large[0]) / 2
```

## Hash Tables
### Design HashMap
**Explanation:**  
Implement a basic hash map with separate chaining.

**Use Case:**  
Database indexing, caching.

**Code:**
```python
class HashMap:
    def __init__(self, size=1000):
        self.size = size
        self.table = [[] for _ in range(size)]
    def put(self, key, value):
        h = hash(key) % self.size
        for i, (k, v) in enumerate(self.table[h]):
            if k == key:
                self.table[h][i] = (key, value)
                return
        self.table[h].append((key, value))
    def get(self, key):
        h = hash(key) % self.size
        for k, v in self.table[h]:
            if k == key:
                return v
        return None
    def remove(self, key):
        h = hash(key) % self.size
        self.table[h] = [(k, v) for k, v in self.table[h] if k != key]
```

---
### Group Anagrams
**Explanation:**  
Group strings that are anagrams of each other.

**Use Case:**  
Dictionary search, spell checkers.

**Code:**
```python
from collections import defaultdict
def group_anagrams(strs):
    anagrams = defaultdict(list)
    for s in strs:
        key = tuple(sorted(s))
        anagrams[key].append(s)
    return list(anagrams.values())
```

---
### First Unique Character
**Explanation:**  
Find the first non-repeating character in a string.

**Use Case:**  
Data validation, error detection.

**Code:**
```python
from collections import Counter
def first_unique(s):
    count = Counter(s)
    for i, c in enumerate(s):
        if count[c] == 1:
            return i
    return -1
```

---
### LRU Cache Implementation
*(See Linked Lists section for code and explanation)*

## Bit Manipulation
### Single Number
**Explanation:**  
Find the element that appears once when every other appears twice using XOR.

**Use Case:**  
Error detection in data streams.

**Code:**
```python
def single_number(nums):
    res = 0
    for n in nums:
        res ^= n
    return res
```

---
### Counting Bits
**Explanation:**  
Count the number of 1's for every number up to n.

**Use Case:**  
Hardware design, cryptography.

**Code:**
```python
def count_bits(n):
    res = [0] * (n+1)
    for i in range(1, n+1):
        res[i] = res[i >> 1] + (i & 1)
    return res
```

---
### Power of Two
**Explanation:**  
Check if a number is a power of two using bitwise operations.

**Use Case:**  
Memory allocation, performance optimization.

**Code:**
```python
def is_power_of_two(n):
    return n > 0 and (n & (n-1)) == 0
```

---
### Number of 1 Bits
**Explanation:**  
Count the number of 1 bits in an integer.

**Use Case:**  
Population count in cryptography.

**Code:**
```python
def hamming_weight(n):
    count = 0
    while n:
        n &= n - 1
        count += 1
    return count
```

## Advanced Topics
### Trie Implementation
**Explanation:**  
Prefix tree for fast retrieval of strings.

**Use Case:**  
Autocomplete, spell checking.

**Code:**
```python
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False
class Trie:
    def __init__(self):
        self.root = TrieNode()
    def insert(self, word):
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
        node.is_end = True
    def search(self, word):
        node = self.root
        for c in word:
            if c not in node.children:
                return False
            node = node.children[c]
        return node.is_end
```

---
### Union Find (Disjoint Set)
**Explanation:**  
Data structure to keep track of disjoint sets, supporting union and find.

**Use Case:**  
Network connectivity, Kruskal's algorithm.

**Code:**
```python
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    def union(self, x, y):
        self.parent[self.find(x)] = self.find(y)
```

---
### Segment Trees
**Explanation:**  
Efficiently perform range queries and updates.

**Use Case:**  
Range sum queries, interval problems.

**Code:**
```python
class SegmentTree:
    def __init__(self, data):
        self.n = len(data)
        self.tree = [0] * (2 * self.n)
        for i in range(self.n):
            self.tree[self.n + i] = data[i]
        for i in range(self.n - 1, 0, -1):
            self.tree[i] = self.tree[2 * i] + self.tree[2 * i + 1]
    def update(self, i, val):
        i += self.n
        self.tree[i] = val
        while i > 1:
            i //= 2
            self.tree[i] = self.tree[2 * i] + self.tree[2 * i + 1]
    def query(self, l, r):
        res = 0
        l += self.n
        r += self.n
        while l < r:
            if l % 2:
                res += self.tree[l]
                l += 1
            if r % 2:
                r -= 1
                res += self.tree[r]
            l //= 2
            r //= 2
        return res
```

---
### Red-Black Trees
**Explanation:**  
Self-balancing binary search tree with guaranteed O(log n) operations.

**Use Case:**  
Database indexing, memory management.

**Note:**  
Implementation is complex; use built-in libraries when available (e.g., `TreeMap` in Java).

---
### A* Pathfinding Algorithm
**Explanation:**  
Find the shortest path between nodes using heuristic-based search.

**Use Case:**  
Navigation, game AI.

**Code:**
```python
import heapq
def a_star(start, goal, h_func, neighbors_func):
    open_set = []
    heapq.heappush(open_set, (0 + h_func(start), 0, start, [start]))
    closed_set = set()
    while open_set:
        est_total, cost, node, path = heapq.heappop(open_set)
        if node == goal:
            return path
        if node in closed_set:
            continue
        closed_set.add(node)
        for neighbor, weight in neighbors_func(node):
            if neighbor not in closed_set:
                heapq.heappush(open_set, (cost + weight + h_func(neighbor), cost + weight, neighbor, path + [neighbor]))
    return None
```

---
### **Advanced Data Structures**

#### Fenwick Tree (Binary Indexed Tree)
**Explanation:**  
Efficiently compute prefix sums and updates in logarithmic time.

**Use Case:**  
Inversion count, cumulative frequency tables.

**Code:**
```python
class FenwickTree:
    def __init__(self, size):
        self.tree = [0] * (size + 1)
    def update(self, i, delta):
        while i < len(self.tree):
            self.tree[i] += delta
            i += i & -i
    def query(self, i):
        res = 0
        while i > 0:
            res += self.tree[i]
            i -= i & -i
        return res
```

#### AVL Tree
**Explanation:**  
Self-balancing binary search tree with strict height balancing for O(log n) operations.

**Use Case:**  
Databases, memory management, indexing.

**Note:**  
AVL trees require rotations for balancing after insertions/deletions. Full implementation is lengthy; use libraries if possible.

## System Design Concepts
### Time & Space Complexity Analysis
**Explanation:**  
Analyze how algorithm performance scales with input size.

**Use Case:**  
Scalability planning, resource estimation.

---
### Big O Notation
**Explanation:**  
Describes the upper bound of an algorithm's growth rate.

**Use Case:**  
Comparing algorithm efficiency.

---
### Memory Management
**Explanation:**  
How memory is allocated, used, and released.

**Use Case:**  
Garbage collection, memory leaks prevention.

---
### Threading and Concurrency Basics
**Explanation:**  
Parallel execution of code for performance improvement.

**Use Case:**  
Web servers, real-time applications.

---
### Database Indexing Fundamentals
**Explanation:**  
Techniques to speed up data retrieval from databases.

**Use Case:**  
Query optimization, search engines.

## Problem-Solving Patterns
### Two Pointers
**Explanation:**  
Use two pointers to traverse or partition data efficiently.

**Use Case:**  
Palindrome checking, sorted array problems.

---
### Sliding Window
**Explanation:**  
Maintain a window over a subset of data for efficient subarray/substring problems.

**Use Case:**  
Maximum sum subarrays, substring search.

---
### Fast & Slow Pointers
**Explanation:**  
Move pointers at different speeds to detect cycles or find midpoints.

**Use Case:**  
Cycle detection, linked list midpoint.

---
### Merge Intervals
**Explanation:**  
Combine overlapping intervals into one.

**Use Case:**  
Calendar management, event scheduling.

---
### Cyclic Sort
**Explanation:**  
Place elements in their correct indices in O(n) time when the range is known.

**Use Case:**  
Finding missing numbers, sorting small ranges.

---
### In-place Reversal of Linked List
**Explanation:**  
Reverse a linked list without extra space.

**Use Case:**  
Data structure transformations.

---
### Tree BFS/DFS
**Explanation:**  
Traverse trees using breadth-first or depth-first strategies.

**Use Case:**  
Parsing, searching, serialization.

---
### Two Heaps
**Explanation:**  
Maintain two heaps for median finding or balancing data.

**Use Case:**  
Running median, load balancing.

---
### Subsets
**Explanation:**  
Generate all possible subsets of a set.

**Use Case:**  
Combinatorial problems, power set generation.

---
### Top K Elements
**Explanation:**  
Find the k largest or smallest elements efficiently.

**Use Case:**  
Leaderboards, recommendation systems.

---
### K-way Merge
**Explanation:**  
Merge k sorted lists or arrays efficiently.

**Use Case:**  
External sorting, merging log files.

---
### Topological Sort
**Explanation:**  
Order tasks given dependencies.

**Use Case:**  
Course scheduling, build systems.