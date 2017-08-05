

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import model.Item;
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
import org.encog.ml.MLMethod;
import org.encog.ml.MLRegression;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.factory.MLTrainFactory;
import org.encog.ml.train.MLTrain;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.PersistNEATPopulation;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.TrainingSetUtil;
import util.TechnicalAnalyst;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arief on 05/03/2017.
 */
public class Main {

    private static final String TRAIN_FILE = "train.csv";
    private static final String TEST_FILE = "test.csv";
    private static final String ALL_FILE = "all.csv";
    private static final String NORMALIZED_TRAIN = "normalized_train.csv";
    private static final String NORMALIZED_ALL = "normalized_all.csv";
    private static final String NORMALIZED_TEST = "normalized_test.csv";

    private static final String NORMALIZE_SCRIPT_TRAIN = "normalize_script_train";
    private static final String NORMALIZE_SCRIPT_TEST = "normalize_script_test";
    private static final String NORMALIZE_SCRIPT_ALL = "normalize_script_all";


    private static final int INPUT_VAR = 6;
    private static final int OUTPUT_VAR = 1;

    private static final String SEMICOL = ";";


    public static void main(String args[]) {

        System.out.println("Normalize Input File");

        normalizeData(TRAIN_FILE, TEST_FILE, WizardMethodType.FeedForward, true, NormalizeRange.Zero2One);
        normalizeData(TRAIN_FILE, TEST_FILE, WizardMethodType.FeedForward, false, NormalizeRange.Zero2One);
        normalizeAll(ALL_FILE, WizardMethodType.FeedForward, NormalizeRange.Zero2One);

        ActivationFunction activationFunction = new ActivationTANH();


        MLDataSet allSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_ALL, true, INPUT_VAR, OUTPUT_VAR);
        List<MLDataPair> trainingList = new ArrayList<>();
        List<MLDataPair> testList = new ArrayList<>();

        for (int i = 0; i < allSet.size(); i++) {
            if (i < 950) {
                trainingList.add(allSet.get(i));
            } else {
                testList.add(allSet.get(i));
            }
        }

//        MLDataSet trainingSet = new BasicMLDataSet(trainingList);
//        MLDataSet testSet = new BasicMLDataSet(testList);

        MLDataSet trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TRAIN, true, INPUT_VAR, OUTPUT_VAR);
        MLDataSet testSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TEST, true, INPUT_VAR, OUTPUT_VAR);

        EncogAnalyst analyst = new EncogAnalyst();
        analyst.load(new File(NORMALIZE_SCRIPT_TRAIN));


        String json = "";

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

//        for (int i = 0; i < dataPathList.length; i++) {
//            String newPathName = dataPathList[i].concat(TRAIN_FILE);
//            normalizeData(newPathName, TEST_FILE, WizardMethodType.FeedForward, true, NormalizeRange.NegOne2One);
//
//            newPathName = dataPathList[i].concat(TEST_FILE);
//            normalizeData(TRAIN_FILE, newPathName, WizardMethodType.FeedForward, false, NormalizeRange.NegOne2One);
//
//            trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TRAIN, true, inputList[i], OUTPUT_VAR);
//            testSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TEST, true, inputList[i], OUTPUT_VAR);
//            analyst.load(new File(NORMALIZE_SCRIPT_TEST));
//            reportList.add(dataPathList[i]);
//            reportList.add("epoch;time;start;train_error;test_error;mape");
//            System.out.println(dataPathList[i]);
//
////            for (int j = INPUT_VAR; j <= 2 * INPUT_VAR; j++) {
////                BpnnRunner bpnnRunner = new BpnnRunner(inputList[i], OUTPUT_VAR, trainingSet, testSet, 0.0001, j);
//        for (int j = 0; j < 10; j++) {
//            BpnnRunner bpnnRunner = new BpnnRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.0001, 7);
//            bpnnRunner.loadFile("gabpnn_6.eg");
//            //bpnnRunner.runTrain(false, 100000);
//            bpnnRunner.runTest(analyst);
//            //bpnnRunner.saveBpnn("gabpnn_"+j+".eg");
//            reportList.add(bpnnRunner.getMinReport().toString());

//            }

//            json = new Gson().toJson(reportList);
//            try (FileWriter file = new FileWriter("hasil.json")) {
//                file.write(json);
//                file.flush();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            for (String s : reportList) {
//                System.out.println(s);
//            }
//        }

