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
# Update and install packages
sudo apt update && sudo apt upgrade
sudo apt install <package>

# Set hostname
sudo hostnamectl set-hostname myserver

# Edit network config
sudo nano /etc/network/interfaces
```

### Operations (DDL/DML)
```bash
# Create user
sudo adduser myuser

# File permissions
chmod 755 myfile
chown user:group myfile
```

### Troubleshooting
```bash
top                # Show running processes
df -h              # Disk usage
dmesg | tail       # Kernel messages
systemctl status <service>
tail -f /var/log/syslog
hostnamectl status              # Check hostname
```

### Backup & Recovery
```bash
# Backup files
tar czvf backup.tar.gz /path/to/dir
rsync -av /source /backup/     # Efficient backup

# Restore files
tar xzvf backup.tar.gz
```

### Security
```bash
ufw enable
ufw allow 22/tcp
sudo visudo           # Edit sudoers
passwd                # Change password
systemctl status ssh  # Check SSH service status
```

---

## MySQL
### Installation & Configuration
```bash
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql_secure_installation
mysql -u root -p
```

### Operations (DDL/DML)
```sql
CREATE DATABASE dbname;
USE dbname;
CREATE TABLE t1 (id INT PRIMARY KEY, name VARCHAR(50));
INSERT INTO t1 VALUES (1, 'Alice');
SELECT * FROM t1;
UPDATE t1 SET name='Bob' WHERE id=1;
DELETE FROM t1 WHERE id=1;
SHOW TABLES;
DESCRIBE t1;
```

### Troubleshooting
```bash
sudo systemctl status mysql
tail -f /var/log/mysql/error.log
SHOW PROCESSLIST;
SHOW VARIABLES LIKE 'max_connections';
mysqladmin status
```

### Backup & Recovery
```bash
mysqldump -u root -p dbname > backup.sql
mysql -u root -p dbname < backup.sql
rsync -av /var/lib/mysql /backup/mysql/
```

### Security
```sql
CREATE USER 'user'@'%' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON dbname.* TO 'user'@'%';
FLUSH PRIVILEGES;
```

---

## PostgreSQL
### Installation & Configuration
```bash
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
sudo -u postgres psql
```

### Operations (DDL/DML)
```sql
CREATE DATABASE dbname;
\c dbname
CREATE TABLE t1 (id SERIAL PRIMARY KEY, name TEXT);
INSERT INTO t1 (name) VALUES ('Alice');
SELECT * FROM t1;
UPDATE t1 SET name='Bob' WHERE id=1;
DELETE FROM t1 WHERE id=1;
\l      -- List databases
\dt     -- List tables
\d t1   -- Describe table structure
```

### Troubleshooting
```bash
sudo systemctl status postgresql
tail -f /var/log/postgresql/postgresql-*.log
SELECT * FROM pg_stat_activity;
```

### Backup & Recovery
```bash
pg_dump dbname > backup.sql
psql dbname < backup.sql
```

### Security
```sql
CREATE USER myuser WITH PASSWORD 'pass';
GRANT ALL PRIVILEGES ON DATABASE dbname TO myuser;
```

---

## MongoDB
### Installation & Configuration
```bash
sudo apt install -y mongodb
sudo systemctl start mongodb
mongo
```

### Operations (DDL/DML)
```js
use dbname
db.createCollection('t1')
db.t1.insert({name: 'Alice'})
db.t1.find()
db.t1.update({name: 'Alice'}, {$set: {name: 'Bob'}})
db.t1.remove({name: 'Bob'})
db.getCollectionNames();
db.t1.find().pretty();
```

### Troubleshooting
```bash
sudo systemctl status mongodb
tail -f /var/log/mongodb/mongod.log
db.serverStatus()
```

### Backup & Recovery
```bash
mongodump --db dbname --out /backup/dir
mongorestore --db dbname /backup/dir/dbname
```

### Security
```js
use admin
db.createUser({user: "user", pwd: "pass", roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"]})
```

---

## Cassandra
### Installation & Configuration
```bash
sudo apt install cassandra
sudo systemctl start cassandra
cqlsh
```

### Operations (DDL/DML)
```sql
CREATE KEYSPACE ks1 WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};
USE ks1;
CREATE TABLE t1 (id int PRIMARY KEY, name text);
INSERT INTO t1 (id, name) VALUES (1, 'Alice');
SELECT * FROM t1;
UPDATE t1 SET name='Bob' WHERE id=1;
DELETE FROM t1 WHERE id=1;
DESCRIBE KEYSPACES;
DESCRIBE TABLE ks1.t1;
```

### Troubleshooting
```bash
nodetool status
nodetool info
tail -f /var/log/cassandra/system.log
```

### Backup & Recovery
```bash
nodetool snapshot ks1
# Restore: copy snapshots back and run nodetool refresh
```

### Security
```sql
CREATE ROLE myuser WITH PASSWORD = 'pass' AND LOGIN = true;
GRANT ALL PERMISSIONS ON KEYSPACE ks1 TO myuser;
```

---

## Kafka
### Installation & Configuration
```bash
# Download and extract Kafka
wget https://downloads.apache.org/kafka/<version>/kafka_2.13-<version>.tgz
tar -xzf kafka_2.13-<version>.tgz
cd kafka_2.13-<version>
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

### Operations (DDL/DML)
```bash
# Create topic
bin/kafka-topics.sh --create --topic mytopic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
# Produce message
bin/kafka-console-producer.sh --topic mytopic --bootstrap-server localhost:9092
# Consume message
bin/kafka-console-consumer.sh --topic mytopic --from-beginning --bootstrap-server localhost:9092
```

### Troubleshooting
```bash
bin/kafka-topics.sh --describe --topic mytopic --bootstrap-server localhost:9092
tail -f logs/server.log
```

### Backup & Recovery
> No built-in backup; use file system snapshots or third-party tools.  
> Use Kafka MirrorMaker for topic replication (as backup).

### Security
```properties
# In server.properties
ssl.enable=true
sasl.enabled.mechanisms=PLAIN
```

---

## Elasticsearch
### Installation & Configuration
```bash
# Install and start Elasticsearch
sudo apt install elasticsearch
sudo systemctl start elasticsearch
curl -X GET "localhost:9200/"

# Configure memory settings (in jvm.options)
-Xms4g
-Xmx4g

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
# Index Management
curl -X PUT "localhost:9200/myindex" -H 'Content-Type: application/json' -d'
{
  "settings": {
    "number_of_shards": 3,
    "number_of_replicas": 2
  }
}'

# Bulk Insert
curl -X POST "localhost:9200/_bulk" -H 'Content-Type: application/json' -d'
{"index": {"_index": "myindex", "_id": "1"}}
{"name": "Alice", "age": 25}
{"index": {"_index": "myindex", "_id": "2"}}
{"name": "Bob", "age": 30}
'

# Advanced Queries
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

# Aggregations
curl -X GET "localhost:9200/myindex/_search" -H 'Content-Type: application/json' -d'
{
  "aggs": {
    "avg_age": { "avg": { "field": "age" } }
  }
}'
```

### Troubleshooting
```bash
# Cluster health details
curl -X GET "localhost:9200/_cluster/health?pretty"
curl -X GET "localhost:9200/_cat/health?v"
curl -X GET "localhost:9200/_cat/indices?v"
curl -X GET "localhost:9200/_cat/nodes?v"

# Pending tasks
curl -X GET "localhost:9200/_cat/pending_tasks?v"

# Shard allocation issues
curl -X GET "localhost:9200/_cat/shards?v"

# Hot threads analysis
curl -X GET "localhost:9200/_nodes/hot_threads"

# Clear cache
curl -X POST "localhost:9200/_cache/clear"

# Additional stats
curl -X GET "localhost:9200/_cluster/stats?pretty"
curl -X GET "localhost:9200/_nodes/stats?pretty"
```

### Backup & Recovery
```bash
# Create repository with S3
curl -X PUT "localhost:9200/_snapshot/s3_backup" -H 'Content-Type: application/json' -d'
{
  "type": "s3",
  "settings": {
    "bucket": "my-es-snapshots",
    "region": "us-west-2"
  }
}'

# Incremental snapshot
curl -X PUT "localhost:9200/_snapshot/my_backup/snapshot_2?wait_for_completion=true" -H 'Content-Type: application/json' -d'
{
  "indices": "myindex",
  "ignore_unavailable": true,
  "include_global_state": false
}'

# List snapshots
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

# Create users and roles
curl -X POST "localhost:9200/_security/user/myuser" -H 'Content-Type: application/json' -d'
{
  "password": "mypassword",
  "roles": ["admin"],
  "full_name": "My Admin User"
}'

# Configure role
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