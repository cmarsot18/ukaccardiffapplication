package Windows;

import javax.swing.*;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.web.HTMLEditor;

import static javafx.application.Platform.runLater;


public class Text_Editor{

    public HTMLEditor htmlEditor;

    private static void init(){
        JFrame frame = new JFrame("HTML Editor");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        runLater(new Runnable() {
            @Override
            public void run() {
                final HTMLEditor htmlEditor = new HTMLEditor();
                Scene scene = new Scene(htmlEditor);
                fxPanel.setScene(scene);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    public HTMLEditor getHtmlEditor() {
        return htmlEditor;
    }
    public void setHtmlEditor(HTMLEditor pHTMLEditor){
        this.htmlEditor = pHTMLEditor;
    }
}
