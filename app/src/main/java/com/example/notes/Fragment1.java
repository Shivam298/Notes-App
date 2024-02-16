package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;



public class Fragment1 extends Fragment {

ArrayList<String> list=new ArrayList<String>();
static int a=40;
Bundle bundle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        Log.d("mess","onCreate"+bundle.getString("text"));
        list.add(bundle.getString("text"));
        Log.d("a","value of a"+a);
        a++;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  Log.d("printing","inside fragment");

        Log.d("mess","onCreateView");
        String message = bundle.getString("text");
        Log.d("printing","inside fragment "+list);
        View view = inflater.inflate(
                R.layout.fragment_1, container, false);
        TextView myTextView;
        myTextView = (TextView) view.findViewById(R.id.res);
        EditText ed=(EditText) view.findViewById(R.id.edittext);
        CardView card=view.findViewById(R.id.card);
      //  Log.d("mess","messGE"+myTextView.getText());
        myTextView.setText(message);
        card.setY(a);
        a=a+250;
        Log.d("mess","messGE"+myTextView.getText());

        Button btn_edit=view.findViewById(R.id.edit);
        Button btn= view.findViewById(R.id.delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("tag","Inside editing "+myTextView.getText());
                String str=myTextView.getText().toString();
              //  myTextView.setText(target.getText().toString());
                String[] args=new String[1];
                args[0]=str;
                getActivity().getApplicationContext().getContentResolver()
                        .delete(MyContentProvider.CONTENT_URI, MyContentProvider.name+"=?",args);
                MainActivity obj =new MainActivity();
                myTextView.setText("Deleted");

            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // EditText ed=(EditText) v.findViewById(R.id.edittext);
                Log.d("tag","meg "+ed.getVisibility());
                if(ed.getVisibility()==0){
                   String st=ed.getText().toString();
                   ed.setVisibility(View.INVISIBLE);
                    ContentValues updateValues = new ContentValues();
                    updateValues.put(MyContentProvider.name,st);
                    String str=myTextView.getText().toString();
                    String[] args=new String[1];
                    args[0]=str;
                   getActivity().getApplicationContext().getContentResolver().update(MyContentProvider.CONTENT_URI,updateValues,MyContentProvider.name+"=?",args);
                   myTextView.setText(st);
                }
                else{
                    ed.setVisibility(View.VISIBLE);
                }
            }
        });



        return view;
    }

}