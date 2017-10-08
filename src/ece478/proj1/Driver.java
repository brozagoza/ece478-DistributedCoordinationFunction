package ece478.proj1;

public class Driver {
	
	
	public static void main (String [] args) {
		int [] lambdas = new int[] {50, 100, 200, 300};	// test sample
		
		new Simulation(50, 50).runSimulation();
//		for (int i = 0; i < 4; i++) {
//			Simulation scenarioA = new Simulation(lambdas[i], lambdas[i]);
//			Simulation scenarioB = new Simulation(2*lambdas[i], lambdas[i]);
//			scenarioA.runSimulation();
//			scenarioB.runSimulation();
//		}
	} // end main

}
