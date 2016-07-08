package com.example.sanjivi.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DBAdapter myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);


            openDB();

     populateListView();
        registerClickCallback();

    }
    @Override
    protected void onResume(){
        super.onResume();
        populateListView();
    }
    private void openDB(){
        myDb=new DBAdapter(this);
        myDb.open();
    }
    private void populateListView(){
        Cursor cursor=myDb.getAllRows();
        String[] fromFieldNames=new String[]{DBAdapter.KEY_ICON,DBAdapter.KEY_NAME,DBAdapter.KEY_NUMBER};
        int[] toViewIds=new  int[]{R.id.imageView,R.id.nameText,R.id.numberText};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter=new SimpleCursorAdapter(getBaseContext(),R.layout.custom_row,cursor,fromFieldNames,toViewIds,0);
        ListView myList=(ListView)findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);
    }
    private void registerClickCallback(){
        final ListView myList=(ListView)findViewById(R.id.listView);

        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      int flag=1;
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        ImageView icon=(ImageView)view.findViewById(R.id.imageView);
                        TextView name=(TextView)view.findViewById(R.id.nameText);
                        TextView number=(TextView)view.findViewById(R.id.numberText);
                        int iconId;
                        switch (position){
                            case 0: {
                                iconId = R.mipmap.rajni;
                                break;
                            }
                            case 1: {
                                iconId = R.mipmap.ajith;
                                break;
                            }
                            case 2: {
                                iconId = R.mipmap.clooney;
                                break;
                            }
                            case 3: {
                                iconId = R.mipmap.selena;
                                break;
                            }
                            case 4: {
                                iconId = R.mipmap.dicaprio;
                                break;
                            }
                            case 5: {
                                iconId = R.mipmap.ronaldo;
                                break;
                            }
                            case 6: {
                                iconId = R.mipmap.federer;
                                break;
                            }
                            case 7: {
                                iconId = R.mipmap.abd;
                                break;
                            }
                            case 8: {
                                iconId = R.mipmap.vijay;
                                break;
                            }
                            case 9: {
                                iconId = R.mipmap.eminem;
                                break;
                            }

                            default:iconId=R.mipmap.standard;
                        }
                        String nameId=name.getText().toString();
                        String numberId=number.getText().toString();
                        intent.putExtra("id1",position+1);
                        intent.putExtra("icon1",iconId);
                        intent.putExtra("name1",nameId);
                        intent.putExtra("number1",numberId);
                        intent.putExtra("flag1",flag);
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClick(View v){
        int flag=0;
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
         intent.putExtra("flag1",flag);
        startActivity(intent);
    }
}
