# 🗃️ JPA Annotations in Java – Complete & Advanced Guide

Java Persistence API (JPA) enables ORM (Object Relational Mapping) between Java objects and relational databases. JPA annotations allow developers to define entity relationships, mappings, constraints, and database behaviors directly in code, minimizing the need for verbose XML configurations.

---

## 🏗️ 1. Core Entity and Table Mapping

| Annotation     | Description                                         |
|----------------|-----------------------------------------------------|
| `@Entity`      | Declares a class as a JPA entity                    |
| `@Table`       | Specifies table name and indexing                   |
| `@Id`          | Denotes the primary key                             |
| `@Column`      | Maps a field to a specific table column             |
| `@GeneratedValue` | Configures primary key generation strategy     |
| `@Basic`       | Optional mapping for basic types                    |
| `@Transient`   | Ignores field from persistence                      |
| `@Access`      | Switches between field/property access              |
| `@Lob`         | Marks field as a BLOB or CLOB                       |
| `@Enumerated`  | Persists enums as `STRING` or `ORDINAL`            |

### ✅ Example:
```java
@Entity
@Table(name = "users", indexes = @Index(name = "idx_username", columnList = "username", unique = true))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Lob
    private String description;

    @Transient
    private int runtimeOnlyField;
}
```

---

## 🔑 2. ID Generation Strategies

| Strategy         | Description                                      |
|------------------|--------------------------------------------------|
| `AUTO`           | Chooses strategy based on provider               |
| `IDENTITY`       | Uses auto-increment columns                      |
| `SEQUENCE`       | Uses DB sequences (preferred for portability)    |
| `TABLE`          | Uses a table to manage ID sequence               |

### ✅ Example with Sequence:
```java
@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
private Long id;
```

---

## 🔄 3. Relationships

### One-to-One
```java
@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "profile_id")
private Profile profile;
```

### One-to-Many / Many-to-One
```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Order> orders;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
private User user;
```

### Many-to-Many
```java
@ManyToMany
@JoinTable(name = "user_roles",
  joinColumns = @JoinColumn(name = "user_id"),
  inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles;
```

---

## 🧠 4. Embeddable Types

| Annotation       | Description                                |
|------------------|--------------------------------------------|
| `@Embeddable`     | Defines a class whose fields are embedded |
| `@Embedded`       | Embeds a value object                     |
| `@AttributeOverride` | Customizes column mapping in embedded fields |

### ✅ Example:
```java
@Embeddable
public class Address {
    private String street;
    private String city;
}

@Embedded
@AttributeOverrides({
    @AttributeOverride(name = "city", column = @Column(name = "billing_city"))
})
private Address billingAddress;
```

---

## 🗃️ 5. Collections and Value Types

| Annotation            | Description                                       |
|------------------------|---------------------------------------------------|
| `@ElementCollection`   | Maps non-entity collections like List<String>     |
| `@CollectionTable`     | Configures join table for element collection      |

### ✅ Example:
```java
@ElementCollection
@CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
@Column(name = "skill")
private Set<String> skills;
```

---

## 🧬 6. Inheritance Mapping

| Strategy         | Description                                     |
|------------------|--------------------------------------------------|
| `SINGLE_TABLE`   | All types stored in one table (with a `dtype`)   |
| `JOINED`         | Tables for each class, joined via foreign keys   |
| `TABLE_PER_CLASS`| Each class has its own independent table         |

### ✅ Example:
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "account_type")
public abstract class Account { ... }

@Entity
public class SavingsAccount extends Account { ... }
```

---

## 📦 7. Lifecycle Callback Annotations

| Annotation        | When Called               |
|-------------------|---------------------------|
| `@PrePersist`     | Before entity insertion   |
| `@PostPersist`    | After entity insertion    |
| `@PreUpdate`      | Before entity update      |
| `@PostUpdate`     | After entity update       |
| `@PreRemove`      | Before entity removal     |
| `@PostRemove`     | After entity removal      |
| `@PostLoad`       | After entity load         |

```java
@PrePersist
void onCreate() {
    this.createdAt = LocalDateTime.now();
}
```

---

## 🔐 8. Concurrency and Versioning

| Annotation   | Purpose                            |
|--------------|-------------------------------------|
| `@Version`    | Enables optimistic locking          |

```java
@Version
private Long version;
```

---

## 🔧 9. Custom Converters

| Annotation     | Description                              |
|----------------|------------------------------------------|
| `@Converter`    | Defines custom mapping logic for fields |

### ✅ Example:
```java
@Converter(autoApply = true)
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {
    public String convertToDatabaseColumn(Boolean value) {
        return (value != null && value) ? "Y" : "N";
    }
    public Boolean convertToEntityAttribute(String value) {
        return "Y".equals(value);
    }
}
```

---

## 💬 10. Named Queries and Native Queries

```java
@NamedQuery(
    name = "User.findByUsername",
    query = "SELECT u FROM User u WHERE u.username = :username"
)
```

```java
@Query(value = "SELECT * FROM users WHERE active = true", nativeQuery = true)
List<User> findAllActiveUsers();
```

---

## 📊 11. Indexes and Constraints

```java
@Table(
  name = "products",
  indexes = {@Index(name = "idx_product_name", columnList = "name")},
  uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "code"})}
)
```

---

## 📈 12. Advanced Mapping Annotations

### Composite Keys
```java
@Entity
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    
    // other fields
}

