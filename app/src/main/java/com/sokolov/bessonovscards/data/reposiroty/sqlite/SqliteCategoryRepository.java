package com.sokolov.bessonovscards.data.reposiroty.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.ICategory;

import java.util.ArrayList;
import java.util.List;

import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.COLUMN_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.COLUMN_ORDINAL;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.COLUMN_SCHEDULE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.TABLE_NAME;

public class SqliteCategoryRepository implements ICategoryRepository {

    private final SQLiteOpenHelper sqLiteOpenHelper;

    public SqliteCategoryRepository(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public ICategory getByName(String name) throws NotFoundException {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = sqLiteOpenHelper
                    .getReadableDatabase();
            cursor = db
                    .rawQuery(
                            "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + name + "'",
                            null);

            if (cursor.moveToFirst()) {
                return
                        new Category(
                                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                                cursor.getInt(cursor.getColumnIndex(COLUMN_ORDINAL)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULE)));
            } else {
                throw new NotFoundException();
            }
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
    public ICategory getByOrdinal(int ordinal) throws NotFoundException {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = sqLiteOpenHelper
                    .getReadableDatabase();
            cursor = db
                    .rawQuery(
                            "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ORDINAL + " = " + ordinal,
                            null);

            if (cursor.moveToFirst()) {
                return
                        new Category(
                                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                                cursor.getInt(cursor.getColumnIndex(COLUMN_ORDINAL)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULE)));
            } else {
                throw new NotFoundException();
            }
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
    public List<ICategory> getAll() {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = sqLiteOpenHelper
                    .getReadableDatabase();
            cursor = db
                    .rawQuery(
                            "SELECT * FROM " + TABLE_NAME,
                            null);

            List<ICategory> answer = new ArrayList<>();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    answer.add(
                            new Category(
                                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                                    cursor.getInt(cursor.getColumnIndex(COLUMN_ORDINAL)),
                                    cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULE))));
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
}
