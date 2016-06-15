package com.example.sanjivi.mockform;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    String[] departments={"ARCHI","CHEM","CIVIL","CSE","ECE","EEE","ICE","MECH","META","PROD",};
    private CheckBox check1,check2,check3,check4;
    String name,roll;
    boolean tronix,webdev,appdev,algos,mentor;
    int dept;
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
        if(savedInstanceState!=null) {
            name =savedInstanceState.getString("name");
            roll=savedInstanceState.getString("roll");
            dept=savedInstanceState.getInt("dept");
            tronix=savedInstanceState.getBoolean("tronix");
            webdev=savedInstanceState.getBoolean("webdev");
            appdev=savedInstanceState.getBoolean("appdev");
            algos=savedInstanceState.getBoolean("algos");
            mentor=savedInstanceState.getBoolean("mentor");
            check1 = (CheckBox) findViewById(R.id.checkBox);
            check2 = (CheckBox) findViewById(R.id.checkBox2);
            check3 = (CheckBox) findViewById(R.id.checkBox3);
            check4 = (CheckBox) findViewById(R.id.checkBox4);
            EditText edit1 = (EditText) findViewById(R.id.editText);
            EditText edit2 = (EditText) findViewById(R.id.editText2);
            Switch toggleSwitch=(Switch)findViewById(R.id.switch1);
            edit1.setText(name);
            edit2.setText(roll);
            spinner.setSelection(dept);
            check1.setChecked(tronix);
            check2.setChecked(webdev);
            check3.setChecked(appdev);
            check4.setChecked(algos);
            toggleSwitch.setChecked(mentor);


        }
        addListenerOnButton();
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
      String item=arg0.getItemAtPosition(arg2).toString(); //on selecting a spinner item
        dept=arg2;

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
               Switch toggleSwitch=(Switch)findViewById(R.id.switch1);
               mentor=toggleSwitch.isChecked();
               name = edit1.getText().toString();
               roll = edit2.getText().toString();
               tronix=check1.isChecked();
               webdev=check2.isChecked();
               appdev=check3.isChecked();
               algos=check4.isChecked();

               if (name.matches(""))
                   Toast.makeText(MainActivity.this, "Fill in your name", Toast.LENGTH_LONG).show();
               else if (roll.matches(""))
                   Toast.makeText(MainActivity.this, "Fill in your roll no", Toast.LENGTH_LONG).show();
               else if (!tronix && !webdev && !appdev && !algos)
                   Toast.makeText(MainActivity.this, "Fill in your profile", Toast.LENGTH_LONG).show();
               else {

                   Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                   intent.putExtra("name1",name);

                   startActivity(intent);
               }
           }
       });

   }
 @Override
    protected void onSaveInstanceState(Bundle outState)
 {outState.putString("name",name);
     outState.putString("roll",roll);
     outState.putInt("dept",dept);
     outState.putBoolean("tronix",tronix);
     outState.putBoolean("webdev",webdev);
     outState.putBoolean("appdev",appdev);
     outState.putBoolean("algos",algos);
     outState.putBoolean("mentor",mentor);


 }
}
