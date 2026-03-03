# Observability and Monitoring

In a microservices world, when a request fails, it spans multiple independent services, network hops, and databases. Simple logging is no longer enough to investigate issues. You need **Observability**.

## 1. The Three Pillars of Observability

Observability is a measure of how well internal states of a system can be inferred from knowledge of its external outputs. It is built on three pillars:

### A. Metrics (The "What")
- **Definition**: Numerical measurements recorded over time. Great for dashboards, high-level trends, and triggering automated alerts.
- **Examples**: CPU Usage, Memory Consumption, HTTP Error Rates, Requests Per Second (RPS), Active Database Connections.
- **Tools**: **Prometheus** (the industry standard for scraping and storing time-series metrics), **Grafana** (visualization), Micrometer.
- **Key Concept - The RED Method**: For instrumenting services, focus on Rate (requests/sec), Errors (failed requests/sec), and Duration (response time p99).

### B. Logs (The "Why")
- **Definition**: Immutable, timestamped records of discrete events that happened over time. Used to find the exact root cause of a specific failure.
- **Examples**: "Failed to connect to database at 10:04", "NullPointerException at com.example.UserService.getUser".
- **Tools**: **ELK/EFK Stack** (Elasticsearch, Logstash/Fluentd, Kibana), Datadog, Splunk.
- **Best Practices**:
  - Always use **Structured Logging** (JSON format) so log aggregators can easily search and filter (e.g., `{"level":"ERROR", "userId":"123", "message":"Payment declined"}`).
  - Standardize logging levels (INFO, WARN, ERROR) and only log what's necessary to avoid enormous ingest costs.

### C. Distributed Tracing (The "Where")
- **Definition**: Follows a single user request as it travels across various microservices. Crucial for identifying *which* specific service is causing the bottleneck or failure in a complex chain.
- **How it works**:
  - **Trace ID**: A unique ID generated when a request enters the system (e.g., at the API Gateway) and is passed along in HTTP headers to every subsequent service call.
  - **Span ID**: Each unit of work (e.g., DB query, HTTP call) within the trace gets its own Span ID.
- **Tools**: **Jaeger**, **Zipkin**, OpenTelemetry.

## 2. OpenTelemetry (OTel)

Historically, you had to use different libraries to instrument metrics (Prometheus client), logs (Logback), and traces (Jaeger client).
**OpenTelemetry** is a CNCF open-source project that provides a unified, vendor-neutral standard and set of APIs/SDKs to generate, collect, and export telemetry data (metrics, logs, and traces) to any backend platform (Datadog, New Relic, Prometheus, etc.).

## 3. Alerting Strategies

- **Don't alert on causes, alert on symptoms**.
  - *Bad Alert*: "CPU is at 90%". (Who cares if the CPU is high but customers aren't affected?)
  - *Good Alert*: "Checkout page error rate is > 5%" or "Checkout latency p99 > 2 seconds". (Directly impacts the user).
- **Service Level Objectives (SLOs)**: Internal goals set for SLIs (Service Level Indicators, like latency). Example: "99.9% of API requests completed successfully in < 200ms over a rolling 30-day window." If you burn through your "error budget" too fast, you get an alert.

## Interview Questions on Observability

1. **How would you troubleshoot an issue where users complain the "checkout" process is slow in a microservice architecture?**
   - *Answer*: First, I would look at the **Distributed Traces** (e.g., in Jaeger) for the slow checkout requests to identify exactly which service or database call is causing the latency spike. Then, I would look at the **Metrics** (Prometheus/Grafana) for that specific service to see if it's struggling with high CPU, memory, or thread exhaustion. Finally, I would search the **Logs** (Kibana) for that specific `Trace ID` to find explicit error messages or long-running processing steps.
2. **What is the difference between structured and unstructured logging?**
   - *Answer*: Unstructured logging outputs plain text strings (e.g., "User 123 logged in"). Structured logging outputs structured data, usually JSON (e.g., `{"event":"login", "user_id": 123}`). Structured logging is essential for modern systems because log aggregators like Elasticsearch can easily index the JSON keys, allowing very fast, complex search queries across billions of logs.
3. **What is a Correlation ID (or Trace ID)?**
   - *Answer*: A unique identifier generated at the entry point of a distributed system. It is passed along in the headers of all subsequent network calls related to that initial request. It allows developers to tie together logs from a dozen different microservices to reconstruct the complete lifecycle of a single user action.
