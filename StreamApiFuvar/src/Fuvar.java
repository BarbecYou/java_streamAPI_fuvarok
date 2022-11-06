import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Fuvar {

    private int id;
    private LocalDateTime indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetesModja;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Fuvar(String line){
        String[] temp = line.replaceAll(",", ".").split(";");
        this.id = Integer.parseInt(temp[0]);
        this.indulas = LocalDateTime.parse(temp[1], formatter);
        this.idotartam = Integer.parseInt(temp[2]);
        this.tavolsag = Double.parseDouble(temp[3]);
        this.viteldij = Double.parseDouble(temp[4]);
        this.borravalo = Double.parseDouble(temp[5]);
        this.fizetesModja = temp[6];
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getIndulas() {
        return indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public String getFizetesModja() {
        return fizetesModja;
    }

    @Override
    public String toString() {
        return String.format("azonosító: %d\n" +
                "indulás ideje: %s\n" +
                "az utazás időtartama (mp): %d\n" +
                "megtett távolság (mérföld): %.3f\n" +
                "viteldíj: %.3f\n" +
                "borravaló: %.3f\n" +
                "fizetés módja: %s",
                this.id, this.indulas.toString(), this.idotartam, this.tavolsag, this.viteldij, this.borravalo, this.fizetesModja);
    }
}
