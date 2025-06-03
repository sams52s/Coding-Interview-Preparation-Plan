## 🧱 System Design Interview Q&A

### How should you approach system design interviews?
**Interview Question:**  
How should you approach a system design interview to maximize your chances of success?

**Answer:**  
Approach system design interviews by following a structured process:
1. **Clarify requirements:** Ask clarifying questions to ensure you understand what's being asked. Confirm functional and non-functional requirements.
2. **Define the scope:** Set boundaries for what you will and won't cover. Prioritize core features.
3. **High-level design:** Sketch a block diagram of the system, identifying major components and their interactions.
4. **Detailed component design:** Dive deeper into key components, explaining internal workings, data flow, and interfaces.
5. **Address bottlenecks:** Identify potential scalability, performance, or reliability issues and discuss mitigation strategies.
6. **Discuss trade-offs:** Explain why you chose certain technologies or designs, and what alternatives exist.

**Real-World Scenario:**  
Designing a ride-sharing service, you clarify if real-time tracking is a requirement, define whether to include driver onboarding, and focus on trip matching and location updates as core features.

**Follow-up Question:**  
What are some common pitfalls to avoid in system design interviews?

**Follow-up Answer:**  
Avoid jumping into details without clarifying requirements, ignoring scalability, neglecting failure scenarios, and forgetting to discuss trade-offs. Always communicate your thought process.

---

## System Design Problems (Interview-Style Q&A)

### 1. URL Shortener
**Interview Question:**  
How would you design a scalable URL shortener like bit.ly?

**Answer:**  
A URL shortener maps long URLs to short, unique aliases. The system must support high write (creation) and read (redirection) throughput, ensure uniqueness, and optionally provide analytics.

**High-Level Design:**  
- API server for creating and resolving short URLs  
- Database to store mappings  
- Unique ID generator  
- Optional analytics/logging layer  

**Real-World Scenario:**  
If a viral tweet uses your shortener, millions of users might click the link simultaneously. Your system should handle the load and avoid collisions.

**Pseudocode Example:**
```python
def shorten_url(long_url):
    short_id = generate_unique_id()
    db.save(short_id, long_url)
    return BASE_URL + short_id

def resolve_url(short_id):
    return db.get(short_id)
```

**Follow-up Questions:**  
- How would you ensure short URL uniqueness?  
  *Use a counter, hash, or UUID. For high scale, use sharded counters or distributed ID generators like Snowflake.*
- How would you handle database scaling?  
  *Use sharding, caching hot URLs, and possibly a NoSQL store for fast lookups.*

---

### 2. API Rate Limiter
**Interview Question:**  
How would you design an API rate limiter to prevent abuse of your service?

**Answer:**  
An API rate limiter restricts how many requests a user or client can make in a given time window.

**Design Approaches:**  
- Token Bucket or Leaky Bucket algorithms  
- Store counters in an in-memory cache (e.g., Redis) for fast access  
- Enforce limits per API key or IP

**Real-World Scenario:**  
Preventing a single user from overwhelming your login endpoint with brute-force attempts.

**Pseudocode Example:**
```python
def is_allowed(user_id):
    key = f"rate:{user_id}"
    count = redis.incr(key)
    if count == 1:
        redis.expire(key, 60)  # 60 seconds window
    return count <= MAX_REQUESTS
```

**Follow-up Questions:**  
- How do you handle distributed rate limiting?  
  *Use a centralized cache (Redis/Memcached) or synchronize counters across nodes.*
- How do you prevent race conditions?  
  *Atomic increment operations in Redis or using Lua scripts for complex logic.*

---

### 3. Distributed Cache
**Interview Question:**  
How would you design a distributed caching system for a high-traffic web application?

**Answer:**  
Distributed caches (e.g., Redis, Memcached) store frequently accessed data in memory across multiple nodes, reducing database load.

**Key Considerations:**  
- Consistency (eventual, strong)  
- Cache invalidation strategies  
- Data partitioning (sharding)

