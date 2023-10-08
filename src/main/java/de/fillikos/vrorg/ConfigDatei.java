package de.fillikos.vrorg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConfigDatei {

    private String saisonanmeldeThread;
    private String eventanmeldeThread;
    private String ausgabeDatei;
    private String anmeldeStart;
    private String anmeldeEnde;
    private String rennTag;
    private int wertungslaufNr;

    public ConfigDatei() {
    }

    public ConfigDatei(String saisonanmeldeThread, String eventanmeldeThread, String ausgabeDatei, String anmeldeStart, String anmeldeEnde, String rennTag, int wertungslaufNr) {
        this.saisonanmeldeThread = saisonanmeldeThread;
        this.eventanmeldeThread = eventanmeldeThread;
        this.ausgabeDatei = ausgabeDatei;
        this.anmeldeStart = anmeldeStart;
        this.anmeldeEnde = anmeldeEnde;
        this.rennTag = rennTag;
        this.wertungslaufNr = wertungslaufNr;
    }

    public static ConfigDatei loadFile() {
        System.setProperty("file.encoding", "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("C:\\bbcode\\config.json"), ConfigDatei.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        System.setProperty("file.encoding", "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("C:\\bbcode\\config.json"), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSaisonanmeldeThread() {
        return saisonanmeldeThread;
    }

    public void setSaisonanmeldeThread(String saisonanmeldeThread) {
        this.saisonanmeldeThread = saisonanmeldeThread;
    }

    public String getEventanmeldeThread() {
        return eventanmeldeThread;
    }

    public void setEventanmeldeThread(String eventanmeldeThread) {
        this.eventanmeldeThread = eventanmeldeThread;
    }

    public String getAusgabeDatei() {
        return ausgabeDatei;
    }

    public void setAusgabeDatei(String ausgabeDatei) {
        this.ausgabeDatei = ausgabeDatei;
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

    public int getWertungslaufNr() {
        return wertungslaufNr;
    }

    public void setWertungslaufNr(int wertungslaufNr) {
        this.wertungslaufNr = wertungslaufNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigDatei that = (ConfigDatei) o;
        return wertungslaufNr == that.wertungslaufNr && Objects.equals(saisonanmeldeThread, that.saisonanmeldeThread) && Objects.equals(eventanmeldeThread, that.eventanmeldeThread) && Objects.equals(ausgabeDatei, that.ausgabeDatei) && Objects.equals(anmeldeStart, that.anmeldeStart) && Objects.equals(anmeldeEnde, that.anmeldeEnde) && Objects.equals(rennTag, that.rennTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saisonanmeldeThread, eventanmeldeThread, ausgabeDatei, anmeldeStart, anmeldeEnde, rennTag, wertungslaufNr);
    }

    @Override
    public String toString() {
        return "ConfigDatei{" +
                "saisonanmeldeThread='" + saisonanmeldeThread + '\'' +
                ", eventanmeldeThread='" + eventanmeldeThread + '\'' +
                ", ausgabeDatei='" + ausgabeDatei + '\'' +
                ", anmeldeStart='" + anmeldeStart + '\'' +
                ", anmeldeEnde='" + anmeldeEnde + '\'' +
                ", rennTag='" + rennTag + '\'' +
                ", nummer=" + wertungslaufNr +
                '}';
    }
}
