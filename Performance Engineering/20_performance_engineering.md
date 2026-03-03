# Performance Engineering Fundamentals

Performance engineering involves designing, modeling, testing, and tuning systems to ensure they meet non-functional requirements regarding speed, scalability, and resource usage.

## 1. Key Metrics & Terminology

1. **Latency (Response Time)**: How long a system takes to process a single request. Measured usually at the 50th (p50), 90th (p90), and 99th (p99) percentiles. *Never look at just the "average" latency, as it hides outliers.*
2. **Throughput**: The number of requests (or transactions) the system can process over a given time frame (e.g., Requests Per Second - RPS or TPS).
3. **Concurrency**: The number of requests currently in-flight and being processed by the system simultaneously.
4. **Little’s Law**: A fundamental theorem. $Concurrency = Throughput \times Latency$. E.g., if a server processes 100 RPS and each request takes 0.5s, there are 50 concurrent requests in the system.
5. **Bottleneck**: The component that limits the overall throughput of the system (e.g., CPU, Memory, Disk I/O, Network Bandwidth, Database connections).

## 2. The Performance Anti-Patterns

- **N+1 Query Problem**: Executing one query to fetch N parent records, then N queries to fetch children.
- **Premature Optimization**: "Premature optimization is the root of all evil" (Donald Knuth). Only optimize based on measured data, not assumptions. Make it work, make it right, make it fast.
- **Synchronous Calls over the Network**: Making a blocking API call to a third-party service during a critical user flow.
- **Memory Leaks**: Holding onto object references preventing garbage collection, eventually causing OutOfMemory errors and drastically impacting performance before crashing.

## 3. Profiling & Measurement

Before tuning, you must measure. "You can't manage what you don't measure."

### Steps for Profiling:
1. **Identify the Bottleneck**: Is it CPU bound (complex calculations), I/O bound (waiting on DB/Network), or Memory bound (frequent GC pauses)?
2. **Use Profilers**: Tools like JProfiler, YourKit, Async-profiler, or Java Flight Recorder (JFR) capture where time is spent in code.
3. **Flame Graphs**: Visualizations of application stack traces where X-axis shows time spent and Y-axis shows the call stack. Wide blocks indicate methods taking the most time.
4. **APM (Application Performance Monitoring)**: Tools like New Relic, AppDynamics, or Datadog track response times and DB queries in production.

## 4. Core Tuning Strategies

1. **Caching (The silver bullet)**
   - Move data closer to the compute. Cache DB results locally (Guava, Caffeine) or distributed (Redis).
   - Invalidate or TTL your cache effectively to ensure data consistency.
2. **Asynchronous Processing**
   - Offload heavy tasks (e.g., sending emails, generating reports) to background workers or message queues like Kafka/RabbitMQ. Don't block the main HTTP thread.
3. **Connection Pooling**
   - Opening a TCP connection is expensive (3-way handshake, TLS). Reusing connections to databases (HikariCP) or external APIs (Apache HttpClient pooling) is critical.
4. **Pagination and Batching**
   - Never load everything into memory. Fetch records in chunks. Update records in batches.
5. **Scaling (Horizontal)**
   - Add more instances behind a load balancer to increase total throughput.
   
## Interview Questions on Performance Engineering

1. **How do you approach investigating a slow API endpoint in production?**
   - *Answer*: First check APM tools (e.g., Datadog) to isolate if the issue is in the application code, network, or the database. Analyze logs. If it's a DB issue, look for missing indexes or slow queries. If it's application code, look at thread dumps or use a profiler to find the slow method (e.g., blocking I/O calls).
2. **What is the difference between p95 and p99 latency? Why not use average?**
   - *Answer*: Averages hide extreme outliers (e.g., 99 requests take 1ms, 1 request takes 10,000ms; average is ~100ms, which is misleading). p99 means 99% of requests are faster than that value, indicating the worst-case experience for 1% of users. p99 helps measure consistency.
3. **What does it mean for a system to be "I/O bound" vs "CPU bound"?**
   - *Answer*: CPU bound means the speed of processing is limited by CPU cycles (e.g., heavy math, crypto, parsing large JSON). I/O bound means the CPU is mostly idle, waiting for network responses or disk reads/writes (e.g., typical web applications querying a database).
