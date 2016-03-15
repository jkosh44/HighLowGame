package hu.ait.android.highlowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.start);
        Button helpButton = (Button) findViewById(R.id.help);
        Button aboutButton = (Button) findViewById(R.id.about);

        startButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentNewGame = new Intent();
                        intentNewGame.setClass(MainActivity.this,
                                HighLowActivity.class);
                        startActivity(intentNewGame);
                    }
                }
        );

        helpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), R.string.helpMessage, Toast.LENGTH_LONG).show();
                    }
                }
        );

        aboutButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), R.string.aboutMessage, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


}
