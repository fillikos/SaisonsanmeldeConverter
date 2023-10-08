package de.fillikos.vrorg;

import java.util.Objects;

public class Anmeldung {
    private int nummer;
    private Klasse klasse;
    private String fahrzeug;
    private String teanName;
    private boolean teamStatus;
    private String angemeldet;

    public Anmeldung(int nummer, Klasse klasse, String fahrzeug, String teanName, boolean teamStatus) {
        this.nummer = nummer;
        this.klasse = klasse;
        this.fahrzeug = fahrzeug;
        this.teanName = teanName;
        this.teamStatus = teamStatus;
        this.angemeldet = "";
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(String fahrzeug) {
        this.fahrzeug = fahrzeug;
    }

    public String getTeanName() {
        return teanName;
    }

    public void setTeanName(String teanName) {
        this.teanName = teanName;
    }

    /**
     * Returned true bei Stammteam und false bei Ersatzteam
     * @return true == Stammteam
     */
    public boolean isTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(boolean teamStatus) {
        this.teamStatus = teamStatus;
    }

    /**
     * String im Form des BBCode Symbols für angemeldet / abgemeldet / wartend ET
     * @return :white_check_mark: == angemeldet   :x: == abgemeldet
     */
    public String isAngemeldet() {
        return angemeldet;
    }

    public void setAngemeldet(String angemeldet) {
        this.angemeldet = angemeldet;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public boolean equalsNummer(int nummer, Anmeldung anmeldung) {
        return anmeldung.getNummer() == nummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anmeldung anmeldung = (Anmeldung) o;
        return nummer == anmeldung.nummer && teamStatus == anmeldung.teamStatus && angemeldet == anmeldung.angemeldet && klasse == anmeldung.klasse && Objects.equals(fahrzeug, anmeldung.fahrzeug) && Objects.equals(teanName, anmeldung.teanName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nummer, klasse, fahrzeug, teanName, teamStatus, angemeldet);
    }

    @Override
    public String toString() {
        return "Anmeldung{" +
                "nummer=" + nummer +
                ", klasse=" + klasse +
                ", fahrzeug='" + fahrzeug + '\'' +
                ", teanName='" + teanName + '\'' +
                ", teamStatus=" + teamStatus +
                ", angemeldet=" + angemeldet +
                '}';
    }
}
