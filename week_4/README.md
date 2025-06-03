# 📅 Week 4: Advanced System Design & Architecture

> 🎯 Master large-scale distributed systems, design patterns, and complex algorithms. Focus on real-world applications and industry best practices.

---

## 📚 Core Focus Areas:

### 1. System Design Fundamentals
- [ ] **Scalability**
  - Horizontal vs Vertical Scaling
  - Capacity Planning
  - Performance Metrics & SLAs
- [ ] **High Availability**
  - Redundancy & Replication
  - Fault Tolerance Strategies
  - Disaster Recovery Planning
- [ ] **Distributed Systems**
  - CAP Theorem in Practice
  - ACID vs BASE
  - Consistency Models
  - Consensus Protocols (Paxos, Raft)
- [ ] **Security & Performance**
  - Authentication & Authorization
  - SSL/TLS
  - DDoS Protection
  - Rate Limiting Strategies
  - Performance Monitoring
  - APM Tools Integration
- [ ] **Data Management**
  - Data Partitioning
  - Consistent Hashing
  - Write-Ahead Logging
  - Change Data Capture
  - Data Migration Strategies

### 2. Core Infrastructure Components
- [ ] **Load Balancing**
  - Algorithms (Round Robin, Least Connections)
  - Health Checks
  - SSL Termination
- [ ] **Caching**
  - Distributed Cache (Redis, Memcached)
  - Cache Invalidation Strategies
  - Cache-Aside Pattern
- [ ] **Data Storage**
  - Polyglot Persistence
  - Sharding Strategies
  - Replication Patterns
  - Indexing Optimization
- [ ] **Service Communication**
  - gRPC vs REST
  - GraphQL Implementation
  - Service Discovery
  - Circuit Breaking
  - Bulk heading
  - Retry Patterns
- [ ] **Monitoring & Observability**
  - Distributed Tracing
  - Log Aggregation
  - Metrics Collection
  - Alert Management
  - SLO/SLA Monitoring

### 3. Modern Architecture Patterns
- [ ] **Microservices**
  - Domain-Driven Design
  - Service Mesh
  - API Gateway Pattern
- [ ] **Event-Driven Architecture**
  - Event Sourcing
  - CQRS
  - Message Queues
- [ ] **Serverless Architecture**
  - FaaS vs BaaS
  - Cold/Warm Starts
  - Cost Optimization
- [ ] **Cloud-Native Patterns**
  - Container Orchestration
  - Service Mesh Implementation
  - GitOps Workflow
  - Infrastructure as Code
  - Blue-Green Deployment
  - Canary Releases
- [ ] **Resilience Patterns**
  - Circuit Breaker
  - Bulkhead
  - Timeout/Retry
  - Rate Limiter
  - Dead Letter Queues
  - Fallback Mechanisms

### 4. Advanced Distributed Systems Concepts
- [ ] **Consensus Algorithms**
  - Raft Implementation
  - Byzantine Fault Tolerance
  - Vector Clocks
  - Gossip Protocols
- [ ] **Distributed Transactions**
  - 2PC/3PC Protocols
  - Saga Pattern
  - Distributed Locking
  - Transaction Isolation Levels
- [ ] **Stream Processing**
  - Apache Kafka Streams
  - Apache Flink
  - Real-time Analytics
  - Complex Event Processing
- [ ] **Data Replication**
  - Multi-Master Replication
  - Change Data Capture
  - Conflict Resolution
  - Eventual Consistency

### 5. Performance Engineering
- [ ] **Load Testing**
  - Performance Metrics
  - Stress Testing
  - Chaos Engineering
  - Performance Profiling
- [ ] **Database Optimization**
  - Query Optimization
  - Index Design
  - Partitioning Strategies
  - Read/Write Separation
- [ ] **Network Optimization**
  - Protocol Optimization
  - Connection Pooling
  - Content Delivery
  - Edge Computing

---

## 6. DevSecOps Practices and Tools

### Overview
DevSecOps integrates security into every phase of the DevOps lifecycle, ensuring continuous security and compliance without slowing down development.

### Key Practices
- Shift-left security: Integrate security early in the development process.
- Automated security testing: Static Application Security Testing (SAST), Dynamic Application Security Testing (DAST).
- Secrets management: Use tools like HashiCorp Vault or AWS Secrets Manager.
- Container security: Scan images with Clair or Trivy.
- Infrastructure security: Implement policy as code with tools like Open Policy Agent (OPA).

### Tools
- **Snyk**: Vulnerability scanning for dependencies.
- **Aqua Security**: Container security platform.
- **SonarQube**: Code quality and security analysis.
- **Twistlock (Palo Alto Prisma Cloud)**: Cloud-native security platform.

