package com.mobile.animepedia.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.animepedia.Model.ListAnimeItem;
import com.mobile.animepedia.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAnimeAdapter extends RecyclerView.Adapter<ListAnimeAdapter.ListAnimeViewHolder> {
    private List<ListAnimeItem>animeItems= new ArrayList<>();
    Context context;

    public List<ListAnimeItem> getAnimeItems() {
        return animeItems;
    }

    public void setAnimeItems(List<ListAnimeItem> animeItems) {
        this.animeItems = animeItems;
    }

    public ListAnimeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListAnimeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.item_list_detail_anime, viewGroup, false );
        return new ListAnimeAdapter.ListAnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAnimeViewHolder listAnimeViewHolder, int i) {
        Glide.with(context).load(getAnimeItems().get(i).getGambar()).into(listAnimeViewHolder.cimgImage);
        listAnimeViewHolder.tvJudulList.setText(getAnimeItems().get(i).getSub_judul());

    }

    @Override
    public int getItemCount() {
        return getAnimeItems().size();
    }

    public class ListAnimeViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cimgImage;
        TextView tvJudulList;
        public ListAnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            cimgImage = itemView.findViewById(R.id.cimg_image);
            tvJudulList = itemView.findViewById(R.id.tv_judul_listdetail);
        }
    }
}
