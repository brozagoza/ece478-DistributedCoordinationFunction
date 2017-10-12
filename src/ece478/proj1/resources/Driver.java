package ece478.proj1.resources;

import ece478.proj1.topology1.sim.Simulation1;
import ece478.proj1.topology1.sim.Simulation2;

public class Driver {

	public static void main(String[] args) {
		int[] lambdas = new int[] { 50, 100, 200, 300 }; // test sample

		// Topology A : Scenario 1 : LambdaA = LambdaC
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 1 : A=C");
		System.out.println("***************************************************");
		for (int i = 0; i < 4; i++) {
			Simulation1 scenarioA = new Simulation1(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println("***************************************************");

		// Topology A : Scenario 1 : LambdaA = 2 * LambdaC
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 1 : A=2*C");
		System.out.println("***************************************************");
		for (int i = 0; i < 4; i++) {
			Simulation1 scenarioB = new Simulation1(2 * lambdas[i], lambdas[i]);
			scenarioB.runSimulation();
			scenarioB.printResults();
		}
		System.out.println("***************************************************");

		// Topology A : Scenario 2 : LambdaA = LambdaC
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 2 : A=C");
		System.out.println("***************************************************");
		for (int i = 0; i < 4; i++) {
			Simulation2 scenarioA = new Simulation2(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println("***************************************************");

		// Topology A : Scenario 2 : LambdaA = 2 * LambdaC
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 2 : A=2*C");
		System.out.println("***************************************************");
		for (int i = 0; i < 4; i++) {
			Simulation2 scenarioB = new Simulation2(2 * lambdas[i], lambdas[i]);
			scenarioB.runSimulation();
			scenarioB.printResults();
		}
		System.out.println("***************************************************");

	} // end main

}
