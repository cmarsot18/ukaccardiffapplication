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


public class Text_Editor{

    private String htmltext;

    private  HTMLEditor init(){
        JFrame frame = new JFrame("HTML Editor");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        final HTMLEditor htmlEditor = new HTMLEditor();
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                htmltext = htmlEditor.getHtmlText();
                frame.dispose();
            }
        });

        runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = new Scene(htmlEditor);
                fxPanel.setScene(scene);
            }
        });
        return null;
    }

    public void main(String[] args) {
        Text_Editor temp=new Text_Editor();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                 temp.init();
            }
        });
    }
    public String getHtmltext(){return htmltext;}


}
