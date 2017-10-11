package ece478.proj1.resources;

import ece478.proj1.sim1.Simulation;

public class Driver {

	public static void main(String[] args) {
		int[] lambdas = new int[] { 50, 100, 200, 300 }; // test sample

		// Topology A : Scenario 1 : LambdaA = LambdaC
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 1 : A=C");
		System.out.println("***************************************************");
		for (int i = 0; i < 4; i++) {
			Simulation scenarioA = new Simulation(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println("***************************************************");

		// Topology A : Scenario 1 : LambdaA = 2 * LambdaC
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 1 : A=2*C");
		System.out.println("***************************************************");
		for (int i = 0; i < 4; i++) {
			Simulation scenarioA = new Simulation(2 * lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println("***************************************************");

	} // end main

}
