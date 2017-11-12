package com.sokolov.bessonovscards.data.reposiroty.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_CATEGORY_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_ID;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_TEXT;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_TRANSLATE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.TABLE_NAME;

public class SqliteCardRepository implements ICardRepository {

    private final SQLiteOpenHelper sqLiteOpenHelper;

    public SqliteCardRepository(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public void save(ICard card) throws NotFoundException {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, card.id());
        values.put(COLUMN_TEXT, card.text());
        values.put(COLUMN_TRANSLATE, card.translate());
        values.put(COLUMN_CATEGORY_NAME, card.categoryName());

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    @Override
    public List<ICard> getAllByCategoryName(String categoryName) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = sqLiteOpenHelper
                    .getReadableDatabase();
            cursor = db
                    .rawQuery(
                            "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY_NAME + " = " + "'" + categoryName + "'",
                            null);

            List<ICard> answer = new ArrayList<>();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    answer.add(
                            new Card(
                                    cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                                    cursor.getString(cursor.getColumnIndex(COLUMN_TEXT)),
                                    cursor.getString(cursor.getColumnIndex(COLUMN_TRANSLATE)),
                                    cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)),
                                    LocalDate.now()));
                    cursor.moveToNext();
                }
            }
            return answer;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public void delete(String cardUuid) {

    }
}
