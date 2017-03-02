/**
 * User created node for a binary tree
 * Created by Sadat Msi on 2/22/2017.
 */
public class TreeNode {

    /**
     * data field
     */
    public char [] data;

    /**
     * parent: points to predecessor node
     * left: points to left child
     * right: points to right child
     */
    public TreeNode parent, left, right;

    /**
     * Used for AVL Trees
     */
    public int balance;

    /**
     * Constructs an empty TreeNode
     */
    public TreeNode(){
        parent = null;
        left = null;
        right = null;
        data = new char[41];

    }

    /**
     * Constructs a TreeNode
     * @param data data field
     * @param p parent TreeNode reference
     * @param l left child TreeNode reference
     * @param r right child TreeNode reference
     */
    public TreeNode(char [] data, TreeNode p, TreeNode l, TreeNode r){
        this.data = data;
        parent = p;
        left = l;
        right = r;
    }


}
