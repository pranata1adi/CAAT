package com.example.aseps.caat.Temp.home;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemHome implements Parcelable {
    private int id;
    private int id_project;
    private int no_form;
    private int id_form;
    private int id_field_auditor;
    private String logo;
    private String title;
    private String address;
    private int status;
    private int statusAll;
    private String deadline;
    private int progress_bar;
    private String nilaiPB;

    public ItemHome(int id, String title, int statusAll, int progress_bar) {
        this.id = id;
        this.title = title;
        this.statusAll = statusAll;
        this.progress_bar = progress_bar;
    }

    public ItemHome(String title, int statusAll, int progress_bar) {
        this.title = title;
        this.statusAll = statusAll;
        this.progress_bar = progress_bar;
    }

    public ItemHome(int id, int id_project, int no_form, int id_form, int id_field_auditor,
                    String logo, String title, String address, int status, int statusAll, String deadline) {
        this.id = id;
        this.id_project = id_project;
        this.no_form = no_form;
        this.id_form = id_form;
        this.id_field_auditor = id_field_auditor;
        this.logo = logo;
        this.title = title;
        this.address = address;
        this.status = status;
        this.statusAll = statusAll;
        this.deadline = deadline;
    }

    public ItemHome(int id, int id_project, int no_form, int id_form, int id_field_auditor,
                    String logo, String title, String address, int status, String deadline) {
        this.id = id;
        this.id_project = id_project;
        this.no_form = no_form;
        this.id_form = id_form;
        this.id_field_auditor = id_field_auditor;
        this.logo = logo;
        this.title = title;
        this.address = address;
        this.status = status;
        this.deadline = deadline;
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

    public int getNo_form() {
        return no_form;
    }

    public void setNo_form(int no_form) {
        this.no_form = no_form;
    }

    public int getId_form() {
        return id_form;
    }

    public void setId_form(int id_form) {
        this.id_form = id_form;
    }

    public int getId_field_auditor() {
        return id_field_auditor;
    }

    public void setId_field_auditor(int id_field_auditor) {
        this.id_field_auditor = id_field_auditor;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusAll() {
        return statusAll;
    }

    public void setStatusAll(int statusAll) {
        this.statusAll = statusAll;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getProgress_bar() {
        return progress_bar;
    }

    public void setProgress_bar(int progress_bar) {
        this.progress_bar = progress_bar;
    }

    public String getNilaiPB() {
        return nilaiPB;
    }

    public void setNilaiPB(String nilaiPB) {
        this.nilaiPB = nilaiPB;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(id_project);
        parcel.writeInt(no_form);
        parcel.writeInt(id_form);
        parcel.writeInt(id_field_auditor);
        parcel.writeString(logo);
        parcel.writeString(title);
        parcel.writeString(address);
        parcel.writeString(deadline);
//        parcel.writeString(progress_bar);
//        parcel.writeString(nilaiPB);
    }

    private ItemHome(Parcel in) {
        this.id = in.readInt();
        this.id_project = in.readInt();
        this.no_form = in.readInt();
        this.id_form = in.readInt();
        this.id_field_auditor = in.readInt();
        this.logo = in.readString();
        this.title = in.readString();
        this.address = in.readString();
        this.deadline = in.readString();
//        this.progress_bar = in.readString();
//        this.nilaiPB = in.readString();
    }

    public static Creator<ItemHome> getCREATOR() {
        return CREATOR;
    }

    public static final Parcelable.Creator<ItemHome> CREATOR
            = new Parcelable.Creator<ItemHome>() {
        @Override
        public ItemHome createFromParcel(Parcel in) {
            return new ItemHome(in);
        }

        @Override
        public ItemHome[] newArray(int size) {
            return new ItemHome[size];
        }
    };
}
