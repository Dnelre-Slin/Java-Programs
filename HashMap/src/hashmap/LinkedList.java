package hashmap;

public class LinkedList <Key extends Comparable,T>{
    private Key key;
    private T value;
    private LinkedList<Key,T> next;
    
    public LinkedList(Key new_key, T new_value){
        key = new_key;
        value = new_value;
        next = null;
    }
    public LinkedList(){
        this(null,null);
    }
    
    public void append(Key append_key, T append_value){
        if (key == null){
            key = append_key;
            value = append_value;
        }
        else if (next == null){
            next = new LinkedList(append_key, append_value);
        }
        else{
            next.append(append_key, append_value);
        }
    }
    
    public LinkedList<Key,T> search(Key search_key){
        if (search_key.equals(key)){
            return this;
        }
        if (next != null){
            return next.search(search_key);
        }
        System.out.println("Value not in list.");
        return null;
    }
    
    public LinkedList<Key,T> delete(Key delete_key){
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
    
    public String print(){
        String s = "";
        s += value.toString();
        s += (next!=null)?(", " + next.print()):"";
        return s;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
