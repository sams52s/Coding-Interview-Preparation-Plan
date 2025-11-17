# 📡 Monitoring & Observability in System Design

## 1. Introduction
Monitoring and observability are critical for understanding system behavior, detecting issues, and ensuring reliability. While **monitoring** focuses on collecting and alerting on predefined metrics, **observability** enables deeper understanding and debugging of complex systems by providing rich telemetry data.

---

## 2. Key Concepts

| Concept | Description |
|---------|-------------|
| Monitoring | Tracking system health and performance through metrics, logs, and alerts |
| Observability | Ability to understand the internal state of a system from its outputs |
| Telemetry | Data collected from systems (metrics, logs, traces) |
| SLO (Service Level Objective) | Target performance/reliability goal |
| SLA (Service Level Agreement) | Contractual commitment to customers |
| SLI (Service Level Indicator) | Measurable performance metric (e.g., availability %) |

---

## 3. Three Pillars of Observability

| Pillar | Description | Example Tools |
|--------|-------------|---------------|
| Metrics | Numeric measurements over time | Prometheus, Datadog |
| Logs | Textual event records | ELK Stack, Loki |
| Traces | End-to-end request tracking | Jaeger, Zipkin |

---

## 4. Monitoring vs Observability

| Aspect | Monitoring | Observability |
|--------|------------|--------------|
| Purpose | Detect known issues | Investigate unknown issues |
| Data | Predefined metrics | Rich, multi-dimensional telemetry |
| Scope | Narrow, alert-focused | Broad, debugging-focused |

---

## 5. Real-World Implementation Examples

### 5.1 Spring Boot Metrics with Micrometer
```java
@RestController
public class OrderController {
    private final Counter orderCounter;
    private final Timer orderProcessingTimer;

    public OrderController(MeterRegistry registry) {
        this.orderCounter = registry.counter("orders.created");
        this.orderProcessingTimer = registry.timer("orders.processing.time");
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        orderCounter.increment();
        return orderProcessingTimer.record(() -> {
            // Process order
            return ResponseEntity.ok(order);
        });
    }
}
```

### 5.2 Distributed Tracing with OpenTelemetry
```java
@Service
public class OrderService {
    private final Tracer tracer;

    public Order processOrder(OrderRequest request) {
        Span orderSpan = tracer.spanBuilder("process-order")
            .setAttribute("orderId", request.getId())
            .startSpan();
        
        try (Scope scope = orderSpan.makeCurrent()) {
            // Process order steps with child spans
            Span paymentSpan = tracer.spanBuilder("process-payment")
                .startSpan();
            // Process payment
            paymentSpan.end();
            
            return order;
        } finally {
            orderSpan.end();
        }
    }
}
```

---

## 6. Monitoring Stack Setup

### 6.1 Prometheus Configuration
```yaml
global:
  scrape_interval: 15s
  evaluation_interval: 15s

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']

  - job_name: 'node-exporter'
    static_configs:
      - targets: ['localhost:9100']
```

### 6.2 Grafana Dashboard Example
```json
{
  "panels": [
    {
      "title": "API Response Times",
      "type": "graph",
      "datasource": "Prometheus",
      "targets": [
        {
          "expr": "histogram_quantile(0.95, rate(http_server_requests_seconds_bucket[5m]))",
          "legendFormat": "p95"
        }
      ]
    }
  ]
}
```

---

## 7. Alert Rules and Configurations

### 7.1 Prometheus Alert Rules
```yaml
groups:
  - name: application_alerts
    rules:
      - alert: HighErrorRate
        expr: rate(http_server_requests_seconds_count{status="5xx"}[5m]) > 0.1
        for: 5m
        labels:
          severity: critical
        annotations:
          summary: "High error rate detected"
          description: "Error rate is {{ $value }} for the last 5 minutes"
```

---

## 8. Instrumentation

- Use **OpenTelemetry** SDKs for consistent telemetry.
- Wrap business logic with timers to measure performance.
- Add unique request IDs for log correlation.

Example (Python + Prometheus Client):
```python
from prometheus_client import start_http_server, Summary
import time, random

REQUEST_TIME = Summary('request_processing_seconds', 'Time spent processing request')

@REQUEST_TIME.time()
def process_request():
    time.sleep(random.random())

if __name__ == '__main__':
    start_http_server(8000)
    while True:
        process_request()
```

---

## 9. Alerting Best Practices

- Use severity levels (info, warning, critical)
- Avoid alert fatigue by tuning thresholds
- Include contextual info in alerts (links to dashboards)
- Test alerts regularly

---

## 10. Distributed Tracing Example

```java
Tracer tracer = GlobalTracer.get();
Span span = tracer.buildSpan("checkout").start();
try {
    // checkout logic
} finally {
    span.finish();
}
```

---

## 11. Visualization & Dashboards

- Use **Grafana** to create dashboards for:
  - System health
  - Application performance
  - Business KPIs
- Drill down from metrics → logs → traces.

---

## 12. Security in Monitoring

- Encrypt telemetry in transit and at rest
- Restrict access to sensitive logs
- Redact personal data before storing logs

---

## 13. Advanced Observability Patterns

### 13.1 Log Correlation
```java
@Aspect
@Component
public class LoggingAspect {
    private final TracingService tracer;

    @Around("execution(* com.example.service.*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String traceId = tracer.getCurrentSpan().getTraceId();
        MDC.put("traceId", traceId);
        try {
            return joinPoint.proceed();
        } finally {
            MDC.remove("traceId");
        }
    }
}
```

---

## 14. Common Pitfalls

- Collecting too much data without purpose
- No alert prioritization
- Missing correlation between metrics/logs/traces
- Ignoring cost of telemetry storage

---

## 15. References

- [Prometheus Documentation](https://prometheus.io/docs/)
- [OpenTelemetry](https://opentelemetry.io/)
- [Grafana](https://grafana.com/)
- [Google SRE Book - Monitoring](https://sre.google/sre-book/monitoring-distributed-systems/)