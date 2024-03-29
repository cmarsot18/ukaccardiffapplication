package app.Windows;

import app.DB.DB;
import app.Document.Paragraph;
import app.Document.Section;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Iterator;


public class Document_Display extends JFrame implements ActionListener {


    private JButton Commit = new JButton("Commit");
    private JButton New_Section = new JButton("New Section");
    private JButton New_Paragraph = new JButton("New paragraph");
    private  JButton Editing = new JButton("Edit");
    private JButton Delete = new JButton("Delete");
    private JButton Move = new JButton("Move ");
    private  JTree Document;
    public Section root;
    private String selected;
    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private DB Current_Server;
    private boolean name_changed;
    private String opening_name;
    private boolean saved;
    private String Log;
    private String Pass;


    public Document_Display(Section root, DB pDB,String Login,String Password){
        this.Log = Login;
        this.Pass = Password;
        this.name_changed = false;
        this.saved = false;
        this.opening_name = root.Getname();
        this.Current_Server = pDB;
        this.setSize(650,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setTitle(root.Getname());
        this.root = root;
        Document = new JTree(displaydocument(root));
        this.expandAllNodes(Document);
        pan.setLayout(new BorderLayout());
        pan2.setLayout(new GridLayout(1,6));
        pan2.add(Commit);
        pan2.add(New_Section);
        pan2.add(New_Paragraph);
        pan2.add(Editing);
        pan2.add(Move);
        pan2.add(Delete);
        Editing.addActionListener(this);
        Commit.addActionListener(this);
        New_Paragraph.addActionListener(this);
        New_Section.addActionListener(this);
        Move.addActionListener(this);
        Delete.addActionListener(this);
        pan.add(pan2,BorderLayout.NORTH);
        pan.add(Document,BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                if(saved){
                    dispose();
                    new Open_Create(Current_Server,Log,Pass);
                }else{
                    int answer = JOptionPane.showConfirmDialog(null,
                            "Document not committed, do you want to quit whatever?",
                            "Not saved",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(answer == JOptionPane.YES_OPTION ){
                        dispose();
                        new Open_Create(Current_Server,Log,Pass);
                    }
                }
            }
        });
        this.TreeListener();
        this.setContentPane(pan);
        this.setVisible(true);

    }
    //Create the displayed tree in the JFrame from the root
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
    //Allow to interact with the tree
    private void TreeListener(){
        Document.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                if(Document.getLastSelectedPathComponent() != null){
                    selected = getAbsolutePath(e.getPath());
                }
            }
            //write the path by a specific way that allow to use like : SectionName1<%>SectionName2<%>ParagraphName
            private String getAbsolutePath(TreePath treePath){
                String str = "";
                for(Object name : treePath.getPath()){
                    if(name.toString() != null)
                        str += name.toString()+"<%>";
                }
                return str;
            }
        });
    }
    //allow to have the whole tree expand when it opens
    private void expandAllNodes(JTree tree) {
        int j = tree.getRowCount();
        int i = 0;
        while(i < j) {
            tree.expandRow(i);
            i += 1;
            j = tree.getRowCount();
        }
    }
    //Refresh the display after any modification
    private void refresh(){
        pan.removeAll();
        pan.updateUI();
        Document = new JTree(displaydocument(root));
        this.expandAllNodes(Document);
        this.TreeListener();
        pan2.add(Commit);
        pan2.add(New_Section);
        pan2.add(New_Paragraph);
        pan2.add(Editing);
        pan2.add(Delete);
        pan.add(pan2,BorderLayout.NORTH);
        pan.add(Document,BorderLayout.CENTER);
        pan.updateUI();
    }

    public void actionPerformed(ActionEvent a){
        if(a.getSource() == Commit){
            if(name_changed){
                Current_Server.getDatabase().drop(opening_name.replace(" ",""));
            }else{
                Current_Server.getDatabase().drop(this.root.Getname().replace(" ",""));
            }
            Current_Server.SaveNewDocument(root,"admin","admin");
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Successfully saved", "Save", JOptionPane.INFORMATION_MESSAGE);
            this.saved = true;
        }

        if((a.getSource() == New_Section)||(a.getSource() == New_Paragraph)){
            if(root.GetSectionFromRoot(selected)instanceof Paragraph ){
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, "You can`t add a successor to a Paragraph", "Add failure", JOptionPane.ERROR_MESSAGE);
            }else{
                if(a.getSource() == New_Section){
                    Section New = new Section();
                    JOptionPane jop = new JOptionPane();
                    String name = jop.showInputDialog(null, "Enter the name of the section", "New Section", JOptionPane.QUESTION_MESSAGE);
                    if(name != null) {
                        New.SetName(name);
                        Section Predecessor = root.GetSectionFromRoot(selected);
                        Predecessor.AddSuccessor(New);
                        New.SetPredecessor(Predecessor);
                    }
                    this.refresh();
                }
                else{
                        Paragraph New = new Paragraph();
                        JOptionPane jop = new JOptionPane();
                        String name = jop.showInputDialog(null, "Enter the name of the Paragraph", "New Paragraph", JOptionPane.QUESTION_MESSAGE);
                        if( name != null) {
                            New.SetName(name);
                            Section Predecessor = root.GetSectionFromRoot(selected);
                            Predecessor.AddSuccessor(New);
                            New.SetPredecessor(Predecessor);

                        }
                        this.refresh();
                        this.saved=false;
                }
            }
        }

        if(a.getSource() == Editing){
            Section Selected = root.GetSectionFromRoot(selected);
            if(Selected instanceof Paragraph){
                Paragraph temp = (Paragraph) Selected;
                Text_Editor editor = new Text_Editor(temp,((Paragraph) Selected).getText(),Selected.Getname());
                editor.setVisible(true);
            }else{
                JOptionPane jop = new JOptionPane();
                String name = jop.showInputDialog(null, "Enter the new name of the section", "Rename", JOptionPane.QUESTION_MESSAGE);
                if(name != null) {
                    if (Selected == root) {
                        name_changed = true;
                        Selected.SetName(name);
                    } else {
                        Selected.SetName(name);
                    }
                }
            }
            this.refresh();
            this.saved=false;
        }

        if(a.getSource() == Move){
            Section temp = root.GetSectionFromRoot(selected);
            int NumberOfSuccessors = temp.GetPredecessor().GetSuccessors().size();
            double number = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new subsection number"));
            int num = (int) number;
            System.out.println(num);
            System.out.println(NumberOfSuccessors);
            if(num > NumberOfSuccessors){
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, "Enter a lower number ", "Wrong number", JOptionPane.ERROR_MESSAGE);
            }else{
                temp.GetPredecessor().MoveSuccessor(temp.GetSubsectionNumber(),num);
                this.refresh();
            }
        }

        if(a.getSource() == Delete){
            Section Selected = root.GetSectionFromRoot(selected);
            if(Selected == root){
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, "Can`t suppress the root", "Delete failure", JOptionPane.ERROR_MESSAGE);

            }else{
                Selected.DeleteSection();
                this.refresh();
            }
            this.saved=false;
        }
    }
}
