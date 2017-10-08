package ece478.proj1;

public class Simulation {
	final int DATA_FRAME_SIZE = 1500;		// bytes
	final double SLOT_DURATION = 0.000020;	// seconds
	final double SIFS_DURATION = 0.000010;	// seconds
	final int CW_INITAL = 4;	// slots
	final int ACK_RTS_CTS_SIZE = 30;	// bytes
	final double DIFS_DURATION = 0.000040;	// seconds
	final int TRANSMISSION_RATE = 6;	// mbps
	final int CW_MAX = 1024;	// slots
	final int SIMULATION_TIME = 10;	// seconds
	
	private int aLamb;
	private int cLamb;
	
	public Simulation(int aLamb, int cLamb) {
		this.aLamb = aLamb;
		this.cLamb = cLamb;
	}
	
	// runsSimulation
	public void runSimulation() {
		double [] aNums = new double[aLamb*SIMULATION_TIME];
		double [] cNums = new double[cLamb*SIMULATION_TIME];
		
		uniformDistribution(aNums, cNums);
		
		computeSeries(aNums, cNums);
		
		
		
	}
	
	public void uniformDistribution(double [] aInp, double [] cInp) {
		double aDistribution = (double)1/aInp.length;
		double cDistribution = (double)1/cInp.length;
		
		for (int i = 0; i < aInp.length; i++)
			aInp[i] = (i+1)*aDistribution;
		
		for (int i = 0; i < cInp.length; i++)
			cInp[i] = (i+1)*cDistribution;
		
		
	}
	
	public void computeSeries(double [] aInp, double [] cInp) {
		
		// Compute the Series values
		for (int i = 0; i < aInp.length; i++)
			aInp[i] = (-1)*(1/aLamb)*Math.log(1-aInp[i]);
		
		for (int i = 0; i < cInp.length; i++)
			cInp[i] = (-1)*(1/cLamb)*Math.log(1-cInp[i]);
		
		// Add the previous numbers together
		for (int i = 1; i < aInp.length; i++)
			aInp[i] += aInp[i-1];
		
		for (int i = 1; i < cInp.length; i++)
			cInp[i] += cInp[i-1];
		
		// Divide by 20 microseconds
		for (int i = 0; i < aInp.length; i++)
			aInp[i] = Math.ceil(aInp[i]/SLOT_DURATION);
		
		for (int i = 0; i < cInp.length; i++)
			cInp[i] = Math.ceil(cInp[i]/SLOT_DURATION);
		
	} // end computeSeries
	
	
	
	
}
