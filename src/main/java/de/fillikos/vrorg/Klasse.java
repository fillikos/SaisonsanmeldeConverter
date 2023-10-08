package de.fillikos.vrorg;

public enum Klasse {
    SP9GT3("SP9 GT3"),
    CUP2("Cup 2"),
    SP3T("SP3T"),
    H2("H2"),
    H4("H4"),
    SP10GT4("SP10 GT4"),
    DEFAULT("Default");

    private final String ausgabeName;

    Klasse(String ausgabeName) {
        this.ausgabeName = ausgabeName;
    }

    public String getAusgabeName() {
        return ausgabeName;
    }
}
