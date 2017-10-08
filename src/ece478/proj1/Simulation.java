package ece478.proj1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
	final int DATA_FRAME_SIZE = 1500; // bytes
	final double SLOT_DURATION = 0.000020; // seconds
	final double SIFS_DURATION = 0.000010; // seconds
	final int CW_INITAL = 4; // slots
	final int ACK_RTS_CTS_SIZE = 30; // bytes
	final double DIFS_DURATION = 0.000040; // seconds
	final int TRANSMISSION_RATE = 6; // mbps
	final int CW_MAX = 1024; // slots
	final int SIMULATION_TIME = 10; // seconds

	private int aLamb;
	private int cLamb;
	private int current_slot;
	private int final_slot;
	private int count_a;
	private int count_c;
	private int collision_count;

	public Simulation(int aLamb, int cLamb) {
		this.aLamb = aLamb;
		this.cLamb = cLamb;
		final_slot = 500000; // change me later dude
	}

	// runsSimulation
	public void runSimulation() {
		count_a = 0;
		count_c = 0;
		collision_count = 0;
		double[] aNums = new double[aLamb * SIMULATION_TIME];
		double[] cNums = new double[cLamb * SIMULATION_TIME];

		uniformDistribution(aNums, cNums);

		computeSeries(aNums, cNums);

		// Because I'm too lazy to change the rest of the code dude
		Queue<Packet> aPacks = new LinkedList<>();
		Queue<Packet> cPacks = new LinkedList<>();

		initPackets(aPacks, cPacks, aNums, cNums);

		begin(aPacks, cPacks);
	}

	public void begin(Queue<Packet> a, Queue<Packet> c) {
		current_slot = 0;
		boolean channelBusy = false;
		Packet workingA = a.poll();
		Packet workingC = c.poll();

		Packet transmitting = null;

		while (current_slot < final_slot) {

			int readies = 0;

			if (workingA.backoff == 0 && workingA.value <= current_slot)
				readies++;

			if (workingC.backoff == 0 && workingC.value <= current_slot) {
				readies++;
			}

			if (readies == 0) {

				if (workingA.value <= current_slot)
					workingA.updateCounter(channelBusy, false);
				if (workingC.value <= current_slot)
					workingC.updateCounter(channelBusy, false);

				if (transmitting != null) {
					transmitting.updateCounter(channelBusy, true);
				}
			} else if (readies == 1) {
				if (transmitting == null) {
					if (workingA.backoff == 0) {
						transmitting = workingA;
						// pop workingA off queue
						workingA = a.poll();
						count_a++;
					}
					if (workingC.backoff == 0) {
						transmitting = workingC;
						// pop workingC off queue
						workingC = c.poll();
						count_c++;
					}

				}
				transmitting.updateCounter(channelBusy, true);
			} else {
				workingA.collisionUpdate();
				workingC.collisionUpdate();
				// decrement slot counter
				--current_slot;
				collision_count++;
			}

			if(transmitting.ack == 0) {
		        transmitting = null;
		        channelBusy = false;
		    }
			
			// increment slot counter
			++current_slot;
		}

	}

	public void uniformDistribution(double[] aInp, double[] cInp) {
		double aDistribution = (double) 1 / aInp.length;
		double cDistribution = (double) 1 / cInp.length;

		for (int i = 0; i < aInp.length; i++)
			aInp[i] = (i + 1) * aDistribution;

		for (int i = 0; i < cInp.length; i++)
			cInp[i] = (i + 1) * cDistribution;

	}

	public void computeSeries(double[] aInp, double[] cInp) {

		// Compute the Series values
		for (int i = 0; i < aInp.length; i++)
			aInp[i] = (-1) * (1 / aLamb) * Math.log(1 - aInp[i]);

		for (int i = 0; i < cInp.length; i++)
			cInp[i] = (-1) * (1 / cLamb) * Math.log(1 - cInp[i]);

		// Divide by 20 microseconds
		for (int i = 0; i < aInp.length; i++)
			aInp[i] = Math.ceil(aInp[i] / SLOT_DURATION);

		for (int i = 0; i < cInp.length; i++)
			cInp[i] = Math.ceil(cInp[i] / SLOT_DURATION);

		// Add the previous numbers together
		for (int i = 1; i < aInp.length; i++)
			aInp[i] += aInp[i - 1];

		for (int i = 1; i < cInp.length; i++)
			cInp[i] += cInp[i - 1];

	} // end computeSeries

	public void initPackets(Queue<Packet> a, Queue<Packet> c, double[] aD, double[] cD) {
		for (int i = 0; i < aD.length; i++)
			a.add(new Packet(aD[i], 'a'));
		for (int i = 0; i < cD.length; i++)
			c.add(new Packet(cD[i], 'c'));
	}

}
