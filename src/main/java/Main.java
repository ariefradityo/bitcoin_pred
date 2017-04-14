

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hmm.Hmm;
import org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.util.DoubleArray;
import org.encog.ConsoleStatusReportable;
import org.encog.Encog;
import org.encog.app.analyst.AnalystFileFormat;
import org.encog.app.analyst.AnalystGoal;
import org.encog.app.analyst.EncogAnalyst;
import org.encog.app.analyst.csv.normalize.AnalystNormalizeCSV;
import org.encog.app.analyst.script.normalize.AnalystField;
import org.encog.app.analyst.wizard.AnalystWizard;
import org.encog.app.analyst.wizard.NormalizeRange;
import org.encog.app.analyst.wizard.WizardMethodType;
import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationSteepenedSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.mathutil.matrices.Matrix;
import org.encog.ml.MLMethod;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.MLSequenceSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLSequenceSet;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.factory.MLTrainFactory;
import org.encog.ml.hmm.HiddenMarkovModel;
import org.encog.ml.hmm.alog.ForwardBackwardCalculator;
import org.encog.ml.hmm.alog.ForwardBackwardScaledCalculator;
import org.encog.ml.hmm.alog.MarkovGenerator;
import org.encog.ml.hmm.distributions.ContinousDistribution;
import org.encog.ml.hmm.train.bw.TrainBaumWelch;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.strategy.SmartLearningRate;
import org.encog.neural.networks.training.strategy.SmartMomentum;
import org.encog.neural.pattern.FeedForwardPattern;
import org.encog.neural.prune.PruneIncremental;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.TrainingSetUtil;
import org.jenetics.BitGene;
import org.jenetics.Genotype;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arief on 05/03/2017.
 */
public class Main {

    private static final String TRAIN_FILE = "train.csv";
    private static final String TEST_FILE = "test.csv";
    private static final String NORMALIZED_TRAIN = "normalized_train.csv";
    private static final String NORMALIZED_TEST = "normalized_test.csv";

    private static final String NORMALIZE_SCRIPT_TRAIN = "normalize_script_train";
    private static final String NORMALIZE_SCRIPT_TEST = "normalize_script_test";

    private static final int INPUT_VAR = 5;
    private static final int OUTPUT_VAR = 1;


    public static void main(String args[]) {

        System.out.println("Normalize Input File");

        normalizeData(TRAIN_FILE, TEST_FILE, WizardMethodType.FeedForward, true, NormalizeRange.NegOne2One);
        normalizeData(TRAIN_FILE, TEST_FILE, WizardMethodType.FeedForward, false, NormalizeRange.NegOne2One);

        ActivationFunction activationFunction = new ActivationTANH();

        MLDataSet trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TRAIN, true, INPUT_VAR, OUTPUT_VAR);
        MLDataSet testSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TEST, true, INPUT_VAR, OUTPUT_VAR);

        EncogAnalyst analyst = new EncogAnalyst();
        analyst.load(new File(NORMALIZE_SCRIPT_TEST));

        //VersatileMLDataSet versatile = new VersatileMLDataSet()

//        FeedForwardPattern pattern = new FeedForwardPattern();
//        pattern.setInputNeurons(INPUT_VAR);
//        pattern.setOutputNeurons(OUTPUT_VAR);
//        pattern.setActivationFunction(new ActivationTANH());

//        PruneIncremental pruneIncremental = new PruneIncremental(trainingSet, pattern, 100, 1, 10, new ConsoleStatusReportable());
//        pruneIncremental.addHiddenLayer(1,10);
//        pruneIncremental.addHiddenLayer(1,10);
//        pruneIncremental.process();
        List<String> reportList = new ArrayList<>();

        //for (int i = INPUT_VAR; i <= 2*INPUT_VAR; i++) {
//            BpnnRunner bpnnRunner = new BpnnRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.001, 15);
//            bpnnRunner.runTrain(false);
//            bpnnRunner.runTest(analyst);
//            reportList.add(bpnnRunner.getReport().toString());
        //}


        /////////////////////////////////////////////////////////////////////////////////////////////////
