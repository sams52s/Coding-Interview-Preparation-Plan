## ☁️ Cloud & DevOps [NEW] — Interview Q&A

---

### AWS Services for Java Apps

**Interviewer:** How would you use AWS Lambda with Java?  
**Candidate:** I'd implement Java functions using the AWS Lambda runtime in these steps:
1. Create handler class implementing RequestHandler interface
2. Use AWS SDK for additional services
3. Package dependencies using Maven/Gradle
4. Deploy using SAM or direct upload
5. Configure triggers (API Gateway, S3, etc.)
6. Set up monitoring with CloudWatch
7. Implement error handling and retries

**Interviewer:** How does Elastic Beanstalk help Java app deployment?  
**Candidate:** Elastic Beanstalk provides these key benefits:
1. Platform-as-a-Service for Java applications
2. Automatic handling of capacity provisioning
3. Load balancing configuration
4. Auto-scaling management
5. Health monitoring integration
6. Easy deployment of WAR/JAR files
7. Environment configuration management
8. Rolling updates with zero downtime

**Interviewer:** How do you deploy Java apps with ECS/EKS?  
**Candidate:** ECS runs Docker containers using Fargate or EC2, while EKS runs Kubernetes clusters. Both handle orchestration, scaling, and integration with AWS services.

**Interviewer:** How would you integrate S3 in Java apps?  
**Candidate:** I'd use the AWS SDK for Java to perform S3 operations like upload, download, and presigned URLs, ensuring proper IAM roles and bucket policies.

**Interviewer:** How do you configure RDS for Java applications?  
**Candidate:** I’d set up RDS with the correct engine, configure connection pools (HikariCP), manage credentials securely, and use multi-AZ or read replicas for availability.

---

### Containerization

**Interviewer:** What are Dockerfile best practices?  
**Candidate:** Use minimal base images, layer caching wisely, reduce image size, avoid secrets in images, and pin versions to ensure reproducibility.

**Interviewer:** How do you use Docker Compose?  
**Candidate:** It defines multi-container environments using a YAML file, making it easy to spin up dependent services (like app + DB) locally or in testing.

**Interviewer:** What Kubernetes objects do you commonly use?  
**Candidate:** Pods, Deployments, Services, ConfigMaps, Secrets, Ingress, StatefulSets, Jobs, and CronJobs, depending on workload needs.

**Interviewer:** What’s the purpose of Helm charts?  
**Candidate:** Helm is the package manager for Kubernetes, simplifying app deployment using reusable templates and configuration values.

**Interviewer:** What is a service mesh?  
**Candidate:** A service mesh (e.g., Istio) manages service-to-service communication, offering features like traffic routing, retries, timeouts, observability, and security (mTLS).

---

### CI/CD

**Interviewer:** How would you set up GitHub Actions workflows?  
**Candidate:** I’d define YAML workflows triggered by events (push, pull request), running steps for build, test, lint, deploy, and notifications.

**Interviewer:** How do Jenkins pipelines differ?  
**Candidate:** Jenkins uses scripted or declarative pipelines defined in Jenkinsfiles, integrating with various plugins and agents.

**Interviewer:** What is GitLab CI?  
**Candidate:** GitLab CI integrates directly into GitLab repositories, using `.gitlab-ci.yml` to define stages and jobs.

**Interviewer:** How does ArgoCD work?  
**Candidate:** ArgoCD implements GitOps for Kubernetes, syncing cluster state to a Git repository, managing application rollouts declaratively.

**Interviewer:** What is Infrastructure as Code (IaC)?  
**Candidate:** IaC uses tools like Terraform or CloudFormation to define infrastructure in code, enabling versioning, automation, and consistency.

---

## ☁️ Cloud & DevOps Deep Dive — Interview Q&A

---

### Multi-region failover architecture

**Candidate:** Design using DNS failover, multi-region deployments, and data replication strategies (active-active or active-passive) to ensure resilience.

---

### Zero-downtime deployments

**Candidate:** Use blue-green, canary, or rolling deployments to gradually shift traffic, allowing rollback without downtime.

---

### Service mesh architecture

**Candidate:** Introduces sidecar proxies (like Envoy) for observability, traffic management, and security without changing application code.

---

### Monitoring SLIs/SLOs/SLA

**Candidate:** Define metrics (latency, error rates), set targets (SLOs), and monitor compliance (SLAs) using tools like Prometheus, Grafana, or Datadog.

---

### Cloud cost optimization

**Candidate:** Use reserved or spot instances, right-size resources, monitor usage, and apply autoscaling to control costs.

---

### CI/CD pipeline security

**Candidate:** Secure secrets, apply least privilege, run builds in isolated environments, scan dependencies, and review pipeline configs.

---

### Kubernetes autoscaling

**Candidate:** Horizontal Pod Autoscaler (HPA), Vertical Pod Autoscaler (VPA), and cluster autoscaler adjust pod or node resources based on metrics.

---

### Infrastructure drift detection

**Candidate:** Use tools like Terraform Cloud or Driftctl to detect configuration drift and reconcile state files.

---

### Cloud-native disaster recovery

**Candidate:** Define RTO/RPO, implement regular backups, use multi-region failover, and test recovery plans.

---

### Managing secrets

**Candidate:** Use AWS Secrets Manager, HashiCorp Vault, or Kubernetes Secrets, avoiding hardcoding in code or configs.

---

### Infrastructure as code at scale

**Candidate:** Use modules, workspaces, policy as code, and CI integration to manage complex, multi-environment IaC deployments.

---

### Multi-cloud vs hybrid-cloud

**Candidate:** Multi-cloud spans multiple public providers; hybrid combines public cloud with on-premises; trade-offs include complexity vs vendor lock-in.

---

### Centralized logging and observability

**Candidate:** Aggregate logs with ELK/EFK, metrics with Prometheus, and traces with Jaeger, providing a unified observability stack.

---

### Cloud-native API gateway

**Candidate:** Use tools like Kong or Ambassador for routing, authentication, rate limiting, and analytics.

---

### Secure VPC and network segmentation

**Candidate:** Design subnets (public/private), apply NACLs and security groups, and use VPNs or Direct Connect securely.

---

### Kubernetes blue/green and canary deployments

**Candidate:** Use Deployment strategies, Helm, or Argo Rollouts to shift traffic safely and monitor metrics.

---

### Stateful workloads in Kubernetes

**Candidate:** Use StatefulSets, PersistentVolumeClaims, and appropriate storage classes to handle durable state.

---

### Auto-healing infrastructure

**Interviewer:** How would you implement auto-healing in cloud infrastructure?  
**Candidate:** I implement auto-healing through:
1. Health Checks
   - Load balancer health checks
   - Custom application metrics
   - System-level monitoring

2. Automatic Recovery
   - Auto Scaling Group configurations
   - Kubernetes liveness/readiness probes
   - Lambda functions for remediation

3. Monitoring & Alerts
   - CloudWatch metrics
   - Log analysis
   - Alert thresholds

4. Documentation
   - Runbooks for common failures
   - Recovery procedures
   - Incident response plans

**Interviewer:** What strategies do you use for container image security?  
**Candidate:** For container image security, I implement:
1. Image Scanning
   - Regular vulnerability scans
   - Policy enforcement
   - SBOM generation

2. Base Image Management
   - Use minimal base images
   - Regular updates
   - Version pinning

3. Build Process
   - Multi-stage builds
   - Layer optimization
   - Dependency scanning

4. Registry Security
   - Access controls
   - Image signing
   - Pull policies

5. Runtime Security
   - Immutable containers
   - Resource limitations
   - Security contexts
