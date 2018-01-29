package org.usfirst.frc.team1155.robot;

public class PortMap {
		
	//*****************JOYSTICKS*****************//

	public static final int JOYSTICK_LEFT = 0;
	public static final int JOYSTICK_RIGHT = 1;
	
	//*******************DRIVE*******************//
	
	public static final int DRIVE_FRONT_LEFT_TALON = 3;
//	public static final int DRIVE_MIDDLE_LEFT_TALON = 2;
	public static final int DRIVE_BACK_LEFT_TALON = 4;
	public static final int DRIVE_FRONT_RIGHT_TALON = 1;
//	public static final int DRIVE_MIDDLE_RIGHT_TALON = 5;
	public static final int DRIVE_BACK_RIGHT_TALON = 2;
	
	public static final int[] GEAR_SHIFTER_SOLENOID = {3,4};

	//*******************LIFT********************//	
	
	public static final int LIFT_LEFT_TALON = 8;
	public static final int LIFT_RIGHT_TALON = 9;
	public static final int LIFT_REVERSE_LEFT_TALON = 8;
	public static final int LIFT_REVERSE_RIGHT_TALON = 9;
	
	//*****************INTAKE*******************//	
	
	public static final int INTAKE_LEFT_TALON = 7;
	public static final int INTAKE_RIGHT_TALON = 6;
	public static final int INTAKE_ARM_LEFT_TALON = 7;
	public static final int INTAKE_ARM_RIGHT_TALON = 6;
	
	public static final int[] INTAKE_SOLENOID_LEFT = {0, 7};
	public static final int[] INTAKE_SOLENOID_RIGHT = {2, 5};
	
	//*****************CLIMBER******************//	
	
	public static final int LEFT_CLIMB_TALON = 7;
	public static final int RIGHT_CLIMB_TALON = 6;
	public static final int TILT_CLIMB_SERVO = 1;

}