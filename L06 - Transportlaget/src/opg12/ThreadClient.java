package opg12;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadClient extends Thread {

    String modifiedSentence = "";
    BufferedReader inFromServer;
    boolean samtale;
    String clientIP;

    public ThreadClient(BufferedReader inFromServer, String clientIP) {
        this.inFromServer = inFromServer;
        samtale = true;
        this.clientIP = clientIP;
    }

    public void run() {
        while (samtale) {
            try {
                modifiedSentence = inFromServer.readLine();
                System.out.println(clientIP + ": " + modifiedSentence);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void slutSamtale() {
        samtale = false;
    }
}
