<p align="center">
  <img src="banner.png" alt="Java Evolution Banner" width="100%">
</p>

<h1 align="center">☕ Java History, Features & Evolution (Java 1 → 25)</h1>

<p align="center">
  <b>This repository provides a structured, version-by-version technical overview of Java’s evolution, covering key language features, JEP milestones, and platform innovations from Java 1 to the latest releases.</b><br>
  It is designed as a consolidated reference for software engineers and Java practitioners seeking a precise understanding of the platform’s historical and ongoing advancements.
</p>

<p align="center">
  <a href="./java-1-7.md"><img src="https://img.shields.io/badge/Java%201–7-Reference-green?style=for-the-badge" alt="Java 1–7"></a>
  <a href="./java-8.md"><img src="https://img.shields.io/badge/Java%208-Reference-green?style=for-the-badge" alt="Java 8"></a>
  <a href="./java-11.md"><img src="https://img.shields.io/badge/Java%2011-Reference-green?style=for-the-badge" alt="Java 11"></a>
  <a href="./java-17.md"><img src="https://img.shields.io/badge/Java%2017-Reference-green?style=for-the-badge" alt="Java 17"></a>
  <a href="./java-21.md"><img src="https://img.shields.io/badge/Java%2021-Reference-green?style=for-the-badge" alt="Java 21"></a>
  <a href="./feature-list.md"><img src="https://img.shields.io/badge/Feature%20List-Full%20Catalog-blueviolet?style=for-the-badge" alt="Feature List"></a>
</p>

> 💡 **What’s inside?**  
> ✅ Detailed breakdown of every major Java release (1–25)  
> ✅ Code examples, feature explanations, and JEP links  
> ✅ Interactive diagrams, timelines, and developer insights  
> ✅ Dedicated summary: [Full Java Feature List](feature-list.md)

## 📊 Java Version Overview Table

