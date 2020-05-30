
public class Test {
	private static final String PATH = "tests/test";
	private static final int NUMTESTS = 10;
	
	public static void main(String[] args) {
		testBacktrackingStrategy();
	}
	
	private static void testBacktrackingStrategy() {
		System.out.println("Testing Backtracking Strategy");
		System.out.println("-----------------------");
		for (int i = 1; i <= NUMTESTS; i++) {
			System.out.println("TEST " + i + "\n");
			String fileName = PATH + i + ".txt";
			Strategy bs = new BacktrackingStrategy(fileName);
			bs.solve();
			System.out.println("Test" + i + " grid is:");
			bs.printGrid();
			System.out.println();
			System.out.println("Backtracking Strategy produced the solution:");
			bs.printSolution();
			System.out.println();
			System.out.println("Now validating the solution:");
			System.out.println(bs.validate());
			System.out.println();
		}
	}
}
