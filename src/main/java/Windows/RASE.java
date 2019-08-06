package Windows;

import Document.Paragraph;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class RASE extends JFrame implements ActionListener {
    private JButton previous = new JButton("<= Previous");
    private JButton next = new JButton("Next =>");
    private JButton Select = new JButton("Select");
    private JComboBox<String> choice = new JComboBox<>();
    private JButton submit = new JButton("Submit");
    private JPanel panel = new JPanel();
    private Pan Current_pan;
    private String[] R;
    private String[] A;
    private String[] S;
    private String[] E;
    private String Selected;
    private int ind = 0;
    private String[] RASE_List;


    public RASE(Paragraph pPraragraph){
        this.R = this.DataToUI(pPraragraph.getR());
        this.A = this.DataToUI(pPraragraph.getA());
        this.S = this.DataToUI(pPraragraph.getS());
        this.E = this.DataToUI(pPraragraph.getE());
        System.out.println(R[0]);
        System.out.println(A[0]);
        System.out.println(S[0]);
        System.out.println(E[0]+" / "+E[1]);
        this.RASE_List = this.choices();
        choice = new JComboBox<>(this.RASE_List);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setTitle("RASE");
        this.Current_pan = null;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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

        public Pan(String pstring){
            this.setLayout(new GridLayout(5,2));
            int i,j;
            i=pstring.indexOf(">>");
            init(Topic,"Topic :",pstring,0,i);
            i=i+2;
            j=pstring.indexOf(">>",i+1);
            init(Properties,"Properties :",pstring,i,j);
            i=j+2;
            j=pstring.indexOf(">>",i+1);
            init(Comparison,"Comparison :",pstring,i,j);
            i=j+2;
            j=pstring.indexOf(">>",i+1);
            init(Value,"Value :",pstring,i,j);
            i=j+2;
            j=pstring.length();
            init(Unit,"Unit :",pstring,i,j);
        }
        private void init(JTextField t,String Title,String s,int i,int j){
            String temp = s.substring(i,j);
            if(temp.equals("none")){
                t = new JTextField();
            }else{
                t = new JTextField(temp);
            }
            this.add(new JLabel(Title,JLabel.CENTER));
            this.add(t);
        }
        public String GetTopic(){
            String temp = Topic.getText();
            System.out.println(temp);
            if(temp == null){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetProperties(){
            String temp = Properties.getText();
            if(temp == null){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetComparison(){
            String temp = Comparison.getText();
            if(temp == null){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetValue(){
            String temp = Value.getText();
            if(temp == null){
                return "none";
            } else{
                return temp;
            }
        }
        public String GetUnit(){
            String temp = Unit.getText();
            if(temp == null){
                return "none";
            } else{
                return temp;
            }
        }
    }

    private String[] DataToUI(String pString){
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

    private String UIToDate(String pString){
        return null;
    }

    private String[] choices(){
        String temp[] = new String[R.length+A.length+S.length+E.length];
        for(int i = 0 ; i < R.length; i++){
            temp[i]="R"+i+"-"+R[i].substring(0,R[i].indexOf(">>"));
        }for(int i = 0 ; i < A.length; i++){
            temp[R.length+i]="A"+i+"-"+A[i].substring(0,A[i].indexOf(">>"));
        }for(int i = 0 ; i < S.length; i++){
            temp[i+R.length+A.length]="S"+i+"-"+S[i].substring(0,S[i].indexOf(">>"));
        }for(int i = 0 ; i < E.length; i++){
            temp[i+R.length+A.length+S.length]="E"+i+"-"+E[i].substring(0,E[i].indexOf(">>"));
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
        String Rase = this.ChoicesToData(this.RASE_List[ind]);
        System.out.println(this.ChoicesToData(this.RASE_List[ind]));
        if (a.getSource() == submit){

        }
        if (a.getSource() == previous){
            if (Current_pan == null){
                System.out.println("test");
                Current_pan = new Pan(this.ChoicesToData(this.RASE_List[0]));
                panel.add(Current_pan);
                panel.updateUI();
            }else {
                System.out.println(Current_pan.GetTopic());
                if(Current_pan.GetTopic().equals("none")){
                    JOptionPane jop3 = new JOptionPane();
                    jop3.showMessageDialog(null, "Can`t access to the server", "Connection Failure", JOptionPane.ERROR_MESSAGE);
                }else{
                    Rase = Current_pan.GetTopic()+">>"+Current_pan.GetProperties()+">>"+Current_pan.GetComparison()+">>"+Current_pan.GetValue()+">>"+Current_pan.GetUnit();
                    if(ind == 0){
                        ind = max;
                    }else {
                        ind--;
                    }
                    panel.remove(Current_pan);
                    panel.add(new Pan(this.ChoicesToData(this.RASE_List[ind])));
                    panel.updateUI();
                }
            }

        }
        if (a.getSource() == next){
            if (Current_pan == null){
                System.out.println("test");
                Current_pan = new Pan(this.ChoicesToData(this.RASE_List[0]));
                panel.add(Current_pan);
                panel.updateUI();
            }else {
                System.out.println(Current_pan.GetTopic());
                if(Current_pan.GetTopic().equals("none")){
                    JOptionPane jop3 = new JOptionPane();
                    jop3.showMessageDialog(null, "Can`t access to the server", "Connection Failure", JOptionPane.ERROR_MESSAGE);
                }else{
                    Rase = Current_pan.GetTopic()+">>"+Current_pan.GetProperties()+">>"+Current_pan.GetComparison()+">>"+Current_pan.GetValue()+">>"+Current_pan.GetUnit();
                    if(ind == max){
                        ind = 0;
                    }else {
                        ind++;
                    }
                    panel.remove(Current_pan);
                    panel.add(new Pan(this.ChoicesToData(this.RASE_List[ind])));
                    panel.updateUI();
                }
            }
        }
        if (a.getSource() == Select){

        }

    }


}
