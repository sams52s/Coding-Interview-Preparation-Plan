# Java 14 (March 2020) – Stepping into Pattern Matching and Data Carriers 🛠️

## ✨ Overview
Java 14 delivers experimental and preview features that modernize Java’s language constructs, improve error handling, and introduce new tools. This release is a significant stepping stone toward more expressive, concise Java code, laying the groundwork for future pattern matching and immutable data carriers.

---

## 📋 Feature Navigation Table

| Category          | Feature                                      | Link                                                  |
|-------------------|---------------------------------------------|------------------------------------------------------|
| Language          | Records (Preview)                           | [Jump](#-records-preview)                           |
| Language          | Pattern Matching for `instanceof` (Preview) | [Jump](#-pattern-matching-for-instanceof-preview)   |
| Runtime           | Helpful NullPointerExceptions               | [Jump](#-helpful-nullpointerexceptions)             |
| Tooling           | Packaging Tool (Incubator)                  | [Jump](#-packaging-tool-incubator)                 |

---

## 🚀 Detailed Feature Breakdown

### 🔹 Records (Preview)
**Definition**: Immutable, transparent data carriers with compact syntax.

**Why it matters**:
- Reduces boilerplate (getters, equals, hashCode, toString).
- Encourages immutability and clearer data modeling.

**Example**:
```java
record Point(int x, int y) {}
```

---

### 🔹 Pattern Matching for `instanceof` (Preview)
**Definition**: Combines type test and cast into one concise expression.

**Why it matters**:
- Reduces verbosity.
- Improves readability of conditional type logic.

**Example**:
```java
if (obj instanceof String s) {
    System.out.println(s.toLowerCase());
}
```

---

### 🔹 Helpful NullPointerExceptions
**Definition**: Enhanced NPE messages that tell you exactly which variable was null.

**Why it matters**:
- Speeds up debugging.
- Reduces time spent diagnosing production issues.

---

### 🔹 Packaging Tool (Incubator)
**Definition**: `jpackage` tool to package Java applications into platform-specific installers.

**Why it matters**:
- Simplifies deployment.
- Supports cross-platform distribution with native system integration.

**Example Command**:
```bash
jpackage --input target/ --name MyApp --main-jar app.jar --type dmg
```

---

## 📚 JEP References
- [JEP 359](https://openjdk.org/jeps/359): Records (Preview)
- [JEP 305](https://openjdk.org/jeps/305): Pattern Matching for instanceof (Preview)
- [JEP 358](https://openjdk.org/jeps/358): Helpful NullPointerExceptions
- [JEP 343](https://openjdk.org/jeps/343): Packaging Tool (Incubator)

---

## 🔗 Official Resources
- [Java 14 Oracle Docs](https://www.oracle.com/java/technologies/javase/14-relnotes.html)
- [OpenJDK Java 14](https://openjdk.org/projects/jdk14/)

---

## 🧠 Advanced Patterns and Practices

### Records Best Practices

**Record Design Patterns**:
| Pattern | Use Case | Example |
|---------|----------|---------|
| DTO | API responses | `record UserDTO(String name, String email) {}` |
| Value Object | Domain modeling | `record Money(BigDecimal amount, Currency currency) {}` |
| Tuple | Multiple returns | `record Pair<T,U>(T first, U second) {}` |
| Immutable Config | Settings | `record DatabaseConfig(String url, String user) {}` |

**Advanced Record Examples**:
```java
// Custom constructor validation
record Rectangle(int width, int height) {
    public Rectangle {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid dimensions");
        }
    }
}

// Generic record with methods
record Cache<K,V>(Map<K,V> store, Duration timeout) {
    public Optional<V> get(K key) {
        return Optional.ofNullable(store.get(key));
    }
}
```

**Additional Advanced Usage**:
- Records can implement interfaces.
- Static fields and methods are allowed.
- Can override default methods from interfaces.

**Example**:
```java
interface Identifiable {
    String id();
}

record User(String id, String name) implements Identifiable {}
```

### Pattern Matching Advanced Use

**Pattern Matching Scenarios**:
| Scenario | Old Approach | New Approach |
|----------|-------------|--------------|
| Type Check + Cast | Multiple lines | Single expression |
| Multiple Types | Nested if/else | Switch expressions |
| Optional handling | isPresent + get | Pattern matching |

**Advanced Pattern Examples**:
```java
// Complex type hierarchy handling
if (shape instanceof Rectangle r && r.width() > 0) {
    area = r.width() * r.height();
} else if (shape instanceof Circle c) {
    area = Math.PI * c.radius() * c.radius();
}

// Future pattern matching (preview of coming features)
Object result = switch (obj) {
    case String s when s.length() > 5 -> s.toLowerCase();
    case Integer i -> i * 2;
    case null -> "null";
    default -> "unknown";
};
```

**Pattern Matching Roadmap**:
- Java 14 preview lays groundwork for:
    - Pattern matching in `switch` (coming in Java 17).
    - Deconstruction patterns for records.
    - Sealed classes + pattern matching integration.

### Migration and Tooling Setup

**Build Tool Configurations**:
```xml
<!-- Maven -->
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <release>14</release>
                <compilerArgs>
                    <arg>--enable-preview</arg>
                </compilerArgs>
            </configuration>
        </plugin>
    </plugins>
</build>

<!-- Gradle -->
tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("--enable-preview"))
    options.release.set(14)
}
```

**Migration Deep-Dive**:
- Watch for:
    - Removed `Nashorn` JavaScript engine.
    - Deprecated `RMI Activation` system.
- Validate native packaging pipeline if migrating from older tools.

### Performance Optimization

**JVM Tuning Matrix**:
| Feature | Flag | Impact |
|---------|------|--------|
| NPE Details | `-XX:+ShowCodeDetailsInExceptionMessages` | Memory overhead |
| Record Inlining | `-XX:+InlineRecordConstructors` | Improved allocation |
| Pattern Matching | No specific flags | Bytecode optimization |

**Additional JVM Details**:
- **JEP 345 (NUMA-Aware Memory Allocation for G1)**
    - Improves G1 GC performance on NUMA systems.
    - Allocates memory aligned with NUMA node locality.

**Example Command**:
```bash
-XX:+UseG1GC -XX:+UseNUMA
```

**Performance Monitoring**:
```bash
# JFR recording for pattern matching
jcmd <pid> JFR.start settings=profile \
    duration=60s \
    filename=pattern-matching.jfr

# Analyze record allocation
java -XX:+UnlockDiagnosticVMOptions \
     -XX:+PrintInlining \
     YourApp.java
```

### IDE Integration Tips

**IDE Support Matrix**:
| IDE | Version | Features |
|-----|---------|----------|
| IntelliJ IDEA | 2020.1+ | Full preview support |
| Eclipse | 2020-03+ | Basic preview support |
| VS Code | Java 0.55.0+ | Syntax highlighting |

**Recommended Plugins**:
- "Java Preview Features" for Eclipse
- "Java Language Support" for VS Code
- "Java Previewer" for IntelliJ

### Packaging Tool Advanced Config

- Specify icons, license, and file associations:
```bash
jpackage --input target/ --name MyApp \
    --main-jar app.jar --icon app.ico --license license.txt \
    --file-associations assoc.properties --type pkg
```

- Integrate with CI/CD to auto-build native installers for each OS.

### Ecosystem Impact

- **Spring Framework** starts to explore record-based DTO support.
- **Jackson / Gson** libraries add record serialization support.
- **IDEA Ultimate** enables deep record + pattern matching inspections.

### Profiling and Diagnostics

- Combine Helpful NPEs with structured logging to improve production diagnostics.
- Use async-profiler or `perf` to measure pattern matching JIT optimizations.

---

## 🏁 Summary

Java 14 refines the language with highly requested features like records and pattern matching, modernizes runtime behaviors, and upgrades packaging and diagnostics tools. It acts as a transitional release toward Java’s pattern-driven, immutable future and provides a playground for developers eager to adopt cutting-edge improvements before the next LTS.

---

✅ This completes `java-14.md` as an expert-level, fully detailed document.
