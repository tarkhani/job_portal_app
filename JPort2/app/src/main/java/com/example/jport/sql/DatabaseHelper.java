package com.example.jport.sql;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jport.model.User;
import com.example.jport.model.Resume;
import com.example.jport.model.job;

public class DatabaseHelper  extends SQLiteOpenHelper{

   private static final int DATABASE_VERSION = 1;

   private static final String DATABASE_NAME = "UserManager.db";

   public static final String TABLE_USER = "user";
   public static final String TABLE_USER_RESUME = "resume";
   public static final String TABLE_USER_JOB = "job";

   public static final String COLUMN_USER_ID = "user_id";
   public static final String COLUMN_USER_EMAIL = "user_email";
   public static final String COLUMN_USER_PASSWORD = "user_password";

   public static final String COLUMN_USER_RESUME_ID = "user_resume_id";
    public static final String COLUMN_USER_NAME = "user_name";
   public static final String COLUMN_USER_DEGREE= "user_degree";
   public static final String COLUMN_USER_MAJOR = "user_major";
   public static final String COLUMN_USER_COUNTRY = "user_country";
   public static final String COLUMN_USER_PROVINCE = "user_province";
   public static final String COLUMN_USER_YEARS_OF_EMPLOTEMENT = "user_years_of_employment";
   public static final String COLUMN_USER_PHONE = "user_phone";
   public static final String COLUMN_USER_SALLARY = "user_sallary";
   public static final String COLUMN_USER_DESCRIPTION = "user_description";

   public static final String COLUMN_USER_JOB_ID = "user_job_id";
   public static final String COLUMN_USER_COMPANY= "user_company";
   public static final String COLUMN_USER_JOB_TITLE = "user_job_title";
   public static final String COLUMN_USER_JOB_COUNTRY = "user_job_country";
   public static final String COLUMN_USER_JOB_PROVINCE = "user_job_province";
   public static final String COLUMN_USER_REQUIRED_YEARS_OF_EMPLOTEMENT = "user_required_years_of_employment";
   public static final String COLUMN_USER_JOB_PHONE = "user_job_phone";
   public static final String COLUMN_USER_OFFER_SALLARY = "user_offer_sallary";
   public static final String COLUMN_USER_JOB_DESCRIPTION = "user_job_description";



   private String CREATE_USER_RESUME_TABLE = "CREATE TABLE " + TABLE_USER_RESUME + "("
           + COLUMN_USER_RESUME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_USER_NAME + " TEXT,"+ COLUMN_USER_ID + " INTEGER NOT NULL UNIQUE,"
           + COLUMN_USER_MAJOR + " TEXT," + COLUMN_USER_DEGREE + " TEXT,"+ COLUMN_USER_COUNTRY + " TEXT,"
           + COLUMN_USER_PROVINCE+ " TEXT,"  + COLUMN_USER_YEARS_OF_EMPLOTEMENT + " INTEGER," + COLUMN_USER_PHONE+ " TEXT,"
           + COLUMN_USER_SALLARY+ " INTEGER," + COLUMN_USER_DESCRIPTION+ " TEXT," + "FOREIGN KEY" +"("+ COLUMN_USER_ID + ")" + "REFERENCES  " +
            TABLE_USER + "("+ COLUMN_USER_ID +"))";


   private String CREATE_USER_JOB_TABLE = "CREATE TABLE " + TABLE_USER_JOB + "("
           + COLUMN_USER_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_ID + " INTEGER,"
            + COLUMN_USER_COMPANY + " TEXT,"
           + COLUMN_USER_JOB_TITLE + " TEXT," + COLUMN_USER_JOB_COUNTRY + " TEXT,"+ COLUMN_USER_JOB_PROVINCE + " TEXT,"
           + COLUMN_USER_REQUIRED_YEARS_OF_EMPLOTEMENT + " INTEGER," + COLUMN_USER_JOB_PHONE+ " TEXT,"
           + COLUMN_USER_OFFER_SALLARY+ " INTEGER," + COLUMN_USER_JOB_DESCRIPTION+ " TEXT," +
           "FOREIGN KEY" +"("+ COLUMN_USER_ID + ")" + "REFERENCES  " +
           TABLE_USER + "("+ COLUMN_USER_ID +"))";



   private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
           + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
           + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";


   private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
   private String DROP_USER_RESUME_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER_RESUME;
   private String DROP_USER_JOB_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER_JOB;


