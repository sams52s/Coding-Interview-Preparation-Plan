# Data Preparation

**Navigation:** [README](README.md) | Previous: [ML Basics](02_ml_basics.md) | Next: [Model Training](04_model_training.md)

Data preparation often decides whether an ML system succeeds. In interviews, strong data reasoning is usually more valuable than naming advanced models.

## Main steps

1. Identify data sources.
2. Define labels clearly.
3. Remove duplicate or corrupt records.
4. Handle missing values.
5. Normalize or standardize numeric features when needed.
6. Encode categorical values.
7. Split data without leakage.
8. Document assumptions and data quality risks.

## Data leakage

Data leakage happens when training data includes information that would not be available at prediction time.

Examples:

- Using a field created after the outcome.
- Randomly splitting time-series data instead of splitting by time.
- Scaling features using all data before splitting.
- Including duplicate users in both train and test sets.

## Common transformations

| Data type | Typical preparation |
|-----------|---------------------|
| Numeric | missing-value handling, scaling, clipping outliers |
| Categorical | one-hot encoding, target encoding, grouping rare values |
| Text | normalization, tokenization, embeddings |
| Time | hour, day, seasonality, recency, rolling windows |
| Images/audio | resizing, normalization, augmentation |

## Imbalanced data

When one class is rare, accuracy can be misleading.

Options:

- Use precision, recall, F1, PR-AUC, or cost-weighted metrics.
- Adjust decision thresholds.
- Re-sample carefully.
- Use class weights.
- Add human review for uncertain cases.

## Interview checklist

- Can I explain how labels are created?
- Can I identify leakage risks?
- Can I choose a split strategy for user data, time-series data, or transactions?
- Can I explain missing-value handling?
- Can I explain why feature scaling matters for some algorithms?

