package com.mobile.animepedia.Api;

import com.mobile.animepedia.BuildConfig;

public class AnimepediaApi {
    public String getLeague() {
        String home = "http://3jnc.tech/animepedia/api/home/listanime.php";
        return home;
    }

    public String getListDetail(String string) {
        String listDetail = "search/serach_anime.php?judul=";
        String List = BuildConfig.SERVER + listDetail + string ;

        return List;
    }
    public String getSearchEps(String judul,String eps) {
        String search = "search/serach_anime.php?judul=";
        String SearchEps = BuildConfig.SERVER + search + judul+"&eps="+eps ;

        return SearchEps;
    }
}
