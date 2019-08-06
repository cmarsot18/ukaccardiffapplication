package Windows;

import javax.swing.*;

import Document.Paragraph;
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
        JButton save = new JButton("Save & Exit");
        setTitle(name);
        JFXPanel fxPanel = new JFXPanel();
        add(save,BorderLayout.NORTH);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saved=true;
                pParagraph.save(getHtmlText());
//                pSesson.setState(false);
                setVisible(false);
                System.out.println("SAVED!");
                System.out.println(getHtmlText());
                dispose();
            }
        });
        add(fxPanel,BorderLayout.CENTER);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int answer = JOptionPane.showConfirmDialog(null,
                        "Do you want to save?",
                        "Modification save",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(answer == JOptionPane.YES_OPTION ) {
                    saved=true;
                    pParagraph.save(getHtmlText());
//                    pSesson.setState(false);
                    System.out.println("SAVED!");
                    System.out.println(getHtmlText());
                    dispose();
                } else {
                    saved=false;
                }
                setVisible(false);
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
