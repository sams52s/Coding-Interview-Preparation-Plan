---

## 🟡 Behavioral, Technical, and Cloud Scenario Q&A Additions

---

### Interviewer (Follow-up):
➤ Which specific MySQL or MongoDB version features have you worked with, and how did they impact your administration tasks?

**Candidate:**  
➤ MySQL 8.0 features like window functions, CTEs, invisible indexes, and improved JSON support enhanced query flexibility and performance.  
➤ MongoDB 4.x aggregation pipelines and multi-document transactions allowed complex business logic on the database layer, reducing application-side load.

---

### Interviewer:
2️⃣ Tell me about a time you handled a production outage.

**Candidate:**  
➤ Diagnosed MySQL server down due to disk space exhaustion.  
➤ Used `df -h`, `du`, `systemctl`; compressed old logs, freed space, restarted service.  
➤ Implemented log rotation, disk monitoring to prevent recurrence.  
➤ Final check: Validated service restoration, integrity, documented incident.

---

### Interviewer (Follow-up):
➤ What monitoring gaps did you discover after that outage?

**Candidate:**  
➤ Lack of disk space and log rotation alerts; implemented proactive monitoring and alerting.

---

### Interviewer:
3️⃣ How do you handle prioritizing tasks between independent work and team collaboration?

**Candidate:**  
➤ Use Jira/Trello for prioritization; block time for focused tasks; communicate clearly with stakeholders; balance planned and unplanned work.

---

### Interviewer (Follow-up):
➤ Can you share an example where conflicting priorities caused tension, and how you handled it?

**Candidate:**  
➤ Facilitated a meeting between teams, clarified critical deadlines, negotiated compromises, and kept both streams moving.

---

### Interviewer:
4️⃣ How do you approach learning a new database or data platform?

**Candidate:**  
➤ Read official docs, set up sandbox, run experiments, study community best practices, evaluate production readiness through staged testing.

---

### Interviewer (Follow-up):
➤ How do you evaluate whether the new platform is production-ready?

**Candidate:**  
➤ Assess stability, performance, ecosystem, support, security, and maintainability through POCs and benchmark testing.

---

### Interviewer:
5️⃣ How would you troubleshoot a slow-running query?

**Candidate:**  
➤ Identify using slow query logs, `SHOW PROCESSLIST`.  
➤ Analyze with `EXPLAIN`, check execution plans.  
➤ Optimize indexes, restructure logic, test changes safely.  
➤ Benchmark improvements before rollout.

---

### Interviewer (Follow-up):
➤ How do you decide between adding an index vs rewriting the query?

**Candidate:**  
➤ If query shape is sound but missing support, add an index. If logic is flawed or wasteful, prioritize rewrite.

---

### Interviewer:
6️⃣ What are key Linux commands you’d use when managing a database server?

**Candidate:**  
➤ `systemctl`, `df`, `du`, `free`, `top`, `htop`, `tail`, `netstat`, `ss`, `cron`, `rsync`.

---

### Interviewer (Follow-up):
➤ How do you automate these system checks?

**Candidate:**  
➤ Bash scripts + cron + integration with Prometheus/Nagios; automated notifications.

---

### Interviewer:
7️⃣ How do you perform database health checks?

**Candidate:**  
➤ Monitor uptime, replication, error logs, query performance, backups, resource use.

---

### Interviewer (Follow-up):
➤ What metrics would trigger immediate incident response?

**Candidate:**  
➤ High replication lag, disk near full, connection pool exhaustion, critical error spikes.

---

### Interviewer:
8️⃣ Describe how you automate routine database maintenance.

**Candidate:**  
➤ Bash/Ansible scripts for backups, index optimization, log rotation; schedule with cron; integrate alerts.

---

### Interviewer (Follow-up):
➤ How do you handle failures in your automation scripts?

**Candidate:**  
➤ Build in retries, error logging, notification mechanisms.

---

### Interviewer:
9️⃣ How do you ensure database backups are reliable?

**Candidate:**  
➤ Encrypt backups, store offsite, perform periodic restore tests, monitor completion, log retention.

