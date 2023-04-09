package com.example.appKp6.entity;

import javax.persistence.*;
import java.util.Date;

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
    private int status;

    @Column(name = "type")
    private int type;

    @Column(name = "id_user")
    private int id_user;

    @Column(name = "id_provider")
    private int id_provider;

    public Document(){

    }

    public Document(Long id, int number, Date date, int status, int type, int id_user, int id_provider) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.status = status;
        this.type = type;
        this.id_user = id_user;
        this.id_provider = id_provider;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_provider() {
        return id_provider;
    }

    public void setId_provider(int id_provider) {
        this.id_provider = id_provider;
    }
}
