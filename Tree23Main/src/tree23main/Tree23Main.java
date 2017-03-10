package tree23main;

public class Tree23Main {

    public static void main(String[] args) {
        Tree<Integer> t = new Tree();
        t.append(7);
        t.append(3);
        t.append(1);
        t.append(2);
        t.append(6);
        t.append(3);
        t.append(4);
        t.append(9);
        t.append(1);
        
        t.inOrder();
    }
    
}
