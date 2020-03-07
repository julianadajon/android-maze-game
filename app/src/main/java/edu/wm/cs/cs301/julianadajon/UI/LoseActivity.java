package edu.wm.cs.cs301.julianadajon.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import edu.wm.cs.cs301.julianadajon.R;


/**
 * Finish screen after maze completes or robot/user runs out of gas.
 */
public class LoseActivity extends AppCompatActivity {

    private static Button startOverButton;

    private String energy;
    private String path;
    private TextView energyText;
    private TextView pathText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        startOverButton();


        getInfo();
        text();

    }

    /**
     * Takes User back to homescreen
     */
    public void startOverButton() {
        startOverButton = (Button)findViewById(R.id.button10);
        startOverButton.setOnClickListener(

                new Button.OnClickListener(){

                    public void onClick(View v){
                        switch (v.getId()) {
                            case R.id.button10 :
                                startOverButtonClick();
                                Toast.makeText(LoseActivity.this, "Starting Over", Toast.LENGTH_SHORT).show();
                                Log.v("LoseActivity", "Starting Over");
                                break;

                        }
                    }
                }
        );
    }

    /**
     * Connects this activity back to main maze activity
     */
    private void startOverButtonClick(){

        Intent intent = new Intent(this, AMazeActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed(){
        Intent back = new Intent(LoseActivity.this, AMazeActivity.class);

        startActivity(back);
        finish();
    }

    private void getInfo() {

        Intent intent = getIntent();

        if (intent.getStringExtra("energy") != null) {
            energy = intent.getStringExtra("energy");
        }

        if (intent.getStringExtra("path") != null) {
            path = intent.getStringExtra("path");
        }
    }

    private void text() {
        energyText = (TextView)findViewById(R.id.textView11);
        pathText = (TextView)findViewById(R.id.textView10);

        energyText.setText("Fish Food Consumed: " + 2500);
        pathText.setText("Distance Swam: " + path);
    }
}

