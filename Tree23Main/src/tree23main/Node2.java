package tree23main;

public class Node2 <T extends Comparable> implements NodeI<T>{
    T value;
    NodeI<T> left;
    NodeI<T> right;
    
    public Node2(T start_value){
        value = start_value;
        left = null;
        right = null;
    }
    
    @Override
    public NodeI<T> append(T append_value){
        if (left != null && append_value.compareTo(value) < 0){
            left = left.append(append_value);
        }
        else if (right != null && append_value.compareTo(value) >= 0){
            right = right.append(append_value);
        }
        else{
            return remakeNode(append_value);
        }
        return this;
    }
    
    public NodeI<T> remakeNode(T append_value){
        T left_value,right_value;
        if (append_value.compareTo(value) < 0){
            left_value = append_value;
            right_value = value;
        }
        else{
            right_value = append_value;
            left_value = value;
        }
        NodeI<T> new_node = new Node3(left_value,right_value);
        return new_node;
    }

    @Override
    public void setChildren(NodeI<T> left_child, NodeI<T> right_child) {
        left = left_child;
        right = right_child;
    }
}


