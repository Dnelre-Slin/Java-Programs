package hashmap;

public class Node <Key extends Comparable,T>{
    private Key key;
    private T value;
    private Node<Key,T> next;
    
    public Node(Key new_key, T new_value){
        key = new_key;
        value = new_value;
        next = null;
    }
    
    public void append(Key append_key, T append_value){
        if (key == null){
            key = append_key;
            value = append_value;
        }
        else if (next == null){
            next = new Node(append_key, append_value);
        }
        else{
            next.append(append_key, append_value);
        }
    }
    
    public Node<Key,T> search(Key search_key){
        if (search_key.equals(key)){
            return this;
        }
        if (next != null){
            return next.search(search_key);
        }
//        System.out.println("Value not in list.");
        return null;
    }
    
    public Node<Key,T> delete(Key delete_key){
        if (delete_key.equals(key)){
            return next;
        }
        if (next != null){
            next = next.delete(delete_key);
        }
        return this;
    }
    
    public T getValue(){
        return value;
    }
    public Key getKey(){
        return key;
    }
    public Node<Key,T> getNext(){
        return next;
    }
    
    public String print(){
        String s = "";
        s += (value!=null)?value.toString():"";
        s += (next!=null)?(", " + next.print()):"";
        return s;
    }

    @Override
    public String toString() {
        return value.toString();
    }
    
    public Node<Key,T> findLast(){
        if (next == null){
            return this;
        }
        return next.findLast();
    }
}
