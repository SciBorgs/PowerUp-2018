package org.usfirst.frc.team1155.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class DesCartesianPlane {
	Timer timer;
    private double x = 0;
    private double y = 0;
    private double prevVx = 0;
    private double prevVy = 0;
    private double vx = 0;
    private double vy = 0;
    private double prevAx = 0;
    private double prevAy = 0;
    private BuiltInAccelerometer accelerometer;

    DesCartesianPlane(Timer t, BuiltInAccelerometer a) {
        timer = t;
        accelerometer = a;
    }

    public void updatePosition() {
        double ax = accelerometer.getX() * 9.80665;
        double ay = accelerometer.getX() * 9.80665;
        double dt = (timer.getTimeDifference() / 1000);
        vx += (0.5 * dt * (prevAx + ax));
        vy += (0.5 * dt * (prevAy + ay));

        x += (0.5 * dt * (prevVx + vx));
        y += (0.5 * dt * (prevVy + vy));

        prevAx = ax;
        prevAy = ay;

        prevVx = vx;
        prevVy = vy;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
