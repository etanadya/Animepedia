package com.mobile.animepedia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.animepedia.MainActivity;
import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<AnimepediaItem> animepediaItems;
    private Context context;


    public HomeAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<AnimepediaItem> getAnimepediaItems() {
        return animepediaItems;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setAnimepediaItems(ArrayList<AnimepediaItem> animepediaItems) {
        this.animepediaItems = animepediaItems;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.item_list_utama, viewGroup, false );
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {
        Glide.with(getContext()).load(animepediaItems.get(i).getGambar()).into(homeViewHolder.imgPhoto);
        homeViewHolder.tvJudul.setText(animepediaItems.get(i).getJudul());
        homeViewHolder.tvHari.setText(animepediaItems.get(i).getHari_rilis());
    }

    @Override
    public int getItemCount() {
        return getAnimepediaItems().size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvJudul,tvHari;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvHari = itemView.findViewById(R.id.tv_hari);
        }
    }
}
