import model.Item;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.PersistNEATPopulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by arief on 27/04/2017.
 */
public class ParamEstimator {

    private NEATNetwork highNetwork;
    private NEATNetwork lowNetwork;
    private NEATNetwork volumeNetwork;


    public ParamEstimator() throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(new File("network_estimate/high_neat.eg"));
        PersistNEATPopulation pnp = new PersistNEATPopulation();
        NEATPopulation population = (NEATPopulation) pnp.read(stream);
        highNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());

        stream = new FileInputStream(new File("network_estimate/low_neat.eg"));
        pnp = new PersistNEATPopulation();
        population = (NEATPopulation) pnp.read(stream);
        lowNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());

        stream = new FileInputStream(new File("network_estimate/volume_neat.eg"));
        pnp = new PersistNEATPopulation();
        population = (NEATPopulation) pnp.read(stream);
        volumeNetwork = (NEATNetwork) population.getCODEC().decode(population.getBestGenome());
    }

    public double computeHigh(Item item){
        return highNetwork.compute(genInputData(item)).getData(0);
    }

    public double computeLow(Item item){
        return lowNetwork.compute(genInputData(item)).getData(0);
    }

    public double computeVolume(Item item){
        return volumeNetwork.compute(genInputData(item)).getData(0);
    }

    private MLData genInputData(Item item){
        return new BasicMLData(new double[]{item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVolume()});
    }
}
