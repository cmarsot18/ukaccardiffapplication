package app.Windows;

import app.DB.DB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Open_Create extends JFrame implements ActionListener
{
    private JButton Open = new JButton("Open a document");
    private JButton New = new JButton(("Create a document"));
    private JButton Rules = new JButton("Make rules");
    private  JPanel pan = new JPanel();
    private DB Current_Server;
    private String Log;
    private String Pass;

    public Open_Create(DB pDB, String Login, String Password){
        this.Log = Login;
        this.Pass = Password;
        this.Current_Server = pDB;
        this.setSize(500,75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(null,
                        "Do you want to change the server?",
                        "Server",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(reponse == JOptionPane.YES_OPTION ){
                    new Connection();
                }else{
                    System.exit(0);
                }


            }
        });
        Open.addActionListener(this);
        New.addActionListener(this);
        Rules.addActionListener(this);
        pan.setBackground(Color.LIGHT_GRAY);
        pan.setLayout(new GridLayout(1,2));
        pan.add(Open);
        pan.add(New);
        pan.add(Rules);
        this.setContentPane(pan);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a){
        if(a.getSource() == Open){
            new Document_Select(this.Current_Server,this.Log,this.Pass);
            this.dispose();
        }
        if(a.getSource() == New){
            new Doc_Creation(this.Current_Server,this.Log,this.Pass);
            this.dispose();
        }
        if(a.getSource() == Rules){
            new Rules_Maker(Current_Server,Log,Pass);
            this.dispose();
        }
    }
}
