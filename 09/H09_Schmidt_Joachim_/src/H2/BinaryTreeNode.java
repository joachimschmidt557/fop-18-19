package H2;

public class BinaryTreeNode<T> {
	
	public T value;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T value) {
		this.value = value;
	}
	
	public BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

}
