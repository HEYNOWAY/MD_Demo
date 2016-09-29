package com.example.luos.demofortest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luos on 2016/9/25.
 */
public class NewsBean implements Parcelable{
    private int ImageId;
    private String title;
    private String content;

    public NewsBean(int imageId, String title, String content) {
        ImageId = imageId;
        this.title = title;
        this.content = content;
    }

    protected NewsBean(Parcel in) {
        ImageId = in.readInt();
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel in) {
            return new NewsBean(in);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ImageId);
        dest.writeString(title);
        dest.writeString(content);
    }
}
