package Windows;

import DB.DB;
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
    private  JButton Editing = new JButton("Edit Selection");
    private  JTree Document;
    public Section root;
    private String selected;
    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private DB Current_Server;

    public Document_Display(Section root,DB pDB){
        this.Current_Server = pDB;
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(root.Getname());
        this.root = root;
        Document = new JTree(displaydocument(root));
        pan.setLayout(new BorderLayout());
        pan2.setLayout(new GridLayout(1,4));
        pan2.add(Commit);
        pan2.add(New_Section);
        pan2.add(New_Paragraph);
        pan2.add(Editing);
        Editing.addActionListener(this);
        Commit.addActionListener(this);
        New_Paragraph.addActionListener(this);
        New_Section.addActionListener(this);
        pan.add(pan2,BorderLayout.NORTH);
        pan.add(Document,BorderLayout.CENTER);
        Document.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                if(Document.getLastSelectedPathComponent() != null){
                    selected = getAbsolutePath(e.getPath());
                    System.out.println(selected);
                }
            }
            private String getAbsolutePath(TreePath treePath){
                String str = "";
                for(Object name : treePath.getPath()){
                    if(name.toString() != null)
                        str += name.toString()+"<%>";
                }
                return str;
            }
        });
        this.setContentPane(pan);
        this.setVisible(true);

    }

    private static DefaultMutableTreeNode displaydocument(Section pSection){
        if (pSection.GetSuccessors().isEmpty()){
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

    private void expandAllNodes(JTree tree) {
        int j = tree.getRowCount();
        int i = 0;
        while(i < j) {
            tree.expandRow(i);
            i += 1;
            j = tree.getRowCount();
        }
    }

    public void actionPerformed(ActionEvent a){
        if(a.getSource() == Commit){
            if(Current_Server.getDatabase().exists(this.root.Getname())){
                Current_Server.getDatabase().drop(this.root.Getname());
            }
            Current_Server.SaveNewDocument(root,"admin","admin");
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Successfully saved", "Save", JOptionPane.INFORMATION_MESSAGE);
        }
        if(a.getSource() == New_Section){
            JOptionPane jop = new JOptionPane();
            String name = jop.showInputDialog(null, "Enter the name of the section", "New Section", JOptionPane.QUESTION_MESSAGE);
            Section New = new Section();
            New.SetName(name);
            Section Predecessor = root.GetSectionFromRoot(selected);
            Predecessor.AddSuccessor(New);
            pan.removeAll();
            pan.updateUI();
            Document = new JTree(displaydocument(root));
            this.expandAllNodes(Document);
            Document.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    if(Document.getLastSelectedPathComponent() != null){
                        selected = getAbsolutePath(e.getPath());
                        System.out.println(selected);
                    }
                }
                private String getAbsolutePath(TreePath treePath){
                    String str = "";
                    for(Object name : treePath.getPath()){
                        if(name.toString() != null)
                            str += name.toString()+"<%>";
                    }
                    return str;
                }
            });
            pan2.add(Commit);
            pan2.add(New_Section);
            pan2.add(New_Paragraph);
            pan2.add(Editing);
            pan.add(pan2,BorderLayout.NORTH);
            pan.add(Document,BorderLayout.CENTER);
            pan.updateUI();


        }
        if(a.getSource() == New_Paragraph) {
            JOptionPane jop = new JOptionPane();
            String name = jop.showInputDialog(null, "Enter the name of the paragraph", "New Paragraph", JOptionPane.QUESTION_MESSAGE);
            Paragraph New = new Paragraph();
            New.SetName(name);
            Section Predecessor = root.GetSectionFromRoot(selected);
            Predecessor.AddSuccessor(New);
            pan.removeAll();
            pan.updateUI();
            Document = new JTree(displaydocument(root));
            this.expandAllNodes(Document);
            Document.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    if(Document.getLastSelectedPathComponent() != null){
                        selected = getAbsolutePath(e.getPath());
                        System.out.println(selected);
                    }
                }
                private String getAbsolutePath(TreePath treePath){
                    String str = "";
                    for(Object name : treePath.getPath()){
                        if(name.toString() != null)
                            str += name.toString()+"<%>";
                    }
                    return str;
                }
            });
            pan2.add(Commit);
            pan2.add(New_Section);
            pan2.add(New_Paragraph);
            pan2.add(Editing);
            pan.add(pan2, BorderLayout.NORTH);
            pan.add(Document, BorderLayout.CENTER);
            pan.updateUI();
        }
        if(a.getSource() == Editing){
            Section Selected = root.GetSectionFromRoot(selected);
            if(Selected instanceof Paragraph){

            }else{
                JOptionPane jop = new JOptionPane();
                String name = jop.showInputDialog(null, "Enter the new name of the section", "Rename", JOptionPane.QUESTION_MESSAGE);
                Selected.SetName(name);
            }

        }
    }
}
