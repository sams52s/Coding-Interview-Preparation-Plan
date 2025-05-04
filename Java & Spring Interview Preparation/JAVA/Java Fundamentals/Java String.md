# 📌 Java String – Complete Guide

In Java, **String** is a **class** that represents a sequence of characters. It is one of the most commonly used types in Java and is **immutable**, meaning once created, its value cannot be changed.

---

## 🧱 1. What is a String in Java?

- `String` is a **class** in `java.lang` package
- Strings are **immutable**
- Used to store **textual data**

### 🔍 Declaration:
```java
String s = "Hello World";
```
This creates a string literal in the **string pool**.

---

## 🧪 2. Ways to Create Strings

### ✅ Using string literals (preferred):
```java
String s1 = "Hello";
```

### ✅ Using `new` keyword:
```java
String s2 = new String("Hello");
```
- Creates a new object in heap even if an identical string exists in pool.

---

## 📦 3. String Pool (Interning)

Java maintains a special memory area called the **String Constant Pool** to optimize memory usage.

### 🔍 Example:
```java
String a = "Java";
String b = "Java";
System.out.println(a == b); // true (same reference)

String c = new String("Java");
System.out.println(a == c); // false
System.out.println(a.equals(c)); // true
```

---

## 📚 4. Common String Methods

| Method                  | Description                           |
|-------------------------|---------------------------------------|
| `length()`              | Returns string length                 |
| `charAt(int index)`     | Character at specific index           |
| `substring(begin, end)` | Returns a substring                   |
| `contains(String)`      | Checks for presence of a substring    |
| `equals(String)`        | Compares string content               |
| `equalsIgnoreCase()`    | Case-insensitive comparison           |
| `indexOf(char)`         | Finds index of character              |
| `toUpperCase()`         | Converts to uppercase                 |
| `toLowerCase()`         | Converts to lowercase                 |
| `replace(old, new)`     | Replaces characters                   |
| `trim()`                | Removes leading/trailing whitespace   |
| `split(String regex)`   | Splits string by delimiter            |
| `valueOf()`             | Converts other types to string        |

---

## 🧵 5. String Immutability

- Once created, a string cannot be modified.
- Any modification creates a **new object**.
- Ensures **security, thread safety, and memory efficiency**.

```java
String s = "Java";
s.concat(" Language"); // new string created
System.out.println(s); // still prints "Java"

// To update the reference:
s = s.concat(" Language");
System.out.println(s); // now prints "Java Language"
```

### Why Immutability Matters:
- **Security**: Safe to pass strings between methods/threads without defensive copying
- **Hashcode Caching**: Improves performance in collections
- **String Pool Optimization**: Enables string interning for memory efficiency
- **Thread Safety**: No synchronization needed for shared strings

---

## 🚀 5.1. String Immutability & DSA Considerations

When solving algorithm problems, string immutability has significant performance implications:

### 🔍 Time Complexity of Common String Operations

| Operation                | Time Complexity | Space Complexity | Notes                                    |
|--------------------------|-----------------|------------------|------------------------------------------|
| Access by index (charAt) | O(1)            | O(1)             | Direct access to character array         |
| String concatenation (+) | O(n+m)          | O(n+m)           | Creates new string of combined length    |
| substring(begin, end)    | O(end-begin)    | O(end-begin)     | Java 7+ copies the characters            |
| contains()               | O(n*m)          | O(1)             | n = string length, m = pattern length    |
| indexOf()                | O(n*m)          | O(1)             | Naive implementation                     |
| equals()                 | O(n)            | O(1)             | Must check every character               |
| startsWith(), endsWith() | O(n)            | O(1)             | In worst case                            |

### 🧩 Common String Algorithm Patterns

1. **Sliding Window**:
   ```java
   // Find longest substring without repeating characters
   public int lengthOfLongestSubstring(String s) {
       int n = s.length();
       Set<Character> set = new HashSet<>();
       int maxLength = 0, i = 0, j = 0;
       
       while (i < n && j < n) {
           if (!set.contains(s.charAt(j))) {
               set.add(s.charAt(j++));
               maxLength = Math.max(maxLength, j - i);
           } else {
               set.remove(s.charAt(i++));
           }
       }
       return maxLength;
   }
   ```

