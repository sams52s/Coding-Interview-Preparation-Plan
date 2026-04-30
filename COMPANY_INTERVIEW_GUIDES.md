# Company-Specific Interview Guides

Navigation: [Main README](README.md) | [Interview Tracks](INTERVIEW_TRACKS.md) | [Question Bank](Question%20Bank/README.md) | [Mock Scorecard](MOCK_INTERVIEW_SCORECARD.md)

This guide maps common company interview styles to the repository content. Treat it as a preparation lens, not a guarantee of any specific company's process.

## Quick map

| Company style | Likely emphasis | Best track |
|---------------|-----------------|------------|
| Google / Meta / Microsoft | coding, system design, fundamentals, communication | Backend + System Design |
| Amazon | leadership principles, scalable systems, coding | Backend + System Design |
| Stripe / Square / fintech | API design, payments, correctness, idempotency | Payment / Trading Systems |
| Netflix / Uber | distributed systems, reliability, scale, data flows | Backend + System Design |
| AWS / Azure / GCP | cloud, infrastructure, operations, reliability | DevOps / SRE |
| LinkedIn / enterprise SaaS | Java/Spring, APIs, data modeling, systems | Backend Engineer |
| ML-heavy product teams | model serving, ranking, data pipelines, observability | ML Systems Engineer |

## Google style

Focus:
- DSA fluency under time pressure.
- Clear problem clarification and edge-case analysis.
- System-design structure for senior roles.
- Communication, collaboration, and humility while iterating.

Study:
- [DAILY_PROBLEM_LOG.md](DAILY_PROBLEM_LOG.md)
- [BLIND_75_PROBLEM_MAPPING.md](BLIND_75_PROBLEM_MAPPING.md)
- [System Design Case Study Template](SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md)
- [Question Bank/dsa.md](Question%20Bank/dsa.md)
- [Question Bank/system-design.md](Question%20Bank/system-design.md)
- [Real-World Case Studies](Real-World%20Case%20Studies/README.md)

Practice emphasis:

| Area | What to show |
|------|--------------|
| Coding | brute force first, optimized solution, complexity, edge cases, clean implementation |
| Data structures | arrays, strings, hash maps, heaps, trees, graphs, dynamic programming |
| System design | requirements, scale, APIs, data model, bottlenecks, reliability |
| Communication | ask clarifying questions, narrate decisions, accept hints productively |

## Meta / Microsoft style

Focus:
- Coding speed and correctness.
- Practical product/API thinking.
- Clean object modeling and maintainable code.
- System design for senior roles.

Study:
- [BLIND_75_PROBLEM_MAPPING.md](BLIND_75_PROBLEM_MAPPING.md)
- [CODE_REFACTORING_EXAMPLES.md](CODE_REFACTORING_EXAMPLES.md)
- [Question Bank/lld.md](Question%20Bank/lld.md)
- [System Design Fundamentals](System%20Design%20Fundamentals/README.md)

## Amazon style

Focus:
- Ownership and behavioral stories.
- Scalable service design.
- Operational excellence.
- Clear decision making.
- Syntactically correct code, edge cases, validation, and robust tests.

Study:
- [BEHAVIORAL_ANSWER_BANK.md](BEHAVIORAL_ANSWER_BANK.md)
- [INTERVIEW_SIMULATION_GUIDE.md](INTERVIEW_SIMULATION_GUIDE.md)
- [Cloud and Observability](Cloud%20and%20Observability/README.md)
- [Production Readiness Checklist](PRODUCTION_READINESS_CHECKLIST.md)
- [Real-World Production Incidents](Real-World%20Case%20Studies/04_production_incident_reviews.md)

Practice emphasis:

| Area | What to show |
|------|--------------|
| Leadership stories | ownership, customer impact, trade-offs, conflict, failure recovery |
| Coding | correct Java, input validation, edge cases, tests, clean naming |
| System design | practicality, reliability, scalability, operational readiness |
| Bar-raiser style signal | crisp examples, measurable results, lessons learned |

Amazon's official SDE prep pages emphasize coding, system design, and Leadership Principles. Their SDE II guidance mentions a 90-minute online assessment with technical questions and system-design scenarios, and their SDE III guidance highlights system-wide architecture, reliability, scalability, and maintainable code.

## Stripe / fintech style

Focus:
- Payment state machines.
- Idempotency.
- Ledger consistency.
- API correctness and auditability.

Study:
- [Advanced Distributed Systems](Advanced%20Distributed%20Systems/README.md)
- [17_distributed_transactions.md](Advanced%20Distributed%20Systems/17_distributed_transactions.md)
- [Question Bank/database-sql.md](Question%20Bank/database-sql.md)
- [Question Bank/security.md](Question%20Bank/security.md)

## Netflix / Uber style

Focus:
- Microservices at scale.
- Event-driven architecture.
- Observability and resilience.
- Performance under high traffic.

Study:
- [Microservices and Architecture](Microservices%20and%20Architecture/README.md)
- [Performance Engineering](Performance%20Engineering/README.md)
- [VISUAL_ARCHITECTURE_DIAGRAMS.md](VISUAL_ARCHITECTURE_DIAGRAMS.md)
- [Netflix Case Study](Real-World%20Case%20Studies/01_netflix_streaming_platform.md)
- [Uber Case Study](Real-World%20Case%20Studies/02_uber_ride_matching.md)

## Cloud provider / SRE style

Focus:
- Kubernetes and cloud-native deployment.
- Monitoring and incident response.
- Security and automation.
- Capacity and cost awareness.

Study:
- [DevSecOps and Frontend](DevSecOps%20and%20Frontend/README.md)
- [Infra and ML](Infra%20and%20ML/README.md)
- [Cloud and Observability](Cloud%20and%20Observability/README.md)
- [COST_CALCULATOR.md](COST_CALCULATOR.md)

## Preparation checklist

- [ ] Pick one primary company style.
- [ ] Pick one role track from [INTERVIEW_TRACKS.md](INTERVIEW_TRACKS.md).
- [ ] Solve at least 20 role-relevant coding problems.
- [ ] Write at least 3 system design case studies.
- [ ] Prepare 6 behavioral stories.
- [ ] Complete 2 mock interviews and score them.

## Official preparation references

- [Google Careers interview tips](https://www.google.com/about/careers/applications/interview-tips)
- [Amazon SDE II interview prep](https://amazon.jobs/content/en/how-we-hire/sde-ii-interview-prep)
- [Amazon SDE III interview prep](https://www.amazon.jobs/content/en/how-we-hire/sde-iii-interview-prep)
- [Amazon software development interview topics](https://www.amazon.jobs/content/en-gb/how-we-hire/interview-prep/software-development-topics)
