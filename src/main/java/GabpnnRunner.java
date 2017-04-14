import org.encog.app.analyst.EncogAnalyst;
import org.encog.ml.MLError;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.strategy.SmartLearningRate;
import org.encog.neural.networks.training.strategy.SmartMomentum;
import org.encog.persist.EncogDirectoryPersistence;
import org.jenetics.BitChromosome;
import org.jenetics.BitGene;
import org.jenetics.Genotype;
import org.jenetics.util.Factory;

import java.io.File;

/**
 * Created by arief on 21/03/2017.
 */
public class GabpnnRunner extends Runner
{

    private BasicNetwork network;
    private Train train;
    private final String FILE_NAME = "gabpnn.eg";
    private final String GA_FILE_NAME = "gann.eg";

    private boolean[] check = {false, false, false, false, false};
    public GabpnnRunner(int input, int output, MLDataSet trainingSet, MLDataSet testSet, double error){
        super(input, output, trainingSet, testSet, error);
        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(GA_FILE_NAME));
    }

    protected void inTrain(int epoch, double error) {

        if(Double.compare(currentTestError, minError) == -1 || Double.compare(currentTestError, minError) == 0){
            saveGabpnn(network);
        }

        if(error <= 0.0016 && !check[0]){
            saveGabpnn(network);
            check[0] = true;
        }
        if(error <= 0.00155 && !check[1]){
            saveGabpnn(network);
            check[1] = true;
        }
        if(error <= 0.0015 && !check[2]){
            saveGabpnn(network);
            check[2] = true;
        }
        if(error <= 0.00145 && !check[3]){
            saveGabpnn(network);
            check[3] = true;
        }
        if(error <= 0.0014 && !check[4]){
            saveGabpnn(network, FILE_NAME );
            check[4] = true;
        }
    }

    private void saveGabpnn(BasicNetwork network) {
        File neatFile = new File(FILE_NAME);
        EncogDirectoryPersistence.saveObject(neatFile, network);
    }

    private void saveGabpnn(BasicNetwork network, String fileName) {
        File neatFile = new File(fileName);
        EncogDirectoryPersistence.saveObject(neatFile, network);
    }

    public void runTrain(boolean load){
        if(load){
            network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(FILE_NAME));
        }

        train = new Backpropagation(network, TRAIN_SET);
        train.addStrategy(new SmartLearningRate());
        train.addStrategy(new SmartMomentum());

        train(train, network);

        saveGabpnn(network);
    }

    public void runTest(EncogAnalyst analyst) {
        test(network, network, analyst);
    }

}
