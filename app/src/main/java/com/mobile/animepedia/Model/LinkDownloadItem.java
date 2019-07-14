package com.mobile.animepedia.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class LinkDownloadItem implements Parcelable {
    String id, nama_link,link_download;

    public LinkDownloadItem(String id, String nama_link, String link_download) {
        this.id = id;
        this.nama_link = nama_link;
        this.link_download = link_download;
    }

    protected LinkDownloadItem(Parcel in) {
        id = in.readString();
        nama_link = in.readString();
        link_download = in.readString();
    }

    public static final Creator<LinkDownloadItem> CREATOR = new Creator<LinkDownloadItem>() {
        @Override
        public LinkDownloadItem createFromParcel(Parcel in) {
            return new LinkDownloadItem(in);
        }

        @Override
        public LinkDownloadItem[] newArray(int size) {
            return new LinkDownloadItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_link() {
        return nama_link;
    }

    public void setNama_link(String nama_link) {
        this.nama_link = nama_link;
    }

    public String getLink_download() {
        return link_download;
    }

    public void setLink_download(String link_download) {
        this.link_download = link_download;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama_link);
        dest.writeString(link_download);
    }
}
