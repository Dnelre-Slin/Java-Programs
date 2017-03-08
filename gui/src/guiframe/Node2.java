//package guiframe;
//
//import java.awt.Color;
//import guiframe.GUI;
//import java.awt.Color;
//
//public class Node2 <T extends Comparable>{
//
//    private T value;
//    private Node2<T> left;
//    private Node2<T> right;
//    private Node2<T> parent;
//    
//    public Node2(T new_value, Node2<T> new_parent){
//        value = new_value;
//        parent = new_parent;
//    }
//    
//    public void addChild(T new_value){
//        if (new_value.compareTo(value) < 0){
//            if (left == null){
//                left = new Node2<>(new_value,this);
//            }
//            else{
//                left.addChild(new_value);
//            }
//        }
//        else{
//            if (right == null){
//                right = new Node2<>(new_value,this);
//            }
//            else{
//                right.addChild(new_value);
//            }
//        }
//    }
//    
//    public void drawTree(GUI gui, int x_coord, int y_coord, int x_mid, int y_mid, int depth){
//        if (depth <= 0) depth = 1;
//        gui.draw("fillOval", x_coord, y_coord, 20, 20);
//        gui.drawLine(x_mid, y_mid, x_coord+10, y_coord+10, 3);
//        gui.drawString(value.toString(), x_coord+8, y_coord+15, Color.WHITE);
//        if (left != null){
//            left.drawTree(gui,x_coord-(30*depth),y_coord+50,x_coord+10,y_coord+10,depth/2);
//        }
//        if (right != null){
//            right.drawTree(gui,x_coord+(30*depth),y_coord+50,x_coord+10,y_coord+10,depth/2);
//        }
//    }
//    
//    public Node2<T> searchFor(T search_value){
//        System.out.println(value);
//        if (search_value.compareTo(value) == 0){
//            return this;
//        }
//        else if (search_value.compareTo(value) < 0 && left != null){
//            return left.searchFor(search_value);
//        }
//        else if (search_value.compareTo(value) > 0 && right != null){
//            return right.searchFor(search_value);
//        }
//        else{
//            System.out.println("Value not in tree.");
//            return null;
//        }
//    }
//    
//    public void rmvNode(T remove_value){
//        Node2<T> rmv_node = searchFor(remove_value);
//        Node2<T> replace_node = null;
//        if (rmv_node != null){
//            if (rmv_node.right != null){
//                if (rmv_node.right.left == null){
//                    replace_node = rmv_node.right;
//                    
//                }
//                else{
//                    replace_node = rmv_node.getFurthestLeft();
//                }
//            }
//        }
//    }
//    
//    public Node2<T> removeNode(T remove_value){
//        if (remove_value.compareTo(value) == 0){
//            //Node to be removed.
//            Node2<T> replacement = findReplacement();
//            if (replacement != null && replacement != left){
//                replacement.left = this.left;
//                if (replacement != right){
//                    replacement.right = this.right;
//                }
//            }
//            return replacement;
//        }
//        else if (remove_value.compareTo(value) < 0 && left != null){
//            left = left.removeNode(remove_value);
//        }
//        else if (remove_value.compareTo(value) > 0 && right != null){
//            right = right.removeNode(remove_value);
//        }
//        return this; //Return this to parent.
//    }
////    public Node2<T> findReplacement(){
////        if (left == null && right == null){
////            System.out.println("yup");
////            return null; //Node to be removed is a leafnode.
////        }
////        else if (left != null && right == null){
////            return left;
////        }
////        else if (right.left == null){
////            return right;
////        }
////        return right.getFurthestLeft(this);
////    }
////    public Node2<T> getFurthestLeft(){
////        if (left == null){
////            parent.right = right;
////            parent.left = null;
////            return this;
////        }
////        return left.getFurthestLeft(this);
////    }
//    
//    public void inOrder(){
//        if (left != null){
//            left.inOrder();
//        }
//        System.out.print(value + "  ");
//        if (right != null){
//            right.inOrder();
//        }
//    }
//}
