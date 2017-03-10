package tree23main;

import guiframe.GUI;
import java.awt.Color;

public class Node2 <T extends Comparable> implements NodeI<T>{
    protected T value;
    protected NodeI<T> left;
    protected NodeI<T> right;
    
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
                try{
                    left = checkN3((Node3<T>) tmp_node);
                }
                catch(Exception e){
                    return mergeLeft((Node2<T>)tmp_node);
                }
            }
        }
        else if (right != null && append_value.compareTo(value) >= 0){
            NodeI<T> tmp_node = right.append(append_value);
            if (tmp_node != null){
                try{
                    right = checkN3((Node3<T>) tmp_node);
                }
                catch(Exception e){
                    return mergeRight((Node2<T>)tmp_node);
                }
            }
        }
        else{
            return remakeNode(append_value);
        }
        return null;
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

    public NodeI<T> checkN3(Node3<T> n3){
        return n3;
    }
    
    public NodeI<T> mergeLeft(Node2<T> m_node){
        Node3<T> n3 = new Node3(m_node.value,value);
        n3.left = m_node.left;
        n3.mid = m_node.right;
        n3.right = right;
        return n3;
    }
    public NodeI<T> mergeRight(Node2<T> m_node){
        Node3<T> n3 = new Node3(value,m_node.value);
        n3.left = left;
        n3.mid = m_node.left;
        n3.right = m_node.right;
        return n3;
    }
    
    @Override
    public NodeI<T> search(T search_value){
        if (search_value.equals(value)){
            return this;
        }
        if (left != null && search_value.compareTo(value) < 0){
            return left.search(search_value);
        }
        if (right != null && search_value.compareTo(value) > 0){
            return right.search(search_value);
        }
        System.out.println("Value not in tree. Null returned.");
        return null;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public void inOrder() {
        if (left != null){
            left.inOrder();
        }
        System.out.print("  " + value);
        if (right != null){
            right.inOrder();
        }
    }

    @Override
    public void drawTree(GUI gui, POS pos, POS parent_mid, POS size, int gap) {
        if (gap <= 0){
            gap = 1;
        }
        POS mid = new POS();

        mid.x = pos.x + (size.x/2);
        mid.y = pos.y + (size.y/2);
        
        if (left != null){
            POS left_pos = new POS();
            left_pos.x = pos.x - (gap/2);
            left_pos.y = pos.y + (size.y+(size.y/2));
            left.drawTree(gui, left_pos, mid, size,gap/2);
        }
        if (right != null){
            POS right_pos = new POS();
            right_pos.x = pos.x + (gap/2);
            right_pos.y = pos.y + (size.y+(size.y/2));
            right.drawTree(gui, right_pos, mid, size,gap/2);
        }
        
        gui.draw("fillOval", pos.x, pos.y, size.x, size.y);
        if (parent_mid != null){
            gui.drawLine(mid.x, mid.y, parent_mid.x, parent_mid.y, 2);
        }
        gui.drawString(value.toString(), pos.x+6, pos.y+14, Color.white);
    }
}


