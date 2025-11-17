# 🟢 High Availability in System Design

## Overview

**High Availability (HA)** means a system or component is continuously operational for a long time, with minimal downtime. The goal is to ensure your system remains accessible and functional—even when things go wrong.

- **Availability** is measured as a percentage (e.g., 99.9%, "three nines") and reflects **uptime vs. downtime**.
- High Availability does **not** mean zero downtime, but it aims to minimize both the frequency and duration of outages.

---

## Availability Metrics

| Level            | Annual Downtime         | Description          |
|------------------|------------------------|----------------------|
| 99% (“two nines”)| ~3.65 days              | Acceptable for internal tools |
| 99.9% (“three nines”) | ~8.76 hours         | Typical for SaaS     |
| 99.99% (“four nines”)| ~53 minutes         | Used by banks, APIs  |
| 99.999% (“five nines”)| ~5.26 minutes      | Telecom, mission critical |

**Formula:**  
```
Availability = (Total Uptime) / (Total Uptime + Total Downtime)
```

---

## Principles of High Availability

1. **Redundancy**  
   Duplicate critical components or functions (servers, power, networks).

2. **Failover & Recovery**  
   Automatic switching to standby or backup when failure occurs.

3. **Fault Isolation**  
   Minimize blast radius—limit failures from impacting the whole system.

4. **Monitoring & Alerting**  
   Detect failures rapidly and respond promptly.

5. **Regular Testing**  
   Practice failover drills, chaos engineering, and disaster recovery tests.

---

## HA Architectures and Patterns

### 1. Load Balancers

- **Active-Active**: All nodes serve traffic.
- **Active-Passive**: One node serves, standby takes over if primary fails.

**Example:**  
- **AWS Elastic Load Balancer (ELB)** distributes traffic across healthy instances. If one fails, traffic shifts automatically.

### 2. Redundancy at Every Layer

| Layer              | Redundant Option           | Example                           |
|--------------------|---------------------------|-----------------------------------|
| Compute            | Multiple servers           | Web server cluster                |
| Storage            | RAID, replication          | Amazon S3, GCP Storage            |
| Network            | Dual network paths         | Two ISPs                          |
| Database           | Replicas, clusters         | MySQL Group Replication, MongoDB Replica Set |
| Power              | Backup power supplies      | UPS, diesel generators            |

### 3. Geographic Distribution

- **Multi-AZ (Availability Zone)**: Spread resources across data centers in a region.
- **Multi-Region**: Active-active or active-passive deployments across continents.

**Example:**  
- **Netflix** runs in multiple AWS regions, can reroute traffic if a whole region goes down.

### 4. Stateless Services

- Stateless applications are easier to scale and recover because any instance can handle any request.

---

## Real-World Examples

### 1. Netflix

- Deploys microservices across multiple AWS regions and Availability Zones.
- Uses “Chaos Monkey” to randomly kill instances and test system resilience.

### 2. Google Search

- Traffic is routed to nearest healthy data center using **Anycast DNS**.
- If a data center fails, requests reroute instantly.

### 3. Financial Trading Platforms

- Require **99.999%** uptime for real-time trades.
- Use redundant data feeds, backup trading engines, and disaster recovery sites.

---

## Strategies for Achieving High Availability

### 1. **Failover Mechanisms**

- **DNS Failover**: Route users to another data center if one goes down.
- **Database Failover**: Use master-slave replication or automated failover solutions (e.g., AWS RDS Multi-AZ).

### 2. **Replication**

- **Data Replication**: Multiple copies of data for reliability.
- **Synchronous vs. Asynchronous Replication**: Synchronous guarantees no data loss but can add latency.

### 3. **Health Checks & Auto-Healing**

- Load balancers use health checks to send traffic only to healthy nodes.
- **Auto-healing**: Automatically restart failed VMs/containers (Kubernetes, AWS ASG).

### 4. **Graceful Degradation**

- If a component fails, system continues with reduced functionality.
- Example: Search might be temporarily disabled, but checkout still works.

---

## Failure Scenarios and Mitigations

| Scenario                      | HA Mitigation                        | Example                |
|-------------------------------|--------------------------------------|------------------------|
| Server crash                  | Auto-restart, traffic re-routed      | AWS EC2 auto-replace   |
| Database failure              | Read replica, failover, backups      | PostgreSQL Streaming Replication |
| Data center outage            | Multi-AZ or multi-region deployment  | Google Cloud multi-region buckets|
| Network partition             | Redundant routes, CDN fallback       | Cloudflare failover    |
| Code deployment bug           | Blue-green or canary deployments     | Kubernetes rolling updates |

