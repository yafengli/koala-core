package test.filelock;

import org.junit.Test;

public class LockTest {
	@Test
	public void testLock() {
		Thread t1 = new Thread(new LockThread());
		Thread t2 = new Thread(new LockThread());
		t1.start();
		t2.start();
		try {
			Thread.currentThread().sleep(5000);
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
