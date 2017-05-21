package genetic;

import org.encog.ml.CalculateScore;
import org.encog.ml.MLMethod;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.ContainsFlat;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.strategy.SmartLearningRate;
import org.encog.neural.networks.training.strategy.SmartMomentum;
import org.encog.util.error.CalculateRegressionError;

/**
 * Created by arief on 15/04/2017.
 */
public class GabpnnTrainingScore implements CalculateScore {

    private final MLDataSet training;
    private final MLDataSet test;

    public GabpnnTrainingScore(MLDataSet training, MLDataSet test) {
        this.training = training;
        this.test = test;
    }

    @Override
    public double calculateScore(MLMethod method) {

        BasicNetwork network = (BasicNetwork) ((BasicNetwork) method).clone();
        MLTrain train = new Backpropagation(network, training);
        train.addStrategy(new SmartLearningRate());
        train.addStrategy(new SmartMomentum());

        boolean training = true;
        double currentTestError = 0;
        double minError = 1;
        double overFitCount = 0;
        int epoch = 0;

        do {
            train.iteration();
            currentTestError = network.calculateError(test);
            if (Double.compare(currentTestError, minError) == -1 || Double.compare(currentTestError, minError) == 0) {
                minError = currentTestError;
                overFitCount = 0;
            } else if (Double.compare(currentTestError, minError) == 1) {
                overFitCount++;
            }

            if (overFitCount == 1000) {
                training = false;
            }


            //System.out.println(epoch+";"+currentTestError +";" + minError);
            epoch++;
        } while (training && epoch < 10000);

        System.out.println(epoch+";"+currentTestError +";" + minError);
        return minError;
    }

    @Override
    public boolean shouldMinimize() {
        return true;
    }

    @Override
    public boolean requireSingleThreaded() {
        return false;
    }
}
