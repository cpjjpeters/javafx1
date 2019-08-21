package be.ipeters.brol.cpbelcar.utils;


public class GUI {
	MenuView mv = new MenuView();
	MenuView mvsub = new MenuView();
	MenuController mc = new MenuController();

	public void start() {
		System.out.println("This is the BELCAR application");
		System.out.println("Created by Carl Peters");
		System.out.println("Choose from these options");
		System.out.println("-------------------------\n");
	}
	 
	public void requestSelection() {
		InputRequest ir = new InputRequest();
		int choice;
		choice = ir.giveInput("your choice");
		while (choice != 99) {
			switch (choice) {
			case 1:
				System.out.println("Case 1 List all cars");
				mv.MenuItem1();
				break;
			case 2:
				// System.out.println("Show the fifth game");
				mv.MenuItem2();
				requestGameDetailsSelection();
				break;
			case 3:
				//
				// System.out.println("Show the first borrower");
				mv.MenuItem3();
				break;
			case 4:
				// System.out.println("Show a game of your choice");
				mv.MenuItem4();
				break;
			case 5:
//				System.out.println("Show all games");
				mv.MenuItem5();
				break;
			case 6:
				// System.out.println("Show a game of your choice");
				mv.MenuItem6();
//				requestGameDetailsForMenu6();
				break;
			case 7:
				// System.out.println("Show borrowed games");
				mv.MenuItem7();  // godeepeer
				break;
			case 8:
				// System.out.println("Difficulty");
				mv.MenuItem8();
				break;
			case 9:
				// System.out.println("Difficulty");
			//	mv.MenuItem9();
				break;
			case 10:
				// System.out.println("Difficulty");
			//	mv.MenuItem10();
				break;
			case 11:
				// System.out.println("Difficulty");
				mv.MenuItem11();
				break;
//			case 38:
//				mv.MenuItem38();
//				break;
			default:
				System.out.println("this is unexpected, retry please.\n");

			}
			mc.showHash(mc.fillHashMain());
			choice = ir.giveInput("your choice");
			// System.out.println("choice= "+choice);
		}
		mv.CleanConsole();
		System.out.println("Thanks for trying this program!");
	}

	private void requestGameDetailsForMenu6() {
		InputRequest ir = new InputRequest();
		int choice;
		choice = ir.giveInput("a game you wish to see the details from ");
		while (choice != 99) {
			switch (choice) {
			case 1:
				System.out.println("requestGameDetailsForMenu6");
				break;
			case 2:
			default:
				System.out.println("this is unexpected, retry please.\n");
			}
			mv.SubCleanConsole();
			choice = ir.giveInput("submenu choice");
		}
		mv.CleanConsole();
		System.out.println("Thanks for using this item.\n");
	}
	private void requestBorrowDetailsForMenu7() {
		InputRequest ir = new InputRequest();
		int choice;
		choice = ir.giveInput("a person you wish to see the details from ");
		while (choice != 99) {
			switch (choice) {
			case 1:
				System.out.println("requestBorrowDetailsForMenu7");
				// mvsub.SubMenuGameDetail();
				break;
			case 2:
			default:
				System.out.println("this is unexpected, retry please.\n");
			}
			mv.SubCleanConsole();
			// mc.showHash(mc.fillHashSix());
			choice = ir.giveInput("submenu choice");
			// System.out.println("choice= "+choice);
		}
		mv.CleanConsole();
		System.out.println("Thanks for using this item.\n");
	}

	private void requestGameDetailsSelection() {
		InputRequest ir = new InputRequest();
		int choice;
		choice = ir.giveInput("submenu choice ");

		while (choice != 99) {
			switch (choice) {
			case 1:
				mvsub.SubMenuGameDetail();
				break;
			case 2:
			default:
				System.out.println("this is unexpected, retry please.\n");
			}
			mv.SubCleanConsole();
			mc.showHash(mc.fillHashTwo());
			choice = ir.giveInput("submenu choice");
			// System.out.println("choice= "+choice);
		}
		mv.CleanConsole();
		System.out.println("Thanks for using this item.\n");
	}

}
