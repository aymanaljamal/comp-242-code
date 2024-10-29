package application;


public class QUAZ {

	static StackLinkedStack<Integer> soso = new StackLinkedStack<>();

	public static void main(String[] args) {
		pushSorted(50, soso);
		pushSorted(20, soso);
		pushSorted(70, soso);
		pushSorted(40, soso);
		pushSorted(80, soso);
		printBackword(soso);
	}


	// O(N)
	private static void pushSorted(int data, StackLinkedStack<Integer> stack) {
	    if (stack.isEmpty() || data > stack.peek().getData()) {
	        stack.push(data);
	    } else {
	        Integer temp = stack.pop().getData();
	        pushSorted(data, stack);
	        stack.push(temp);
	    }
	}

	// O(N)
	public static void printBackword(StackLinkedStack<Integer> stack) {
		if (!stack.isEmpty()) {
			Node<Integer> data = stack.pop();
		
			System.out.print(data + " ");
			printBackword(stack);
			stack.push(data.getData());
		}
	}

}
