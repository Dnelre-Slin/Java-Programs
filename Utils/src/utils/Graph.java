package utils;

import extras.Edge;
import extras.Vertex;
import extras.VertexEdgeStruct;
import extras.VertexPathStruct;
import guiframe.GUI;
import guiframe.Pos;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Graph <K extends Comparable<K>, T>{
    private Hashmap<K,Vertex<K,T>> map; //Stores the graph in a hashmap.
    
    public Graph(int size){
        map = new Hashmap<>(size);
    }
    public Graph(){ //Hashmap will default to size 20, if no size is passed to constructor.
        map = new Hashmap<>();
    }
    
    public void addVertex(K _key, T _core){
        map.add(_key, new Vertex<>(_key, _core)); //Adds vertex to graph.
    }
    public void deleteVertex(K _key){
        Vertex<K,T> _vertex = map.delete(_key); //Deletes vertex from graph.
        if (_vertex != null){ //If something was deleted, 
            for (Edge<K,T> _edge: _vertex.getEdges()){ //delete every edge to this vertex as well.
                Vertex<K,T> _neighborVertex = _edge.getVertex(); //Get neighbor edges,
                _neighborVertex.deleteEdge(_key); //and delete this node from their edges.
            }
        }
    }
    
    public void addEdge(K _key1, K _key2, int _weight){
        if (_key1 != _key2){ //Key can't be the same, or the vertex would make a edge to itself.
            doAddEdge(_key1, _key2, _weight); //Do the add edge on both vertices,
            doAddEdge(_key2, _key1, _weight); //since it is an undirected graph.
        }
    }
    public void addEdge(K _key1, K _key2){
        addEdge(_key1, _key2, 0); //Weight defaults to 0, if no weight is passed.
    }
    private void doAddEdge(K _vertexKey, K _neighborKey, int _weight){
        Vertex<K,T> _vertex = map.get(_vertexKey); //Find both edges.
        Vertex<K,T> _neighbor = map.get(_neighborKey);
        if (_vertex != null && _neighbor != null){ //If both where found,
            _vertex.addEdge(_neighborKey, _neighbor, _weight); //do add edge operation.
        }
    }
    
    public void deleteEdge(K _key1, K _key2){
        if (_key1 != _key2){ 
            doDeleteEdge(_key1, _key2); //Delete edge from both vertices.
            doDeleteEdge(_key2, _key1);            
        }
    }
    private void doDeleteEdge(K _vertexKey, K _neighborKey){
        Vertex<K,T> _vertex = map.get(_vertexKey);
        if (_vertex != null){ //Deletes edge, if vertex was found.
            _vertex.deleteEdge(_neighborKey);
        }
    }
    
    public boolean isNeighbors(K _key1, K _key2){
        Vertex<K,T> _vertex = map.get(_key1); //Checks whether two vertices are neighbors.
        if (_vertex != null && _vertex.getEdge(_key2) != null){
            return true;
        }
        return false;
    }
    public ArrayList<Edge<K,T>> getNeighbors(K _key){
        Vertex<K,T> _vertex = map.get(_key); //Get all neighbors, in an arraylist, of this vertex.
        if (_vertex != null){
            return _vertex.getEdges().toArray();
        }
        return null;
    }
    
    //Find a path using depth-first, and returns the path in an arraylist.
    public ArrayList<Vertex<K,T>> depthFirst(K _startKey, K _goalKey){
        ArrayList<Vertex<K,T>> _path = new ArrayList<>();
        Vertex<K,T> _startVertex = map.get(_startKey);
        if (_startVertex != null){
            _startVertex.depthFirst(_goalKey, _path); //Uses vertex's depthFirst method.
        }
        resetFlags(); //Resets visited flags.
        return _path;
    }
    //Same as above, this return a path in an arraylist. But it finds the path using breadth-first.
    public ArrayList<Vertex<K,T>> breadthFirst(K _startKey, K _goalKey){
        ArrayList<Vertex<K,T>> _path = new ArrayList<>();
        ArrayList<VertexPathStruct<K,T>> _queue = new ArrayList<>(); //Queue, used in vertex class's breadth-first method.
        Vertex<K,T> _startVertex = map.get(_startKey);
        if (_startVertex != null){
            _path.add(_startVertex); //Adds the startVertex to the path.
            _path = _startVertex.breadthFirst(_goalKey, _path, _queue); //Uses vertex's breadthFirst method.
        }
        resetFlags(); //Reset visited flags.
        return _path;
    }
    
    //Used in prims algorithm.
    private void addToQueue(ArrayList<VertexEdgeStruct<K,T>> _queue, Vertex<K,T> _vertex){
        _vertex.setVisited(true); //Set vertex to visited.
        for (Edge<K,T> _edge : _vertex.getEdges()){
            if (!_edge.getVertex().isVisited()){ //Add all vertices that are not already visited to the queue.
                _queue.add(new VertexEdgeStruct(_vertex, _edge));
            }
        }
        _queue.sort(new Comparator<VertexEdgeStruct<K, T>> () { //Sort queue using arraylist own sort method.
            @Override
            public int compare(VertexEdgeStruct<K, T> t, VertexEdgeStruct<K, T> t1) {
                return Integer.compare(t.edge.getWeight(), t1.edge.getWeight()); //Compares the weights in edges.
            }
        });
    }
    //Use prims algorithm to get an mst.
    public void prim(Graph<K,T> _mst, Vertex<K,T> _startVertex){
        ArrayList<VertexEdgeStruct<K,T>> _queue = new ArrayList<>(); //Make a queue of structs, containing edges and struct. See VertexEdgeStruct for more info.
        addToQueue(_queue, _startVertex); //Add start vertex's edges to queue, using the method above.
        _mst.addVertex(_startVertex.getKey(), _startVertex.getCore()); //Add start vertex to mst.
        while (!_queue.isEmpty()){ //Keep going until all connected vertices are added to mst.
            VertexEdgeStruct<K,T> _struct = _queue.remove(0); //Pop first element. Since the queue is sorted, it will place the cheapest edge first.
            if (!_struct.edge.getVertex().isVisited()){ //Check if it is visited. This will make sure that no cycles occur in the mst.
                //Add the vertex in the edge, in the struct, to the mst, with the same key and core as it's normal graph's counterpart.
                _mst.addVertex(_struct.edge.getVertex().getKey(), _struct.edge.getVertex().getCore()); 
                //Connect this vertex, to the vertex that added it to the queue. Both are stored in the struct. This will make an edge in the mst.
                _mst.addEdge(_struct.vertex.getKey(), _struct.edge.getVertex().getKey(), _struct.edge.getWeight());
            }
            addToQueue(_queue, _struct.edge.getVertex()); //Add the recently added vertex's edges to the queue, using the addToQueue method.
        }
    }
    //Get a minimal spanning tree from the graph.
    public Graph<K,T> getMst(K _startVertexKey){
        Graph<K,T> _mst = new Graph<>(); //Instantiate the mst graph.
        Vertex<K,T> _startVertex = map.get(_startVertexKey); //Find the start-vertex, based on the key provided.
        if (_startVertex != null){
            prim(_mst, _startVertex); //Create mst, using prims algorithm.
        }
        resetFlags(); //Reset all visited flags.
        return _mst;
    }
    
    private void resetFlags(){
        for (Vertex<K,T> _vertex: map){
            _vertex.resetFlag(); //Reset flag of every vertex in graph.
        }
    }
    
    //Graphically draws the graph.
    public void drawGraph(GUI gui){
        // Size of ovals.
        int _height = 60;
        int _width = 60;
        //
        LinkedList<K,Pos> _positions = getPosList(); //Get a list of positions from method below.
        
        for (Vertex<K,T> _vertex : map){
            Pos _pos = _positions.get(_vertex.getKey()); //Pos is a struct, storing an x int, and a y int.
            gui.draw("fillOval", _pos.x, _pos.y, _height, _width, Color.yellow); //Draws the vertices as yellow ovals, in the position arranged by getPosList method.
            gui.drawString(_vertex.getCore().toString(), _pos.x+_height/6, _pos.y+_height/2); //Draw core of vertex as string. At a position close to the center of the oval.
            for (Edge<K,T> _edge : _vertex.getEdges()){
                Pos _posNeighbor = _positions.get(_edge.getVertex().getKey());
                if (_posNeighbor != null){ //Draws a line between to neighbors. And draw the weight of the edge, close to the middle of the line.
                    gui.drawLine(_pos.x+_height/2, _pos.y+_width/2, _posNeighbor.x+_height/2, _posNeighbor.y+_width/2, 1);
                    gui.drawString(Integer.toString(_edge.getWeight()), (((_pos.x+_height/2)+(_posNeighbor.x+_height/2))/2), (((_pos.y+_width/2)+(_posNeighbor.y+_width/2))/2), Color.BLUE);
                }
            }
        }
    }
    //Make a linkedlist of positions for each vertex. Used to draw graph graphicly.
    private LinkedList<K,Pos> getPosList(){
        // Values for spacing and positions that will be used.
        Pos _center = new Pos(400,300); //Center of circle.
        double _angle = 0;
        double _radius = 100;
        double _angleInc = (Math.PI/3);
//        Pos _pos = new Pos(_xSpace*2,_ySpace);
        //
        LinkedList<K,Pos> _positions = new LinkedList<>();
        for (Vertex<K,T> _vertex : map){
            int _x = _center.x + (int)(_radius * Math.cos(_angle)); //Calculates x
            int _y = _center.y + (int)(_radius * Math.sin(_angle)); //and y pos.
            _positions.add(_vertex.getKey(), new Pos(_x,_y));
            _angle += _angleInc; //Increments angle by an amount decided by angleInc.
            if (_angle >= Math.PI*2){ //Has come full circle.
                _angle = 0;
                _radius *= 2; //Double the radius.
            }
        }
        return _positions;
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
