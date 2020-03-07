package edu.wm.cs.cs301.julianadajon.UI;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.SeekBar;
import android.content.Intent;
import android.os.Bundle;

import edu.wm.cs.cs301.julianadajon.R;
import edu.wm.cs.cs301.julianadajon.falstad.BasicRobot;
import edu.wm.cs.cs301.julianadajon.falstad.ManualDriver;
import edu.wm.cs.cs301.julianadajon.falstad.MazeController;
import edu.wm.cs.cs301.julianadajon.falstad.MazeFileReader;
import edu.wm.cs.cs301.julianadajon.falstad.MazeFileWriter;
import edu.wm.cs.cs301.julianadajon.falstad.MazePanel;
import edu.wm.cs.cs301.julianadajon.falstad.Pledge;
import edu.wm.cs.cs301.julianadajon.falstad.RobotDriver;
import edu.wm.cs.cs301.julianadajon.falstad.SingletonMaze;
import edu.wm.cs.cs301.julianadajon.falstad.WallFollower;
import edu.wm.cs.cs301.julianadajon.falstad.Wizard;
import edu.wm.cs.cs301.julianadajon.generation.Cells;
import edu.wm.cs.cs301.julianadajon.generation.MazeConfiguration;
import edu.wm.cs.cs301.julianadajon.generation.MazeFactory;
import edu.wm.cs.cs301.julianadajon.generation.Order;

/**
 * Generates maze with parameters set in AMazeActivity.
 * Is loading screen, uses circular progress bar.
 * Allows for cancellation of loaded maze.
 */
public class GeneratingActivity extends AppCompatActivity {

    private static ProgressBar progressBar;
    public static int progressStatus;
    public static Handler handler;
    private TextView percentage;

    private static String seekBarLevel;
    private static String spinnerExplore;
    private static String spinnerDriver;
    private boolean back = false;
    private MazeController mazeController;
    private MazeFactory factory;
    private MazePanel mp;
    private String saveMaze;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        mazeController = new MazeController();

        getInfo();

        generateMaze();

        progressBar();




    }

    /**
     * Uses progress bar to show amount of maze loaded.
     */
    protected void progressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        percentage = (TextView) findViewById(R.id.textView7);
        percentage.setText(" ");

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                if (msg.what <= 100) {

                    progressBar.setProgress(msg.what);
                    percentage.setText(msg.what + "%");

                }

                if (msg.what == 150){
                    switchToPlayActivity();
                }
            }
        };
    }

    /**
     * Gets settings for maze from mazeActivity
     */
    private void getInfo() {

        Intent intent = getIntent();

        if (intent.getStringExtra("seekBarLevel") != null) {
            seekBarLevel = intent.getStringExtra("seekBarLevel");
        }

        if (intent.getStringExtra("spinnerExplore") != null) {
            spinnerExplore = intent.getStringExtra("spinnerExplore");
        }

        if (intent.getStringExtra("spinnerDriver") != null) {
            spinnerDriver = intent.getStringExtra("spinnerDriver");
        }

        if (intent.getStringExtra("saveMaze") != null){
            saveMaze = intent.getStringExtra("saveMaze");
        }
    }

    /**
     * Connects generating Activity with PlayActivity.
     */
    private void switchToPlayActivity() {
        if (back == false) {
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtra("seekBarLevel", seekBarLevel);
            intent.putExtra("spinnerExplore", spinnerExplore);
            intent.putExtra("spinnerDriver", spinnerDriver);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        back = true;
        Intent back = new Intent(this, AMazeActivity.class);
        startActivity(back);
//        moveTaskToBack(true);
//        finish();
    }


    public void generateMaze() {

        if (saveMaze.equals("false")) {

            setSkill(seekBarLevel);

            setAlgorithm(spinnerExplore);

            BasicRobot robot = new BasicRobot();
            mazeController.updateRobot(robot);

            setDriver(spinnerDriver);


            SingletonMaze.Instance().setMazeController(mazeController);

            mazeController.mf.order(this.mazeController);
        }

        else {
            if (seekBarLevel.equals("0")){
                MazeFileReader mr = new MazeFileReader("level0");
                MazeConfiguration mc = mr.getMazeConfiguration();


//                mazeController.setMazeConfig(mc);

                BasicRobot robot = new BasicRobot();
                mazeController.updateRobot(robot);

                setDriver(spinnerDriver);

                SingletonMaze.Instance().setMazeController(mazeController);
                mazeController.deliver(mc);

//                mazeController.updateProgress(150);
//                handler.sendMessage(Message.obtain(GeneratingActivity.handler, 150));

//                switchToPlayActivity();
            }
        }



    }





    private void setSkill(String skill) {
        int s = Integer.parseInt(skill);
        mazeController.updateSkill(s);
    }

    private void setAlgorithm(String alg){
        if (alg.equals("DFS")){
            mazeController.updateBuilder(Order.Builder.DFS);
        }
        if (alg.equals("Prim")){
            mazeController.updateBuilder(Order.Builder.Prim);
        }
        if (alg.equals("Eller")){
            mazeController.updateBuilder(Order.Builder.Eller);
        }
    }

    private void setDriver(String driver){
        if (driver.equals("Manual")){
            RobotDriver manual = new ManualDriver();
            mazeController.updateDriver(manual);
        }

        if (driver.equals("Wizard")){
            RobotDriver wizard = new Wizard();
            mazeController.updateDriver(wizard);
        }

        if (driver.equals("Wall Follower")){
            RobotDriver wf = new WallFollower();
            mazeController.updateDriver(wf);
        }

        if (driver.equals("Pledge")){
            RobotDriver p = new Pledge();
            mazeController.updateDriver(p);
        }
    }


}