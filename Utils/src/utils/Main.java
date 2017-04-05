package utils;

import extras.Vertex;
import guiframe.GUI;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        
        Graph<Integer,String> graph = new Graph<>(10);
        
        graph.addVertex(1, "One");
        graph.addVertex(2, "Two");
        graph.addVertex(3, "Three");
        graph.addVertex(4, "Four");
        graph.addVertex(5, "Five");
        graph.addVertex(6, "Six");
        graph.addVertex(7, "Seven");
        graph.addVertex(8, "Eight");
        
        System.out.println(graph);
        
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 7);
        graph.addEdge(4, 3, 1);
        graph.addEdge(4, 5, 2);
        graph.addEdge(5, 3, 8);
        
        graph.addEdge(6, 2, 13);
        graph.addEdge(6, 8, 6);
        graph.addEdge(8, 3, 11);
        
        System.out.println(graph);
//        
        graph.deleteEdge(1, 2);
        graph.deleteEdge(1, 3);
        graph.deleteEdge(1, 1);
        
        System.out.println(graph);
//        
        graph.addVertex(7, "Seven");
        graph.addEdge(7, 5, 12);
        
        System.out.println(graph);
//        
        graph.addEdge(1, 7, 4);
        graph.addEdge(3, 7, 88);
        
        System.out.println(graph);
        
        ArrayList<Vertex<Integer,String>> _list = graph.depthFirst(3, 7);
        
        for (Vertex<Integer,String> _vertex:_list){
            System.out.print(_vertex + "   ");
        }
        System.out.println("\n");
        
        _list = graph.depthFirst(5, 7);
        
        for (Vertex<Integer,String> _vertex:_list){
            System.out.print(_vertex + "   ");
        }
        System.out.println("\n");
        
        ArrayList<Vertex<Integer,String>> _list2 = graph.breadthFirst(5, 7);
        
        for (Vertex<Integer,String> _vertex:_list2){
            System.out.print(_vertex + "   ");
        }
        System.out.println("\n");
        
        Graph<Integer,String> mst = graph.getMst(6);
        
        System.out.println(mst);
        
        GUI gui = new GUI();
        
        graph.drawGraph(gui);
        
        //Running gui. (Not 100% reliable. If in doubt, check the print-outs in the output-window).
        
        graph.drawGraph(gui);
        gui.drawString("Press Space to swap between MST and normal graph.", 200, 550);
        gui.repaint();
        
        boolean nextDrawMst = true;
        boolean runProgram = true;
        while (runProgram){
            while (gui.hasInput()){
                switch(gui.getInput().getKeyCode()){
                    case 32: //Space pressed.
                        if (nextDrawMst){
                            mst.drawGraph(gui);
                            nextDrawMst = false;
                        }else{
                            graph.drawGraph(gui);
                            nextDrawMst = true;
                        }
                        gui.drawString("Press Space to swap between MST and normal graph.", 200, 550);
                        gui.repaint();
                        break;
                }
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        
        //End of gui.
        
    }
}
