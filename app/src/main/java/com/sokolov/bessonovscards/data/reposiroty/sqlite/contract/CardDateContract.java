package com.sokolov.bessonovscards.data.reposiroty.sqlite.contract;

import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardDateContract.Entity.COLUMN_CARD_UUID;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardDateContract.Entity.COLUMN_DATE;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.CardDateContract.Entity.TABLE_NAME;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.PRIMARY_KEY;
import static com.sokolov.bessonovscards.data.reposiroty.sqlite.contract.Constants.TEXT_TYPE;

public class CardDateContract {

    public static class Entity {
        public static final String TABLE_NAME = "cardDate";
        public static final String COLUMN_CARD_UUID = "cardUuid";
        public static final String COLUMN_DATE = "date";

        private Entity() {
        }
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_CARD_UUID + " " + TEXT_TYPE + " " + PRIMARY_KEY + "," +
                    COLUMN_DATE + " " + TEXT_TYPE + ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private CardDateContract() {
    }
}
