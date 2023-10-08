package de.fillikos.vrorg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ergebnis {

    private List<Team> teams = new ArrayList<>();

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ergebnis ergebnis = (Ergebnis) o;
        return Objects.equals(teams, ergebnis.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teams);
    }

    @Override
    public String toString() {
        return "Ergebnis{" +
                "teams=" + teams +
                '}';
    }
}
