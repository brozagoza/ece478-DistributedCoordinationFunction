package ece478.proj1;

public class Driver {
	
	
	public static void main (String [] args) {
		int [] lambdas = new int[] {50, 100, 200, 300};	// test sample

		// Topology A : Scenario 1 : LambdaA = LambdaC
		for (int i = 0; i < 4; i++) {
			Simulation scenarioA = new Simulation(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		
		// Topology A : Scenario 1 : LambdaA = 2 * LambdaC
		for (int i = 0; i < 4; i++) {
			Simulation scenarioA = new Simulation(2*lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		
		
	} // end main

}
