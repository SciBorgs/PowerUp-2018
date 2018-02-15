package org.usfirst.frc.team1155.robot;

public class PortMap {
		
	//*****************JOYSTICKS*****************//

	public static final int JOYSTICK_LEFT = 0;
	public static final int JOYSTICK_RIGHT = 1;
	
	//*******************DRIVE*******************//
	
	public static final int DRIVE_FRONT_LEFT_TALON = 24;
	public static final int DRIVE_MIDDLE_LEFT_TALON = 32;
	public static final int DRIVE_BACK_LEFT_TALON = 19;
	public static final int DRIVE_FRONT_RIGHT_TALON = 21;
	public static final int DRIVE_MIDDLE_RIGHT_TALON = 17;
	public static final int DRIVE_BACK_RIGHT_TALON = 22;
	
	public static final int[] GEAR_SHIFTER_SOLENOID = {0,1};

	//*******************LIFT********************//	
	
	public static final int LIFT_LEFT_TALON = 20;
	public static final int LIFT_LEFT_2_TALON = 30;
	public static final int LIFT_RIGHT_TALON = 31;
	public static final int LIFT_RIGHT_2_TALON = 28;
	
	public static final int[] LEFT_LIFT_ENCODER = {1, 2};
	public static final int[] RIGHT_LIFT_ENCODER = {3, 4};
	
	//*****************INTAKE*******************//	
	
	public static final int INTAKE_LEFT_TALON = 20;
	public static final int INTAKE_RIGHT_TALON = 31;
	public static final int INTAKE_ARM_LEFT_TALON = 30;
	public static final int INTAKE_ARM_RIGHT_TALON = 28;
	
	public static final int[] INTAKE_SOLENOID = {2,3};//{0, 1};
	
	//*****************CLIMBER******************//	
	
	public static final int LEFT_CLIMB_TALON = 7;
	public static final int RIGHT_CLIMB_TALON = 6;
	public static final int TILT_CLIMB_SERVO_LEFT = 0;
	public static final int TILT_CLIMB_SERVO_RIGHT = 1;

}