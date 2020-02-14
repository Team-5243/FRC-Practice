/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final AHRS m_gyro = new AHRS();

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(this);

  private final XboxController m_controller = new XboxController(Constants.CONTROLLER);

  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_controller);

  private final JoystickButton m_aButton = new JoystickButton(m_controller, 1);
  private final JoystickButton m_bButton = new JoystickButton(m_controller, 2);
  private final JoystickButton m_xButton = new JoystickButton(m_controller, 3);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_aButton.whenPressed(new DriveStraightCommand(m_driveSubsystem, 1d, 250d));
    m_bButton.whenPressed(new TurnPIDCommandManual(m_driveSubsystem, m_controller, 90d, true));
    m_xButton.whenPressed(new TurnCommand(m_driveSubsystem, 0.3d, 90d));
  }

  public AHRS getGyro() {
    return(m_gyro);
  }

  public XboxController getController() {
    return m_controller;
  }

  public DriveSubsystem getDriveSubsystem() {
    return m_driveSubsystem;
  }
  public DriveCommand getDriveCommand() {
    return m_driveCommand;
  }
}
