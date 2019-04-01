package H2;

/**
 * A binary tree which organizes itself to improve search speed
 * 
 * @author joachim
 *
 * @param <T> The generic parameter
 */
public class SelfOrganizedTree<T extends Comparable<T>> extends BinaryTree<T> {

	/*
	public static void main(String[] args) {

		SelfOrganizedTree<Integer> tree = new SelfOrganizedTree<Integer>();

		// Randfall: leere Tree
		BTreePrinter.printNode(tree.root);
		System.out.println("Searching: " + tree.search(42));

		// Randfall: nur root
		tree.insert(10);
		BTreePrinter.printNode(tree.root);
		System.out.println("Searching: " + tree.search(10));
		System.out.println("Searching: " + tree.search(42));

		tree.insert(5);
		tree.insert(7);
		tree.insert(32);
		tree.insert(2);
		tree.insert(1);
		tree.insert(60);
		tree.insert(24);
		tree.insert(42);

		BTreePrinter.printNode(tree.root);

		System.out.println("Searching: " + tree.search(42));

		BTreePrinter.printNode(tree.root);

	}*/

	/**
	 * Searches the binary tree for the value
	 * 
	 * @param value The value to search for
	 * @return True if the value was found, False if not
	 */
	public boolean search(T value) {

		BinaryTreeNode<T> btnWeAreCurrentlyLookingAt = root;
		BinaryTreeNode<T> parentOfbtnWeAreCurrentlyLookingAt = null;
		BinaryTreeNode<T> grandparentOfbtnWeAreCurrentlyLookingAt = null;
		BinaryTreeNode<T> greatgrandparentOfbtnWeAreCurrentlyLookingAt = null;

		while (btnWeAreCurrentlyLookingAt != null) {

			if (btnWeAreCurrentlyLookingAt.value.compareTo(value) == 0) {

				// The item was found
				// Now rearrange the binary tree

				// If the item is already the root, cancel
				if (btnWeAreCurrentlyLookingAt != root) {

					// If the item is a child of the root,
					// perform a Zick or Zack rotation
					if (parentOfbtnWeAreCurrentlyLookingAt == root) {

						// Perform a Zick rotation
						if (parentOfbtnWeAreCurrentlyLookingAt.left == btnWeAreCurrentlyLookingAt) {

							BinaryTreeNode<T> oldRoot = root;
							BinaryTreeNode<T> oldRight = btnWeAreCurrentlyLookingAt.right;

							// The root changes to the value we are
							// looking at
							root = btnWeAreCurrentlyLookingAt;

							// The right of the new root changes to
							// the old root
							btnWeAreCurrentlyLookingAt.right = oldRoot;

							// The left of the old root changes to the
							// old right
							btnWeAreCurrentlyLookingAt.right.left = oldRight;

						}
						// Perform a Zack rotation
						else if (parentOfbtnWeAreCurrentlyLookingAt.right == btnWeAreCurrentlyLookingAt) {

							BinaryTreeNode<T> oldRoot = root;
							BinaryTreeNode<T> oldLeft = btnWeAreCurrentlyLookingAt.left;

							// The root changes to the value we are
							// looking at
							root = btnWeAreCurrentlyLookingAt;

							// The left of the new root changes to
							// the old root
							btnWeAreCurrentlyLookingAt.left = oldRoot;

							// The right of the old root changes to the
							// old left
							btnWeAreCurrentlyLookingAt.left.right = oldLeft;

						}

					}

					// The parent of the current node is not the root
					else {

						// FIRST ROTATION
						// Rotate with the parent
						// Are we on the left or right side of the parent?
						// Perform a Zick rotation
						if (parentOfbtnWeAreCurrentlyLookingAt.left == btnWeAreCurrentlyLookingAt) {

							BinaryTreeNode<T> oldParent = parentOfbtnWeAreCurrentlyLookingAt;
							BinaryTreeNode<T> oldRight = btnWeAreCurrentlyLookingAt.right;

							// We know parent is not root, so we can do this;
							// It is guaranteed that grandparent is not null
							if (grandparentOfbtnWeAreCurrentlyLookingAt.left == parentOfbtnWeAreCurrentlyLookingAt) {

								/*
								// Zick Zick
								//
								// First rotate parent and grandparent
								BinaryTreeNode<T> oldGrandParent = grandparentOfbtnWeAreCurrentlyLookingAt;
								BinaryTreeNode<T> oldParentRight = btnWeAreCurrentlyLookingAt.right;
								
								if (grandparentOfbtnWeAreCurrentlyLookingAt == root)
									root = parentOfbtnWeAreCurrentlyLookingAt;
								else {
									if (greatgrandparentOfbtnWeAreCurrentlyLookingAt.left == grandparentOfbtnWeAreCurrentlyLookingAt)
										greatgrandparentOfbtnWeAreCurrentlyLookingAt.left = btnWeAreCurrentlyLookingAt;
									else if (greatgrandparentOfbtnWeAreCurrentlyLookingAt.right == grandparentOfbtnWeAreCurrentlyLookingAt)
										greatgrandparentOfbtnWeAreCurrentlyLookingAt.right = btnWeAreCurrentlyLookingAt;
								}
								
								parentOfbtnWeAreCurrentlyLookingAt.right = oldGrandParent;

								parentOfbtnWeAreCurrentlyLookingAt.right.left = oldParentRight;
								
								// Then rotate parent and item
								if (parentOfbtnWeAreCurrentlyLookingAt == root)
									
								*/
								
								// Zick Zick
								if (grandparentOfbtnWeAreCurrentlyLookingAt == root) {
									root = btnWeAreCurrentlyLookingAt;
								}
								else {
									if (grandparentOfbtnWeAreCurrentlyLookingAt == greatgrandparentOfbtnWeAreCurrentlyLookingAt.left)
										greatgrandparentOfbtnWeAreCurrentlyLookingAt.left = btnWeAreCurrentlyLookingAt;
									else
										greatgrandparentOfbtnWeAreCurrentlyLookingAt.right = btnWeAreCurrentlyLookingAt;
								}
								
								btnWeAreCurrentlyLookingAt.right = parentOfbtnWeAreCurrentlyLookingAt;
								
								parentOfbtnWeAreCurrentlyLookingAt.left = oldRight;
								
								BinaryTreeNode<T> oldParentRight = parentOfbtnWeAreCurrentlyLookingAt.right;
								parentOfbtnWeAreCurrentlyLookingAt.right = grandparentOfbtnWeAreCurrentlyLookingAt;
								
								grandparentOfbtnWeAreCurrentlyLookingAt.left = oldParentRight;
								
								// We are finished rotating
								return true;
								

							} else if (grandparentOfbtnWeAreCurrentlyLookingAt.right == parentOfbtnWeAreCurrentlyLookingAt) {
								
								// Zick Zack
								
								// Rotate with parent first
								grandparentOfbtnWeAreCurrentlyLookingAt.right = btnWeAreCurrentlyLookingAt;
								btnWeAreCurrentlyLookingAt.right = oldParent;
								btnWeAreCurrentlyLookingAt.right.left = oldRight;
							}

						}
						// Perform a Zack rotation
						else if (parentOfbtnWeAreCurrentlyLookingAt.right == btnWeAreCurrentlyLookingAt) {

							BinaryTreeNode<T> oldParent = parentOfbtnWeAreCurrentlyLookingAt;
							BinaryTreeNode<T> oldLeft = btnWeAreCurrentlyLookingAt.left;

							if (grandparentOfbtnWeAreCurrentlyLookingAt.right == parentOfbtnWeAreCurrentlyLookingAt) {

								// Zack Zack
								if (grandparentOfbtnWeAreCurrentlyLookingAt == root) {
									root = btnWeAreCurrentlyLookingAt;
								}
								else {
									if (grandparentOfbtnWeAreCurrentlyLookingAt == greatgrandparentOfbtnWeAreCurrentlyLookingAt.left)
										greatgrandparentOfbtnWeAreCurrentlyLookingAt.left = btnWeAreCurrentlyLookingAt;
									else
										greatgrandparentOfbtnWeAreCurrentlyLookingAt.right = btnWeAreCurrentlyLookingAt;
								}
								
								btnWeAreCurrentlyLookingAt.left = parentOfbtnWeAreCurrentlyLookingAt;
								
								parentOfbtnWeAreCurrentlyLookingAt.right = oldLeft;
								
								BinaryTreeNode<T> oldParentLeft = parentOfbtnWeAreCurrentlyLookingAt.left;
								parentOfbtnWeAreCurrentlyLookingAt.left = grandparentOfbtnWeAreCurrentlyLookingAt;
								
								grandparentOfbtnWeAreCurrentlyLookingAt.right = oldParentLeft;
								
								// We are finished rotating
								return true;

							} else if (grandparentOfbtnWeAreCurrentlyLookingAt.left == parentOfbtnWeAreCurrentlyLookingAt) {
								
								// Zack Zick
								
								// Rotate with parent first
								grandparentOfbtnWeAreCurrentlyLookingAt.left = btnWeAreCurrentlyLookingAt;
								btnWeAreCurrentlyLookingAt.left = oldParent;
								btnWeAreCurrentlyLookingAt.left.right = oldLeft;

							}
						}

						// SECOND ROTATION
						// Rotate again
						//
						// By now, don't access parentOfbtnWeAreCurrentlyLookingAt
						// because it has been rotated already! It's no longer the parent,
						// it's the child of btnWeAreCurrentlyLookingAt!
						//
						// Perform a Zick rotation
						if (grandparentOfbtnWeAreCurrentlyLookingAt.left == btnWeAreCurrentlyLookingAt) {

							BinaryTreeNode<T> oldGrandParent = grandparentOfbtnWeAreCurrentlyLookingAt;
							BinaryTreeNode<T> oldRight = btnWeAreCurrentlyLookingAt.right;

							// Special case: what if grandparent is root?
							if (grandparentOfbtnWeAreCurrentlyLookingAt == root) {

								root = btnWeAreCurrentlyLookingAt;

							} else {

								if (greatgrandparentOfbtnWeAreCurrentlyLookingAt.left == grandparentOfbtnWeAreCurrentlyLookingAt)
									greatgrandparentOfbtnWeAreCurrentlyLookingAt.left = btnWeAreCurrentlyLookingAt;
								else if (greatgrandparentOfbtnWeAreCurrentlyLookingAt.right == grandparentOfbtnWeAreCurrentlyLookingAt)
									greatgrandparentOfbtnWeAreCurrentlyLookingAt.right = btnWeAreCurrentlyLookingAt;

							}

							btnWeAreCurrentlyLookingAt.right = oldGrandParent;

							btnWeAreCurrentlyLookingAt.right.left = oldRight;

						}
						// Perform a Zack rotation
						else if (grandparentOfbtnWeAreCurrentlyLookingAt.right == btnWeAreCurrentlyLookingAt) {

							BinaryTreeNode<T> oldGrandParent = grandparentOfbtnWeAreCurrentlyLookingAt;
							BinaryTreeNode<T> oldLeft = btnWeAreCurrentlyLookingAt.left;

							// Special case: what if grandparent is root?
							if (grandparentOfbtnWeAreCurrentlyLookingAt == root) {

								root = btnWeAreCurrentlyLookingAt;

							} else {

								if (greatgrandparentOfbtnWeAreCurrentlyLookingAt.left == grandparentOfbtnWeAreCurrentlyLookingAt)
									greatgrandparentOfbtnWeAreCurrentlyLookingAt.left = btnWeAreCurrentlyLookingAt;
								else if (greatgrandparentOfbtnWeAreCurrentlyLookingAt.right == grandparentOfbtnWeAreCurrentlyLookingAt)
									greatgrandparentOfbtnWeAreCurrentlyLookingAt.right = btnWeAreCurrentlyLookingAt;

							}

							btnWeAreCurrentlyLookingAt.left = oldGrandParent;

							btnWeAreCurrentlyLookingAt.left.right = oldLeft;

						}

					}

				}

				// After everything, return true
				return true;

				// Continue search
			} else if (btnWeAreCurrentlyLookingAt.value.compareTo(value) > 0) {

				// Move left
				greatgrandparentOfbtnWeAreCurrentlyLookingAt = grandparentOfbtnWeAreCurrentlyLookingAt;
				grandparentOfbtnWeAreCurrentlyLookingAt = parentOfbtnWeAreCurrentlyLookingAt;
				parentOfbtnWeAreCurrentlyLookingAt = btnWeAreCurrentlyLookingAt;
				btnWeAreCurrentlyLookingAt = btnWeAreCurrentlyLookingAt.left;

			} else if (btnWeAreCurrentlyLookingAt.value.compareTo(value) < 0) {

				// Move right
				greatgrandparentOfbtnWeAreCurrentlyLookingAt = grandparentOfbtnWeAreCurrentlyLookingAt;
				grandparentOfbtnWeAreCurrentlyLookingAt = parentOfbtnWeAreCurrentlyLookingAt;
				parentOfbtnWeAreCurrentlyLookingAt = btnWeAreCurrentlyLookingAt;
				btnWeAreCurrentlyLookingAt = btnWeAreCurrentlyLookingAt.right;

			}

		}

		// Not found
		return false;
	}

}
