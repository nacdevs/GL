package com.nestor.globalLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.nestor.apimanager.Clases.Item;
import com.nestor.globalLog.Model.PresenterListener;

import java.util.ArrayList;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPresenter presenter = new MainPresenter(MainActivity.this);
        presenter.getList(listener);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL));

    }

    PresenterListener<ArrayList<Item>> listener = new PresenterListener<ArrayList<Item>> (){
        @Override
        public void dataReady(ArrayList<Item> data) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            listAdapter = new ListAdapter(MainActivity.this,data);
            recyclerView.setAdapter(listAdapter);
        }
    };
}