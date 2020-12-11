package com.example.rpsgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView player1_image, player2_image, rock, paper, scissor, reset, info;
    TextView player1_score, player2_score, winning_text, player1_set, player2_set;
    private String[] names = {"rock", "scissor", "paper"};
    String player2, player1;
    static int score1 = 0, score2 = 0, set1 = 0, set2 = 0;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = new SharedPref(this);

        player1_image = findViewById(R.id.player1_image);
        player1_score = findViewById(R.id.player1_score);
        player2_image = findViewById(R.id.player2_image);
        player2_score = findViewById(R.id.player2_score);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);
        winning_text = findViewById(R.id.winning_text);
        player1_set = findViewById(R.id.player1_set);
        player2_set = findViewById(R.id.player2_set);
        reset = findViewById(R.id.reset);
        info = findViewById(R.id.info);

        player1_score.setText(String.valueOf(score1));
        player2_score.setText(String.valueOf(score2));

        set1 = sharedPref.get_ScoreSet1();
        set2 = sharedPref.get_ScoreSet2();

        player1_set.setText(String.valueOf(set1));
        player2_set.setText(String.valueOf(set2));

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionSelected("rock");
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionSelected("paper");
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionSelected("scissor");
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Reset Game")
                        .setMessage("Are you sure you want to reset the game")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPref.put_ScoreSet1(0);
                                sharedPref.put_ScoreSet2(0);
                                set1 = 0;
                                set2 = 0;
                                player1_set.setText(String.valueOf(set1));
                                player2_set.setText(String.valueOf(set2));
                                score1 = 0;
                                score2 = 0;
                                player2_score.setText(String.valueOf(score2));
                                player1_score.setText(String.valueOf(score1));
                                winning_text.setText("");
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void actionSelected(String action) {
        int random = new Random().nextInt(3);
        player2 = names[random];

        if (action.equals("paper")) {
            player1_image.setImageResource(R.drawable.paperc);
        } else if (action.equals("scissor")) {
            player1_image.setImageResource(R.drawable.scissorc);
        } else {
            player1_image.setImageResource(R.drawable.rockc);
        }

        if (names[random].equals("rock")) {
            player2_image.setImageResource(R.drawable.rockc_r);
        } else if (names[random].equals("scissor")) {
            player2_image.setImageResource(R.drawable.scissorc_r);
        } else {
            player2_image.setImageResource(R.drawable.paperc_r);
        }

        if ((action.equals("rock") && player2.equals("scissor"))
                || (action.equals("scissor") && player2.equals("paper"))
                || (action.equals("paper") && player2.equals("rock"))) {
            score1 = score1 + 1;
            player1_score.setText(String.valueOf(score1));
            winning_text.setText("You won!");
            winning_text.setTextColor(getResources().getColor(R.color.blue));
        } else if ((action.equals("scissor") && player2.equals("rock"))
                || (action.equals("paper") && player2.equals("scissor"))
                || (action.equals("rock") && player2.equals("paper"))) {
            score2 = score2 + 1;
            player2_score.setText(String.valueOf(score2));
            winning_text.setText("Opponent won.");
            winning_text.setTextColor(getResources().getColor(R.color.red));
        } else {
            score1 = score1 + 1;
            score2 = score2 + 1;
            player2_score.setText(String.valueOf(score2));
            player1_score.setText(String.valueOf(score1));
            winning_text.setText("Match Draw.");
            winning_text.setTextColor(getResources().getColor(R.color.gray));
        }

        if (score1 == 10 && score2 == 10) {
            winning_text.setText("Set Draw.");
            winning_text.setTextColor(getResources().getColor(R.color.gray));
            score1 = 0;
            player1_score.setText(String.valueOf(score1));
            score2 = 0;
            player2_score.setText(String.valueOf(score2));
            set1 = set1 + 1;
            set2 = set2 + 1;
            player1_set.setText(String.valueOf(set1));
            player2_set.setText(String.valueOf(set2));
            sharedPref.put_ScoreSet1(set1);
            sharedPref.put_ScoreSet2(set2);
        } else if (score1 == 10) {
            winning_text.setText("You won the set!");
            winning_text.setTextColor(getResources().getColor(R.color.blue));
            score1 = 0;
            player1_score.setText(String.valueOf(score1));
            score2 = 0;
            player2_score.setText(String.valueOf(score2));
            set1 = set1 + 1;
            player1_set.setText(String.valueOf(set1));
            sharedPref.put_ScoreSet1(set1);
        } else if (score2 == 10) {
            winning_text.setText("Opponent won the set.");
            winning_text.setTextColor(getResources().getColor(R.color.red));
            score1 = 0;
            player1_score.setText(String.valueOf(score1));
            score2 = 0;
            player2_score.setText(String.valueOf(score2));
            set2 = set2 + 1;
            player2_set.setText(String.valueOf(set2));
            sharedPref.put_ScoreSet2(set2);
        }
    }
}