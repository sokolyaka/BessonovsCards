SELECT id, text, translate, categoryName, date
FROM card
INNER JOIN cardDate ON cardDate.cardUuid = card.id
WHERE categoryName = ?
