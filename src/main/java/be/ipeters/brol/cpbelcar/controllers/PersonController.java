package be.ipeters.brol.cpbelcar.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ipeters.brol.cpbelcar.domain.Person;
import be.ipeters.brol.cpbelcar.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping("/all")
	public List<Person> findAll(){
		return personService.findAll();
	}
	@GetMapping("/{id}")
	public Person findById(@PathVariable(value="id") Integer id){
		return personService.findById(id);
	}
	
	@PostMapping("/create")
	public void createPerson(@RequestBody Person person) {
		System.out.println("create: " +person);
		personService.save(person);
	}
	@PostMapping("/update")
	public void updatePerson(@RequestBody Person person) {
		
		personService.update(person);
		
	}
	@PostMapping("/delete/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		personService.deleteById(id);
	}

}
