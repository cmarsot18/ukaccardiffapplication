package DB;

import Document.Paragraph;
import Document.Section;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.ODirection;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;


public class DB
{
    OrientDB Database;
    boolean ClassNotCreated = true;

    public DB(){
        Database = null;
    }

    public boolean Connection(String URL,String Login,String Password){
        boolean Connection_established;
        StackTraceElement[] tab = null;
        try{

            Database = new OrientDB(URL,Login,Password,OrientDBConfig.defaultConfig());
        }catch(Exception e){
            tab = e.getStackTrace();
        }
        if(tab == null){
            Connection_established = true;
            return Connection_established;
        }else {
            Connection_established=false;
            return Connection_established;
        }
    }

    public void SaveNewDocument(Section root,String Login,String Password){
        this.Database.create(root.Getname(),ODatabaseType.PLOCAL);
        ODatabaseDocument document = Database.open(root.Getname(),Login,Password);
        OClass Section = document.createVertexClass("Section");
        OClass Paragraph = document.createVertexClass("Paragraph");
        SectionToVertex(root,document,Paragraph,Section);
        document.close();
    }

    public OVertex SectionToVertex(Section pSection, ODatabaseDocument document,OClass Paragraph ,OClass Section ){
        if(pSection instanceof Paragraph){
            OVertex v = document.newVertex(Paragraph);
            v.setProperty("name",pSection.Getname());
            v.setProperty("R",((Paragraph) pSection).getR());
            v.setProperty("A",((Paragraph) pSection).getA());
            v.setProperty("S",((Paragraph) pSection).getS());
            v.setProperty("E",((Paragraph) pSection).getE());
            v.setProperty("Text",((Paragraph) pSection).getText());
            v.save();
            return v;
        }else{
            OVertex v = document.newVertex(Section);
            v.setProperty("name",pSection.Getname());
            if(pSection.GetPredecessor()==null){
                v.setProperty("PredecessorName","root");
            }else{
                v.setProperty("PredecessorName",pSection.GetPredecessor().Getname());
            }
            v.save();
            int i;
            for(i=0;i<pSection.GetSuccessors().size();i++){
                Section stemp = pSection.GetSuccessors().get(i);
                OVertex vtemp = this.SectionToVertex(stemp,document,Paragraph,Section);
                OEdge etemp = document.newEdge(v,vtemp);
                etemp.save();
            }
            return v;
        }
    }

    public Section LoadDocument(String DocName,String Login,String Password){
        ODatabaseDocument Doc = Database.open(DocName,Login,Password);
        OResultSet tryroot =  Doc.query("SELECT * FROM V WHERE PredecessorName = \"root\"");
        //Stream<OVertex> origin = root.vertexStream();

        tryroot.close();




        return null;
    }

//    public Section VertexToSection(OVertex pVertex){
//        Iterable<OVertex> Successors=pVertex.getVertices(ODirection.OUT);
//        if (pVertex.isVertex()){
//            //Section S = new Section(pVertex.getProperty("name"), pVertex.getProperty("PredecessorName"));
//        }
//
//
//    }

}
