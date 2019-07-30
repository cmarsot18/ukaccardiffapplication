package Windows;

import DB.DB;
import Document.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Doc_Creation extends JFrame implements ActionListener {

    private JButton Create = new JButton("Create");
    private JPanel pan = new JPanel();
    private JTextField Name = new JTextField();
    private DB Current_Server;


    public Doc_Creation(DB pDB) {
        this.Current_Server = pDB;
        this.setSize(500, 75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Create.addActionListener(this);
        pan.setLayout(new GridLayout(1,2));
        pan.setBackground(Color.LIGHT_GRAY);
        pan.add(Name);
        pan.add(Create);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                dispose();
                new Open_Create(Current_Server);
            }
        });
        this.setTitle("Enter the name of the new document");
        this.setContentPane(pan);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a){
        Section NewDoc = new Section();
        NewDoc.SetName(this.Name.getText());
        new Document_Display(NewDoc,Current_Server);
        this.dispose();
    }
}
