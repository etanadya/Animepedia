package com.mobile.animepedia.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.animepedia.Api.AnimepediaApi;
import com.mobile.animepedia.DownloadFragment;
import com.mobile.animepedia.Model.ListAnimeItem;
import com.mobile.animepedia.View.MainView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAnimePresenter {
    MainView view;
    StringRequest stringRequest;
    AnimepediaApi animepediaApi;
    DownloadFragment context;

    public ListAnimePresenter(MainView view, AnimepediaApi animepediaApi, DownloadFragment context) {
        this.view = view;
        this.animepediaApi = animepediaApi;
        this.context = context;
    }

    public DownloadFragment getContext() {
        return context;
    }

    public void setContext(DownloadFragment context) {
        this.context = context;
    }

    public MainView getView() {
        return view;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public StringRequest getStringRequest() {
        return stringRequest;
    }

    public void setStringRequest(StringRequest stringRequest) {
        this.stringRequest = stringRequest;
    }

    public AnimepediaApi getAnimepediaApi() {
        return animepediaApi;
    }

    public void setAnimepediaApi(AnimepediaApi animepediaApi) {
        this.animepediaApi = animepediaApi;
    }


    public void LoadItemDetail(String string) {
        String URL = animepediaApi.getListDetail(string);
        final ArrayList<ListAnimeItem> listAnimeItems = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray listAnimepediaArray = object.getJSONArray("pencarian");
                    for (int i = 0; i < listAnimepediaArray.length(); i++) {
                        JSONObject listAnimepediaObject = listAnimepediaArray.getJSONObject(i);
                        ListAnimeItem listAnimeItem = new ListAnimeItem(
                                listAnimepediaObject.getString("id"),
                                listAnimepediaObject.getString("judul"),
                                listAnimepediaObject.getString("sub_judul"),
                                listAnimepediaObject.getString("episode"),
                                listAnimepediaObject.getString("genre"),
                                listAnimepediaObject.getString("hari_rilis"),
                                listAnimepediaObject.getString("gambar"),
                                listAnimepediaObject.getString("banner"),
                                listAnimepediaObject.getString("video"),
                                listAnimepediaObject.getString("deskripsi"),
                                listAnimepediaObject.getString("deskripsi_eps")
                        );

                        listAnimeItems.add(listAnimeItem);

                    }


                    view.showListDetail(listAnimeItems);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext().getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getActivity());
        requestQueue.add(stringRequest);
    }
}
