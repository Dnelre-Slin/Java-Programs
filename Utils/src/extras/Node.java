package extras;

import java.util.ArrayList;

public class Node<K extends Comparable<K>, T> {
    private K key;
    private T value;
    private Node<K,T> next;
    private Node<K,T> previous;
    private int index;
    
    public Node(K _key, T _value, Node<K,T> _last, int _index){
        key = _key;
        value = _value;
        previous = _last;
        next = null;
        index = _index; 
    }
    
    public void add(K _key, T _value){
        if (next == null){
            next = new Node<>(_key, _value, this, index+1);
        }
        else{
            next.add(_key, _value);
        }
    }
    
    public Node<K,T> get(K _key){
        if (_key.compareTo(key) == 0){ //Find node by comparing keys.
            return this;
        }
        if (next != null){ //Keep looking until node is found, or to the end of the list.
            return next.get(_key);
        }
        return null;
    }
    
    public Node<K,T> getByIndex(int _index){
        if (_index == index){ //Use index, instead of key, to find node.
            return this;
        }
        if (next != null){
            return next.getByIndex(_index);
        }
        return null;
    }
    
    public void delete(){ //Deletes this node, by linking the next and previous nodes together, bypassing this node.
        previous.next = next;
        if (next != null){
            next.previous = previous;
        }
    }
    
    public void shiftIndex(){ //Shifts this node's index, and all nodes after it, to one less. Used when a node is deleted.
        index--;
        if (next != null){
            next.shiftIndex();
        }
    }
    
    public T getValue(){
        return value;
    }
    public Node<K,T> getNext(){
        return next;
    }

    public ArrayList<T> toArray(ArrayList<T> _array){ //Used by linkedlist to make arraylist.
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
}
