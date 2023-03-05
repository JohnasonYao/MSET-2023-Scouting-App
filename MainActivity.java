package com.example.mset2023scoutingapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private String firstName;
    private String lastName;
    private int match;
    private int team;
    private boolean mobility;
    private boolean autoEngaged;
    private boolean autoNotEngaged;
    private boolean autoNotDocked;
    private boolean engaged;
    private boolean notEngaged;
    private boolean notDocked;
    private boolean broke;
    private int totalScore;
    private int autoScore;
    private int teleScore;
    private int autoThirdRow;
    private int autoSecondRow;
    private int autoFirstRow;
    private int teleThirdRow;
    private int teleSecondRow;
    private int teleFirstRow;

    private EditText first;
    private EditText last;
    private EditText matchNumber;
    private EditText teamNumber;
    private EditText comments;
    private CheckBox Mobility;
    private CheckBox AutoNotEngaged;
    private CheckBox AutoEngaged;
    private CheckBox TeleNotEngaged;
    private CheckBox TeleEngaged;
    private CheckBox robotBroke;
    private Button submit;

    private ToggleButton three_1 = findViewById(R.id.three_1);
    private ToggleButton three_2 = findViewById(R.id.three_2);
    private ToggleButton three_3 = findViewById(R.id.three_3);
    private ToggleButton three_4 = findViewById(R.id.three_4);
    private ToggleButton three_5 = findViewById(R.id.three_5);
    private ToggleButton three_6 = findViewById(R.id.three_6);
    private ToggleButton three_7 = findViewById(R.id.three_7);
    private ToggleButton three_8 = findViewById(R.id.three_8);
    private ToggleButton three_9 = findViewById(R.id.three_9);
    private ToggleButton two_1 = findViewById(R.id.two_1);
    private ToggleButton two_2 = findViewById(R.id.two_2);
    private ToggleButton two_3 = findViewById(R.id.two_3);
    private ToggleButton two_4 = findViewById(R.id.two_4);
    private ToggleButton two_5 = findViewById(R.id.two_5);
    private ToggleButton two_6 = findViewById(R.id.two_6);
    private ToggleButton two_7 = findViewById(R.id.two_7);
    private ToggleButton two_8 = findViewById(R.id.two_8);
    private ToggleButton two_9 = findViewById(R.id.two_9);
    private ToggleButton one_1 = findViewById(R.id.one_1);
    private ToggleButton one_2 = findViewById(R.id.one_2);
    private ToggleButton one_3 = findViewById(R.id.one_3);
    private ToggleButton one_4 = findViewById(R.id.one_4);
    private ToggleButton one_5 = findViewById(R.id.one_5);
    private ToggleButton one_6 = findViewById(R.id.one_6);
    private ToggleButton one_7 = findViewById(R.id.one_7);
    private ToggleButton one_8 = findViewById(R.id.one_8);
    private ToggleButton one_9 = findViewById(R.id.one_9);

    private ToggleButton[][] scoreList = {
            {three_1, three_2, three_3, three_4, three_5, three_6, three_7, three_8, three_9},
            {two_1, two_2, two_3, two_4, two_5, two_6, two_7, two_8, two_9},
            {one_1, one_2, one_3, one_4, one_5, one_6, one_7, one_8, one_9}};

    private static final long autoTimerMilli = 15000;
    private TextView autoCountdown;
    private Button autoStartStop;
    private Button autoReset;
    private Boolean autoRun = false;
    private CountDownTimer autoCount;
    private long autoTimeLeft = autoTimerMilli;

    private static final long teleTimerMilli = 135000;
    private TextView teleCountdown;
    private Button teleStartStop;
    private Button teleReset;
    private Boolean teleRun = false;
    private CountDownTimer teleCount;
    private long teleTimeLeft = teleTimerMilli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = findViewById(R.id.first);
        last = findViewById(R.id.last);
        matchNumber = findViewById(R.id.matchNumber);
        teamNumber = findViewById(R.id.team);
        comments = findViewById(R.id.comments);

        Mobility = findViewById(R.id.checkBox4);
        AutoNotEngaged = findViewById(R.id.checkBox5);
        AutoEngaged = findViewById(R.id.checkBox6);
        TeleNotEngaged = findViewById(R.id.checkBox7);
        TeleEngaged = findViewById(R.id.checkBox9);
        robotBroke = findViewById(R.id.checkBox10);

        submit = findViewById(R.id.submit);

        autoCountdown = findViewById(R.id.autoCountdown);
        autoStartStop = findViewById(R.id.autoStartStop);
        autoReset = findViewById(R.id.autoReset);

        //defineButtons();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = first.getText().toString();
                lastName = last.getText().toString();
                match = Integer.parseInt(matchNumber.getText().toString());
                team = Integer.parseInt(teamNumber.getText().toString());
                mobility = Mobility.isChecked();
                autoEngaged = AutoEngaged.isChecked();
                autoNotEngaged = AutoNotEngaged.isChecked();
                autoNotDocked = !(autoEngaged || autoNotEngaged);
                engaged = TeleEngaged.isChecked();
                notEngaged = TeleEngaged.isChecked();
                notDocked = !(engaged || notEngaged);
                broke = robotBroke.isChecked();
                totalScore = autoScore + teleScore;

            }
        });

        autoStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autoRun){
                    pauseAuto();
                } else{
                    startAuto();
                }
            }
        });

        autoReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAuto();
            }
        });

        updateAuto();

        teleCountdown = findViewById(R.id.teleCountdown);
        teleStartStop = findViewById(R.id.teleStartStop);
        teleReset = findViewById(R.id.teleReset);

        teleStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teleRun){
                    pauseTele();
                } else{
                    startTele();
                }
            }
        });

        teleReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTele();
            }
        });

        updateTele();

    }



    private void startAuto(){
        autoCount = new CountDownTimer(autoTimeLeft, 1){
            @Override
            public void onTick(long x){
                autoTimeLeft = x;
                updateAuto();
            }

            @Override
            public void onFinish() {
                autoRun = false;
                autoTimeLeft = 0;
                autoStartStop.setText("Start");
                autoStartStop.setVisibility(View.INVISIBLE);
                autoReset.setVisibility(View.VISIBLE);
                autoFirstRow = autoScored()[0];
                autoSecondRow = autoScored()[1];
                autoThirdRow = autoScored()[2];
                autoScore = autoScored()[3];
            }
        }.start();
        autoRun = true;
        autoStartStop.setText("Stop");
        autoReset.setVisibility(View.INVISIBLE);
    }

    private void updateAuto(){
        int seconds = (int) autoTimeLeft/1000;
        int centiSec = (int) (autoTimeLeft % 1000)/10;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", seconds, centiSec);
        autoCountdown.setText(timeLeft);
    }

    private void pauseAuto(){
        autoCount.cancel();
        autoRun = false;
        autoStartStop.setText("Start");
        autoReset.setVisibility(View.VISIBLE);
    }

    public void resetAuto(){
        autoTimeLeft = autoTimerMilli;
        updateAuto();
        autoReset.setVisibility(View.INVISIBLE);
        autoStartStop.setVisibility(View.VISIBLE);
    }


    private void pauseTele(){
        teleCount.cancel();
        teleRun = false;
        teleStartStop.setText("Start");
        teleReset.setVisibility(View.VISIBLE);
    }

    public void resetTele(){
        teleTimeLeft = teleTimerMilli;
        updateTele();
        teleReset.setVisibility(View.INVISIBLE);
        teleStartStop.setVisibility(View.VISIBLE);
    }

    private void startTele(){
        teleCount = new CountDownTimer(teleTimeLeft, 1000){
            @Override
            public void onTick(long x){
                teleTimeLeft = x;
                updateTele();
            }

            @Override
            public void onFinish() {
                teleRun = false;
                teleStartStop.setText("Start");
                teleStartStop.setVisibility(View.INVISIBLE);
                teleReset.setVisibility(View.VISIBLE);
                teleFirstRow = teleScored()[0];
                teleSecondRow = teleScored()[1];
                teleThirdRow = teleScored()[2];
                teleScore = teleScored()[3];
            }
        }.start();
        teleRun = true;
        teleStartStop.setText("Stop");
        teleReset.setVisibility(View.INVISIBLE);
    }

    private void updateTele(){
        int min = (int) (teleTimeLeft/1000) / 60;
        int sec = (int) (teleTimeLeft/1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
        teleCountdown.setText(timeLeft);
    }

    private int[] autoScored(){
        int first = 0;
        int second = 0;
        int third = 0;
        int additional = 0;

        for(int i = 0; i < scoreList.length; i++){
            int count = 0;

            for(int j = 0; i < scoreList[1].length; j++){
                if(scoreList[i][j].isChecked()){
                    count++;
                }
            }

            if(i == 0){
                third = count;
            }
            else if(i == 1){
                second = count;
            } else{
                first = count;
            }
        }

        if(mobility){
            additional = additional + 3;
        }
        if(autoEngaged){
            additional = additional + 12;
        }
        if(autoNotEngaged){
            additional = additional + 8;
        }

        int[] scores = new int[]{first, second, third, first*2 + second*3 + third*5 + additional};
        return scores;
    }

    private int[] teleScored(){
        int first = 0;
        int second = 0;
        int third = 0;
        int additional = 0;

        for(int i = 0; i < scoreList.length; i++){
            int count = 0;

            for(int j = 0; i < scoreList[1].length; j++){
                if(scoreList[i][j].isChecked()){
                    count++;
                }
            }

            if(i == 0){
                third = count - autoThirdRow;
            }
            else if(i == 1){
                second = count - autoSecondRow;
            } else{
                first = count - autoFirstRow;
            }
        }

        if(autoEngaged){
            additional = additional + 10;
        }
        if(autoNotEngaged){
            additional = additional + 6;
        }

        int[] scores = new int[]{first, second, third, first*2 + second*3 + third*5 + additional};
        return scores;
    }

    /*private void defineButtons(){
        three_1.setOnClickListener(row3);
        three_2.setOnClickListener(row3);
        three_3.setOnClickListener(row3);
        three_4.setOnClickListener(row3);
        three_5.setOnClickListener(row3);
        three_6.setOnClickListener(row3);
        three_7.setOnClickListener(row3);
        three_8.setOnClickListener(row3);
        three_9.setOnClickListener(row3);
        two_1.setOnClickListener(row2);
        two_2.setOnClickListener(row2);
        two_3.setOnClickListener(row2);
        two_4.setOnClickListener(row2);
        two_5.setOnClickListener(row2);
        two_6.setOnClickListener(row2);
        two_7.setOnClickListener(row2);
        two_8.setOnClickListener(row2);
        two_9.setOnClickListener(row2);
        one_1.setOnClickListener(row1);
        one_2.setOnClickListener(row1);
        one_3.setOnClickListener(row1);
        one_4.setOnClickListener(row1);
        one_5.setOnClickListener(row1);
        one_6.setOnClickListener(row1);
        one_7.setOnClickListener(row1);
        one_8.setOnClickListener(row1);
        one_9.setOnClickListener(row1);
    }

    private final View.OnClickListener row3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (autoRun){
                if()
            }else{
                teleThirdRow++;
            }
            updateScore();
        }
    };

    private final View.OnClickListener row2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (autoRun){
                autoThirdRow++;
            }else{
                teleThirdRow++;
            }
            updateScore();
        }
    };

    private final View.OnClickListener row1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (autoRun){
                autoThirdRow++;
            }else{
                teleThirdRow++;
            }
            updateScore();
        }
    };*/

}