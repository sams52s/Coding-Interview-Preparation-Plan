# Blind 75 & Extended Problem Mapping

Navigation: [Main README](README.md) | [Project Architecture](PROJECT_ARCHITECTURE.md) | [Daily Problem Log](DAILY_PROBLEM_LOG.md) | [DSA Question Bank](Question%20Bank/dsa.md)

This document maps the **most frequently asked interview problems** to topics, difficulty, and company frequency. Use this as your curated problem list for 5-week prep.

---

## Overview: The 75 Most Important Problems

**Why 75?** These problems cover 80% of coding interview patterns and are repeatedly asked at top companies. Mastering these will prepare you for 90%+ of actual interviews.

**How to Use:**
1. Group by difficulty: Easy (Week 1-2) → Medium (Week 2-3) → Hard (Week 4-5)
2. Group by pattern: Arrays → Strings → Trees → Graphs → DP
3. Sort by company frequency: Facebook → Google → Amazon → Microsoft
4. Track completion in [Daily Problem Log](DAILY_PROBLEM_LOG.md)

---

## 📊 Problem Statistics

| Difficulty | Count | Recommended Time | Companies Most Asked |
|------------|-------|-----------------|----------------------|
| **Easy** | 20 | Week 1-2 (4-5 days) | All (warm-up) |
| **Medium** | 40 | Week 2-4 (12-15 days) | All (main round) |
| **Hard** | 15 | Week 4-5 (5-7 days) | Google, Microsoft, Amazon |
| **Total** | **75** | **21-27 days** | Focus on top 3 companies |

---

## EASY Problems (20) — Foundation Week

### Arrays & Strings (6 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Time | Space |
|---|---------|---------------|-----------|---------|------|-------|
| 1 | Two Sum | LC 1 | FB, G, A | Hash Map | O(n) | O(n) |
| 2 | Valid Parentheses | LC 20 | FB, MS, G | Stack | O(n) | O(n) |
| 3 | Best Time to Buy Stock | LC 121 | A, MS, FB | DP | O(n) | O(1) |
| 4 | Reverse String | LC 344 | FB, A, G | Two Pointer | O(n) | O(1) |
| 5 | Merge Sorted Array | LC 88 | All | Two Pointer | O(n+m) | O(1) |
| 6 | N-th Fibonacci | LC 509 | A, G, MS | DP | O(n) | O(1) |

**Key Takeaway:** Master hash maps, stacks, two pointers, and simple DP.

### Linked List (3 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Time | Space |
|---|---------|---------------|-----------|---------|------|-------|
| 7 | Reverse Linked List | LC 206 | All | Linked List | O(n) | O(1) |
| 8 | Merge Two Sorted Lists | LC 21 | FB, A, MS | Linked List | O(n+m) | O(1) |
| 9 | Linked List Cycle | LC 141 | FB, G, A | Floyd's Cycle | O(n) | O(1) |

**Key Takeaway:** Master pointer manipulation, dummy nodes, and slow-fast pointers.

### Trees & Graphs (5 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Time | Space |
|---|---------|---------------|-----------|---------|------|-------|
| 10 | Binary Tree Level Order | LC 102 | All | BFS | O(n) | O(w) |
| 11 | Symmetric Tree | LC 101 | FB, MS, G | DFS/Recursion | O(n) | O(h) |
| 12 | Valid Binary Search Tree | LC 98 | All | DFS/Inorder | O(n) | O(h) |
| 13 | Number of Islands | LC 200 | FB, G, A | DFS/Union-Find | O(n*m) | O(n*m) |
| 14 | Invert Binary Tree | LC 226 | FB, A, MS | DFS | O(n) | O(h) |

**Key Takeaway:** Master BFS, DFS, inorder traversal, and connected components.

### Dynamic Programming (3 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Time | Space |
|---|---------|---------------|-----------|---------|------|-------|
| 15 | Climbing Stairs | LC 70 | A, FB, G | DP | O(n) | O(1) |
| 16 | Max Subarray | LC 53 | All | Kadane's | O(n) | O(1) |
| 17 | House Robber | LC 198 | FB, MS, A | DP | O(n) | O(1) |

