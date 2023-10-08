package de.fillikos.vrorg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ForumReader {

    private ConfigDatei configDatei;

    public ForumReader(ConfigDatei configDatei) {
        this.configDatei = configDatei;
    }

    public void getSaisonAnmeldeListe(List<Anmeldung> anmeldungen) {
        try {
            Document doc = Jsoup.connect(configDatei.getSaisonanmeldeThread()).get();
            Element firstThread = doc.selectFirst(".message-body");
            Elements tableValues = firstThread.select(".bbTable");
            Elements tables = tableValues.select("tr");

            Klasse klasse = Klasse.SP9GT3;
            int i = 0;

            for (Element zeile: tables) {
                if (zeile.firstChild().toString().startsWith("<th>")) {
                    switch (i) {
                        case 0 -> klasse = Klasse.SP9GT3;
                        case 1 -> klasse = Klasse.CUP2;
                        case 2 -> klasse = Klasse.SP3T;
                        case 3 -> klasse = Klasse.H2;
                        case 4 -> klasse = Klasse.H4;
                        case 5 -> klasse = Klasse.SP10GT4;
                    }
                    i++;
                }
                if (zeile.child(0).text().startsWith("#")) {
                    anmeldungen.add(new Anmeldung(Integer.parseInt(zeile.child(0).text().substring(1)),
                            klasse,
                            zeile.child(1).text(),
                            zeile.child(2).text(),
                            !zeile.child(6).text().equals("ET")));
                }
            }
        } catch (IOException e) {
            anmeldungen.clear();
        }
    }

    public void getEventAnmeldeDaten(List<Anmeldung> anmeldungen) {
        try {
            Document doc = Jsoup.connect(configDatei.getEventanmeldeThread()).get();
            Elements tables = doc.selectFirst(".message-body").select(".bbTable").select("tr");

            for (Element zeile: tables) {
                if (zeile.child(0).text().startsWith("#")) {
                    if (!checkAnmeldeEintrag(zeile, anmeldungen)) {
                        System.out.printf("Fehler: FahrzeugNummer %s im Anmeldethread vorhanden, aber nicht in der Saisonanmeldung.%n", zeile.child(0).text());
                    }
                }
            }
        } catch (IOException e) {
            anmeldungen.clear();
        }
    }

    private boolean checkAnmeldeEintrag(Element zeile, List<Anmeldung> anmeldungen) {
        for (Anmeldung anmeldung: anmeldungen) {
            if (anmeldung.equalsNummer(Integer.parseInt(zeile.child(0).text().substring(1)), anmeldung)) {
                if (zeile.child(4).toString().contains(":white_check_mark:")) {
                    anmeldung.setAngemeldet(":white_check_mark:" +
                            (zeile.child(4).text().isEmpty() ? "" : " " + zeile.child(4).text()));
                } else if (zeile.child(4).toString().contains(":x:")) {
                    anmeldung.setAngemeldet(":x:" +
                            (zeile.child(4).text().isEmpty() ? "" : " " + zeile.child(4).text()));
                } else {
                    anmeldung.setAngemeldet(zeile.child(4).text());
                }
                return true;
            }
        }
        return false;
    }

    public void getForumsDaten(List<Anmeldung> anmeldungen) {
        getSaisonAnmeldeListe(anmeldungen);
        getEventAnmeldeDaten(anmeldungen);
    }
}
