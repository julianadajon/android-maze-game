package edu.wm.cs.cs301.julianadajon.falstad;

import android.os.Handler;
import android.os.Message;

import edu.wm.cs.cs301.julianadajon.UI.PlayActivity;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Direction;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Turn;
import edu.wm.cs.cs301.julianadajon.generation.Distance;
import edu.wm.cs.cs301.julianadajon.generation.MazeConfiguration;

/**
 * This class solves a randomly generated maze by using the wall follower algorithm
 * @author julianadajon
 *
 */

public class WallFollower implements RobotDriver{

	
	BasicRobot basicRobot;
	int width;
	int height;
	Distance distance;
	MazeController controller;
	MazeConfiguration container;
	private PlayActivity playActivity;
	Handler h;
	public WallFollower(){
		
	}
	
	@Override
	public void setRobot(BasicRobot r) {
		basicRobot = r;
		
	}
	

	@Override
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void setDistance(Distance distance) {
		this.distance = distance; 
		
	}


	//while robot is not at exit

	//if distanceToObstacle(Forward) > 0 and Dist(left) = 0:
	//move (1)

	//if distance(foward) = 0 and distance(left) = 0:
	//rotate right

	//if forward > 0 and left  = 0:
	//move 1

	//use else here instead of another if statement?***
	//if forward = 0 and left = 0:
	//rotate right
	//move forward 1

	//if forward = 0 and left = 0:
	//rotate right
	//move forward 1

//		while (basicRobot.isAtExit() == false && basicRobot.batteryLevel > 0){


	@Override
	public boolean drive2Exit() throws Exception{
		if (basicRobot.hasStopped() == true) {
			playActivity.lose();
			return true;
		}

		if (atExit() == true) {
			playActivity.win();
			return true;
		}

		h = new Handler();
		h.postDelayed( r, 100);
		return false;
	}


	Runnable r = new Runnable() {
		public void run() {
			int distforward = basicRobot.distanceToObstacle(Direction.FORWARD);
			int distleft = basicRobot.distanceToObstacle(Direction.LEFT);
			int distright = basicRobot.distanceToObstacle(Direction.RIGHT);
			int distbackward = basicRobot.distanceToObstacle(Direction.BACKWARD);


			System.out.println("distance to obstacle forward: " + distforward);
			System.out.println("distance to obstacle left: " + distleft);
			System.out.println("distance to obstacle right: " + distright);
			System.out.println("distance to obstacle backward: " + distbackward);

			//if there is no wall to my left or in front of me, turn right
			//add cases within if statement******
			if (basicRobot.distanceToObstacle(Direction.FORWARD) > 0
					&& basicRobot.distanceToObstacle(Direction.LEFT) > 0
					&& basicRobot.hasStopped == false) {
				basicRobot.rotate(Turn.LEFT);
				basicRobot.move(1, false);
			}

			//if there is wall to my left and nothing in front of me, move forward
			else if (basicRobot.distanceToObstacle(Direction.FORWARD) > 0
					&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
					&& basicRobot.hasStopped == false) {
				basicRobot.move(1, false);
			}

			//if there is a wall in front of me but not to my left
			else if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
					&& basicRobot.distanceToObstacle(Direction.LEFT) > 0
					&& basicRobot.hasStopped == false) {
				basicRobot.rotate(Turn.LEFT);
				basicRobot.move(1, false);
			}
			//if there is a wall to my left and in front of me, rotate right and move 1 (turn me around)
			else if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
					&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
					&& basicRobot.hasStopped == false) {

				basicRobot.rotate(Turn.RIGHT);

				//after rotating right, if there is a wall to my left and nothing in front of me,
				//move forward
				if (basicRobot.distanceToObstacle(Direction.FORWARD) > 0
						&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
						&& basicRobot.hasStopped == false) {
					basicRobot.move(1, false);
				}

				//if there is a wall to my left and in front of me, rotate right and move 1 (turn me around)
				if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
						&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
						&& basicRobot.hasStopped == false) {
					basicRobot.rotate(Turn.RIGHT);
					basicRobot.move(1, false);
				}
			}
			//if there is a wall to my left and in front of me, rotate right and move 1 (turn me around)
			else if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
					&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
					&& basicRobot.hasStopped == false) {
				basicRobot.rotate(Turn.RIGHT);
				basicRobot.move(1, false);
			}

			getEnergyConsumption();

			try {
				drive2Exit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	};



