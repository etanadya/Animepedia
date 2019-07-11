package com.mobile.animepedia.View;

import com.mobile.animepedia.Model.AnimepediaItem;
import com.mobile.animepedia.Model.LinkDownloadItem;
import com.mobile.animepedia.Model.ListAnimeItem;

import java.util.ArrayList;

public interface MainView {
    void showAnimepedia(ArrayList<AnimepediaItem> animepediaItems);
    void showListDetail(ArrayList<ListAnimeItem>listAnimeItems);
    void showSearchEps(ArrayList<ListAnimeItem>listAnimeItems);
    void showLink(ArrayList<LinkDownloadItem>linkDownloadItems);
}
