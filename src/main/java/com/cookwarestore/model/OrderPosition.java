package com.cookwarestore.model;

import javax.persistence.*;

@Entity(name = "torderposition")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cookware cookware;
    private int quantity;

    public OrderPosition(int id, Cookware tool, int quantity) {
        this.id = id;
        this.cookware = tool;
        this.quantity = quantity;
    }

    public OrderPosition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public Cookware getCookware() {
        return cookware;
    }

    public void setCookware(Cookware cookware) {
        this.cookware = cookware;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
