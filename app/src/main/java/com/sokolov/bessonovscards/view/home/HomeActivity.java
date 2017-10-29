package com.sokolov.bessonovscards.view.home;

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
import com.sokolov.bessonovscards.domain.home.AddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.GetAllCategoriesUseCase;
import com.sokolov.bessonovscards.view.home.adapter.CategoryAdapter;
import com.sokolov.bessonovscards.view.home.interactor.HomeInteractor;
import com.sokolov.bessonovscards.view.home.mapper.CategoryMapper;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;
import com.sokolov.bessonovscards.view.home.presenter.HomePresenter;
import com.sokolov.bessonovscards.view.home.view.IHomeView;
import com.sokolov.bessonovscards.view.home.widget.NewCardDialog;

import java.util.ArrayList;
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
                                                openHelper)),
                                new GetAllCategoriesUseCase(
                                        new SqliteCategoryRepository(
                                                openHelper))),
                        new CategoryMapper(
                                new SqliteCardRepository(
                                        openHelper)));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(new ArrayList<>());
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
