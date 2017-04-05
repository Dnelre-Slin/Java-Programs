package extras;

import java.util.ArrayList;
import utils.LinkedList;

public class Vertex <K extends Comparable<K>, T>{
    protected T core; //The core of the vertex. It is the value which is actually stored by the vertex.
    protected K key;  
    protected LinkedList<K,Edge<K,T>> edges; //All of this vertexes edges, stored in a linkedlist.
    protected boolean visited; //Used by Depth-first, Breadth-frist, and prims, to keep track of which vertices that has been visited.
    
    public Vertex(K _key, T _core){
        core = _core;
        key = _key;
        edges = new LinkedList<>();
        visited = false;
    }
    
    public void addEdge(K _key, Vertex<K,T> _edge, int _weight){
        if (edges.get(_key) == null){// Check if this edge already exists.
            edges.add(_key, new Edge(_edge, _weight)); //If it does not, make it.
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
        visited = true; //Sets this vertex to visited.
        if (_goalKey.compareTo(key) == 0){ //If this is the goal node,
            _path.add(0,this); //add it to path, and return true, indicating that the path is found.
            return true;
        }
        for (int i=0; i<edges.size(); i++){
            Vertex<K,T> _vertex = edges.getByIndex(i).getVertex(); //Loop through the vertex of every edge.
            if (!_vertex.visited){
                if (_vertex.depthFirst(_goalKey, _path)){ //Recursively look deeper for the goal node. Will return true if found.
                    _path.add(0,this); //Path will be stored on the way back up the stack.
                    return true; //Return true to the last recurive call.
                }
            }
        }
        return false;
    }
    
    public ArrayList<Vertex<K,T>> breadthFirst(K _goalKey, ArrayList<Vertex<K,T>> _thisPath, ArrayList<VertexPathStruct<K,T>> _queue){
        visited = true; //Sets vertex to visited.
        if (_goalKey.compareTo(key) == 0){ //If goal node is found,
            return _thisPath;              // return the path that was taken to reach this node.
        }
        for (Edge<K,T> _edge:edges){
            Vertex<K,T> _vertex = _edge.getVertex();
            if (!_vertex.visited){
                _vertex.visited = true;
                ArrayList<Vertex<K,T>> _newPath = new ArrayList<>(); //Creates a new path,
                _newPath.addAll(_thisPath);//which adds the path to this point, 
                _newPath.add(_vertex);     //and adds one more vertex to path; this vertex.
                _queue.add(new VertexPathStruct(_vertex,_newPath)); //Append this vertex, and the path to it, by storing them in a struct.
            }
        }
        if (_queue.get(0) != null){ 
            VertexPathStruct<K,T> _next = _queue.remove(0); //Pop the first element in the queue.
            return _next.vertex.breadthFirst(_goalKey, _next.path, _queue); //Recursivlely traverse the graph.
        }
        return new ArrayList<>(); //Return empty path, if no path was found.
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
