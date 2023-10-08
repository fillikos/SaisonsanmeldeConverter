package de.fillikos.vrorg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Rennstatistik {

    private Ergebnis ergebnis;
    private ConfigDatei configDatei;
    private Elements tabellen;

    public Rennstatistik() {
        this.configDatei = ConfigDatei.loadFile();
        try {
            Document doc = Jsoup.connect(configDatei.getRennstatistikLink()).get();
            tabellen = doc.select(".r2la_table");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void ladeErgebnis() {
        ergebnis = new Ergebnis();
        int sp9gt3 = 1;
        int cup2 = 1;
        int sp3t = 1;
        int h2 = 1;
        int h4 = 1;
        int sp10gt4 = 1;
        int lapsLeaderSp9gt3 = 0;
        int lapsLeaderCup2 = 0;
        int lapsLeaderSp3t = 0;
        int lapsLeaderH2 = 0;
        int lapsLeaderH4 = 0;
        int lapsLeaderSp10gt4 = 0;
        Element raceResult = tabellen.get(1);
//        System.out.println(raceResult);
        Elements zeilen = raceResult.select("tr");
        for (int i = 1; i < zeilen.size(); i++) {
            Element zeile = zeilen.get(i);
            Element child = zeile.child(6);
            if (child.text().equals("DNS")) {
                break;
            }
            Team team = new Team();
            team.setPositionOverall(Integer.parseInt(zeile.child(0).text()));
            team.getFahrer().add(zeile.child(2).text());
            team.setTeamName(zeile.child(3).text());
            team.setVehicleNumber(Integer.parseInt(zeile.child(4).text().substring(1,
                    zeile.child(4).text().indexOf(" "))));
            team.setVehicle(zeile.child(4).text().substring(zeile.child(4).text().indexOf(" ") + 1));
            team.setLaps(Integer.parseInt(zeile.child(5).text()));
            Klasse klasse = getKlasse(zeile.child(4).text());
            switch (klasse) {
                case SP9GT3 -> {
                    if (sp9gt3 == 1) {
                        lapsLeaderSp9gt3 = Integer.parseInt(zeile.child(5).text());
                    }
                    team.setPositionClass(sp9gt3++);
                    team.setGewertet(check60prozent(team.getLaps(), lapsLeaderSp9gt3));
                }
                case CUP2 -> {
                    if (cup2 == 1) {
                        lapsLeaderCup2 = Integer.parseInt(zeile.child(5).text());
                    }
                    team.setPositionClass(cup2++);
                    team.setGewertet(check60prozent(team.getLaps(), lapsLeaderCup2));
                }
                case SP3T -> {
                    if (sp3t == 1) {
                        lapsLeaderSp3t = Integer.parseInt(zeile.child(5).text());
                    }
                    team.setPositionClass(sp3t++);
                    team.setGewertet(check60prozent(team.getLaps(), lapsLeaderSp3t));
                }
                case H2 -> {
                    if (h2 == 1) {
                        lapsLeaderH2 = Integer.parseInt(zeile.child(5).text());
                    }
                    team.setPositionClass(h2++);
                    team.setGewertet(check60prozent(team.getLaps(), lapsLeaderH2));
                }
                case H4 -> {
                    if (h4 == 1) {
                        lapsLeaderH4 = Integer.parseInt(zeile.child(5).text());
                    }
                    team.setPositionClass(h4++);
                    team.setGewertet(check60prozent(team.getLaps(), lapsLeaderH4));
                }
                case SP10GT4 -> {
                    if (sp10gt4 == 1) {
                        lapsLeaderSp10gt4 = Integer.parseInt(zeile.child(5).text());
                    }
                    team.setPositionClass(sp10gt4++);
                    team.setGewertet(check60prozent(team.getLaps(), lapsLeaderSp10gt4));
                }
                case DEFAULT -> System.out.println("Das sollte nicht passieren");
            }
//            System.out.println(team);
            ergebnis.getTeams().add(team);
        }
    }

    private boolean check60prozent(int laps, int lapsLeader) {
        if (laps == 0) return false;
        return ((100 / lapsLeader * laps) >= 60);
    }

    private Klasse getKlasse(String text) {
        if (text.contains("#1")) {
            return Klasse.SP9GT3;
        } else if (text.contains("#2")) {
            return Klasse.CUP2;
        } else if (text.contains("#3")) {
            return Klasse.SP3T;
        } else if (text.contains("#4")) {
            return Klasse.H2;
        } else if (text.contains("#5")) {
            return Klasse.H4;
        } else if (text.contains("#6")) {
            return Klasse.SP10GT4;
        }
        return Klasse.DEFAULT;
    }

    public Ergebnis getErgebnis() {
        return ergebnis;
    }
}
