package com.example.crapsgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Button playButton, rerollButton;
    private ImageView die1, die2, die3, die4;
    private TextView pointTextView, rolledTextView, textPrompt;
    public int pointsValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        rerollButton = findViewById(R.id.rerollButton);

        pointTextView = findViewById(R.id.pointTextView);
        rolledTextView = findViewById(R.id.rolledTextView);
        textPrompt = findViewById(R.id.textPrompt);

        die1 = findViewById(R.id.die1);
        die2 = findViewById(R.id.die2);
        die3 = findViewById(R.id.die3);
        die4 = findViewById(R.id.die4);

        rerollButton.setEnabled(false);


        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                rolledTextView.setText("");

                textPrompt.setText("");

                int value1 = randomDiceValue();
                int value2 = randomDiceValue();

                int res1 = getResources().getIdentifier("die" + value1, "drawable", "com.example.crapsgame");
                int res2 = getResources().getIdentifier("die" + value2, "drawable", "com.example.crapsgame");
                int reset = getResources().getIdentifier("blank1", "drawable", "com.example.crapsgame" );

                die1.setImageResource(res1);
                die2.setImageResource(res2);
                die3.setImageResource(reset);
                die4.setImageResource(reset);


                 pointsValue = value1 + value2;


                pointTextView.setText(String.valueOf(pointsValue));

                switch(pointsValue) {
                    case 2:
                    case 3:
                    case 12:
                        textPrompt.setText("Sorry, you lose \n Play again?");
                        break;
                    case 7:
                    case 11:
                        textPrompt.setText("YOU WIN!");
                        break;
                    default:
                        pointTextView.setText(String.valueOf(pointsValue));
                        rerollButton.setEnabled(true);
                        playButton.setEnabled(false);
                        break;
                }

            }
        });

        rerollButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int value3 = randomDiceValue();
                int value4 = randomDiceValue();

                int res3 = getResources().getIdentifier("die" + value3, "drawable", "com.example.crapsgame");
                int res4 = getResources().getIdentifier("die" + value4, "drawable", "com.example.crapsgame");

                die3.setImageResource(res3);
                die4.setImageResource(res4);

                int rolledValue = value3 + value4;
                rolledTextView.setText(String.valueOf(rolledValue));


                if(rolledValue == pointsValue) {
                    textPrompt.setText("YOU WIN!");
                    rerollButton.setEnabled(false);
                    playButton.setEnabled(true);
                     } else if (rolledValue == 7) {
                        textPrompt.setText("Sorry, you lose \n Play again?");
                        rerollButton.setEnabled(false);
                        playButton.setEnabled(true);
                    } else {
                    rolledTextView.setText(String.valueOf(rolledValue));
                    textPrompt.setText("Roll again");

                }

            }
        });

    }



    public static int randomDiceValue() {
        return RANDOM.nextInt(6) +1;
    }
//    public void newGame(View view) {
//        pointTextView.setText("");
//        rolledTextView.setText("");
//
//        int reset = getResources().getIdentifier("blank1", "drawable", "com.example.crapsgame" );
//        die1.setImageResource(reset);
//        die2.setImageResource(reset);
//        die3.setImageResource(reset);
//        die4.setImageResource(reset);
//    }

}
