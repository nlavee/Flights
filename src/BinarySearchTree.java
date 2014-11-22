

import java.util.Comparator;
import java.util.Iterator;

/**
 * Binary Search Tree class that used data from TreeNode class 
 * @author AnhVuNguyen
 *
 */
public class BinarySearchTree<T>{

	TreeNode<T> root = null;

	Comparator<T> comp; 
	
	public BinarySearchTree(Comparator<T> comp2) {
		this.comp = comp2;
		root = null;
	} // end constructor 
	
	/**
	 * Method to insert a data as the root of the Binary Search Tree
	 * @param data
	 */
	public void insert(T data) {
		TreeNode<T> node = new TreeNode<T>(data, null, null,null);
		if (root == null) {
			root = node;
		} else {
			insertNode(node, root);
		} // end if 
	} // end insert()
	
	/**
	 * Method to add node into a binary search tree
	 * @param node
	 * @param subTree
	 */
	private void insertNode(TreeNode<T> node, TreeNode<T> subTree) {
		if (comp.compare(node.getData(), subTree.getData()) > 0) {
		//if ((node.compareTo(subTree)  > 0)) {
			if (subTree.getRight() == null) {
				subTree.setRight(node);
				node.setParent(subTree);
			} else {
				insertNode(node,subTree.getRight());
			} // end if 
		} else {
			if (subTree.getLeft() == null) {
				subTree.setLeft(node);
				node.setParent(subTree);
			} else {
				insertNode(node,subTree.getLeft());
			} // end if 
		} // end if 

	} // end insertNode()

	/**
	 * 
	 * @param subTree
	 * @return
	 */
	private String inOrderWalk(TreeNode<T> subTree) {
		String result = "";
		if (subTree != null) {
			result = inOrderWalk(subTree.getLeft());
			result += " " + subTree.getData() + " ";
			result += inOrderWalk(subTree.getRight());
		} // end if 
		return result;
	}

	public String toString() {
		String result;
		result = inOrderWalk(root);
		return result;
	} // end toString() 
	
	/**
	 * Search with recursion method
	 * @param data
	 * @return
	 */
	//search with Recursion
	public String searchRecursion(T data) {
		//create nodes for the data and for the result
		TreeNode<T> node = new TreeNode<T>(data, null, null,null);
		TreeNode<T> res = new TreeNode<T>(null,null,null, null);
		//check if there's already a binary tree
		if(root == null) {
			System.out.println("Binary Search Tree is empty");
		}
		else{
			res = recursiveSearch(node, root);	
		}

		//depend on whether we can find the data
		if(res.getData() == null) {
			System.out.println("This value is not in the list");
			return null;
		}
		else {
			return res.toString(); //return String
		}
	} // end search Recursion

	/**
	 * The actual recursion inside the recursion method
	 * @param node
	 * @param subTree
	 * @return
	 */
	//the recursive search inside the searchRecursion
	public TreeNode<T> recursiveSearch(TreeNode<T> node, TreeNode<T> subTree) {
		//create the result TreeNode
		TreeNode<T> res = new TreeNode<T>(null,null,null,null);
		//if there is no value, the method will spit out null

		//if larger
		if (comp.compare(node.getData(), subTree.getData()) > 0) {
		//if ((node.compareTo(subTree)  > 0)) {
			if (subTree.getRight() == null); //we cannot find the data, res = null
			else {
				res = recursiveSearch(node,subTree.getRight()); //continue searching for larger
			} // end if
			//if smaller
		} else if (comp.compare(node.getData(), subTree.getData()) < 0){
		//} else if ((node.compareTo(subTree)  < 0)) {
			if (subTree.getLeft() == null); //we cannot find the data, res = null
			else {
				res = recursiveSearch(node,subTree.getLeft()); //continue searching for smaller
			} // end if
			//if equal
		} else {
			res = subTree; //equal then res is that root of subtree
		} 
		return res;
	} //end recursive Search 

	/**
	 * Method to search for a node using a loop
	 * @param data
	 * @return
	 */
	public T searchLoop(T data) {
		T result = null;
		TreeNode<T> nodeFound = searchforNode(data);
		if(nodeFound != null) {
			result = nodeFound.getData();
		}
		else {
			System.out.println("Cannot find the following data from search TreeGiven: " + data + ". Set search result to the data.");
			result =  data;
		}
		return result;
	}

