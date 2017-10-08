package ece478.proj1;

import java.util.Random;

public class Packet {

	final int MAXCW = 1024;
	double value;
	int xTime;
	int collisions;
	int backoff;
	int difs;
	int sifs;
	int ack;
	char channel;

	public Packet(double value, char channel) {
		this.value = value;
		this.channel = channel;
		collisions = 0;
		backoff = 0;
		xTime = 100;
		difs = 2;
		sifs = 1;
		ack = 2;
	}

	public int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	public void collisionUpdate() {
		collisions++;
		int tempCW = (int) Math.pow(2, collisions) * 4;

		if (MAXCW < tempCW)
			tempCW = MAXCW;

		backoff = randInt(0, tempCW - 1);
	}

	public void updateCounter(boolean channelBusy, boolean transmitting) {
        if(difs >0)
            difs--;
        else if (backoff > 0) {
            if(!channelBusy)
                backoff--;
        }
        else if(transmitting) { 
            if(xTime > 0)
                xTime--;
            else if(sifs > 0)
                sifs--;
            else if(ack > 0)
                ack--;
        }
    }
	
	
}
