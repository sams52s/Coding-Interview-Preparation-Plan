# References

Navigation: [Main README](README.md) | [Project Architecture](PROJECT_ARCHITECTURE.md) | [Interview Tracks](INTERVIEW_TRACKS.md) | [Question Bank](Question%20Bank/README.md)

This file is the single reference index for the whole interview-preparation repository. Use it when you want high-quality external material for a topic: official documentation, standards, books, papers, engineering blogs, and well-known videos.

Prefer this order when studying:

1. **Official docs and standards** for correctness.
2. **Books and papers** for depth.
3. **Engineering blogs and case studies** for production judgment.
4. **Videos and talks** for intuition and communication style.
5. **Practice platforms and interview guides** for execution.

---

## Quick Priority List

If time is limited, start here:

| Topic | Best first reference |
|------|----------------------|
| Java | [Oracle Java Documentation](https://docs.oracle.com/en/java/) |
| Modern Java style | [Effective Java, 3rd Edition](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/) |
| Java concurrency | [Java Concurrency in Practice](https://www.oreilly.com/library/view/java-concurrency-in/0321349601/) |
| Spring Boot | [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/reference/index.html) |
| REST and HTTP | [RFC 9110: HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110) |
| System design | [Designing Data-Intensive Applications](https://www.oreilly.com/library/view/designing-data-intensive-applications/9781491903063/) |
| SRE and reliability | [Google SRE Books](https://sre.google/books/) |
| Cloud architecture | [AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/framework/welcome.html) |
| Kubernetes | [Kubernetes Documentation](https://kubernetes.io/docs/) |
| Security | [OWASP Top 10](https://owasp.org/www-project-top-ten/) |
| OAuth | [RFC 6749: OAuth 2.0](https://www.rfc-editor.org/rfc/rfc6749) |
| Algorithms | [MIT 6.006 Introduction to Algorithms](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/) |
| Interview system design | [The System Design Primer](https://github.com/donnemartin/system-design-primer) |

---

## Java Core, JVM, OOP, and Collections

### Official Documentation

- [Oracle Java Documentation](https://docs.oracle.com/en/java/) — Java platform landing page.
- [Java SE Documentation](https://www.oracle.com/java/technologies/java-se-doc.html) — Java SE docs, guides, and release notes.
- [Java SE API Documentation](https://www.oracle.com/java/technologies/java-se-api-doc.html) — API reference for Java SE.
- [OpenJDK](https://openjdk.org/) — JDK project, JEPs, and release work.
- [JDK Enhancement Proposals](https://openjdk.org/jeps/0) — language/runtime feature proposals.
- [Dev.java](https://dev.java/) — modern Java learning content from Oracle.

### Books

- [Effective Java, 3rd Edition](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/) — best practices for Java APIs, objects, generics, lambdas, exceptions, and concurrency.
- [Java Concurrency in Practice](https://www.oreilly.com/library/view/java-concurrency-in/0321349601/) — thread safety, visibility, locks, executors, and concurrent design.
- [Head First Java](https://www.oreilly.com/library/view/head-first-java/9781491910761/) — beginner-friendly Java foundation.
- [Core Java, Volume I](https://www.oreilly.com/library/view/core-java-volume/9780135167182/) — language fundamentals and standard library depth.

### Videos and Talks

- [How to Design a Good API and Why it Matters - Joshua Bloch](https://research.google/pubs/how-to-design-a-good-api-and-why-it-matters/) — API design principles from the Java Collections designer.
- [Simple Made Easy - Rich Hickey](https://www.youtube.com/watch?v=SxdOUGdseq4) — simplicity, complexity, state, and design thinking.

### Repo Connections

- [JAVA Hub](Java%20%26%20Spring%20Interview%20Preparation/JAVA/README.md)
- [Java Fundamentals](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/README.md)
- [OOP](Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/README.md)
- [Java Collections Framework](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Collections%20Framework/README.md)
- [Code Refactoring Examples](CODE_REFACTORING_EXAMPLES.md)

---

## Spring Boot, Backend Engineering, REST, and APIs

### Official Documentation

- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/reference/index.html) — main Spring Boot reference.
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/) — IoC, beans, web, transactions, testing, and AOP.
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/) — authentication, authorization, OAuth2, resource servers, and secure defaults.
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/reference/) — repositories, transactions, query methods, and persistence patterns.
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/) — JPA implementation details and ORM behavior.
- [Bean Validation / Jakarta Validation](https://beanvalidation.org/) — validation annotations and constraints.
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/) — unit and integration testing.
- [Mockito Documentation](https://site.mockito.org/) — mocking and behavior verification.

### Standards and API Design

- [RFC 9110: HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110) — HTTP methods, status codes, headers, resources, and representations.
- [OpenAPI Specification](https://spec.openapis.org/oas/latest.html) — REST API contract documentation.
- [Google API Improvement Proposals](https://google.aip.dev/) — practical API design guidance.
- [Microsoft REST API Guidelines](https://github.com/microsoft/api-guidelines) — REST API naming, versioning, pagination, errors, and consistency.
- [Roy Fielding REST Dissertation](https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm) — original REST architectural style definition.

### Books

- [Spring in Action, 6th Edition](https://www.manning.com/books/spring-in-action-sixth-edition) — Spring and Spring Boot application development.
- [RESTful Web APIs](https://www.oreilly.com/library/view/restful-web-apis/9781449359713/) — resource modeling and hypermedia/API design.
- [API Design Patterns](https://www.manning.com/books/api-design-patterns) — practical API design patterns.

### Repo Connections

- [Spring Boot](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md)
- [Annotations Docs](Java%20%26%20Spring%20Interview%20Preparation/annotations-docs/README.md)
- [Spring Boot Q&A](Question%20Bank/spring-boot.md)
- [REST API Q&A](Question%20Bank/rest-api.md)
- [Production Readiness Checklist](PRODUCTION_READINESS_CHECKLIST.md)

---

## Data Structures, Algorithms, and Coding Interviews

### Courses and Documents

- [MIT 6.006 Introduction to Algorithms](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/) — algorithms, data structures, notes, videos, problem sets, and solutions.
- [MIT 6.046 Design and Analysis of Algorithms](https://ocw.mit.edu/courses/6-046j-design-and-analysis-of-algorithms-spring-2015/) — more advanced algorithm design.
- [CP-Algorithms](https://cp-algorithms.com/) — concise algorithm reference for graph, DP, number theory, strings, and data structures.
- [LeetCode Explore](https://leetcode.com/explore/) — guided practice by topic.

### Books

- [Introduction to Algorithms, 4th Edition](https://mitpress.mit.edu/9780262046305/introduction-to-algorithms/) — CLRS, the standard algorithms reference.
- [The Algorithm Design Manual](https://www.algorist.com/) — practical algorithm design and problem-solving.
- [Elements of Programming Interviews in Java](https://elementsofprogramminginterviews.com/) — coding interview practice in Java.
- [Cracking the Coding Interview](https://www.crackingthecodinginterview.com/) — interview patterns, coding problems, and behavioral prep.

### Videos

- [MIT 6.006 Lecture 1: Algorithms and Computation](https://www.youtube.com/watch?v=ZA-tUyM_y7s)
- [MIT 6.006 Lecture Videos](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/video_galleries/lecture-videos/)
- [NeetCode](https://www.youtube.com/@NeetCode) — coding pattern walkthroughs.

### Repo Connections

- [DSA](Java%20%26%20Spring%20Interview%20Preparation/DSA/README.md)
- [Advanced Algorithms](Java%20%26%20Spring%20Interview%20Preparation/DSA/advanced-algorithms.md)
- [Blind 75 Mapping](BLIND_75_PROBLEM_MAPPING.md)
- [Daily Problem Log](DAILY_PROBLEM_LOG.md)
- [DSA Q&A](Question%20Bank/dsa.md)

---

## System Design and Distributed Systems

### Books

- [Designing Data-Intensive Applications](https://www.oreilly.com/library/view/designing-data-intensive-applications/9781491903063/) — distributed data systems, consistency, replication, partitioning, transactions, and stream processing.
- [Designing Data-Intensive Applications, 2nd Edition](https://www.oreilly.com/library/view/designing-data-intensive-applications/9781098119058/) — updated edition by Martin Kleppmann and Chris Riccomini.
- [Distributed Systems, 4th Edition](https://www.distributed-systems.net/) — broad distributed-systems textbook by Maarten van Steen and Andrew S. Tanenbaum.
- [Database Internals](https://www.oreilly.com/library/view/database-internals/9781492040330/) — storage engines, indexes, distributed databases, and database implementation.
- [Building Microservices, 2nd Edition](https://www.oreilly.com/library/view/building-microservices-2nd/9781492034018/) — microservice design, ownership, deployment, and operations.
- [Release It!, 2nd Edition](https://pragprog.com/titles/mnee2/release-it-second-edition/) — production stability, capacity, integration points, and resilience.

### Papers and Classic Documents

- [The Google File System](https://research.google/pubs/the-google-file-system/) — large-scale distributed file storage.
- [MapReduce](https://research.google/pubs/mapreduce-simplified-data-processing-on-large-clusters/) — batch computation across large clusters.
- [Bigtable](https://research.google/pubs/bigtable-a-distributed-storage-system-for-structured-data/) — distributed structured storage.
- [Spanner](https://research.google/pubs/spanner-googles-globally-distributed-database/) — global distributed SQL and external consistency.
- [Dynamo: Amazon's Highly Available Key-value Store](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf) — AP-style distributed key-value design.
- [The Log: What every software engineer should know](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying) — log-centric data architecture.
- [Kafka: a Distributed Messaging System for Log Processing](https://notes.stephenholiday.com/Kafka.pdf) — Kafka design paper.
- [Time, Clocks, and the Ordering of Events](https://lamport.azurewebsites.net/pubs/time-clocks.pdf) — Lamport clocks and distributed ordering.
- [Paxos Made Simple](https://lamport.azurewebsites.net/pubs/paxos-simple.pdf) — consensus explanation.
- [In Search of an Understandable Consensus Algorithm](https://raft.github.io/raft.pdf) — Raft consensus paper.

### Videos and Talks

- [Turning the Database Inside Out - Martin Kleppmann](https://martin.kleppmann.com/2015/03/04/turning-the-database-inside-out.html) — event logs, materialized views, and stream processing.
- [The System Design Primer](https://github.com/donnemartin/system-design-primer) — open-source system design interview guide.
- [Awesome System Design Resources](https://github.com/ashishps1/awesome-system-design-resources) — broad resource map for system design.
- [ByteByteGo](https://www.youtube.com/@ByteByteGo) — visual system design explanations.
- [Gaurav Sen](https://www.youtube.com/@gkcs) — system design interview walkthroughs.

### Repo Connections

- [System Design Fundamentals](System%20Design%20Fundamentals/README.md)
- [Core Infrastructure Components](Core%20Infrastructure%20Components/README.md)
- [Microservices and Architecture](Microservices%20and%20Architecture/README.md)
- [Advanced Distributed Systems](Advanced%20Distributed%20Systems/README.md)
- [Real-World Case Studies](Real-World%20Case%20Studies/README.md)
- [System Design Template](SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md)

---

## Microservices, Event-Driven Architecture, and Resilience

### Documents and Articles

- [Martin Fowler: Microservices](https://www.martinfowler.com/articles/microservices.html) — foundational microservices article.
- [Martin Fowler: Microservices and the First Law of Distributed Objects](https://www.martinfowler.com/articles/distributed-objects-microservices.html) — why distributed boundaries matter.
- [Enterprise Integration Patterns](https://www.enterpriseintegrationpatterns.com/) — messaging, routing, transformation, and integration patterns.
- [Microservices.io Patterns](https://microservices.io/patterns/index.html) — service decomposition, data, communication, deployment, and observability patterns.
- [Saga Pattern](https://microservices.io/patterns/data/saga.html) — distributed transaction coordination.
- [Circuit Breaker Pattern](https://martinfowler.com/bliki/CircuitBreaker.html) — protecting systems from cascading failures.

### Books

- [Building Microservices, 2nd Edition](https://www.oreilly.com/library/view/building-microservices-2nd/9781492034018/)
- [Microservices Patterns](https://www.manning.com/books/microservices-patterns) — Chris Richardson's microservice patterns.
- [Enterprise Integration Patterns](https://www.enterpriseintegrationpatterns.com/book/) — messaging and integration pattern catalog.

### Repo Connections

- [Microservices and Architecture](Microservices%20and%20Architecture/README.md)
- [Advanced Distributed Systems](Advanced%20Distributed%20Systems/README.md)
- [Production Incidents](Real-World%20Case%20Studies/04_production_incident_reviews.md)
- [Microservices Q&A](Question%20Bank/microservices.md)

---

## Databases, Caching, Messaging, and Search

### Official Documentation

- [PostgreSQL Documentation](https://www.postgresql.org/docs/current/) — SQL, indexing, transactions, replication, and administration.
- [MySQL Documentation](https://dev.mysql.com/doc/) — MySQL database reference.
- [MongoDB Manual](https://www.mongodb.com/docs/manual/) — document modeling, indexing, replication, sharding, and transactions.
- [Redis Documentation](https://redis.io/docs/latest/get-started/) — cache, data structures, persistence, streams, and clustering.
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/) — Kafka architecture, clients, operations, and security.
- [Elasticsearch Reference](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html) — search, indexing, cluster operations, and analytics.

### Books

- [SQL Performance Explained](https://sql-performance-explained.com/) — indexing and SQL query performance.
- [High Performance MySQL](https://www.oreilly.com/library/view/high-performance-mysql/9781492080503/) — MySQL performance, replication, and operations.
- [Kafka: The Definitive Guide](https://www.oreilly.com/library/view/kafka-the-definitive/9781492043072/) — Kafka producers, consumers, streams, and operations.

### Engineering Articles

- [How Discord Stores Trillions of Messages](https://discord.com/blog/how-discord-stores-trillions-of-messages)
- [Scaling Memcache at Facebook](https://www.usenix.org/conference/nsdi13/technical-sessions/presentation/nishtala)
- [Stripe Payments APIs: The First 10 Years](https://stripe.com/blog/payment-api-design)

### Repo Connections

- [Core Infrastructure Components](Core%20Infrastructure%20Components/README.md)
- [Advanced Distributed Systems](Advanced%20Distributed%20Systems/README.md)
- [DBA Cheat Sheet](DBA_CHEAT_SHEET.md)
- [Database SQL Q&A](Question%20Bank/database-sql.md)

---

## Performance Engineering, JVM Tuning, and Observability

### Official Documentation and Tools

- [OpenTelemetry Documentation](https://opentelemetry.io/docs/) — traces, metrics, logs, collector, instrumentation, and semantic conventions.
- [OpenTelemetry Specification](https://opentelemetry.io/docs/specs/otel/) — vendor-neutral telemetry model.
- [Prometheus Documentation](https://prometheus.io/docs/introduction/overview/) — metrics, PromQL, alerting, and exporters.
- [Grafana Documentation](https://grafana.com/docs/grafana/latest/) — dashboards, alerting, exploration, and visualization.
- [async-profiler](https://github.com/async-profiler/async-profiler) — low-overhead Java CPU, allocation, lock, and wall-clock profiling.
- [Java Flight Recorder](https://docs.oracle.com/javacomponents/jmc-5-4/jfr-runtime-guide/about.htm) — JVM event recording and runtime diagnostics.
- [JDK Mission Control](https://www.oracle.com/java/technologies/jdk-mission-control.html) — JVM profiling and JFR analysis.

### Books and Papers

- [Systems Performance, 2nd Edition](https://www.brendangregg.com/systems-performance-2nd-edition-book.html) — performance analysis, observability, and systems thinking.
- [Java Performance, 2nd Edition](https://www.oreilly.com/library/view/java-performance-2nd/9781492056102/) — JVM performance and tuning.
- [The Tail at Scale](https://research.google/pubs/the-tail-at-scale/) — latency tail behavior in large-scale systems.
- [USE Method](https://www.brendangregg.com/usemethod.html) — utilization, saturation, and errors troubleshooting method.

### Videos and Talks

- [Brendan Gregg: Computing Performance](https://www.brendangregg.com/videos.html) — performance engineering talks and flame graph material.
- [AWS Well-Architected Framework Video](https://www.youtube.com/watch?v=KvEDbPmha6o) — cloud architecture pillars overview.

### Repo Connections

- [Performance Engineering](Performance%20Engineering/README.md)
- [Advanced Flame Graphs, async-profiler, and GC Tuning](Performance%20Engineering/advanced_flame_graphs_async_profiler_gc_tuning.md)
- [Cloud and Observability](Cloud%20and%20Observability/README.md)
- [Performance Benchmarks](PERFORMANCE_BENCHMARKS.md)

---

## Security, OAuth, JWT, and Secure Coding

### Standards and Official Documents

- [OWASP Top 10](https://owasp.org/www-project-top-ten/) — common web application risks.
- [OWASP Cheat Sheet Series](https://cheatsheetseries.owasp.org/) — practical secure coding checklists.
- [RFC 6749: OAuth 2.0](https://www.rfc-editor.org/rfc/rfc6749) — OAuth 2.0 authorization framework.
- [RFC 6750: Bearer Token Usage](https://www.rfc-editor.org/rfc/rfc6750) — bearer token security.
- [RFC 7519: JSON Web Token](https://www.rfc-editor.org/rfc/rfc7519) — JWT specification.
- [RFC 7636: PKCE](https://www.rfc-editor.org/rfc/rfc7636) — proof key for code exchange.
- [OpenID Connect Core](https://openid.net/specs/openid-connect-core-1_0.html) — identity layer on top of OAuth 2.0.
- [OAuth 2.0 Security Best Current Practice](https://www.rfc-editor.org/rfc/rfc9700) — updated OAuth security guidance.

### Books and Guides

- [Web Application Security](https://www.oreilly.com/library/view/web-application-security/9781492053101/) — practical web security.
- [OAuth 2 in Action](https://www.manning.com/books/oauth-2-in-action) — OAuth flows and implementation behavior.
- [Building Secure and Reliable Systems](https://sre.google/books/building-secure-reliable-systems/) — Google security and reliability practices.

### Videos

- [OAuth 2.0 Simplified](https://www.oauth.com/) — practical OAuth explanations and references.
- [Understanding OAuth 2.0 from RFC 6749](https://www.youtube.com/watch?v=6SNXbjE_w10) — RFC-oriented video introduction.

### Repo Connections

- [DevSecOps and Frontend](DevSecOps%20and%20Frontend/README.md)
- [OAuth, JWT, and Secure Coding Deep Dive](DevSecOps%20and%20Frontend/advanced_security_oauth_jwt_secure_coding.md)
- [Spring Security Practices](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/04_security_practices.md)
- [Security Q&A](Question%20Bank/security.md)

---

## Cloud, DevOps, SRE, IaC, and Kubernetes

### Official Documentation

- [AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/framework/welcome.html) — cloud architecture review framework.
- [AWS Architecture Center](https://aws.amazon.com/architecture/) — AWS reference architectures and guidance.
- [Google Cloud Architecture Framework](https://cloud.google.com/architecture/framework) — GCP architecture guidance.
- [Azure Well-Architected Framework](https://learn.microsoft.com/en-us/azure/well-architected/) — Azure architecture guidance.
- [Kubernetes Documentation](https://kubernetes.io/docs/) — cluster concepts, workloads, services, storage, and operations.
- [Docker Documentation](https://docs.docker.com/) — containers, Dockerfile, Compose, and Docker Engine.
- [Terraform Documentation](https://developer.hashicorp.com/terraform/docs) — IaC, state, modules, providers, and workflows.
- [CNCF Landscape](https://landscape.cncf.io/) — cloud-native ecosystem map.

### Books

- [Site Reliability Engineering](https://sre.google/sre-book/table-of-contents/) — production reliability at Google.
- [The Site Reliability Workbook](https://sre.google/workbook/table-of-contents/) — practical SRE implementation.
- [Kubernetes in Action](https://www.manning.com/books/kubernetes-in-action-second-edition) — Kubernetes fundamentals and workloads.
- [Terraform: Up & Running](https://www.oreilly.com/library/view/terraform-up/9781098116736/) — Terraform patterns and workflow.
- [Continuous Delivery](https://continuousdelivery.com/) — build, test, deploy, and release automation.

### Repo Connections

- [DevSecOps and Frontend](DevSecOps%20and%20Frontend/README.md)
- [Infra and ML](Infra%20and%20ML/README.md)
- [Cloud and Observability](Cloud%20and%20Observability/README.md)
- [Multi-Region and Chaos Engineering](Cloud%20and%20Observability/advanced_multi_region_chaos_engineering.md)
- [Cloud/DevOps Q&A](Question%20Bank/cloud-devops.md)

---

## AI, ML, Model Serving, and LLM Systems

### Official Documentation and Courses

- [Google Machine Learning Crash Course](https://developers.google.com/machine-learning/crash-course) — practical ML fundamentals.
- [scikit-learn User Guide](https://scikit-learn.org/stable/user_guide.html) — classical ML algorithms and model evaluation.
- [PyTorch Documentation](https://pytorch.org/docs/stable/index.html) — deep learning framework reference.
- [TensorFlow Documentation](https://www.tensorflow.org/learn) — TensorFlow learning and reference material.
- [MLflow Documentation](https://mlflow.org/docs/latest/index.html) — experiment tracking and model lifecycle.
- [NVIDIA Triton Inference Server](https://docs.nvidia.com/deeplearning/triton-inference-server/user-guide/docs/) — production model serving.
- [OpenAI Documentation](https://platform.openai.com/docs/) — LLM APIs and application patterns.

### Books

- [Hands-On Machine Learning with Scikit-Learn, Keras, and TensorFlow](https://www.oreilly.com/library/view/hands-on-machine-learning/9781098125967/) — practical ML and deep learning.
- [Designing Machine Learning Systems](https://www.oreilly.com/library/view/designing-machine-learning/9781098107956/) — production ML systems, data, deployment, and monitoring.
- [Machine Learning Design Patterns](https://www.oreilly.com/library/view/machine-learning-design/9781098115777/) — ML system and pipeline design patterns.

### Videos

- [Stanford CS229 Machine Learning](https://www.youtube.com/@stanfordonline/search?query=CS229) — ML theory and applied concepts.
- [Full Stack Deep Learning](https://fullstackdeeplearning.com/) — production deep learning systems.

### Repo Connections

- [Basic AI & ML](Basic%20AI%20%26%20ML/README.md)
- [AI ML Models and ALGO](AI%20ML%20Models%20and%20ALGO/README.md)
- [Machine Learning Integration](Infra%20and%20ML/27_machine_learning_integration.md)
- [ML Systems Interview Track](INTERVIEW_TRACKS.md)

---

## Web, Frontend Integration, and Browser Fundamentals

### Official Documentation

- [MDN Web Docs](https://developer.mozilla.org/) — HTML, CSS, JavaScript, HTTP, browser APIs, and web security.
- [React Documentation](https://react.dev/) — modern React docs.
- [Vue Documentation](https://vuejs.org/guide/introduction.html) — Vue framework guide.
- [Web.dev](https://web.dev/) — performance, accessibility, Core Web Vitals, and modern web practices.
- [WAI Accessibility Standards](https://www.w3.org/WAI/standards-guidelines/) — accessibility standards and guidance.

### Books and Guides

- [You Don't Know JS Yet](https://github.com/getify/You-Dont-Know-JS) — JavaScript language depth.
- [Eloquent JavaScript](https://eloquentjavascript.net/) — JavaScript and browser programming.

### Repo Connections

- [DevSecOps and Frontend](DevSecOps%20and%20Frontend/README.md)
- [Frontend Communication](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/08_frontend_communication.md)
- [Web Frontend Q&A](Question%20Bank/web-frontend.md)

---

## Low-Level Design, Refactoring, and Clean Architecture

### Books

- [Refactoring, 2nd Edition](https://martinfowler.com/books/refactoring.html) — behavior-preserving code improvement.
- [Design Patterns](https://www.oreilly.com/library/view/design-patterns-elements/0201633612/) — Gang of Four pattern catalog.
- [Clean Architecture](https://www.oreilly.com/library/view/clean-architecture-a/9780134494272/) — architecture boundaries and dependency direction.
- [Clean Code](https://www.oreilly.com/library/view/clean-code-a/9780136083238/) — readable, maintainable code practices.
- [Domain-Driven Design](https://www.domainlanguage.com/ddd/) — domain modeling and bounded contexts.
- [Implementing Domain-Driven Design](https://www.oreilly.com/library/view/implementing-domain-driven-design/9780133039900/) — practical DDD implementation.
- [Patterns of Enterprise Application Architecture](https://martinfowler.com/books/eaa.html) — enterprise application architecture patterns.

### Articles and Catalogs

- [Refactoring Catalog](https://refactoring.com/catalog/) — refactoring techniques.
- [Martin Fowler Articles](https://martinfowler.com/articles/) — architecture, refactoring, delivery, and design writing.

### Repo Connections

- [Concept-to-Code Mapping](CONCEPT_TO_CODE_MAPPING.md)
- [Code Refactoring Examples](CODE_REFACTORING_EXAMPLES.md)
- [Low-Level Design Q&A](Question%20Bank/lld.md)

---

## Company-Specific Interview Preparation

### Official Interview Guides

- [Google Careers Interview Tips](https://www.google.com/about/careers/applications/interview-tips) — Google interview process and preparation guidance.
- [Amazon SDE II Interview Prep](https://amazon.jobs/content/en/how-we-hire/sde-ii-interview-prep) — Amazon SDE II preparation.
- [Amazon SDE III Interview Prep](https://www.amazon.jobs/content/en/how-we-hire/sde-iii-interview-prep) — Amazon senior-level preparation.
- [Amazon Software Development Topics](https://www.amazon.jobs/content/en-gb/how-we-hire/interview-prep/software-development-topics) — coding and software-development preparation.
- [Meta Careers Interviewing](https://www.metacareers.com/careerprograms/pathways) — Meta career/interview resources.
- [Microsoft Interview Tips](https://careers.microsoft.com/v2/global/en/hiring-tips) — Microsoft hiring guidance.

### Practice Resources

- [LeetCode](https://leetcode.com/) — coding practice and company-tagged problems.
- [HackerRank Interview Preparation Kit](https://www.hackerrank.com/interview/interview-preparation-kit) — coding patterns and timed practice.
- [Pramp](https://www.pramp.com/) — peer mock interviews.
- [Interviewing.io](https://interviewing.io/) — anonymous technical interview practice.

### Repo Connections

- [Company Interview Guides](COMPANY_INTERVIEW_GUIDES.md)
- [Interview Tracks](INTERVIEW_TRACKS.md)
- [Interview Simulation Guide](INTERVIEW_SIMULATION_GUIDE.md)
- [Mock Interview Scorecard](MOCK_INTERVIEW_SCORECARD.md)
- [Behavioral Answer Bank](BEHAVIORAL_ANSWER_BANK.md)
- [Interview War Stories](Real-World%20Case%20Studies/05_interview_war_stories.md)

---

## Real-World Architecture and Production Case Studies

Use these to build production instincts. Read them after you understand the basic components.

### Engineering Blogs

- [Netflix Tech Blog](https://netflixtechblog.com/) — streaming, resilience, personalization, encoding, observability, and platform engineering.
- [Uber Engineering Blog](https://www.uber.com/blog/engineering/) — marketplace, geo, distributed systems, data, and reliability.
- [Instagram Engineering Blog](https://instagram-engineering.com/) — feed, media, infrastructure, and mobile/backend systems.
- [Meta Engineering](https://engineering.fb.com/) — infra, scale, AI, networking, and reliability.
- [Google Research Publications](https://research.google/pubs/) — systems papers and architecture research.
- [AWS Architecture Blog](https://aws.amazon.com/blogs/architecture/) — cloud reference architectures and design patterns.
- [Stripe Engineering Blog](https://stripe.com/blog/engineering) — APIs, payments, reliability, and developer experience.
- [Slack Engineering](https://slack.engineering/) — messaging, infrastructure, observability, and reliability.
- [Cloudflare Blog](https://blog.cloudflare.com/tag/engineering/) — networking, edge, security, and performance.
- [Shopify Engineering](https://shopify.engineering/) — commerce scale, Rails, data, and platform engineering.

### Case Study Starting Points

- [Netflix Tech Blog: Architecture](https://netflixtechblog.com/tagged/architecture)
- [Uber Engineering: Architecture](https://www.uber.com/blog/engineering/architecture/)
- [High Scalability](http://highscalability.com/) — classic architecture writeups.

### Repo Connections

- [Netflix Streaming Case Study](Real-World%20Case%20Studies/01_netflix_streaming_platform.md)
- [Uber Ride Matching Case Study](Real-World%20Case%20Studies/02_uber_ride_matching.md)
- [Instagram Feed and Media Case Study](Real-World%20Case%20Studies/03_instagram_feed_media.md)
- [Production Incident Reviews](Real-World%20Case%20Studies/04_production_incident_reviews.md)

---

## Behavioral, Communication, and Technical Storytelling

### Books and Guides

- [The Manager's Path](https://www.oreilly.com/library/view/the-managers-path/9781491973882/) — engineering growth, team communication, and leadership.
- [Staff Engineer](https://staffeng.com/book) — senior/staff-level technical leadership.
- [The Staff Engineer's Path](https://www.oreilly.com/library/view/the-staff-engineers/9781098118723/) — influence and technical leadership.
- [Crucial Conversations](https://cruciallearning.com/books/) — high-stakes communication.

### Repo Connections

- [Behavioral Answer Bank](BEHAVIORAL_ANSWER_BANK.md)
- [Interview Simulation Guide](INTERVIEW_SIMULATION_GUIDE.md)
- [Mock Interview Scorecard](MOCK_INTERVIEW_SCORECARD.md)
- [Company Interview Guides](COMPANY_INTERVIEW_GUIDES.md)

---

## How to Maintain This File

When adding a reference:

- Prefer official docs, standards, publisher pages, or original author pages.
- Add the resource under the most relevant topic.
- Include one short reason why it matters.
- Link the resource back to a repo file in the relevant **Repo Connections** section.
- Avoid dumping random tutorials; keep this file curated.
- Recheck external links quarterly because documentation URLs and course pages move.

When a resource becomes outdated:

- Keep it only if it is historically important.
- Add a note such as "classic but version-specific."
- Prefer the latest official docs for tool behavior and API details.
