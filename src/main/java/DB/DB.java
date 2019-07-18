package DB;

import Document.Paragraph;
import Document.Section;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;

public class DB
{
    OrientDB Database;

    public DB(){
        //Database = new OrientDB();
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

    }
    public void SaveSection(Section pSection, ODatabaseDocument document){
        if(pSection instanceof Paragraph){
            //String Query = "CREATE VERTEX Paragraph "+pSection.Getname()+" SET name = `"+pSection.Getname()+"`, R = `"+pSection.GetR();


        }
    }
}
