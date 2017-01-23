package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Egor on 20.01.2017.
 */
@Entity(name = "gallery")
public class Photo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private byte[] picture;

    @Column
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo that = (Photo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!Arrays.equals(picture, that.picture)) return false;
        return info != null ? info.equals(that.info) : that.info == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(picture);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", picture=" + Arrays.toString(picture) +
                ", info='" + info + '\'' +
                '}';
    }
}
