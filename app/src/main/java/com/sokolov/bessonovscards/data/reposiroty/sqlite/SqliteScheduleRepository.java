package com.sokolov.bessonovscards.data.reposiroty.sqlite;

import android.database.sqlite.SQLiteOpenHelper;

import com.sokolov.bessonovscards.data.reposiroty.IScheduleRepository;
import com.sokolov.bessonovscards.entity.ISchedule;

public class SqliteScheduleRepository implements IScheduleRepository {
    private final SQLiteOpenHelper openHelper;

    public SqliteScheduleRepository(SQLiteOpenHelper openHelper) {
        this.openHelper = openHelper;
    }

    @Override
    public ISchedule getByUuid(String uuid) {
        return null;
    }
}
