import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
public class Filegrabber {
    public Map<String, String> Ordner;

    public Filegrabber(){
    }
    public void init(String pfad){
        File f = new File(pfad); //ordner auslesen
        File[] ArrayOrdner = f.listFiles(); //speichern der dateien im ordner
        Ordner = new HashMap<>();
        for (File file : ArrayOrdner) { //alle dateien printen

            File datei = new File(file.toString());
            String name = file.getName();
            Ordner.put(name, datei.toString());
        }
    }
    public Object auslesen(String pfad, String suche) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pfad));
        String line = br.readLine();
        String erg = null;
        while (line != null) {
            if (suche.equals(line)) {
                erg = br.readLine();
                break;
            } else {
                line = br.readLine();
            }
        }
        return erg;
    }
    public BigInteger getKPI(String name){
        File file = new File (Ordner.get(name));
        BigInteger wert = null;
        try {

            Scanner scan = new Scanner(file);
            scan.nextLine();
            while (scan.hasNext()) {
                wert = scan.nextBigInteger();
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wert;
    }
}
