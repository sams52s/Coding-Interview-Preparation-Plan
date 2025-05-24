# Java 10 (March 2018) – Type Inference & Performance Boosts ⚡

## ✨ Overview
Java 10 introduced local variable type inference, reducing verbosity and aligning Java closer to modern language syntaxes like Kotlin or Scala. It also focused on performance enhancements, especially in JVM startup, garbage collection, and security.

**Version Highlights**:
| Feature Category | Enhancement | Impact Score |
|-----------------|-------------|--------------|
| Syntax | Type Inference | High |
| Performance | AppCDS | Medium |
| Memory | G1 GC Updates | High |
| Security | Root Certificates | Medium |

**Technical Requirements**:
```java
// Minimum System Requirements
JDK 10+
Memory: 2GB+ recommended
Disk: 1GB+ for full SDK
```

---

## 📋 Feature Navigation Table

| Category              | Feature                                 | Link                                          |
|-----------------------|----------------------------------------|-----------------------------------------------|
| Language Enhancement  | Local Variable Type Inference (`var`)   | [Jump to section](#-local-variable-type-inference-var) |
| Performance           | Application Class-Data Sharing (AppCDS) | [Jump to section](#-application-class-data-sharing-appcds) |
| Security             | Root Certificates                       | [Jump to section](#-root-certificates) |
| Performance           | Parallel Full GC for G1                 | [Jump to section](#-parallel-full-gc-for-g1) |

---

## 🚀 Detailed Feature Breakdown

### 🔹 Local Variable Type Inference (`var`)
**Definition**: Allows developers to use `var` for local variable declarations where the type can be inferred.

**Type Inference Comparison Table**:
| Scenario | Without var | With var | Recommendation |
|----------|-------------|----------|----------------|
| Simple Types | `String name = "John"` | `var name = "John"` | ✅ Use var |
| Complex Generics | `Map<String, List<Integer>> map = new HashMap<>()` | `var map = new HashMap<String, List<Integer>>()` | ✅ Use var |
| Lambda Expressions | `Predicate<String> pred = s -> s.isEmpty()` | `var pred = (Predicate<String>) s -> s.isEmpty()` | ⚠ Avoid var |
| Arrays | `int[] numbers = new int[10]` | `var numbers = new int[10]` | ⚠ Use explicit |

**Advanced Examples**:
```java
// Collection with complex generics
var complexMap = new HashMap<String, List<Map<Integer, Set<String>>>>();

// Try-with-resources
var items = new ArrayList<String>();
try (var reader = new BufferedReader(new FileReader("data.txt"));
     var writer = new BufferedWriter(new FileWriter("output.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        items.add(line);
    }
}

// Stream operations
var result = Stream.of(1, 2, 3, 4)
    .map(num -> num * 2)
    .collect(Collectors.groupingBy(
        num -> num % 2 == 0 ? "even" : "odd",
        Collectors.summarizingInt(Integer::intValue)
    ));

// Generic method with var
public <T extends Comparable<T>> void sortAndPrint(List<T> list) {
    var sortedCopy = new ArrayList<>(list);
    Collections.sort(sortedCopy);
    var formatter = new Formatter();
    sortedCopy.forEach(item -> formatter.format("%s\n", item));
}
```

**Var Usage Guidelines**:
| Context | Use Var? | Explanation |
|---------|----------|-------------|
| Local Variables | ✅ | When type is clear from initialization |
| Loop Variables | ✅ | For enhanced for-loops |
| Lambda Parameters | ❌ | Not supported |
| Method Parameters | ❌ | Not supported |
| Fields | ❌ | Not supported |
| Return Types | ❌ | Not supported |

**Common Pitfalls and Solutions**:
```java
// ❌ Bad: Type not obvious
var result = someMethod();

// ✅ Good: Type clear from context
var userList = getUserList();

// ❌ Bad: Loses type information
var obj = "test".getClass().cast(new Object());

// ✅ Good: Explicit type when needed
Class<?> cls = "test".getClass();
var obj = cls.cast(new Object());

// ❌ Bad: Complex lambda
var processor = (Function<String, List<Integer>> & Serializable) s -> 
    Arrays.asList(s.length());

// ✅ Good: Explicit type for complex lambda
Function<String, List<Integer>> processor = s -> Arrays.asList(s.length());
```

---

### 🔹 Application Class-Data Sharing (AppCDS)
**Definition**: Extends Class-Data Sharing (CDS) to allow application classes to be placed in the shared archive.

**Why it matters**:
- Improves startup time.
- Reduces memory footprint.
- Benefits cloud deployments and microservices.

**JVM Internals and AppCDS**:
AppCDS enhances ahead-of-time class data sharing by allowing application classes, not just core JDK classes, to be pre-processed into a shared archive. This reduces class loading and initialization overhead during JVM startup.

AppCDS requires building a shared archive file through profiling runs that capture hot class usage patterns, ensuring the archive is optimized for the application's typical runtime behavior.

**Expert Tip**:
```bash
java -Xshare:dump -XX:SharedArchiveFile=app-cds.jsa -cp app.jar MyMain
```
Use profiling runs to capture hot class usage patterns before creating the archive.

**Example Command**:
```bash
java -Xshare:dump -XX:SharedArchiveFile=app-cds.jsa -cp myapp.jar MyMainClass
java -XX:SharedArchiveFile=app-cds.jsa -cp myapp.jar MyMainClass
```

---

### 🔹 Root Certificates
**Definition**: Ships OpenJDK builds with a default set of root Certification Authority certificates.

**Why it matters**:
- Improves out-of-the-box HTTPS and TLS support.
- Reduces the need for manual certificate management.

**Security Considerations with Root Certificates**:
It is advisable to verify the imported CA roots to ensure compliance and trustworthiness:

```bash
keytool -list -keystore $JAVA_HOME/lib/security/cacerts
```

---

### 🔹 Parallel Full GC for G1
**Definition**: Parallelizes the full garbage collection process for the G1 garbage collector.

**Why it matters**:
- Improves GC pause performance.
- Makes G1 more attractive as a general-purpose collector.

**Advanced G1 GC Tuning**:
- `-XX:MaxGCPauseMillis` sets a target for maximum GC pause times, helping tune G1 for latency-sensitive applications.
- `-XX:+ParallelRefProcEnabled` enables parallel processing of reference objects during GC, reducing pause durations.

**Tuning Example**:
```bash
-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+ParallelRefProcEnabled
```

**Advanced Tuning Example**:
```bash
-XX:+UseG1GC -XX:+ParallelRefProcEnabled
```

---

## 📚 JEP References
- [JEP 286](https://openjdk.org/jeps/286): Local-Variable Type Inference
- [JEP 310](https://openjdk.org/jeps/310): Application Class-Data Sharing
- [JEP 319](https://openjdk.org/jeps/319): Root Certificates
- [JEP 307](https://openjdk.org/jeps/307): Parallel Full GC for G1

---

## 🔗 Official Resources
- [Java 10 Oracle Docs](https://www.oracle.com/java/technologies/javase/10-relnotes.html)
- [OpenJDK Java 10](https://openjdk.org/projects/jdk10/)

---

## 🧠 Advanced Patterns and Practices

### Advanced `var` Usage
- Leverage type inference in `try-with-resources`.
- Combine with `Stream` and collectors.
- Avoid using `var` with unclear lambda types.

**Example**:
```java
try (var stream = Files.lines(Path.of("file.txt"))) {
    stream.forEach(System.out::println);
}
```

---

### Migration Tips (Java 9 → 10)
- Audit codebases to selectively adopt `var` where clarity improves.
- Use AppCDS to optimize microservices and cloud apps.
- Validate GC settings if moving from CMS or Parallel to G1.

---

## 🌍 Ecosystem Integration
- **Frameworks**: Minimal impact; mostly internal toolchain updates.
- **Tooling**: IDEs like IntelliJ and Eclipse added `var` support, including on-hover type hints and quick fixes for `var` usage.
- **Build Tools**: Maven and Gradle enhanced support for `var`-based compilation, ensuring smooth integration.
- **Libraries**: Remain compatible, no major API changes.

---

## 🚀 Performance Guidelines
- Profile startup improvements with AppCDS.
- Enable G1 parallel full GC on large heaps.
- Keep using explicit types when `var` hurts readability.

**Performance Profiling Tools**:
- Use `jcmd` for live JVM diagnostics and monitoring.
- Employ `jvisualvm` or `Java Mission Control` to observe AppCDS usage and GC behavior in real time.

---

## 🏁 Summary

Java 10 balanced language evolution (with `var`) and runtime optimizations (with AppCDS and G1 improvements). For enterprise applications, it provided crucial JVM tunings, while for developers, it introduced syntax changes that modernized Java’s expressiveness. Mastering Java 10 means understanding not just its syntax but its runtime footprint and how to leverage the JVM’s full potential.

---

✅ This upgrade turns `java-10.md` into a super advanced, developer-focused, expert-level reference.
