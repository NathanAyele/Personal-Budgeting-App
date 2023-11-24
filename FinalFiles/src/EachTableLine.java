// Class with Constructors to make it easy to add data into the table on the Main Window

public class EachTableLine extends MainController {

    String desal;
    String catal;
    String datal;
    double amoal;

    public EachTableLine(String desal, String catal, String datal, double amoal) {
        this.desal = desal;
        this.catal = catal;
        this.datal = datal;
        this.amoal = amoal;
    }

    public String getDesal() {
        return desal;
    }

    public void setDesal(String desal) {
        this.desal = desal;
    }

    public String getCatal() {
        return catal;
    }

    public void setCatal(String catal) {
        this.catal = catal;
    }

    public String getDatal() {
        return datal;
    }

    public void setDatal(String datal) {
        this.datal = datal;
    }

    public double getAmoal() {
        return amoal;
    }

    public void setAmoal(double amoal) {
        this.amoal = amoal;
    }

}
