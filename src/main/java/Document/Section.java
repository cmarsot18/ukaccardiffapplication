package Document;


import java.util.ArrayList;



public class Section
{
    String name;
    int subsection_number;
    ArrayList<Section> Successors;
    Section Predecessor = null;

    public Section(){
        name = "None";
        subsection_number = -1;
        Successors=new ArrayList<Section>();
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

    public void AddSuccessor(int type, String Name){
        if (type ==1){
            Section S = new Section();
            this.Successors.add(S);
            S.subsection_number=this.Successors.size();
            S.name = Name;
        }
        if(type == 2){
            Paragraph S = new Paragraph();
            this.Successors.add(S);
            S.subsection_number=this.Successors.size();
            S.name = Name;
        }
    }
    public void DeleteSuccessor(int index){
        this.Successors.remove(index-1);
        int i;
        int max=Successors.size()-1;
        for (i= index; i <= max; i++){
            Section S = this.Successors.get(i);
            S.SetSubsectionNumber(S.GetSubsectionNumber()-1);
        }


    }
    public void MoveSuccessor(int indexFrom,int IndexTo) {
        Section S = this.Successors.get(indexFrom-1);
        this.Successors.remove(indexFrom-1);
        this.Successors.add(IndexTo-1,S);
        int i;
        int max=Successors.size()-1;
        for (i= indexFrom; i <=IndexTo-1; i++){
            S = Successors.get(i);
            S.SetSubsectionNumber(S.GetSubsectionNumber()-1);
        }
        for(i=IndexTo+1;i <= max; i++){
            S = Successors.get(i);
            S.SetSubsectionNumber(S.GetSubsectionNumber()+1);
        }
    }
}
