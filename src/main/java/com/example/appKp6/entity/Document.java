package com.example.appKp6.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="document")
public class Document {

    @Id
    @Column(name = "iddocument", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private int number;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Set<DocumentInfo> documentInfo = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Set<Income> income = new HashSet<>();


    public Document(){

    }

    public Document(Long id, int number, Date date, String status, String type) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.status = status;
        this.type = type;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
