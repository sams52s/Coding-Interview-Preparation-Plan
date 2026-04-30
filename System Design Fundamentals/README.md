# System Design Fundamentals

This folder is the first block of the system-design path. It builds the vocabulary needed before moving into infrastructure, microservices, advanced distributed systems, and performance engineering.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [Spring Boot](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md) | Next: [Core Infrastructure Components](../Core%20Infrastructure%20Components/README.md)

**Practice:** [System Design Template](../SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) | [Real-World Case Studies](../Real-World%20Case%20Studies/README.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md) | [Cost Calculator](../COST_CALCULATOR.md)

## Folder files
- [01_scalability.md](01_scalability.md) — horizontal and vertical scaling, bottlenecks, capacity planning, growth patterns, and SLAs/SLOs.
- [02_high_availability.md](02_high_availability.md) — redundancy, failover, disaster recovery, multi-zone/multi-region thinking, and graceful degradation.
- [03_distributed_systems.md](03_distributed_systems.md) — coordination, consistency, CAP trade-offs, distributed failure modes, tracing, and recovery.
- [04_security_performance.md](04_security_performance.md) — authentication, authorization, TLS, rate limiting, caching, benchmarking, and safety/performance trade-offs.
- [05_data_management.md](05_data_management.md) — relational/NoSQL choices, sharding, replication, data lifecycle, caching, governance, and backup.
- [System Design.md](System%20Design.md) — compact system design summary and interview structure.
- [README.md](README.md) — this folder guide.

## How it connects
- Comes after the [Spring Boot backend stage](../README.md#learning-roadmap), where service design is introduced.
- Feeds directly into [Core Infrastructure Components](../Core%20Infrastructure%20Components/README.md), where the abstract fundamentals become concrete components.
- Provides the base vocabulary used in [Netflix](../Real-World%20Case%20Studies/01_netflix_streaming_platform.md), [Uber](../Real-World%20Case%20Studies/02_uber_ride_matching.md), and [Instagram](../Real-World%20Case%20Studies/03_instagram_feed_media.md) case studies.
- Supports [Question Bank/system-design.md](../Question%20Bank/system-design.md) and [Question Bank/cloud-devops.md](../Question%20Bank/cloud-devops.md).

## Suggested reading order
1. [System Design.md](System%20Design.md) for the quick interview structure.
2. [01_scalability.md](01_scalability.md)
3. [02_high_availability.md](02_high_availability.md)
4. [03_distributed_systems.md](03_distributed_systems.md)
5. [05_data_management.md](05_data_management.md)
6. [04_security_performance.md](04_security_performance.md)

## Deep-dive practice
- Rate limiting: compare token bucket, leaky bucket, fixed window, and sliding window for API abuse control.
- Consistent hashing: explain virtual nodes, hot partitions, and rebalance behavior during node changes.
- Disaster recovery: state RPO, RTO, backup restore flow, failover trigger, and customer-impact trade-off.
- Capacity estimation: practice requests per second, storage growth, cache size, and bandwidth estimates with [COST_CALCULATOR.md](../COST_CALCULATOR.md).
- Trade-off framing: use SQL vs NoSQL, sync vs async, cache vs source-of-truth, and single-region vs multi-region decisions in every case study.
