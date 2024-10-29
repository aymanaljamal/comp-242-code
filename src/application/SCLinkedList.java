package application;

public class SCLinkedList<T extends Comparable<T>> {
	private CursorLinkedList<T> list;

	public SCLinkedList(int capacity) {
		this.list = new CursorLinkedList<>(capacity);
	}

	public void push(T data) {
		list.insertAtHead(data);
	}

	public T pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty.");
			return null;
		}

		T data = list.peekFirst();
		list.deleteFirst();
		return data;
	}

	public T peek() {
		if (isEmpty()) {
			System.out.println("Stack is empty.");
			return null;
		}

		T data = list.peekFirst();

		return data;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void clear() {
		list.clear();
	}

	public void traverse() {
		list.traverse();
	}
	 public static void main(String[] args) {
	        SCLinkedList<String> stack = new SCLinkedList<>(10);  // Create a stack with a capacity of 10

	        // Test isEmpty when stack is actually empty
	        System.out.println("Is the stack empty? " + stack.isEmpty());  // Should return true

	        // Push elements onto the stack
	        stack.push("Hello");
	        stack.push("World");
	        stack.push("!");

	        // Display the stack contents
	        System.out.println("Current stack:");
	        stack.traverse();  // Expected: ! -> World -> Hello -> null

	        // Peek at the top element
	        System.out.println("Top element: " + stack.peek());  // Should return "!"

	        // Pop an element
	        System.out.println("Popped element: " + stack.pop());  // Should pop "!"

	        // Display the stack after one pop
	        System.out.println("Stack after one pop:");
	        stack.traverse();  // Expected: World -> Hello -> null

	        // Check isEmpty after some operations
	        System.out.println("Is the stack empty? " + stack.isEmpty());  // Should return false

	        // Push more elements to test stack functionality
	        stack.push("Java");
	        stack.push("Stack");

	        // Final state of the stack
	        System.out.println("Final state of the stack:");
	        stack.traverse();  // Expected: Stack -> Java -> World -> Hello -> null

	        // Clear the stack
	        stack.clear();
	        System.out.println("Stack after clearing:");
	        stack.traverse();  // Should show nothing as stack is cleared

	        // Check if empty after clearing
	        System.out.println("Is the stack empty after clearing? " + stack.isEmpty());  // Should return true
	    }
}
