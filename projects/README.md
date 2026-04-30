# Project Portfolio Map

This folder turns the study plan into practical portfolio work. Each project is written as a build specification so you can implement it in Java/Spring when you are ready.

Navigation: [Main README](../README.md) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | [Production Readiness Checklist](../PRODUCTION_READINESS_CHECKLIST.md)

## Project sequence

| Project | Week | Main skills | Primary track |
|---------|------|-------------|---------------|
| [01_student_record_management.md](01_student_record_management.md) | Week 1 | Java classes, arrays, OOP, validation | Backend Engineer |
| [02_library_management_system.md](02_library_management_system.md) | Week 2 | Collections, generics, IO/NIO, exceptions | Backend Engineer |
| [03_spring_boot_rest_api.md](03_spring_boot_rest_api.md) | Week 3 | REST API, JPA, validation, security, testing | Backend / Full-Stack |
| [04_microservice_architecture.md](04_microservice_architecture.md) | Week 4 | service boundaries, gateway, messaging, observability | Senior / DevOps |
| [05_system_design_case_studies.md](05_system_design_case_studies.md) | Week 4-5 | system design drills and interview storytelling | All tracks |

Completed reference cases live in [Real-World Case Studies](../Real-World%20Case%20Studies/README.md). Use those as examples before writing your own case-study answers.

## Portfolio completion standard

Each finished project should include:

- Clear problem statement and user stories.
- README with setup, run, test, and design notes.
- Domain model and API contract where relevant.
- Error handling and edge-case handling.
- Tests for core behavior.
- Short "interview explanation" section: design choices, trade-offs, failures, and future improvements.

## Suggested folder convention when code is added

```text
projects/
  01-student-record-management/
    README.md
    src/
    tests/
  02-library-management-system/
    README.md
    src/
    tests/
  03-spring-boot-rest-api/
    README.md
    src/
    tests/
  04-microservice-architecture/
    README.md
    services/
    docker-compose.yml
  05-system-design-case-studies/
    README.md
    diagrams/
```

Keep implementation folders separate from these specification files so the learning plan stays readable.
