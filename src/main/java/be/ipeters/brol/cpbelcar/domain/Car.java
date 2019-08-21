package be.ipeters.brol.cpbelcar.domain;

public class Car {
	private Integer id;
	private String name;
	private Integer stock;
	
	
	public Car(Integer id, String name, Integer stock) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
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
	@Override
	public String toString() {
		return "Car [name=" + name + ", stock=" + stock + "]";
	}
	
	

}