---

### Interviewer (Follow-up):
➤ How do you validate that backups meet RTO/RPO?

**Candidate:**  
➤ Conduct timed recovery drills, compare against SLAs, adjust processes if needed.

---

### Interviewer:
🔟 What security measures do you apply?

**Candidate:**  
➤ Strong passwords, least privilege, IP restrictions, SSL/TLS, encryption, regular audits, patching.

---

### Interviewer (Follow-up):
➤ How do you stay current on evolving security threats?

**Candidate:**  
➤ Subscribe to vendor advisories, security mailing lists, participate in forums, and schedule regular internal reviews.

---

### Interviewer:
11️⃣ How familiar are you with cloud-managed databases?

**Candidate:**  
➤ AWS RDS, Azure Database, MongoDB Atlas; set up scaling, backups, failover, integrate with IAM and VPC.

---

### Interviewer (Follow-up):
➤ What challenges have you faced working with vendor SLAs?

**Candidate:**  
➤ Slow escalations, unclear severity levels; mitigated with clear escalation plans and regular vendor communication.

---

### Interviewer:
12️⃣ How do you monitor SLA adherence?

**Candidate:**  
➤ Use Prometheus, CloudWatch, Grafana; track uptime, resolution times, generate SLA reports.

---

### Interviewer:
13️⃣ How would you escalate a critical production issue to a vendor?

**Candidate:**  
➤ Collect logs, document business impact, open high-priority ticket, follow up regularly, keep stakeholders informed.

---

### Interviewer:
14️⃣ How do you implement a new data platform?

**Candidate:**  
➤ Assess requirements, design solution, POC, benchmark, phased rollout, document, train teams.

---

### Interviewer:
15️⃣ How do you choose between Cloudera, ScyllaDB, Elasticsearch, DataStax?

**Candidate:**  
➤ Cloudera → Hadoop/Spark analytics.  
➤ ScyllaDB → high-throughput NoSQL.  
➤ Elasticsearch → search, log analytics.  
➤ DataStax → enterprise Cassandra.

---

✅ All missing Q&A, follow-ups, and detailed examples have now been integrated.

## 💾 Database & SQL — Interview Q&A

---

### Duplicate Row Detection Query

**Interviewer:** How would you write a SQL query to find duplicate rows?

**Candidate:** I’d use a `GROUP BY` clause on the columns we want to check and apply `HAVING COUNT(*) > 1` to detect duplicates.

**Example Query:**
```sql
SELECT column1, column2, COUNT(*)
FROM table_name
GROUP BY column1, column2
HAVING COUNT(*) > 1;
```

---

### Second Highest Salary Query

**Interviewer:** How can you fetch the second highest salary?

**Candidate:** One method is to use a subquery that filters out the maximum salary.

**Example Query:**
```sql
SELECT MAX(salary) AS second_highest
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```

Alternatively, use:
```sql
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 1;
```

---

### DELETE vs TRUNCATE vs DROP

**Interviewer:** What’s the difference between DELETE, TRUNCATE, and DROP?

**Candidate:** 
- `DELETE` removes selected rows, is transaction-safe, and can be rolled back.
- `TRUNCATE` removes all rows, is faster, minimal logging, but usually can’t be rolled back.
- `DROP` deletes the entire table structure and data.

---

### Indexing and Optimization

**Interviewer:** How do indexes improve performance?

**Candidate:** Indexes speed up read queries by allowing faster lookups but can slow down writes due to maintenance overhead. Choosing the right indexes is key for optimization.

---

### Joins (Inner, Left, Right, Full Outer)

**Interviewer:** Can you explain the different join types?

**Candidate:** 
- **INNER JOIN:** Returns matching rows from both tables.
- **LEFT JOIN:** Returns all rows from the left table, plus matching right-side rows.
- **RIGHT JOIN:** Returns all rows from the right table, plus matching left-side rows.
- **FULL OUTER JOIN:** Returns all rows when there’s a match in either table.

