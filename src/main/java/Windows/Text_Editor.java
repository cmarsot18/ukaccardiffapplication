package Windows;

import javax.swing.*;
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

    public Text_Editor(){
        saved=false;
        setLayout(new BorderLayout());
        JButton save = new JButton("Save & Exit");
        JFXPanel fxPanel = new JFXPanel();
        add(save,BorderLayout.NORTH);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saved=true;
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
                int reponse = JOptionPane.showConfirmDialog(null,
                        "Do you want to save?",
                        "Modification save",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(reponse == JOptionPane.YES_OPTION ) {
                    saved=true;

                    //the code to update anything based on the HTML response must be here
                    //this may mean you need to pass in something to this class so it can update the database.
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
