# Object-Oriented Programming (OOP) in Java

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Contents](#-contents)
- [Quick Start](#-quick-start)
- [Learning Path](#️-learning-path)
- [Goals](#-goals)
- [How to Use](#-how-to-use)
- [Highlights](#-highlights)
- [Who is this For](#-who-is-this-for)
- [Progress Tracker](#-progress-tracker)
- [Frequently Asked Questions](#-frequently-asked-questions)
- [Additional Resources](#-additional-resources)
- [What's Next](#️-whats-next)
- [Contributions & Feedback](#-contributions--feedback)

---

## 🔍 Overview

Object-Oriented Programming (OOP) is a programming paradigm that uses "objects" to design applications and computer programs. Java is fundamentally an object-oriented language built around the concept of objects that contain both data and methods. This section covers core OOP principles, advanced techniques, and design patterns in Java to help you master object-oriented design for interviews and real-world applications.

---

## 📂 Contents

- [Classes and Objects](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Classes%20and%20Objects.md)
- [Functions vs Methods in Java](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Functions%20vs%20Methods%20in%20Java.md)
- [Constructors & Overloading](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Constructors%20%26%20Overloading%20in%20Java.md)
- [Encapsulation](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Encapsulation%20in%20Java.md)
- [Inheritance, Polymorphism & Subclassing in Java](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Inheritance%2C%20Polymorphism%20%26%20Subclassing%20in%20Java.md)
- [Abstract Classes and Interfaces](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Abstract%20Classes%20and%20Interfaces%20in%20Java.md)
- [Composition vs Inheritance](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Inheritance%20vs%20Composition%20in%20OOP.md)
- [SOLID Principles](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/SOLID%20Principles%20in%20Java%20OOP.md)

- [Interview Questions and Answers](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Interview%20Questions%20and%20Answers.md)

> Each file includes diagrams, use cases, code samples, trade-offs, and interview-ready notes.

---

## 🚀 Quick Start

- **🧱 New to OOP?** Start with [Classes and Objects](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Classes%20and%20Objects.md)
- **🎯 Preparing for interviews?** Focus on [[SOLID Principles](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/SOLID%20Principles%20in%20Java%20OOP.md) and design pattern discussions
- **💻 Want implementation tips?** Use [Composition vs Inheritance](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/Inheritance%20vs%20Composition%20in%20OOP.md)


---

## 📂 [Java Fundamentals](https://github.com/sams52s/Coding-Interview-Preparation-Plan/tree/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals)
## 📂 [Java Collection Framework](https://github.com/sams52s/Coding-Interview-Preparation-Plan/tree/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Collections%20Framework)

## 🗺️ Learning Path

```bash
Classes & Objects → Constructors → Encapsulation → Inheritance → Polymorphism → Abstraction → Interfaces → SOLID Principles → Design Patterns
```

---

## 🎯 Goals

After completing this section, you should be able to:

- Implement Java classes with proper encapsulation
- Design inheritance hierarchies that follow best practices
- Apply polymorphism to create flexible and extensible code
- Differentiate between abstraction mechanisms in Java
- Make informed decisions between composition and inheritance
- Apply SOLID principles to your code
- Recognize and implement common design patterns
- Answer interview questions about OOP concepts with confidence

---


## 🔥 Highlights

- ✅ **Real-World Examples**: Each concept illustrated with practical, industry-relevant examples
- ✅ **Visual Learning**: UML diagrams for complex relationships
- ✅ **Common Pitfalls**: Sections highlighting frequent mistakes and how to avoid them
- ✅ **Interview Spotlight**: Specifically tagged sections covering common interview questions
- ✅ **Performance Considerations**: Notes on efficiency and optimization for each pattern
- ✅ **Java-Specific Features**: Focus on Java's implementation of OOP concepts

---


## 🧠 Who is this For?

- 🎓 **Students** learning object-oriented programming 
- 🧩 **Interviewees** preparing for technical interviews with OOP focus
- 👨‍💻 **Experienced developers** refreshing their OOP knowledge
- 🔄 **Backend developers** transitioning to Java
- 🚀 **Self-taught programmers** filling gaps in their OOP understanding
- 🏗️ **Software architects** improving design skills using OOP principles

---

## ❓ Frequently Asked Questions

<details>
<summary><b>What's the difference between an interface and an abstract class in Java?</b></summary>
<p>An abstract class can have state and implemented methods, while interfaces traditionally define only abstract methods (though Java 8+ allows default implementations). A class can implement multiple interfaces but extend only one abstract class.</p>
</details>

<details>
<summary><b>How does polymorphism work in Java?</b></summary>
<p>Polymorphism allows objects to be treated as instances of their parent class rather than their actual class. It's achieved through method overriding and interfaces.</p>
</details>

<details>
<summary><b>When should I use composition over inheritance?</b></summary>
<p>Use composition when you want to reuse functionality without exposing the entire interface of the composed class. "Favor composition over inheritance" is a design principle that promotes more flexible and maintainable code.</p>
</details>

<details>
<summary><b>How do I implement the Singleton pattern in Java?</b></summary>
<p>Implement with a private constructor, a private static instance, and a public static method to access the instance. Consider thread safety with double-checked locking or using an enum.</p>
</details>

<details>
<summary><b>What are SOLID principles and why are they important?</b></summary>
<p>SOLID principles are five design principles that make software more maintainable and extensible. They help create cleaner, more modular code that's easier to test and adapt to changing requirements.</p>
</details>

---

## 📚 Additional Resources

### Official Documentation
- 📘 [Oracle Java Documentation on OOP Concepts](https://docs.oracle.com/javase/tutorial/java/concepts/)
- 📘 [Java API Specification](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
- 📘 [Java Language Specification - Classes](https://docs.oracle.com/javase/specs/jls/se17/html/jls-8.html)

### Learning Platforms
- 🎓 [Coursera - Object Oriented Programming in Java](https://www.coursera.org/specializations/object-oriented-programming)
- 🎓 [Udemy - Java OOP Complete Reference](https://www.udemy.com/course/java-the-complete-java-developer-course/)
- 🎓 [Pluralsight - Java Fundamentals: Object-oriented Design](https://www.pluralsight.com/courses/java-fundamentals-object-oriented-design)

### Tutorials & Articles
- 📗 [Baeldung OOP Articles](https://www.baeldung.com/java-oop)
- 📗 [Refactoring Guru - Design Patterns in Java](https://refactoring.guru/design-patterns/java)
- 📗 [Java Design Patterns](https://java-design-patterns.com/)
- 📗 [OOP Is Dead, Long Live OOP](https://www.infoq.com/presentations/oop-dead-alive/)

### Essential Books
- 📚 *Effective Java* by Joshua Bloch
- 📚 *Head First Design Patterns* by Eric Freeman & Elisabeth Robson
- 📚 *Clean Code* by Robert C. Martin
- 📚 *Design Patterns: Elements of Reusable Object-Oriented Software* by Gang of Four
- 📚 *Object-Oriented Analysis and Design with Applications* by Grady Booch

### Video Resources
- 🎥 [Derek Banas OOP Concepts Playlist](https://www.youtube.com/playlist?list=PLGLfVvz_LVvS5P7khyR4xDp7T9lCk9PgE)
- 🎥 [JavaBrains OOP Fundamentals](https://www.youtube.com/user/koushks)
- 🎥 [Amigoscode Java Course](https://www.youtube.com/c/amigoscode)
- 🎥 [Christopher Okhravi's Design Patterns Series](https://www.youtube.com/playlist?list=PLrhzvIcii6GNjpARdnO4uTUAVxTFuuBNV)

### Practice Resources
- 💻 [LeetCode OOP Design Questions](https://leetcode.com/tag/design/)
- 💻 [HackerRank OOP Challenges](https://www.hackerrank.com/domains/java)
- 💻 [Exercism Java Track](https://exercism.io/tracks/java)
- 💻 [CodeGym OOP Tasks](https://codegym.cc/)

### Community Resources
- 👥 [Stack Overflow - Java](https://stackoverflow.com/questions/tagged/java)
- 👥 [Reddit r/learnjava](https://www.reddit.com/r/learnjava/)
- 👥 [Java Programming Discord](https://discord.gg/java)
- 👥 [DZone Java Design Patterns Zone](https://dzone.com/java-jdk-development-tutorials-tools-news)

---

## ⏭️ What's Next

After mastering OOP concepts, consider exploring these related areas:

- Advanced Java Features (Generics, Annotations, Reflection)
- Concurrency and Multithreading
- Functional Programming in Java
- Spring Framework fundamentals
- Design Patterns in depth
- Test-Driven Development
- Enterprise Application Architecture

---

## 🙌 Contributions & Feedback

Your contributions help improve this resource for everyone! If you find errors, have suggestions, or want to add content:

- Open an issue describing your proposed changes
- Submit a pull request with improvements
- Share feedback through the provided forms
- Add examples or exercises that helped you understand concepts better

For major changes, please open an issue first to discuss what you would like to change.

---
