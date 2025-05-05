package com.app.productserv.dto;

import java.util.Objects;

public class Seller {

    String sellerName;

    String gender;

    boolean isMarried;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public Seller(String sellerName, String gender, boolean isMarried) {
        this.sellerName = sellerName;
        this.gender = gender;
        this.isMarried = isMarried;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerName='" + sellerName + '\'' +
                ", gender='" + gender + '\'' +
                ", isMarried=" + isMarried +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return isMarried == seller.isMarried && Objects.equals(sellerName, seller.sellerName) && Objects.equals(gender, seller.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerName, gender, isMarried);
    }
}
