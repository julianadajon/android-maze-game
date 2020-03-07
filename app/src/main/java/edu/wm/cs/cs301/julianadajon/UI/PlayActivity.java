package edu.wm.cs.cs301.julianadajon.UI;
import edu.wm.cs.cs301.julianadajon.falstad.ManualDriver;
import edu.wm.cs.cs301.julianadajon.falstad.MazeController;
import edu.wm.cs.cs301.julianadajon.falstad.MazePanel;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.graphics.Color;
import android.view.View;


import edu.wm.cs.cs301.julianadajon.R;
import edu.wm.cs.cs301.julianadajon.falstad.RobotDriver;
import edu.wm.cs.cs301.julianadajon.falstad.SingletonMaze;
import edu.wm.cs.cs301.julianadajon.generation.MazeConfiguration;

/**
 * Screen where maze is played.  Shows energy consumption and navigation through maze.
 * Implements use of buttons, switches, and progress bar.
 */


public class PlayActivity extends AppCompatActivity {

    private static Button zoomIn;
    private static Button zoomOut;
    private int batteryLevel;
    private int pathLength;

    private static ProgressBar energyProgressBar;
    private static TextView consumption;
    private String driver;

    private static Switch switchMap;
    private static Switch switchSolution;
    private static Switch switchWalls;

    private static Button forwardButton;
    private static Button backwardButton;
    private static Button leftButton;
    private static Button rightButton;
    private static Button playButton;
    private static Button pauseButton;

    public static MazePanel panel;

    private MazeConfiguration mazeConfig;
    public static MazeController mazeController;
    private static RobotDriver robotDriver;

//    public static Runnable go;
    public static Handler h = new Handler();

    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        sound = MediaPlayer.create(this, R.raw.adventure);
        playMusic();
        panel = (MazePanel)findViewById(R.id.customPanel);

        if (panel == null) {
            Log.e("PlayActivity", "maze panel disappeared");
        }

        getMazeController();

        mazeController.setActivity(this);

        System.out.println("robotDriver is null: " + robotDriver == null);

        robotDriver = mazeController.getDriver();
        robotDriver.setPlayActivity(this);

        mazeController.switchToPlayingScreen();
        mazeController.notifyViewerRedraw();


        energyProgressBar();

        switchMap();
        switchSolution();
        switchWalls();

        zoomIn();
        zoomOut();

        getDriver();
        if (driver.equals("Manual")){
            playButton();
            playButton.setVisibility(View.INVISIBLE);
            pauseButton();
            pauseButton.setVisibility(View.INVISIBLE);
            forwardButton();
            backwardButton();
            leftButton();
            rightButton();
        }

