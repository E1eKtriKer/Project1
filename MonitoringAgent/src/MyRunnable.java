import java.math.BigInteger;
import java.util.Map;

public class MyRunnable  implements Runnable {

    String zeit = null;
    public Runnable createRunnable(String name, BigInteger wert){

        return new Runnable(){
            public void run(){
                System.out.println(name + " : " + wert + " " + zeit);
            }
        };

    }

    @Override
    public void run() {

    }
}
