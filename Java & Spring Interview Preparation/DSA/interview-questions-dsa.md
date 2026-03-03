# Data Structures and Algorithms (DSA) Interview Questions

This document covers essential DSA concepts and common interview questions with a focus on their implementations in Java.

## 1. Array and Strings

### Key Concepts
- Contiguous memory allocation.
- `String` immutability in Java vs `StringBuilder` / `StringBuffer`.
- Prefix sums, Sliding Window, and Two Pointers techniques.

### Common Questions
1. **Two Sum**
   - *Problem*: Find two numbers in an array that add up to a specific target.
   - *Approach*: Use a `HashMap` to store seen numbers and their indices. Time complexity $O(N)$.
2. **Best Time to Buy and Sell Stock**
   - *Problem*: Find the maximum profit from buying and selling a stock once.
   - *Approach*: Keep track of the minimum price seen so far and calculate max profit at each step.
3. **Valid Palindrome**
   - *Problem*: Check if a string is a palindrome, ignoring non-alphanumeric characters and case.
   - *Approach*: Two pointers (start and end), skip non-alphanumeric characters, compare.

## 2. Linked Lists

### Key Concepts
- Singly vs Doubly Linked Lists.
- Sentinel/Dummy nodes to simplify edge cases.
- Fast and Slow pointers (Tortoise and Hare algorithm).

### Common Questions
1. **Reverse Linked List**
   - *Problem*: Reverse a singly linked list.
   - *Approach*: Iteratively track `prev`, `curr`, and `next` nodes.
2. **Merge Two Sorted Lists**
   - *Problem*: Merge two sorted linked lists into one.
   - *Approach*: Dummy node, iterate while both lists are not null, appending the smaller value.
3. **Linked List Cycle**
   - *Problem*: Detect if a cycle exists in a linked list.
   - *Approach*: Fast and slow pointers. If they meet, there is a cycle.

## 3. Stacks and Queues

### Key Concepts
- LIFO (Stack) vs FIFO (Queue).
- Java Implementations: `ArrayDeque` (preferred), `Stack` (legacy), `LinkedList`.
- Monotonic stacks for next greater element problems.

### Common Questions
1. **Valid Parentheses**
   - *Problem*: Check if a string of brackets is valid.
   - *Approach*: Use a stack to push expected closing brackets when an opening bracket is seen.
2. **Implement Queue using Stacks**
   - *Problem*: Create a queue using two stacks.
   - *Approach*: Push to stack 1. For pop/peek, dump stack 1 to stack 2 if stack 2 is empty, then pop from stack 2.
3. **Daily Temperatures**
   - *Problem*: Find how many days to wait for a warmer temperature.
   - *Approach*: Monotonic decreasing stack storing indices.

## 4. Hash Tables

### Key Concepts
- `HashMap`, `HashSet` in Java.
- Handling collisions, Load factor, and Capacity.
- $O(1)$ average time complexity for insert, delete, search.

### Common Questions
1. **Contains Duplicate**
   - *Problem*: Check if an array contains any duplicates.
   - *Approach*: Add elements to a `HashSet`. Return true if `add()` returns false.
2. **Valid Anagram**
   - *Problem*: Check if two strings are anagrams.
   - *Approach*: Character frequency array of size 26 or a `HashMap`.
3. **Group Anagrams**
   - *Problem*: Group an array of strings into sets of anagrams.
   - *Approach*: Sort each string to use as a key in a `HashMap`, mapping to a list of original strings.

## 5. Trees and Graphs

### Key Concepts
- Binary Trees, Binary Search Trees (BST).
- Traversals: Inorder (sorted for BST), Preorder, Postorder, Level-order (BFS).
- Graph representations: Adjacency List vs Adjacency Matrix.
- Algorithms: DFS, BFS, Dijkstra's, Topological Sort.

### Common Questions
1. **Maximum Depth of Binary Tree**
   - *Problem*: Find the max depth of a binary tree.
   - *Approach*: Recursive `1 + Math.max(leftDepth, rightDepth)`.
2. **Invert Binary Tree**
   - *Problem*: Swap left and right children for all nodes.
   - *Approach*: Recursive postorder or preorder traversal, swapping children.
3. **Number of Islands**
   - *Problem*: Count the number of islands (1s) in a 2D grid.
   - *Approach*: Iterate through the grid. When a '1' is found, increment count and DFS/BFS to mark the entire island as visited ('0').
4. **Lowest Common Ancestor of a BST**
   - *Problem*: Find the lowest common ancestor of two nodes.
   - *Approach*: Leverage BST property. If both values are less than root, go left. If both are greater, go right. Else, root is LCA.

## 6. Binary Search

### Key Concepts
- Finding an element in a sorted array in $O(\log N)$ time.
- Handling arrays with duplicates, shifted arrays.
- Calculating mid: `start + (end - start) / 2` to avoid overflow.

### Common Questions
1. **Binary Search**
   - *Problem*: Standard binary search implementation.
2. **Search in Rotated Sorted Array**
   - *Problem*: Search in a sorted array that has been rotated.
   - *Approach*: Determine which half is properly sorted, then check if the target lies in that range.
3. **Find Minimum in Rotated Sorted Array**
   - *Problem*: Find the min element.
   - *Approach*: Look for the inflection point using binary search.

## 7. Dynamic Programming

### Key Concepts
- Overlapping subproblems and Optimal substructure.
- Memoization (Top-down) vs Tabulation (Bottom-up).

### Common Questions
1. **Climbing Stairs**
   - *Problem*: Number of ways to climb $n$ stairs (1 or 2 steps at a time).
   - *Approach*: Fibonacci sequence, $dp[i] = dp[i-1] + dp[i-2]$.
2. **Coin Change**
   - *Problem*: Fewest number of coins to make up an amount.
   - *Approach*: Unbounded knapsack problem. $dp[i] = \min(dp[i], dp[i-coin] + 1)$.
3. **Longest Increasing Subsequence**
   - *Problem*: Length of the longest strictly increasing subsequence.
   - *Approach*: DP array where $dp[i]$ is the LIS ending at $i$. Time $O(N^2)$ (or $O(N \log N)$ with binary search).

## 8. General Tips for Java in Interviews
- Use `Arrays.sort()` and `Collections.sort()`.
- Know `PriorityQueue` (Min-Heap by default). Max-Heap: `new PriorityQueue<>(Collections.reverseOrder())`.
- For string manipulation, favor `StringBuilder` to avoid creating multiple string objects.
- Be comfortable with Java 8 Streams for concise map/filter/reduce operations if allowed.
