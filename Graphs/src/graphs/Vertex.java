package graphs;
import hashmap.LinkedList;

public class Vertex <Key extends Comparable,T>{
    private LinkedList<Key,Vertex<Key,T>> edges;
    private T core;
    
    public Vertex(T new_core){
        core = new_core;
        edges = new LinkedList<>();
    }
    
    public void addEdge(){
//        edges.append(append_key, core);
    }
}
