package tree23main;

import guiframe.GUI;

public interface NodeI <T extends Comparable>{
    public NodeI<T> append(T append_value);
    public void inOrder();
    public void drawTree(GUI gui);
}
