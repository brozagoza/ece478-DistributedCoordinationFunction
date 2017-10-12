package ece478.proj1.topology1.sim;

import java.util.Queue;

import ece478.proj1.resources.GenerateSeries;
import ece478.proj1.resources.Packet;

// Topology A : Scenario 2
public class Simulation2 {

	final int FINAL_SLOT = 500000; // amount of slots to go through

	public int aLamb; // a lambda
	public int cLamb; // c lambda
	private int currentSlot; // global slot counter
	private int aPacketCount;
	private int cPacketCount;
	private int collisionCount;

	private Queue<Packet> aPacks; // A Packets
	private Queue<Packet> cPacks; // C Packets

	public Simulation2(int aLambInp, int cLambInp) {
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

	// runsSimulation
	public void runSimulation() {
		boolean channelBusy = false;
		Packet aPack = aPacks.poll(); // current A packet
		Packet cPack = cPacks.poll(); // current C packet
		Packet transPack = null;

		while (currentSlot < FINAL_SLOT) {
			if (aPack == null || cPack == null)
				break;

			if (!channelBusy) {
				// only if the start time is greater than curSlot will it check. Is ready after
				// DIFS backoff and RTS/CTS
				boolean aReady = false;
				boolean cReady = false;

				if (currentSlot >= aPack.getStartTime())
					aReady = aPack.isReady();

				if (currentSlot >= cPack.getStartTime())
					cReady = cPack.isReady();

				if (aReady && cReady) {
					aPack.collision();
					cPack.collision();
					collisionCount++;
				} else if (aReady && !cReady) {
					channelBusy = true;
					transPack = aPack;
					aPack = aPacks.poll();
				} else if (!aReady && cReady) {
					channelBusy = true;
					transPack = cPack;
					cPack = cPacks.poll();
				}

			} // end IF
			else {
				if (transPack.transmit()) {
					channelBusy = false;

					if (transPack.getChannel() == 'a')
						aPacketCount++;
					else
						cPacketCount++;
				}
			} // end ELSE

			++currentSlot;
		}
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
