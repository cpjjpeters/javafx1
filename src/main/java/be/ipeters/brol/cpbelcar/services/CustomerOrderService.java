package be.ipeters.brol.cpbelcar.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipeters.brol.cpbelcar.Cpbelcar;
import be.ipeters.brol.cpbelcar.domain.Order;
import be.ipeters.brol.cpbelcar.domain.Orderline;
import be.ipeters.brol.cpbelcar.domain.Production;
import be.ipeters.brol.cpbelcar.mappers.OrderMapper;
import ch.qos.logback.classic.Logger;

@Service
public class CustomerOrderService { //implements CrudService<Order, Integer> {
	private final static Logger log = (Logger) LoggerFactory.getLogger(CustomerOrderService.class);
	private boolean isCarCreationPossible = false;
	private Integer partStock = 0;
	private Integer carStock = 0;
	private Integer orderPrice = 0;
	private Double carPrice = 0.0;
	private Orderline orderline;
	private Orderline customerOrderline;
	private Order customerOrder;
	private Production production;
	private Integer carProductId;
	private Integer carId;
	private Integer orderlineId;
	public Integer prefabOrderId;
	public List<Orderline> orderlineList;

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderlineService orderlineService;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SupplierOrderService supplierorderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductionService productionService;
	
//	public void createCustomerOrderViaCarId(Integer carID) {
//		System.out.println("createCustomerOrderViaCarId");
//		// create dummy
//		customerOrder = prefabCustomerOrderCreation();
//		System.out.println("supplierOrder.getId()=" + customerOrder.getId());
//		orderline = new Orderline(null, customerOrder.getId(), carService.findById(carID).getName(), 1, carID);
//		orderlineService.save(orderline);
//		System.out.println("orderline nr " + orderline.getId() + " created");
//		orderlineList = new ArrayList<Orderline>();
//		orderlineList.add(orderline);
//		for (Orderline ol : orderlineList) {
//			System.out.println(ol.getTitle());
//		}
////		System.out.println("pre-updating customerOrder "+ customerOrder.getId());
//		try {
//			customerOrder.setOrderlineList(orderlineList);
//		} catch (Exception e) {
//			System.out.println("customerOrder.setOrderlineList(orderlineList)");
//			e.printStackTrace();
//		}
//		customerOrder.setTypeOrder("customerOrder");
//		customerOrder.setStatus("active");
//		orderService.update(customerOrder);
//		// add line to Production
//		startProduction(customerOrder, orderline.getId());
//		System.out.println("createCustomerOrderViaCarId almost finished");
//		executeCustomerOrder(customerOrder, orderline.getId()) ;
//		System.out.println("createCustomerOrderViaCarId finished");
//	}

	private Order prefabCustomerOrderCreation() {
		System.out.println("start prefabCustomerOrderCreation");
		Order prefab = new Order(); // (1, "prefab", 1, LocalDate.now(), LocalDate.now(),
		// 1, 2, 3, "dummy", 5.0);
		prefab.setTypeOrder("prefabCustomerOrder");
		prefab.setOrderDate(LocalDateTime.now());
		prefab.setDeliveryDate(LocalDate.now());
		prefab.setCustomerId(1);
		prefab.setSupplierId(2);
		prefab.setEmployeeId(3);
		prefab.setStatus("dummy");
		prefab.setPercentage(0);
		System.out.println(prefab.toString());
		orderService.save(prefab);
		System.out.println("prefabCustomerOrder with id: " + prefab.getId() + " saved");

		return prefab;
	}

	public void receiveOrder(Order order) { // voor mij is een CustomerOrder een bestelling van 1 auto
		// getting information from Postman
		System.out.println("receiveOrder CustomerOrderService "+LocalDateTime.now());
		order.setOrderDate(LocalDateTime.now());
		customerOrder=order;
		orderService.save(customerOrder);
		System.out.println("prefabCustomerOrder with id: " + customerOrder.getId() + " saved");
		orderlineList = customerOrder.getOrderlineList();

		for (Orderline ol : orderlineList) {
			System.out.println(ol.getTitle());
			carProductId = ol.getCarProductId();
		}
		Orderline orderline = new Orderline(1, customerOrder.getId(), carService.findById(carProductId).getName(), 1,
				carProductId);
		orderlineService.save(orderline);
		carPrice = carService.calculateCarOrderPrice(carProductId);
		orderPrice = (int)Math.round(carPrice);
		System.out.println(orderPrice +"=(int)Math.round("+carPrice+").");
		customerOrder.setPrice(orderPrice);
		customerOrder.setTypeOrder("CustomerOrder");
		customerOrder.setStatus("active");
		orderService.update(customerOrder);
		// start manufacturing the car
		System.out.println("receiveOrder orderService.update(customerOrder) "+customerOrder.getTypeOrder()+" with ordernumber "+customerOrder.getId());
		// add line to Production
		startProduction(customerOrder, orderline.getId());
		System.out.println("receiveOrder almost finished, need to executeCustomerOrder");
		executeCustomerOrder(customerOrder, orderline.getId()) ;
		System.out.println("receiveOrder finished");

	}

