package graphs;
import hashmap.LinkedList;
import hashmap.Node;

public class Vertex <Key extends Comparable,T>{
    private LinkedList<Key,Vertex<Key,T>> edges;
    private T core;
    
    public Vertex(T new_core){
        core = new_core;
        edges = new LinkedList<>();
    }
    
    public void addEdge(Key add_key, Vertex<Key,T> add_edge){
        edges.append(add_key, add_edge);
    }
    public void removeEdge(Key remove_key){
        edges.delete(remove_key);
    }
    
    public Vertex<Key,T> findEdge(Key find_key){
        Node<Key,Vertex<Key,T>> tmp = edges.search(find_key);
        return (tmp!=null)?tmp.getValue():null;
    }
    
    public T getCore(){
        return core;
    }
    public LinkedList<Key,Vertex<Key,T>> getEdges(){
        return edges;
    }

    @Override
    public String toString() {
        return core.toString();
    }
}
