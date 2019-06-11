package com.example.aseps.caat.Temp.stackholder;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemStakeholder implements Parcelable {
    private int id;
    private int id_project;
    private String position;
    private int total;

    public ItemStakeholder(int id, int id_project, String position, int total) {
        this.id = id;
        this.id_project = id_project;
        this.position = position;
        this.total = total;
    }

    public ItemStakeholder(int id_project, String position) {
        this.id_project = id_project;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static Creator<ItemStakeholder> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.id);
        dest.writeInt(this.id_project);
        dest.writeString(this.position);
        dest.writeInt(this.total);

    }

    private ItemStakeholder(Parcel in) {
        this.id = in.readInt();
        this.id_project = in.readInt();
        this.position = in.readString();
        this.total = in.readInt();
    }

    public static final Parcelable.Creator<ItemStakeholder> CREATOR
            = new Parcelable.Creator<ItemStakeholder>() {
        @Override
        public ItemStakeholder createFromParcel(Parcel in) {
            return new ItemStakeholder(in);
        }

        @Override
        public ItemStakeholder[] newArray(int size) {
            return new ItemStakeholder[size];
        }
    };
}
