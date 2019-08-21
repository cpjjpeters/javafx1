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

import be.ipeters.brol.cpbelcar.domain.Person;

@Repository
@Mapper
public interface PersonMapper {
	@Select("select * from person")
	@Results(id = "personResult", value = { 
			@Result(property = "id", column = "id"),
			@Result(property = "firstName", column = "first_name"), 
			@Result(property = "lastName", column = "last_name"),
			@Result(property = "typePerson", column = "type_person"), 
			@Result(property = "street", column = "street"), 
			@Result(property = "houseNr", column = "house_number"), 
			@Result(property = "extension", column = "extension"), 
			@Result(property = "city", column = "city"), 
			@Result(property = "zipCode", column = "zipcode"), 
			@Result(property = "country", column = "country"), 
			@Result(property = "email", column = "email"), 
			@Result(property = "userName", column = "user_name"), 
			@Result(property = "loyaltyLevel", column = "loyalty_level"), 
			@Result(property = "taxNumber", column = "tax_number"), 
			@Result(property = "numberOfSoldCars", column = "soldcar_number"), 
			@Result(property = "employeeFunction", column = "employee_function")
	})
	List<Person> findAll();
	//----------------------------------------------------------

	@Select  ("select * from person where firstName=#{prefix}") // 
	@ResultMap("personResult")
	List<Person> getAllPersonsWithNameStartingBy(String prefix);
	//----------------------------------------------------------
	
	@Select("select * from person where id=#{id}")
	@ResultMap("personResult")
	Person findById(Integer key);
	//----------------------------------------------------------
	
	@Insert("INSERT into person (first_name, last_name, type_person,street, house_number, extension, city, zipcode, country, email, user_name, loyalty_level, tax_number,soldcar_number , employee_function)" +
            "VALUES     (#{firstName},#{lastName},#{typePerson},#{street},#{houseNr},#{extension},#{city},#{zipCode},#{country},#{email},#{userName},#{loyaltyLevel},#{taxNumber},#{numberOfSoldCars},#{employeeFunction})")
    void insert(Person entity);
	//----------------------------------------------------------
	
	@Delete 	("delete from person where id=#{id}")
	void deleteById(Integer key);
	//----------------------------------------------------------
	
	@Update("UPDATE person set "
            + "first_name = #{firstName}, "
            + "last_name = #{lastName}, "
			+ "type_person = #{typePerson}, " 
			+ "street = #{street},  "
			+ "house_number = #{houseNr},  "
			+ "extension = #{extension},  "
			+ "city = #{city},  "
			+ "zipcode = #{zipCode},  "
			+ "country = #{country},  "
			+ "email = #{email},  "
			+ "user_name = #{userName}, "
			+ "loyalty_level = #{loyaltyLevel},  "
			+ "tax_number = #{taxNumber},  "
			+ "soldcar_number = #{numberOfSoldCars},  "
			+ "employee_function = #{employeeFunction} "
      + "WHERE person.id = #{id}")
	void update(Person person);
	//----------------------------------------------------------

}
