package extras;

import java.util.ArrayList;

public class VertexPath <K extends Comparable<K>,T>{ //Struct
    public Vertex<K,T> vertex;
    public ArrayList<Vertex<K,T>> path;
    public VertexPath(Vertex<K,T> _vertex, ArrayList<Vertex<K,T>> _path){
        vertex = _vertex;
        path = _path;
    }
}
