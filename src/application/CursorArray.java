package application;

public class CursorArray<T extends Comparable<T>> {

	private CNode<T>[] cursorArray;

	public CursorArray(int capacity) {
		cursorArray = new CNode[capacity];
		initialization();
	}

	public int initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++)
			cursorArray[i] = new CNode<T>(null, i + 1);
		cursorArray[cursorArray.length - 1] = new CNode<>(null, 0);
		return 0;
	}

	public int malloc() {
		int p = cursorArray[0].next;
		cursorArray[0].next = cursorArray[p].next;
		return p;
	}

	public void free(int p) {
		cursorArray[p] = new CNode<T>(null, cursorArray[0].next);
		cursorArray[0].next = p;
	}

	public void traversListRe(int l) {
		System.out.print("list " + l + " -->");
		traversListRe1(l);

	}

	public void traversListRe1(int l) {

		if (isNull(l) || isEmpty(l)) {
			System.out.println("null");
			return;
		}
		l = cursorArray[l].next;
		System.out.print(cursorArray[l] + "-->");

		traversListRe1(l);

	}

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	public boolean isEmpty(int l) {
		return cursorArray[l].next == 0;
	}

	public boolean isLast(int p) {
		return cursorArray[p].next == 0;
	}

	public int createList() {
		int l = malloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new CNode<T>(null, 0);
		return l;
	}

	public void insertAtHead(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new CNode<T>(data, cursorArray[l].next);
			cursorArray[l].next = p;
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void insertList(T data, int l) {

		int p = malloc();

		if (l == 0) {
			cursorArray[p] = new CNode<T>(data, 0);
			return;
		}
		while (cursorArray[l].next != 0) {
			l = cursorArray[l].next;
		}
		cursorArray[p] = new CNode<T>(data, 0);
		cursorArray[l].next = p;
	}

	public void insertListRec(T data, int l) {

		if (isLast(l) || l == 0) {
			int p = malloc();
			cursorArray[p] = new CNode<T>(data, 0);
			if (l != 0) {
				cursorArray[l].next = p;
			}
			return;
		}

		insertListRec(data, cursorArray[l].next);
	}

	public void insertSorted(T data, int l) {
		if (data == null) {
			throw new IllegalArgumentException("Data cannot be null");
		}

		int p = malloc();
		cursorArray[p] = new CNode<T>(data, 0);
		if (cursorArray[l] == null) {
			return;
		}

		if (l == 0) {
			cursorArray[p].next = l;

		}
		if ((l != 0 && ((Comparable<T>) data).compareTo(cursorArray[l].data) < 0)) {
			cursorArray[p].next = l;
		}

		int previous = l;
		int current = cursorArray[l].next;
		while (current != 0) {

			if (cursorArray[current] == null) {
				throw new NullPointerException("cursorArray[current] is null");
			}
			if (((Comparable<T>) data).compareTo(cursorArray[current].data) <= 0) {
				break;
			}
			previous = current;
			current = cursorArray[current].next;
		}

		cursorArray[p].next = current;
		cursorArray[previous].next = p;

	}

	public void traversList(int l) {
		System.out.print("list " + l + " -->");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			System.out.print(cursorArray[l] + "-->");
		}
		System.out.println("null");
	}

	public int find(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			if (cursorArray[l].data.equals(data))
				return l;
		}
		return -1; // not found
	}

	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].next].data.equals(data))
				return l;
			l = cursorArray[l].next;
		}
		return -1; // not found
	}

	public T delete(T data, int l) {
	    int prev = findPrevious(data, l);
	    if (prev != -1) {
	        int deleteIndex = cursorArray[prev].next;
	        if (deleteIndex != 0) {
	            T deletedData = cursorArray[deleteIndex].data;
	            cursorArray[prev].next = cursorArray[deleteIndex].next;
	            free(deleteIndex);
	            return deletedData;
	        }
	    }
	    return null;
	}


	public int listLength(int l) {
		if (isLast(l))
			return 0;
		int count = 0;
		while (!isLast(l)) {
			count++;
			l = cursorArray[l].next;
		}
		return count;
	}

	public int listLengthRec(int l) {
		return listLengthRec(l, 0);
	}

	private int listLengthRec(int l, int count) {
		if (isEmpty(l))
			return count;
		return listLengthRec(cursorArray[l].next, ++count);
	}

	public void clear(int l) {
		int p = 0;
		for (; !isLast(l); p = cursorArray[l].next) {
			cursorArray[l].next = cursorArray[p].next;
			free(p);
		}

	}

	public void clearRec(int l) {
		int p = cursorArray[l].next;
		if (!isEmpty(l)) {
			cursorArray[l].next = cursorArray[p].next;
			free(p);
			clearRec(l);

		} else {
			return;
		}
	}

	public void removeList(int l) {
		int p = 0;
		for (; !isLast(l); p = cursorArray[l].next) {
			cursorArray[l].next = cursorArray[p].next;
			free(p);
		}

		free(l);
	}

	public void removeListRec(int l) {
		if (isEmpty(l))
			free(l);
		else {
			int p = cursorArray[l].next;
			cursorArray[l].next = cursorArray[p].next;
			free(p);
			removeListRec(l);
		}
	}

	public void mergeLists(int l1, int l2) {
		if (isEmpty(l1) && isEmpty(l2)) {
			return;
		}

		if (isEmpty(l1)) {
			l1 = l2;
			return;
		}

		while (!isLast(l1)) {
			l1 = cursorArray[l1].next;
		}
		int teampDummy = l2;
		l2 = cursorArray[l2].next;
		free(teampDummy);
		cursorArray[l1].setNext(l2);
	}

	public String toString(int l) {
		StringBuilder sb = new StringBuilder();
		sb.append("list " + l + " -->");

		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;

			sb.append(cursorArray[l].data.toString() + "-->");
		}

		sb.append("null");
		return sb.toString();
	}

	public int mergeAndSortLists(int l1, int l2) {
		int newList = createList();
		if (newList == -1)
			return -1;

		l1 = cursorArray[l1].next;
		while (l1 != -1 && cursorArray[l1].data != null) {
			insertSorted(cursorArray[l1].data, newList);
			l1 = cursorArray[l2].next;
		}

		l2 = cursorArray[l2].next;
		while (l2 != -1 && cursorArray[l2].data != null) {
			insertSorted(cursorArray[l2].data, newList);
			l2 = cursorArray[l2].next;
		}

		return newList;
	}

	public CNode get(int l, CNode<T> data) {

		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[l].data.compareTo((T) data) == 0)
				return cursorArray[l];
			l = cursorArray[l].next;
		}
		return null;
	}
	public T removeFromHead(int l) {
	    if (isNull(l) || isEmpty(l)) {  
	        System.out.println("List does not exist or is empty.");
	        return null;
	    }
	    int firstNode = cursorArray[l].next; 
	    if (firstNode == 0) {
	        System.out.println("List is already empty.");
	        return null;
	    }
	    T data = cursorArray[firstNode].data; 
	    cursorArray[l].next = cursorArray[firstNode].next;
	    free(firstNode); 
	    return data;
	}
	public void addToHead(T data, int l) {
	    if (isNull(l)) {
	        System.out.println("List does not exist.");
	        return;
	    }
	    int p = malloc();  
	    if (p != 0) {
	        cursorArray[p] = new CNode<>(data, cursorArray[l].next); 
	        cursorArray[l].next = p;  
	    } else {
	        System.out.println("Error: Out of space!!!");
	    }
	}
	 public T getDataAt(int index) {
	        if (index > 0 && index < cursorArray.length && cursorArray[index] != null) {
	            return cursorArray[index].data;
	        } else {
	            return null; 
	        }
	    }

	   
	    public int getNextIndex(int index) {
	        if (index > 0 && index < cursorArray.length && cursorArray[index] != null) {
	            return cursorArray[index].next;
	        } else {
	            return 0;  
	        }
	    }
	    public static void main(String[] args) {
	        int capacity = 10;
	        CursorArray<String> cursorArray = new CursorArray<>(capacity);

	        // Creating a list and inserting elements
	        int list = cursorArray.createList();
	        cursorArray.insertAtHead("Hello", list);
	        cursorArray.insertAtHead("World", list);
	        cursorArray.insertAtHead("CursorArray", list);

	        // Display the list
	        System.out.println("Initial list traversal:");
	        cursorArray.traversList(list);

	        // Inserting more elements
	        cursorArray.insertList("Java", list);
	        cursorArray.insertList("Programming", list);

	        // Display the list again
	        System.out.println("List after inserting more elements:");
	        cursorArray.traversList(list);

	        // Remove from head
	        System.out.println("Element removed from head: " + cursorArray.removeFromHead(list));

	        // Display the list after removal
	        System.out.println("List after removing an element from head:");
	        cursorArray.traversList(list);

	        // Try to find an element
	        String searchElement = "Java";
	        int foundIndex = cursorArray.find(searchElement, list);
	        if (foundIndex != -1) {
	            System.out.println("Element '" + searchElement + "' found at index: " + foundIndex);
	        } else {
	            System.out.println("Element '" + searchElement + "' not found.");
	        }

	        // Clear the list
	        cursorArray.clear(list);
	        System.out.println("List after clearing:");
	        cursorArray.traversList(list);

	        // Check if the list is empty
	        System.out.println("Is the list empty? " + cursorArray.isEmpty(list));
	    }

}