### Real-World Use Case
A fintech company integrated SAST and DAST tools into their CI/CD pipeline, catching vulnerabilities before deployment, reducing production incidents by 40%.

### Example: Integrating Snyk in GitHub Actions

```yaml
name: Security Scan
on: [push, pull_request]

jobs:
  snyk:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Run Snyk to check vulnerabilities
        uses: snyk/actions/node@master
        env:
          SNYK_TOKEN: ${{ secrets.SNYK_TOKEN }}
        with:
          args: test
```

---

## 7. Frontend Integration (React, Angular, Vue)

### Overview
Modern system design requires smooth integration between backend services and frontend frameworks.

### React
- Component-based architecture.
- Use of hooks for state and lifecycle management.
- Integration with REST/gRPC APIs via Axios or Fetch.

**Example: Fetching data in React**

```jsx
import React, { useEffect, useState } from 'react';

function UserList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch('/api/users')
      .then(res => res.json())
      .then(data => setUsers(data));
  }, []);

  return (
    <ul>
      {users.map(user => (<li key={user.id}>{user.name}</li>))}
    </ul>
  );
}

export default UserList;
```

### Angular
- Uses TypeScript and RxJS for reactive programming.
- Built-in dependency injection.
- HTTPClient for API calls.

**Example: Angular service to fetch data**

```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {}

  getUsers(): Observable<any> {
    return this.http.get('/api/users');
  }
}
```

### Vue
- Lightweight and flexible.
- Vuex for state management.
- Axios for HTTP requests.

**Example: Vue component fetching data**

```vue
<template>
  <ul>
    <li v-for="user in users" :key="user.id">{{ user.name }}</li>
  </ul>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return { users: [] };
  },
  mounted() {
    axios.get('/api/users').then(response => {
      this.users = response.data;
    });
  }
};
</script>
```

### Real-World Use Case
An e-commerce platform used React with GraphQL to optimize frontend-backend communication, reducing data over-fetching and improving load times.

---

## 8. Infrastructure as Code (Terraform, Pulumi)

### Overview
IaC enables automated, repeatable infrastructure provisioning and management.

### Terraform
- Declarative language (HCL).
- Provider ecosystem (AWS, Azure, GCP, Kubernetes).
- State management for tracking infrastructure.

**Example: Terraform to provision an AWS EC2 instance**

```hcl
provider "aws" {
  region = "us-west-2"
}

resource "aws_instance" "web" {
  ami           = "ami-0abcdef1234567890"
  instance_type = "t2.micro"

  tags = {
    Name = "ExampleInstance"
  }
}
```

### Pulumi
- Uses general-purpose languages (TypeScript, Python, Go).
- Allows complex logic and reusable components.

**Example: Pulumi TypeScript to create an S3 bucket**

```typescript
import * as aws from "@pulumi/aws";

const bucket = new aws.s3.Bucket("my-bucket", {
    acl: "private",
});
```

### Real-World Use Case
A SaaS startup adopted Terraform for multi-cloud deployments, enabling consistent environment setup and reducing manual errors.

---

## 9. Machine Learning Integration

### Overview
Integrating ML models into system architecture enables intelligent features like recommendations, fraud detection, and personalization.

### Common Approaches
- Model as a Service: Deploy models via REST/gRPC APIs.
- Batch inference pipelines for offline predictions.
- Online inference for real-time predictions.

### Tools & Frameworks
- TensorFlow Serving
- MLflow for model tracking
- Kubeflow for ML pipelines
- AWS SageMaker, Azure ML, GCP AI Platform

### Example: Simple Flask API serving a scikit-learn model

```python
from flask import Flask, request, jsonify
import pickle

app = Flask(__name__)
model = pickle.load(open('model.pkl', 'rb'))

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json['data']
    prediction = model.predict([data])
    return jsonify({'prediction': prediction.tolist()})

if __name__ == '__main__':
    app.run(debug=True)
```

### Real-World Use Case
Netflix uses ML to personalize content recommendations, increasing user engagement and retention.

---

## 10. Cloud-Native Practices (AWS, Azure, GCP)

### Overview
Cloud-native architectures leverage managed services, container orchestration, and microservices for scalability and agility.

### AWS
- Services: EC2, Lambda, ECS/EKS, RDS, S3, CloudWatch.
- Infrastructure as Code with CloudFormation or CDK.
- Serverless computing with Lambda.

### Azure
- Services: Azure Functions, AKS, Cosmos DB, Azure Monitor.
- Azure DevOps for CI/CD pipelines.
- Integration with Active Directory for identity management.

### GCP
- Services: Compute Engine, Cloud Functions, GKE, BigQuery.
- Deployment Manager for IaC.
- Stackdriver for monitoring and logging.