**Example:**
```sql
SELECT a.id, b.name
FROM tableA a
INNER JOIN tableB b ON a.id = b.id;
```

---

### What is a Primary Key vs Foreign Key?

**Interviewer:** Can you explain the difference between a primary key and a foreign key?

**Candidate:** 
- A **primary key** uniquely identifies each row in a table.
- A **foreign key** is a reference to a primary key in another table, establishing a relationship between tables.

---

### What are Transactions and ACID Properties?

**Interviewer:** What is a transaction, and what are ACID properties?

**Candidate:** A transaction is a unit of work performed on the database. ACID ensures:
- **Atomicity:** All or nothing.
- **Consistency:** Maintains valid data.
- **Isolation:** Concurrent transactions don’t interfere.
- **Durability:** Changes persist even after failures.

---

### What are Common Table Expressions (CTEs)?

**Interviewer:** What is a CTE, and why is it useful?

**Candidate:** A CTE is a named temporary result set that improves readability and modularizes complex queries, often used with recursion.

---

### Explain Window Functions

**Interviewer:** What are window functions?

**Candidate:** Functions that perform calculations across rows related to the current row, like `ROW_NUMBER()`, `RANK()`, `LEAD()`, `LAG()` — useful for advanced analytics.

---

### Explain Normalization and Denormalization

**Interviewer:** What’s the difference?

**Candidate:** 
- **Normalization** reduces redundancy by splitting data into related tables.
- **Denormalization** adds redundancy for performance, combining tables to reduce joins.

---

### How Do You Prevent SQL Injection?

**Interviewer:** How would you protect against SQL injection?

**Candidate:** Use parameterized queries or prepared statements, avoid dynamic SQL, and validate user inputs.

---

### Explain Index Types

**Interviewer:** What types of indexes exist?

**Candidate:** 
- **B-Tree Index:** Default, balanced for general use.
- **Hash Index:** Fast exact lookups.
- **Bitmap Index:** Efficient for low-cardinality columns.
- **Full-Text Index:** Optimized for text searches.

---

### SQL Query Optimization

**Interviewer:** How do you optimize SQL queries?

**Candidate:** Several key strategies:
1. **Proper Indexing:**
   ```sql
   CREATE INDEX idx_lastname ON employees(last_name);
   ```
2. **Avoid SELECT *:**
   ```sql
   -- Instead of: SELECT * FROM employees
   SELECT employee_id, last_name, salary FROM employees;
   ```
3. **Use EXISTS instead of IN:**
   ```sql
   SELECT * FROM orders o
   WHERE EXISTS (
       SELECT 1 FROM customers c
       WHERE c.id = o.customer_id
       AND c.status = 'active'
   );
   ```
4. **Optimize JOINs:**
   - Join order matters
   - Use proper join types
   - Index join columns

---

### Database Constraints

**Interviewer:** What types of constraints can you use?

**Candidate:**
1. **PRIMARY KEY:**
   ```sql
   CREATE TABLE users (
       id INT PRIMARY KEY,
       username VARCHAR(50) UNIQUE
   );
   ```
2. **FOREIGN KEY:**
   ```sql
   CREATE TABLE orders (
       order_id INT PRIMARY KEY,
       user_id INT,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```
3. **CHECK:**
   ```sql
   CREATE TABLE products (
       price DECIMAL CHECK (price > 0)
   );
   ```

---

### Advanced SQL Features

**Interviewer:** Explain advanced SQL concepts like CTEs and Window Functions.

**Candidate:**

1. **CTEs (Common Table Expressions):**
   ```sql
   WITH recursive_cte AS (
       SELECT id, parent_id, name, 1 as level
       FROM categories
       WHERE parent_id IS NULL
       UNION ALL
       SELECT c.id, c.parent_id, c.name, rc.level + 1
       FROM categories c
       JOIN recursive_cte rc ON c.parent_id = rc.id
   )
   SELECT * FROM recursive_cte;
   ```

