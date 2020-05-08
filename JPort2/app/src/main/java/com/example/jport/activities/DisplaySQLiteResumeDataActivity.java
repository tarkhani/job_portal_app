package com.example.jport.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.jport.R;
import com.example.jport.helper.ResumeListAdapter;
import com.example.jport.sql.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class DisplaySQLiteResumeDataActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ResumeListAdapter listAdapter ;
    ListView LISTVIEW;
    ArrayList<String> ID_Array;
    ArrayList<String> DEGREE_Array;
    ArrayList<String> MAJOR_Array;
    ArrayList<String> COUNTRY_Array;
    ArrayList<String> PROVINENCE_Array;
    ArrayList<String> YEAR_Array;
    ArrayList<String> PHONE_Array;
    ArrayList<String> SALARY_Array;
    ArrayList<String> DES_Array;
    ArrayList<String> NAME_Array;
    private AppCompatButton appCompatButtonUpdateResume;
    private ImageButton search;
    private TextInputEditText textInputEditTextSearchCountry;
    private TextInputEditText textInputEditTextSearchProvinence;
    String SearchCountry;
    String SearchProvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_resume_data);
        LISTVIEW = (ListView) findViewById(R.id.listViewResume);
        ID_Array= new ArrayList<String>();
        DEGREE_Array= new ArrayList<String>();
        MAJOR_Array= new ArrayList<String>();
        COUNTRY_Array= new ArrayList<String>();
        PROVINENCE_Array= new ArrayList<String>();
        YEAR_Array= new ArrayList<String>();
        PHONE_Array= new ArrayList<String>();
        SALARY_Array= new ArrayList<String>();
        DES_Array= new ArrayList<String>();
        NAME_Array= new ArrayList<String>();
        sqLiteHelper = new DatabaseHelper(this);

        appCompatButtonUpdateResume = (AppCompatButton) findViewById(R.id.Add_UpadteResume);
        appCompatButtonUpdateResume.setOnClickListener(this);
        textInputEditTextSearchCountry = (TextInputEditText) findViewById(R.id.textInputEditTextRSearchCountry);
        textInputEditTextSearchProvinence = (TextInputEditText) findViewById(R.id.textInputEditTextRSearchProvinence);
        search =(ImageButton) findViewById(R.id.searchResume);
        search.setOnClickListener(this);
        SearchCountry="";
        SearchProvi="";
        ShowSQLiteDBdata(SearchCountry,SearchProvi) ;

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.Add_UpadteResume:
                Intent updateRES = new Intent(getApplicationContext(), ResumeActivity.class);
                startActivity(updateRES);
                break;

            case R.id.searchResume:
                SearchCountry=textInputEditTextSearchCountry.getText().toString().trim();
                SearchProvi=textInputEditTextSearchProvinence.getText().toString().trim();
                ShowSQLiteDBdata(SearchCountry,SearchProvi) ;
                break;


        }

    }


    private void ShowSQLiteDBdata( String SearchCountry, String SreachProvinence) {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        String Que = "select * from  "+ sqLiteHelper.TABLE_USER_RESUME + " where " +sqLiteHelper. COLUMN_USER_COUNTRY+
                " like \"%"+SearchCountry+"%\""+" and " + sqLiteHelper.COLUMN_USER_PROVINCE + "  like  \"%"+SearchProvi+"%\"";

        cursor = sqLiteDatabase.rawQuery(Que, null);


        ID_Array.clear();
        DEGREE_Array.clear();
        MAJOR_Array.clear();
        COUNTRY_Array.clear();
        PROVINENCE_Array.clear();
        YEAR_Array.clear();
        PHONE_Array.clear();
        SALARY_Array.clear();
        DES_Array.clear();
        NAME_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_RESUME_ID)));
                DEGREE_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_DEGREE)));
                MAJOR_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_MAJOR)));
                COUNTRY_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_COUNTRY)));
                PROVINENCE_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_PROVINCE)));
                YEAR_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_YEARS_OF_EMPLOTEMENT)));
                SALARY_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_SALLARY)));
                PHONE_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_PHONE)));
                DES_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_DESCRIPTION)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(sqLiteHelper.COLUMN_USER_NAME)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ResumeListAdapter(DisplaySQLiteResumeDataActivity.this,

                DEGREE_Array,
                MAJOR_Array,
                COUNTRY_Array,
                PROVINENCE_Array,
                YEAR_Array,
                SALARY_Array,
                PHONE_Array,
                DES_Array,
                ID_Array,
                NAME_Array

        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}