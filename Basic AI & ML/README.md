# Basic AI & ML

This folder is the beginner AI/ML starting point. It should prepare the concepts needed before reading ML integration and system-design material.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | Related: [Infra and ML](../Infra%20and%20ML/README.md) | Related: [AI ML Models and ALGO](../AI%20ML%20Models%20and%20ALGO/README.md)

**Practice:** [ML Systems Track](../INTERVIEW_TRACKS.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md) | [Cost Calculator](../COST_CALCULATOR.md)

## Files
- [01_ai_overview.md](01_ai_overview.md) — AI vs ML vs deep learning vs generative AI.
- [02_ml_basics.md](02_ml_basics.md) — supervised, unsupervised, reinforcement, and self-supervised learning.
- [03_data_preparation.md](03_data_preparation.md) — cleaning, splitting, leakage prevention, missing values, scaling, and imbalance.
- [04_model_training.md](04_model_training.md) — training loops, overfitting, regularization, hyperparameters, and model artifacts.
- [05_metrics_and_validation.md](05_metrics_and_validation.md) — classification, regression, ranking metrics, validation, and threshold tuning.
- [06_intro_to_nlp.md](06_intro_to_nlp.md) — tokens, embeddings, NLP tasks, RAG basics, and serving concerns.
- [README.md](README.md) — this starter guide.

## How it connects
- Gives foundation for [27_machine_learning_integration.md](../Infra%20and%20ML/27_machine_learning_integration.md).
- Complements [AI ML Models and ALGO](../AI%20ML%20Models%20and%20ALGO/README.md), which contains the algorithm/model detail notes.
- Supports system-design discussions involving recommendation systems, fraud detection, search ranking, model serving, and LLM-backed applications.

## ML pipeline stages
1. Data collection and preparation.
2. Exploratory data analysis.
3. Feature engineering and selection.
4. Model selection and training.
5. Hyperparameter tuning.
6. Model evaluation and validation.
7. Deployment, monitoring, and retraining.

## Common beginner mistakes
- Data leakage during preprocessing.
- Not handling imbalanced datasets.
- Using accuracy alone when precision/recall matter more.
- Ignoring feature scaling for distance-based algorithms.
- Overfitting on a small dataset.
- Not monitoring model quality after deployment.
