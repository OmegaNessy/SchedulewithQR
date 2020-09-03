package com.example.schedulewithqr;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schedulewithqr.database.DBHelperV2;

import java.io.IOException;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    private SQLiteDatabase mDb;
    private EditText mGroupNum;
    private EditText mTeacher;
    private EditText mPlace;
    private EditText mTime;
    private EditText mDay;
    private EditText mLesson;
    private EditText mWeekNum;
    private Button btnAdd, btnRemove;
    private DBHelperV2 mDBHelper;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_profile);
        mDBHelper = new DBHelperV2(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        mGroupNum = findViewById(R.id.etGroupNum);
        mTeacher = findViewById(R.id.etTeacher);
        mPlace = findViewById(R.id.etPlace);
        mTime = findViewById(R.id.etTime);
        mDay = findViewById(R.id.etDay);
        mLesson = findViewById(R.id.etLesson);
        mWeekNum = findViewById(R.id.etWeekNum);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);
        btnRemove = (Button) findViewById(R.id.buttonRemove);
        btnRemove.setOnClickListener(this);
        mTextView = findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String product = "";

                Cursor cursor = mDb.rawQuery("SELECT * FROM schedule", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(1) + " | ";
                    cursor.moveToNext();
                }
                cursor.close();

                mTextView.setText(product);

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    //@Override
    //public void onClick(View view) {
    //    String groupNum = mGroupNum.getText().toString();
    //    String teacher = mTeacher.getText().toString();
    //    String place = mPlace.getText().toString();
    //    String time = mTime.getText().toString();
    //    String day = mDay.getText().toString();
    //    String lesson = mLesson.getText().toString();
    //    String weekNum = mWeekNum.getText().toString();
//
//
    //    ContentValues contentValues = new ContentValues();
//
    //    switch (view.getId()){
    //        case R.id.buttonAdd:
    //            Log.d(LOG_TAG, "--- Insert in mytable: ---");
    //            contentValues.put(DBHelperV2.KEY_GROUP,groupNum);
    //            contentValues.put(DBHelperV2.KEY_TEACHER,teacher);
    //            contentValues.put(DBHelperV2.KEY_PLACE,place);
    //            contentValues.put(DBHelperV2.KEY_TIME,time);
    //            contentValues.put(DBHelperV2.KEY_DAY,day);
    //            contentValues.put(DBHelperV2.KEY_LESSON,lesson);
    //            //contentValues.put(dbHelper.KEY_WEEKNUM,weekNum);
//
    //            long rowID = mDb.insert("schedule", null, contentValues);
    //            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    //            break;
    //        case R.id.buttonRemove:
    //            Cursor cursor = mDb.query(DBHelperV2.TABLE_SCHEDULE,null,null,null,null,null,null);
//
    //            if (cursor.moveToFirst()){
    //                int idIndex = cursor.getColumnIndex(DBHelperV2.KEY_ID);
    //                int teacherIndex = cursor.getColumnIndex(DBHelperV2.KEY_TEACHER);
    //                do{
    //                    Log.d("mLog","ID = " + cursor.getInt(idIndex) +
    //                            ", name = " + cursor.getString(teacherIndex));
    //                } while (cursor.moveToNext());
    //            }else
    //                Log.d("mLog","0 rows");
    //            break;
//
    //    }
    //    mDBHelper.close();
//
    //}
}
