package com.example.jport.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.jport.R;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private CardView Hire;
    private CardView Job;
    private CardView Resume;
    private String nameFromIntent;
    private ImageButton signoutBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initViews();
        initListeners();
        nameFromIntent = getIntent().getStringExtra("EMAIL");

    }

    private void initViews(){

        Hire = (CardView) findViewById(R.id.HireCard);
        Job = (CardView) findViewById(R.id.JobCard);
        Resume = (CardView) findViewById(R.id.ResumeCard);
        signoutBut=(ImageButton) findViewById(R.id.imageSignOutButton);
    }

    private void initListeners(){
        Hire.setOnClickListener(this);
        Job.setOnClickListener(this);
        Resume.setOnClickListener(this);
        signoutBut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.JobCard:
                Intent JobFinding = new Intent(getApplicationContext(), DisplaySQLiteJobDataActivity.class);
                startActivity(JobFinding);
                break;

            case R.id.HireCard:
                Intent intentHire = new Intent(getApplicationContext(), DisplaySQLiteResumeDataActivity.class);
                startActivity(intentHire);
                break;

            case R.id.ResumeCard:
                Intent intentAddResume = new Intent(getApplicationContext(), ResumeActivity.class);
                startActivity(intentAddResume);
                break;

            case R.id.imageSignOutButton:
                Intent LogIn = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(LogIn);
                break;
        }
    }
}
