package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class RunShooter extends Command {
    private Shooter shooter;
    private DoubleSupplier speed;

    public RunShooter(Shooter shooter, DoubleSupplier speed) {
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setSpeed(-speed.getAsDouble());
    }

    @Override
    public void end(boolean interruped) {
        shooter.setSpeed(0);
    }
}
