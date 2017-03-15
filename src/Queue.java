/**
 * User created Queue class that stores TreeNodes in an array
 * Created by Sadat Msi on 2/22/2017.
 */
public class Queue {

    /**
     * Array for storage
     */
    private TreeNode [] storage;

    /**
     * size: size of the array
     * head: index of the first element in the queue, -1 if empty
     * tail: index of the last element in the queue, -1 if empty
     */
    private int size, head, tail;

    /**
     * Constructs a queue with the size inputted
     * @param size size of the queue
     */
    public Queue (int size){
        this.size = size;
        storage = new TreeNode[size];
        head = -1;
        tail = -1;
    }

    /**
     * Adds an element to the end of the queue
     * @param x element to be added
     */
    public void enqueue(TreeNode x){
        if(isFull()){
            System.out.println("Queue is full.");
            return;
        }
        if(isEmpty()){
            head = 0;
        }

        if(tail + 1 == size){
            tail = 0;
        }
        tail++;
        storage[tail] = x;

    }

    /**
     * Removes an element from the beginning of the queue
     * @return returns the element at the beginning of the queue
     */
    public TreeNode dequeue(){

        if(isEmpty()){
            System.out.println("Queue is empty, nothing to dequeue.");
            System.exit(0);
        }
        TreeNode rv = storage[head];
        if(head == tail){
            head = -1;
            tail = -1;
        }
        else{
            if(head + 1 == size){
                head = 0;
            }
            else{
                head++;
            }
        }


        return rv;
    }

    /**
     * States if the queue is empty
     * @return true if empty, false if not empty
     */
    public boolean isEmpty(){
        if(head == -1 && tail == -1){
            return true;
        }

        return false;
    }

    /**
     * States if queue is full
     * @return true if full, false if not full
     */
    public boolean isFull(){
        if( ( tail + 1 == head || ( (tail + 1 == size) && head == 0) ) && head != -1 && tail != -1){

            return true;
        }
        return false;
    }
}
