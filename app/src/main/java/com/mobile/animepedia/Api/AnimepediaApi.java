package com.mobile.animepedia.Api;

import com.mobile.animepedia.BuildConfig;

public class AnimepediaApi {
    public String getLeague() {
        String home = "http://cucusadewa0294belajar.000webhostapp.com/animepedia/api/home/listanime.php";
        return home;
    }

    public String getListDetail(String string) {
        String listDetail = "serach/search_animepedia.php?search=";
        String List = BuildConfig.SERVER + listDetail + string ;

        return List;
    }
}
