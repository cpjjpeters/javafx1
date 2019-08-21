package be.ipeters.brol.cpbelcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.Order;
import be.ipeters.brol.cpbelcar.services.CustomerOrderService;
import be.ipeters.brol.cpbelcar.services.OrderService;
@RestController
@RequestMapping("/customerorders")
public class CustomerorderController {

	@Autowired
	private CustomerOrderService customerorderService;
	@Autowired
	private OrderService orderService;
	@GetMapping("/all")
	public List<Order> findAll(){
		return orderService.findAll();
	}
	@GetMapping("/{id}")
	public Order findById(@PathVariable(value="id") Integer id){
		return orderService.findById(id);
	}
	@PostMapping("/treat/{id}")
	public void executeOrder(@PathVariable(value="id") Integer id) {
		System.out.println("treat: " +id);
		customerorderService.treatActiveCustomerOrderNotFinished(id);
	}
	@PostMapping("/create")
	public void createOrder(@RequestBody Order order) {
		System.out.println("create: " +order);
		orderService.save(order);
	}
	@PostMapping("/receive")
	public void receiveOrder(@RequestBody Order order) {
		System.out.println("receive: " +order);
		customerorderService.receiveOrder(order);
	}
	@PostMapping("/update")
	public void updateOrder(@RequestBody Order order) {
		
		orderService.update(order);
		
	}
	@PostMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		orderService.deleteById(id);
	}

}