**Real-World Scenario:**  
Serving user profile data from cache to minimize database hits on a social network.

**Pseudocode Example:**
```python
def get_user_profile(user_id):
    profile = cache.get(user_id)
    if not profile:
        profile = db.get(user_id)
        cache.set(user_id, profile, ttl=3600)
    return profile
```

**Follow-up Questions:**  
- How do you keep cache and database in sync?  
  *Use TTLs, write-through/write-back, or explicit invalidation.*
- What if a cache node fails?  
  *Use consistent hashing and replication for fault tolerance.*

---

### 4. Chat Service
**Interview Question:**  
How would you design a scalable real-time chat service?

**Answer:**  
Chat services require low-latency message delivery, message persistence, and support for online/offline users.

**Design Components:**  
- WebSocket servers for real-time communication  
- Message queues/brokers  
- Database for message history  
- Presence service

**Real-World Scenario:**  
Users in a group chat expect to receive new messages instantly, even on mobile devices.

**Pseudocode Example:**
```python
def send_message(sender, recipient, message):
    store_in_db(sender, recipient, message)
    if recipient_online(recipient):
        deliver_via_websocket(recipient, message)
    else:
        queue_for_later(recipient, message)
```

**Follow-up Questions:**  
- How do you scale WebSocket connections?  
  *Load balance across multiple servers and use sticky sessions or distributed pub/sub.*
- How do you support message delivery to offline users?  
  *Store undelivered messages and deliver when the user reconnects.*

---

### 5. Video Streaming Service
**Interview Question:**  
How would you design a large-scale video streaming platform like YouTube?

**Answer:**  
Key requirements include video upload, encoding, storage, CDN distribution, and adaptive bitrate streaming.

**High-Level Design:**  
- Upload/encoding pipeline  
- Distributed storage (object stores)  
- CDN for content delivery  
- Metadata and search indexing

**Real-World Scenario:**  
Millions of users watch videos concurrently, and new uploads must be processed and available quickly.

**Pseudocode Example:**
```python
def upload_video(user, video_file):
    video_id = save_metadata(user, video_file)
    enqueue_encoding_job(video_id, video_file)
    return video_id
```

**Follow-up Questions:**  
- How do you support adaptive streaming?  
  *Encode videos at multiple bitrates, serve via HLS/DASH protocols.*
- How do you handle CDN cache invalidation?  
  *Purge or version content when updated, use cache-control headers.*

---

### 6. Ride-Sharing Service
**Interview Question:**  
How would you design a ride-sharing platform like Uber?

**Answer:**  
Core features include real-time location tracking, trip matching, pricing, and payment processing.

**Design Components:**  
- Location tracking service  
- Matching engine  
- Pricing and payment modules  
- Notification system

**Real-World Scenario:**  
Matching drivers to riders in a busy city with thousands of concurrent requests.

**Pseudocode Example:**
```python
def match_rider_to_driver(rider_location):
    nearby_drivers = find_nearby_drivers(rider_location)
    best_driver = select_best_driver(nearby_drivers)
    notify_driver(best_driver)
    return best_driver
```

**Follow-up Questions:**  
- How do you handle surge pricing?  
  *Dynamically adjust prices based on demand in real-time.*
- How do you ensure location accuracy?  
  *Use GPS, apply smoothing/filtering algorithms.*

---

### 7. Collaborative Document Editing
**Interview Question:**  
How would you design a Google Docs-like collaborative document editing service?

**Answer:**  
Requirements include real-time multi-user editing, conflict resolution (OT/CRDT), and persistent storage.

**Design Components:**  
- Operational Transformation (OT) or CRDT engine  
- Real-time sync via WebSockets  
- Document storage and versioning

**Real-World Scenario:**  
Multiple users editing the same document simultaneously with minimal latency.

**Pseudocode Example:**
```python
def apply_edit(doc_id, user_id, edit_op):
    transformed_op = transform_against_concurrent_ops(edit_op)
    save_to_db(doc_id, transformed_op)
    broadcast_to_clients(doc_id, transformed_op)
```

