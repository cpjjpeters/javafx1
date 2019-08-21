package be.ipeters.brol.cpbelcar.utils;
 
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MenuController {
	
	public Map fillHashMain() {
		Map<String, String> menuHash = new TreeMap<>();
		menuHash.put(" 1", "Create a new customer");
		menuHash.put(" 2", "Start a production");
		menuHash.put(" 3", "Cancel a production");
		menuHash.put("99", "Quit program");
		menuHash.put(" 4", "Edit a production");
		menuHash.put(" 5", "Review all current productions");
		menuHash.put(" 6", "place an order with a supplier or make one for a customer");
		menuHash.put(" 7", "cancel an order");
		menuHash.put(" 8", "review and edit all orders");
		//menuHash.put(" 9", "Complex search:...");
		//menuHash.put("10", "...");
		menuHash.put("11", "Administration-side ");
		return menuHash;
	}

	public Map fillDifficultyMap() {
		Map<Integer, String> menuHash = new TreeMap<>();
		menuHash.put(1, "very easy");
		menuHash.put(2, "easy");
		menuHash.put(3, "average");
		menuHash.put(4, "difficult");
		menuHash.put(5, "very difficult");
		return menuHash;
	}
	
	public Map fillHashTwo() {
		Map<Integer, String> menuHash = new TreeMap<>();
		menuHash.put( 1, "Game details");
		menuHash.put(99, "Go to previous menu");
		return menuHash;
	}
	
	public Map fillHashGoBack() {
		Map<Integer, String> menuHash = new TreeMap<>();
		menuHash.put(99, "Go to previous menu");
		return menuHash;
	}
	
	public void showHash(Map<Integer, String> menuHash) {
		Set set = menuHash.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.printf("%s - ", me.getKey());
			System.out.println(me.getValue());
		}
	}
	
}
