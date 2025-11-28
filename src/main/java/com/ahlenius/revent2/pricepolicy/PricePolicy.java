package com.ahlenius.revent2.pricepolicy;

public interface PricePolicy {

    String priceVAT(double x);
    double discount(double x);


}