**Follow-up Questions:**  
- How do you handle conflicts?  
  *Use OT or CRDT to merge concurrent edits.*
- How do you persist and recover document state?  
  *Regularly snapshot document state and log all operations.*

---

### 8. Payment Gateway
**Interview Question:**  
How would you design a secure and scalable payment gateway?

**Answer:**  
Handles payment processing, fraud detection, and integration with banks/card networks. Security and reliability are paramount.

**Design Components:**  
- API layer for payment requests  
- Payment processor integration  
- Fraud detection module  
- Transaction logging and audit

**Real-World Scenario:**  
Processing thousands of transactions per second during an online sale event.

**Pseudocode Example:**
```python
def process_payment(order_id, payment_info):
    if not validate_payment_info(payment_info):
        return "Invalid payment"
    if is_fraudulent(payment_info):
        return "Rejected"
    result = charge_card(payment_info)
    log_transaction(order_id, result)
    return result
```

**Follow-up Questions:**  
- How do you ensure PCI compliance?  
  *Encrypt sensitive data, segregate environments, regular audits.*
- How do you handle retries and idempotency?  
  *Use unique transaction IDs and store transaction state.*

---

### 9. Scalable Distributed Storage System
**Interview Question:**  
How would you design a scalable distributed storage system like Amazon S3?

**Answer:**  
Store and retrieve large volumes of unstructured data reliably and efficiently across many nodes.

**Design Components:**  
- Object storage nodes  
- Metadata service  
- Consistent hashing for data placement  
- Replication and erasure coding for durability

**Real-World Scenario:**  
Storing petabytes of user-uploaded files with high availability guarantees.

**Pseudocode Example:**
```python
def store_object(object_id, data):
    nodes = select_storage_nodes(object_id)
    for node in nodes:
        node.save(object_id, data)
    update_metadata(object_id, nodes)
```

**Follow-up Questions:**  
- How do you ensure data durability?  
  *Replicate data across multiple nodes/data centers.*
- How do you handle node failures?  
  *Detect failures and re-replicate data as needed.*

---

## 🏗 Advanced System Design Additions (Interview-Style Q&A)

### 10. Global Notification System (Fan-out, Filtering)
**Interview Question:**  
How would you design a global notification system supporting fan-out and user-specific filtering?

**Answer:**  
Deliver notifications to millions of users instantly, possibly with filtering based on user preferences.

**Design Components:**  
- Producer API  
- Message queue/broker  
- Fan-out workers  
- User preference filtering

**Real-World Scenario:**  
Sending breaking news notifications to all users, but only sports fans receive sports alerts.

**Pseudocode Example:**
```python
def send_notification(event):
    recipients = filter_users(event)
    for user in recipients:
        queue_notification(user, event)
```

**Follow-up Questions:**  
- How do you handle delivery guarantees?  
  *Use persistent queues, retry logic, and acknowledgments.*
- How do you scale fan-out?  
  *Partition users and distribute workload across workers.*

---

### 11. Scalable Pub/Sub Messaging System
**Interview Question:**  
How would you build a scalable publish/subscribe (pub/sub) messaging system?

**Answer:**  
Pub/sub enables decoupled communication between producers and consumers. Key features: topic management, message delivery, scaling.

**Design Components:**  
- Broker (e.g., Kafka, RabbitMQ)  
- Topic/queue management  
- Consumer groups  
- Message persistence

**Real-World Scenario:**  
Microservices architecture where services publish events and others subscribe to them.

**Pseudocode Example:**
```python
def publish(topic, message):
    broker.append_to_topic(topic, message)

def subscribe(topic, consumer):
    messages = broker.get_messages(topic, consumer.offset)
    process(messages)
```

**Follow-up Questions:**  
- How do you ensure message ordering?  
  *Partition topics and assign partitions to consumers.*
- How do you handle consumer failures?  
  *Track offsets and allow replay from last committed offset.*

---

### 12. Search Engine (Crawler, Indexer, Ranker)
**Interview Question:**  
How would you design a large-scale search engine?

