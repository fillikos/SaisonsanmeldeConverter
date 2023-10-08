package de.fillikos.vrorg;

import java.util.Objects;

public class Wertungslauf {

    private String anmeldeStart;
    private String anmeldeEnde;
    private String rennTag;
    private int nummer;


    public Wertungslauf() {

    }

    public Wertungslauf(int nummer, String rennTag, String anmeldeStart, String anmeldeEnde) {
        this.anmeldeStart = anmeldeStart;
        this.anmeldeEnde = anmeldeEnde;
        this.rennTag = rennTag;
        this.nummer = nummer;
    }

    public String getAnmeldeStart() {
        return anmeldeStart;
    }

    public void setAnmeldeStart(String anmeldeStart) {
        this.anmeldeStart = anmeldeStart;
    }

    public String getAnmeldeEnde() {
        return anmeldeEnde;
    }

    public void setAnmeldeEnde(String anmeldeEnde) {
        this.anmeldeEnde = anmeldeEnde;
    }

    public String getRennTag() {
        return rennTag;
    }

    public void setRennTag(String rennTag) {
        this.rennTag = rennTag;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wertungslauf that = (Wertungslauf) o;
        return nummer == that.nummer && Objects.equals(anmeldeStart, that.anmeldeStart) && Objects.equals(anmeldeEnde, that.anmeldeEnde) && Objects.equals(rennTag, that.rennTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anmeldeStart, anmeldeEnde, rennTag, nummer);
    }

    @Override
    public String toString() {
        return "Wertungslauf{" +
                "anmeldeStart='" + anmeldeStart + '\'' +
                ", anmeldeEnde='" + anmeldeEnde + '\'' +
                ", rennTag='" + rennTag + '\'' +
                ", nummer=" + nummer +
                '}';
    }
}
