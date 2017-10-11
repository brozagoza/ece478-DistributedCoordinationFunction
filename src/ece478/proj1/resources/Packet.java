package ece478.proj1.resources;

import java.util.Random;

public class Packet {

	final int CW_MAX = 1024; // slots
	final int CW_INITIAL = 4;

	private int startTime; // starting time of packet
	private char channel; // channel this packet came from
	private int collisionCount;

	// TODO: Add RTS and CTS and specify in the constructor if these are necessary
	// following slot times happen in this order:
	private int difs;
	private int backoff;
	private int data;
	private int sifs;
	private int ack;

	public Packet(int startTime, char channel) {
		this.startTime = startTime;
		this.channel = channel;
		this.collisionCount = 0;

		this.difs = 4;
		this.backoff = randInt(CW_INITIAL);
		this.data = 100;
		this.sifs = 1;
		this.ack = 1;
	} // end constructor

	// GETTERS ******
	public char getChannel() {
		return channel;
	}

	public int getStartTime() {
		return startTime;
	}
	// *******

	// helper funct to find randInt from 0 to max exclusive
	private int randInt(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}

	// If there was a collision, update the backoff value of this packet
	public void collision() {
		++collisionCount;
		int newCW = (int) (Math.pow(2, collisionCount) * 4);
		newCW = Math.min(newCW, CW_MAX);
		backoff = randInt(newCW);
	}

	// Returns false as long as DIFS and Backoff are being decremented. Returns TRUE
	// once it is ready to be sent.
	public boolean isReady() {
		if (difs > 0) {
			difs--;
			return false;
		}

		if (backoff > 0) {
			backoff--;
			return false;
		}

		return true;
	}

	public void resetDifs() {
		difs = 4;
	}

	// Returns false as long as Data, SIFS, and ACK are being transmitted. Returns
	// TRUE once the data is complete.
	public boolean transmit() {
		if (data > 0) {
			--data;
			return false;
		}

		if (sifs > 0) {
			--sifs;
			return false;
		}

		if (ack > 0) {
			--ack;
			return false;
		}

		return true;
	}

}
