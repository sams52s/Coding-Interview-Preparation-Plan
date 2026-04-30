# LLM Application Patterns

**Navigation:** [README](README.md) | Previous: [Neural Networks](07_neural_networks.md) | Related: [Intro to NLP](../Basic%20AI%20%26%20ML/06_intro_to_nlp.md)

Large language model applications combine normal backend engineering with retrieval, prompting, evaluation, safety, and cost controls.

## Common patterns

| Pattern | Purpose |
|---------|---------|
| Direct prompting | ask the model to perform a task from the prompt alone |
| Retrieval-augmented generation | retrieve trusted context before generation |
| Tool/function calling | let the model request structured backend actions |
| Classification with LLMs | label text when rules or small models are not enough |
| Summarization | condense documents, tickets, chats, or logs |
| Extraction | convert text into structured fields |
| Agent workflow | multi-step planning with tools and state |

## RAG flow

1. Ingest documents.
2. Chunk text.
3. Create embeddings.
4. Store embeddings in a vector database.
5. Retrieve relevant chunks for a user query.
6. Build a grounded prompt.
7. Generate answer with citations or references.
8. Evaluate answer quality.

## Guardrails

- Input validation.
- Prompt-injection detection.
- Retrieval filtering and permissions.
- Output schema validation.
- Sensitive-data redaction.
- Safety policies.
- Human review for high-risk actions.

## Evaluation

Evaluate both behavior and system performance:

- Task success.
- Factuality and groundedness.
- Refusal correctness.
- Toxicity or unsafe output.
- Latency.
- Cost per request.
- User satisfaction.

## Cost and latency controls

- Cache stable prompts and retrieval results.
- Use smaller models for simple tasks.
- Batch offline jobs.
- Limit retrieved context size.
- Set token budgets.
- Stream responses for better perceived latency.
- Use fallback models or rules.

## Interview focus

- Explain RAG and why it reduces hallucination risk.
- Explain prompt injection and mitigation.
- Explain vector search at a high level.
- Explain tool calling safety.
- Explain how to evaluate an LLM feature before launch.

