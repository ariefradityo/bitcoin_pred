import org.encog.app.analyst.EncogAnalyst;
import org.encog.app.analyst.script.normalize.AnalystField;
import org.encog.engine.network.activation.ActivationFunction;
import org.encog.ml.CalculateScore;
import org.encog.ml.MLError;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.ea.opp.CompoundOperator;
import org.encog.ml.ea.opp.selection.TournamentSelection;
import org.encog.ml.ea.opp.selection.TruncationSelection;
import org.encog.ml.ea.train.basic.TrainEA;
import org.encog.neural.neat.NEATCODEC;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.PersistNEATPopulation;
import org.encog.neural.neat.training.opp.*;
import org.encog.neural.neat.training.opp.links.MutatePerturbLinkWeight;
import org.encog.neural.neat.training.opp.links.MutateResetLinkWeight;
import org.encog.neural.neat.training.opp.links.SelectFixed;
import org.encog.neural.neat.training.opp.links.SelectProportion;
import org.encog.neural.neat.training.species.OriginalNEATSpeciation;
import org.encog.neural.networks.training.TrainingSetScore;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.TrainingSetUtil;

import java.io.*;

/**
 * Created by arief on 06/03/2017.
 */
public class NeatRunner extends Runner{

    private TrainEA train;
    private NEATPopulation population;
    private final String FILE_NAME = "neat.eg";

    private NEATNetwork neatNetwork;

    private boolean[] check = {false, false, false, false, false};


    public NeatRunner(int input, int output, MLDataSet trainingSet, MLDataSet testSet, double error, ActivationFunction function, boolean load){
        super(input, output, trainingSet, testSet, error);

        this.population = new NEATPopulation(input,output,100);
        population.setInitialConnectionDensity(1.0);// not required, but speeds training
        population.setNEATActivationFunction(function);
        population.reset();

        if (load) loadPopulation();

        CalculateScore score = new TrainingSetScore(trainingSet);

        train = new TrainEA(population, score);
        train.setSpeciation(new OriginalNEATSpeciation());


        //train.setSelection(new TruncationSelection(train, 0.3));
        train.setSelection(new TournamentSelection(train, 10));
        final CompoundOperator weightMutation = new CompoundOperator();

        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectFixed(1),
                        new MutatePerturbLinkWeight(0.02)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectFixed(2),
                        new MutatePerturbLinkWeight(0.02)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectFixed(3),
                        new MutatePerturbLinkWeight(0.02)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectProportion(0.02),
                        new MutatePerturbLinkWeight(0.02)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectFixed(1),
                        new MutatePerturbLinkWeight(1)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectFixed(2),
                        new MutatePerturbLinkWeight(1)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectFixed(3),
                        new MutatePerturbLinkWeight(1)));
        weightMutation.getComponents().add(
                0.1125,
                new NEATMutateWeights(new SelectProportion(0.02),
                        new MutatePerturbLinkWeight(1)));
        weightMutation.getComponents().add(
                0.03,
                new NEATMutateWeights(new SelectFixed(1),
                        new MutateResetLinkWeight()));
        weightMutation.getComponents().add(
                0.03,
                new NEATMutateWeights(new SelectFixed(2),
                        new MutateResetLinkWeight()));
        weightMutation.getComponents().add(
                0.03,
                new NEATMutateWeights(new SelectFixed(3),
                        new MutateResetLinkWeight()));
        weightMutation.getComponents().add(
                0.01,
                new NEATMutateWeights(new SelectProportion(0.02),
                        new MutateResetLinkWeight()));
        weightMutation.getComponents().finalizeStructure();

        train.setChampMutation(weightMutation);
        train.addOperation(0.5, new NEATCrossover());
        train.addOperation(0.494, weightMutation);
        train.addOperation(0.0005, new NEATMutateAddNode());
        train.addOperation(0.005, new NEATMutateAddLink());
        train.addOperation(0.0005, new NEATMutateRemoveLink());
        train.getOperators().finalizeStructure();

        train.setCODEC(new NEATCODEC());
    }


    protected void inTrain(int epoch, double error) {

        if(Double.compare(currentTestError, minError) == -1 || Double.compare(currentTestError, minError) == 0){
            savePopulation();
        }

        if(epoch > 0){
            neatNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());
            mlError = neatNetwork;
        }
    }

    public void runTrain(boolean load) {
        if(population.getBestGenome() != null) {
            neatNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());
        }
        train(train, neatNetwork);
        //savePopulation();
    }

    public void runTrain(boolean load, int limit){
        if(population.getBestGenome() != null) {
            neatNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());
        }
        train(train, limit, neatNetwork);
    }

    public void runTest(EncogAnalyst analyst) {
        loadPopulation();
        neatNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());
        test(neatNetwork, neatNetwork, analyst);
        minReport.setMape(getReport().getMape());
    }

    private void loadPopulation(){
        File neatFile = new File(FILE_NAME);
        try {
            FileInputStream stream = new FileInputStream(neatFile);
            PersistNEATPopulation pnp = new PersistNEATPopulation();
            population = (NEATPopulation) pnp.read(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void savePopulation(){
        savePopulation(FILE_NAME);
    }

    private void savePopulation(String fileName){
        File neatFile = new File(fileName);
        try {
            FileOutputStream stream = new FileOutputStream(neatFile);
            PersistNEATPopulation pnp = new PersistNEATPopulation();
            pnp.save(stream, population);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
