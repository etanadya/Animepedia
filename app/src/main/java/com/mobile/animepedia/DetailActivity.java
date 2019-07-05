package com.mobile.animepedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.animepedia.Adapter.HomeAdapter;
import com.mobile.animepedia.Adapter.ListAnimeAdapter;
import com.mobile.animepedia.Api.AnimepediaApi;
import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.Model.ListAnimeItem;
import com.mobile.animepedia.OnclickLibrary.ItemClickSupport;
import com.mobile.animepedia.Presenter.HomePresenter;
import com.mobile.animepedia.Presenter.ListAnimePresenter;
import com.mobile.animepedia.View.MainView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements MainView {
    TextView tvJudul;
    RecyclerView rvListDetail;

    ListAnimeAdapter listAnimeAdapter;
    ListAnimePresenter listAnimePresenter;
    AnimepediaApi animepediaApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvJudul = findViewById(R.id.tv_judul_detail);
        rvListDetail = findViewById(R.id.rv_listdetail);

        AnimepediaItem animepediaItem = getIntent().getParcelableExtra("animepedia");

        tvJudul.setText(animepediaItem.getJudul());

        TampilDetailList();
        listAnimePresenter.LoadItemDetail("boruto");
    }

    private void TampilDetailList() {
        listAnimeAdapter = new ListAnimeAdapter(this);
        animepediaApi = new AnimepediaApi();
        rvListDetail.setLayoutManager(new LinearLayoutManager(this));
        listAnimePresenter = new ListAnimePresenter(this, animepediaApi, this);
        rvListDetail.setAdapter(listAnimeAdapter);


    }

    @Override
    public void showAnimepedia(ArrayList<AnimepediaItem> animepediaItems) {

    }

    @Override
    public void showListDetail(ArrayList<ListAnimeItem> listAnimeItems) {

        listAnimeAdapter.setAnimeItems(listAnimeItems);
        rvListDetail.setAdapter(listAnimeAdapter);
        listAnimeAdapter.notifyDataSetChanged();

        ItemClickSupport.addTo(rvListDetail).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(DetailActivity.this,"OK",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
