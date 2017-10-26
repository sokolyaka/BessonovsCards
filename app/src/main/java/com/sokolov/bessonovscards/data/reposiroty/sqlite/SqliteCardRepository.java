package com.sokolov.bessonovscards.data.reposiroty.sqlite;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public class SqliteCardRepository implements ICardRepository {

    @Override
    public void save(ICard card) throws NotFoundException {

    }

    @Override
    public List<ICard> getAllByCategoryName(String categoryName) {
        return null;
    }
}
