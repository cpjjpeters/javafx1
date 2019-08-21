package be.ipeters.brol.cpbelcar.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipeters.brol.cpbelcar.domain.Car;
import be.ipeters.brol.cpbelcar.domain.Order;
import be.ipeters.brol.cpbelcar.domain.Orderline;
import be.ipeters.brol.cpbelcar.domain.Production;
import be.ipeters.brol.cpbelcar.mappers.OrderMapper;

@Service
public class SupplierOrderService implements CrudService<Order, Integer> {
	private Integer partStock = 0;
	private Integer orderPrice = 0;
	private Orderline orderline;
	private Orderline supplierOrderline;
	private Order supplierOrder;
	private Production production;
	private int carProductId;
	private int carPartId;
	private int carId;
	public int prefabOrderId;
	public List<Orderline> orderlineList;

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderlineService orderlineService;
	@Autowired
	private CarService carService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductionService productionService;

	@Override
	public void save(Order entity) {
		orderMapper.insert(entity);
		// this supplierorder is created because parts are needed
	}

	@Override
	public Order findById(Integer key) {
		return orderMapper.findById(key);
	}

	// find the orderline when you have the orderId
	public Orderline findOrderlineByOrderId(Integer key) {
		return orderlineService.findOrderlineByOrderId(key);
	}

	@Override
	public List<Order> findAll() {
		return orderMapper.findAll();
	}

	@Override
	public void deleteById(Integer key) {
		orderMapper.deleteById(key);
	}

	@Override
	public void update(Order entity) {
		orderMapper.update(entity);
	}

	private Order prefabSupplierOrderCreation() {
		System.out.println("prefabSupplierOrderCreation");
		Order prefab = new Order(); // (1, "prefabSupplierOrder", 1, LocalDate.now(), LocalDate.now(),
		// 1, 1, 7, "dummy", 0.0); // customerId 1 = Belcar, supplierId 1 = Belcar,
		// employeeID 7 = First Avenger, "dummy", percentage 0%
		prefab.setTypeOrder("prefabSupplierOrder");
		prefab.setOrderDate(LocalDateTime.now());
		prefab.setDeliveryDate(LocalDate.now());
		prefab.setCustomerId(1);
		prefab.setSupplierId(1);
		prefab.setEmployeeId(7);
		prefab.setStatus("dummy");
		prefab.setPercentage(0);
		this.save(prefab);
		System.out.println("prefabSupplierOrder with id: " + prefab.getId() + " saved");
		return prefab;
	}

	public void createSupplierOrderViaPartId(Integer partID) {
		System.out.println("createSupplierOrderViaPartId "+LocalDateTime.now());
		// create dummy
		supplierOrder = prefabSupplierOrderCreation();
		System.out.println("supplierOrder.getId()=" + supplierOrder.getId());
		orderline = new Orderline(null, supplierOrder.getId(), productService.findById(partID).getName(), 1, partID);
		orderlineService.save(orderline);
		System.out.println("orderline nr " + orderline.getId() + " created");
		orderlineList = new ArrayList<Orderline>();
		orderlineList.add(orderline);
		for (Orderline ol : orderlineList) {
			System.out.println(ol.getTitle());
		}

		try {
			supplierOrder.setOrderlineList(orderlineList);
		} catch (Exception e) {
			System.out.println("supplierOrder.setOrderlineList(orderlineList)");
			e.printStackTrace();
		}
		supplierOrder.setTypeOrder("SupplierOrder");
		supplierOrder.setStatus("active");
		orderService.update(supplierOrder);
		// add line to Production
		startProduction();
		productionService.save(production);
		System.out.println("createSupplierOrderViaPartId for " + production.getDescription() + "finished");
	}
	public void createSupplierOrderViaCarId(Integer carID) {
		System.out.println("createSupplierOrderViaCarId "+LocalDateTime.now());
		// create dummy
		supplierOrder = prefabSupplierOrderCreation();
		System.out.println("supplierOrder.getId()=" + supplierOrder.getId());
		orderline = new Orderline(null, supplierOrder.getId(), carService.findById(carID).getName(), 1, carID);
		orderlineService.save(orderline);
		System.out.println("orderline nr " + orderline.getId() + " created");
		orderlineList = new ArrayList<Orderline>();
		orderlineList.add(orderline);
		for (Orderline ol : orderlineList) {
			System.out.println(ol.getTitle());
		}

		try {
			supplierOrder.setOrderlineList(orderlineList);
		} catch (Exception e) {
			System.out.println("supplierOrder.setOrderlineList(orderlineList)");
			e.printStackTrace();
		}
		supplierOrder.setTypeOrder("SupplierOrder");
		supplierOrder.setStatus("active");
		orderService.update(supplierOrder);
		// add line to Production
		startProduction();
		productionService.save(production);
		System.out.println("createSupplierOrderViaCarId for " + production.getDescription() + "finished");
	}

