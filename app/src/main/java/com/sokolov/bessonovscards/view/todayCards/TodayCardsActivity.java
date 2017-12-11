package com.sokolov.bessonovscards.view.todayCards;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.data.reposiroty.inmemory.InMemoryScheduleRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.BessonovCardsSQLiteOpenHelper;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCardRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCategoryRepository;
import com.sokolov.bessonovscards.domain.cards.EditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.GetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.cards.MoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.MoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.PronounceTextUseCase;
import com.sokolov.bessonovscards.entity.AndoidTextToSpeech;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.entity.ITextMode;
import com.sokolov.bessonovscards.entity.TextMode;
import com.sokolov.bessonovscards.view.todayCards.adapter.CardsPagerAdapter;
import com.sokolov.bessonovscards.view.todayCards.adapter.OnCardEditListener;
import com.sokolov.bessonovscards.view.todayCards.adapter.OnCategoryChangeListener;
import com.sokolov.bessonovscards.view.todayCards.adapter.OnTextPronounceListener;
import com.sokolov.bessonovscards.view.todayCards.interactor.TodayCardsInteractor;
import com.sokolov.bessonovscards.view.todayCards.presenter.ITodayCardsPresenter;
import com.sokolov.bessonovscards.view.todayCards.presenter.TodayCardsPresenter;
import com.sokolov.bessonovscards.view.todayCards.view.ICardsView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TodayCardsActivity extends AppCompatActivity implements ICardsView, OnCategoryChangeListener, OnCardEditListener, OnTextPronounceListener {

    private ITodayCardsPresenter cardsPresenter;
    private CardsPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        BessonovCardsSQLiteOpenHelper openHelper = new BessonovCardsSQLiteOpenHelper(this);

        cardsPresenter =
                new TodayCardsPresenter(
                        this,
                        new TodayCardsInteractor(
                                new GetCardsForTodayUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        new HashSet<>(
                                                Arrays.asList(
                                                        "not set",
                                                        "learned"))),
                                new MoveCardToNextCategoryUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        new SqliteCategoryRepository(
                                                openHelper),
                                        new InMemoryScheduleRepository()),
                                new MoveCardToPreviewsCategoryUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        new SqliteCategoryRepository(
                                                openHelper),
                                        new InMemoryScheduleRepository()),
                                new EditCardUseCase(
                                        new SqliteCardRepository(
                                                openHelper)),
                                new PronounceTextUseCase(
                                        new AndoidTextToSpeech(
                                                new TextToSpeech(
                                                        this,
                                                        status -> {
                                                        })))),
                        new TextMode(
                                getIntent()
                                        .getStringExtra(
                                                "EXTRA_TEXT_MODE")));
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
    public void refreshData(List<ICard> cards, ITextMode textMode) {
        adapter =
                new CardsPagerAdapter(
                        cards,
                        getSupportFragmentManager(),
                        textMode);

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

    @Override
    public void onTextPronounce(String text) {
        cardsPresenter.onPronounce(text);
    }
}
