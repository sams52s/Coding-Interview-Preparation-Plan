# DBA Cheat Sheet

## Table of Contents
- [Linux](#linux)
- [MySQL](#mysql)
- [PostgreSQL](#postgresql)
- [MongoDB](#mongodb)
- [Cassandra](#cassandra)
- [Kafka](#kafka)
- [Elasticsearch](#elasticsearch)

---

## Linux
### Installation & Configuration
```bash
# Update system packages and install a package
sudo apt update && sudo apt upgrade -y
sudo apt install <package> -y

# Set system hostname
sudo hostnamectl set-hostname myserver

# Edit network configuration file
sudo nano /etc/network/interfaces
```

### Operations (DDL/DML)
```bash
# Create a new user
sudo adduser myuser

# Modify file permissions and ownership
chmod 755 myfile
chown user:group myfile
```

### File Permission Cheat Sheet
| Symbolic | Numeric | Meaning                | Example Command         | Example Description           |
|----------|---------|------------------------|------------------------|------------------------------|
| rwxr-xr-x| 755     | Owner: rwx, Group: r-x, Others: r-x | chmod 755 file.txt         | Owner full, others read/exec |
| rw-r--r--| 644     | Owner: rw-, Group: r--, Others: r-- | chmod 644 file.txt         | Owner can edit, others read  |
| rw-------| 600     | Owner: rw-, Group: ---, Others: --- | chmod 600 file.txt         | Owner only read/write        |
| rwxrwxrwx| 777     | Owner/Group/Others: rwx | chmod 777 file.txt         | Everyone full access         |
| rwsr-xr-x| 4755    | Setuid bit (user)      | chmod 4755 file.txt        | Run as file owner            |
| rwxr-sr-x| 2755    | Setgid bit (group)     | chmod 2755 dir/             | Run as group, group dirs     |
| rwxrwxrwt| 1777    | Sticky bit (world-writable dir) | chmod 1777 /tmp           | Only owner can delete files  |

**Symbolic Representation:**
* `u` = user (owner), `g` = group, `o` = others, `a` = all
* Example: `chmod u+x file.txt` (add execute for user)
* Example: `chmod go-w file.txt` (remove write from group/others)

### Troubleshooting
```bash
# Display running processes
top

# Show disk usage in human-readable format
df -h

# Display recent kernel messages
dmesg | tail

# Check service status
systemctl status <service>

# Monitor system log file in real-time
tail -f /var/log/syslog

# Check hostname information
hostnamectl status
```

### Backup & Recovery
```bash
# Create a compressed archive backup
tar czvf backup.tar.gz /path/to/dir

# Efficient file synchronization backup
rsync -av /source /backup/

# Extract files from a compressed archive
tar xzvf backup.tar.gz
```

### Security
```bash
# Enable firewall and allow SSH port
ufw enable
ufw allow 22/tcp

# Edit sudoers file safely
sudo visudo

# Change user password
passwd

# Check SSH service status
systemctl status ssh
```

### Logging and Monitoring Enhancements
```bash
# View system logs
journalctl -xe

# Enable verbose logging for systemd services
sudo mkdir -p /etc/systemd/system/<service>.service.d/
echo -e "[Service]\nEnvironment=SYSTEMD_LOG_LEVEL=debug" | sudo tee /etc/systemd/system/<service>.service.d/override.conf
sudo systemctl daemon-reload
sudo systemctl restart <service>

# Monitor system resource usage with htop (install if needed)
sudo apt install htop -y
htop

# Check disk I/O statistics
iostat -xz 1

# Monitor network traffic
iftop
```

---

## MySQL
### Installation & Configuration
```bash
# Install MySQL server
sudo apt install mysql-server -y

# Start MySQL service
sudo systemctl start mysql

# Secure MySQL installation
sudo mysql_secure_installation

# Access MySQL shell as root user
mysql -u root -p
```

### Operations (DDL/DML)
```sql
-- Create a new database and use it
CREATE DATABASE dbname;
USE dbname;

-- Create a table
CREATE TABLE t1 (id INT PRIMARY KEY, name VARCHAR(50));

-- Insert data
INSERT INTO t1 VALUES (1, 'Alice');

-- Query data
SELECT * FROM t1;

-- Update data
UPDATE t1 SET name='Bob' WHERE id=1;

-- Delete data
DELETE FROM t1 WHERE id=1;

-- Show tables and describe table structure
SHOW TABLES;
DESCRIBE t1;
```

### Indexing Commands
```sql
-- Create an index on the 'name' column
CREATE INDEX idx_name ON t1(name);

-- Drop an index
DROP INDEX idx_name ON t1;
```

| Index Type      | Description                                 | Use Case Example                         |
|-----------------|---------------------------------------------|------------------------------------------|
| PRIMARY         | Unique, not null, only one per table        | Table's main identifier (id)             |
| UNIQUE          | Enforces unique values in column(s)         | Email, username                          |
| INDEX (Normal)  | Non-unique, speeds up lookups               | Search by non-unique fields (name)       |
| FULLTEXT        | For text search (MyISAM/InnoDB)             | Article/content search                   |
| SPATIAL         | For spatial data (geometry, GIS)            | Geo-location queries                     |
| COMPOSITE       | Index on multiple columns                   | WHERE col1 = ? AND col2 = ?              |

### Troubleshooting
```bash
# Check MySQL service status
sudo systemctl status mysql

# Tail MySQL error log
tail -f /var/log/mysql/error.log
```
```sql
-- Show running processes
SHOW PROCESSLIST;

-- Check max connections variable
SHOW VARIABLES LIKE 'max_connections';
```
```bash
# Check MySQL server status
mysqladmin status
```

### Backup & Recovery
```bash
# Backup database to SQL file
mysqldump -u root -p dbname > backup.sql

# Restore database from SQL file
mysql -u root -p dbname < backup.sql

# Backup MySQL data directory
rsync -av /var/lib/mysql /backup/mysql/
```

### Security
```sql
-- Create user with password and grant privileges
CREATE USER 'user'@'%' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON dbname.* TO 'user'@'%';
FLUSH PRIVILEGES;
```

### Handling Large Datasets
```sql
-- Use partitioning to manage large tables
ALTER TABLE t1 PARTITION BY RANGE (id) (
    PARTITION p0 VALUES LESS THAN (1000),
    PARTITION p1 VALUES LESS THAN (2000),
    PARTITION pmax VALUES LESS THAN MAXVALUE
);

-- Use EXPLAIN to analyze query performance
EXPLAIN SELECT * FROM t1 WHERE name='Alice';

-- Optimize queries by indexing frequently filtered columns
CREATE INDEX idx_name ON t1(name);

-- Use LOAD DATA INFILE for bulk data import
LOAD DATA INFILE '/path/to/file.csv' INTO TABLE t1
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;
```

### MySQL Performance Monitoring
```sql
-- Show global status
SHOW GLOBAL STATUS;

-- Check buffer pool stats
SHOW GLOBAL STATUS LIKE 'Innodb_buffer_pool%';

-- Show slow queries
SHOW GLOBAL VARIABLES LIKE '%slow%';
SHOW GLOBAL STATUS LIKE '%slow%';

-- Check table statistics
ANALYZE TABLE tablename;
SHOW TABLE STATUS;

-- Monitor replication lag
SHOW SLAVE STATUS\G
```

### MySQL Replication
```sql
-- Configure master
SHOW MASTER STATUS;
GRANT REPLICATION SLAVE ON *.* TO 'replica_user'@'%' IDENTIFIED BY 'password';

-- Configure slave
CHANGE MASTER TO
  MASTER_HOST='master_host',
  MASTER_USER='replica_user',
  MASTER_PASSWORD='password',
  MASTER_LOG_FILE='mysql-bin.000001',
  MASTER_LOG_POS=123;
START SLAVE;
```

---

## PostgreSQL
### Installation & Configuration
```bash
# Install PostgreSQL and contrib packages
sudo apt install postgresql postgresql-contrib -y

# Start PostgreSQL service
sudo systemctl start postgresql

# Access PostgreSQL shell as postgres user
sudo -u postgres psql
```

### Operations (DDL/DML)
```sql
-- Create database and connect to it
CREATE DATABASE dbname;
\c dbname

-- Create a table with serial primary key
CREATE TABLE t1 (id SERIAL PRIMARY KEY, name TEXT);

-- Insert data
INSERT INTO t1 (name) VALUES ('Alice');

-- Query data
SELECT * FROM t1;

-- Update data
UPDATE t1 SET name='Bob' WHERE id=1;

-- Delete data
DELETE FROM t1 WHERE id=1;

-- List databases and tables
\l
\dt

-- Describe table structure
\d t1
```

### Indexing Commands
```sql
-- Create an index on the 'name' column
CREATE INDEX idx_name ON t1(name);

-- Create a unique index
CREATE UNIQUE INDEX idx_unique_name ON t1(name);

-- Drop an index
DROP INDEX idx_name;
```

| Index Type      | Description                                         | Use Case Example                         |
|-----------------|-----------------------------------------------------|------------------------------------------|
| btree           | Default, balanced tree for general queries          | Index on id, name                        |
| hash            | Fast equality comparisons                           | Index on email for fast lookups          |
| unique          | Ensures unique values in column(s)                  | Unique username/email                    |
| gin             | Generalized Inverted Index, for arrays/jsonb/search | Full-text search, array fields           |
| gist            | Generalized Search Tree, for geometric data         | Range, geometric, full-text              |
| spgist          | Space-partitioned GiST, for spatial/partitioned     | IP ranges, geometric data                |
| brin            | Block Range Index, for very large tables            | Time-series, append-only tables          |
| composite       | Index on multiple columns                           | WHERE col1 = ? AND col2 = ?              |

### Troubleshooting
```bash
# Check PostgreSQL service status
sudo systemctl status postgresql

# Tail PostgreSQL log files
tail -f /var/log/postgresql/postgresql-*.log
```
```sql
-- View active queries and connections
SELECT * FROM pg_stat_activity;
```

### Backup & Recovery
```bash
# Backup database to SQL file
pg_dump dbname > backup.sql

# Restore database from SQL file
psql dbname < backup.sql
```

### Security
```sql
-- Create user with password and grant privileges
CREATE USER myuser WITH PASSWORD 'pass';
GRANT ALL PRIVILEGES ON DATABASE dbname TO myuser;
```

### Handling Large Datasets
```sql
-- Use table partitioning for large tables
CREATE TABLE t1_2023 PARTITION OF t1 FOR VALUES FROM ('2023-01-01') TO ('2024-01-01');

-- Use EXPLAIN ANALYZE to optimize queries
EXPLAIN ANALYZE SELECT * FROM t1 WHERE name='Alice';

-- Use COPY command for bulk data import/export
COPY t1 FROM '/path/to/file.csv' DELIMITER ',' CSV HEADER;

-- Use parallel query execution (PostgreSQL 9.6+)
SET max_parallel_workers_per_gather = 4;
```

### Logging and Monitoring Enhancements
```bash
# PostgreSQL log file location
cat /etc/postgresql/*/main/postgresql.conf | grep log_directory

# Enable verbose logging by setting in postgresql.conf
# log_min_duration_statement = 0
# log_statement = 'all'

# Reload PostgreSQL to apply changes
sudo systemctl reload postgresql

# Monitor active connections and locks
SELECT * FROM pg_stat_activity;
SELECT * FROM pg_locks;

# Use pg_stat_statements extension for query statistics
CREATE EXTENSION pg_stat_statements;
SELECT * FROM pg_stat_statements ORDER BY total_time DESC LIMIT 10;
```

### PostgreSQL Advanced Operations
```sql
-- Vacuum and analyze
VACUUM (VERBOSE, ANALYZE) tablename;

-- Table bloat check
SELECT 
  schemaname, tablename, 
  pg_size_pretty(pg_total_relation_size('"'||schemaname||'"."'||tablename||'"')) as total_size
FROM pg_tables
ORDER BY pg_total_relation_size('"'||schemaname||'"."'||tablename||'"') DESC;

-- Connection pooling with pgBouncer
-- /etc/pgbouncer/pgbouncer.ini
[databases]
* = host=localhost port=5432

[pgbouncer]
pool_mode = transaction
max_client_conn = 1000
```

---

## MongoDB
### Installation & Configuration
```bash
# Install MongoDB package
sudo apt install -y mongodb

# Start MongoDB service
sudo systemctl start mongodb

# Access MongoDB shell
mongo
```

### Operations (DDL/DML)
```js
// Switch to database
use dbname

// Create a collection
db.createCollection('t1')

// Insert a document
db.t1.insert({name: 'Alice'})

// Query documents
db.t1.find()

// Update documents
db.t1.update({name: 'Alice'}, {$set: {name: 'Bob'}})

// Remove documents
db.t1.remove({name: 'Bob'})

// List collections
db.getCollectionNames()

// Pretty print query results
db.t1.find().pretty()
```

### Indexing Commands
```js
// Create an index on 'name' field
db.t1.createIndex({name: 1})

// Create a compound index
db.t1.createIndex({name: 1, age: -1})

// Drop an index by name
db.t1.dropIndex('name_1')
```

| Index Type         | Fields / Sorting Example        | Notes / Use Case                      |
|--------------------|-------------------------------|---------------------------------------|
| Single Field       | `{name: 1}`                   | Simple lookups                        |
| Compound           | `{name: 1, age: -1}`          | Multi-field queries, mixed sort        |
| Unique             | `{email: 1}, {unique: true}`  | Prevent duplicate values               |
| Text               | `{desc: "text"}`              | Full-text search                       |
| Hashed             | `{_id: "hashed"}`             | Sharding, even data distribution       |
| Sparse             | `{field: 1}, {sparse: true}`  | Only index documents with field        |
| TTL                | `{createdAt: 1}, {expireAfterSeconds: 3600}` | Auto-delete docs after time           |
| Wildcard           | `{"$**": 1}`                  | Index all fields (MongoDB 4.2+)        |

### Troubleshooting
```bash
# Check MongoDB service status
sudo systemctl status mongodb

# Tail MongoDB log file
tail -f /var/log/mongodb/mongod.log
```
```js
// Check server status
db.serverStatus()
```

### Backup & Recovery
```bash
# Dump database to backup directory
mongodump --db dbname --out /backup/dir

# Restore database from backup directory
mongorestore --db dbname /backup/dir/dbname
```

### Security
```js
// Create user with roles
use admin
db.createUser({
  user: "user",
  pwd: "pass",
  roles: [
    "userAdminAnyDatabase",
    "dbAdminAnyDatabase",
    "readWriteAnyDatabase"
  ]
})
```

### Handling Large Datasets
```js
// Use sharding to distribute large datasets
sh.enableSharding("dbname");
sh.shardCollection("dbname.t1", { _id: "hashed" });

// Use bulk write operations for efficiency
var bulk = db.t1.initializeUnorderedBulkOp();
bulk.insert({name: "Alice"});
bulk.insert({name: "Bob"});
bulk.execute();

// Use aggregation pipeline for data processing
db.t1.aggregate([
  { $match: { age: { $gte: 20 } } },
  { $group: { _id: "$name", total: { $sum: 1 } } }
]);
```

### MongoDB Advanced Operations
```javascript
// Sharding operations
sh.status()
sh.addShard("replicaSet/server1:27017")

// Replication status
rs.status()
rs.printReplicationInfo()

// Performance profiling
db.setProfilingLevel(2)
db.system.profile.find().pretty()

// Collection stats
db.collection.stats()
```

---

## Cassandra
### Installation & Configuration
```bash
# Install Cassandra package
sudo apt install cassandra -y

# Start Cassandra service
sudo systemctl start cassandra

# Access Cassandra shell
cqlsh
```

### Operations (DDL/DML)
```sql
-- Create keyspace with replication settings
CREATE KEYSPACE ks1 WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

-- Use keyspace
USE ks1;

-- Create table
CREATE TABLE t1 (id int PRIMARY KEY, name text);

-- Insert data
INSERT INTO t1 (id, name) VALUES (1, 'Alice');

-- Query data
SELECT * FROM t1;

-- Update data
UPDATE t1 SET name='Bob' WHERE id=1;

-- Delete data
DELETE FROM t1 WHERE id=1;

-- List keyspaces and describe table
DESCRIBE KEYSPACES;
DESCRIBE TABLE ks1.t1;
```

### Indexing Commands
```sql
-- Create secondary index on 'name' column
CREATE INDEX idx_name ON t1(name);

-- Drop index
DROP INDEX idx_name;
```

| Index Type      | Description                                   | Use Case Example                       |
|-----------------|-----------------------------------------------|----------------------------------------|
| Primary Key     | Uniquely identifies row, required for queries | Fast lookup by partition (id, ...).    |
| Secondary Index | Allows non-primary key column search          | Lookup by non-key fields (name, email) |

**Comparison:**
| Feature             | Primary Key                | Secondary Index                   |
|---------------------|---------------------------|-----------------------------------|
| Query Performance   | Fast, partition-based     | Slower, not for high-cardinality  |
| Uniqueness          | Enforced                  | Not enforced                      |
| Use Case            | Direct row access         | Occasional alternate lookups      |
| Scalability         | Highly scalable           | Not recommended for heavy use      |

### Troubleshooting
```bash
# Check cluster status
nodetool status

# Get node info
nodetool info

# Tail Cassandra system log
tail -f /var/log/cassandra/system.log
```

### Backup & Recovery
```bash
# Take snapshot of keyspace
nodetool snapshot ks1

# Restore: copy snapshots back and run nodetool refresh
```

### Security
```sql
-- Create role with login
CREATE ROLE myuser WITH PASSWORD = 'pass' AND LOGIN = true;

-- Grant permissions to role
GRANT ALL PERMISSIONS ON KEYSPACE ks1 TO myuser;
```

### Handling Large Datasets
```bash
# Use data modeling to optimize queries for large datasets
# Avoid tombstones by using proper TTL and deletes

# Use compaction strategies for large data
nodetool compact ks1

# Monitor read/write latency
nodetool cfstats ks1.t1

# Use token-aware drivers for efficient data distribution
```

---

## Kafka
### Installation & Configuration
```bash
# Download and extract Kafka (replace <version> with actual version)
wget https://downloads.apache.org/kafka/<version>/kafka_2.13-<version>.tgz
tar -xzf kafka_2.13-<version>.tgz
cd kafka_2.13-<version>

# Start ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka broker service
bin/kafka-server-start.sh config/server.properties
```

### Operations (DDL/DML)
```bash
# Create a Kafka topic
bin/kafka-topics.sh --create --topic mytopic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Produce messages to topic
bin/kafka-console-producer.sh --topic mytopic --bootstrap-server localhost:9092

# Consume messages from topic
bin/kafka-console-consumer.sh --topic mytopic --from-beginning --bootstrap-server localhost:9092
```

### Indexing Commands
```bash
# Create topic with multiple partitions for parallelism
bin/kafka-topics.sh --create --topic mytopic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

| Concept       | Description                                     | Indexing Effect/Use Case                          |
|---------------|-------------------------------------------------|---------------------------------------------------|
| Partitioning  | Topics split into partitions                    | Enables parallelism, fast lookups by partition     |
| Message Key   | Key determines partition assignment             | Messages with same key go to same partition/order  |
| Offset        | Each message has unique offset in partition     | Allows precise message retrieval                   |
| Consumer Group| Tracks offset per group/partition               | Enables scalable, ordered consumption              |

> **Note:** Kafka does not use traditional indexes; ordering and lookup are achieved via partitioning and keys.

### Troubleshooting
```bash
# Describe topic details
bin/kafka-topics.sh --describe --topic mytopic --bootstrap-server localhost:9092

# Tail Kafka server logs
tail -f logs/server.log
```

### Backup & Recovery
```bash
# No built-in backup; use file system snapshots or third-party tools

# Use Kafka MirrorMaker for topic replication as backup
```

### Security
```properties
# Enable SSL and SASL authentication in server.properties
ssl.enable=true
sasl.enabled.mechanisms=PLAIN
```

### Handling Large Datasets
```bash
# Increase number of partitions for scalability
bin/kafka-topics.sh --alter --topic mytopic --partitions 10 --bootstrap-server localhost:9092

# Configure retention policies to manage data size
bin/kafka-configs.sh --alter --entity-type topics --entity-name mytopic --add-config retention.ms=604800000 --bootstrap-server localhost:9092

# Use compression for messages to save space
# Set producer config: compression.type=snappy|gzip|lz4

# Monitor consumer lag and throughput
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group <group_id>
```

### Logging and Monitoring Enhancements
```bash
# Enable DEBUG logging in log4j.properties
sed -i 's/log4j.rootLogger=INFO, stdout/log4j.rootLogger=DEBUG, stdout/' config/log4j.properties

# Restart Kafka to apply logging changes
bin/kafka-server-stop.sh
bin/kafka-server-start.sh config/server.properties

# Monitor Kafka with kafka-run-class tool
bin/kafka-run-class.sh kafka.tools.JmxTool --jmx-url service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi --object-name kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec

# Use Kafka Manager or other monitoring tools for cluster health
```

---

## Elasticsearch
### Installation & Configuration
```bash
# Install Elasticsearch package
sudo apt install elasticsearch -y

# Start Elasticsearch service
sudo systemctl start elasticsearch

# Test Elasticsearch endpoint
curl -X GET "localhost:9200/"
```
```bash
# Configure JVM memory settings in jvm.options
# -Xms4g
# -Xmx4g
```
```bash
# Configure cluster settings
curl -X PUT "localhost:9200/_cluster/settings" -H 'Content-Type: application/json' -d'
{
  "persistent": {
    "cluster.routing.allocation.disk.threshold_enabled": true,
    "cluster.routing.allocation.disk.watermark.low": "85%"
  }
}'
```

### Operations (DDL/DML)
```bash
# Create an index with settings
curl -X PUT "localhost:9200/myindex" -H 'Content-Type: application/json' -d'
{
  "settings": {
    "number_of_shards": 3,
    "number_of_replicas": 2
  }
}'
```
```bash
# Bulk insert documents
curl -X POST "localhost:9200/_bulk" -H 'Content-Type: application/json' -d'
{"index": {"_index": "myindex", "_id": "1"}}
{"name": "Alice", "age": 25}
{"index": {"_index": "myindex", "_id": "2"}}
{"name": "Bob", "age": 30}
'
```
```bash
# Search with filters
curl -X GET "localhost:9200/myindex/_search" -H 'Content-Type: application/json' -d'
{
  "query": {
    "bool": {
      "must": [
        { "match": { "name": "Alice" } }
      ],
      "filter": [
        { "range": { "age": { "gte": 20 } } }
      ]
    }
  }
}'
```
```bash
# Aggregations example
curl -X GET "localhost:9200/myindex/_search" -H 'Content-Type: application/json' -d'
{
  "aggs": {
    "avg_age": { "avg": { "field": "age" } }
  }
}'
```

### Indexing Commands
```bash
# Create index with custom mappings
curl -X PUT "localhost:9200/myindex" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "name": { "type": "text" },
      "age": { "type": "integer" }
    }
  }
}'
```

| Field Type | Description                                    | Use Case Example                   |
|------------|------------------------------------------------|------------------------------------|
| text       | Full-text search, analyzed                     | Article body, comments             |
| keyword    | Exact value, not analyzed                      | Tags, status, IDs                  |
| integer    | Whole numbers                                  | Age, count, quantity               |
| float      | Decimal numbers                                | Price, rating                      |
| date       | Date/time values                               | Timestamps, created_at             |
| boolean    | True/false                                     | Active flag, is_deleted            |
| geo_point  | Latitude/longitude for geo queries             | Store locations, user position     |
| nested     | Array of objects, queryable as sub-documents   | Product variants, order items      |
| object     | Embedded object, not indexed as nested         | Addresses, metadata                |

### Troubleshooting
```bash
# Check cluster health
curl -X GET "localhost:9200/_cluster/health?pretty"

# View indices status
curl -X GET "localhost:9200/_cat/indices?v"

# View nodes information
curl -X GET "localhost:9200/_cat/nodes?v"

# Check pending tasks
curl -X GET "localhost:9200/_cat/pending_tasks?v"

# Check shard allocation
curl -X GET "localhost:9200/_cat/shards?v"

# Analyze hot threads
curl -X GET "localhost:9200/_nodes/hot_threads"

# Clear cache
curl -X POST "localhost:9200/_cache/clear"

# Additional cluster stats
curl -X GET "localhost:9200/_cluster/stats?pretty"
curl -X GET "localhost:9200/_nodes/stats?pretty"
```

### Backup & Recovery
```bash
# Create snapshot repository with S3
curl -X PUT "localhost:9200/_snapshot/s3_backup" -H 'Content-Type: application/json' -d'
{
  "type": "s3",
  "settings": {
    "bucket": "my-es-snapshots",
    "region": "us-west-2"
  }
}'
```
```bash
# Create incremental snapshot and wait for completion
curl -X PUT "localhost:9200/_snapshot/my_backup/snapshot_2?wait_for_completion=true" -H 'Content-Type: application/json' -d'
{
  "indices": "myindex",
  "ignore_unavailable": true,
  "include_global_state": false
}'
```
```bash
# List all snapshots in repository
curl -X GET "localhost:9200/_snapshot/my_backup/_all"
```

### Security
```yaml
# Enhanced security configuration in elasticsearch.yml
xpack.security.enabled: true
xpack.security.transport.ssl.enabled: true
xpack.security.http.ssl.enabled: true
xpack.security.authc:
  anonymous:
    username: anonymous_user
    roles: anonymous_role
    authz_exception: false
```
```bash
# Create users
curl -X POST "localhost:9200/_security/user/myuser" -H 'Content-Type: application/json' -d'
{
  "password": "mypassword",
  "roles": ["admin"],
  "full_name": "My Admin User"
}'
```
```bash
# Configure roles
curl -X POST "localhost:9200/_security/role/custom_role" -H 'Content-Type: application/json' -d'
{
  "cluster": ["monitor"],
  "indices": [
    {
      "names": ["myindex"],
      "privileges": ["read"]
    }
  ]
}'
```

### Handling Large Datasets
```bash
# Use index lifecycle management (ILM) to manage large indices
curl -X PUT "localhost:9200/_ilm/policy/my_policy" -H 'Content-Type: application/json' -d'
{
  "policy": {
    "phases": {
      "hot": { "min_age": "0ms", "actions": { "rollover": { "max_size": "50GB" }}},
      "warm": { "min_age": "2d", "actions": { "shrink": { "number_of_shards": 1 }}},
      "cold": { "min_age": "7d", "actions": { "freeze": {}}},
      "delete": { "min_age": "30d", "actions": { "delete": {}}}
    }
  }
}'

# Apply ILM policy to index template
curl -X PUT "localhost:9200/_template/my_template" -H 'Content-Type: application/json' -d'
{
  "index_patterns": ["myindex*"],
  "settings": {
    "index.lifecycle.name": "my_policy",
    "index.lifecycle.rollover_alias": "myindex_alias"
  }
}'
```

### Logging and Monitoring Enhancements
```bash
# Elasticsearch logs location
cat /etc/elasticsearch/elasticsearch.yml | grep path.logs

# Enable detailed logging by modifying log4j2.properties
# Set root logger level to debug or trace

# Restart Elasticsearch to apply logging changes
sudo systemctl restart elasticsearch

# Monitor cluster health and metrics via APIs
curl -X GET "localhost:9200/_cluster/health?pretty"
curl -X GET "localhost:9200/_nodes/stats?pretty"

# Use Kibana or other monitoring tools for advanced visualization
```
```
