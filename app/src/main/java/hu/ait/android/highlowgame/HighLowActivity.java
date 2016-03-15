package hu.ait.android.highlowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class HighLowActivity extends AppCompatActivity {

    public static final String KEY_GENERATED = "KEY_GENERATED";
    public static final String KEY_TEXT_DATA = "KEY_TEXT_DATA";
    public static final String GUESS_TEXT_DATA = "GUESS_TEXT_DATA";
    public static final String NUM_GUESSES = "NUM_GUESSES";
    private int generatedNum = 0;
    private TextView tvData;
    private TextView numGuessMsg;
    private int numGuess = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_low);

        tvData = (TextView) findViewById(R.id.tvData);
        numGuessMsg = (TextView) findViewById(R.id.numGuesses);

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(KEY_GENERATED)) {
            generatedNum = savedInstanceState.getInt(KEY_GENERATED);
            numGuess = savedInstanceState.getInt(NUM_GUESSES);
            numGuessMsg.setText(savedInstanceState.getString(GUESS_TEXT_DATA));
            tvData.setText(savedInstanceState.getString(KEY_TEXT_DATA));
        } else {
            generateNewNumber();
        }

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        Button btnGuess = (Button) findViewById(R.id.btnGuess);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(etGuess.getText().toString())) {
                    int guess = Integer.parseInt(
                            etGuess.getText().toString());

                    if (guess == generatedNum) {
                        tvData.setText(R.string.Winner);

                        Intent intentStartDialog = new Intent();
                        intentStartDialog.setClass(HighLowActivity.this, ResultDialogActivity.class);
                        startActivity(intentStartDialog);

                    } else if (guess < generatedNum) {
                        tvData.setText(R.string.guessIsSmaller);
                    } else if (guess > generatedNum) {
                        tvData.setText(R.string.guessLarger);
                    }
                    numGuess++;
                    numGuessMsg.setText("Number of Guesses: " + Integer.toString(numGuess));
                } else {
                    etGuess.setError(getString(R.string.enterANumber));
                }
                etGuess.setText("");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_GENERATED, generatedNum);
        outState.putInt(NUM_GUESSES, numGuess);
        outState.putString(KEY_TEXT_DATA, tvData.getText().toString());
        outState.putString(GUESS_TEXT_DATA, numGuessMsg.getText().toString());
    }

    private void generateNewNumber() {
        Random rand = new Random(System.currentTimeMillis());
        // generate new number between 0 and 99
        generatedNum = rand.nextInt(100);
    }

}