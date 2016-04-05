package com.teioh08.branchingout;


import android.os.Parcel;
import android.os.Parcelable;

public class Tree implements Parcelable{
    public String objectID;
    public int treeID;
    public String common;
    public String scientific;
    public String wiki;
    public String image;
    public double latitute;
    public double longitude;


    public Tree(String cName, String sName, String pId, double lat, double log, int id, String link, String img){
        objectID = pId;
        treeID = id;
        common = cName;
        scientific = sName;
        latitute = lat;
        longitude = log;
        wiki = link;
        image =img;
        image = "http://farm4.static.flickr.com/3154/2858250032_13783e7640_o.jpg";
    }

    public Tree(String cName){
        common = cName;
        image = "http://farm4.static.flickr.com/3154/2858250032_13783e7640_o.jpg";
        wiki = "https://en.wikipedia.org/wiki/Tree";
    }

    protected Tree(Parcel in) {
        objectID = in.readString();
        treeID = in.readInt();
        common = in.readString();
        scientific = in.readString();
        wiki = in.readString();
        image = in.readString();
        latitute = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Tree> CREATOR = new Creator<Tree>() {
        @Override
        public Tree createFromParcel(Parcel in) {
            return new Tree(in);
        }

        @Override
        public Tree[] newArray(int size) {
            return new Tree[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(objectID);
        dest.writeInt(treeID);
        dest.writeString(common);
        dest.writeString(scientific);
        dest.writeString(wiki);
        dest.writeString(image);
        dest.writeDouble(latitute);
        dest.writeDouble(longitude);
    }
}
