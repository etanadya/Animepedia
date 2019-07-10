package com.mobile.animepedia.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.animepedia.DetailDownloadActivity;
import com.mobile.animepedia.R;

public class LinkDownloadAdapter extends RecyclerView.Adapter<LinkDownloadAdapter.LinkViewHolder> {
    public LinkDownloadAdapter(DetailDownloadActivity detailDownloadActivity) {
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.item_list_download, viewGroup, false );
        return new LinkDownloadAdapter.LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder linkViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class LinkViewHolder extends RecyclerView.ViewHolder {
        TextView tvLink;
        TextView btnLink;
        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLink = itemView.findViewById(R.id.tv_link);
            btnLink = itemView.findViewById(R.id.btn_link);
        }
    }
}