2. **Window Functions Examples:**
   ```sql
   SELECT 
       department,
       employee_name,
       salary,
       ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) as rank,
       AVG(salary) OVER (PARTITION BY department) as dept_avg,
       LAG(salary) OVER (ORDER BY hire_date) as previous_salary
   FROM employees;
   ```

---

### Database Performance

**Interviewer:** How do you monitor and improve database performance?

**Candidate:**
1. **Monitoring:**
   - Query execution plans
   - Index usage statistics
   - Wait statistics
   - I/O metrics

2. **Optimization Tools:**
   ```sql
   EXPLAIN ANALYZE SELECT * FROM large_table
   WHERE complex_condition = true;
   ```

3. **Index Maintenance:**
   ```sql
   -- Rebuild fragmented indexes
   ALTER INDEX idx_name ON table_name REBUILD;
   ```

---

### Database Security

**Interviewer:** What security measures do you implement?

**Candidate:**
1. **User Management:**
   ```sql
   CREATE USER 'app_user'@'localhost' 
   IDENTIFIED BY 'secure_password';
   
   GRANT SELECT, INSERT ON database.* 
   TO 'app_user'@'localhost';
   ```

2. **SQL Injection Prevention:**
   ```java
   // Using PreparedStatement
   PreparedStatement stmt = conn.prepareStatement(
       "SELECT * FROM users WHERE id = ?"
   );
   stmt.setInt(1, userId);
   ```

---

### Database Backup Strategies

**Interviewer:** What backup strategies do you use?

**Candidate:**
1. **Full Backup:**
   ```sql
   BACKUP DATABASE mydatabase 
   TO DISK = 'path/backup.bak';
   ```

2. **Differential Backup:**
   ```sql
   BACKUP DATABASE mydatabase 
   TO DISK = 'path/diff.bak'
   WITH DIFFERENTIAL;
   ```

3. **Transaction Log Backup:**
   ```sql
   BACKUP LOG mydatabase 
   TO DISK = 'path/log.trn';
   ```

---

### Explain Sharding and Partitioning

**Interviewer:** What’s the difference between sharding and partitioning?

**Candidate:** 
- **Partitioning:** Divides a table into smaller parts within the same database.
- **Sharding:** Splits data across multiple databases or servers for scalability.

---

### NoSQL vs SQL

**Interviewer:** When would you choose NoSQL over SQL?

**Candidate:**
- Use SQL for:
  - Complex transactions
  - Data integrity requirements
  - Structured data
- Use NoSQL for:
  - Scalability needs
  - Schema flexibility
  - High-velocity data

---

### Database Replication

**Interviewer:** Explain database replication concepts.

**Candidate:**
1. **Master-Slave:**
   - Read scalability
   - Backup solution
   - Async replication

2. **Master-Master:**
   - Write scalability
   - High availability
   - Conflict resolution needed

---

## 🏋️ Complex Query Examples

---

### Query: Find employees with salary above department average

```sql
SELECT e.employee_id, e.employee_name, e.salary
FROM employees e
JOIN (
    SELECT department_id, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department_id
) d_avg ON e.department_id = d_avg.department_id
WHERE e.salary > d_avg.avg_salary;
```

---

### Query: Get top 3 salaries per department

```sql
SELECT employee_id, employee_name, department_id, salary
FROM (
    SELECT *, 
        DENSE_RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) AS rank
    FROM employees
) ranked
WHERE rank <= 3;
```

---

### Query: Find customers who placed no orders

```sql
SELECT c.customer_id, c.customer_name
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;
```

---

### Query: Recursive CTE for category hierarchy

```sql
WITH RECURSIVE category_path AS (
    SELECT id, name, parent_id, name AS path
    FROM categories
    WHERE parent_id IS NULL
    UNION ALL
    SELECT c.id, c.name, c.parent_id, CONCAT(cp.path, ' > ', c.name)
    FROM categories c
    JOIN category_path cp ON c.parent_id = cp.id
)
SELECT * FROM category_path;
```

---

### Query: Calculate Running Total by Date

```sql
SELECT 
    order_date,
    order_amount,
    SUM(order_amount) OVER (ORDER BY order_date) as running_total
FROM orders;
```

