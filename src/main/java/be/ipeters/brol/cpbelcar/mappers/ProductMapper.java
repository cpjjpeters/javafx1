package be.ipeters.brol.cpbelcar.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import be.ipeters.brol.cpbelcar.domain.Car;
import be.ipeters.brol.cpbelcar.domain.Product;

@Repository
@Mapper

public interface ProductMapper {
	@Select("select * from product")
	@Results(id = "productResult", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"), 
			@Result(property = "stock", column = "stock"),
			@Result(property = "stockLocation", column = "stock_location"),
			@Result(property = "productionTime", column = "production_time"),
			@Result(property = "supplierId", column = "supplier_id"),
			@Result(property = "consumerPrice", column = "consumer_price"),
			@Result(property = "productionPrice", column = "production_price"),
			@Result(property = "statut", column = "statut"),
			@Result(property = "color", column = "color"),
			@Result(property = "roofWindow", column = "roofwindow"),
			@Result(property = "leatherSeats", column = "leatherseats")
	})
	List<Product> findAll();
//----------------------------------------------------------
	@Select  ("select * from product where name=#{prefix}") // 
	@ResultMap("productResult")
	List<Product> getAllProductsWithNameStartingBy(String prefix);
	//----------------------------------------------------------
	
	@Select("select * from product where id=#{id}")
	@ResultMap("productResult")
	Product findById(Integer key);
	//----------------------------------------------------------
	
	@Insert("INSERT into product (name, stock, stock_location, production_time, supplier_id, consumer_price, production_price, statut, color, roofwindow, leatherseats)" +
            "VALUES          (#{name},#{stock},#{stockLocation},#{productionTime},#{supplierId},#{consumerPrice},#{productionPrice},#{statut},#{color},#{roofWindow},#{leatherSeats})")
    void insert(Product entity);
	//----------------------------------------------------------
	
	@Delete 	("delete from product where id=#{id}")
	void deleteById(Integer key);
	//----------------------------------------------------------
	
	@Update("UPDATE product set "
            + "name = #{name}, "
            + "stock = #{stock}, "
            + "stock_location = #{stockLocation}, "
            + "production_time = #{productionTime}, "
            + "supplier_id = #{supplierId}, "
            + "consumer_price = #{consumerPrice}, "
            + "production_price = #{productionPrice}, "
            + "statut = #{statut}, "
            + "color = #{color}, "
            + "roofwindow = #{roofWindow}, "
            + "leatherseats = #{leatherSeats} "
      + "WHERE product.id = #{id}")
	void update(Product product);
	//----------------------------------------------------------
	// misschien niet meer nodig !!
		@Update("UPDATE product set "        
	            + "stock = #{partStock} "
	      + "WHERE product.id = #{key}")
		void updateProductStock(Integer key, Integer partStock);
	//----------------------------------------------------------
		@Update("UPDATE product set "
		           
            + "stock = #{stock} "
      + "WHERE product.id = #{id}")
	void updateStock(Product product);
	//----------------------------------------------------------
	@Select  ("select * from product where id=#{prefix}") // 
	@ResultMap("productResult")
	List<Product> findAllById(Integer key);
	//----------------------------------------------------------
	@Select ("select stock from product where id=#{prefix}")
	Integer getProductStock(Integer key);
	
}
