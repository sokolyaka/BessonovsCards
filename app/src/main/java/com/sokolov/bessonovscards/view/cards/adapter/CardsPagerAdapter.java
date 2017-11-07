package com.sokolov.bessonovscards.view.cards.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public class CardsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<ICard> cards;

    public CardsPagerAdapter(List<ICard> cards, FragmentManager fm) {
        super(fm);
        this.cards = cards;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(cards.get(position));
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cards.get(position).categoryName();
    }

    public void updateCard(ICard newCard) {
        cards.replaceAll(
                oldCard ->
                        oldCard.id().equals(newCard.id())
                                ? newCard : oldCard);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
