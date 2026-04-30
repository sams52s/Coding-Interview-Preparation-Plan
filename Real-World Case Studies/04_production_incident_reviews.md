# Production Incident Reviews

Navigation: [Case Studies](README.md) | [Observability](../Cloud%20and%20Observability/29_observability_monitoring.md) | [Resilience Patterns](../Microservices%20and%20Architecture/15_resilience_patterns.md) | [Mock Scorecard](../MOCK_INTERVIEW_SCORECARD.md)

These anonymized examples show how to discuss production incidents in interviews. The goal is not blame. The goal is clear root-cause analysis, user impact, mitigation, long-term fixes, and learning.

## Incident review template

| Section | Questions to answer |
|---------|---------------------|
| Summary | What happened in one paragraph? |
| Impact | Who was affected? How much? For how long? |
| Detection | How did the team notice? Alert, customer report, dashboard? |
| Timeline | What happened minute by minute? |
| Root cause | What technical and process failures allowed it? |
| Mitigation | What stopped the bleeding? |
| Permanent fix | What prevents recurrence? |
| Follow-ups | Owners, due dates, tests, dashboards, runbooks |

## Incident 1: Cache stampede after deployment

**Summary:** A catalog service deployed with a changed cache key format. Most requests missed cache at the same time and overloaded the database.

**Impact:** Elevated latency and partial 5xx errors for catalog reads for 28 minutes.

**Root cause:**

- New cache key version caused global cold cache.
- No request coalescing for hot keys.
- Database connection pool allowed too many concurrent expensive queries.
- Load test did not simulate cold-cache deployment.

**Mitigation:**

- Rolled back the key change.
- Temporarily increased cache TTL for hot catalog entries.
- Reduced API concurrency to protect the database.

**Permanent fixes:**

- Add cache warming for high-traffic keys.
- Add single-flight/request coalescing.
- Add circuit breaker around database-heavy fallback.
- Add cold-cache load test to release checklist.

**Interview lesson:** Caches are not just speed tools; they are load-bearing architecture. Explain what happens when cache disappears.

## Incident 2: Duplicate payment capture

**Summary:** A retrying worker captured payment twice for a small set of completed orders.

**Impact:** Duplicate charges for affected users. Financial correction and support outreach required.

**Root cause:**

- Payment operation was retried without a stable idempotency key.
- Worker retried after network timeout even though the provider had completed the capture.
- Internal order status was updated after provider response, leaving a race window.

**Mitigation:**

- Disabled the retrying worker.
- Queried provider transaction state.
- Refunded duplicate captures.

**Permanent fixes:**

- Require idempotency keys for all payment mutations.
- Store payment intent before external call.
- Reconcile provider state asynchronously.
- Add alerts for duplicate provider transaction IDs per order.

**Interview lesson:** Money movement needs idempotency, reconciliation, and auditability.

## Incident 3: Regional outage without clean failover

**Summary:** A regional dependency outage caused API timeouts. Traffic manager failed to shift traffic because health checks were too shallow.

**Impact:** Users in one region saw elevated errors for 45 minutes.

**Root cause:**

- Health check only tested process liveness, not dependency health.
- Failover runbook was outdated.
- Some clients cached regional endpoints too long.

**Mitigation:**

- Manually shifted traffic.
- Lowered DNS TTL.
- Disabled the failing dependency path with a feature flag.

**Permanent fixes:**

- Add synthetic transactions to health checks.
- Practice failover quarterly.
- Move critical clients to dynamic endpoint discovery.
- Add brownout mode for non-critical features.

**Interview lesson:** Multi-region architecture is only real if failover is tested.

## Incident 4: Message queue backlog broke SLAs

**Summary:** A slow consumer caused notification delays after a traffic spike.

**Impact:** Users received delayed email and push notifications for two hours.

**Root cause:**

- Consumer scaled on CPU, not queue lag.
- Poison messages retried indefinitely.
- No separate priority queue for transactional notifications.

**Mitigation:**

- Increased consumer replicas.
- Moved poison messages to a dead-letter queue.
- Prioritized payment/security notifications.

**Permanent fixes:**

- Autoscale on consumer lag and age of oldest message.
- Add DLQ with replay tooling.
- Separate critical and marketing notification queues.
- Add backpressure and rate limits.

**Interview lesson:** Queue depth alone is not enough; age of oldest message often better reflects user impact.