   public DatabaseHelper(Context context){
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db){
      db.execSQL("PRAGMA foreign_keys = ON");
      db.execSQL(CREATE_USER_TABLE);
      db.execSQL(CREATE_USER_RESUME_TABLE);
      db.execSQL(CREATE_USER_JOB_TABLE);



   }

   @Override
   public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      db.execSQL(DROP_USER_TABLE);
      db.execSQL(DROP_USER_RESUME_TABLE);
      db.execSQL(DROP_USER_JOB_TABLE);


      onCreate(db);
   }

   public void addUser(User user){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(COLUMN_USER_EMAIL, user.getEmail());
      values.put(COLUMN_USER_PASSWORD, user.getPassword());

      db.insert(TABLE_USER, null, values);
      db.close();
   }

   public void addResume(Resume resume, String EMAIL){

      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
      String query1="select "+ COLUMN_USER_ID +" from "+  TABLE_USER + " where user_email = ? ";
      String[] selectionArgs = {EMAIL};
      Cursor cursor = db.rawQuery(query1, selectionArgs);
      cursor.moveToFirst();
      int _id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
      String query2="select "+ COLUMN_USER_ID +" from "+  TABLE_USER_RESUME + " where user_id = ? ";
      String string =_id+"";
      String[] selectionArg2  = {string};
      Cursor cursor2 = db.rawQuery(query2 , selectionArg2);

      resume.setUserID(_id);
      values.put(COLUMN_USER_ID, resume.getUserID());
       values.put(COLUMN_USER_NAME, resume.getName());
      values.put(COLUMN_USER_DEGREE, resume.getDegree());
      values.put(COLUMN_USER_MAJOR, resume.getMajor());
      values.put(COLUMN_USER_COUNTRY, resume.getCountry());
      values.put(COLUMN_USER_PROVINCE, resume.getProvince());
      values.put(COLUMN_USER_YEARS_OF_EMPLOTEMENT,resume.getYearsOfExper());
      values.put(COLUMN_USER_PHONE, resume.getPhone());
      values.put(COLUMN_USER_SALLARY, resume.getSalary());
      values.put(COLUMN_USER_DESCRIPTION, resume.getDescription());


      if ( !cursor2.moveToFirst()){

         db.insert(TABLE_USER_RESUME, null, values);
         db.close();

      } else{

         db.update(TABLE_USER_RESUME,  values," user_id = ? ",selectionArg2);
         db.close();

      }

   }

   public void addJOB(job job , String EMAIL){

      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
      String query1="select "+ COLUMN_USER_ID +" from "+  TABLE_USER + " where user_email = ? ";
      String[] selectionArgs = {EMAIL};
      Cursor cursor = db.rawQuery(query1, selectionArgs);
      cursor.moveToFirst();
      int _id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
      job.setUserID(_id);

      values.put(COLUMN_USER_ID, job.getUserID());
      values.put(COLUMN_USER_COMPANY, job.getCompany());
      values.put(COLUMN_USER_JOB_TITLE, job.getTitle());
      values.put(COLUMN_USER_JOB_COUNTRY, job.getCountry());
      values.put(COLUMN_USER_JOB_PROVINCE, job.getProvince());
      values.put(COLUMN_USER_REQUIRED_YEARS_OF_EMPLOTEMENT,job.getYearsOfExper());
      values.put(COLUMN_USER_JOB_PHONE, job.getPhone());
      values.put(COLUMN_USER_OFFER_SALLARY, job.getSalary());
      values.put(COLUMN_USER_JOB_DESCRIPTION, job.getDescription());

      db.insert(TABLE_USER_JOB, null, values);
      db.close();
   }

   public boolean checkUser(String email){
      String[] columns = {
              COLUMN_USER_ID
      };
      SQLiteDatabase db = this.getWritableDatabase();
      String selection = COLUMN_USER_EMAIL + " = ?";
      String[] selectionArgs = { email };

      Cursor cursor = db.query(TABLE_USER,
              columns,
              selection,
              selectionArgs,
              null,
              null,
              null);
      int cursorCount = cursor.getCount();
      cursor.close();
      db.close();

      if (cursorCount > 0){
         return true;
      }
      return false;
   }

   public boolean checkUser(String email, String password){
      String[] columns = {
              COLUMN_USER_ID
      };
      SQLiteDatabase db = this.getWritableDatabase();
      String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
      String[] selectionArgs = { email, password };

      Cursor cursor = db.query(TABLE_USER,
              columns,
              selection,
              selectionArgs,
              null,
              null,
              null);
      int cursorCount = cursor.getCount();
      cursor.close();
      db.close();

      if (cursorCount > 0){
         return true;
      }
      return false;
   }
}