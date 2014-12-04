
import java.util.Comparator;

public class redBlackTree<T> extends BinarySearchTree<T>{

	public redBlackTree(Comparator comp2) {
		super(comp2);
	}

	public void insertNode(T data) {
		TreeNode<T> node = this.insert(data);
		//System.out.println(node);
		//System.out.println(node.isRed());
		this.RBTFix(node);
		//System.out.println("done");
		//System.out.println(node);
		//System.out.println(node.isRed());
	}

	private void RBTFix(TreeNode<T> node) {
		//set sentinel to point to itself so that the loop reach the one before the root
		while(node.isRed() && !node.equals(root) && node.getParent().getParent().getData() != null ) {
			//System.out.println(node.getParent());
			//System.out.println("+" + node.getParent().getParent().getData());
			if(node.getParent().equals(node.getParent().getParent().getLeft())) {
				TreeNode<T> a = node.getParent().getParent().getRight();
				if(a.isRed()) {
					//System.out.println("*case 1");
					node.getParent().setRed(false);
					a.setRed(false);
					node.getParent().getParent().setRed(true);
					node = node.getParent().getParent(); //case 1
				} else if (node.equals(node.getParent().getRight())) {
					//System.out.println("*case 2");
					node = node.getParent();
					this.leftRotate(node); //case 2
				} else {
					//System.out.println("*case 3");
					node.getParent().setRed(false);
					node.getParent().getParent().setRed(true);
					node = node.getParent().getParent();
					this.rightRotate(node); //case 3
				}
			} else {
				TreeNode<T> a = node.getParent().getParent().getLeft();
				//System.out.println("uncle: " + a);
				//System.out.println("*" + node.getData());
				if(a != null && a.isRed()) {
					//System.out.println("case 1");
					node.getParent().setRed(false);
					a.setRed(false);
					node.getParent().getParent().setRed(true);
					node = node.getParent().getParent(); //case 1
				} else if (node.equals(node.getParent().getLeft())) {
					//System.out.println("case 2");
					node = node.getParent();
					this.rightRotate(node); //case 2
				} else {
					//System.out.println("case 3");
					node.getParent().setRed(false);
					node.getParent().getParent().setRed(true);
					//System.out.println("node: " + node);
					node = node.getParent().getParent();
					//System.out.println("grandparent: " + node);
					this.leftRotate(node); //case 3
				}
			}
		}
		this.getRoot().setRed(false);
	}
	
	//change this to public and try rotating
	private void leftRotate(TreeNode<T> node) {
		TreeNode<T> a = node.getRight();
		
		node.setRight(a.getLeft());
		
		if(a.getLeft()!=null) {
			a.getLeft().setParent(node);
		}
		a.setParent(node.getParent());
		if(node.getParent().getData() == null) this.setRoot(a);
		else if(node.equals(node.getParent().getLeft())) node.getParent().setLeft(a);
		else node.getParent().setRight(a);
		
		a.setLeft(node);
		node.setParent(a);
	} //end leftRotate

	private void rightRotate(TreeNode<T> node) {
		TreeNode<T> a = node.getLeft();
		node.setLeft(a.getRight());
		if(a.getRight()!=null) a.getRight().setParent(node);
		
		a.setParent(node.getParent());
		if(node.getParent().getData()==null) this.setRoot(a);
		else if(node.equals(node.getParent().getRight())) node.getParent().setRight(a);
		else node.getParent().setLeft(a);
		
		a.setRight(node);
		node.setParent(a);
	} //end rightRotate

	
	/*
	public static void main(String[] args) {
		redBlackTree<City> test = new redBlackTree<City>(new cityComparator());

		test.insertNode(new City("house",1.0,1.0));
		test.insertNode(new City("abraham",1.1,1.1));
		test.insertNode(new City("seattle", 1.2, 1.2));
		test.insertNode(new City("utah", 1.3,1.3));
		test.insertNode(new City("onizuka", 1.4,1.4));
		//System.out.println(test.root);
		//System.out.println(test.toString());
		test.insertNode(new City("alabama", 1.5,1.5));
		test.insertNode(new City("austin",1.6,1.6));
		test.insertNode(new City("autumn", 1.7,1.7));
		test.insertNode(new City("valerie", 1.8, 1.8));
		test.insertNode(new City("vuper", 1.9, 1.9));


		System.out.println("final: " + test.toString());
		System.out.println(test.getRoot());
		//System.out.println(test.getRoot().getLeft());
		//System.out.println(test.getRoot().getLeft().getRight());
		//System.out.println(test.getRoot().getLeft().getLeft().getRight());
		//System.out.println(test.root.left.right.right.right.toString());
		//System.out.println(test.root.isRed());
		//System.out.println(test.root.left.isRed());
		//System.out.println(test.root.right.isRed());
		//System.out.println(test.root.right.left.isRed());
		//System.out.println(test.root.right.right.isRed());
	}*/
}