package com.mobile.animepedia.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mobile.animepedia.DeskripsiFragment;
import com.mobile.animepedia.DownloadFragment;
import com.mobile.animepedia.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {
   final Context context;
   final int Page = 2;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = DownloadFragment.newInstance();
                break;
            case 1:
                fragment = DeskripsiFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString( R.string.tab_1 );
            case 1:
                return context.getResources().getString( R.string.tab_2 );
        }
        return null;
    }

    @Override
    public int getCount() {
        return Page;

    }
}
