# Supervised Learning

**Navigation:** [README](README.md) | Previous: [ML Workflow](02_ml_workflow.md) | Next: [Unsupervised Learning](04_unsupervised_learning.md)

Supervised learning trains on examples that include both input features and known labels.

## Problem types

| Type | Target | Examples |
|------|--------|----------|
| Classification | category | spam, fraud, churn, sentiment |
| Regression | number | price, ETA, demand, revenue |
| Ranking | ordered relevance | search results, feeds, recommendations |

## Common algorithms

| Algorithm | Strengths | Watch-outs |
|-----------|-----------|------------|
| Linear regression | simple, explainable | limited for nonlinear patterns |
| Logistic regression | strong baseline classification | needs good features |
| Decision tree | interpretable | can overfit |
| Random forest | robust tabular baseline | larger, less transparent |
| Gradient boosting | strong tabular performance | tuning and overfitting risk |
| SVM | useful on smaller datasets | can be expensive at scale |
| Neural network | flexible and powerful | needs more data and tuning |

## Choosing a model

Start simple when:

- Data is tabular.
- Explainability matters.
- Latency budget is tight.
- Dataset is small.

Use more complex models when:

- Simple baselines are not enough.
- Data is large and high quality.
- Inputs are text, image, audio, or complex sequences.
- The business value justifies the cost.

## Evaluation examples

- Fraud detection: recall, precision, PR-AUC, manual-review load.
- Churn prediction: ROC-AUC, calibration, lift in top deciles.
- ETA prediction: MAE, p95 absolute error.
- Search ranking: NDCG, MRR, click-through rate.

## Interview focus

- Explain classification vs regression.
- Explain why a decision tree can overfit.
- Explain when logistic regression is a strong choice.
- Explain class imbalance handling.
- Explain threshold tuning for business cost.