@Embeddable
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;
    // equals, hashCode
}
```

### Secondary Tables
```java
@Entity
@Table(name = "employee")
@SecondaryTable(name = "employee_details",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "employee_id"))
public class Employee {
    @Id
    private Long id;
    
    @Column(table = "employee_details")
    private String additionalInfo;
}
```

### Custom Type Mappings
```java
@TypeDef(
    name = "json",
    typeClass = JsonType.class
)
@Entity
public class Product {
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> attributes;
}
```

## 🎯 13. Performance Optimization Annotations

### Batch Size
```java
@Entity
@BatchSize(size = 25)
public class Department {
    @OneToMany
    @BatchSize(size = 10)
    private List<Employee> employees;
}
```

### Cache Configuration
```java
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.CacheConcurrencyStrategy(READ_WRITE)
public class Category {
    @OneToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private Set<Product> products;
}
```

### Fetch Profiles
```java
@Entity
@FetchProfile(name = "product-with-details",
    fetchOverrides = {
        @FetchProfile.FetchOverride(
            entity = Product.class,
            association = "details",
            mode = FetchMode.JOIN
        )
    }
)
public class Product { }
```

## 🔒 14. Validation Annotations

```java
@Entity
public class User {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @Email
    @Column(unique = true)
    private String email;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
    private String phone;
    
    @Past
    private LocalDate birthDate;
    
    @Valid
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
```

---

## 💼 Interview Q&A

### ❓ What is JPA and why is it important?
**✅** JPA is a specification for mapping Java objects to relational databases, enabling data persistence in a platform-independent way.

### ❓ Difference between `@OneToMany` and `@ManyToOne`?
**✅** `@ManyToOne` is the owner; `@OneToMany` is the inverse side.

### ❓ What does `@Version` do?
**✅** Adds a version field for optimistic locking to prevent concurrent update conflicts.

### ❓ How do you handle embedded value types?
**✅** Use `@Embeddable` and `@Embedded`, optionally override with `@AttributeOverride`.

### ❓ What are fetch types?
- **LAZY**: Loads reference on demand (recommended)  
- **EAGER**: Loads immediately (can lead to performance issues)

### ❓ Difference between `@ElementCollection` and `@OneToMany`?
**✅** `@ElementCollection` is for simple values (not entities). `@OneToMany` is for entity relationships.

### Additional Interview Questions

### ❓ How do you handle composite keys in JPA?
**✅ A:** Use either `@IdClass` or `@EmbeddedId`:
```java
// Using @IdClass
@IdClass(OrderLineId.class)
@Entity
public class OrderLine {
    @Id private Long orderId;
    @Id private Long lineId;
}

// Using @EmbeddedId
@Entity
public class OrderLine {
    @EmbeddedId
    private OrderLineId id;
}
```

### ❓ How do you optimize JPA performance?
**✅ A:**
```java
// 1. Use batch fetching
@BatchSize(size = 25)
@OneToMany(mappedBy = "parent")
private List<Child> children;

// 2. Use query hints
@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
List<Product> findAll();

// 3. Use fetch joins for N+1 problems
@Query("SELECT p FROM Product p JOIN FETCH p.category")
List<Product> findAllWithCategory();
```

### ❓ How do you handle soft deletes?
**✅ A:**
```java
@Entity
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Product {
    private boolean deleted = false;
}
```

### ❓ How do you implement audit trails?
**✅ A:**
```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AuditedEntity {
    @CreatedBy
    private String createdBy;
    
    @CreatedDate
    private Instant createdDate;
    
    @LastModifiedBy
    private String lastModifiedBy;
    
    @LastModifiedDate
    private Instant lastModifiedDate;
}
```

### ❓ How do you handle database-specific features?
**✅ A:**
```java
@Entity
public class Document {
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @Formula("(SELECT COUNT(c.id) FROM comments c WHERE c.doc_id = id)")
    private Long commentCount;
    
    @Index(columnList = "title,content", 
           using = "gin",  // PostgreSQL-specific
           options = @IndexOptions(postgresql = "WITH (fastupdate = off)"))
    private String searchableContent;
}
```

---

## ✅ Summary

This comprehensive guide covers all JPA annotations used to control persistence, relationship mapping, validation, and database behavior. Mastery of these annotations is essential for building robust, efficient, and maintainable data-driven applications.

---

## 📝 References

- [Jakarta Persistence](https://jakarta.ee/specifications/persistence/)  
- [Hibernate User Guide](https://docs.jboss.org/hibernate/orm/current/userguide/)  
- [Baeldung JPA Annotations](https://www.baeldung.com/jpa-annotations)