---

## High Availability in Cloud

- **Cloud providers** offer HA by default—multi-AZ, managed load balancers, auto-scaling.
- **Serverless functions (AWS Lambda, Azure Functions):** No single point of failure, auto-scaled by the platform.

---

## Best Practices

- **Automate failover and recovery**: Don’t rely on manual intervention.
- **Test for failure regularly**: Use chaos engineering tools.
- **Eliminate single points of failure**: Redundancy in all critical components.
- **Monitor everything**: Use alerts for downtime, latency spikes, or unusual error rates.
- **Keep recovery procedures documented and tested**.

---

## Tools & Technologies

| Purpose              | Tool/Service         | Description                      |
|----------------------|---------------------|----------------------------------|
| Load Balancing       | HAProxy, NGINX, AWS ELB | Distributes traffic         |
| Database Replication | MySQL Group Replication, MongoDB Replica Set | Syncs copies      |
| Clustering           | Kubernetes, Docker Swarm | Manages HA for containers   |
| Failover/DR          | AWS Route 53, GCP Cloud DNS | DNS-based failover         |
| Monitoring           | Prometheus, Grafana, Datadog | Track uptime/alerts      |
| Backup               | Veeam, AWS Backup   | Scheduled, versioned backups     |

---

## Sample Configurations

### 1. Kubernetes Deployment with Multi-Zone HA

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  replicas: 4
  template:
    spec:
      containers:
      - name: my-app
        image: my-app:latest
      affinity:
        podAntiAffinity:
          requiredDuringSchedulingIgnoredDuringExecution:
          - labelSelector:
              matchExpressions:
              - key: "zone"
                operator: In
                values: ["zone-a", "zone-b"]
```

### 2. MySQL Group Replication (simplified)

```sql
-- Each MySQL node joins the group for multi-master HA
CHANGE MASTER TO MASTER_USER='repl', MASTER_PASSWORD='pass', MASTER_AUTO_POSITION=1;
START GROUP_REPLICATION;
```

---

## Advanced HA Configurations & Real-World Examples

### 1. Netflix's Multi-Region Active-Active Setup
```yaml
# AWS Route53 Configuration for Global DNS
{
    "Type": "AWS::Route53::HealthCheck",
    "Properties": {
        "HealthCheckConfig": {
            "Port": 80,
            "Type": "HTTP",
            "ResourcePath": "/health",
            "FailureThreshold": 3,
            "RequestInterval": 30
        }
    }
}

# Netflix-style Auto-Recovery Lambda
def handle_instance_failure(event, context):
    instance_id = event['detail']['instance-id']
    try:
        # Attempt recovery
        ec2.stop_instances(InstanceIds=[instance_id])
        ec2.start_instances(InstanceIds=[instance_id])
        
        # If recovery fails, replace instance
        response = auto_scaling.execute_policy(
            AutoScalingGroupName='netflix-asg',
            PolicyName='ReplaceUnhealthy'
        )
    except Exception as e:
        # Trigger incident management
        sns.publish(Topic='incident-topic', Message=str(e))
```

### 2. Financial Trading Platform HA Architecture
```java
// High-Frequency Trading System with Circuit Breaker
@Service
public class TradingService {
    private final CircuitBreaker circuitBreaker;
    
    @Autowired
    public TradingService(CircuitBreakerFactory factory) {
        this.circuitBreaker = factory.create("trading-service", 
            new CircuitBreakerConfig.Builder()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(60))
                .slidingWindowSize(10)
                .build());
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TradeResult executeTrade(TradeRequest request) {
        return circuitBreaker.run(() -> {
            // Primary exchange execution
            try {
                return primaryExchange.execute(request);
            } catch (ExchangeException e) {
                // Failover to backup exchange
                return backupExchange.execute(request);
            }
        }, throwable -> fallbackTrade(request));
    }
}
```

### 3. E-Commerce Platform's Database HA Setup
```sql
-- PostgreSQL Streaming Replication Configuration
-- primary postgresql.conf
wal_level = replica
max_wal_senders = 10
wal_keep_segments = 64

-- Standby postgresql.conf
primary_conninfo = 'host=primary port=5432 user=repl password=secret'
restore_command = 'cp /var/lib/postgresql/archive/%f %p'

-- Monitoring Query for Replication Lag
SELECT client_addr, 
       state, 
       sent_lsn, 
       write_lsn, 
       flush_lsn, 
       replay_lsn, 
       (extract(epoch from now() - pg_last_xact_replay_timestamp()))::int as lag_in_seconds
