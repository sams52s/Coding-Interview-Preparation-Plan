# Production Readiness Checklist

Navigation: [Main README](README.md) | [Spring Boot](Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/README.md) | [DevSecOps](DevSecOps%20and%20Frontend/README.md) | [Cloud and Observability](Cloud%20and%20Observability/README.md)

Use this checklist before calling a Spring Boot or backend project production-ready.

## Application design

- [ ] Clear package structure.
- [ ] DTOs are separate from entities.
- [ ] Validation is enforced at API boundaries.
- [ ] Global exception handling returns consistent error responses.
- [ ] No business logic in controllers.
- [ ] Transaction boundaries are explicit.
- [ ] Timeouts are configured for external calls.
- [ ] Retries are limited and safe.
- [ ] Idempotency is used for create/payment/order-like operations.

## Configuration

- [ ] Environment-specific config is externalized.
- [ ] Secrets are not committed.
- [ ] Config values have safe defaults.
- [ ] Feature flags are documented.
- [ ] Profiles are clearly named.

## API readiness

- [ ] API versioning strategy exists.
- [ ] Pagination and sorting exist for list endpoints.
- [ ] Request/response examples are documented.
- [ ] OpenAPI/Swagger is available.
- [ ] Rate limiting is planned for public endpoints.
- [ ] CORS rules are restricted.

## Persistence

- [ ] Database migrations are managed with Flyway or Liquibase.
- [ ] Indexes support main queries.
- [ ] N+1 query risks are checked.
- [ ] Connection pool is configured.
- [ ] Backup and restore plan exists.
- [ ] Transaction isolation is understood for critical flows.

## Security

- [ ] Authentication is enabled where needed.
- [ ] Authorization is tested.
- [ ] Passwords/secrets are stored securely.
- [ ] TLS is used.
- [ ] Sensitive fields are not logged.
- [ ] Dependency scan is part of CI.
- [ ] Input validation prevents injection.
- [ ] Audit logs exist for sensitive actions.

## Testing

- [ ] Unit tests cover business logic.
- [ ] Controller tests cover success and failure responses.
- [ ] Integration tests cover persistence.
- [ ] Security tests cover protected endpoints.
- [ ] Testcontainers or equivalent is used for realistic dependencies.
- [ ] Load test baseline exists for key endpoints.

## Observability

- [ ] Actuator health endpoint is enabled.
- [ ] Readiness and liveness checks are configured.
- [ ] Metrics are exported.
- [ ] Structured logs include correlation id.
- [ ] Distributed tracing is planned for service-to-service calls.
- [ ] Alerts exist for latency, errors, saturation, and dependency failures.

## Deployment

- [ ] Docker image uses a small base and non-root user.
- [ ] Build is repeatable.
- [ ] CI pipeline runs tests and scans.
- [ ] Rollback strategy exists.
- [ ] Database migration strategy is safe.
- [ ] Resource limits are configured.
- [ ] Graceful shutdown is enabled.

## Runbook

- [ ] How to deploy.
- [ ] How to rollback.
- [ ] How to check health.
- [ ] How to inspect logs.
- [ ] How to restore database.
- [ ] Known failure modes and first actions.
