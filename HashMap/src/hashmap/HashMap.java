package hashmap;

import java.util.ArrayList;

public class HashMap <Key extends Comparable,T>{
    Key key;
    LinkedList<Key,T>[] array;
    
    public HashMap(int size){
        size = (size<1)?1:size;
        array = new LinkedList[size];
        key = null;
    }
    public HashMap(){
        this(20);
    }
    
    public void append(Key append_key, T append_value){
        int index = getIndex(append_key);
        if (array[index] == null){
            array[index] = new LinkedList<>();
        }
        array[index].append(append_key,append_value);
    }
    
    public T search(Key search_key){
        int index = getIndex(search_key);
        if (array[index] != null){
            Node<Key,T> tmp = array[index].search(search_key);
            return (tmp != null)?tmp.getValue():null;
        }
        System.out.println("Value not in table");
        return null;
    }
    
    public void delete(Key remove_key){
        int index = getIndex(remove_key);
        if (array[index] != null){
            array[index].delete(remove_key);
        }
    }
    
    private int getIndex(Key g_key){
        return ((g_key.hashCode())%array.length);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0; i<array.length; i++){
            if (array[i] != null){
                s += array[i].print();
                s += "\n";
            }
        }
        return s;
    }
    
    public LinkedList<Integer,LinkedList<Key,T>> getAllElements(){
        LinkedList<Integer,LinkedList<Key,T>> tmp_list = new LinkedList<>();
        for (int i=0; i<array.length; i++){
            if (array[i] != null){
                tmp_list.append(i,array[i]);
            }
        }
        return tmp_list;
    }
}