//        reportList.add("epoch;time;start;train_error;test_error;mape");
//        /////////////////////////////////////////////////////////////////////////////////////////////////
//        for (int i = 0; i < dataPathList.length; i++) {
//
//            String newPathName = dataPathList[i].concat(TRAIN_FILE);
//            normalizeData(newPathName, TEST_FILE, WizardMethodType.FeedForward, true, NormalizeRange.Zero2One);
//
//            newPathName = dataPathList[i].concat(TEST_FILE);
//            normalizeData(TRAIN_FILE, newPathName, WizardMethodType.FeedForward, false, NormalizeRange.Zero2One);
//
//            trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TRAIN, true, inputList[i], OUTPUT_VAR);
//            testSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TEST, true,  inputList[i], OUTPUT_VAR);
//            analyst.load(new File(NORMALIZE_SCRIPT_TEST));
//            reportList.add(dataPathList[i]);
//
//            System.out.println(dataPathList[i]);
//for (int j = 0; j < 10; j++) {
////            NeatRunner neatRunner = new NeatRunner(inputList[i], OUTPUT_VAR, trainingSet, testSet, 0.0005, new ActivationSteepenedSigmoid(), false);
//            NeatRunner neatRunner = new NeatRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.00001, new ActivationSteepenedSigmoid(), true);
           // neatRunner.runTrain(false);
//            neatRunner.runTest(analyst);
            //reportList.add(neatRunner.getMinReport().toString());
            //neatRunner.savePopulation("neat_"+j+".eg");

//            json = new Gson().toJson(reportList);
//            try (FileWriter file = new FileWriter("report.json")) {
//                file.write(json);
//                file.flush();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            for (String s : reportList) {
//                System.out.println(s);
//            }
////        }
//}

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//        for (int i = 0; i < dataPathList.length; i++) {
//            String newPathName = dataPathList[i].concat(TRAIN_FILE);
//            normalizeData(newPathName, TEST_FILE, WizardMethodType.FeedForward, true, NormalizeRange.Zero2One);
//
//            newPathName = dataPathList[i].concat(TEST_FILE);
//            normalizeData(TRAIN_FILE, newPathName, WizardMethodType.FeedForward, false, NormalizeRange.Zero2One);
//
//            trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TRAIN, true, inputList[i], OUTPUT_VAR);
//            testSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.DECIMAL_POINT, NORMALIZED_TEST, true,  inputList[i], OUTPUT_VAR);
//            analyst.load(new File(NORMALIZE_SCRIPT_TEST));
//            reportList.add(dataPathList[i]);
//            reportList.add("epoch;time;start;train_error;test_error;mape");
//            System.out.println(dataPathList[i]);
//
//            for (int j = inputList[i]; j <= 2 * inputList[i]; j++) {
//                System.out.println(j);
//        //GannRunner gannRunner = new GannRunner(inputList[i], OUTPUT_VAR, trainingSet, testSet, 0.001, j);
//        for (int j = 0; j < 10; j++) {
//            GannRunner gannRunner = new GannRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.0001, 9);
//            gannRunner.loadFile("gann.eg");
//            gannRunner.runTrain(false, false);
//            gannRunner.runTest(analyst);
//            reportList.add(gannRunner.getMinReport().toString());
//            gannRunner.saveGann("gann_"+j+".eg");
////            }
////
//            json = new Gson().toJson(reportList);
//            try (FileWriter file = new FileWriter("report.json")) {
//                file.write(json);
//                file.flush();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            for (String s : reportList) {
//                System.out.println(s);
//            }
//        }
//
//        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        GabpnnRunner gabpnnRunner = new GabpnnRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.001);
//        gabpnnRunner.runTrain(false);
//        gabpnnRunner.runTest(analyst);
//        reportList.add(gabpnnRunner.getMinReport().toString());

//        for (int j = 0; j < 10; j++) {
//            NewGabpnnRunner newGabpnnRunner = new NewGabpnnRunner(INPUT_VAR, OUTPUT_VAR, trainingSet, testSet, 0.001);
//            newGabpnnRunner.runTrain(false, false);
//            newGabpnnRunner.runTest(analyst);
//            reportList.add(newGabpnnRunner.getMinReport().toString());
//
//
//            newGabpnnRunner.saveGabpnn("gbpann_"+j+".eg");
////            }
////
//            json = new Gson().toJson(reportList);
//            try (FileWriter file = new FileWriter("report.json")) {
//                file.write(json);
//                file.flush();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            for (String s : reportList) {
//                System.out.println(s);
//            }
//        }