	private void startProduction(Order customerOrder, Integer orderlineId) {
		System.out.println("startProduction via CustomerOrder (actually kind of logging)" + customerOrder.getId());
		// fill out the fields orderId, orderlineId, description, lastUpdate
		try {
			production = new Production(1, customerOrder.getId(), orderlineId,
					"created customerOrder " + customerOrder.getId() + ", with orderline " + orderlineId,
					LocalDate.now());
		} catch (Exception e) {
			System.out.println("cannot make new Production");
			e.printStackTrace();
		}
		System.out.println("startProduction - description: "+production.getDescription());
		productionService.save(production);
	}

	private void executeCustomerOrder(Order customerOrder, Integer orderlineId){
		System.out.println("executeCustomerOrder " + customerOrder.getId());
		//customerOrderline = findOrderlineByOrderId(orderId);
		carProductId = orderlineService.findById(orderlineId).getCarProductId();
		
//		if the car is in stock, we complete the CustomerOrder, else we produce the car (if all parts are available) else we order parts
		carStock=carService.getProductStock(carProductId);
		System.out.println("carStock="+carStock+", for car "+carProductId+". ");
		if(carStock>0) {
			System.out.println("Car in stock");
//			carStock-- and update CustomerOrder
			carService.updateStockMinOne(carService.findById(carProductId));
			customerOrder.setStatus("Finished");
			orderService.update(customerOrder);
			System.out.println("customerOrder finished, car ordered/produced/delivered!");
		}else  // else we produce the car (if all parts are available) else we order parts
		{
			System.out.println("start supplierOrder for the car");
//			gaan we voor receive of createSupplierOrderViaCarId ??
//			supplierorderService.createSupplierOrderViaCarId(carProductId);  // here we need to give the partnumber of product, not of car
			supplierorderService.receiveCarOrder(customerOrder);
			System.out.println("customerOrder resulting in car ordered/produced!");
			System.out.println("we could check order not Finished...");
		}
	}

	private void executeActiveCustomerOrder(Order customerOrder, Integer orderlineId){
		System.out.println("executeActiveCustomerOrder " + customerOrder.getId());
		//customerOrderline = findOrderlineByOrderId(orderId);
		carProductId = orderlineService.findById(orderlineId).getCarProductId();
		
		carStock=carService.getProductStock(carProductId);
		System.out.println("carStock="+carStock+", for car "+carProductId+". ");
		if(carStock==0) {
			System.out.println("Car not in stock, which is OK");
//			
			
		}else  // else we should not be here!
		{
			System.out.println("we should not be here!");
			}
//		check if all parts are now present
		carService.checkPartsAvailable(customerOrder.getId());
		if (isCarCreationPossible) {
			// verminder de stock van elk onderdeel
			carId=customerOrder.getId();
			carService.adaptPartStock( carId);
			carService.updateStockPlusOne(carService.findById(carId));
			carService.filloutProduction();
			productionService.save(production);
			System.out.println("We have: "+production.getDescription());
			carService.updateStockPlusOne(carService.findById(carProductId));
			customerOrder.setStatus("Finished");
			orderService.update(customerOrder);
			System.out.println("customerOrder finished, car ordered/produced/delivered!");
		}else {
			System.out.println("ai ai, still not all parts present");
			executeCustomerOrder(customerOrder, orderlineId);
		}
		
	}
	public void treatActiveCustomerOrderNotFinished(Integer orderId) {
		System.out.println("treatActiveCustomerOrderNotFinished nr "+ orderId +" at "+LocalDateTime.now());
		customerOrder=orderService.findById(orderId);
		orderlineId=orderlineService.findOrderlineByOrderId(orderId).getId();
		String oStatus=customerOrder.getStatus().trim();
		if (oStatus.equals("active")) { // should be the case
			executeActiveCustomerOrder(customerOrder, orderlineId);
		}else {
			System.out.println("Order with id "+ orderId + " is not in status active, but in status |"+customerOrder.getStatus().trim()+"|");
		}
	}

}
