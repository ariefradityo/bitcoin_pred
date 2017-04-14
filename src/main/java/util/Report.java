package util;

/**
 * Created by arief on 06/04/2017.
 */
public class Report
{
    private int epoch;
    private double train_error;
    private long time;
    private long start;
    private double test_error;
    private double mape;

    public Report(){

    }

    public Report(int epoch, double train_error, long time, long start, double test_error) {
        this.epoch = epoch;
        this.train_error = train_error;
        this.time = time;
        this.start = start;
        this.test_error = test_error;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public double getTrain_error() {
        return train_error;
    }

    public void setTrain_error(double train_error) {
        this.train_error = train_error;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public double getTest_error() {
        return test_error;
    }

    public void setTest_error(double test_error) {
        this.test_error = test_error;
    }

    public double getMape() {
        return mape;
    }

    public void setMape(double mape) {
        this.mape = mape;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(epoch).append(";");
        builder.append(time).append(";");
        builder.append(start).append(";");
        builder.append(train_error).append(";");
        builder.append(test_error).append(";");
        builder.append(mape).append(";");
        return builder.toString();
    }
}
