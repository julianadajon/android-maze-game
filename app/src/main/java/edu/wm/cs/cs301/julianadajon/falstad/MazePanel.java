package edu.wm.cs.cs301.julianadajon.falstad;

//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Panel;
//import java.awt.RenderingHints;
//import java.awt.Color;
import java.util.Arrays;
//import java.awt.Event;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import android.content.res.TypedArray;
import android.graphics.Paint.Style;

import edu.wm.cs.cs301.julianadajon.R;


/**
 * Add functionality for double buffering to an AWT Panel class.
 * Used for drawing a maze.
 *
 * @author pk
 *
 */
public class MazePanel extends View {

	Bitmap b ;
	Canvas fCanvas ;
//	Paint lpaint = new Paint();
//	Paint cpaint= new Paint();
	Paint canvasPaint;

	private int circleCol;
	private Paint circlePaint;

	/* Panel operates a double buffer see
	 * http://www.codeproject.com/Articles/2136/Double-buffer-in-standard-Java-AWT
	 * for details
	 */
//	private Image bufferImage ;
//	private Graphics2D gc;

	/**
	 * Constructor. Object is not focusable.
	 */

public void init() {
		b = Bitmap.createBitmap(800, 1100, Bitmap.Config.ARGB_8888);
		fCanvas = new Canvas(b);
//	Paint lpaint = new Paint();
//	Paint cpaint= new Paint();
		canvasPaint = new Paint();
	}

	public MazePanel(Context context) {
		super(context);
init();
	}

    public MazePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
init();
	}

	@Override
	public void onDraw(Canvas c){
		super.onDraw(c);
		c.drawBitmap(b, 0, 0, canvasPaint);
//		invalidate();



	}

// Redraw graphics on canvas by calling invalidate();
	public void update(MazePanel p) {
//		paint(getGraphics()) ;
		System.out.println("entered panel.update");
		p.invalidate();
	}


	/**
	 * Draws the buffer image to the given graphics object.
	 * This method is called when this panel should redraw itself.
	 */

//	public void paint(Graphics g) {
//		if (null == g) {
//			System.out.println("MazePanel.paint: no graphics object, skipping drawImage operation") ;
//		}
//		else {
//			g.drawImage(bufferImage,0,0,null) ;
//		}
//	}

	public void initBufferImage() {
//		bufferImage = createImage(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
//		if (null == bufferImage)
//		{
//			System.out.println("Error: creation of buffered image failed, presumedly container not displayable");
//		}
//		//initialize graphics object here
//		else{
//			gc = (Graphics2D) bufferImage.getGraphics();
//		}
	}


	/**
	 * Obtains a graphics object that can be used for drawing.
	 * Multiple calls to the method will return the same graphics object
	 * such that drawing operations can be performed in a piecemeal manner
	 * and accumulate. To make the drawing visible on screen, one
	 * needs to trigger a call of the paint method, which happens
	 * when calling the update method.
	 * @return graphics object to draw on
	 */
