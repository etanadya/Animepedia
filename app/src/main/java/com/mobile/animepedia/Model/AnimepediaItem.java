package com.mobile.animepedia.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AnimepediaItem implements Parcelable  {
    String id, judul,genre,hari_rilis,gambar,banner,deskripsi;

    public AnimepediaItem(String id, String judul, String genre, String hari_rilis, String gambar, String banner, String deskripsi) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.hari_rilis = hari_rilis;
        this.gambar = gambar;
        this.banner = banner;
        this.deskripsi = deskripsi;
    }

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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    protected AnimepediaItem(Parcel in) {
        id = in.readString();
        judul = in.readString();
        genre = in.readString();
        hari_rilis = in.readString();
        gambar = in.readString();
        banner = in.readString();
        deskripsi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(judul);
        dest.writeString(genre);
        dest.writeString(hari_rilis);
        dest.writeString(gambar);
        dest.writeString(banner);
        dest.writeString(deskripsi);
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
}
