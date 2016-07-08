package com.example.sanjivi.contacts;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText nameText,numberText;
    int flag,id,icon;
    String name,number;
    DBAdapter myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nameText=(EditText)findViewById(R.id.editText);
        numberText=(EditText)findViewById(R.id.editText2);
        Bundle extras=getIntent().getExtras();
        flag=extras.getInt("flag1");
           if(flag==1){
               id=extras.getInt("id1");
               icon=extras.getInt("icon1");
               name=extras.getString("name1");
               number=extras.getString("number1");
               ImageView imageView=(ImageView)findViewById(R.id.imageView2);

               imageView.setImageResource(icon);
               nameText.setText(name);
               numberText.setText(number);
        }
    }
    public void onClickButton(View v){
        if(!TextUtils.isEmpty(nameText.getText().toString())&& !TextUtils.isEmpty(numberText.getText().toString())){
            myDb=new DBAdapter(this);
            myDb.open();
           if(flag==0){
               myDb.insertRow(R.mipmap.standard,nameText.getText().toString(),numberText.getText().toString());
           }
            else if(flag==1){
               myDb.updateRow(id,icon,nameText.getText().toString(),numberText.getText().toString());
           }
            myDb.close();
            finish();
        }
        else{
            Toast.makeText(Main2Activity.this, "Fill in the details", Toast.LENGTH_LONG).show();
        }
    }

}
