import org.encog.app.analyst.EncogAnalyst;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.MLError;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.strategy.SmartLearningRate;
import org.encog.neural.networks.training.strategy.SmartMomentum;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;

/**
 * Created by arief on 14/03/2017.
 */
public class BpnnRunner extends Runner{

    private BasicNetwork network;
    private Train train;
    private final String FILE_NAME = "bpnn.eg";
    private final String GA_FILE_NAME = "gann.eg";

    private boolean[] check = {false, false, false, false, false};


    public BpnnRunner(int input, int output, MLDataSet trainingSet, MLDataSet testSet, double error) {
        super(input, output, trainingSet, testSet, error);

        network = new BasicNetwork();
        network.addLayer(new BasicLayer(new ActivationTANH(), false, INPUT));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, INPUT));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, OUTPUT));
        network.getStructure().finalizeStructure();
        network.reset();
    }

    public BpnnRunner(int input, int output, MLDataSet trainingSet, MLDataSet testSet, double error, int hiddenNode) {
        super(input, output, trainingSet, testSet, error);

        network = new BasicNetwork();
        network.addLayer(new BasicLayer(new ActivationTANH(), false, INPUT));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, hiddenNode));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, OUTPUT));
        network.getStructure().finalizeStructure();
        network.reset();
    }

    private void saveBpnn(BasicNetwork network) {
        File neatFile = new File(FILE_NAME);
        EncogDirectoryPersistence.saveObject(neatFile, network);
    }

    private void saveBpnn(BasicNetwork network, String fileName) {
        File neatFile = new File(fileName);
        EncogDirectoryPersistence.saveObject(neatFile, network);
    }

    public void saveBpnn(String fileName){
        File neatFile = new File(fileName);
        EncogDirectoryPersistence.saveObject(neatFile, network);
    }

    public void loadFile(String fileName){
        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(fileName));
    }

    public void runTrainFromGa(boolean load){
        if(load){
            network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(GA_FILE_NAME));
        }
        run(0);
    }

    public void runTrain(boolean load, int limit){
        if(load){
            network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(FILE_NAME));
        }
        run(limit);
    }

    public void runTrain(boolean load){
        if(load){
            network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(FILE_NAME));
        }
        run(0);
    }


    private void run(int limit){
        train = new Backpropagation(network, TRAIN_SET);
        train.addStrategy(new SmartLearningRate());
        train.addStrategy(new SmartMomentum());

        train(train, limit, network);

        //saveBpnn(network);
    }

    public void inTrain(int epoch, double error) {

        if(Double.compare(currentTestError, minError) == -1 || Double.compare(currentTestError, minError) == 0){
            saveBpnn(network);
        }

    }

    public void runTest(EncogAnalyst analyst) {
        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(FILE_NAME));
        test(network, network, analyst);
        minReport.setMape(getReport().getMape());
    }
}
