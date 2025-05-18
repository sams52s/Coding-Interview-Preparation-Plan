# 📁 Java I/O and NIO

Java provides two main APIs for file and stream handling:
- **Java I/O (Input/Output)** – Stream-based, blocking.
- **Java NIO (New I/O)** – Buffer-based, non-blocking, scalable.

---

## 🧵 Java I/O Overview (java.io)

Java I/O works with **streams**: sequences of data (bytes or characters).

### 🔽 Input and Output Streams (Binary Data)

```java
FileInputStream fis = new FileInputStream("input.txt");
int b;
while ((b = fis.read()) != -1) {
    System.out.print((char) b);
}
fis.close();
```

### 🔤 Readers and Writers (Character Data)

```java
BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
String line;
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
reader.close();
```

---

## 🧱 Java I/O Building Blocks

| Class | Use |
|-------|-----|
| `File` | Represents file/directory pathnames |
| `FileReader` / `FileWriter` | Read/write character data |
| `BufferedReader` / `BufferedWriter` | Efficient reading/writing |
| `PrintWriter` | Convenient formatted writing |
| `ObjectInputStream` / `ObjectOutputStream` | For serialization |
| `DataInputStream` / `DataOutputStream` | Read/write Java primitive types |

---

## ⚙️ Java NIO Overview (java.nio)

NIO is based on **channels**, **buffers**, and **selectors**.

### 📦 Buffers

```java
ByteBuffer buffer = ByteBuffer.allocate(1024);
buffer.put("Hello".getBytes());
buffer.flip();
while (buffer.hasRemaining()) {
    System.out.print((char) buffer.get());
}
```

### 🔌 Channels

```java
FileChannel channel = new FileInputStream("input.txt").getChannel();
ByteBuffer buffer = ByteBuffer.allocate(1024);
channel.read(buffer);
buffer.flip();
```

### 🎛️ Selectors (For Multiplexing)

```java
Selector selector = Selector.open();
channel.configureBlocking(false);
channel.register(selector, SelectionKey.OP_READ);
```

---

## 🛠️ Common NIO Utilities

| Utility | Use |
|--------|-----|
| `Paths.get()` | Convert path strings to `Path` |
| `Files.readAllLines()` | Read all lines into a list |
| `Files.copy()` | Copy file contents |
| `Files.walk()` | Traverse directories |

---

## 💾 Serialization

```java
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
out.writeObject(new Student("Alice", 20));
out.close();
```

---

## ⚠️ I/O and NIO – Common Pitfalls

- ❌ Forgetting to close streams → Use try-with-resources
- ❌ Using streams for character data (use readers/writers instead)
- ❌ Blocking behavior in I/O (prefer NIO for scale)
- ❌ Not flipping/clearing buffers in NIO

---

## 🌍 Real-World Use Cases – Java I/O and NIO

### 📁 1. Copying Files (Java 7+)

```java
Path source = Paths.get("source.txt");
Path destination = Paths.get("backup.txt");
Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
```

### 📝 2. Processing Log Files (Line-by-Line)

```java
try (BufferedReader reader = Files.newBufferedReader(Paths.get("server.log"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        if (line.contains("ERROR")) {
            System.out.println("Found error: " + line);
        }
    }
}
```

### 🌐 3. Non-blocking Server with NIO

```java
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.bind(new InetSocketAddress(8080));
serverChannel.configureBlocking(false);

Selector selector = Selector.open();
serverChannel.register(selector, SelectionKey.OP_ACCEPT);

while (true) {
    selector.select();
    Set<SelectionKey> keys = selector.selectedKeys();
    for (SelectionKey key : keys) {
        if (key.isAcceptable()) {
            SocketChannel client = serverChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            client.read(buffer);
            buffer.flip();
            client.write(buffer); // echo back
        }
    }
    keys.clear();
}
```

### 📊 4. CSV Report Generator

```java
List<String> rows = Arrays.asList("id,name", "1,Alice", "2,Bob");
Path file = Paths.get("report.csv");
Files.write(file, rows, StandardCharsets.UTF_8);
```

### 🔄 5. Directory Walk (Recursive File List)

```java
Files.walk(Paths.get("./logs"))
     .filter(Files::isRegularFile)
     .forEach(System.out::println);
```

---

## 🎯 Advanced NIO Features

### Memory-Mapped Files
```java
try (FileChannel channel = FileChannel.open(Paths.get("bigfile.dat"), 
        StandardOpenOption.READ, StandardOpenOption.WRITE)) {
    MappedByteBuffer buffer = channel.map(
        FileChannel.MapMode.READ_WRITE, 0, channel.size());
    
    // Direct memory access, very efficient for large files
    while (buffer.hasRemaining()) {
        buffer.put((byte) (buffer.get() + 1));
    }
}
```

### Async Channel Operations
```java
AsynchronousFileChannel channel = AsynchronousFileChannel.open(
    Paths.get("large.file"), StandardOpenOption.READ);
ByteBuffer buffer = ByteBuffer.allocate(1024);

Future<Integer> result = channel.read(buffer, 0);
while (!result.isDone()) {
    // Do other work while reading
}
```

## 🔄 NIO.2 Path Operations (Java 7+)

