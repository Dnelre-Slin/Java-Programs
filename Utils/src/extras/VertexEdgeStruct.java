package extras;

public class VertexEdgeStruct <K extends Comparable<K>, T>{ //Struct
    public Vertex<K,T> vertex;
    public Edge<K,T> edge;
    public VertexEdgeStruct(Vertex<K,T> _vertex, Edge<K,T> _edge){
        vertex = _vertex;
        edge = _edge;
    }
}
