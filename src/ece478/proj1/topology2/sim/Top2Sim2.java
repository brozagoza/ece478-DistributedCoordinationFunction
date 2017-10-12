package ece478.proj1.topology2.sim;

import java.util.Queue;

import ece478.proj1.resources.GenerateSeries;
import ece478.proj1.resources.Packet;

public class Top2Sim2 {
	final int FINAL_SLOT = 500000; // amount of slots to go through

	public int aLamb; // a lambda
	public int cLamb; // c lambda
	private int currentSlot; // global slot counter
	private int aPacketCount;
	private int cPacketCount;
	private int collisionCount;

	private Queue<Packet> aPacks; // A Packets
	private Queue<Packet> cPacks; // C Packets

	public Top2Sim2(int aLambInp, int cLambInp) {
		aLamb = aLambInp;
		cLamb = cLambInp;
		currentSlot = 0;
		aPacketCount = 0;
		cPacketCount = 0;
		collisionCount = 0;

		GenerateSeries series = new GenerateSeries(aLamb, cLamb, true);

		aPacks = series.getAPacks();
		cPacks = series.getCPacks();
	}

	/*
	 * THIS IS THE VERSION WITH RTS AND CTS, SO COLLISIONS WILL BE not as YUUGGEE
	 */
	public void runSimulation() {
		boolean channelBusy = false;
		Packet aPack = aPacks.poll(); // current A packet
		Packet cPack = cPacks.poll(); // current C packet
		Packet transPack = null;
		int aExpectedCompletionTime = -1;
		int cExpectedCompletionTime = -1;
		int aCollisionCount = 0;
		int cCollisionCount = 0;
		
		while (currentSlot < FINAL_SLOT) {
			boolean aReady = aPack != null && aPack.isReady();
			boolean cReady = cPack != null && cPack.isReady();
			
			
			if (aExpectedCompletionTime == currentSlot) {
				// the ack has been received
				if (aPack != null && aPack.transmissionComplete()) {
					aPacketCount++;
					aPack = aPacks.poll();
				}
				// the ack was not received
				else {
					aCollisionCount++;
					aPack.collision();
				}
			} // end IF STATEMENT **************************************

			if (cExpectedCompletionTime == currentSlot) {
				// the ack has been received
				if (cPack != null && cPack.transmissionComplete()) {
					cPacketCount++;
					cPack = cPacks.poll();
				}
				// the ack was not received
				else {
					cCollisionCount++;
					cPack.collision();
				}
			} // end IF STATEMENT **************************************
			
			
			
			if (aPack != null && cPack != null && aPack.inTransit() && cPack.inTransit()) {
				channelBusy = false;
				aPack.stopTransmission();
				cPack.stopTransmission();
				++collisionCount;
			} else if (aPack != null && aPack.getStartTime() <= currentSlot && aReady && !aPack.inTransit()
					&& !channelBusy) {
				channelBusy = true;
				aPack.transmit();
				aExpectedCompletionTime = currentSlot + 103;
			} else if (aPack != null && aPack.inTransit())
				aPack.transmit();
			else if (cPack != null && cPack.getStartTime() <= currentSlot && cReady && !cPack.inTransit()
					&& !channelBusy) {
				channelBusy = true;
				cPack.transmit();
				cExpectedCompletionTime = currentSlot + 103;
			} else if (cPack != null && cPack.inTransit())
				cPack.transmit();
			
			
			++currentSlot;
		} // end while loop
		
		collisionCount += aCollisionCount + cCollisionCount;
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
