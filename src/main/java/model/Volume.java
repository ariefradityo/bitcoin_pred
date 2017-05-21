package model;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by arief on 23/04/2017.
 */
public class Volume {

    @CsvBindByName
    private double volume;

    @CsvBindByName(column = "next_volume")
    private double nextVolume;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getNextVolume() {
        return nextVolume;
    }

    public void setNextVolume(double nextVolume) {
        this.nextVolume = nextVolume;
    }
}
