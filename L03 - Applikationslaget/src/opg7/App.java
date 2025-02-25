package opg7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://valutakurser.dk");
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        String str;
        boolean found = false;
        while ((str = in.readLine()) != null && !found) {
            String[] words = str.split("[<>]");
            for (int i = 0; i < words.length; i++) {
                if (words[i].contains("amerikanske-dollar")) {
                    //System.out.println(words[i]);
                    //System.out.println(words[i].indexOf("/valuta/"));
                    System.out.println(in.readLine().substring(279, 285));
                    /*
                    String[] words2 = in.readLine().split("");
                    for (int j = 0; j < words2.length; j++) {
                        if (words2[j].equalsIgnoreCase(",")) {
                            System.out.println(words2[j-1]);
                        }
                    }
                     */
                    found = true;
                }
            }
            //System.out.println(str);
        }
        in.close();
    }
}
