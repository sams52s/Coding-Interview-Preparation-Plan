# Neural Networks

**Navigation:** [README](README.md) | Previous: [Feature Engineering](06_feature_engineering.md) | Next: [LLM Application Patterns](08_llm_application_patterns.md)

Neural networks are flexible models built from layers of learned transformations.

## Building blocks

- Neuron: computes a weighted combination of inputs.
- Layer: group of neurons.
- Activation function: adds nonlinearity.
- Loss function: objective optimized during training.
- Backpropagation: computes gradients.
- Optimizer: updates weights.
- Epoch: one pass over training data.

## Common architectures

| Architecture | Typical use |
|--------------|-------------|
| Feedforward network | tabular or generic features |
| CNN | image and spatial data |
| RNN/LSTM/GRU | sequence data, older NLP/time-series approaches |
| Transformer | language, retrieval, multimodal, modern sequence tasks |
| Autoencoder | compression, anomaly detection |

## Training concerns

- Data volume and quality.
- Learning rate.
- Batch size.
- Vanishing or exploding gradients.
- Overfitting.
- Hardware cost.
- Reproducibility.

## Regularization and stability

- Dropout.
- Weight decay.
- Batch normalization or layer normalization.
- Early stopping.
- Data augmentation.
- Gradient clipping.

## Deployment concerns

- Model size.
- Inference latency.
- GPU/CPU requirements.
- Quantization or distillation.
- Batch inference vs online inference.
- Monitoring quality and drift.

## Interview focus

- Explain backpropagation at a high level.
- Explain why neural networks need lots of data.
- Explain CNN vs RNN vs Transformer use cases.
- Explain overfitting controls.
- Explain serving cost and latency trade-offs.

