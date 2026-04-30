# Intro to NLP

**Navigation:** [README](README.md) | Previous: [Metrics and Validation](05_metrics_and_validation.md) | Related: [LLM Application Patterns](../AI%20ML%20Models%20and%20ALGO/08_llm_application_patterns.md)

Natural language processing handles text and language data. Modern NLP ranges from basic text classification to large language model applications.

## Core concepts

- Token: a unit of text processed by a model.
- Vocabulary: known token set.
- Embedding: numeric vector representing text meaning.
- Sequence: ordered text input.
- Context window: maximum text length a model can process at once.
- Prompt: instruction or input given to a generative model.

## Common NLP tasks

| Task | Output | Example |
|------|--------|---------|
| Text classification | label | spam, sentiment, intent |
| Named entity recognition | spans and labels | person, company, location |
| Search/retrieval | ranked documents | support knowledge base search |
| Summarization | shorter text | ticket summary, meeting notes |
| Question answering | answer text | documentation assistant |
| Generation | new text | email draft, explanation, code |

## Text preparation

Traditional NLP may use:

- Lowercasing.
- Stop-word removal.
- Stemming or lemmatization.
- Bag-of-words or TF-IDF.

Modern neural NLP often uses:

- Subword tokenization.
- Pretrained embeddings.
- Transformer encoders or decoders.
- Retrieval-augmented generation for factual grounding.

## System-design concerns

- Latency and token cost.
- Prompt injection and unsafe outputs.
- Retrieval quality.
- Hallucination risk.
- Logging and privacy.
- Evaluation of answer quality.
- Fallback behavior when confidence is low.

## Interview focus

- Explain tokens and embeddings.
- Explain search vs generation.
- Explain why RAG is useful.
- Explain why LLM outputs need evaluation and guardrails.
- Explain how to serve an NLP model behind an API.

