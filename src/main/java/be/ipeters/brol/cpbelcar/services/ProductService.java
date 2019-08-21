package be.ipeters.brol.cpbelcar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipeters.brol.cpbelcar.domain.Car;
import be.ipeters.brol.cpbelcar.domain.Product;
import be.ipeters.brol.cpbelcar.mappers.ProductMapper;

@Service
public class ProductService implements CrudService<Product, Integer>{
	private Integer partStock = 0;
	private Integer partId = 0;
	private Product part;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public void save(Product entity) {
		productMapper.insert(entity);
		
	}

	@Override
	public Product findById(Integer key) {
		return productMapper.findById(key);
	}

	@Override
	public List<Product> findAll() {
		return productMapper.findAll();
	}
	public List<Product> findAllById(Integer key) {
		return productMapper.findAllById(key);
	}

	@Override
	public void deleteById(Integer key) {
		productMapper.deleteById(key);
		
	}

	@Override
	public void update(Product entity) {
		productMapper.update(entity);
	}
	
	public void updateProductStock(Integer key) {
//		System.out.println("updateProductStock(Integer key)");
		productMapper.updateProductStock(key,productMapper.getProductStock(key));
//		System.out.println("updateProductStock(Integer key)"+productMapper.getProductStock(key));
	}
	public void updateStockMinOne(Integer key) {
		partStock=productMapper.getProductStock(key);
//		System.out.println("ProductService-partStock="+partStock);
		partStock--;
//		System.out.println("ProductService-partStock="+partStock);
		part=this.findById(key);
		part.setStock(partStock);
//		System.out.println("part.getStock: "+part.getStock()); 
		productMapper.updateStock(part);
		System.out.println("productMapper.updateStock() MinOne "+ key +" part.getStock: "+part.getStock()); 
		
	}

	public void updateStockPlusOne(Integer key) {
		partStock=productMapper.getProductStock(key);
//		System.out.println("ProductService-partStock="+partStock);
		partStock++;
//		System.out.println("ProductService-partStock="+partStock);
		part=this.findById(key);
		part.setStock(partStock);
		productMapper.updateStock(part);
		System.out.println("productMapper.updateStock() PlusOne "+ key +" part.getStock: "+part.getStock()); 
		
	}
	public Integer getProductStock(Integer key) {
		return productMapper.getProductStock(key);
	}

}