**Key Takeaway:** Recognize DP patterns (state transition, memoization vs tabulation).

### Binary Search (3 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Time | Space |
|---|---------|---------------|-----------|---------|------|-------|
| 18 | Binary Search | LC 704 | All | Binary Search | O(log n) | O(1) |
| 19 | Search in Rotated Array | LC 33 | All | Binary Search | O(log n) | O(1) |
| 20 | First Bad Version | LC 278 | FB, G, A | Binary Search | O(log n) | O(1) |

**Key Takeaway:** Master binary search boundaries (left, mid, right logic).

---

## MEDIUM Problems (40) — Core Weeks

### Arrays & Strings (8 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Difficulty | Key Idea |
|---|---------|---------------|-----------|---------|------------|----------|
| 21 | 3Sum | LC 15 | FB, G, A | Two Pointer | M | Sort + 2Sum pattern |
| 22 | Container With Most Water | LC 11 | Microsoft | Two Pointer | M | Greedy pointers |
| 23 | Longest Substring Without Repeating | LC 3 | FB, G, A | Sliding Window | M | Window contraction |
| 24 | Longest Palindromic Substring | LC 5 | Microsoft | Expand Center | M | Two approaches |
| 25 | Group Anagrams | LC 49 | FB, G, A | Hash Map | M | Sorted key trick |
| 26 | Word Ladder | LC 127 | Facebook | BFS | M | 2-end BFS |
| 27 | Product of Array Except Self | LC 238 | Facebook | Prefix/Suffix | M | Two-pass solution |
| 28 | Increasing Triplet Subsequence | LC 334 | Airbnb | Greedy | M | Track min, mid |

### Linked List (4 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Difficulty | Key Idea |
|---|---------|---------------|-----------|---------|------------|----------|
| 29 | Add Two Numbers | LC 2 | All | Linked List | M | Carry logic |
| 30 | Remove Nth Node From End | LC 19 | FB, G, A | Two Pointer | M | Dummy + slow-fast |
| 31 | LRU Cache | LC 146 | Google, A, FB | HashMap + Doubly LL | M | Design pattern |
| 32 | Reorder List | LC 143 | FB, MS | Linked List | M | Reverse + merge |

### Trees & Graphs (10 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Difficulty | Key Idea |
|---|---------|---------------|-----------|---------|------------|----------|
| 33 | Binary Tree Max Path Sum | LC 124 | Google, Facebook | DFS | H | Return vs post-order |
| 34 | Serialize & Deserialize BST | LC 449 | Microsoft, Google | DFS | M | Pre-order + regex |
| 35 | Lowest Common Ancestor | LC 236 | Facebook, Google | DFS | M | Recursive properties |
| 36 | Binary Tree Right View | LC 199 | Microsoft | BFS/DFS | M | Level order trick |
| 37 | Word Ladder II | LC 126 | Google, Facebook | BFS + DFS | H | Backtracking |
| 38 | Alien Dictionary | LC 269 | Google | Topological Sort | H | Trie + DFS |
| 39 | Clone Graph | LC 133 | Facebook, Google | DFS/BFS | M | Visited + map |
| 40 | Course Schedule | LC 207 | Google, Facebook | Topological | M | Kahn's algorithm |
| 41 | Course Schedule II | LC 210 | Facebook, Google | Topological | M | DFS coloring |
| 42 | Pacific Atlantic Water | LC 417 | Microsoft, Google | DFS | M | Reverse thinking |

### Dynamic Programming (10 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Difficulty | Key Idea |
|---|---------|---------------|-----------|---------|------------|----------|
| 43 | Coin Change | LC 322 | Google, Facebook | DP | M | Bottom-up DP |
| 44 | Coin Change II | LC 518 | Facebook, Amazon | DP | M | Unbounded knapsack |
| 45 | Longest Increasing Subsequence | LC 300 | Google, Facebook | DP + Binary | M | LIS with optimization |
| 46 | Edit Distance | LC 72 | Google, Facebook | 2D DP | M | Levenshtein distance |
| 47 | Decode Ways | LC 91 | Amazon, Google | DP | M | String DP |
| 48 | Unique Paths | LC 62 | Amazon, Microsoft | DP | M | Grid DP |
| 49 | Jump Game | LC 55 | Google, Facebook | Greedy/DP | M | Reachability |
| 50 | Word Break | LC 139 | Google, Facebook | DP + Trie | M | Memoization |
| 51 | Minimum Window Substring | LC 76 | Google, Facebook | Sliding Window | H | Window optimization |
| 52 | Longest Repeating Character | LC 424 | Google, Facebook | Sliding Window | M | Greedy window |

