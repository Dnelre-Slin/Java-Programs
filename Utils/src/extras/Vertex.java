package extras;

import utils.LinkedList;

public class Vertex <K extends Comparable, T>{
    protected T core;
    protected LinkedList<K,Vertex<K,T>> edges;
    protected boolean visited;
    
    public Vertex(T _core){
        core = _core;
        edges = new LinkedList<>();
        visited = false;
    }
    
    public void addEdge(K _key, Vertex<K,T> _edge){
        if (edges.get(_key) == null){// Check if this edge already exists.
            edges.add(_key, _edge);
        }
    }
    
    public void deleteEdge(K _key){
        edges.delete(_key);
    }
    
    public Vertex<K,T> getEdge(K _key){
        return edges.get(_key);
    }
    
    public LinkedList<K,Vertex<K,T>> getEdges(){
        return edges;
    }
    
    public T getCore(){
        return core;
    }
    
    public void resetFlag(){
        visited = false;
    }

    @Override
    public String toString(){
        return core.toString();
    }
}
