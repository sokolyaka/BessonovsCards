package com.sokolov.bessonovscards.view.cards.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CardsPagerAdapter extends FragmentPagerAdapter {

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
}
