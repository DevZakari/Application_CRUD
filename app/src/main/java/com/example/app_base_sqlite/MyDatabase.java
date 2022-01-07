package com.example.app_base_sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "BaseName.db";
    public static  final int DATABASE_VERSION = 1;
    public static final  String TABLE_NAME = "m_person";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "last_name";
    public static final String COLUMN_PRENOM = "first_name";
    private SQLiteDatabase db = this.getWritableDatabase();

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PRENOM + " TEXT, " +
                        COLUMN_NOM + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // will be run only if we change the database version ;
        // drop the table :
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        // whenever we drop the table we need to create it ;
        onCreate(db);

    }
    void addPerson(String first_name,String last_name){
        // we need to create a Content Value for holding the data;
        ContentValues cv = new ContentValues();
        // then we put the data :
        cv.put(COLUMN_NOM,last_name);
        cv.put(COLUMN_PRENOM,first_name);
        long result =db.insert(TABLE_NAME,null,cv);
        if(result == -1 )
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully !", Toast.LENGTH_SHORT).show();
        }
    }
    // Second Method :
    Cursor readAllData() {
        String query = "SELECT * FROM "+ TABLE_NAME;
        db = this.getReadableDatabase();
        Cursor c = null;
        if(db != null)
        {
            c = db.rawQuery(query,null);
        }
        return c;
    }
    void updateData(String row_id,String nom,String prenom){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM,nom);
        cv.put(COLUMN_PRENOM,prenom);
        long result= db.update(TABLE_NAME,cv,COLUMN_ID+"=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Update", Toast.LENGTH_SHORT).show();
        }
    }

    // First Method :
    public  String GetData(){
        String [] columns = new String [] {COLUMN_ID,COLUMN_NOM,COLUMN_PRENOM};
        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,null,null);

        int iRowID = c.getColumnIndex(COLUMN_ID);
        int iName = c.getColumnIndex(COLUMN_NOM);
        int iPrenom = c.getColumnIndex(COLUMN_PRENOM);

        String Result = "";

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            Result += c.getString(iRowID) + " :  " + c.getString(iName) + " " + c.getString(iPrenom) + "\n";
        }
        c.close();
        return Result;
    }

    public long DeleteEntry(String row_id){
        return db.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{row_id});
    }

    public long UpdateEntry(String row_id,String first_name,String last_name){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRENOM,first_name);
        cv.put(COLUMN_NOM,last_name);
        return db.update(TABLE_NAME,cv,COLUMN_ID+"=?",new String[]{row_id});
    }
}