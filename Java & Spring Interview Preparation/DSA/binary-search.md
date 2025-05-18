# 🔎 Binary Search in Data Structures

**Binary Search** is an efficient algorithm used to find an element in a **sorted array**. It works by repeatedly dividing the search interval in half.

---

## 📌 Key Characteristics

- Requires a **sorted** array or data structure.
- Reduces search space by half each step.
- Time complexity is O(log n).

---

## 🔁 1. Iterative Implementation

```java
int binarySearch(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;  // prevents overflow
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}
```

---

## 🔁 2. Recursive Implementation

```java
int binarySearch(int[] arr, int low, int high, int target) {
    if (low > high) return -1;
    int mid = low + (high - low) / 2;
    if (arr[mid] == target) return mid;
    if (arr[mid] < target) return binarySearch(arr, mid + 1, high, target);
    else return binarySearch(arr, low, mid - 1, target);
}
```

---

## ✅ Best Use Cases

- Searching in sorted arrays/lists
- Finding upper/lower bounds (first/last occurrence)
- Efficient range queries in sorted datasets
- Invariant checks (peak finding, rotated arrays)

---

## 🧠 Edge Cases

- Array with all duplicates
- Empty array (return -1)
- Integer overflow → use `mid = low + (high - low)/2`
- Infinite loop if `low` and `high` are not updated properly

---

## 🧪 Common Interview Problems

1. **Search Insert Position** (Leetcode 35)  
2. **First and Last Position of Element** (LC 34)  
3. **Peak Element in Array**  
4. **Binary Search on Rotated Array** (LC 33)  
5. **Find Minimum in Rotated Array**  
6. **Find Square Root (Floor) of a Number**  
7. **Koko Eating Bananas** (binary search on answer space)  
8. **Capacity to Ship Packages in D Days**

---

## 📈 Time & Space Complexity

| Operation          | Complexity          |
|--------------------|---------------------|
| Best Case          | O(1)                |
| Average/Worst Case | O(log n)            |
| Space (Iterative)  | O(1)                |
| Space (Recursive)  | O(log n) recursion stack |

---

## 🧮 Binary Search on Answer (Advanced)

Used when the answer is not a position but a **value** (e.g., minimum capacity, speed).

```java
int searchMinCapacity(int[] weights, int D) {
    int low = Arrays.stream(weights).max().getAsInt();
    int high = Arrays.stream(weights).sum();
    while (low < high) {
        int mid = (low + high) / 2;
        if (canShip(weights, D, mid)) high = mid;
        else low = mid + 1;
    }
    return low;
}
```

---

## 🎯 Advanced Binary Search Variations

### 1. Search in Matrix (Sorted Rows & Columns)
```java
boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) return false;
    int m = matrix.length, n = matrix[0].length;
    int left = 0, right = m * n - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int element = matrix[mid / n][mid % n];
        
        if (element == target) return true;
        if (element < target) left = mid + 1;
        else right = mid - 1;
    }
    return false;
}
```

### 2. Aggressive Cows Problem (Binary Search on Answer)
```java
boolean canPlace(int[] stalls, int cows, int minDist) {
    int count = 1, lastPos = stalls[0];
    for (int i = 1; i < stalls.length; i++) {
        if (stalls[i] - lastPos >= minDist) {
            count++;
            lastPos = stalls[i];
        }
    }
    return count >= cows;
}

int maxMinDistance(int[] stalls, int cows) {
    Arrays.sort(stalls);
    int left = 1, right = stalls[stalls.length - 1] - stalls[0];
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (canPlace(stalls, cows, mid)) {
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

### 3. Find Peak Element in Bitonic Array
```java
int findPeak(int[] arr) {
    int left = 0, right = arr.length - 1;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] > arr[mid + 1]) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

---

## 🔍 Additional Interview Questions

### ❓ How would you implement exponential search?
**✅ A:**
```java
int exponentialSearch(int[] arr, int target) {
    if (arr[0] == target) return 0;
    
    int i = 1;
    while (i < arr.length && arr[i] <= target)
        i *= 2;
    
    return binarySearch(arr, i/2, Math.min(i, arr.length-1), target);
}
```

