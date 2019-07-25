package Windows;

import javax.swing.*;
import java.awt.*;

public class Doc_Creaction extends JFrame {

    private JButton Create = new JButton("Create");
    private JPanel pan = new JPanel();
    private JTextField Name = new JTextField();


    public Doc_Creaction() {
        this.setSize(500, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        pan.setLayout(new GridLayout(1,2,20,20));
        pan.setBackground(Color.LIGHT_GRAY);
        pan.add(Name);
        pan.add(Create);
        this.setTitle("Enter the name of the new document");
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
