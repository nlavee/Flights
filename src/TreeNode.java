


public class TreeNode<T>{

	T data;
	TreeNode<T> left;
	TreeNode<T> right;
	TreeNode<T> parent;
	boolean red;
	
	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right,
			TreeNode<T> parent, boolean red) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.red = red;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public T getData() {
		return data;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	boolean isRed() {
		return red;
	}

	void setRed(boolean red) {
		this.red = red;
	}	
	
} //end class TreeNode
