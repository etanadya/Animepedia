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
import com.mobile.animepedia.Model.LinkDownloadItem;
import com.mobile.animepedia.Model.ListAnimeItem;
import com.mobile.animepedia.Presenter.HomePresenter;
import com.mobile.animepedia.Presenter.LinkDownloadPresenter;
import com.mobile.animepedia.View.MainView;

import java.util.ArrayList;
import java.util.List;

public class DetailDownloadActivity extends AppCompatActivity implements MainView {
    TextView tvJudulDownloadDetail, tvEpisodeDownload, tvDeskripsiEps;
    ImageView imgGambarDownload;
    AnimepediaApi animepediaApi;
    RecyclerView rvLink;
    LinkDownloadAdapter linkDownloadAdapter;
    LinkDownloadPresenter linkDownloadPresenter;
    public static String EXTRA_JUDUL = "extra_judul";
    public static String EXTRA_SUB_JUDUL = "extra_sub_judul";
    public static String EXTRA_EPISODE = "extra_episode";


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


        tvJudulDownloadDetail.setText(listAnimeItem.getJudul());
        tvEpisodeDownload.setText(listAnimeItem.getEpisode());
        tvDeskripsiEps.setText(listAnimeItem.getDeskripsi_eps());
        Glide.with(this).load(listAnimeItem.getGambar()).into(imgGambarDownload);

        EXTRA_EPISODE = listAnimeItem.getEpisode();
        EXTRA_JUDUL = listAnimeItem.getJudul();
        EXTRA_SUB_JUDUL = listAnimeItem.getSub_judul();


        linkDownloadAdapter = new LinkDownloadAdapter(this);
        rvLink.setLayoutManager(new LinearLayoutManager(this));
        rvLink.setAdapter(linkDownloadAdapter);
        animepediaApi = new AnimepediaApi();
        linkDownloadPresenter = new LinkDownloadPresenter(this, animepediaApi, this);
        linkDownloadPresenter.LoadLink();

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

    @Override
    public void showLink(ArrayList<LinkDownloadItem> linkDownloadItems) {
        linkDownloadAdapter.setLinkDownloadItems(linkDownloadItems);
        rvLink.setAdapter(linkDownloadAdapter);
        linkDownloadAdapter.notifyDataSetChanged();

    }
}
