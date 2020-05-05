import java.io.Serializable;

public class Biblioteka implements Serializable {

    private static final long serialVersionUID = 1L;
    private  int numerId;
    private String tytul;
    private String autor;
    private int rokWydania;
    private String status;

    public Biblioteka(){}

    public Biblioteka(int numerId, String tytul, String autor, int rokWydania, String status) {
        this.numerId = numerId;
        this.tytul = tytul;
        this.autor = autor;
        this.rokWydania = rokWydania;
        this.status = status;
    }

    public int getNumberId() {
        return numerId;
    }

    public void setNumerId(int numerId) {
        this.numerId = numerId;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String  getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    @Override
    public String toString() {
        return "Biblioteka{" +
                "ID='" + numerId + '\'' +
                ", tytul=" + tytul +
                ", autor=" + autor +
                ", rok wydania='" + rokWydania + '\'' +
                ", status=" + status + '\'' +
                '}';
    }
}
