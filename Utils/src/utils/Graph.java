package utils;

import extras.Vertex;
import java.util.ArrayList;

public class Graph <K extends Comparable, T>{
    private Hashmap<K,Vertex<K,T>> map;
    
    public Graph(int size){
        map = new Hashmap<>(size);
    }
    public Graph(){
        map = new Hashmap<>();
    }
    
    public void addVertex(K _key, T _core){
        map.add(_key, new Vertex<>(_core));
    }
    public void deleteVertex(K _key){
        Vertex<K,T> _vertex = map.delete(_key);
        if (_vertex != null){
            ArrayList<Vertex<K,T>> _edges = _vertex.getEdges().toArray();
            for (Vertex<K,T> _edge: _edges){
                _edge.deleteEdge(_key);
            }
        }
    }
    
    public void addEdge(K _key1, K _key2){
        if (_key1 != _key2){
            doAddEdge(_key1, _key2);
            doAddEdge(_key2, _key1);
        }
    }
    private void doAddEdge(K _vertexKey, K _edgeKey){
        Vertex<K,T> _vertex = map.get(_vertexKey);
        Vertex<K,T> _edge = map.get(_edgeKey);
        if (_vertex != null && _edge != null){
            _vertex.addEdge(_edgeKey, _edge);
        }
    }
    
    public void deleteEdge(K _key1, K _key2){
        if (_key1 != _key2){
            doDeleteEdge(_key1, _key2);
            doDeleteEdge(_key2, _key1);            
        }
    }
    private void doDeleteEdge(K _vertexKey, K _edgeKey){
        Vertex<K,T> _vertex = map.get(_vertexKey);
        if (_vertex != null){
            _vertex.deleteEdge(_edgeKey);
        }
    }
    
    public boolean isNeighbors(K _key1, K _key2){
        Vertex<K,T> _vertex = map.get(_key1);
        if (_vertex != null && _vertex.getEdge(_key2) != null){
            return true;
        }
        return false;
    }
    public ArrayList<Vertex<K,T>> getNeighbors(K _key){
        Vertex<K,T> _vertex = map.get(_key);
        if (_vertex != null){
            return _vertex.getEdges().toArray();
        }
        return null;
    }
    
    private void resetFlags(){
        for (Vertex<K,T> _vertex: map){
            _vertex.resetFlag();
        }
//        ArrayList<Vertex<K,T>> _list = map.toArray();
//        for (Vertex<K,T> _vertex:_list){
//            _vertex.resetFlag();
//        }
    }

    @Override
    public String toString() {
        String s = "";
        
        for (Vertex<K,T> _vertex: map){
            s += _vertex.getCore() + ": ";
            LinkedList<K,Vertex<K,T>> _edges = _vertex.getEdges();
            if (_edges != null){
                s += _edges.toString();
            }
            s += '\n';
        }
        return s;
//        ArrayList<Vertex<K,T>> _array = map.toArray();
//        String s = "";
//        for (Vertex<K,T> _list:_array){
//            s += _list.getCore();
//            s += ": ";
//            LinkedList<K,Vertex<K,T>> _edges = _list.getEdges();
//            if (_edges != null){
//                s += _edges.toString();
//            }
//            s += '\n';
//        }
//        return s;
//        ArrayList<Vertex<K,T>> _array = map.toArray();
//        String s = "";
//        for (int i=0; i<_array.size(); i++){
//            s += _array.get(i).getCore();
//            s += ": ";
//            LinkedList<K,Vertex<K,T>> _edges = _array.get(i).getEdges();
//            if (_edges != null){
//                s += _edges.toString();
//            }
//            s += '\n';
//        }
//        return s;
    }
}
