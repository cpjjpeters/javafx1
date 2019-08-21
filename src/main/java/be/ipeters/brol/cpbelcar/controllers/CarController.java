package be.ipeters.brol.cpbelcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.Car;
import be.ipeters.brol.cpbelcar.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
		@Autowired
		private CarService carService;
		
		@RequestMapping(value = "/all")
		public List<Car> findAll(){
			return carService.findAll();
		}
		@GetMapping("/{id}")
		public Car findById(@PathVariable(value="id") Integer id){
			return carService.findById(id);
		}
		
		@PostMapping("/create")
		public void createCar(@RequestBody Car car) {
			System.out.println("create: " +car);
			carService.save(car);
		}
		@PostMapping("/updatestockminone")
		public void updatestockminone(@RequestBody Car car) {
				carService.updateStockMinOne(car);		
		}
		@PostMapping("/updatestockplusone")
		public void updatestockplusone(@RequestBody Car car) {
				carService.updateStockMinOne(car);
			
		}
		@PostMapping("/update")
		public void updateCar(@RequestBody Car car) {
			
			carService.update(car);
			
		}
		@PostMapping("/delete/{id}")
		public void deleteById(@PathVariable(value="id") Integer id) {
			carService.deleteById(id);
		}
}
