# Visual Architecture Diagrams

Navigation: [Main README](README.md) | [Project Architecture](PROJECT_ARCHITECTURE.md) | [System Design Template](SYSTEM_DESIGN_CASE_STUDY_TEMPLATE.md)

Use these Mermaid diagrams as starting points for notes, case studies, and interview explanations.

For complete case-specific diagrams, see:

- [Netflix-style streaming platform](Real-World%20Case%20Studies/01_netflix_streaming_platform.md)
- [Uber-style ride matching](Real-World%20Case%20Studies/02_uber_ride_matching.md)
- [Instagram-style feed and media platform](Real-World%20Case%20Studies/03_instagram_feed_media.md)

## Repository learning flow

```mermaid
flowchart TD
    A["README.md"] --> B["PROJECT_ARCHITECTURE.md"]
    A --> C["INTERVIEW_TRACKS.md"]
    A --> J["Java / Spring / DSA READMEs"]
    A --> S["System Design READMEs"]
    A --> P["Projects"]
    A --> Q["Question Bank"]
    J --> Q
    S --> Q
    P --> Q
```

## System design chapter sequence

```mermaid
flowchart LR
    F["01-05 Fundamentals"] --> I["06-10 Infrastructure"]
    I --> M["11-15 Microservices"]
    M --> D["16-19 Distributed Systems"]
    D --> P["20-23 Performance"]
    P --> S["24-25 DevSecOps / Frontend"]
    S --> ML["26-27 Infra / ML"]
    ML --> O["28-29 Cloud / Observability"]
```

## Production Spring Boot service

```mermaid
flowchart LR
    Client --> LB["Load Balancer"]
    LB --> App["Spring Boot API"]
    App --> Cache["Redis Cache"]
    App --> DB[("SQL Database")]
    App --> MQ["Message Queue"]
    App --> Obs["Metrics / Logs / Traces"]
    MQ --> Worker["Async Worker"]
    Worker --> DB
```

## Microservice order flow

```mermaid
sequenceDiagram
    participant C as Client
    participant G as API Gateway
    participant O as Order Service
    participant P as Payment Service
    participant I as Inventory Service
    participant N as Notification Service
    C->>G: Create order
    G->>O: POST /orders
    O->>P: Authorize payment
    P-->>O: Payment authorized
    O->>I: Reserve stock
    I-->>O: Stock reserved
    O-->>G: Order confirmed
    G-->>C: 201 Created
    O-->>N: order-confirmed event
```

## Observability flow

```mermaid
flowchart TD
    App["Application"] --> Logs["Structured Logs"]
    App --> Metrics["Metrics"]
    App --> Traces["Traces"]
    Logs --> Store["Log Store"]
    Metrics --> Prom["Prometheus"]
    Traces --> TraceStore["Tracing Backend"]
    Prom --> Grafana["Grafana Dashboards"]
    Store --> Grafana
    TraceStore --> Grafana
    Prom --> Alerts["Alertmanager"]
```

## ML serving flow

```mermaid
flowchart LR
    Client --> API["Product API"]
    API --> Feature["Feature Service"]
    API --> Model["Model Serving API"]
    Model --> Registry["Model Registry"]
    Model --> Metrics["Model Metrics"]
    Feature --> OnlineStore[("Online Feature Store")]
    Batch["Batch Pipeline"] --> OfflineStore[("Offline Store")]
    OfflineStore --> Registry
```

## Payment idempotency flow

```mermaid
flowchart TD
    Request["Payment Request + Idempotency Key"] --> Check{"Key exists?"}
    Check -- Yes --> Return["Return previous result"]
    Check -- No --> Lock["Create idempotency record"]
    Lock --> Charge["Call payment network"]
    Charge --> Ledger["Write ledger entry"]
    Ledger --> Done["Mark key completed"]
    Done --> Response["Return response"]
```
