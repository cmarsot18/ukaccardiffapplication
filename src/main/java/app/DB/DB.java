package app.DB;

import app.Document.Paragraph;
import app.Document.Section;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.ODirection;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import app.Windows.RASE;

import java.util.*;

//this class allows to interact with the server
public class DB
{
    OrientDB Database;
    public String Login;
    public String Password;

    public DB(){
        Database = null;
    }
    //function made to connect to the server, return true if connection get established
    public boolean Connection(String URL,String Login,String Password){
        boolean Connection_established;
        this.Login = Login;
        this.Password = Password;
        StackTraceElement[] tab = null;
        try{

            Database = new OrientDB(URL,Login,Password,OrientDBConfig.defaultConfig());
        }catch(Exception e){
            tab = e.getStackTrace();
            System.out.println(tab);
        }
        if(tab == null){
            Connection_established = true;
            return Connection_established;
        }else {
            Connection_established=false;
            return Connection_established;
        }
    }

    public OrientDB getDatabase(){
        return this.Database;
    }
    //Function made to Save a document to the Database
    public void SaveNewDocument(Section root,String Login,String Password){
        this.Database.create(root.Getname().replace(" ",""),ODatabaseType.PLOCAL);
        ODatabaseDocument document = Database.open(root.Getname().replace(" ",""),Login,Password);
        //Init the different class linked to document structure
        OClass Section = document.createVertexClass("Section");
        OClass Paragraph = document.createVertexClass("Paragraph");
        OClass Topic = document.createVertexClass("Topic");
        OClass R_RASE = document.createVertexClass("R_RASE");
        OClass A_RASE = document.createVertexClass("A_RASE");
        OClass S_RASE = document.createVertexClass("S_RASE");
        OClass E_RASE = document.createVertexClass("E_RASE");
        //Saving the data to DB
        SectionToVertex(root,document,Paragraph,Section,R_RASE,A_RASE,S_RASE,E_RASE,Topic);
        document.close();
    }
    //Recursive method that allow to go from de Section/Paragraph to the DataBase
    public OVertex SectionToVertex(Section pSection, ODatabaseDocument document,OClass Paragraph ,OClass Section, OClass pR, OClass pA, OClass pS, OClass pE,OClass Topic ){
        if(pSection instanceof Paragraph){
            OVertex v = document.newVertex(Paragraph);
            v.setProperty("name",pSection.Getname());
            v.setProperty("R",((Paragraph) pSection).getR());
            v.setProperty("A",((Paragraph) pSection).getA());
            v.setProperty("S",((Paragraph) pSection).getS());
            v.setProperty("E",((Paragraph) pSection).getE());
            v.setProperty("Text",((Paragraph) pSection).getText());
            //Add the R field to the paragraph if it`s not empty
            if(((Paragraph) pSection).getR().length() != 1){
                OVertex R = document.newVertex(pR);
                R.save();
                OEdge etemp = document.newEdge(v,R);
                etemp.save();
                String r = ((Paragraph) pSection).getR();
                String[] Topics = RASE.DataToUI(r);
                int i,j,k;
                String stemp;
                OVertex vtemp;
                //Reader of the different topic
                for(k = 0; k < Topics.length ; k++ ){
                    i=0;
                    vtemp = document.newVertex(Topic);
                    j = Topics[k].indexOf(">>",i);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Topic",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Properties",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Comparison",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Value",stemp);
                    i=j+2;
                    stemp = Topics[k].substring(i);
                    vtemp.setProperty("Unit",stemp);
                    etemp = document.newEdge(R,vtemp);
                    etemp.save();
                    vtemp.save();
                }
            }
            //Add the A field to the paragraph if it`s not empty
            if(((Paragraph) pSection).getA().length() != 1){
                OVertex A = document.newVertex(pA);
                A.save();
                OEdge etemp = document.newEdge(v,A);
                etemp.save();
                String a = ((Paragraph) pSection).getA();
                String[] Topics = RASE.DataToUI(a);
                int i,j,k;
                String stemp;
                OVertex vtemp;
                //Reader of the different topic
                for(k = 0; k < Topics.length ; k++ ){
                    i=0;
                    vtemp = document.newVertex(Topic);
                    j = Topics[k].indexOf(">>",i);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Topic",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Properties",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Comparison",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Value",stemp);
                    i=j+2;
                    stemp = Topics[k].substring(i);
                    vtemp.setProperty("Unit",stemp);
                    etemp = document.newEdge(A,vtemp);
                    etemp.save();
                    vtemp.save();
                }
            }
            //Add the S field to the paragraph if it`s not empty
            if(((Paragraph) pSection).getS().length() != 1){
                OVertex S  = document.newVertex(pS);
                S.save();
                OEdge etemp = document.newEdge(v,S);
                etemp.save();
                String s = ((Paragraph) pSection).getS();
                String[] Topics = RASE.DataToUI(s);
                int i,j,k;
                String stemp;
                OVertex vtemp;
                //Reader of the different topic
                for(k = 0; k < Topics.length ; k++ ){
                    i=0;
                    vtemp = document.newVertex(Topic);
                    j = Topics[k].indexOf(">>",i);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Topic",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Properties",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Comparison",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Value",stemp);
                    i=j+2;
                    stemp = Topics[k].substring(i);
                    vtemp.setProperty("Unit",stemp);
                    etemp = document.newEdge(S,vtemp);
                    etemp.save();
                    vtemp.save();
                }
            }
            //Add the E field to the paragraph if it`s not empty
            if(((Paragraph) pSection).getE().length() != 1){
                OVertex E = document.newVertex(pE);
                E.save();
                OEdge etemp = document.newEdge(v,E);
                etemp.save();
                String e = ((Paragraph) pSection).getE();
                String[] Topics = RASE.DataToUI(e);
                int i,j,k;
                i=0;
                String stemp;
                OVertex vtemp;
                //Reader of the different topic
                for(k = 0; k < Topics.length ; k++ ){
                    i=0;
                    vtemp = document.newVertex(Topic);
                    j = Topics[k].indexOf(">>",i);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Topic",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Properties",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Comparison",stemp);
                    i=j+2;
                    j = Topics[k].indexOf(">>",i);
                    stemp = Topics[k].substring(i,j);
                    vtemp.setProperty("Value",stemp);
                    i=j+2;
                    stemp = Topics[k].substring(i);
                    vtemp.setProperty("Unit",stemp);
                    etemp = document.newEdge(E,vtemp);
                    etemp.save();
                    vtemp.save();
                }
            }
            v.save();
            return v;
        }else{
            OVertex v = document.newVertex(Section);
            v.setProperty("name",pSection.Getname());
            if(pSection.GetPredecessor()==null){
                v.setProperty("PredecessorName","root_init_doc");
            }else{
                v.setProperty("PredecessorName",pSection.GetPredecessor().Getname());
            }
            v.save();
            int i;
            //applying the recursion to all successors
            for(i=0;i<pSection.GetSuccessors().size();i++){
                Section stemp = pSection.GetSuccessors().get(i);
                OVertex vtemp = this.SectionToVertex(stemp,document,Paragraph,Section,pR,pA,pS,pE,Topic);
                //make the link between vertices
                OEdge etemp = document.newEdge(v,vtemp);
                etemp.save();
            }
            return v;
        }
    }
    //Allow to load a document from the DataBase
    public Section LoadDocument(String DocName,String Login,String Password){
        ODatabaseDocument Doc = Database.open(DocName,Login,Password);
        //the following lines allow to get the root of the document, in fact the root predecessors is root_init_doc
        OResultSet tryroot =  Doc.query("SELECT * FROM V WHERE PredecessorName = \"root_init_doc\"");
        String rid =  tryroot.next().getProperty("@rid").toString();
        ORecordId id = new ORecordId(rid);
        OVertex root = Doc.getRecord(id);
        //Give the root to the recursive function to make go From Database to Section/Paragraph storage
        Section res = VertexToSection(root);
        tryroot.close();
        return res;
    }
    //Reverse of SectionToVertex
    public Section VertexToSection(OVertex pVertex){
        Iterable<OVertex> Successors=pVertex.getVertices(ODirection.OUT);
        String temp = pVertex.getProperty("@class").toString();
        if(temp.equals("Section")){
            Section stemp = new Section();
            temp = pVertex.getProperty("name").toString();
            stemp.SetName(temp);
            Iterator<OVertex> I = Successors.iterator();
            while(I.hasNext()){
                OVertex vtemp = I.next();
                Section stemp2 = this.VertexToSection(vtemp);
                stemp2.SetPredecessor(stemp);
                stemp.AddSuccessor(stemp2);
            }
            return stemp;
        }else{
            Paragraph ptemp = new Paragraph();
            temp = pVertex.getProperty("name").toString();
            ptemp.SetName(temp);
            temp = pVertex.getProperty("R").toString();
            ptemp.setR(temp);
            temp = pVertex.getProperty("A").toString();
            ptemp.setA(temp);
            temp = pVertex.getProperty("S").toString();
            ptemp.setS(temp);
            temp = pVertex.getProperty("E").toString();
            ptemp.setE(temp);
            temp = pVertex.getProperty("Text").toString();
            ptemp.setText(temp);
            return ptemp;
        }

    }
    //method that return the list of Vertices from the DataBase
    public ArrayList<OVertex> Make_rules_list(String DocName,String Login,String Password){
        ArrayList<OVertex> res = new ArrayList<>();
        ODatabaseDocument Doc = Database.open(DocName,Login,Password);
        OResultSet tryroot =  Doc.query("SELECT * FROM V WHERE PredecessorName = \"root_init_doc\"");
        String rid =  tryroot.next().getProperty("@rid").toString();
        ORecordId id = new ORecordId(rid);
        OVertex root = Doc.getRecord(id);
        Listing_Vertex(res,root);
        return res;
    }
    //recursive method that makes the List used for the rules
    private static void Listing_Vertex(ArrayList<OVertex> list, OVertex V){
        Iterable<OVertex> Successors=V.getVertices(ODirection.OUT);
        String temp = V.getProperty("@class").toString();
        if(temp.equals("Paragraph")) {
            list.add(V);
        }else {
            Iterator<OVertex> I = Successors.iterator();
            OVertex vtemp;
            list.add(V);
            while(I.hasNext()) {
                vtemp = I.next();
                Listing_Vertex(list,vtemp);
            }
        }
    }

}


