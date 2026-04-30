# AI Fundamentals

**Navigation:** [README](README.md) | Next: [ML Workflow](02_ml_workflow.md) | Related: [Basic AI Overview](../Basic%20AI%20%26%20ML/01_ai_overview.md)

This file collects the model-side fundamentals used across ML and AI system design.

## Task mapping

| Product need | ML task | Typical output |
|--------------|---------|----------------|
| Detect fraud | classification or anomaly detection | risk score or fraud label |
| Predict delivery time | regression | numeric ETA |
| Show best feed items | ranking/recommendation | ordered list |
| Group customers | clustering | segment ID |
| Answer user questions | retrieval plus generation | text answer |

## Key model concepts

- Features: input signals.
- Target: value to predict.
- Loss: training objective.
- Parameters: values learned during training.
- Hyperparameters: settings chosen before training.
- Inference: running the trained model on new input.
- Calibration: predicted probabilities match real-world likelihood.

## Bias and variance

- High bias: model is too simple and misses real patterns.
- High variance: model is too sensitive to training data noise.
- Good models balance both through features, data, regularization, and model choice.

## Practical trade-offs

| Trade-off | Why it matters |
|-----------|----------------|
| Accuracy vs latency | realtime APIs cannot wait for slow models |
| Accuracy vs explainability | regulated domains may need simpler models |
| Cost vs quality | large models can be expensive at scale |
| Freshness vs stability | frequent retraining can improve quality but add risk |
| Automation vs review | high-risk outputs may need human approval |

## Interview focus

- Start with the business goal, then map to ML task.
- Define success metrics before naming algorithms.
- Explain model deployment and monitoring.
- Discuss fallback behavior for bad or uncertain predictions.

