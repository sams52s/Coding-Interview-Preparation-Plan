# AI Overview

**Navigation:** [README](README.md) | Next: [ML Basics](02_ml_basics.md) | Related: [AI ML Models and ALGO](../AI%20ML%20Models%20and%20ALGO/README.md)

This note gives the vocabulary needed before discussing ML-backed systems in interviews.

## Core definitions

| Term | Meaning | Interview example |
|------|---------|-------------------|
| Artificial intelligence | Systems that perform tasks that normally require human intelligence | Search, planning, game playing, assistants |
| Machine learning | AI systems that learn patterns from data | Fraud detection, recommendations, churn prediction |
| Deep learning | ML using multi-layer neural networks | Vision, speech, language models |
| Generative AI | Models that create text, images, code, audio, or structured output | Chatbots, summarization, content generation |

## Common AI problem types

- Classification: predict a category, such as fraud or not fraud.
- Regression: predict a numeric value, such as delivery time or price.
- Ranking: order results by relevance, risk, or likelihood.
- Clustering: group similar data without labels.
- Anomaly detection: identify unusual patterns.
- Generation: create new content from a prompt or context.

## What interviewers expect

- You can explain the difference between AI, ML, deep learning, and generative AI.
- You know when a rule-based system is enough and when ML is useful.
- You understand that ML quality depends heavily on data quality.
- You can discuss model trade-offs: accuracy, latency, cost, explainability, and safety.

## System-design connection

ML systems add components that normal backend systems do not always need:

- Training data pipelines.
- Feature generation.
- Model registry and versioning.
- Online inference service.
- Batch scoring jobs.
- Monitoring for data drift and model quality.
- Human review or fallback paths for risky predictions.

## Quick review checklist

- Can I define AI, ML, DL, and GenAI in one sentence each?
- Can I map a product feature to classification, regression, ranking, clustering, or generation?
- Can I explain why data leakage makes evaluation unreliable?
- Can I explain how a model is served behind a normal API?

