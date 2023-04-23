package com.example.appKp6.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




@Entity
@Table(name="document_info")
public class DocumentInfo {

    @Id
    @Column(name = "idinfo", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    public DocumentInfo(){}

    public DocumentInfo(Long id, int amount, Double price, Document document, Item item) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.document = document;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
