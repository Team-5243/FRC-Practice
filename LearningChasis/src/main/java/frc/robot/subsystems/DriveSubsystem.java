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
  
  CANSparkMax frontLeft, frontRight, backLeft, backRight;
  
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    frontLeft = new CANSparkMax(Constants.FRONTLEFT, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.FRONTRIGHT, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACKLEFT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACKRIGHT, MotorType.kBrushless);

    backRight.follow(frontRight);
    backLeft.follow(frontLeft);
  }

  public void setMotors(double right, double left) {
    frontLeft.set(left);
    frontRight.set(right);
    backLeft.set(left);
    backRight.set(right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
