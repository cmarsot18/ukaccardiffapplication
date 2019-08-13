package app.Windows;

import javax.swing.*;

import app.Document.Paragraph;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javafx.application.Platform.runLater;


public class Text_Editor extends JFrame  {

    private HTMLEditor html;
    private boolean saved;

    public Text_Editor(Paragraph pParagraph,String init,String name){
        saved=false;
        setLayout(new BorderLayout());
        JButton save = new JButton("Save");
        JPanel Panel = new JPanel();
        Panel.setLayout(new GridLayout(1,8));
        JButton Rase = new JButton("Edit RASE");
        Rase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saved=true;
                pParagraph.update(getHtmlText());
                new RASE(pParagraph);
            }
        });
        JButton rename = new JButton("Rename");
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop = new JOptionPane();
                String name = jop.showInputDialog(null, "Enter the new name", "Renaming", JOptionPane.QUESTION_MESSAGE);
                if(name != null) {
                    pParagraph.SetName(name);
                    setTitle(name);
                }
            }
        });
        Panel.add(rename);
        Panel.add(save);
        Panel.add(Rase);
        Panel.add(new JLabel("Color correspondences : ",JLabel.CENTER));
        JPanel red = new JPanel();
        red.setBackground(Color.RED);
        red.add(new JLabel("R"));
        Panel.add(red);
        JPanel green = new JPanel();
        green.setBackground(Color.GREEN);
        green.add(new JLabel("A"));
        Panel.add(green);
        JPanel pink = new JPanel();
        pink.setBackground(Color.MAGENTA);
        pink.add(new JLabel("S"));
        Panel.add(pink);
        JPanel cyan = new JPanel();
        cyan.setBackground(Color.CYAN);
        cyan.add(new JLabel("E"));
        Panel.add(cyan);
        setTitle(name);
        JFXPanel fxPanel = new JFXPanel();
        add(Panel,BorderLayout.NORTH);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saved=true;
                pParagraph.update(getHtmlText());
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, "Successfully saved", "Save", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(fxPanel,BorderLayout.CENTER);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int answer = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(answer == JOptionPane.YES_OPTION ) {
                    setVisible(false);
                }
            }
        });



        runLater(new Runnable() {
            @Override
            public void run() {
                html = new HTMLEditor();
                if(init != null){
                    html.setHtmlText(init);
                }
                Scene scene = new Scene(html);
                fxPanel.setScene(scene);
            }
        });
    }

    public boolean getSaved() {
        return saved;
    }

    public String getHtmlText(){
        return html.getHtmlText();
    }
}
