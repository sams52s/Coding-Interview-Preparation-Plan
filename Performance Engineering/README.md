# Performance Engineering

This folder covers how to measure, explain, and improve performance across application code, databases, networks, and the JVM.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [Advanced Distributed Systems](../Advanced%20Distributed%20Systems/README.md) | Next: [DevSecOps and Frontend](../DevSecOps%20and%20Frontend/README.md)

**Practice:** [Performance Benchmarks](../PERFORMANCE_BENCHMARKS.md) | [Cost Calculator](../COST_CALCULATOR.md) | [Production Checklist](../PRODUCTION_READINESS_CHECKLIST.md)

## Folder files
- [20_performance_engineering.md](20_performance_engineering.md) — performance thinking, bottlenecks, measurement, profiling, load testing, and optimization strategy.
- [21_database_optimization.md](21_database_optimization.md) — indexing, query planning, schema trade-offs, slow-query diagnosis, and database tuning.
- [22_network_optimization.md](22_network_optimization.md) — latency, throughput, HTTP/TCP behavior, payload optimization, CDN, and protocol efficiency.
- [23_jvm_gc_tuning.md](23_jvm_gc_tuning.md) — JVM memory behavior, GC algorithms, pause tuning, heap sizing, profiling, and GC logging.
- [advanced_flame_graphs_async_profiler_gc_tuning.md](advanced_flame_graphs_async_profiler_gc_tuning.md) — flame graphs, async-profiler workflow, allocation profiling, and JVM tuning from production symptoms.
- [README.md](README.md) — this folder guide.

## How it connects
- Depends on the system components in [Core Infrastructure Components](../Core%20Infrastructure%20Components/README.md).
- Adds measurement depth to [Java Memory Management](../Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Memory%20Management.md), [Multithreading & Concurrency](../Java%20%26%20Spring%20Interview%20Preparation/JAVA/Multithreading%20%26%20Concurrency.md), and [Spring Boot observability](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/06_observability_monitoring.md).
- Supports [Question Bank/database-sql.md](../Question%20Bank/database-sql.md), [Question Bank/advanced-topics.md](../Question%20Bank/advanced-topics.md), and [Question Bank/testing-quality.md](../Question%20Bank/testing-quality.md).

## Suggested reading order
1. [20_performance_engineering.md](20_performance_engineering.md)
2. [21_database_optimization.md](21_database_optimization.md)
3. [22_network_optimization.md](22_network_optimization.md)
4. [23_jvm_gc_tuning.md](23_jvm_gc_tuning.md)
5. [advanced_flame_graphs_async_profiler_gc_tuning.md](advanced_flame_graphs_async_profiler_gc_tuning.md)

## Metrics to track
- Latency: p50, p95, p99, p99.9.
- Throughput: requests/sec, messages/sec, jobs/sec.
- Saturation: CPU, heap, threads, database connections, network, disk I/O.
- Reliability: error rate, timeout rate, retry rate, queue depth.
- Cost: cost per request, cost per GB stored, cost per job.

## Deep-dive practice
- Capture a CPU flame graph and explain the widest frame.
- Compare CPU profiling, allocation profiling, lock profiling, and GC logs.
- Diagnose an N+1 query from metrics and traces.
- Write before/after benchmark notes using [PERFORMANCE_BENCHMARKS.md](../PERFORMANCE_BENCHMARKS.md).
- Explain how performance changes map to user-facing SLOs.
