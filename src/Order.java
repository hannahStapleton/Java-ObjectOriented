public class Order {

	protected String tabelNo;
	protected String type;
	protected String dish;
	protected boolean isPrepared;
	protected boolean atTable;

	public Order() {

		this.tabelNo = "";
		this.type = "";
		this.dish = "";
		this.isPrepared = false;
		this.atTable = false;

	}

	public Order(String tableNum, String dishType, String dishOrdered, boolean prepped, boolean processed) {
		this.tabelNo = tableNum;
		this.type = dishType;
		this.dish = dishOrdered;
		this.isPrepared = prepped;
		this.atTable = processed;
	}

	public String getTableNo() {
		return this.tabelNo;
	}

	public String getDish() {
		return this.dish;
	}

	public boolean isPrepped() {
		return this.isPrepared;
	}

	public void setPrepped(boolean p) {
		this.isPrepared = p;
	}

	public boolean isAtTable() {
		return this.atTable;
	}

	public void processOrder(boolean processed) {
		this.atTable = processed;
	}

	public String getOrder() {
		String status;
		if (!isPrepped()) {
			status = "Being prepared ";
		} else {
			if (isAtTable()) {
				status = "Served";
			} else
				status = "On its way!";
		}
		return "-------ORDER-------\nTable No: " + this.tabelNo + "\nDish : " + this.dish + "\nStatus: " + status;
	}
}
