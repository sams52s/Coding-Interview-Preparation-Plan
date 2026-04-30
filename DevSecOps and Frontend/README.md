# DevSecOps and Frontend

This folder connects secure delivery practices with frontend/backend integration, which matters for both full-stack projects and backend interviews.

**Navigation:** [Main README](../README.md) | [Learning Roadmap](../README.md#learning-roadmap) | [Project Architecture](../PROJECT_ARCHITECTURE.md) | [Interview Tracks](../INTERVIEW_TRACKS.md) | Previous: [Performance Engineering](../Performance%20Engineering/README.md) | Next: [Infra and ML](../Infra%20and%20ML/README.md)

**Practice:** [Production Readiness Checklist](../PRODUCTION_READINESS_CHECKLIST.md) | [Company Interview Guides](../COMPANY_INTERVIEW_GUIDES.md) | [Visual Diagrams](../VISUAL_ARCHITECTURE_DIAGRAMS.md)

## Folder files
- [24_devsecops_practices.md](24_devsecops_practices.md) — CI/CD security, dependency checks, SAST/DAST, secret scanning, image scanning, and supply-chain awareness.
- [25_frontend_integration.md](25_frontend_integration.md) — REST consumption, CORS, auth integration, JWT refresh, WebSocket, and frontend-backend responsibilities.
- [advanced_security_oauth_jwt_secure_coding.md](advanced_security_oauth_jwt_secure_coding.md) — OAuth/OIDC flow selection, JWT vulnerabilities, secure coding, Spring resource server checklist, and CORS incident review.
- [README.md](README.md) — this folder guide.

## How it connects
- Extends [Spring Boot DevOps automation](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/07_devops_automation.md) and [Spring Boot frontend communication](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/08_frontend_communication.md).
- Prepares deployment and integration topics used in [Infra and ML](../Infra%20and%20ML/README.md) and [Cloud and Observability](../Cloud%20and%20Observability/README.md).
- Supports [Question Bank/security.md](../Question%20Bank/security.md), [Question Bank/cloud-devops.md](../Question%20Bank/cloud-devops.md), and [Question Bank/web-frontend.md](../Question%20Bank/web-frontend.md).

## Suggested reading order
1. [24_devsecops_practices.md](24_devsecops_practices.md)
2. [25_frontend_integration.md](25_frontend_integration.md)
3. [advanced_security_oauth_jwt_secure_coding.md](advanced_security_oauth_jwt_secure_coding.md)
4. [04_security_practices.md](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/04_security_practices.md)
5. [08_frontend_communication.md](../Java%20%26%20Spring%20Interview%20Preparation/Spring%20Boot/08_frontend_communication.md)

## Security checklist
- Dependency scanning and patching cadence.
- Secret scanning, secret rotation, and vault usage.
- Least-privilege IAM and environment separation.
- TLS everywhere, secure cookies, CORS, CSRF, XSS, and CSP.
- Container image scanning and signed artifacts.
- Audit logging and incident-response notes.

## Deep-dive practice
- Explain OAuth vs OIDC vs JWT without mixing the terms.
- Identify JWT validation bugs in a code review.
- Compare browser token storage options and XSS/CSRF trade-offs.
- Design a secure CI/CD pipeline with scanning and artifact signing.
- Walk through the CORS incident in [advanced_security_oauth_jwt_secure_coding.md](advanced_security_oauth_jwt_secure_coding.md).
