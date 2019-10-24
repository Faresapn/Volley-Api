package com.example.sub3eca;

import android.os.Parcel;
import android.os.Parcelable;


public class Items implements Parcelable {

    String Desc_film,Title_film,Info_film,photo;

    public Items(String desc_film, String title_film, String info_film, String photo) {
        Desc_film = desc_film;
        Title_film = title_film;
        Info_film = info_film;
        this.photo = photo;
    }


    public String getDesc_film() {
        return Desc_film;
    }

    public String getTitle_film() {
        return Title_film;
    }

    public String getInfo_film() {
        return Info_film;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Desc_film);
        dest.writeString(this.Title_film);
        dest.writeString(this.Info_film);
        dest.writeString(this.photo);
    }

    protected Items(Parcel in) {
        this.Desc_film = in.readString();
        this.Title_film = in.readString();
        this.Info_film = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel source) {
            return new Items(source);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };
}