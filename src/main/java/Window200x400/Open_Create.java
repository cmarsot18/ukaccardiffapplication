package Window200x400;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Open_Create extends JFrame
{
    public Open_Create(){
        this.setSize(300,600);
        JPanel pan = new JPanel();
        pan.setBackground(Color.getHSBColor(217,217,217));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(10,10,20,20));

        this.add(new JButton("Open a document"));

        this.add(new JButton("Create a document"));















        this.setVisible(true);
    }
}
