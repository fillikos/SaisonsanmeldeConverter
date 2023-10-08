package de.fillikos.vrorg;

public class Main {

    public static void main(String[] args) {
        ForumReader reader = new ForumReader(ConfigDatei.loadFile());
        Auswertung auswertung = new Auswertung(reader.getUebersichtAnmeldungen());
        System.out.println(Auswertung.toBBCode4HTML(auswertung.getAnmeldeAuswertung()));

    }
}
