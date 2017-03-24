package graphs;

public class Main {
    public static void main(String[] args){
        Graph<Integer,Integer> g = new Graph<>(10);
        
        g.addVertex(3, 3);
        g.addVertex(6, 6);
        g.addVertex(1, 1);
        g.addVertex(2, 2);
        g.addVertex(7, 7);
        g.addVertex(9, 9);
        
        g.addEdge(3, 6);
        g.addEdge(3, 7);
        g.addEdge(7, 9);
        g.addEdge(1, 2);
        
        g.printVertexEdges();
        System.out.println("\n\n");
        
        g.removeEdge(3,7);
        
        g.printVertexEdges();
        
    }
}
