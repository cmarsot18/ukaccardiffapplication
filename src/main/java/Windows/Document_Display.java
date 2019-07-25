package Windows;

import Document.Paragraph;
import Document.Section;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Document_Display extends JFrame {
    public Document_Display(Section root){
        this.setSize(300,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(root.Getname());
        JTree Document = new JTree(displaydocument(root));
        //Document.setRootVisible(false);
        this.getContentPane().add(new JScrollPane(Document));
        this.setVisible(true);

    }

    private static DefaultMutableTreeNode displaydocument(Section pSection){
        if (pSection instanceof Paragraph){
            DefaultMutableTreeNode temp = new DefaultMutableTreeNode(pSection.GetSubsectionNumber()+"."+pSection.Getname());
            return temp;
        }else{
            DefaultMutableTreeNode temp = new DefaultMutableTreeNode(pSection.GetSubsectionNumber()+"."+pSection.Getname());
            int i;
            for(i=0;i<pSection.GetSuccessors().size();i++){
                Section stemp = pSection.GetSuccessors().get(i);
                temp.add(displaydocument(stemp));
            }
            return temp;
        }
    }
}
