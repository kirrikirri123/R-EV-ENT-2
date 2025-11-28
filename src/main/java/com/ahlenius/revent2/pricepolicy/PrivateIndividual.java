package com.ahlenius.revent2.pricepolicy;

public class PrivateIndividual  implements PricePolicy{

    @Override
    public String priceVAT(double x) {
        double inkVAT = x * 1.25;
        double diff = inkVAT - x;
        return "ínkl. moms 25 % : "+ diff +" kr.\nTotalkostnad: \" + inkVAT +\" kr.";}

    @Override
    public double discount(double x) {
        return x * 0.7; }
}
