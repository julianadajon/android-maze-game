package edu.wm.cs.cs301.julianadajon.falstad;

import android.os.Handler;
import android.os.Message;

import java.util.Random;

import edu.wm.cs.cs301.julianadajon.UI.PlayActivity;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Direction;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Turn;
import edu.wm.cs.cs301.julianadajon.generation.CardinalDirection;
import edu.wm.cs.cs301.julianadajon.generation.Distance;

/**
 * This class solves a randomly generated maze by using pledge's algorithm
 * @author julianadajon
 *
 */
public class Pledge implements RobotDriver{

	BasicRobot basicRobot;
	int width;
	int height;
	Distance distance;
	MazeController controller;

	private PlayActivity playActivity;
	private Handler h;


	public Pledge(){
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

//	@Override
//	public boolean drive2Exit() throws Exception{
//		if (basicRobot.hasStopped() == true){
//			throw new Exception();
//		}
//
//		//randomly pick main direction to go
//		CardinalDirection mainDir = randomDirection();
//
//		//find direction robot is facing, then turn accordingly so it faces mainDir
//		faceCardinalDir(mainDir);
//
//		//while not at exit
////		if (basicRobot.isAtExit() == false && basicRobot.batteryLevel > 0){
//			//move in this direction until you hit a wall
////			while (basicRobot.distanceToObstacle(Direction.FORWARD) > 0){
////				basicRobot.move(1,  false);
////			}
//
//			//if  wall on right, check for wall in front
//			if (basicRobot.distanceToObstacle(Direction.RIGHT) == 0) {
//				if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0){
//					pledge();
//				}
//
//				else {
//					basicRobot.move(1, false);
//				}
//			}
//
//			else {
//				basicRobot.rotate(Turn.RIGHT);
//				basicRobot.move(1, false);}
//
//			if (basicRobot.isAtExit() == true){
//				basicRobot.getMazeController().switchToFinishScreen();
//				return true;
//			}
//
////		}
//
//
//		return false;
//
//	}

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

		//randomly pick main direction to go
		CardinalDirection mainDir = randomDirection();

		//find direction robot is facing, then turn accordingly so it faces mainDir
		faceCardinalDir(mainDir);

		h = new Handler();
		h.postDelayed(r, 100);


		return false;
	}

	Runnable r = new Runnable() {

		public void run() {
//			while (basicRobot.distanceToObstacle(Direction.FORWARD) > 0){
//				basicRobot.move(1,  false);
//			}
			if (basicRobot.isAtExit() == false && basicRobot.batteryLevel > 0) {

				if (basicRobot.distanceToObstacle(Direction.RIGHT) == 0) {
					if (basicRobot.distanceToObstacle(Direction.FORWARD) == 0) {

						pledge();

					} else {
						basicRobot.move(1, false);
					}
				} else {
					basicRobot.rotate(Turn.RIGHT);
					basicRobot.move(1, false);
				}
			}

			getEnergyConsumption();
			try {
				drive2Exit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};


	private void pledge() {
		//initialize counter = 0
		int counter =  1;
//		int numOfLoops = 0; 
		//enter pledge alg while loop, while counter != 0 and not at exit
		
		basicRobot.rotate(Turn.LEFT);
		counter -= 1; 
		
		while (counter != 0 && basicRobot.isAtExit() == false){
			
	//		if start of pledge alg, set counter to 0
//			if (numOfLoops == 0){

//			}
			
			//if wall to right and wall in front, turn left (C-=1)
			if (basicRobot.distanceToObstacle(Direction.RIGHT) == 0 
					&& basicRobot.distanceToObstacle(Direction.FORWARD) == 0){




				basicRobot.rotate(Turn.LEFT);
				counter -= 1; 
				System.out.println("wall to right and in front");
			}
			
			//else if wall to right and wall not in front, move forward
			else if (basicRobot.distanceToObstacle(Direction.RIGHT) == 0 
					&& basicRobot.distanceToObstacle(Direction.FORWARD) > 0){



				basicRobot.move(1, false);
				System.out.println("wall to right and in not front");
			}
			
			//else if no wall to right, turn right (C+=1) and move forward
//				else if (basicRobot.distanceToObstacle(Direction.RIGHT) > 0){
			else{



				basicRobot.rotate(Turn.RIGHT);
				counter += 1; 
				basicRobot.move(1, false);
				System.out.println("no wall to right");
			}
			
//			numOfLoops++; 
		}
	}

	public CardinalDirection randomDirection() {
		CardinalDirection mainDir = CardinalDirection.North;
		
		Random rand = new Random();
		int randomDir = rand.nextInt(4);
		
		if (randomDir == 0){
			mainDir = CardinalDirection.North;
		}
		
		if (randomDir == 1){
			mainDir = CardinalDirection.South;
		}
		
		if (randomDir == 2){
			mainDir = CardinalDirection.East;
		}
		
		if (randomDir == 3){
			mainDir = CardinalDirection.West;
		}
		return mainDir;
	}
	
		
	
	public void faceCardinalDir(CardinalDirection dirToFace){
		CardinalDirection currDir = basicRobot.getCurrentDirection();
		
		if (currDir == CardinalDirection.North){
			if (dirToFace == CardinalDirection.North){
				//do nothing
			}
			if (dirToFace == CardinalDirection.South){
				basicRobot.rotate(Turn.AROUND);
			}
			if (dirToFace == CardinalDirection.East){
				basicRobot.rotate(Turn.LEFT);
			}
			if (dirToFace == CardinalDirection.West){
				basicRobot.rotate(Turn.RIGHT);
			}
		}
		
		else if (currDir == CardinalDirection.South){
			if (dirToFace == CardinalDirection.North){
				basicRobot.rotate(Turn.AROUND);
			}
			if (dirToFace == CardinalDirection.South){
				//do nothing
			}
			if (dirToFace == CardinalDirection.East){
				basicRobot.rotate(Turn.RIGHT);
			}
			if (dirToFace == CardinalDirection.West){
				basicRobot.rotate(Turn.LEFT);
			}
		}

		else if (currDir == CardinalDirection.East){
			if (dirToFace == CardinalDirection.North){
				basicRobot.rotate(Turn.RIGHT);
			}
			if (dirToFace == CardinalDirection.South){
				basicRobot.rotate(Turn.LEFT);
			}
			if (dirToFace == CardinalDirection.East){
				//do nothing
			}
			if (dirToFace == CardinalDirection.West){
				basicRobot.rotate(Turn.RIGHT);
			}
		}
		
		else if (currDir == CardinalDirection.West){
			if (dirToFace == CardinalDirection.North){
				basicRobot.rotate(Turn.LEFT);
			}
			if (dirToFace == CardinalDirection.South){
				basicRobot.rotate(Turn.RIGHT);
			}
			if (dirToFace == CardinalDirection.East){
				basicRobot.rotate(Turn.RIGHT);
			}
			if (dirToFace == CardinalDirection.West){
				//do nothing
			}
		}

		
		
	}
	
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
		// TODO Auto-generated method stub
		
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
