# ML Basics

**Navigation:** [README](README.md) | Previous: [AI Overview](01_ai_overview.md) | Next: [Data Preparation](03_data_preparation.md)

Machine learning is about learning useful patterns from data and applying those patterns to new examples.

## Learning paradigms

| Paradigm | Input data | Goal | Examples |
|----------|------------|------|----------|
| Supervised learning | Features plus labels | Predict labels or values | Spam detection, credit risk, delivery ETA |
| Unsupervised learning | Features without labels | Find hidden structure | Customer segments, anomaly grouping |
| Reinforcement learning | Agent actions and rewards | Learn actions that maximize reward | Games, robotics, optimization |
| Self-supervised learning | Data creates its own training signal | Learn representations | Language models, embeddings |

## Basic workflow

1. Define the business problem.
2. Decide the prediction target.
3. Collect and clean data.
4. Split into train, validation, and test sets.
5. Train a baseline model.
6. Evaluate with the right metrics.
7. Improve features, model, and thresholds.
8. Deploy, monitor, and retrain when needed.

## Important concepts

- Feature: an input signal used by the model.
- Label: the value the model should learn to predict.
- Training set: examples used to fit the model.
- Validation set: examples used to tune choices.
- Test set: examples used for final unbiased evaluation.
- Loss function: what the model optimizes during training.
- Overfitting: performs well on training data but poorly on new data.
- Underfitting: too simple to capture the pattern.

## Baseline first

Start with a simple baseline before using complex models:

- Majority class for classification.
- Mean or median for regression.
- Logistic regression or decision tree for tabular data.
- Simple keyword rules for text classification.

Baselines make it easier to prove that complexity is worth the cost.

## Interview focus

- Explain supervised vs unsupervised learning.
- Explain overfitting and how to reduce it.
- Explain why train/test separation matters.
- Choose a metric based on business risk.
- Discuss when a non-ML rule is preferable.

