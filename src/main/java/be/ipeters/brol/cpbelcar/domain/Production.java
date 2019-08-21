package be.ipeters.brol.cpbelcar.domain;

import java.time.LocalDate;

public class Production {
	private Integer id;
	private Integer orderId;
	private Integer orderlineId;
	private String description;
	private LocalDate lastUpdate;
	
	
	public Production(Integer id, Integer orderId, Integer orderlineId, String description, LocalDate lastUpdate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderlineId = orderlineId;
		this.description = description;
		this.lastUpdate = lastUpdate;
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
	public Integer getOrderlineId() {
		return orderlineId;
	}
	public void setOrderlineId(Integer orderlineId) {
		this.orderlineId = orderlineId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "Production [id=" + id + ", orderId=" + orderId + ", orderlineId=" + orderlineId + ", description="
				+ description + ", lastUpdate=" + lastUpdate + "]";
	}
	
	

}
