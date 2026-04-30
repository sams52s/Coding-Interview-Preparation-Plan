# Model Training

**Navigation:** [README](README.md) | Previous: [Data Preparation](03_data_preparation.md) | Next: [Metrics and Validation](05_metrics_and_validation.md)

Model training is the process of finding parameters that reduce prediction error on training data while still generalizing to unseen data.

## Training loop

1. Load prepared data.
2. Choose a model family.
3. Define loss function and metrics.
4. Train on the training set.
5. Evaluate on validation data.
6. Tune hyperparameters.
7. Test once on a held-out test set.
8. Save the model artifact and metadata.

## Overfitting vs underfitting

| Problem | Symptom | Fixes |
|---------|---------|-------|
| Underfitting | poor train and validation performance | better features, larger model, train longer |
| Overfitting | strong train performance but weak validation performance | regularization, more data, simpler model, early stopping |

## Regularization

Regularization discourages the model from memorizing noise.

Common approaches:

- L1/L2 penalties.
- Dropout in neural networks.
- Early stopping.
- Data augmentation.
- Tree depth limits.
- Minimum samples per split or leaf.

## Hyperparameters

Hyperparameters are chosen before training and tuned with validation data.

Examples:

- Learning rate.
- Number of trees.
- Tree depth.
- Regularization strength.
- Batch size.
- Number of epochs.

## Model artifact contents

A production-ready model package should record:

- Model version.
- Training data version.
- Feature definitions.
- Metric results.
- Hyperparameters.
- Training date.
- Owner and rollback plan.

## Interview focus

- Explain the difference between parameters and hyperparameters.
- Explain how validation data is used.
- Explain early stopping.
- Explain why final test data should not be reused many times.
- Explain how to package a model for deployment.

