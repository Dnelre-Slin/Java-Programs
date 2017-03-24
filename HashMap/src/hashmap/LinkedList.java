package hashmap;

public class LinkedList <Key extends Comparable,T>{
    Node<Key,T> first;
    int size;
    public LinkedList(){
        first = null;
        size = 0;
    }
    public LinkedList(Key key, T value){
        first = new Node<>(key,value);
        size = 1;
    }
    
    public void append(Key append_key, T append_value){
        if (first == null){
            first = new Node<>(append_key,append_value);
        }
        else{
            first.append(append_key, append_value);
        }
        size++;
    }
    public void append(LinkedList<Key,T> append_list){
        Node<Key,T> tmp = findLast();
        Node<Key,T> append_node = append_list.first;
        while (append_node != null){
            append(append_node.getKey(),append_node.getValue());
            append_node = append_node.getNext();
        }
    }
    
    public Node search(Key search_key){
        if (first != null){
            return first.search(search_key);
        }
        return null;
    }
    
    public void delete(Key delete_key){
        if (first != null){
            first = first.delete(delete_key);
            size--;
        }
    }
    
    public String print(){
        return (first != null)?first.print():"";
    }
    
    private Node<Key,T> findLast(){
        if (first != null){
            first.findLast();
        }
        return first;
    }
    
    public Node<Key,T> getFirst(){
        return first;
    }
    
    public int size(){
        return size;
    }
}
