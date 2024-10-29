package application;

public class LinkedList<A extends Comparable<A>> {
	Node<A> head;

	public void insert(A data) {
		Node<A> newNode = new <A>Node(data);
		if (head == null) {
			head = newNode;
		} else {
			Node<A> prev = null, curr = head;
			for (; curr != null && curr.getData().compareTo(data) <0; prev = curr, curr = curr.getNext())
				;
			if (prev == null) {
				newNode.setNext(curr);
				head = newNode;
			} else if (curr == null) {
				prev.setNext(newNode);
			} else {
				newNode.setNext(curr);
				prev.setNext(newNode);
			}

		}

	}
	public int length(Node<A> head) {
		if (head == null) {
			return 0;
		} else {
			return 1 + length(head.getNext());
		}
	}

	public Node<A> delete(A data) {
		if (head != null) {
			Node<A> prev = null, curr = head;

			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
				;

			if (curr != null && curr.getData().compareTo(data) == 0) {
				if (prev == null) {
					head = curr.getNext();
				} else {
					prev.setNext(curr.getNext());
				}
				return curr;
			}
		}
		return null;
	}

	public Node<A> find(A data) {

		if (head != null) {
			Node<A> curr = head;

			while (curr != null && curr.getData().compareTo(data) < 0) {
				curr = curr.getNext();
			}

			if (curr != null && curr.getData().compareTo(data) == 0) {
				return curr;
			}
		}

		return null;
	}

	

	
	 private Node<A> getAt(int index,Node <A>head) {
	        Node<A> current = head;
	        for (int i = 0; i < index && current != null; i++) {
	            current = current.getNext();
	        }
	        return current;
	    }
	 
	 
	 
	

	public void travars() {
		Node<A> curr = head;
		System.out.print("head-->");
		while (curr != null) {
			System.out.print(curr + "--");
			curr = (curr.getNext());
		}
		System.out.print("null");

	}



	public void reverse() {
	    int length = length(head); 
	    Object[] data = new Object[length];
	    Node<A> current = head;
	    int i = 0;

	   
	    while (current != null) {
	        data[i++] = current.getData();
	        current = current.getNext();
	    }

	    current = head;
	    i = length - 1;

	    
	    while (current != null) {
	        current.setData((A) data[i--]);
	        current = current.getNext();
	    }
	}

	 
	
	

	 public void reverseRecursive() {
	        reverseRecursive(head, 0,  length(head));
	    }

	    private void reverseRecursive(Node<A> current, int Index, int size) {
	        if (current == null || (Index >=( size / 2))) {
	            return;
	        }

	        Node<A> prev = getAt((size - Index) - 1,head);

	        A data = current.getData();
	        current.setData(prev.getData());
	        prev.setData(data);

	        reverseRecursive(current.getNext(), Index + 1, size);
	    }

	   
	    
    @Override
    public String toString() {
        String toStringLinked = "head -> ";
        Node <A> current = head;
        while (current != null) {
            toStringLinked += current.getData();
            if (current.getNext() != null) {
                toStringLinked += " -> ";
            }
            current = current.getNext();
        }
        return toStringLinked + " -> null";
    }
}
