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
        int _index = getIndex(_key); //Get index by hashcode.
        if (array[_index] == null){
            array[_index] = new LinkedList<>();
        }
        array[_index].add(_key, _value);
        size++;
    }
    
    public T get(K _key){
        int _index = getIndex(_key); //Get index by hashcode.
        if (array[_index] != null){
            return array[_index].get(_key);
        }
        return null;
    }
    
    public T delete(K _key){
        int _index = getIndex(_key); //Get index by hashcode.
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
    
    private int getIndex(K _key){ //Method to get index by hashcode of key.
        return (Math.abs((_key.hashCode())%array.length));
    }
    
    public int size(){
        return size;
    }
    public ArrayList toArray(){ //Makes a copy of the hashmap, and puts it in an arraylist.
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

    @Override //Used to iterate through the hashmap.
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
        private void getNextNode(){ //Gets next node in the linkedlist of a specific index of the hashmap's array.
            if (nextNode != null && nextNode.getNext() != null){ //If nextnode has next.
                nextNode = nextNode.getNext();
            }
            else{ //Else, get next index of array.
                getNextList();
            }
        }
        private void getNextList(){
            index++;
            //Search for next index that has a non-empty linkedlist.
            while ((index < map.array.length) && (map.array[index] == null || map.array[index].size() == 0)){
                index++;
            }
            if (index < map.array.length){ //If an index is found before the index is out of bounds.
                nextNode = map.array[index].getNodeByIndex(0);
            }
            else{ //No more elements to iterate.
                nextNode = null;
            }
        }
        @Override
        public boolean hasNext() { //Checks if there is more to iterate through.
            getNextNode();
            return (nextNode != null); 
        }
        @Override
        public T2 next() { //Iterates through the next value.
            return nextNode.getValue();
        }
    }
}
