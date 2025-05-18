# 💾 Java Memory Management – Deep Dive & Performance Guide

Efficient memory management in Java is critical for building high-performance, scalable, and reliable applications. This guide provides an expert-level overview of the JVM memory model, garbage collection strategies, diagnostics tools, tuning techniques, and interview-level insights.

---

## 🧠 1. JVM Memory Structure

### 🔹 Heap Memory

- **Young Generation (Young Gen)** – For short-lived objects
  - **Eden Space**: New allocations happen here
  - **Survivor Spaces (S0, S1)**: Surviving objects are moved here before promotion
- **Old Generation (Tenured)** – For long-lived objects

### 🔹 Stack Memory

- Stores method frames, local variables, and partial results.
- One stack per thread. Grows/shrinks with method calls.

### 🔹 Metaspace (Java 8+)

- Stores class metadata and static variables.
- Dynamically grows in native memory.

### 🔹 Others

- **Code Cache**: Compiled native code (JIT output)
- **Direct Memory**: NIO buffers using `Unsafe.allocateMemory()`

---

## 🚮 2. Garbage Collection (GC) Algorithms

| GC Algorithm     | Features                                   | Suitable For               |
|------------------|--------------------------------------------|-----------------------------|
| **Serial GC**     | Single-threaded, stop-the-world           | Small applications, VMs     |
| **Parallel GC**   | Multi-threaded, high throughput           | CPU-intensive apps          |
| **CMS GC**        | Low-pause, concurrent (deprecated)        | Latency-sensitive workloads |
| **G1 GC**         | Predictable pause times, region-based     | General-purpose (Java 11+)  |
| **ZGC**           | Ultra-low pause, scales to TBs of heap    | Large heaps, real-time      |
| **Shenandoah**    | Pause-less GC from Red Hat                | Responsive cloud-native apps|

### ✅ G1 GC in Action:
```bash
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:+UseStringDeduplication
```

---

## 🧪 3. GC Tuning & JVM Options

### Heap Sizing Flags
```bash
-Xms1g                   # Initial heap size
-Xmx4g                   # Maximum heap size
-XX:NewRatio=3           # Ratio between young and old generations
-XX:SurvivorRatio=6      # Eden to survivor space ratio
```

### GC Tuning for G1 Example:
```bash
-XX:+UseG1GC
-XX:MaxGCPauseMillis=100
-XX:InitiatingHeapOccupancyPercent=45
-XX:G1ReservePercent=20
```

### String Deduplication:
```bash
-XX:+UseStringDeduplication
```

### GC Logging (Java 9+ Unified Logging):
```bash
-Xlog:gc*:file=logs/gc.log:time,level,tags
```

---

## 🔍 4. JVM Monitoring & Profiling Tools

| Tool               | Feature Set                                     |
|--------------------|--------------------------------------------------|
| **VisualVM**        | Visual profiler, GC & heap viewer               |
| **jconsole**        | JMX monitoring (heap, threads, CPU)             |
| **jstat**           | Command-line GC/heap stats                      |
| **jmap**            | Dump heap and memory maps                       |
| **Eclipse MAT**     | Heap dump analysis for leak detection           |
| **JFR & JMC**       | Flight Recorder & Java Mission Control (Java 11+) |
| **GCEasy.io**       | GC log visual analyzer                          |

### ✅ Sample `jstat` Usage:
```bash
jstat -gc <PID> 1000 10
```

---

## 🛡️ 5. Memory Leak Prevention

- ✅ Always close streams/sockets in `try-with-resources` blocks
- ✅ Avoid static caches holding references indefinitely
- ✅ Use `WeakReference` for soft caching or listener registries
- ✅ Monitor for growing heap with profiling tools
- ✅ Detect leaks with heap dumps (`-XX:+HeapDumpOnOutOfMemoryError`)

### Sample Leak Code:
```java
List<byte[]> leakyList = new ArrayList<>();
while (true) {
    leakyList.add(new byte[1024 * 1024]); // adds 1MB chunks forever
}
```

