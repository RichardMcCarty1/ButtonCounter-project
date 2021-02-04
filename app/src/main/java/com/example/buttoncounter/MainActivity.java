package com.example.buttoncounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Bundle stateBundle = new Bundle();
    private buttonIncre incrementalCounter;
    private Button upButton;
    private Button downButton;
    private TextView viewCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upButton = (Button) this.findViewById(R.id.upButton);
        downButton = (Button) this.findViewById(R.id.downButton);
        viewCount = (TextView) this.findViewById(R.id.count);
        incrementalCounter = new buttonIncre();
        viewCount.setText(String.valueOf(incrementalCounter.increment));

    }
    //When Rotate do...
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }
    //Restore state of app after change from saved state
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getBundle("stateBundle");
    }
    //Save state of app prior to change
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("stateBundle", stateBundle);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.upButton) {
            incrementalCounter.up();
        }
        else {
            incrementalCounter.down();
        }
        viewCount.setText(String.valueOf(incrementalCounter.increment));
    }

    private class buttonIncre {
        private int increment;

        private buttonIncre() {
            int increment = 0;
        }

        private int up() {
              return this.increment++;
        }
        private int down() {
            return this.increment--;
        }
    }
}