package com.example.buttoncounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private buttonIncre incrementalCounter;
    private String locLang;
    private TextView viewCount;
    private Button downIncre;
    private Button upIncre;
    private Button reset;
    private Switch langSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewCount = (TextView) this.findViewById(R.id.count);
        incrementalCounter = new buttonIncre();
        viewCount.setText(String.valueOf(incrementalCounter.increment));
        locLang = "en";
        downIncre = (Button) this.findViewById(R.id.downButton);
        upIncre = (Button) this.findViewById(R.id.upButton);
        reset = (Button) this.findViewById(R.id.resetButton);
        langSwitch = (Switch) this.findViewById(R.id.languageButton);
        //downIncre.setText(R.string.downButText);
        //upIncre.setText(R.string.upButText);
        //reset.setText(R.string.resetButton);
        //langSwitch.setText(R.string.languageButton);

    }
    //Restore state of app after change from saved state
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        incrementalCounter.increment = savedInstanceState.getInt("count");
        viewCount.setText(String.valueOf(incrementalCounter.increment));
        locLang = savedInstanceState.getString("locLang");
        if(locLang.equals("en")) {
            setLocale(this, "en");
            locLang = "en";
        }
        else {
            setLocale(this, "es");
            locLang = "es";
        }
        resetText();
    }
    //Save state of app prior to change
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("locLang", locLang);
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
        else if(v.getId() == R.id.languageButton) {
            if(locLang.equals("en")) {
                setLocale(this, "es");
                locLang = "es";
            }
            else {
                setLocale(this, "en");
                locLang = "en";
            }
            resetText();
        }
        viewCount.setText(String.valueOf(incrementalCounter.increment));
    }

    private static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
    private void resetText() {
        downIncre.setText(R.string.downButText);
        upIncre.setText(R.string.upButText);
        reset.setText(R.string.resetButton);
        langSwitch.setText(R.string.languageButton);
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