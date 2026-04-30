# Core Infrastructure Components

This folder turns system-design fundamentals into the concrete building blocks used in production systems.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [System Design Fundamentals](../System%20Design%20Fundamentals/README.md) | Next: [Microservices and Architecture](../Microservices%20and%20Architecture/README.md)

**Practice:** [System Design Template](../SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) | [Real-World Case Studies](../Real-World%20Case%20Studies/README.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md) | [Performance Benchmarks](../PERFORMANCE_BENCHMARKS.md)

## Folder files
- [06_load_balancing.md](06_load_balancing.md) — traffic distribution, health checks, failover, algorithms, SSL/TLS termination, and throttling.
- [07_caching.md](07_caching.md) — cache layers, cache-aside, invalidation, TTL, stampede prevention, distributed caching, and Redis examples.
- [08_data_storage.md](08_data_storage.md) — storage models, durability, indexing, replication, partitioning, and access-pattern design.
- [09_service_communication.md](09_service_communication.md) — REST, gRPC, messaging, WebSocket, request/reply, saga communication, and serialization.
- [10_monitoring_observability.md](10_monitoring_observability.md) — logs, metrics, traces, Prometheus, Grafana, OpenTelemetry, alerting, and dashboards.
- [README.md](README.md) — this folder guide.

## How it connects
- Builds on [System Design Fundamentals](../System%20Design%20Fundamentals/README.md).
- Prepares the component knowledge needed for [Microservices and Architecture](../Microservices%20and%20Architecture/README.md).
- Cross-links naturally with [Performance Engineering](../Performance%20Engineering/README.md), [Cloud and Observability](../Cloud%20and%20Observability/README.md), and [Question Bank/system-design.md](../Question%20Bank/system-design.md).
- Shows up repeatedly in [Netflix](../Real-World%20Case%20Studies/01_netflix_streaming_platform.md), [Uber](../Real-World%20Case%20Studies/02_uber_ride_matching.md), and [Instagram](../Real-World%20Case%20Studies/03_instagram_feed_media.md) designs.

## Suggested reading order
1. [06_load_balancing.md](06_load_balancing.md)
2. [07_caching.md](07_caching.md)
3. [08_data_storage.md](08_data_storage.md)
4. [09_service_communication.md](09_service_communication.md)
5. [10_monitoring_observability.md](10_monitoring_observability.md)

## Interview focus areas
- Choose a component based on bottleneck, failure mode, and consistency needs.
- Explain component trade-offs clearly: cost, latency, complexity, availability, and operational burden.
- Connect every component to monitoring, security, and rollback/recovery.
- Practice common designs: URL shortener, notification service, chat system, feed service, file storage.

## Deep-dive practice
- CDN and edge caching: choose cache keys, TTLs, purge strategy, and origin-protection controls.
- DNS and service discovery: explain health checks, connection pooling, failover, and stale endpoint risks.
- Gateway comparison: separate the responsibilities of API gateway, reverse proxy, load balancer, and service mesh.
- Queue resilience: define backpressure, retry policy, poison-message handling, and dead-letter queue ownership.
- Redis operations: describe cluster sharding, eviction policy, persistence mode, failover, and cache-stampede protection.
