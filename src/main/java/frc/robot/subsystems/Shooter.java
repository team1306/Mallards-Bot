// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import frc.robot.Constants.ShooterConstants;

/** Class to run the rollers over CAN */
public class Shooter extends SubsystemBase {
  private final SparkMax shooterMotor1;
  private final SparkMax shooterMotor2;

  public Shooter() {
    // Set up the roller motor as a brushed motor
    shooterMotor1 = new SparkMax(ShooterConstants.SHOOTER_MOTOR_ID_1, MotorType.kBrushed);
    shooterMotor2 = new SparkMax(ShooterConstants.SHOOTER_MOTOR_ID_2, MotorType.kBrushed);
    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    shooterMotor1.setCANTimeout(250);
    shooterMotor2.setCANTimeout(250);

    // Create and apply configuration for roller motor. Voltage compensation helps
    // the roller behave the same as the battery
    // voltage dips. The current limit helps prevent breaker trips or burning out
    // the motor in the event the roller stalls.
    SparkMaxConfig shooterConfig = new SparkMaxConfig();
    shooterConfig.voltageCompensation(ShooterConstants.SHOOTER_MOTOR_VOLTAGE_COMP);
    shooterConfig.smartCurrentLimit(ShooterConstants.SHOOTER_MOTOR_CURRENT_LIMIT);
    shooterMotor1.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    shooterConfig.follow(shooterMotor1.getDeviceId());
    shooterMotor2.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
  }

  // Command to run the roller with joystick inputs
  public Command runShooter(Shooter shooter, DoubleSupplier speed) {
    return Commands.run(
        () -> shooterMotor1.set(speed.getAsDouble()), shooter);
  }

}
