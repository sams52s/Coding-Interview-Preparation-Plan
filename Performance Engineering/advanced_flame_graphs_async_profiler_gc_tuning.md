# Flame Graphs, async-profiler, and JVM GC Tuning

Navigation: [README](README.md) | Previous: [JVM GC Tuning](23_jvm_gc_tuning.md) | Related: [Performance Benchmarks](../PERFORMANCE_BENCHMARKS.md)

This file adds practical production performance workflow: how to find CPU hotspots, allocation pressure, lock contention, and garbage-collection problems.

## Performance workflow

1. Define the symptom: latency, throughput, CPU, memory, GC pause, or error rate.
2. Reproduce with representative traffic.
3. Capture metrics and profiles.
4. Identify the dominant bottleneck.
5. Change one thing.
6. Re-measure.
7. Document the before/after result.

## Flame graphs

A flame graph visualizes stack traces sampled over time.

- Width means total time spent in a stack.
- Tall stacks show call depth.
- Wide frames are the first places to investigate.
- Compare before/after flame graphs to confirm improvement.

## async-profiler quick usage

Example commands:

```bash
./profiler.sh -d 60 -e cpu -f cpu.html <pid>
./profiler.sh -d 60 -e alloc -f alloc.html <pid>
./profiler.sh -d 60 -e lock -f lock.html <pid>
```

Use CPU profiling for hot methods, allocation profiling for object churn, and lock profiling for contention.

## Common findings

| Finding | Symptom | Fix |
|---------|---------|-----|
| Excess JSON serialization | high CPU in serializers | reduce payloads, cache DTOs, tune mapper |
| N+1 database query | slow request and DB load | fetch join, batch loading, query redesign |
| Allocation churn | frequent young GC | reuse buffers carefully, reduce temporary objects |
| Lock contention | blocked threads | narrow lock scope, use concurrent collections |
| Regex hotspot | CPU spike | precompile pattern, simplify parsing |

## GC tuning checklist

- Pick GC based on goal: throughput, low latency, or huge heap.
- Set heap based on live set plus headroom, not all available memory.
- Track allocation rate, pause time, promotion rate, and old-gen occupancy.
- Avoid tuning blindly; first find allocation sources.
- For container deployments, verify memory limits and JVM container awareness.

## Useful JVM flags

```bash
-Xms2g -Xmx2g
-XX:+UseG1GC
-Xlog:gc*:file=gc.log:time,uptime,level,tags
-XX:MaxGCPauseMillis=200
-XX:+HeapDumpOnOutOfMemoryError
```

## Incident example: allocation storm

**Symptom:** p99 latency increased from 180ms to 1.8s after a release.

**Finding:** Allocation flame graph showed a new logging statement serializing full request objects on every call.

**Fix:** Log stable identifiers and selected fields, not full objects. Add a log-size guard and sample debug logs.

**Prevention:** Add allocation profiling to performance test checklist.

## Interview talking points

- Profiling beats guessing.
- Flame graphs show where time goes, not where code looks suspicious.
- GC tuning cannot fix uncontrolled allocation forever.
- Always show before/after metrics.
- Connect JVM tuning to user-facing SLOs.