### Binary Search & Other Patterns (8 problems)

| # | Problem | LeetCode Link | Companies | Pattern | Difficulty | Key Idea |
|---|---------|---------------|-----------|---------|------------|----------|
| 53 | Search in Rotated Array II | LC 81 | Microsoft, Google | Binary Search | M | Handle duplicates |
| 54 | Find First & Last Position | LC 34 | Google, Facebook | Binary Search | M | Two binary searches |
| 55 | Median in Sorted Arrays | LC 4 | Google, Microsoft | Binary Search | H | Partition logic |
| 56 | Sort List | LC 148 | Facebook, Google | Merge Sort | M | Linked list sort |
| 57 | Merge k Sorted Lists | LC 23 | Google, Facebook, A | Heap/Divide-Conquer | H | Min-heap |
| 58 | Kth Largest Element | LC 215 | Google, Facebook | Quickselect/Heap | M | Quickselect faster |
| 59 | Top K Frequent | LC 347 | Google, Facebook | Heap + Hash | M | Min-heap size k |
| 60 | Occupied Seats | LC 1478 | Facebook | Custom | M | Distance trick |

---

## HARD Problems (15) — Advanced Week

### Advanced DP & Graph Problems (8)

| # | Problem | LeetCode Link | Companies | Pattern | Key Idea |
|---|---------|---------------|-----------|---------|----------|
| 61 | Regular Expression Matching | LC 10 | Google, Facebook | 2D DP | State machine |
| 62 | Wildcard Matching | LC 44 | Google | 2D DP + Optimization | Greedy matching |
| 63 | Backstroke in Trie | LC 212 | Google, Facebook | Trie + DFS | Pruning |
| 64 | Trapping Rain Water | LC 42 | Google, Facebook | Stack/DP | Two pointers |
| 65 | Largest Rectangle in Histogram | LC 84 | Google, Microsoft | Monotonic Stack | Index tracking |
| 66 | Maximal Rectangle | LC 85 | Google, Microsoft | Histogram + DP | 2D compression |
| 67 | Distinct Subsequences | LC 115 | Google | 2D DP | String DP |
| 68 | Burst Balloons | LC 312 | Google | DP Interval | Reverse thinking |

### System Design Interview Problems (5)

| # | Problem | LeetCode Link | Companies | Pattern | Key Idea |
|---|---------|---------------|-----------|---------|----------|
| 69 | LRU Cache | LC 146 | Google, Facebook, A | Design | HashMap + LL |
| 70 | Design File System | LC 1166 | Google | Trie Design | Path parsing |
| 71 | Design In-Memory File System | LC 588 | Google | Trie + FS | Directory tree |
| 72 | Design Search Autocomplete | LC 642 | Google, Facebook | Trie + Design | Prefix search |
| 73 | Design Snake Game | LC 353 | Facebook | Design | Queue-based |

### Advanced Graph & Math (2)

| # | Problem | LeetCode Link | Companies | Pattern | Key Idea |
|---|---------|---------------|-----------|---------|----------|
| 74 | Dungeon Game | LC 174 | Google, Facebook | DP + Reverse | Backward DP |
| 75 | Skyline Problem | LC 218 | Google, Facebook | Sweep Line | Priority queue |

---

## 🎯 Study Plan by Week

### Week 1-2: Easy Foundation (20 problems)
**Goal:** Master basic data structures and patterns

