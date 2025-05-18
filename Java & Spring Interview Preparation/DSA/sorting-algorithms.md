# 📊 Sorting Algorithms in Data Structures

Sorting is the process of arranging data in a particular order (ascending/descending). Efficient sorting is critical for optimizing search algorithms, data analysis, and real-time applications.

---

## 🔁 1. Bubble Sort

- Repeatedly swaps adjacent elements if they are in the wrong order.

```java
void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```

- Best: O(n), Avg/Worst: O(n²)  
- Stable: ✅ Yes

---

## 🪜 2. Insertion Sort

- Builds the sorted list one element at a time.

```java
void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        int key = arr[i], j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
```

- Best: O(n), Worst: O(n²)  
- Stable: ✅ Yes

---

## 📌 3. Selection Sort

- Selects the minimum element and places it at the beginning.

```java
void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIdx]) minIdx = j;
        }
        int temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;
    }
}
```

- Time: O(n²)  
- Stable: ❌ No

---

## 🧠 4. Merge Sort

- Divide and conquer algorithm. Recursively splits and merges sorted halves.

```java
void mergeSort(int[] arr, int l, int r) {
    if (l < r) {
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}

void merge(int[] arr, int l, int m, int r) {
    int[] left = Arrays.copyOfRange(arr, l, m + 1);
    int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
    int i = 0, j = 0, k = l;
    while (i < left.length && j < right.length)
        arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
    while (i < left.length) arr[k++] = left[i++];
    while (j < right.length) arr[k++] = right[j++];
}
```

- Time: O(n log n), Space: O(n)  
- Stable: ✅ Yes

---

## ⚡ 5. Quick Sort

- Selects a pivot and partitions the array around it.

```java
void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
}
```

- Best: O(n log n), Worst: O(n²)  
- In-place: ✅ Yes  
- Stable: ❌ No

---

## 🔄 6. Advanced Sorting Algorithms

### TimSort (Java's Arrays.sort())
```java
class TimSort {
    static final int MIN_MERGE = 32;
    
    public static void timSort(int[] arr) {
        int minRun = minRunLength(MIN_MERGE);
        // First, do insertion sort on small subarrays
        for (int i = 0; i < arr.length; i += minRun) {
            insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (arr.length - 1)));
        }
        // Start merging from size minRun
        for (int size = minRun; size < arr.length; size = 2 * size) {
            for (int left = 0; left < arr.length; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (arr.length - 1));
                if (mid < right)
                    merge(arr, left, mid, right);
            }
        }
    }
}
```

### 3-Way QuickSort (For Many Duplicates)
```java
void quickSort3Way(int[] arr, int low, int high) {
    if (high <= low) return;
    
    int lt = low, gt = high;
    int pivot = arr[low];
    int i = low + 1;
    
    while (i <= gt) {
        if (arr[i] < pivot)
            swap(arr, lt++, i++);
        else if (arr[i] > pivot)
            swap(arr, i, gt--);
        else
            i++;
    }
    
    quickSort3Way(arr, low, lt - 1);
    quickSort3Way(arr, gt + 1, high);
}
```

### IntroSort (Hybrid of QuickSort, HeapSort, and InsertionSort)
```java
class IntroSort {
    private static final int SIZE_THRESHOLD = 16;
    
    public static void sort(int[] arr) {
        int maxDepth = (int)(2 * Math.floor(Math.log(arr.length) / Math.log(2)));
        introSort(arr, 0, arr.length - 1, maxDepth);
    }
    
    private static void introSort(int[] arr, int low, int high, int maxDepth) {
        int size = high - low + 1;
        
        if (size < SIZE_THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }
        
        if (maxDepth == 0) {
            heapSort(arr, low, high);
            return;
        }
        
        int pivot = partition(arr, low, high);
        introSort(arr, low, pivot - 1, maxDepth - 1);
        introSort(arr, pivot + 1, high, maxDepth - 1);
    }
}
```

---

## 📈 Summary Table

| Algorithm       | Best     | Average  | Worst    | Stable | Space     |
|----------------|----------|----------|----------|--------|-----------|
| Bubble Sort     | O(n)     | O(n²)    | O(n²)    | ✅     | O(1)      |
| Insertion Sort  | O(n)     | O(n²)    | O(n²)    | ✅     | O(1)      |
| Selection Sort  | O(n²)    | O(n²)    | O(n²)    | ❌     | O(1)      |
| Merge Sort      | O(n log n)| O(n log n)| O(n log n)| ✅   | O(n)      |
| Quick Sort      | O(n log n)| O(n log n)| O(n²)   | ❌     | O(log n)  |

---

## 🛠️ When to Use What?

- **Bubble/Selection**: Simple but inefficient → avoid for large inputs  
- **Insertion Sort**: Best for nearly sorted arrays or small data sets  
- **Merge Sort**: Preferred when stability is needed and memory isn't constrained  
- **Quick Sort**: Best average performance; avoid worst case with good pivot

---

## 💼 Interview Questions & Problems

- Sort a linked list (Merge Sort)  
- Kth smallest/largest element (QuickSelect)  
- Merge k sorted arrays (Heap + Merge Sort)  
- Sort colors (Dutch National Flag using Quick Partition)  
- Sort strings based on custom order  

---

## 🎯 Advanced Interview Solutions

