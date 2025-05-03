# 🧠 Java Memory Management & Garbage Collection

Memory management in Java is primarily handled by the **Java Virtual Machine (JVM)** through **automatic garbage collection**. It ensures efficient use of memory by allocating and reclaiming memory dynamically during application execution.

---

## 🧱 1. JVM Memory Areas (Runtime Data Areas)

### 🔹 1. Heap
- Stores **objects** and **instance variables**
- Shared among all threads
- Managed by the Garbage Collector

### 🔹 2. Stack
- Stores **method call frames**, **local variables**, and **partial results**
- Each thread has its own stack
- Memory is freed when method execution ends

### 🔹 3. Method Area (MetaSpace in Java 8+)
- Stores **class metadata**, **static fields**, **method definitions**
- Shared area used by all classes

### 🔹 4. Program Counter (PC) Register
- Stores the address of the currently executing bytecode instruction per thread

### 🔹 5. Native Method Stack
- Supports execution of **native (non-Java)** methods via JNI

---

## ♻️ 2. What is Garbage Collection (GC)?

Garbage Collection is the process of **automatically identifying and removing unused objects** to free up memory.

> Java programmers do not need to manually deallocate memory, unlike C/C++.

---

## ⚙️ 3. Phases of Garbage Collection

1. **Mark** — Identifies live (reachable) objects
2. **Sweep/Delete** — Clears unreferenced objects from memory
3. **Compact** — Moves live objects together to eliminate fragmentation

---

## 🔁 4. Generational Garbage Collection (Heap Division)

| Generation      | Purpose                                  |
|-----------------|-------------------------------------------|
| **Young Gen**   | Newly created objects; frequent collection|
| **Old Gen**     | Long-lived objects; less frequent         |
| **Survivor Spaces (S0, S1)** | Used to move objects from Eden to Old Gen

> ✅ Short-lived objects stay in **Young Gen**, long-lived objects are promoted to **Old Gen**.

---

## 🧪 5. Garbage Collectors in Java

| GC Name                 | Type         | Description                          |
|--------------------------|--------------|--------------------------------------|
| Serial GC                | Stop-the-world | Best for single-threaded apps        |
| Parallel GC (default ≤8) | Multi-threaded | Good throughput for multi-core CPUs  |
| CMS (deprecated)         | Concurrent   | Low pause times, more complex        |
| G1 GC (default ≥9)       | Concurrent   | Region-based, predictable pause times|
| ZGC (Java 11+)           | Low-latency  | Scalable, low-pause, large heaps     |
| Shenandoah (Java 12+)    | Low-latency  | Predictable pause for ultra low-latency apps |

### Set GC with JVM flag:
```bash
java -XX:+UseG1GC -jar MyApp.jar
```

---

## 🔍 6. Common JVM Memory Flags

| Flag                            | Purpose                                 |
|----------------------------------|-----------------------------------------|
| `-Xms<size>`                    | Initial heap size                        |
| `-Xmx<size>`                    | Maximum heap size                        |
| `-Xss<size>`                    | Stack size per thread                    |
| `-XX:+UseG1GC`                  | Enable G1 Garbage Collector              |
| `-XX:+PrintGCDetails`          | Print detailed GC logs                   |
| `-XX:+UseStringDeduplication`  | Enable string deduplication in G1        |

---

## 📘 7. Best Practices

- Minimize object creation inside loops
- Avoid memory leaks by clearing unused references (e.g., in collections)
- Use weak references (`WeakReference`, `SoftReference`) for cache
- Monitor memory with tools like **JVisualVM**, **Java Mission Control**, **JFR**
- Profile GC logs to tune memory and GC performance
- Close resources properly using try-with-resources
- Consider object pooling for expensive object creation
- Use immutable objects where possible to reduce memory churn

---

## 📚 8. Advanced Memory Management Topics

### 🔹 Reference Types in Java

| Reference Type | Garbage Collection Behavior | Common Use Cases |
|----------------|----------------------------|-----------------|
| **Strong** | Default; never collected if reachable | Regular object references |
| **Weak** (`WeakReference`) | Collected in next GC cycle if only weakly reachable | Caching, preventing memory leaks |
| **Soft** (`SoftReference`) | Collected only when memory is low | Memory-sensitive caches |
| **Phantom** (`PhantomReference`) | Allows cleanup before memory reclaimed | Pre-mortem cleanup actions |

### 🔹 Off-Heap Memory Management

Off-heap memory is managed outside the JVM heap:
- DirectByteBuffer for native I/O operations
- Memory-mapped files for large datasets
- Native memory access via JNI/JNA/Panama
- Control with `-XX:MaxDirectMemorySize`

### 🔹 Escape Analysis & Optimization

JVM's escape analysis determines if:
- Objects can be **stack-allocated** (faster than heap)
- Object **allocation can be eliminated** completely
- **Lock elision** can be performed for synchronized blocks

### 🔹 Common Memory Leak Patterns

