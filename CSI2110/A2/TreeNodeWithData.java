/*** 
 * This is class implements the node of a trie that holds the string that 
 * gets you from the parent to this node (to be use in compressed tries).
 * It is derived from TreeNode and inherit its methods; providing the extra 
 * methods related to the new field data.
 * 
 * Nothing to be changed. Just use it.
 * 
 * @author Lucia Moura
 *
 */
public class TreeNodeWithData extends TreeNode {
	private String data;
	
	public TreeNodeWithData() {
		data=null;
	}
	
	public TreeNodeWithData(TreeNode par, TreeNode left, TreeNode right, boolean used, String s) {
		super(par,left,right,used);
		data=s;
	}
    
	public String getData() { 
		return data;
	}
	
	public void setData(String s) { 
		data=s;
	}	
}