**Answer:**  
Involves crawling web pages, building an index, and ranking results for user queries.

**Design Components:**  
- Web crawler  
- Indexer (inverted index)  
- Query processor and ranker

**Real-World Scenario:**  
Hundreds of millions of users searching the web for relevant pages.

**Pseudocode Example:**
```python
def crawl(url):
    content = fetch(url)
    links = extract_links(content)
    for link in links:
        enqueue(link)
    index(content)
```

**Follow-up Questions:**  
- How do you keep the index up-to-date?  
  *Regularly recrawl and reindex popular/updated sites.*
- How do you rank results?  
  *Use algorithms like PageRank, and relevance scoring.*

---

### 13. Real-Time Analytics Dashboard
**Interview Question:**  
How would you design a real-time analytics dashboard?

**Answer:**  
Ingest, process, and visualize data streams in real-time.

**Design Components:**  
- Data ingestion pipeline (Kafka, Flink)  
- Stream processing  
- Aggregation and storage  
- Dashboard visualization

**Real-World Scenario:**  
Monitoring live user activity on an e-commerce site.

**Pseudocode Example:**
```python
def process_event(event):
    update_aggregates(event)
    push_to_dashboard(event)
```

**Follow-up Questions:**  
- How do you handle late or out-of-order events?  
  *Use windowing and watermarking techniques.*
- How do you scale processing?  
  *Partition streams and process in parallel.*

---

### 14. Scalable IoT Data Ingestion Platform
**Interview Question:**  
How would you design a scalable platform for ingesting IoT device data?

**Answer:**  
Support high-frequency, bursty data from millions of devices, ensuring reliability and scalability.

**Design Components:**  
- Device gateway/API  
- Message broker  
- Stream processing  
- Time-series storage

**Real-World Scenario:**  
Smart meters sending telemetry every second from millions of homes.

**Pseudocode Example:**
```python
def ingest_data(device_id, payload):
    validate(payload)
    broker.publish(device_id, payload)
    process_and_store(payload)
```

**Follow-up Questions:**  
- How do you secure device communication?  
  *Use authentication, TLS, and device certificates.*
- How do you handle unreliable networks?  
  *Buffer data on device, retry transmission.*

---

### 15. Distributed Locking Service (e.g., Redis-based)
**Interview Question:**  
How would you implement a distributed locking service?

**Answer:**  
Distributed locks prevent concurrent modification of shared resources in distributed systems.

**Design Components:**  
- Centralized store (e.g., Redis)  
- Lock acquisition/release API  
- TTL for lock expiry

**Real-World Scenario:**  
Multiple workers processing the same job queue, only one should handle a job at a time.

**Pseudocode Example:**
```python
def acquire_lock(resource_id):
    return redis.setnx(resource_id, "locked", ex=30)

def release_lock(resource_id):
    redis.delete(resource_id)
```

**Follow-up Questions:**  
- How do you prevent deadlocks?  
  *Set expiration (TTL) on locks.*
- How do you ensure lock ownership?  
  *Store unique token with lock and verify on release.*

---

### 16. Feature Flag Management System
**Interview Question:**  
How would you design a feature flag management system?

**Answer:**  
Feature flags enable toggling features on/off without deployment, supporting gradual rollouts and A/B testing.

**Design Components:**  
- Flag storage and API  
- SDKs for clients  
- Audit and change history

**Real-World Scenario:**  
Rolling out a new feature to 10% of users for testing.

**Pseudocode Example:**
```python
def is_feature_enabled(user_id, feature):
    flag = get_flag(feature)
    return flag.enabled_for(user_id)
```

**Follow-up Questions:**  
- How do you minimize flag evaluation latency?  
  *Cache flags at the edge or in client SDKs.*
- How do you audit flag changes?  
  *Log all changes and provide a dashboard for review.*

---

### 17. Online Multiplayer Game Backend
**Interview Question:**  
How would you design a backend for a real-time online multiplayer game?

**Answer:**  
Requirements: low-latency state sync, cheat prevention, matchmaking, persistence.

