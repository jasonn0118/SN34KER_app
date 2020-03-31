package com.example.sn34ker;

import java.util.Date;

public class ProductModel {
    private int id;
    private String name;
    private String brand;
    private String type;
    private double CA_price;
    private double US_Size;
    private String updatedDate;

    public ProductModel(int id, String name, String brand, String type, double CA_price, double US_Size, String updatedDate) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.CA_price = CA_price;
        this.US_Size = US_Size;
        this.updatedDate = updatedDate;
    }

    public ProductModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCA_price() {
        return CA_price;
    }

    public void setCA_price(double CA_price) {
        this.CA_price = CA_price;
    }

    public double getUS_Size() {
        return US_Size;
    }

    public void setUS_Size(double US_Size) {
        this.US_Size = US_Size;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", CA_price=" + CA_price +
                ", US_Size=" + US_Size +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
