import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Vydelek {
    public static void main(String[] args) {

        String[] mesice = { "leden", "únor", "březen", "duben", "květen", "červen", "červenec", "srpen", "září", "říjen", "listopad", "prosinec" };
        List<MesicniVydelek> vydelky = new ArrayList<MesicniVydelek>(); //Vytvoření kolekce s výdělky
        Scanner sc = new Scanner(System.in);

        for(String mesic : mesice){
            double vydelek;


            boolean jeSpravne = false; //Kontrolní boolean, který slouží k potvrzení zadání správného vstupu uživatele, pokud uživatel zadal správný input vrátí se hodnota true
            /*
             * Následující cyklus while se stará o kontrolu zadáné hodnoty uživatelem, pokud zadáná hodnota není číslo nebo celé číslo (např.: 1 nebo 10),
             *  tak se celý cyklus bude opakovat do doby dokud uživatel nezadá správnou hodnotu, při zadání špatné hodnoty se uživateli do konzole
             *  vytiskne varovná zpráva
             */
            while (!jeSpravne){
                try{
                    System.out.print(mesic + ": ");
                    vydelek = Double.parseDouble(sc.nextLine()); //Přetypování (Parsování) vstupu uživatele do proměnné vydelek, provádí se překlad ze String do Double
                    vydelky.add(new MesicniVydelek(mesic, vydelek));
                    jeSpravne = true; //Nastavení booleanu jeSpravne na true, které ukončení zacyklení cyklu while

                }catch (Exception e ){
                    /*
                     * Exception error, se spustí při jakékoliv chybě, nebo při uživatelem zvolené chybě,
                     */
                    System.out.println("Zadej znovu: ");
                }
            }
        }
        sc.close();

        System.out.println();

        double celkovyVydelek = vydelky.stream().map(v -> v.getVydelek()).collect(Collectors.summingDouble(Double::doubleValue));
        OptionalDouble prumernyVydelek = vydelky.stream().mapToDouble(v -> v.getVydelek()).average();

        double nejmensiVydelek = vydelky.stream().map(v -> v.getVydelek()).min(Double::compareTo).get();
        double nejvetsiVydelek = vydelky.stream().map(v -> v.getVydelek()).max(Double::compareTo).get();

        int pocetVydelecnychMesicu = (int) vydelky.stream().filter(v -> v.getVydelek() > 0).count();
        int pocetProdelecnychMesicu = (int) vydelky.stream().filter(v -> v.getVydelek() < 0).count();

        String prvniVydelecnyMesic = vydelky.stream().filter(v -> v.getVydelek() > 0).findFirst().get().getMesic();
        String posledniVydelecnyMesic = vydelky.stream().filter(v -> v.getVydelek() > 0).reduce((prvni, druhy) -> druhy).get().getMesic();

        System.out.println("Celkový výdělek: " + (int)celkovyVydelek + " Kč" );
        System.out.println("Průměrný výdělek: " + prumernyVydelek.getAsDouble() + " Kč");
        System.out.println("Nejmenší výdělek: " + (int)nejmensiVydelek + " Kč");
        System.out.println("Největší výdělek: " + (int)nejvetsiVydelek + " Kč");
        System.out.println("Počet výdělečných měsíců: " + pocetVydelecnychMesicu);
        System.out.println("Počet prodělečných měsíců: " + pocetProdelecnychMesicu);
        System.out.println("První výdělečný měsíc: " +  prvniVydelecnyMesic);
        System.out.println("Poslední výdělečný měsíc: " + posledniVydelecnyMesic);

    }
}