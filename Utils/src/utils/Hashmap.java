package utils;

import java.util.ArrayList;

public class Hashmap <K extends Comparable, T>{
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
//        T[] _array = (T[]) new Object[size];
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
}
