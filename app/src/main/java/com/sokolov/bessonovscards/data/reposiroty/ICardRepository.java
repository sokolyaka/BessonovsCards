package com.sokolov.bessonovscards.data.reposiroty;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public interface ICardRepository {
    void save(ICard card) throws NotFoundException;

    List<ICard> getAllByCategoryName(String categoryName);

    void delete(String cardUuid);
}
