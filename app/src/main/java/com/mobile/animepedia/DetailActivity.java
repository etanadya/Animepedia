package com.mobile.animepedia;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobile.animepedia.Adapter.HomeAdapter;
import com.mobile.animepedia.Adapter.ListAnimeAdapter;
import com.mobile.animepedia.Adapter.ViewPagerAdapter;
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
    ImageView imgBanner;
    ViewPager viewPager;
    TabLayout tabs;
    Toolbar toolbar;
    ListAnimeAdapter listAnimeAdapter;
    ListAnimePresenter listAnimePresenter;
    AnimepediaApi animepediaApi;

    public static String EXTRA_JUDUL = "extra_judul";
    public static String EXTRA_EPISODE = "extra_episode";
    public static String EXTRA_GAMBAR = "extra_gambar";
    public static String EXTRA_DESKRIPSI_EPS = "extra_deskripsi_episode";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tabs = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar);
        tvJudul = findViewById(R.id.tv_judul_detail);
        rvListDetail = findViewById(R.id.rv_listdetail);
        imgBanner = findViewById(R.id.banner_detail_anime);
        viewPager = findViewById(R.id.viewPager);

        AnimepediaItem  animepediaItem = getIntent().getParcelableExtra("animepedia");
        tvJudul.setText(animepediaItem.getJudul());
        Glide.with(this).load(animepediaItem.getBanner()).into(imgBanner);

//        TampilDetailList();
//        listAnimePresenter.LoadItemDetail(animepediaItem.getJudul());
//        setupToolbar();
        init();
        EXTRA_JUDUL = animepediaItem.getJudul();
        EXTRA_GAMBAR = animepediaItem.getGambar();
        EXTRA_DESKRIPSI_EPS = animepediaItem.getDeskripsi_eps();



    }


//    protected void setupToolbar() {
//        if (toolbar != null) {
//            setSupportActionBar( toolbar );
//            getSupportActionBar().setDisplayShowHomeEnabled( true );
//        }
//    }

    private void init() {
        setupViewPager( viewPager );
        tabs.setupWithViewPager( viewPager );
    }

    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter( getSupportFragmentManager(), this );
        viewPager.setAdapter( viewPagerAdapter );
        viewPager.setOffscreenPageLimit( 3 );
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabs ) );

        tabs.setTabsFromPagerAdapter( viewPagerAdapter );
    }

    private void TampilDetailList() {
//        listAnimeAdapter = new ListAnimeAdapter(this);
//        animepediaApi = new AnimepediaApi();
//        rvListDetail.setLayoutManager(new LinearLayoutManager(this));
//        listAnimePresenter = new ListAnimePresenter(this, animepediaApi, this);
//        rvListDetail.setAdapter(listAnimeAdapter);


    }

    @Override
    public void showAnimepedia(ArrayList<AnimepediaItem> animepediaItems) {

    }

    @Override
    public void showListDetail(ArrayList<ListAnimeItem> listAnimeItems) {

//        listAnimeAdapter.setAnimeItems(listAnimeItems);
//        rvListDetail.setAdapter(listAnimeAdapter);
//        listAnimeAdapter.notifyDataSetChanged();
//
//        ItemClickSupport.addTo(rvListDetail).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Toast.makeText(DetailActivity.this,"OK",Toast.LENGTH_SHORT).show();
//                Intent intentDownload =  new Intent(DetailActivity.this, DetailDownloadActivity.class);
//                startActivity(intentDownload);
//            }
//        });

    }

    @Override
    public void showSearchEps(ArrayList<ListAnimeItem> listAnimeItems) {

    }

}