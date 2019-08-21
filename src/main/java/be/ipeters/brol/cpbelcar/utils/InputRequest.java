package be.ipeters.brol.cpbelcar.utils;
import java.util.Scanner;

public class InputRequest {
	Scanner input = new Scanner(System.in);
	private int getal;
	public int giveInput(String message) {
		System.out.println("\nPlease enter " + message);
		String inputnext = input.next();
		try {
			getal = Integer.parseInt(inputnext);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Please type a numeric value!");
			//e.printStackTrace();
		}
		// System.out.println(" you gave " + inputnext + " as input");
		return getal;	
	}
}