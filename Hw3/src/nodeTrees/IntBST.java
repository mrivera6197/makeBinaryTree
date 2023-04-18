package nodeTrees;

import java.util.ArrayList;
import java.util.List;

// binary search tree storing integers
public class IntBST extends NodeBinaryTree<Integer> {

	//private int size = 0;

	public IntBST() {	}

	public int size() {
		return size;
	}

	public void setSize(int s) { size = s; }
	
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Places element e at the root of an empty tree and returns its new Node.
	 *
	 * @param e the new element
	 * @return the Node of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */

	public Node<Integer> addRoot(Integer e) throws IllegalStateException {
		if (size != 0)
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Print a binary tree horizontally Modified version of
	 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	 * Modified by Keith Gutfreund
	 * 
	 * @param n Node in tree to start printing from
	 */
	
	  public void print(Node<Integer> n){ print ("", n); }
	  
	  public void print(String prefix, Node<Integer> n){
		  if (n != null){
			  print(prefix + "       ", right(n));
			  System.out.println (prefix + ("|-- ") + n.getElement());
			  print(prefix + "       ", left(n));
		  }
	  }
	  
	  
	  public void inorderPrint(Node<Integer> n) {
		if (n == null)
			return;
		inorderPrint(n.getLeft());
		System.out.print(n.getElement() + "  ");
		inorderPrint(n.getRight());
	}

	
	public Iterable<Node<Integer>> children(Node<Integer> n) {
		List<Node<Integer>> snapshot = new ArrayList<>(2); // max capacity of 2 
		if (left(n) != null) 
			snapshot.add(left(n)); 
		if (right(n) != null)
			snapshot.add(right(n)); return snapshot; 
	}
	
	public int height(Node<Integer> n) throws IllegalArgumentException { 
		if (isExternal(n)) { return 0; } 
		int h = 0; // base case if p is external
		for (Node<Integer> c : children(n)) h = Math.max(h, height(c)); return h + 1; 
	}

	/**
	 *
	 * @param a int[]
	 * @param startIndex int
	 * @param endIndex int
	 * @return IntBST
	 * receives an array, start index, and end index and returns a "complete" binary
	 * tree. prints the tree, tree size and height to the console.
	 */
	public static IntBST binaryTreeHelper(int[] a, int startIndex, int endIndex) {
		  IntBST tree = new IntBST();

		  // base case
		  if (startIndex > endIndex) {
			  return tree;
		  }

		  // calculates the midpoint of the array and sets the
		  int midpoint = (startIndex + endIndex)/2;
		  tree.addRoot(a[midpoint]);

		  // recursively builds the left side and right sides of the tree
		  IntBST left = binaryTreeHelper(a, startIndex, midpoint -1);
		  IntBST right = binaryTreeHelper(a, midpoint + 1, endIndex);

		  // attaches the left and right trees to the root
		  tree.attach(tree.root, left, right);
		  // updates size
		  tree.setSize(a.length);
		return tree;
	}



	public static IntBST makeBinaryTree(int[] a){
		// calls helper function to get a complete binary tree
		  IntBST tree = binaryTreeHelper(a, 0, a.length -1);
		return tree;
		
	}







}
