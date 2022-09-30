import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Restaurant implements ActionListener {
	static ArrayList<Order> orders;
	Order r;

	public Restaurant() {
		JFrame frame = new JFrame("Restaurant");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		JPanel orderPanel = new JPanel(new BorderLayout());
		JPanel tablesPanel = new JPanel(new BorderLayout());
		JPanel typePanel = new JPanel(new BorderLayout());
		JPanel dishPanel = new JPanel(new BorderLayout());
		TitledBorder title = BorderFactory.createTitledBorder("Table No:");
	    TitledBorder title2 = BorderFactory.createTitledBorder("Dish Type");
	    TitledBorder title3 = BorderFactory.createTitledBorder("Dish");
	    tablesPanel.setBorder(title);
		typePanel.setBorder(title2);
		dishPanel.setBorder(title3);
		JTextArea txt = new JTextArea();
		content.add(orderPanel, BorderLayout.NORTH);
		content.add(txt, BorderLayout.SOUTH);
		JLabel tableNum = new JLabel("Table No:");
		JLabel type = new JLabel("Type:");
		String[] tables = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		String[] dishTypes = { "Starter", "Main", "Dessert" };
		JComboBox<String> tableNo = new JComboBox<String>(tables);
		JComboBox<String> dishType = new JComboBox<String>(dishTypes);
		JTextField dish = new JTextField();
		dish.setColumns(30);
		JButton bOrder = new JButton("Place Order");
		tablesPanel.add(tableNum);
		tablesPanel.add(tableNo);
		typePanel.add(type);
		typePanel.add(dishType);
        dishPanel.add(dish);
		orderPanel.add(tablesPanel, BorderLayout.WEST);
		orderPanel.add(typePanel, BorderLayout.CENTER);
		orderPanel.add(dishPanel, BorderLayout.EAST);
		orderPanel.add(bOrder, BorderLayout.SOUTH);
		orders = new ArrayList<Order>();
		tableNo.addActionListener(this);
		dish.addActionListener(this);

		bOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r = new Order((String) tableNo.getSelectedItem(), (String) dishType.getSelectedItem(), dish.getText(),
						false, false);
				orders.add(r);
				dish.setText(null);
				txt.append(r.getOrder() + "\n");
				
			}
		});
		for (Order order : orders) {
			if (order.isAtTable()) {
				txt.append("Table No " + order.getTableNo() + " has received their dish!");
			}
		}
		
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new Restaurant();
		Waiter w = new Waiter(orders);
		w.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
