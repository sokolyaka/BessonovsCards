package com.sokolov.bessonovscards.data.reposiroty.sqlite.contract;

import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.COLUMN_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.COLUMN_ORDINAL;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.COLUMN_SCHEDULE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CategoryContract.Entity.TABLE_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.COMMA_SEP;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.INTEGER_TYPE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.PRIMARY_KEY;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.TEXT_TYPE;

public class CategoryContract {

    public static class Entity {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCHEDULE = "schedule";
        public static final String COLUMN_ORDINAL = "ordinal";

        private Entity() {
        }
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME + " " + TEXT_TYPE + " " + PRIMARY_KEY + "," +
                    COLUMN_SCHEDULE + " "+ TEXT_TYPE + COMMA_SEP +
                    COLUMN_ORDINAL + " "+ INTEGER_TYPE + ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SQL_POPULATE_TABLE =
            "INSERT or replace INTO " + TABLE_NAME + "(" +
                    COLUMN_NAME + COMMA_SEP +
                    COLUMN_SCHEDULE + COMMA_SEP +
                    COLUMN_ORDINAL + ")" +
                    " VALUES " +
                    "('not set','EMPTY','0')," +
                    "('today','TODAY','1')," +
                    "('tomorrow','TOMORROW','2')," +
                    "('week','ONCE_PER_WEEK','3')," +
                    "('month','ONCE_PER_MONTH','4')," +
                    "('learned','EMPTY','5')";


    private CategoryContract() {
    }
}