---

### Query: Find Gaps in Sequential Data

```sql
WITH numbers AS (
    SELECT generate_series(
        (SELECT MIN(id) FROM sequence_table),
        (SELECT MAX(id) FROM sequence_table)
    ) AS id
)
SELECT n.id
FROM numbers n
LEFT JOIN sequence_table s ON n.id = s.id
WHERE s.id IS NULL;
```

---

### Query: Pivot Table Example

```sql
SELECT *
FROM (
    SELECT product_name, quarter, sales_amount
    FROM sales
) AS source
PIVOT (
    SUM(sales_amount)
    FOR quarter IN (Q1, Q2, Q3, Q4)
) AS pivot_table;
```

---

## 📝 Practice Quiz Set with Answers

---

### Q1: What’s the difference between WHERE and HAVING?

✅ **Answer:**  
- WHERE filters rows before grouping.  
- HAVING filters groups after aggregation.

---

### Q2: How do you ensure a column only contains unique values?

✅ **Answer:**  
Define a UNIQUE constraint or create a unique index on the column.

---

### Q3: What’s the difference between INNER JOIN and CROSS JOIN?

✅ **Answer:**  
- INNER JOIN matches rows based on join condition.
- CROSS JOIN returns the Cartesian product (all row combinations).

---

### Q4: When should you use a covering index?

✅ **Answer:**  
When all queried columns are included in the index, avoiding the need to access the table (improves read performance).

---

### Q5: What is a materialized view?

✅ **Answer:**  
A precomputed, stored result set that can be refreshed periodically; improves query performance for expensive joins/aggregations.

---

### Q6: What is a self-join and when would you use it?

✅ **Answer:**  
A self-join is when a table is joined with itself. Used for hierarchical data (employee-manager relationships) or comparing rows within the same table.

---

### Q7: Explain the difference between UNION and UNION ALL.

✅ **Answer:**  
- UNION removes duplicate rows and sorts the result
- UNION ALL keeps duplicates and doesn't sort, making it faster

---

### Q8: What is a correlated subquery?

✅ **Answer:**  
A subquery that references columns from the outer query, executed once for each row in the outer query. Can impact performance but useful for row-by-row comparison.

---

### Q9: What is the purpose of the COALESCE function?

✅ **Answer:**  
Returns the first non-null value in a list. Useful for handling NULL values and providing default values in queries.

---

### Q10: Explain the difference between DELETE and TRUNCATE with respect to transaction logs.

✅ **Answer:**  
DELETE logs individual row deletions and can be rolled back, while TRUNCATE logs only page deallocation, making it faster but non-reversible in most cases.


---

## 9️⃣ Database System Comparisons

---

### PostgreSQL vs MySQL

**Interviewer:** How would you compare PostgreSQL and MySQL?

**Candidate:**

| Feature            | PostgreSQL                                | MySQL                                 |
|--------------------|------------------------------------------|---------------------------------------|
| Type               | Object-relational database (ORDBMS)       | Relational database (RDBMS)           |
| Standards          | Strong SQL, advanced compliance           | Good SQL support, slightly looser     |
| Data Types         | Rich types (JSON, arrays, custom types)   | Basic types, JSON support improved    |
| Extensibility      | Highly extensible, supports custom funcs  | Less extensible                      |
| Performance        | Better for complex queries, analytics     | Faster for simple reads, web apps     |
| Replication        | Logical, streaming, physical replication  | Master-slave, group replication       |
| Community          | Strong open-source community, academic    | Huge open-source + corporate backing  |
| Use Cases          | Analytics, complex data models, GIS       | Web apps, CMSs, OLTP-heavy workloads  |

---

### Oracle vs MySQL / PostgreSQL

**Interviewer:** How would you compare Oracle Database with MySQL and PostgreSQL?

**Candidate:**

