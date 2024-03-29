package app.Windows;

import app.DB.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

public class Rules_Maker extends JFrame implements ActionListener {
    private JButton Select = new JButton("Select this document");
    private JComboBox Existing_DB = new JComboBox();
    private JPanel pan = new JPanel();
    public String Document_Name;
    public DB Current_server;
    private String Log;
    private String Pass;
    private JFileChooser FileChoose = new JFileChooser();

    public Rules_Maker(DB pDB,String Login,String Password){
        this.Log = Login;
        this.Pass = Password;
        this.Current_server = pDB;
        this.setSize(500, 75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                dispose();
                new Open_Create(Current_server,Log,Pass);
            }
        });
        Select.addActionListener(this);
        Existing_DB.addItemListener(new Rules_Maker.ItemState());
        pan.setBackground(Color.LIGHT_GRAY);
        pan.setLayout(new GridLayout(1,2));
        Existing_DB.setPreferredSize(new Dimension(200,75));
        List<String> temp = Current_server.getDatabase().list();
        Iterator<String> I = temp.iterator();
        String stemp;
        while(I.hasNext()){
            stemp =I.next();
            Existing_DB.addItem(stemp);
        }
        pan.add(Existing_DB);
        pan.add(Select);
        this.setContentPane(pan);
        this.setVisible(true);


    }
    private class ItemState implements ItemListener {
        public void itemStateChanged(ItemEvent e){
            Document_Name = Existing_DB.getSelectedItem().toString();
        }
    }

    public void actionPerformed(ActionEvent a){
        this.add(FileChoose);
        if (FileChoose.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
            app.Writer.rules_writer.Write(Current_server,FileChoose.getSelectedFile(),Document_Name);
        }
    }
}
