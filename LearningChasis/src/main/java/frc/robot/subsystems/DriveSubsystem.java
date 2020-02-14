/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {
  CANSparkMax frontLeft, frontRight, backLeft, backRight;
  AHRS gyro;
  Pose2d estimatedPose;
  Pose2d drivetrainPower;
  double lastPower;

  /**
   * Creates a new chassis.
   */
  public DriveSubsystem(RobotContainer robotContainer) {
    frontLeft = new CANSparkMax(Constants.FRONT_LEFT, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACK_LEFT, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.FRONT_RIGHT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACK_RIGHT, MotorType.kBrushless);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    gyro = robotContainer.getGyro();
    estimatedPose = new Pose2d();
    drivetrainPower = new Pose2d();
    lastPower = 0d;
  }

  public AHRS getGyro() {
    return gyro;
  }

  public Pose2d getPose() {
    return estimatedPose;
  }

  public void setMotors(double right, double left) {
    frontLeft.set(left);
    frontRight.set(-right);
  }

  public void steerDrive(double drivePower, double steerPower) {
    //setDrivetrainPower(new Pose2d(new Translation2d(drivePower, 0d), new Rotation2d(steerPower)));
    double normalization = Math.hypot(drivePower, steerPower) < 0.05 ? 0d : 1 / Math.hypot(drivePower, steerPower); // Math.sqrt(2);
    double power = (Math.abs(drivePower) + Math.abs(steerPower)) / 2d;
    double dt = 1 / 200d;
    double acceleration = (power - lastPower) / dt;
    if(acceleration > Constants.MAX_DRIVE_ACCELERATION_CAP) {
      power = Constants.MAX_DRIVE_ACCELERATION_CAP * dt + lastPower;
    } else if(acceleration < -Constants.MAX_DRIVE_ACCELERATION_CAP) {
      power = -Constants.MAX_DRIVE_ACCELERATION_CAP * dt + lastPower;
    }

    lastPower = power;

    frontLeft.set((drivePower + steerPower) * normalization * power);
    frontRight.set(-(drivePower - steerPower) * normalization * power);
  }

  public void stopMotors() {
    steerDrive(0d, 0d);
  }

  public Pose2d getDrivetrainPower() {
    return drivetrainPower;
  }

  public void setDrivetrainPower(Pose2d power) {
    drivetrainPower = power;
  }

  public double getDrivePower() {
    return drivetrainPower.getTranslation().getX();
  }

  public double getSteerPower() {
    return drivetrainPower.getRotation().getRadians();
  }

  public double getLeftDisplacement() {
    return frontLeft.getEncoder().getPosition() / Constants.NEO_ENCODER_PULSES_PER_REVOLUTION*
      (Constants.DRIVE_WHEEL_CIRCUMFERENCE * Constants.DRIVETRAIN_GEAR_RATIO);
  }

  @Override
  public void periodic() {
    double leftVelocity = (frontLeft.getEncoder().getVelocity()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*
      (Constants.DRIVE_WHEEL_CIRCUMFERENCE * Constants.DRIVETRAIN_GEAR_RATIO);
    double rightVelocity = (frontRight.getEncoder().getVelocity()/Constants.NEO_ENCODER_PULSES_PER_REVOLUTION)*
      (Constants.DRIVE_WHEEL_CIRCUMFERENCE * Constants.DRIVETRAIN_GEAR_RATIO);
    Pose2d negativePoseChange = new Pose2d(new Translation2d(-(leftVelocity + rightVelocity) / 2d, 0d), new Rotation2d((leftVelocity - rightVelocity) / (2d * Constants.DRIVE_WHEEL_SEPARATION_DISTANCE)));
    estimatedPose.minus(negativePoseChange);
  }
}
