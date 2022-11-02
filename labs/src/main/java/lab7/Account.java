package lab7;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private double balance;
	ReentrantLock lock = new ReentrantLock();

    /**
     *
     * @param money
     */
    public  void deposit(double money) {
        try {
			lock.lock();
			double newBalance = balance + money;
			try {
			    Thread.sleep(10);   // Simulating this service takes some processing time
			}
			catch(InterruptedException ex) {
			    ex.printStackTrace();
			}
			balance = newBalance;
//			lock.unlock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

    }


    public double getBalance() {
        return balance;
    }
}