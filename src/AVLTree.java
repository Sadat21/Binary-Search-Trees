import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

/**
 * Created by Sadat Msi on 2/28/2017.
 */
public class AVLTree extends BinaryTree{


    public AVLTree(){
        super();

    }

    /**
     * Inserts a Node in the correct place in order to maintain balance
     * @param data
     * @return
     */
    public TreeNode insert(char [] data) {
        TreeNode inserted = super.insert(data);
        int caseNumber  = 0;
        //Finding pivot if it exists, and the son, and ancestor if needed
        TreeNode pivot = inserted;
        TreeNode son = null;
        TreeNode ancestor = null;
        TreeNode grandson = null;
        do{
            if(pivot.parent == null){
                caseNumber = 1;
                break;
            }
            son = pivot;
            pivot = pivot.parent;

        }while(pivot.balance == 0);

        if(pivot.parent != null){
            ancestor = pivot.parent;
        }


        //Now to determine if we are adding to the short end or long end
        String insertedLastName = new String (inserted.lastName);
        if(caseNumber == 0) {

            String pivotLastName = new String(pivot.lastName);

            if (insertedLastName.compareTo(pivotLastName) > 0) {
                if (pivot.balance == 1) {
                    caseNumber = 3;
                }
                else {
                    caseNumber = 2;
                }
            } else {
                if (pivot.balance == 1) {
                    caseNumber = 2;
                }
                else {
                    caseNumber = 3;
                }

            }
        }


        //Three Cases
        TreeNode upper;
        switch (caseNumber){


            //Case 1: There is no Pivot
            case 1:
                inserted.balance = 0;

                upper = inserted.parent;
                while(upper != null){
                    String parentLastName = new String(upper.lastName);
                    if( insertedLastName.compareTo(parentLastName) > 0){
                        upper.balance++;
                    }
                    else{
                        upper.balance--;
                    }
                    upper = upper.parent;
                }

                break;



            //Case 2: Pivot exists and a node is added to the shorter side
            case 2:
                inserted.balance = 0;

                upper = inserted.parent;
                while(upper != pivot.parent){
                    String parentData = new String(upper.lastName);
                    if( insertedLastName.compareTo(parentData) > 0){
                        upper.balance++;
                    }
                    else{
                        upper.balance--;
                    }
                    upper = upper.parent;
                }

                break;



            //Case 3: Pivot exists and a node is added to the larger side
            case 3:
                System.out.println("Case 3 happens");

                //Two cases within Case 3
                int case3cases = 0;

                //Find if inserted Node is left or right child of son and put this value into grandson
                grandson = inserted;
                while(grandson != son){
                    if(grandson.parent == son){
                        break;
                    }
                    else {
                        grandson = grandson.parent;
                    }
                }

                if( (son.left == grandson && pivot.balance == -1) || (son.right == grandson && pivot.balance == 1)  ){
                    case3cases = 1;
                }
                else{
                    case3cases = 2;
                }

                switch (case3cases){


                    //Adding to the outside subtree of the son
                    case 1:
                        if(pivot.balance == -1){
                            rightRotation(ancestor, pivot, son, inserted);
                            pivot.balance = 0;
                            inserted.balance = 0;

                            upper = inserted.parent;
                            while(upper != son){
                                String parentData = new String(upper.lastName);
                                if( insertedLastName.compareTo(parentData) > 0){
                                    upper.balance++;
                                }
                                else{
                                    upper.balance--;
                                }
                                upper = upper.parent;
                            }

                        }
                        else{
                            leftRotation(ancestor, pivot, son, inserted);
                            pivot.balance = 0;
                            inserted.balance = 0;

                            upper = inserted.parent;
                            while(upper != son){
                                String parentData = new String(upper.lastName);
                                if( insertedLastName.compareTo(parentData) > 0){
                                    upper.balance++;
                                }
                                else{
                                    upper.balance--;
                                }
                                upper = upper.parent;
                            }
                        }


                        break;

                    //Adding to the inside subtree of the son
                    case 2:
                        System.out.println("Case 3b");
                        if(pivot.balance == 1){
                            System.out.println("RL Roation");
                            doubleRight(ancestor, pivot, son, grandson, inserted);
                            doubleLeft(ancestor, pivot, son, grandson, inserted);

                            //Update Balances

                            inserted.balance = 0;
                            String grandsonLastName = new String ( grandson.lastName);
                            if ( insertedLastName.compareTo(grandsonLastName) > 0){
                                pivot.balance = -1;
                            }
                            else{
                                pivot.balance = 0;
                                son.balance = 1;
                            }

                            upper = inserted.parent;
                            if(upper == son || upper == pivot){

                            }
                            else {
                                while (upper.parent != son || upper.parent != pivot) {
                                    String parentData = new String(upper.lastName);
                                    if (insertedLastName.compareTo(parentData) > 0) {
                                        upper.balance++;
                                    } else {
                                        upper.balance--;
                                    }
                                    upper = upper.parent;
                                }
                            }
                        }
                        else{
                            System.out.println("LR Roation");
                            doubleLeft(ancestor, pivot, son, grandson, inserted);
                            doubleRight(ancestor, pivot, son, grandson, inserted);

                            //Update Balances

                            inserted.balance = 0;
                            String grandsonLastName = new String ( grandson.lastName);
                            if ( insertedLastName.compareTo(grandsonLastName) < 0){
                                pivot.balance = -1;
                            }
                            else{
                                pivot.balance = 0;
                                son.balance = 1;
                            }

                            upper = inserted.parent;
                            if(upper == son || upper == pivot){

                            }
                            else {
                                while (upper.parent != son || upper.parent != pivot) {
                                    String parentData = new String(upper.lastName);
                                    if (insertedLastName.compareTo(parentData) > 0) {
                                        upper.balance++;
                                    } else {
                                        upper.balance--;
                                    }
                                    upper = upper.parent;
                                }
                            }
                        }





                        break;
                }

                break;

            default:
                System.err.println("Error inserting into AVL tree.");
                System.exit(0);
                break;
        }

        return inserted;



    }


