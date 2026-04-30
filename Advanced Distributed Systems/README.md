# Advanced Distributed Systems

This folder covers the deeper distributed-systems ideas that explain why large systems behave the way they do under failures, partitions, replication lag, and concurrent writes.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [Microservices and Architecture](../Microservices%20and%20Architecture/README.md) | Next: [Performance Engineering](../Performance%20Engineering/README.md)

**Practice:** [System Design Case Studies](../projects/05_system_design_case_studies.md) | [System Design Template](../SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md)

## Folder files
- [16_consensus_algorithms.md](16_consensus_algorithms.md) — Raft, Paxos, quorums, leader election, safety/liveness, fencing, testing, and failure drills.
- [17_distributed_transactions.md](17_distributed_transactions.md) — 2PC, 3PC, sagas, compensation, isolation, outbox, and transactional trade-offs.
- [18_stream_processing.md](18_stream_processing.md) — batch vs stream, Kafka, event-driven architectures, windows, and real-time pipelines.
- [19_data_replication.md](19_data_replication.md) — replication models, consistency, conflict resolution, lag, failover, and availability.
- [advanced_crdts_vector_clocks_eventual_consistency.md](advanced_crdts_vector_clocks_eventual_consistency.md) — CRDTs, vector clocks, conflict detection, merge semantics, and eventual consistency patterns.
- [README.md](README.md) — this folder guide.

## How it connects
- Builds on [03_distributed_systems.md](../System%20Design%20Fundamentals/03_distributed_systems.md).
- Explains the hard parts behind [Microservices and Architecture](../Microservices%20and%20Architecture/README.md).
- Feeds into [Performance Engineering](../Performance%20Engineering/README.md) and [Cloud and Observability](../Cloud%20and%20Observability/README.md), because advanced distributed systems must be measured and operated.
- Supports [Question Bank/system-design.md](../Question%20Bank/system-design.md), [Question Bank/advanced-topics.md](../Question%20Bank/advanced-topics.md), and [Question Bank/database-sql.md](../Question%20Bank/database-sql.md).

## Suggested reading order
1. [19_data_replication.md](19_data_replication.md)
2. [16_consensus_algorithms.md](16_consensus_algorithms.md)
3. [17_distributed_transactions.md](17_distributed_transactions.md)
4. [18_stream_processing.md](18_stream_processing.md)
5. [advanced_crdts_vector_clocks_eventual_consistency.md](advanced_crdts_vector_clocks_eventual_consistency.md)

## Core trade-offs to master
- Linearizability vs serializability vs eventual consistency.
- Single-leader vs multi-leader vs leaderless replication.
- Two-phase commit vs saga/outbox.
- Exactly-once claims vs idempotent processing.
- Quorum reads/writes vs latency and availability.

## Deep-dive practice
- Explain when CRDTs are safer than last-write-wins.
- Compare saga/outbox with two-phase commit.
- Describe how vector clocks detect concurrent writes.
- Explain stream-processing delivery semantics: at-most-once, at-least-once, and effectively-once.
- Use [Real-World Case Studies](../Real-World%20Case%20Studies/README.md) to connect these concepts to interview systems.
