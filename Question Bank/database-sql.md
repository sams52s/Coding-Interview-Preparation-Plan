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

