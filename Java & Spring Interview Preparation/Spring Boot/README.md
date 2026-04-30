# Spring Boot

This folder is the Spring Boot and enterprise backend section of the plan. It is the main companion for the backend engineering stage and connects directly to microservices, DevSecOps, cloud, observability, and final interview revision.

**Navigation:** [Main README](../../README.md) | [Learning Roadmap](../../README.md#learning-roadmap) | [Project Architecture](../../PROJECT_ARCHITECTURE.md) | [Java & Spring Hub](../README.md) | [Microservices](../../Microservices%20and%20Architecture/README.md) | [Spring Boot Q&A](../../Question%20Bank/spring-boot.md)

**Practice:** [Spring Boot REST API Project](../../projects/03_spring_boot_rest_api.md) | [Production Readiness Checklist](../../PRODUCTION_READINESS_CHECKLIST.md) | [Concept-to-Code Mapping](../../CONCEPT_TO_CODE_MAPPING.md) | [Code Refactoring Examples](../../CODE_REFACTORING_EXAMPLES.md)

## Folder files
- [01_core_spring_framework.md](01_core_spring_framework.md) — IoC, dependency injection, beans, scopes, lifecycle, profiles, and Spring internals.
- [02_enterprise_integration_patterns.md](02_enterprise_integration_patterns.md) — messaging, integration patterns, routing, transformations, and enterprise communication.
- [03_data_access_persistence.md](03_data_access_persistence.md) — JPA, repositories, transactions, ORM, persistence design, and database access.
- [04_security_practices.md](04_security_practices.md) — authentication, authorization, secure defaults, Spring Security, JWT, and endpoint protection.
- [05_cloud_platform_integration.md](05_cloud_platform_integration.md) — cloud deployment, managed services, service integration, and cloud-native Spring practices.
- [06_observability_monitoring.md](06_observability_monitoring.md) — Actuator, Micrometer, Prometheus, logs, metrics, tracing, and monitoring.
- [07_devops_automation.md](07_devops_automation.md) — CI/CD, Docker, build automation, deployment workflows, and release hygiene.
- [08_frontend_communication.md](08_frontend_communication.md) — REST APIs, CORS, frontend integration, auth flows, and gateway patterns.
- [09_machine_learning_springboot.md](09_machine_learning_springboot.md) — model-serving integration and ML-backed endpoints in Spring Boot.
- [10_best_practices.md](10_best_practices.md) — maintainability, production readiness, configuration, error handling, and coding standards.
- [11_JKS_Certificate.md](11_JKS_Certificate.md) — JKS certificates, key stores, trust stores, TLS basics, and certificate handling.
- [README.md](README.md) — this Spring Boot guide.

## How it connects
- Builds on [JAVA](../JAVA/README.md), especially exceptions, generics, annotations, concurrency, and memory management.
- Powers the [Spring Boot backend engineering stage](../../README.md#learning-roadmap).
- Leads into [Microservices and Architecture](../../Microservices%20and%20Architecture/README.md), [DevSecOps and Frontend](../../DevSecOps%20and%20Frontend/README.md), [Infra and ML](../../Infra%20and%20ML/README.md), and [Cloud and Observability](../../Cloud%20and%20Observability/README.md).
- Connects to production security through [OAuth, JWT, and secure coding](../../DevSecOps%20and%20Frontend/advanced_security_oauth_jwt_secure_coding.md).

## Suggested reading order
1. [01_core_spring_framework.md](01_core_spring_framework.md)
2. [03_data_access_persistence.md](03_data_access_persistence.md)
3. [04_security_practices.md](04_security_practices.md)
4. [08_frontend_communication.md](08_frontend_communication.md)
5. [06_observability_monitoring.md](06_observability_monitoring.md)
6. [07_devops_automation.md](07_devops_automation.md)
7. [02_enterprise_integration_patterns.md](02_enterprise_integration_patterns.md)
8. [05_cloud_platform_integration.md](05_cloud_platform_integration.md)
9. [09_machine_learning_springboot.md](09_machine_learning_springboot.md)
10. [10_best_practices.md](10_best_practices.md)
11. [11_JKS_Certificate.md](11_JKS_Certificate.md)

## Related question bank files
- [Spring Boot](../../Question%20Bank/spring-boot.md)
- [Spring Data JPA & Hibernate](../../Question%20Bank/spring-data-JPA-%26-hibernate.md)
- [REST API Design](../../Question%20Bank/rest-api.md)
- [Microservices](../../Question%20Bank/microservices.md)
- [Security](../../Question%20Bank/security.md)
- [Testing & Quality](../../Question%20Bank/testing-quality.md)

## Deep-dive practice
- Testing slices: choose `@WebMvcTest`, `@DataJpaTest`, `@SpringBootTest`, or Testcontainers based on the risk being tested.
- OAuth2/OIDC: explain login, resource-server validation, token claims, refresh tokens, and failure handling.
- Native/AOT: know when Spring Boot native images help startup and memory, and when reflection/proxy behavior needs configuration.
- Production readiness: verify actuator, logs, metrics, tracing, health checks, migrations, Docker, CI/CD, rollback, and secrets handling with [PRODUCTION_READINESS_CHECKLIST.md](../../PRODUCTION_READINESS_CHECKLIST.md).
- Code quality: use [CODE_REFACTORING_EXAMPLES.md](../../CODE_REFACTORING_EXAMPLES.md) to practice controller/service/domain separation and idempotent workflows.
