# Project 04: Microservice Architecture

Navigation: [Projects](README.md) | [Stage 4](../README.md#learning-roadmap) | [Microservices and Architecture](../Microservices%20and%20Architecture/README.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md)

## Goal

Design and optionally implement a small microservice system that demonstrates service boundaries, communication, resilience, observability, and deployment thinking.

## Recommended domain

Use an e-commerce order flow because it naturally includes user, catalog, order, payment, notification, and inventory concerns.

## Services

| Service | Responsibility | Data ownership |
|---------|----------------|----------------|
| API Gateway | Routing, auth handoff, rate limiting | No domain data |
| User Service | user profiles and identity reference | users |
| Catalog Service | products, prices, categories | products |
| Order Service | order lifecycle and orchestration | orders |
| Payment Service | payment attempts and idempotency | payments |
| Inventory Service | stock reservation and release | inventory |
| Notification Service | email/SMS/event notifications | notification log |

## Communication patterns

- REST for user-facing reads and synchronous commands.
- Events for order-created, payment-authorized, stock-reserved, order-confirmed.
- Idempotency keys for payment and order submission.
- Outbox pattern for reliable event publishing.
- Dead-letter queue for failed asynchronous messages.

## Resilience requirements

- Timeout on all remote calls.
- Retry only for safe/idempotent operations.
- Circuit breaker around payment and inventory.
- Fallback for catalog recommendations.
- Bulkhead thread pools for external dependencies.

## Observability requirements

- Correlation id in every request and event.
- Metrics: request count, latency, error rate, queue lag, payment failure rate.
- Traces across gateway, order, payment, and inventory.
- Structured logs with service name and trace id.

## Deployment notes

- Start with Docker Compose for local development.
- Move to Kubernetes manifests later.
- Add readiness and liveness probes.
- Use externalized configuration and secrets.

## Interview talking points

- Why these service boundaries were chosen.
- How consistency is handled without distributed transactions.
- How duplicate payment requests are prevented.
- How to debug a failed order across services.
- How to scale read-heavy catalog traffic separately from order writes.
