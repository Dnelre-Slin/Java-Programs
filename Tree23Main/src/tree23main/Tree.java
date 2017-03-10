package tree23main;

import guiframe.GUI;

public class Tree <T extends Comparable> {
    NodeI root;
    public Tree(){
        root = null;
    }
    
    public void append(T new_value){
        if (root == null){
            root = new Node2(new_value);
        }
        else{
            NodeI<T> tmp_node = root.append(new_value);
            if (tmp_node != null){
                root = tmp_node;
            }
        }
    }
    
    public void inOrder(){
        System.out.print("InOrder: [");
        if (root != null){
            root.inOrder();
        }
        System.out.println("  ]");
    }
    
    public void drawTree(GUI gui){
        if (root != null){
            POS pos = new POS(400,20);
            POS parent_mid = null;
            POS size = new POS(20,20);
            int start_gap = 350;
            root.drawTree(gui,pos,parent_mid,size,start_gap);
        }
    }
    
                
}
