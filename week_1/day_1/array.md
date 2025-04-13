# Arrays in Java

## Introduction
An array in Java is a data structure that stores a fixed-size sequential collection of elements of the same type. They are useful for storing and managing collections of data. It is used to store multiple values in a single variable, instead of declaring separate variables for each value.
> For primitive arrays, elements are stored in a contiguous memory location, For non-primitive arrays, references are stored at contiguous locations

### Key features of Arrays:

- Contiguous Memory Allocation (for Primitives): Java array elements are stored in continuous memory locations, which means that the elements are placed next to each other in memory.
- Zero-based Indexing: The first element of the array is at index 0.
- Fixed Length: Once an array is created, its size is fixed and cannot be changed.
- Can Store Primitives & Objects: Java arrays can hold both primitive types (like int, char, boolean, etc.) and objects (like String, Integer, etc.)

*`Example:`*
> This example demonstrates how to initialize an array and traverse it using a for loop to print each element.
```java
public class Main {
    public static void main(String[] args)
    {

        // initializing array
        int[] arr = { 1, 2, 3, 4, 5 };

        // size of array
        int n = arr.length;

        // traversing array
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }
}
```
*`Output:`*
```
1 2 3 4 5 
```

## Declaration and Initialization
### Declaration
```java
dataType[] arrayName;
```

### Initialization
```java
arrayName = new dataType[size];
```

### Combined Declaration and Initialization
```java
dataType[] arrayName = new dataType[size];
```

### Example
```java
int[] numbers = new int[5];
```

## Accessing Array Elements
Array elements are accessed using their index, which starts from `0`.

### Example
```java
int[] numbers = {10, 20, 30, 40, 50};
System.out.println(numbers[0]); // Output: 10
```

## Iterating Through an Array

### Using a For Loop
```java
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

### Using an Enhanced For Loop
```java
for (int number : numbers) {
    System.out.println(number);
}
```

## Common Operations
### Finding the Length of an Array
```java
int length = numbers.length;
```

### Copying an Array
```java
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

### Sorting an Array
```java
Arrays.sort(numbers);
```

## Multidimensional Arrays
Java supports multidimensional arrays, such as 2D arrays.

### Declaration and Initialization
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

### Accessing Elements
```java
System.out.println(matrix[1][2]); // Output: 6

int firstElement = numbers[0]; 
System.out.println(firstElement);
```

### Change an Array Element
```java
// Changing the first element to 20
numbers[0] = 20; 
```

## In-Depth Concepts of Java Array

