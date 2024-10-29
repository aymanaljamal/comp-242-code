package application;

public class CursorLinkedList<T extends Comparable<T>> {
    private CNode<T>[] cursorArray;
    private int headIndex;
    private final int capacity;
    private int freeIndex; // New variable to track the index of the first free node

    public CursorLinkedList(int capacity) {
        this.capacity = capacity;
        this.cursorArray = new CNode[capacity];
        headIndex = 0;
        freeIndex = 1; // Start from index 1 for the free list
        initializeCursorArray();
    }

    private void initializeCursorArray() {
        for (int i = 0; i < capacity; i++) {
            cursorArray[i] = new CNode<>(null, i + 1);
        }
        cursorArray[capacity - 1].next = 0; // Circular free list management
    }

    private int malloc() {
        if (freeIndex == 0) {
            System.out.println("No space available");
            return 0; // No available space
        }
        int newIndex = freeIndex;
        freeIndex = cursorArray[newIndex].next; // Update freeIndex to next free node
        return newIndex;
    }

    private void free(int index) {
        cursorArray[index].next = freeIndex;
        cursorArray[index].data = null;
        freeIndex = index; // Update freeIndex to newly freed node
    }

    public void insert(T data) {
        int newNodeIndex = malloc();
        if (newNodeIndex == 0) {
            System.out.println("No space to insert");
            return; // No space to insert
        }

        cursorArray[newNodeIndex].data = data;
        if (headIndex == 0 || cursorArray[headIndex].data.compareTo(data) > 0) {
            cursorArray[newNodeIndex].next = headIndex;
            headIndex = newNodeIndex;
        } else {
            int prevIndex = headIndex;
            int currentIndex = cursorArray[headIndex].next;
            while (currentIndex != 0 && cursorArray[currentIndex].data.compareTo(data) < 0) {
                prevIndex = currentIndex;
                currentIndex = cursorArray[currentIndex].next;
            }
            cursorArray[newNodeIndex].next = currentIndex;
            cursorArray[prevIndex].next = newNodeIndex;
        }
    }

    public boolean delete(T data) {
        if (headIndex == 0) {
            System.out.println("List is empty, cannot delete");
            return false; // List is empty
        }

        int currentIndex = headIndex;
        int prevIndex = 0;
        while (currentIndex != 0 && cursorArray[currentIndex].data.compareTo(data) != 0) {
            prevIndex = currentIndex;
            currentIndex = cursorArray[currentIndex].next;
        }
        if (currentIndex == 0) {
            System.out.println("Data not found, deletion failed");
            return false; // Data not found
        }

        if (prevIndex == 0) {
            headIndex = cursorArray[currentIndex].next;
        } else {
            cursorArray[prevIndex].next = cursorArray[currentIndex].next;
        }
        free(currentIndex);
        return true;
    }

    public void traverse() {
        System.out.print("List: ");
        int currentIndex = headIndex;
        while (currentIndex != 0) {
            System.out.print(cursorArray[currentIndex].data + " -> ");
            currentIndex = cursorArray[currentIndex].next;
        }
        System.out.println("null");
    }

    public T peekFirst() {
        if (headIndex == 0) return null;
        return cursorArray[headIndex].data;
    }

    public boolean deleteFirst() {
        if (headIndex == 0) return false;
        int toDelete = headIndex;
        headIndex = cursorArray[headIndex].next;
        free(toDelete);
        return true;
    }

    public boolean isEmpty() {
        return headIndex == 0;
    }

    public void clear() {
        headIndex = 0; // Reset head index
        freeIndex = 1; // Reset free index
        initializeCursorArray(); // Reinitialize cursor array
    }

    public void insertAtHead(T data) {
        int newHeadIndex = malloc();
        if (newHeadIndex == 0) {
            System.out.println("No space available to insert");
            return;
        }
        cursorArray[newHeadIndex].data = data;
        cursorArray[newHeadIndex].next = headIndex;
        headIndex = newHeadIndex;
    }

    public static void main(String[] args) {
        int capacity = 20;
        CursorLinkedList<String> linkedList = new CursorLinkedList<>(capacity);

        // Test isEmpty when the list is actually empty
        System.out.println("Is the list empty? " + linkedList.isEmpty());  // Should return true if empty

        // Insert elements at the head
        linkedList.insertAtHead("Hello");
        linkedList.insertAtHead("World");
        linkedList.insertAtHead("!");

        // Display the list
        System.out.println("List after inserts at head:");
        linkedList.traverse();  // Should show "! -> World -> Hello -> null"

        // Insert elements sorted
        linkedList.insert("Java");
        linkedList.insert("Python");
        linkedList.insert("C++");

        // Display the list after sorted inserts
        System.out.println("List after sorted inserts:");
        linkedList.traverse();  // Should display sorted order based on string comparison

        // Peek the first element
        System.out.println("First element: " + linkedList.peekFirst());  // Should return the first element in the list

        // Delete the first element
        if (linkedList.deleteFirst()) {
            System.out.println("First element deleted successfully.");
        } else {
            System.out.println("Failed to delete first element.");
        }

        // Display the list after deletion
        System.out.println("List after deleting the first element:");
        linkedList.traverse();

        // Try to delete a specific element
        String elementToDelete = "Java";
        if (linkedList.delete(elementToDelete)) {
            System.out.println("Element '" + elementToDelete + "' was deleted successfully.");
        } else {
            System.out.println("Element '" + elementToDelete + "' not found.");
        }

        // Final state of the list
        System.out.println("Final state of the list:");
        linkedList.traverse();

        // Clear the list
        linkedList.clear();
        System.out.println("List after clearing:");
        linkedList.traverse();  // Should show empty list

        // Check if the list is empty after clearing
        System.out.println("Is the list empty now? " + linkedList.isEmpty());  // Should return true
    }
}
