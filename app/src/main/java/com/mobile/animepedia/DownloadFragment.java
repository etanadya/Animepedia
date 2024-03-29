package com.mobile.animepedia;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.animepedia.Adapter.ListAnimeAdapter;
import com.mobile.animepedia.Api.AnimepediaApi;
import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.Model.LinkDownloadItem;
import com.mobile.animepedia.Model.ListAnimeItem;
import com.mobile.animepedia.OnclickLibrary.ItemClickSupport;
import com.mobile.animepedia.Presenter.ListAnimePresenter;
import com.mobile.animepedia.View.MainView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment implements MainView {
    public static String EXTRA_JUDUL= "extra_judul";
    RecyclerView rvListDownload;
    ListAnimeAdapter listAnimeAdapter;
    ListAnimePresenter listAnimePresenter;
    AnimepediaApi animepediaApi;

    Context context;

    public static DownloadFragment newInstance() {
        return new DownloadFragment();
    }


    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_download, container, false);
        rvListDownload = view.findViewById(R.id.rv_listdetail);

        showList();
        return view;
    }
    public void showList() {
        DetailActivity detailActivity = new DetailActivity();
        listAnimeAdapter = new ListAnimeAdapter(this);
        animepediaApi = new AnimepediaApi();
        rvListDownload.setLayoutManager(new LinearLayoutManager(context));
        ListAnimeAdapter listAnimeAdapter = new ListAnimeAdapter(DownloadFragment.this);
        rvListDownload.setAdapter(listAnimeAdapter);
        listAnimePresenter = new ListAnimePresenter( this,animepediaApi,this);
        listAnimePresenter.LoadItemDetail(detailActivity.EXTRA_JUDUL);


    }


    @Override
    public void showAnimepedia(ArrayList<AnimepediaItem> animepediaItems) {

    }

    @Override
    public void showListDetail(final ArrayList<ListAnimeItem> listAnimeItems) {
        listAnimeAdapter.setAnimeItems(listAnimeItems);
        rvListDownload.setAdapter(listAnimeAdapter);
        listAnimeAdapter.notifyDataSetChanged();
        ItemClickSupport.addTo(rvListDownload).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailDownloadActivity.class);

                ListAnimeItem listAnimeItem = new ListAnimeItem(
                        listAnimeItems.get(position).getId(),
                        listAnimeItems.get(position).getJudul(),
                        listAnimeItems.get(position).getEpisode(),
                        listAnimeItems.get(position).getSub_judul(),
                        listAnimeItems.get(position).getGenre(),
                        listAnimeItems.get(position).getHari_rilis(),
                        listAnimeItems.get(position).getGambar(),
                        listAnimeItems.get(position).getVideo(),
                        listAnimeItems.get(position).getBanner(),
                        listAnimeItems.get(position).getDeskripsi(),
                        listAnimeItems.get(position).getDeskripsi_eps()

                );
                intent.putExtra("datalist",listAnimeItem);
                startActivity(intent);
                Toasty.normal(getContext(), listAnimeItem.getEpisode(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void showSearchEps(ArrayList<ListAnimeItem> listAnimeItems) {

    }

    @Override
    public void showLink(ArrayList<LinkDownloadItem> linkDownloadItems) {

    }
}
