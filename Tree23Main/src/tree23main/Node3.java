package tree23main;

public class Node3 <T extends Comparable> extends Node2<T> implements NodeI<T>{
    protected T value_right;
    protected NodeI<T> mid;
    
    public Node3(T start_value_left, T start_value_right){
        super(start_value_left);
        value_right = start_value_right;
        mid = null;
    }
    
    @Override
    public NodeI<T> append(T append_value){
        if (mid != null && append_value.compareTo(value) >= 0 && append_value.compareTo(value_right) < 0){
            NodeI<T> tmp_node = mid.append(append_value);
            if (tmp_node != null){
                try{
                    mid = checkN3((Node3<T>)tmp_node);
                }
                catch (Exception e){
                    return mergeMid((Node2<T>)tmp_node);
                }
            }
        }
        return super.append(append_value);
    }
    
//    @Override
//    public NodeI<T> append(T append_value) {
//        if (left != null && append_value.compareTo(value) < 0){
//            left = left.append(append_value);
//        }
//        else if (mid != null && append_value.compareTo(value_right) < 0){
//            mid = mid.append(append_value);
//        }
//        else if (right != null && append_value.compareTo(value_right) >= 0){
//            right = right.append(append_value);
//        }
//        else{
//            return remakeNode(append_value);
//        }
//        return this;
//    }
    
    @Override
    public NodeI<T> remakeNode(T append_value){
        T left_value,mid_value,right_value;
        left_value = value;
        mid_value = append_value;
        right_value = value_right;
        if (append_value.compareTo(value) < 0){
            left_value = append_value;
            mid_value = value;
        }
        else if (append_value.compareTo(value_right) >= 0){
            right_value = append_value;
            mid_value = value_right;
        }
        Node2<T> top_node = new Node2(mid_value);
        Node2<T> left_node = new Node2(left_value);
        Node2<T> right_node = new Node2(right_value);
        
        top_node.left = left_node;
        top_node.right = right_node;
//        top_node.setChildren(left_node, right_node);
        return top_node;
    }
    
    @Override
    public NodeI<T> mergeLeft(Node2<T> m_node){
        Node2<T> top_node = new Node2(value);
        Node2<T> left_node = m_node;
        Node2<T> right_node = new Node2(value_right);
        right_node.left = mid;
        right_node.right = right;
        top_node.left = left_node;
        top_node.right = right_node;
        return top_node;
    }
    public NodeI<T> mergeMid(Node2<T> m_node){
        Node2<T> top_node = m_node;
        Node2<T> left_node = new Node2(value);
        Node2<T> right_node = new Node2(value_right);
        left_node.left = left;
        left_node.right = top_node.left;
        right_node.left = top_node.right;
        right_node.right = right;
        top_node.left = left_node;
        top_node.right = right_node;
        return top_node;
    }
    @Override
    public NodeI<T> mergeRight(Node2<T> m_node){
        Node2<T> top_node = new Node2(value_right);
        Node2<T> left_node = new Node2(value);
        Node2<T> right_node = m_node;
        left_node.left = left;
        left_node.right = mid;
        top_node.left = left_node;
        top_node.right = right_node;
        return top_node;
    }
    
    @Override
    public void inOrder(){
        if (left != null){
            left.inOrder();
        }
        System.out.print("  " + value);
        if (mid != null){
            mid.inOrder();
        }
        System.out.print("  " + value_right);
        if (right != null){
            right.inOrder();
        }
    }
}
