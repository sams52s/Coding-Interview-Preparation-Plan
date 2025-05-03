# 🔧 Java Methods: Complete Guide

Java methods are reusable blocks of code that perform a specific task. They help organize code into logical units, increase reusability, and improve readability.

---

## 📘 1. What is a Method?
A **method** is a block of code that performs a specific task and can be executed when it is called.

```java
returnType methodName(parameters) {
    // method body
}
```

### Example:
```java
int add(int a, int b) {
    return a + b;
}
```

---

## 🧱 2. Method Components

| Component      | Description                                   |
|----------------|-----------------------------------------------|
| **Modifier**    | Access level (`public`, `private`, `protected`) |
| **Return Type** | Data type returned by method (`void`, `int`)   |
| **Name**        | Method identifier (should be meaningful)       |
| **Parameters**  | Input data types passed to method              |
| **Body**        | Code block to execute                         |
| **Return Value**| Final result returned by method (if any)       |

---

## 🧪 3. Method Types

### ✅ 1. Void Method (No return value)
```java
void greet() {
    System.out.println("Hello!");
}
```

### ✅ 2. Return Method (With return value)
```java
int square(int num) {
    return num * num;
}
```

### ✅ 3. Static Method
```java
static void printMessage() {
    System.out.println("Static method");
}
```

### ✅ 4. Instance Method
```java
public void showName(String name) {
    System.out.println(name);
}
```

---

## 🛠️ 4. Parameters

### ✅ Pass by Value (default in Java)
Java always passes method arguments by **value**, not reference.
```java
void modify(int x) {
    x = 10; // original value unchanged
}
```

### ✅ Multiple Parameters
```java
void display(String name, int age) {
    System.out.println(name + " - " + age);
}
```

### ✅ Method Overloading
Same method name with different parameter lists.
```java
void display(String name) {}
void display(String name, int age) {}
```

### ✅ Varargs
```java
void printNumbers(int... numbers) {
    for (int n : numbers) System.out.println(n);
}
```

---

## 🔄 5. Return Types

| Return Type | Description                          |
|-------------|--------------------------------------|
| `void`      | No return value                      |
| `int`, `double`, etc. | Primitive type return values     |
| `String`, `Object`     | Reference type return values     |
| `boolean`   | Often used in validations             |
| Custom Class| Used to return complex result objects |

```java
String getName() {
    return "Alice";
}
```

---

## 🔁 6. Calling Methods
```java
int result = add(5, 3);
System.out.println(result);
```

---

## 📋 7. Best Practices

- Use descriptive, verb-based names (`calculateTax()`, `fetchUser()`).
- Ensure **single responsibility** — do one thing well.
- Keep methods short: ideally 5–20 lines.
- Limit the number of parameters (≤ 3); consider objects or builder pattern.
- Document behavior using JavaDoc (`@param`, `@return`, `@throws`).
- Use default values where applicable.
- Reuse logic through helper methods.
- Avoid unnecessary state mutation inside methods.
- Prefer immutability for inputs where possible.

---

## 🚫 8. Bad Practices

- ❌ Poor naming like `doStuff()`, `process()`.
- ❌ Long methods trying to handle too many responsibilities.
- ❌ Ignoring `return` values from non-void methods.
- ❌ Using global variables or hidden side effects.
- ❌ Too many parameters (especially of same type).
- ❌ Mixing logic with output (e.g., printing inside calculation).
- ❌ Overloading methods unnecessarily with confusing signatures.
- ❌ Using `null` returns without documentation.

---

## 🧠 9. Interview Tips

- ❓ **What’s the difference between `void` and return methods?**  
  > A `void` method does not return any value, while a return method provides a result using the `return` keyword.

- ❓ **Can we overload `main()` method in Java?**  
  > Yes, Java allows overloading of `main()` with different parameter lists, but only the standard `public static void main(String[] args)` is used as the entry point.

- ❓ **Is Java pass-by-reference or pass-by-value?**  
  > Java is strictly pass-by-value. When passing objects, the reference itself is passed by value, not the object.

- ❓ **How does `static` affect method behavior?**  
  > A `static` method belongs to the class and can be called without creating an instance. It cannot access instance-level variables directly.

- ❓ **What’s method overloading vs overriding?**  
  > Overloading allows multiple methods with the same name but different parameters in the same class. Overriding means redefining a superclass method in a subclass.

- ❓ **Can methods return objects in Java?**  
  > Yes, methods can return objects of any class, including custom-defined types.

- ❓ **What is recursion in methods?**  
  > Recursion is when a method calls itself to solve a sub-problem. It must have a base case to terminate.

- ❓ **What are varargs in method parameters?**  
  > `varargs` allow passing a variable number of arguments of the same type. Syntax: `methodName(int... nums)`.

- ❓ **Can constructors be overloaded like methods?**  
  > Yes, constructors can be overloaded to allow different ways of object initialization.

- ❓ **What’s the role of method signature?**  
  > It consists of the method name and parameter list. It’s used to identify overloaded methods uniquely.

- ❓ **How does exception handling affect method design?**  
  > Methods should declare exceptions using `throws` if they propagate errors. Use `try-catch` internally only when recovery is possible.
