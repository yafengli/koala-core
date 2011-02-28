package test.filelock;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockThread implements Runnable {

	@Override
	public void run() {
		FileChannel fc = null;
		FileLock lock = null;
		FileOutputStream out=null;
		try {
			out=new FileOutputStream("f:/Test.html");
			fc =out.getChannel();
			while (true) {
				try {
					// lock=fc.lock();
					lock = fc.tryLock();
				} catch (Exception e) {
				}
				System.out.printf("[Thread=%s,lock=%s]\n", Thread
						.currentThread().getId(), lock);
				if (lock != null) {
					try {
						Thread.sleep(3000);
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (lock != null)
				lock.release();
			fc.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
