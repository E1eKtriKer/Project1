import java.io.FileInputStream;
import java.io.IOException;

import java.math.BigInteger;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.Map.*;
import static java.util.concurrent.TimeUnit.*;


public class Scheduler {



    public static void main(String[] args) {
        Properties prop = new Properties();
        String fileName = "Konfiguration";
        DatumZeit datetime = new DatumZeit();
        Filegrabber filegrabber = new Filegrabber();
        MyRunnable myrun = new MyRunnable();
        if (args.length == 0){
        try (FileInputStream fis = new FileInputStream(fileName)) {
                prop.load(fis);
            } catch (IOException ignored) {

            }
            filegrabber.init(prop.getProperty("Pfad"));
        }
        else {
            filegrabber.init(args[0]);
        }


        ScheduledExecutorService ses = Executors.newScheduledThreadPool(filegrabber.Ordner.size()+1);

            for (Entry entry : filegrabber.Ordner.entrySet()) {

                Runnable run = () -> {
                    myrun.zeit = datetime.setDT();
                    myrun.createRunnable((String) entry.getKey(), filegrabber.getKPI((String) entry.getKey()));

                };
                ses.scheduleAtFixedRate(run, 10, Long.parseLong(prop.getProperty((String) entry.getKey())), SECONDS);
            }


        Runnable Reboot = () -> {
                System.out.println("reboot");
                ses.shutdown();
                filegrabber.init(prop.getProperty("Pfad"));
            };
        ses.scheduleAtFixedRate(Reboot, 24, Long.parseLong(prop.getProperty("Interval_reboot")), SECONDS);
    }
}
