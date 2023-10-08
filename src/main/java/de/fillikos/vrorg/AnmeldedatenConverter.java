package de.fillikos.vrorg;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

public class AnmeldedatenConverter {

    private ConfigDatei configDatei;
    private List<Anmeldung> anmeldungen;
    private String timestamp;

    public AnmeldedatenConverter() {

    }

    public AnmeldedatenConverter(ConfigDatei configDatei, List<Anmeldung> anmeldungen) {
        this.configDatei = configDatei;
        this.anmeldungen = anmeldungen;
    }

    public String getAnmeldedatenBBCode() {
        StringBuilder returnValue = new StringBuilder();
        boolean ersterDurchlauf = true;

        Klasse klasseOld = Klasse.DEFAULT;
        for (Anmeldung anmeldung: anmeldungen) {
            if (anmeldung.getKlasse() != klasseOld) {
                returnValue.append(String.format("""
                [/TABLE]
                
                [B]Klasse %s:[/B]
                [TABLE]
                [TR]
                [TH][COLOR=rgb(209, 213, 216)]Nummer[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Fahrzeug[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Teamname[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Status[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Anmeldung[/COLOR][/TH]
                [/TR]
                """, anmeldung.getKlasse().getAusgabeName()));
            }
            if (ersterDurchlauf) {
                returnValue.delete(0,10);
                ersterDurchlauf = false;
            }
            returnValue.append(
                    String.format("""
                    [TR]
                    [TD]#%d[/TD]
                    [TD]%s[/TD]
                    [TD]%s[/TD]
                    [TD]%s[/TD]
                    [TD]%s[/TD]
                    [/TR]
                    """,
                    anmeldung.getNummer(),
                    anmeldung.getFahrzeug(),
                    anmeldung.getTeanName(),
                    anmeldung.isTeamStatus() ? "[COLOR=rgb(65, 168, 95)][B]ST[/B][/COLOR]" : "[COLOR=#FF0000][B]ET[/B][/COLOR]",
                    anmeldung.isAngemeldet())
            );
            klasseOld = anmeldung.getKlasse();
        }
        returnValue.append("[/TABLE]");
        return returnValue.toString();
    }

    public String getAnmeldedatenBBCode4HTML() {
        StringBuilder returnValue = new StringBuilder();
        boolean ersterDurchlauf = true;

        Klasse klasseOld = Klasse.DEFAULT;
        for (Anmeldung anmeldung: anmeldungen) {
            if (anmeldung.getKlasse() != klasseOld) {
                returnValue.append(String.format("""
                [/TABLE]<br>
                <br>
                [B]Klasse %s:[/B]<br>
                [TABLE]<br>
                [TR]<br>
                [TH][COLOR=rgb(209, 213, 216)]Nummer[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Fahrzeug[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Teamname[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Status[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Anmeldung[/COLOR][/TH]<br>
                [/TR]<br>
                """, anmeldung.getKlasse().getAusgabeName()));
            }
            if (ersterDurchlauf) {
                returnValue.delete(0,12);
                ersterDurchlauf = false;
            }
            returnValue.append(
                    String.format("""
                    [TR]<br>
                    [TD]#%d[/TD]<br>
                    [TD]%s[/TD]<br>
                    [TD]%s[/TD]<br>
                    [TD]%s[/TD]<br>
                    [TD]%s[/TD]<br>
                    [/TR]<br>
                    """,
                            anmeldung.getNummer(),
                            anmeldung.getFahrzeug(),
                            anmeldung.getTeanName(),
                            anmeldung.isTeamStatus() ? "[COLOR=rgb(65, 168, 95)][B]ST[/B][/COLOR]" : "[COLOR=#FF0000][B]ET[/B][/COLOR]",
                            anmeldung.isAngemeldet())
            );
            klasseOld = anmeldung.getKlasse();
        }
        returnValue.append("[/TABLE]");
        return returnValue.toString();
    }