```
Day 1-2: Arrays & Strings (1-6)
  - Two Sum, Valid Parentheses, Best Time to Buy Stock
  - Reverse String, Merge Array, Fibonacci
  
Day 3: Linked Lists (7-9)
  - Reverse LL, Merge 2 Sorted, Cycle detection
  
Day 4-5: Trees (10-14)
  - Level order, Symmetric, Valid BST, Islands, Invert
  
Day 6: DP & Binary Search (15-20)
  - Stairs, Max Subarray, House Robber
  - Binary Search, Rotated Array, Bad Version
```

**Practice:** Solve 3-4 problems daily from [Daily Problem Log](DAILY_PROBLEM_LOG.md)

---

### Week 2-3: Medium Core (40 problems)
**Goal:** Master mid-level patterns and design

```
Day 1-3: Advanced Arrays (21-28)
  - 3Sum, Container Water, Substring, Palindrome
  - Anagrams, Word Ladder, Product Array
  
Day 4-5: Advanced Linked Lists (29-32)
  - Add Two Numbers, Remove Nth, LRU Cache, Reorder
  
Day 6-10: Trees & Graphs (33-42)
  - Max Path Sum, Serialize, LCA, Right View
  - Graph clone, Course schedule, Topological sort
  
Day 11-15: DP Problems (43-52)
  - Coin change, LIS, Edit distance, Decode ways
  - Word break, Min window substring
  
Day 16+: Binary Search & Misc (53-60)
  - Rotated array, Find position, Median
  - Kth largest, Top K frequent
```

**Tempo:** 2-3 medium problems per day

---

### Week 4-5: Hard & Design (15 problems)
**Goal:** Master complex problems and system design

```
Day 1-3: Hard DP & String (61-68)
  - Regex matching, Backtracking in Trie
  - Trapping rain, Histogram, Rectangles
  
Day 4-5: Design Problems (69-73)
  - LRU Cache, File System, Autocomplete, Snake Game
  
Day 6+: Advanced Graphs (74-75)
  - Dungeon game, Skyline problem
```

**Tempo:** 1-2 hard problems per day (review solutions multiple times)

---

## 📈 Success Metrics

| Metric | Goal | How to Track |
|--------|------|--------------|
| **Easy Mastery** | 100% of 20 problems | Can solve in <15 min, no hints |
| **Medium Proficiency** | 85%+ of 40 problems | Can solve in 30-45 min |
| **Hard Understanding** | 70%+ of 15 problems | Can solve with hints, understand fully |
| **Pattern Recognition** | 90%+ accuracy | Identify pattern within 5 min |
| **Speed Improvement** | -20% time/month | Track in Daily Log |
| **Blind Success Rate** | 80%+ at interview | Depends on prep consistency |

---

## 🏆 Company-Specific Focus

### Facebook (Most Coding Questions)
**Must Master:** Problems #1-10, 21-32, 39-42, 50-51, 56-60, 69-70

### Google (Most System Design)
**Must Master:** Problems #1-5, 20, 42, 51, 55, 58, 61-65, 69, 72, 75

### Amazon (Most DP)
**Must Master:** Problems #6-7, 15-17, 43-52, 55-58

### Microsoft (Balanced)
**Must Master:** Problems #1-20, 25-28, 42, 53-54, 62-65

---

## 💡 Pro Tips

1. **Solve in Order:** Don't skip easy → medium → hard progression
2. **Understand, Don't Memorize:** Read solution after 30 min struggle, then re-solve
3. **Track Time:** Time yourself. Target: Easy 8-10 min, Medium 30-40 min, Hard 45-60 min
4. **Categorize by Pattern:** Group similar problems to build muscle memory
5. **Use [Daily Problem Log](DAILY_PROBLEM_LOG.md):** Track mistakes and review dates
6. **Mock Interview Problems:** Problems #69-75 are excellent for system design mock interviews

---

## 🔗 Resources

- **LeetCode Premium:** Filter by company + difficulty (highly recommended)
- **[Question Bank DSA](Question%20Bank/dsa.md):** Detailed Q&A for all patterns
- **[Interview Tracks](INTERVIEW_TRACKS.md):** Company-specific study paths
- **[Daily Problem Log](DAILY_PROBLEM_LOG.md):** Track your progress
