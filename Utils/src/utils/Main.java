package utils;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
//        LinkedList<Integer,String> list = new LinkedList<>();
//        
//        System.out.println(list.size());
//        list.add(2, "2");
//        System.out.println(list.size());
//        list.add(4, "4");
//        list.add(5, "5");
//        list.add(6, "6");
//        list.add(7, "7");
//        list.add(8, "8");
//        System.out.println(list.size());
//        list.delete(6);
//        list.delete(2);
//        list.delete(8);
//        System.out.println(list.size());
//        
//        System.out.println(list);
//        
//        LinkedList<Integer,String> list2 = new LinkedList<>();
//        
//        for (String s:list){
//            System.out.println(s);
//        }
//        System.out.println(list);
//        
//        for (String s:list){
//            System.out.println(s);
//        }
//        
//        System.out.println("");
//        for (int i=0; i<list.size(); i++){
//            System.out.println(list.getByIndex(i));
//        }
        
        Graph<Integer,String> graph = new Graph<>(10);
        
        graph.addVertex(1, "One");
        graph.addVertex(2, "Two");
        graph.addVertex(3, "Three");
        graph.addVertex(4, "Four");
        graph.addVertex(5, "Five");
        
        System.out.println(graph);
        
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        
        System.out.println(graph);
//        
//        graph.deleteEdge(1, 2);
//        graph.deleteEdge(1, 3);
//        graph.deleteEdge(1, 1);
//        
//        System.out.println(graph);
//        
//        graph.addVertex(7, "Seven");
//        graph.addEdge(7, 5);
//        
//        System.out.println(graph);
//        
//        graph.deleteVertex(5);
//        graph.deleteVertex(3);
//        
//        System.out.println(graph);

//        Hashmap<Integer,String> map = new Hashmap<>(5);
//    
//        for (Integer i=0; i<10; i++){
//            map.add(i, i.toString());
//        }
//        
//        System.out.println(map);
//        System.out.println(map.size());
//        
////        map.add(12, "t");
//        
//        System.out.println(map.size());
//        
////        map.delete(7);
////        map.delete(2);
////        map.delete(4);
//        
//        System.out.println(map.size());
//        
////        for (int i=0; i<20; i++){
////            map.delete(i);
////        }
//        
//        System.out.println(map);
//        System.out.println(map.size());
//        
//        ArrayList<String> _array = map.toArray();
//        for (String s: _array){
//            System.out.print(s + "  ");
//        }
//        System.out.println("\n============");
//        for (String s: map){
//            System.out.print(s + "  ");
//        }
    }
}
