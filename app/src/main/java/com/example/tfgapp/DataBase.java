package com.example.tfgapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users (usuario text, password text, correo text)";
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void registro (String usuario, String password, String correo) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("usuario", usuario);
        contentValues.put("password", password);
        contentValues.put("correo", correo);

        SQLiteDatabase database = getWritableDatabase();
        database.insert("users", null, contentValues);
        database.close();
    }

    public int login (String usuario, String password) {
        int result = 0;

        String string[] = new String[2];
        string[0] = usuario;
        string[1] = password;

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from users where usuario=? and password=?", string);

        if (cursor.moveToFirst()) {
            result = 1;
        }
        return result;
    }
}
