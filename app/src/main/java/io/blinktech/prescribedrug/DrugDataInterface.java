package io.blinktech.prescribedrug;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by mayank on 7/12/15.
 */
public class DrugDataInterface {
    private SQLiteDatabase database;
    private DBOpenHelper dbHelper;
    public long result;

    public DrugDataInterface(Context context){
        dbHelper = new DBOpenHelper(context);
    }

    public void open() throws SQLException {

        database = dbHelper.getWritableDatabase();

    }

    public void openRead() throws SQLException {

        database = dbHelper.getReadableDatabase();

    }

    public void close() throws SQLException {
        dbHelper.close();
    }

    public long createDrug(Drug element) throws SQLException {

        this.open();

        ContentValues value = new ContentValues();
        //value.put(dbHelper.DRUG_ID, element.getId());
        value.put(dbHelper.DRUG_NAME, element.getName());

        long newRowId;
        newRowId = this.database.insert(dbHelper.TABLE_DRUG, null, value);

       // this.close();

        return newRowId;
    }

    public Drug getDrug(int id) throws SQLException{
        this.openRead();

        Drug element = new Drug();

        String selection = "_id=?";
        String[] selectionArgs = new String[] { String.valueOf(id)};
        Cursor c = database.query(DBOpenHelper.TABLE_DRUG,null,selection,selectionArgs,null,null,null);
        c.moveToFirst();
        element.setName(c.getString(c.getColumnIndexOrThrow(DBOpenHelper.DRUG_NAME)));
        element.setId(id);
        //this.close();

        return element;

    }

    public String[] getAllDrugs()
    {
        Cursor cursor = database.query(DBOpenHelper.TABLE_DRUG, new String[] {DBOpenHelper.DRUG_NAME}, null, null, null, null, null);

        if(cursor.getCount() >0)
        {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext())
            {
                str[i] = cursor.getString(cursor.getColumnIndex(DBOpenHelper.DRUG_NAME));
                i++;
            }
            return str;
        }
        else
        {
            return new String[] {};
        }
    }
}