| 🏷️ Version | 📅 Release Date  | 🏆 Status   | 🚀 Key Highlights                                      | 📄 My Notes (.md)         | 🔗 JEP Reference                                   | 🧩 Features Summary                                       | 📘 Additional Reference                                  |
|----------------------|------------------|-------------|--------------------------------------------------------|---------------------------|--------------------------------------------------|--------------------------------------------------------|--------------------------------------------------------|
| <a href="./java-1-7.md"><img src="https://img.shields.io/badge/Java%201–7-Reference-green?style=for-the-badge" alt="Java 1–7"></a> | 1996–2011        | ✅ LTS      | OOP foundations, collections, generics, Swing, AWT      | [java-1-7.md](java-1-7.md) | [JEP Index](https://openjdk.org/projects/jdk/)    | Collections, Generics, Classic IO, JDBC                 | [Oracle Archive](https://www.oracle.com/java/technologies/javase-downloads.html) |
| <a href="./java-8.md"><img src="https://img.shields.io/badge/Java%208-Reference-green?style=for-the-badge" alt="Java 8"></a> | Mar 2014         | ✅ LTS      | Functional programming, Lambdas, Streams, Date/Time     | [java-8.md](java-8.md)    | [JEP 126](https://openjdk.org/projects/jdk8/)     | Lambdas, Stream API, Optional, Nashorn, Date/Time       | [Java 8 Docs](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) |
| <a href="./java-9.md"><img src="https://img.shields.io/badge/Java%209-Reference-green?style=for-the-badge" alt="Java 9"></a> | Sept 2017        | ❌ Feature  | Modular system (JPMS), JShell, Compact Strings          | [java-9.md](java-9.md)    | [JEP 261](https://openjdk.org/projects/jdk9/)     | JPMS, JShell, Collection Factory Methods, Multi-Release JARs | [Java 9 Docs](https://www.oracle.com/java/technologies/javase/9-relnotes.html) |
| <a href="./java-10.md"><img src="https://img.shields.io/badge/Java%2010-Reference-green?style=for-the-badge" alt="Java 10"></a> | Mar 2018         | ❌ Feature  | Local variable type inference (`var`), AppCDS          | [java-10.md](java-10.md)  | [JEP 286](https://openjdk.org/projects/jdk10/)    | `var`, Application CDS, Root Certificates              | [Java 10 Docs](https://www.oracle.com/java/technologies/javase/10-relnotes.html) |
| <a href="./java-11.md"><img src="https://img.shields.io/badge/Java%2011-Reference-green?style=for-the-badge" alt="Java 11"></a> | Sept 2018        | ✅ LTS      | HTTP Client, TLS 1.3, single-file source run           | [java-11.md](java-11.md)  | [JEP 321](https://openjdk.org/projects/jdk11/)    | HTTP Client, Flight Recorder, String Enhancements      | [Java 11 Docs](https://www.oracle.com/java/technologies/javase/11-relnotes.html) |
| <a href="./java-12.md"><img src="https://img.shields.io/badge/Java%2012-Reference-green?style=for-the-badge" alt="Java 12"></a> | Mar 2019         | ❌ Feature  | Switch Expressions (preview), Shenandoah GC            | [java-12.md](java-12.md)  | [JEP 325](https://openjdk.org/projects/jdk12/)    | Switch Expressions, Shenandoah GC, Microbenchmark Suite | [Java 12 Docs](https://www.oracle.com/java/technologies/javase/12-relnotes.html) |
| <a href="./java-13.md"><img src="https://img.shields.io/badge/Java%2013-Reference-green?style=for-the-badge" alt="Java 13"></a> | Sept 2019        | ❌ Feature  | Text Blocks (preview), Dynamic CDS Archives            | [java-13.md](java-13.md)  | [JEP 355](https://openjdk.org/projects/jdk13/)    | Text Blocks, Reimplemented Socket API                 | [Java 13 Docs](https://www.oracle.com/java/technologies/javase/13-relnotes.html) |
| <a href="./java-14.md"><img src="https://img.shields.io/badge/Java%2014-Reference-green?style=for-the-badge" alt="Java 14"></a> | Mar 2020         | ❌ Feature  | Records (preview), Pattern Matching, Helpful NPEs      | [java-14.md](java-14.md)  | [JEP 359](https://openjdk.org/projects/jdk14/)    | Records, Pattern Matching, Helpful NullPointerException | [Java 14 Docs](https://www.oracle.com/java/technologies/javase/14-relnotes.html) |
| <a href="./java-15.md"><img src="https://img.shields.io/badge/Java%2015-Reference-green?style=for-the-badge" alt="Java 15"></a> | Sept 2020        | ❌ Feature  | Sealed Classes (preview), Hidden Classes, Text Blocks  | [java-15.md](java-15.md)  | [JEP 360](https://openjdk.org/projects/jdk15/)    | Sealed Classes, Hidden Classes, Text Blocks (standard) | [Java 15 Docs](https://www.oracle.com/java/technologies/javase/15-relnotes.html) |
| <a href="./java-16.md"><img src="https://img.shields.io/badge/Java%2016-Reference-green?style=for-the-badge" alt="Java 16"></a> | Mar 2021         | ❌ Feature  | Records (final), Pattern Matching, Vector API (incub)  | [java-16.md](java-16.md)  | [JEP 395](https://openjdk.org/projects/jdk16/)    | Records, Pattern Matching, Vector API                 | [Java 16 Docs](https://www.oracle.com/java/technologies/javase/16-relnotes.html) |
| <a href="./java-17.md"><img src="https://img.shields.io/badge/Java%2017-Reference-green?style=for-the-badge" alt="Java 17"></a> | Sept 2021        | ✅ LTS      | Sealed Classes, Pattern Matching (final), Foreign API  | [java-17.md](java-17.md)  | [JEP 409](https://openjdk.org/projects/jdk17/)    | Sealed Classes, Pattern Matching, Strong Encapsulation | [Java 17 Docs](https://www.oracle.com/java/technologies/javase/17-relnotes.html) |
| <a href="./java-18.md"><img src="https://img.shields.io/badge/Java%2018-Reference-green?style=for-the-badge" alt="Java 18"></a> | Mar 2022         | ❌ Feature  | UTF-8 by Default, Simple Web Server                   | [java-18.md](java-18.md)  | [JEP 400](https://openjdk.org/projects/jdk18/)    | UTF-8 Encoding, Simple Web Server, Code Snippets      | [Java 18 Docs](https://www.oracle.com/java/technologies/javase/18-relnotes.html) |
| <a href="./java-19.md"><img src="https://img.shields.io/badge/Java%2019-Reference-green?style=for-the-badge" alt="Java 19"></a> | Sept 2022        | ❌ Feature  | Virtual Threads (preview), Structured Concurrency      | [java-19.md](java-19.md)  | [JEP 425](https://openjdk.org/projects/jdk19/)    | Virtual Threads, Structured Concurrency, Record Patterns | [Java 19 Docs](https://www.oracle.com/java/technologies/javase/19-relnotes.html) |
| <a href="./java-20.md"><img src="https://img.shields.io/badge/Java%2020-Reference-green?style=for-the-badge" alt="Java 20"></a> | Mar 2023         | ❌ Feature  | Record Patterns, Virtual Threads (2nd preview)         | [java-20.md](java-20.md)  | [JEP 432](https://openjdk.org/projects/jdk20/)    | Record Patterns, Virtual Threads, Scoped Values       | [Java 20 Docs](https://www.oracle.com/java/technologies/javase/20-relnotes.html) |
| <a href="./java-21.md"><img src="https://img.shields.io/badge/Java%2021-Reference-green?style=for-the-badge" alt="Java 21"></a> | Sept 2023        | ✅ LTS      | Virtual Threads (final), String Templates, Sequenced Collections | [java-21.md](java-21.md)  | [JEP 444](https://openjdk.org/projects/jdk21/)    | Virtual Threads, String Templates, Sequenced Collections | [Java 21 Docs](https://www.oracle.com/java/technologies/javase/21-relnotes.html) |
| <a href="./java-22.md"><img src="https://img.shields.io/badge/Java%2022-Reference-green?style=for-the-badge" alt="Java 22"></a> | Mar 2024         | ❌ Feature  | Unnamed Patterns, Foreign Function API                | [java-22.md](java-22.md)  | [JEP 443](https://openjdk.org/projects/jdk22/)    | Unnamed Patterns, FFM API, Stream Gatherers          | [Java 22 Docs](https://www.oracle.com/java/technologies/javase/22-relnotes.html) |
| <a href="./java-23.md"><img src="https://img.shields.io/badge/Java%2023-Reference-green?style=for-the-badge" alt="Java 23"></a> | Sept 2024        | ❌ Feature  | Enhanced Record Patterns, Sealed Interfaces           | [java-23.md](java-23.md)  | [JEP 449](https://openjdk.org/projects/jdk23/)    | Enhanced Record Patterns, Sealed Interfaces          | [Java 23 Docs](https://www.oracle.com/java/technologies/javase/23-relnotes.html) |
| <a href="./java-24.md"><img src="https://img.shields.io/badge/Java%2024-Reference-green?style=for-the-badge" alt="Java 24"></a> | Mar 2025+        | ❌ Planned  | Loom, Panama, Valhalla, Vector API                   | [java-24.md](java-24.md) | [JEP 455](https://openjdk.org/projects/jdk24/)    | Structured Concurrency, Inline Types, Advanced Performance | [Java 24 Docs](https://www.oracle.com/java/technologies/javase/24-relnotes.html) |
| <a href="./java-25.md"><img src="https://img.shields.io/badge/Java%2025-Reference-green?style=for-the-badge" alt="Java 25"></a> | Sept 2025 (planned) | ❌ Planned | Primitive Classes, Unified Valhalla & Panama, Leyden AOT | [java-25.md](java-25.md) | [JEP 460](https://openjdk.org/projects/jdk25/) | Primitive classes, unified memory model, static AOT images | [Java 25 Docs](https://www.oracle.com/java/technologies/javase/25-relnotes.html) |
| **Feature List**    | Ongoing          | 🌟 Global   | Aggregated feature summary across all versions       | [feature-list.md](feature-list.md) | —                                              | Cross-version comparison, historical evolution        | [JEP Master Index](https://openjdk.org/jeps/0)        |

---

## 🤝 Connect & Contribute

This documentation is
_Last updated: `MAY 2025`_ BY **[@sams52s](https://github.com/sams52s)**

✅ Like this repo? ⭐ Star it and share with your developer friends!
