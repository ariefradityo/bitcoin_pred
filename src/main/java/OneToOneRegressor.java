/**
 * Created by arief on 04/06/2017.
 */
public class OneToOneRegressor implements ParamRegressor {
    public double computeOpen(double open, double high, double low, double close, double volume) {
        return 0.0000564 + 0.0076267 * open - 0.0065778 * high + 0.0015276 * low + 0.9974116 * close + 0.0004570 * volume;
    }

    public double computeHigh(double open, double high, double low, double close, double volume) {
        return -0.033852 + 0.003362 * open + 0.322284 * high - 0.165992 * low + 0.859502 * close - 0.010343 * volume;
    }

    public double computeLow(double open, double high, double low, double close, double volume) {
        return -0.022524 + 0.046890 * open - 0.214200 * high + 0.335508 * low + 0.824968 * close - 0.001159 * volume;
    }

    public double computeVolume(double open, double high, double low, double close, double volume) {
        return -0.20690 - 0.04186 * open - 0.75429 * high + 0.14584 * low + 0.75925 * close + 0.74683 * volume;
    }
}
