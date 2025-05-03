# ⚖️ Comparable vs Comparator in Java

## 📌 Overview
In Java, `Comparable` and `Comparator` are two interfaces provided to allow objects to be compared and sorted. They are part of the `java.lang` and `java.util` packages respectively. Understanding the differences and use cases of these interfaces is critical when working with sorting and ordering of objects.

---

## 🧩 Comparable Interface

### ✅ Purpose
Defines the **natural ordering** of objects.

### 🛠️ Declaration
```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

### 📦 Package
`java.lang`

### 🔍 Key Point
The class itself **implements Comparable** and defines the logic of comparison.

### 🧪 Example
```java
class Student implements Comparable<Student> {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int compareTo(Student s) {
        return this.id - s.id;
    }
}
```

### ⚠️ Comparable Contract
- `x.compareTo(y)` should return negative if x < y
- `x.compareTo(y)` should return 0 if x equals y
- `x.compareTo(y)` should return positive if x > y
- `x.compareTo(y)` should throw `NullPointerException` if y is null
- The relation should be transitive: if x.compareTo(y) > 0 && y.compareTo(z) > 0 then x.compareTo(z) > 0

---

## 🧩 Comparator Interface

### ✅ Purpose
Defines **custom orderings** externally.

### 🛠️ Declaration
```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

### 📦 Package
`java.util`

### 🔍 Key Point
Separate class or lambda provides **flexibility** for multiple sorting criteria.

### 🧪 Example
```java
class NameComparator implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.name.compareTo(b.name);
    }
}
```

### ⚠️ Comparator Contract
- `compare(x, y)` should return negative if x < y
- `compare(x, y)` should return 0 if x equals y
- `compare(x, y)` should return positive if x > y
- The relation should be transitive: if compare(x,y) > 0 && compare(y,z) > 0 then compare(x,z) > 0
- The relation should be antisymmetric: compare(x,y) == -(compare(y,x))

---

## 🧠 When to Use What?
| Feature | Comparable | Comparator |
|--------|------------|------------|
| Sorting logic location | Inside the class | Outside the class |
| Flexibility | Single order only | Multiple orders possible |
| Code coupling | Tight | Loose |
| Java Package | `java.lang` | `java.util` |
| Functional Interface | No | Yes (Java 8+) |
| When to use | Primary, natural order | Alternative, custom orders |
| Control over class | Required | Not required |

---

## 🚀 Advanced Comparator Techniques

### 🔄 Immutable Comparators
```java
// Create reusable, immutable comparators
public static final Comparator<Student> BY_ID = Comparator.comparing(Student::getId);
public static final Comparator<Student> BY_NAME = Comparator.comparing(Student::getName);
```

### 🧾 Null-Safe Comparisons
```java
// Handle null values properly
Comparator<Student> nullSafeNameComparator = Comparator.nullsFirst(
    Comparator.comparing(Student::getName, Comparator.nullsFirst(String::compareTo))
);
```

### 🔍 Natural Order Comparator
```java
// Use natural ordering
Comparator<String> naturalOrder = Comparator.naturalOrder();
```

### 🔄 Case Insensitive Comparison
```java
Comparator<String> caseInsensitive = String.CASE_INSENSITIVE_ORDER;
// or
Comparator<Student> byNameIgnoreCase = 
    Comparator.comparing(s -> s.getName().toLowerCase());
```

### 📊 Numeric Comparison Optimizations
```java
// Avoid potential integer overflow issues with subtraction
@Override
public int compareTo(Student other) {
    // Instead of: return this.id - other.id;
    return Integer.compare(this.id, other.id);
}
```

---

## 🧪 Java 8 Comparator Enhancements

### 🧾 Lambda-based Comparison
```java
Comparator<Student> byName = (s1, s2) -> s1.name.compareTo(s2.name);
```

### 🧾 Method Reference
```java
Comparator<Student> byName = Comparator.comparing(Student::getName);
```