    private void rightRotation(TreeNode ancestor, TreeNode pivot, TreeNode son, TreeNode inserted){

        //Three pointers to change
        String pivotLastName = new String(pivot.lastName);

        //Pointer 1
        if(ancestor == null){
            System.err.println("Ancestor Node is null");
            System.exit(1);
            /*
            root = son;
            son.parent = null;
            pivot.parent = null;
            */

        }
        else {
            String ancestorLastName = new String(ancestor.lastName);
            if (ancestorLastName.compareTo(pivotLastName) > 0) {
                ancestor.left = son;
                son.parent = ancestor;
            } else {
                ancestor.right = son;
                son.parent = ancestor;
            }
        }

        //Pointer 2
        pivot.left = son.right;
        if(son.right == null){

        }
        else {
            son.right.parent = pivot;
            son.right = null;
        }


        //Pointer 3
        son.right = pivot;
        pivot.parent = son;



    }

    private void leftRotation(TreeNode ancestor, TreeNode pivot, TreeNode son, TreeNode inserted){

        String pivotLastName = new String(pivot.lastName);
        //Three pointers to change

        //Pointer 1
        if(ancestor == null){
            System.err.println("Ancestor Node is null");
            System.exit(1);
            /*
            root = son;
            son.parent = null;
            pivot.parent = null;
            */

        }
        else {
            String ancestorLastName = new String(ancestor.lastName);
            if (ancestorLastName.compareTo(pivotLastName) > 0) {
                ancestor.left = son;
                son.parent = ancestor;
            } else {
                ancestor.right = son;
                son.parent = ancestor;
            }
        }

        //Pointer 2
        pivot.right = son.left;

        if(son.left == null){

        }
        else {
            son.left.parent = pivot;
            son.left = null;
        }

        //Pointer 3
        son.left = pivot;
        pivot.parent = son;



    }



    private void doubleRight(TreeNode ancestor, TreeNode pivot, TreeNode son, TreeNode grandson, TreeNode inserted){
        pivot.right = grandson;
        grandson.parent = pivot;
        if(grandson.right != null){
            son.left = grandson.right;
            grandson.right.parent = son;
            grandson.right = null;
        }
        else{
            son.left = null;
        }
        grandson.right = son;
        son.parent = grandson;
    }

    private  void doubleLeft(TreeNode ancestor, TreeNode pivot, TreeNode son, TreeNode grandson, TreeNode inserted){
        if(ancestor == null){
            root = grandson;
            grandson.parent = null;
        }
        else if( new String(pivot.lastName).compareTo( new String(ancestor.lastName)) > 0 ){
            ancestor.right = grandson;
            grandson.parent = ancestor;
        }
        else{
            ancestor.left = grandson;
            grandson.parent = ancestor;
        }

        if(grandson.left != null) {
            pivot.right = grandson.left;
            grandson.left.parent = pivot;
            grandson.left = null;
        }
        else{
            pivot.right = null;
        }
        grandson.left = pivot;
        pivot.parent = grandson;

    }


    /**
     * Delete method not implemented for this project
     * @param data field to be deleted
     */
    public void delete(char [] data){
        System.out.println("Delete not supported yet");
    }

}
