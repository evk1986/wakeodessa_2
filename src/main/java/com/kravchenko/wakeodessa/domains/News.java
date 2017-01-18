package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Egor on 16.01.2017.
 */
@Entity(name = "news")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    @Column(length = 65535)
    private byte[] image;

    @Column(length = 15000)
    private String information;

    @Column
    private String title;


    @Column
    private String creationDate;

    public News() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        this.creationDate = dateFormat.format(date);
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (newsId != news.newsId) return false;
        if (!Arrays.equals(image, news.image)) return false;
        if (information != null ? !information.equals(news.information) : news.information != null) return false;
        return creationDate.equals(news.creationDate);

    }

    @Override
    public int hashCode() {
        int result = (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + Arrays.hashCode(image);
        result = 31 * result + (information != null ? information.hashCode() : 0);
        result = 31 * result + creationDate.hashCode();
        return result;
    }

    public int getNewsId() {

        return newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


}
