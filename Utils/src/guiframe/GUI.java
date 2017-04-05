package guiframe;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class GUI extends JComponent implements KeyListener{
    private JFrame main_frame;
    
    private final int shape = 0;
    private final int line = 1;
    private final int string = 2;
    
    private Queue<Integer> order_q;
    private Queue<PaintType> paint_q;
    private Queue<KeyEvent> input_q;
    private Queue<PaintText> text_q;
    private Queue<PaintLine> line_q;
    private int x = 20;
    private int y = 20;
    
    private boolean is_painting;
    private int paint_count;
    
    private boolean new_painting;
    
    public GUI(JFrame new_frame){
        if (new_frame == null){
            main_frame = new JFrame();
            main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main_frame.setSize(800,600);
            main_frame.setVisible(true);
        }
        else{
            main_frame = new_frame;
        }
        
        main_frame.getContentPane().add(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        
        order_q = new Queue<>();
        paint_q = new Queue<>();
        input_q = new Queue<>();
        text_q  = new Queue<>();
        line_q = new Queue<>();
        
        is_painting = false;
        paint_count = 0;
        
        new_painting = false;
    }
    public GUI(){
        this(null);
    }
    
    public void draw(String type, int x, int y, int w, int h, Color c){
        PaintType pt = new PaintType();
        pt.type = type;
        pt.x = x;
        pt.y = y;
        pt.height = h;
        pt.width = w;
        pt.color = c;
        
        paint_q.enqueue(pt);
        order_q.enqueue(shape);
        new_painting = true;
    }
    public void draw(String type, int x, int y, int w, int h){
        draw(type, x, y, w, h, Color.black);
    }
    
    public void drawString(String txt, int x, int y, Color color){
        PaintText pt = new PaintText();
        pt.text = txt;
        pt.x = x;
        pt.y = y;
        pt.color = color;
        text_q.enqueue(pt);
        order_q.enqueue(string);
        new_painting = true;
    }
    public void drawString(String txt, int x, int y){
        drawString(txt,x ,y ,Color.BLACK);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2, int line_thickness, Color color){
        PaintLine pl = new PaintLine();
        pl.x1 = x1;
        pl.y1 = y1;
        pl.x2 = x2;
        pl.y2 = y2;
        pl.line_thickness = line_thickness;
        pl.color = color;
        line_q.enqueue(pl);
        order_q.enqueue(line);
        new_painting = true;
    }
    public void drawLine(int x1, int y1, int x2, int y2, int line_thickness){
        drawLine(x1, y1, x2, y2, line_thickness, Color.BLACK);
    }
    
    public KeyEvent getInput(){
        return input_q.dequeue();
    }
    public boolean hasInput(){
        return !input_q.isEmpty();
    }
    public void flushInput(){
        input_q.emptyQueue();
    }
    public JFrame getFrame(){
        return main_frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (new_painting){
            while (is_painting){
                try {
                    Thread.sleep(10);
                    System.out.println("jep  "  + paint_count);
                } catch (InterruptedException ex) {
                }
            }
            is_painting = true;
            paint_count++;
            super.paintComponent(g); 
            
            while (!order_q.isEmpty()){
                int order_type = order_q.dequeue();
                switch(order_type){
                    case shape:
                        paintShape(g,paint_q.dequeue());
                        break;
                    case line:
                        paintLine(g,line_q.dequeue());
                        break;
                    case string:
                        paintString(g,text_q.dequeue());
                }
            }
            
//            PaintLine pl;
//            while(!line_q.isEmpty()){
//                pl = line_q.dequeue();
//                paintLine(g, pl);
////                g.setColor(pl.color);
////                int x_thick = 0;
////                int y_thick = 0;
////                if (Math.abs(pl.x2-pl.x1) >= Math.abs(pl.y2-pl.y1)){
////                    y_thick = 1;
////                }
////                else{
////                    x_thick = 1;
////                }
//////                g.drawLine(pl.x1, pl.y1, pl.x2, pl.y2);
////                for (int i=0; i<pl.line_thickness; i++){
//////                    System.out.println(i);
////                    g.drawLine(pl.x1+(i*x_thick), pl.y1+(i*y_thick), pl.x2+(i*x_thick), pl.y2+(i*y_thick));
////                }
//            }
//            PaintType pt;
//            while(!paint_q.isEmpty()){
//                pt = paint_q.dequeue();
//                paintShape(g, pt);
////                g.setColor(pt.color);
////                switch(pt.type){
////                    case "fillOval":
////                        g.fillOval(pt.x, pt.y, pt.width, pt.height);
////                        break;
////                    case "drawOval":
////                        g.drawOval(pt.x, pt.y, pt.width, pt.height);
////                        break;
////                    case "fillRect":
////                        g.fillRect(pt.x, pt.y, pt.width, pt.height);
////                        break;
////                    case "drawRect":
////                        g.drawRect(pt.x, pt.y, pt.width, pt.height);
////                        break;
////                    default:
////                        System.out.println("\"" + pt.type + "\"" + " is not a valid option.");
////                }
//            }
//            PaintText px;
//            while(!text_q.isEmpty()){
//                px = text_q.dequeue();
//                paintString(g, px);
////                g.setColor(px.color);
////                g.drawString(px.text, px.x, px.y);
//            }
////            g.drawString(Integer.toString(paint_count), 20, 500);
            is_painting = false;
            paint_count--;
            new_painting = false;
        }
    }
    private void paintShape(Graphics g, PaintType pt){
        g.setColor(pt.color);
        switch(pt.type){
            case "fillOval":
                g.fillOval(pt.x, pt.y, pt.width, pt.height);
                break;
            case "drawOval":
                g.drawOval(pt.x, pt.y, pt.width, pt.height);
                break;
            case "fillRect":
                g.fillRect(pt.x, pt.y, pt.width, pt.height);
                break;
            case "drawRect":
                g.drawRect(pt.x, pt.y, pt.width, pt.height);
                break;
            default:
                System.out.println("\"" + pt.type + "\"" + " is not a valid option.");
        }
    }
    private void paintLine(Graphics g, PaintLine pl){
        g.setColor(pl.color);
        int x_thick = 0;
        int y_thick = 0;
        if (Math.abs(pl.x2-pl.x1) >= Math.abs(pl.y2-pl.y1)){
            y_thick = 1;
        }
        else{
            x_thick = 1;
        }
        for (int i=0; i<pl.line_thickness; i++){
            g.drawLine(pl.x1+(i*x_thick), pl.y1+(i*y_thick), pl.x2+(i*x_thick), pl.y2+(i*y_thick));
        }
    }
    private void paintString(Graphics g, PaintText px){
        g.setColor(px.color);
        g.drawString(px.text, px.x, px.y);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
//        System.out.println(ke);
//        x += 20;
//        y += 20;
//        repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
//        System.out.println(ke);
        input_q.enqueue(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
//        System.out.println(ke);
    }
    
    public void close(){
        main_frame.dispatchEvent(new WindowEvent(main_frame, WindowEvent.WINDOW_CLOSING));
    }
}
