
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Sadat Msi on 2/22/2017.
 */

public class BinaryTree {

    public TreeNode root;



    public BinaryTree(){
        root = null;
    }

    /**
     * Inserts data into correct position on Binary Tree
     * Reference: Used a similar method to Leonard Manzera's lecture code
     * @param data
     */
    public void insert(char [] data){

        TreeNode current = root;
        TreeNode parent = null;
        String dataTemp = new String( Arrays.copyOfRange(data,7,31) );
        while( current != null){
            parent = current;


            String currentTemp = new String ( Arrays.copyOfRange(current.data,7,31) );
            if(  dataTemp.compareTo(currentTemp)> 0 ){
                current = current.right;
            }
            else{
                current = current.left;
            }
        }


        if(root == null){
            root = new TreeNode(data, null, null, null);
            return;
        }
        String parentTemp = new String(Arrays.copyOfRange(parent.data, 7, 31));
        if( dataTemp.compareTo(parentTemp) > 0){
            parent.right = new TreeNode(data, parent, null, null);
        }
        else{
            parent.left = new TreeNode(data, parent, null, null);
        }


    }

    public void delete(char [] data){
        TreeNode temp = search(root, data);
        System.out.println("Point 1.5");
        if(temp == null){
            System.err.println("Data to be deleted does not exist.");
            System.exit(0);
        }



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
                current = temp;
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

    public TreeNode search(TreeNode current, char[] key){
        while(current != null){
            if( Arrays.equals(key,current.data) ){
                return current;
            }

            if(key[7] == current.data[7]){
                boolean cont = true;
                for(int i = 7;  key[i] == current.data[i]  && cont ; i++){
                    if( key[i + 1] == current.data[i + 1]){

                    }
                    else{
                        if (key[i + 1] < current.data[i + 1]) {
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
                if (key[7] < current.data[7]) {
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
                writer.write(new String(current.data));
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
    /*public void breadthFirst(FileWriter writer){
        TreeNode p = root;
        Queue queue = new Queue();
        if( p != null ){
            queue.enqueue(p);
            while( !queue.isEmpty()){
                p = (TreeNode) queue.dequeue();

                try {
                    writer.write(new String(p.data));
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
    }*/

}
