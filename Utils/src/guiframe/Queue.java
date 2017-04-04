package guiframe;

public class Queue <T>{
    private T[] queue;
    private int first;
    private int last;
    private int size;
    
    public Queue(int init_size){
        queue = (T[])new Object[init_size];
        first = last = -1;
        size = 0;
    }
    public Queue(){
        this(10); //Calling the other constructor, with a default size of 10.
    }
    
    public void enqueue(T new_element){
        if (size == queue.length){
            resize();
        }
        else if (isEmpty()){
            first = 0;
        }
        last = (last+1)%queue.length;
        queue[last] = new_element;
        size++;
    }
    
    public T dequeue(){
        if (isEmpty()){
            System.out.println("Queue is empty.");
            return null;
        }
        T r_value = queue[first];
        first = (first+1)%queue.length;
        size--;
        if (isEmpty()){
            first = last = -1;
        }
        return r_value;
    }
    
    public boolean isEmpty(){
        return size <= 0;
    }
    
    public int size(){
        return size;
    }
    
    public void emptyQueue(){
        size = 0;
        first = last = -1;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!isEmpty()){
            for (int i=0; i<size-1; i++){
                sb.append(queue[(first+i)%queue.length]);
                sb.append(" , ");
            }
            sb.append(queue[last]);
        }
        sb.append(" ]");
        return sb.toString();
    }
    
    private void resize(){
        T[] new_queue = (T[]) new Object[queue.length*2];
        for(int i=0; i<size; i++){
            new_queue[i] = queue[(first+i)%queue.length];
        }
        first = 0;
        last = size-1;
        queue = new_queue;
    }
}
