package utils;

import extras.Node;
import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<K extends Comparable, T> implements Iterable<T>{
    private Node<K,T> first;
    private int size;
    
    public LinkedList(){
        first = null;
        size = 0;
    }
    
    public void add(K _key, T _value){
        if (first == null){
            first = new Node<>(_key,_value,null);
        }
        else{
            first.add(_key, _value);
        }
        size++;
    }
    
    public T get(K _key){
        if (first != null){
            Node<K,T> _node = first.get(_key);
            if (_node != null){
                return _node.getValue();
            }
        }
        return null;
    }
    
    public T delete(K _key){
        if (first != null){
            Node<K,T> _node = first.get(_key);
            if (_node == first){
                first = first.getNext();
                size--;
                return _node.getValue();
            }
            if (_node != null){
                _node.delete();
                size--;
                return _node.getValue();
            }
        }
        return null;
    }
    
    public int size(){
        return size;
    }
    public ArrayList<T> toArray(){
        ArrayList<T> _array = new ArrayList<>();
        if (first != null){
            first.toArray(_array);
        }
        return _array;
    }

    @Override
    public String toString() {
        String s = "[ ";
        if (first != null){
            s += first.toString();
        }
        s += " ]";
        return s;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(this);
    }
    public class MyIterator<K2 extends Comparable, T2> implements Iterator<T2>{
        LinkedList<K2, T2> list;
        public MyIterator(LinkedList<K2, T2> _list){
            list = _list;
        }
        @Override
        public boolean hasNext() {
            return list.first != null;
        }
        @Override
        public T2 next() {
            T2 _value = list.first.getValue();
            list.first = list.first.getNext();
            return _value;
        }
    }
}
