package Document;

public class Paragraph extends Section
{
    String R;
    String A;
    String S;
    String E;
    String text;

    public Paragraph() {
        super("none",0,0);
        R = "r";
        A = "a";
        S = "s";
        E = "e";
        this.text = "text";
    }
    public Paragraph(String pName, int pSubsctionNumber, int pSuccessorNUmber, String r, String a, String s, String e, String text) {
        super(pName, pSubsctionNumber,pSuccessorNUmber);
        R = r;
        A = a;
        S = s;
        E = e;
        this.text = text;
    }
    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


























}
