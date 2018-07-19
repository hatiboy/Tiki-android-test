package com.tiki.android.hometest.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Deal {
    @NonNull
    private String productName;
    @NonNull
    private String productThumbnail;
    private double productPrice;
    @NonNull
    private Date startedDate;
    @NonNull
    private Date endDate;

    public Deal() {

    }

    @NonNull
    public final String getProductName() {
        return this.productName;
    }

    @NonNull
    public final String getProductThumbnail() {
        return this.productThumbnail;
    }

    public final double getProductPrice() {
        return this.productPrice;
    }

    @NonNull
    public final Date getStartedDate() {
        return this.startedDate;
    }

    @NonNull
    public final Date getEndDate() {
        return this.endDate;
    }


    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    public void setProductThumbnail(@NonNull String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setStartedDate(@NonNull Date startedDate) {
        this.startedDate = startedDate;
    }

    public void setEndDate(@NonNull Date endDate) {
        this.endDate = endDate;
    }

}
