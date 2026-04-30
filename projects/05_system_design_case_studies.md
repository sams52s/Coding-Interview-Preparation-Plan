# Project 05: System Design Case Studies

Navigation: [Projects](README.md) | [Stage 4](../README.md#learning-roadmap) | [System Design Template](../SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) | [Real-World Case Studies](../Real-World%20Case%20Studies/README.md) | [Cost Calculator](../COST_CALCULATOR.md)

## Goal

Build a reusable library of system-design answers using the same structure every time: requirements, APIs, data model, architecture, scaling, trade-offs, observability, security, and cost.

Use [SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md](../SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) as the base for every case.

## Case study list

| Case | Main topics | Related chapters |
|------|-------------|------------------|
| URL Shortener | hashing, redirects, caching, analytics | 01, 06, 07, 08, 10 |
| File Storage System | object storage, metadata, CDN, consistency | 05, 08, 19, 28 |
| Notification Service | queues, retries, DLQ, rate limits | 09, 12, 15 |
| Real-Time Chat | WebSocket, ordering, presence, fanout | 09, 12, 18 |
| Payment Processor | idempotency, ledger, saga, audit | 16, 17, 19 |
| News Feed | ranking, fanout, caching, data modeling | 07, 08, 12, 20 |
| Search Service | indexing, ranking, async ingestion | 08, 12, 18, 21 |
| Recommendation System | ML inference, feature freshness, monitoring | 27, 28, 29 |
| API Rate Limiter | token bucket, Redis, consistency, fail-open/fail-closed | 06, 07, 15 |
| Distributed Job Scheduler | leases, leader election, retries, idempotency | 16, 17, 18 |

## Completion standard for each case

- [ ] Clear functional and non-functional requirements.
- [ ] Capacity estimates.
- [ ] API contract.
- [ ] Data model and storage choice.
- [ ] High-level architecture diagram.
- [ ] Critical flows.
- [ ] Scaling plan.
- [ ] Failure scenarios.
- [ ] Security and abuse prevention.
- [ ] Observability and alerting.
- [ ] Cost estimate.
- [ ] Final trade-off summary.

## Practice cadence

- Week 4: write two full designs.
- Week 5: do three timed 45-minute designs.
- Before interviews: rehearse one design aloud each day.
