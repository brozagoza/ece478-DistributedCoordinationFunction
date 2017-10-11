package ece478.proj1.resources;

import java.util.LinkedList;
import java.util.Queue;


public class GenerateSeries {

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
	private Queue<Packet> aPacks;
	private Queue<Packet> cPacks;

	public GenerateSeries(int a, int c) {
		aLamb = a;
		cLamb = c;

		aPacks = new LinkedList<>();
		cPacks = new LinkedList<>();

		computeSeriesA();
		computeSeriesC();

	}

	public void computeSeriesA() {
		int runningCount = 0;
		for (int i = 0; i < SIMULATION_TIME * aLamb; i++) {
			int tempTime = 0;
			while (tempTime == 0)
				tempTime = (int) Math
						.ceil((((double) -1 / aLamb) * Math.log(1 - Math.random())) / (2 / Math.pow(10, 5)));

			runningCount += tempTime;
			Packet tempPacket = new Packet(runningCount, 'a');

			aPacks.add(tempPacket);
		}

	}

	public void computeSeriesC() {
		int runningCount = 0;

		for (int i = 0; i < SIMULATION_TIME * cLamb; i++) {
			int tempTime = 0;
			while (tempTime == 0)
				tempTime = (int) Math
						.ceil((((double) -1 / cLamb) * Math.log(1 - Math.random())) / (2 / Math.pow(10, 5)));

			runningCount += tempTime;
			Packet tempPacket = new Packet(runningCount, 'c');

			cPacks.add(tempPacket);
		}
	}

	public Queue<Packet> getAPacks() {
		return aPacks;
	}

	public Queue<Packet> getCPacks() {
		return cPacks;
	}

}
