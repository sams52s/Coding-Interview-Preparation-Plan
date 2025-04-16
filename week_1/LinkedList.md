/**
 * A generic implementation of a doubly linked list data structure.
 *
 * This LinkedList class provides an efficient way to store and manipulate a sequence of elements.
 * Each element (node) in the list contains a reference to the next and previous nodes, allowing
 * for efficient insertion and deletion operations at both ends or in the middle of the list.
 *
 * Key Features:
 * - Dynamic size: The list can grow or shrink as needed without pre-allocating memory.
 * - Bidirectional traversal: Each node maintains references to both its predecessor and successor.
 * - Efficient operations: Insertion and deletion operations have O(1) time complexity when the node
 *   reference is known.
 *
 * Usage:
 * - Add elements to the front or back of the list.
 * - Remove elements from the front, back, or a specific position.
 * - Iterate through the list in forward or reverse order.
 * - Access elements by their position in the list.
 *
 * Example:
 * ```java
 * LinkedList<Integer> list = new LinkedList<>();
 * list.addFirst(10); // Adds 10 to the front of the list
 * list.addLast(20);  // Adds 20 to the end of the list
 * list.removeFirst(); // Removes the first element (10)
 * list.removeLast();  // Removes the last element (20)
 * ```
 *
 * Type Parameters:
 * @param <T> The type of elements stored in the linked list.
 *
 * Public Methods:
 * - `void addFirst(T element)`: Adds an element to the front of the list.
 * - `void addLast(T element)`: Adds an element to the end of the list.
 * - `T removeFirst()`: Removes and returns the first element of the list.
 * - `T removeLast()`: Removes and returns the last element of the list.
 * - `T getFirst()`: Retrieves the first element without removing it.
 * - `T getLast()`: Retrieves the last element without removing it.
 * - `boolean isEmpty()`: Checks if the list is empty.
 * - `int size()`: Returns the number of elements in the list.
 *
 * Internal Structure:
 * - Each node in the list is represented by an inner class `Node<T>`, which contains:
 *   - `T data`: The data stored in the node.
 *   - `Node<T> next`: A reference to the next node in the list.
 *   - `Node<T> prev`: A reference to the previous node in the list.
 *
 * Performance Considerations:
 * - Accessing an element by index requires O(n) time complexity due to sequential traversal.
 * - Use this data structure when frequent insertions and deletions are required, especially at
 *   the ends of the list.
 *
 * Limitations:
 * - Not suitable for scenarios where random access is frequently required.
 * - Consumes more memory compared to arrays due to the storage of node references.
 */