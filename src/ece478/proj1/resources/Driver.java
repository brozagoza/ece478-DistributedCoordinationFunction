package ece478.proj1.resources;

import ece478.proj1.topology1.sim.Top1Sim1;
import ece478.proj1.topology1.sim.Top1Sim2;
import ece478.proj1.topology2.sim.Top2Sim1;
import ece478.proj1.topology2.sim.Top2Sim2;

public class Driver {

	public static void main(String[] args) {
		int[] lambdas = new int[] { 50, 100, 200, 300 }; // test sample

		// Topology A : Scenario 1 : LambdaA = LambdaC
		System.out.println("Topology A : Scenario 1 : A=C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top1Sim1 scenarioA = new Top1Sim1(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 1 : A=2*C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top1Sim1 scenarioB = new Top1Sim1(2 * lambdas[i], lambdas[i]);
			scenarioB.runSimulation();
			scenarioB.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 2 : A=C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top1Sim2 scenarioA = new Top1Sim2(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology A : Scenario 2 : A=2*C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top1Sim2 scenarioB = new Top1Sim2(2 * lambdas[i], lambdas[i]);
			scenarioB.runSimulation();
			scenarioB.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology B : Scenario 1 : A=C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top2Sim1 scenarioA = new Top2Sim1(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology B : Scenario 1 : A=2C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top2Sim1 scenarioA = new Top2Sim1(2*lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology B : Scenario 2 : A=C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top2Sim2 scenarioA = new Top2Sim2(lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println();

		// THE OTHER ONE
		System.out.println("***************************************************");
		System.out.println("Topology B : Scenario 2 : A=2*C");
		System.out.println("====================================================");
		for (int i = 0; i < 4; i++) {
			Top2Sim2 scenarioA = new Top2Sim2(2*lambdas[i], lambdas[i]);
			scenarioA.runSimulation();
			scenarioA.printResults();
		}
		System.out.println();

	} // end main

}
