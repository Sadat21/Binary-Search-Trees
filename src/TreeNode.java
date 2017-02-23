/**
 * Created by Sadat Msi on 2/22/2017.
 */
public class TreeNode {

    public char [] data;

    public TreeNode parent, left, right;


    public TreeNode(){
        parent = null;
        left = null;
        right = null;
        data = new char[41];

    }

    public TreeNode(char [] data, TreeNode p, TreeNode l, TreeNode r){
        this.data = data;
        parent = p;
        left = l;
        right = r;
    }


}
