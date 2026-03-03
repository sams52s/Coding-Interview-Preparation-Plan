# Data Replication

Data replication involves keeping copies of data on multiple distinct nodes (servers) connected via a network.

## 1. Why Replicate Data?

1. **High Availability**: If one node fails, the data is still available on other replica nodes.
2. **Lower Latency**: You can route a user to a replica geographically closer to them (e.g., a CDN or multi-region database).
3. **Increased Read Throughput**: You can distribute read queries across multiple nodes to handle more traffic than a single machine could.

## 2. Replication Strategies

### A. Single-Leader (Leader-Follower / Primary-Secondary)
The most common technique (used in MySQL, PostgreSQL, MongoDB, Kafka).
- **How it works**: One node is designated as the **Leader**. All *write* requests must go to the leader. The leader writes to its local storage, then sends the data changes (replication log/stream) to all **Followers**. Followers can serve *read* requests.
- **Pros**: Easy to reason about. Ensures sequential consistency for writes.
- **Cons**: Write bottleneck (only one node accepts writes). If the leader fails, a "failover" process is needed to elect a new leader, causing temporary write unavailability.

### B. Multi-Leader (Master-Master)
Allows multiple nodes to accept writes.
- **How it works**: Multiple leaders accept writes. Each leader is a follower to the other leaders. Common in multi-datacenter setups where each DC has its own leader.
- **Pros**: Better write availability and lower latency for remote users. Withstands the failure of an entire datacenter.
- **Cons**: Extremely complex. Resolving **Write Conflicts** (when two users edit the same record concurrently on different leaders) is notoriously difficult.

### C. Leaderless Replication
No node is "special." Any replica can accept writes and reads (Dynamo-style databases like Cassandra, Riak).
- **How it works**: Clients send writes and reads to *multiple* nodes in parallel.
- **Quorums**: To determine if an operation is successful, a quorum formula is used: $W + R > N$, where $N$ is total replicas, $W$ is write nodes, $R$ is read nodes. This guarantees that at least one of the nodes read from contains the most recent write.

## 3. Synchronous vs. Asynchronous Replication

### Synchronous Replication
- The leader waits for followers to confirm they received the write before responding "Success" to the user.
- **Pros**: Strong consistency. No data loss if the leader crashes.
- **Cons**: Very slow. System halts if a follower is unreachable or slow.

### Asynchronous Replication
- The leader sends the data to followers but immediately responds "Success" to the user without waiting.
- **Pros**: Extremely fast. High availability. The system isn't blocked by slow network connections to followers.
- **Cons**: Weak consistency (followers might serve stale data). **Data Loss**: If the leader crashes before the data replicates, any acknowledged writes are lost.

*Common hybrid approach*: Semi-synchronous. Wait for at least one follower to confirm, then replicate asynchronously to the rest.

## 4. Common Problems with Replication Lags

When using **asynchronous leader-follower replication**, followers will inevitably lag behind the leader. This creates consistency anomalies:

1. **Reading Your Own Writes**: A user updates their profile (goes to leader), reloads the page (load balancer hits a lagging follower), and sees their old profile.
   - *Solution*: Route read requests for recently updated items to the leader for a short time window.
2. **Monotonic Reads (Time-travel anomaly)**: User sees a newer state of data from one follower, then refreshes and hits an older, lagging follower, seeing data revert backward in time.
   - *Solution*: Pin the user's session to a specific replica (e.g., using IP hash).
3. **Consistent Prefix Reads**: User reads a sequence of events out of order because some partitions replicate faster than others.

## Interview Questions on Data Replication

1. **What is a Write Conflict in multi-leader replication, and how can it be resolved?**
   - *Answer*: It occurs when two clients simultaneously modify the same data on different leaders. Solutions include: "Last write wins" (LWW) based on timestamps (risks data loss), routing requests for a particular record to the same datacenter always, or letting the application resolve the conflict upon read (like Git merge).
2. **Explain the tradeoff between Synchronous and Asynchronous replication.**
   - *Answer*: Synchronous guarantees zero data loss and strong consistency but compromises performance and availability. Asynchronous provides high performance and availability but risks data loss on leader failure and serves stale data (eventual consistency).
3. **If a database uses Quorum Reads/Writes ($W=2, R=2, N=3$), what happens if one node goes down?**
   - *Answer*: Both reads and writes will continue to succeed because we only need 2 out of 3 nodes to acknowledge the operation to meet the quorum.
