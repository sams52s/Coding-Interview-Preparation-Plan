# Unsupervised Learning

**Navigation:** [README](README.md) | Previous: [Supervised Learning](03_supervised_learning.md) | Next: [Model Evaluation](05_model_evaluation.md)

Unsupervised learning finds structure in data without explicit labels.

## Main use cases

- Customer segmentation.
- Document clustering.
- Topic discovery.
- Dimensionality reduction.
- Anomaly detection.
- Embedding exploration.

## Common methods

| Method | Use case | Notes |
|--------|----------|-------|
| K-means | compact clusters | choose K carefully, scale features |
| Hierarchical clustering | nested group structure | useful for exploration |
| DBSCAN | arbitrary cluster shape, noise | sensitive to distance parameters |
| PCA | dimensionality reduction | linear projection |
| t-SNE / UMAP | visualization | not usually a production feature by itself |
| Isolation Forest | anomaly detection | useful for rare abnormal behavior |

## Evaluation challenge

Without labels, evaluation is harder. Use:

- Business review of clusters.
- Silhouette score.
- Cluster stability across runs.
- Downstream task improvement.
- Human labeling for sampled results.

## Practical risks

- Clusters may not map to useful business segments.
- Distance metrics may be misleading.
- Scaling can change results dramatically.
- High-dimensional data can make similarity difficult.
- Results can be hard to explain to stakeholders.

## Interview focus

- Explain why unsupervised evaluation is difficult.
- Explain K-means assumptions.
- Explain dimensionality reduction.
- Explain anomaly detection as an unsupervised or semi-supervised task.
- Connect clustering output to a real product decision.