**Design Components:**  
- Game state servers  
- Matchmaking service  
- Real-time communication (UDP, WebSockets)  
- Persistent storage for progress

**Real-World Scenario:**  
Hundreds of players in a battle royale game, all interacting in real-time.

**Pseudocode Example:**
```python
def update_player_state(player_id, state):
    broadcast_to_players(player_id, state)
    save_state(player_id, state)
```

**Follow-up Questions:**  
- How do you prevent cheating?  
  *Validate actions server-side, use authoritative game logic.*
- How do you scale game servers?  
  *Spin up new instances per match, use auto-scaling.*

---

### 18. Social Media Recommendation Engine
**Interview Question:**  
How would you build a recommendation engine for social media feeds?

**Answer:**  
Recommends relevant content based on user activity, interests, and social graph.

**Design Components:**  
- User/content feature extraction  
- Ranking algorithms  
- Real-time and batch pipelines

**Real-World Scenario:**  
Showing users personalized posts and ads in their feed.

**Pseudocode Example:**
```python
def recommend(user_id):
    features = extract_user_features(user_id)
    candidates = get_candidate_posts(features)
    ranked = rank_candidates(candidates, features)
    return ranked[:N]
```

**Follow-up Questions:**  
- How do you handle cold start?  
  *Use popularity, trending, or random content for new users.*
- How do you update recommendations in real-time?  
  *Stream user activity and re-rank feeds on the fly.*

---

### 19. Large-Scale Machine Learning Model Serving System
**Interview Question:**  
How would you design a system for serving large-scale ML models?

**Answer:**  
Serve ML models with low latency and high throughput, supporting versioning and scaling.

**Design Components:**  
- Model registry  
- Model servers (TensorFlow Serving, TorchServe)  
- Load balancing  
- Monitoring and logging

**Real-World Scenario:**  
Serving recommendations or vision models to millions of users.

**Pseudocode Example:**
```python
def predict(model_id, input_data):
    model = load_model(model_id)
    return model.infer(input_data)
```

**Follow-up Questions:**  
- How do you handle model updates?  
  *Blue/green deployments, canary releases.*
- How do you ensure low latency?  
  *Use GPU acceleration, batch requests, and cache inputs/outputs.*

---

### 20. Distributed Scheduler (e.g., for Cron Jobs)
**Interview Question:**  
How would you design a distributed scheduler for running jobs at specific times?

**Answer:**  
Ensures jobs are executed reliably across many servers, avoiding duplication.

**Design Components:**  
- Job registry  
- Distributed lock (to avoid double execution)  
- Worker nodes  
- Failure detection and retry

**Real-World Scenario:**  
Running nightly data backups or periodic report generation.

**Pseudocode Example:**
```python
def schedule_job(job):
    if acquire_lock(job.id):
        run_job(job)
        release_lock(job.id)
```

**Follow-up Questions:**  
- How do you handle missed jobs?  
  *Track job state and retry failed/missed executions.*
- How do you scale job execution?  
  *Distribute jobs across worker pools and shards.*

---

### 21. Time-Series Database
**Interview Question:**  
How would you build a scalable time-series database?

**Answer:**  
Optimized for storing and querying time-stamped data (metrics, logs).

**Design Components:**  
- Write-optimized storage (LSM trees)  
- Compression and retention policies  
- Fast range queries

**Real-World Scenario:**  
Storing and querying IoT sensor data or application metrics.

**Pseudocode Example:**
```python
def insert_metric(timestamp, value):
    shard = get_shard(timestamp)
    shard.append(timestamp, value)
```

**Follow-up Questions:**  
- How do you handle high write throughput?  
  *Batch writes, use append-only storage.*
- How do you manage data retention?  
  *Periodic deletion or downsampling of old data.*

---

### 22. Geospatial Search Service ("Find Nearby")
**Interview Question:**  
How would you design a geospatial search service to find nearby entities?

**Answer:**  
Efficiently query for entities within a geographic radius.

