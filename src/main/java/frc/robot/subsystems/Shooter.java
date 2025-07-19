// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
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
  private final TalonFX shooterMotor1;
  private final TalonFX shooterMotor2;

  public Shooter() {
    // Set up the roller motor as a brushed motor
    shooterMotor1 = new TalonFX(ShooterConstants.SHOOTER_MOTOR_ID_1);
    shooterMotor2 = new TalonFX(ShooterConstants.SHOOTER_MOTOR_ID_2);

    shooterMotor2.setControl(new Follower(shooterMotor1.getDeviceID(), false));

    TalonFXConfiguration config = new TalonFXConfiguration();
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    config.CurrentLimits.SupplyCurrentLimit = 80;
    config.CurrentLimits.StatorCurrentLimit = 80;
    config.CurrentLimits.StatorCurrentLimitEnable = true;

    shooterMotor1.getConfigurator().apply(config.CurrentLimits);
    shooterMotor2.getConfigurator().apply(config.CurrentLimits);

  }

  @Override
  public void periodic() {
  }

  // Command to run the roller with joystick inputs
  public Command runShooter(Shooter shooter, DoubleSupplier speed) {
    return Commands.run(
        () -> shooterMotor1.set(-speed.getAsDouble()), shooter);
  }

}
