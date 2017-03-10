package tree23main;

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
}
