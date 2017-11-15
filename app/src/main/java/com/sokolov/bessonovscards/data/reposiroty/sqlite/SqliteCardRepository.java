package com.sokolov.bessonovscards.data.reposiroty.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardDateContract;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_CATEGORY_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_ID;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_TEXT;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_TRANSLATE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardDateContract.Entity.COLUMN_DATE;

public class SqliteCardRepository implements ICardRepository {

    private final SQLiteOpenHelper sqLiteOpenHelper;

    public SqliteCardRepository(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public void save(ICard card) throws NotFoundException {
        ContentValues cardValues = new ContentValues();
        cardValues.put(COLUMN_ID, card.id());
        cardValues.put(COLUMN_TEXT, card.text());
        cardValues.put(COLUMN_TRANSLATE, card.translate());
        cardValues.put(COLUMN_CATEGORY_NAME, card.categoryName());

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.insertWithOnConflict(CardContract.Entity.TABLE_NAME, null, cardValues, SQLiteDatabase.CONFLICT_REPLACE);

        ContentValues cardDateValues = new ContentValues();
        cardDateValues.put(CardDateContract.Entity.COLUMN_CARD_UUID, card.id());
        cardDateValues.put(COLUMN_DATE, card.date().toString());
        db.insertWithOnConflict(CardDateContract.Entity.TABLE_NAME, null, cardDateValues, SQLiteDatabase.CONFLICT_REPLACE);

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
                            "SELECT "
                                    + COLUMN_ID + ", "
                                    + COLUMN_TEXT + ", "
                                    + COLUMN_TRANSLATE + ", "
                                    + COLUMN_CATEGORY_NAME + ", "
                                    + COLUMN_DATE +
                                    " FROM " + CardContract.Entity.TABLE_NAME +
                                    " INNER JOIN " + CardDateContract.Entity.TABLE_NAME +
                                    " ON " + CardDateContract.Entity.TABLE_NAME + "." + CardDateContract.Entity.COLUMN_CARD_UUID + " = " + CardContract.Entity.TABLE_NAME + "." + COLUMN_ID +
                                    " WHERE " + COLUMN_CATEGORY_NAME + " = " + "'" + categoryName + "'",
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
                                    LocalDate.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))));
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
