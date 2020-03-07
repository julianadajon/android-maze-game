package edu.wm.cs.cs301.julianadajon.falstad;

import android.os.Handler;
import android.os.Message;

import java.util.Arrays;

/**
 * This class solves a randomly generated maze by getting the neighbor closer to exit until at the actual exit.
 * @author julianadajon
 *
 */

import edu.wm.cs.cs301.julianadajon.UI.GeneratingActivity;
import edu.wm.cs.cs301.julianadajon.UI.PlayActivity;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Direction;
import edu.wm.cs.cs301.julianadajon.falstad.Robot.Turn;
import edu.wm.cs.cs301.julianadajon.generation.CardinalDirection;
import edu.wm.cs.cs301.julianadajon.generation.Distance;
import edu.wm.cs.cs301.julianadajon.generation.MazeConfiguration;
public class Wizard implements RobotDriver{

	Robot robot; 
	BasicRobot basicRobot;
	int width; 
	int height; 
	Distance distance; 
	MazeController controller; 
	MazeConfiguration container;
	private MazePanel panel;
	private MazeController mazeController;
	private PlayActivity playActivity;

	Handler h;

	public Wizard(){
		
	}
	
	@Override
	//edited this, made robot of type robot
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
//

		//set maze controller to notifyViewerRedraw;

		//while curr position's distance to exit is > 1: 
//		while (container.getDistanceToExit(currPos[0], currPos[1]) > 1 && basicRobot.batteryLevel > 0){

