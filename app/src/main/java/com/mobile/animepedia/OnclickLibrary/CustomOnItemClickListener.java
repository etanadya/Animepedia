package com.mobile.animepedia.OnclickLibrary;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int position;

     public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    private OnItemClickCallback onItemClickCallback;



    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemCLicked(view, position);
    }

    public interface OnItemClickCallback {
        void onItemCLicked(View view, int position);
    }

}
