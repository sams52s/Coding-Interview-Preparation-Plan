# Metrics and Validation

**Navigation:** [README](README.md) | Previous: [Model Training](04_model_training.md) | Next: [Intro to NLP](06_intro_to_nlp.md)

The right metric depends on the business problem. A model can have a high generic score and still fail the product goal.

## Classification metrics

| Metric | Use when |
|--------|----------|
| Accuracy | classes are balanced and mistakes have similar cost |
| Precision | false positives are expensive |
| Recall | false negatives are expensive |
| F1 | precision and recall both matter |
| ROC-AUC | ranking positives above negatives matters |
| PR-AUC | positive class is rare |

## Regression metrics

| Metric | Use when |
|--------|----------|
| MAE | errors have roughly linear business cost |
| RMSE | large errors should be punished more |
| MAPE | relative percent error is useful and target is not near zero |
| R-squared | explain variance, mostly for analysis |

## Ranking and recommendation metrics

- Precision@K: how many top K results are relevant.
- Recall@K: how many relevant items appear in top K.
- NDCG: rewards relevant items appearing higher.
- MRR: measures first relevant result position.
- Click-through rate: product behavior metric, but can be biased.

## Validation strategies

- Random split: good for independent samples.
- Time-based split: good for time-series or event streams.
- Group split: keeps same user/account/entity out of multiple splits.
- K-fold cross-validation: useful for small tabular datasets.
- Online A/B test: validates production impact.

## Threshold tuning

Many classifiers output probabilities. The threshold should match business risk.

Examples:

- Fraud detection may prefer high recall.
- Account blocking may require high precision.
- Medical triage may use multiple thresholds and human review.

## Interview focus

- Explain confusion matrix terms.
- Explain precision vs recall with a concrete example.
- Explain why accuracy can be dangerous for imbalanced data.
- Pick a metric for fraud, search ranking, recommendation, and ETA prediction.
- Explain offline vs online evaluation.

