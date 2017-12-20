package com.sokolov.bessonovscards.di;

import android.database.sqlite.SQLiteOpenHelper;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.BessonovCardsSQLiteOpenHelper;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCardRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCategoryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BindRepositoryModule {

    @Binds
    public abstract ICardRepository bindCardRepository(SqliteCardRepository cardRepository);

    @Binds
    public abstract ICategoryRepository bindCategoryRepository(SqliteCategoryRepository categoryRepository);

    @Binds
    public abstract SQLiteOpenHelper bindSQLiteOpenHelper(BessonovCardsSQLiteOpenHelper sqLiteOpenHelper);
}
