package mytest;

public class ThreadTest {
	int num = 0;

	public static void main(String[] args) {
		ThreadTest tt = new ThreadTest();
		Th th1 = new Th(tt, "Th1-->");
		Th th2 = new Th(tt, "Th2-->");
		th1.start();
		th2.start();
	}
}

class Th extends Thread {
	ThreadTest tt;

	public Th(ThreadTest tt, String name) {
		super(name);
		this.tt = tt;
	}

	@Override
	public void run() {
		for (; tt.num < 20; tt.num++) {
			System.out.println(Thread.currentThread().getName() + " : " + tt.num);
		}
	}
}