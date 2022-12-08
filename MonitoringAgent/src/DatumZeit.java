import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DatumZeit {

    public String fldt;

    public DatumZeit(){
    }

    /**
     * setten der local date time.
     * format Datum und Zeit bis auf Millisekunde, Zeitzone
     * @return
     */
    public String setDT(){

        OffsetDateTime ldt = OffsetDateTime.now(); // Datum-Zeitstempel
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSSS ZZZZ");
        fldt = ldt.format(format);
        return fldt;
    }
}
