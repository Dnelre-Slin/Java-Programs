package utils;

import extras.Node;
import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<K extends Comparable<K>, T> implements Iterable<T>{
    private Node<K,T> first;
    private int size;
    
    public LinkedList(){
        first = null;
        size = 0;
    }
    
    public void add(K _key, T _value){
        if (first == null){
            first = new Node<>(_key,_value,null,0); //Instantiate first if first was null.
        }
        else{
            first.add(_key, _value); //Else pass it on to first.
        }
        size++;
    }
    
    public T get(K _key){
        if (first != null){
            Node<K,T> _node = first.get(_key); //Search for a node with the specified key.
            if (_node != null){
                return _node.getValue(); //Return nodes value, if it was found (not null).
            }
        }
        return null; //Return null if the node was not found.
    }
    
    //Every node gets an index of type int automatically, in addition to the key provided.
    //This method search for a node by it's index, instead of key.
    public Node<K,T> getNodeByIndex(int _index){
        if (first != null){
            return first.getByIndex(_index); //Returns the node.
        }
        return null;
    }
    //Same as above, but return value of type T, instead of node.
    public T getByIndex(int _index){
        Node<K,T> _node = first.getByIndex(_index);
        if (_node != null){
            return _node.getValue(); //Return value if node was found.
        }
        return null;
    }
    
    // Searches for the node to delete, and deletes it if found.
    public T delete(K _key){
        if (first != null){
            Node<K,T> _node = first.get(_key); //Use the get method to find the node to by deleted.
            if (_node == first){        //If the delete-node is the first node,
                first.shiftIndex();     //calls the shiftIndex method, which moves all indexes after the deleted key one forward.
                first = first.getNext();//Deletes first, by replacing it with it's next.
                size--;
                return _node.getValue();//Return value for good measure.
            }
            if (_node != null){ //Same as above, but for a node that is not first.
                _node.shiftIndex();
                _node.delete(); //Use the delete method for node to be deleted. Can't use this method for first, since it has no previous node.
                size--;
                return _node.getValue();
            }
        }
        return null;
    }
    
    public int size(){
        return size;
    }
    public ArrayList<T> toArray(){ //Makes an arraylist with the same values as the linkedlist.
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

    @Override //Used to iterate through linkedlist.
    public Iterator<T> iterator() {
        return new MyIterator(first);
    }
    public class MyIterator<K2 extends Comparable<K2>, T2> implements Iterator<T2>{
        private Node<K2,T2> node;
        public MyIterator(Node<K2, T2> _node){
            node = _node;
        }
        @Override
        public boolean hasNext() {
            return (node != null); //Node is the next node, so if it is null, there is no next node.
        }
        @Override
        public T2 next() {
            T2 _value = node.getValue();
            node = node.getNext(); //Set node to next node.
            return _value; //Return value of node.
        }
    }
}
