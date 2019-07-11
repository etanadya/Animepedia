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
import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.Model.LinkDownloadItem;
import com.mobile.animepedia.View.MainView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LinkDownloadPresenter {
    MainView view;
    StringRequest stringRequest;
    AnimepediaApi animepediaApi;
    Context context;

    public MainView getView() {
        return view;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public AnimepediaApi getAnimepediaApi() {
        return animepediaApi;
    }

    public void setAnimepediaApi(AnimepediaApi animepediaApi) {
        this.animepediaApi = animepediaApi;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LinkDownloadPresenter(MainView view, AnimepediaApi animepediaApi, Context context) {
        this.view = view;
        this.animepediaApi = animepediaApi;
        this.context = context;
    }
    public void LoadLink() {
        String URL = animepediaApi.getLinkDownload();
        final ArrayList<LinkDownloadItem> linkDownloadItems = new ArrayList<>();

        StringRequest stringRequest = new StringRequest( Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject( response );
                    JSONArray linkArray = object.getJSONArray( "link_download_anime" );
                    for (int i = 0; i < linkArray.length(); i++) {
                        JSONObject linkObject = linkArray.getJSONObject( i );
                        LinkDownloadItem linkDownloadItem = new LinkDownloadItem(
                                linkObject.getString( "id" ),
                                linkObject.getString( "nama_link" ),
                                linkObject.getString( "link_download" )

                        );

                        linkDownloadItems.add( linkDownloadItem );

                    }


                    view.showLink( linkDownloadItems );

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( context, "Error", Toast.LENGTH_SHORT ).show();
            }
        } );
        RequestQueue requestQueue = Volley.newRequestQueue( getContext());
        requestQueue.add( stringRequest );
    }
}