    public String getNeueAnmeldedatenBBCode4HTML() {
        StringBuilder returnValue = new StringBuilder();
        boolean ersterDurchlauf = true;

        Klasse klasseOld = Klasse.DEFAULT;
        for (Anmeldung anmeldung: anmeldungen) {
            if (anmeldung.getKlasse() != klasseOld) {
                returnValue.append(String.format("""
                [/TABLE]<br>
                <br>
                [B]Klasse %s:[/B]<br>
                [TABLE]<br>
                [TR]<br>
                [TH][COLOR=rgb(209, 213, 216)]Nummer[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Fahrzeug[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Teamname[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Status[/COLOR][/TH]<br>
                [TH][COLOR=rgb(209, 213, 216)]Anmeldung[/COLOR][/TH]<br>
                [/TR]<br>
                """, anmeldung.getKlasse().getAusgabeName()));
            }
            if (ersterDurchlauf) {
                returnValue.delete(0,12);
                ersterDurchlauf = false;
            }
            returnValue.append(
                    String.format("""
                    [TR]<br>
                    [TD]#%d[/TD]<br>
                    [TD]%s[/TD]<br>
                    [TD]%s[/TD]<br>
                    [TD]%s[/TD]<br>
                    [TD][/TD]<br>
                    [/TR]<br>
                    """,
                            anmeldung.getNummer(),
                            anmeldung.getFahrzeug(),
                            anmeldung.getTeanName(),
                            anmeldung.isTeamStatus() ? "[COLOR=rgb(65, 168, 95)][B]ST[/B][/COLOR]" : "[COLOR=#FF0000][B]ET[/B][/COLOR]"
                    ));
            klasseOld = anmeldung.getKlasse();
        }
        returnValue.append("[/TABLE]");
        return returnValue.toString();
    }

    public String getNeueAnmeldedatenBBCode() {
        StringBuilder returnValue = new StringBuilder();
        boolean ersterDurchlauf = true;

        Klasse klasseOld = Klasse.DEFAULT;
        for (Anmeldung anmeldung: anmeldungen) {
            if (anmeldung.getKlasse() != klasseOld) {
                returnValue.append(String.format("""
                [/TABLE]
                
                [B]Klasse %s:[/B]
                [TABLE]
                [TR]
                [TH][COLOR=rgb(209, 213, 216)]Nummer[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Fahrzeug[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Teamname[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Status[/COLOR][/TH]
                [TH][COLOR=rgb(209, 213, 216)]Anmeldung[/COLOR][/TH]
                [/TR]
                """, anmeldung.getKlasse().getAusgabeName()));
            }
            if (ersterDurchlauf) {
                returnValue.delete(0,10);
                ersterDurchlauf = false;
            }
            returnValue.append(
                    String.format("""
                    [TR]
                    [TD]#%d[/TD]
                    [TD]%s[/TD]
                    [TD]%s[/TD]
                    [TD]%s[/TD]
                    [TD][/TD]
                    [/TR]
                    """,
                            anmeldung.getNummer(),
                            anmeldung.getFahrzeug(),
                            anmeldung.getTeanName(),
                            anmeldung.isTeamStatus() ? "[COLOR=rgb(65, 168, 95)][B]ST[/B][/COLOR]" : "[COLOR=#FF0000][B]ET[/B][/COLOR]")
            );
            klasseOld = anmeldung.getKlasse();
        }
        returnValue.append("[/TABLE]");
        return returnValue.toString();
    }

