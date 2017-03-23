package graphs;
import hashmap.HashMap;
import hashmap.LinkedList;

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
    
    public void addEdge(Key key1, Key key2){
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
}