	/**
	 * The actual looping inside searching by loop
	 * @param data
	 * @return
	 */
	private TreeNode<T> searchforNode(T data) {
		//TreeNode<T> result;
		TreeNode<T> curr = root;
		TreeNode<T> prob = new TreeNode<T>(data,null,null, null);

		while(curr != null && this.comp.compare(curr.getData(), data) != 0) {
		//while(curr != null && (curr.compareTo(prob)  != 0)) {
			if(this.comp.compare(data, curr.getData()) < 0) {
			//if ((prob.compareTo(curr)  < 0)) {
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		return curr;
	}

	public String preOrderWalk() {
		return printPreorder(root);
	}


	private String printPreorder(TreeNode<T> initRoot) {
		String res = "";
		if(root!= null) {
			res += root.data;
			res += printPreorder(root.left);
			res +=printPreorder(root.right);
		}
		return res;
	}

	public String printLeft(TreeNode<T> parent) {
		TreeNode<T> curr = null;
		String res ="";
		curr = parent;

		while(curr.getLeft() != null) {
			curr = curr.getLeft();
			res+= curr.data.toString();
		}
		return res;
	}

	public String printRight(TreeNode<T> parent) {
		TreeNode<T> curr = null;
		String res="";
		curr = parent;

		while(curr.getRight() != null) {
			curr = curr.getRight();
			res += curr.data.toString();
		}
		return res;
	}

	public String postOrderWalk() {
		return null;
	}

	/**
	 * Method to find the minimum in a tree
	 * @return TreeNode
	 */
	public TreeNode<T> minimum(TreeNode<T> key) {
		TreeNode<T> curr;
		curr = key;

		while(curr.getLeft() != null && this.comp.compare(key.getData(),curr.getLeft().getData()) != 0) {
		//while(curr.getLeft() != null && key.compareTo(curr.getLeft()) != 0) {
			curr = curr.getLeft();
		}
		return curr;
	}

	/**
	 * Method to find successor in a binary search tree
	 * @param data
	 * @return
	 */
	public TreeNode<T> sucessor(T data) {
		TreeNode<T> curr;
		TreeNode<T> res = new TreeNode<T> (null,null,null,null);

		curr = this.searchforNode(data);

		if(curr != null && curr.getRight() != null) {
			res = this.minimum(curr.getRight());
		} else {
			//walk up to the parent, the moment you take the left, that's the successor
			res = getLeftParent(curr);
		}
		return res;
	}

	/**
	 * Method to find the closest parent that is larger than the node passed as parameter
	 * @param curr
	 * @return TreeNode
	 */
	private TreeNode<T> getLeftParent(TreeNode<T> curr) {
		TreeNode<T> res = null;

		//check if curr is null first
		if(curr != null) {
			res = curr.getParent();

			//if that node has smaller value, keep going up to parent
			if(comp.compare(res.data, curr.data) < 0) {
				res = getLeftParent(res);
			}
		}
		return res;
	}

	/**
	 * Method to delete a Node with value that matches the data
	 * @param key
	 */
	public void deleteNode(T key) {
		TreeNode<T> keyNode = searchforNode(key);
		TreeNode<T> prob = new TreeNode<T>(key, null,null,null);
		if(keyNode != null) {
			if(keyNode == root) {
				root = sucessor(root.getData());
				deleteNode(sucessor(root.getData()).getData());
			}
			else {
				if(keyNode.getLeft() == null & keyNode.getRight() == null) {
					if(comp.compare(key, keyNode.getParent().data) <0) {
					//if ((prob.compareTo(keyNode.getParent())  > 0)) {
						keyNode.getParent().setLeft(null);
					} else {
						keyNode.getParent().setRight(null);
					}
				}
				else if(keyNode.getLeft()!= null && keyNode.getRight() == null) {
					keyNode.getParent().setLeft(keyNode.getLeft());	
				}
				else if(keyNode.getRight() != null && keyNode.getLeft() == null) {
					keyNode.getParent().setRight(keyNode.getRight());
				} else {
					TreeNode<T> sucessor = sucessor(key);
					if(sucessor != null) {
						keyNode.setData(sucessor.getData());
						deleteNode(sucessor.getData());
					}
				}
			}
		}
	}


	//Iterator//
	public class BSTIterator implements Iterator<T> {

		TreeNode<T> curr;
		TreeNode<T> pred = null;

		BSTIterator() {
			curr = minimum(root);
		}

		@Override
		public boolean hasNext() {
			return (curr != null);
		}

		@Override
		public T next() {
			T res = curr.getData();
			curr = sucessor(curr.getData());
			return res;
		}

		@Override
		public void remove() {
			deleteNode(pred.data);
		}

	}

	public String getRoot() {
		return root.toString();
	}

} // end class BinarySearchTree