package com.sokolov.bessonovscards.view.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.data.reposiroty.inmemory.InMemoryScheduleRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.BessonovCardsSQLiteOpenHelper;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCardRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCategoryRepository;
import com.sokolov.bessonovscards.domain.cards.EditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.GetCardsByCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.MoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.MoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.view.category.view.ICategoryView;
import com.sokolov.bessonovscards.view.category.view.adapter.CategoryAdapter;
import com.sokolov.bessonovscards.view.category.view.interactor.CategoryInteractor;
import com.sokolov.bessonovscards.view.category.view.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements ICategoryView {

    private CategoryAdapter categoryAdapter;
    private CategoryPresenter categoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        BessonovCardsSQLiteOpenHelper openHelper = new BessonovCardsSQLiteOpenHelper(this);

        categoryPresenter =
                new CategoryPresenter(
                        this,
                        new CategoryInteractor(
                                new GetCardsByCategoryUseCase(
                                        new SqliteCardRepository(openHelper),
                                        getIntent().getStringExtra("EXTRA_CATEGORY_NAME")),
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
                                                openHelper))));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter =
                new CategoryAdapter(
                        new ArrayList<>(),
                        card -> categoryPresenter.selectedCard(card));

        recyclerView.setAdapter(categoryAdapter);

        findViewById(R.id.btn_preview_category).setOnClickListener(v -> categoryPresenter.onMoveCardToPreviewsCategory());
        findViewById(R.id.btn_next_category).setOnClickListener(v -> categoryPresenter.onMoveCardToNextCategory());

        categoryPresenter.onCreate();
    }

    @Override
    public void refreshData(List<ICard> cards) {
        categoryAdapter.setCards(cards);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showMoveCardSuccessMessage() {

    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void updateCard(ICard card) {

    }

    @Override
    public void showBottomBar() {
        findViewById(R.id.bottom_bar).setVisibility(View.VISIBLE);
    }
}
