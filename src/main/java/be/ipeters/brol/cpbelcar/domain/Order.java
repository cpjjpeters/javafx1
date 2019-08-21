package be.ipeters.brol.cpbelcar.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private Integer id;
	private String typeOrder;
	private Integer price;
	private LocalDateTime orderDate;
	private LocalDate deliveryDate;
	private Integer customerId;
	private Integer supplierId;
	private Integer employeeId;
	private String status;
	private double percentage;
	private List<Orderline> orderlineList;
	
//	blijkbaar kan je geen constructor gebruiken of anders rare fauten ;-) ou
//	public Order(Integer id, String typeOrder, Integer price, LocalDate orderDate, LocalDate deliveryDate,
//			Integer customerId, Integer supplierId, Integer employeeId, String status, double percentage,
//			List<Orderline> orderlineList) {
//		super();
//		this.id = id;
//		this.typeOrder = typeOrder;
//		this.price = price;
//		this.orderDate = orderDate;
//		this.deliveryDate = deliveryDate;
//		this.customerId = customerId;
//		this.supplierId = supplierId;
//		this.employeeId = employeeId;
//		this.status = status;
//		this.percentage = percentage;
//		this.orderlineList = orderlineList;
//	}
//	public Order(Integer id, String typeOrder, Integer price, LocalDate orderDate, LocalDate deliveryDate,
//			Integer customerId, Integer supplierId, Integer employeeId, String status, double percentage) {
//		super();
//		this.id = id;
//		this.typeOrder = typeOrder;
//		this.price = price;
//		this.orderDate = orderDate;
//		this.deliveryDate = deliveryDate;
//		this.customerId = customerId;
//		this.supplierId = supplierId;
//		this.employeeId = employeeId;
//		this.status = status;
//		this.percentage = percentage;
//	}
	public List<Orderline> getOrderlineList() {
		return orderlineList;
	}

	public void setOrderlineList(List<Orderline> orderlineList) {
		this.orderlineList = orderlineList;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeOrder() {
		return typeOrder;
	}

	public void setTypeOrder(String typeOrder) {
		this.typeOrder = typeOrder;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime localDate) {
		this.orderDate = localDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		
		return "Order [id=" + id + ", typeOrder=" + typeOrder + ", price=" + price + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", customerId=" + customerId + ", supplierId=" + supplierId
				+ ", employeeId=" + employeeId + ", status=" + status + ", percentage=" + percentage
				+ ", orderlineList size=" +orderlineList.size()
				+ "| big]";
	}

	
	
	

}