| Feature            | Oracle                                   | PostgreSQL / MySQL                    |
|--------------------|-----------------------------------------|---------------------------------------|
| Type               | Enterprise commercial RDBMS              | Open-source RDBMS/ORDBMS             |
| Licensing          | Paid, enterprise-level support           | Free, open-source, optional paid      |
| Features           | Advanced partitioning, clustering, RAC   | Lighter, fewer built-in enterprise features |
| Scalability        | Excellent vertical and horizontal scaling| Good, but less enterprise-grade       |
| Performance        | Highly optimized, robust optimizers      | Optimized for general workloads       |
| Ecosystem          | Integrated tools, reporting, backup      | Community or third-party tools        |
| Use Cases          | Large enterprise apps, banking, telecom  | SMEs, startups, SaaS, analytics       |

---

### SQL vs NoSQL

**Interviewer:** What’s the difference between SQL and NoSQL databases?

**Candidate:**

| Category         | SQL (Relational)                          | NoSQL (Non-relational)                   |
|------------------|------------------------------------------|-----------------------------------------|
| Data Model       | Structured tables, predefined schema      | Flexible schema: document, key-value, graph, column |
| Transactions     | ACID-compliant, strong consistency        | BASE (basically available, soft state, eventual consistency) |
| Scalability      | Vertical scaling (scale-up)               | Horizontal scaling (scale-out)           |
| Query Language   | SQL (standardized)                       | Custom or no formal query language       |
| Use Cases        | Financial systems, ERP, CRM, OLTP         | Big data, IoT, real-time analytics, content management |
| Examples         | MySQL, PostgreSQL, Oracle, SQL Server     | MongoDB, Cassandra, DynamoDB, Redis      |

---

## 🔟 Additional Database Comparisons

---

### MongoDB vs Cassandra

**Interviewer:** How would you compare MongoDB and Cassandra?

**Candidate:**

| Feature            | MongoDB                                     | Cassandra                                  |
|--------------------|--------------------------------------------|-------------------------------------------|
| Data Model         | Document store (JSON/BSON)                  | Wide-column store                         |
| Schema Flexibility | Dynamic schema, very flexible               | Semi-structured, designed for wide rows   |
| Consistency        | Tunable, generally strong (replica sets)    | Eventually consistent by default          |
| Scalability        | Horizontal scaling (sharding)               | Horizontal scaling, excellent write performance |
| Query Language     | MongoDB Query Language (MQL)                | Cassandra Query Language (CQL), SQL-like  |
| Best Use Cases     | Content management, catalogs, analytics     | Time-series, IoT, large-scale writes, logs|

---

### DynamoDB vs MongoDB

**Interviewer:** How would you compare DynamoDB and MongoDB?

**Candidate:**

| Feature            | DynamoDB (AWS)                             | MongoDB                                    |
|--------------------|-------------------------------------------|-------------------------------------------|
| Type               | Managed NoSQL key-value & document store   | Open-source document store                |
| Scaling            | Auto-scaling, on-demand or provisioned      | Manual sharding setup                     |
| Performance        | Predictable, low-latency reads/writes       | Flexible, depends on deployment           |
| Consistency        | Strong or eventual (configurable)          | Tunable with replica sets                 |
| Integrations       | Deep AWS integration                       | Broad integrations, multi-cloud ready     |
| Cost Model         | Pay-per-request or provisioned capacity    | Server/cloud service costs                |

---

### Redis vs Memcached

**Interviewer:** How would you compare Redis and Memcached?

**Candidate:**

| Feature            | Redis                                      | Memcached                                 |
|--------------------|-------------------------------------------|------------------------------------------|
| Data Structures    | Rich (strings, lists, sets, hashes, etc.)  | Simple key-value pairs                   |
| Persistence        | Snapshotting, AOF logs supported           | Pure in-memory, no persistence           |
| Clustering         | Native clustering, replication supported   | Requires client-side sharding            |
| Use Cases          | Leaderboards, pub/sub, complex caching     | Simple caching, session storage          |

---

### Elasticsearch vs Solr

**Interviewer:** How would you compare Elasticsearch and Solr?

**Candidate:**