//	public Graphics getBufferGraphics() {
//		if (null == bufferImage)
//			initBufferImage() ;
//		if (null == bufferImage)
//			return null ;
//		return bufferImage.getGraphics() ;
//	}



	/**
	 * Used in FirstPersonDrawer
	 */
	protected void setRenderingHint(){
//		((Graphics2D) gc).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		((Graphics2D) gc).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	}



	/**
	 * Sets color in FirstPersonDrawer and MapDrawer
	 */
	protected void setColor(String color){
		if (color == "white"){
//			gc.setColor(Color.white);
			canvasPaint.setColor(Color.WHITE);
		}
		if (color == "gray"){
//			gc.setColor(Color.gray);
			canvasPaint.setColor(Color.GRAY);
		}

		if (color == "yellow"){
//			gc.setColor(Color.yellow)
			canvasPaint.setColor(Color.YELLOW);
		}

		if (color == "red" ){
//			gc.setColor(Color.red);
			canvasPaint.setColor(Color.RED);
		}

	}



	/**
	 * Used in FirstPersonDrawer. Takes an integer array and converts it to a color object.
	 * @param color
	 */
	public void setColorSeg(int[] color){
//		Color color1 = new Color(color[0],color[1],color[2]);
//		gc.setColor(color1);
		canvasPaint.setColor(Color.rgb(color[0], color[1], color[2]));
	}

	/**
	 * Used in MapDrawer
	 */
	protected void drawLine(int a,
			int b, int c, int d){
		fCanvas.drawLine(a, b, c, d, canvasPaint);

	}

	/**
	 * Used in MapDrawer
	 */
	protected void fillOval(int a, int b, int c, int d){
		RectF rec = new RectF(a, b, c, d);
		fCanvas.drawOval(rec, canvasPaint);

	}


	/**
	 * Used in FirstPersonDrawer.  Sets the background of the maze.
	 */
	protected void drawBkg(String color, int vw, int vh){

		if (color == "black"){
////			gc.setColor(Color.black);
////			gc.fillRect(0, 0, vw, vh/2);
			canvasPaint.setColor(Color.BLACK);
			canvasPaint.setStyle(Style.FILL);
			fCanvas.drawRect(0, 0, vw, vh/2, canvasPaint);
//			fCanvas.drawRect(0, 0, 800, 450, canvasPaint);

//			Rect r = new Rect();
//			r.set(0, 450, 800, 900);
//			fCanvas.drawRect(r, canvasPaint);


		}

		if (color == "darkGray"){
//			gc.setColor(Color.gray);
//			gc.fillRect(0, vh/2, vw, vh/2);
			canvasPaint.setColor(Color.GRAY);
			canvasPaint.setStyle(Style.FILL);
			fCanvas.drawRect(0, vh/2, vw, vw, canvasPaint);
//			fCanvas.drawRect(0, 0, 800, 450, canvasPaint);

		}



	}

	/**
	 * Used in FirstPersonDrawer
	 * @param x
	 * @param y
	 * @param i
	 */
	public void fillPolygon(int[] x, int[] y, int i){
//		gc.fillPolygon(xps, yps, i);

		Path path = new Path();
		path.reset();
		path.moveTo(x[0], y[0]);
		path.lineTo(x[1], y[1]);
		path.lineTo(x[2], y[2]);
		path.lineTo(x[3], y[3]);
		path.lineTo(x[0], y[0]);

		fCanvas.drawPath(path, canvasPaint);
	}

	/**
	 * Method incorporates all reactions to keyboard input in original code,
	 * The simple key listener calls this method to communicate input.
	 */

//	public int event(int direction){
//		int ev = 0;
//		if (direction == 0) {
////			ev = Event.LEFT;
//		}
//		if (direction == 1) {
////			ev = Event.RIGHT;
//		}
//		if (direction == 2){
////			ev = Event.UP;
//		}
//
//		return ev;
//	}


	public boolean keyDown(MazeController mazeController, int key) {


		switch (key) {
			case 2:
			case 'k':
			case '8':
				// move forward

				mazeController.walk(1);
				if (mazeController.isOutside(mazeController.px, mazeController.py)) {
					mazeController.switchToFinishScreen();
				}
				System.out.println("Moved forward. current pos in maze: " + Arrays.toString(mazeController.getCurrentPosition()));

				break;
			case 0:
			case 'h':
			case '4':
				// turn left
				mazeController.rotate(1);
				System.out.println("Turned left. current pos in maze: " + Arrays.toString(mazeController.getCurrentPosition()));

				break;
			case 1:
			case 'l':
			case '6':
				// turn right
				mazeController.rotate(-1);
				System.out.println("Turned right. current pos in maze: " + Arrays.toString(mazeController.getCurrentPosition()));

				break;
			case 3:
			case 'j':
			case '2':
				// move backward
				mazeController.walk(-1);
				if (mazeController.isOutside(mazeController.px, mazeController.py)) {
					mazeController.switchToFinishScreen();
				}
				break;
		}
		return true;
	}



//		public boolean keyDown(MazeController mazeController, int key) {


//		String s1 = "before: px,py:" + mazeController.px + "," + mazeController.py + " dir:" + mazeController.dx + "," + mazeController.dy;
//		// possible inputs for key: unicode char value, 0-9, A-Z, Escape, 'k','j','h','l'
//		// depending on the current state of the GUI, inputs have different effects
//		// implemented as a little automaton that switches state and performs necessary actions
//		switch (mazeController.state) {
//		// if screen shows title page, keys describe level of expertise
//		// create a maze according to the user's selected level
//		// user types wrong key, just use 0 as a possible default value
//		case STATE_TITLE:
//			mazeController.switchToGeneratingScreen(key);
//			break;
//			// if we are currently generating a maze, recognize interrupt signal (ESCAPE key)
//			// to stop generation of current maze
//		case STATE_GENERATING:
//			if (key == Constants.ESCAPE) {
//				mazeController.switchToTitleScreen(true);
//			}
//			break;
//			// if user explores maze,
//			// react to input for directions and interrupt signal (ESCAPE key)
//			// react to input for displaying a map of the current path or of the overall maze (on/off toggle switch)
//			// react to input to display solution (on/off toggle switch)
//			// react to input to increase/reduce map scale
//		case STATE_PLAY:
//			switch (key) {
//			case Event.UP: case 'k': case '8':
//				// move forward
//
//				mazeController.walk(1);
//				if (mazeController.isOutside(mazeController.px,mazeController.py)) {
//					mazeController.switchToFinishScreen();
//				}
//				System.out.println("Moved forward. current pos in maze: " + Arrays.toString(mazeController.getCurrentPosition()));
//
//				break;
//			case Event.LEFT: case 'h': case '4':
//				// turn left
//				mazeController.rotate(1);
//				System.out.println("Turned left. current pos in maze: " + Arrays.toString(mazeController.getCurrentPosition()));
//
//				break;
//			case Event.RIGHT: case 'l': case '6':
//				// turn right
//				mazeController.rotate(-1);
//				System.out.println("Turned right. current pos in maze: " + Arrays.toString(mazeController.getCurrentPosition()));
//
//				break;
//			case Event.DOWN: case 'j': case '2':
//				// move backward
//				mazeController.walk(-1);
//				if (mazeController.isOutside(mazeController.px,mazeController.py)) {
//					mazeController.switchToFinishScreen();
//				}
//				break;
//			case Constants.ESCAPE: case 65385:
//				// escape to title screen
//				mazeController.switchToTitleScreen(false);
//				break;
//			case ('w' & 0x1f):
//				// Ctrl-w makes a step forward even through a wall
//				// go to position if within maze
//				if (mazeController.mazeConfig.isValidPosition(mazeController.px + mazeController.dx, mazeController.py + mazeController.dy)) {
//					mazeController.setCurrentPosition(mazeController.px + mazeController.dx, mazeController.py + mazeController.dy) ;
//					mazeController.notifyViewerRedraw() ;
//				}
//				break;
//			case '\t': case 'm':
//				// show local information: current position and visible walls
//				// precondition for showMaze and showSolution to be effective
//				// acts as a toggle switch
//				mazeController.mapMode = !mazeController.mapMode;
//				mazeController.notifyViewerRedraw() ;
//				break;
//			case 'z':
//				// show the whole maze
//				// acts as a toggle switch
//				mazeController.showMaze = !mazeController.showMaze;
//				mazeController.notifyViewerRedraw() ;
//				break;
//			case 's':
//				// show the solution as a yellow line towards the exit
//				// acts as a toggle switch
//				mazeController.showSolution = !mazeController.showSolution;
//				mazeController.notifyViewerRedraw() ;
//				break;
//			case '+': case '=':
//				// zoom into map
//				mazeController.notifyViewerIncrementMapScale() ;
//				mazeController.notifyViewerRedraw() ; // seems useless but it is necessary to make the screen update
//				break ;
//			case '-':
//				// zoom out of map
//				mazeController.notifyViewerDecrementMapScale() ;
//				mazeController.notifyViewerRedraw() ; // seems useless but it is necessary to make the screen update
//				break ;
//			} // end of internal switch statement for playing state
//			// debug
//			/*
//			if (this.mazeConfig.isValidPosition(px, py)) {
//				String s2 = "after: px,py:" + px + "," + py + " dir:" + dx + "," + dy;
//				System.out.println("mc.keydown:" + s1 + "  " + s2);
//				String s3 = "Walls at: px,py:" + px + "," + py;
//				s3 += " ESWN: " + mazeConfig.hasWall(px, py, CardinalDirection.East) +
//						"," + mazeConfig.hasWall(px, py, CardinalDirection.South) +
//						"," + mazeConfig.hasWall(px, py, CardinalDirection.West) +
//						"," + mazeConfig.hasWall(px, py, CardinalDirection.North);
//				System.out.println("mc.keydown:" + s3);
//			}
//			*/
//			//
//			break ;
//		// if we are finished, return to initial state with title screen
//		case STATE_FINISH:
//
////			new MazeApplication();
//
////			mazeController.switchToTitleScreen(false);
//			break;
//
//		case STATE_LOSS:
////			new MazeApplication();
//			break;
//
//		}

//		return true;
//	}





}
