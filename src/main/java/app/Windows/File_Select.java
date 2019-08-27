package app.Windows;

import app.DB.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import app.Writer.*;

public class File_Select extends JFrame{
    private String Log;
    private String Pass;
    private String Doc_name;
    private DB Current_server;
    private JFileChooser FileChoose = new JFileChooser();

    public File_Select(String Login, String password,String Doc,DB pDB){
        this.Log = Login;
        this.Pass = password;
        this.Doc_name = Doc;
        this.Current_server = pDB;
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setTitle("Select File");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(FileChoose);
        if (FileChoose.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
            app.Writer.rules_writer.Write(Current_server,FileChoose.getSelectedFile(),Doc_name);
        }
        this.setVisible(true);
    }
}
