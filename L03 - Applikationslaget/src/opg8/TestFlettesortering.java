package opg8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFlettesortering {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList<Integer>();
		Random r=new Random();
		for (int i=0;i<1_000_000;i++) {
			list.add(Math.abs(r.nextInt()%10000));
		};

		ThreadFlet t1 = new ThreadFlet(list, 0, list.size() / 2);
		ThreadFlet t2 = new ThreadFlet(list, (list.size()/2) + 1, list.size()-1);
		//System.out.println(list);

		FletteSortering sort = new FletteSortering();
		long l1,l2;
		l1 = System.nanoTime();

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		sort.merge(list, 0, list.size()/2, list.size()-1);

		l2 = System.nanoTime();
		System.out.println();
		System.out.println("Køretiden var " + (l2-l1)/1000000);
		System.out.println();
		//System.out.println(list);

		/*
		 * Køretider u/tråde:
		 * 760, 722, 756, 709, 811
		 *
		 * Køretider m/tråde:
		 * 415, 471, 408, 409, 440
		 */
	}

}
