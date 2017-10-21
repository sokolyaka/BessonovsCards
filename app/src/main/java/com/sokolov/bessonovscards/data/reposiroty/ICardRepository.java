package com.sokolov.bessonovscards.data.reposiroty;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.entity.ICard;

public interface ICardRepository {
    void save(ICard card) throws NotFoundException;
}
