package application;

public class StackLinkedStack<A extends Comparable<A>> {
	private Node<A> topNode;

	public void push(A data) {
		Node<A> newNode = new Node<A>(data);
		newNode.setNext(topNode);
		topNode = newNode;
	}

	public Node<A> pop() {
		Node<A> toDel = topNode;
		if (topNode != null)
			topNode = topNode.getNext();
		return toDel;
	}

	public Node<A> peek() {
		return topNode;
	}

	public int length() {
		int length = 0;
		Node<A> curr = topNode;
		while (curr != null) {
			length++;
			curr = curr.getNext();
		}
		return length;
	}

	public boolean isEmpty() {
		return (topNode == null);
	}

	public void clear() {
		topNode = null;
	}
	  public static void main(String[] args) {
	        StackLinkedStack<String> stack = new StackLinkedStack<>();  // Create a stack

	        // Test isEmpty when stack is actually empty
	        System.out.println("Is the stack empty? " + stack.isEmpty());  // Should return true

	        // Push elements onto the stack
	        stack.push("Hello");
	        stack.push("World");
	        stack.push("Java");

	        // Display the top element
	        System.out.println("Top element after pushes: " + (stack.peek() != null ? stack.peek().getData() : "No top element"));

	        // Check the length of the stack
	        System.out.println("Current stack length: " + stack.length());  // Expected length is 3

	        // Pop an element
	        System.out.println("Popped element: " + (stack.pop() != null ? stack.pop().getData() : "No element to pop"));

	        // Display the top element after one pop
	        System.out.println("Top element after one pop: " + (stack.peek() != null ? stack.peek().getData() : "No top element"));

	        // Check isEmpty after some operations
	        System.out.println("Is the stack empty? " + stack.isEmpty());  // Should return false

	        // Push another element
	        stack.push("Stack");

	        // Final state of the stack
	        System.out.println("Final state of the stack:");
	        while (!stack.isEmpty()) {
	            System.out.println("Popped: " + stack.pop().getData());
	        }

	        // Check if empty after popping all
	        System.out.println("Is the stack empty after popping all? " + stack.isEmpty());  // Should return true

	        // Clear the stack
	        stack.clear();
	        System.out.println("Is the stack empty after clearing? " + stack.isEmpty());  // Should return true
	    }
}
