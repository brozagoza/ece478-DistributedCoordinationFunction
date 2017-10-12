package ece478.proj1.resources;

import java.util.Random;

public class Packet {

	final int CW_MAX = 1024; // slots
	final int CW_INITIAL = 4;

	private int startTime; // starting time of packet
	private char channel; // channel this packet came from
	private int collisionCount;
	private boolean rtsCtsEnabled;
	private boolean inTransmission;

	// TODO: Add RTS and CTS and specify in the constructor if these are necessary
	// following slot times happen in this order:
	private int difs;
	private int backoff;
	private int rts;
	private int cts;
	private int data;
	private int sifs;
	private int ack;

	public Packet(int startTime, char channel, boolean rtsCtsEnabled) {
		this.startTime = startTime;
		this.channel = channel;
		this.collisionCount = 0;
		
		this.difs = 4;
		this.backoff = randInt(CW_INITIAL);
		this.data = 100;
		this.sifs = 1;
		this.ack = 1;
		
		// RTS and CTS Enabled ?
		if (rtsCtsEnabled) {
			this.rtsCtsEnabled = true;
			this.rts = 1;
			this.cts = 1;
		} else {
			this.rtsCtsEnabled = false;
			rts = 0;
			cts = 0;
		}
	} // end constructor

	// GETTERS ******
	public char getChannel() {
		return channel;
	}

	public int getStartTime() {
		return startTime;
	}
	
	public boolean inTransit() {
		return inTransmission;
	}
	
	public boolean transmissionComplete() {
		return ack == 0;
	}
	
	public void stopTransmission() {
		inTransmission = false;
	}
	// *******
	

	// helper funct to find randInt from 0 to max exclusive
	private int randInt(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}

	// If there was a collision, update the backoff value of this packet
	public void collision() {
		data = 100;
		inTransmission = false;
		
		++collisionCount;
		if (rtsCtsEnabled)
			rts = 1;
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
		
		if (rts > 0) {
			rts--;
			return false;
		}

		return true;
	}

	// Returns false as long as Data, SIFS, and ACK are being transmitted. Returns
	// TRUE once the data is complete.
	public boolean transmit() {
		inTransmission = true;
		
		if (cts > 0) {
			--cts;
			return false;
		}
		
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
