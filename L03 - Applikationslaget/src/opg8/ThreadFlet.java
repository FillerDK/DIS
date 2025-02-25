package opg8;

import java.util.List;

public class ThreadFlet extends Thread {

    FletteSortering fletteSortering = new FletteSortering();
    List<Integer> nums;
    int start;
    int slut;

    public ThreadFlet(List<Integer> list, int start, int slut) {
        nums = list;
        this.start = start;
        this.slut = slut;
    }

    public void run() {
        fletteSortering.mergesort(nums, start, slut);
    }
}
