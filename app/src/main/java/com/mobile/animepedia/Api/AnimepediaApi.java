package com.mobile.animepedia.Api;

import com.mobile.animepedia.BuildConfig;

public class AnimepediaApi {
    public String getLeague() {
        String home = "http://3jnc.tech/animepedia/api/home/listanime.php";
        return home;
    }
    public String getHome() {
        String home = "home/listanime_home.php";
        String ListHome = "http://3jnc.tech/animepedia/api/home/listanime_home.php";
        return ListHome;
    }

    public String getListDetail(String string) {
        String listDetail = "search/serach_anime.php?judul=";
        String List = BuildConfig.SERVER + listDetail + string ;

        return List;
    }
    public String getLinkDownload() {
        String link = "download/link_download_anime.php";
        String LinkDownload = BuildConfig.SERVER + link ;

        return LinkDownload;
    }
}