- In Java, all arrays are [dynamically allocated](https://www.geeksforgeeks.org/what-is-dynamic-memory-allocation/).
- Arrays may be stored in contiguous memory [consecutive memory locations].
- Since arrays are objects in Java, we can find their length using the object property length. This is different from C/C++, where we find length using size of.
- A Java array variable can also be declared like other variables with [] after the data type.
- The variables in the array are ordered, and each has an index beginning with 0.
- Java array can also be used as a static field, a local variable, or a method parameter.
>An array can contain primitives (int, char, etc.) and object (or non-primitive) references of a class, depending on the definition of the array. In the case of primitive data types, the actual values might be stored in contiguous memory locations (JVM does not guarantee this behavior). In the case of class objects, [the actual objects are stored in a heap segment.](https://www.geeksforgeeks.org/g-fact-46/)

## Default Array Values in Java
> If we don’t assign values to array elements and try to access them, the compiler does not produce an error as in the case of simple variables. Instead, it assigns values that aren’t garbage. 

*Below are the default assigned values.*
|S. No.	|Datatype	|Default Value|
|----|-----|----|
|1	|boolean	|false|
|2	|int	|0|
|3	|double	|0.0|
|4	|String	|null|
|5	|User-Defined Type	|null|

*`Example:`*
```Java

class ArrayDemo {
	public static void main(String[] args)
	{
		System.out.println("String array default values:");
		String str[] = new String[5];
		for (String s : str)
			System.out.print(s + " ");

		System.out.println(
			"\n\nInteger array default values:");
		int num[] = new int[5];
		for (int val : num)
			System.out.print(val + " ");

		System.out.println(
			"\n\nDouble array default values:");
		double dnum[] = new double[5];
		for (double val : dnum)
			System.out.print(val + " ");

		System.out.println(
			"\n\nBoolean array default values:");
		boolean bnum[] = new boolean[5];
		for (boolean val : bnum)
			System.out.print(val + " ");

		System.out.println(
			"\n\nReference Array default values:");
		ArrayDemo ademo[] = new ArrayDemo[5];
		for (ArrayDemo val : ademo)
			System.out.print(val + " ");
	}
}
```
*`Output:`*
``` bash
String array default values:
null null null null null 

Integer array default values:
0 0 0 0 0 

Double array default values:
0.0 0.0 0.0 0.0 0.0 

Boolean array default values:
false false false false false 

Reference Array default values:
null null null null null 
```

## Arrays of Objects in Java
> An array of objects is created like an array of primitive-type data items in the following way. 

*`Syntax:`*
```
Method 1:
ObjectType[] arrName;


Method 2:
ObjectType arrName[]; 
```
### Example of Arrays of Objects
*`Example:`*
> Here we are taking a student class and creating an array of Student with five Student objects stored in the array. The Student objects have to be instantiated using the constructor of the Student class, and their references should be assigned to the array elements.

```Java
//  an array of objects

class Student {
    public int roll_no;
    public String name;
  
    Student(int roll_no, String name){
        this.roll_no = roll_no;
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args){
      
        // declares an Array of Student
        Student[] arr;

        // allocating memory for 5 objects of type Student.
        arr = new Student[5];

        // initialize the elements of the array
        arr[0] = new Student(1, "aman");
        arr[1] = new Student(2, "vaibhav");
        arr[2] = new Student(3, "shikar");
        arr[3] = new Student(4, "dharmesh");
        arr[4] = new Student(5, "mohit");

        // accessing the elements of the specified array
        for (int i = 0; i < arr.length; i++)
            System.out.println("Element at " + i + " : { "
                               + arr[i].roll_no + " "
                               + arr[i].name+" }");
    }
}

```
*`Output:`*
```
Element at 0 : { 1 aman }
Element at 1 : { 2 vaibhav }
Element at 2 : { 3 shikar }
Element at 3 : { 4 dharmesh }
Element at 4 : { 5 mohit }
```
*`Example:`*
```Java
class Student{
    public String name;
  
    Student(String name){
        this.name = name;
    }
      
  	@Override
    public String toString(){
        return name;
    }
}
  

public class Main{
    public static void main (String[] args){
      
        // declares an Array and initializing the
      	// elements of the array
        Student[] myStudents = new Student[]{
          new Student("Dharma"),new Student("sanvi"),
          new Student("Rupa"),new Student("Ajay")
        };
  
        // accessing the elements of the specified array
        for(Student m:myStudents){    
            System.out.println(m);
        }
    }
}
```
*`Output:`*
```
Dharma
sanvi
Rupa
Ajay
```

## What happens if we try to access elements outside the array size?
> JVM throws ArrayIndexOutOfBoundsException to indicate that the array has been accessed with an illegal index. The index is either negative or greater than or equal to the size of an array.

Below code shows what happens if we try to access elements outside the array size:
```
// Code for showing error "ArrayIndexOutOfBoundsException"

public class GFG {
    public static void main(String[] args)
    {
        int[] arr = new int[4];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        arr[3] = 40;

        System.out.println(
            "Trying to access element outside the size of array");
        System.out.println(arr[5]);
    }
}
```
*`Output:`*
```
Trying to access element outside the size of array
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 4
at GFG.main(GFG.java:13)
```

## Multidimensional Arrays in Java
>Multidimensional arrays are arrays of arrays with each element of the array holding the reference of other arrays. A multidimensional array is created by appending one set of square brackets ([]) per dimension. 
*`Syntax:`*
There are 2 methods to declare Java Multidimensional Arrays as mentioned below:
```
// Method 1
datatype [][] arrayrefvariable;


// Method 2
datatype arrayrefvariable[][];
```
*`Declaration:`*
```
// 2D array or matrix
int[][] intArray = new int[10][20]; 


// 3D array
int[][][] intArray = new int[10][20][10]; 
```
*`Java Multidimensional Arrays Examples`*
```
// Java Program to demonstrate
// Multidimensional Array
import java.io.*;

class GFG {
    public static void main(String[] args){
      
        // Two Dimensional Array 
      	// Declared and Initialized
      	int[][] arr = new int[3][3];
        

        // Number of Rows
        System.out.println("Rows : " + arr.length);
      
        // Number of Columns
        System.out.println("Columns : " + arr[0].length);
    }
}
```
*`Output:`*
```
Rows:3
Columns:3
```

*`Example:`*
> Now, after declaring and initializing the array we will check how to Traverse the Multidimensional Array using for loop.



```Java
// Java Program to Multidimensional Array

// Driver Class
public class multiDimensional {
      // main function
    public static void main(String args[])
    {
        // declaring and initializing 2D array
        int arr[][] = { { 2, 7, 9 }, { 3, 6, 1 }, { 7, 4, 2 } };

        // printing 2D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(arr[i][j] + " ");

            System.out.println();
        }
    }
}
```
*`Output:`*
```
2 7 9 
3 6 1 
7 4 2 
```

### Passing Arrays to Methods
> Like variables, we can also pass arrays to methods. For example, the below program passes the array to method sum to calculate the sum of the array’s values.


```Java

// Java program to demonstrate
// passing of array to method

public class Test {
    // Driver method
    public static void main(String args[])
    {
        int arr[] = { 3, 1, 2, 5, 4 };

        // passing array to method m1
        sum(arr);
    }

    public static void sum(int[] arr)
    {
        // getting sum of array values
        int sum = 0;

        for (int i = 0; i < arr.length; i++)
            sum += arr[i];

        System.out.println("sum of array values : " + sum);
    }
}
```

*`Output:`*
```
sum of array values : 15
```

### Returning Arrays from Methods
>As usual, a method can also return an array. For example, the below program returns an array from method m1. 


```Java

// Java program to demonstrate
// return of array from method

class Test {
    // Driver method
    public static void main(String args[])
    {
        int arr[] = m1();

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public static int[] m1()
    {
        // returning  array
        return new int[] { 1, 2, 3 };
    }
}
```

*`Output:`*
```
1 2 3
``` 
## Conclusion
Arrays are a fundamental part of Java programming. They provide a way to store and manipulate collections of data efficiently. However, their size is fixed once declared, so consider using dynamic data structures like `ArrayList` if flexibility is needed.
