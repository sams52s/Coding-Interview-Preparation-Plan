# Documentation Style Guide

Navigation: [Main README](README.md) | [Project Architecture](PROJECT_ARCHITECTURE.md)

Use this guide to keep the repository consistent as it grows.

## File structure

Each topic file should use this shape when applicable:

1. Title.
2. Navigation line.
3. Short purpose statement.
4. Concept explanation.
5. Practical example or diagram.
6. Interview focus.
7. Common mistakes or trade-offs.
8. Links to related docs.

## Tone

- Write as a practical interview-preparation guide.
- Prefer concise explanations over academic wording.
- Use concrete examples and trade-offs.
- Avoid unsupported claims like "always" or "never" unless truly absolute.
- Make failure modes explicit in system design docs.

## Link style

- Link to the nearest relevant `README.md` at the top of each file.
- Use relative links.
- When adding a new file, update the folder README and the nearest parent README.
- Root-level major docs should also be listed in [README.md](README.md).

## Tables

Use tables for:

- Comparisons.
- Decision guides.
- File maps.
- Trade-offs.
- Metrics and checklists.

## Diagrams

Use Mermaid diagrams for:

- System architecture.
- Request flows.
- State machines.
- Replication or event flows.

Keep diagrams readable. If a diagram has more than 12 nodes, split it into smaller diagrams.

## Code examples

- Prefer Java or Spring Boot examples when the concept is backend-related.
- Show before/after examples for refactoring.
- Add comments only where they clarify non-obvious decisions.
- Include edge cases and complexity for algorithm examples.

## System design docs

Every major system design case should include:

- Requirements.
- Scale assumptions.
- API sketch.
- Data model.
- Architecture diagram.
- Scaling strategy.
- Consistency model.
- Failure handling.
- Observability.
- Security.
- Cost discussion.
- Interview talking points.

