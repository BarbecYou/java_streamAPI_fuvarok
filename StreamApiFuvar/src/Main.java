import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static List<Fuvar> fuvarLista = new ArrayList<>();

    public static void main(String[] args) {

        try {
            feltolt();
        } catch (FileNotFoundException e){
            throw new Error("Hibás file");
        }

        feladat1();
        feladat2(6185);
        feladat3();
        feladat4();
        feladat5();
        feladat6(4261);
        feladat7();
        feladat8(1452);
        feladat9();
        feladat10();
        feladat11();
    }

    private static void feladat11() {
        double borravaloAranyDec31 = fuvarLista.stream()
                .filter(f -> f.getIndulas().getMonth() == Month.DECEMBER && f.getIndulas().getDayOfMonth() == 31)
                .mapToDouble(f -> (f.getBorravalo() / f.getViteldij()) * 100).average().getAsDouble();
        System.out.println();
        System.out.println();
        System.out.printf("December 31-én %.1f%%-os arányban adtak borravalót az utasok", borravaloAranyDec31);
    }

    private static void feladat10() {
        long fuvarSzamDec24 = fuvarLista.stream()
                        .filter(f -> f.getIndulas().getMonth() == Month.DECEMBER && f.getIndulas().getDayOfMonth() == 24)
                                .count();

        System.out.println();
        System.out.printf("December 24-én %d fuvar volt", fuvarSzamDec24);
    }

    private static void feladat9() {
        List<Fuvar> legrovidebbFuvarok = fuvarLista.stream()
                .filter(f -> f.getIdotartam() > 0)
                .sorted(Comparator.comparing(Fuvar::getIdotartam))
                .limit(3)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("A 3 legrövidebb fuvar adatai: ");
        for (Fuvar f : legrovidebbFuvarok){
            System.out.println(f);
            System.out.println();
        }
    }

    private static void feladat8(int taxiId) {
        boolean vanIlyenTaxis = fuvarLista.stream()
                .map(f -> f.getId())
                .distinct()
                .filter(id -> id == taxiId)
                .count() >= 1;
        System.out.println();
        System.out.println();
        System.out.printf((vanIlyenTaxis ? "Van" : "Nincs") + " %d azonosítójú taxis", taxiId);
    }

    private static void feladat7() {
        long hibasFuvarokSzama = fuvarLista.stream()
                .filter(f -> f.getIdotartam() > 0 && f.getViteldij() > 0 && f.getTavolsag() == 0)
                .count();
        int hibasFuvarokIdotartama = fuvarLista.stream()
                .filter(f -> f.getIdotartam() > 0 && f.getViteldij() > 0 && f.getTavolsag() == 0)
                .mapToInt(f -> f.getIdotartam()).sum();
        double hibasFuvarokBevetel = fuvarLista.stream()
                .filter(f -> f.getIdotartam() > 0 && f.getViteldij() > 0 && f.getTavolsag() == 0)
                .mapToDouble(f -> f.getViteldij() + f.getBorravalo()).sum();
        System.out.println();
        System.out.println();
        System.out.printf("Hibás fuvarok\n" +
                "száma: %d\n" +
                "összes időtartama: %d\n" +
                "teljes bevétele: %.3f", hibasFuvarokSzama, hibasFuvarokIdotartama, hibasFuvarokBevetel);

    }

    private static void feladat6(int taxiId) {
        double megtettKilometer = fuvarLista.stream()
                .filter(f -> f.getId() == taxiId)
                .mapToDouble(f -> f.getTavolsag() * 1.6).sum();

        System.out.println();
        System.out.printf("A taxis összesen %.3f km távolságot tett meg", megtettKilometer);
    }

    private static void feladat5() {
        Fuvar legtobbBorravalo = fuvarLista.stream()
                .max(Comparator.comparing(f -> {return f.getBorravalo() / f.getViteldij();})).get();
        System.out.println();
        System.out.println("A legtöbb borravalót kapott fuvar: " + legtobbBorravalo);
    }

    private static void feladat4() {
        Fuvar leghoszabbFuvar = fuvarLista.stream()
                .max(Comparator.comparing(Fuvar::getIdotartam)).get();
        System.out.println();
        System.out.println("A leghoszabb fuvar adatai: ");
        System.out.println(leghoszabbFuvar);
    }

    private static void feladat3() {
        double osszMerfold = fuvarLista.stream()
                .mapToDouble(p -> p.getTavolsag())
                .sum();
        System.out.println();
        System.out.println("Az összes taxi által megtett távolság: " + osszMerfold + " mérföld");
    }

    private static void feladat2(int taxiId) {
        long fuvarszam = fuvarLista.stream()
                .filter(p -> p.getId() == taxiId)
                .count();
        double bevetel = fuvarLista.stream()
                .filter(p -> p.getId() == taxiId)
                .mapToDouble(p -> p.getViteldij() + p.getBorravalo()).sum();
        System.out.println();
        System.out.printf("A taxis bevétele %d fuvarból: " + bevetel, fuvarszam);
    }

    private static void feladat1() {
        System.out.printf(fuvarLista.size() + " utazás került feljegyzésre");
    }

    private static void feltolt() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("fuvar.csv"));
        sc.nextLine();
        while (sc.hasNext()){
            fuvarLista.add(new Fuvar(sc.nextLine()));
        }
    }
}