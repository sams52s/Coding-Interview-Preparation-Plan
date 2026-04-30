# Interview Tracks: Role-Based Learning Paths

This document outlines **6 distinct interview tracks** based on role type. Use this to customize your study plan based on the position you're targeting.

Supporting tools: [PROJECT_ARCHITECTURE.md](PROJECT_ARCHITECTURE.md) | [projects/README.md](projects/README.md) | [DAILY_PROBLEM_LOG.md](DAILY_PROBLEM_LOG.md) | [MOCK_INTERVIEW_SCORECARD.md](MOCK_INTERVIEW_SCORECARD.md) | [COMPANY_INTERVIEW_GUIDES.md](COMPANY_INTERVIEW_GUIDES.md)

---

## 🎯 Track Selection Guide

Choose your primary track based on the role you're targeting:

| Track | Target Role | Interview Focus | Primary Weeks | Key Question Banks |
|-------|------------|-----------------|----------------|-------------------|
| **Backend Engineer** | Java/Spring backend engineer, API developer | Code quality, design patterns, REST APIs, databases | 1, 2, 3 | core-java, spring-boot, dsa, rest-api |
| **Backend + System Design** | Senior engineer, Staff engineer, Tech lead | System design, distributed systems, architecture | 1-4 | system-design, advanced-topics, database-sql, cloud-devops |
| **Full-Stack Backend** | Backend engineer + frontend skills, full-stack engineer | Spring Boot + React/Vue/Angular, WebSocket, CORS | 1-3 | spring-boot, rest-api, web-frontend |
| **DevOps / SRE** | DevOps engineer, SRE, platform engineer | Infrastructure, deployment, observability, security | 2, 4 (Chapters 24-29) | cloud-devops, testing-quality, security |
| **ML Systems Engineer** | ML-focused roles, recommendation systems, ranking | ML integration, model serving, pipelines | 4 (Chapters 27) | advanced-topics, cloud-devops |
| **Payment / Trading Systems** | Financial/payment systems engineer, trading platform | Distributed transactions, consistency, idempotency | 4 (Chapters 16-17, 19) | advanced-topics, database-sql, system-design |

---

## 📋 Track 1: Backend Engineer (3-4 weeks, lighter on system design)

### 🎯 Target Position
Java/Spring backend engineer, API developer, mid-level backend engineer

### 📚 Study Plan

#### **Week 1: Java Foundations**
- **Folder:** [Java Fundamentals](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/README.md), [OOP](Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/README.md), [Collections](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Collections%20Framework/README.md)
- **DSA:** [Arrays, Strings, Recursion](Java%20%26%20Spring%20Interview%20Preparation/DSA/README.md)
- **Time:** 5-6 days
- **Q&A:** [core-java.md](Question%20Bank/core-java.md), [dsa.md](Question%20Bank/dsa.md)
- **Leetcode:** 10-15 Easy/Medium array/string problems

#### **Week 2: Advanced Java**
- **Folder:** [Generics](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Generics.md), [I/O](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20IO%20%26%20NIO.md), [Lambda & Streams](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Lambda%20Expressions%20%26%20Functional%20Programming%20in%20Java.md), [Multithreading](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Multithreading%20%26%20Concurrency.md)
- **Annotations:** [Spring Annotations](Java%20%26%20Spring%20Interview%20Preparation/annotations-docs/spring-annotations.md), [JPA Annotations](Java%20%26%20Spring%20Interview%20Preparation/annotations-docs/jpa-annotations.md)
- **DSA:** [Linked Lists](Java%20%26%20Spring%20Interview%20Preparation/DSA/linked%20lists.md), [Stacks/Queues](Java%20%26%20Spring%20Interview%20Preparation/DSA/stacks-queues.md), [Sorting](Java%20%26%20Spring%20Interview%20Preparation/DSA/sorting-algorithms.md), [Binary Search](Java%20%26%20Spring%20Interview%20Preparation/DSA/binary-search.md)
- **Time:** 5-6 days
- **Q&A:** [multithreading.md](Question%20Bank/multithreading.md), [dsa.md](Question%20Bank/dsa.md), [testing-quality.md](Question%20Bank/testing-quality.md)
- **Leetcode:** 20-30 Medium problems (linked lists, stacks, sorting)

