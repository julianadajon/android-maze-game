package edu.wm.cs.cs301.julianadajon.UI;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.wm.cs.cs301.julianadajon.R;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Intent;

/**
 * Homescreen
 * Lets user set desired parameters for maze:
 * Type of algorithm, robot, and skill level.
 * Also includes the option to reload previously generated maze.
 */
public class AMazeActivity extends AppCompatActivity{

    private static SeekBar seekBarLevel;
    private static TextView textLevel;
    private static Spinner spinnerExplore;
    private static Spinner spinnerDriver;
    private static Button newButton;
    private static Button revisitButton;

    private static int progressValue;
    private static String exploreValue;
    private static String driverValue;

    MediaPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("start", "re-start");

        setContentView(R.layout.activity_amaze);

        sound = MediaPlayer.create(this, R.raw.nemomusic);
        playMusic();

        seekBar();
        spinnerExplore();
        spinnerDriver();
        newButton();
        revisitButton();



        System.out.println(seekBarLevel.getProgress());
    }
    /**
     * Uses seek bar to get user request for skill level.
     */
    protected void seekBar() {
        seekBarLevel = (SeekBar)findViewById(R.id.seekBar2);
        textLevel = (TextView)findViewById(R.id.textView2);

        textLevel.setText("Level: " + seekBarLevel.getProgress());

        seekBarLevel.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
//                    int progressValue;

                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                        progressValue = progress;
                        textLevel.setText("Level: " + progress);
                        Toast.makeText(AMazeActivity.this, "Setting Level: " + progressValue, Toast.LENGTH_SHORT).show();
                        Log.v("AMazeActivity", "level: " + progressValue);
                    }

                    public void onStartTrackingTouch(SeekBar seekbar){
//                        Toast.makeText(AMazeActivity.this, "Setting Level in Start Tracking", Toast.LENGTH_SHORT).show();

                    }

                    public void onStopTrackingTouch(SeekBar seekbar){
                        textLevel.setText("Level: " + progressValue);
//                        Toast.makeText(AMazeActivity.this, "Setting Level in Stop Tracking", Toast.LENGTH_SHORT).show();

                    }

                }

        );
    }
    /**
     * Uses Spinner to get desired algorithm from user.
     */
    protected void spinnerExplore() {
        spinnerExplore = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Explore, android.R.layout.simple_spinner_item);
        spinnerExplore.setAdapter(adapter);

        spinnerExplore.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                        exploreValue = spinnerExplore.getSelectedItem().toString();
                        TextView myText = (TextView) view;
                        Toast.makeText(AMazeActivity.this, "You Selected "+ myText.getText(), Toast.LENGTH_SHORT).show();
                        Log.v("AMazeActivity", "Explorer: " + exploreValue);
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {


                    }

                }
        );
    }


    /**
     * Uses spinner to get desired robot from user input.
     */
    protected void spinnerDriver() {
        spinnerDriver = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Robot, android.R.layout.simple_spinner_item);
        spinnerDriver.setAdapter(adapter);

        spinnerDriver.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                        driverValue = spinnerDriver.getSelectedItem().toString();
                        TextView myText = (TextView) view;
                        Toast toast = Toast.makeText(AMazeActivity.this, "Driver: "+ myText.getText(), Toast.LENGTH_SHORT);
                        toast.show();
                        Log.v("AMazeActivity", "Driver: " + driverValue);
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }

                }
        );
    }


    /**
     * Button starts maze with indicated settings.
     */
    protected void newButton() {
        newButton = (Button)findViewById(R.id.button);
        newButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button :
                                newButtonClick();
                                Toast.makeText(AMazeActivity.this, "You clicked New Maze", Toast.LENGTH_SHORT).show();
                                Log.v("AMazeActivity", "You clicked Generate Maze");

                                break;

                        }
                    }
                }
        );


    }
    /**
     * Connects AMazeActivity with generatingActivity
     */
    private void newButtonClick(){

        Intent intent = new Intent(this, GeneratingActivity.class);
//        intent.putExtra("seekBarLevel", seekBarLevel.getProgress());
        intent.putExtra("seekBarLevel", Integer.toString(progressValue));
//        intent.putExtra("spinnerExplore", spinnerExplore.getSelectedItem().toString());
        intent.putExtra("spinnerExplore", exploreValue);
//        intent.putExtra("spinnerRobot", spinnerDriver.getSelectedItem().toString());
        intent.putExtra("spinnerDriver", driverValue);
        String saveMaze = "false";
        intent.putExtra("saveMaze", saveMaze);

        sound.stop();

        startActivity(intent);
        finish();
    }


    /**
     * Starts game with previously generated maze.
     */
    protected void revisitButton() {
        revisitButton = (Button)findViewById(R.id.button9);
        revisitButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button9 :
                                revisitButtonClick();
                                Toast.makeText(AMazeActivity.this, "You clicked Revisit Maze", Toast.LENGTH_SHORT).show();
                                Log.v("AMazeActivity", "You clicked Revisit Maze");
                                break;

                        }
                    }
                }
        );
    }
    /**
     * Connects AMazeActivity with generatingActivity
     */
    private void revisitButtonClick(){
        String saveMaze = "true";
        Intent intent = new Intent(this, GeneratingActivity.class);
//        intent.putExtra("seekBarLevel", seekBarLevel.getProgress());
        intent.putExtra("seekBarLevel", Integer.toString(progressValue));
        intent.putExtra("spinnerExplore", exploreValue);
        intent.putExtra("spinnerDriver", spinnerDriver.getSelectedItem().toString());
        intent.putExtra("saveMaze", saveMaze);
        startActivity(intent);
        finish();
    }

    private void playMusic() {
        sound.start();
    }

    @Override
    public void onBackPressed(){
//        Intent back = new Intent(this, AMazeActivity.class);
//        startActivity(back);
        moveTaskToBack(true);
    }
}

/////////////////////test////////////////////////////