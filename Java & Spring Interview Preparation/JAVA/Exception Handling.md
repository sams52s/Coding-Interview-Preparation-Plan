# ⚠️ Exception Handling in Java

Exception handling in Java is a powerful mechanism that allows developers to gracefully manage unexpected conditions and runtime errors without crashing the program.

---

## 📚 What is an Exception?

An **exception** is an event that disrupts the normal flow of a program's execution. It can be caused by logical errors or external factors like file not found, network failure, or invalid user input.

---

## 🧩 Checked vs Unchecked Exceptions

| Type              | Description                                                                 | Examples                          |
|-------------------|-----------------------------------------------------------------------------|-----------------------------------|
| **Checked**       | Checked at compile-time. Must be either caught or declared in the method.   | `IOException`, `SQLException`     |
| **Unchecked**     | Occur at runtime. Not checked at compile-time.                              | `NullPointerException`, `ArithmeticException` |

```java
// Checked Exception
public void readFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
}

// Unchecked Exception
int divide = 10 / 0; // ArithmeticException
```

---

## 🔄 try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero.");
} finally {
    System.out.println("This block always executes.");
}
```

- `try`: Code that might throw an exception.
- `catch`: Handles specific exception types.
- `finally`: Always executes, used for cleanup (e.g., closing streams).

---

## 🧰 Try-with-Resources (Java 7+)

Automatically manages resources (e.g., files, sockets) without needing explicit finally blocks.

```java
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line = reader.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
```

- Resources must implement `AutoCloseable`
- Closes resources even if exceptions occur

---

## 🔀 Multi-catch Blocks

Allows you to catch multiple exceptions in a single block.

```java
try {
    // code that may throw IOException or SQLException
} catch (IOException | SQLException ex) {
    ex.printStackTrace();
}
```

> 🔹 All exceptions must be **unrelated** in hierarchy.

---

## 🧨 throw vs throws

| Keyword   | Purpose                              | Used In       |
|-----------|--------------------------------------|---------------|
| `throw`   | Used to explicitly throw an exception | Inside method |
| `throws`  | Declares exceptions that may be thrown| Method signature |

```java
// throw example
throw new IllegalArgumentException("Invalid input");

// throws example
public void readFile() throws IOException {
    // reading logic
}
```

---

## 🛠️ Custom Exceptions

You can define your own exception by extending `Exception` or `RuntimeException`.

```java
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
```

Usage:
```java
if (age < 18) {
    throw new InvalidAgeException("Age must be 18+");
}
```

---

## ✅ Best Practices

- Catch only **specific** exceptions.
- Don't suppress exceptions without handling/logging.
- Use `finally` or try-with-resources for resource cleanup.
- Avoid catching `Exception` or `Throwable` broadly.
- Prefer **custom exceptions** for clarity.
- Provide **meaningful messages**.

---

## 🔁 Exception Propagation

Uncaught exceptions propagate up the call stack until handled:

```java
void methodA() {
    methodB();
}

void methodB() {
    throw new NullPointerException();
}
```

- `methodA()` -> `methodB()` -> Exception propagates to JVM → Program crashes

---

## ⛓️ Chained Exceptions

Preserve original cause when throwing new exceptions:

```java
try {
    // code that fails
} catch (IOException e) {
    throw new CustomException("Failed to process", e); // Add original exception
}
```

Retrieve root cause with `getCause()`:

```java
catch (CustomException ex) {
    Throwable rootCause = ex.getCause();
}
```

---

## 🚫 Common Pitfalls

- **Swallowing Exceptions:**
```java
try { ... }
catch (Exception e) {} // Empty catch block
```

- **Catching Throwable:**
```java
catch (Throwable t) { ... } // Catches Errors too!
```

- **Resource Leaks:**
```java
FileInputStream fis = new FileInputStream("file.txt");
// Forgot to close in finally
```

- **Overly Broad Catches:**
```java
catch (Exception e) { ... } // Hides specific exceptions
```

---

## 🔄 Exception Handling Workflow

1. **Prevention:** Validate inputs to avoid exceptions
```java
if (divisor == 0) throw new IllegalArgumentException();
```
2. **Handling:** Use try-catch for recoverable errors
3. **Propagation:** Declare `throws` for non-recoverable errors
4. **Logging:** Always log exceptions (`logger.log()`)
5. **Cleanup:** Use `finally` or try-with-resources

---

## 📜 final vs finally vs finalize

| Term       | Purpose                                          |
|------------|--------------------------------------------------|
| `final`    | Makes variables immutable, prevents inheritance |
| `finally`  | Cleanup code after try-catch                    |
| `finalize()`| Deprecated method for object cleanup (avoid)   |

---

## 🆕 Java 16: Pattern Matching for instanceof

Simplify exception type checks:

```java
try { ... }
catch (Exception e) {
    if (e instanceof SQLException sqlEx) {
        System.out.println(sqlEx.getErrorCode());
    }
}
```

---

## 📊 Exception Handling Checklist

- ✅ Is the exception recoverable? → Handle with try-catch
- ✅ Is the exception a programming error? → Fix the code
- ✅ Should the caller handle it? → Declare `throws`
- ✅ Are resources involved? → Use try-with-resources
- ✅ Is the exception type clear? → Use custom exceptions

---

## 🧪 Common Exceptions

| Exception                     | Cause                                      |
|-------------------------------|---------------------------------------------|
| `NullPointerException`        | Accessing object methods on null            |
| `ArrayIndexOutOfBounds`       | Invalid index in array access               |
| `IllegalArgumentException`    | Passing invalid arguments                   |
| `IOException`                 | Failure in I/O operations                   |
| `ClassCastException`          | Invalid casting between types               |
| `FileNotFoundException`       | File not found                              |
| `NumberFormatException`       | Calling `Integer.parseInt("text")`         |
| `ConcurrentModificationException`| Modifying collection during iteration   |
| `UnsupportedOperationException` | Calling unsupported method (e.g. immutable list) |

---

## 🌍 Global Exception Handling (Spring Boot)

Global exception handling allows centralizing error handling logic to improve maintainability and ensure consistent responses.

### 📦 Using @ControllerAdvice

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An error occurred: " + ex.getMessage());
    }
}
```

- `@ControllerAdvice` or `@RestControllerAdvice`: Declares global exception handler.
- `@ExceptionHandler`: Specifies method to handle specific exception types.
- Allows returning custom response bodies with status codes.

### ✅ Benefits

- Keeps controller logic clean
- Encourages DRY principle
- Ensures consistent error responses
- Ideal for REST APIs and production systems

### 💡 Enhancements

- Return custom error object with timestamp, error code, and path
- Log stack traces using SLF4J or Logback
- Customize validation errors via `MethodArgumentNotValidException`

---

## 🔁 Exception Handling in Java

Java uses five keywords for handling exceptions:
- `try`
- `catch`
- `finally`
- `throw`
- `throws`

These allow developers to manage both expected and unexpected issues in a structured and robust way.

---

## 📖 References
- [Java Exception Handling - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html)
- [Java Exception Handling - GeeksforGeeks](https://www.geeksforgeeks.org/exceptions-in-java/)
- [Java Exception Handling - Baeldung](https://www.baeldung.com/java-exceptions)
- [Java Exception Handling - Javatpoint](https://www.javatpoint.com/exception-handling-in-java)