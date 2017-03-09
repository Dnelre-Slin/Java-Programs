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
            NodeI<T> tmp_node = left.append(append_value);
            if (tmp_node != null){
                return mergeLeft(tmp_node);
            }
        }
        else if (right != null && append_value.compareTo(value) >= 0){
            NodeI<T> tmp_node = right.append(append_value);
            if (tmp_node != null){
                return mergeRight(tmp_node);
            }
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
    
    public NodeI<T> mergeLeft(NodeI<T> merge_node){
        NodeI<T> new_this = new Node3(merge_node.getValue(),value);
//        new_this.setChildren(merge_node., right);
        return this;
    }
    public NodeI<T> mergeRight(NodeI<T> merge_node){
        
        return this;
    }

    @Override
    public void setChildren(NodeI<T> left_child, NodeI<T> right_child) {
        left = left_child;
        right = right_child;
    }
    
    @Override
    public T getValue(){
        return value;
    }
}


