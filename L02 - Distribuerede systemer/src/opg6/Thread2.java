package opg6;

public class Thread2 extends Thread {

    Common common;

    public Thread2(Common common) {
        this.common = common;
    }

    public void run() {
        while (true) {
            System.out.println(common.getText());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            /*
            sout (string)
            sleep 3 sek
             */
        }
    }
}
