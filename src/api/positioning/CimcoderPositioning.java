package api.positioning;

import api.Position;
import edu.wpi.first.wpilibj.Encoder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CimcoderPositioning {
    private Encoder leftEncoder, rightEncoder;
    private ScheduledExecutorService scheduledExecutorService;

    private Position position;

    public CimcoderPositioning(Position position, Encoder leftEncoder, Encoder rightEncoder) {
        this.position = position;
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;

        final double WHEEL_RADIUS = 2;

        leftEncoder.setDistancePerPulse(Math.PI * 2 * WHEEL_RADIUS);

        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
        }, 0, 1, TimeUnit.SECONDS);
    }
    public void updatePosition() {
        double distance = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
        leftEncoder.reset();
        rightEncoder.reset();

        position.setX(position.getX() + position.getX() * Math.cos(Math.toRadians(position.getDirection())));
        position.setY(position.getY() + position.getY() * Math.sin(Math.toRadians(position.getDirection())));
        System.out.println(position);
    }
}
