package Windows;

import javax.swing.*;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.web.HTMLEditor;
import DB.DB;
import Document.Paragraph;
import Document.Section;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javafx.application.Platform.runLater;


public class Text_Editor extends JFrame implements ActionListener{

    private String htmltext;
    public boolean open=true;
    private JButton save = new JButton("Save");
    private HTMLEditor html;

    private  HTMLEditor init(){
        final JFXPanel fxPanel = new JFXPanel();
        this.setLayout(new BorderLayout());
        this.add(save,BorderLayout.NORTH);
        save.addActionListener(this);
        this.add(fxPanel,BorderLayout.CENTER);
        this.setSize(1200, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(null,
                        "Are you sure that you saved?",
                        "Modification save",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(reponse == JOptionPane.YES_OPTION ){
                    open = false;
                    dispose();
                }

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
        return null;
    }

    public void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }
    public String getHtmltext(){return htmltext;}

    public boolean Open(){return this.open; }

    public void actionPerformed(ActionEvent a){
        htmltext = html.getHtmlText();
        System.out.println(htmltext);
    }


}
