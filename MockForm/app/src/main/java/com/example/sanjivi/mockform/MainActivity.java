package com.example.sanjivi.mockform;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    Spinner spinner;
    String[] departments={"ARCHI","CHEM","CIVIL","CSE","ECE","EEE","ICE","MECH","META","PROD",};
    private CheckBox check1,check2,check3,check4;
    String name;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Adapters are the link between a set of data and the AdapterView that displays the data.
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,departments); //creating adapter for spinner
        Spinner spinner=(Spinner)findViewById(R.id.spinner); //spinner element
        spinner.setAdapter(adapter); //attaching adapter to spinner
        spinner.setOnItemSelectedListener(this); //spinner click listener
        spinner.setSelection(3);
        addListenerOnButton();
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
     String item=arg0.getItemAtPosition(arg2).toString(); //on selecting a spinner item

    }
    public void onNothingSelected(AdapterView<?> arg0)
    {// TODO Auto-generated method stub
         }
   public void addListenerOnButton() {

       btn = (Button) findViewById(R.id.button);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               check1 = (CheckBox) findViewById(R.id.checkBox);
               check2 = (CheckBox) findViewById(R.id.checkBox2);
               check3 = (CheckBox) findViewById(R.id.checkBox3);
               check4 = (CheckBox) findViewById(R.id.checkBox4);
               EditText edit1 = (EditText) findViewById(R.id.editText);
               EditText edit2 = (EditText) findViewById(R.id.editText2);
               name = edit1.getText().toString();
               String roll = edit2.getText().toString();
               if (name.matches(""))
                   Toast.makeText(MainActivity.this, "Fill in your name", Toast.LENGTH_LONG).show();
               else if (roll.matches(""))
                   Toast.makeText(MainActivity.this, "Fill in your roll no", Toast.LENGTH_LONG).show();
               else if (!check1.isChecked() && !check2.isChecked() && !check3.isChecked() && !check4.isChecked())
                   Toast.makeText(MainActivity.this, "Fill in your profile", Toast.LENGTH_LONG).show();
               else {

                   Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                   intent.putExtra("name1",name);

                   startActivity(intent);
               }
           }
       });

   }

}