        else {
            forwardButton();
            backwardButton();
            leftButton();
            rightButton();
            forwardButton.setVisibility(View.INVISIBLE);
            backwardButton.setVisibility(View.INVISIBLE);
            leftButton.setVisibility(View.INVISIBLE);
            rightButton.setVisibility(View.INVISIBLE);
            playButton();
            pauseButton();

        }
    }

    private void playMusic() {
        sound.start();
    }

    /**
     * Keeps track of energy consumption
     */
    protected void energyProgressBar() {
        energyProgressBar = (ProgressBar)findViewById(R.id.progressBar2);
        consumption = (TextView)findViewById(R.id.textView9);

        energyProgressBar.setProgress(2500);
        consumption.setText("Fish Food Left: ");


        h = new Handler() {

            @Override
            public void handleMessage(Message msg) {
//                robotDriver.getEnergyConsumption();

                super.handleMessage(msg);
                energyProgressBar.setProgress(2500 - msg.what);
                consumption.setText("Fish Food left: " + (2500 -msg.what));

//                if (msg.what <= 100) {
//
//                    progressBar.setProgress(msg.what);
//                    percentage.setText(msg.what + "%");
//
//                }
//
//                if (msg.what == 150){
//                    switchToPlayActivity();
//                }
            }
        };
//        energyProgressBar.setProgress(energyProgressBar.getMax());
//        consumption.setText("Energy left: " + energyProgressBar.getMax());
//        int e = Math.round(robotDriver.getEnergyConsumption());
//        energyProgressBar.setProgress(energyProgressBar.getMax() - e);
//        consumption.setText("Energy left: " + (energyProgressBar.getMax() - e));

    }

    /**
     * toggles map view.
     */
    protected void switchMap(){
        switchMap = (Switch)findViewById(R.id.switch1);
        switchMap.setOnCheckedChangeListener(
                new Switch.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                        if (isChecked == true) {
                            Toast.makeText(PlayActivity.this, "Map On", Toast.LENGTH_SHORT).show();
                            Log.v("PlayActivity", "Map On");
                            mazeController.mapSwitch();
                        }
                        else {
                            Toast.makeText(PlayActivity.this, "Map Off", Toast.LENGTH_SHORT).show();
                            Log.v("PlayActivity", "Map Off");
                            mazeController.mapSwitch();
                        }


                    }
                }
        );

    }
    /**
     * toggles solution view.
     */
    protected void switchSolution(){
        switchSolution = (Switch)findViewById(R.id.switch2);
        switchSolution.setOnCheckedChangeListener(
                new Switch.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked == true) {
                            Toast.makeText(PlayActivity.this, "Solution On", Toast.LENGTH_SHORT).show();
                            Log.v("PlayActivity", "Solution On");
                            mazeController.solutionSwitch();
                        }
                        else {
                            Toast.makeText(PlayActivity.this, "Solution Off", Toast.LENGTH_SHORT).show();
                            Log.v("PlayActivity", "Solution Off");
                            mazeController.solutionSwitch();
                        }
                    }
                }
        );
    }

    /**
     * toggles walls view.
     */
    protected void switchWalls(){
        switchWalls = (Switch)findViewById(R.id.switch3);
        switchWalls.setOnCheckedChangeListener(
                new Switch.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked == true) {
                            Toast.makeText(PlayActivity.this, "Walls On", Toast.LENGTH_SHORT).show();
                            Log.v("PlayActivity", "Walls On");
                            mazeController.wallsSwitch();
                        }
                        else {
                            Toast.makeText(PlayActivity.this, "Walls Off", Toast.LENGTH_SHORT).show();
                            Log.v("PlayActivity", "Walls Off");
                            mazeController.wallsSwitch();
                        }
                    }
                }
        );
    }

    /**
     * Short cut that takes you to finish screen.
     */
    protected void zoomIn() {
        zoomIn = (Button)findViewById(R.id.zoomIn);
        zoomIn.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.zoomIn :


                                mazeController.notifyViewerIncrementMapScale();



                                Toast.makeText(PlayActivity.this, "Zoom In", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "zoom in");
                                break;

                        }
                    }
                }
        );
    }


    /**
     * Short cut that takes you to finish screen.
     */
    protected void zoomOut() {
        zoomOut = (Button)findViewById(R.id.zoomOut);
        zoomOut.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.zoomOut :
                                zoomOutClick();
                                Toast.makeText(PlayActivity.this, "Zoom Out", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "zoom out");
                                break;

                        }
                    }
                }
        );
    }


    /**
     * zooms in on map
     */
    private void zoomOutClick(){
        mazeController.notifyViewerDecrementMapScale();

    }


    /**
     * Short cut that takes you to finish screen.
     */
//    protected void shortCutButton() {
//        shortCutButton = (Button)findViewById(R.id.button2);
//        shortCutButton.setOnClickListener(
//
//                new Button.OnClickListener(){
//
//                    public void onClick(View v){
//                        switch (v.getId()) {
//                            case R.id.button2 :
//                                shortCutButtonClick();
//                                Toast.makeText(PlayActivity.this, "Switch to Finish", Toast.LENGTH_SHORT).show();
//                                Log.v("PlayActivity", "Switch to Finish");
//                                break;
//
//                        }
//                    }
//                }
//        );
//    }

    /**
     * Connects play activity to finish.
     */
