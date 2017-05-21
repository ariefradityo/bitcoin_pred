import genetic.GabpnnTrainingScore;
import org.encog.app.analyst.EncogAnalyst;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.CalculateScore;
import org.encog.ml.MLEncodable;
import org.encog.ml.MLMethod;
import org.encog.ml.MethodFactory;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.ea.genome.GenomeFactory;
import org.encog.ml.ea.opp.selection.TournamentSelection;
import org.encog.ml.ea.population.BasicPopulation;
import org.encog.ml.ea.population.Population;
import org.encog.ml.ea.sort.GenomeComparator;
import org.encog.ml.ea.sort.MaximizeScoreComp;
import org.encog.ml.ea.sort.MinimizeScoreComp;
import org.encog.ml.ea.species.Species;
import org.encog.ml.genetic.MLEncodableCODEC;
import org.encog.ml.genetic.MLMethodGeneticAlgorithm;
import org.encog.ml.genetic.MLMethodGenome;
import org.encog.ml.genetic.MLMethodGenomeFactory;
import org.encog.ml.genetic.crossover.Splice;
import org.encog.ml.genetic.mutate.MutatePerturb;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.TrainingSetScore;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.obj.SerializeObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by arief on 15/04/2017.
 */
public class NewGabpnnRunner extends Runner {

    private BasicNetwork network;
    private MLMethodGeneticAlgorithm train;
    private final String FILE_NAME = "gabpnn.eg";
    private final String POP_FILE_NAME = "gabpnn_population.eg";

    private final int POP_SIZE = 40;


    public NewGabpnnRunner(int input, int output, MLDataSet trainingSet, MLDataSet testSet, double error) {
        super(input, output, trainingSet, testSet, error);

        network = new BasicNetwork();
        network.addLayer(new BasicLayer(new ActivationTANH(), true, INPUT));
        network.addLayer(new BasicLayer(new ActivationTANH(), true, 15));
        network.addLayer(new BasicLayer(new ActivationTANH(), true, OUTPUT));
        network.getStructure().finalizeStructure();
        network.reset(10);
    }

    @Override
    protected void inTrain(int epoch, double error) {
        if(Double.compare(currentTestError, minError) == -1 || Double.compare(currentTestError, minError) == 0){
            saveGabpnn(network);
            saveGannPopulation(train.getGenetic().getPopulation());
        }
    }

    @Override
    public void runTrain(boolean load) {
        runTrain(true, false);
    }

    public void runTrain(boolean load, boolean loadPopulation) {
        if (load) {
            network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(FILE_NAME));
        }

        CalculateScore score = new GabpnnTrainingScore(TRAIN_SET, TEST_SET);

        MethodFactory phenotypeFactory = new MethodFactory() {
            public MLMethod factor() {
                final BasicNetwork result = network;
                result.reset();
                return result;
            }
        };

        train = new MLMethodGeneticAlgorithm(phenotypeFactory, score, POP_SIZE);

        Population population = new BasicPopulation(POP_SIZE, null);
        final Species defaultSpecies = population.createSpecies();

        for (int i = 0; i < population.getPopulationSize(); i++) {
            final MLEncodable chromosomeNetwork = (MLEncodable) phenotypeFactory
                    .factor();
            final MLMethodGenome genome = new MLMethodGenome(chromosomeNetwork);
            defaultSpecies.add(genome);
        }
        defaultSpecies.setLeader(defaultSpecies.getMembers().get(0));

        if (loadPopulation) {
            try {
                population = (Population) SerializeObject.load(new File(POP_FILE_NAME));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        population.setGenomeFactory(new MLMethodGenomeFactory(phenotypeFactory,
                population));

        MLMethodGeneticAlgorithm.MLMethodGeneticAlgorithmHelper geneticTrainer = train.getGenetic();
        geneticTrainer.setPopulation(population);
        geneticTrainer.setCODEC(new MLEncodableCODEC());
        geneticTrainer.setEliteRate(0.3);

        GenomeComparator comp;
        if (score.shouldMinimize()) {
            comp = new MinimizeScoreComp();
        } else {
            comp = new MaximizeScoreComp();
        }
        geneticTrainer.setBestComparator(comp);
        geneticTrainer.setSelectionComparator(comp);

        // create the operators
        final int s = Math
                .max(defaultSpecies.getMembers().get(0).size() / 5, 1);

        geneticTrainer.getOperators().clear();

        geneticTrainer.addOperation(0.9, new Splice(s));
        geneticTrainer.addOperation(0.1, new MutatePerturb(1.0));
        geneticTrainer.setSelection(new TournamentSelection(geneticTrainer, 4));

        //geneticTrainer.setSelection(new TruncationSelection(geneticTrainer, ));

        train.setGenetic(geneticTrainer);

        train(train, 10, network);
    }

    @Override
    public void runTest(EncogAnalyst analyst) {
        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(FILE_NAME));
        test(network, network, analyst);
        minReport.setMape(getReport().getMape());
    }

    private void saveGabpnn(BasicNetwork network) {
        File neatFile = new File(FILE_NAME);
        EncogDirectoryPersistence.saveObject(neatFile, network);
    }

    private void saveGannPopulation(Population population){
        GenomeFactory factory = population.getGenomeFactory();
        population.setGenomeFactory(null);
        File popFile = new File(POP_FILE_NAME);
        try {
            SerializeObject.save(popFile,population);
        } catch (IOException e) {
            e.printStackTrace();
        }
        population.setGenomeFactory(factory);
    }


}
