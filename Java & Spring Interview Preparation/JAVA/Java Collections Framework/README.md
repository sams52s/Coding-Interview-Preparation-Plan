# Java Collections Framework

This folder is the Java Collections Framework study block. It explains the collection hierarchy, key interfaces, common implementations, iteration, sorting, performance, concurrency, immutability, best practices, and interview Q&A.

**Navigation:** [Main README](../../../README.md) | [Learning Roadmap](../../../README.md#learning-roadmap) | [Java Hub](../README.md) | Previous: [OOP](../OOP/README.md) | Related: [Collections Q&A](17_Interview_Questions_and_Answers.md)

## Folder files
- [01_Collection_Framework_Introduction.md](01_Collection_Framework_Introduction.md) — JCF overview, hierarchy, and motivation.
- [02_Collection_Interfaces.md](02_Collection_Interfaces.md) — `Collection`, `List`, `Set`, `Queue`, `Deque`, and `Map` interface relationships.
- [03_List_Interface.md](03_List_Interface.md) — `ArrayList`, `LinkedList`, `Vector`, list operations, and trade-offs.
- [04_Set_Interface.md](04_Set_Interface.md) — `HashSet`, `LinkedHashSet`, `TreeSet`, uniqueness, ordering, and hashing.
- [05_Queue_and_Deque.md](05_Queue_and_Deque.md) — queue/deque operations, priority queues, and use cases.
- [06_Map_Interface.md](06_Map_Interface.md) — `HashMap`, `TreeMap`, `LinkedHashMap`, `ConcurrentHashMap`, and map internals.
- [07_Iterator_and_Iterable.md](07_Iterator_and_Iterable.md) — iterators, fail-fast behavior, custom iteration, and streams.
- [08_Collections_vs_Collection.md](08_Collections_vs_Collection.md) — framework interface vs utility class distinction.
- [09_Comparable_vs_Comparator.md](09_Comparable_vs_Comparator.md) — natural ordering, custom sorting, and comparator patterns.
- [10_Collections_Utility_Class.md](10_Collections_Utility_Class.md) — `Collections` helper methods and common usage.
- [11_Sorting_and_Searching.md](11_Sorting_and_Searching.md) — sorting/searching APIs and algorithmic considerations.
- [12_Performance_Comparison_Table.md](12_Performance_Comparison_Table.md) — Big-O and implementation comparison tables.
- [13_Concurrent_Collections.md](13_Concurrent_Collections.md) — concurrent collection types and multi-threaded access patterns.
- [14_Synchronization_and_FailFast.md](14_Synchronization_and_FailFast.md) — synchronized wrappers, fail-fast/fail-safe iteration, and concurrency behavior.
- [15_Immutable_Collections.md](15_Immutable_Collections.md) — immutable collection APIs and defensive copying.
- [16_Best_Practices.md](16_Best_Practices.md) — collection selection, memory, performance, and clean usage.
- [17_Interview_Questions_and_Answers.md](17_Interview_Questions_and_Answers.md) — collections interview Q&A.
- [problem List.md](problem%20List.md) — practice problems.
- [image.png](image.png), [commonDataStructureOperations.png](commonDataStructureOperations.png), [arraySortingAlgorithms.png](arraySortingAlgorithms.png) — supporting diagrams/images.
- [README.md](README.md) — this folder guide.

## Suggested reading order
1. [01_Collection_Framework_Introduction.md](01_Collection_Framework_Introduction.md)
2. [02_Collection_Interfaces.md](02_Collection_Interfaces.md)
3. [03_List_Interface.md](03_List_Interface.md), [04_Set_Interface.md](04_Set_Interface.md), [05_Queue_and_Deque.md](05_Queue_and_Deque.md), [06_Map_Interface.md](06_Map_Interface.md)
4. [07_Iterator_and_Iterable.md](07_Iterator_and_Iterable.md)
5. [09_Comparable_vs_Comparator.md](09_Comparable_vs_Comparator.md), [10_Collections_Utility_Class.md](10_Collections_Utility_Class.md), [11_Sorting_and_Searching.md](11_Sorting_and_Searching.md)
6. [12_Performance_Comparison_Table.md](12_Performance_Comparison_Table.md)
7. [13_Concurrent_Collections.md](13_Concurrent_Collections.md), [14_Synchronization_and_FailFast.md](14_Synchronization_and_FailFast.md), [15_Immutable_Collections.md](15_Immutable_Collections.md)
8. [16_Best_Practices.md](16_Best_Practices.md)
9. [17_Interview_Questions_and_Answers.md](17_Interview_Questions_and_Answers.md)

## How it connects
- The [Java foundation stage](../../../README.md#learning-roadmap) uses this folder after Java fundamentals and OOP.
- The [advanced Java and DSA stage](../../../README.md#learning-roadmap) builds on this with generics, IO/NIO, lambdas, and DSA structures.
- Related question bank: [Question Bank/core-java.md](../../../Question%20Bank/core-java.md) and [Question Bank/dsa.md](../../../Question%20Bank/dsa.md).

## Interview focus areas
- Choose the right collection for a use case.
- Explain `HashMap` internals, hashing, collisions, resizing, and equality contracts.
- Compare `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap`, and `ConcurrentHashMap`.
- Discuss fail-fast iterators, immutable collections, synchronized wrappers, and concurrent collections.
- Analyze time and space complexity of common operations.
