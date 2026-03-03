# JVM Architecture and GC Tuning

Understanding the Java Virtual Machine (JVM) internals and Garbage Collection (GC) is a distinguishing skill for Senior Java Engineers.

## 1. JVM Memory Model Anatomy

The JVM divides memory into several distinct areas:

1. **Heap**: Where all Object instances and arrays are allocated. It is shared across all threads and is the primary area managed by the Garbage Collector.
2. **Metaspace** (Replaced PermGen in Java 8): Native memory used to store class metadata, constant pool information, and static variables. It grows automatically by default.
3. **Thread Stacks**: Each thread has its own stack, storing local variables, method call histories, and partial results. Not garbage collected (freed when the method returns).
4. **Program Counter (PC) Register**: Tracks the current execution instruction for a thread.
5. **Native Method Stack**: Used for methods written in languages other than Java (e.g., C/C++ via JNI).

## 2. Generational Garbage Collection

The Heap is traditionally divided into "Generations" based on the "Weak Generational Hypothesis": *Most objects die young.*

### Inside the Heap:
- **Young Generation**: Where newly created objects are placed. Divided into:
  - **Eden Space**: Initial allocation area.
  - **Survivor Spaces (S0 and S1)**: Objects that survive a garbage collection in Eden are moved here. Only one is used at a time.
- **Old (Tenured) Generation**: Objects that survive multiple garbage collection cycles in the Young Generation are promoted here. Long-lived application state lives here.

### Types of GC Events:
- **Minor GC**: Cleans the Young Generation. Fast and occurs frequently. "Stops the world" (pauses application threads) but usually very briefly.
- **Major / Full GC**: Cleans the Old Generation (and usually the Young Gen too). Inherently slower and involves much longer "Stop the world" pauses. **A primary goal of GC tuning is minimizing Full GCs.**

## 3. Garbage Collectors in Java

Java provides different GC algorithms optimized for different use cases (Throughput vs. Low Latency).

1. **Serial GC** (`-XX:+UseSerialGC`): Single-threaded. For small applications.
2. **Parallel GC** (`-XX:+UseParallelGC`): Default in Java 8. Uses multiple threads for GC. Optimizes for maximum overall throughput, but full GC pauses can be noticeable.
3. **G1 GC (Garbage-First)** (`-XX:+UseG1GC`): Default since Java 9. Designed for large heaps (>4GB). It divides the heap into equal-sized regions. It tries to meet predefined pause-time targets. Highly recommended for most modern server applications.
4. **ZGC** (`-XX:+UseZGC`) and **Shenandoah**: Ultra-low latency collectors. They aim for pause times of < 1 millisecond, even on multi-terabyte heaps. Excellent for latency-sensitive applications (e.g., high-frequency trading), but may trade off some overall throughput.

## 4. Key JVM Tuning Flags

- `-Xms`: Initial heap size.
- `-Xmx`: Maximum heap size. *(Best practice: Set `-Xms` and `-Xmx` to the same value to prevent the JVM from pausing application threads to resize the heap).*
- `-XX:MaxMetaspaceSize`: Limits Metaspace.
- `-XX:+UseG1GC`: Explicitly enable G1 GC.
- `-XX:MaxGCPauseMillis=200`: (For G1 GC) Target pause time. G1 will adjust its region sizing to try and meet this goal.
- `-XX:+HeapDumpOnOutOfMemoryError`: Crucial for debugging. Generates a memory dump when the JVM crashes, which you can analyze in Eclipse MAT.

## Interview Questions on JVM & GC

1. **Explain the difference between a Memory Leak and an `OutOfMemoryError` in Java.**
   - *Answer*: A memory leak occurs when the application unintentionally holds strong references to objects that are no longer needed, preventing the GC from reclaiming them. An `OutOfMemoryError` (OOM) is the symptom that occurs when the JVM completely runs out of heap space (often caused by a leak, but can also be caused by trying to load a massive dataset into memory legitimately).
2. **What does "Stop the World" mean in the context of Garbage Collection?**
   - *Answer*: It refers to the GC pausing all application threads to safely trace object root references or move objects in memory. During this time, the application cannot respond to any requests, causing latency spikes.
3. **Why do we separate the Heap into Young and Old generations?**
   - *Answer*: Because most objects die young (e.g., request-scoped DTOs). By isolating them in a small Young Generation, the Minor GC can quickly clean up dead objects without scanning the entire massive heap. Long-lived objects are promoted to the Old generation, which is collected much less frequently.
