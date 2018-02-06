package api.positioning;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1155.robot.Timer;

import java.util.ArrayList;

public class AccelerometerPositioning {
	private Timer timer;
    private double x = 0;
    private double y = 0;
    private double prevVx = 0;
    private double prevVy = 0;
    private double vx = 0;
    private double vy = 0;
    private double prevAx = 0;
    private double prevAy = 0;
    private short[] shortArr;
    private double ax,ay,dt;
    private final double mpsps = 9.80665;
    private final double ftpsps = 32.185039370079;
    private PigeonIMU accelerometer;
    private final double ALPHA = 0;

    private ArrayList<Double> sampleVX = new ArrayList<>();
    private ArrayList<Double> sampleVY = new ArrayList<>();
    private double referenceVX = 0;
    private double referenceVY = 0;

    private final int ZERO_TOLERANCE = 5;
    private final int SAMPLE_SIZE = 10;

    public AccelerometerPositioning(Timer t, PigeonIMU pigeon) {
        timer = t;
        accelerometer = pigeon;
        shortArr = new short[3];
    }

    
    private double XWMAX(double raw) {
    	if (prevAx == 0) {
    		prevAx = raw;
    	}
    	double a = prevAx + ALPHA*(raw - prevAx);
    	prevAx = a;
    	return a;
    }
    
    private double XWMAY(double raw) {
    	if (prevAx == 0) {
    		prevAx = raw;
    	}
    	double a = prevAx + ALPHA*(raw - prevAx);
    	prevAx = a;
    	return a;
    }
    
    public void updatePosition() {
    	accelerometer.getBiasedAccelerometer(shortArr);
        ax = (shortArr[0] / 16384.0) * ftpsps;
        ay = (shortArr[1] / 16384.0) * ftpsps;

       // ax = XWMAX(ax);
       // ay = XWMAY(ay);
        
        SmartDashboard.putNumber("shortArr[0]", shortArr[0]);
        SmartDashboard.putNumber("shortArr[1]", shortArr[1]);
        SmartDashboard.putNumber("prevVy", prevVy);
        SmartDashboard.putNumber("prevVx", prevVx);
        
        
        if (Math.abs(ax) <= 0.2)
        	ax = 0;
        if (Math.abs(ay) <= 0.2)
        	ay = 0;
        
/*
        if (Math.abs(vx) <= 0.005 * ftpsps)
        	vx = 0;
        if (Math.abs(vy) <= 0.005 * ftpsps)
        	vy = 0;*/
        /*
        if(prevAx == 0 && ax == 0)
        	vx /= 2;

        if(prevAy == 0 && ay == 0)
        	vy /= 2;
        */
        //dt = (timer.getTimeDifference() / 1000);
        dt = 0.02;
        
        SmartDashboard.putNumber("dt", dt);
        vx += (dt * ax);
        vy += (dt * ay);

        sampleVX.add(vx);
        sampleVY.add(vy);
        vx -= referenceVX;
	vy -= referenceVY;

        x += (dt * vx);
        y += (dt * vy);

        prevAx = ax;
        prevAy = ay;

        prevVx = vx;
        prevVy = vy;
    }

    public void setReferences(){
        int maxCountX = 0;
    	int maxCountY = 0;
    	double modeX = sampleVX.get(0);
    	double modeY = sampleVY.get(0);
	    
    	for(int i = 0; i < sampleVX.size(); i++){
    	    int xc = 0;
    	    int yc = 0;
    	    for(int j = 0; j < sampleVX.size(); j++){
    	        if(sampleVX.get(i) == sampleVX.get(j)) xc++;
    		if(sampleVY.get(i) == sampleVY.get(j)) yc++;
    	    }
    	    if(xc > maxCountX){
    		maxCountX = xc;
    		modeX = sampleVX.get(i);
    	    }
    	    if(yc > maxCountY){
    		maxCountY = yc;
    		modeY = sampleVY.get(i);
    	    }
    	}
    	if(maxCountX >= ZERO_TOLERANCE){
    	    referenceVX = modeX;
    	}
    	if(maxCountY >= ZERO_TOLERANCE){
    	    referenceVY = modeY;
    	}
    }
    
    public double getAx() {
    	return prevAx;
    }
    
    public double getAy() {
    	return prevAy;
    }
    
    public double getVy() {
    	return prevVy;
    }
    
    public double getVx() {
    	return prevVx;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
