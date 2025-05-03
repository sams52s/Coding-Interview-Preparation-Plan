# Java Collections Framework Practice Problems

## 📚 Java Collections Framework Practice

### List Operations

| #  | Problem                                    | Source      | Link                                                         | Difficulty | Status | My Solution  | Notes                               |
|:--:|:-------------------------------------------|:-----------:|:-------------------------------------------------------------|:----------:|:------:|:------------:|:------------------------------------|
| 1  | 📊 ArrayList vs LinkedList Performance     | HackerRank  | [Link](https://www.hackerrank.com/challenges/java-list)      | Easy       | [ ]    | [Solution]() | Compare operations: add, remove, get |
| 2  | 📋 Two Sum                                | LeetCode    | [Link](https://leetcode.com/problems/two-sum/)               | Easy       | [ ]    | [Solution]() | Use HashMap for O(n) solution        |
| 3  | 🔄 Remove Duplicates from List            | LeetCode    | [Link](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | [ ] | [Solution]() | Try HashSet approach                |
| 4  | 📊 Majority Element                        | LeetCode    | [Link](https://leetcode.com/problems/majority-element/)      | Easy       | [ ]    | [Solution]() | HashMap frequency counting          |

### Map & Set Problems

| #  | Problem                                   | Source     | Link                                                               | Difficulty | Status | My Solution  | Notes                                |
|:--:|:------------------------------------------|:----------:|:-------------------------------------------------------------------|:----------:|:------:|:------------:|:-------------------------------------|
| 5  | 🔑 HashMap Implementation                 | Practice   | [Link](https://www.baeldung.com/java-hashmap)                      | Medium     | [ ]    | [Solution]() | Implement with array and linked lists |
| 6  | 📝 Group Anagrams                         | LeetCode   | [Link](https://leetcode.com/problems/group-anagrams/)              | Medium     | [ ]    | [Solution]() | HashMap with sorted string keys       |
| 7  | 🔍 Find Common Characters                 | LeetCode   | [Link](https://leetcode.com/problems/find-common-characters/)      | Easy       | [ ]    | [Solution]() | HashSet intersection                  |
| 8  | 🔤 First Unique Character                 | LeetCode   | [Link](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | [ ] | [Solution]() | LinkedHashMap maintains order         |

### Queue & Stack Problems

| #  | Problem                                    | Source     | Link                                                        | Difficulty | Status | My Solution  | Notes                                 |
|:--:|:-------------------------------------------|:----------:|:------------------------------------------------------------|:----------:|:------:|:------------:|:--------------------------------------|
| 9  | 📚 Implement Stack using Queues            | LeetCode   | [Link](https://leetcode.com/problems/implement-stack-using-queues/) | Easy  | [ ]    | [Solution]() | Use Queue interface implementations   |
| 10 | ⚖️ Min Stack Design                        | LeetCode   | [Link](https://leetcode.com/problems/min-stack/)            | Medium     | [ ]    | [Solution]() | Track minimum in auxiliary structure   |
| 11 | 🔄 Circular Queue Implementation           | LeetCode   | [Link](https://leetcode.com/problems/design-circular-queue/) | Medium     | [ ]    | [Solution]() | Fixed size array implementation       |
| 12 | 📊 Top K Frequent Elements                 | LeetCode   | [Link](https://leetcode.com/problems/top-k-frequent-elements/) | Medium  | [ ]    | [Solution]() | PriorityQueue implementation          |

### Advanced Data Structures

| #  | Problem                                    | Source     | Link                                                               | Difficulty | Status | My Solution  | Notes                                    |
|:--:|:-------------------------------------------|:----------:|:-------------------------------------------------------------------|:----------:|:------:|:------------:|:-----------------------------------------|
| 13 | 🌲 Implement Trie (Prefix Tree)            | LeetCode   | [Link](https://leetcode.com/problems/implement-trie-prefix-tree/)  | Medium     | [ ]    | [Solution]() | Character-based tree structure           |
| 14 | 💾 LRU Cache Implementation                | LeetCode   | [Link](https://leetcode.com/problems/lru-cache/)                   | Medium     | [ ]    | [Solution]() | LinkedHashMap with access ordering       |
| 15 | 💾 LFU Cache Design                        | LeetCode   | [Link](https://leetcode.com/problems/lfu-cache/)                   | Hard       | [ ]    | [Solution]() | Track both frequency and recency         |
| 16 | 🔄 Design Twitter                          | LeetCode   | [Link](https://leetcode.com/problems/design-twitter/)              | Medium     | [ ]    | [Solution]() | Use multiple collection types            |

### Concurrency & Threading

| #  | Problem                                     | Source     | Link                                                       | Difficulty | Status | My Solution  | Notes                                  |
|:--:|:--------------------------------------------|:----------:|:-----------------------------------------------------------|:----------:|:------:|:------------:|:---------------------------------------|
| 17 | 🧵 Producer-Consumer Implementation         | Practice   | [Link](https://www.baeldung.com/java-blocking-queue)       | Medium     | [ ]    | [Solution]() | BlockingQueue implementations          |
| 18 | 🔒 Thread-Safe Collections                  | Practice   | [Link](https://docs.oracle.com/javase/tutorial/essential/concurrency/collections.html) | Hard | [ ] | [Solution]() | Compare ConcurrentHashMap & Synchronized |
| 19 | 🔄 Parallel Stream Processing               | Practice   | [Link](https://www.baeldung.com/java-when-to-use-parallel-stream) | Medium | [ ] | [Solution]() | Benchmark parallel vs sequential        |
| 20 | 🧩 Dining Philosophers Problem              | Practice   | [Link](https://www.baeldung.com/java-dining-philoshophers)  | Hard       | [ ]    | [Solution]() | Synchronization & deadlock prevention   |

## Interview Tips

- 💡 Know the big-O complexity for common operations on different collection types
- 💡 Understand when to use which collection type (ArrayList vs LinkedList, HashMap vs TreeMap)
- 💡 Be familiar with the Collection hierarchy and its interfaces
- 💡 Practice implementing custom Comparable and Comparator for sorting
- 💡 Know how to use Iterator and enhanced for-loop with collections
