package com.example.technicaltest.model;

import javax.validation.constraints.*;

public class ProductDetail {

    @NotBlank
    @Size(min = 1)
    private String id;
    @NotBlank
    @Size(min = 1)
    private String name;
    @NotBlank
    private double price;
    @NotBlank
    private boolean availability;

    public ProductDetail() {
    }
    public ProductDetail(String id, String name, double price, boolean availability) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.availability = availability;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public boolean isAvailability() {
      return availability;
    }

    public void setAvailability(boolean availability) {
      this.availability = availability;
    }
}
