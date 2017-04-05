package extras;

public class Edge <K extends Comparable<K>, T>{
    private Vertex<K,T> vertex; //The vertex at the other end of the edge. The neighbor of the vertex that has this edge.
    private int weight;         //Weight of the edge.
    public Edge(Vertex<K,T> _vertex, int _weight){
        vertex = _vertex;
        weight = _weight;
    }
    public Vertex<K,T> getVertex(){
        return vertex;
    }
    public int getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return ( "(" + weight + ") " + vertex);
    }
}
