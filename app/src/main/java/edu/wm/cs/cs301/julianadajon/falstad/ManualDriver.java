package edu.wm.cs.cs301.julianadajon.falstad;

import android.os.Message;

import edu.wm.cs.cs301.julianadajon.UI.PlayActivity;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Turn;
import edu.wm.cs.cs301.julianadajon.generation.Distance;

/**
 * The Manual Driver directly interacts with the Robot.  It takes key inputs from a key listener, 
 * and then it instructs the robot to move or rotate in the given direction.  
 * @author julianadajon
 *
 */

public class ManualDriver implements RobotDriver {

	Robot robot;
	int width;
	int height;
	Distance distance; 
	
	public ManualDriver() {
		
	}
	
	@Override
	public void setRobot(BasicRobot r) {
		// TODO Auto-generated method stub
		robot = r; 
		
	}

	@Override
	public void setDimensions(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}

	@Override
	public void setDistance(Distance distance) {
		// TODO Auto-generated method stub
		this.distance = distance;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		// TODO Auto-generated method stub
		if (robot.isAtExit()){
			return true;
		}
		else { return false; }

	}

	@Override
	public float getEnergyConsumption() {
		// TODO Auto-generated method stub
		PlayActivity.h.sendMessage(Message.obtain(PlayActivity.h, 2500 - Math.round(robot.getBatteryLevel())));
		return 2500 - robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		return robot.getPathLength();
	}

	/**
	 * This method takes key inputs from the key listener and directs the robot based on such inputs
	 * @param key
	 */


	@Override
	public void keyDown(int key) {
		System.out.println("hit key down in manual driver");
		//move forward 1 step
		if (key == 'k'){
			robot.move(1, true);
			getEnergyConsumption();
			System.out.println("hit manual driver");
		}
		//rotate left
		if (key == 'h'){
			robot.rotate(Turn.LEFT);
			getEnergyConsumption();
		}
		//rotate right
		if (key == 'l' ){
			robot.rotate(Turn.RIGHT);
			getEnergyConsumption();
		}
		//move backwards one step
		if (key == 'j'){
			robot.rotate(Turn.LEFT);
			getEnergyConsumption();
			robot.rotate(Turn.LEFT);
			getEnergyConsumption();
//			robot.move(1, true);
//			getEnergyConsumption();
		}

	}

	@Override
	public Boolean atExit() {
		return null;
	}

	@Override
	public void setPlayActivity(PlayActivity p) {

	}

	@Override
	public void interrupt() {

	}

}