**Design Components:**  
- Spatial index (R-tree, Geohash)  
- Proximity query API  
- Real-time updates for moving entities

**Real-World Scenario:**  
Finding nearby drivers or restaurants in a city.

**Pseudocode Example:**
```python
def find_nearby(lat, lon, radius):
    geohash = encode_geohash(lat, lon)
    candidates = index.query(geohash, radius)
    return filter_by_exact_distance(candidates, lat, lon, radius)
```

**Follow-up Questions:**  
- How do you update moving entities?  
  *Update spatial index in real-time as locations change.*
- How do you scale queries?  
  *Partition index and use distributed search.*

---

### 23. Multi-Tenant SaaS Platform
**Interview Question:**  
How would you design a multi-tenant SaaS platform?

**Answer:**  
Support multiple customers (tenants) securely and efficiently, possibly with data isolation.

**Design Components:**  
- Tenant-aware authentication  
- Data partitioning (shared, isolated, hybrid)  
- Usage metering and billing

**Real-World Scenario:**  
Hosting CRM software for thousands of companies on the same infrastructure.

**Pseudocode Example:**
```python
def get_data(tenant_id, resource_id):
    db = get_tenant_db(tenant_id)
    return db.query(resource_id)
```

**Follow-up Questions:**  
- How do you isolate tenant data?  
  *Separate schemas, databases, or row-level security.*
- How do you handle per-tenant scaling?  
  *Auto-scale resources based on tenant usage.*

---

### 24. Distributed Transaction Coordinator (2PC/3PC)
**Interview Question:**  
How would you implement a distributed transaction coordinator using 2PC or 3PC?

**Answer:**  
Ensures atomicity across distributed systems using protocols like Two-Phase Commit (2PC) or Three-Phase Commit (3PC).

**Design Components:**  
- Coordinator and participants  
- Prepare/commit/abort phases  
- Logging for recovery

**Real-World Scenario:**  
Bank transfer between accounts in different databases.

**Pseudocode Example:**
```python
def two_phase_commit(txn):
    for participant in txn.participants:
        if not participant.prepare(txn):
            abort_all()
            return False
    for participant in txn.participants:
        participant.commit(txn)
    return True
```

**Follow-up Questions:**  
- What are the drawbacks of 2PC?  
  *Blocking, coordinator single point of failure.*
- How does 3PC improve on 2PC?  
  *Adds a pre-commit phase to reduce blocking.*

---

### 25. Real-Time Collaborative Whiteboarding System
**Interview Question:**  
How would you design a real-time collaborative whiteboarding system?

**Answer:**  
Support drawing, editing, and multi-user collaboration with low latency.

**Design Components:**  
- Real-time sync (WebSockets)  
- Conflict resolution (OT/CRDT)  
- Persistent storage

**Real-World Scenario:**  
Multiple users brainstorming on a shared whiteboard during a remote meeting.

**Pseudocode Example:**
```python
def apply_draw_action(board_id, user_id, action):
    transformed_action = resolve_conflicts(action)
    save_action(board_id, transformed_action)
    broadcast_action(board_id, transformed_action)
```

**Follow-up Questions:**  
- How do you handle network latency?  
  *Use local prediction and eventual consistency.*
- How do you persist board state?  
  *Snapshot board and log actions for replay.*

---

### 26. Scalable Video Conferencing Backend
**Interview Question:**  
How would you design a scalable backend for video conferencing?

**Answer:**  
Enable real-time audio/video communication for many users with low latency.

**Design Components:**  
- Media servers (SFU/MCU)  
- Signaling server  
- TURN/STUN servers for NAT traversal  
- Recording and archiving

**Real-World Scenario:**  
Hundreds of users in a virtual conference, with screen sharing and chat.

**Pseudocode Example:**
```python
def join_conference(user_id, conference_id):
    signaling_server.connect(user_id, conference_id)
    media_server.add_participant(user_id, conference_id)
```

**Follow-up Questions:**  
- How do you minimize latency?  
  *Use regional media servers and optimize network paths.*
