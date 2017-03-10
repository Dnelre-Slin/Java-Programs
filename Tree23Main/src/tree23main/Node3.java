package tree23main;

import guiframe.GUI;
import java.awt.Color;

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
            return null;
        }
        return super.append(append_value);
    }
    
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
        if (this.mid != null){
            POS mid_pos = new POS();
            mid_pos.x = pos.x;
            mid_pos.y = pos.y + (size.y+(size.y/2));
            this.mid.drawTree(gui, mid_pos, mid, size,gap/2);
        }
        if (right != null){
            POS right_pos = new POS();
            right_pos.x = pos.x + (gap/2);
            right_pos.y = pos.y + (size.y+(size.y/2));
            right.drawTree(gui, right_pos, mid, size,gap/2);
        }
        
        gui.draw("fillOval", pos.x, pos.y, size.x+10, size.y);
        if (parent_mid != null){
            gui.drawLine(mid.x, mid.y, parent_mid.x, parent_mid.y, 2);
        }
        gui.drawString(value.toString(), pos.x+6, pos.y+14, Color.white);
        gui.drawString(value_right.toString(), pos.x+17, pos.y+14, Color.white);
    }
}
