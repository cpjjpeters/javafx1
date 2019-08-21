package be.ipeters.brol.cpbelcar.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipeters.brol.cpbelcar.domain.Order;
import be.ipeters.brol.cpbelcar.domain.Orderline;
import be.ipeters.brol.cpbelcar.mappers.OrderMapper;

@Service
public class OrderService implements CrudService<Order, Integer>{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderlineService orderlineService;
	
	private Orderline orderline;
	public int prefabOrderId;
	@Override
	public void save(Order entity) {
		/*
		 * maak een voorlopig order hou de id bij
		 * 
		 */
		
		orderMapper.insert(entity);
		//postman, zet input van order met orderlien klaar
	}
	public void save2(Order entity) {
		/*
		 * maak een voorlopig order hou de id bij
		 * maak orderlijnen en vul hier bovenstaande id in als orderId
		 * Op het einde een update doen van het order
		 * 
		 */
		// create prefab order
		prefabOrderId=prefabOrderCreation().getId();
		
		// oproep create orderline
		orderlineService.save(orderline);
		
		// oproep validate
		
		orderMapper.insert(entity);
		//postman, zet input van order met orderlien klaar
	}
	@Override
	public Order findById(Integer key) {
		return orderMapper.findById(key);
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
	private void orderValidation(Order order) {
		// check minstens 1 orderline
		
	}
	private Order prefabOrderCreation() {
		Order prefab = new Order(); //(1, "prefab", 1, LocalDate.now(), LocalDate.now(),
				//1, 2, 3, "dummy", 5.0);
		prefab.setTypeOrder("prefab");
		prefab.setOrderDate(LocalDateTime.now());
		prefab.setDeliveryDate(LocalDate.now());
		prefab.setCustomerId(1);
		prefab.setSupplierId(2);
		prefab.setEmployeeId(3);
		prefab.setStatus("dummy");
		prefab.setPercentage(0);
		return prefab;
	}
	

}
