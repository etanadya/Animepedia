package com.mobile.animepedia.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AnimepediaItem implements Parcelable  {
    String id, judul,genre,hari_rilis,gambar,video,deskripsi,banner,episode,deskripsi_eps;

    public AnimepediaItem(String id, String judul, String genre, String hari_rilis, String gambar, String video, String deskripsi, String banner, String episode, String deskripsi_eps) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.hari_rilis = hari_rilis;
        this.gambar = gambar;
        this.video = video;
        this.deskripsi = deskripsi;
        this.banner = banner;
        this.episode = episode;
        this.deskripsi_eps = deskripsi_eps;
    }

    protected AnimepediaItem(Parcel in) {
        id = in.readString();
        judul = in.readString();
        genre = in.readString();
        hari_rilis = in.readString();
        gambar = in.readString();
        video = in.readString();
        deskripsi = in.readString();
        banner = in.readString();
        episode = in.readString();
        deskripsi_eps = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(judul);
        dest.writeString(genre);
        dest.writeString(hari_rilis);
        dest.writeString(gambar);
        dest.writeString(video);
        dest.writeString(deskripsi);
        dest.writeString(banner);
        dest.writeString(episode);
        dest.writeString(deskripsi_eps);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AnimepediaItem> CREATOR = new Creator<AnimepediaItem>() {
        @Override
        public AnimepediaItem createFromParcel(Parcel in) {
            return new AnimepediaItem(in);
        }

        @Override
        public AnimepediaItem[] newArray(int size) {
            return new AnimepediaItem[size];
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public String getDeskripsi_eps() {
        return deskripsi_eps;
    }

    public void setDeskripsi_eps(String deskripsi_eps) {
        this.deskripsi_eps = deskripsi_eps;
    }
}