2. **Two Pointers**:
   ```java
   // Check if string is palindrome
   public boolean isPalindrome(String s) {
       int left = 0, right = s.length() - 1;
       while (left < right) {
           if (s.charAt(left) != s.charAt(right)) {
               return false;
           }
           left++;
           right--;
       }
       return true;
   }
   ```

3. **String Builder for In-place Modification**:
   ```java
   // Reverse words in a string
   public String reverseWords(String s) {
       String[] words = s.trim().split("\\s+");
       StringBuilder result = new StringBuilder();
       
       for (int i = words.length - 1; i >= 0; i--) {
           result.append(words[i]);
           if (i > 0) result.append(" ");
       }
       
       return result.toString();
   }
   ```

### 🔥 DSA Optimization Tips

1. **Avoid String Concatenation in Loops**:
   ```java
   // Bad - O(n²) time complexity
   String result = "";
   for (int i = 0; i < n; i++) {
       result += s.charAt(i);
   }
   
   // Good - O(n) time complexity
   StringBuilder sb = new StringBuilder();
   for (int i = 0; i < n; i++) {
       sb.append(s.charAt(i));
   }
   String result = sb.toString();
   ```

2. **Character Frequency Counting**:
   ```java
   // Count character frequency (for strings with ASCII characters)
   public Map<Character, Integer> getFrequencyMap(String s) {
       // More efficient for ASCII strings
       int[] freq = new int[128]; 
       for (char c : s.toCharArray()) {
           freq[c]++;
       }
       
       // For Unicode strings
       Map<Character, Integer> map = new HashMap<>();
       for (char c : s.toCharArray()) {
           map.put(c, map.getOrDefault(c, 0) + 1);
       }
       return map;
   }
   ```

3. **String Comparison in Algorithms**:
   ```java
   // Use equals() not == for string comparison
   String a = new String("test");
   String b = new String("test");
   
   if (a.equals(b)) {  // Correct: compares content
       // Do something
   }
   
   if (a == b) {  // Incorrect: compares references
       // Will not execute
   }
   ```

4. **String Hashing for Fast Comparison**:
   ```java
   // Rolling hash for pattern matching (Rabin-Karp algorithm)
   public int rabinKarp(String text, String pattern) {
       int m = pattern.length();
       int n = text.length();
       int patternHash = 0, textHash = 0;
       int prime = 101;
       int power = 1;
       
       // Calculate initial hash values
       for (int i = 0; i < m; i++) {
           patternHash = (patternHash + pattern.charAt(i)) % prime;
           textHash = (textHash + text.charAt(i)) % prime;
           if (i > 0) power = (power * 256) % prime;
       }
       
       for (int i = 0; i <= n - m; i++) {
           if (patternHash == textHash) {
               // Potential match, verify character by character
               boolean match = true;
               for (int j = 0; j < m; j++) {
                   if (text.charAt(i + j) != pattern.charAt(j)) {
                       match = false;
                       break;
                   }
               }
               if (match) return i;
           }
           
           // Calculate rolling hash for next window
           if (i < n - m) {
               textHash = (256 * (textHash - text.charAt(i) * power) + text.charAt(i + m)) % prime;
               if (textHash < 0) textHash += prime;
           }
       }
       
       return -1; // Not found
   }
   ```

5. **Trie Data Structure for String Problems**:
   ```java
   // Efficient prefix search with Trie
   class TrieNode {
       TrieNode[] children = new TrieNode[26]; // For lowercase English letters
       boolean isEndOfWord;
   }
   
   class Trie {
       TrieNode root = new TrieNode();
       
       // Insert a word into the trie - O(word length)
       public void insert(String word) {
           TrieNode node = root;
           for (char c : word.toCharArray()) {
               int index = c - 'a';
               if (node.children[index] == null) {
                   node.children[index] = new TrieNode();
               }
               node = node.children[index];
           }
           node.isEndOfWord = true;
       }
       
       // Search for exact word - O(word length)
       public boolean search(String word) {
           TrieNode node = getNode(word);
           return node != null && node.isEndOfWord;
       }
       
       // Check if any word starts with prefix - O(prefix length)
       public boolean startsWith(String prefix) {
           return getNode(prefix) != null;
       }
       
       private TrieNode getNode(String word) {
           TrieNode node = root;
           for (char c : word.toCharArray()) {
               int index = c - 'a';
               if (node.children[index] == null) return null;
               node = node.children[index];
           }
           return node;
       }
   }
   ```

