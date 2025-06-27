package com.Sambhav.Ecommerce.model;

import com.Sambhav.Ecommerce.Repository.Productrepositry;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.Date;

@Data
@Component
@Entity
@Table(name ="Product")

public class Product
{
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String brand;
    private int price;
    private String category;
    private String quantity;
    private String desc;
    private Boolean available;
    private Date releaseDate;
    private String imagename;
    private String imagetype;
    @Lob
    private byte[]  imagedata;

    public String getImagename() {
        return imagename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

    public byte[] getImagedata() {
        return imagedata;
    }

    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }


    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    @Override
    public String toString()
    {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
