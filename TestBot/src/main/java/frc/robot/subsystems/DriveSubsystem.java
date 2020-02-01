/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  CANSparkMax leftServant, leftMaster, rightServant, rightMaster;
  
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    leftServant = new CANSparkMax(Constants.BACK_LEFT, MotorType.kBrushless);
    leftMaster = new CANSparkMax(Constants.FRONT_LEFT, MotorType.kBrushless);

    rightServant = new CANSparkMax(Constants.FRONT_RIGHT, MotorType.kBrushless);
    rightMaster = new CANSparkMax(Constants.BACK_RIGHT, MotorType.kBrushless);

    leftServant.follow(leftMaster);
    rightServant.follow(rightMaster);
  }

  public void tankDriveMotors(double leftPower, double rightPower) {
    leftMaster.set(leftPower);
    rightMaster.set(rightPower);
  }

  public void steerDrive(double drivePower, double steerPower) {
    leftMaster.set(drivePower - steerPower);
    rightMaster.set(drivePower + steerPower);
  }

  public void stop() {
    tankDriveMotors(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
