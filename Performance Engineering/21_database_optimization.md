# Database Optimization

Database performance is often the primary bottleneck in backend applications. Optimizing the database involves improving schema design, query strategy, and indexing.

## 1. Indexing Strategies

Indexes are critical for read performance, acting like the index at the back of a book. They map a column value to the disk location of the row.

- **B-Tree Indexes**: The default index type. Good for range queries (`>`, `<`), equality, and prefix matching.
- **Hash Indexes**: Only support equality (`=`) lookups. Cannot be used for range queries. Usually only supported by memory DBs (like Redis) or specific PostgreSQL setups.
- **Composite Indexes**: An index on multiple columns (e.g., `(last_name, first_name)`). *Rule of thumb*: The order matters! A query filtering on `first_name` alone *cannot* use this index. The leftmost prefix must be used.
- **Covering Index**: An index that contains all the columns needed for a query. The database engine can fulfill the query entirely from the index tree without having to do a separate disk read to fetch the actual row.

### Trade-offs of Indexes
Indexes speed up reads but **slow down writes** (INSERT, UPDATE, DELETE), because the database must update both the table and the index tree. Also, indexes consume disk space and RAM.

## 2. Query Optimization (EXPLAIN)

You must know how to use the `EXPLAIN` (or `EXPLAIN ANALYZE` in Postgres) command. It tells you *how* the database plans to execute your query.

Look for:
- **Seq Scan / Table Scan**: The database is reading every single row to find the matching ones. Usually terrible for large tables. Indicates a missing index.
- **Index Scan**: Good. The database is traversing an index tree.
- **Filesort / External Merge**: The database is sorting data on disk instead of memory. Terrible for performance. Often solved by adding an index on the `ORDER BY` column.

## 3. Dealing with Large Data (Scaling the DB)

When a single table or server becomes too large:

1. **Read Replicas**: Separate read traffic from write traffic. Used for read-heavy applications (typical web apps).
2. **Partitioning**: Splitting a large table into smaller, physical pieces (partitions) within the same database instance based on a rule (e.g., partitioning a `sales` table by month). Makes queries on specific months extremely fast.
3. **Sharding (Horizontal Partitioning)**: Splitting data across multiple separate database servers.
   - Requires a **Shard Key** (e.g., `user_id % num_shards` or Geographic region).
   - Extremely complex to manage. Joins across shards are slow or impossible. Only do this when absolutely necessary.

## 4. Anti-Patterns & Best Practices

- **Avoid `SELECT *`**: Only fetch the columns you need. Reduces network overhead and allows covering indexes to work.
- **N+1 Query Problem**: (As discussed in Hibernate contexts). Always use JOINs or fetch batches rather than querying individually in a loop.
- **Denormalization for Reads**: While standard 3NF (Third Normal Form) reduces data duplication, it requires expensive JOINs. Sometimes, copying redundant data into a table (denormalization) makes read queries much faster.

## Interview Questions on DB Optimization

1. **What is the difference between a Clustered and Non-Clustered index?**
   - *Answer*: A Clustered Index dictates the actual physical order of the data on disk (there can only be one per table, usually the Primary Key). A Non-Clustered index is a separate structure that contains pointers to the physical rows (there can be many per table).
2. **You have a slow query doing a `LIKE '%term%'`. How do you optimize it?**
   - *Answer*: A B-Tree index cannot speed up a leading-wildcard search. You must use a Full-Text Search engine (like Elasticsearch/Solr) or specific database extensions (like Trigram indexes `pg_trgm` in PostgreSQL).
3. **When is denormalization appropriate?**
   - *Answer*: When the system is highly read-heavy and the overhead of JOINs across multiple normalized tables is causing unacceptable latency. It's a trade-off: you gain read performance but sacrifice write performance (you now must update multiple places) and use more storage.
