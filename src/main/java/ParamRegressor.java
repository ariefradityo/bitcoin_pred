/**
 * Created by arief on 22/04/2017.
 */
public interface ParamRegressor {

//    public double computeOpen(double open){
//        return 1.112251 + 0.998371*open;
//    }
//
//    public double computeHigh(double high){
//        return  -0.004884 + 0.992354*high;
//    }
//
//    public double computeLow(double low){
//        return -0.005766 +  0.990311*low;
//    }
//
//    public double computeVolume(double volume){
//        return -0.16620 +  0.82004*volume;
//    }

    double computeOpen(double open, double high, double low, double close, double volume);
    double computeHigh(double open, double high, double low, double close, double volume);
    double computeLow(double open, double high, double low, double close, double volume);
    double computeVolume(double open, double high, double low, double close, double volume);

}
