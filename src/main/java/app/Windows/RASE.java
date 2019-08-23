package app.Windows;

import app.Document.Paragraph;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import org.apache.commons.lang3.StringUtils;

public class RASE extends JFrame implements ActionListener {
    private JButton previous = new JButton("<= Previous");
    private JButton next = new JButton("Next =>");
    private JButton Select = new JButton("Select");
    private JComboBox<String> choice;
    private JButton submit = new JButton("Submit");
    private JPanel panel = new JPanel();
    private Pan Current_pan;
    private String[] R;
    private String[] A;
    private String[] S;
    private String[] E;
    private String Selected;
    private int ind;
    private String[] RASE_List;
    private Paragraph Data;


    public RASE(Paragraph pPraragraph){
        this.R = DataToUI(pPraragraph.getR());
        this.A = DataToUI(pPraragraph.getA());
        this.S = DataToUI(pPraragraph.getS());
        this.E = DataToUI(pPraragraph.getE());
        this.Data = pPraragraph;
        this.RASE_List = this.choices();
        choice = new JComboBox<>(this.RASE_List);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setTitle("RASE");
        this.Current_pan = null;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Current_pan = new Pan(this.ChoicesToData(this.RASE_List[0]));
        ind = 0;
        choice.addItemListener(new ItemState());
        submit.addActionListener(this);
        previous.addActionListener(this);
        next.addActionListener(this);
        Select.addActionListener(this);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,2));
        panel2.add(previous);
        panel2.add(next);
        panel2.add(choice);
        panel2.add(Select);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,3));
        panel3.add(new JLabel(""));
        panel3.add(submit);
        panel3.add(new JLabel(""));
        panel.setLayout(new BorderLayout());
        panel.add(panel2,BorderLayout.NORTH);
        panel.add(Current_pan,BorderLayout.CENTER);
        panel.add(panel3,BorderLayout.SOUTH);
        this.setContentPane(panel);
        this.setVisible(true);
    }

    private class Pan extends JPanel{
        private JTextField Topic;
        private JTextField Properties;
        private JTextField Comparison;
        private JTextField Value;
        private JTextField Unit;
        private JTextField Word;

        public Pan(String pstring){
            System.out.println(pstring);
            this.setLayout(new GridLayout(6,2));
            String temp;
            int i,j=0;
            i=pstring.indexOf(">>");
            temp = pstring.substring(j,i);
            this.add(new JLabel(" Word in text :",JLabel.CENTER));
            Word = new JTextField(temp);
            Word.setEditable(false);
            this.add(Word);
            i=i+2;
            j=pstring.indexOf(">>",i+1);
            temp = pstring.substring(i,j);
            if(temp.equals("none")){
                Topic = new JTextField();
            }else{
                Topic = new JTextField(temp);
            }
            this.add(new JLabel("Topic :",JLabel.CENTER));
            this.add(Topic);
            i=j+2;
            j=pstring.indexOf(">>",i+1);
            temp = pstring.substring(i,j);
            if(temp.equals("none")){
                Properties = new JTextField();
            }else{
                Properties = new JTextField(temp);
            }
            this.add(new JLabel("Properties :",JLabel.CENTER));
            this.add(Properties);
            i=j+2;
            j=pstring.indexOf(">>",i+1);
            temp = pstring.substring(i,j);
            if(temp.equals("none")){
                Comparison = new JTextField();
            }else{
                Comparison = new JTextField(temp);
            }
            this.add(new JLabel("Comparison :",JLabel.CENTER));
            this.add(Comparison);
            i=j+2;
            j=pstring.indexOf(">>",i+1);
            temp = pstring.substring(i,j);
            if(temp.equals("none")){
                Value = new JTextField();
            }else{
                Value = new JTextField(temp);
            }
            this.add(new JLabel("Value :",JLabel.CENTER));
            this.add(Value);
            i=j+2;
            j=pstring.length();
            temp = pstring.substring(i,j);
            if(temp.equals("none")){
                Unit = new JTextField();
            }else{
                Unit = new JTextField(temp);
            }
            this.add(new JLabel("Unit :",JLabel.CENTER));
            this.add(Unit);
        }

        public String GetWord(){
            String temp = Word.getText();
            return temp;
        }

        public String GetTopic(){
            String temp = Topic.getText();
            if(temp.isEmpty()){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetProperties(){
            String temp = Properties.getText();
            if(temp.isEmpty()){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetComparison(){
            String temp = Comparison.getText();
            if(temp.isEmpty()){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetValue(){
            String temp = Value.getText();
            if(temp.isEmpty()){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetUnit(){
            String temp = Unit.getText();
            if(temp.isEmpty()){
                return "none";
            } else{
                return temp;
            }
        }
    }

    public static String[] DataToUI(String pString){
        if(pString.length() == 1){
            return null;
        }
        int elt = StringUtils.countMatches(pString, "<$>")+1;
        String temp[] = new String[elt];
        int i=0;
        int j;
        int k=0;
        String s;
        if(elt>1){
            while (k < elt) {
                j = pString.indexOf("<$>", i);
                if(k == elt-1){
                    s = pString.substring(i+1,pString.length()-1);
                }else{
                    s = pString.substring(i+1,j-1);
                }
                temp[k] = s;
                k++;
                i = j + 3;
            }
        }else{
            temp[k] = pString.substring(1,pString.length()-1);
        }

        return temp;
    }

    private void UIToData(){
        int i;
        String temp;
        if(!(this.R == null)){
            temp = "(" + R[0];
            for (i = 1; i < R.length; i++) {
                temp = temp + ")<$>(" + R[i];
            }
            temp = temp + ")";
            Data.setR(temp);
        }
        System.out.println(Data.getR());
        if(!(this.A == null)){
            temp = "(" + A[0];
            for (i = 1; i < A.length; i++) {
                temp = temp + ")<$>(" + A[i];
            }
            temp = temp + ")";
            Data.setA(temp);
        }
        System.out.println(Data.getA());
        if(!(this.S == null)){
            temp="("+S[0];
            for(i=1;i<S.length; i++){
                temp = temp + ")<$>(" + S[i];
            }
            temp = temp +")";
            Data.setS(temp);
        }
        System.out.println(Data.getS());
        if(!(this.E == null)){
            temp="("+E[0];
            for(i=1;i<E.length; i++){
                temp = temp + ")<$>(" + E[i];
            }
            temp = temp +")";
            Data.setE(temp);
        }
        System.out.println(Data.getE());
    }

    private String[] choices(){
        int i=0,j=0,k=0,l=0,n=0;
        if(!(this.R == null)){
            n = R.length;
        }
        if(!(this.A == null)){
            j = A.length;
        }
        if(!(this.S == null)){
            k = S.length;
        }
        if(!(this.E == null)){
            l = E.length;
        }
        String temp[] = new String[n+j+k+l];
        if(!(this.R == null)){
            for( i = 0 ; i < R.length; i++){
                temp[i]="R"+i+"-"+R[i].substring(0,R[i].indexOf(">>"));
            }
        }
        if(!(this.A == null)){
            for( i = 0 ; i < A.length; i++){
                temp[n+i]="A"+i+"-"+A[i].substring(0,A[i].indexOf(">>"));
            }
        }
        if(!(this.S == null)){
            for( i = 0 ; i < S.length; i++){
                temp[i+n+j]="S"+i+"-"+S[i].substring(0,S[i].indexOf(">>"));
            }
        }
        if(!(this.E == null)){
            for( i = 0 ; i < E.length; i++){
                temp[i+n+j+k]="E"+i+"-"+E[i].substring(0,E[i].indexOf(">>"));
            }
        }
        return temp;
    }

    private String ChoicesToData(String pString){
        String c1 = pString.substring(0,1);
        String c2 = pString.substring(1,2);
        if(c1.equals("R")){
            return this.R[Integer.valueOf(c2)];
        }if(c1.equals("A")){
            return this.A[Integer.valueOf(c2)];
        }if(c1.equals("S")){
            return this.S[Integer.valueOf(c2)];
        }if(c1.equals("E")){
            return this.E[Integer.valueOf(c2)];
        }else{
            return null;
        }
    }

    private class ItemState implements ItemListener {
        public void itemStateChanged(ItemEvent e){
            Selected = choice.getSelectedItem().toString();
        }
    }

    public void actionPerformed(ActionEvent a){
        int max = this.RASE_List.length;
        if (a.getSource() == submit){
            if(Current_pan != null){
                if(Current_pan.GetTopic().equals("none")){
                    JOptionPane jop3 = new JOptionPane();
                    jop3.showMessageDialog(null, "Topic field can`t be empty", "No Topic", JOptionPane.ERROR_MESSAGE);
                }else {
                    String c1 = RASE_List[ind].substring(0, 1);
                    String c2 = RASE_List[ind].substring(1, 2);
                    if (c1.equals("R")) {
                        this.R[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("A")) {
                        this.A[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("S")) {
                        this.S[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("E")) {
                        this.E[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    this.UIToData();
                    System.out.println(Data.getR());
                    this.dispose();
                }
            }
        }

        if (a.getSource() == previous) {
            if (Current_pan == null) {
                Current_pan = new Pan(this.ChoicesToData(this.RASE_List[0]));
                ind = 0;
                panel.add(Current_pan,BorderLayout.CENTER);
                panel.updateUI();
            } else {
                if (Current_pan.GetTopic().equals("none")) {
                    JOptionPane jop3 = new JOptionPane();
                    jop3.showMessageDialog(null, "Topic field can`t be empty", "No Topic", JOptionPane.ERROR_MESSAGE);
                } else {
                    String c1 = RASE_List[ind].substring(0, 1);
                    String c2 = RASE_List[ind].substring(1, 2);
                    if (c1.equals("R")) {
                        this.R[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("A")) {
                        this.A[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("S")) {
                        this.S[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("E")) {
                        this.E[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                        if (ind == 0) {
                            ind = max - 1;
                        } else {
                            ind--;
                        }
                        panel.remove(Current_pan);
                        Current_pan = new Pan(this.ChoicesToData(this.RASE_List[ind]));
                        panel.add(Current_pan,BorderLayout.CENTER);
                        panel.updateUI();
                    }
                }

            }

        if (a.getSource() == next){
            if (Current_pan == null){
                Current_pan = new Pan(this.ChoicesToData(this.RASE_List[0]));
                ind = 0;
                panel.add(Current_pan);
                panel.updateUI();
            }else {
                if(Current_pan.GetTopic().equals("none")){
                    JOptionPane jop3 = new JOptionPane();
                    jop3.showMessageDialog(null, "Topic field can`t be empty", "No Topic", JOptionPane.ERROR_MESSAGE);
                }else{
                    String c1 = RASE_List[ind].substring(0,1);
                    String c2 = RASE_List[ind].substring(1,2);
                    if(c1.equals("R")){
                        this.R[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic()+">>"+Current_pan.GetProperties()+">>"+Current_pan.GetComparison()+">>"+Current_pan.GetValue()+">>"+Current_pan.GetUnit();
                    }if(c1.equals("A")){
                        this.A[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic()+">>"+Current_pan.GetProperties()+">>"+Current_pan.GetComparison()+">>"+Current_pan.GetValue()+">>"+Current_pan.GetUnit();
                    }if(c1.equals("S")){
                        this.S[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic()+">>"+Current_pan.GetProperties()+">>"+Current_pan.GetComparison()+">>"+Current_pan.GetValue()+">>"+Current_pan.GetUnit();
                    }if(c1.equals("E")) {
                        this.E[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic()+">>"+Current_pan.GetProperties()+">>"+Current_pan.GetComparison()+">>"+Current_pan.GetValue()+">>"+Current_pan.GetUnit();
                    }
                    if(ind == max-1){
                        ind = 0;
                    }else {
                        ind++;
                    }
                    panel.remove(Current_pan);
                    Current_pan = new Pan(this.ChoicesToData(this.RASE_List[ind]));
                    panel.add(Current_pan,BorderLayout.CENTER);
                    panel.updateUI();
                }
            }
        }
        if (a.getSource() == Select){
            if(Current_pan.GetTopic().equals("none")){
                JOptionPane jop3 = new JOptionPane();
                jop3.showMessageDialog(null, "Topic field can`t be empty", "No Topic", JOptionPane.ERROR_MESSAGE);
            }else {
                if (Current_pan != null) {
                    String c1 = RASE_List[ind].substring(0, 1);
                    String c2 = RASE_List[ind].substring(1, 2);
                    if (c1.equals("R")) {
                        this.R[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("A")) {
                        this.A[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("S")) {
                        this.S[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    if (c1.equals("E")) {
                        this.E[Integer.valueOf(c2)] = Current_pan.GetWord() + ">>" + Current_pan.GetTopic() + ">>" + Current_pan.GetProperties() + ">>" + Current_pan.GetComparison() + ">>" + Current_pan.GetValue() + ">>" + Current_pan.GetUnit();
                    }
                    panel.remove(Current_pan);
                }
                System.out.println(Selected);
                String temp = this.ChoicesToData(Selected);
                String c1 = Selected.substring(0, 1);
                String c2 = Selected.substring(1, 2);
                if (c1.equals("R")) {
                    ind = Integer.valueOf(c2);
                }
                if (c1.equals("A")) {
                    ind = R.length + Integer.valueOf(c2);
                }
                if (c1.equals("S")) {
                    ind = R.length + A.length + Integer.valueOf(c2);
                }
                if (c1.equals("E")) {
                    ind = R.length + A.length + S.length + Integer.valueOf(c2);
                }
                Current_pan = new Pan(this.ChoicesToData(this.RASE_List[ind]));
                panel.add(Current_pan, BorderLayout.CENTER);
                panel.updateUI();
            }
        }
    }
}
