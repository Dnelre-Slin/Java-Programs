package tree23main;

public interface NodeI <T extends Comparable>{
    
    
    
    public NodeI<T> append(T append_value);
    public void setChildren(NodeI<T> left_child, NodeI<T> right_child);
//    public NodeI<T> mergeNode(Node2 n);
    public T getValue();
}
