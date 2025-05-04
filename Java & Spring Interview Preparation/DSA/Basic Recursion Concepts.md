# 01. Basic Recursion Concepts (DSA Focus)

## 🔍 Overview
Recursion is a programming technique where a method calls itself to solve a smaller instance of the same problem. It is a core concept in data structures and algorithms (DSA), often used in problems involving divide-and-conquer, backtracking, and dynamic programming.

Understanding recursion is vital for mastering problems involving:
- Tree traversal
- Graph traversal
- Combinatorics (e.g., permutations, combinations)
- Searching and sorting algorithms (e.g., binary search, quicksort, mergesort)

---

## 📌 Key Characteristics
- **Base Case:** Terminates recursion to prevent infinite calls.
- **Recursive Case:** Calls itself with a reduced subproblem.

```java
// Basic recursive structure
void recursiveFunction(int n) {
    if (n == 0) return; // base case
    recursiveFunction(n - 1); // recursive case
}
```

---

## 🔁 Recursion Flow Example
```java
void printCountdown(int n) {
    if (n == 0) {
        System.out.println("Blast off!");
        return;
    }
    System.out.println(n);
    printCountdown(n - 1);
}
```

**Output:**
```
5
4
3
2
1
Blast off!
```

### 🔄 Call Stack Visualization (for `printCountdown(3)`)
1. `printCountdown(3)` → prints 3
2. `printCountdown(2)` → prints 2
3. `printCountdown(1)` → prints 1
4. `printCountdown(0)` → prints "Blast off!"

---

## 🧠 Use Cases in DSA
| Area | Recursive Applications |
|------|------------------------|
| Arrays | Binary Search, Divide & Conquer |
| Strings | Palindromes, Reversals |
| Trees | Preorder, Inorder, Postorder Traversals |
| Graphs | DFS, Connectivity Checks |
| Backtracking | Sudoku Solver, N-Queens Problem |
| Dynamic Programming | Fibonacci, Knapsack |

---

## 📘 Classic Examples

### 1. Factorial (n!)
```java
int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
```
**Time Complexity:** O(n)  
**Space Complexity:** O(n) (due to call stack)

### 2. Fibonacci Numbers
```java
int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```
**Time Complexity:** O(2^n) – very inefficient without memoization

---

## ⚠️ Common Mistakes
- Missing base case → leads to stack overflow
- Multiple recursive calls with overlapping subproblems → inefficiency (optimize with memoization or DP)
- Overuse in simple problems where iteration is more efficient

---

## 🔧 Optimizations
- **Tail Recursion**: Compiler optimization when recursive call is the last operation
- **Memoization**: Store results to avoid redundant calls
- **Dynamic Programming**: Convert recursion to bottom-up iteration

---

## 📊 Comparison: Recursion vs Iteration
| Feature | Recursion | Iteration |
|--------|----------|----------|
| Readability | ✅ Cleaner for divide-and-conquer | ❌ Can be verbose |
| Performance | ❌ Slower (due to stack calls) | ✅ Faster |
| Memory Usage | ❌ Call stack usage | ✅ Minimal |
| Debugging | ❌ Harder | ✅ Easier |

---

## ✅ Advanced Interview Tips

### Explanation Strategies
- Begin by stating the problem-solving approach: "I'll solve this using recursion because..."
- Always identify and explain both base case and recursive case with clear reasoning
- Write the recurrence relation in mathematical form (e.g., T(n) = T(n-1) + O(1) for linear recursion)
- Draw the recursion tree when discussing complex recursive solutions
- Demonstrate call stack visualization for at least one test case

### Code Quality
- Use meaningful variable/method names that reflect recursive intent
- Write clean, modular recursive code with helper methods when appropriate
- Consider parameter ordering: place changing parameters first, fixed parameters last
- Add proper documentation commenting what each parameter represents

### Optimization Techniques
- Identify when to use memoization (top-down) vs tabulation (bottom-up)
- Know how to convert between recursive and iterative approaches
- Explain tradeoffs between recursive and iterative approaches for the specific problem
- Identify tail recursion opportunities and explain why they're beneficial
- Demonstrate space optimization techniques (e.g., using constant extra space)

### Advanced Concepts
- Understand mutual recursion (when two+ functions call each other)
- Identify and solve overlapping subproblems
- Recognize recursive backtracking patterns
- Know recursive vs. divide-and-conquer approaches

---

## ❓ Practice Problems by Category

