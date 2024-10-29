package application;

public class CStack<T extends Comparable<T>> implements Stackable<T> {
	private CursorArray<T> stackCursorArray;
	private int p;
	private T top;

	public CStack(int capacity) {
		this.stackCursorArray = new CursorArray<>(capacity + 1);
		p = stackCursorArray.createList();
	}

	@Override
	public void push(T data) {
		stackCursorArray.addToHead(data, p);
		top = data;
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			return null;
		}

		T itemToPop = stackCursorArray.removeFromHead(p);

		if (!stackCursorArray.isEmpty(p)) {
			int newTopIndex = stackCursorArray.getNextIndex(p);
			top = stackCursorArray.getDataAt(newTopIndex);
		} else {
			top = null;
		}

		return itemToPop;
	}

	@Override
	public T peek() {
		return top;
	}

	@Override
	public boolean isEmpty() {
		return stackCursorArray.isEmpty(p);
	}

	@Override
	public void clear() {
		stackCursorArray.clear(p);
		top = null;
	}

	public static void main(String[] args) {
		CStack<String> stack = new CStack<>(5); // Create a stack with a capacity of 5

		// Test isEmpty when stack is actually empty
		System.out.println("Is the stack empty? " + stack.isEmpty()); // Should return true

		// Push elements onto the stack
		stack.push("Hello");
		stack.push("World");
		stack.push("!");

		// Display the top element
		System.out.println("Top element after pushes: " + stack.peek()); // Should return "!"

		// Pop an element
		System.out.println("Popped element: " + stack.pop()); // Should pop "!"

		// Display the top element after one pop
		System.out.println("Top element after one pop: " + stack.peek()); // Should return "World"

		// Check isEmpty after some operations
		System.out.println("Is the stack empty? " + stack.isEmpty()); // Should return false

		// Clear the stack
		stack.clear();
		System.out.println("Stack cleared.");

		// Check if empty after clearing
		System.out.println("Is the stack empty after clearing? " + stack.isEmpty()); // Should return true

		// Push more elements to test stack functionality after clearing
		stack.push("Java");
		stack.push("Python");
		System.out.println("Top element after pushing new elements: " + stack.peek()); // Should return "Python"

		// Final state of the stack
		while (!stack.isEmpty()) {
			System.out.println("Popped: " + stack.pop());
		}

		// Check if empty after popping all
		System.out.println("Is the stack empty after popping all? " + stack.isEmpty()); // Should return true
	}
}
