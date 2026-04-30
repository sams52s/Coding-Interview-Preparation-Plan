# ML Workflow

**Navigation:** [README](README.md) | Previous: [AI Fundamentals](01_ai_fundamentals.md) | Next: [Supervised Learning](03_supervised_learning.md)

An ML workflow turns raw data into a monitored model running in production.

## End-to-end lifecycle

1. Problem framing.
2. Data collection.
3. Label definition.
4. Data validation.
5. Feature engineering.
6. Model training.
7. Offline evaluation.
8. Model packaging.
9. Deployment.
10. Online monitoring.
11. Retraining and rollback.

## Important artifacts

| Artifact | Purpose |
|----------|---------|
| Dataset version | Reproduce training and evaluation |
| Feature definition | Keep training and serving logic consistent |
| Model artifact | Saved trained model |
| Model card | Documents use case, metrics, limitations, risks |
| Evaluation report | Shows quality and trade-offs |
| Deployment config | Defines runtime, resources, thresholds |

## Offline to online gap

Offline metrics do not always predict production results because:

- User behavior changes.
- Data distribution changes.
- Feedback loops appear.
- Latency affects user experience.
- Product metrics depend on more than model score.

## Monitoring

Monitor both system health and model quality:

- Request rate and latency.
- Error rate.
- Feature missingness.
- Input distribution drift.
- Prediction distribution drift.
- Business outcome metrics.
- Human feedback or labels when available.

## Interview focus

- Explain why ML systems need versioned data and models.
- Explain training-serving skew.
- Explain rollback for bad model releases.
- Explain online A/B testing.
- Explain drift monitoring.

