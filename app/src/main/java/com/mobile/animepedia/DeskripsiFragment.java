package com.mobile.animepedia;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeskripsiFragment extends Fragment {
    JustifiedTextView tvdes;
    public static DeskripsiFragment newInstance() {
        return new DeskripsiFragment();
    }

    public DeskripsiFragment() {
        // Required empty public constructor
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_deskripsi, container, false);
        tvdes = view.findViewById(R.id.tv_deskripsi_frag);



        DetailActivity detailActivity = new DetailActivity();
//        tvdes.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        tvdes.setText(detailActivity.EXTRA_DESKRIPSI);
        return view;
    }

}
