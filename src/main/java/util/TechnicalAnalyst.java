package util;

import model.Item;
import org.encog.app.analyst.EncogAnalyst;
import org.encog.app.analyst.script.normalize.AnalystField;

import java.util.List;

/**
 * Created by arief on 22/04/2017.
 */
public class TechnicalAnalyst {

    private List<Item> originList;

    private List<Item> itemList;
    private EncogAnalyst analyst;
    private AnalystField openField;
    private AnalystField highField;
    private AnalystField lowField;
    private AnalystField volumeField;
    private AnalystField closeField;
    private AnalystField will5Field;
    private AnalystField will14Field;
    private AnalystField ema12Field;
    private AnalystField ema26Field;


    public TechnicalAnalyst(List<Item> originList, List<Item> itemList, EncogAnalyst analyst) {
        this.itemList = itemList;
        this.analyst = analyst;
        this.originList = originList;

        openField = analyst.getScript().getNormalize().getNormalizedFields().get(0);
        highField = analyst.getScript().getNormalize().getNormalizedFields().get(1);
        lowField = analyst.getScript().getNormalize().getNormalizedFields().get(2);
        closeField = analyst.getScript().getNormalize().getNormalizedFields().get(3);
        volumeField = analyst.getScript().getNormalize().getNormalizedFields().get(4);
        will5Field = analyst.getScript().getNormalize().getNormalizedFields().get(5);
        will14Field = analyst.getScript().getNormalize().getNormalizedFields().get(6);
        ema12Field = analyst.getScript().getNormalize().getNormalizedFields().get(7);
        ema26Field = analyst.getScript().getNormalize().getNormalizedFields().get(8);

    }

    public void addItem(double open, double high, double low, double close, double volume) {


        Item item = new Item();
        item.setOpen(openField.deNormalize(open));
        item.setHigh(highField.deNormalize(high));
        item.setLow(lowField.deNormalize(low));
        item.setClose(closeField.deNormalize(close));
        item.setVolume(volumeField.deNormalize(volume));
        originList.add(item);

        item = new Item();
        item.setOpen(open);
        item.setHigh(high);
        item.setLow(low);
        item.setClose(close);
        item.setVolume(volume);
        itemList.add(item);

        calculateWill(originList.size() - 1, 5);
        calculateWill(originList.size() - 1, 14);
        calculateEma12(originList.size() - 1);
        calculateEma26(originList.size() - 1);
    }

    private void calculateWill(int index, int decay) {
        Item indexItem = originList.get(index);
        double curClose = indexItem.getClose();

        double maxHigh = indexItem.getHigh();
        double minLow = indexItem.getLow();
        for (int i = index - 1; i > index - decay; i--) {
            Item curItem = originList.get(i);

            if (curItem.getHigh() > maxHigh) {
                maxHigh = curItem.getHigh();
            }

            if (curItem.getLow() < minLow) {
                minLow = curItem.getLow();
            }
        }

        double will = (maxHigh - curClose) / (maxHigh - minLow) * -100;

        AnalystField field;
        if (decay == 5) {
            field = will5Field;
            indexItem.setWill5(will);
            itemList.get(itemList.size() - 1).setWill5(field.normalize(will));
        } else {
            field = will14Field;
            indexItem.setWill14(will);
            itemList.get(itemList.size() - 1).setWill14(field.normalize(will));
        }
    }

    private void calculateEma12(int index) {

        double multiplier = 0.153846154;
        Item indexItem = originList.get(index);
        Item previousItem = originList.get(index - 1);

        double indexClose = indexItem.getClose();
        double prevEma12 = previousItem.getEma12();

        double ema12 = (indexClose - prevEma12) * multiplier + prevEma12;
        indexItem.setEma12(ema12);
        itemList.get(itemList.size() - 1).setEma12(ema12Field.normalize(ema12));
    }

    private void calculateEma26(int index) {
        double multiplier = 0.074074074;
        Item indexItem = originList.get(index);
        Item previousItem = originList.get(index - 1);


        double indexClose = indexItem.getClose();
        double prevEma26 = previousItem.getEma26();

        double ema26 = (indexClose - prevEma26) * multiplier + prevEma26;
        indexItem.setEma26(ema26);
        itemList.get(itemList.size() - 1).setEma26(ema26Field.normalize(ema26));
    }
}
