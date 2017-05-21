package model;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by arief on 23/04/2017.
 */
public class Open {

    @CsvBindByName
    private double open;

    @CsvBindByName(column = "next_open")
    private double nextOpen;

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getNextOpen() {
        return nextOpen;
    }

    public void setNextOpen(double nextOpen) {
        this.nextOpen = nextOpen;
    }
}
