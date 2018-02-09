/***
 * This is class implements a compressed trie that holds a set of strings.
 * MyCompressedTrie stores nodes using class TreeNodeWithData
 *
 * Name: Oliver Benning
 * Student Number: 7798804
 * Uottawa Email: obenn009@uottawa.ca
 * 
 *
 */
public class MyCompressedTrie {
	
	private TreeNodeWithData root;
	
	private int numNodes;
	
	public MyCompressedTrie() { // simple constructor (empty trie consisting of root only)
		root = new TreeNodeWithData(null, null, null,false,null);
		numNodes=1;
	}
	
	// to be implemented by you see handout Part 2A
	// Constructor that receives a regular trie and creates this
	// instance that is a compressed trie
	// 
	// ### Implemented Method ###
	public MyCompressedTrie(MyTrie trie) {
		// Get the root of the tree we're constructing from.
		TreeNode oldRoot = trie.root();
		// Create root, same as in no param constructor.
		root = new TreeNodeWithData(null, null, null,false,null);
		numNodes++;
		// Call recursive method used to build new tree using initial values.
		traverseAndConstruct( root, oldRoot, "");
	}

	// ### Implemented Method ###
	// Private helper method to recursively construct compressed tree
	private void traverseAndConstruct(TreeNodeWithData dataNode, TreeNode oldNode, String s) {
		// Treat left child
		if ( oldNode.getLeftChild()  != null ) {
			// Set a current variable to the left child
			TreeNode current = oldNode.getLeftChild();

			// If it is used, add a parralel node to the new tree. And continue traversal
			if ( current.getIsUsed() ) {
				TreeNodeWithData newDataNode = new TreeNodeWithData( dataNode, null, null, true, s+'0' );
				numNodes++;
				dataNode.setLeftChild( newDataNode );
				traverseAndConstruct( newDataNode, current, s + '0' );
			}
			// If not used...
			else {
				// Case one, has two children. We therefore add the node.
				if ( current.getLeftChild() != null && current.getRightChild() != null ) {
					TreeNodeWithData newDataNode = new TreeNodeWithData(dataNode, null, null, false, null );
					numNodes++;
					dataNode.setLeftChild( newDataNode );
					traverseAndConstruct( newDataNode, current, s + '0' );
				
				// Case two, one child not null. Skip add and continue
				} else if ( current.getLeftChild()  != null ) {
					traverseAndConstruct( dataNode , current, s + '0' );
				} else if ( current.getRightChild() != null ) {
					traverseAndConstruct( dataNode , current, s + '0' );
				}
				// Case three, no children. Not theoretically possible with unused node, do nothing.
				// ...
			}
		}
		// Treat right child
		if ( oldNode.getRightChild() != null ) {
			// Set a current variable to the right child
			TreeNode current = oldNode.getRightChild();
			
			// If it is used, add a parralel node to the new tree. And continue traversal
			if ( current.getIsUsed() ) {
				TreeNodeWithData newDataNode = new TreeNodeWithData(dataNode, null, null, true, s+'1' );
				numNodes++;
				dataNode.setRightChild( newDataNode );
				traverseAndConstruct( newDataNode, current, s + '1' );
			
			}
			// If not used...
			else {	
				// Case one, has two children. We therefore add the node.
				if ( current.getLeftChild() != null && current.getRightChild() != null ) {
					TreeNodeWithData newDataNode = new TreeNodeWithData(dataNode, null, null, false, null );
					numNodes++;
					dataNode.setRightChild( newDataNode );
					traverseAndConstruct( newDataNode, current, s + '1' );
				
				// Case two, one child not null. Skip add and continue
				} else if ( current.getLeftChild()  != null ) {
					traverseAndConstruct( dataNode , current, s + '1' );
				} else if ( current.getRightChild() != null ) {
					traverseAndConstruct( dataNode , current, s + '1' );
				}
				// Case three, no children. Not theoretically possible with unused node, do nothing.
				// ...
			}
		}
	}


	// ### Implemented Method ####
	public void printStringsInLexicoOrder() {
		// Some logic here to properly treat the comma.
		// If there is a left child, make the first call to it to avoid starting comma.
		if ( root.getLeftChild() != null ) {
			traverseAndPrintPreOrder(root.getLeftChild() );
			// Then, if there is a right child call it. With transition comma.
			if ( root.getRightChild() != null){
				System.out.print(",");
				traverseAndPrintPreOrder(root.getRightChild());
			}
		}
		// If no left child and only a right child from the root, start with it.
		else if (root.getRightChild() != null ) {
			traverseAndPrintPreOrder(root.getRightChild() );
		}
	}

	// ### Implemented Method ####
	// Private helped method to recursively print tree contents in Pre-order
	// Order: Root, Left, Right
	private void traverseAndPrintPreOrder( TreeNode node ) {
		// If node is used, print it's data. Casted to data node.
		if ( node.getIsUsed() ) System.out.print( ((TreeNodeWithData)node).getData() );
		// If left child exists, add a comma and continue to it
		if ( node.getLeftChild()  != null ) {
			System.out.print(",");
			traverseAndPrintPreOrder( node.getLeftChild() );
		}
		// If right child exists, add a comma and continue to it
		if ( node.getRightChild() != null ) {
			System.out.print(",");
			traverseAndPrintPreOrder( node.getRightChild() );
		}
	}

	// the following method that calls its recursive counterpart
	// prints the tree and its data values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(((TreeNodeWithData)N).getData());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public int numNodes() {
		return numNodes;	
	}
	

}
