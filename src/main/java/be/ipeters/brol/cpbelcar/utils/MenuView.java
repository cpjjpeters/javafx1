package be.ipeters.brol.cpbelcar.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ipeters.brol.cpbelcar.controllers.CarController;
import be.ipeters.brol.cpbelcar.domain.Car;
import be.ipeters.brol.cpbelcar.services.CarService;

@Service
public class MenuView {
	// *********************************************************************
	MenuController mcsub = new MenuController();
//	GamesDAO gamesdao = new GamesDAO();
	@Autowired
	private CarService cs;
	//CarService cs = new CarService();
	
	public void CleanConsole() {
		for (int i = 0; i < 40; i++) {
			System.out.println();
		}
	}

	public void SubCleanConsole() {
		for (int i = 0; i < 05; i++) {
			System.out.println();
		}
	}

	public void MenuItem1() {
		CleanConsole();
		System.out.println("MenuItem1-Showing the first option category");
		System.out.println("-------------------------------");
		List<Car> alleOttoz = new ArrayList<>();
		alleOttoz.addAll(cs.findAll());
//		System.out.println(alleOttoz);
		for (Car car:cs.findAll()) {
			System.out.println(car.toString());	
		}
		
//		System.out.println(gamesdao.findCategoryById(1));
		// mcsub.showHash(mcsub.fillHashGoBack());
		System.out.println("   ........................................................     ");
	}

	public void MenuItem2() {
		CleanConsole();
		System.out.println("MenuItem2-Showing the second option");
		System.out.println("--------------------");
//		System.out.println(gamesdao.findGameById(5).toSmallString());
		// need submenu to show details
		System.out.println("   ........................................................     ");
		System.out.println("   ........................................................     ");
	}

	public void MenuItem3() {
		CleanConsole();
		System.out.println("Showing the third option");
		System.out.println("--------------------------");
//		System.out.println(gamesdao.findBorrowerById(1));
		System.out.println("   ........................................................     ");
	}

	public void MenuItem4() {
		CleanConsole();
		System.out.println("Show a game fourth option ");
		System.out.println("---------------------------");
	System.out.println("   ........................................................     ");
	}

	public void MenuItem5() {
		CleanConsole();
		System.out.println("   ........................................................     ");
	}

	public void MenuItem6() {
		CleanConsole();
	System.out.println("   ........................................................     ");
	}

	public void MenuItem7() {
		CleanConsole();
		System.out.println("   ........................................................     ");
	}

	public void MenuItem8() {
		CleanConsole();
	System.out.println("   ........................................................     ");
	}
	public void MenuItem11() {
		CleanConsole();
		System.out.println("--------------");
		System.out.println("   ........................................................     ");
		
	}
	public void MenuItem38() {
		CleanConsole();
		
		System.out.println("   ........................................................     ");
	}

//*********************************************************************fillHashThirtyEigth
	public void SubMenuAllGames() {
		CleanConsole();
		System.out.println("SubMenuAllGames, ...");
		System.out.println("gamesdao.findAll");
		System.out.println("");
	}

	public void SubMenuGameDetail() {
		CleanConsole();
		System.out.println("   ........................................................     ");
	}
}
//*********************************************************************		
