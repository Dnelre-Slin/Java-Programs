package tree23main;

import guiframe.GUI;

public class Tree23Main {

    public static void main(String[] args) {
        Tree<Integer> t = new Tree();
        
        GUI gui = new GUI();
        t.drawTree(gui);
        
        StringBuilder store_input = new StringBuilder();
        int x = 0;
        boolean run_loop = true;
        while (run_loop){
            //Get input.
            while (gui.checkInput()){
                int key_code = gui.getInput().getKeyCode();
                System.out.println(key_code);
                if (key_code == 27){ //Esc.
                    //Do something.
                    gui.close();
                }
                else if (key_code == 10){ //Enter(Return).
                    if (store_input.length() > 0){
                        int append_value = Integer.parseInt(store_input.toString());
                        t.append(append_value);
                        store_input = new StringBuilder();
                    }
                }
                else if (key_code == 8){ //Backspace.
                    if (store_input.length() > 0){
                        store_input.deleteCharAt(store_input.length()-1);
                    }
                }
                else if (key_code >= 48 && key_code <= 57){ //Numbers. 0-9.
                    if (store_input.length() < 2){
                        store_input.append((key_code-48));
                    }
                }
                else if (key_code >= 96 && key_code <= 105){ //Num numbers. 0-9.
                    if (store_input.length() < 2){
                        store_input.append((key_code-96));
                    }
                }
            }
            t.drawTree(gui);
            gui.drawString(("Append: " + store_input), 300, 500);
            gui.repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
            }
        }
    }
    
}
