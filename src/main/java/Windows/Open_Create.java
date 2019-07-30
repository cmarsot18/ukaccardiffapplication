package Windows;

import DB.DB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Open_Create extends JFrame implements ActionListener
{
    private JButton Open = new JButton("Open a document");
    private JButton New = new JButton(("Create a document"));
    private  JPanel pan = new JPanel();
    private DB Current_Server;

    public Open_Create(DB pDB){
        this.Current_Server = pDB;
        this.setSize(500,75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Open.addActionListener(this);
        New.addActionListener(this);
        pan.setBackground(Color.LIGHT_GRAY);
        pan.setLayout(new GridLayout(1,2));
        pan.add(Open);
        pan.add(New);
        this.setContentPane(pan);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a){
        if(a.getSource() == Open){
            new Document_Select(this.Current_Server);
            this.dispose();
        }
        if(a.getSource() == New){
            new Doc_Creation(this.Current_Server);
            this.dispose();
        }

    }
}
