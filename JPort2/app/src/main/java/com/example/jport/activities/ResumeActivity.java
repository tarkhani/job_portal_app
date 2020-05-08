package com.example.jport.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import com.example.jport.R;
import com.example.jport.helper.InputValidation;
import com.example.jport.model.Resume;
import com.example.jport.sql.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ResumeActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = ResumeActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutDegree;
    private TextInputLayout textInputLayoutMajor;
    private TextInputLayout textInputLayoutCountry;
    private TextInputLayout textInputLayoutProvince;
    private TextInputLayout textInputLayoutYears;
    private TextInputLayout textInputLayoutSalary;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutDescription;
    private TextInputEditText textInputEditTextDegree;
    private TextInputEditText textInputEditTextMajor;
    private TextInputEditText textInputEditTextCountry;
    private TextInputEditText textInputEditTextProvince;
    private TextInputEditText textInputEditTextYears;
    private TextInputEditText textInputEditTextSalary;
    private TextInputEditText textInputEditTextPhone;
    private TextInputEditText textInputEditTextDescription;
    private TextInputEditText textInputEditTextName;
    private AppCompatButton appCompatButtonUpdate;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    DatabaseHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    private Resume resume;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        sqLiteHelper = new DatabaseHelper(this);
        initViews();
        initListeners();
        initObjects();

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        String query1="select "+ "*" +" from "+  DatabaseHelper.TABLE_USER + " where user_email = ? ";
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor;
        prefsEditor = myPrefs.edit();
        String StoredValue=myPrefs.getString("EMAIL", "");
        String[] selectionArgs = {StoredValue};
        Cursor cursor = sqLiteDatabase.rawQuery(query1, selectionArgs);
        cursor.moveToFirst();

        int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_ID));
        String query2="select * from "+ DatabaseHelper.TABLE_USER_RESUME + " where user_id = ? ";
        String string =_id+"";
        String[] selectionArg2  = {string};
        Cursor cursor2 = sqLiteDatabase.rawQuery(query2 , selectionArg2);






        if (!cursor2.moveToFirst()) {

        }
        else{
            cursor2.moveToFirst();
            String MAJOR = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_MAJOR));
            String DEGREE = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_DEGREE));
            String COUNTRY = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_COUNTRY));
            String PROVINCE = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_PROVINCE));
            String DESCRIPTION = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_DESCRIPTION));
            String YEAR = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_YEARS_OF_EMPLOTEMENT));
            String SALLARY = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_SALLARY));
            String PHONE = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_PHONE));
            String Name = cursor2.getString(cursor2.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME));

            textInputEditTextDegree.setText(DEGREE);
            textInputEditTextMajor.setText(MAJOR);
            textInputEditTextCountry.setText(COUNTRY);
            textInputEditTextProvince.setText(PROVINCE);
            textInputEditTextYears.setText(String.valueOf(YEAR));
            textInputEditTextSalary.setText(String.valueOf(SALLARY));
            textInputEditTextPhone.setText(PHONE);
            textInputEditTextDescription.setText(DESCRIPTION);
            textInputEditTextName.setText(Name);



        }


    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);



        textInputLayoutDegree = (TextInputLayout) findViewById(R.id.textInputLayoutDegree);
        textInputLayoutMajor = (TextInputLayout) findViewById(R.id.textInputLayoutMajor);
        textInputLayoutCountry = (TextInputLayout) findViewById(R.id.textInputLayoutCountry);
        textInputLayoutProvince = (TextInputLayout) findViewById(R.id.textInputLayoutProvince);
        textInputLayoutYears = (TextInputLayout) findViewById(R.id.textInputLayoutYears);
        textInputLayoutSalary = (TextInputLayout) findViewById(R.id.textInputLayoutSalary);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);
        textInputLayoutDescription= (TextInputLayout) findViewById(R.id.textInputLayoutDescription);

        textInputEditTextDegree = (TextInputEditText) findViewById(R.id.textInputEditTextDegree);
        textInputEditTextMajor = (TextInputEditText) findViewById(R.id.textInputEditTextMajor);
        textInputEditTextCountry = (TextInputEditText) findViewById(R.id.textInputEditTextCountry);
        textInputEditTextProvince = (TextInputEditText) findViewById(R.id.textInputEditTextProvince);
        textInputEditTextYears = (TextInputEditText) findViewById(R.id.textInputEditTextYears);
        textInputEditTextSalary = (TextInputEditText) findViewById(R.id.textInputEditTextSalary);
        textInputEditTextPhone = (TextInputEditText) findViewById(R.id.textInputEditTextPhone);
        textInputEditTextDescription= (TextInputEditText) findViewById(R.id.textInputEditTextDescription);
        textInputEditTextName= (TextInputEditText) findViewById(R.id.textInputEditTextname);



        appCompatButtonUpdate = (AppCompatButton) findViewById(R.id.appCompatButtonUpdate);


    }

    private void initListeners(){
        appCompatButtonUpdate.setOnClickListener(this);

    }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        resume = new Resume();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.appCompatButtonUpdate:
                postDataToSQLite();

                break;

        }
    }

    private void postDataToSQLite(){

        if (!inputValidation.isInputEditTextFilled(textInputEditTextYears, textInputLayoutYears, getString(R.string.error_message_number))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSalary, textInputLayoutSalary, getString(R.string.error_message_number))) {
            return;
        }
        resume.setName(textInputEditTextName.getText().toString().trim());
        resume.setCountry(textInputEditTextCountry.getText().toString().trim());
        resume.setDegree(textInputEditTextDegree.getText().toString().trim());
        resume.setDescription(textInputEditTextDescription.getText().toString().trim());
        resume.setMajor(textInputEditTextMajor.getText().toString().trim());
        resume.setPhone(textInputEditTextPhone.getText().toString().trim());
        resume.setProvince(textInputEditTextProvince.getText().toString().trim());
        resume.setYearsOfExper(Integer.parseInt(textInputEditTextYears.getText().toString().trim()));
        resume.setSalary(Integer.parseInt(textInputEditTextSalary.getText().toString().trim()));
        String nameFromIntent = getIntent().getStringExtra("EMAIL");

        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String StoredValue=myPrefs.getString("EMAIL", "");

        databaseHelper.addResume(resume,StoredValue);
        Intent intentBackMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentBackMain);


    }
}
