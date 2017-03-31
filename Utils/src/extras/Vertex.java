package extras;

import java.util.ArrayList;
import utils.LinkedList;

public class Vertex <K extends Comparable<K>, T>{
    protected T core;
    protected K key;
    protected LinkedList<K,Edge<K,T>> edges;
    protected boolean visited;
    
    public Vertex(K _key, T _core){
        core = _core;
        key = _key;
        edges = new LinkedList<>();
        visited = false;
    }
    
    public void addEdge(K _key, Vertex<K,T> _edge, int _weight){
        if (edges.get(_key) == null){// Check if this edge already exists.
            edges.add(_key, new Edge(_edge, _weight));
        }
    }
    
    public void deleteEdge(K _key){
        edges.delete(_key);
    }
    
    public Edge<K,T> getEdge(K _key){
        return edges.get(_key);
    }
    
    public LinkedList<K,Edge<K,T>> getEdges(){
        return edges;
    }
    
    public boolean depthFirst(K _goalKey, ArrayList<Vertex<K,T>> _path){
        visited = true;
        if (_goalKey.compareTo(key) == 0){
            _path.add(0,this);
            return true;
        }
        for (int i=0; i<edges.size(); i++){
            Vertex<K,T> _vertex = edges.getByIndex(i).getVertex();
            if (!_vertex.visited){
                if (_vertex.depthFirst(_goalKey, _path)){
                    _path.add(0,this);
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList<Vertex<K,T>> breadthFirst(K _goalKey, ArrayList<Vertex<K,T>> _thisPath, ArrayList<VertexPathStruct<K,T>> _queue){
        visited = true;
        if (_goalKey.compareTo(key) == 0){
            return _thisPath;
        }
        for (Edge<K,T> _edge:edges){
            Vertex<K,T> _vertex = _edge.getVertex();
            if (!_vertex.visited){
                _vertex.visited = true;
                ArrayList<Vertex<K,T>> _newPath = new ArrayList<>();
                _newPath.addAll(_thisPath);
                _newPath.add(_vertex);
                _queue.add(new VertexPathStruct(_vertex,_newPath));
            }
        }
        if (_queue.get(0) != null){
            VertexPathStruct<K,T> _next = _queue.remove(0);
            return _next.vertex.breadthFirst(_goalKey, _next.path, _queue);
        }
        return new ArrayList<>();
    }
    
    public T getCore(){
        return core;
    }
    public K getKey(){
        return key;
    }
    
    public void resetFlag(){
        visited = false;
    }
    
    public void setVisited(boolean _visited){
        visited = _visited;
    }
    public boolean isVisited(){
        return visited;
    }

    @Override
    public String toString(){
        return core.toString();
    }
}
