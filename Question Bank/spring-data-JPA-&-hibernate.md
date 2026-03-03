# Spring Data JPA & Hibernate Interview Questions

## Core Concepts

1. **What is JPA? What is Hibernate? What is the difference between them?**
   - **JPA (Java Persistence API)**: A specification (standard) for Object-Relational Mapping (ORM) in Java. It defines interfaces and annotations.
   - **Hibernate**: An actual implementation of the JPA specification. It provides the underlying code to map Java objects to database tables.
   - **Spring Data JPA**: An abstraction layer on top of JPA providers (like Hibernate) that significantly reduces boilerplate code required for data access layers by generating repository implementations at runtime.

2. **Explain the different entity states in Hibernate.**
   - **Transient**: An object newly created (using `new`), not associated with any Session, and has no database representation.
   - **Persistent (Managed)**: An object associated with an active Hibernate `Session`. Changes made to it are tracked and automatically pushed to the DB (dirty checking).
   - **Detached**: An object previously persistent but its `Session` has been closed. Changes are no longer tracked.
   - **Removed**: An object scheduled for deletion from the database.

3. **What is the First-Level Cache and Second-Level Cache in Hibernate?**
   - **First-Level Cache**: Enabled by default. Associated with the `Session` object. Exists only for the duration of a transaction/session.
   - **Second-Level Cache**: Disabled by default. Associated with the `SessionFactory`. Shared across all sessions. Requires a third-party provider like Ehcache or Hazelcast.

## Spring Data JPA Specifics

4. **What is the difference between `CrudRepository`, `PagingAndSortingRepository`, and `JpaRepository`?**
   - `CrudRepository`: Provides basic CRUD methods (save, findById, delete).
   - `PagingAndSortingRepository`: Extends `CrudRepository` and adds methods for pagination (`Pageable`) and sorting (`Sort`).
   - `JpaRepository`: Extends `PagingAndSortingRepository` (and `CrudRepository`). Adds JPA-specific methods like `flush()`, `saveAndFlush()`, and batch deletes. It returns lists instead of iterables.

5. **How do you write custom queries in Spring Data JPA?**
   - **Method Name Resolution**: `findByFirstNameAndLastName(String first, String last)`.
   - **@Query Annotation (JPQL)**: `@Query("SELECT u FROM User u WHERE u.email = ?1")`.
   - **Native Query**: `@Query(value = "SELECT * FROM users u WHERE u.email = ?1", nativeQuery = true)`.

6. **What is the N+1 Select Problem and how do you solve it?**
   - **Problem**: When loading a collection of parent entities (1 query) and then lazily loading a child collection for each parent (N queries). Resulting in N+1 total queries, severely impacting performance.
   - **Solution in Spring Data JPA**:
     - Use `JOIN FETCH` in a `@Query` annotation.
     - Use `@EntityGraph` to define fetch plans dynamically.

## Advanced Concepts & Annotations

7. **Explain Fetch Types (`FetchType.LAZY` vs `FetchType.EAGER`).**
   - **EAGER**: The related entity or collection is fetched immediately along with the parent entity. Default for `@ManyToOne` and `@OneToOne`.
   - **LAZY**: The related entity or collection is fetched only when accessed for the first time via its getter. Default for `@OneToMany` and `@ManyToMany`. *Best practice is to use LAZY for everything to avoid performance issues.*

8. **What is the difference between `@Entity` and `@Table`?**
   - `@Entity`: Marks a class as a JPA entity. (Mandatory).
   - `@Table`: Specifies the details of the database table (name, schema) the entity maps to. (Optional; defaults to the class name).

9. **How do you handle Transactions in Spring Data JPA?**
   - Use the `@Transactional` annotation on service methods.
   - **Propagation limits**: `REQUIRED` (default - joining existing or creating new), `REQUIRES_NEW` (always creates a new transaction, suspending current).
   - **Rollback rules**: By default, rolls back on `RuntimeException` and `Error`, but NOT on checked exceptions (`Exception`). Configure via `@Transactional(rollbackFor = Exception.class)`.

10. **What is Optimistic Locking?**
    - Ensuring data consistency without locking the DB row. Instead, a version column is used.
    - Achieved using the `@Version` annotation. If two transactions read the same row and attempt to update it, the first succeeds (version increments), and the second fails with an `OptimisticLockException` because the version no longer matches.
