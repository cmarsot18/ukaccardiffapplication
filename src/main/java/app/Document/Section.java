package app.Document;


import java.util.*;



public class Section
{
    String name;
    int subsection_number;
    ArrayList<Section> Successors;
    Section Predecessor = null;

    public Section(){
        name = "None";
        subsection_number = 0;
        Successors = new ArrayList<Section>();
    }

    public Section(String pName,Section pPredecessor, ArrayList<Section> pSuccessors){
        name= pName;
        subsection_number = pSuccessors.size();
        Successors=pSuccessors;
        Predecessor = pPredecessor;
    }

    public String Getname(){
        return name;
    }

    public int GetSubsectionNumber(){
        return  subsection_number;
    }

    public ArrayList<Section> GetSuccessors(){
        return Successors;
    }

    public Section GetPredecessor(){
        return this.Predecessor;
    }

    public void SetName(String pName) {
        this.name = pName;
    }

    public void SetSubsectionNumber(int pNumber){
        this.subsection_number= pNumber;
    }

    public void SetSuccessor_number(ArrayList<Section> pSuccessors) {
        Successors = pSuccessors;
    }

    public void SetPredecessor(Section pPredecessor){
        this.Predecessor=pPredecessor;
    }

    public void AddSuccessor(Section pSection){
        this.Successors.add(pSection);
        pSection.SetSubsectionNumber(this.Successors.size());

    }

    public void DeleteSuccessor(int index){
        this.Successors.remove(index-1);
        int i;
        int max=Successors.size()-1;
        for (i= index-1; i <= max; i++){
            Section S = this.Successors.get(i);
            S.SetSubsectionNumber(S.GetSubsectionNumber()-1);
        }
    }

    public void MoveSuccessor(int indexFrom,int IndexTo) {
        Section S = this.Successors.get(indexFrom-1);
        this.Successors.remove(S);
        this.Successors.add(IndexTo-1,S);
        Iterator<Section> I = this.Successors.iterator();
        int k = 1;
        Section stemp;
        while (I.hasNext()){
            stemp = I.next();
            stemp.SetSubsectionNumber(k);
            k++;
        }
    }
    //allow to get a section from the root of the tree by giving the path in the Document_display
    public Section GetSectionFromRoot(String Path){
        if((Path.indexOf("<%>")+4)>Path.length()){
            return this;
        }else{
            ArrayList<String> Predecessors = new ArrayList<String>();
            int max = Path.length();
            int i = 0;
            int j;
            while (i < max) {
                j = Path.indexOf(".", i);
                i = Path.indexOf("<%>", i);
                String temp = Path.substring(j + 1, i);
                i = i + 3;
                Predecessors.add(temp);
            }
            Iterator<String> I = Predecessors.iterator();
            I.next();
            String stemp = I.next();
            Section stemp2 = this.SearchInSuccessors(stemp);
            while (I.hasNext()) {
                stemp = I.next();
                stemp2 = stemp2.SearchInSuccessors(stemp);
            }
            return stemp2;
        }
    }

    public Section SearchInSuccessors(String Name){
        Iterator<Section> I = this.Successors.iterator();
        Section res = new Section();
        while (I.hasNext()){
            Section temp = I.next();
            if(Name.equals(temp.Getname())){
                res = temp;
            }
        }
        return res;

    }

    public void DeleteSection(){
        System.out.println(this.GetSubsectionNumber());
        this.GetPredecessor().DeleteSuccessor(this.GetSubsectionNumber());
    }

}


