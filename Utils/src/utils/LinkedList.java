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
            first = new Node<>(_key,_value,null,0);
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
    
    public Node<K,T> getNodeByIndex(int _index){
        if (first != null){
            return first.getByIndex(_index);
        }
        return null;
    }
    public T getByIndex(int _index){
        Node<K,T> _node = first.getByIndex(_index);
        if (_node != null){
            return _node.getValue();
        }
        return null;
    }
    
    
    public T delete(K _key){
        if (first != null){
            Node<K,T> _node = first.get(_key);
            if (_node == first){
                first.shiftIndex();
                first = first.getNext();
                size--;
                return _node.getValue();
            }
            if (_node != null){
                _node.shiftIndex();
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
    
    public Node<K,T> popFirst(){
        if (first != null){
            size--;
            Node<K,T> _node = first;
            first = first.getNext();
            return _node;
        }
        return null;
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
        return new MyIterator(first);
    }
    public class MyIterator<K2 extends Comparable, T2> implements Iterator<T2>{
        private Node<K2,T2> node;
        public MyIterator(Node<K2, T2> _node){
            node = _node;
        }
        @Override
        public boolean hasNext() {
            return (node != null);
        }
        @Override
        public T2 next() {
            T2 _value = node.getValue();
            node = node.getNext();
            return _value;
        }
    }
//    public class MyIterator<K2 extends Comparable, T2> implements Iterator<T2>{
//        private LinkedList<K2, T2> list;
//        public MyIterator(LinkedList<K2, T2> _list){
//            list = _list;
//        }
//        @Override
//        public boolean hasNext() {
//            return list.first != null;
//        }
//        @Override
//        public T2 next() {
//            T2 _value = list.first.getValue();
//            list.first = list.first.getNext();
//            return _value;
//        }
//    }
}
