import java.util.ArrayList;

public class Chef extends Thread {

	long mealPrep = 100;
	Order r;
	ArrayList<Order> list;

	public Chef(ArrayList<Order> list) {
		list = new ArrayList<Order>();
	}

	public void run() {
		// wait for waiter's notify()
		synchronized (this) {
			for (Order r : list) {
				while (!list.isEmpty()) {
					while (!r.isPrepped()) {
						// prepare dish for 90
						try {
							sleep(mealPrep);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						r.setPrepped(true);
						// notify waiter dish is prepared
						this.notify();
						// wait for next order
						while (r.isPrepped()) {
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}

				}
			}
		}
	}
}
