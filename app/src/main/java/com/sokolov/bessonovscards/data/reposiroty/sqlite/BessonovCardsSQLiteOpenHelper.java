package com.sokolov.bessonovscards.data.reposiroty.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract;


public class BessonovCardsSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String BESSONOV_CARDS_DB = "BessonovCards.db";
    private static final int DATABASE_VERSION = 1;

    public BessonovCardsSQLiteOpenHelper(Context context) {
        super(context, BESSONOV_CARDS_DB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CardContract.SQL_CREATE_TABLE);
        db.execSQL(CategoryContract.SQL_CREATE_TABLE);
        db.execSQL(CategoryContract.SQL_POPULATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CategoryContract.SQL_DROP_TABLE);
        db.execSQL(CardContract.SQL_DROP_TABLE);
        onCreate(db);
    }
}
