package com.app.productserv.dto;

import java.util.Map;
import java.util.Objects;

public class Product {

    int id;

    String productName;

    long price;

    String imageUrl;

    Map<String, String> featureDescMap;

    Seller seller;

    double rating;

    public Product(int id, String productName, long price, Seller seller) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSSeller(String sellerName) {
        this.seller = seller;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Map<String, String> getFeatureDescMap() {
        return featureDescMap;
    }

    public void setFeatureDescMap(Map<String, String> featureDescMap) {
        this.featureDescMap = featureDescMap;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Double.compare(rating, product.rating) == 0 && Objects.equals(productName, product.productName) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(featureDescMap, product.featureDescMap) && Objects.equals(seller, product.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, imageUrl, featureDescMap, seller, rating);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", featureDescMap=" + featureDescMap +
                ", seller=" + seller +
                ", rating=" + rating +
                '}';
    }
}
