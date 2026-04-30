# Performance Benchmarks

Navigation: [Main README](README.md) | [Performance Engineering](Performance%20Engineering/README.md) | [Cost Calculator](COST_CALCULATOR.md) | [Production Checklist](PRODUCTION_READINESS_CHECKLIST.md)

This file gives a standard way to plan and record benchmark results. It is not a claim that one technology is always faster; benchmark in the context of your workload.

## Benchmark principles

- Measure before optimizing.
- Compare one variable at a time.
- Warm up JVM-based tests.
- Capture p50, p95, p99, throughput, error rate, and resource usage.
- Document hardware, dataset size, concurrency, and configuration.
- Treat benchmark results as evidence, not universal truth.

## Benchmark record template

| Field | Value |
|-------|-------|
| Date | |
| Component | |
| Goal | |
| Dataset size | |
| Concurrency | |
| Environment | |
| Tool | |
| Baseline result | |
| Optimized result | |
| Conclusion | |

## Java/JVM benchmarks

| Scenario | Tool | Metrics |
|----------|------|---------|
| Microbenchmark method performance | JMH | ops/sec, allocation rate |
| Memory pressure | VisualVM / JFR | heap, GC pause, allocation |
| Lock contention | JFR / async-profiler | blocked time, CPU |
| Thread pool sizing | load test + metrics | latency, queue depth |

## API benchmarks

| Scenario | Tool | Metrics |
|----------|------|---------|
| REST endpoint load | k6, JMeter, Gatling, Artillery | p95/p99, RPS, errors |
| Cold start/warm start | repeated runs | first response, steady response |
| Pagination strategy | load test | DB time, response size |
| Serialization format | benchmark | CPU, payload size |

## Database benchmarks

| Scenario | Metrics to capture |
|----------|-------------------|
| Query with/without index | execution time, rows scanned, plan |
| Join strategy | execution time, memory, sort/hash usage |
| Pagination offset vs keyset | latency at large page numbers |
| Read replica impact | replication lag, stale reads |
| Connection pool tuning | wait time, timeout rate, DB CPU |

## Cache benchmarks

| Scenario | Metrics to capture |
|----------|-------------------|
| Cache hit ratio | hit/miss rate, latency |
| Cache stampede protection | backend load under expiry |
| TTL strategy | freshness vs hit rate |
| Local vs distributed cache | latency, consistency risk |

## Network benchmarks

| Scenario | Metrics to capture |
|----------|-------------------|
| Payload compression | bandwidth, CPU, latency |
| HTTP/1.1 vs HTTP/2 | connection reuse, latency |
| CDN vs origin | cache hit, edge latency |
| gRPC vs REST | latency, payload, CPU |

## Interview answer pattern

```text
I would first define the target metric and baseline it.
Then I would isolate the suspected bottleneck.
After changing one variable, I would compare p95/p99, throughput, errors, and resource usage.
I would only keep the optimization if it improves the target without hurting reliability or maintainability.
```
