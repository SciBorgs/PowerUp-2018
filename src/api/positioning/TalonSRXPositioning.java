package api.positioning;

import api.Position;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXPositioning {
    private TalonSRX leftTalon, rightTalon;
    private static final double RATIO = 25/4;
    private static final double RADIUS = 3;
    // radius is 3
    // 4 wheel rotations is 25 encoder rotations
    // 1 wheel rotation is 6.25 encoder ticks
    // circumference is 2pi*r

    private AccelerometerPositioning accelerometerPositioning;
    private Position position;



    public TalonSRXPositioning(Position position, TalonSRX leftTalon, TalonSRX rightTalon) {
        this.position = position;
        this.leftTalon = leftTalon;
        this.rightTalon = rightTalon;

        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);
    }

    public void updatePosition() {        // (position / 6.25) * 2 pi r
        double distance = (((leftTalon.getSelectedSensorPosition(0) + rightTalon.getSelectedSensorPosition(0)) / 2) / RATIO) * (2 * Math.PI * RADIUS);
        leftTalon.getSensorCollection().setPulseWidthPosition(0, 1);
        rightTalon.getSensorCollection().setPulseWidthPosition(0, 1);
        // have to replace this with the distance values
        position.setX(position.getX() + distance * Math.cos(Math.toRadians(position.getDirection())));
        position.setY(position.getY() + distance * Math.sin(Math.toRadians(position.getDirection())));
        System.out.println(position);
    }
}
