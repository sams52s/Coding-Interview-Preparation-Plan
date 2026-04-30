# System Design Cost Calculator

Navigation: [Main README](README.md) | [System Design Template](SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) | [Cloud and Observability](Cloud%20and%20Observability/README.md) | [Performance Benchmarks](PERFORMANCE_BENCHMARKS.md)

Use this file to make rough cost estimates during system design practice. Exact pricing changes over time, so use this as a structure, not a price source.

## Step 1: Traffic estimate

| Metric | Value | Notes |
|--------|-------|-------|
| Monthly active users | | |
| Daily active users | | |
| Requests per user per day | | |
| Average requests per second | | |
| Peak multiplier | | |
| Peak requests per second | | |

## Step 2: Storage estimate

| Data type | Size per item | Items per day | Retention | Total |
|-----------|---------------|---------------|-----------|-------|
| Primary records | | | | |
| Logs | | | | |
| Metrics | | | | |
| Object/blob data | | | | |
| Backups | | | | |

## Step 3: Compute estimate

| Workload | Unit | Estimate |
|----------|------|----------|
| API instances | number of instances | |
| Worker instances | number of workers | |
| CPU/memory size | per instance | |
| Autoscaling headroom | percentage | |
| Batch jobs | runtime per day | |

## Step 4: Database estimate

| Item | Estimate |
|------|----------|
| Primary database size | |
| Read replicas | |
| Write throughput | |
| Read throughput | |
| Backup storage | |
| Multi-region replication | |

## Step 5: Network estimate

| Item | Estimate |
|------|----------|
| Ingress traffic | |
| Egress traffic | |
| CDN traffic | |
| Inter-region traffic | |

## Step 6: Observability estimate

| Signal | Volume | Retention | Notes |
|--------|--------|-----------|-------|
| Logs | | | |
| Metrics | | | |
| Traces | | | |
| Audit events | | | |

## Common cost drivers

- Egress bandwidth.
- High-cardinality metrics.
- Long log retention.
- Large managed database instances.
- Cross-region replication.
- GPU inference.
- Overprovisioned Kubernetes nodes.
- Excessive cache memory.

## Optimization checklist

- [ ] Cache hot reads.
- [ ] Use CDN for static or cacheable content.
- [ ] Reduce payload size.
- [ ] Tune log level and retention.
- [ ] Use autoscaling with sane limits.
- [ ] Right-size database and indexes.
- [ ] Avoid unnecessary cross-region traffic.
- [ ] Batch non-urgent work.

## Interview phrasing

```text
The biggest cost driver here is likely ...
I would first estimate traffic, storage, and peak load.
For early scale, I would choose managed services to reduce operational cost.
At larger scale, I would optimize the most expensive dimension: compute, storage, bandwidth, or observability.
```
