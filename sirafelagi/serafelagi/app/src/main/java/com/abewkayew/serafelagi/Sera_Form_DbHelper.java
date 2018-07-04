package com.abewkayew.serafelagi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.crypto.NullCipher;

import static android.os.Build.ID;

/**
 *
 */

public class Sera_Form_DbHelper extends SQLiteOpenHelper{

//    declare some final variables...
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + ConstantValues.TABLE_NAME
            + "(id integer primary key autoincrement," + ConstantValues.UNIVERSITY_NAME + " text,"
            +ConstantValues.TEMPO_FILE +" text,"+
            ConstantValues.GPA +" text ,"+
            ConstantValues.SYNC_STATUS + " integer"+ ")";
    private static final String DROP_TABLE = "drop table if exists " + ConstantValues.TABLE_NAME;

    //    constructor definition...
    public Sera_Form_DbHelper(Context context)
    {
        super(context, ConstantValues.DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
//    custom methods for implementing saving informations to the local database...
    public void saveApplyFormToLocal(String university,String tempo_file,String gpa, int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
//      insert the data into the sqlite....
        contentValues.put(ConstantValues.UNIVERSITY_NAME, university);
        contentValues.put(ConstantValues.GPA,gpa);
        contentValues.put(ConstantValues.TEMPO_FILE,tempo_file);
        contentValues.put(ConstantValues.SYNC_STATUS, sync_status);
//
        database.insert(ConstantValues.TABLE_NAME, null, contentValues);

    }
//    read database from the local sqlite database...
    public Cursor readApplyDataFromLocal(SQLiteDatabase database)
        {
            String[] projection = {ConstantValues.UNIVERSITY_NAME,
                    ConstantValues.GPA,
                    ConstantValues.TEMPO_FILE,
                    ConstantValues.SYNC_STATUS};
            return (database.query(ConstantValues.TABLE_NAME, projection, null, null,
                    null, null, null));

        }
//    update the data...
    public void updateData(String name, int sync_status, SQLiteDatabase databse)
        {
    //        Update your database...
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantValues.SYNC_STATUS, sync_status);
            String selection = ConstantValues.UNIVERSITY_NAME + " LIKE ?";
            String[] selection_args = {name};
            databse.update(ConstantValues.TABLE_NAME, contentValues, selection, selection_args);

        }

}
