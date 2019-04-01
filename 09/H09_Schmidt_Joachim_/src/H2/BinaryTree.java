package H2;

public class BinaryTree<T extends Comparable<T>> {

	public BinaryTreeNode<T> root;

	/**
	 * Inserts a value into the binary tree
	 * 
	 * @param value
	 */
	public void insert(T value) {
		if (root == null) {
			root = new BinaryTreeNode<T>(value);
		} else {
			insertRec(value, root);
		}
	}

	/**
	 * Recursive helper function to insert a value into the tree
	 * 
	 * @param value
	 * @param root
	 */
	private void insertRec(T value, BinaryTreeNode<T> root) {
		if (value.compareTo(root.value) == -1) {
			if (root.left != null) {
				insertRec(value, root.left);
			} else {
				root.left = new BinaryTreeNode<T>(value);
			}
		}

		if (value.compareTo(root.value) == 1) {
			if (root.right != null) {
				insertRec(value, root.right);
			} else {
				root.right = new BinaryTreeNode<T>(value);
			}
		}
	}

	/**
	 * @param value
	 * @return true if the tree contains the given value
	 */
	public boolean search(T value) {
		BinaryTreeNode<T> p = root;
		while (p != null) {
			if (value.compareTo(p.value) == 0) {
				return true;
			}
			if (value.compareTo(p.value) == -1) {
				p = p.left;
			} else {
				if (value.compareTo(p.value) == 1) {
					p = p.right;
				}
			}
		}
		return false;

	}

}
