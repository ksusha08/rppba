package com.example.appKp6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="items")
public class Item {

    @Id
    @Column(name = "iditems", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "vendore_code", length = 255)
    private String vendoreCode;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "photos", length = 64)
    private String photos;

    @Column(name = "number", length = 64)
    private int number;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<DocumentInfo> documentInfo = new HashSet<>();


    public Item(){
    }


    public Item(Long id, String name, String vendoreCode, String description, Double discountPrice, String photos, int number) {
        this.id = id;
        this.name = name;
        this.vendoreCode = vendoreCode;
        this.description = description;
        this.discountPrice = discountPrice;
        this.photos = photos;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotos() {
        return photos;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/" + id + "/" + photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendoreCode() {
        return vendoreCode;
    }

    public void setVendoreCode(String vendoreCode) {
        this.vendoreCode = vendoreCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
