package mytest;

public class SyncThreadTest {

	Integer num = 0;

	public static void main(String[] args) {

		SyncThreadTest stt = new SyncThreadTest();
		ThreadSon ts1 = new ThreadSon(stt, "ts1-->");
		ThreadSon ts2 = new ThreadSon(stt, "ts2-->");
		ts1.start();
		ts2.start();
	}
}

class ThreadSon extends Thread {
	private SyncThreadTest stt;

	public ThreadSon(SyncThreadTest stt, String name) {
		super(name);
		this.stt = stt;
	}

	@Override
	public void run() {
		while (true) {
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			synchronized (stt) {
				if (stt.num < 11)
					print();
				else
					break;
			}
		}
	}

	public void print() {
		System.out.println(Thread.currentThread().getName() + stt.num);
		stt.num++;
		// notifyAll();
		// try {
		// wait();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}
}
