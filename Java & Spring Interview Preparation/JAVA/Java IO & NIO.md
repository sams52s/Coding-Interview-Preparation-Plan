# 📂 Java I/O and NIO

Java provides two primary APIs for handling input and output operations: **I/O (java.io)** and **NIO (java.nio)**. While traditional I/O is stream-based and blocking, NIO offers buffer-based, non-blocking capabilities for scalable, high-performance I/O.

---

## 📄 File Handling in Java (java.io)

### 📁 Common Classes

| Class           | Description                             |
|----------------|-----------------------------------------|
| `File`         | Represents a file or directory           |
| `FileReader`   | Reads character streams from a file      |
| `BufferedReader` | Reads text from input stream efficiently |
| `FileWriter`   | Writes character streams to a file       |
| `PrintWriter`  | Buffered writer with formatted output    |

### 🧪 Example
```java
File file = new File("test.txt");
if (file.exists()) {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
    reader.close();
}
```

### ✅ Interview Tip
- Always **close readers/writers** using try-with-resources.
- Use `BufferedReader` for reading large text files efficiently.

---

## 🧳 Serialization & Deserialization

Serialization is the process of converting an object into a byte stream. Deserialization is the reverse process.

### 🔐 Key Classes & Interfaces
- `Serializable`: Marker interface (no methods)
- `ObjectOutputStream`, `ObjectInputStream`

### 🧪 Example
```java
// Serialization
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
out.writeObject(someObject);
out.close();

// Deserialization
ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
MyClass obj = (MyClass) in.readObject();
in.close();
```

### ⚠️ Notes
- Use `transient` to skip fields during serialization.
- Custom serialization: implement `readObject()` and `writeObject()`.

---

## ⚡ Java NIO (Non-blocking I/O)

Introduced in Java 1.4, NIO provides improved scalability using buffers and channels.

### 📦 Key Concepts

| Component | Description                          |
|----------|--------------------------------------|
| `Buffer` | A container for data (ByteBuffer, CharBuffer, etc.) |
| `Channel`| Connection to entities like file/network |
| `Selector`| Monitors multiple channels for events |

### 🧭 NIO Data Flow Diagram
```text
[File/Socket] <--Channel--> [Buffer] <---> Application Logic
                           ↑      ↓
                    flip(), clear(), rewind()
```

### 🧪 FileChannel Example
```java
RandomAccessFile file = new RandomAccessFile("sample.txt", "r");
FileChannel channel = file.getChannel();
ByteBuffer buffer = ByteBuffer.allocate(1024);

while (channel.read(buffer) > 0) {
    buffer.flip();
    while (buffer.hasRemaining()) {
        System.out.print((char) buffer.get());
    }
    buffer.clear();
}
channel.close();
file.close();
```

---

## 🔄 java.nio.file (Java 7+ Modern File I/O)

- `Paths` and `Files` classes provide modern file handling.

```java
Path path = Paths.get("data.txt");
List<String> lines = Files.readAllLines(path);
Files.write(path, Arrays.asList("Line1", "Line2"));
```

- Methods like `exists()`, `createDirectories()`, `copy()`, `delete()` simplify file operations.

---

## 📊 Comparison: I/O vs NIO

| Feature         | java.io             | java.nio               |
|-----------------|---------------------|-------------------------|
| Model           | Stream-based        | Buffer-based            |
| Blocking        | Yes                 | No (non-blocking)       |
| Performance     | Good for small files| Better for large data / concurrency |
| API Simplicity  | Simple              | More complex            |

### ✅ Use Case Summary
- Use **I/O** for quick, linear file reading/writing.
- Use **NIO** for performance-critical, scalable applications.

---

## 🔐 Additional Topics Worth Exploring

- `FileVisitor` for directory tree traversal
- Memory-mapped files (`MappedByteBuffer`)
- WatchService API (file system event monitoring)
- Charset handling with `Charset` and `CharsetDecoder`
- AsynchronousFileChannel for async I/O

---

## 📘 Real-world Examples

- **Log File Readers**: Use `BufferedReader` or `Files.lines()`
- **Network Servers**: Use `Selector` and `SocketChannel` in NIO
- **Object Caching**: Serialize objects for caching user sessions or app state
- **File Watchers**: Use `WatchService` to reload config on changes

---

## 🧠 Interview Tips

- Know how to serialize nested objects
- Be ready to compare I/O vs NIO trade-offs
- Understand why `transient` exists
- Explain `try-with-resources` in file I/O
- Be familiar with channels and buffers if applying for high-performance/backend roles

---

## 🧾 Cheatsheet Summary

| Area             | Classes/Interfaces                  | Purpose                                   |
|------------------|--------------------------------------|-------------------------------------------|
| File I/O         | `File`, `FileReader`, `BufferedReader` | Reading/writing text files                |
| Output           | `FileWriter`, `PrintWriter`           | Writing character data                    |
| Serialization    | `Serializable`, `ObjectOutputStream`  | Object persistence                        |
| Modern File API  | `Path`, `Files`, `Paths`              | Easy file manipulation (Java 7+)          |
| NIO Buffer/Channel | `ByteBuffer`, `FileChannel`, `Selector` | High-performance I/O                      |
| Extras           | `WatchService`, `MappedByteBuffer`    | File monitoring, memory mapping           |

---