### 🧾 Reversed Comparator
```java
Comparator<Student> byIdDesc = Comparator.comparing(Student::getId).reversed();
```

### 🧾 Chained Comparators
```java
Comparator<Student> comp = Comparator
    .comparing(Student::getName)
    .thenComparing(Student::getId);
```

### 🧾 Complex Chaining Example
```java
students.sort(
    Comparator.comparing(Student::getGraduationYear)
        .thenComparing(Student::getGpa, Comparator.reverseOrder())
        .thenComparing(Student::getName)
        .thenComparingInt(Student::getId)
);
```

---

## 🔧 Performance Considerations

### 💡 Time Complexity
- Collections.sort() has O(n log n) time complexity
- Comparator operations themselves should be kept efficient
- Avoid expensive operations inside compare methods

### 💾 Memory Impact
- Lambda-based comparators generate anonymous classes
- Multiple chained comparators may increase memory usage slightly
- Reuse predefined comparators when possible

### 🔄 Stability
- `Collections.sort()` guarantees stability (equal elements maintain relative order)
- `Arrays.parallelSort()` does not guarantee stability

---

## 🚫 Common Pitfalls
- Using `==` instead of `.compareTo()` or `.equals()`
- Not overriding `compareTo()` properly in `Comparable`
- Forgetting `null` checks in `Comparator`
- Returning non-consistent comparison results leading to `IllegalArgumentException`
- Integer overflow when using subtraction for comparing integers
- Breaking the contract of compareTo() or compare() methods
- Not considering the impact on hash-based collections

---

## 📝 Best Practices
- Follow the contracts of Comparable and Comparator interfaces
- Use static final comparators for reuse
- Prefer method references and Comparator factory methods over lambdas when possible
- Handle nulls explicitly with nullsFirst() or nullsLast()
- Ensure consistency with equals() when implementing Comparable
- Use Integer.compare(), Boolean.compare(), etc. instead of subtraction
- Document your comparison logic clearly

---

## 🚀 Real-World Scenarios
- **Employee Records**: Sort by name, then salary (Comparator chaining)
- **Student Portal**: Natural order by roll number (Comparable), or custom order by GPA, name, etc. (Comparator)
- **Leaderboard**: Descending sort using `reversed()`
- **Product Catalog**: Multiple sort options by price, rating, popularity
- **File System**: Sort files by date, size, or name
- **Database Query Results**: Custom sorting of complex domain objects
- **Event Scheduling**: Sort events by priority, then timestamp

---

## 📘 Code Example: Sort List with Comparator
```java
List<Student> students = new ArrayList<>();
students.add(new Student(1, "Asha"));
students.add(new Student(2, "Kabir"));

// Sort by name
students.sort(Comparator.comparing(Student::getName));

// Sort by multiple criteria
students.sort(
    Comparator.comparing(Student::getGradeLevel)
             .thenComparing(Student::getGpa, Comparator.reverseOrder())
);
```

## 🔄 Working with Collections and Arrays
```java
// With TreeSet (automatically sorted)
TreeSet<Student> studentSet = new TreeSet<>(Comparator.comparing(Student::getName));
studentSet.add(new Student(1, "Asha"));

// With Arrays
Student[] studentArray = {new Student(1, "Asha"), new Student(2, "Kabir")};
Arrays.sort(studentArray, Comparator.comparing(Student::getName));

// With priority queue
PriorityQueue<Student> studentQueue = new PriorityQueue<>(
    Comparator.comparing(Student::getGpa).reversed()
);
```

---

## 🧠 Interview Questions & Answers

### Q1. Why use Comparator if Comparable is already available?
**A:** Comparator allows custom, flexible ordering without modifying the original class. It's useful when:
- You can't modify the class (e.g., third-party libraries)
- You need multiple different orderings
- You want to separate comparison logic from business logic