    public String getEventanmeldeHead() {
        StandardCharsets.UTF_8.newDecoder();
        return String.format("""
                In diesem Thread könnt ihr eure Teams und die dafür vorgesehenen Fahrer für den %d. Wertungslauf anmelden oder auch abmelden.
                                
                Alle Teams, die am %d. Wertungslauf teilnehmen möchten, können sich zwischen [B][COLOR=rgb(191, 0, 0)]Mittwoch, %s, 19:00 Uhr (Forenzeit) und Mittwoch, %s, 22:00 Uhr (Forenzeit)[/COLOR][/B] anmelden.
                Bei der Anmeldung müssen die folgenden Angaben gemacht werden:
                [B]Startnummer und Teilnahmeabsicht[/B]
                                
                Die Anzahl der Fahrer pro Team pro Wertungslauf ist nicht vorgegeben und kann von Wertungslauf zu Wertungslauf variieren, ebenso die Besetzung.
                                
                Beispiel:
                #xxx, nimmt teil [U][I]oder[/I][/U] nimmt nicht teil
                                
                [B][COLOR=rgb(184, 49, 47)]Beiträge, die editiert werden, verlieren ihre Gültigkeit![/COLOR][/B]
                                
                freie zusätzliche Startplätze für Ersatzteams: (Stand: %s Uhr)
                %s
                                
                Freie Startplätze aufgrund von Abmeldungen werden zunächst klassenintern vergeben. Ist dies aufgrund von mangelnder Nachfrage nicht möglich, werden diese Plätze der Reihe nach klassenübergreifend vergeben.
                                
                freie Stammplätze: (Stand: %s Uhr)
                %s
                                
                Die freien Stammplätze erhalten die Ersatzteams, die sich zuerst für dieses Rennen anmelden.
                                
                                
                Legende:
                [COLOR=rgb(65, 168, 95)][B]ST[/B][/COLOR] = Stammteams
                [COLOR=#FF0000][B]ET[/B][/COLOR] = Ersatzteams
                                
                :white_check_mark: = Angemeldet
                :x: = Abgemeldet
                :ballot_box_with_check: = Anmeldung, aber bisher kein freier Startplatz (nur für Ersatzteams)
                                
                                
                ---------------------------------------------------------------------------------------
                [B]Übersicht (Stand: %s Uhr):[/B]
                
                """,
                configDatei.getWertungslaufNr(),
                configDatei.getWertungslaufNr(),
                configDatei.getAnmeldeStart(),
                configDatei.getAnmeldeEnde(),
                getTimestamp(),
                getZusSP4HTML(),
                getTimestamp(),
                getFreieST4HTML(),
                getTimestamp()
        );
    }

    public String getEventanmeldeHead4HTML() {
        StandardCharsets.UTF_8.newDecoder();
        return String.format("""
                In diesem Thread könnt ihr eure Teams und die dafür vorgesehenen Fahrer für den %d. Wertungslauf anmelden oder auch abmelden.<br>
                <br>
                Alle Teams, die am %d. Wertungslauf teilnehmen möchten, können sich zwischen [B][COLOR=rgb(191, 0, 0)]Mittwoch, %s, 19:00 Uhr (Forenzeit) und Mittwoch, %s, 22:00 Uhr (Forenzeit)[/COLOR][/B] anmelden.<br>
                Bei der Anmeldung müssen die folgenden Angaben gemacht werden:<br>
                [B]Startnummer und Teilnahmeabsicht[/B]<br>
                <br>
                Die Anzahl der Fahrer pro Team pro Wertungslauf ist nicht vorgegeben und kann von Wertungslauf zu Wertungslauf variieren, ebenso die Besetzung.
                <br>
                Beispiel:<br>
                #xxx, nimmt teil [U][I]oder[/I][/U] nimmt nicht teil<br>
                <br>
                [B][COLOR=rgb(184, 49, 47)]Beiträge, die editiert werden, verlieren ihre Gültigkeit![/COLOR][/B]<br>
                <br>
                freie zusätzliche Startplätze für Ersatzteams: (Stand: %s Uhr)<br>
                %s
                <br>
                Freie Startplätze aufgrund von Abmeldungen werden zunächst klassenintern vergeben. Ist dies aufgrund von mangelnder Nachfrage nicht möglich, werden diese Plätze der Reihe nach klassenübergreifend vergeben.<br>
                <br>
                freie Stammplätze: (Stand: %s Uhr)<br>
                %s
                <br>
                Die freien Stammplätze erhalten die Ersatzteams, die sich zuerst für dieses Rennen anmelden.<br>
                <br>
                <br>
                Legende:<br>
                [COLOR=rgb(65, 168, 95)][B]ST[/B][/COLOR] = Stammteams<br>
                [COLOR=#FF0000][B]ET[/B][/COLOR] = Ersatzteams<br>
                <br>
                :white_check_mark: = Angemeldet<br>
                :x: = Abgemeldet<br>
                :ballot_box_with_check: = Anmeldung, aber bisher kein freier Startplatz (nur für Ersatzteams)<br>
                <br>
                <br>
                ---------------------------------------------------------------------------------------<br>
                [B]Übersicht (Stand: %s Uhr):[/B]<br>
                <br>
                """,
                configDatei.getWertungslaufNr(),
                configDatei.getWertungslaufNr(),
                configDatei.getAnmeldeStart(),
                configDatei.getAnmeldeEnde(),
                getTimestamp(),
                getZusSP4HTML(),
                getTimestamp(),
                getFreieST4HTML(),
                getTimestamp()
        );
    }

