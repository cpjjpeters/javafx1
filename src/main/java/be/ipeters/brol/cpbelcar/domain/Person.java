package be.ipeters.brol.cpbelcar.domain;

public class Person {
	private Integer id;
	private String firstName;
	private String lastName;
	private String typePerson;
	private String street;
	private String houseNr;
	private String extension;
	private String city;
	private int zipCode;
	private String country;
	private String email;
	private String userName;
	private String loyaltyLevel;
	private String taxNumber;
	private Integer numberOfSoldCars;
	private String employeeFunction;
	
	public Person(Integer id, String firstName, String lastName, String typePerson, String street, String houseNr,
			String extension, String city, int zipCode, String country, String email, String userName,
			String loyaltyLevel, String taxNumber, Integer numberOfSoldCars, String employeeFunction) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.typePerson = typePerson;
		this.street = street;
		this.houseNr = houseNr;
		this.extension = extension;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.email = email;
		this.userName = userName;
		this.loyaltyLevel = loyaltyLevel;
		this.taxNumber = taxNumber;
		this.numberOfSoldCars = numberOfSoldCars;
		this.employeeFunction = employeeFunction;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(String houseNr) {
		this.houseNr = houseNr;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoyaltyLevel() {
		return loyaltyLevel;
	}

	public void setLoyaltyLevel(String loyaltyLevel) {
		this.loyaltyLevel = loyaltyLevel;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public Integer getNumberOfSoldCars() {
		return numberOfSoldCars;
	}

	public void setNumberOfSoldCars(Integer numberOfSoldCars) {
		this.numberOfSoldCars = numberOfSoldCars;
	}

	public String getEmployeeFunction() {
		return employeeFunction;
	}

	public void setEmployeeFunction(String employeeFunction) {
		this.employeeFunction = employeeFunction;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", typePerson=" + typePerson
				+ ", street=" + street + ", houseNr=" + houseNr + ", extension=" + extension + ", city=" + city
				+ ", zipCode=" + zipCode + ", country=" + country + ", email=" + email + ", userName=" + userName
				+ ", loyaltyLevel=" + loyaltyLevel + ", taxNumber=" + taxNumber + ", numberOfSoldCars="
				+ numberOfSoldCars + ", employeeFunction=" + employeeFunction + "]";
	}
	
	

}
