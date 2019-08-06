package Document;
import java.util.*;


public class Paragraph extends Section
{
    String R;
    String A;
    String S;
    String E;
    String text;

    public Paragraph() {
        super();
        R = "r";
        A = "a";
        S = "s";
        E = "e";
        this.text = "text";
    }
    public Paragraph(String pName, Section pPredecessor, ArrayList pSuccessors, String r, String a, String s, String e, String text) {
        super(pName, pPredecessor,pSuccessors);
        R = r;
        A = a;
        S = s;
        E = e;
        this.text = text;
    }
    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ArrayList<String> ExtractColor(String color,String HTML){
        ArrayList<String> temp = new ArrayList<String>();
        int max = HTML.length();
        int j = HTML.indexOf(color);
        int i = HTML.indexOf("</span>",j);
        while ((0 < j)&& (i < max)) {
            String stemp = HTML.substring(j + color.length(), i);
            i = i + color.length();
            temp.add(stemp);
            j = HTML.indexOf(color, i);
            i = HTML.indexOf("</span>", j);
        }
        return temp;
    }

    public void save(String HTML){
        String RED = "<span style=\"background-color: rgb(255, 0, 0);\">";
        String BLUE = "<span style=\"background-color: rgb(0, 255, 255);\">";
        String PINK = "<span style=\"background-color: rgb(255, 0, 255);\">";
        String GREEN = "<span style=\"background-color: rgb(0, 255, 0);\">";
        String R = new String();
        String A = new String();
        String S = new String();
        String E = new String();
        ArrayList<String> r_elt = new ArrayList<String>();
        ArrayList<String> a_elt = new ArrayList<String>();
        ArrayList<String> s_elt = new ArrayList<String>();
        ArrayList<String> e_elt = new ArrayList<String>();
        r_elt = ExtractColor(RED,HTML);
        a_elt = ExtractColor(GREEN,HTML);
        s_elt = ExtractColor(PINK,HTML);
        e_elt = ExtractColor(BLUE,HTML);
        Iterator<String> temp = r_elt.iterator();
        String stemp = "("+ temp.next()+">>none>>none>>none>>none)";
        R = stemp;
        while(temp.hasNext()){
            stemp ="("+ temp.next()+">>none>>none>>none>>none)";
            R = R +"<$>"+ stemp;
        }
        temp = a_elt.iterator();
        stemp ="("+ temp.next()+">>none>>none>>none>>none)";
        A = stemp;
        while(temp.hasNext()){
            stemp ="("+ temp.next()+">>none>>none>>none>>none)";
            A = A +"<$>"+ stemp;
        }
        temp = s_elt.iterator();
        stemp ="("+ temp.next()+">>none>>none>>none>>none)";
        S = stemp;
        while(temp.hasNext()){
            stemp ="("+ temp.next()+">>none>>none>>none>>none)";
            S = S +"<$>"+ stemp;
        }
        temp = e_elt.iterator();
        stemp ="("+ temp.next()+">>none>>none>>none>>none)";
        E = stemp;
        while(temp.hasNext()){
            stemp ="("+ temp.next()+">>none>>none>>none>>none)";
            E = E +"<$>"+ stemp;
        }
        this.setR(R.replace("&nbsp;",""));
        this.setA(A.replace("&nbsp;",""));
        this.setS(S.replace("&nbsp;",""));
        this.setE(E.replace("&nbsp;",""));
        this.setText(HTML);
    }
}


























