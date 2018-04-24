package com.pwnz.www.mobileapplicaiton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pwnz.www.mobileapplicaiton.controller.MoviesAdapter;
import com.pwnz.www.mobileapplicaiton.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private List<Movie> mMovieList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mRecyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(mMovieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        for (int i = 1; i <= 20; i++) {
            mMovieList.add(new Movie("This is movie number ", String.valueOf(i), "2018"));
        }

        mAdapter.notifyDataSetChanged();
    }
}
