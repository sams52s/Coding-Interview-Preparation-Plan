# Project 03: Spring Boot REST API

Navigation: [Projects](README.md) | [Stage 3](../README.md#learning-roadmap) | [Spring Boot](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md) | [Production Readiness Checklist](../PRODUCTION_READINESS_CHECKLIST.md)

## Goal

Build a production-style Spring Boot REST API that demonstrates CRUD, validation, persistence, exception handling, security basics, tests, and observability.

## Recommended domain

Use one of these:

- Student Management API.
- Task Management API.
- Inventory Management API.
- Library API based on Project 02.

## Core requirements

- REST endpoints for create/read/update/delete.
- DTOs separate from entities.
- Request validation.
- Global exception handling with consistent error response.
- Spring Data JPA persistence.
- Database migrations with Flyway or Liquibase.
- Pagination and sorting for list endpoints.
- Basic authentication or JWT-secured endpoints.
- Actuator health endpoint enabled.
- Unit and integration tests.

## Suggested package structure

```text
com.example.app
  config
  controller
  dto
  entity
  exception
  mapper
  repository
  security
  service
```

## Minimum API contract

| Method | Path | Purpose |
|--------|------|---------|
| `POST` | `/api/v1/resources` | Create resource |
| `GET` | `/api/v1/resources/{id}` | Read by id |
| `GET` | `/api/v1/resources?page=0&size=20` | List with pagination |
| `PUT` | `/api/v1/resources/{id}` | Full update |
| `PATCH` | `/api/v1/resources/{id}` | Partial update |
| `DELETE` | `/api/v1/resources/{id}` | Delete |

## Testing checklist

- [ ] Service unit tests.
- [ ] Controller tests for success and failure responses.
- [ ] Repository integration tests.
- [ ] Validation tests.
- [ ] Security tests for protected endpoints.

## Production checklist

Before calling this project interview-ready, validate it against [PRODUCTION_READINESS_CHECKLIST.md](../PRODUCTION_READINESS_CHECKLIST.md).

## Interview talking points

- Why DTOs are separated from entities.
- How transaction boundaries are chosen.
- How validation and exception handling work.
- How API versioning could be added.
- How you would deploy and monitor it.
