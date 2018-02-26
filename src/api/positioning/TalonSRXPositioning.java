package api.positioning;

import api.Position;

import org.usfirst.frc.team1155.robot.Robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXPositioning {
    public TalonSRX leftTalon, rightTalon;
    public static final double RATIO = 4096; // 1 wheel rotation is 4096 encoder ticks
    public static final double RADIUS = 3;   // wheel radius is 3 inches

    public Position position;

    public TalonSRXPositioning(Position position, TalonSRX leftTalon, TalonSRX rightTalon) {
        this.position = position;
        this.leftTalon = leftTalon;
        this.rightTalon = rightTalon;

        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1);
    	leftTalon.getSensorCollection().setPulseWidthPosition(0, 1);
        rightTalon.getSensorCollection().setPulseWidthPosition(0, 1);

    }

    public void updatePosition() {        // (position / 1024) * 2 pi r
        //double distance = (((leftTalon.getSelectedSensorPosition(0) + rightTalon.getSelectedSensorPosition(0)) / 2) / RATIO) * (2 * Math.PI * RADIUS);
//        double distance = (rightTalon.getSelectedSensorPosition(0) / RATIO) * Robot.driveSubsystem.ENC_WHEEL_RATIO * (2 * Math.PI * Robot.driveSubsystem.ENC_WHEEL_RATIO);

		double dist = Robot.driveSubsystem.getEncPosition();
        // have to replace this with the distance values
        position.setX(position.getX() + dist * Math.cos(Math.toRadians(Robot.driveSubsystem.getPigeonAngle())));
        position.setY(position.getY() + dist * Math.sin(Math.toRadians(Robot.driveSubsystem.getPigeonAngle())));
       // System.out.println(position);
    }
}
