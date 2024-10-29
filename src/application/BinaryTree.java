package application;

public class BinaryTree<T extends Comparable<T>> {
	TNode<T> root;

	public BinaryTree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode(data);
		else
			insert(data, root);
	}

	public void insert(T data, TNode node) {
		if (data.compareTo((T) node.data) >= 0) {
			if (!node.hasRight())
				node.right = new TNode(data);
			else
				insert(data, node.right);
		} else { // insert into left subtree
			if (!node.hasLeft())
				node.left = new TNode(data);
			else
				insert(data, node.left);
		}
	}

	public void printInOrder() {
		printInOrderRecursive(root);
		System.out.println();
	}

	private void printInOrderRecursive(TNode<T> node) {
		if (node != null) {
			printInOrderRecursive(node.getLeft());
			System.out.print(node.getData() + " ");
			printInOrderRecursive(node.getRight());
		}
	}

	public void printInPreOrder() {
		printInPreOrderRecursive(root);
		System.out.println();
	}

	private void printInPreOrderRecursive(TNode<T> node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			printInPreOrderRecursive(node.getLeft());
			printInPreOrderRecursive(node.getRight());
		}
	}

	public void printInPostOrder() {
		printInPostOrderRecursive(root);
		System.out.println();
	}

	private void printInPostOrderRecursive(TNode<T> node) {
		if (node != null) {
			printInPostOrderRecursive(node.getRight());
			printInPostOrderRecursive(node.getLeft());
			System.out.print(node.getData() + " ");
		}
	}

	public void printInLevelOrder() {
		printInLevelRecursive(root);
		System.out.println();
	}

	private void printInLevelRecursive(TNode<T> node) {

		if (node == null) {
			return;
		} else {
			System.out.print(node.getData() + " ");
			printInLevelRecursive(node.getLeft());

			printInLevelRecursive(node.getRight());

		}
	}

	public void printInLevelOrder2() {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printGivenLevel(root, i);
			System.out.print(" ");
		}
		System.out.println();
	}

	private void printGivenLevel(TNode<T> node, int level) {
		if (node == null)
			return;
		if (level == 1)
			System.out.print(node.getData() + " ");
		else if (level > 1) {
			printGivenLevel(node.getLeft(), level - 1);
			printGivenLevel(node.getRight(), level - 1);
		}
	}

	private int height(TNode<T> node) {
		if (node == null)
			return 0;
		else {
			int lheight = height(node.getLeft());
			int rheight = height(node.getRight());

			if (lheight > rheight)
				return (lheight + 1);
			else
				return (rheight + 1);
		}
	}

	public int countParents() {
		return countParents(root);
	}

	private int countParents(TNode<T> node) {
		if (node == null || (node.left == null && node.right == null)) {
			return 0;
		}
		return 1 + countParents(node.left) + countParents(node.right);
	}

	// طريقة لحساب عدد الأبناء
	public int countChildren() {
		return countChildren(root);
	}

	private int countChildren(TNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + countChildren(node.left) + countChildren(node.right);
	}

	// طريقة لحساب عدد الأوراق
	public int countLeaves() {
		return countLeaves(root);
	}

	private int countLeaves(TNode<T> node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}
		return countLeaves(node.left) + countLeaves(node.right);
	}

	public int size() {
		return size(root);
	}

	private int size(TNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + size(node.left) + size(node.right);
	}

	public TNode delete(T data) {
		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = false;
		if (isEmpty())
			return null;
		while (current != null && !current.data.equals(data)) {
			parent = current;
			if (data.compareTo((T) current.data) < 0) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
		}
		if (current == null)
			return null;

		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.right = null;
			}
		}
		return parent;
	}

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.insert(435);
		tree.insert(5345);
		tree.insert(634);
		tree.insert(75435);
		tree.insert(54234);
		tree.insert(632423);
		tree.insert(734543556);
		System.out.println("In-Order traversal of the binary tree:");
		tree.printInOrder();
		tree.printInPreOrder();
		tree.printInPostOrder();
		tree.printInLevelOrder();
		tree.printInLevelOrder2();
		System.out.println("Number of Parents: " + tree.countParents());
		System.out.println("Number of Children: " + tree.countChildren());
		System.out.println("Number of Leaves: " + tree.countLeaves());

	}
}
