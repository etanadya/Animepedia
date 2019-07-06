package com.mobile.animepedia.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListAnimeItem implements Parcelable {

    String id,judul,sub_judul,genre,hari_rilis,gambar,video,banner,episode;

    public ListAnimeItem(String id, String judul, String sub_judul, String genre, String hari_rilis, String gambar, String video, String banner, String episode) {
        this.id = id;
        this.judul = judul;
        this.sub_judul = sub_judul;
        this.genre = genre;
        this.hari_rilis = hari_rilis;
        this.gambar = gambar;
        this.video = video;
        this.banner = banner;
        this.episode = episode;
    }

    protected ListAnimeItem(Parcel in) {
        id = in.readString();
        judul = in.readString();
        sub_judul = in.readString();
        genre = in.readString();
        hari_rilis = in.readString();
        gambar = in.readString();
        video = in.readString();
        banner = in.readString();
        episode = in.readString();
    }

    public static final Creator<ListAnimeItem> CREATOR = new Creator<ListAnimeItem>() {
        @Override
        public ListAnimeItem createFromParcel(Parcel in) {
            return new ListAnimeItem(in);
        }

        @Override
        public ListAnimeItem[] newArray(int size) {
            return new ListAnimeItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSub_judul() {
        return sub_judul;
    }

    public void setSub_judul(String sub_judul) {
        this.sub_judul = sub_judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getHari_rilis() {
        return hari_rilis;
    }

    public void setHari_rilis(String hari_rilis) {
        this.hari_rilis = hari_rilis;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(judul);
        dest.writeString(sub_judul);
        dest.writeString(genre);
        dest.writeString(hari_rilis);
        dest.writeString(gambar);
        dest.writeString(video);
        dest.writeString(banner);
        dest.writeString(episode);
    }
}