### Real-World Use Case
A global media company migrated to Kubernetes on AWS EKS, enabling zero-downtime deployments and autoscaling during peak traffic.

---

## 11. Observability (Prometheus, Grafana, ELK)

### Overview
Observability ensures visibility into system health, performance, and debugging capabilities.

### Prometheus
- Metrics collection and alerting.
- Pull-based model with exporters.
- Query language: PromQL.

### Grafana
- Visualization of metrics.
- Dashboards for real-time monitoring.
- Supports multiple data sources including Prometheus, Elasticsearch.

### ELK Stack (Elasticsearch, Logstash, Kibana)
- Centralized logging solution.
- Logstash for log ingestion and transformation.
- Elasticsearch for storage and search.
- Kibana for visualization.

### Example: Prometheus metrics exposition in a Python app

```python
from prometheus_client import start_http_server, Summary
import random
import time

REQUEST_TIME = Summary('request_processing_seconds', 'Time spent processing request')

@REQUEST_TIME.time()
def process_request():
    time.sleep(random.random())

if __name__ == '__main__':
    start_http_server(8000)
    while True:
        process_request()
```

### Real-World Use Case
Uber uses Prometheus and Grafana for monitoring microservices performance and ELK for centralized logging, enabling rapid incident response.

---

### 🧪 Practice Focus for this Week

- Solve 2–3 LeetCode/HackerRank/Codeforces/Codechef problems daily focused on Graphs and DP.
- Design a basic System Architecture for "URL Shortener" or "File Sharing Service".
- Build a small Spring Boot project containerized with Docker (Optional Challenge).
- Implement a simple Prometheus exporter for a sample service.
- Create a Terraform script to provision a basic cloud resource.
- Develop a React component that consumes a backend API.
- Build a minimal ML inference API in Flask.

## 📚 Recommended Case Studies
- URL Shortener System
- Real-time Chat Application
- Distributed Job Scheduler
- Payment Processing System
- Social Media Feed
- Distributed Search Engine
- Video Streaming Platform
- E-commerce Cart System
- Notification Service
- Distributed Caching System
- Real-time Analytics Pipeline
- Distributed Time Series Database
- Real-time Fraud Detection System
- Distributed Task Scheduler
- Global Content Delivery Network
- Multi-region Database System
- Real-time Recommendation Engine
- Distributed Rate Limiter
- Log Processing Pipeline

## 🔗 Essential Reading Materials
- System Design Interview (Alex Xu)
- Designing Data-Intensive Applications (Martin Kleppmann)
- Building Microservices (Sam Newman)

---

