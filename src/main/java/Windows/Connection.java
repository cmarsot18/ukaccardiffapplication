package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Connection extends JFrame implements ActionListener {
    private JButton log = new JButton("Log in");
    private JTextField user = new JTextField();
    private  JTextField password = new JTextField();
    private JTextField server = new JTextField();
    private JPanel pan = new JPanel();
    private JLabel UserLabel = new JLabel("User :");
    private  JLabel PasswordLabel = new JLabel("Password :");
    private JLabel ServerLabel = new JLabel("Server :");

    public Connection(){
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
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
        System.out.print("appuyer");
    }

}