//	@Override
//	public boolean drive2Exit() throws Exception{
//		if (basicRobot.hasStopped() == true) {
//			throw new Exception();
//		}
//		//while robot is not at exit
//
//		//if distanceToObstacle(Forward) > 0 and Dist(left) = 0:
//			//move (1)
//
//		//if distance(foward) = 0 and distance(left) = 0:
//			//rotate right
//
//			//if forward > 0 and left  = 0:
//				//move 1
//
//			//use else here instead of another if statement?***
//			//if forward = 0 and left = 0:
//				//rotate right
//				//move forward 1
//
//		//if forward = 0 and left = 0:
//			//rotate right
//			//move forward 1
//
//		while (basicRobot.isAtExit() == false && basicRobot.batteryLevel > 0){
//			int distforward = basicRobot.distanceToObstacle(Direction.FORWARD);
//			int distleft = basicRobot.distanceToObstacle(Direction.LEFT);
//			int distright = basicRobot.distanceToObstacle(Direction.RIGHT);
//			int distbackward = basicRobot.distanceToObstacle(Direction.BACKWARD);
//
//
//			System.out.println("distance to obstacle forward: " + distforward);
//			System.out.println("distance to obstacle left: " + distleft);
//			System.out.println("distance to obstacle right: " + distright);
//			System.out.println("distance to obstacle backward: " + distbackward);
//
//			//if there is no wall to my left or in front of me, turn right
//				//add cases within if statement******
//			if (basicRobot.distanceToObstacle(Direction.FORWARD) > 0
//					&& basicRobot.distanceToObstacle(Direction.LEFT) > 0
//					&& basicRobot.hasStopped == false)
//				basicRobot.rotate(Turn.LEFT);
//				basicRobot.move(1, false);
//
//			//if there is wall to my left and nothing in front of me, move forward
//			if (basicRobot.distanceToObstacle(Direction.FORWARD) > 0
//					&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
//					&& basicRobot.hasStopped == false){
//				basicRobot.move(1, false);
//			}
//
//			//if there is a wall in front of me but not to my left
//			if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
//					&& basicRobot.distanceToObstacle(Direction.LEFT) > 0
//					&& basicRobot.hasStopped == false){
//				basicRobot.rotate(Turn.LEFT);
//				basicRobot.move(1, false);
//			}
//			//if there is a wall to my left and in front of me, rotate right and move 1 (turn me around)
//			if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
//					&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
//					&& basicRobot.hasStopped == false){
//
//				basicRobot.rotate(Turn.RIGHT);
//
//				//after rotating right, if there is a wall to my left and nothing in front of me,
//				//move forward
//				if (basicRobot.distanceToObstacle(Direction.FORWARD) > 0
//						&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
//						&& basicRobot.hasStopped == false){
//					basicRobot.move(1,  false);
//				}
//
//				//if there is a wall to my left and in front of me, rotate right and move 1 (turn me around)
//				if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
//						&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
//						&& basicRobot.hasStopped == false){
//					basicRobot.rotate(Turn.RIGHT);
//					basicRobot.move(1,  false);
//				}
//			}
//			//if there is a wall to my left and in front of me, rotate right and move 1 (turn me around)
//			if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0
//					&& basicRobot.distanceToObstacle(Direction.LEFT) == 0
//					&& basicRobot.hasStopped == false){
//				basicRobot.rotate(Turn.RIGHT);
//				basicRobot.move(1,  false);
//			}
//
//			if (basicRobot.isAtExit() == true){
//				basicRobot.getMazeController().switchToFinishScreen();
//				return true;
//			}
////
////			PlayActivity.h.postDelayed(PlayActivity.go, 2000);
//
//		}
//
//
//
//		basicRobot.getMazeController().switchToLossScreen();
//			//when battery runs out, access robot's maze controller??
//		return false;
//
//	}

	@Override
	public float getEnergyConsumption() {
		PlayActivity.h.sendMessage(Message.obtain(PlayActivity.h, 2500 - Math.round(basicRobot.getBatteryLevel())));
		return 2500 - basicRobot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		return basicRobot.getPathLength();
	}

	@Override
	public void keyDown(int key) {

	}

	@Override
	public Boolean atExit() {
		if (basicRobot.isAtExit() == true){
			return true;
		}
		return false;
	}

	@Override
	public void setPlayActivity(PlayActivity p) {
		playActivity = p;
	}

	@Override
	public void interrupt(){
		h.removeCallbacks(r);
	}
}
