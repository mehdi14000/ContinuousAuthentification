package MyEvaluation;

import engine.discreteValues.IntegerDiscreteValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crozes Mehdi and Zineb Idel-Ouali on 29/01/16
 */
public class extractorClient {

    public static ArrayList<Integer> dataCollectionUsers;
    public static final File datacollectionFile = new File("dataset.json");

    static {
        dataCollectionUsers = new ArrayList<>();
        dataCollectionUsers.add(2); // le premier est nul
        dataCollectionUsers.add(3);
        dataCollectionUsers.add(4);
       /* dataCollectionUsers.add(5);
        dataCollectionUsers.add(6);
        dataCollectionUsers.add(7);
        dataCollectionUsers.add(9);
        dataCollectionUsers.add(18);
        dataCollectionUsers.add(27);
        dataCollectionUsers.add(31);*/
    }

    public final static double ONE_WEEK = 60*60*24*7; //604800
    public final static double ONE_MINUTE = 60;

}