#### **Week 3: Spring Boot & REST APIs**
- **Folder:** [Spring Boot](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md) - Chapters 01-08, 10
  - Core Spring Framework (IoC, DI)
  - Data Access & Persistence (JPA, Hibernate)
  - Security Practices
  - REST APIs & Frontend Communication
  - Observability & Monitoring
- **Q&A:** [spring-boot.md](Question%20Bank/spring-boot.md), [spring-data-JPA-&-hibernate.md](Question%20Bank/spring-data-JPA-%26-hibernate.md), [rest-api.md](Question%20Bank/rest-api.md)
- **Project:** Build a "Student Management REST API" with Spring Boot + JPA
  - CRUD operations
  - Input validation
  - Exception handling
  - SQL database with migrations
- **Project Spec:** [03_spring_boot_rest_api.md](projects/03_spring_boot_rest_api.md)
- **Time:** 6-7 days

#### **Week 4: System Design (Basics Only)**
- **Folder:** [System Design Fundamentals](System%20Design%20Fundamentals/README.md) - Chapters 01-05 only
  - Scalability basics
  - High Availability
  - Distributed Systems fundamentals
  - Data Management (SQL vs NoSQL)
- **Folder:** [Core Infrastructure Components](Core%20Infrastructure%20Components/README.md) - Chapters 06-10
  - Load Balancing
  - Caching
  - Storage & Data Models
  - Service Communication (REST)
- **Q&A:** [system-design.md](Question%20Bank/system-design.md), [database-sql.md](Question%20Bank/database-sql.md)
- **Time:** 3-4 days (light on system design)

### 📝 Mock Interview Topics

**Coding:**
- LeetCode Medium level (linked lists, trees, stacks, sorting)
- Write clean, testable code with proper error handling

**Design:**
- Design a simple REST API (todo app, note app, user management)
- Database schema design with normalization
- Caching strategy for frequently accessed data

**Behavioral:**
- Tell me about your biggest project
- How do you handle code reviews?
- Describe a time you debugged a production issue

### 📊 Success Criteria
- ✅ Solve 20-30 LeetCode problems confidently
- ✅ Build and deploy a Spring Boot REST API using [PRODUCTION_READINESS_CHECKLIST.md](PRODUCTION_READINESS_CHECKLIST.md)
- ✅ Explain HTTP, REST principles, and API design
- ✅ Discuss database design and indexing basics
- ✅ Code is clean, tested, and follows SOLID principles

---

## 📋 Track 2: Backend + System Design (Senior / 5 weeks, full course)

### 🎯 Target Position
Senior backend engineer, staff engineer, tech lead, architect

### 📚 Study Plan