FROM pg_stat_replication;
```

### 4. Modern Cloud-Native HA Patterns

```yaml
# Kubernetes StatefulSet with PodDisruptionBudget
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: highly-available-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: ha-app
  template:
    metadata:
      labels:
        app: ha-app
    spec:
      topologySpreadConstraints:
      - maxSkew: 1
        topologyKey: topology.kubernetes.io/zone
        whenUnsatisfiable: DoNotSchedule
        labelSelector:
          matchLabels:
            app: ha-app
      containers:
      - name: app
        image: my-ha-app:latest
        readinessProbe:
          httpGet:
            path: /healthz
            port: 8080
          initialDelaySeconds: 10
          periodSeconds: 5
        livenessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 15
          periodSeconds: 15
---
apiVersion: policy/v1
kind: PodDisruptionBudget
metadata:
  name: ha-app-pdb
spec:
  minAvailable: 2
  selector:
    matchLabels:
      app: ha-app
```

### 5. Real-World Disaster Recovery Implementation

```python
# Multi-Region Backup Strategy
class DisasterRecoveryManager:
    def __init__(self):
        self.primary_region = 'us-east-1'
        self.dr_region = 'us-west-2'
        
    def initiate_failover(self):
        try:
            # 1. Check primary region health
            if not self._is_region_healthy(self.primary_region):
                # 2. Update DNS
                self._update_route53_records()
                
                # 3. Promote DR database to primary
                self._promote_dr_database()
                
                # 4. Scale up DR region resources
                self._scale_dr_resources()
                
                # 5. Update application configs
                self._update_application_configs()
                
                return True
        except Exception as e:
            self._notify_incident_team(str(e))
            return False
    
    def _promote_dr_database(self):
        # AWS RDS Multi-Region Promotion
        rds.promote_read_replica(
            DBInstanceIdentifier='dr-database'
        )
```

### 6. Load Testing & Capacity Planning

```bash
# Artillery.io Load Test Configuration
config:
  target: "https://api.example.com"
  phases:
    - duration: 300
      arrivalRate: 10
      rampTo: 100
  conditions:
    - target: p99
      value: 100
      condition: "<"
    
scenarios:
  - name: "High Availability Test"
    flow:
      - get:
          url: "/health"
      - think: 1
      - post:
          url: "/api/v1/orders"
          json:
            productId: "{{ $randomString() }}"
