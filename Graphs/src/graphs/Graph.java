package graphs;
import hashmap.HashMap;
import hashmap.LinkedList;
import hashmap.Node;

public class Graph <Key extends Comparable,T>{
    private HashMap<Key,Vertex<Key,T>> map;
    
    public Graph(int size){
        map = new HashMap<>(size);
    }
    
    public void addVertex(Key append_key, T append_value){
        map.append(append_key, new Vertex(append_value));
    }
    public void removeVertex(Key remove_key){
        map.delete(remove_key);
    }
    
    /*
    Method that the user calls to add edge. Adds an edge to both vertices.
    */
    public void addEdge(Key key1, Key key2){
        doAddEdge(key1, key2);
        doAddEdge(key2, key1);
    }
    /*
    This actually does the edge adding operation. key1 is the vertex that
    gets appended to, while key2 is the edge from vertex with key1 to 
    vertex with key2.
    */
    private void doAddEdge(Key key1, Key key2){
        Vertex<Key,T> vertex1 = map.search(key1);
        if (vertex1 != null){
            Vertex<Key,T> vertex2 = map.search(key2);
            if (vertex2 != null){
                vertex1.addEdge(key2, vertex2);
            }
            else{
                System.out.println("This key: " + key2 + ", not in map.");
            }
        }
        else{
            System.out.println("This key: " + key1 + ", not in map.");
        }
    }
    public void removeEdge(Key key1, Key key2){
        Vertex<Key,T> vertex1 = map.search(key1);
        if (vertex1 != null){
            vertex1.removeEdge(key2);
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += map.toString();
        return s;
    }
    
    public LinkedList<Integer,LinkedList<Key,Vertex<Key,T>>> graphToList(){
        return map.getAllElements();
    }
    public void printVertexEdges(){
        LinkedList<Integer,LinkedList<Key,Vertex<Key,T>>> al = map.getAllElements();
        
        for(int i=0; i<al.size(); i++){
            if (al.search(i) != null){
//                System.out.print(al.search(i).print() + ":  ");
                LinkedList<Key,Vertex<Key,T>> sub_list = (LinkedList<Key,Vertex<Key,T>>) al.search(i).getValue();
                System.out.print(sub_list.print() + ":  ");
                Node<Key,Vertex<Key,T>> n = sub_list.getFirst();
                while (n != null){
                    System.out.print(n.print());
                    n = n.getNext();
                }
//                if (sub_list.search(i).getEdges() != null){
//                    System.out.print(sub_list.search(i).getEdges().print());
//                }
                System.out.println();
            }
        }
    }
}
