/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1155.robot;

import java.io.File;
import java.io.IOException;

import org.usfirst.frc.team1155.robot.commands.*;
import org.usfirst.frc.team1155.robot.commands.autoCommands.AutonomousCommandGroup;
import org.usfirst.frc.team1155.robot.subsystems.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU;

import api.AutonomousRoutine;
import api.Client;
import api.Position;
import api.positioning.PositioningHandler;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static OI m_oi;
	
	public static DriveSubsystem driveSubsystem;
	public static LiftSubsystem liftSubsystem;
	public static ClimbSubsystem climbSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static AutonomousSubsystem autonomousSubsystem;
	public static VisionSubsystem visionSubsystem;
//	public static ADXRS450_Gyro Gyro;
	public static File file;
	public static AutonomousRoutine autonomousRoutine;
	public static int PointTwoMeters[];
	
	public static PigeonIMU pigeon;
	public static BuiltInAccelerometer accel;
	
	public static Timer timer;

	public static Client client;

	public static short[] shortArr;

	public static Position position;
	public static PositioningHandler positioningHandler;
	
	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveSubsystem = new DriveSubsystem();
		liftSubsystem = new LiftSubsystem(1.0, 0.1, 0.1);
		climbSubsystem = new ClimbSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		visionSubsystem = new VisionSubsystem();
		autonomousSubsystem = new AutonomousSubsystem();
		m_oi = new OI();
		m_chooser.addDefault("Auto Position 1", 1);
		m_chooser.addObject("Auto Position 2", 2);
		m_chooser.addObject("Auto Position 3", 3);
		
		pigeon = new PigeonIMU(driveSubsystem.talonWithPigeon);
//		//System.out.println("entering calibration mode");
		pigeon.setYaw(0.,0);

		
		
//		position = new Position();
//		positioningHandler = new PositioningHandler(position, driveSubsystem.frontRightMotor, driveSubsystem.backLeftMotor);
		SmartDashboard.putNumber("angleToTurn", 90);
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putNumber("P Value", 1.0);
		SmartDashboard.putNumber("I Value", 0);
		SmartDashboard.putNumber("D Value", 0.6);
		SmartDashboard.putNumber("Dist To Drive", 0);
		SmartDashboard.putNumber("AngleToTurn", 0);
		//Gyro = new ADXRS450_Gyro();
        try {
            client = new Client();
        } catch (IOException e) {
            System.out.println("could not start client");
        }
	/*	file = new File("test.path");
		try {
			path = new Path(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/ 
		timer = new Timer();
		accel = new BuiltInAccelerometer();
		PointTwoMeters = new int[2];
		shortArr = new short[3];
		PointTwoMeters[0] = 0;
		PointTwoMeters[1] = 80;
		//pigeon.enterCalibrationMode(CalibrationMode.BootTareGyroAccel, 3000);
		SmartDashboard.putNumber("Intake Speed", 0.45);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		pigeon.setYaw(0.,0);

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();

		m_autonomousCommand = new AutonomousCommandGroup(gameData, m_chooser.getSelected());
		Robot.driveSubsystem.resetEncoders();
		Robot.liftSubsystem.resetEncoders();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			System.out.println("startinggg...");
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//plane.updatePosition();

		//System.out.println(plane.getX() + ", " + plane.getY());
		intakeSubsystem.leftArmMotor.set(ControlMode.PercentOutput, .2);
		
		SmartDashboard.putNumber("Left Encoder Feet", Robot.driveSubsystem.getLeftEncPosition());
		SmartDashboard.putNumber("Left Encoder Ticks", Robot.driveSubsystem.getLeftEncPositionTicks());	
		
		SmartDashboard.putNumber("Right Encoder Feet", Robot.driveSubsystem.getRightEncPosition());
		SmartDashboard.putNumber("Right Encoder Ticks", Robot.driveSubsystem.getRightEncPosition());
		
		SmartDashboard.putNumber("Avg. enc feet", Robot.driveSubsystem.getEncPosition());
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when 
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		/*DoubleSolenoid ds = new DoubleSolenoid(5, 7);
		if(ds.get() == DoubleSolenoid.Value.kForward) {
			ds.set(DoubleSolenoid.Value.kReverse);
		}else if (ds.get() == DoubleSolenoid.Value.kReverse){
			ds.set(DoubleSolenoid.Value.kForward);
		}*/
		
//		pigeon.setYaw(0.,0);
		Robot.driveSubsystem.resetEncoders();
		Robot.liftSubsystem.resetEncoders();
		//System.out.println(position.getX() + ", " + position.getY());
//		new WestCoastDriveCommand(OI.leftJoystick, OI.rightJoystick).start();
		new DriveStraightCommand(OI.xbox).start();
		new WestCoastDriveCommand(OI.xbox).start();
		new PlaceCommand(OI.xbox).start();
		new CascadeLiftCommand(OI.xbox).start();
		new ClimbCommand().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		pigeon.getYawPitchRoll(anglesYPR);
//		positioningHandler.updatePosition();
//		SmartDashboard.putNumber("Xceleration", plane.getAx());
//		SmartDashboard.putNumber("Yceleration", plane.getAy());
//		SmartDashboard.putNumber("Xvelocity", plane.getVx());
//		SmartDashboard.putNumber("Yvelocity", plane.getVy());
		//System.out.println(driveSubsystem.getPigeonRoll());
		SmartDashboard.putNumber("Left Encoder Feet", Robot.driveSubsystem.getLeftEncPosition());
		SmartDashboard.putNumber("Left Encoder Ticks", Robot.driveSubsystem.getLeftEncPositionTicks());	
		
		SmartDashboard.putNumber("Right Encoder Feet", Robot.driveSubsystem.getRightEncPosition());
		SmartDashboard.putNumber("Right Encoder Ticks", Robot.driveSubsystem.getRightEncPosition());
		
		SmartDashboard.putNumber("Avg. enc feet", Robot.driveSubsystem.getEncPosition());
		
		SmartDashboard.putNumber("PigeonRoll", Robot.driveSubsystem.getPigeonAngle());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		//System.out.println(position.getX() + ", " + position.getY());
	}
}
