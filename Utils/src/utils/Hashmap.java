package utils;

import extras.Node;
import java.util.ArrayList;
import java.util.Iterator;

public class Hashmap <K extends Comparable<K>, T> implements Iterable<T>{
    private LinkedList<K,T>[] array;
    private int size;
    
    public Hashmap(int _arraySize){
        array = new LinkedList[_arraySize];
        size = 0;
    }
    public Hashmap(){
        this(20);
    }
    
    public void add(K _key, T _value){
        int _index = getIndex(_key);
        if (array[_index] == null){
            array[_index] = new LinkedList<>();
        }
        array[_index].add(_key, _value);
        size++;
    }
    
    public T get(K _key){
        int _index = getIndex(_key);
        if (array[_index] != null){
            return array[_index].get(_key);
        }
        return null;
    }
    
    public T delete(K _key){
        int _index = getIndex(_key);
        if (array[_index] != null){
            T _value = array[_index].delete(_key);
            if (_value != null){
                size--;
            }
            return _value;
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "{\n";
        for (int i=0; i<array.length; i++){
            if (array[i] != null){
                s += array[i].toString();
                s += "\n";
            }
        }
        s += "}";
        return s;
    }
    
    private int getIndex(K _key){
        return (Math.abs((_key.hashCode())%array.length));
    }
    
    public int size(){
        return size;
    }
    public ArrayList toArray(){
        ArrayList<T> _array = new ArrayList<>();
        for (int i=0; i<array.length; i++){
            if (array[i] != null){
                ArrayList<T> _subArray = array[i].toArray();
                for (int j=0; j<_subArray.size(); j++){
                    _array.add(_subArray.get(j));
                }
            }
        }
        return _array;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this);
    }
    public class MyIterator<K2 extends Comparable<K2>, T2> implements Iterator<T2>{
        private Hashmap<K2,T2> map;
        private int index;
        private Node<K2,T2> nextNode;
        public MyIterator(Hashmap<K2,T2> _map){
            map = _map;
            index = -1;
            nextNode = null;
        }
        private void getNextNode(){
            if (nextNode != null && nextNode.getNext() != null){
                nextNode = nextNode.getNext();
            }
            else{
                getNextList();
            }
        }
        private void getNextList(){
            index++;
            while ((index < map.array.length) && (map.array[index] == null || map.array[index].size() == 0)){
                index++;
            }
            if (index < map.array.length){
                nextNode = map.array[index].getNodeByIndex(0);
            }
            else{
                nextNode = null;
            }
        }
        @Override
        public boolean hasNext() {
            getNextNode();
            return (nextNode != null);
        }
        @Override
        public T2 next() {
            return nextNode.getValue();
        }
    }
}
