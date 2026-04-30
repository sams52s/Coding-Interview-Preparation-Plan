# Real-World Case Studies

Navigation: [Main README](../README.md) | [System Design Template](../SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md) | [Question Bank](../Question%20Bank/README.md)

This folder contains complete, interview-ready case studies. Each case follows the repository's system-design template: requirements, APIs, data model, architecture, scaling plan, consistency choices, failure handling, observability, security, cost, and interview talking points.

## Case study map

| File | Scenario | Main concepts |
|------|----------|---------------|
| [01_netflix_streaming_platform.md](01_netflix_streaming_platform.md) | Netflix-style video streaming | CDN, encoding pipeline, metadata, recommendations, multi-region, observability |
| [02_uber_ride_matching.md](02_uber_ride_matching.md) | Uber-style ride matching | geo-indexing, real-time dispatch, driver location, pricing, consistency, event streams |
| [03_instagram_feed_media.md](03_instagram_feed_media.md) | Instagram-style feed and media platform | feed ranking, fanout, media storage, social graph, timelines, abuse protection |
| [04_production_incident_reviews.md](04_production_incident_reviews.md) | Production incident RCA examples | root cause analysis, fixes, prevention, runbooks |
| [05_interview_war_stories.md](05_interview_war_stories.md) | Anonymized interview scenarios | communication, recovery, trade-offs, interviewer signals |

## How to use these cases

1. Read the problem statement and requirements.
2. Draw the Mermaid diagram from memory.
3. Explain the data model and APIs out loud.
4. Pick trade-offs deliberately: consistency vs latency, cost vs reliability, simplicity vs scale.
5. Score your answer with [MOCK_INTERVIEW_SCORECARD.md](../MOCK_INTERVIEW_SCORECARD.md).

## Standard case-study answer shape

Use this order in interviews:

1. Clarify requirements and scale.
2. Define APIs and data model.
3. Sketch high-level architecture.
4. Deep-dive into one or two bottlenecks.
5. Cover reliability, security, observability, and cost.
6. Summarize trade-offs and follow-up improvements.

