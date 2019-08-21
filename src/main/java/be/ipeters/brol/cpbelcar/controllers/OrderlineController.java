package be.ipeters.brol.cpbelcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.Orderline;
import be.ipeters.brol.cpbelcar.services.OrderlineService;


@RestController
@RequestMapping("/orderlines")
public class OrderlineController {
	@Autowired
	private OrderlineService orderlineService;

	@GetMapping("/all")
	public List<Orderline> findAll(){
		return orderlineService.findAll();
	}
	@GetMapping("/{id}")
	public Orderline findById(@PathVariable(value="id") Integer id){
		return orderlineService.findById(id);
	}
	
	@PostMapping("/create")
	public void createOrder(@RequestBody Orderline orderline) {
		System.out.println("create: " +orderline);
		orderlineService.save(orderline);
	}
	@PostMapping("/update")
	public void updateOrder(@RequestBody Orderline orderline) {
		
		orderlineService.update(orderline);
		
	}
	@PostMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		orderlineService.deleteById(id);
	}

}