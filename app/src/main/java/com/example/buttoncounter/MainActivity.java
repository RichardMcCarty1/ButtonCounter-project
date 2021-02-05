package com.example.buttoncounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private buttonIncre incrementalCounter;
    private TextView viewCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewCount = (TextView) this.findViewById(R.id.count);
        incrementalCounter = new buttonIncre();
        viewCount.setText(String.valueOf(incrementalCounter.increment));

    }
    //Restore state of app after change from saved state
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        incrementalCounter.increment = savedInstanceState.getInt("count");
        viewCount.setText(String.valueOf(incrementalCounter.increment));
    }
    //Save state of app prior to change
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("count", incrementalCounter.increment);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.upButton) {
            incrementalCounter.up();
        }
        else if(v.getId() == R.id.downButton){
            incrementalCounter.down();
        }
        else if(v.getId() == R.id.resetButton) {
            incrementalCounter.reset();
        }
        viewCount.setText(String.valueOf(incrementalCounter.increment));
    }

    private class buttonIncre {
        private int increment;

        private buttonIncre() {
            increment = 0;
        }

        private int up() {
              return this.increment++;
        }
        private int down() {
            return this.increment--;
        }
        private void reset() {
            this.increment = 0;
        }
    }
}