package extras;

import java.util.ArrayList;

public class Node<K extends Comparable, T> {
    private K key;
    private T value;
    private Node<K,T> next;
    private Node<K,T> last;
    
    public Node(K _key, T _value, Node<K,T> _last){
        key = _key;
        value = _value;
        last = _last;
        next = null;
    }
    
    public void add(K _key, T _value){
        if (next == null){
            next = new Node<>(_key, _value, this);
        }
        else{
            next.add(_key, _value);
        }
    }
    
    public Node<K,T> get(K _key){
        if (_key.compareTo(key) == 0){
            return this;
        }
        if (next != null){
            return next.get(_key);
        }
        return null;
    }
    
    public void delete(){
        last.next = next;
        if (next != null){
            next.last = last;
        }
    }
    
    public T getValue(){
        return value;
    }
    public Node<K,T> getNext(){
        return next;
    }
    public ArrayList<T> toArray(ArrayList<T> _array){
        _array.add(value);
        if (next == null){
            return _array;
        }
        return next.toArray(_array);
    }

    @Override
    public String toString(){
        if (next == null){
            return value.toString();
        }
        return value + " , " + next.toString();
    }
//    public String keyToString(){
//        if (next == null){
//            return key.toString();
//        }
//        return key + " , " + next.toString();
//    }
}