**Complete the full [learning roadmap](README.md#learning-roadmap) as designed.**

Use [SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md](SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md), [VISUAL_ARCHITECTURE_DIAGRAMS.md](VISUAL_ARCHITECTURE_DIAGRAMS.md), and [COST_CALCULATOR.md](COST_CALCULATOR.md) for every system-design case.

Use [Real-World Case Studies](Real-World%20Case%20Studies/README.md) for complete Netflix, Uber, Instagram, production incident, and interview-story examples.

### 📝 Key Additions for This Track

#### **System Design Deep Dive (Week 4 - Full Coverage)**
- All 29 chapters: [System Design Fundamentals](System%20Design%20Fundamentals/README.md) → [Cloud and Observability](Cloud%20and%20Observability/README.md)
- [Advanced Distributed Systems](Advanced%20Distributed%20Systems/README.md) - Consensus, transactions, replication
- [Performance Engineering](Performance%20Engineering/README.md) - Profiling, database optimization, JVM tuning
- [Advanced consistency patterns](Advanced%20Distributed%20Systems/advanced_crdts_vector_clocks_eventual_consistency.md) - CRDTs, vector clocks, and merge semantics
- [Advanced performance workflow](Performance%20Engineering/advanced_flame_graphs_async_profiler_gc_tuning.md) - flame graphs, async-profiler, and GC diagnosis

#### **Advanced Topics**
- [Infra and ML](Infra%20and%20ML/README.md) - IaC, ML integration
- [DevSecOps](DevSecOps%20and%20Frontend/README.md) - CI/CD security, supply chain

### 📝 Mock Interview Topics

**Coding:**
- Hard LeetCode problems (dynamic programming, graph algorithms, complex data structures)
- System design interviews: 45 minutes each
  - Design YouTube, Uber, Twitter feed, notification system, real-time chat
  - Use [Netflix](Real-World%20Case%20Studies/01_netflix_streaming_platform.md), [Uber](Real-World%20Case%20Studies/02_uber_ride_matching.md), and [Instagram](Real-World%20Case%20Studies/03_instagram_feed_media.md) as full worked examples
  - Draw architecture, discuss tradeoffs, handle scale

**System Design Focus:**
- CAP theorem tradeoffs and real examples
- Database replication, consensus algorithms
- Service discovery, circuit breakers, rate limiting
- Event-driven architecture, CQRS, saga patterns
- Load balancing strategies
- Caching invalidation
- Database indexing and query optimization

**Deep Dives:**
- Why did you choose this architecture?
- How would you handle 10x traffic growth?
- Describe your observability strategy
- How do you ensure consistency in distributed transactions?

---

## 📋 Track 3: Full-Stack Backend (Backend + Frontend Skills)

### 🎯 Target Position
Full-stack engineer, backend engineer with frontend interest, API + UI developer

### 📚 Study Plan

#### **Weeks 1-2:** Same as Backend Engineer track
- Java Fundamentals, Advanced Java, DSA

#### **Week 3:** Spring Boot + Frontend Integration (EXTENDED)
- [Spring Boot](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md) - All 11 chapters
  - Emphasize **08_frontend_communication.md** (REST, CORS, JWT, WebSocket)
  - **09_machine_learning_springboot.md** (optional but useful)
- [Frontend Integration - Chapter 25](DevSecOps%20and%20Frontend/25_frontend_integration.md)
- **Q&A:** [web-frontend.md](Question%20Bank/web-frontend.md)
- **Project:** Build a full-stack todo app
  - **Backend:** Spring Boot REST API with JWT auth
  - **Frontend:** React or Vue frontend
  - **Together:** Connect them, handle CORS, refresh tokens
- **Useful support:** [Production Readiness Checklist](PRODUCTION_READINESS_CHECKLIST.md), [Visual Architecture Diagrams](VISUAL_ARCHITECTURE_DIAGRAMS.md)

#### **Week 4:** System Design + DevSecOps
- [System Design Fundamentals + Infrastructure](System%20Design%20Fundamentals/README.md) + [Core Infrastructure Components](Core%20Infrastructure%20Components/README.md)
- [DevSecOps - Chapter 24](DevSecOps%20and%20Frontend/24_devsecops_practices.md)
- Deploy the full-stack app using Docker Compose or Kubernetes

### 💡 Key Differences
- Understand REST API contract negotiation from both sides
- Know CORS, JWT token handling, refresh token strategies
- Understand WebSocket and real-time data patterns
- Deployment and containerization essential
- Frontend testing (Jest, Cypress) appreciated

---

## 📋 Track 4: DevOps / SRE (Infrastructure Focused)

### 🎯 Target Position
DevOps engineer, Site Reliability Engineer (SRE), Platform engineer, Infrastructure engineer

### 📚 Study Plan

#### **Week 1: Java Basics (Abbreviated)**
- [Java Fundamentals](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/README.md) - syntax overview only (1-2 days)
- [Multithreading & Concurrency](Java%20%26%20Spring%20Interview%20Preparation/JAVA/Multithreading%20%26%20Concurrency.md) - thread basics
- Skip: OOP deep dive, Collections details (less relevant for DevOps)

#### **Week 2-3: Spring Boot Overview**
- [Core Spring Framework - Chapter 01](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/01_core_spring_framework.md)
- [Observability & Monitoring - Chapter 06](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/06_observability_monitoring.md)
- [DevOps Automation - Chapter 07](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/07_devops_automation.md)
- [11_JKS_Certificate.md](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/11_JKS_Certificate.md) - TLS/certificates
- Goal: Understand what a Spring Boot app needs to run

#### **Week 4: Deep Dive into Infrastructure (CORE)**

**Chapters 06-10** (Infrastructure Components):
- [Load Balancing - Chapter 06](Core%20Infrastructure%20Components/06_load_balancing.md)
- [Caching - Chapter 07](Core%20Infrastructure%20Components/07_caching.md)
- [Monitoring & Observability - Chapter 10](Core%20Infrastructure%20Components/10_monitoring_observability.md)

**Chapters 14, 24-29** (Cloud & Deployment):
- [Cloud Native Patterns - Chapter 14](Microservices%20and%20Architecture/14_cloud_native_patterns.md)
- [DevSecOps Practices - Chapter 24](DevSecOps%20and%20Frontend/24_devsecops_practices.md)
- [Infrastructure as Code - Chapter 26](Infra%20and%20ML/26_infrastructure_as_code.md)
- [Cloud Native Practices - Chapter 28](Cloud%20and%20Observability/28_cloud_native_practices.md)
- [Observability & Monitoring - Chapter 29](Cloud%20and%20Observability/29_observability_monitoring.md)

**Resilience Patterns - Chapter 15**
- Circuit breakers, rate limiting, timeouts, bulkheads

### 📝 Q&A Focus
- [cloud-devops.md](Question%20Bank/cloud-devops.md) ⭐⭐⭐ (primary)
- [security.md](Question%20Bank/security.md) (important for DevSecOps)
- [advanced-topics.md](Question%20Bank/advanced-topics.md) (for distributed systems)
- [testing-quality.md](Question%20Bank/testing-quality.md) (CI/CD testing)

### 💼 Technical Skills to Demonstrate
- Kubernetes (Deployments, StatefulSets, Services)
- Docker & container security
- Terraform or CloudFormation (Infrastructure as Code)
- Prometheus + Grafana (monitoring stack)
- CI/CD pipelines (GitHub Actions, GitLab CI, Jenkins)
- Secret management (Vault, AWS Secrets Manager)
- Network policies, firewalls, security
- Cost optimization and capacity planning
- Use [COST_CALCULATOR.md](COST_CALCULATOR.md) for rough cloud/system design estimates.

### 📝 Mock Interview Topics

**Design Scenarios:**
- Design a CI/CD pipeline for microservices
- Design a monitoring stack with alerting
- Design multi-region failover strategy
- Design a secret rotation system

**Operational Questions:**
- Describe a past incident and how you debugged it
- How do you handle zero-downtime deployments?
- How do you ensure security in your deployment pipeline?
- Explain your approach to capacity planning

---

## 📋 Track 5: ML Systems Engineer (ML Integration Focused)

### 🎯 Target Position
Machine learning systems engineer, ML infrastructure engineer, ML platform engineer

### 📚 Study Plan

#### **Weeks 1-3:** Backend Foundation (Abbreviated)
- **Week 1:** Java Fundamentals + Collections (skip deep OOP)
- **Week 2:** Generics, Multithreading, Lambda & Streams
- **Week 3:** Spring Boot (focus on APIs and observability)

#### **Week 4: System Design for ML Systems** (CORE)

**Chapters to Focus On:**
- [Scalability - Chapter 01](System%20Design%20Fundamentals/01_scalability.md) - capacity for model inference
- [Service Communication - Chapter 09](Core%20Infrastructure%20Components/09_service_communication.md) - gRPC for low-latency serving
- [Monitoring & Observability - Chapter 10](Core%20Infrastructure%20Components/10_monitoring_observability.md) - model performance tracking
- [Machine Learning Integration - Chapter 27](Infra%20and%20ML/27_machine_learning_integration.md) ⭐⭐⭐ (primary)
- [Cloud Native Practices - Chapter 28](Cloud%20and%20Observability/28_cloud_native_practices.md) - deployment
- [Observability - Chapter 29](Cloud%20and%20Observability/29_observability_monitoring.md) - model monitoring, drift detection

**AI/ML Foundations:**
- [Basic AI & ML](Basic%20AI%20%26%20ML/README.md)
- [AI ML Models and ALGO](AI%20ML%20Models%20and%20ALGO/README.md)
- [Real-World Case Studies](Real-World%20Case%20Studies/README.md) for ML-heavy system design practice
- [Cost Calculator](COST_CALCULATOR.md) for GPU/CPU inference cost discussion
- [Visual Architecture Diagrams](VISUAL_ARCHITECTURE_DIAGRAMS.md) for ML serving diagrams

### 💡 Key Topics to Master

**ML Serving Patterns:**
- Batch vs. Real-time inference
- Model versioning and rollback
- Canary deployments for models
- Feature serving (online/offline consistency)
- Model caching and TTL

**System Design:**
- When to use embedded models vs. microservices
- Request batching for efficiency
- Handling cold models (initialization time)
- Cost optimization (CPU vs GPU vs TPU)
- Model A/B testing and experimentation

**Observability for ML:**
- Model performance monitoring (accuracy, latency, throughput)
- Data drift and model drift detection
- Prediction quality metrics vs. infrastructure metrics
- Bias and fairness monitoring

### 📝 Mock Interview Topics

**Design Scenarios:**
- Design a recommendation system backend
- Design a ranking/scoring service with ML
- Design a fraud detection system
- Design an ML feature platform

**Technical Questions:**
- How do you handle model latency SLAs?
- Describe your approach to model monitoring and alerts
- How do you version and rollback models?
- How do you handle feature data freshness?

---

## 📋 Track 6: Payment / Trading Systems (High-Consistency, Low-Latency)

### 🎯 Target Position
Payment systems engineer, trading platform engineer, fintech backend engineer

### 📚 Study Plan

#### **Weeks 1-3:** Backend Foundation (Standard)
- Complete Weeks 1-3 from Backend Engineer track
- **Special focus:** Exception handling, proper error codes, idempotency

#### **Week 4: Advanced Distributed Systems + Consistency** (CORE)

**Must Study Chapters:**
- [Distributed Systems - Chapter 03](System%20Design%20Fundamentals/03_distributed_systems.md) ⭐⭐⭐
  - CAP theorem and consistency models
  - Transactional guarantees
- [Data Management - Chapter 05](System%20Design%20Fundamentals/05_data_management.md)
  - ACID vs eventual consistency
  - Data replication strategies
- [Distributed Transactions - Chapter 17](Advanced%20Distributed%20Systems/17_distributed_transactions.md) ⭐⭐⭐ (PRIMARY)
  - 2-phase commit (2PC)
  - Saga pattern for compensation
  - Idempotency and deduplication
- [Data Replication - Chapter 19](Advanced%20Distributed%20Systems/19_data_replication.md) ⭐⭐⭐
  - Replication lag handling
  - Failover strategies
  - Consistency levels
- [Consensus Algorithms - Chapter 16](Advanced%20Distributed%20Systems/16_consensus_algorithms.md)
  - Raft, Paxos for leader election
  - Quorum systems
- [Service Communication - Chapter 09](Core%20Infrastructure%20Components/09_service_communication.md)
  - Request-reply and messaging
  - Retry and timeout strategies
- [Performance Engineering - Chapter 20](Performance%20Engineering/20_performance_engineering.md)
  - Sub-100ms latency requirements
- [Payment system case study](projects/05_system_design_case_studies.md)

### 💼 Key Topics for This Track

**Consistency & Correctness:**
- Exactly-once processing (payment deduplication)
- Idempotent APIs (critical for payments!)
- Double-entry bookkeeping (ledger consistency)
- Handling partial failures
- Rollback and compensation strategies

**High Availability:**
- Multi-region payment processing
- Partition tolerance (handling network splits)
- State recovery after crashes
- Hot standby vs active-active

**Performance:**
- Sub-100ms payment processing
- High-throughput settlement systems
- Query performance for transaction history
- Real-time balance updates

**Security:**
- End-to-end encryption
- Audit logging for compliance
- PCI-DSS considerations
- Anti-fraud detection signals

### 📝 Q&A Focus
- [system-design.md](Question%20Bank/system-design.md) - payment focus
- [advanced-topics.md](Question%20Bank/advanced-topics.md) - consistent hashing, consensus
- [database-sql.md](Question%20Bank/database-sql.md) - ACID, transactions, indexes
- [security.md](Question%20Bank/security.md) - encryption, audit

### 📝 Mock Interview Topics

**Design Scenarios:**
- Design a payment processing system (stripe/paypal model)
- Design a settlement system for transfers
- Design a fraud detection system
- Design a real-time balance ledger

**Technical Deep Dives:**
- How do you prevent duplicate charges?
- What happens if a payment fails mid-transaction?
- How do you ensure consistency across payment states?
- How would you recover from a leader failure?
- Describe your audit logging strategy

---

## 🧭 How to Choose Your Track

1. **Look at the job description** - What skills/experience are emphasized?
2. **Match to your background** - Which area do you have strength in?
3. **Consider your target company** - Different companies emphasize different areas
   - **Google/Meta/Microsoft:** Staff level → Track 2 (Backend + System Design)
   - **Stripe/Square/Payment Co:** Track 6 (Payment Systems)
   - **Netflix/Uber:** Track 2 (Backend + System Design) + Track 5 (ML Systems)
   - **LinkedIn/Facebook:** Track 3 (Full-Stack) or Track 2
   - **AWS/Azure/GCP:** Track 4 (DevOps/SRE)
   - **Companies with ML:** Track 5 (ML Systems Engineer)

4. **Check your confidence** - Are you weak in any area? Add 1-2 extra study days

5. **Use company style guidance** - See [COMPANY_INTERVIEW_GUIDES.md](COMPANY_INTERVIEW_GUIDES.md) to align your track with common interview emphasis.

---

## 📊 Study Time Estimates

| Track | Total Time | Focus Weeks | Coding Problems | System Design |
|-------|-----------|-----------|---|---|
| Backend Engineer | 3-4 weeks | 1, 2, 3 | 30+ | Basics (Chapters 01-10) |
| Backend + System Design | 5 weeks | 1-5 (full) | 40+ | Advanced (Chapters 01-29) |
| Full-Stack | 4-5 weeks | 1, 2, 3+frontend, 4 | 25+ | Basics + DevSecOps |
| DevOps/SRE | 3-4 weeks | focus 4 | 10+ (less coding) | Infrastructure only |
| ML Systems | 4-5 weeks | 1-3 (quick), 4 (focus) | 20+ | ML-focused design |
| Payment/Trading | 4-5 weeks | 1-3, 4 (focus) | 35+ | Distributed transactions |

---

*Choose your track, follow the recommended plan, and customize based on your strengths and target role!*
