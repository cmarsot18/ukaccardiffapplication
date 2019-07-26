package Windows;

import Document.Paragraph;
import Document.Section;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Document_Display extends JFrame implements ActionListener {

    private JButton Commit = new JButton("Commit");
    private JButton New_Section = new JButton("New Section");
    private JButton New_Paragraph = new JButton("New paragraph");


    public Document_Display(Section root){
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(root.Getname());
        final JTree Document = new JTree(displaydocument(root));
        JPanel pan = new JPanel();
        JPanel pan2 = new JPanel();
        pan.setLayout(new BorderLayout());
        pan2.setLayout(new GridLayout(1,3));
        pan2.add(Commit);
        pan2.add(New_Section);
        pan2.add(New_Paragraph);
        pan.add(pan2,BorderLayout.NORTH);
        pan.add(Document,BorderLayout.CENTER);
        Document.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                if(Document.getLastSelectedPathComponent() != null){
                    System.out.println(getAbsolutePath(e.getPath()));
                }
            }
            private String getAbsolutePath(TreePath treePath){
                String str = "";
                for(Object name : treePath.getPath()){
                    if(name.toString() != null)
                        str += name.toString()+" > ";
                }
                return str;
            }
        });
        //Document.setRootVisible(false);
        this.setContentPane(pan);
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

    public void actionPerformed(ActionEvent a){
        if(a.getSource() == Commit){

        }
        if(a.getSource() == New_Section){

        }
        if(a.getSource() == New_Paragraph){

        }
    }
}
