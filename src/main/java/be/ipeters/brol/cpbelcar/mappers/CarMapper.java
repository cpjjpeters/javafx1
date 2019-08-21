package be.ipeters.brol.cpbelcar.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import be.ipeters.brol.cpbelcar.domain.Car;

@Repository
@Mapper

public interface CarMapper {

	@Select("select * from car")
	@Results(id = "carResult", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"), 
			@Result(property = "stock", column = "stock") })
	List<Car> findAll();
	//----------------------------------------------------------

	@Select  ("select * from car where name=#{prefix}") // 
	@ResultMap("carResult")
	List<Car> getAllCarsWithNameStartingBy(String prefix);
	//----------------------------------------------------------

	@Select("select * from car where id=#{id}")
	@ResultMap("carResult")
	Car findById(Integer key);
	//----------------------------------------------------------

	@Insert("INSERT into car (name, stock)" +
            "VALUES          (#{name},#{stock})")
    void insert(Car entity);
	//----------------------------------------------------------

	@Delete 	("delete from car where id=#{id}")
	void deleteById(Integer key);
	//----------------------------------------------------------

	@Update("UPDATE car set "
            + "name = #{name}, "
            + "stock = #{stock} "
      + "WHERE car.id = #{id}")
	void update(Car car);
	//----------------------------------------------------------
	@Update("UPDATE car set "
           
            + "stock = #{stock} "
      + "WHERE car.id = #{id}")
	void updateStock(Car car);
	//----------------------------------------------------------
	@Select ("select stock from car where id=#{prefix}")
	Integer getCarStock(Integer key);


}
