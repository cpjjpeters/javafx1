package be.ipeters.brol.cpbelcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.CarProduct;
import be.ipeters.brol.cpbelcar.services.CarProductService;

@RestController
@RequestMapping("/carproduct")
public class CarProductController {

	@Autowired
	private CarProductService carProductService;
	
	@GetMapping("/all")
	public List<CarProduct> findAll(){
		return carProductService.findAll();
	}
	@GetMapping("/{id}")
	public CarProduct findById(@PathVariable(value="id") Integer id){
		return carProductService.findById(id);
	}
	@GetMapping("/all/{id}")
	public List<CarProduct>  getAllProductsWithCarId(@PathVariable(value="id") Integer id){
		return carProductService.findAllById(id);
	}
	
	@PostMapping("/create")
	public void createCarProduct(@RequestBody CarProduct carProduct) {
		System.out.println("create: " +carProduct);
		carProductService.save(carProduct);
	}
	@PostMapping("/update")
	public void updateCarProduct(@RequestBody CarProduct carProduct) {
		
		carProductService.update(carProduct);
		
	}
	@PostMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		carProductService.deleteById(id);
	}

}
