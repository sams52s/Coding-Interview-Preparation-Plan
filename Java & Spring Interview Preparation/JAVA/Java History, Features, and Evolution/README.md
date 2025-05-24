<p align="center">
  <img src="banner.png" alt="Java Evolution Banner" width="100%">
</p>

<h1 align="center">☕ Java History, Features & Evolution (Java 1 → 25)</h1>

<p align="center">
  <b>This repository provides a structured, version-by-version technical overview of Java’s evolution, covering key language features, JEP milestones, and platform innovations from Java 1 to the latest releases.</b><br>
  It is designed as a consolidated reference for software engineers and Java practitioners seeking a precise understanding of the platform’s historical and ongoing advancements.
</p>

<p align="center">
  <a href="./java-8.md"><img src="https://img.shields.io/badge/Java%208-LTS-green?style=for-the-badge" alt="Java 8"></a>
  <a href="./java-11.md"><img src="https://img.shields.io/badge/Java%2011-LTS-green?style=for-the-badge" alt="Java 11"></a>
  <a href="./java-17.md"><img src="https://img.shields.io/badge/Java%2017-LTS-green?style=for-the-badge" alt="Java 17"></a>
  <a href="./java-21.md"><img src="https://img.shields.io/badge/Java%2021-LTS-green?style=for-the-badge" alt="Java 21"></a>
  <a href="./feature-list.md"><img src="https://img.shields.io/badge/Feature%20List-Full%20Catalog-blueviolet?style=for-the-badge" alt="Feature List"></a>
</p>

> 💡 **What’s inside?**  
> ✅ Detailed breakdown of every major Java release (1–25)  
> ✅ Code examples, feature explanations, and JEP links  
> ✅ Interactive diagrams, timelines, and developer insights  
> ✅ Dedicated summary: [Full Java Feature List](feature-list.md)

## 📊 Java Version Overview Table

