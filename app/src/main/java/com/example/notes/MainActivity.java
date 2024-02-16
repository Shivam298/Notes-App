package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<String>();
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void load(View view){
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                String newText;
                newText=cursor.getString(cursor.getColumnIndex(MyContentProvider.name));
                addFragment(new Fragment1(),newText);
                strBuild.append("\n").
                        append(cursor.getString(cursor.getColumnIndex(MyContentProvider.name)));
                Log.d("print","message "+strBuild);
                cursor.moveToNext();
            }
         //   resultView.setText(strBuild);
        }
        else {
         //   resultView.setText("No Records Found");
        }

    }
    public void add(View v){
        Log.d("press","inside add method");
        text=findViewById(R.id.notes);

        ContentValues values = new ContentValues();
        // fetching text from user
        values.put(MyContentProvider.name,text.getText().toString());
        // inserting into database through content URI
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

      //  list.add(text.getText().toString());
     //   Log.d("printing","list "+list);
        replacefragment(new Fragment1());
    }
    public void replacefragment(Fragment1 frag){
            //Log.d("print","fragment"+frag.);
            Bundle mbundle=new Bundle();
            mbundle.putString("text",text.getText().toString());
            FragmentManager fragmag = getSupportFragmentManager();
            FragmentTransaction fragtran = fragmag.beginTransaction();
            frag.setArguments(mbundle);
            fragtran.add(R.id.framelayout, frag);
            fragtran.commit();
    }
    public void addFragment(Fragment1 frag,String newText){
        Bundle mbundle=new Bundle();
        mbundle.putString("text",newText);
        FragmentManager fragmag = getSupportFragmentManager();
        FragmentTransaction fragtran = fragmag.beginTransaction();
        frag.setArguments(mbundle);
        fragtran.add(R.id.framelayout, frag);
        fragtran.commit();

    }
   /* public void edit(View v){
        Log.d("tag","INSIDE EDIT");
        TextView text=findViewById(R.id.notes);
        TextView target=findViewById(R.id.res);
        Log.d("tag","texts "+v.findViewById(R.id.res));
    }*/
}