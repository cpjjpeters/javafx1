package be.ipeters.brol.cpbelcar.domain;

public class Orderline {
	private Integer id;
	private Integer orderId;
	private String title;
	private Integer quantity;
	private Integer carProductId;
	
	public Orderline(Integer id, Integer orderId, String title, Integer quantity, Integer carProductId) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.title = title;
		this.quantity = quantity;
		this.carProductId = carProductId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getCarProductId() {
		return carProductId;
	}
	public void setCarProductId(Integer carProductId) {
		this.carProductId = carProductId;
	}
	@Override
	public String toString() {
		return "Orderline [id=" + id + ", orderId=" + orderId + ", title=" + title + ", quantity=" + quantity
				+ ", carProductId=" + carProductId + "]";
	}
	
	

}
