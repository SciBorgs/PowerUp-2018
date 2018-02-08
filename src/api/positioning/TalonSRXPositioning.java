package api.positioning;

import api.Position;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXPositioning {
    private TalonSRX leftTalon, rightTalon;
    private static final double RATIO = 1024; // 1 wheel rotation is 1024 encoder ticks
    private static final double RADIUS = 3;   // wheel radius is 3 inches

    private Position position;

    public TalonSRXPositioning(Position position, TalonSRX leftTalon, TalonSRX rightTalon) {
        this.position = position;
        this.leftTalon = leftTalon;
        this.rightTalon = rightTalon;

        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);
    	leftTalon.getSensorCollection().setPulseWidthPosition(0, 1);
        rightTalon.getSensorCollection().setPulseWidthPosition(0, 1);

    }

    public void updatePosition() {        // (position / 1024) * 2 pi r
        //double distance = (((leftTalon.getSelectedSensorPosition(0) + rightTalon.getSelectedSensorPosition(0)) / 2) / RATIO) * (2 * Math.PI * RADIUS);
        double distance = (rightTalon.getSelectedSensorPosition(0) / RATIO) * (2 * Math.PI * RADIUS);

        // have to replace this with the distance values
        position.setX(position.getX() + distance);// * Math.cos(Math.toRadians(position.getDirection())));
        position.setY(position.getY() + distance);// * Math.sin(Math.toRadians(position.getDirection())));
        System.out.println(position);
    }
}
