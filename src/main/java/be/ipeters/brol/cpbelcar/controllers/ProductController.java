package be.ipeters.brol.cpbelcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.Product;
import be.ipeters.brol.cpbelcar.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public List<Product> findAll(){
		return productService.findAll();
	}
	@GetMapping("/{id}")
	public Product findById(@PathVariable(value="id") Integer id){
		return productService.findById(id);
	}
	
	@GetMapping("/all/{id}")
	public List<Product> findAllById(@PathVariable(value="id") Integer id){
		return productService.findAllById(id);
	}
	
	@PostMapping("/create")
	public void createProduct(@RequestBody Product product) {
		System.out.println("create: " +product);
		productService.save(product);
	}
	@PostMapping("/update")
	public void updateProduct(@RequestBody Product product) {
		System.out.println("/update::product="+product.toString());
		productService.update(product);
		
	}
	@PostMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		productService.deleteById(id);
	}

}
