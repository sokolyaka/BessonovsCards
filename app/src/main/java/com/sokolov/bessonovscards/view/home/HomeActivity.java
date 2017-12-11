package com.sokolov.bessonovscards.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.BessonovCardsSQLiteOpenHelper;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCardRepository;
import com.sokolov.bessonovscards.data.reposiroty.sqlite.SqliteCategoryRepository;
import com.sokolov.bessonovscards.domain.cards.GetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.home.AddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.GetAllCategoriesUseCase;
import com.sokolov.bessonovscards.view.category.CategoryActivity;
import com.sokolov.bessonovscards.view.home.adapter.CategoryAdapter;
import com.sokolov.bessonovscards.view.home.interactor.HomeInteractor;
import com.sokolov.bessonovscards.view.home.mapper.CategoryMapper;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;
import com.sokolov.bessonovscards.view.home.presenter.HomePresenter;
import com.sokolov.bessonovscards.view.home.view.IHomeView;
import com.sokolov.bessonovscards.view.home.widget.NewCardDialog;
import com.sokolov.bessonovscards.view.home.widget.SelectTextModeDialog;
import com.sokolov.bessonovscards.view.todayCards.TodayCardsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private HomePresenter homePresenter;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BessonovCardsSQLiteOpenHelper openHelper = new BessonovCardsSQLiteOpenHelper(this);
        homePresenter =
                new HomePresenter(
                        this,
                        new HomeInteractor(
                                new AddNewCardUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        "not set"),
                                new GetAllCategoriesUseCase(
                                        new SqliteCategoryRepository(
                                                openHelper)),
                                new GetCardsForTodayUseCase(
                                        new SqliteCardRepository(
                                                openHelper),
                                        new HashSet<>(
                                                Arrays.asList(
                                                        "not set",
                                                        "learned")))),
                        new CategoryMapper(
                                new SqliteCardRepository(
                                        openHelper)));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter =
                new CategoryAdapter(
                        new ArrayList<>(),
                        categoryName -> {
                            if (categoryName.equals("Today")) {
                                new SelectTextModeDialog()
                                        .show(
                                                getSupportFragmentManager(),
                                                "tag",
                                                textMode -> {
                                                    Intent intent = new Intent(getBaseContext(), TodayCardsActivity.class);
                                                    intent.putExtra("EXTRA_TEXT_MODE", textMode);
                                                    startActivity(intent);
                                                });

                            } else {
                                Intent intent = new Intent(getBaseContext(), CategoryActivity.class);
                                intent.putExtra("EXTRA_CATEGORY_NAME", categoryName);
                                startActivity(intent);
                            }
                        });

        recyclerView.setAdapter(categoryAdapter);
        findViewById(R.id.fab)
                .setOnClickListener(
                        v -> new NewCardDialog()
                                .show(
                                        getSupportFragmentManager(),
                                        "tag",
                                        (text, translate) ->
                                                homePresenter
                                                        .onAddNewCard(
                                                                text.toString(),
                                                                translate.toString())));
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.onResume();
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void hideSpinner() {

    }

    @Override
    public void setCategories(List<ICategoryDisplayModel> categories) {
        categoryAdapter.setCategories(categories);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
    }
}