---

## 🔁 6. StringBuilder vs StringBuffer vs String

| Feature           | String       | StringBuilder    | StringBuffer     |
|-------------------|--------------|------------------|------------------|
| Mutability        | Immutable    | Mutable          | Mutable          |
| Thread-safe       | Yes          | ❌ No            | ✅ Yes           |
| Performance       | Slower       | Fastest          | Moderate         |
| Storage           | String Pool  | Heap             | Heap             |
| Initial Capacity  | N/A          | 16 chars         | 16 chars         |
| Growth Formula    | N/A          | (capacity*2)+2   | (capacity*2)+2   |

- Use `StringBuilder` for **single-threaded** environments
- Use `StringBuffer` for **multi-threaded** environments
- Use `String` when **immutability** is required

### Performance Examples:

```java
// BAD: Creates many intermediate String objects
String result = "";
for(int i = 0; i < 10000; i++) {
    result += "some text";  // Very inefficient
}

// GOOD: Uses a single mutable object
StringBuilder sb = new StringBuilder();
for(int i = 0; i < 10000; i++) {
    sb.append("some text");  // Much more efficient
}
String result = sb.toString();
```

### Common Methods:
- `append(String)`: Add content
- `insert(int, String)`: Insert at position
- `delete(int, int)`: Remove substring
- `reverse()`: Reverse contents
- `setLength(int)`: Change buffer size
- `capacity()`: Get current capacity

---

## 📌 7. String Interning

You can manually place a string into the pool using `intern()`:
```java
String a = new String("Hello");
String b = a.intern();
String c = "Hello";
System.out.println(b == c); // true - same reference from pool
```
- Now `b` points to the pooled version.

### Advanced Interning Details:
- Interning is performed automatically for **string literals**
- Java 7+ moved string pool from PermGen to Heap space
- JVM flag `-XX:StringTableSize` can adjust the string pool size
- Use with caution on large strings to avoid memory pressure
- Performance trade-off: memory vs. equality check speed

---

## 📊 8. String Performance Optimizations

### String Concatenation:
```java
// Modern Java compiler optimizes this:
String s = "Hello" + " " + "World"; // Compiled to: "Hello World"

// But not this (uses StringBuilder internally):
String s = header + body + footer;
```

### Java 9+ Compact Strings:
- Internally uses byte[] instead of char[] when possible
- Latin-1 encoding (1 byte) used for ASCII instead of UTF-16 (2 bytes)
- Reduces memory footprint by ~50% for English text
- Automatically handled by JVM

### Java 11+ String Methods:
- `strip()`, `stripLeading()`, `stripTrailing()`: Unicode-aware trim
- `isBlank()`: Checks if string is empty or whitespace
- `lines()`: Stream of lines from multi-line string
- `repeat(int)`: Repeats a string n times

### Java 12+ String Methods:
- `indent(int)`: Adjusts indentation
- `transform(Function)`: Apply a function to string

### Java 15+ Text Blocks:
```java
String html = """
              <html>
                <body>
                  <p>Hello, World!</p>
                </body>
              </html>
              """;
```

---

## 📝 9. Regular Expressions with Strings

```java
// Check if string matches a pattern
boolean isEmail = email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$");

// Find all matches
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher("I have 2 apples and 3 oranges");
while (matcher.find()) {
    System.out.println(matcher.group()); // Prints: 2, 3
}

// Replace using regex
String result = "Hello World".replaceAll("\\s+", "-"); // "Hello-World"
```

### Common Pattern Flags:
- `Pattern.CASE_INSENSITIVE`: Case-insensitive matching
- `Pattern.MULTILINE`: `^` and `$` match line starts/ends
- `Pattern.DOTALL`: `.` matches any character including newlines

