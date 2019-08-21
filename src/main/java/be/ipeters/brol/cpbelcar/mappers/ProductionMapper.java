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
import be.ipeters.brol.cpbelcar.domain.Production;

@Repository
@Mapper

public interface ProductionMapper {
	@Select("select * from production")
	@Results(id = "productionResult", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "orderId", column = "order_id"),
			@Result(property = "orderlineId", column = "orderline_id"),
			@Result(property = "description", column = "description"), 
			@Result(property = "lastUpdate", column = "last_update")
	})
	List<Production> findAll();
	//----------------------------------------------------------
	@Select  ("select * from production where name=#{prefix}") // 
	@ResultMap("productionResult")
	List<Production> getAllProductsWithNameStartingBy(String prefix);
	//----------------------------------------------------------
	
	@Select("select * from production where id=#{id}")
	@ResultMap("productionResult")
	Production findById(Integer key);
	//----------------------------------------------------------
	
	@Insert("INSERT into production (order_id, orderline_id, description, last_update)" +
            "VALUES          (#{orderId},#{orderlineId},#{description},#{lastUpdate})")
    void insert(Production entity);
	//----------------------------------------------------------
	@Delete 	("delete from production where id=#{id}")
	void deleteById(Integer key);
	//----------------------------------------------------------
	
	@Update("UPDATE production set "
            + "order_id = #{orderId}, "
            + "orderline_id = #{orderlineId}, "
            + "description = #{description}, "
            + "last_update = #{lastUpdate} "
      + "WHERE production.id = #{id}")
	void update(Production production);
	//----------------------------------------------------------
	@Select  ("select * from production where id=#{prefix}") // 
	@ResultMap("productionResult")
	List<Production> findAllById(Integer key);
	//----------------------------------------------------------
	
}
