package application;

public class SQueue<T extends Comparable<T>> implements Queueable<T> {
	private StackLinkedStack<T> stack = new StackLinkedStack<>();

	@Override
	public T dequeue() {
		T data = null;
		if (!stack.isEmpty()) {
			data = stack.pop().getData();
		}
		return data;
	}

	@Override
	public T getFront() {
		T data = null;
		if (!stack.isEmpty()) {
			data = stack.peek().getData();
		}
		return data;
	}

	@Override
	public void enqueue(T data) {
		StackLinkedStack<T> tempStack = new StackLinkedStack<>();
		while (!stack.isEmpty()) {
			tempStack.push(stack.pop().getData());
		}
		stack.push(data);
		while (!tempStack.isEmpty()) {
			stack.push(tempStack.pop().getData());
		}
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		if (!stack.isEmpty()) {
			stack.clear();
		}

	}

	@Override
	public String toString() {
		String string = "";
		StackLinkedStack<T> tempStack = new StackLinkedStack<>();
		while (!stack.isEmpty()) {
			tempStack.push(stack.pop().getData());
		}
		while (!tempStack.isEmpty()) {
			Node<T> node = tempStack.pop();
			stack.push(node.getData());
			string += node.getData() + " ";
		}

		return string;
	}

	 public static void main(String[] args) {
	        SQueue<String> queue = new SQueue<>();  // Create a queue

	        // Test isEmpty when queue is actually empty
	        System.out.println("Is the queue empty? " + queue.isEmpty());  // Should return true

	        // Enqueue elements into the queue
	        queue.enqueue("Hello");
	        queue.enqueue("World");
	        queue.enqueue("Java");

	        // Display the queue contents
	        System.out.println("Queue after enqueues: " + queue);  // Expected: Hello World Java 

	        // Peek at the front element
	        System.out.println("Front element: " + queue.getFront());  // Should return "Hello"

	        // Dequeue an element
	        System.out.println("Dequeued element: " + queue.dequeue());  // Should dequeue "Hello"

	        // Display the queue after one dequeue
	        System.out.println("Queue after one dequeue: " + queue);  // Expected: World Java

	        // Check isEmpty after some operations
	        System.out.println("Is the queue empty? " + queue.isEmpty());  // Should return false

	        // Enqueue another element
	        queue.enqueue("Queue");
	        System.out.println("Queue after enqueueing 'Queue': " + queue);  // Expected: World Java Queue

	        // Clear the queue
	        queue.clear();
	        System.out.println("Queue after clearing:");
	        System.out.println("Is the queue empty after clearing? " + queue.isEmpty());  // Should return true
	    }
}