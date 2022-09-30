import java.util.ArrayList;

public class Waiter extends Thread {
	Order o;
	Chef c;
	ArrayList<Order> list;

	public Waiter(ArrayList<Order> orders) {
		list = new ArrayList<Order>();
	}

	public void run() {
		// do while arraylist is not empty:
		// pass order to chef, notify()
		c = new Chef(list);
		
		synchronized (c) {
			for (Order o : list) {
				while (!list.isEmpty()){
					if (!c.isAlive()){
						c.start();
					}
					else {
						c.notify();
						// while at Table is false, if prepared, bring to table, else wait
						
						while (!o.isAtTable()) {
							if (o.isPrepared) {
								o.processOrder(true);;
								c.notify();
							} else {
								try {
									c.wait();
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

}
