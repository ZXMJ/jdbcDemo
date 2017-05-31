package mytest;

public class SyncRunableTest implements Runnable {

	Integer num = 0;

	Integer flag = 10;

	@Override
	public void run() {
		while (true) {
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			synchronized (this.flag) {
				if (num < 11) {
					print();
				} else
					break;
			}
		}
	}

	public void print() {
		System.out.println(Thread.currentThread().getName() + num);
		num++;
		// 实现一替一个打印
		// notify();
		// try {
		// wait();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}

	public static void main(String[] args) {
		SyncRunableTest target = new SyncRunableTest();
		Thread st1 = new Thread(target, "st1-->");
		Thread st2 = new Thread(target, "st2-->");
		st1.start();
		st2.start();
	}
}
