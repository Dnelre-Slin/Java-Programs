package extras;

public class VertexEdgeStruct <K extends Comparable<K>, T>{ //Struct. Used to store both sides of an edge. Only necessary in the prims algorithm method.
    public Edge<K,T> edge; //Edge only stores destination vertex. 
    public Vertex<K,T> vertex; //This struct stores the source vertex as well.
    public VertexEdgeStruct(Vertex<K,T> _vertex, Edge<K,T> _edge){
        vertex = _vertex;
        edge = _edge;
    }
}
