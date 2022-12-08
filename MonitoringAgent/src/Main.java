import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final File c = new File("/Users/mavi/BA/MonitoringAgent/Konfiguration"); //hier pfad der konfigurationsdatei

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        String fileName = "Konfiguration";
        Scheduler scheduler = new Scheduler();

        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (IOException ignored) {
        }
        DatumZeit d = new DatumZeit();
        Filegrabber f = new Filegrabber();
        String pfad = prop.getProperty("Pfad");

        if (args.length != 0) {
            pfad = args[0];
        }
        f.init(pfad);


        for (int xx = 0; xx<2;xx++) {
            for (Entry entry : f.Ordner.entrySet()) {
                String zeit = d.setDT();
                System.out.println(entry.getKey() + " : " + f.getKPI((String) entry.getKey()) + "  " + zeit);
            }
        }

        //System.out.println("late messages" + " = " + f.getKPI("late_messages"));
        //System.out.println("ac_0_voltage" + " = " + prop.getProperty("ac_0_voltage"));
        for (int i = 1; i < args.length; i++) {
            System.out.println();
            System.out.println(args[i] + " = " + f.getKPI(args[i]));
        }



    }
}