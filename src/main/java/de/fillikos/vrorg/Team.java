package de.fillikos.vrorg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {
    private int positionOverall;
    private int positionClass;
    private List<String> Fahrer = new ArrayList<>();
    private String teamName;
    private int vehicleNumber;
    private String vehicle;
    private int laps;
    private boolean gewertet;

    public Team() {
    }

    public int getPositionOverall() {
        return positionOverall;
    }

    public void setPositionOverall(int positionOverall) {
        this.positionOverall = positionOverall;
    }

    public int getPositionClass() {
        return positionClass;
    }

    public void setPositionClass(int positionClass) {
        this.positionClass = positionClass;
    }

    public List<String> getFahrer() {
        return Fahrer;
    }

    public void setFahrer(List<String> fahrer) {
        Fahrer = fahrer;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public boolean isGewertet() {
        return gewertet;
    }

    public void setGewertet(boolean gewertet) {
        this.gewertet = gewertet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return positionOverall == team.positionOverall && positionClass == team.positionClass && vehicleNumber == team.vehicleNumber && laps == team.laps && gewertet == team.gewertet && Objects.equals(Fahrer, team.Fahrer) && Objects.equals(teamName, team.teamName) && Objects.equals(vehicle, team.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionOverall, positionClass, Fahrer, teamName, vehicleNumber, vehicle, laps, gewertet);
    }

    @Override
    public String toString() {
        return "Team{" +
                "positionOverall=" + positionOverall +
                ", positionClass=" + positionClass +
                ", Fahrer=" + Fahrer +
                ", teamName='" + teamName + '\'' +
                ", vehicleNumber=" + vehicleNumber +
                ", vehicle='" + vehicle + '\'' +
                ", laps=" + laps +
                ", gewertet=" + gewertet +
                '}';
    }
}
