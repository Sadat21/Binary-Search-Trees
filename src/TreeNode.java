/**
 * User created node for a binary tree
 * Created by Sadat Msi on 2/22/2017.
 */
public class TreeNode {

    /**
     * data fields
     */
    public char [] studentNumber;
    public char [] lastName;
    public char [] homeDepartment;
    public char [] program;
    public char [] year;

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
        studentNumber = new char[7];
        lastName = new char[25];
        homeDepartment = new char[4];
        program = new char[4];
        year = new char[1];

    }

    /**
     * Constructs a TreeNode
     * @param stdNum StudentNumber
     * @param lName Last Name
     * @param hmDep Home Department
     * @param pgm Program Name
     * @param yr Year of Study
     * @param p parent TreeNode reference
     * @param l left child TreeNode reference
     * @param r right child TreeNode reference
     */
    public TreeNode(char [] stdNum, char [] lName, char [] hmDep, char [] pgm, char [] yr, TreeNode p, TreeNode l, TreeNode r){
        parent = p;
        left = l;
        right = r;
        studentNumber = stdNum;
        lastName = lName;
        homeDepartment = hmDep;
        program = pgm;
        year = yr;
    }

    /**
     * String representation of a TreeNode
     * @return String
     */
    @Override
    public String toString(){
        return String.format(new String(studentNumber) + "            " + new String(lastName) + "  "
                + new String(homeDepartment) +
                "              " + new String(program) + "       " + new String(year));
    }

}
