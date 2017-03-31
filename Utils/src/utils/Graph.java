package utils;

import extras.Edge;
import extras.Vertex;
import extras.VertexEdgeStruct;
import extras.VertexPathStruct;
import java.util.ArrayList;

public class Graph <K extends Comparable<K>, T>{
    private Hashmap<K,Vertex<K,T>> map;
    
    public Graph(int size){
        map = new Hashmap<>(size);
    }
    public Graph(){
        map = new Hashmap<>();
    }
    
    public void addVertex(K _key, T _core){
        map.add(_key, new Vertex<>(_key, _core));
    }
    public void deleteVertex(K _key){
        Vertex<K,T> _vertex = map.delete(_key);
        if (_vertex != null){
            for (Edge<K,T> _edge: _vertex.getEdges()){
                Vertex<K,T> _neighborVertex = _edge.getVertex();
                _neighborVertex.deleteEdge(_key);
            }
        }
    }
    
    public void addEdge(K _key1, K _key2, int _weight){
        if (_key1 != _key2){
            doAddEdge(_key1, _key2, _weight);
            doAddEdge(_key2, _key1, _weight);
        }
    }
    public void addEdge(K _key1, K _key2){
        addEdge(_key1, _key2, 0);
    }
    private void doAddEdge(K _vertexKey, K _neighborKey, int _weight){
        Vertex<K,T> _vertex = map.get(_vertexKey);
        Vertex<K,T> _neighbor = map.get(_neighborKey);
        if (_vertex != null && _neighbor != null){
            _vertex.addEdge(_neighborKey, _neighbor, _weight);
        }
    }
    
    public void deleteEdge(K _key1, K _key2){
        if (_key1 != _key2){
            doDeleteEdge(_key1, _key2);
            doDeleteEdge(_key2, _key1);            
        }
    }
    private void doDeleteEdge(K _vertexKey, K _neighborKey){
        Vertex<K,T> _vertex = map.get(_vertexKey);
        if (_vertex != null){
            _vertex.deleteEdge(_neighborKey);
        }
    }
    
    public boolean isNeighbors(K _key1, K _key2){
        Vertex<K,T> _vertex = map.get(_key1);
        if (_vertex != null && _vertex.getEdge(_key2) != null){
            return true;
        }
        return false;
    }
    public ArrayList<Edge<K,T>> getNeighbors(K _key){
        Vertex<K,T> _vertex = map.get(_key);
        if (_vertex != null){
            return _vertex.getEdges().toArray();
        }
        return null;
    }
    
    public ArrayList<Vertex<K,T>> depthFirst(K _startKey, K _goalKey){
        ArrayList<Vertex<K,T>> _path = new ArrayList<>();
        Vertex<K,T> _startVertex = map.get(_startKey);
        if (_startVertex != null){
            _startVertex.depthFirst(_goalKey, _path);
        }
        resetFlags();
        return _path;
    }
    
    public ArrayList<Vertex<K,T>> breadthFirst(K _startKey, K _goalKey){
        ArrayList<Vertex<K,T>> _path = new ArrayList<>();
        ArrayList<VertexPathStruct<K,T>> _queue = new ArrayList<>();
        Vertex<K,T> _startVertex = map.get(_startKey);
        if (_startVertex != null){
            _path.add(_startVertex);
            _path = _startVertex.breadthFirst(_goalKey, _path, _queue);
        }
        resetFlags();
        return _path;
    }
    
    private void addToQueue(ArrayList<VertexEdgeStruct<K,T>> _queue, Vertex<K,T> _vertex){
        _vertex.setVisited(true);
        for (Edge<K,T> _edge : _vertex.getEdges()){
            if (!_edge.getVertex().isVisited()){
                for (int i=0; i<=_queue.size(); i++){ //Makes sure that _queue is always sorted.
                    if (i == _queue.size()){ // Adds to end of arraylist, if it is the biggest element.
                        _queue.add(new VertexEdgeStruct<>(_vertex, _edge));
                        break;
                    }
                    if (_edge.getWeight() < _queue.get(i).edge.getWeight()){
                        _queue.add(i, new VertexEdgeStruct<>(_vertex, _edge));
                        break;
                    }
                }
            }
        }
    }
    public void prim(Graph<K,T> _mst, Vertex<K,T> _startVertex){
        ArrayList<VertexEdgeStruct<K,T>> _queue = new ArrayList<>();
        addToQueue(_queue, _startVertex);
        _mst.addVertex(_startVertex.getKey(), _startVertex.getCore());
        while (!_queue.isEmpty()){
            VertexEdgeStruct<K,T> _struct = _queue.remove(0); //Pop first element.
            if (!_struct.edge.getVertex().isVisited()){
                _mst.addVertex(_struct.edge.getVertex().getKey(), _struct.edge.getVertex().getCore());
                _mst.addEdge(_struct.vertex.getKey(), _struct.edge.getVertex().getKey(), _struct.edge.getWeight());
            }
            addToQueue(_queue, _struct.edge.getVertex());
        }
    }
    public Graph<K,T> getMst(K _startVertexKey){
        Graph<K,T> _mst = new Graph<>();
        Vertex<K,T> _startVertex = map.get(_startVertexKey);
        if (_startVertex != null){
            prim(_mst, _startVertex);
        }
        resetFlags();
        return _mst;
    }
    
    private void resetFlags(){
        for (Vertex<K,T> _vertex: map){
            _vertex.resetFlag();
        }
    }

    @Override
    public String toString() {
        String s = "";
        
        for (Vertex<K,T> _vertex: map){
            s += _vertex.getCore() + ": ";
            LinkedList<K,Edge<K,T>> _edges = _vertex.getEdges();
            if (_edges != null){
                s += _edges.toString();
            }
            s += '\n';
        }
        return s;
    }
}
