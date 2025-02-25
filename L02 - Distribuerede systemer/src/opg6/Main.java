package opg6;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Common common = new Common();

        Thread1 t1 = new Thread1(common);
        Thread2 t2 = new Thread2(common);

        t1.start();
        t2.start();
    }
}
