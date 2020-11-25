package com.ualr.recyclerviewassignment.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

;

public class Inbox implements Parcelable {
    private String from;
    private String email;
    private String message;
    private String date;
    private boolean selected;

    public Inbox() {
        this.selected = false;
    }

    public void setData(String from, String email, String message, String date, boolean selected) {
        this.from = from;
        this.email = email;
        this.message = message;
        this.date = date;
        this.selected = selected;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void toggleSelection() {
        this.selected = !this.selected;
    }

    public static final Creator<Inbox> CREATOR = new Creator<Inbox>() {
        @Override
        public Inbox createFromParcel(Parcel in) {
            return new Inbox(in);
        }
        @Override
        public Inbox[] newArray(int size) {
            return new Inbox[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.from);
        parcel.writeString(this.email);
        parcel.writeString(this.message);
        parcel.writeString(this.date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.selected);
        }
        else {
            parcel.writeInt(this.selected ? 1 : 0);
        }
    }


    protected Inbox(Parcel in) {
        this.from = in.readString();
        this.email = in.readString();
        this.message = in.readString();
        this.date = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.selected = in.readBoolean();
        }
        else {
            this.selected = in.readInt() == 1;
        }
    }
}