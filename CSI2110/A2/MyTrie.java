/*** 
 * This is class implements a trie that holds a set of strings.
 * MyTrie stores stores nodes using class TreeNode
 * 
 * Name: Oliver Benning 
 * Student Number: 7798804
 * Uottawa Email: obenn009@uottawa.ca
 * 
 *
 */

public class MyTrie {
	
	private TreeNode root = null;

	private int numNodes;

    // Constructor. Note that an empty trie (no strings added) contains the root node 
	public MyTrie() {
		root = new TreeNode(null, null, null,false); 
		numNodes=1;
	}

	// ### Implemented method ###
	public boolean insert(String s) {
		// Convert string to char array for iteration
		char[] chars = s.toCharArray();
		TreeNode node = root;

		// Iterate over given string
		for ( int i = 0; i < chars.length; i++ ) {
			// Left case
			if ( chars[i] == '0' ) {
				// If left node exists, go to it.
				if ( node.getLeftChild() != null ) {
					node = node.getLeftChild();
				}
				// If not, create the new node. 
				else {
					TreeNode newNode = new TreeNode ( node, null, null, false );
					node.setLeftChild( newNode );
					node = newNode;
					numNodes++;
				}
			}
			// Right case
			else if ( chars[i] == '1' ) {
				// If right node exists, go to it.
				if ( node.getRightChild() != null ) {
					node = node.getRightChild();
				}
				// If not, create the new node. 
				else {
					TreeNode newNode = new TreeNode ( node, null, null, false );
					node.setRightChild( newNode );
					node = newNode;
					numNodes++;
				}
			}
		}

		// Set return value to whether current node is used
		boolean wasPresent = node.getIsUsed();
		// Set it to being used since it was inserted
		node.setIsUsed( true );

		return !wasPresent;
	}
	
	// ### Implemented method ###
	public boolean search(String s) {
		// Convert string to char array, making the string iterable
		char[] chars = s.toCharArray();
		TreeNode currentNode = root;

		// Iterate over character array
		for ( int i = 0; i < chars.length; i++ ) {
			// If character is a zero traverse to left node, or return false if no node
			if ( chars[i] == '0' ) {
				if ( currentNode.getLeftChild() == null ) {
					return false;
				} else {
					currentNode = currentNode.getLeftChild();
				}
			}
			// If character is a one traverse to right node, or return false if no node
			else if ( chars[i] == '1' ) {
				if ( currentNode.getRightChild() == null ) {
					return false;
				} else {
					currentNode = currentNode.getRightChild();
				}
			}
		}
		// If for loop is finished without interruption 
		// we've arrived at the node, and return whether the node is used.
		return currentNode.getIsUsed();
	}

	

	// ### Implemented method ###
	public void printStringsInLexicoOrder() {
		// Call recursive method, initially at root with empty string buffer
		traverseAndPrintPreOrder( root, "" );
	}
	
	// ### Implemented method ###
	// Private auxillary method for recursive printing.
	// Order: Root, Left, Right
	private void traverseAndPrintPreOrder( TreeNode node, String s ) {
		// Print (visit) root node first, print contents if used.
		if ( node.getIsUsed() ) System.out.print( s + "," );

		// If left child exists call recursively, adding '0' to string
		if ( node.getLeftChild() != null ) {
			traverseAndPrintPreOrder( node.getLeftChild(), s + '0' );
		}
		// If right child exists call recursively, adding '1' to string
		if ( node.getRightChild() != null ) {
			traverseAndPrintPreOrder( node.getRightChild(), s + '1' );
		}
	}
	
	// the following method that calls its recursive counterpart
	// prints the tree and its boolean values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(N.getIsUsed());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public TreeNode root() { // not to be changed
		return root;
	}
	
	public int numNodes() { // not to be changed
		return numNodes;
	}


}
