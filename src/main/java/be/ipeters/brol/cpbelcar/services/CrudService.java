package be.ipeters.brol.cpbelcar.services;

import java.util.List;


public interface CrudService <T,ID>{
	void save(T entity);
	T findById(ID key);
	List<T> findAll();
	//void delete(T entity);
	void deleteById(ID key);
	void update(T entity);
}
