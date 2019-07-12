package Document;

public class Section
{
    String name;
    int subsection_number;
    int successor_number;


    public Section(){
        name = "None";
        subsection_number = -1;
        successor_number = 0;
    }

    public Section(String pName,Section pPredecessor){
        name= pName;
        subsection_number = pPredecessor.GetSuccessorNumber();
        successor_number=0;
    }
    public String Getname(){
        return name;
    }
    public int GetSubsectionNumber(){
        return  subsection_number;
    }

    public int GetSuccessorNumber(){
        return successor_number;
    }

    public void SetName(String pName) {
        this.name = name;
    }
    public void SetSubsectionNumber(int pNumber){
        this.subsection_number= pNumber;
    }

    public void SetSuccessor_number(int pNumber) {
        this.successor_number = pNumber;
    }
    public void AddSuccessor()

}
