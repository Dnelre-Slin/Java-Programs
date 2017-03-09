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
            root.append(new_value);
        }
    }
}
