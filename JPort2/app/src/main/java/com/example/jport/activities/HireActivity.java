package com.example.jport.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.jport.R;
import com.example.jport.helper.InputValidation;
import com.example.jport.model.job;
import com.example.jport.sql.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class HireActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = HireActivity.this;

    private NestedScrollView nestedScrollView;
    private String nameFromIntent;

    private TextInputLayout textInputLayoutCompany;
    private TextInputLayout textInputLayoutPosition;
    private TextInputLayout textInputLayoutJobCountry;
    private TextInputLayout textInputLayoutJobProvince;
    private TextInputLayout textInputLayoutJobExpYears;
    private TextInputLayout textInputLayoutJobSalary;
    private TextInputLayout textInputLayoutJobContactPhone;
    private TextInputLayout textInputLayoutJobDescription;

    private TextInputEditText textInputEditTextCompany;
    private TextInputEditText textInputEditTextPosition;
    private TextInputEditText textInputEditTextJobCountry;
    private TextInputEditText textInputEditTextJobProvince;
    private TextInputEditText textInputEditTextJobExpYears;
    private TextInputEditText textInputEditTextJobSalary;
    private TextInputEditText textInputEditTextJobContactPhone;
    private TextInputEditText textInputEditTextJobDescription;

    private AppCompatButton appCompatButtonPostJob;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private job job;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        initViews();
        initListeners();
        initObjects();
    }
    private void initViews(){

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutCompany = (TextInputLayout) findViewById(R.id.textInputLayoutCompany);
        textInputLayoutPosition = (TextInputLayout) findViewById(R.id.textInputLayoutPosition);
        textInputLayoutJobCountry = (TextInputLayout) findViewById(R.id.textInputLayoutJobCountry);
        textInputLayoutJobProvince = (TextInputLayout) findViewById(R.id.textInputLayoutJobProvince);
        textInputLayoutJobExpYears = (TextInputLayout) findViewById(R.id.textInputLayoutJobExpYears);
        textInputLayoutJobSalary = (TextInputLayout) findViewById(R.id.textInputLayoutJobSalary);
        textInputLayoutJobContactPhone = (TextInputLayout) findViewById(R.id.textInputLayoutJobContactPhone);
        textInputLayoutJobDescription= (TextInputLayout) findViewById(R.id.textInputLayoutJobDescription);

        textInputEditTextCompany = (TextInputEditText) findViewById(R.id.textInputEditTextCompany);
        textInputEditTextPosition = (TextInputEditText) findViewById(R.id.textInputEditTextPosition);
        textInputEditTextJobCountry = (TextInputEditText) findViewById(R.id.textInputEditTextJobCountry);
        textInputEditTextJobProvince = (TextInputEditText) findViewById(R.id.textInputEditTextJobProvince);
        textInputEditTextJobExpYears = (TextInputEditText) findViewById(R.id.textInputEditTextJobExpYears);
        textInputEditTextJobSalary = (TextInputEditText) findViewById(R.id.textInputEditTextJobSalary);
        textInputEditTextJobContactPhone = (TextInputEditText) findViewById(R.id.textInputEditTextJobContactPhone);
        textInputEditTextJobDescription= (TextInputEditText) findViewById(R.id.textInputEditTextJobDescription);

        appCompatButtonPostJob = (AppCompatButton) findViewById(R.id.appCompatButtonPostJob);


    }

    private void initListeners(){ appCompatButtonPostJob.setOnClickListener(this); }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        job  = new job();
    }

    private void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextJobExpYears, textInputLayoutJobExpYears, getString(R.string.error_message_number))) { return; }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextJobSalary, textInputLayoutJobSalary, getString(R.string.error_message_number))) { return; }

        job.setCountry(textInputEditTextJobCountry.getText().toString().trim());
        job.setCompany(textInputEditTextCompany.getText().toString().trim());
        job.setDescription(textInputEditTextJobDescription.getText().toString().trim());
        job.setTitle(textInputEditTextPosition.getText().toString().trim());
        job.setPhone(textInputEditTextJobContactPhone.getText().toString().trim());
        job.setProvince(textInputEditTextJobProvince.getText().toString().trim());
        job.setYearsOfExper(Integer.parseInt(textInputEditTextJobExpYears.getText().toString().trim()));
        job.setSalary(Integer.parseInt(textInputEditTextJobSalary.getText().toString().trim()));
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String StoredValue=myPrefs.getString("EMAIL", "");
        databaseHelper.addJOB(job, StoredValue);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonPostJob:
                postDataToSQLite();
                Intent JobFinding = new Intent(getApplicationContext(), DisplaySQLiteJobDataActivity.class);
                nameFromIntent = getIntent().getStringExtra("EMAIL");
                JobFinding.putExtra("EMAIL", nameFromIntent);
                startActivity(JobFinding);
                break;

        }
    }

}
