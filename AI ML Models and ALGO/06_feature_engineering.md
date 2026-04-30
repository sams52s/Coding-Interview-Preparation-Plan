# Feature Engineering

**Navigation:** [README](README.md) | Previous: [Model Evaluation](05_model_evaluation.md) | Next: [Neural Networks](07_neural_networks.md)

Feature engineering turns raw data into useful model inputs. Good features often beat complex models on real business problems.

## Feature types

| Feature type | Examples |
|--------------|----------|
| Numeric | price, age, count, duration |
| Categorical | country, plan type, device |
| Text | title, query, review, ticket |
| Time | hour, weekday, season, recency |
| Aggregate | purchases in last 7 days, failed logins in last hour |
| Embedding | user vector, item vector, document vector |

## Common techniques

- Scaling and normalization.
- One-hot encoding.
- Target encoding with leakage protection.
- Bucketing or binning.
- Log transforms for skewed values.
- Rolling-window aggregations.
- Text embeddings.
- Feature crosses.

## Leakage prevention

Feature engineering must respect prediction time.

Bad examples:

- Using future events in a past prediction.
- Computing aggregates with data after the prediction timestamp.
- Encoding categories using labels from validation or test data.

Good practice:

- Use point-in-time correct features.
- Split data before fitting transformations.
- Version feature definitions.
- Test training-serving consistency.

## Feature stores

A feature store helps share and reuse features across teams. It can provide:

- Offline features for training.
- Online features for low-latency inference.
- Feature versioning.
- Consistency checks.
- Ownership metadata.

## Interview focus

- Explain how feature engineering improves model quality.
- Explain categorical encoding choices.
- Explain point-in-time correctness.
- Explain online vs offline feature stores.
- Give examples of features for fraud, recommendation, or ETA prediction.