| 🏷️ Version (Batch) | 📅 Release Date | 📄 My Notes (.md) | 🚀 Highlights & Features | 🔗 JEPs & Docs |
|--------------------|-----------------|-------------------|-------------------------|---------------|
| <a href="./java-1-7.md"><img src="https://img.shields.io/badge/Java%201–7-LTS-orange?style=for-the-badge" alt="Java 1–7"></a> | 1996–2011      | [java-1-7.md](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20History%2C%20Features%2C%20and%20Evolution/java-1%20to%207.md) | OOP foundations, collections, generics, Swing, AWT, Classic IO, JDBC | [JEP Index](https://openjdk.org/projects/jdk/), [Oracle Archive](https://www.oracle.com/java/technologies/javase-downloads.html) |
| <a href="./java-8.md"><img src="https://img.shields.io/badge/Java%208-LTS-orange?style=for-the-badge" alt="Java 8"></a> | Mar 2014       | [java-8.md](java-8.md) | Functional programming, Lambdas, Streams, Optional, Nashorn, Date/Time | [JEP 126](https://openjdk.org/projects/jdk8/), [Java 8 Docs](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) |
| <a href="./java-9.md"><img src="https://img.shields.io/badge/Java%209-Feature-blue?style=for-the-badge" alt="Java 9"></a> | Sept 2017      | [java-9.md](java-9.md) | Modular system (JPMS), JShell, Compact Strings, Collection Factory Methods, Multi-Release JARs | [JEP 261](https://openjdk.org/projects/jdk9/), [Java 9 Docs](https://www.oracle.com/java/technologies/javase/9-relnotes.html) |
| <a href="./java-10.md"><img src="https://img.shields.io/badge/Java%2010-Feature-blue?style=for-the-badge" alt="Java 10"></a> | Mar 2018       | [java-10.md](java-10.md) | Local variable type inference (`var`), AppCDS, Root Certificates | [JEP 286](https://openjdk.org/projects/jdk10/), [Java 10 Docs](https://www.oracle.com/java/technologies/javase/10-relnotes.html) |
| <a href="./java-11.md"><img src="https://img.shields.io/badge/Java%2011-LTS-orange?style=for-the-badge" alt="Java 11"></a> | Sept 2018      | [java-11.md](java-11.md) | HTTP Client, TLS 1.3, single-file source run, Flight Recorder, String Enhancements | [JEP 321](https://openjdk.org/projects/jdk11/), [Java 11 Docs](https://www.oracle.com/java/technologies/javase/11-relnotes.html) |
| <a href="./java-12.md"><img src="https://img.shields.io/badge/Java%2012-Feature-blue?style=for-the-badge" alt="Java 12"></a> | Mar 2019       | [java-12.md](java-12.md) | Switch Expressions (preview), Shenandoah GC, Microbenchmark Suite | [JEP 325](https://openjdk.org/projects/jdk12/), [Java 12 Docs](https://www.oracle.com/java/technologies/javase/12-relnotes.html) |
| <a href="./java-13.md"><img src="https://img.shields.io/badge/Java%2013-Feature-blue?style=for-the-badge" alt="Java 13"></a> | Sept 2019      | [java-13.md](java-13.md) | Text Blocks (preview), Dynamic CDS Archives, Reimplemented Socket API | [JEP 355](https://openjdk.org/projects/jdk13/), [Java 13 Docs](https://www.oracle.com/java/technologies/javase/13-relnotes.html) |
| <a href="./java-14.md"><img src="https://img.shields.io/badge/Java%2014-Feature-blue?style=for-the-badge" alt="Java 14"></a> | Mar 2020       | [java-14.md](java-14.md) | Records (preview), Pattern Matching, Helpful NullPointerException | [JEP 359](https://openjdk.org/projects/jdk14/), [Java 14 Docs](https://www.oracle.com/java/technologies/javase/14-relnotes.html) |
| <a href="./java-15.md"><img src="https://img.shields.io/badge/Java%2015-Feature-blue?style=for-the-badge" alt="Java 15"></a> | Sept 2020      | [java-15.md](java-15.md) | Sealed Classes (preview), Hidden Classes, Text Blocks (standard) | [JEP 360](https://openjdk.org/projects/jdk15/), [Java 15 Docs](https://www.oracle.com/java/technologies/javase/15-relnotes.html) |
| <a href="./java-16.md"><img src="https://img.shields.io/badge/Java%2016-Feature-blue?style=for-the-badge" alt="Java 16"></a> | Mar 2021       | [java-16.md](java-16.md) | Records (final), Pattern Matching, Vector API (incubating) | [JEP 395](https://openjdk.org/projects/jdk16/), [Java 16 Docs](https://www.oracle.com/java/technologies/javase/16-relnotes.html) |
| <a href="./java-17.md"><img src="https://img.shields.io/badge/Java%2017-LTS-orange?style=for-the-badge" alt="Java 17"></a> | Sept 2021      | [java-17.md](java-17.md) | Sealed Classes, Pattern Matching (final), Foreign API, Strong Encapsulation | [JEP 409](https://openjdk.org/projects/jdk17/), [Java 17 Docs](https://www.oracle.com/java/technologies/javase/17-relnotes.html) |
| <a href="./java-18.md"><img src="https://img.shields.io/badge/Java%2018-Feature-blue?style=for-the-badge" alt="Java 18"></a> | Mar 2022       | [java-18.md](java-18.md) | UTF-8 by Default, Simple Web Server, Code Snippets | [JEP 400](https://openjdk.org/projects/jdk18/), [Java 18 Docs](https://www.oracle.com/java/technologies/javase/18-relnotes.html) |
| <a href="./java-19.md"><img src="https://img.shields.io/badge/Java%2019-Feature-blue?style=for-the-badge" alt="Java 19"></a> | Sept 2022      | [java-19.md](java-19.md) | Virtual Threads (preview), Structured Concurrency, Record Patterns | [JEP 425](https://openjdk.org/projects/jdk19/), [Java 19 Docs](https://www.oracle.com/java/technologies/javase/19-relnotes.html) |
| <a href="./java-20.md"><img src="https://img.shields.io/badge/Java%2020-Feature-blue?style=for-the-badge" alt="Java 20"></a> | Mar 2023       | [java-20.md](java-20.md) | Record Patterns, Virtual Threads (2nd preview), Scoped Values | [JEP 432](https://openjdk.org/projects/jdk20/), [Java 20 Docs](https://www.oracle.com/java/technologies/javase/20-relnotes.html) |
| <a href="./java-21.md"><img src="https://img.shields.io/badge/Java%2021-LTS-orange?style=for-the-badge" alt="Java 21"></a> | Sept 2023      | [java-21.md](java-21.md) | Virtual Threads (final), String Templates, Sequenced Collections | [JEP 444](https://openjdk.org/projects/jdk21/), [Java 21 Docs](https://www.oracle.com/java/technologies/javase/21-relnotes.html) |
| <a href="./java-22.md"><img src="https://img.shields.io/badge/Java%2022-Feature-blue?style=for-the-badge" alt="Java 22"></a> | Mar 2024       | [java-22.md](java-22.md) | Unnamed Patterns, Foreign Function API (FFM), Stream Gatherers | [JEP 443](https://openjdk.org/projects/jdk22/), [Java 22 Docs](https://www.oracle.com/java/technologies/javase/22-relnotes.html) |
| <a href="./java-23.md"><img src="https://img.shields.io/badge/Java%2023-Feature-blue?style=for-the-badge" alt="Java 23"></a> | Sept 2024      | [java-23.md](java-23.md) | Enhanced Record Patterns, Sealed Interfaces | [JEP 449](https://openjdk.org/projects/jdk23/), [Java 23 Docs](https://www.oracle.com/java/technologies/javase/23-relnotes.html) |
| <a href="./java-24.md"><img src="https://img.shields.io/badge/Java%2024-Feature-blue?style=for-the-badge" alt="Java 24"></a> | Mar 2025+      | [java-24.md](java-24.md) | Loom, Panama, Valhalla, Vector API, Structured Concurrency, Inline Types | [JEP 455](https://openjdk.org/projects/jdk24/), [Java 24 Docs](https://www.oracle.com/java/technologies/javase/24-relnotes.html) |
| <a href="./java-25.md"><img src="https://img.shields.io/badge/Java%2025-Planned-lightgrey?style=for-the-badge" alt="Java 25"></a> | Sept 2025 (planned) | [java-25.md](java-25.md) | Primitive Classes, Unified Valhalla & Panama, Leyden AOT, Static AOT Images | [JEP 460](https://openjdk.org/projects/jdk25/), [Java 25 Docs](https://www.oracle.com/java/technologies/javase/25-relnotes.html) |
| <a href="./feature-list.md"><img src="https://img.shields.io/badge/Feature%20List-Full%20Catalog-blueviolet?style=for-the-badge" alt="Feature List"></a> | Ongoing       | [feature-list.md](feature-list.md) | Aggregated feature summary across all versions, cross-version comparison | [JEP Master Index](https://openjdk.org/jeps/0) |

---

## 🤝 Connect & Contribute

This documentation is
_Last updated: `MAY 2025`_ BY **[@sams52s](https://github.com/sams52s)**

✅ Like this repo? ⭐ Star it and share with your developer friends!
