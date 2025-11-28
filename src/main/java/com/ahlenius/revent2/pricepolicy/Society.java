package com.ahlenius.revent2.pricepolicy;

public class Society implements PricePolicy{

    @Override
    public String priceVAT(double x) {
        return String.format("Totalkostnad: %.2f kr.\nexkl. moms.",x);
    }

    @Override
    public double discount(double x) {
        return x * 1;
    }
}
