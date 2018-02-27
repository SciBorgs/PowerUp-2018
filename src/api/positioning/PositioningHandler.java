package api.positioning;

import api.Position;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc.team1155.robot.Robot;

import java.io.IOException;

public class PositioningHandler {
    // Encoder positioning
    private TalonSRX leftTalonSRX, rightTalonSRX;
    private Talon leftTalon, rightTalon;
    private Encoder leftEncoder, rightEncoder;

    private Object positioningHandler; // i was going to make an interface
    private AccelerometerPositioning accelerometerPositioning;
    private boolean SRXmode;

    public Position position;

    public boolean isGoingStraight() {
        if (SRXmode) {
            return leftTalonSRX.getSelectedSensorVelocity(0) == rightTalonSRX.getSelectedSensorVelocity(0);
        } else {
            return leftTalon.getSpeed() == rightTalon.getSpeed();
        }
    }

    public PositioningHandler() {
        this.accelerometerPositioning = new AccelerometerPositioning(Robot.timer, Robot.pigeon);
    }

    public PositioningHandler(Position position, TalonSRX leftTalonSRX, TalonSRX rightTalonSRX) {
        this();
        this.position = position;
        this.leftTalonSRX = leftTalonSRX;
        this.rightTalonSRX = rightTalonSRX;
        this.SRXmode = true;
        this.positioningHandler = new TalonSRXPositioning(position, leftTalonSRX, rightTalonSRX);
    }

    public PositioningHandler(Position position, Talon leftTalon, Talon rightTalon, Encoder leftEncoder, Encoder rightEncoder) {
        this();
        this.position = position;
        this.leftTalon = leftTalon;
        this.rightTalon = rightTalon;
        this.SRXmode = false;
        this.positioningHandler = new CimcoderPositioning(position, leftEncoder, rightEncoder);
    }

    public void updatePosition() {
        if (isGoingStraight()) {
            if (SRXmode) {
                ((TalonSRXPositioning)this.positioningHandler).updatePosition();
            } else {
                ((CimcoderPositioning)this.positioningHandler).updatePosition();
            }
        }
        System.out.println(position);

        // Send coordinates to the server
        Robot.client.setPosition(position);
        try {
            Robot.client.sendPosition();
        } catch (IOException e) {
            System.out.println("error sending position");
        }
    }
}
