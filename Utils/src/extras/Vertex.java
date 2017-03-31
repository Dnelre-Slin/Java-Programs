package extras;

import java.util.ArrayList;
import utils.LinkedList;

public class Vertex <K extends Comparable, T>{
    protected T core;
    protected K key;
    protected LinkedList<K,Vertex<K,T>> edges;
    protected boolean visited;
    
    public Vertex(K _key, T _core){
        core = _core;
        key = _key;
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
    
    public boolean depthFirst(K _goalKey, ArrayList<Vertex<K,T>> _path){
        visited = true;
        if (_goalKey.compareTo(key) == 0){
            _path.add(0,this);
            return true;
        }
        for (int i=0; i<edges.size(); i++){
            Vertex<K,T> _vertex = edges.getByIndex(i);
            if (!_vertex.visited){
                if (_vertex.depthFirst(_goalKey, _path)){
                    _path.add(0,this);
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList<Vertex<K,T>> breadthFirst(K _goalKey, ArrayList<Vertex<K,T>> _thisPath, ArrayList<VertexPath<K,T>> _queue){
        visited = true;
        if (_goalKey.compareTo(key) == 0){
            return _thisPath;
        }
        for (Vertex<K,T> _vertex:edges){
            if (!_vertex.visited){
                _vertex.visited = true;
                ArrayList<Vertex<K,T>> _newPath = new ArrayList<>();
                _newPath.addAll(_thisPath);
                _newPath.add(_vertex);
                _queue.add(new VertexPath(_vertex,_newPath));
            }
        }
        if (_queue.get(0) != null){
            VertexPath<K,T> _next = _queue.remove(0);
            return _next.vertex.breadthFirst(_goalKey, _next.path, _queue);
        }
        return new ArrayList<>();
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