//    private void shortCutButtonClick(){
//
//        Intent intent = new Intent(this, FinishActivity.class);
//        intent.putExtra("batteryLevel", Integer.toString(batteryLevel));
//        intent.putExtra("pathLength", Integer.toString(pathLength));
//        startActivity(intent);
//        finish();
//    }

    /**
     * Moves robot forward
     */
    protected void forwardButton() {
        forwardButton = (Button)findViewById(R.id.button3);
        forwardButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){

                        switch (v.getId()) {
                            case R.id.button3 :
                                Toast.makeText(PlayActivity.this, "You moved forward", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "You moved forward");
                                break;

                        }


                        try{
                            if (robotDriver.drive2Exit() == true){
                                win();
                            }
//
//                            if (robotDriver.getEnergyConsumption() <= 0){
//                                lose();
//                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        robotDriver.keyDown('k');

                    }
                }
        );
    }

    public void win() {
        mazeController.setMazePanel(null);
        SingletonMaze.Instance().kill();

        sound.stop();



        Intent win = new Intent(this, FinishActivity.class);

        int e = (int) robotDriver.getEnergyConsumption();

        win.putExtra("energy", Integer.toString(e));
        win.putExtra("path", Integer.toString(robotDriver.getPathLength()));

        startActivity(win);
        finish();
    }

    public void lose() {
        mazeController.setMazePanel(null);
        SingletonMaze.Instance().kill();

        sound.stop();

        Intent lose = new Intent(this, LoseActivity.class);

        int e = (int) robotDriver.getEnergyConsumption();


        lose.putExtra("energy", Integer.toString(e));
        lose.putExtra("path", Integer.toString(robotDriver.getPathLength()));

        startActivity(lose);
        finish();
    }

    /**
     * Moves robot backward.
     */
    protected void backwardButton() {
        backwardButton = (Button)findViewById(R.id.button4);
        backwardButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button4 :
                                Toast.makeText(PlayActivity.this, "You moved backward", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "You moved backward");
                                break;

                        }

                        robotDriver.keyDown('j');
                        try{
                            if (robotDriver.drive2Exit() == true){
                                win();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    /**
     * Moves robot right.
     */
    protected void rightButton() {
        rightButton = (Button)findViewById(R.id.button5);
        rightButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button5 :
                                Toast.makeText(PlayActivity.this, "You moved right", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "You moved right");
                                break;

                        }
                        robotDriver.keyDown('l');
                        try{
                            if (robotDriver.drive2Exit() == true){
                                win();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    /**
     * Moves robot left.
     */
    protected void leftButton() {
        leftButton = (Button)findViewById(R.id.button6);
        leftButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button6 :
                                Toast.makeText(PlayActivity.this, "You moved left", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "You moved left");
                                break;

                        }
                        robotDriver.keyDown('h');
                        try{
                            if (robotDriver.drive2Exit() == true){
                                win();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
    /**
     * Starts Robot solver.
     */
    protected void playButton() {
        playButton = (Button)findViewById(R.id.button7);
        playButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button7 :
                                Toast.makeText(PlayActivity.this, "You pressed play", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "You pressed play");
                                break;

                        }


                        try {
                            robotDriver.drive2Exit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        try{
                            if (robotDriver.atExit() == true){
                                win();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
    }



    /**
     * Pauses Robot Solver.
     */
    protected void pauseButton() {
        pauseButton = (Button)findViewById(R.id.button8);
        pauseButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button8 :
                                Toast.makeText(PlayActivity.this, "You pressed pause", Toast.LENGTH_SHORT).show();
                                Log.v("PlayActivity", "You pressed pause");
                                break;

                        }
                        robotDriver.interrupt();
//
                    }
                }
        );
    }

    /**
     * Gets type of robot requested from Generating Activity
     */
    private void getDriver() {
        Intent iDriver = getIntent();
        driver = iDriver.getStringExtra("spinnerDriver");
    }

    @Override
    public void onBackPressed(){
        Intent back = new Intent(this, AMazeActivity.class);
        finish();
        startActivity(back);
    }


    /**
     * gets mazeController (after created by the factory/builder)
     */
    private void getMazeController(){
        mazeController = SingletonMaze.Instance().getMazeController();

    }


}
