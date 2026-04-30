# Infra and ML

This folder connects infrastructure-as-code with production machine-learning integration.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [DevSecOps and Frontend](../DevSecOps%20and%20Frontend/README.md) | Next: [Cloud and Observability](../Cloud%20and%20Observability/README.md)

**Practice:** [Cost Calculator](../COST_CALCULATOR.md) | [Real-World Case Studies](../Real-World%20Case%20Studies/README.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md) | [Performance Benchmarks](../PERFORMANCE_BENCHMARKS.md)

## Folder files
- [26_infrastructure_as_code.md](26_infrastructure_as_code.md) — declarative infrastructure, Terraform fundamentals, state, modules, reproducibility, and deployment automation.
- [27_machine_learning_integration.md](27_machine_learning_integration.md) — model serving, inference patterns, application integration, latency, monitoring, and ML lifecycle concerns.
- [README.md](README.md) — this folder guide.

## How it connects
- Builds on [DevSecOps and Frontend](../DevSecOps%20and%20Frontend/README.md) by turning delivery and integration into repeatable infrastructure.
- Leads into [Cloud and Observability](../Cloud%20and%20Observability/README.md), where deployed systems are operated and monitored.
- Links to the AI/ML learning folders: [Basic AI & ML](../Basic%20AI%20%26%20ML/README.md) and [AI ML Models and ALGO](../AI%20ML%20Models%20and%20ALGO/README.md).
- Supports [Question Bank/cloud-devops.md](../Question%20Bank/cloud-devops.md), [Question Bank/advanced-topics.md](../Question%20Bank/advanced-topics.md), and [Question Bank/system-design.md](../Question%20Bank/system-design.md).

## Suggested reading order
1. [26_infrastructure_as_code.md](26_infrastructure_as_code.md)
2. [28_cloud_native_practices.md](../Cloud%20and%20Observability/28_cloud_native_practices.md)
3. [27_machine_learning_integration.md](27_machine_learning_integration.md)
4. [29_observability_monitoring.md](../Cloud%20and%20Observability/29_observability_monitoring.md)

## Production ML considerations
- Batch vs real-time inference.
- Model versioning, rollback, and canary release.
- Feature pipeline ownership and data contracts.
- Latency vs accuracy vs cost.
- Model monitoring: drift, bias, quality, error rate, and inference latency.

## Deep-dive practice
- Terraform module structure: split network, database, compute, secrets, monitoring, and deployment concerns.
- Model registry flow: track model version, training data, evaluation metrics, approval, rollout, and rollback.
- Feature-store consistency: explain online/offline feature parity and point-in-time correctness.
- Model rollout: compare canary, shadow, A/B, and blue/green deployment for inference services.
- Inference cost: estimate CPU vs GPU trade-offs using throughput, latency target, utilization, and traffic shape.