//        MLMethodFactory factory = new MLMethodFactory();
//        MLMethod method = factory.create(MLMethodFactory.TYPE_FEEDFORWARD,"?->TANH->7->TANH->?", INPUT_VAR, OUTPUT_VAR);
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

        for(int k = 0; k < 10; k++) {
            System.out.println(k);
            try {
                int startTestIndex = 950;

                analyst.load(new File(NORMALIZE_SCRIPT_TEST));
                CSVReader reader = new CSVReader(new FileReader(NORMALIZED_TEST));
                HeaderColumnNameMappingStrategy<Item> strategy = new HeaderColumnNameMappingStrategy<>();
                strategy.setType(Item.class);
                CsvToBean<Item> csvToBean = new CsvToBean<>();
                List<Item> itemList = csvToBean.parse(strategy, reader);

                reader = new CSVReader(new FileReader(ALL_FILE));
                strategy = new HeaderColumnNameMappingStrategy<>();
                strategy.setType(Item.class);
                csvToBean = new CsvToBean<>();
                List<Item> originList = csvToBean.parse(strategy, reader);


                BasicNetwork bpnn = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("bpnn_"+k+".eg"));
                BasicNetwork gann = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("network/gann.eg"));
                BasicNetwork gabpnn = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("gabpnn_"+k+".eg"));

                FileInputStream stream = new FileInputStream(new File("neat_"+k+".eg"));
                PersistNEATPopulation pnp = new PersistNEATPopulation();
                NEATPopulation population = (NEATPopulation) pnp.read(stream);
                NEATNetwork neat = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());


                StringBuilder gabpBuilder = new StringBuilder("GABPNN").append("\n");
                StringBuilder gaBuilder = new StringBuilder("GANN").append("\n");
                StringBuilder neatBuilder = new StringBuilder("NEAT").append("\n");

                ParamEstimator estimator = new ParamEstimator();
                ParamRegressor regressor = new OneToOneRegressor();
                //ParamRegressor regressor = new ZeroToOneRegressor();

                reportList.add("BPNN");
                reportList.add("H+1;H+1_pred;H+2;H+2_pred;H+3;H+3_pred;H+4;H+4_pred;H+5;H+5_pred;H+6;H+6_pred;H+7;H+7_pred;");

                MLRegression network = bpnn;

                //BPNN Prediction
                for (int i = startTestIndex; i <= originList.size() - 7; i++) {
                    List<Item> trainList = new ArrayList<>();
                    trainList.add(itemList.get(i - startTestIndex));
                    List<Item> originTrainList = new ArrayList<>(originList.subList(0, i + 1));
                    TechnicalAnalyst trainAnalyst = new TechnicalAnalyst(originTrainList, trainList, analyst);

                    Item item = trainList.get(0);
                    BasicMLData inputDataBp = new BasicMLData(new double[]{item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume(), item.getEma12()});
                    BasicMLData inputDataGa = new BasicMLData(new double[]{item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume(), item.getEma26()});

                    BasicMLData inputData = inputDataBp;

                    double bpOut = networkCompute(network, inputData);

                    AnalystField nextField = analyst.getScript().getNormalize().getNormalizedFields().get(9);
                    double ideal = nextField.deNormalize(itemList.get(i - startTestIndex).getNext1());
                    double predicted = nextField.deNormalize(bpOut);

                    StringBuilder bpBuilder = new StringBuilder(String.valueOf(ideal)).append(SEMICOL).append(predicted).append(SEMICOL);

                    for (int j = 0; j < 6; j++) {
                        item = trainList.get(j);
                        trainAnalyst.addItem(
                                item.getClose(),
                                //estimator.computeHigh(item),
                                regressor.computeHigh(item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume()),
                                //estimator.computeLow(item),
                                regressor.computeLow(item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume()),
                                bpOut,
                                //estimator.computeVolume(item)
                                regressor.computeVolume(item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume())
                        );
                        item = trainList.get(j + 1);
                        inputDataBp = new BasicMLData(new double[]{item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume(), item.getEma12()});
                        inputDataGa = new BasicMLData(new double[]{item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume(), item.getEma26()});

                        inputData = inputDataBp;

                        bpOut = networkCompute(network, inputData);
                        ideal = nextField.deNormalize(itemList.get(i - startTestIndex + j).getNext1());
                        predicted = nextField.deNormalize(bpOut);
                        bpBuilder.append(ideal).append(SEMICOL).append(predicted).append(SEMICOL);
                    }
                    reportList.add(bpBuilder.toString());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (String s : reportList) {
                System.out.println(s);
            }

            reportList.clear();
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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


    private static double networkCompute(MLRegression network, MLData input) {
        return network.compute(input).getData(0);
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

    private static void normalizeAll(String path, WizardMethodType methodType, NormalizeRange range) {
        File sourceFile = new File(path);
        File targetFile = new File(NORMALIZED_ALL);

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

        analyst.save(NORMALIZE_SCRIPT_ALL);
    }

    private static final String[] dataPathList = {
//            "data/vanilla/",
            "data/will-ema/ema12/",
//            "data/will-ema/ema12-26/",
//            "data/will-ema/ema26/",
//            "data/will-ema/ema-will5/",
//            "data/will-ema/ema-will14/",
//            "data/will-ema/will5/",
//            "data/will-ema/will5-14/",
//            "data/will-ema/will5-ema12/",
//            "data/will-ema/will5-ema26/",
//            "data/will-ema/will14/",
//            "data/will-ema/will14-ema12/",
//            "data/will-ema/will14-ema26/",
//            "data/will-ema/will-ema12/",
//            "data/will-ema/will-ema26/",
//            "data/will-ema/",
//            "data/gann_cny_ltc/cny/",
//            "data/gann_cny_ltc/ltc/",
//            "data/gann_cny_ltc/",
//            "data/"
    };

    private static final int[] inputList = {
//            5,
            6,
//            7,
//            6,
//            8,
//            8,
//            6,
//            7,
//            7,
//            7,
//            6,
//            7,
//            7,
//            8,
//            8,
//            9,
//            7,
//            7,
//            8,
//            11
    };
}

