package application;

public class DLinkedList<A extends Comparable<A>> {
	DNode<A> head;

	public void insert(A data) {
		DNode<A> newNode = new DNode<>(data);

		if (head == null) {

			head = newNode;
		} else if (head.getData().compareTo(data) >= 0) {

			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		} else {

			DNode<A> current = head;
			while (current.getNext() != null && current.getNext().getData().compareTo(data) < 0) {
				current = current.getNext();
			}

			newNode.setNext(current.getNext());
			if (current.getNext() != null) {
				current.getNext().setPrev(newNode);
			}
			current.setNext(newNode);
			newNode.setPrev(current);
		}
	}

	public boolean delete(A data) {
		if (head == null) {

			return false;
		}

		if (head.getData().equals(data)) {
			head = head.getNext();
			if (head != null) {
				head.setPrev(null);
			}
			return true;
		}

		DNode<A> current = head;
		while (current != null && !current.getData().equals(data)) {
			current = current.getNext();
		}

		if (current == null) {
			return false;
		}

		if (current.getNext() != null) {
			current.getNext().setPrev(current.getPrev());
		}
		current.getPrev().setNext(current.getNext());
		return true;
	}

	// (ayman aljamal-----------Hi)
	public boolean find(A data) {
		DNode<A> current = head;
		while (current != null) {
			if (current.getData().equals(data)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public int length() {
		int length = 0;
		DNode<A> current = head;
		while (current != null) {
			length++;
			current = current.getNext();
		}
		return length;
	}

	public int lengthRecursive() {
		return lengthRecursive(head, 0);
	}

	private int lengthRecursive(DNode<A> current, int length) {
		if (current == null) {
			return length;
		}
		return lengthRecursive(current.getNext(), ++length);

	}

	public void travars() {
		DNode<A> current = head;
		System.out.print("head-->");
		while (current != null) {
			System.out.print(current + "--");
			current = (current.getNext());
		}
		System.out.print("null");

	}

	void travarsRecursive() {
		travarsRecursive(head);
	}

	private void travarsRecursive(DNode<A> current) {

		if (current == head) {

			System.out.print("head-->");
		}
		if (current == null) {
			System.out.print("null");
			return;
		}
		System.out.print("{" + current.toString() + "}" + "<=>");

		travarsRecursive(current.getNext());
	}

	void travaersBakeRecursive() {
		System.out.print("null");
		travaersBakeRecursive(head);
		System.out.print("-->head");
	}

	private void travaersBakeRecursive(DNode<A> current) {

		if (current != null) {

			travaersBakeRecursive(current.getNext());
			System.out.print("<=>" + "{" + current.toString() + "}");
		}

	}

	public int count(A data) {
		DNode<A> current = head;
		int count = 0;
		while (current != null) {
			if (current.getData().compareTo(data) == 0) {
				count++;
			}
			current = current.getNext();
		}
		return count;
	}

	public int countRecursive(A data) {
		return countRecursive(data, 0, head);
	}

	private int countRecursive(A data, int count, DNode<A> current) {
		if (current == null) {
			return count;
		} else if (current.getData().compareTo(data) == 0) {
			count++;
		} else if (current.getData().compareTo(data) > 0) {
			return count;
		}
		return countRecursive(data, count, current.getNext());
	}

	public void romoveDuplicate() {
		DNode<A> current = head;
		while (current != null && current.getNext() != null) {
			if (current.getData().compareTo((A) current.getNext().getData()) == 0) {
				delete((A) current.getData());
			}
			current = current.getNext();
		}

	}

	public void romoveDuplicateRecursive() {
		romoveDuplicateRecursive(head);
	}

	private void romoveDuplicateRecursive(DNode<A> current) {
		if (current == null || current.getNext() == null) {
			return;
		} else if (current.getData().compareTo((A) current.getNext().getData()) == 0) {
			delete((A) current.getData());
		}
		romoveDuplicateRecursive(current.getNext());
	}

	public void reverseRecursive() {
		head = reverseRecursive(head);
	}

	private DNode<A> reverseRecursive(DNode<A> current) {
		if (current == null) {
			return null;
		}

		if (current.getNext() == null) {
			current.setNext(current.getPrev());
			current.setPrev(null);
			return current;
		}
		DNode<A> newHead = reverseRecursive(current.getNext());
		current.getNext().setNext(current);
		current.setPrev(current.getNext());
		current.setNext(null);

		return newHead;
	}

	@Override
	public String toString() {
		String toStringDLinkedList = "Head-->";
		DNode<A> current = this.head;
		while (current != null) {
			toStringDLinkedList += "[" + current + "]";
			current = current.getNext();
			if (current != null)
				toStringDLinkedList += "<=>";
		}
		return toStringDLinkedList + "-->NULL";
	}
}
