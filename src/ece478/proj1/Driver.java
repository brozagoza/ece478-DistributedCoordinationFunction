package ece478.proj1;

public class Driver {
	
	
	public static void main (String [] args) {
		int [] lambdas = new int[] {50, 100, 200, 300};	// test sample

		// Topology 1 : Scenario A 
		for (int i = 0; i < 4; i++) {
			Simulation scenarioA = new Simulation(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		
		
	} // end main

}
