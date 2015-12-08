package kz.inculerate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aibek on 09.12.15.
 */
public class News implements Parcelable{


    String newsCat;
    String accountFullname;
    String newsTitle;
    String newsImageFile;
    int newsSite;
    String newsCreatedDate;


    public News(){}
    protected News(Parcel in) {
        newsCat = in.readString();
        accountFullname = in.readString();
        newsTitle = in.readString();
        newsImageFile = in.readString();
        newsSite = in.readInt();
        newsCreatedDate = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getNewsCat() {
        return newsCat;
    }

    public void setNewsCat(String newsCat) {
        this.newsCat = newsCat;
    }

    public String getAccountFullname() {
        return accountFullname;
    }

    public void setAccountFullname(String accountFullname) {
        this.accountFullname = accountFullname;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImageFile() {
        return newsImageFile;
    }

    public void setNewsImageFile(String newsImageFile) {
        this.newsImageFile = newsImageFile;
    }

    public int getNewsSite() {
        return newsSite;
    }

    public void setNewsSite(int newsSite) {
        this.newsSite = newsSite;
    }

    public String getNewsCreatedDate() {
        return newsCreatedDate;
    }

    public void setNewsCreatedDate(String newsCreatedDate) {
        this.newsCreatedDate = newsCreatedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(newsCat);
        parcel.writeString(accountFullname);
        parcel.writeString(newsTitle);
        parcel.writeString(newsImageFile);
        parcel.writeInt(newsSite);
        parcel.writeString(newsCreatedDate);
    }
}
