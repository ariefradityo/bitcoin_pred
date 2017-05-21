package model;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by arief on 23/04/2017.
 */
public class Low {

    @CsvBindByName
    private double low;

    @CsvBindByName(column = "next_low")
    private double nextLow;

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getNextLow() {
        return nextLow;
    }

    public void setNextLow(double nextLow) {
        this.nextLow = nextLow;
    }
}