		h = new Handler();
		h.postDelayed(r, 100);
//			public void run() {
//				System.out.println("entered while loop in wizard.solve()");
//				//	move the robot to neighbor closest to exit
//				//get direction of neightClos to exit
//				//assuming default direction is east, rotate the robot so that
//				//it is facing the neighborClosToExit
//				//move robot forward
//
//
//				int[] dirNeighbor = basicRobot.getDirectionOfNeighborClosestToExit();
//				CardinalDirection currDir = controller.getCurrentDirection();
//				int[] north = {0,-1};
//				int[] south = {0,1};
//				int[] east = {1,0};
//				int[] west = {-1, 0};
//
//				System.out.println("Direction of neighb closest to exit: " + Arrays.toString(dirNeighbor));
//
//
//				//If neighbor closest to exit is to the north of my current position
//				if (Arrays.equals(dirNeighbor, north)){
//
//					if (currDir == CardinalDirection.North){
//						basicRobot.move(1, false);
//
//					}
//
//					if (currDir == CardinalDirection.South){
//						basicRobot.rotate(Turn.AROUND);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.West){
//						basicRobot.rotate(Turn.LEFT);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.East){
//						basicRobot.rotate(Turn.RIGHT);
//						basicRobot.move(1, false);
//					}
//
//					//				update current position
//					int[] newCurrPos = controller.getCurrentPosition();
//					currPos[0] = newCurrPos[0];
//					currPos[1] = newCurrPos[1];
//
//
//
//					System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
//					System.out.println(" " );
//				}
//
//
//
//				//If neighbor closest to exit is to the South of my current position
//				if (Arrays.equals(dirNeighbor, south)){
//					if (currDir == CardinalDirection.North){
//						basicRobot.rotate(Turn.AROUND);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.South){
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.West){
//						basicRobot.rotate(Turn.RIGHT);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.East){
//						basicRobot.rotate(Turn.LEFT);
//						basicRobot.move(1, false);
//					}
//
//					//				update current position
//					int[] newCurrPos = controller.getCurrentPosition();
//					currPos[0] = newCurrPos[0];
//					currPos[1] = newCurrPos[1];
//					System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
//					System.out.println(" " );
//
//
//				}
//
//
//
//				//If neighbor closest to exit is to the East of my current position
//				if (Arrays.equals(dirNeighbor, east)){
//					if (currDir == CardinalDirection.North){
//						basicRobot.rotate(Turn.LEFT);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.South){
//						basicRobot.rotate(Turn.RIGHT);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.West){
//						basicRobot.rotate(Turn.AROUND);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.East){
//						basicRobot.move(1, false);
//					}
//
//
//					//				update current position
//					int[] newCurrPos = controller.getCurrentPosition();
//					currPos[0] = newCurrPos[0];
//					currPos[1] = newCurrPos[1];
//					System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
//					System.out.println(" " );
//
//
//
//				}
//
//
//
//				//If neighbor closest to exit is to the West of my current position
//				if (Arrays.equals(dirNeighbor, west)){
//					if (currDir == CardinalDirection.North){
//						basicRobot.rotate(Turn.RIGHT);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.South){
//						basicRobot.rotate(Turn.LEFT);
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.West){
//						basicRobot.move(1, false);
//					}
//
//					if (currDir == CardinalDirection.East){
//						basicRobot.rotate(Turn.AROUND);
//						basicRobot.move(1, false);
//					}
//
//					//				update current position
//					int[] newCurrPos = controller.getCurrentPosition();
//					currPos[0] = newCurrPos[0];
//					currPos[1] = newCurrPos[1];
//					System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
//					System.out.println(" " );
//
//
//
//				}
//
////				if (basicRobot.isAtExit() == true){
////					basicRobot.getMazeController().switchToFinishScreen();
////					return true;
////
////				}
//				getEnergyConsumption();
//
//                try {
//                    drive2Exit();
//			    } catch (Exception e) {
//				    e.printStackTrace();
//			    }
//
//			}





//		}
		
		return false;
		
		
	}


	Runnable r = new Runnable() {
		public void run() {

			setMazeController();

			//get the robot's controller
			controller = basicRobot.getMazeController();
			System.out.println("wizard controller null: " + (controller == null));

			container = basicRobot.getMazeConfiguration();

			//get current position
			final int[] currPos = controller.getCurrentPosition();


			System.out.println("entered while loop in wizard.solve()");
			//	move the robot to neighbor closest to exit
			//get direction of neightClos to exit
			//assuming default direction is east, rotate the robot so that
			//it is facing the neighborClosToExit
			//move robot forward


			int[] dirNeighbor = basicRobot.getDirectionOfNeighborClosestToExit();
			CardinalDirection currDir = controller.getCurrentDirection();
			int[] north = {0, -1};
			int[] south = {0, 1};
			int[] east = {1, 0};
			int[] west = {-1, 0};

			System.out.println("Direction of neighb closest to exit: " + Arrays.toString(dirNeighbor));


			//If neighbor closest to exit is to the north of my current position
			if (Arrays.equals(dirNeighbor, north)) {

				if (currDir == CardinalDirection.North) {
					basicRobot.move(1, false);

				}

				if (currDir == CardinalDirection.South) {
					basicRobot.rotate(Turn.AROUND);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.West) {
					basicRobot.rotate(Turn.LEFT);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.East) {
					basicRobot.rotate(Turn.RIGHT);
					basicRobot.move(1, false);
				}

				//				update current position
				int[] newCurrPos = controller.getCurrentPosition();
				currPos[0] = newCurrPos[0];
				currPos[1] = newCurrPos[1];


				System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
				System.out.println(" ");
			}


			//If neighbor closest to exit is to the South of my current position
			if (Arrays.equals(dirNeighbor, south)) {
				if (currDir == CardinalDirection.North) {
					basicRobot.rotate(Turn.AROUND);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.South) {
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.West) {
					basicRobot.rotate(Turn.RIGHT);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.East) {
					basicRobot.rotate(Turn.LEFT);
					basicRobot.move(1, false);
				}

				//				update current position
				int[] newCurrPos = controller.getCurrentPosition();
				currPos[0] = newCurrPos[0];
				currPos[1] = newCurrPos[1];
				System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
				System.out.println(" ");


			}


			//If neighbor closest to exit is to the East of my current position
			if (Arrays.equals(dirNeighbor, east)) {
				if (currDir == CardinalDirection.North) {
					basicRobot.rotate(Turn.LEFT);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.South) {
					basicRobot.rotate(Turn.RIGHT);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.West) {
					basicRobot.rotate(Turn.AROUND);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.East) {
					basicRobot.move(1, false);
				}


				//				update current position
				int[] newCurrPos = controller.getCurrentPosition();
				currPos[0] = newCurrPos[0];
				currPos[1] = newCurrPos[1];
				System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
				System.out.println(" ");


			}


			//If neighbor closest to exit is to the West of my current position
			if (Arrays.equals(dirNeighbor, west)) {
				if (currDir == CardinalDirection.North) {
					basicRobot.rotate(Turn.RIGHT);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.South) {
					basicRobot.rotate(Turn.LEFT);
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.West) {
					basicRobot.move(1, false);
				}

				if (currDir == CardinalDirection.East) {
					basicRobot.rotate(Turn.AROUND);
					basicRobot.move(1, false);
				}

				//				update current position
				int[] newCurrPos = controller.getCurrentPosition();
				currPos[0] = newCurrPos[0];
				currPos[1] = newCurrPos[1];
				System.out.println("updated curr position: " + Arrays.toString(newCurrPos));
				System.out.println(" ");


			}

//				if (basicRobot.isAtExit() == true){
//					basicRobot.getMazeController().switchToFinishScreen();
//					return true;
//
//				}
			getEnergyConsumption();

			try {
				drive2Exit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	};

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


	private void setMazeController() {
		mazeController = PlayActivity.mazeController;
	}

	public void setPlayActivity(PlayActivity p){
		playActivity = p;
	}


	public void interrupt() {
		h.removeCallbacks(r);
	}
}
