package be.ipeters.brol.cpbelcar.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ibatis.javassist.bytecode.Descriptor.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ipeters.brol.cpbelcar.domain.Car;
import be.ipeters.brol.cpbelcar.domain.CarProduct;
import be.ipeters.brol.cpbelcar.domain.Production;
import be.ipeters.brol.cpbelcar.mappers.CarMapper;

@Service
public class CarService implements CrudService<Car, Integer> {
	private boolean isCarCreationPossible = false;
	private Integer pcgetProductId;
	private Integer partStock = 0;
	private Integer carStock = 0;
	private Double partPrice=0.0;
	private Double carPrice = 0.0;
	private Integer partId = 0;
	private Production production;
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private SupplierOrderService supplierorderService;
	@Autowired
	private CarProductService cpService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductionService productionService;

	public CarService() {
		super();
	}

	public CarService(CarMapper carMock) {
		this.carMapper = carMock;
	}

	@Override
	public void save(Car entity) {
		/*
		 * bij een nieuwe wagen van een bepaald type dient de stock +1 te worden
		 * tegelijk 4 parts -1
		 */
		checkPartsAvailable(entity.getId());
		if (isCarCreationPossible) {
			// verminder de stock van elk onderdeel
			partId=entity.getId();
			adaptPartStock( partId);
//			carMapper.insert(entity); // not creating a new line in the table, so update the stock
			this.updateStockPlusOne(entity);
			filloutProduction();
			productionService.save(production);
			System.out.println("We have: "+production.getDescription());
		} else {
			System.out.println("Not all parts are available...");
		}
	}
	protected void filloutProduction() {
		// fill out the fields orderId, orderlineId, description, lastUpdate
		production=new Production(1, 1, 1, "created car of type "+partId, LocalDate.now());
		
	}

	protected void adaptPartStock(Integer carId) {
		// verminder stock van elk van de 4 parts met id=partID
		List<CarProduct> cpList = cpService.findAllById(carId);
		System.out.println("carId=" + carId + ", size of cpList=" + cpList.size());
		for (CarProduct pc : cpList) {
			System.out.println("productService.updateProductStock(pc.getProductId()):"+pc.getProductId());
			partStock=productService.getProductStock(pc.getProductId());
			System.out.println("partStock="+partStock+", for part "+pc.getProductId()+", which is "+productService.findById(pc.getProductId()));
			partStock--;
			System.out.println("partStock="+partStock);
			productService.updateStockMinOne(pc.getProductId());
		}

	}
	public boolean checkPartsAvailable(Integer carId) {

		Map<Integer, Integer> cpMap = new HashMap<Integer, Integer>();
		List<CarProduct> cpList = cpService.findAllById(carId);
		System.out.println("carId=" + carId + ", size of cpList=" + cpList.size());
		if (cpList.size() == 0) {
			System.out.println("We cannot produce a car with id " + carId + ".");
			isCarCreationPossible = false;
		} else { //
			for (CarProduct pc : cpList) {
				pcgetProductId = pc.getProductId();
				System.out.println(pcgetProductId);

				partStock = productService.findById(pcgetProductId).getStock();
				cpMap.put(pcgetProductId, partStock);
				switch (partStock) {
				case 0:
					// part not available
					System.out
							.println("part <" + productService.findById(pcgetProductId).getName() + "> not available");
					System.out.println("Need to order this part...");
					// create SupplierOrder  // Order this part
					supplierorderService.createSupplierOrderViaPartId(pcgetProductId);
					
					isCarCreationPossible = false;
					break;

				default:
					System.out.println("available!");
					isCarCreationPossible = true;
				}
			}
			// check if at least one part is missing to set isCarCreationPossible=false;
			for (Map.Entry<Integer, Integer> entry : cpMap.entrySet()) {
				if (entry.getValue() == 0) {
					isCarCreationPossible = false;
					
				}
			}
		}
		System.out.println("isCarCreationPossible=" + isCarCreationPossible);
		return isCarCreationPossible;
	}

	public Double calculateCarOrderPrice(Integer carId) {
		System.out.println("carService - calculateCarOrderPrice");
		carPrice=0.0;
		Map<Integer, Double> cpMap = new HashMap<Integer, Double>();
		List<CarProduct> cpList = cpService.findAllById(carId);
		System.out.println("carId=" + carId + ", size of cpList=" + cpList.size());
		if (cpList.size() == 0) {
			System.out.println("We cannot calculate a price for car with id " + carId + ".");
		} else {
			for (CarProduct cp : cpList) {
				pcgetProductId = cp.getProductId();
				partPrice = productService.findById(pcgetProductId).getConsumerPrice();
				System.out.println(pcgetProductId+ " costs " +partPrice);
				carPrice+=partPrice;
			
			}
			
		}
		System.out.println("carPrice=" + carPrice);
		return carPrice;
	}

	@Override
	public Car findById(Integer key) {
		return carMapper.findById(key);
	}

	@Override
	public List<Car> findAll() {
		return carMapper.findAll();
	}

	@Override
	public void deleteById(Integer key) {
		carMapper.deleteById(key);
	}

	@Override
	public void update(Car entity) {
		carMapper.update(entity);
	}
	public Integer getCarStock(Integer key) {
		return carMapper.getCarStock(key);
	}
	public void updateStockMinOne(Car entity) {
		carStock=carMapper.getCarStock(entity.getId());
		System.out.println("carStock="+carStock);
		carStock--;
		System.out.println("carStock="+carStock);
		entity.setStock(carStock);
		carMapper.updateStock(entity);
	}

	public void updateStockPlusOne(Car entity) {
		carStock=carMapper.getCarStock(entity.getId());
		System.out.println("updateStockPlusOne carStock for Car "+entity.getId()+"="+carStock);
		carStock++;
		System.out.println("updateStockPlusOne carStock for Car "+entity.getId()+"="+carStock);
		entity.setStock(carStock);
		carMapper.updateStock(entity);
	}

	public Integer getProductStock(int carProductId) {
		// TODO Auto-generated method stub
		return this.findById(carProductId).getStock();
	}

}