	private void startProduction() {
		System.out.println("startProduction via SupplierOrder " +LocalDateTime.now());
		// fill out the fields orderId, orderlineId, description, lastUpdate
		production = new Production(1, 1, 1,
				"created supplierOrder " + supplierOrder.getId() + ", with orderline " + orderline.getId(),
				LocalDate.now());
		executeSupplierOrder(supplierOrder.getId());

	}
	private void startCarProduction() {
		System.out.println("startCarProduction via SupplierOrder " +LocalDateTime.now());
		// fill out the fields orderId, orderlineId, description, lastUpdate
		production = new Production(1, 1, 1,
				"created supplierOrder " + supplierOrder.getId() + ", with orderline " + orderline.getId(),
				LocalDate.now());
		executeSupplierOrder(supplierOrder.getId());

	}

	private void executeSupplierOrder(Integer orderId) { // ofwel hier suporderId of orderlineId
		// get the info
		System.out.println("executeSupplierOrder");
		supplierOrderline = findOrderlineByOrderId(orderId);
		carProductId = supplierOrderline.getCarProductId();
		partStock = productService.getProductStock(carProductId);
		System.out.println("partStock="+partStock+", for part "+carProductId+", which is "+productService.findById(carProductId));
		partStock++;
		// execute the orderline
		productService.updateStockPlusOne(carProductId);

		// update status SupplierOrder
//		System.out.println("supplierOrder.getStatus()="+supplierOrder.getStatus());
		supplierOrder.setTypeOrder("SupplierPartOrder");
		orderPrice = (int)Math.round(productService.findById(carProductId).getConsumerPrice());
		supplierOrder.setPrice(orderPrice);
		supplierOrder.setStatus("Produced");
		orderService.update(supplierOrder);
		System.out.println("supplierOrder finished, part produced!");
	}

	private void executeCarSupplierOrder(Integer orderId) { // 
		// get the info
		System.out.println("executeCarSupplierOrder ");
		supplierOrderline = findOrderlineByOrderId(orderId);
		carProductId = supplierOrderline.getCarProductId();
		
		partStock = productService.getProductStock(carProductId);
//		System.out.println("partStock="+partStock+", for part "+carProductId+", which is "+productService.findById(carProductId));
		partStock++;
		// execute the orderline
		productService.updateStockPlusOne(carProductId);

		// update status SupplierOrder
//		System.out.println("supplierOrder.getStatus()="+supplierOrder.getStatus());
		supplierOrder.setTypeOrder("SupplierCarOrder");
		supplierOrder.setStatus("Produced");
		orderService.update(supplierOrder);
		System.out.println("supplierOrder finished, part produced!");
	}
	
	public void receiveCarOrder(Order order) {
		// getting information from Postman
		System.out.println("receiveCarOrder SupplierOrderService "+LocalDateTime.now());
		order.setOrderDate(LocalDateTime.now());
		supplierOrder = order;
		supplierOrder.setTypeOrder("SupplierOrder for car ");
		orderService.save(supplierOrder);
		System.out.println("receiveSupplierOrder with id: " + supplierOrder.getId() + " saved");
		orderlineList = supplierOrder.getOrderlineList();
		for (Orderline ol : orderlineList) {
			System.out.println(ol.getTitle());
			carId = ol.getCarProductId();
		}
		Orderline orderline = new Orderline(1, supplierOrder.getId(), carService.findById(carId).getName(), 1,
				carProductId);
		orderlineService.save(orderline);
		orderService.save(supplierOrder);
		Car car = carService.findById(carId);
		System.out.println("-- so far, so good -- carId="+carId+", "+carService.findById(carId).getName());
		carService.save(car);
		supplierOrder.setStatus("Treated");
		orderService.save(supplierOrder);
//		System.out.println("MORE TODO");
	}
	//--------------
	public void receivePartOrder(Order order) {
		// getting information from Postman
		System.out.println("receivePartOrder SupplierOrderService "+LocalDateTime.now());
		order.setOrderDate(LocalDateTime.now());
		supplierOrder = order;
		orderService.save(supplierOrder);
		System.out.println("receiveSupplierOrder with id: " + supplierOrder.getId() + " saved");
		orderlineList = supplierOrder.getOrderlineList();
		for (Orderline ol : orderlineList) {
			System.out.println(ol.getTitle());
//			carProductId = ol.getCarProductId();
			carPartId = ol.getCarProductId();
		}
		Orderline orderline = new Orderline(1, supplierOrder.getId(),productService.findById(carPartId).getName() ,1,carPartId);
		orderlineService.save(orderline);
		executeSupplierOrder(supplierOrder.getId());
		production = new Production(1, supplierOrder.getId(), orderline.getId(),
				"executed supplierOrder " + supplierOrder.getId() + ", orderline " + orderline.getId(),
				LocalDate.now());
		productionService.save(production);
		System.out.println("receivePartOrder ended");
	}

}
