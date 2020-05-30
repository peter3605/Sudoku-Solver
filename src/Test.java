
public class Test {
	public static void main(String[] args) {
		testBasicStrategy();
	}
	private static void testBasicStrategy() {
		String path = "tests/test";
		for (int i = 1; i < 3; i++) {
			System.out.println("TEST " + i + "\n");
			String fileName = path + i + ".txt";
			BasicStrategy bs = new BasicStrategy(fileName);
			System.out.println("Test" + i + " grid is:");
			bs.printGrid();
			System.out.println();
			bs.solve();
			System.out.println("Basic Strategy produced the solution:");
			bs.printSolution();
			System.out.println();
			System.out.println("Now validating the solution:");
			System.out.println(bs.validate());
			System.out.println();
		}
	}
}
