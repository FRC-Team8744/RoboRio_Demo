// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
// import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This sample program shows how to control a motor using a joystick. In the operator control part
 * of the program, the joystick is read and the value is written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and motor controller inputs also range from -1 to 1
 * making it easy to work together.
 *
 * <p>In addition, the encoder value of an encoder connected to ports 0 and 1 is consistently sent
 * to the Dashboard.
 */
public class Robot extends TimedRobot {
  private static final int kMotorPort = 0;
  private static final int kJoystickPort = 0;
  // private static final int kEncoderPortA = 0;
  // private static final int kEncoderPortB = 1;

  private PWMSparkMax m_motor;
  private Joystick m_joystick;
  // private Encoder m_encoder;

  // Initializes an AnalogInput on port 0
  AnalogInput analog0 = new AnalogInput(0);
  DigitalOutput digital1 = new DigitalOutput(1);

  private double m_setpoint;

  @Override
  public void robotInit() {
    m_motor = new PWMSparkMax(kMotorPort);
    m_joystick = new Joystick(kJoystickPort);
    // m_encoder = new Encoder(kEncoderPortA, kEncoderPortB);
    // Use SetDistancePerPulse to set the multiplier for GetDistance
    // This is set up assuming a 6 inch wheel with a 360 CPR encoder.
    // m_encoder.setDistancePerPulse((Math.PI * 6) / 360.0);

    digital1.enablePWM(0.5);
    digital1.setPWMRate(50.0);  // 50 Hz or 1/20ms

  }

  /*
   * The RobotPeriodic function is called every control packet no matter the
   * robot mode.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Analog Voltage", analog0.getVoltage());
  }

  @Override
  public void teleopPeriodic() {
    m_setpoint = m_joystick.getY();

    SmartDashboard.putNumber("PWM outputEncoder", m_setpoint);
    m_motor.set(m_setpoint);
    digital1.updateDutyCycle(m_setpoint);
  }
}