### File Tree Operations
```java
// Copy entire directory tree
Files.walk(sourcePath)
     .forEach(source -> {
         Path target = destPath.resolve(sourcePath.relativize(source));
         try {
             Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
         } catch (IOException e) {
             e.printStackTrace();
         }
     });

// Watch directory for changes
WatchService watcher = FileSystems.getDefault().newWatchService();
Path dir = Paths.get("logs");
dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
```

---

## 🧠 Interview Questions & Answers – Java I/O & NIO

### ❓ Q1: What is the difference between Java I/O and Java NIO?  
**✅ A:** I/O is stream-based and blocking. NIO is buffer-based and non-blocking, suitable for scalable applications.

---

### ❓ Q2: What are Channels and Buffers?  
**✅ A:** Channels are asynchronous data transport mechanisms. Buffers are containers that hold data for reading/writing.

---

### ❓ Q3: What is the use of Selectors in NIO?  
**✅ A:** Selectors allow a single thread to monitor multiple channels for events like read/write without blocking.

---

### ❓ Q4: What is the purpose of FileChannel?  
**✅ A:** FileChannel provides high-performance I/O using buffers, allowing file reads/writes with random access and memory mapping.

---

### ❓ Q5: How does try-with-resources work with I/O?  
**✅ A:** It automatically closes resources (e.g., streams) that implement `AutoCloseable`.

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    // use br
}
```

---

### ❓ Q6: Can you use NIO for network programming?  
**✅ A:** Yes. `SocketChannel` and `Selector` are widely used for building scalable non-blocking servers.

---

### ❓ Q7: What is the difference between absolute and relative paths in NIO?
**✅ A:** 
- Absolute paths contain complete directory list from root
- Relative paths are relative to current working directory
```java
Path absolute = Paths.get("/home/user/file.txt");
Path relative = Paths.get("docs/file.txt");
```

### ❓ Q8: How do you handle character encoding in Java I/O?
**✅ A:** Use `Charset` and character-encoded streams:
```java
try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(
            new FileInputStream("file.txt"), 
            StandardCharsets.UTF_8))) {
    // Read UTF-8 encoded text
}
```

### ❓ Q9: What is the difference between Buffer's position, limit, and capacity?
**✅ A:**
- Position: Current position in buffer
- Limit: First element that should not be read/written
- Capacity: Maximum number of elements buffer can hold
```java
ByteBuffer buffer = ByteBuffer.allocate(10); // capacity=10
buffer.put("Hello".getBytes());              // position=5, limit=10
buffer.flip();                               // position=0, limit=5
```

### ❓ Q10: How do you implement a non-blocking server using NIO?
**✅ A:** Use `Selector` with non-blocking channels:
```java
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.configureBlocking(false);
serverChannel.register(selector, SelectionKey.OP_ACCEPT);

while (true) {
    selector.select();
    for (SelectionKey key : selector.selectedKeys()) {
        if (key.isAcceptable()) {
            handleAccept(key);
        } else if (key.isReadable()) {
            handleRead(key);
        }
    }
}
```

### ❓ Q11: What is the purpose of DirectByteBuffer?
**✅ A:** `DirectByteBuffer` provides direct memory access outside JVM heap:
```java
ByteBuffer direct = ByteBuffer.allocateDirect(1024);
// Better performance for I/O operations, especially with native code
```

### ❓ Q12: How do you handle file locks in Java?
**✅ A:** Use `FileLock` for file-level synchronization:
```java
try (FileChannel channel = FileChannel.open(path, 
        StandardOpenOption.WRITE, StandardOpenOption.READ)) {
    FileLock lock = channel.lock();
    try {
        // Exclusive access to file
    } finally {
        lock.release();
    }
}
```

### ❓ Q13: What are the benefits of using NIO's Path over traditional File?
**✅ A:** 
- Better platform independence
- More operations (symbolic links, file attributes)
- Cleaner API for path manipulation
```java
Path path = Paths.get("dir", "subdir", "file.txt");
Path normalized = path.normalize();
Path resolved = path.resolve("another.txt");
```

### ❓ Q14: How do you implement random access to files?
**✅ A:** Use `RandomAccessFile` or `FileChannel`:
```java
try (RandomAccessFile file = new RandomAccessFile("data.bin", "rw")) {
    file.seek(1000); // Jump to position 1000
    file.write("data".getBytes());
}
```

### ❓ Q15: Explain memory-mapped files and their advantages.
**✅ A:** Memory-mapped files allow direct memory access to file contents:
- Better performance for large files
- Allows sharing between processes
- Supports random access efficiently
```java
MappedByteBuffer buffer = FileChannel.open(path)
    .map(FileChannel.MapMode.READ_WRITE, 0, size);
```

---

## ✅ Summary

- Use **I/O** for simple file handling and blocking operations.  
- Use **NIO** for high-performance or networked applications.  
- Always close resources or use **try-with-resources**.  
- Prefer **buffered readers/writers** for better performance.

---

## 📝 References

- [Java I/O (Oracle Docs)](https://docs.oracle.com/javase/tutorial/essential/io/index.html)  
- [Java NIO Tutorial – Baeldung](https://www.baeldung.com/java-nio)  
- [GeeksforGeeks – Java I/O](https://www.geeksforgeeks.org/java-io-package-examples/)  
- [Java NIO Overview (Oracle)](https://docs.oracle.com/javase/8/docs/api/java/nio/package-summary.html)
