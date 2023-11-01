package ru.bazhanov.utils;

public class PriceUtils {
    public static Double getDoubleFromString(String number,Double valueInCaseOfFailure){
        try {
            return Double.parseDouble(number);
        }catch (NumberFormatException e){
            return valueInCaseOfFailure;
        }
    }
}
