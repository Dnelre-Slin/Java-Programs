package extras;

public class Edge <K extends Comparable<K>, T>{
    private Vertex<K,T> vertex;
    private int weight;
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