### Q2. Can a class implement both Comparable and use Comparator?
**A:** Yes. Comparable defines natural ordering; Comparator allows custom sorting. This is common practice for classes that have a clear natural order but may need alternative sorting options.

### Q3. How do you sort in descending order using Comparator?
**A:** Use `reversed()` method:
```java
Comparator.comparing(Student::getName).reversed();
```

### Q4. What are the risks of inconsistent comparison logic?
**A:** Can lead to incorrect sorting or `IllegalArgumentException` in sorted collections like TreeSet. May also break the equals-compareTo contract, causing unpredictable behavior in collections.

### Q5. What happens if `compareTo()` returns inconsistent results?
**A:** You may violate the contract of `Comparable`, breaking TreeMap, TreeSet, and sort behavior. This can cause elements to be lost in collections or incorrect sorting results.

### Q6. Explain the relationship between equals() and compareTo()?
**A:** They should be consistent: if x.equals(y) is true, then x.compareTo(y) should return 0, and vice versa. If not, collections like TreeSet may behave unexpectedly, where an object might not be found even when present.

### Q7. How would you handle null values in a Comparator?
**A:** Use the nullsFirst() or nullsLast() methods:
```java
Comparator<Student> nullSafeComparator = Comparator.nullsFirst(
    Comparator.comparing(Student::getName)
);
```

### Q8. What's the difference between Comparator.comparing() and Comparator.thenComparing()?
**A:** 
- `comparing()` creates a new Comparator based on a key extraction function
- `thenComparing()` is used for chaining additional comparison criteria when the primary criteria result in equality

### Q9. How do you implement a case-insensitive string comparison?
**A:** Several approaches:
```java
// 1. Use String.CASE_INSENSITIVE_ORDER
Comparator<String> caseInsensitive1 = String.CASE_INSENSITIVE_ORDER;

// 2. Use String.compareToIgnoreCase() with method reference
Comparator<String> caseInsensitive2 = String::compareToIgnoreCase;

// 3. Transform to lowercase/uppercase before comparing
Comparator<String> caseInsensitive3 = Comparator.comparing(String::toLowerCase);
```

### Q10. How does parallelSort() work with Comparator and what are its implications?
**A:** Arrays.parallelSort() uses the Fork/Join framework to parallelize the sorting process. It may provide better performance for large arrays on multi-core systems but doesn't guarantee stability (equal elements may be reordered). You should only use it when stability isn't required and the array is large enough to benefit from parallelization.

### Q11. What's the difference between compareTo returning -1, 0, or 1 versus any negative, zero, or positive number?
**A:** The contract only requires negative, zero, or positive values—not specifically -1, 0, or 1. However, returning exactly -1, 0, or 1 can avoid integer overflow issues that might occur with subtraction. For large integers, prefer Integer.compare(a, b) over (a - b).

### Q12. Can Comparators be serialized?
**A:** Yes, Comparators can be serialized if they don't capture references to non-serializable objects. Lambda-based comparators are serializable if they don't capture non-serializable variables. It's best practice to make custom Comparator implementations implement Serializable if they'll be used with serializable collections.

### Q13. How would you create a Comparator for a complex object with nested properties?
**A:** You can compose key extractors:
```java
Comparator<Department> byManagerNameThenSize = 
    Comparator.comparing(dept -> dept.getManager().getName())
              .thenComparingInt(Department::getEmployeeCount);
```

---

## ✅ Summary
- Use `Comparable` when natural order makes sense and is part of the object.
- Use `Comparator` for custom, reusable, and flexible orderings.
- Java 8+ makes Comparators more powerful via lambdas and method references.
- Always follow the contract of comparison methods to ensure consistent behavior.
- Leverage the rich Comparator API for complex sorting requirements.
- Consider performance, null handling, and consistency with equals() when implementing comparison logic.

> 🔗 Next: [Collections Utility Class](./10_Collections_Utility_Class.md)
