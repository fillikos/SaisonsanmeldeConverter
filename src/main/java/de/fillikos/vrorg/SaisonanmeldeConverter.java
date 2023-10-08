package de.fillikos.vrorg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaisonanmeldeConverter {

    private ConfigDatei configDatei = new ConfigDatei();
    private final List<Anmeldung> anmeldungen = new ArrayList<>();

    public SaisonanmeldeConverter() {
        configDatei = ConfigDatei.loadFile();
    }

    public SaisonanmeldeConverter(String saisonanmeldeThread, String eventanmeldeThread) {
        new SaisonanmeldeConverter();
        if (!saisonanmeldeThread.isEmpty() || !eventanmeldeThread.isEmpty()) {
            if (!saisonanmeldeThread.isEmpty() && !saisonanmeldeThread.equals(configDatei.getSaisonanmeldeThread())) {
                configDatei.setSaisonanmeldeThread(saisonanmeldeThread);
            }
            if (!eventanmeldeThread.isEmpty() && !eventanmeldeThread.equals(configDatei.getEventanmeldeThread())) {
                configDatei.setEventanmeldeThread(eventanmeldeThread);
            }
            configDatei.saveFile();
        }
    }

    public SaisonanmeldeConverter(String saisonanmeldeThread, String eventanmeldeThread, String eventDatum, int wertungslaufNr) {
        new SaisonanmeldeConverter();
        if (!saisonanmeldeThread.isEmpty() || !eventanmeldeThread.isEmpty() ||
        !eventDatum.equals(configDatei.getRennTag()) || wertungslaufNr != configDatei.getWertungslaufNr()) {
            if (!saisonanmeldeThread.isEmpty() && !saisonanmeldeThread.equals(configDatei.getSaisonanmeldeThread())) {
                configDatei.setSaisonanmeldeThread(saisonanmeldeThread);
            }
            if (!eventanmeldeThread.isEmpty() && !eventanmeldeThread.equals(configDatei.getEventanmeldeThread())) {
                configDatei.setEventanmeldeThread(eventanmeldeThread);
            }
            if (!eventDatum.equals(configDatei.getRennTag())) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    Date date = formatter.parse(eventDatum);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    configDatei.setRennTag(eventDatum);
                    configDatei.setAnmeldeStart(sdf.format(new Date(date.getTime() - 1_000 * 60 * 60 * 24 * 17)));
                    configDatei.setAnmeldeEnde(sdf.format(new Date(date.getTime() - 1_000 * 60 * 60 * 24 * 3)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            if (wertungslaufNr != configDatei.getWertungslaufNr()) {
                configDatei.setWertungslaufNr(wertungslaufNr);
            }
            configDatei.saveFile();
        }
    }

    private String convert() {
        ForumReader forumReader = new ForumReader(configDatei);
        forumReader.getForumsDaten(anmeldungen);

        AnmeldedatenConverter anmeldedatenConverter = new AnmeldedatenConverter(configDatei, anmeldungen);
        String bbCode = anmeldedatenConverter.getEventanmeldeHead();
        bbCode += anmeldedatenConverter.getAnmeldedatenBBCode();

        return bbCode;
    }

    public void convert2Console() {
        System.out.println(convert2HTML());
    }

    public String convert2String() {
        return convert();
    }

    public String convert2HTML() {
        ForumReader forumReader = new ForumReader(configDatei);
        forumReader.getForumsDaten(anmeldungen);

        AnmeldedatenConverter anmeldedatenConverter = new AnmeldedatenConverter(configDatei, anmeldungen);
        String bbCode = anmeldedatenConverter.getEventanmeldeHead4HTML();
        bbCode += anmeldedatenConverter.getAnmeldedatenBBCode4HTML();

        return bbCode;
    }

    public void convert2File() {
        String bbCode = convert();
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(configDatei.getAusgabeDatei()))) {
            StandardCharsets.UTF_8.newDecoder();
            osw.write(bbCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertNeueAnmeldeThrad2HTML() {
        ForumReader forumReader = new ForumReader(configDatei);
        forumReader.getForumsDaten(anmeldungen);

        AnmeldedatenConverter anmeldedatenConverter = new AnmeldedatenConverter(configDatei, anmeldungen);
        String bbCode = anmeldedatenConverter.getEventanmeldeHead4HTML();
        bbCode += anmeldedatenConverter.getAnmeldedatenBBCode4HTML();

        return bbCode;
    }

    public String convertNeueAnmeldeThrad2Console() {
        ForumReader forumReader = new ForumReader(configDatei);
        forumReader.getForumsDaten(anmeldungen);

        AnmeldedatenConverter anmeldedatenConverter = new AnmeldedatenConverter(configDatei, anmeldungen);
        String bbCode = anmeldedatenConverter.getEventanmeldeHead();
        bbCode += anmeldedatenConverter.getNeueAnmeldedatenBBCode();

        return bbCode;
    }
}