## 🔗 Additional Resources
- [System Design Primer GitHub](https://github.com/donnemartin/system-design-primer)
- [Spring Cloud Official Docs](https://spring.io/projects/spring-cloud)
- [LeetCode Graph Problems Card](https://leetcode.com/explore/learn/card/graph/)
- [LeetCode Dynamic Programming Problems Card](https://leetcode.com/explore/learn/card/dynamic-programming/)
- [GeeksforGeeks System Design](https://www.geeksforgeeks.org/system-design-tutorial/)
- [System Design Interview – An Insider's Guide](https://www.amazon.com/System-Design-Interview-insiders-guide/dp/0984782850)
- [Designing Data-Intensive Applications](https://www.amazon.com/Designing-Data-Intensive-Applications-Foundations-Scalability/dp/1492035612)
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Docker Official Documentation](https://docs.docker.com/get-started/)
- [Terraform Documentation](https://www.terraform.io/docs)
- [Pulumi Documentation](https://www.pulumi.com/docs/)
- [Prometheus Documentation](https://prometheus.io/docs/introduction/overview/)
- [Grafana Documentation](https://grafana.com/docs/)
- [ELK Stack Documentation](https://www.elastic.co/what-is/elk-stack)

---

### 📜 Engineering Articles
- [How Discord stores trillions of messages](https://discord.com/blog/how-discord-stores-trillions-of-messages)
- [How Uber built a real-time data pipeline](https://eng.uber.com/real-time-data-pipeline/)
- [How Netflix handles billions of events per day](https://netflixtechblog.com/how-netflix-handles-billions-of-events-per-day-4f3b2c1a5e8d)
- [How Spotify scales its backend](https://engineering.atspotify.com/2020/01/how-spotify-scales-its-backend/)
- [How Facebook handles billions of messages](https://engineering.fb.com/2018/10/01/core-data/facebook-messenger-messaging-scale/)
- [How Twitter handles billions of tweets](https://blog.twitter.com/engineering/en_us/topics/infrastructure/2020/how-twitter-handles-billions-of-tweets.html)
- [How LinkedIn handles billions of connections](https://engineering.linkedin.com/blog/2019/how-linkedin-handles-billions-of-connections)
- [Building In-Video Search at Netflix](https://netflixtechblog.com/building-in-video-search-at-netflix-1c5f3f3c5f3f)
- [How Canva scaled Media uploads from Zero to 50 Million per Day](https://www.canva.com/learn/how-canva-scaled-media-uploads-from-zero-to-50-million-per-day/)
- [How Airbnb avoids double payments in a Distributed Payments System](https://medium.com/airbnb-engineering/how-airbnb-avoids-double-payments-in-a-distributed-payments-system-5f3b2c1a5e8d)
- [Stripe’s payments APIs - The first 10 years](https://stripe.com/docs/stripe-10-years)
- [Real time messaging at Slack](https://slack.engineering/real-time-messaging-at-slack/)
- [How Dropbox handles file synchronization](https://blogs.dropbox.com/tech/2018/10/how-dropbox-handles-file-synchronization/)
- [How Pinterest handles billions of pins](https://medium.com/pinterest-engineering/how-pinterest-handles-billions-of-pins-4f3b2c1a5e8d)
- [How Cloudflare's CDN Works](https://blog.cloudflare.com/how-cloudflare-works/)
- [DynamoDB at Scale](https://www.amazon.science/publications/amazon-dynamodb-a-scalable-predictably-performant-and-fully-managed-nosql-database-service)
- [Meta's Social Graph](https://engineering.fb.com/2021/02/11/data-infrastructure/tao/)
- [Google's Large Scale Distributed Systems](https://research.google/pubs/pub41378/)

### 🔬 Advanced Distributed Systems Papers
- [Paxos: The Part-Time Parliament](https://lamport.azurewebsites.net/pubs/lamport-paxos-simple.pdf)
- [MapReduce: Simplified Data Processing on Large Clusters](https://research.google/pubs/archive/33491.pdf)
- [The Dataflow Model of Computation](https://research.google/pubs/archive/33491.pdf)
- [The Tail at Scale](https://research.google/pubs/archive/43438.pdf)
- [The Chubby lock service for loosely-coupled distributed systems](https://research.google/pubs/archive/33491.pdf)
- [The Google File System](https://research.google/pubs/archive/33491.pdf)
- [Dynamo: Amazon’s Highly Available Key-value Store](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf)
- [Spanner: Google’s Globally-Distributed Database](https://research.google/pubs/archive/43438.pdf)
- [Kafka: a Distributed Messaging System for Log Processing](https://kafka.apache.org/documentation/)
- [Spanner: Google’s Globally-Distributed Database](https://research.google/pubs/archive/43438.pdf)
- [Bigtable: A Distributed Storage System for Structured Data](https://research.google/pubs/archive/bigtable.pdf)
- [ZooKeeper: Wait-free coordination for Internet-scale systems](https://research.google/pubs/archive/33491.pdf)
- [The Log-Structured Merge-Tree (LSM-Tree)](https://research.google/pubs/archive/33491.pdf)
- [The Chubby lock service for loosely-coupled distributed systems](https://research.google/pubs/archive/33491.pdf)
- [The Twelve-Factor App](https://12factor.net/)
- [The Art of Multiprocessor Programming](https://www.amazon.com/Art-Multiprocessor-Programming-MIT-Press/dp/0262035675)
- [The Tail at Scale](https://research.google/pubs/archive/43438.pdf)
- [The Data Distribution Service (DDS) for Real-Time Systems](https://www.omg.org/spec/DDS/)
- [CAP Theorem](https://en.wikipedia.org/wiki/CAP_theorem)
- [ACID vs BASE](https://www.geeksforgeeks.org/acid-properties-in-dbms/)
- [Distributed Systems](https://en.wikipedia.org/wiki/Distributed_system)
- [Microservices](https://microservices.io/)
- [RESTful APIs](https://restfulapi.net/)
---
## 📅 Weekly Revision + Mock Test
- **Revision**: Review all topics covered this week.
- [Problem List](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/week_4/solution_of_week_4_coding_problem/problem%20List.md)

---

## 🎯 Advanced Challenges
1. Design a distributed rate limiter
2. Implement a simple distributed cache
3. Build a basic service mesh
4. Create a fault-tolerant message queue

---

## 🎯 End Goal of Week 4

By the end of this week, you should be able to:
- Architect scalable backend systems.
- Understand how microservices communicate and manage failure.
- Build professional APIs following best practices.
- Write optimized SQL queries and manage transaction integrity.
- Solve graph and DP-based problems with confidence.
- Integrate security seamlessly in CI/CD pipelines.
- Build frontend applications consuming backend APIs.
- Automate infrastructure provisioning using IaC tools.
- Deploy machine learning models in production environments.
- Implement cloud-native solutions leveraging managed services.
- Establish observability pipelines for monitoring and alerting.

---