| Feature            | Elasticsearch                              | Solr                                      |
|--------------------|-------------------------------------------|------------------------------------------|
| Core Focus         | Distributed search + analytics             | Enterprise search (Apache Lucene-based)  |
| Scaling            | Native clustering, elastic scalability      | Manual sharding, more complex setup      |
| API Access         | RESTful JSON API                           | REST, XML-based APIs                     |
| Ecosystem          | Elastic Stack (ELK) integrations           | Rich plugin ecosystem, strong community  |
| Use Cases          | Log analytics, real-time search, metrics   | Enterprise document search, legacy apps  |

---

### OLTP vs OLAP

**Interviewer:** What’s the difference between OLTP and OLAP?

**Candidate:**

| Feature            | OLTP (Online Transaction Processing)        | OLAP (Online Analytical Processing)      |
|--------------------|-------------------------------------------|-----------------------------------------|
| Purpose            | Real-time transactional operations          | Complex analytical queries, reporting   |
| Data Model         | Highly normalized, small transactions      | Denormalized, aggregated for fast reads |
| Performance        | Fast, frequent small reads/writes          | Optimized for large scans, aggregations |
| Use Cases          | Banking, e-commerce, CRM                   | Data warehousing, BI, multidimensional analysis |


---

## 🗂 Cheat Sheet: When to Choose What

---

### Relational (SQL) vs Non-relational (NoSQL)

✅ Choose SQL:
- Strong consistency required (e.g., banking)
- Complex relationships & joins
- Standard reporting & analytics
- Small to medium dataset, vertical scaling

✅ Choose NoSQL:
- Flexible schema, evolving data models
- High-volume, distributed workloads
- Need horizontal scaling
- Use cases like IoT, logs, caching, social networks

---

### PostgreSQL vs MySQL

✅ Choose PostgreSQL:
- Advanced analytics or GIS (PostGIS)
- Complex queries, custom functions, JSON + relational needs
- Need for strict standards and data types

✅ Choose MySQL:
- Simple, fast web applications
- Mature ecosystem, large community
- Easier replication setups for read-heavy apps

---

### MongoDB vs Cassandra

✅ Choose MongoDB:
- Flexible document model (JSON/BSON)
- Varying record structures
- Analytics over semi-structured data

✅ Choose Cassandra:
- Massive write throughput
- Time-series or IoT data
- Multi-region, highly available architecture

---

### DynamoDB vs MongoDB

✅ Choose DynamoDB:
- Fully managed, serverless, AWS-integrated
- Predictable workloads, low latency needs

✅ Choose MongoDB:
- Custom deployment flexibility
- Advanced querying and aggregation

---

### Redis vs Memcached

✅ Choose Redis:
- Complex caching (sorted sets, lists)
- Need persistence or replication
- Pub/sub or atomic counters

✅ Choose Memcached:
- Simple key-value caching
- Extremely lightweight, minimal features

---

### Elasticsearch vs Solr

✅ Choose Elasticsearch:
- Real-time log analytics
- ELK Stack integrations
- Cloud-native scaling

✅ Choose Solr:
- Enterprise document search
- Mature legacy integrations
- Fine-tuned customization

---

## 🌳 Decision Trees (Text Diagrams)

---

### SQL vs NoSQL

```
                ┌─────────────┐
                │    Data?    │
                └─────┬───────┘
                      │
      ┌───────────────┴───────────────┐
      │ Structured, relational?       │
      │ (strict schema, joins)        │
      └─────┬────────────┬────────────┘
            │            │
         Yes (SQL)    No (NoSQL)
```

---

### PostgreSQL vs MySQL

```
                ┌──────────────────────────┐
                │ Advanced queries, GIS,   │
                │ JSON + relational?       │
                └─────┬────────────┬───────┘
                      │            │
                   Yes (Postgres)  No (MySQL)
```

---

### MongoDB vs Cassandra

```
                ┌─────────────────────┐
                │ Write-heavy,        │
                │ time-series?        │
                └─────┬─────────┬─────┘
                      │         │
                   Yes (Cassandra)
                                │
                          No → Flexible docs?
                                │
                            Yes (MongoDB)
```

---
