package evaluation.utils;

import engine.discreteValues.IntegerDiscreteValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class Constants {

    public static ArrayList<Integer> dataCollectionSubjects;
    public static final File datacollectionFile = new File("dataset.json");

    static {
        dataCollectionSubjects = new ArrayList<>();
        dataCollectionSubjects.add(8);
        dataCollectionSubjects.add(10);
        dataCollectionSubjects.add(12);
        dataCollectionSubjects.add(15);
        dataCollectionSubjects.add(19);
       /* dataCollectionSubjects.add(22);
        dataCollectionSubjects.add(26);
        dataCollectionSubjects.add(28);
        dataCollectionSubjects.add(34);
        dataCollectionSubjects.add(35);*/

    }

    public final static double ONE_WEEK = 60*60*24*7; //604800
    public final static double ONE_MINUTE = 60;

}
