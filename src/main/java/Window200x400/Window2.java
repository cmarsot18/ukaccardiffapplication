package Window200x400;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window2 extends JFrame
{
    public Window2(){
        this.setSize(300,600);
        JPanel pan = new JPanel();
        pan.setBackground(Color.getHSBColor(217,217,217));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    public Window2(int type,String DocumentName){
        this.setSize(250,600);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        if (type == 1){
            this.setTitle("Connection to DataBase");
            this.setContentPane(new Connection());
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if(type == 2){
            this.setTitle(DocumentName);
            this.setContentPane(new Document_Display());
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
            //MISS THE CHECK OF SAVE
        }
        if(type == 3){
            this.setContentPane(new Open_Create());
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        this.setVisible(true);
    }
}
