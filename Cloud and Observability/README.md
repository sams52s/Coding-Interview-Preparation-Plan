# Cloud and Observability

This folder closes the system-design path with cloud-native deployment practices and the observability stack needed to operate systems after release.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [Infra and ML](../Infra%20and%20ML/README.md) | Next: [Question Bank](../Question%20Bank/README.md)

**Practice:** [Production Readiness Checklist](../PRODUCTION_READINESS_CHECKLIST.md) | [Cost Calculator](../COST_CALCULATOR.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md)

## Folder files
- [28_cloud_native_practices.md](28_cloud_native_practices.md) — cloud-native design, containers, Kubernetes, managed services, scaling, deployment patterns, and cloud trade-offs.
- [29_observability_monitoring.md](29_observability_monitoring.md) — metrics, logs, traces, alerting, SLOs, OpenTelemetry, Prometheus, Grafana, and debugging workflows.
- [advanced_multi_region_chaos_engineering.md](advanced_multi_region_chaos_engineering.md) — active-active vs active-passive, cell-based architecture, chaos experiments, and reliability patterns.
- [README.md](README.md) — this folder guide.

## How it connects
- Builds on [Infra and ML](../Infra%20and%20ML/README.md) and [DevSecOps and Frontend](../DevSecOps%20and%20Frontend/README.md).
- Supports final interview revision in the [learning roadmap](../README.md#learning-roadmap), especially cloud-native concepts and observability.
- Links back to [10_monitoring_observability.md](../Core%20Infrastructure%20Components/10_monitoring_observability.md) and [06_observability_monitoring.md](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/06_observability_monitoring.md).
- Supports [Question Bank/cloud-devops.md](../Question%20Bank/cloud-devops.md), [Question Bank/security.md](../Question%20Bank/security.md), and [Question Bank/system-design.md](../Question%20Bank/system-design.md).

## Suggested reading order
1. [28_cloud_native_practices.md](28_cloud_native_practices.md)
2. [29_observability_monitoring.md](29_observability_monitoring.md)
3. [advanced_multi_region_chaos_engineering.md](advanced_multi_region_chaos_engineering.md)
4. [Question Bank](../Question%20Bank/README.md) and [Mock Interview Scorecard](../MOCK_INTERVIEW_SCORECARD.md) for final revision and mock interview practice.

## Cloud-native checklist
- Externalized configuration and environment parity.
- Containers, orchestration, readiness/liveness checks.
- Horizontal and vertical autoscaling.
- Multi-zone and multi-region resilience.
- Managed service trade-offs.
- IAM, network policies, encryption, and secrets.
- Cost tagging, budgets, and rightsizing.

## Observability maturity levels
- Level 1: structured logs and basic dashboards.
- Level 2: metrics, alerts, and SLOs.
- Level 3: distributed tracing and correlation IDs.
- Level 4: incident playbooks, anomaly detection, and continuous improvement.

## Deep-dive practice
- Compare active-passive, active-active, cell-based, and edge-plus-regional architectures.
- Design a chaos experiment with hypothesis, blast radius, metrics, and rollback.
- Explain how SLOs, alerts, traces, and incident runbooks work together.
- Use [Real-World Production Incidents](../Real-World%20Case%20Studies/04_production_incident_reviews.md) to practice root cause analysis.
- Estimate cost and reliability trade-offs with [COST_CALCULATOR.md](../COST_CALCULATOR.md).
