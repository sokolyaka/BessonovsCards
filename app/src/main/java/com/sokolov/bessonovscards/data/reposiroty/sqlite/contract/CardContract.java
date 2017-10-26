package com.sokolov.bessonovscards.data.reposiroty.sqlite.contract;

import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_CATEGORY_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_ID;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_TEXT;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.COLUMN_TRANSLATE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardContract.Entity.TABLE_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.COMMA_SEP;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.INTEGER_TYPE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.PRIMARY_KEY;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.TEXT_TYPE;

public class CardContract {

    public static class Entity {
        public static final String TABLE_NAME = "card";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_TRANSLATE = "translate";
        public static final String COLUMN_CATEGORY_NAME = "categoryName";

        private Entity() {
        }
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " " + INTEGER_TYPE + " " + PRIMARY_KEY + "," +
                    COLUMN_TEXT + TEXT_TYPE + COMMA_SEP +
                    COLUMN_TRANSLATE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_CATEGORY_NAME + ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private CardContract() {
    }

}
