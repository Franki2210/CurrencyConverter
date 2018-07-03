package com.example.user.cbrinfo.converter;

import com.example.user.cbrinfo.model.Valute;

import java.util.ArrayList;
import java.util.List;

public class ConverterModel {

    private double conversionRate;

    private List<Valute> valuteList;

    private Valute convertibleValute;
    private Valute convertedValute;

    public ConverterModel() {
        valuteList = new ArrayList<>();
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(float conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Valute getConvertibleValute() {
        return convertibleValute;
    }

    public void setConvertibleValute(Valute convertibleValute) {
        this.convertibleValute = convertibleValute;
    }

    public Valute getConvertedValute() {
        return convertedValute;
    }

    public void setConvertedValute(Valute convertedValute) {
        this.convertedValute = convertedValute;
    }

    public List<Valute> getValuteList() {
        return valuteList;
    }

    public void setValuteList(List<Valute> valuteList) {
        this.valuteList = valuteList;

        Valute valute = new Valute();
        valute.setCharCode("RUB");
        valute.setName("Российский рубль");
        valute.setNominal("1");
        valute.setValue("1");
        this.valuteList.add(0, valute);
    }

    public Valute getValuteByPos(int pos) {
        return valuteList.get(pos);
    }
}
