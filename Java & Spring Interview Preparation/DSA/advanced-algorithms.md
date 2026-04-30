# Advanced Algorithms: Graphs and Dynamic Programming Patterns

Navigation: [README](README.md) | Related: [Daily Problem Log](../../DAILY_PROBLEM_LOG.md) | [Blind 75 Mapping](../../BLIND_75_PROBLEM_MAPPING.md) | [DSA Q&A](../../Question%20Bank/dsa.md)

This file expands the DSA section for interviews that go beyond arrays, strings, sorting, and basic recursion.

## Graph algorithm patterns

| Pattern | Use when | Core idea |
|---------|----------|-----------|
| BFS | shortest path in unweighted graph | explore by level |
| DFS | connected components, cycle detection | explore depth-first with visited state |
| Topological sort | dependency ordering | process zero-indegree nodes or DFS finish order |
| Dijkstra | shortest path with non-negative weights | greedily expand lowest distance |
| Bellman-Ford | negative edges | relax all edges repeatedly |
| Union-Find | dynamic connectivity | merge sets with path compression |
| Floyd-Warshall | all-pairs shortest path | dynamic programming over intermediate nodes |

## Graph interview checklist

- What are nodes and edges?
- Is the graph directed or undirected?
- Weighted or unweighted?
- Can cycles exist?
- What is the size of `V` and `E`?
- Is the graph sparse or dense?
- Do we need shortest path, reachability, ordering, or components?

## Example: topological sort

```java
List<Integer> topoSort(int n, List<int[]> edges) {
    List<List<Integer>> graph = new ArrayList<>();
    int[] indegree = new int[n];

    for (int i = 0; i < n; i++) {
        graph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
        graph.get(edge[0]).add(edge[1]);
        indegree[edge[1]]++;
    }

    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        if (indegree[i] == 0) {
            queue.offer(i);
        }
    }

    List<Integer> order = new ArrayList<>();
    while (!queue.isEmpty()) {
        int node = queue.poll();
        order.add(node);
        for (int next : graph.get(node)) {
            if (--indegree[next] == 0) {
                queue.offer(next);
            }
        }
    }

    return order.size() == n ? order : List.of();
}
```

## Dynamic programming patterns

| Pattern | Examples | State design |
|---------|----------|--------------|
| 1D DP | climbing stairs, house robber | `dp[i]` best answer up to index |
| 2D grid DP | unique paths, min path sum | `dp[row][col]` best answer to cell |
| Knapsack | subset sum, coin change | `dp[item][capacity]` or optimized 1D |
| String DP | edit distance, LCS | `dp[i][j]` comparing prefixes |
| Interval DP | burst balloons, matrix chain | `dp[left][right]` best inside interval |
| Tree DP | diameter, max path | return include/exclude values from child |
| Bitmask DP | traveling salesman small n | `dp[mask][last]` |

## DP interview checklist

1. Define the state.
2. Define the transition.
3. Define base cases.
4. Define answer extraction.
5. State time and space complexity.
6. Explain whether space can be optimized.

## Example: coin change

```java
int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;

    for (int value = 1; value <= amount; value++) {
        for (int coin : coins) {
            if (coin <= value) {
                dp[value] = Math.min(dp[value], dp[value - coin] + 1);
            }
        }
    }

    return dp[amount] > amount ? -1 : dp[amount];
}
```

## Practice order

1. BFS/DFS grid problems.
2. Topological sort.
3. Union-Find.
4. Dijkstra.
5. 1D and 2D DP.
6. Knapsack and string DP.
7. Interval/tree/bitmask DP only after fundamentals are solid.

