package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();


    private int lastPosition, position;
    private final Logic logic;
    private String lastString = " ";
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        logic = new LogicImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            lastPosition = position;
        	position = logic.newPosition(cells, position);
            update();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        Random rand = new Random();

        position = rand.nextInt(size*size - size, size*size);
        cells.get(position).setText("*");
        

        for(int i = 0; i < 20; i++){
            int r, l;
            do{
                r = rand.nextInt(size*size);
                l = rand.nextInt(size*size);
            }while(r == l && cells.get(r).getText() != " " && cells.get(l).getText() != " ");
            cells.get(r).setText("R");
            cells.get(l).setText("L");
        }

        

        this.setVisible(true);
    }

    private void update(){
        String tmp = cells.get(position).getText();
        cells.get(position).setText("*");
        cells.get(lastPosition).setText(lastString);
        lastString = tmp;
    }
    
}
