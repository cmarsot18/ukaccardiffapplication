package Writer;

import com.orientechnologies.orient.core.record.ODirection;
import com.orientechnologies.orient.core.record.OVertex;
import com.sun.glass.events.WheelEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class rules_writer {
   public static void main(String[] args) {
        File file = new File("test.txt");
        try{
            System.out.println("test    ");
            FileWriter writer = new FileWriter(file);
            StringBuffer buf = new StringBuffer();











            buf.append("prout ");
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
               Rlist.append(temp);
           }
           Rlist.append(")");
       }
       if(A != null){
           Successors = A.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Alist.append("(");
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
               Rlist.append(temp);
           }
           Alist.append(")");
       }
       if(S != null){
           Successors = S.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Slist.append("(");
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
               Slist.append(temp);
           }
           Slist.append(")");
       }
       if(E != null){
           Successors = E.getVertices(ODirection.OUT);
           I = Successors.iterator();
           Elist.append("(");
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
               Elist.append(temp);
           }
           Elist.append(")");
       }
       res.append("SCOPE_"+name+":");
       res.append(System.lineSeparator());












       return res.toString();
   }






}