---

## 🌐 10. Character Encodings

- Java strings are UTF-16 encoded internally
- String methods work with Unicode code points and code units
- BMP (Basic Multilingual Plane) characters use 1 char
- Supplementary characters use 2 chars (surrogate pairs)

```java
String s = "😀";  // Surrogate pair (single emoji)
System.out.println(s.length());      // 2 (code units)
System.out.println(s.codePointCount(0, s.length())); // 1 (actual character)

// Iterating code points (not chars)
s.codePoints().forEach(c -> System.out.printf("U+%04X ", c));
```

---

## 🧠 Interview Questions & Answers

- ❓ **What is the difference between `==` and `.equals()` for strings?**
  > `==` checks reference equality; `.equals()` checks value equality.
  ```java
  String a = "test"; 
  String b = new String("test");
  System.out.println(a == b);       // false (different references)
  System.out.println(a.equals(b));  // true (same content)
  ```

- ❓ **Why are strings immutable in Java?**
  > To ensure thread safety, enable caching, security (e.g., class loading, network connections), and hashcode consistency. Immutability guarantees that once a String is created, it can be safely shared between multiple threads without synchronization.

- ❓ **How is memory managed for strings?**
  > Java uses the string constant pool to reuse immutable string objects. String literals are stored in this pool, while strings created with `new` are in the regular heap unless interned. Since Java 7, the string pool is located in the heap rather than PermGen/Metaspace.

- ❓ **When should you use `StringBuilder` over `String`?**
  > Use StringBuilder when you need to modify strings frequently (e.g., inside loops, concatenating multiple values) to avoid creating many intermediate String objects which impacts performance and memory usage.

- ❓ **Can string concatenation inside a loop cause performance issues?**
  > Yes. Each concatenation creates a new String object, leading to O(n²) complexity. Use StringBuilder instead for O(n) performance.

- ❓ **What does `intern()` do?**
  > Moves a string to the pool or returns the reference if already there, allowing reference equality (`==`) checks instead of content equality (`.equals()`).

- ❓ **Is `String` final? Can we extend it?**
  > Yes, `String` is final to prevent inheritance and preserve immutability guarantees. You cannot extend String.

- ❓ **How does `substring()` behave in Java 7 vs Java 6?**
  > Java 7+ avoids memory leaks by copying substrings instead of referencing original. In Java 6, substring shared the same character array as the original string, which could cause memory leaks.

- ❓ **What's the impact of String.intern() on memory?**
  > Excessive use of String.intern() can increase memory pressure on the string pool. However, judicious use can save memory by eliminating duplicate strings.

- ❓ **What happens when you concatenate strings with "+"?**
  > The Java compiler translates it to StringBuilder operations. Multiple concatenations in a single statement use one StringBuilder, but concatenations across statements or in loops create multiple objects.

- ❓ **How does Java 9's compact string implementation work?**
  > Java 9+ uses a byte[] rather than char[] to store strings, with a flag indicating whether LATIN-1 (1 byte/char) or UTF-16 (2 bytes/char) encoding is used. This reduces memory usage by up to 50% for ASCII-only strings.

- ❓ **What's the difference between `trim()` and `strip()` methods?**
  > `trim()` removes characters with codepoint ≤ U+0020 (space), while `strip()` (Java 11+) removes all Unicode whitespace, making it more internationally aware.

- ❓ **How can you efficiently compare strings ignoring case?**
  > Use `String.equalsIgnoreCase()` rather than converting both strings to uppercase/lowercase which creates new string objects.

- ❓ **What's the time complexity of String.indexOf()?**
  > O(n×m) where n is the length of the string and m is the length of the substring being searched.

- ❓ **How would you implement a custom string interning solution?**
  > Use a WeakHashMap<String, WeakReference<String>> to store unique strings while allowing garbage collection.

- ❓ **What's the best way to join multiple strings with a delimiter?**
  > Use `String.join()` (Java 8+) or `StringJoiner` rather than manual concatenation.
  ```java
  String joined = String.join(", ", "Apple", "Banana", "Cherry"); // "Apple, Banana, Cherry"
  ```