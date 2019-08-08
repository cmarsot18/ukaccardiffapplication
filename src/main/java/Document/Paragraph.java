package Document;
import org.apache.commons.lang3.StringUtils;

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

    public void update(String HTML){
        String RED = "<span style=\"background-color: rgb(255, 0, 0);\">";
        String BLUE = "<span style=\"background-color: rgb(0, 255, 255);\">";
        String PINK = "<span style=\"background-color: rgb(255, 0, 255);\">";
        String GREEN = "<span style=\"background-color: rgb(0, 255, 0);\">";
        String pR;
        String pA;
        String pS;
        String pE;
        ArrayList<String> r_html = ExtractColor(RED,HTML);
        ArrayList<String> a_html = ExtractColor(GREEN,HTML);
        ArrayList<String> s_html = ExtractColor(PINK,HTML);
        ArrayList<String> e_html = ExtractColor(BLUE,HTML);
        ArrayList<String> r_data = this.RGetTopics();
        ArrayList<String> a_data = this.AGetTopics();
        ArrayList<String> s_data = this.SGetTopics();
        ArrayList<String> e_data = this.EGetTopics();
        ArrayList<String> new_r = new ArrayList<>();
        ArrayList<String> new_a = new ArrayList<>();
        ArrayList<String> new_s = new ArrayList<>();
        ArrayList<String> new_e = new ArrayList<>();
        ArrayList<String> old_r = new ArrayList<>();
        ArrayList<String> old_a = new ArrayList<>();
        ArrayList<String> old_s = new ArrayList<>();
        ArrayList<String> old_e = new ArrayList<>();
        Iterator<String> i = r_html.iterator();
        String temp;
        while(i.hasNext()){
            temp = i.next();
            if(r_data == null){
                new_r.add(temp);
            }else{
                if(!r_data.contains(temp)){
                    new_r.add(temp);
                }
            }
        }
        i = a_html.iterator();
        while(i.hasNext()){
            temp = i.next();
            if(a_data == null){
                new_a.add(temp);
            }else{
                if(!a_data.contains(temp)){
                    new_a.add(temp);
                }
            }
        }
        i = s_html.iterator();
        while(i.hasNext()){
            temp = i.next();
            if(s_data == null){
                new_s.add(temp);
            }else{
                if(!s_data.contains(temp)){
                    new_s.add(temp);
                }
            }
        }
        i = e_html.iterator();
        while(i.hasNext()){
            temp = i.next();
            if(e_data == null){
                new_e.add(temp);
            }else{
                if(!e_data.contains(temp)){
                    new_e.add(temp);
                }
            }
        }
        if(!(r_data == null)){
            i = r_data.iterator();
            while(i.hasNext()){
                temp = i.next();
                if(!r_html.contains(temp)){
                    old_r.add(temp);
                }
            }
            i = old_r.iterator();
            while(i.hasNext()){
                temp = i.next();
                this.R = remove(this.R,temp);
            }
        }
        if(!(a_data == null)){
            i = a_data.iterator();
            while(i.hasNext()){
                temp = i.next();
                if(!a_html.contains(temp)){
                    old_a.add(temp);
                }
            }
            i = old_a.iterator();
            while(i.hasNext()){
                temp = i.next();
                this.A = remove(this.A,temp);
            }
        }
        if(!(s_data == null)){
            i = s_data.iterator();
            while(i.hasNext()){
                temp = i.next();
                if(!s_html.contains(temp)){
                    old_s.add(temp);
                }
            }
            i = old_s.iterator();
            while(i.hasNext()){
                temp = i.next();
                this.S = remove(this.S,temp);
            }
        }
        if(!(e_data == null)){
            i = e_data.iterator();
            while(i.hasNext()){
                temp = i.next();
                if(!e_html.contains(temp)){
                    old_e.add(temp);
                }
            }
            i = old_e.iterator();
            while(i.hasNext()){
                temp = i.next();
                this.E = remove(this.E,temp);
            }
        }
        i =  new_r.iterator();
        String stemp;
        if(!new_r.isEmpty()){
            stemp = "("+ i.next()+">>none>>none>>none>>none)";
            pR = stemp;
            while(i.hasNext()){
                stemp ="("+ i.next()+">>none>>none>>none>>none)";
                pR = pR +"<$>"+ stemp;
            }
            pR = pR.replace("&nbsp;","");
            if(R.length() == 1){
                this.setR(pR);
            }else{
                this.setR(this.R+"<$>"+pR);
            }
        }
        i = new_a.iterator();
        if(!new_a.isEmpty()){
            stemp ="("+ i.next()+">>none>>none>>none>>none)";
            pA = stemp;
            while(i.hasNext()){
                stemp ="("+ i.next()+">>none>>none>>none>>none)";
                pA = pA +"<$>"+ stemp;
            }
            pA = pA.replace("&nbsp;","");
            if(A.length() == 1){
                this.setA(pA);
            }else{
                this.setA(this.A+"<$>"+pA);
            }
        }
        i = new_s.iterator();
        if(!new_s.isEmpty()){
            stemp ="("+ i.next()+">>none>>none>>none>>none)";
            pS = stemp;
            while(i.hasNext()){
                stemp ="("+ i.next()+">>none>>none>>none>>none)";
                pS = pS +"<$>"+ stemp;
            }
            pS = pS.replace("&nbsp;","");
            if(S.length() == 1){
                this.setS(pS);
            }else{
                this.setS(this.S+"<$>"+pS);
            }
        }
        i = new_e.iterator();
        if(!new_e.isEmpty()){
            stemp ="("+ i.next()+">>none>>none>>none>>none)";
            pE = stemp;
            while(i.hasNext()){
                stemp ="("+ i.next()+">>none>>none>>none>>none)";
                pE = pE +"<$>"+ stemp;
            }
            pE = pE.replace("&nbsp;","");
            if(E.length() == 1){
                this.setE(pE);
            }else{
                this.setE(this.E+"<$>"+pE);
            }
        }
        this.setText(HTML);
    }

    private ArrayList<String> RGetTopics(){
        ArrayList<String> res = new ArrayList<>();
        if(R.length() == 1){
            return  null;
        }else{
            int elt = StringUtils.countMatches(this.R, "<$>")+1;
            int k;
            int i = R.indexOf(">>");
            int j = R.indexOf(")<$>(");
            res.add(R.substring(1,i));
            for(k=2;k<=elt;k++){
                i = j + 5;
                j= R.indexOf(")<$>(", i );
                res.add(R.substring(i,R.indexOf(">>",i)));
            }
            return res;
        }
    }

    private ArrayList<String> AGetTopics(){
        if(A.length() == 1){
            return null;
        }else{
            ArrayList<String> res = new ArrayList<>();
            int elt = StringUtils.countMatches(this.A, "<$>")+1;
            int k;
            int i = A.indexOf(">>");
            int j = A.indexOf(")<$>(");
            res.add(A.substring(1,i));
            for(k=2;k<=elt;k++){
                i = j + 5;
                j= A.indexOf(")<$>(", i );
                res.add(A.substring(i,A.indexOf(">>",i)));
            }
            return res;
        }
    }

    private ArrayList<String> SGetTopics(){
        ArrayList<String> res = new ArrayList<>();
        if(S.length()==1){
            return null;
        }else{
            int elt = StringUtils.countMatches(this.S, "<$>")+1;
            int k;
            int i = S.indexOf(">>");
            int j = S.indexOf(")<$>(");
            res.add(S.substring(1,i));
            for(k=2;k<=elt;k++){
                i = j + 5;
                j= S.indexOf(")<$>(", i );
                res.add(S.substring(i,S.indexOf(">>",i)));
            }
            return res;
        }
    }
    private ArrayList<String> EGetTopics(){
        ArrayList<String> res = new ArrayList<>();
        if(E.length()==1){
            return null;
        }else{
            int elt = StringUtils.countMatches(this.E, "<$>")+1;
            int k;
            int i = E.indexOf(">>");
            int j = E.indexOf(")<$>(");
            res.add(E.substring(1,i));
            for(k=2;k<=elt;k++){
                i = j + 5;
                j= E.indexOf(")<$>(", i );
                res.add(E.substring(i,E.indexOf(">>",i)));
            }
            return res;

        }
    }

    public static String remove(String RASE, String Topic){
        int i = RASE.indexOf(Topic);
        String temp1 = RASE.substring(0,i-1);
        int j = RASE.indexOf(")<$>",i)+4;
        if (j<=i){
            return temp1.substring(0,temp1.lastIndexOf("<$>"));
        }else{
            String temp2 = RASE.substring(j);
            return temp1+temp2;
        }
    }
}


