- How do you scale media servers?  
  *Auto-scale based on active conferences and participants.*

---

### 27. Event-Driven Architecture for Microservices
**Interview Question:**  
How would you design an event-driven architecture for microservices?

**Answer:**  
Microservices communicate via events, enabling loose coupling and scalability.

**Design Components:**  
- Event bus/broker  
- Event producers/consumers  
- Event schema/versioning

**Real-World Scenario:**  
Order service emits "OrderPlaced" event, inventory and billing services react to it.

**Pseudocode Example:**
```python
def on_order_placed(event):
    inventory_service.handle(event)
    billing_service.handle(event)
```

**Follow-up Questions:**  
- How do you ensure event delivery?  
  *Use durable queues and acknowledgments.*
- How do you handle event versioning?  
  *Schema evolution and backward compatibility.*

---

### 28. Global E-commerce Checkout System (Inventory, Payments, Fraud)
**Interview Question:**  
How would you design a global e-commerce checkout system?

**Answer:**  
Handles inventory management, payments, and fraud detection across regions.

**Design Components:**  
- Distributed inventory service  
- Payment gateway integration  
- Fraud detection module  
- Order management

**Real-World Scenario:**  
Black Friday sale with users from multiple continents checking out simultaneously.

**Pseudocode Example:**
```python
def checkout(order):
    if not reserve_inventory(order.items):
        return "Out of stock"
    if not process_payment(order.payment_info):
        release_inventory(order.items)
        return "Payment failed"
    if is_fraudulent(order):
        release_inventory(order.items)
        refund_payment(order)
        return "Fraud detected"
    finalize_order(order)
    return "Success"
```

**Follow-up Questions:**  
- How do you handle inventory consistency?  
  *Use distributed locks or transactional reservations.*
- How do you localize payments?  
  *Integrate with local payment providers and currencies.*

---

### 29. Distributed Tracing System (Spans, Correlation, Storage)
**Interview Question:**  
How would you build a distributed tracing system for microservices?

**Answer:**  
Tracks requests across distributed services for debugging and performance analysis.

**Design Components:**  
- Trace/span collection SDK  
- Correlation ID propagation  
- Storage and query engine  
- Visualization dashboard

**Real-World Scenario:**  
Tracing a user request through dozens of microservices to identify bottlenecks.

**Pseudocode Example:**
```python
def handle_request(request):
    trace_id = get_or_create_trace_id(request)
    span_id = create_span(trace_id)
    log_span(span_id, request)
    process_request(request)
    close_span(span_id)
```

**Follow-up Questions:**  
- How do you minimize tracing overhead?  
  *Sample requests, batch and compress trace data.*
- How do you correlate traces across services?  
  *Propagate trace IDs in HTTP headers or metadata.*

---

### 30. Metadata Service (e.g., for Data Lakes)
**Interview Question:**  
How would you design a metadata service for a data lake?

**Answer:**  
Manages metadata (schemas, partitions, lineage) for large-scale data storage.

**Design Components:**  
- Metadata catalog  
- API/query interface  
- Schema/version management  
- Access control

**Real-World Scenario:**  
Data scientists searching for datasets and understanding their structure in a large organization.

**Pseudocode Example:**
```python
def register_dataset(name, schema, location):
    catalog.add_entry(name, schema, location)

def get_metadata(name):
    return catalog.lookup(name)
```

**Follow-up Questions:**  
- How do you ensure metadata consistency?  
  *Use ACID transactions and versioning.*
- How do you support search and discovery?  
  *Index metadata and provide flexible query APIs.*

---

### 31. Real-Time Analytics Pipeline
**Interview Question:**  
Design a real-time analytics pipeline processing millions of events per second.

**Initial Discussion:**
- What type of events need processing?
- What's the expected throughput and latency?
- What kind of analytics are required?

**Architecture Components:**
```plaintext
Producers -> Kafka -> Flink/Spark Streaming -> ClickHouse/Druid -> Dashboard
```

