package be.ipeters.brol.cpbelcar.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import be.ipeters.brol.cpbelcar.domain.Orderline;

@Repository
@Mapper
public interface OrderlineMapper {
	@Select("select * from orderline")
	@Results(id = "orderlineResult", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "orderId", column = "order_id"), 
			@Result(property = "title", column = "title"), 
			@Result(property = "quantity", column = "quantity"),
			@Result(property = "carProductId", column = "car_product_id")
	})
	List<Orderline> findAll();

	@Select  ("select * from orderline where name=#{prefix}") // 
	@ResultMap("orderlineResult")
	List<Orderline> getAllOrderlinesWithNameStartingBy(String prefix);
	
	@Select("select * from orderline where id=#{id}")
	@ResultMap("orderlineResult")
	Orderline findById(Integer key);
	
	@Insert("INSERT into orderline (order_id,title,quantity,car_product_id)" +
            "VALUES          (#{orderId},#{title}, #{quantity},#{carProductId})")
	@Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Orderline entity);
	
	@Delete 	("delete from orderline where id=#{id}")
	void deleteById(Integer key);
	
	@Update("UPDATE orderline set "
            + "order_id = #{orderId}, "
            + "price = #{price}, "
            + "title = #{title},"
			+ "quantity = #{quantity},"
			+ "car_product_id = #{carProductId}"	
      + "WHERE orderline.id = #{id}")
	void update(Orderline order);

	@Select("select * from orderline where order_id=#{id}")
	@ResultMap("orderlineResult")
	Orderline findOrderlineByOrderId(Integer key);

}

