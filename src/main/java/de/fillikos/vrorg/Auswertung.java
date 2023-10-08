package de.fillikos.vrorg;

import java.util.List;

public class Auswertung {

    private final List<Anmeldung> uebersichtAnmeldungen;
    private final Ergebnis ergebnis;
    public Auswertung(List<Anmeldung> uebersichtAnmeldungen) {
        this.uebersichtAnmeldungen = uebersichtAnmeldungen;
        Rennstatistik rennstatistik =new Rennstatistik();
        rennstatistik.ladeErgebnis();
        ergebnis = rennstatistik.getErgebnis();
    }

    public String getAnmeldeAuswertung() {
        StringBuilder abgemeldet = new StringBuilder();
        abgemeldet.append("Abgemeldet - gilt als nicht Teilnahme:\n");
        StringBuilder nichtGemeldet = new StringBuilder();
        nichtGemeldet.append("Nicht gemeldet - gilt als nicht Teilnahme:\n");
        StringBuilder angemeldetOhneTeilnahme = new StringBuilder();
        angemeldetOhneTeilnahme.append("Angemeldet, aber nicht teilgenommen ==> Verlust ST:\n");
        for (Anmeldung anmeldung: uebersichtAnmeldungen) {
            if (anmeldung.isAngemeldet().isBlank()) {
                eintragHinzufuegen(nichtGemeldet, anmeldung);
            }
            if (anmeldung.isAngemeldet().equals(":x:")) {
                eintragHinzufuegen(abgemeldet, anmeldung);
            }
            if (checkAngemeldetOhneTeilnahme(anmeldung)) {
                eintragHinzufuegen(angemeldetOhneTeilnahme, anmeldung);
            }
        }
        return abgemeldet + "\n" + nichtGemeldet + "\n" + angemeldetOhneTeilnahme;
    }

    private void eintragHinzufuegen(StringBuilder stringBuilder, Anmeldung anmeldung) {
        stringBuilder.append("-#")
                .append(anmeldung.getNummer())
                .append(", ")
                .append(anmeldung.getTeamName())
                .append((anmeldung.isTeamStatus()) ? " (ST)\n" : " (ET)\n");
    }

    public static String toBBCode(String anmeldeAuswertung) {
        return anmeldeAuswertung.replaceAll("\\(ST\\)", "([B]ST[/B])");
    }

    public static String toBBCode4HTML(String anmeldeAuswertung) {
        String neu = anmeldeAuswertung.replaceAll("\\(ST\\)", "([B]ST[/B])");
        return neu.replaceAll("\n", "<br>\n");
    }

    public boolean checkAngemeldetOhneTeilnahme(Anmeldung anmeldung) {
        if (anmeldung.isAngemeldet().contains(":white_check_mark:")) {
            for (Team team: ergebnis.getTeams()) {
                if (team.getVehicleNumber() == anmeldung.getNummer()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