**Code Example - Event Processing:**
```java
@Service
public class AnalyticsPipeline {
    private final KafkaTemplate<String, Event> kafka;
    
    public void processEvent(Event event) {
        // Enrich event
        event.setTimestamp(Instant.now());
        event.setVersion("1.0");
        
        // Route to appropriate topic
        String topic = determineRoutingTopic(event);
        kafka.send(topic, event.getId(), event);
    }
    
    // Flink processing
    @StreamListener
    public void processEventStream(DataStream<Event> events) {
        events.keyBy(Event::getUserId)
              .window(TumblingEventTimeWindows.of(Time.minutes(5)))
              .aggregate(new EventAggregator())
              .addSink(new ClickHouseSink());
    }
}
```

**Follow-up Questions:**
1. How would you handle late-arriving data?
   ```java
   WindowAssigner<Event> assigner = EventTimeSessionWindows
       .withGap(Time.minutes(10))
       .withAllowedLateness(Time.minutes(5));
   ```

2. How to scale the processing pipeline?
   ```java
   @Configuration
   public class KafkaConfig {
       @Bean
       public KafkaConsumer<String, Event> consumer() {
           Properties props = new Properties();
           props.put("partition.assignment.strategy", 
                     "org.apache.kafka.clients.consumer.RoundRobinAssignor");
           props.put("auto.offset.reset", "latest");
           return new KafkaConsumer<>(props);
       }
   }
   ```

---

### 32. Distributed Task Scheduler
**Interview Question:**  
Design a distributed task scheduler supporting millions of recurring tasks.

**Key Requirements:**
- Task scheduling with cron expressions
- High availability
- No duplicate executions
- Task dependencies

**Architecture Example:**
```java
@Service
public class DistributedScheduler {
    private final RedissonClient redisson;
    private final TaskRepository taskRepo;
    
    public void scheduleTask(Task task) {
        String lockKey = "task-lock:" + task.getId();
        RLock lock = redisson.getLock(lockKey);
        
        try {
            if (lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                executeTask(task);
            }
        } finally {
            lock.unlock();
        }
    }
    
    @Scheduled(fixedDelay = 1000)
    public void pollTasks() {
        List<Task> dueTasks = taskRepo.findDueTasks(Instant.now());
        dueTasks.forEach(this::scheduleTask);
    }
}
```

**Real-World Scenario:**
```java
@Configuration
public class TaskExecutionConfig {
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setRejectedExecutionHandler(new CallerRunsPolicy());
        return executor;
    }
}
```

---

### 33. Distributed Configuration Service
**Interview Question:**  
Design a distributed configuration service like etcd or ZooKeeper.

**System Components:**
- Configuration storage
- Watch mechanisms
- Consensus protocol
- Client SDK

**Implementation Example:**
```java
public class ConfigService {
    private final Map<String, String> config = new ConcurrentHashMap<>();
    private final Map<String, List<ConfigWatcher>> watchers = new ConcurrentHashMap<>();
    
    public void set(String key, String value) {
        config.put(key, value);
        notifyWatchers(key, value);
    }
    
    public Optional<String> get(String key) {
        return Optional.ofNullable(config.get(key));
    }
    
    public void watch(String key, ConfigWatcher watcher) {
        watchers.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>())
                .add(watcher);
    }
    
    private void notifyWatchers(String key, String value) {
        watchers.getOrDefault(key, Collections.emptyList())
                .forEach(w -> w.onChange(key, value));
    }
}
```

**Client Usage:**
```java
@Configuration
public class ServiceConfig {
    private final ConfigService configService;
    
    @PostConstruct
    public void init() {
        configService.watch("feature.flags", (key, value) -> {
            updateFeatureFlags(value);
        });
    }
}
```

---

## ✅ Reference links:
- [System Design Primer](https://github.com/donnemartin/system-design-primer)
- [How to Answer a System Design Interview Problem](https://blog.algomaster.io/p/how-to-answer-a-system-design-interview-problem)
- [System Design Interview Questions](https://www.educative.io/blog/system-design-interview-questions)