```

---

## Further Reading

- [Google SRE Book - Chapter on Availability](https://sre.google/books/)
- [AWS Well-Architected Framework: Reliability](https://aws.amazon.com/architecture/well-architected/)
- [Netflix Tech Blog](https://netflixtechblog.com/)
- [Designing Data-Intensive Applications, Martin Kleppmann]
- [Principles of Chaos Engineering](https://principlesofchaos.org/)

---

**Tip:**  
In interviews or design discussions, always mention how you would design for high availability, cite real-world outages (e.g., AWS S3 outage), and the mitigation steps you’d take.

---

**Want diagrams or even more code samples? Just ask!**
# 🟢 High Availability in System Design

## Overview

**High Availability (HA)** means a system or component is continuously operational with minimal downtime. The goal is to **keep your system accessible even in the face of failures**—hardware, software, network, or even operator error.

- **Availability** is measured as a percentage (e.g., 99.9%, “three nines”).
- High Availability does **not** mean zero downtime, but strives for the lowest possible downtime.
- **MTTR/MTTF** (Mean Time to Recovery/Failure) are critical metrics.

---

## Availability Metrics

| Level            | Annual Downtime   | Description               | Typical Use Case                 |
|------------------|------------------|---------------------------|----------------------------------|
| 99% (two nines)  | ~3.65 days       | Internal tools            | Dev/test environments            |
| 99.9% (three nines) | ~8.76 hours   | SaaS apps, web platforms  | Online services, e-commerce      |
| 99.99% (four nines) | ~53 minutes   | Payment, APIs             | Banking, critical APIs           |
| 99.999% (five nines) | ~5.26 minutes| Mission critical          | Telecom, stock trading           |

**Formula:**  
```
Availability = (Uptime) / (Uptime + Downtime)
```
**MTBF & MTTR**:  
- **MTBF (Mean Time Between Failures)**: How often failures occur.
- **MTTR (Mean Time To Recovery)**: How quickly failures are fixed.
- **Tip:** Always strive to reduce MTTR!

---

## Principles of High Availability

1. **Redundancy:** Duplicate everything that’s critical (servers, disks, power, networks).
2. **Failover & Recovery:** Automatic switch to standby on failure.
3. **Fault Isolation:** Contain the blast radius—failure in one part shouldn’t take down the rest.
4. **Monitoring & Alerting:** Fast detection and notification of problems.
5. **Regular Testing:** Simulate failures (chaos engineering) and test disaster recovery.
6. **Graceful Degradation:** If something fails, degrade service quality instead of full outage.

---

## HA Architectures and Patterns

### 1. Load Balancers

- **Active-Active:** All nodes serve traffic. Good for scale and resilience.
- **Active-Passive:** Standby node ready to take over. Used for stateful workloads.
- **Example:**  
  - **AWS ELB** distributes user requests and checks health of each node.
  - **Real-World:** Google Front Ends (GFE) globally balance traffic, rerouting users instantly on node or region failure.

### 2. Redundancy at Every Layer

| Layer              | Redundant Option           | Example                                      |
|--------------------|---------------------------|----------------------------------------------|
| Compute            | Multiple servers/nodes     | Web cluster, Kubernetes Pods                 |
| Storage            | RAID, object replication   | AWS S3 (11 9’s durability), RAID 10          |
| Network            | Dual ISPs, multiple NICs   | Netflix uses multiple transit providers      |
| Database           | Replicas, sharding         | MongoDB Replica Set, MySQL Group Replication |
| Power              | UPS, generators            | Data centers: UPS, diesel, dual power feeds  |

### 3. Geographic Distribution

- **Multi-AZ (Availability Zone):** Redundant resources in separate datacenters within a region.
- **Multi-Region:** Deploy across multiple global regions for disaster resilience.
- **Example:**  
  - **Netflix:** Multi-region active-active (failover in seconds).
  - **Google Cloud Spanner:** Replicates data globally.

### 4. Stateless Services

- **Stateless apps**: Easy to auto-recover and scale (any request handled by any instance).
- **Real-World:**  
  - **Instagram:** Web and API servers are stateless, only DB/cache store session or persistent state.

### 5. Consensus and Leader Election

- **ZooKeeper**, **etcd**, or **Consul** for HA of system metadata and leader selection.
- **Example:** Kubernetes uses etcd (quorum-based) for cluster state.

---

## Real-World Examples

### 1. Netflix
- Microservices deployed across 3+ AWS regions and many AZs.
- **Chaos Monkey**: Randomly terminates instances to prove resilience.
- **Simian Army**: Test network, AZ, and region failures.

### 2. Google Search
- **Anycast DNS**: User requests routed to the nearest healthy region.
- If one data center fails, users are instantly routed elsewhere.

### 3. Financial Trading
- **99.999%+** availability (SLA): Hot-hot datacenters, redundant exchanges, multiple feeds.
- **Automated failover:** Milliseconds matter—fastest recovery is critical.

### 4. WhatsApp
- Erlang-based servers, “supervisor trees” automatically restart crashed processes.
- Runs clusters on both primary and backup datacenters, all ready to serve.

### 5. AWS S3 Outage (2017)
- A single mistyped command took down a huge chunk of S3’s US-EAST-1. Shows why automation, least-privilege, and regional isolation are important.

---

## Strategies for Achieving High Availability

### 1. **Failover Mechanisms**
- **DNS Failover:** Use low TTL; switch to backup site fast.
- **Database Failover:** Managed DBs (AWS RDS Multi-AZ), or open-source (Patroni, MySQL Group Replication).

### 2. **Replication**
- **Synchronous:** Safer, but can cause higher latency.
- **Asynchronous:** Fast, but risk of some data loss on failover.
- **Hybrid:** Modern systems can do both, depending on workload.

### 3. **Health Checks & Auto-Healing**
- **Load balancer health checks:** Only route to healthy backends.
- **Kubernetes:** Probes (readiness/liveness) auto-replace bad pods.

### 4. **Graceful Degradation**
- **Fallbacks:**  
  - Show cached content if DB is down.
  - Reduce feature set (disable “expensive” features).
- **Example:** Google Maps disables 3D or traffic layers during heavy load.

### 5. **Blue-Green & Canary Deployments**
- Minimize deployment risk by shifting only a portion of traffic to new code.

---

## Failure Scenarios and Mitigations

| Scenario           | Mitigation                           | Real Example                       |
|--------------------|--------------------------------------|------------------------------------|
| Server crash       | Auto-restart, traffic reroute        | AWS EC2 self-heal; K8s auto-replace|
| Database failure   | Read replica, automated failover     | AWS Aurora Multi-AZ                |
| Data center outage | Multi-region/zone deployment         | Netflix, Google failover           |
| Network partition  | CDN fallback, multiple paths         | Cloudflare/Google CDN edge reroute |
| Software bugs      | Canary/blue-green deployments        | Facebook “feature flags”           |
| Operator error     | Principle of “least privilege”, automation, “undo” tools | AWS S3 outage (2017)               |

---

## High Availability in Cloud

- **Cloud-native HA**: Managed load balancers, multi-AZ VMs, serverless (AWS Lambda/Azure Functions).
- **Cloud provider SLAs:** Know your SLA! Cloud services still fail (see AWS and Azure region-wide outages).
- **Cross-cloud HA:** Some enterprises run active-active across multiple cloud vendors.

---

## Best Practices

- **Eliminate all single points of failure** (compute, network, power, DB, DNS, etc.).
- **Automate everything** (failover, health checks, monitoring, rollbacks).
- **Practice chaos engineering** (Netflix, Gremlin).
- **Test disaster recovery plans** regularly (not just docs—real failovers!).
- **Use rate limiting and circuit breakers** to prevent cascading failures.
- **Implement detailed observability** (distributed tracing, SLO/SLA alerting).
- **Security is part of HA:** Don’t let DDoS or ransomware take you offline.

---

## Tools & Technologies

| Purpose              | Tool/Service         | Description                               |
|----------------------|---------------------|-------------------------------------------|
| Load Balancing       | HAProxy, NGINX, AWS ELB | Distributes traffic, L4/L7 balancers  |
| Database Replication | MySQL Group Replication, MongoDB RS, Patroni | DB failover |
| Clustering/Orchestration | Kubernetes, Docker Swarm | Self-heal containers, reschedule |
| Consensus            | etcd, ZooKeeper, Consul | Leader election, distributed locks      |
| Failover/DR          | Route 53, GCP Cloud DNS | DNS-based, global health checks          |
| Monitoring           | Prometheus, Grafana, Datadog | Dashboards, metrics, alerting       |
| Chaos Testing        | Chaos Monkey, Gremlin, LitmusChaos | Test failures           |
| Backup               | Veeam, AWS Backup, GCP Snapshots | Versioned, offsite backups          |

---

## Sample Configurations & Real-World Patterns

### 1. Kubernetes Multi-Zone Deployment with PodDisruptionBudget

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  replicas: 4
  template:
    spec:
      containers:
      - name: my-app
        image: my-app:latest
      affinity:
        podAntiAffinity:
          requiredDuringSchedulingIgnoredDuringExecution:
          - labelSelector:
              matchExpressions:
              - key: "zone"
                operator: In
                values: ["zone-a", "zone-b"]
---
apiVersion: policy/v1
kind: PodDisruptionBudget
metadata:
  name: ha-app-pdb
spec:
  minAvailable: 3
  selector:
    matchLabels:
      app: my-app
```

