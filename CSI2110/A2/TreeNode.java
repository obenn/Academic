/*** 
 * This is class implements the node of a trie
 * with getters and setters.
 * Nothing to be changed. Just use it.
 * 
 * @author Lucia Moura
 *
 */

public class TreeNode {
	private TreeNode parent;
	private TreeNode leftChild;
	private TreeNode rightChild;
	private boolean isUsed;
	
	public TreeNode() { 
		parent=null;
		leftChild=null;
		rightChild=null;
		isUsed=false; // indicates that this is a white node (i.e. it holds a string inserted in the trie)
	}
	
	public TreeNode(TreeNode par, TreeNode left, TreeNode right, boolean used) {
		parent=par;
		leftChild= left;
		rightChild=right;	
		isUsed=used;
	}
    
	public void setParent(TreeNode par) {
		parent= par;
	}
	
	public void setLeftChild(TreeNode left) {
		leftChild=left;
	}
	
	void setRightChild(TreeNode right) {
		rightChild=right;
	}	
	
	void setIsUsed(boolean used) {
		isUsed=used;
	}
	public TreeNode getParent() {
		return parent;
	}
	
	public TreeNode getLeftChild() {
		return leftChild;
	}

	public TreeNode getRightChild() {
		return rightChild;
	}
	
	public boolean getIsUsed() {
		return isUsed;
	}
}
