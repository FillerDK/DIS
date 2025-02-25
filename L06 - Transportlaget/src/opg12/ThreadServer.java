package opg12;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadServer extends Thread {

    String clientSentence;
    String clientName;

    BufferedReader modpart;
    boolean samtale;

    public ThreadServer(BufferedReader modpart, String clientName) {
        this.modpart = modpart;
        samtale = true;
        this.clientName = clientName;
    }

    public void run() {
        while (samtale) {
            try {
                clientSentence = modpart.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (clientSentence.equals("END")) {
                System.out.println("SERVER: " +
                        clientName + " vil ikke chatte mere :(");
                System.out.println("SERVER: Samtale afsluttet!");
                TCPServer.samtale = false;
                samtale = false;
            } else {
                System.out.println(clientName.toUpperCase() + ": " + clientSentence);
            }
        }
    }
}