### 2. PostgreSQL Streaming Replication

```sql
-- On primary:
wal_level = replica
max_wal_senders = 10
-- On standby:
primary_conninfo = 'host=primary port=5432 user=replicator password=secret'
-- Monitor replication lag
SELECT pg_last_wal_receive_lsn(), pg_last_wal_replay_lsn();
```

### 3. Blue-Green Deployment Example (Simplified)

- Two environments: “Blue” (current live), “Green” (new version).
- Deploy to “Green”, reroute traffic after health checks pass.
- Rollback is instant—just swap traffic back.

---

## Advanced Patterns

- **Quorum-based Replication** (Cassandra, etcd): Survives some node failures.
- **Write-ahead logging**: Ensures durability of data before confirming success.
- **Multi-cloud or Hybrid HA**: Critical for compliance, disaster recovery, or vendor lock-in protection.

---

## Further Reading

- [Google SRE Book - Availability](https://sre.google/books/)
- [AWS Well-Architected Framework: Reliability](https://aws.amazon.com/architecture/well-architected/)
- [Netflix Tech Blog](https://netflixtechblog.com/)
- [Designing Data-Intensive Applications, Martin Kleppmann]
- [Principles of Chaos Engineering](https://principlesofchaos.org/)
- [Gremlin Chaos Engineering Resources](https://www.gremlin.com/chaos-engineering/)

---

## Tips for Interviews & Real Projects

- **Always cite real-world outages** (e.g., AWS S3 2017, Azure global DNS 2019, Google GCP 2019).
- **Discuss trade-offs:** Synchronous = safer but slower, Async = faster but riskier.
- **Explain blast radius:** Don’t let any single failure take down your whole system.
- **Diagram your HA plan:** Visuals are great in interviews!
- **Emphasize recovery time and regular testing.**

---

**Need sample diagrams, detailed case studies, or a cloud-specific focus? Just ask!**