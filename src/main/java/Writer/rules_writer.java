package Writer;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.ODirection;
import com.orientechnologies.orient.core.record.OVertex;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class rules_writer {
   public static void main(String[] args) {
        File file = new File("test.txt");
        try{
            System.out.println("test    ");
            FileWriter writer = new FileWriter(file);
            StringBuffer buf = new StringBuffer();












            writer.write(buf.toString());
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

   }
   public String Write_Paragraph (OVertex pParagraph){
       StringBuffer res = new StringBuffer();
       StringBuffer Rlist = new StringBuffer();
       StringBuffer Alist = new StringBuffer();
       StringBuffer Slist = new StringBuffer();
       StringBuffer Elist = new StringBuffer();
       Iterable<OVertex> Successors=pParagraph.getVertices(ODirection.OUT);
       Iterator<OVertex> I = Successors.iterator();
       String name = pParagraph.getProperty("name").toString();
       OVertex vtemp,R,A,S,E;
       R = null;
       A = null;
       S = null;
       E = null;
       String vclass,temp;
       while (I.hasNext()){
           vtemp = I.next();
           vclass = vtemp.getProperty("@class").toString();
           if(vclass.equals("R_RASE")){
               R = vtemp;
           }
           if(vclass.equals("A_RASE")){
               A = vtemp;
           }
           if(vclass.equals("S_RASE")){
               S = vtemp;
           }
           if(vclass.equals("E_RASE")){
               E = vtemp;
           }
       }
       if(R != null){
           Successors = R.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Rlist.append("(");
           while(I.hasNext()){
               vtemp = I.next();
               temp = "("+vtemp.getProperty("Topic").toString()+".";
               if(vtemp.getProperty("Properties").toString().equals("none")){
                   temp = temp+" "+vtemp.getProperty("Properties").toString();
               }
               if(vtemp.getProperty("Comparison").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Comparison").toString();
               }
               if(vtemp.getProperty("Value").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Value").toString();
               }
               temp = temp +")";
               Rlist.append(" and ");
               Rlist.append(temp);
           }
           Rlist.append(")");
       }
       if(A != null){
           Successors = A.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Alist.append("(");
           vtemp = I.next();
           temp = "("+vtemp.getProperty("Topic").toString()+".";
           if(vtemp.getProperty("Properties").toString().equals("none")){
               temp = temp+" "+vtemp.getProperty("Properties").toString();
           }
           if(vtemp.getProperty("Comparison").toString().equals("none")){
               temp = temp + " " + vtemp.getProperty("Comparison").toString();
           }
           if(vtemp.getProperty("Value").toString().equals("none")){
               temp = temp + " " + vtemp.getProperty("Value").toString();
           }
           temp = temp +")";
           Alist.append(temp);
           while(I.hasNext()){
               vtemp = I.next();
               temp = "("+vtemp.getProperty("Topic").toString()+".";
               if(vtemp.getProperty("Properties").toString().equals("none")){
                   temp = temp+" "+vtemp.getProperty("Properties").toString();
               }
               if(vtemp.getProperty("Comparison").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Comparison").toString();
               }
               if(vtemp.getProperty("Value").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Value").toString();
               }
               temp = temp +")";
               Alist.append(" and ");
               Alist.append(temp);
           }
           Alist.append(")");
       }
       if(S != null){
           Successors = S.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Slist.append("(");
           vtemp = I.next();
           temp = "("+vtemp.getProperty("Topic").toString()+".";
           if(vtemp.getProperty("Properties").toString().equals("none")){
               temp = temp+" "+vtemp.getProperty("Properties").toString();
           }
           if(vtemp.getProperty("Comparison").toString().equals("none")){
               temp = temp + " " + vtemp.getProperty("Comparison").toString();
           }
           if(vtemp.getProperty("Value").toString().equals("none")){
               temp = temp + " " + vtemp.getProperty("Value").toString();
           }
           temp = temp +")";
           Slist.append(temp);
           while(I.hasNext()){
               vtemp = I.next();
               temp = "("+vtemp.getProperty("Topic").toString()+".";
               if(vtemp.getProperty("Properties").toString().equals("none")){
                   temp = temp+" "+vtemp.getProperty("Properties").toString();
               }
               if(vtemp.getProperty("Comparison").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Comparison").toString();
               }
               if(vtemp.getProperty("Value").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Value").toString();
               }
               temp = temp +")";
               Slist.append(" or ");
               Slist.append(temp);
           }
           Slist.append(")");
       }
       if(E != null){
           Successors = E.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Elist.append("(");
           vtemp = I.next();
           temp = "("+vtemp.getProperty("Topic").toString()+".";
           if(vtemp.getProperty("Properties").toString().equals("none")){
               temp = temp+" "+vtemp.getProperty("Properties").toString();
           }
           if(vtemp.getProperty("Comparison").toString().equals("none")){
               temp = temp + " " + vtemp.getProperty("Comparison").toString();
           }
           if(vtemp.getProperty("Value").toString().equals("none")){
               temp = temp + " " + vtemp.getProperty("Value").toString();
           }
           temp = temp +")";
           Elist.append(temp);
           while(I.hasNext()){
               vtemp = I.next();
               temp = "("+vtemp.getProperty("Topic").toString()+".";
               if(vtemp.getProperty("Properties").toString().equals("none")){
                   temp = temp+" "+vtemp.getProperty("Properties").toString();
               }
               if(vtemp.getProperty("Comparison").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Comparison").toString();
               }
               if(vtemp.getProperty("Value").toString().equals("none")){
                   temp = temp + " " + vtemp.getProperty("Value").toString();
               }
               temp = temp +")";
               Elist.append(" or ");
               Elist.append(temp);
           }
           Elist.append(")");
       }
       res.append("SCOPE_"+name+":");
       res.append(System.lineSeparator());
       boolean RisnotEmpty = (Rlist.length() != 0);
       boolean AisnotEmpty = (Alist.length() != 0);
       boolean SisnotEmpty = (Slist.length() != 0);
       boolean EisnotEmpty = (Elist.length() != 0);
       if(AisnotEmpty & SisnotEmpty & EisnotEmpty ){
           res.append("("+Alist.toString()+")");
           res.append(System.lineSeparator());
           res.append("and");
           res.append(System.lineSeparator());
           res.append("("+Slist.toString()+")");
           res.append(System.lineSeparator());
           res.append("or");
           res.append(System.lineSeparator());
           res.append("!("+Elist.toString()+")");
           res.append(System.lineSeparator());
       }
       if(AisnotEmpty & !SisnotEmpty & !EisnotEmpty ){
           res.append("("+Alist.toString()+")");
       }
       if(!AisnotEmpty & !SisnotEmpty & EisnotEmpty ){
           res.append("!("+Elist.toString()+")");
       }
       if(!AisnotEmpty & SisnotEmpty & !EisnotEmpty ){
           res.append("("+Slist.toString()+")");
       }
       if(!AisnotEmpty & SisnotEmpty & EisnotEmpty ){
           res.append("("+Slist.toString()+")");
           res.append(System.lineSeparator());
           res.append("or");
           res.append(System.lineSeparator());
           res.append("!("+Elist.toString()+")");
           res.append(System.lineSeparator());
       }
       if(AisnotEmpty & !SisnotEmpty & EisnotEmpty ){
           res.append("("+Alist.toString()+")");
           res.append(System.lineSeparator());
           res.append("or");
           res.append(System.lineSeparator());
           res.append("!("+Elist.toString()+")");
           res.append(System.lineSeparator());
       }
       if(AisnotEmpty & SisnotEmpty & !EisnotEmpty ){
           res.append("("+Alist.toString()+")");
           res.append(System.lineSeparator());
           res.append("and");
           res.append(System.lineSeparator());
           res.append("("+Slist.toString()+")");
           res.append(System.lineSeparator());
       }
       res.append("REQUIREMENT "+name+":");
       res.append(System.lineSeparator());
       if(RisnotEmpty){
           res.append("("+Rlist.toString()+")");
           res.append(System.lineSeparator());
       }
       res.append("NAME :");
       res.append(System.lineSeparator());
       res.append("(REQUIREMENT "+name+" and "+"SCOPE "+name+")");
       res.append(System.lineSeparator());
       res.append("OR");
       res.append(System.lineSeparator());
       res.append("!SCOPE "+name);
       return res.toString();
   }

   public String Write_Section(OVertex pSection){
       StringBuffer res = new StringBuffer();
       Iterable<OVertex> Successors=pSection.getVertices(ODirection.OUT);
       Iterator<OVertex> I = Successors.iterator();
       res.append("Section "+pSection.getProperty("name").toString()+":");
       res.append(System.lineSeparator());
       OVertex temp= I.next();
       res.append(temp.getProperty("name").toString());
       while(I.hasNext()){
           temp = I.next();
           res.append(" and ");
           res.append(temp.getProperty("name").toString());
       }
       return res.toString();
   }

   public ArrayList<OVertex> VerticesList(ODatabaseDocument Doc){
       ArrayList<OVertex> res = new ArrayList<>();

       return res;
   }
   private static void MakeList(ArrayList<OVertex> L,OVertex V){

   }
}
