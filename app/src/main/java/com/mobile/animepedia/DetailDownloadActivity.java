package com.mobile.animepedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.animepedia.Adapter.HomeAdapter;
import com.mobile.animepedia.Adapter.LinkDownloadAdapter;
import com.mobile.animepedia.Api.AnimepediaApi;
import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.Model.ListAnimeItem;
import com.mobile.animepedia.Presenter.HomePresenter;
import com.mobile.animepedia.View.MainView;

import java.util.ArrayList;
import java.util.List;

public class DetailDownloadActivity extends AppCompatActivity implements MainView {
    TextView tvJudulDownloadDetail,tvEpisodeDownload, tvDeskripsiEps;
    ImageView imgGambarDownload;
    AnimepediaApi animepediaApi;
    RecyclerView rvLink;
    List<ListAnimeItem>listAnimeItems= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_download);

        tvJudulDownloadDetail = findViewById(R.id.tv_judul_download);
        tvEpisodeDownload = findViewById(R.id.tv_judul_episode_download);
        tvDeskripsiEps = findViewById(R.id.tv_deskripsi_download);
        imgGambarDownload = findViewById(R.id.banner_episode);
        rvLink = findViewById(R.id.rvLink);
        ListAnimeItem listAnimeItem = getIntent().getParcelableExtra("datalist");

//        animepediaApi = new AnimepediaApi();
//        searchEpsPresenter = new SearchEpsPresenter(this, animepediaApi, this);
//        searchEpsPresenter.LoadSearchEps(listAnimeItem.getJudul(),"episode 01");
//        searchEpsAdapter = new SearchEpsAdapter(this);
//
//        searchEpsAdapter.notifyDataSetChanged();

        tvJudulDownloadDetail.setText(listAnimeItem.getJudul());
        tvEpisodeDownload.setText(listAnimeItem.getEpisode());
        tvDeskripsiEps.setText(listAnimeItem.getDeskripsi_eps());
        Glide.with(this).load(listAnimeItem.getGambar()).into(imgGambarDownload);

        LinkDownloadAdapter linkDownloadAdapter = new LinkDownloadAdapter(this);
        rvLink.setLayoutManager(new LinearLayoutManager(this));
        rvLink.setAdapter(linkDownloadAdapter);

    }

    @Override
    public void showAnimepedia(ArrayList<AnimepediaItem> animepediaItems) {

    }

    @Override
    public void showListDetail(ArrayList<ListAnimeItem> listAnimeItems) {

    }

    @Override
    public void showSearchEps(ArrayList<ListAnimeItem> listAnimeItems) {

    }
}
