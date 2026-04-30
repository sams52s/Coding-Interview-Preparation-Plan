# Microservices and Architecture

This folder focuses on service decomposition, distributed communication, event-driven design, serverless, cloud-native patterns, and resilience.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [Core Infrastructure Components](../Core%20Infrastructure%20Components/README.md) | Next: [Advanced Distributed Systems](../Advanced%20Distributed%20Systems/README.md)

**Practice:** [Microservice Project](../projects/04_microservice_architecture.md) | [Real-World Case Studies](../Real-World%20Case%20Studies/README.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md) | [Production Checklist](../PRODUCTION_READINESS_CHECKLIST.md)

## Folder files
- [11_microservices_architecture.md](11_microservices_architecture.md) — service boundaries, domain modeling, independent deployment, API governance, testing, reliability, and migration patterns.
- [12_event_driven_architecture.md](12_event_driven_architecture.md) — asynchronous messaging, events, queues, streams, CQRS, event sourcing, and decoupled workflows.
- [13_serverless_architecture.md](13_serverless_architecture.md) — functions, triggers, workflows, cold starts, DLQs, cost, security, and production readiness.
- [14_cloud_native_patterns.md](14_cloud_native_patterns.md) — 12-factor design, containers, Kubernetes, GitOps, service mesh, scaling, and deployment patterns.
- [15_resilience_patterns.md](15_resilience_patterns.md) — timeouts, retries, circuit breakers, bulkheads, rate limiting, fallback, and chaos testing.
- [README.md](README.md) — this folder guide.

## How it connects
- Extends [Spring Boot](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md) from single-service backend knowledge into service ecosystems.
- Uses infrastructure concepts from [Core Infrastructure Components](../Core%20Infrastructure%20Components/README.md).
- Leads into [Advanced Distributed Systems](../Advanced%20Distributed%20Systems/README.md), especially consensus, transactions, streams, and replication.
- Applies directly to [Netflix streaming](../Real-World%20Case%20Studies/01_netflix_streaming_platform.md), [Uber ride matching](../Real-World%20Case%20Studies/02_uber_ride_matching.md), and [Instagram feed/media](../Real-World%20Case%20Studies/03_instagram_feed_media.md) design cases.
- Supports [Question Bank/microservices.md](../Question%20Bank/microservices.md), [Question Bank/rest-api.md](../Question%20Bank/rest-api.md), and [Question Bank/system-design.md](../Question%20Bank/system-design.md).

## Suggested reading order
1. [11_microservices_architecture.md](11_microservices_architecture.md)
2. [09_service_communication.md](../Core%20Infrastructure%20Components/09_service_communication.md)
3. [12_event_driven_architecture.md](12_event_driven_architecture.md)
4. [15_resilience_patterns.md](15_resilience_patterns.md)
5. [13_serverless_architecture.md](13_serverless_architecture.md)
6. [14_cloud_native_patterns.md](14_cloud_native_patterns.md)

## Common interview decisions
- Monolith vs modular monolith vs microservices.
- API gateway vs BFF vs service mesh.
- Synchronous request flow vs asynchronous event flow.
- Database per service vs shared database.
- Orchestration vs choreography for sagas.
- Retry, timeout, circuit breaker, bulkhead, and idempotency strategy.

## Applied practice
- Contract testing: define producer and consumer expectations before changing a public API.
- Strangler-fig migration: move one capability at a time from a monolith into a bounded service.
- Service ownership: connect service boundaries to team responsibility, on-call ownership, and deployment autonomy.
- Schema evolution: plan backward-compatible API and event changes with versioning, defaults, and deprecation windows.
- Idempotency: use idempotency keys for payment, order, retry, and message-processing workflows.
- Incident review: compare these patterns with [production incident reviews](../Real-World%20Case%20Studies/04_production_incident_reviews.md).