### ❓ How to find the minimum difference element in a sorted array?
**✅ A:**
```java
int minDiffElement(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return arr[mid];
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    
    if (right < 0) return arr[left];
    if (left >= arr.length) return arr[right];
    
    return Math.abs(arr[left] - target) < 
           Math.abs(arr[right] - target) ? arr[left] : arr[right];
}
```

### ❓ How to handle floating-point binary search?
**✅ A:**
```java
double sqrt(double n, double precision) {
    double left = 0, right = Math.max(1, n);
    
    while (right - left > precision) {
        double mid = left + (right - left) / 2;
        double sq = mid * mid;
        
        if (Math.abs(sq - n) < precision) return mid;
        if (sq < n) left = mid;
        else right = mid;
    }
    return left + (right - left) / 2;
}
```

### ❓ How to find the smallest element greater than target (ceiling)?
**✅ A:**
```java
int ceiling(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int ceil = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return arr[mid];
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            ceil = arr[mid];
            right = mid - 1;
        }
    }
    return ceil;
}
```

### ❓ How would you implement binary search for infinite arrays?
**✅ A:**
```java
int searchInfinite(ArrayReader reader, int target) {
    // ArrayReader.get(k) returns kth element or Integer.MAX_VALUE if out of bounds
    int left = 0, right = 1;
    
    // Find bounds first
    while (reader.get(right) < target) {
        left = right;
        right *= 2;
    }
    
    // Regular binary search
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int val = reader.get(mid);
        if (val == target) return mid;
        if (val < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

### ❓ How to find the first bad version using binary search?
**✅ A:**
```java
public int firstBadVersion(int n) {
    int left = 1, right = n;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (isBadVersion(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### ❓ How to find local minimum in an array?
**✅ A:**
```java
int findLocalMinimum(int[] arr) {
    int left = 0, right = arr.length - 1;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (mid > 0 && arr[mid - 1] < arr[mid]) {
            right = mid - 1;
        } else if (mid < arr.length - 1 && arr[mid + 1] < arr[mid]) {
            left = mid + 1;
        } else {
            return mid;
        }
    }
    return left;
}
```

### ❓ How to implement binary search on a stream of data?
**✅ A:**
```java
class StreamSearch {
    private List<Integer> buffer;
    
    public StreamSearch() {
        buffer = new ArrayList<>();
    }
    
    public int search(int target) {
        int left = 0, right = buffer.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) == target) return mid;
            if (buffer.get(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        
        buffer.add(left, target); // Insert maintaining sorted order
        return left;
    }
}
```

### ❓ How to find median of two sorted arrays using binary search?
**✅ A:**
```java
double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
        return findMedianSortedArrays(nums2, nums1);
    }
    
    int x = nums1.length, y = nums2.length;
    int low = 0, high = x;
    
    while (low <= high) {
        int partitionX = (low + high) / 2;
        int partitionY = (x + y + 1) / 2 - partitionX;
        
        int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
        int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
        
        int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
        int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];
        
        if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
            if ((x + y) % 2 == 0) {
                return (Math.max(maxLeftX, maxLeftY) + 
                        Math.min(minRightX, minRightY)) / 2.0;
            } else {
                return Math.max(maxLeftX, maxLeftY);
            }
        } else if (maxLeftX > minRightY) {
            high = partitionX - 1;
        } else {
            low = partitionX + 1;
        }
    }
    throw new IllegalArgumentException();
}
```

---

## 💬 Interview Q&A

### ❓ When is binary search better than linear search?
**✅** When data is sorted and random access is cheap.

### ❓ Can binary search be applied to linked lists?
**✅** Yes, but not efficient — no random access (O(n) to find mid).

### ❓ What happens if input is not sorted?
**❌** Binary search won't work — must use linear scan or sort first.

### ❓ Can binary search be used to find range of elements?
**✅** Yes, with lower_bound/upper_bound variations or custom logic.

### ❓ How to handle duplicates in binary search?
**✅** Adjust binary search to find first/last occurrence.

---

## ✅ Summary

Binary search is a high-performance search algorithm essential for sorted data structures. Mastering its variations (bounds, rotated arrays, binary-on-answer) is key for interviews and efficient coding.

---

## 📝 References

- [GeeksforGeeks – Binary Search](https://www.geeksforgeeks.org/binary-search/)  
- [Leetcode Binary Search Patterns](https://leetcode.com/explore/learn/card/binary-search/)  
- [VisuAlgo – Binary Search](https://visualgo.net/en/search)
