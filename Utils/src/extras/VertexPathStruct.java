package extras;

import java.util.ArrayList;

public class VertexPathStruct <K extends Comparable<K>,T>{ //Struct. Used for breadth-first, where I need to store a vertex and a path to the vertex, in a queue.
    public Vertex<K,T> vertex;
    public ArrayList<Vertex<K,T>> path;
    public VertexPathStruct(Vertex<K,T> _vertex, ArrayList<Vertex<K,T>> _path){
        vertex = _vertex;
        path = _path;
    }
}
