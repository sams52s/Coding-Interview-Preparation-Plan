# Java 1 (JDK 1.0) - The Beginning ☕️

## 📑 Table of Contents
- [Java 1 (JDK 1.0) - The Beginning](#java-1-jdk-10---the-beginning)
- [Java 2 (JDK 1.2) - Playground](#java-2-jdk-12---playground)
- [Java 3 (JDK 1.3) - Kestrel](#java-3-jdk-13---kestrel)
- [Java 4 (JDK 1.4) - Merlin](#java-4-jdk-14---merlin)
- [Java 5 (JDK 1.5) - The Language Revolution](#java-5-jdk-15---the-language-revolution-)
- [Java 6 (JDK 1.6) - Mustang](#java-6-jdk-16---mustang)
- [Java 7 (JDK 1.7) - Dolphin](#java-7-jdk-17---dolphin)

## 📅 Release Information
- **Release Date**: January 23, 1996
- **Code Name**: Oak
- **Status**: Historical Release

## ⭐ Key Features
![Base](https://img.shields.io/badge/🏗️-Base%20Language-blue)
![OOP](https://img.shields.io/badge/📦-OOP%20Features-blue)
![GC](https://img.shields.io/badge/🗑️-Garbage%20Collection-blue)

### Core Features
- [Java Virtual Machine (JVM)](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/JVM%2C%20JRE%2C%20JDK%20%E2%80%93%20Differences%20%26%20Roles.md)
- [Garbage Collection](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/%20Java%20Memory%20Management%20%26%20Garbage%20Collection.md)
- [Basic Object-Oriented Programming (OOP) features](https://github.com/sams52s/Coding-Interview-Preparation-Plan/tree/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP)
- Platform Independence (WORA)

### APIs & Libraries
- AWT (Abstract Window Toolkit)
- Applet Support
- Basic Networking
- [I/O Streams](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20IO%20%26%20NIO.md)

## 🔍 Code Examples
```java
// The first Java application
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java 1.0!");
    }
}
```

## 📚 Historical Context
- Originally developed by James Gosling at Sun Microsystems
- First called "Oak", renamed to "Java"
- Designed for interactive television
- Found its niche in Internet programming

# Java 2 (JDK 1.2) - Playground 🚀🏢

## 📅 Release Information
- **Release Date**: December 8, 1998
- **Code Name**: Playground
- **Status**: Historical Release

## ⭐ Key Features
![Swing](https://img.shields.io/badge/🎨-Swing-blue)
![Collections](https://img.shields.io/badge/📚-Collections-blue)
![Performance](https://img.shields.io/badge/🚀-Performance-blue)

### Major Features
- Introduction of Swing GUI toolkit
- [Collections Framework](https://github.com/sams52s/Coding-Interview-Preparation-Plan/tree/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Collections%20Framework)
- Just-In-Time (JIT) Compiler
- Java Plug-in and Java IDL

## 🔍 Code Examples
```java
// Using the Collections Framework
List<String> list = new ArrayList<>();
list.add("Java 2");
Collections.sort(list);
System.out.println(list.get(0));
```

## 📚 Historical Impact
- Major upgrade improving GUI and data structures
- Enhanced performance and scalability
- Foundation for modern Java APIs

# Java 3 (JDK 1.3) - Kestrel 🚀🏢

## 📅 Release Information
- **Release Date**: May 8, 2000
- **Code Name**: Kestrel
- **Status**: Historical Release

## ⭐ Key Features
![HotSpot](https://img.shields.io/badge/🔥-HotSpot%20VM-blue)
![RMI](https://img.shields.io/badge/📡-RMI%20Enhancements-blue)
![JNDI](https://img.shields.io/badge/📂-JNDI-blue)
![Performance](https://img.shields.io/badge/🚀-Performance-blue)
![Enterprise](https://img.shields.io/badge/🏢-Enterprise-blue)

### Major Features
- Introduction of the HotSpot Virtual Machine (default)
- Java Naming and Directory Interface (JNDI)
- RMI over IIOP
- Synthetic proxy classes
- JavaSound API
- Drag-and-drop support in AWT/Swing

## 🔍 Code Examples
```java
// Using a proxy (dynamic proxy example)
InvocationHandler handler = new MyInvocationHandler();
MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
    MyInterface.class.getClassLoader(),
    new Class<?>[] { MyInterface.class },
    handler
);
```

## 📚 Historical Impact
- Marked a major performance leap with the HotSpot VM
- Expanded Java’s enterprise capabilities (naming, directory, RMI/IIOP)
- Improved desktop interactivity with sound and drag/drop APIs

# Java 4 (JDK 1.4) - Merlin 🚀💡

## 📅 Release Information
- **Release Date**: February 6, 2002
- **Code Name**: Merlin
- **Status**: Historical Release

## ⭐ Key Features
![Assertions](https://img.shields.io/badge/✔️-Assertions-blue)
![NIO](https://img.shields.io/badge/📂-NIO-blue)
![Performance](https://img.shields.io/badge/🚀-Performance-blue)

### Major Features
- Assertions for debugging
- [New I/O (NIO) APIs](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20IO%20%26%20NIO.md)

- Exception chaining
- Logging API
- XML processing enhancements

## 🔍 Code Examples
```java
// Using assertions
assert x > 0 : "x must be positive";
```

## 📚 Historical Impact
- Improved debugging and I/O capabilities
- Enhanced robustness and maintainability

# Java 5 (JDK 1.5) - The Language Revolution 🚀🧬💡

## 📅 Release Information
- **Release Date**: September 30, 2004
- **Code Name**: Tiger
- **Status**: Historical Milestone

## ⭐ Key Features
![Generics](https://img.shields.io/badge/🧬-Generics-orange)
![Enum](https://img.shields.io/badge/📋-Enums-orange)
![Annotations](https://img.shields.io/badge/🏷️-Annotations-orange)
![Language Evolution](https://img.shields.io/badge/🧬-Language%20Evolution-orange)
![Developer Productivity](https://img.shields.io/badge/💡-Developer%20Productivity-orange)

### Major Language Features
- [Generics](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Generics.md)
- [Enhanced for Loop (for-each)](https://github.com/sams52s/Coding-Interview-Preparation-Plan/blob/main/Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/Java%20Control%20Flow.md)
- Autoboxing/Unboxing
- Enumerations (enum)
- Varargs
- Static Imports
- [Annotations](https://github.com/sams52s/Coding-Interview-Preparation-Plan/tree/main/Java%20%26%20Spring%20Interview%20Preparation/annotations-docs)
- Concurrent API

## 🔍 Code Examples
```java
// Generics
List<String> list = new ArrayList<>();
list.add("Java 5");

// Enhanced for loop
for (String item : list) {
    System.out.println(item);
}

// Enum
enum Day { MONDAY, TUESDAY, WEDNESDAY }

// Annotations
@Override
public String toString() {
    return "Java 5";
}
```

## 📚 Historical Impact
- Revolutionized Java development
- Enhanced type safety
- Improved developer productivity
- Foundation for modern Java features

# Java 6 (JDK 1.6) - Mustang 🚀💡

## 📅 Release Information
- **Release Date**: December 11, 2006
- **Code Name**: Mustang
- **Status**: Historical Release

## ⭐ Key Features
![Scripting](https://img.shields.io/badge/📝-Scripting-blue)
![Compiler API](https://img.shields.io/badge/⚙️-Compiler%20API-blue)
![Developer Productivity](https://img.shields.io/badge/💡-Developer%20Productivity-blue)

### Major Features
- Scripting language support via javax.script
- Compiler API for dynamic compilation
- Improvements to Web Services and JDBC
- Performance enhancements and monitoring

## 🔍 Code Examples
```java
// Using the Compiler API
JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
int result = compiler.run(null, null, null, "Test.java");
System.out.println("Compile result: " + result);
```

## 📚 Historical Impact
- Enhanced integration with scripting languages
- Improved developer tools and performance

# Java 7 (JDK 1.7) - Dolphin 🚀💡🧬

## 📅 Release Information
- **Release Date**: July 28, 2011
- **Code Name**: Dolphin
- **Status**: Historical Release

## ⭐ Key Features
![Diamond Operator](https://img.shields.io/badge/💎-Diamond%20Operator-green)
![Performance](https://img.shields.io/badge/🚀-Performance-green)
![Developer Productivity](https://img.shields.io/badge/💡-Developer%20Productivity-green)

### Major Features
- Diamond operator for type inference
- Try-with-resources statement
- Improved exception handling (multi-catch)
- Fork/Join Framework for parallelism 
- NIO.2 file system API enhancements 

## 🔍 Code Examples
```java
// Try-with-resources
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
}
```

## 📚 Historical Impact
- Simplified code and resource management
- Improved concurrency and file I/O APIs
