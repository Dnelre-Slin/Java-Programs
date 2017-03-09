package tree23main;

public class Node3 <T extends Comparable> implements NodeI<T>{
    T value_left;
    T value_right;
    NodeI<T> left;
    NodeI<T> mid;
    NodeI<T> right;
    
    public Node3(T start_value_left, T start_value_right){
        value_left = start_value_left;
        value_right = start_value_right;
        left = mid = right = null;
    }
    
    @Override
    public NodeI<T> append(T append_value) {
        if (left != null && append_value.compareTo(value_left) < 0){
            left = left.append(append_value);
        }
        else if (mid != null && append_value.compareTo(value_right) < 0){
            mid = mid.append(append_value);
        }
        else if (right != null && append_value.compareTo(value_right) >= 0){
            right = right.append(append_value);
        }
        else{
            return remakeNode(append_value);
        }
        return this;
    }
    
    public NodeI<T> remakeNode(T append_value){
        T left_value,mid_value,right_value;
        left_value = value_left;
        mid_value = append_value;
        right_value = value_right;
        if (append_value.compareTo(value_left) < 0){
            left_value = append_value;
            mid_value = value_left;
        }
        else if (append_value.compareTo(value_right) >= 0){
            right_value = append_value;
            mid_value = value_right;
        }
        NodeI<T> top_node = new Node2(mid_value);
        NodeI<T> left_node = new Node2(left_value);
        NodeI<T> right_node = new Node2(right_value);
        
        top_node.setChildren(left_node, right_node);
        return top_node;
    }
    @Override
    public void setChildren(NodeI<T> left_child, NodeI<T> right_child){
        //Do nothing.
    }
    
}