### Basic Recursion
| # | Problem | Description | Link | Difficulty |
|---|---------|-------------|------|------------|
| 1 | Factorial | Calculate n! | [LeetCode](https://leetcode.com/problems/factorial-trailing-zeroes/) | Easy |
| 2 | Fibonacci | Generate nth Fibonacci number | [LeetCode](https://leetcode.com/problems/fibonacci-number/) | Easy |
| 3 | Power Function | Implement pow(x, n) | [LeetCode](https://leetcode.com/problems/powx-n/) | Medium |

### String & Array Recursion
| # | Problem | Description | Link | Difficulty |
|---|---------|-------------|------|------------|
| 4 | Reverse String | Reverse a string using recursion | [LeetCode](https://leetcode.com/problems/reverse-string/) | Easy |
| 5 | Palindrome Check | Check if string is palindrome | [LeetCode](https://leetcode.com/problems/valid-palindrome/) | Easy |
| 6 | Binary Search | Implement binary search recursively | [LeetCode](https://leetcode.com/problems/binary-search/) | Easy |
| 7 | K-th Symbol in Grammar | Recursive pattern recognition | [LeetCode](https://leetcode.com/problems/k-th-symbol-in-grammar/) | Medium |

### Tree & Graph Recursion
| # | Problem | Description | Link | Difficulty |
|---|---------|-------------|------|------------|
| 8 | Maximum Depth of Binary Tree | Find tree height | [LeetCode](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Easy |
| 9 | Same Tree | Check if two trees are identical | [LeetCode](https://leetcode.com/problems/same-tree/) | Easy |
| 10 | Path Sum | Find if path with given sum exists | [LeetCode](https://leetcode.com/problems/path-sum/) | Easy |
| 11 | Flood Fill | DFS coloring algorithm | [LeetCode](https://leetcode.com/problems/flood-fill/) | Easy |

### Combinatorial Recursion
| # | Problem | Description | Link | Difficulty |
|---|---------|-------------|------|------------|
| 12 | Subsets | Generate all possible subsets | [LeetCode](https://leetcode.com/problems/subsets/) | Medium |
| 13 | Permutations | Generate all possible permutations | [LeetCode](https://leetcode.com/problems/permutations/) | Medium |
| 14 | Combinations | Generate all combinations of k elements | [LeetCode](https://leetcode.com/problems/combinations/) | Medium |
| 15 | Letter Combinations of Phone Number | Generate letter combinations | [LeetCode](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | Medium |

### Advanced Recursion & Backtracking
| # | Problem | Description | Link | Difficulty |
|---|---------|-------------|------|------------|
| 16 | N-Queens | Classic backtracking problem | [LeetCode](https://leetcode.com/problems/n-queens/) | Hard |
| 17 | Sudoku Solver | Solve a 9x9 Sudoku board | [LeetCode](https://leetcode.com/problems/sudoku-solver/) | Hard |
| 18 | Word Search | Find word in 2D board | [LeetCode](https://leetcode.com/problems/word-search/) | Medium |
| 19 | Generate Parentheses | Generate valid parentheses combinations | [LeetCode](https://leetcode.com/problems/generate-parentheses/) | Medium |
| 20 | Regular Expression Matching | Pattern matching with recursion | [LeetCode](https://leetcode.com/problems/regular-expression-matching/) | Hard |

---

## 🔍 Common Pitfalls and Solutions

### Stack Overflow Issues
- **Problem**: Excessive recursion depth
- **Solution**: Ensure base case is reachable; consider iteration for deep recursion

### Performance Issues
- **Problem**: Exponential time complexity with naive recursion
- **Solution**: Apply memoization to cache results of subproblems

### Memory Management
- **Problem**: Excessive memory usage in recursive calls
- **Solution**: Use tail recursion; consider iterative approaches for memory-intensive operations

### Infinite Recursion
- **Problem**: Recursion never terminates
- **Solution**: Verify base case logic; ensure recursive calls move toward base case

### Parameter Management
- **Problem**: Incorrect state management between calls
- **Solution**: Use immutable parameters or clearly document state changes

---

## 🎯 Interview Q&A

### Conceptual Questions

**Q1: How would you explain recursion to a non-technical person?**
A: Recursion is like looking at yourself between two mirrors - you see smaller versions of yourself repeating infinitely. In programming, it's solving a problem by breaking it down into smaller versions of the same problem until reaching simple cases with known solutions. For example, to find your way out of a maze, you try one path, and if it leads to a dead end, you go back and try another path - this "try, backtrack, and try again" approach is recursive.

**Q2: What's the difference between direct and indirect recursion?**
A: Direct recursion is when a method calls itself directly. Indirect recursion (or mutual recursion) is when method A calls method B, which then calls method A again, creating a cycle. For example:

```java
// Direct recursion
void methodA(int n) {
    if (n <= 0) return;
    methodA(n-1);
}

// Indirect recursion
void methodA(int n) {
    if (n <= 0) return;
    methodB(n-1);
}

void methodB(int n) {
    if (n <= 0) return;
    methodA(n-1);
}
```

**Q3: Why is recursion sometimes less efficient than iteration?**
A: Each recursive call adds a new frame to the call stack, consuming memory and adding overhead for function calls. This includes storing local variables, parameters, and return address information. Iteration typically uses constant memory regardless of input size. Additionally, the function call overhead in recursion can be significant compared to the simple increment of a counter in iteration.

**Q4: When would you prefer recursion over iteration?**
A: I prefer recursion when:
- The problem naturally breaks down into recursive subproblems (trees, graphs)
- The solution is cleaner and more intuitive with recursion
- The problem involves backtracking
- The depth of recursion will be limited
- Code readability is more important than minor performance gains
- When working with hierarchical data structures

**Q5: How can you convert a recursive solution to an iterative one?**
A: The general approach is to:
1. Create an explicit stack to replace the call stack
2. Push initial parameters onto the stack
3. Use a loop to process stack items
4. For each iteration, pop values and process them
5. Push any recursive calls' parameters onto the stack instead of calling the function

Example - converting a recursive factorial:
```java
// Recursive
int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n-1);
}

// Iterative
int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

### Coding Questions

**Q6: How would you implement a recursive binary search?**
A:
```java
public int binarySearch(int[] arr, int target, int left, int right) {
    if (left > right) return -1; // Base case: element not found
    
    int mid = left + (right - left) / 2;
    
    if (arr[mid] == target) {
        return mid; // Base case: element found
    } else if (arr[mid] > target) {
        return binarySearch(arr, target, left, mid - 1); // Search left half
    } else {
        return binarySearch(arr, target, mid + 1, right); // Search right half
    }
}
```

**Q7: How would you calculate the height of a binary tree recursively?**
A:
```java
public int maxDepth(TreeNode root) {
    // Base case: empty tree has height 0
    if (root == null) return 0;
    
    // Recursive case: height is 1 + max of left and right subtree heights
    int leftHeight = maxDepth(root.left);
    int rightHeight = maxDepth(root.right);
    
    return 1 + Math.max(leftHeight, rightHeight);
}
```

**Q8: Implement a recursive solution to count all possible paths from top-left to bottom-right in an m×n grid, moving only right or down.**
A:
```java
public int countPaths(int m, int n) {
    // Base cases
    if (m == 1 || n == 1) return 1;
    
    // Recursive case: sum of paths from cell above and cell to the left
    return countPaths(m-1, n) + countPaths(m, n-1);
}
```

**Q9: How would you optimize the recursive Fibonacci function using memoization?**
A:
```java
public int fibonacci(int n, Map<Integer, Integer> memo) {
    // Base cases
    if (n <= 1) return n;
    
    // Check if already calculated
    if (memo.containsKey(n)) {
        return memo.get(n);
    }
    
    // Calculate and store result
    int result = fibonacci(n-1, memo) + fibonacci(n-2, memo);
    memo.put(n, result);
    
    return result;
}

// Usage
public int fib(int n) {
    return fibonacci(n, new HashMap<>());
}
```

---

## 📚 Advanced Resources

### Books & Academic Papers
- 📘 "Introduction to Algorithms" by CLRS - Chapter on Recursion and Divide-and-Conquer
- 📘 "Algorithms" by Robert Sedgewick - In-depth coverage of recursive algorithms
- 📘 "The Art of Computer Programming, Vol. 1" by Donald Knuth - Mathematical foundation of recursion
- 📄 "Recursion to Iteration: Towards a Transformation System" - Academic paper on conversion techniques

### Online Courses
- 🔷 [Stanford CS106B - Programming Abstractions](https://www.edx.org/course/programming-abstractions) - Excellent recursion modules
- 🔷 [MIT 6.006 Introduction to Algorithms](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/) - Recursion fundamentals
- 🔷 [Princeton Algorithms, Part I & II](https://www.coursera.org/learn/algorithms-part1) - Covers recursive algorithms and analysis

### Interactive Practice
- 🌐 [Visualgo.net](https://visualgo.net/) - Recursive algorithm visualizations
- 🌐 [Recursion Visualization Tool](https://recursion.now.sh/) - Interactive call stack visualization
- 🌐 [HackerRank Recursion Track](https://www.hackerrank.com/domains/algorithms?filters%5Bsubdomains%5D%5B%5D=recursion) - Specialized practice problems

### Advanced Blogs & Tutorials
- 📘 [Functional Programming & Recursion](https://www.cs.cornell.edu/courses/cs3110/2019sp/textbook/hop/recursion.html) - Cornell CS course materials
- 📘 [Tail Call Optimization in JVM](https://www.baeldung.com/java-tail-recursion) - Advanced article on TCO
- 📘 [Stanford CS Recursive Backtracking](https://see.stanford.edu/materials/icspacs106b/H19-RecBacktrackExamples.pdf) - In-depth backtracking patterns

### Video Tutorials
- 📺 [MIT OCW Recursion Lecture](https://www.youtube.com/watch?v=WPSeyjX1-4s) - Academic deep-dive
- 📺 [Recursive Thinking](https://www.youtube.com/watch?v=B0NtAFf4bvU) - Conceptual approach to thinking recursively
- 📺 [Advanced Recursion Patterns](https://www.youtube.com/watch?v=aPQY__2H3tE) - Beyond basic recursion

---

> 🔗 Next: [02_Tail_Recursion_and_Optimization.md](./02_Tail_Recursion_and_Optimization.md)