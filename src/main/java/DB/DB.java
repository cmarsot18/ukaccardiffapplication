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
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;

public class DB
{
    OrientDB Database;

    public DB(){
        Database = null;
    }

    public boolean Connection(String server,int port,String Login,String Password){
        boolean Connection_established;
        StackTraceElement[] tab = null;
        try{
            String URL="remote"+server+":"+port+"/";
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
        Database.create(root.Getname(),ODatabaseType.PLOCAL);
        ODatabaseDocument document = Database.open(root.Getname(),Login,Password);
        SaveSection(root,document);
    }

    public OVertex SaveSection(Section pSection, ODatabaseDocument document){

        OClass Section =  document.createVertexClass("Section");
        OClass Paragraph = document.createVertexClass("Paragraph");
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
                v.setProperty("predecessor name","root");
            }else{
                v.setProperty("predecessor name",pSection.GetPredecessor().Getname());
            }
            v.save();
            int i;
            for(i=0;i<pSection.GetSuccessors().size();i++){
                Section stemp = pSection.GetSuccessors().get(i);
                OVertex vtemp = this.SaveSection(stemp,document);
                document.newEdge(v,vtemp);
            }
            return v;
        }
    }

    public Section LoadDocument(){
        return null;
    }
}
