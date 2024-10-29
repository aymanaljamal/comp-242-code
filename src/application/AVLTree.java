package application;

public class AVLTree<T extends Comparable<T>> {
	private TNode<T> root;

	private int getHeight(TNode<T> N) {
		if (N == null)
			return 0;
		return 1 + Math.max(getHeight(N.getLeft()), getHeight(N.getRight()));
	}

	private int getBalance(TNode<T> N) {
		if (N == null)
			return 0;
		return getHeight(N.getLeft()) - getHeight(N.getRight());
	}

	private TNode<T> rightRotate(TNode<T> y) {
		TNode<T> x = y.getLeft();
		TNode<T> T2 = x.getRight();
		x.setRight(y);
		y.setLeft(T2);
		return x;
	}

	private TNode<T> leftRotate(TNode<T> x) {
		TNode<T> y = x.getRight();
		TNode<T> T2 = y.getLeft();
		y.setLeft(x);
		x.setRight(T2);
		return y;
	}

	public TNode<T> insert(TNode<T> node, T key) {
		if (node == null) {
			return new TNode<>(key);
		}

		if (key.compareTo(node.getData()) < 0) {
			node.setLeft(insert(node.getLeft(), key));
		} else if (key.compareTo(node.getData()) > 0) {
			node.setRight(insert(node.getRight(), key));
		} else {
			return node;
		}

		return rebalance(node);
	}

	public void insert(T key) {
		root = insert(root, key);
	}

	public TNode<T> deleteNode(TNode<T> root, T key) {
		if (root == null)
			return null;

		if (key.compareTo(root.getData()) < 0) {
			root.setLeft(deleteNode(root.getLeft(), key));
		} else if (key.compareTo(root.getData()) > 0) {
			root.setRight(deleteNode(root.getRight(), key));
		} else {
			if (root.getLeft() == null || root.getRight() == null) {
				root = (root.getLeft() == null) ? root.getRight() : root.getLeft();
			} else {
				TNode<T> temp = minValueNode(root.getRight());
				root.setData(temp.getData());
				root.setRight(deleteNode(root.getRight(), temp.getData()));
			}
		}

		return root == null ? null : rebalance(root);
	}

	public void delete(T key) {
		root = deleteNode(root, key);
	}

	private TNode<T> minValueNode(TNode<T> node) {
		TNode<T> current = node;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}

	private TNode<T> rebalance(TNode<T> node) {
		int balance = getBalance(node);
		if (balance > 1) {
			if (getBalance(node.getLeft()) < 0) {
				node.setLeft(leftRotate(node.getLeft()));
			}
			return rightRotate(node);
		} else if (balance < -1) {
			if (getBalance(node.getRight()) > 0) {
				node.setRight(rightRotate(node.getRight()));
			}
			return leftRotate(node);
		}
		return node;
	}

	public void preOrder(TNode<T> node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	public void preOrder() {
		preOrder(root);
	}

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>();

		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(25);

		System.out.println("Preorder traversal of the constructed AVL tree is:");
		tree.preOrder();

		tree.delete(20);
		System.out.println("\nAfter deleting 20:");
		tree.preOrder();
	}
}
