# Model Evaluation

**Navigation:** [README](README.md) | Previous: [Unsupervised Learning](04_unsupervised_learning.md) | Next: [Feature Engineering](06_feature_engineering.md)

Model evaluation decides whether a model is good enough to use and safe enough to ship.

## Evaluation layers

1. Offline validation on held-out data.
2. Slice analysis across important user or data segments.
3. Stress tests for edge cases.
4. Shadow deployment with real traffic but no user impact.
5. Online experiment or gradual rollout.
6. Post-release monitoring.

## Metrics by task

| Task | Metrics |
|------|---------|
| Classification | precision, recall, F1, ROC-AUC, PR-AUC, calibration |
| Regression | MAE, RMSE, p95 error, MAPE when appropriate |
| Ranking | NDCG, MRR, precision@K, recall@K |
| Generation | human rating, factuality, toxicity, task success, latency, cost |
| Anomaly detection | alert precision, recall on known events, investigation time |

## Slice analysis

Always evaluate important segments separately:

- New vs returning users.
- Regions or languages.
- Device types.
- High-value vs low-value accounts.
- Minority classes.
- Recent data vs older data.

## Calibration

Calibration checks whether probabilities mean what they say. If a model assigns 0.8 probability, roughly 80 percent of those cases should be positive.

Calibration matters for:

- Risk scores.
- Human review queues.
- Pricing.
- Medical or financial decisions.
- Alert thresholds.

## Interview focus

- Pick metrics based on business cost.
- Explain why aggregate metrics can hide segment failures.
- Explain offline vs online evaluation.
- Explain model calibration.
- Explain rollout safety and rollback.