1. **Static Collections/Fields** - Objects stored in static fields never get collected
2. **Unclosed Resources** - Streams, connections not closed properly
3. **Improper equals/hashCode** in collections - Objects can't be found/removed
4. **Inner Class References** - Non-static inner classes hold reference to outer class
5. **ThreadLocal variables** - Not removed after thread termination

### 🔹 Advanced GC Tuning

| Tuning Goal | Relevant Flags |
|-------------|----------------|
| Consistent throughput | `-XX:GCTimeRatio`, `-XX:MaxGCPauseMillis` |
| Large heap support | `-XX:G1HeapRegionSize`, `-XX:+UseCompressedOops` |
| Metaspace tuning | `-XX:MetaspaceSize`, `-XX:MaxMetaspaceSize` |
| GC logging | `-Xlog:gc*` (Java 9+), `-XX:+UseGCLogFileRotation` |
| String deduplication | `-XX:+UseStringDeduplication` (G1 GC) |

---

## 🧠 Interview Questions & Answers

- ❓ **What is the role of the garbage collector in Java?**
  > Automatically reclaims memory used by unreachable objects.

- ❓ **How does JVM determine if an object is unreachable?**
  > By checking reference reachability from GC roots.

- ❓ **What are GC roots in Java?**
  > Static variables, local variables, thread stacks, and JNI references.

- ❓ **What is the difference between Young and Old generation in heap?**
  > Young Gen holds short-lived objects; Old Gen stores objects that survived multiple GC cycles.

- ❓ **What are some JVM flags used for tuning memory?**
  > `-Xms`, `-Xmx`, `-Xss`, `-XX:+UseG1GC`, etc.

- ❓ **How does G1 GC differ from CMS?**
  > G1 uses regions and incremental compaction; CMS is concurrent but causes fragmentation.

- ❓ **What happens if the JVM runs out of heap memory?**
  > It throws an `OutOfMemoryError`.

- ❓ **Can you manually trigger garbage collection?**
  > Yes, using `System.gc()`, but it’s only a suggestion.

- ❓ **What are memory leaks in Java?**
  > When references are held unnecessarily, preventing GC from reclaiming memory.

- ❓ **How to analyze memory usage in Java?**
  > Use profiling tools like VisualVM, JConsole, Eclipse MAT.

- ❓ **What's the difference between Minor GC and Full GC?**
  > Minor GC collects the Young Generation only, while Full GC collects both Young and Old Generation, typically causing longer pause times.

- ❓ **Explain the four reference types in Java.**
  > **Strong**: Normal references that prevent GC; **Soft**: Cleared when memory is low; **Weak**: Cleared in next GC cycle; **Phantom**: For pre-reclamation cleanup.

- ❓ **What is 'stop-the-world' in garbage collection?**
  > A pause in application execution when the JVM stops all threads to perform certain GC operations, affecting application responsiveness.

- ❓ **What's the purpose of the Metaspace in Java 8+?**
  > It replaced PermGen and stores class metadata. Unlike PermGen, it can dynamically resize and isn't part of the heap.

- ❓ **How does the String pool work in Java?**
  > String literals are stored in a special memory area called String pool. `String.intern()` method ensures strings with the same content share storage.

- ❓ **What causes java.lang.OutOfMemoryError: GC Overhead Limit Exceeded?**
  > It occurs when the GC is spending excessive time (default >98%) collecting with minimal heap reclaimed (default <2%).

- ❓ **What is escape analysis in JVM?**
  > An optimization technique where JVM determines if objects can be allocated on stack instead of heap, or eliminated entirely.

- ❓ **How would you troubleshoot a memory leak in production?**
  > 1. Enable GC logging, 2. Take heap dumps using jmap, 3. Analyze with tools like MAT/YourKit, 4. Look for growing collections/caches, 5. Check for resource leaks.

- ❓ **What is the Concurrent Mark Sweep (CMS) collector and why was it deprecated?**
  > CMS is a low-pause collector that does most work concurrently. It was deprecated because it causes heap fragmentation, has high CPU usage, and lacks modern features compared to G1.

- ❓ **How does G1 garbage collector work?**
  > G1 divides the heap into equal-sized regions, works incrementally, prioritizes high-garbage regions ("Garbage First"), and provides more predictable pause times through evacuation pauses.

- ❓ **What is the purpose of the -XX:MaxDirectMemorySize parameter?**
  > It controls the maximum amount of off-heap direct memory that can be allocated by the JVM for operations like NIO direct buffers.

- ❓ **How are large objects handled in modern garbage collectors?**
  > Large objects may be allocated directly in the Old Generation (or in Humongous Regions in G1) to avoid costly young-to-old promotions.

- ❓ **What mechanisms replaced the deprecated Object.finalize() method?**
  > Cleaner and PhantomReference in Java 9+ provide more reliable resource cleanup without the issues of finalization.

- ❓ **What is JVM ergonomics?**
  > The JVM's ability to self-tune based on the underlying hardware and observed application behavior, automatically adjusting heap sizes and GC parameters.