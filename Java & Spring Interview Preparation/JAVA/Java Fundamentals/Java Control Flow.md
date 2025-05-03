# 🧭 Java Control Flow: The Ultimate Guide

Control flow statements in Java determine the execution path based on conditions or repetitions. This guide covers all essential constructs, advanced patterns, best practices, anti-patterns, and design principles.

---

## 🔀 1. Conditional Statements

### `if` Statement
```java
if (condition) {
    // execute block
}
```

### `if-else` Statement
```java
if (condition) {
    // if block
} else {
    // else block
}
```

### `if-else-if` Ladder
```java
if (condition1) {
    // block1
} else if (condition2) {
    // block2
} else {
    // default block
}
```

---

## 🧭 2. `switch-case` Statement

### Traditional Syntax
```java
switch (expression) {
    case value1:
        // block
        break;
    case value2:
        // block
        break;
    default:
        // fallback
}
```

### Modern Switch (Java 14+)
```java
String result = switch (input) {
    case "A", "B" -> "Accepted";
    case "C" -> "Conditional";
    default -> "Rejected";
};
```

#### 🔹 Using `yield` for multi-line blocks in modern switch:
```java
String result = switch (input) {
    case "A" -> {
        System.out.println("Case A");
        yield "Accepted";
    }
    case "B" -> {
        System.out.println("Case B");
        yield "Also Accepted";
    }
    default -> {
        yield "Rejected";
    }
};
```

---

## 🔁 3. Looping Constructs

### `for` Loop
```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

### `while` Loop
```java
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}
```

### `do-while` Loop
```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 5);
```

### Enhanced `for-each` Loop
```java
int[] numbers = {1, 2, 3};
for (int number : numbers) {
    System.out.println(number);
}
```

---

## ⛔ Loop Control Modifiers

- `break`: exits loop/switch
- `continue`: skips current iteration
- `return`: exits method
- Labeled break: `break outer;`

```java
outer:
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (i == j) break outer;
    }
}
```

---

## 📋 Best Practices

| Practice | Description |
|---------|-------------|
| Use braces `{}` always | Avoids bugs in one-liners |
| Prefer `switch` for constants | Clean alternative to multiple `if-else` |
| Use enhanced `for` | More readable for collections |
| Keep loops small | Focus on single tasks |
| Extract logic | Use helper methods |
| Check for null/empty | Defensive programming |
| Add default in switch | Ensures fallback case |
| Validate before switch | Prevent `NullPointerException` |
| Use meaningful variable names | Improves clarity |

---

## ❌ Bad Practices

| Anti-Pattern | Problem |
|--------------|---------|
| No braces | Refactor breaks logic easily |
| Nested ifs | Reduces readability |
| Magic values | Use constants or enums |
| Missing break in switch | Unintended fall-through |
| Empty loop body | Likely a bug |
| Complex inline conditionals | Hard to debug and understand |

---

## 🧠 Advanced Concepts

### 🔹 Nested Control Flows
Control flows can be nested; avoid deep nesting by using guard clauses.

### 🔹 Guard Clauses
```java
if (!isValid()) return;
// Proceed with valid case
```

### 🔹 Replace conditionals with polymorphism
Use design patterns like Strategy or Command to reduce `if-else` chains.

---

## 🧼 Design Rules & Principles

| Principle | Description |
|----------|-------------|
| SRP | Loops and methods should do one thing |
| DRY | Avoid repeating logic in conditionals |
| Fail Fast | Exit early on invalid cases |
| Defensive Code | Always handle default or edge cases |
| Declarative > Imperative | Prefer streams over loops if applicable |

---

## 📚 Interview Notes

- ❓ **What happens if `break` is missed in switch?**  
  > ✅ Causes unintended fall-through to the next case block.

- ❓ **How to exit nested loops?**  
  > ✅ Use **labeled `break`** or refactor the logic into a method.

- ❓ **When to use `for` vs `while`?**  
  > ✅ Use `for` when the number of iterations is **known**; use `while` when the condition is **evaluated dynamically**.

- ❓ **Loop pitfalls?**  
  > ✅ Watch out for **off-by-one errors**, **infinite loops**, and **incorrect loop boundaries**.

- ❓ **What is the difference between `for` and enhanced `for`?**  
  > ✅ Enhanced `for` simplifies iteration over **arrays and collections** and prevents **index errors**, but does **not support index-based access** or modification.

- ❓ **What is the purpose of `yield` in modern switch?**  
  > ✅ `yield` returns a value from a switch block when using multi-line case bodies (Java 14+).

- ❓ **When should you prefer `switch` over `if-else`?**  
  > ✅ Use `switch` when comparing a single variable against discrete constant values (e.g., enums, strings, integers).

- ❓ **Can you return from inside a loop?**  
  > ✅ Yes, `return` exits the method completely, even from within a loop.

- ❓ **How to avoid deep nesting in control flow?**  
  > ✅ Use **guard clauses** (`if (invalid) return;`) or extract logic into helper methods.

- ❓ **Why is `break` important in traditional switch-case?**  
  > ✅ Prevents unintentional execution of subsequent cases (fall-through behavior).

- ❓ **Can `switch` be used with enums or strings?**  
  > ✅ Yes, since Java 5 for enums and Java 7 for strings.

---

## Summary Table

| Feature           | `if-else` | `switch` | `for` | `while` | `do-while` |
|------------------|-----------|----------|-------|---------|------------|
| Multiple Branches| Yes       | Yes      | No    | No      | No         |
| Loop Repeats     | No        | No       | Yes   | Yes     | Yes        |
| Pre/Post Check   | Pre       | -        | Pre   | Pre     | Post       |
| Use Case         | Logic     | Discrete | Count | Condition | At least once |

---

## 🔚 Final Tip

> Always write control flow that **reveals intent clearly**. Readable code is maintainable code.