---

## 🏗️ 6. Memory-Safe Design Patterns

- **Object Pooling**: Reuse expensive objects (e.g., DB connections)  
- **Immutable Data**: Avoid accidental object mutation and duplication  
- **Builder Pattern**: Avoid unnecessary interim objects in constructors  
- **Flyweight Pattern**: Share object instances (e.g., string interning)  

---

## 🌍 7. Real-World Tuning Scenarios

### 🚀 High Throughput System
```bash
-XX:+UseParallelGC
-Xms4g -Xmx4g
-XX:+UseAdaptiveSizePolicy
```

### ⚡ Low Latency Microservice
```bash
-XX:+UseZGC
-Xmx512m -Xms512m
-XX:ZUncommitDelay=300
```

### 🐋 Containerized JVM
```bash
-XX:+UseContainerSupport (Java 10+)
-Djava.security.egd=file:/dev/./urandom
```

---

## 🔬 8. Advanced Memory Management Topics

### Memory Barriers and Visibility
```java
public class MemoryBarrierExample {
    private volatile boolean flag = false;
    private int data = 0;

    public void writer() {
        data = 42;        // Store to data
        flag = true;      // Store with release semantics
    }

    public void reader() {
        while (!flag) {   // Load with acquire semantics
            Thread.onSpinWait(); // Java 9+ optimization
        }
        assert data == 42; // Will always see updated value
    }
}
```

### Direct ByteBuffer Management
```java
public class DirectBufferManager {
    private static final int BUFFER_SIZE = 1024 * 1024; // 1MB

    public static ByteBuffer allocateDirectBuffer() {
        try {
            return ByteBuffer.allocateDirect(BUFFER_SIZE);
        } catch (OutOfMemoryError e) {
            System.gc(); // Hint for cleaning direct buffers
            return ByteBuffer.allocateDirect(BUFFER_SIZE);
        }
    }

    public static void releaseDirectBuffer(ByteBuffer buffer) {
        if (buffer.isDirect()) {
            ((DirectBuffer) buffer).cleaner().clean();
        }
    }
}
```

### Custom Memory Pool Implementation
```java
public class MemoryPool<T> {
    private final Queue<T> pool;
    private final Supplier<T> factory;
    private final int maxSize;

    public MemoryPool(Supplier<T> factory, int maxSize) {
        this.pool = new ConcurrentLinkedQueue<>();
        this.factory = factory;
        this.maxSize = maxSize;
    }

    public T acquire() {
        T item = pool.poll();
        return item != null ? item : factory.get();
    }

    public void release(T item) {
        if (pool.size() < maxSize) {
            pool.offer(item);
        }
    }
}

// Usage
MemoryPool<ByteBuffer> bufferPool = new MemoryPool<>(
    () -> ByteBuffer.allocateDirect(1024),
    100
);
```

### Memory-Mapped Files
```java
public class MemoryMappedFileExample {
    public static void processLargeFile(String path) throws IOException {
        try (FileChannel channel = FileChannel.open(Path.of(path))) {
            MappedByteBuffer buffer = channel.map(
                FileChannel.MapMode.READ_WRITE, 
                0, 
                channel.size()
            );
            
            // Process file in memory
            while (buffer.hasRemaining()) {
                buffer.get(); // Read byte by byte
            }
            
            buffer.force(); // Flush changes to disk
        }
    }
}
```

---

## 🎯 9. Performance Optimization Patterns

### Thread-Local Allocation Buffers (TLAB)
```java
public class TLABOptimization {
    private static final ThreadLocal<byte[]> buffer = 
        ThreadLocal.withInitial(() -> new byte[1024]);

    public void process() {
        byte[] localBuffer = buffer.get();
        // Use local buffer for allocations
        // Avoids contention in Eden space
    }
}
```

