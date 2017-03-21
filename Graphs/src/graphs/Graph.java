package graphs;
import hashmap.HashMap;
import hashmap.LinkedList;

public class Graph <Key extends Comparable,T>{
    private HashMap<Key,Vertex<Key,T>> map;
    
    public Graph(int size){
        map = new HashMap<>(size);
    }
    
    public void addVertex(Key append_key, T append_value){
//        map.append(append_key, new Vertex(append_key,append_value));
    }
    
//    public void addEdge()
}
