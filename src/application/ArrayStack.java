package application;

public class ArrayStack<T> {
	private Object[] s;
	private int n = -1;

	public ArrayStack(int capacity) {
		s = new Object[capacity];
	}

	public boolean isEmpty() {
		return n == -1;
	}
	public Object peek() {
		return s[n];
	}
	public int getN() {
		return n;
	}

	public void push(T data) {
		s[++n] = data;
	}

	public Object pop() {
		if (!isEmpty())
			return s[n--];
		return null;
	}

	public String toString() {
		String res = "Top-->";
		for (int i = n; i >= 0; i--)
			res += "[" + s[i] + "]-->";
		return res + "Null";
	}
	 public static void main(String[] args) {
	        ArrayStack<String> stack = new ArrayStack<>(5);  // Create a stack with a capacity of 5

	        // Test isEmpty when stack is actually empty
	        System.out.println("Is the stack empty? " + stack.isEmpty());  // Should return true

	        // Push elements onto the stack
	        stack.push("Hello");
	        stack.push("World");
	        stack.push("!");
	        
	        // Display the stack contents
	        System.out.println("Current stack: " + stack.toString());  // Should print from top to bottom
	        
	        // Peek at the top element
	        System.out.println("Top element: " + stack.peek());  // Should return "!"
	        
	        // Pop an element
	        System.out.println("Popped element: " + stack.pop());  // Should pop "!"
	        
	        // Display the stack after one pop
	        System.out.println("Stack after one pop: " + stack.toString());
	        
	        // Check isEmpty after some operations
	        System.out.println("Is the stack empty? " + stack.isEmpty());  // Should return false
	        
	        // Push more elements to test stack overflow handling (you should handle this in your implementation)
	        stack.push("New");
	        stack.push("Year");
	        stack.push("2024"); // Here, it might overflow, depending on prior pops

	        // Final state of the stack
	        System.out.println("Final stack: " + stack.toString());
	        
	        // Pop all to empty
	        while (!stack.isEmpty()) {
	            System.out.println("Popped: " + stack.pop());
	        }
	        
	        // Check if empty after popping all
	        System.out.println("Is the stack empty after popping all? " + stack.isEmpty());  // Should return true
	    }
}