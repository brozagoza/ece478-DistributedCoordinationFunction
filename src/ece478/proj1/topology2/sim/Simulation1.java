package ece478.proj1.topology2.sim;

import java.util.Queue;

import ece478.proj1.resources.GenerateSeries;
import ece478.proj1.resources.Packet;

public class Simulation1 {

	final int FINAL_SLOT = 500000; // amount of slots to go through

	public int aLamb; // a lambda
	public int cLamb; // c lambda
	private int currentSlot; // global slot counter
	private int aPacketCount;
	private int cPacketCount;
	private int collisionCount;

	private Queue<Packet> aPacks; // A Packets
	private Queue<Packet> cPacks; // C Packets

	public Simulation1(int aLambInp, int cLambInp) {
		aLamb = aLambInp;
		cLamb = cLambInp;
		currentSlot = 0;
		aPacketCount = 0;
		cPacketCount = 0;
		collisionCount = 0;

		GenerateSeries series = new GenerateSeries(aLamb, cLamb, false);

		aPacks = series.getAPacks();
		cPacks = series.getCPacks();

	}

	/*
	 * THIS IS THE VERSION WITH NO RTS AND CTS, SO COLLISIONS WILL BE YUUGGEE
	 * 
	 * When time matches one of the packets from A or C, it will start sending it's
	 * packets. If it doesn't receive an ACK when it should have, indicating that it
	 * was successful, then it never got delivered! In this case, recalculated
	 * backoff and try again. If at any point a new packet interrupts another packet
	 * from completing it's transmission, then the receiver just farts and gives up
	 * and sends no one an ack. Collisions will be through the roof.
	 */
	public void runSimulation() {
		boolean channelBusy = false;
		Packet aPack = aPacks.poll(); // current A packet
		Packet cPack = cPacks.poll(); // current C packet
		Packet transPack = null;

		

	}

	public void printResults() {
		double aThroughPut = (double) (aPacketCount * 12000) / 10;
		double cThroughPut = (double) (cPacketCount * 12000) / 10;

		System.out.printf("LambdaA: %d \t Lambda C: %d\n", aLamb, cLamb);
		System.out.printf("A Throughput: %f \t C Throughput: %f\n", aThroughPut, cThroughPut);
		System.out.printf("A# %d\tC#%d\n", aPacketCount, cPacketCount);
		System.out.printf("Collision Count: %d\n\n", collisionCount);
	}

}
