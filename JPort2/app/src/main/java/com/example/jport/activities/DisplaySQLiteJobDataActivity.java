package com.example.jport.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import java.util.ArrayList;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.jport.R;
import com.example.jport.helper.JobListAdapter;
import com.example.jport.sql.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class DisplaySQLiteJobDataActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    JobListAdapter listAdapter ;
    ListView LISTVIEW;
    ArrayList<String> ID_Array;
    ArrayList<String> COMPANY_Array;
    ArrayList<String> TITLE_Array;
    ArrayList<String> COUNTRY_Array;
    ArrayList<String> PROVINENCE_Array;
    ArrayList<String> YEAR_Array;
    ArrayList<String> PHONE_Array;
    ArrayList<String> SALARY_Array;
    ArrayList<String> DES_Array;
    private AppCompatButton appCompatButtonAddJob;
    private ImageButton search;
    private TextInputEditText textInputEditTextSearchCountry;
    private TextInputEditText textInputEditTextSearchProvinence;
    String SearchCountry;
    String SearchProvi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_job_data);
        LISTVIEW = (ListView) findViewById(R.id.listViewJob);
        textInputEditTextSearchCountry = (TextInputEditText) findViewById(R.id.textInputEditTextSearchCountry);
        textInputEditTextSearchProvinence = (TextInputEditText) findViewById(R.id.textInputEditTextSearchProvinence);
        search =(ImageButton) findViewById(R.id.searchJob);
        search.setOnClickListener(this);
        SearchCountry="";
        SearchProvi="";
        ID_Array = new ArrayList<String>();
        COMPANY_Array = new ArrayList<String>();
        TITLE_Array = new ArrayList<String>();
        COUNTRY_Array = new ArrayList<String>();
        PROVINENCE_Array = new ArrayList<String>();
        YEAR_Array = new ArrayList<String>();
        PHONE_Array = new ArrayList<String>();
        SALARY_Array = new ArrayList<String>();
        DES_Array = new ArrayList<String>();
        sqLiteHelper = new DatabaseHelper(this);
        appCompatButtonAddJob = (AppCompatButton) findViewById(R.id.Add_Job);
        appCompatButtonAddJob.setOnClickListener(this);
        ShowSQLiteDBdata(SearchCountry,SearchProvi) ;

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.Add_Job:
                Intent AddJob = new Intent(getApplicationContext(), HireActivity.class);
                startActivity(AddJob);
                break;

            case R.id.searchJob:
                SearchCountry=textInputEditTextSearchCountry.getText().toString().trim();
                SearchProvi=textInputEditTextSearchProvinence.getText().toString().trim();
                ShowSQLiteDBdata(SearchCountry,SearchProvi) ;
                break;


        }

    }




    private void ShowSQLiteDBdata( String SearchCountry, String SreachProvinence) {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        String Que = "select * from  "+ sqLiteHelper.TABLE_USER_JOB + " where " +sqLiteHelper. COLUMN_USER_JOB_COUNTRY+
                " like \"%"+SearchCountry+"%\""+" and " + sqLiteHelper.COLUMN_USER_JOB_PROVINCE + "  like  \"%"+SearchProvi+"%\"";

        cursor = sqLiteDatabase.rawQuery(Que, null);

        ID_Array.clear();
        COMPANY_Array.clear();
        TITLE_Array.clear();
        COUNTRY_Array.clear();
        PROVINENCE_Array.clear();
        YEAR_Array.clear();
        PHONE_Array.clear();
        SALARY_Array.clear();
        DES_Array.clear();


        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_JOB_ID)));
                COMPANY_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_COMPANY)));
                TITLE_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_JOB_TITLE)));
                COUNTRY_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_JOB_COUNTRY)));
                PROVINENCE_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_JOB_PROVINCE)));
                YEAR_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_REQUIRED_YEARS_OF_EMPLOTEMENT)));
                SALARY_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_OFFER_SALLARY)));
                PHONE_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_JOB_PHONE)));
                DES_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_JOB_DESCRIPTION)));


            } while (cursor.moveToNext());
        }

        listAdapter = new JobListAdapter(DisplaySQLiteJobDataActivity.this,

                COMPANY_Array,
                TITLE_Array,
                COUNTRY_Array,
                PROVINENCE_Array,
                YEAR_Array,
                SALARY_Array,
                PHONE_Array,
                DES_Array,
                ID_Array

        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}