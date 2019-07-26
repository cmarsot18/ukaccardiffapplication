package Windows;

import Document.Paragraph;
import DB.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Connection extends JFrame implements ActionListener {
    private JButton log = new JButton("Log in");
    private JTextField user = new JTextField();
    private  JPasswordField password = new JPasswordField();
    private JTextField server = new JTextField();
    private JPanel pan = new JPanel();
    private JLabel UserLabel = new JLabel("User :");
    private  JLabel PasswordLabel = new JLabel("Password :");
    private JLabel ServerLabel = new JLabel("Server :");
    private boolean Connected;
    public DB Current_Server = new DB();

    public Connection(){
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setTitle("Connect to the Data Base");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        log.addActionListener(this);
        pan.setBackground(Color.LIGHT_GRAY);
        pan.setLayout(new GridLayout(4,2,20,20));
        pan.add(ServerLabel);
        pan.add(server);
        pan.add(UserLabel);
        pan.add(user);
        pan.add(PasswordLabel);
        pan.add(password);
        pan.add(new JLabel(""));
        pan.add(log);
        this.setContentPane(pan);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a){
        Connected=Current_Server.Connection(server.getText(),user.getText(),password.getPassword().toString());
        if(Connected){
            new Open_Create(this.Current_Server);
            this.dispose();
        }else{
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Can`t access to the server", "Connection Fqilure", JOptionPane.ERROR_MESSAGE);

        }
    }

}