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
import java.awt.event.*;
import Windows.Text_Editor;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;


public class Document_Display extends JFrame implements ActionListener {


    private JButton Commit = new JButton("Commit");
    private JButton New_Section = new JButton("New Section");
    private JButton New_Paragraph = new JButton("New paragraph");
    private  JButton Editing = new JButton("Edit");
    private JButton Delete = new JButton("Delete");
    private  JTree Document;
    public Section root;
    private String selected;
    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private DB Current_Server;
    private boolean name_changed;
    private String opening_name;
    private boolean saved;
    private boolean TextEditorOpen;


    public Document_Display(Section root, final DB pDB){
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
        pan2.setLayout(new GridLayout(1,4));
        pan2.add(Commit);
        pan2.add(New_Section);
        pan2.add(New_Paragraph);
        pan2.add(Editing);
        pan2.add(Delete);
        Editing.addActionListener(this);
        Commit.addActionListener(this);
        New_Paragraph.addActionListener(this);
        New_Section.addActionListener(this);
        Delete.addActionListener(this);
        pan.add(pan2,BorderLayout.NORTH);
        pan.add(Document,BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                if(saved){
                    dispose();
                }else{
                    int reponse = JOptionPane.showConfirmDialog(null,
                            "Document not committed, do you want to quit whatever?",
                            "Not saved",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(reponse == JOptionPane.YES_OPTION ){
                        dispose();
                        new Open_Create(Current_Server);
                    }
                }
            }
        });
        this.TreeListener();
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

    private void TreeListener(){
        Document.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                if(Document.getLastSelectedPathComponent() != null){
                    selected = getAbsolutePath(e.getPath());
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
                Current_Server.getDatabase().drop(opening_name);
            }else{
                Current_Server.getDatabase().drop(this.root.Getname());
            }
            Current_Server.SaveNewDocument(root,"admin","admin");
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Successfully saved", "Save", JOptionPane.INFORMATION_MESSAGE);
            this.saved = true;
        }

        if((a.getSource() == New_Section)||(a.getSource() == New_Paragraph)){
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
                String name = jop.showInputDialog(null, "Enter the name of the section", "New Section", JOptionPane.QUESTION_MESSAGE);
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

        if(a.getSource() == Editing){
            Section Selected = root.GetSectionFromRoot(selected);
            if(Selected instanceof Paragraph){
                if(TextEditorOpen){
                    JOptionPane jop3 = new JOptionPane();
                    jop3.showMessageDialog(null, "An editor is already open, close it then try again", "Editor open", JOptionPane.ERROR_MESSAGE);
                }else {
                    this.TextEditorOpen = true;
                    Text_Editor test = new Text_Editor();
                    String[] args = new String[]{"123"};
                    test.main(args);

                    while (test.Open()) {
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                    }
                    String temp = test.getHtmltext();
                    System.out.println(temp);
                    this.TextEditorOpen = false;
                }

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
