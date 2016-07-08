package com.example.sanjivi.contacts;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter{
    private static final String TAG="DBAdapter";  //used for logging database changes

    //field names
    public static final String KEY_ID="_id";
    public static final String KEY_ICON="icon";
    public static final String KEY_NAME="name";
    public static final String KEY_NUMBER="number";
    public static final String[] ALL_KEYS=new String[]{KEY_ID,KEY_ICON,KEY_NAME,KEY_NUMBER};

    //column numbers for each field name
    public static final int COL_ICON=0;
    public static final int COL_NAME=1;
    public static final int COL_NUMBER=2;

    //database info
    public static final String DATABASE_NAME="dbContacts";
    public static final String DATABASE_TABLE="mainContacts";
    public static final int DATABASE_VERSION=2;

    //SQL statement to create database
    private static final String DATABASE_CREATE_SQL="CREATE TABLE "+DATABASE_TABLE+"( "+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                                                      +KEY_ICON+" INTEGER,"
                                                                                      +KEY_NAME+" TEXT,"
                                                                                      +KEY_NUMBER+" TEXT "+");";
    private static final String INSERT1_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.rajni+",'Rajnikanth','958654325');";
    private static final String INSERT2_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.ajith+",'Ajith Kumar','9486425753');";
    private static final String INSERT3_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.clooney+",'George Clooney','9159962325');";
    private static final String INSERT4_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.selena+",'Selena Gomez','9875632415');";
    private static final String INSERT5_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.dicaprio+",'Leonardo diCaprio','9789321654');";
    private static final String INSERT6_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.ronaldo+",'Cristiano Ronaldo','9483927816');";
    private static final String INSERT7_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.federer+",'Roger Federer','9452985658');";
    private static final String INSERT8_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.abd+",'AB de Villiers','9014789632');";
    private static final String INSERT9_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.vijay+",'Vijay','9021520620');";
    private static final String INSERT10_SQL="INSERT INTO "+DATABASE_TABLE+" ("+KEY_ICON+","+KEY_NAME+","+KEY_NUMBER+") VALUES ("+R.mipmap.eminem+",'Eminem','9852963014');";
    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;
    public DBAdapter(Context ctx){
        this.context=ctx;
        myDBHelper=new DatabaseHelper(context);
    }
    public void setDatabaseCreateSql(){
        myDBHelper.onCreate(db);
    }
//open the database connection
    public DBAdapter open(){

        db=myDBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
    myDBHelper.close();
}
    //add a new set of values to be inserted
    public long insertRow(int icon,String name,String number){
        ContentValues initialValues=new ContentValues();
        initialValues.put(KEY_ICON,icon);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_NUMBER, number);

        //insert data into the database
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    // Return all data in the database.
    public Cursor getAllRows() {

        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(int id,int icon, String name, String number) {
        String where = KEY_ID + "=" + id;
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_ICON, icon);
        newValues.put(KEY_NAME, name);
        newValues.put(KEY_NUMBER,number);
        // Insert it into the database.
        return db.update(DATABASE_TABLE, newValues, where, null) != 0;
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
            _db.execSQL(INSERT1_SQL);
            _db.execSQL(INSERT2_SQL);
            _db.execSQL(INSERT3_SQL);
            _db.execSQL(INSERT4_SQL);
            _db.execSQL(INSERT5_SQL);
            _db.execSQL(INSERT6_SQL);
            _db.execSQL(INSERT7_SQL);
            _db.execSQL(INSERT8_SQL);
            _db.execSQL(INSERT9_SQL);
            _db.execSQL(INSERT10_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }

}









