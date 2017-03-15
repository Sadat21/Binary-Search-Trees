
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * User created Binary Search Tree Class. The data fields in the tree are char arrays.
 * Created by Sadat Msi on 2/22/2017.
 */
public class BinaryTree {

    /**
     * Reference to the first Node in the binary tree
     */
    public TreeNode root;


    /**
     * Constructs an empty binary tree
     */
    public BinaryTree(){
        root = null;
    }

    /**
     * Inserts data into correct position on Binary Tree
     * Reference: Used a similar method to Leonard Manzera's lecture code
     * @param data
     * @return returns a reference to the newly inserted TreeNode
     */
    public TreeNode insert(char [] data){

        //Create Node

        char [] studentNumber =  (Arrays.copyOfRange(data,0,6));
        char [] lastName =  (Arrays.copyOfRange(data,7,31));
        char [] department =  (Arrays.copyOfRange(data,32,35));
        char [] program =  (Arrays.copyOfRange(data,36,39));
        char [] year =  (Arrays.copyOfRange(data,40,41));
        TreeNode brandNew = new TreeNode(studentNumber,lastName,department,program,year,null,null,null);

        //



        TreeNode current = root;
        TreeNode parent = null;
        String dataTemp = new String( lastName );
        while( current != null){
            parent = current;

            String currentTemp = new String ( current.lastName) ;
            if(  dataTemp.compareTo(currentTemp)> 0 ){
                current = current.right;
            }
            else{
                current = current.left;
            }
        }


        if(root == null){
            brandNew.parent = null;
            brandNew.left = null;
            brandNew.right = null;
            root = brandNew;
            return root;
        }
        String parentTemp = new String(parent.lastName);
        if( dataTemp.compareTo(parentTemp) > 0){
            brandNew.parent = parent;
            brandNew.left = null;
            brandNew.right = null;
            parent.right = brandNew;
            return parent.right;
        }
        else{
            brandNew.parent = parent;
            brandNew.left = null;
            brandNew.right = null;
            parent.left = brandNew;
            return parent.left;
        }


    }

    /**
     * Deletes the data field inputed from the binary tree
     * @param data field to be deleted
     */
    public void delete(char [] data){

        char [] toDelete =  (Arrays.copyOfRange(data,7,31));

        TreeNode temp = search(root, toDelete);
        if(temp == null){
            System.err.println("Data to be deleted does not exist.");
            System.exit(0);
        }


        //The to be deleted node can be 1/3 cases

        //If it's a leaf node
        if( temp.left == null && temp.right == null){

            //If node to be removed is root node
            if(temp == root)
            {
                root =null;
            }
            else {
                if (temp.parent.right == temp) {
                    temp.parent.right = null;
                } else {
                    temp.parent.left = null;
                }
            }
        }
        //Two children
        else if( temp.left != null && temp.right != null){
            TreeNode current = temp.right;
            //Find smallest node on right subtree
            while(current != null){
                if(current.left != null){
                    current = current.left;
                }
                else{
                    break;
                }

            }
            //If there is a node on the right child of current
            if(current.right != null){
                current.parent.left = current.right;
                current.right.parent = current.parent;
            }
            else{
                current.parent.left = null;
                current.parent = null;
            }

            //Make current the new temp
            if(temp == root)
            {
                current.right = root.right;
                current.left = root.left;
                current.parent = null;
                root.right.parent = current;
                root.left.parent = current;
                root = current;

            }
            else {
                current.left = temp.left;
                current.right = temp.right;
                current.parent = temp.parent;
                if (current.parent.right == temp) {
                    current.parent.right = current;
                } else {
                    current.parent.left = current;
                }
            }

        }
        //Only one child
        else{
            //If node to be removed is root node
            if(temp == root)
            {
                if (temp.right != null) {
                    root = temp.right;
                    temp.right.parent = null;
                }
                else{
                    root = temp.left;
                    temp.left.parent = null;
                }

            }
            else {
                if (temp.right != null) {
                    if (temp.parent.right == temp) {
                        temp.parent.right = temp.right;
                    } else {
                        temp.parent.left = temp.right;
                    }
                }
                else {
                    if (temp.parent.right == temp) {
                        temp.parent.right = temp.left;
                    } else {
                        temp.parent.left = temp.left;
                    }

                }
            }

        }



    }

    /**
     * Searches the binary tree for the key value
     * @param current Root node of tree or subtree to be searched
     * @param key char array to be found
     * @return returns a reference to the node or null if it is not found
     */
    public TreeNode search(TreeNode current, char[] key){
        while(current != null){
            if( Arrays.equals(key,current.lastName) ){
                return current;
            }


            if(key[0] == current.lastName[0]){
                boolean cont = true;
                for(int i = 0;  key[i] == current.lastName[i]  && cont ; i++){
                    if( key[i + 1] == current.lastName[i + 1]){

                    }
                    else{
                        if (key[i + 1] < current.lastName[i + 1]) {
                            current = current.left;
                            cont = false;
                        } else {
                            current = current.right;
                            cont = false;
                        }
                    }
                }

            }
            else {
                if (key[0] < current.lastName[0]) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

        }


        return null;
    }

    /**
     * Prints the contents of the Binary Tree in order to the file
     * @param current Intitally should be the root node
     * @param writer the FileWriter object that has the stream for the output
     */
    public void inorder(TreeNode current, FileWriter writer){

        try {
            if (current != null) {
                inorder(current.left, writer);

                writer.write(current.toString());
                writer.write("\r\n");
                inorder(current.right, writer);
            }
        }
        catch (IOException e){
            System.err.println("An IOException has been caught: " + e.getMessage());
        }
        catch (Exception e){
            System.err.println("An exception has been caught: " +  e.getMessage());
        }
    }

    /**
     * Outputs a BinaryTree input level by level to the filestream in writer
     * Reference: Drozdek (p224)
     * @param writer
     */
    public void breadthFirst(FileWriter writer){
        TreeNode p = root;
        Queue queue = new Queue(1000);
        if( p != null ){
            queue.enqueue(p);
            while( !queue.isEmpty()){
                p = (TreeNode) queue.dequeue();

                try {

                    writer.write(p.toString());
                    writer.write("\r\n");
                }
                catch (IOException e){
                    System.err.println("An IOException has been caught: " + e.getMessage());
                }
                catch (Exception e){
                    System.err.println("An exception has been caught: " +  e.getMessage());
                }

                if(p.left != null){
                    queue.enqueue(p.left);
                }
                if (p.right != null){
                    queue.enqueue(p.right);
                }
            }

        }
    }

}
