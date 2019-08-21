package be.ipeters.brol.cpbelcar.domain;

import java.time.LocalDateTime;

public class Product {
	private Integer id;
	private String name;
	private Integer stock;
	private String stockLocation;
	private LocalDateTime productionTime;
	
	private Integer supplierId;
	private double consumerPrice;
	private double productionPrice;
	private String statut;
	private String color;
	private boolean roofWindow;
	private boolean leatherSeats;
	
	
	public Product(Integer id, String name, Integer stock, String stockLocation, LocalDateTime productionTime,
			Integer supplierId, double consumerPrice, double productionPrice, String statut, String color,
			boolean roofWindow, boolean leatherSeats) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.stockLocation = stockLocation;
		this.productionTime = productionTime;
		this.supplierId = supplierId;
		this.consumerPrice = consumerPrice;
		this.productionPrice = productionPrice;
		this.statut = statut;
		this.color = color;
		this.roofWindow = roofWindow;
		this.leatherSeats = leatherSeats;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public LocalDateTime getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(LocalDateTime productionTime) {
		this.productionTime = productionTime;
	}


	public String getStockLocation() {
		return stockLocation;
	}

	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public double getConsumerPrice() {
		return consumerPrice;
	}

	public void setConsumerPrice(double consumerPrice) {
		this.consumerPrice = consumerPrice;
	}

	public double getProductionPrice() {
		return productionPrice;
	}

	public void setProductionPrice(double productionPrice) {
		this.productionPrice = productionPrice;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isRoofWindow() {
		return roofWindow;
	}

	public void setRoofWindow(boolean roofWindow) {
		this.roofWindow = roofWindow;
	}

	public boolean isLeatherSeats() {
		return leatherSeats;
	}

	public void setLeatherSeats(boolean leatherSeats) {
		this.leatherSeats = leatherSeats;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", stock=" + stock + ", stockLocation=" + stockLocation
				+ ", supplierId=" + supplierId + ", consumerPrice=" + consumerPrice + ", productionPrice="
				+ productionPrice + ", statut=" + statut + ", color=" + color + ", roofWindow=" + roofWindow
				+ ", leatherSeats=" + leatherSeats + "]";
	}
	
	

}
