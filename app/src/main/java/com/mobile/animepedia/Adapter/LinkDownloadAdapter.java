package com.mobile.animepedia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.animepedia.DetailDownloadActivity;
import com.mobile.animepedia.Model.LinkDownloadItem;
import com.mobile.animepedia.OnclickLibrary.CustomOnItemClickListener;
import com.mobile.animepedia.R;

import java.util.ArrayList;

public class LinkDownloadAdapter extends RecyclerView.Adapter<LinkDownloadAdapter.LinkViewHolder> {
    private ArrayList<LinkDownloadItem>linkDownloadItems = new ArrayList<>();
    public Context context;


    public LinkDownloadAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<LinkDownloadItem> getLinkDownloadItems() {
        return linkDownloadItems;
    }

    public void setLinkDownloadItems(ArrayList<LinkDownloadItem> linkDownloadItems) {
        this.linkDownloadItems = linkDownloadItems;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.item_list_download, viewGroup, false );
        return new LinkDownloadAdapter.LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder linkViewHolder,int i) {
        linkViewHolder.tvLink.setText(getLinkDownloadItems().get(i).getNama_link());
        linkViewHolder.btnLink.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemCLicked(View view, int position) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(getLinkDownloadItems().get(position).getLink_download()));
                context.startActivity(browserIntent);
                Toast.makeText(context,getLinkDownloadItems().get(position).getLink_download(),Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getLinkDownloadItems().size();
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
