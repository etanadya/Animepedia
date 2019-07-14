package com.mobile.animepedia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeskripsiFragment extends Fragment {
    TextView tvdes;
    public static DeskripsiFragment newInstance() {
        return new DeskripsiFragment();
    }

    public DeskripsiFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_deskripsi, container, false);
        tvdes = view.findViewById(R.id.tv_deskripsi_frag);

        DetailActivity detailActivity = new DetailActivity();

        tvdes.setText(detailActivity.EXTRA_DESKRIPSI);
        return view;
    }

}
