package com.sokolov.bessonovscards.view.cards;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.BessonovCardsSQLiteOpenHelper;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCardRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCategoryRepository;
import com.sokolov.bessonovscards.domain.cards.EditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.GetShuffleCardsByCategory;
import com.sokolov.bessonovscards.domain.cards.MoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.MoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.PronounceTextUseCase;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.view.cards.adapter.CardsPagerAdapter;
import com.sokolov.bessonovscards.view.cards.adapter.OnCardEditListener;
import com.sokolov.bessonovscards.view.cards.adapter.OnCategoryChangeListener;
import com.sokolov.bessonovscards.view.cards.interactor.CardsInteractor;
import com.sokolov.bessonovscards.view.cards.presenter.CardsPresenter;
import com.sokolov.bessonovscards.view.cards.presenter.ICardsPresenter;
import com.sokolov.bessonovscards.view.cards.view.ICardsView;

import java.util.List;

public class CardsActivity extends AppCompatActivity implements ICardsView, OnCategoryChangeListener, OnCardEditListener {

    private ICardsPresenter cardsPresenter;
    private CardsPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        BessonovCardsSQLiteOpenHelper openHelper = new BessonovCardsSQLiteOpenHelper(this);
        String categoryName = getIntent().getStringExtra("EXTRA_CATEGORY_NAME");

        cardsPresenter =
                new CardsPresenter(
                        this,
                        new CardsInteractor(
                                new GetShuffleCardsByCategory(
                                        new SqliteCardRepository(
                                                openHelper),
                                        categoryName),
                                new MoveCardToNextCategoryUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        new SqliteCategoryRepository(
                                                openHelper)),
                                new MoveCardToPreviewsCategoryUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        new SqliteCategoryRepository(
                                                openHelper)),
                                new EditCardUseCase(
                                        new SqliteCardRepository(
                                                openHelper)),
                                new PronounceTextUseCase(null)));
        cardsPresenter.onCreate();
    }

    @Override
    public void onNext(ICard card) {
        cardsPresenter.onMoveCardToNextCategory(card);
    }

    @Override
    public void onPreviews(ICard card) {
        cardsPresenter.onMoveCardToPreviewsCategory(card);
    }

    @Override
    public void setData(List<ICard> cards) {
        adapter =
                new CardsPagerAdapter(
                        cards,
                        getSupportFragmentManager());

        ((ViewPager) findViewById(R.id.container)).setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMoveCardSuccessMessage() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void updateCard(ICard card) {
        adapter.updateCard(card);
    }

    @Override
    public void onCardEdit(ICard editedCard) {
        cardsPresenter.onEditCard(editedCard);
    }
}
