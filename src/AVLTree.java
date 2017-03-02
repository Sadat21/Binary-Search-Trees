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
        System.out.println(inserted.data);
        int caseNumber  = 0;
        //Finding pivot if it exists
        TreeNode pivot = inserted;
        do{
            if(pivot.parent == null){
                caseNumber = 1;
                break;
            }
            pivot = pivot.parent;

        }while(pivot.balance == 0);

        //Now to determine if we are adding to the short end or long end
        String insertedData = new String(Arrays.copyOfRange(inserted.data, 7, 31));
        if(caseNumber == 0) {

            String pivotData = new String(Arrays.copyOfRange(pivot.data, 7, 31));

            if (insertedData.compareTo(pivotData) > 0) {
                if (pivot.balance == 1) {
                    caseNumber = 3;
                } else {
                    caseNumber = 2;
                }
            } else {
                if (pivot.balance == 1) {
                    caseNumber = 2;
                } else {
                    caseNumber = 3;
                }

            }
        }


        //Three Cases
        TreeNode upper;
        switch (caseNumber){


            //Case 1: There is no Pivot
            case 1:
                System.out.println("Case 1 happens");
                inserted.balance = 0;

                upper = inserted.parent;
                while(upper != null){
                    String parentData = new String(Arrays.copyOfRange(upper.data, 7, 31));
                    if( insertedData.compareTo(parentData) > 0){
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
                System.out.println("Case 2 happens");
                inserted.balance = 0;

                upper = inserted.parent;
                while(upper != null){
                    String parentData = new String(Arrays.copyOfRange(upper.data, 7, 31));
                    if( insertedData.compareTo(parentData) > 0){
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
                break;

            default:
                System.err.println("Error inserting into AVL tree.");
                System.exit(0);
                break;
        }

        return inserted;



    }

    /**
     * Delete method not implemented for this project
     * @param data field to be deleted
     */
    public void delete(char [] data){
        System.out.println("Delete not supported yet");
    }

}
