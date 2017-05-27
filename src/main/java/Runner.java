import org.encog.app.analyst.EncogAnalyst;
import org.encog.app.analyst.script.normalize.AnalystField;
import org.encog.ml.CalculateScore;
import org.encog.ml.MLError;
import org.encog.ml.MLRegression;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.train.MLTrain;
import util.Report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arief on 14/03/2017.
 */
public abstract class Runner {

    private final String NORM_SCRIPT_TEST = "normalize_script_test";

    protected final int INPUT;
    protected final int OUTPUT;

    protected final MLDataSet TRAIN_SET;
    protected final MLDataSet TEST_SET;

    protected MLError mlError;

    protected final double ERROR;

    protected boolean training = false;

    private Report report;
    protected Report minReport;

    private List<Double> relativeErrorList = new ArrayList<>();

    protected  int overFitCount = 0;
    protected double minError = 1;
    protected double currentTestError = 1;


    public Runner(int input, int output, MLDataSet trainingSet, MLDataSet testSet, double error) {
        this.INPUT = input;
        this.OUTPUT = output;
        this.TRAIN_SET = trainingSet;
        this.TEST_SET = testSet;
        this.ERROR = error;
        this.overFitCount = 0;

        report = new Report(0, 1,0, 0, currentTestError);
    }

    protected void train(MLTrain train, MLError mlError) {
        this.mlError = mlError;
        train(train, 0, mlError);
    }

    protected void train(MLTrain train, int limit, MLError newMlError) {
        this.mlError = newMlError;
        training = true;
        long start = System.currentTimeMillis();
        int epoch = 1;
        do {
            train.iteration();
            currentTestError = 0;
            if (mlError != null) {
               currentTestError =  mlError.calculateError(TEST_SET);
                if (Double.compare(currentTestError, minError) == -1 || Double.compare(currentTestError, minError) == 0) {
                    minError = currentTestError;
                    long time = (System.currentTimeMillis() - start);
                    minReport = new Report(epoch, train.getError(),time, start, currentTestError);
                    overFitCount = 0;
                } else if(Double.compare(currentTestError, minError) == 1) {
                    overFitCount++;
                }

                if(overFitCount == 500){
                    training = false;
                }
            }
            long time = (System.currentTimeMillis() - start);
            String trainReport = "Epoch #" + epoch + " Error:" + train.getError() + " Time:" + time + " Start:" + start + " TestError: " + currentTestError + " MinError: " + minError;
            //System.out.println(trainReport);
            report = new Report(epoch, train.getError(),time, start, currentTestError);

            if (limit != 0 && epoch == limit) {
                break;
            }
            epoch++;
            inTrain(epoch, train.getError());

        } while (train.getError() >= ERROR && training);
        training = false;
        train.finishTraining();
        long finish = (System.currentTimeMillis() - start);
        System.out.println("Training time:" + finish);
    }

    protected void test(MLRegression mlRegression, MLError mlError, EncogAnalyst analyst) {
        long start = System.currentTimeMillis();
        System.out.println("Actual;Predicted");
        double sumRelative = 0;
        for (final MLDataPair pair : TEST_SET) {
            final MLData output = mlRegression.compute(pair.getInput());
            AnalystField field = analyst.getScript().getNormalize().getNormalizedFields().get(INPUT);
            double ideal = field.deNormalize( pair.getIdeal().getData(0));
            double predicted = field.deNormalize(output.getData(0));
            relativeErrorList.add((predicted - ideal)/ideal);
            sumRelative += Math.abs((predicted - ideal)/ideal);
            System.out.println(ideal+ ";" + predicted);
        }

        double mape = 100.0/relativeErrorList.size()*sumRelative;
        report.setMape(mape);
        System.out.println("MAPE: " + mape);
        System.out.println("MSE: " + mlError.calculateError(TEST_SET));
        System.out.println("Test time:"  + (System.currentTimeMillis() - start));
    }

    protected abstract void inTrain(int epoch, double error);

    public abstract void runTrain(boolean load);

    public abstract void runTest(EncogAnalyst analyst);

    public Report getReport() {
        return report;
    }

    public Report getMinReport() { return minReport;}
}
