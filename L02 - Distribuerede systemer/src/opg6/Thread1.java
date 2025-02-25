package opg6;

import java.util.Scanner;

public class Thread1 extends Thread {

    Common common;

    public Thread1(Common common) {
        this.common = common;
    }

    public void run() {
        while (true) {
            System.out.println("Skriv venligst en streng:");
            Scanner input = new Scanner(System.in);
            common.setText(input.nextLine());
            /*
            indlæser en string fra tastatur
            Common.setText
             */
        }
    }
}
