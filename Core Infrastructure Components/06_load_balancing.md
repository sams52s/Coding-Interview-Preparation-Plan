# ⚖️ Load Balancing in System Design

## 1. Overview

Load balancing is the process of distributing network or application traffic across multiple servers. It improves scalability, fault tolerance, and ensures high availability. In modern distributed systems and cloud-native architectures, load balancing is essential for system resilience.

---

## 2. Why Load Balancing Matters

Load balancing ensures that no single server bears too much demand, preventing potential outages and enhancing the overall user experience.

- Prevents server overload
- Ensures high system availability
- Supports horizontal scalability
- Enables zero-downtime deployments
- Optimizes resource utilization
- ⚡ Distributes real-time traffic
- 🧠 Enables intelligent routing decisions
- 🔄 Provides resilience through automatic failover
- 🌍 Supports global scalability with latency-based routing

---

## 3. Types of Load Balancing

| Type | Description | Example Use Case |
|------|-------------|------------------|
| Layer 4 (Transport) | Operates at TCP/UDP level | NGINX, HAProxy in TCP mode |
| Layer 7 (Application) | Operates at HTTP level (content-aware) | Path-based routing in API gateway |
| Global Load Balancing | Routes traffic across geo-regions | Multi-region failover |
| DNS Load Balancing | Distributes via DNS responses | CDN routing |
| Hardware Load Balancer | Dedicated appliance | F5, Citrix NetScaler |

### Implementation Examples

#### Layer 4 Load Balancing (HAProxy)
```conf
frontend tcp-in
    bind *:3306
    mode tcp
    default_backend mysql-cluster

backend mysql-cluster
    mode tcp
    balance roundrobin
    server mysql-1 10.0.0.1:3306 check
    server mysql-2 10.0.0.2:3306 check
```

#### Layer 7 Load Balancing (NGINX)
```nginx
http {
    upstream api_servers {
        least_conn; # Least connection algorithm
        server api1.example.com:8080;
        server api2.example.com:8080;
        server api3.example.com:8080 backup;
    }

    server {
        listen 80;
        location /api/ {
            proxy_pass http://api_servers;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
        }
    }
}
```

---

## 4. Real-World Scenarios

### Scenario 1: E-commerce Platform
```plaintext
Requirements:
- Handle 100K concurrent users
- Session persistence
- SSL termination
- Global distribution

Solution:
1. AWS Global Accelerator + ALB
2. Sticky sessions enabled
3. Auto-scaling groups per region
4. Health checks every 5 seconds
```

### Scenario 2: Microservices Architecture
```yaml
# Docker Compose with Traefik
version: '3'
services:
  traefik:
    image: traefik:v2.5
    command:
      - "--providers.docker=true"
      - "--providers.docker.exposedbydefault=false"
    ports:
      - "80:80"
      - "8080:8080"

  api-service:
    deploy:
      replicas: 3
      labels:
        - "traefik.enable=true"
        - "traefik.http.routers.api.rule=PathPrefix(`/api`)"
```

### Scenario 3: SaaS Platform with Multi-Tenant Users
```plaintext
Requirements:
- Isolate tenant traffic
- High throughput per region
- Autoscaling based on traffic bursts

Solution:
1. DNS Load Balancing with region-based routing
2. Traefik + Envoy layered with Kubernetes Ingress
3. Redis-backed rate-limiting middleware
```

---

## 5. Advanced Configuration Patterns

### Circuit Breaking Pattern
```yaml
# Spring Cloud Gateway Configuration
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
          filters:
            - name: CircuitBreaker
              args:
                name: userServiceBreaker
                fallbackUri: forward:/fallback
```

### Rate Limiting Implementation
```nginx
# NGINX Rate Limiting
http {
    limit_req_zone $binary_remote_addr zone=one:10m rate=10r/s;
    
    server {
        location /api/ {
            limit_req zone=one burst=20 nodelay;
            proxy_pass http://backend;
        }
    }
}
```

---

## 6. Architecture Examples

### Example: Web App Behind Load Balancer

```
[Client] → [Load Balancer] → [Web Server 1]
                             → [Web Server 2]
                             → [Web Server 3]
```

### Real-life scenario:
> An eCommerce platform with millions of users globally uses AWS ELB with auto-scaling groups. As traffic spikes during Black Friday, the load balancer evenly routes users to newly added instances.

---

## 7. Spring Boot Example with NGINX

### `nginx.conf`
```nginx
http {
  upstream backend {
    server app1.example.com;
    server app2.example.com;
  }

  server {
    listen 80;

    location / {
      proxy_pass http://backend;
    }
  }
}
```

---

## 8. Health Checks

- Liveness and readiness probes
- Periodic TCP/HTTP checks to verify backend status
- Auto-remove unhealthy nodes from rotation

---

## 9. Load Balancer as API Gateway

- Combine load balancing with authentication, rate limiting, routing
- Tools: Kong, Envoy, Ambassador, Spring Cloud Gateway

---

## 10. Performance Optimization Tips

| Tip | Implementation |
|-----|----------------|
| SSL Termination | Offload SSL at LB level |
| Connection Pooling | Keep-alive connections |
| Compression | Enable GZIP for text content |
| Caching | Use CDN for static content |
| TCP Keepalive | Maintain persistent connections |
| HTTP/2 Support | Multiplexing for faster transfer |

### Monitoring Dashboard Example
```javascript
// Prometheus Query for Error Rate
sum(rate(http_requests_total{status=~"5.."}[5m])) 
  / 
sum(rate(http_requests_total[5m])) * 100
```

---

## 11. Common Pitfalls

| Pitfall | Impact |
|--------|--------|
| No sticky sessions when needed | Broken user sessions |
| Improper timeout configs | Dropped connections |
| Missing health checks | Serving traffic to dead instances |
| Single point of failure | Risk of total outage |
| Overuse of sticky sessions | Limits scaling and load distribution |

---

## 12. Best Practices

- Use managed services when possible
- Always enable health checks
- Consider sticky sessions for stateful apps
- Use TLS termination at load balancer
- Deploy in multi-AZ (availability zone)

---

## 13. Monitoring and Metrics

| Metric | Description |
|--------|-------------|
| Request rate | Number of incoming requests |
| Response time | Latency to serve requests |
| Error rate | 4xx/5xx responses |
| Backend health | Number of healthy instances |
| Throughput | Volume of data transferred over time |

Tools: Prometheus, Grafana, CloudWatch, Datadog

---

## 14. References

- [NGINX Load Balancing Guide](https://docs.nginx.com/nginx/admin-guide/load-balancer/http-load-balancer/)
- [AWS Elastic Load Balancer Docs](https://docs.aws.amazon.com/elasticloadbalancing/)
- [HAProxy Configuration Guide](https://www.haproxy.org/#docs)
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)

---

## 15. Thought Process for Designing Load Balancing Systems

> Before choosing or designing a load balancing solution, consider:

- 🔍 Nature of workload: stateless vs stateful
- 🌐 Geographic distribution of users
- ⚙️ Existing deployment architecture (K8s, VM, monolith)
- 🔄 Dynamic vs. static scaling
- 🔒 Security and compliance requirements

> 💡 Use a layered approach: DNS → Global LB → App Gateway → Internal LB

---