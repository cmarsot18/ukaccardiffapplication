package Windows;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Open_Create extends JFrame
{
    private JButton Open = new JButton("Open a document");
    private JButton New = new JButton(("Create a document"));
    private  JPanel pan = new JPanel();

    public Open_Create(){
        this.setSize(300,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        pan.setBackground(Color.LIGHT_GRAY);
        pan.setLayout(new BorderLayout());
        JPanel pan2 = new JPanel();
        pan2.setLayout(new GridLayout(3,1));
        pan2.add(Open);
        pan.add(new JLabel(""));
        pan2.add(New);
        pan.add(pan2,BorderLayout.CENTER);
        this.setContentPane(pan2);
        this.setVisible(true);
    }
}
