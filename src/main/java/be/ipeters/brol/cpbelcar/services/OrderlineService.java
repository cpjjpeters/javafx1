package be.ipeters.brol.cpbelcar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipeters.brol.cpbelcar.domain.Orderline;
import be.ipeters.brol.cpbelcar.mappers.OrderlineMapper;

@Service
public class OrderlineService implements CrudService<Orderline, Integer>{
	@Autowired
	private OrderlineMapper orderlineMapper;

	@Override
	public void save(Orderline entity) {
		System.out.println("OrderlineService - insert");
		orderlineMapper.insert(entity);
		
	}

	@Override
	public Orderline findById(Integer key) {
		return orderlineMapper.findById(key);
	}

	@Override
	public List<Orderline> findAll() {
		return orderlineMapper.findAll();
	}

	@Override
	public void deleteById(Integer key) {
		orderlineMapper.deleteById(key);
		
	}

	@Override
	public void update(Orderline entity) {
		System.out.println("OrderlineService - update");
		orderlineMapper.update(entity);
		
	}

	public Orderline findOrderlineByOrderId(Integer key) {
			return orderlineMapper.findOrderlineByOrderId(key);
	}

}
