package model;

import com.opencsv.bean.CsvBind;
import com.opencsv.bean.CsvBindByName;

/**
 * Created by arief on 22/04/2017.
 */
public class Item {

    @CsvBindByName
    private double open;

    @CsvBindByName
    private double high;

    @CsvBindByName
    private double low;

    @CsvBindByName
    private double close;

    @CsvBindByName
    private double volume;

    @CsvBindByName
    private double will5;

    @CsvBindByName
    private double will14;

    @CsvBindByName
    private double ema12;

    @CsvBindByName
    private double ema26;

    @CsvBindByName
    private double next1;

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWill5() {
        return will5;
    }

    public void setWill5(double will5) {
        this.will5 = will5;
    }

    public double getWill14() {
        return will14;
    }

    public void setWill14(double will14) {
        this.will14 = will14;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma26() {
        return ema26;
    }

    public void setEma26(double ema26) {
        this.ema26 = ema26;
    }

    public double getNext1() {
        return next1;
    }

    public void setNext1(double next1) {
        this.next1 = next1;
    }

}
