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
import be.ipeters.brol.cpbelcar.domain.Order;

@Repository
@Mapper
public interface SupplierorderMapper {
	@Select("select * from orders")
	@Results(id = "orderResult", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "typeOrder", column = "type_order"), 
			@Result(property = "price", column = "price"), 
			@Result(property = "orderDate", column = "order_date"),
			@Result(property = "deliveryDate", column = "delivery_date"),
			@Result(property = "customerId", column = "customer_id"),
			@Result(property = "supplierId", column = "supplier_id"),
			@Result(property = "employeeId", column = "employee_id"),
			@Result(property = "status", column = "status"),
			@Result(property = "percentage", column = "percentage")
	})
	List<Order> findAll();

	@Select  ("select * from orders where name=#{prefix}") // 
	@ResultMap("orderResult")
	List<Order> getAllOrdersWithNameStartingBy(String prefix);
	
	@Select("select * from orders where id=#{id}")
	@ResultMap("orderResult")
	Order findById(Integer key);
	
	@Insert("INSERT into orders (type_order,price,order_date,delivery_date,customer_id,supplier_id,employee_id,status,percentage)" +
            "VALUES          (#{typeOrder},#{price}, #{orderDate},#{deliveryDate},#{customerId},#{supplierId},#{employeeId},#{status},#{percentage})")
	@Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Order entity);
	
	@Delete 	("delete from orders where id=#{id}")
	void deleteById(Integer key);
	
	@Update("UPDATE orders set "
            + "type_order = #{typeOrder}, "
            + "price = #{price}, "
            + "order_date = #{orderDate},"
			+ "delivery_date = #{deliveryDate},"
			+ "customer_id = #{customerId},"
			+ "supplier_id = #{supplierId},"
			+ "employee_id = #{employeeId},"
			+ "status = #{status},"
			+ "percentage = #{percentage}"
      + "WHERE orders.id = #{id}")
	void update(Order order);

}
