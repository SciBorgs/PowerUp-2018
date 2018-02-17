package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//TODO: change joystick button numbers

	public static Joystick leftJoystick = new Joystick(PortMap.JOYSTICK_LEFT);
	public static Joystick rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);
	public static XboxController xbox = new XboxController(PortMap.XBOX);
	
//	
	public static Button extendClimber = new JoystickButton(xbox, PortMap.XBOX_B);
	public static Button stopClimber = new JoystickButton(xbox, PortMap.XBOX_X);
//	
//	public static Button gearShifter = new JoystickButton(leftJoystick, 2);
	public static Button xboxGearShifter = new JoystickButton(xbox, PortMap.XBOX_STICK_RIGHT_BUTTON);
	
	//public static Button tiltClimb = new JoystickButton(rightJoystick, 3);

	//public static Button intakeArmControl = new JoystickButton(rightJoystick, 1);
	//public static Button xboxIntakeArmControl = new JoystickButton(rightJoystick, 9);
	
	public static Button driveStraightButton = new JoystickButton(xbox, PortMap.XBOX_Y);
	//public static Button pidAngleTest = new JoystickButton(leftJoystick, 4);
	public static Button driveDistTest = new JoystickButton(xbox, PortMap.XBOX_BUMPER_LEFT);
	public static Button driving = new JoystickButton(xbox, PortMap.XBOX_BUMPER_RIGHT);
	//public static Button leftArmIntake = new JoystickButton(leftJoystick, 1);
	
//	public static Button intakeCube = new JoystickButton(leftJoystick, 3);
//	public static Button placeOutCube = new JoystickButton(leftJoystick, 3);
//	public static Button shootOutCube = new JoystickButton(leftJoystick, 3);
//	
	public OI() {
//		intakeArmControl.toggleWhenPressed(new ToggleArmCommand());
		//xboxIntakeArmControl.toggleWhenPressed(new ToggleArmCommand());
		
//		gearShifter.toggleWhenPressed(new ToggleGearCommand());
		
		xboxGearShifter.toggleWhenActive(new ToggleGearCommand());
		//tiltClimb.whenPressed(new ToggleClimbCommand());
//		tiltClimb.toggleWhenPressed(new ToggleClimbCommand());
		
//		pidAngleTest.whenPressed(new TurnToDegreeCommand(90));
//		pidAngleTest.whenReleased(new WestCoastDriveCommand(leftJoystick, rightJoystick));
		driveDistTest.whileHeld(new DriveDistanceCommand(6.0833));
		//driving.whileHeld(new WestCoastDriveCommand(xbox));
//		leftArmIntake.whileHeld(new AdjustCubeRotationCommand(0));
		//driveDistTest.whenReleased(new WestCoastDriveCommand(xbox));

//		intakeCube.whileActive(new PlaceIntakeCommand());
//		placeOutCube.whileActive(new PlaceOutputCommand());
//		shootOutCube.toggleWhenPressed(new ShootCommand());
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.g
	// button.whenReleased(new ExampleCommand());
	
}