package mytest;

public class RunableTest {
	Integer num = 0;

	public static void main(String[] args) {
		RunableTest rt = new RunableTest();
		Run target1 = new Run(rt);
		Run target2 = new Run(rt);
		Thread r1 = new Thread(target1, "Th1-->");
		Thread r2 = new Thread(target2, "Th2-->");
		r1.start();
		r2.start();
	}
}

class Run implements Runnable {

	private RunableTest rt;

	public Run(RunableTest rt) {
		this.rt = rt;
	}

	@Override
	public void run() {
		for (; rt.num < 20; rt.num++) {
			System.out.println(Thread.currentThread().getName() + " : " + rt.num);
		}
	}

}