//        NeatRunner neatRunner = new NeatRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.0005, new ActivationSteepenedSigmoid(), true);
//        //neatRunner.runTrain(false);
//        neatRunner.runTest(analyst);
//        reportList.add(neatRunner.getMinReport().toString());

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String json = "";

        for (int i = 0; i < dataPathList.length; i++) {
            String newPathName = dataPathList[i].concat(TRAIN_FILE);
            normalizeData(newPathName, TEST_FILE, WizardMethodType.FeedForward, true, NormalizeRange.NegOne2One);

            newPathName = dataPathList[i].concat(TEST_FILE);
            normalizeData(TRAIN_FILE, newPathName, WizardMethodType.FeedForward, false, NormalizeRange.NegOne2One);

            trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TRAIN, true, inputList[i], OUTPUT_VAR);
            testSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TEST, true,  inputList[i], OUTPUT_VAR);
            analyst.load(new File(NORMALIZE_SCRIPT_TEST));
            reportList.add(dataPathList[i]);
            reportList.add("epoch;time;start;train_error;test_error;mape");
            System.out.println(dataPathList[i]);

            for (int j = INPUT_VAR; j <= 2 * INPUT_VAR; j++) {
                System.out.println(j);
                GannRunner gannRunner = new GannRunner(j, OUTPUT_VAR, trainingSet, testSet, 0.001, j);
                gannRunner.runTrain(false, false);
                gannRunner.runTest(analyst);
                reportList.add(gannRunner.getMinReport().toString());
            }

            json = new Gson().toJson(reportList);
            try (FileWriter file = new FileWriter("report.json")) {
                file.write(json);
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String s : reportList) {
                System.out.println(s);
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        GabpnnRunner gabpnnRunner = new GabpnnRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.001);
//        gabpnnRunner.runTrain(false);
//        gabpnnRunner.runTest(analyst);
//        reportList.add(gabpnnRunner.getMinReport().toString());

//        MLMethodFactory factory = new MLMethodFactory();
//        MLMethod method = factory.create(MLMethodFactory.TYPE_FEEDFORWARD,"?->TANH->10->TANH->?", 10, 1);
//
//        MLTrainFactory trainFactory = new MLTrainFactory();
//        MLTrain inTrain = trainFactory.create(method, trainingSet, MLTrainFactory.TYPE_GENETIC, "");
//
//        int epoch = 1;
//        do {
//            inTrain.iteration();
//            System.out.println("Epoch #" + epoch +
//                    " Error:" + inTrain.getError());
//            epoch++;
//
//        } while(inTrain.getError() > 0.0001);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        json = new Gson().toJson(reportList);
        try (FileWriter file = new FileWriter("report.json")) {
            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : reportList) {
            System.out.println(s);
        }

        Toolkit.getDefaultToolkit().beep();
        Encog.getInstance().shutdown();
    }


    private static void normalizeData(String trainPath, String testPath, WizardMethodType methodType, boolean isTrain, NormalizeRange range) {
        File sourceFile = new File(trainPath);
        File targetFile = new File(NORMALIZED_TRAIN);

        if (!isTrain) {
            sourceFile = new File(testPath);
            targetFile = new File(NORMALIZED_TEST);
        }

        EncogAnalyst analyst = new EncogAnalyst();
        AnalystWizard wizard = new AnalystWizard(analyst);
        wizard.setGoal(AnalystGoal.Regression);
        wizard.setRange(range);
        wizard.setMethodType(methodType);
        wizard.wizard(sourceFile, true, AnalystFileFormat.DECPNT_COMMA);

        final AnalystNormalizeCSV norm = new AnalystNormalizeCSV();
        norm.analyze(sourceFile, true, CSVFormat.ENGLISH, analyst);
        norm.setProduceOutputHeaders(true);
        norm.normalize(targetFile);

        analyst.save(NORMALIZE_SCRIPT_TRAIN);
        if (!isTrain) {
            analyst.save(NORMALIZE_SCRIPT_TEST);
        }

    }

    private static final String[] dataPathList = {
            "data/vanilla/",
            "data/will-ema/ema12/",
            "data/will-ema/ema12-26/",
            "data/will-ema/ema26/",
            "data/will-ema/ema-will5/",
            "data/will-ema/ema-will14/",
            "data/will-ema/will5/",
            "data/will-ema/will5-14/",
            "data/will-ema/will5-ema12/",
            "data/will-ema/will5-ema26/",
            "data/will-ema/will14/",
            "data/will-ema/will14-ema12/",
            "data/will-ema/will14-ema26/",
            "data/will-ema/will-ema12/",
            "data/will-ema/will-ema26/",
            "data/will-ema/",
    };

    private static final int[] inputList = {
            5,
            6,
            7,
            6,
            8,
            8,
            6,
            7,
            7,
            7,
            6,
            7,
            7,
            8,
            8,
            9
    };

}
