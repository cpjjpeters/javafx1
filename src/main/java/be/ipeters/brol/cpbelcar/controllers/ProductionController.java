package be.ipeters.brol.cpbelcar.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.Production;
import be.ipeters.brol.cpbelcar.services.ProductionService;



@RestController
@RequestMapping("/production")
public class ProductionController {
	Logger logger= LoggerFactory.getLogger(ProductionController.class);
	@Autowired
	private ProductionService productionService;
	
	@RequestMapping("/")
	public String index() {
	    logger.trace("A TRACE Message");
	    logger.debug("A DEBUG Message");
	    logger.info("An INFO Message");
	    logger.warn("A WARN Message");
	    logger.error("An ERROR Message");

	    return "Howdy! Check out the Logs to see the output...";
	}
	
	@RequestMapping(value = "/all")
	public List<Production> findAll(){
//		logger.error("An ERROR Production findall");
		return productionService.findAll();
	}
	@GetMapping("/{id}")
	public Production findById(@PathVariable(value="id") Integer id){
		return productionService.findById(id);
	}
	
	@PostMapping("/create")
	public void createProduction(@RequestBody Production production) {
		System.out.println("create: " +production);
		productionService.save(production);
	}
	@PostMapping("/update")
	public void updateProduction(@RequestBody Production production) {
		
		productionService.update(production);
		
	}
	@PostMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		productionService.deleteById(id);
	}
}