    private String getZusSP4HTML() {
        int zusSP = getZusSPGesamt();
        return String.format("""
                [B]Klasse SP9 GT3: %s<br>
                Klasse Cup 2: %s<br>
                Klasse SP3T: %s<br>
                Klasse H2: %s<br>
                Klasse H4: %s<br>
                Klasse SP10 GT4: %s[/B]<br>""",
                zusSP,
                zusSP,
                zusSP,
                zusSP,
                zusSP,
                zusSP
        );
    }

    private int getZusSPGesamt() {
        int i = 0;
        for (Anmeldung anmeldung : anmeldungen) {
            if (anmeldung.isTeamStatus() && anmeldung.isAngemeldet().equals(":x:") ) {
                i++;
            }
            if (!anmeldung.isTeamStatus() && anmeldung.isAngemeldet().contains(":white_check_mark:")) {
                i--;
            }
        }
        return i;
    }

    private String getFreieST4HTML() {
        int freieSTGesamt = getFreieSTGesamt();
        return String.format("""
                [B]Klasse SP9 GT3: %s<br>
                Klasse Cup 2: %s<br>
                Klasse SP3T: %s<br>
                Klasse H2: %s<br>
                Klasse H4: %s<br>
                Klasse SP10 GT4: %s[/B]<br>""",
                getFreieST(Klasse.SP9GT3, freieSTGesamt),
                getFreieST(Klasse.CUP2, freieSTGesamt),
                getFreieST(Klasse.SP3T, freieSTGesamt),
                getFreieST(Klasse.H2, freieSTGesamt),
                getFreieST(Klasse.H4, freieSTGesamt),
                getFreieST(Klasse.SP10GT4, freieSTGesamt)
                );
    }

    private String getFreieST(Klasse klasse, int freieSTGesamt) {
        int i = 15;
        for (Anmeldung anmeldung : anmeldungen) {
            if (anmeldung.getKlasse().equals(klasse) && anmeldung.isTeamStatus()) {
                i--;
            }
        }
        if (freieSTGesamt > 0) {
            if (i < freieSTGesamt) {
                return String.valueOf(i);
            } else {
                return String.valueOf(freieSTGesamt);
            }
        }
        return String.valueOf(0);
    }

    private int getFreieSTGesamt() {
        int i = 50;
        for (Anmeldung anmeldung : anmeldungen) {
            if (anmeldung.isTeamStatus()) {
                i--;
            }
        }
        return i;
    }

    private String getTimestamp() {
        if (timestamp == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            timestamp =  sdf.format(System.currentTimeMillis() - (System.currentTimeMillis() % 300_000)); // abgerundet auf 5 Minuten
        }
        return timestamp;
    }


}
