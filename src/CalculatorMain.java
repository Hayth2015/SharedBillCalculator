import java.util.Arrays;
import java.util.List;

public class CalculatorMain {

	public static List<String> lines = Arrays.asList(
	                   	"40.00 Thijs Danny,Danny,Thijs,Stefan,Den",
	                	"45.00 Danny Danny,Thijs,Stefan,Den",
	                	"36.00 Stefan Danny,Thijs,Stefan",
	                	"40.00 Stefan Danny,Thijs,stefan,Den",
	                	"40.00 Danny Danny,Thijs,Stefan,Den",
	                	"12.00 Stefan Thijs,Stefan,Den",
	                	"44.00 Danny Danny,Thijs,Stefan,Den",
	                	"42.40 Den Danny,Stefan,Den,Den",
	                	"40.00 danny Danny,Thijs,Stefan,Den",
	                	"50.40 Thijs Danny,Thijs,Den",
	                	"48.00 Den Danny,thijs,Stefan,Den",
	                	"84.00 Thijs Thijs,Stefan,den"
	                );
	
	
	public static void main(String[] args) {
		
		Calculator calculator = new Calculator(lines);
		calculator.printBill();
		
	}

}
