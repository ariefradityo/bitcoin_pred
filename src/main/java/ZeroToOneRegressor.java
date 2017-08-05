/**
 * Created by arief on 04/06/2017.
 */
public class ZeroToOneRegressor implements ParamRegressor {
    public double computeOpen(double open, double high, double low, double close, double volume) {
        return -1.943e-04 + 7.627e-03 * open -6.578e-03 * high + 1.528e-03 * low + 9.974e-01 * close + 4.570e-04 * volume;
    }

    public double computeHigh(double open, double high, double low, double close, double volume) {
        return -0.021332 + 0.003362 * open + 0.322284 * high - 0.165992 * low + 0.859502 * close - 0.010343 * volume;
    }

    public double computeLow(double open, double high, double low, double close, double volume) {
        return -0.007265 + 0.046890 * open - 0.214200 * high + 0.335508 * low + 0.824968 * close - 0.001159 * volume;
    }

    public double computeVolume(double open, double high, double low, double close, double volume) {
        return  -0.031332 - 0.04186 * open - 0.75429 * high + 0.14584 * low + 0.75925 * close + 0.74683 * volume;
    }
}