### Escape Analysis Optimization
```java
public class EscapeAnalysis {
    // Object doesn't escape - will be allocated on stack
    public int sumArray(int size) {
        int[] array = new int[size];
        int sum = 0;
        for (int i = 0; i < size; i++) {
            array[i] = i;
            sum += array[i];
        }
        return sum;
    }
}
```

---

## 💼 Interview Questions & Answers

### ❓ What causes `OutOfMemoryError`?
**✅** Unreachable objects are not GCed due to lingering references, native leaks, excessive Metaspace, or large object allocations.

---

### ❓ What is GC thrashing and how do you fix it?
**✅** Continuous garbage collection with minimal heap recovery. Fix by:
- Increasing heap (`-Xmx`)
- Reducing object churn
- Switching to a concurrent GC like G1

---

### ❓ What are Soft, Weak, and Phantom references?

| Reference Type | Description                                |
|----------------|--------------------------------------------|
| **Soft**       | Cleared at memory pressure                 |
| **Weak**       | Cleared eagerly at next GC                 |
| **Phantom**    | Used for cleanup after finalization        |

---

### ❓ How to reduce GC pause time?
- Use `G1GC`, `ZGC`, or `Shenandoah`  
- Tune `MaxGCPauseMillis`  
- Avoid full GCs (e.g., by limiting object promotion)  
- Optimize object allocation rate  

---

### ❓ How does G1 GC improve over CMS?
**✅** Region-based GC with concurrent marking and predictable pause tuning. Handles fragmentation via incremental compaction.

---

### ❓ Difference between minor and major GC?
- **Minor GC**: Cleans Young Gen (fast, frequent)  
- **Major GC**: Cleans Old Gen (slower, stop-the-world)

---

### ❓ What is the purpose of the survivor spaces?
**✅** Allow short-lived objects to age before promotion to Old Gen, reducing tenuring pressure.

---

### ❓ How to profile memory usage in production?
- Enable JFR (Java Flight Recorder)  
- Use `jcmd` to dump heap / run diagnostics  
- Attach VisualVM or use Prometheus JMX exporter  

---

### ❓ How does Escape Analysis work in JVM?
**✅ A:** Escape analysis determines if an object:
```java
public class EscapeExample {
    // No Escape - optimized to stack
    public int noEscape() {
        Point p = new Point(1, 2);
        return p.x + p.y;
    }
    
    // Escapes to heap
    public Point methodEscape() {
        return new Point(1, 2);
    }
}
```

---

### ❓ How to handle Direct ByteBuffer out of memory issues?
**✅ A:**
```java
public class DirectBufferOOM {
    public static void handleOOM() {
        System.setProperty("io.netty.maxDirectMemory", "64MB");
        System.setProperty("io.netty.leakDetection.level", "PARANOID");
        
        try {
            // Force GC to clean direct buffers
            sun.misc.SharedSecrets.getJavaNioAccess()
                .truncate(BufferPool.class, "direct");
        } catch (Exception e) {
            System.gc();
        }
    }
}
```

---

### ❓ How to implement efficient caching with soft references?
**✅ A:**
```java
public class SoftCache<K, V> {
    private final Map<K, SoftReference<V>> cache = 
        new ConcurrentHashMap<>();
    
    public V get(K key, Supplier<V> loader) {
        SoftReference<V> ref = cache.get(key);
        V value = (ref != null) ? ref.get() : null;
        if (value == null) {
            value = loader.get();
            cache.put(key, new SoftReference<>(value));
        }
        return value;
    }
}
```

---

## ✅ Summary

Mastering Java memory management involves understanding the JVM memory model, garbage collection internals, proper tuning flags, and memory-safe design. Together with tools and metrics, these insights empower developers to build optimized, reliable applications.

---

## 📝 References

- [OpenJDK GC Tuning Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/)  
- [Java Flight Recorder](https://openjdk.org/projects/jmc/)  
- [Eclipse Memory Analyzer Tool](https://www.eclipse.org/mat/)  
- [GCEasy.io](https://gceasy.io) – GC log analysis
