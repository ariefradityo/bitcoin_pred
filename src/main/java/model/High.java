package model;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by arief on 23/04/2017.
 */
public class High {

    @CsvBindByName
    private double high;

    @CsvBindByName(column = "next_high")
    private double nextHigh;

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getNextHigh() {
        return nextHigh;
    }

    public void setNextHigh(double nextHigh) {
        this.nextHigh = nextHigh;
    }
}
