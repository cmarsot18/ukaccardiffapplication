package app;

import app.Document.Paragraph;
import app.Windows.*;


public class test{
    public static void main(String[] args){
//        Section root = new Section();
//        root.SetName("doc");
//        Section Part1 = new Section();
//        Section Part2 = new Section();
//        Section Part11 = new Section();
//        Section Part12 = new Section();
        Paragraph text1 = new Paragraph();
//        Paragraph text2 = new Paragraph();
//        Paragraph text3 = new Paragraph();
//        ArrayList<Section> list1 = new ArrayList <Section>();
//        ArrayList<Section> list2 = new ArrayList<Section>();
//        ArrayList<Section> list3 = new ArrayList<Section>();
//        ArrayList<Section> list4 = new ArrayList<Section>();
//        list1.add(Part1);
//        list1.add(Part2);
//        list2.add(Part11);
//        list2.add(Part12);
//        list3.add(text1);
//        list3.add(text2);
//        list4.add(text3);
//        root.SetSuccessor_number(list1);
//        Part1.SetSuccessor_number(list2);
//        Part11.SetSuccessor_number(list3);
//        Part12.SetSuccessor_number(list4);
//        Part1.SetSubsectionNumber(1);
//        Part2.SetSubsectionNumber(2);
//        Part11.SetSubsectionNumber(1);
//        Part12.SetSubsectionNumber(2);
//        text1.SetSubsectionNumber(1);
//        text2.SetSubsectionNumber(2);
//        text3.SetSubsectionNumber(1);
//        Part1.SetPredecessor(root);
//        Part2.SetPredecessor(root);
//        Part11.SetPredecessor(Part1);
//        Part12.SetPredecessor(Part1);
//        text1.SetPredecessor(Part11);
//        text2.SetPredecessor(Part11);
//        text3.SetPredecessor(Part12);
//        Part1.SetName("Part1");
//        Part2.SetName("Part2");
//        Part11.SetName("Part11");
//        Part12.SetName("Part12");
//        text1.SetName("text1");
//        text2.SetName("text2");
//        text3.SetName("text3");
//        text1.setA("(vert1>>n>>ne>>prout>>no)");
//        text1.setE("(tes5tplus>>none>>none>>none>>none)<$>(bleu1>>q>>app.test>>v>>none)");
//        text1.setR("(rouge1>>e>>no>>ne>>noe)");
//        text1.setS("(rose1>>a>>a>>onen>>nae)");
//        text1.setText("rase1");
//        text2.setA("a2");
//        text2.setE("e2");
//        text2.setR("r2");
//        text2.setS("s2");
//        text2.setText("rase2");
//        text3.setA("a3");
//        text3.setE("e3");
//        text3.setR("r3");
//        text3.setS("s3");
//        text3.setText("rase3");
//        app.DB app.test= new app.DB();
//        boolean Connection = app.test.Connection("remote:localhost","root","password");
//        if(app.test.getDatabase().exists("doc")){
//            app.test.getDatabase().drop("doc");
//            app.test.getDatabase().drop("DocBis");
//            System.out.println("b");
//        }
//        if (Connection){
//            app.test.SaveNewDocument(root,"admin","admin");
//            Section load = app.test.LoadDocument("doc","admin","admin");
//            load.SetName("DocBis");
//            app.test.SaveNewDocument(load,"admin","admin");
//            System.out.println("success");
//        }else{
//            System.out.println("fail");
//        }

        //new Document_Display(root);
//    new Doc_Creation();
//    new Connection();
//        new Document_Select(app.test);
        Text_Editor test =  new Text_Editor(text1,"","test");
            test.setVisible(true);
//        new RASE(text1);
//        System.out.println(text1.getR());
//        System.out.println(text1.getA());
//        System.out.println(text1.getS());
//        System.out.println(text1.getE());
//        text1.update("");
//        text1.update("<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"background-color: rgb(255, 0, 0);\">rouge1</span></p><p>Du texte pour rien</p><p><span style=\"background-color: rgb(255, 0, 255);\">rose1</span></p><p>Du texte pour rien</p><p><span style=\"background-color: rgb(0, 255, 0);\">vert1</span></p><p>Du texte pour rien</p><p><span style=\"background-color: rgb(0, 255, 255);\">bleu1</span></p></body></html>    <span style=\"background-color: rgb(255, 0, 0);\">op</span>");
//        System.out.println("---------------------------------");
//        System.out.println(text1.getR());
//        System.out.println(text1.getA());
//        System.out.println(text1.getS());
//        System.out.println(text1.getE());
    }
}