### Sort Nearly Sorted Array
```java
void sortNearlySorted(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    // Add first k + 1 elements
    for (int i = 0; i <= k && i < arr.length; i++)
        pq.offer(arr[i]);
        
    int index = 0;
    for (int i = k + 1; i < arr.length; i++) {
        arr[index++] = pq.poll();
        pq.offer(arr[i]);
    }
    
    while (!pq.isEmpty())
        arr[index++] = pq.poll();
}
```

### Sort Linked List (Merge Sort)
```java
ListNode sortList(ListNode head) {
    if (head == null || head.next == null)
        return head;
        
    // Find middle
    ListNode middle = getMiddle(head);
    ListNode nextOfMiddle = middle.next;
    middle.next = null;
    
    // Sort recursively
    ListNode left = sortList(head);
    ListNode right = sortList(nextOfMiddle);
    
    // Merge
    return mergeSorted(left, right);
}
```

### QuickSelect (Kth Smallest)
```java
int findKthSmallest(int[] arr, int k) {
    return quickSelect(arr, 0, arr.length - 1, k - 1);
}

int quickSelect(int[] arr, int low, int high, int k) {
    if (low == high)
        return arr[low];
        
    int pivot = partition(arr, low, high);
    
    if (pivot == k)
        return arr[k];
    else if (pivot > k)
        return quickSelect(arr, low, pivot - 1, k);
    else
        return quickSelect(arr, pivot + 1, high, k);
}
```

---

## 🧪 Additional Interview Questions

### ❓ How would you sort a million integers with limited RAM?
**✅ A:** Use external merge sort:
```java
class ExternalSort {
    public static void externalSort(String inputFile, String outputFile, int chunkSize) {
        // 1. Split into sorted chunks
        List<String> chunks = splitAndSort(inputFile, chunkSize);
        // 2. Merge chunks using min heap
        mergeChunks(chunks, outputFile);
    }
}
```

### ❓ How to sort strings by custom order?
**✅ A:**
```java
class CustomSort {
    public String sortByCustomOrder(String s, String order) {
        int[] rank = new int[26];
        for (int i = 0; i < order.length(); i++)
            rank[order.charAt(i) - 'a'] = i;
            
        return s.chars()
                .mapToObj(ch -> (char)ch)
                .sorted((a, b) -> rank[a-'a'] - rank[b-'a'])
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
```

### ❓ How would you implement a stable QuickSort?
**✅ A:**
```java
class StableQuickSort {
    static class Pair {
        int val, pos;
        Pair(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }
    }
    
    public static void stableQuickSort(int[] arr) {
        Pair[] pairs = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++)
            pairs[i] = new Pair(arr[i], i);
            
        stableQuickSort(pairs, 0, pairs.length - 1);
        
        for (int i = 0; i < arr.length; i++)
            arr[i] = pairs[i].val;
    }
    
    private static void stableQuickSort(Pair[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            stableQuickSort(arr, low, pi - 1);
            stableQuickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(Pair[] arr, int low, int high) {
        Pair pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j].val < pivot.val || 
               (arr[j].val == pivot.val && arr[j].pos < pivot.pos)) {
                swap(arr, i++, j);
            }
        }
        swap(arr, i, high);
        return i;
    }
}
```

### ❓ How would you sort an array with many duplicates efficiently?
**✅ A:**
```java
class CountingSort {
    public static void sortWithDuplicates(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        // Count frequencies and find range
        for (int num : arr) {
            freq.merge(num, 1, Integer::sum);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // Fill array using frequencies
        int index = 0;
        for (int i = min; i <= max; i++) {
            int count = freq.getOrDefault(i, 0);
            while (count-- > 0) {
                arr[index++] = i;
            }
        }
    }
}
```

### ❓ How do you handle sorting with custom comparators in real-world scenarios?
**✅ A:**
```java
class ComplexSorting {
    static class DataPoint {
        String category;
        int priority;
        LocalDateTime timestamp;
        
        // Complex sorting with multiple criteria
        public static Comparator<DataPoint> getComparator(boolean ascending) {
            return Comparator
                .comparing(DataPoint::getCategory)
                .thenComparing(DataPoint::getPriority, 
                    ascending ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .thenComparing(DataPoint::getTimestamp);
        }
    }
    
    public void sortData(List<DataPoint> data, String orderBy) {
        switch (orderBy) {
            case "priority_asc":
                Collections.sort(data, DataPoint.getComparator(true));
                break;
            case "priority_desc":
                Collections.sort(data, DataPoint.getComparator(false));
                break;
            default:
                Collections.sort(data, 
                    Comparator.comparing(DataPoint::getTimestamp));
        }
    }
}
```

### ❓ How would you implement parallel sorting for large datasets?
**✅ A:**
```java
class ParallelSort {
    private static final int THRESHOLD = 10_000;
    
    public static void parallelSort(int[] arr) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new SortTask(arr, 0, arr.length - 1));
    }
    
    static class SortTask extends RecursiveAction {
        private final int[] arr;
        private final int low, high;
        
        SortTask(int[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }
        
        @Override
        protected void compute() {
            if (high - low < THRESHOLD) {
                Arrays.sort(arr, low, high + 1);
                return;
            }
            
            int mid = low + (high - low) / 2;
            invokeAll(
                new SortTask(arr, low, mid),
                new SortTask(arr, mid + 1, high)
            );
            merge(arr, low, mid, high);
        }
    }
}
```

---

## 📝 References

- [GeeksforGeeks – Sorting](https://www.geeksforgeeks.org/sorting-algorithms/)  
- [VisuAlgo – Sorting Visualizations](https://visualgo.net/en/sorting)  
- [Oracle Java Arrays.sort()](https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html)
